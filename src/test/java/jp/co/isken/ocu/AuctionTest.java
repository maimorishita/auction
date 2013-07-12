package jp.co.isken.ocu;

import static org.junit.Assert.*;
import jp.co.isken.ocu.domain.Auction;
import jp.co.isken.ocu.domain.Market;
import jp.co.isken.ocu.domain.会員;
import jp.co.isken.ocu.util.Util;

import org.junit.BeforeClass;
import org.junit.Test;

public class AuctionTest {

	@BeforeClass
	public static void setup() {
		Market.setup();
	}

	@Test
	public void オークションには商品名とIDがある() {
		会員 m = new 会員("山田");
		Auction a = new Auction("ipad", m, Util.stringToDate("20120101120000"));
		assertEquals("ipad", a.getName());
		assertEquals("山田", a.getMember().getName());
	}
}
