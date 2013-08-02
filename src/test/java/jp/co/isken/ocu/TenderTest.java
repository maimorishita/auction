package jp.co.isken.ocu;

import static org.junit.Assert.assertEquals;

import java.util.List;

import jp.co.isken.ocu.domain.Market;
import jp.co.isken.ocu.domain.Tender;
import jp.co.isken.ocu.domain.Member;
import jp.co.isken.ocu.service.���Facade;
import jp.co.isken.ocu.util.Util;

import org.junit.Before;
import org.junit.Test;


public class TenderTest {

	@Before
	public void setup(){
		Market.setup();
	}

	@Test
	public void �I�[�N�V�����ԍ�������D��Ԃ�() throws Exception{
		Market.setDate(Util.stringToDate("20130627000000"));
		Member a = new Member("�X��3");
		���Facade.grant�o�i(a);
		a.�o�i����("ipad2", "20130630000000", 200000);

		Member b = new Member("�ق�����");
		���Facade.grant���D(b);
		b.���D����("ipad2", 100, "20130629000000");

		List<Tender> rs = Tender.getTenders("ipad2");
		assertEquals(1, rs.size());
		assertEquals(100L, rs.get(0).getAmount());
		assertEquals("�ق�����", rs.get(0).get���D��().getName());

	}

}
