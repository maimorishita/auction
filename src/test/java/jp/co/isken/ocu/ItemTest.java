package jp.co.isken.ocu;

import static org.junit.Assert.assertEquals;
import jp.co.isken.ocu.domain.Item;
import jp.co.isken.ocu.domain.Market;
import jp.co.isken.ocu.domain.���;
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
		��� a = new ���("�X��1");
		a.grant�o�i();
		a.�o�i����("ipad2", "");
		Item m = Item.getItem("ipad2");
		assertEquals("ipad2", m.getName());
	}
	
	@Test
	public void �I�[�N�V�����ɂ͏��i����ID������() {
		��� m = new ���("�R�c");
		Item a = new Item("ipad", m, Util.stringToDate("20120101120000"));
		assertEquals("ipad", a.getName());
		assertEquals("�R�c", a.getMember().getName());
	}
}
