package jp.co.isken.ocu;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;

import jp.co.isken.ocu.domain.Item;
import jp.co.isken.ocu.domain.Market;
import jp.co.isken.ocu.domain.Member;
import jp.co.isken.ocu.service.‰ïˆõFacade;
import jp.co.isken.ocu.util.MarcketTask;
import jp.co.isken.ocu.util.Util;
import jp.co.isken.ocu.util.“üDƒGƒ‰[;
import jp.co.isken.ocu.util.o•iƒGƒ‰[;

import org.junit.Before;
import org.junit.Test;

public class MarketTest {

	@Before
	public void setup() {
		Market.setup();
	}

	@Test
	public void ‚ªİ’è‚µæ“¾‚Å‚«‚é() {
		Date date = Util.stringToDate("20130101111111");
		Market.setDate(date);
		assertEquals(date, Market.getDate());
	}

	@Test
	public void w’è‚ÉÅI“üD‚ğ—D‚Æ‚·‚é() throws o•iƒGƒ‰[, “üDƒGƒ‰[ {
		Market.setDate(Util.stringToDate("20130625000000"));
		Member X‰º = new Member("X‰º2");
		‰ïˆõFacade.grant“üD(X‰º);
		‰ïˆõFacade.granto•i(X‰º);
		Member ×àV = new Member("×àV");
		‰ïˆõFacade.grant“üD(×àV);
		Member X–Ø = new Member("X–Ø");
		‰ïˆõFacade.grant“üD(X–Ø);
		X‰º.o•i‚·‚é("MacBookAir", "20130630000000", 200000);
		X–Ø.“üD‚·‚é("MacBookAir", 100000, "20130629010000");
		×àV.“üD‚·‚é("MacBookAir", 200000, "20130629050000");
		X–Ø.“üD‚·‚é("MacBookAir", 300000, "20130629070000");
		Market.setDate(Util.stringToDate("20130630000000"));
		MarcketTask task  = new MarcketTask();
		task.run();
		Item target = Item.getItem("MacBookAir");
		assertEquals("X–Ø", target.getSuccessfulTender().get“üDÒ().getName());
		assertEquals(300000, target.getSuccessfulTender().getAmount());
	}
	
	@Test
	public void Å’á—D‰¿Ši‚É–‚½‚È‚¢ê‡‚ÍƒLƒƒƒ“ƒZƒ‹‚Æ‚·‚é() throws o•iƒGƒ‰[, “üDƒGƒ‰[ {
		Market.setDate(Util.stringToDate("20130625000000"));
		Member X‰º = new Member("X‰º2");
		‰ïˆõFacade.grant“üD(X‰º);
		‰ïˆõFacade.granto•i(X‰º);
		Member ×àV = new Member("×àV");
		‰ïˆõFacade.grant“üD(×àV);
		Member X–Ø = new Member("X–Ø");
		‰ïˆõFacade.grant“üD(X–Ø);
		X‰º.o•i‚·‚é("MacBookAir", "20130630000000", 200000);
		X–Ø.“üD‚·‚é("MacBookAir", 100000, "20130629010000");
		Market.setDate(Util.stringToDate("20130630000000"));
		MarcketTask task  = new MarcketTask();
		task.run();
		Item target = Item.getItem("MacBookAir");
		assertEquals(null, target.getSuccessfulTender());
		assertThat(target.isCancel(), is(true));
	}

	/**
	 * ƒVƒiƒŠƒIƒeƒXƒg
	 */
	@Test
	public void ƒI[ƒNƒVƒ‡ƒ“() {
		Market.setDate(Util.stringToDate("20130625000000"));
		Member X‰º = new Member("X‰º2");
		‰ïˆõFacade.grant“üD(X‰º);
		‰ïˆõFacade.granto•i(X‰º);

		Member ×àV = new Member("×àV");
		‰ïˆõFacade.grant“üD(×àV);

		Member X–Ø = new Member("X–Ø");
		‰ïˆõFacade.grant“üD(X–Ø);

		try {
			X–Ø.o•i‚·‚é("MacBookAir", "20130630000000", 200000);
			fail();
		} catch (o•iƒGƒ‰[ e) {
			assertEquals("o•iŒ ŒÀ‚ª‚ ‚è‚Ü‚¹‚ñB", e.getMessage());
		}

		try {
			X‰º.o•i‚·‚é("MacBookAir", "20130630000000", 200000);
		} catch (o•iƒGƒ‰[ e) {
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

		Item target = Item.getItem("MacBookAir");
		Market.setDate(Util.stringToDate("20130630000000"));
		MarcketTask task  = new MarcketTask();
		task.run();
		assertEquals("X–Ø", target.getSuccessfulTender().get“üDÒ().getName());
		assertEquals(300000, target.getSuccessfulTender().getAmount());
	}
}
