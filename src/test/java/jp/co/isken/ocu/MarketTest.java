package jp.co.isken.ocu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class MarketTest {

	@BeforeClass
	public static void setup() {
		Market.setup();
	}

	@Test
	public void ‰ïˆõ‚ªæ“¾‚Å‚«‚é() throws Exception {
		‰ïˆõ a = new ‰ïˆõ("X‰º1");
		a.granto•i();
		a.o•i‚·‚é("ipad2", "");
		List<‰ïˆõ> m = Market.getMembers();
		assertEquals(1, m.size());
	}

	@Test
	public void ‚ªİ’è‚µæ“¾‚Å‚«‚é() {
		Date date = Util.stringToDate("20130101111111");
		Market.setDate(date);
		assertEquals(date, Market.getDate());
	}

	/**
	 * ƒVƒiƒŠƒIƒeƒXƒg
	 */
	@Test
	public void ƒI[ƒNƒVƒ‡ƒ“() {
		Market.setDate(Util.stringToDate("20130625000000"));

		‰ïˆõ X‰º = new ‰ïˆõ("X‰º2");
		X‰º.grant“üD();
		X‰º.granto•i();

		‰ïˆõ ×àV = new ‰ïˆõ("×àV");
		×àV.grant“üD();

		‰ïˆõ X–Ø = new ‰ïˆõ("X–Ø");
		X–Ø.grant“üD();

		try {
			X–Ø.o•i‚·‚é("MacBookAir", "20130630000000");
			fail();
		} catch (o•iƒGƒ‰[ e) {
			assertEquals("o•iŒ ŒÀ‚ª‚ ‚è‚Ü‚¹‚ñB", e.getMessage());
		}

		try {
			X‰º.o•i‚·‚é("MacBookAir", "20130630000000");
		}catch (o•iƒGƒ‰[ e) {
			fail();
		}

		try {
			X‰º.“üD‚·‚é("MacBookAir", 100000, "20130629000000");
		} catch (“üDƒGƒ‰[ e) {
			assertEquals("o•iÒ‚Í“üD‚Å‚«‚Ü‚¹‚ñB", e.getMessage());
		}

		try {
			X–Ø.“üD‚·‚é("MacBookAir", 100000, "20130629010000");
		} catch (“üDƒGƒ‰[ e) {
			System.out.println(e.getMessage());
		}

		try {
			×àV.“üD‚·‚é("MacBookAir", 100000, "20130629030000");
			fail();
		} catch (“üDƒGƒ‰[ e) {
			assertEquals("Å‚“üDŠz‚æ‚è‚à‚‚¢‹àŠz‚ğw’è‚µ‚Ä‚­‚¾‚³‚¢B", e.getMessage());
		}

		try {
			×àV.“üD‚·‚é("MacBookAir", 200000, "20130629050000");
		} catch (“üDƒGƒ‰[ e) {
			fail();
		}

		try {
			X–Ø.“üD‚·‚é("MacBookAir", 300000, "20130629070000");
		} catch (“üDƒGƒ‰[ e) {
			fail();
		}

		/**
		 * ‚ğ2013/6/30‚Éi‚ß‚é
		 */
		Market.setDate(Util.stringToDate("20130630000000"));

		try {
			×àV.“üD‚·‚é("MacBookAir", 400000, "2013063000001");
			fail();
		} catch (“üDƒGƒ‰[ e) {
			assertEquals("“üDŠÔŠO‚Å‚·B", e.getMessage());
		}

		Auction target = Market.getAuction("MacBookAir");
		assertEquals("X–Ø", target.getLastTender().get“üDÒ().getName());
		assertEquals(300000, target.getLastTender().getAmount());
	}
}
