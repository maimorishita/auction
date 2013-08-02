package jp.co.isken.ocu;

import static org.junit.Assert.assertEquals;

import java.util.List;

import jp.co.isken.ocu.domain.Market;
import jp.co.isken.ocu.domain.Member;
import jp.co.isken.ocu.service.‰ïˆõFacade;
import jp.co.isken.ocu.util.Util;

import org.junit.Before;
import org.junit.Test;

public class ‰ïˆõTest {

	@Before
	public void doBefore() {
		Market.setup();
		Market.setDate(Util.stringToDate("20130627000000"));
	}

	@Test
	public void ‰ïˆõ–¼‚ğ“o˜^‚·‚é() {
		Member a = new Member("X‰º4");
		assertEquals("X‰º4", a.getName());
	}

	@Test
	public void ‰ïˆõ‚ªæ“¾‚Å‚«‚é() throws Exception {
		Member a = new Member("X‰º1");
		‰ïˆõFacade.granto•i(a);
		a.o•i‚·‚é("ipad2", "", 200000);
		List<Member> m = Member.getAll();
		assertEquals(1, m.size());
	}

}
