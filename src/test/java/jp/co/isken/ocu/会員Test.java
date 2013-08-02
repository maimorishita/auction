package jp.co.isken.ocu;

import static org.junit.Assert.assertEquals;

import java.util.List;

import jp.co.isken.ocu.domain.Market;
import jp.co.isken.ocu.domain.Member;
import jp.co.isken.ocu.service.���Facade;
import jp.co.isken.ocu.util.Util;

import org.junit.Before;
import org.junit.Test;

public class ���Test {

	@Before
	public void doBefore() {
		Market.setup();
		Market.setDate(Util.stringToDate("20130627000000"));
	}

	@Test
	public void �������o�^����() {
		Member a = new Member("�X��4");
		assertEquals("�X��4", a.getName());
	}

	@Test
	public void ������擾�ł���() throws Exception {
		Member a = new Member("�X��1");
		���Facade.grant�o�i(a);
		a.�o�i����("ipad2", "", 200000);
		List<Member> m = Member.getAll();
		assertEquals(1, m.size());
	}

}
