package jp.co.isken.ocu;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;

import jp.co.isken.ocu.domain.Item;
import jp.co.isken.ocu.domain.Market;
import jp.co.isken.ocu.domain.Member;
import jp.co.isken.ocu.service.会員Facade;
import jp.co.isken.ocu.util.MarcketTask;
import jp.co.isken.ocu.util.Util;
import jp.co.isken.ocu.util.入札エラー;
import jp.co.isken.ocu.util.出品エラー;

import org.junit.Before;
import org.junit.Test;

public class MarketTest {

	@Before
	public void setup() {
		Market.setup();
	}

	@Test
	public void 時刻が設定し取得できる() {
		Date date = Util.stringToDate("20130101111111");
		Market.setDate(date);
		assertEquals(date, Market.getDate());
	}

	@Test
	public void 指定時刻に最終入札を落札とする() throws 出品エラー, 入札エラー {
		Market.setDate(Util.stringToDate("20130625000000"));
		Member 森下 = new Member("森下2");
		会員Facade.grant入札(森下);
		会員Facade.grant出品(森下);
		Member 細澤 = new Member("細澤");
		会員Facade.grant入札(細澤);
		Member 森木 = new Member("森木");
		会員Facade.grant入札(森木);
		森下.出品する("MacBookAir", "20130630000000", 200000);
		森木.入札する("MacBookAir", 100000, "20130629010000");
		細澤.入札する("MacBookAir", 200000, "20130629050000");
		森木.入札する("MacBookAir", 300000, "20130629070000");
		Market.setDate(Util.stringToDate("20130630000000"));
		MarcketTask task  = new MarcketTask();
		task.run();
		Item target = Item.getItem("MacBookAir");
		assertEquals("森木", target.getSuccessfulTender().get入札者().getName());
		assertEquals(300000, target.getSuccessfulTender().getAmount());
	}
	
	@Test
	public void 最低落札価格に満たない場合はキャンセルとする() throws 出品エラー, 入札エラー {
		Market.setDate(Util.stringToDate("20130625000000"));
		Member 森下 = new Member("森下2");
		会員Facade.grant入札(森下);
		会員Facade.grant出品(森下);
		Member 細澤 = new Member("細澤");
		会員Facade.grant入札(細澤);
		Member 森木 = new Member("森木");
		会員Facade.grant入札(森木);
		森下.出品する("MacBookAir", "20130630000000", 200000);
		森木.入札する("MacBookAir", 100000, "20130629010000");
		Market.setDate(Util.stringToDate("20130630000000"));
		MarcketTask task  = new MarcketTask();
		task.run();
		Item target = Item.getItem("MacBookAir");
		assertEquals(null, target.getSuccessfulTender());
		assertThat(target.isCancel(), is(true));
	}

	/**
	 * シナリオテスト
	 */
	@Test
	public void オークション() {
		Market.setDate(Util.stringToDate("20130625000000"));
		Member 森下 = new Member("森下2");
		会員Facade.grant入札(森下);
		会員Facade.grant出品(森下);

		Member 細澤 = new Member("細澤");
		会員Facade.grant入札(細澤);

		Member 森木 = new Member("森木");
		会員Facade.grant入札(森木);

		try {
			森木.出品する("MacBookAir", "20130630000000", 200000);
			fail();
		} catch (出品エラー e) {
			assertEquals("出品権限がありません。", e.getMessage());
		}

		try {
			森下.出品する("MacBookAir", "20130630000000", 200000);
		} catch (出品エラー e) {
			fail();
		}

		try {
			森下.入札する("MacBookAir", 100000, "20130629000000");
		} catch (入札エラー e) {
			assertEquals("出品者は入札できません。", e.getMessage());
		}

		try {
			森木.入札する("MacBookAir", 100000, "20130629010000");
		} catch (入札エラー e) {
			System.out.println(e.getMessage());
		}

		try {
			細澤.入札する("MacBookAir", 100000, "20130629030000");
			fail();
		} catch (入札エラー e) {
			assertEquals("最高入札額よりも高い金額を指定してください。", e.getMessage());
		}

		try {
			細澤.入札する("MacBookAir", 200000, "20130629050000");
		} catch (入札エラー e) {
			fail();
		}

		try {
			森木.入札する("MacBookAir", 300000, "20130629070000");
		} catch (入札エラー e) {
			fail();
		}

		/**
		 * 時刻を2013/6/30に進める
		 */
		Market.setDate(Util.stringToDate("20130630000000"));

		try {
			細澤.入札する("MacBookAir", 400000, "2013063000001");
			fail();
		} catch (入札エラー e) {
			assertEquals("入札時間外です。", e.getMessage());
		}

		Item target = Item.getItem("MacBookAir");
		Market.setDate(Util.stringToDate("20130630000000"));
		MarcketTask task  = new MarcketTask();
		task.run();
		assertEquals("森木", target.getSuccessfulTender().get入札者().getName());
		assertEquals(300000, target.getSuccessfulTender().getAmount());
	}
}
