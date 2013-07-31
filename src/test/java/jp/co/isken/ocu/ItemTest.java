package jp.co.isken.ocu;

import static org.junit.Assert.assertEquals;
import jp.co.isken.ocu.domain.Item;
import jp.co.isken.ocu.domain.Market;
import jp.co.isken.ocu.domain.会員;
import jp.co.isken.ocu.util.Util;

import org.junit.Before;
import org.junit.Test;

public class ItemTest {

	@Before
	public void setup() {
		Market.setup();
	}

	@Test
	public void 出品物名からオークションを取得できる() throws Exception {
		会員 a = new 会員("森下1");
		a.grant出品();
		a.出品する("ipad2", "");
		Item m = Item.getItem("ipad2");
		assertEquals("ipad2", m.getName());
	}
	
	@Test
	public void オークションには商品名とIDがある() {
		会員 m = new 会員("山田");
		Item a = new Item("ipad", m, Util.stringToDate("20120101120000"));
		assertEquals("ipad", a.getName());
		assertEquals("山田", a.getMember().getName());
	}
}
