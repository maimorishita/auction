package jp.co.isken.ocu;

import static org.junit.Assert.*;

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
