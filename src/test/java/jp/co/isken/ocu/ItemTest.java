package jp.co.isken.ocu;

import static org.junit.Assert.assertEquals;
import jp.co.isken.ocu.domain.Item;
import jp.co.isken.ocu.domain.Market;
import jp.co.isken.ocu.domain.Member;
import jp.co.isken.ocu.service.���Facade;
import jp.co.isken.ocu.util.Util;

import org.junit.Before;
import org.junit.Test;

public class ItemTest {

	@Before
	public void setup() {
		Market.setup();
	}

	@Test
	public void �o�i��������I�[�N�V�������擾�ł���() throws Exception {
		Member a = new Member("�X��1");
		���Facade.grant�o�i(a);
		a.�o�i����("ipad2", "", 200000);
		Item m = Item.getItem("ipad2");
		assertEquals("ipad2", m.getName());
	}
	
	@Test
	public void �I�[�N�V�����ɂ͏��i����ID������() {
		Member m = new Member("�R�c");
		Item a = new Item("ipad", m, Util.stringToDate("20120101120000"), 200000);
		assertEquals("ipad", a.getName());
		assertEquals("�R�c", a.getMember().getName());
	}
}
