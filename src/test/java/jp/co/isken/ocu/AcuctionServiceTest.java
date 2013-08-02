package jp.co.isken.ocu;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import jp.co.isken.ocu.domain.Market;
import jp.co.isken.ocu.domain.Tender;
import jp.co.isken.ocu.domain.Member;
import jp.co.isken.ocu.service.AcuctionService;
import jp.co.isken.ocu.service.sample;
import jp.co.isken.ocu.service.‰ïˆõFacade;
import jp.co.isken.ocu.util.Util;
import jp.co.isken.ocu.util.“üDƒGƒ‰[;
import jp.co.isken.ocu.util.o•iƒGƒ‰[;

import org.junit.Before;
import org.junit.Test;

public class AcuctionServiceTest {
	
	@Before
	public void setup() {
		Market.setup();
	}

	@Test
	public void ƒgƒŒƒ“ƒh‚ğæ“¾‚·‚é() {
		Market.setDate(Util.stringToDate("20130625000000"));
		Member X‰º = new Member("X‰º2");
		‰ïˆõFacade.grant“üD(X‰º);
		‰ïˆõFacade.granto•i(X‰º);

		Member ×àV = new Member("×àV");
		‰ïˆõFacade.grant“üD(×àV);

		Member X–Ø = new Member("X–Ø");
		‰ïˆõFacade.grant“üD(X–Ø);

		try {
			X‰º.o•i‚·‚é("MacBookAir", "20130630000000", 200000);
		} catch (o•iƒGƒ‰[ e) {
			fail();
		}

		try {
			X–Ø.“üD‚·‚é("MacBookAir", 100000, "20130629010000");
		} catch (“üDƒGƒ‰[ e) {
			System.out.println(e.getMessage());
		}

		try {
			×àV.“üD‚·‚é("MacBookAir", 250000, "20130629050000");
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

		List<Tender> targets = AcuctionService.getTrande("MacBookAir");

		for (Tender tender : targets) {
			System.out.println(tender.getDate() + "¨"
					+ tender.get“üDÒ().getName() + ":" + tender.getAmount());
		}

		assertThat(targets.get(0).getDate() + "¨"
				+ targets.get(0).get“üDÒ().getName() + ":"
				+ targets.get(0).getAmount(),
				is("Sat Jun 29 01:00:00 JST 2013¨X–Ø:100000"));
		assertThat(targets.get(1).getDate() + "¨"
				+ targets.get(1).get“üDÒ().getName() + ":"
				+ targets.get(1).getAmount(),
				is("Sat Jun 29 05:00:00 JST 2013¨×àV:250000"));
		assertThat(targets.get(2).getDate() + "¨"
				+ targets.get(2).get“üDÒ().getName() + ":"
				+ targets.get(2).getAmount(),
				is("Sat Jun 29 07:00:00 JST 2013¨X–Ø:300000"));
		assertThat(targets.size(), is(3));

		/**
		 * –¢ƒeƒXƒg
		 */
		sample s = new sample();
		s.main(targets);

	}

}
