package jp.co.isken.ocu;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;


public class TenderTest {

	@BeforeClass
	public static void setup(){
		Market.setup();
	}

	@Test
	public void �I�[�N�V�����ԍ�������D��Ԃ�() throws Exception{
		Market.setDate(Util.stringToDate("20130627000000"));
		��� a = new ���("�X��3");
		a.grant�o�i();
		a.�o�i����("ipad2", "20130630000000");

		��� b = new ���("�ق�����");
		b.grant���D();
		b.���D����("ipad2", 100, "20130629000000");

		List<Tender> rs = Tender.getTenders("ipad2");
		assertEquals(1, rs.size());
		assertEquals(100L, rs.get(0).getAmount());
		assertEquals("�ق�����", rs.get(0).get���D��().getName());

	}

}