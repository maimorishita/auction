package jp.co.isken.ocu;

import static org.junit.Assert.assertEquals;

import java.util.List;

import jp.co.isken.ocu.domain.Market;
import jp.co.isken.ocu.domain.Tender;
import jp.co.isken.ocu.domain.会員;
import jp.co.isken.ocu.util.Util;

import org.junit.Before;
import org.junit.Test;


public class TenderTest {

	@Before
	public void setup(){
		Market.setup();
	}

	@Test
	public void オークション番号から入札を返す() throws Exception{
		Market.setDate(Util.stringToDate("20130627000000"));
		会員 a = new 会員("森下3");
		a.grant出品();
		a.出品する("ipad2", "20130630000000");

		会員 b = new 会員("ほそざわ");
		b.grant入札();
		b.入札する("ipad2", 100, "20130629000000");

		List<Tender> rs = Tender.getTenders("ipad2");
		assertEquals(1, rs.size());
		assertEquals(100L, rs.get(0).getAmount());
		assertEquals("ほそざわ", rs.get(0).get入札者().getName());

	}

}
