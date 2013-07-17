package jp.co.isken.ocu;

import static org.junit.Assert.assertEquals;

import java.util.List;

import jp.co.isken.ocu.domain.Market;
import jp.co.isken.ocu.domain.Tender;
import jp.co.isken.ocu.domain.‰ïˆõ;
import jp.co.isken.ocu.util.Util;

import org.junit.Before;
import org.junit.Test;


public class TenderTest {

	@Before
	public void setup(){
		Market.setup();
	}

	@Test
	public void ƒI[ƒNƒVƒ‡ƒ“”Ô†‚©‚ç“üD‚ğ•Ô‚·() throws Exception{
		Market.setDate(Util.stringToDate("20130627000000"));
		‰ïˆõ a = new ‰ïˆõ("X‰º3");
		a.granto•i();
		a.o•i‚·‚é("ipad2", "20130630000000");

		‰ïˆõ b = new ‰ïˆõ("‚Ù‚»‚´‚í");
		b.grant“üD();
		b.“üD‚·‚é("ipad2", 100, "20130629000000");

		List<Tender> rs = Tender.getTenders("ipad2");
		assertEquals(1, rs.size());
		assertEquals(100L, rs.get(0).getAmount());
		assertEquals("‚Ù‚»‚´‚í", rs.get(0).get“üDÒ().getName());

	}

}
