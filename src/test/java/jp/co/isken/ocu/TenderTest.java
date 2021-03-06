package jp.co.isken.ocu;

import static org.junit.Assert.assertEquals;

import java.util.List;

import jp.co.isken.ocu.domain.Market;
import jp.co.isken.ocu.domain.Tender;
import jp.co.isken.ocu.domain.Member;
import jp.co.isken.ocu.service.会員Facade;
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
		Member a = new Member("森下3");
		会員Facade.grant出品(a);
		a.出品する("ipad2", "20130630000000", 200000);

		Member b = new Member("ほそざわ");
		会員Facade.grant入札(b);
		b.入札する("ipad2", 100, "20130629000000");

		List<Tender> rs = Tender.getTenders("ipad2");
		assertEquals(1, rs.size());
		assertEquals(100L, rs.get(0).getAmount());
		assertEquals("ほそざわ", rs.get(0).get入札者().getName());

	}

}
