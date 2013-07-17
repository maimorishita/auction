package jp.co.isken.ocu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import jp.co.isken.ocu.domain.Auction;
import jp.co.isken.ocu.domain.Market;
import jp.co.isken.ocu.domain.会員;
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
	public void 会員が取得できる() throws Exception {
		会員 a = new 会員("森下1");
		a.grant出品();
		a.出品する("ipad2", "");
		List<会員> m = Market.getMembers();
		assertEquals(1, m.size());
	}

	@Test
	public void 出品物名からオークションを取得できる() throws Exception {
		会員 a = new 会員("森下1");
		a.grant出品();
		a.出品する("ipad2", "");
		Auction m = Market.getAuction("ipad2");
		assertEquals("ipad2", m.getName());
	}

	@Test
	public void 時刻が設定し取得できる() {
		Date date = Util.stringToDate("20130101111111");
		Market.setDate(date);
		assertEquals(date, Market.getDate());
	}

	/**
	 * シナリオテスト
	 */
	@Test
	public void オークション() {
		Market.setDate(Util.stringToDate("20130625000000"));
		会員 森下 = new 会員("森下2");
		森下.grant入札();
		森下.grant出品();

		会員 細澤 = new 会員("細澤");
		細澤.grant入札();

		会員 森木 = new 会員("森木");
		森木.grant入札();

		try {
			森木.出品する("MacBookAir", "20130630000000");
			fail();
		} catch (出品エラー e) {
			assertEquals("出品権限がありません。", e.getMessage());
		}

		try {
			森下.出品する("MacBookAir", "20130630000000");
		}catch (出品エラー e) {
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

		Auction target = Market.getAuction("MacBookAir");
		assertEquals("森木", target.getLastTender().get入札者().getName());
		assertEquals(300000, target.getLastTender().getAmount());
	}
}
