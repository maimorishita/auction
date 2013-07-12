package jp.co.isken.ocu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

public class 会員Test {

	@BeforeClass
	public static void データ初期化() {
		Market.setup();
		Market.setDate(Util.stringToDate("20130627000000"));
	}

	@Test
	public void 会員名を登録する() {
		会員 a = new 会員("森下4");
		assertEquals("森下4", a.getName());
	}

	@Test
	public void 会員を出品者として登録する() {
		会員 a = new 会員("森下5");
		assertEquals(false, a.is出品者());
		a.grant出品();
		assertEquals(true, a.is出品者());

	}

	@Test
	public void 会員を入札者として登録する() {
		会員 a = new 会員("森下6");
		assertEquals(false, a.is入札者());
		a.grant入札();
		assertEquals(true, a.is入札者());
	}

	@Test
	public void オークションに出品する() throws 出品エラー {
		会員 a = new 会員("森下7");
		a.grant出品();
		a.出品する("ipad1", "20130630120000");
		assertEquals(1, a.getAuctions().size());
		assertEquals("ipad1", a.getAuctions().get(0).getName());

		Date targetdate = Util.stringToDate("20130630120000");
		assertEquals(targetdate, a.getAuctions().get(0).getTimeLimit());
		Market.setup();
	}

	@Test
	public void 出品権限がないのにオークションに出品したらエラー() {
		会員 a = new 会員("森下8");
		try {
			a.出品する("ipad2", "");
			fail("exception");
		} catch (出品エラー e) {
			assertEquals("出品権限がありません。", e.getMessage());
		}
	}

	@Test
	public void 入札できる() throws 出品エラー {
		会員 出品者 = new 会員("出品者");
		出品者.grant出品();
		出品者.出品する("ipad2", "20130630000000");
		会員 a = new 会員("森下9");
		a.grant入札();
		try {
			a.入札する("ipad2", 100, "20130629000000");
		} catch (入札エラー e) {
			fail("exe");
		}
	}

	@Test
	public void 入札時間外のためエラー() throws 出品エラー {
		会員 出品者 = new 会員("出品者");
		出品者.grant出品();
		出品者.出品する("ipad2", "20130630000000");
		会員 a = new 会員("森下9");
		a.grant入札();
		try {
			a.入札する("ipad2", 100, "20130730000000");
			fail();
		} catch (入札エラー e) {
			assertEquals("入札時間外です。", e.getMessage());
		}
	}

	@Test
	public void 入札権限がないのにオークションに入札したらエラー() {
		会員 a = new 会員("森下9");
		try {
			a.入札する("ipad2", 100, "");
			fail("exception");
		} catch (入札エラー e) {
			assertEquals("入札権限がありません。", e.getMessage());
		}
	}

	@Test
	public void オークションの落札者がわかる() throws Exception {
		会員 a = new 会員("森下10");
		a.grant出品();
		a.出品する("ipad3", "20130630000000");

		会員 b = new 会員("ほそざわ");
		b.grant入札();
		b.入札する("ipad3", 100, "20130629000000");

		会員 c = new 会員("もりき");
		c.grant入札();
		c.入札する("ipad3", 200, "20130629010000");

		Auction auction = Market.getAuction("ipad3");
		assertEquals(auction.getLastTender().get入札者().getName(), "もりき");
	}

	@Test
	public void 出品者が自ら入札できない() throws 出品エラー {
		会員 a = new 会員("たーげっと");
		a.grant出品();
		a.grant入札();
		a.出品する("ipad4", "");
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

		会員 a = new 会員("森下s");
		a.grant出品();
		a.出品する("ipadmai", "20130630000000");

		会員 b = new 会員("ほそざわa");
		b.grant入札();
		try {
			b.入札する("ipadmai", 100, "20130628000000");
		} catch (入札エラー e1) {
			fail();
		}
		会員 c = new 会員("もりきb");
		c.grant入札();
		try {
			c.入札する("ipadmai", 100, "20130629000000");
			fail();
		} catch (入札エラー e) {
			assertEquals("最高入札額よりも高い金額を指定してください。", e.getMessage());
		}

		Auction auction = Market.getAuction("ipadmai");
		assertEquals(auction.getLastTender().get入札者().getName(), "ほそざわa");
	}

	@Test
	public void 同じ商品名は出品できない() {
		会員 a = new 会員("森下s");
		a.grant出品();
		try {
			a.出品する("ipad3", "");
		} catch (出品エラー e) {
			fail();
		}

		会員 b = new 会員("ほそざわa");
		b.grant出品();
		try {
			b.出品する("ipad3", "");
			fail();
		} catch (出品エラー e) {
			assertEquals("商品名が重複しています。", e.getMessage());
		}
	}
}
