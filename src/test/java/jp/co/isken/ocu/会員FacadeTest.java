package jp.co.isken.ocu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;

import jp.co.isken.ocu.domain.Item;
import jp.co.isken.ocu.domain.Market;
import jp.co.isken.ocu.domain.Member;
import jp.co.isken.ocu.service.会員Facade;
import jp.co.isken.ocu.util.Util;
import jp.co.isken.ocu.util.入札エラー;
import jp.co.isken.ocu.util.出品エラー;

import org.junit.Before;
import org.junit.Test;

public class 会員FacadeTest {
	
	@Before
	public void doBefore() {
		Market.setup();
		Market.setDate(Util.stringToDate("20130627000000"));
	}

	@Test
	public void 会員を出品者として登録する() {
		Member a = new Member("森下5");
		assertEquals(false, a.is出品者());
		会員Facade.grant出品(a);
		assertEquals(true, a.is出品者());

	}

	@Test
	public void 会員を入札者として登録する() {
		Member a = new Member("森下6");
		assertEquals(false, a.is入札者());
		会員Facade.grant入札(a);
		assertEquals(true, a.is入札者());
	}

	@Test
	public void オークションに出品する() throws 出品エラー {
		Member a = new Member("森下7");
		会員Facade.grant出品(a);
		a.出品する("ipad1", "20130630120000", 200000);
		assertEquals(1, a.getItems().size());
		assertEquals("ipad1", a.getItems().get(0).getName());

		Date targetdate = Util.stringToDate("20130630120000");
		assertEquals(targetdate, a.getItems().get(0).getTimeLimit());
		Market.setup();
	}

	@Test
	public void 出品権限がないのにオークションに出品したらエラー() {
		Member a = new Member("森下8");
		try {
			a.出品する("ipad2", "", 200000);
			fail("exception");
		} catch (出品エラー e) {
			assertEquals("出品権限がありません。", e.getMessage());
		}
	}

	@Test
	public void 入札できる() throws 出品エラー {
		Member 出品者 = new Member("出品者");
		会員Facade.grant出品(出品者);
		出品者.出品する("ipad2", "20130630000000", 200000);
		Member a = new Member("森下9");
		会員Facade.grant入札(a);
		try {
			a.入札する("ipad2", 100, "20130629000000");
		} catch (入札エラー e) {
			fail("exe");
		}
	}

	@Test
	public void 入札時間外のためエラー() throws 出品エラー {
		Member 出品者 = new Member("出品者");
		会員Facade.grant出品(出品者);
		出品者.出品する("ipad2", "20130630000000", 200000);
		Member a = new Member("森下9");
		会員Facade.grant入札(a);
		try {
			a.入札する("ipad2", 100, "20130730000000");
			fail();
		} catch (入札エラー e) {
			assertEquals("入札時間外です。", e.getMessage());
		}
	}

	@Test
	public void 入札権限がないのにオークションに入札したらエラー() {
		Member a = new Member("森下9");
		try {
			a.入札する("ipad2", 100, "");
			fail("exception");
		} catch (入札エラー e) {
			assertEquals("入札権限がありません。", e.getMessage());
		}
	}

	@Test
	public void オークションの落札者がわかる() throws Exception {
		Member a = new Member("森下10");
		会員Facade.grant出品(a);
		a.出品する("ipad3", "20130630000000", 200000);

		Member b = new Member("ほそざわ");
		会員Facade.grant入札(b);
		b.入札する("ipad3", 100, "20130629000000");

		Member c = new Member("もりき");
		会員Facade.grant入札(c);
		c.入札する("ipad3", 200, "20130629010000");

		Item item = Item.getItem("ipad3");
		assertEquals(item.getLastTender().get入札者().getName(), "もりき");
	}

	@Test
	public void 出品者が自ら入札できない() throws 出品エラー {
		Member a = new Member("たーげっと");
		会員Facade.grant出品(a);
		会員Facade.grant入札(a);
		a.出品する("ipad4", "", 200000);
		try {
			a.入札する("ipad4", 100, "");
			fail("Exception is expected");
		} catch (入札エラー e) {
			assertEquals("出品者は入札できません。", e.getMessage());
		}
	}

	@Test
	public void 最新の入札額よりも高い金額でないと入札できない() throws 出品エラー {
		Market.setup();

		Member a = new Member("森下s");
		会員Facade.grant出品(a);
		a.出品する("ipadmai", "20130630000000", 200000);

		Member b = new Member("ほそざわa");
		会員Facade.grant入札(b);
		try {
			b.入札する("ipadmai", 100, "20130628000000");
		} catch (入札エラー e1) {
			fail();
		}
		Member c = new Member("もりきb");
		会員Facade.grant入札(c);
		try {
			c.入札する("ipadmai", 100, "20130629000000");
			fail();
		} catch (入札エラー e) {
			assertEquals("最高入札額よりも高い金額を指定してください。", e.getMessage());
		}

		Item item = Item.getItem("ipadmai");
		assertEquals(item.getLastTender().get入札者().getName(), "ほそざわa");
	}

	@Test
	public void 同じ商品名は出品できない() {
		Member a = new Member("森下s");
		会員Facade.grant出品(a);
		try {
			a.出品する("ipad3", "", 200000);
		} catch (出品エラー e) {
			fail();
		}

		Member b = new Member("ほそざわa");
		会員Facade.grant出品(b);
		try {
			b.出品する("ipad3", "", 200000);
			fail();
		} catch (出品エラー e) {
			assertEquals("商品名が重複しています。", e.getMessage());
		}
	}


}
