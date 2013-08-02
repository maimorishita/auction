package jp.co.isken.ocu;

import static org.junit.Assert.assertEquals;
import jp.co.isken.ocu.domain.Item;
import jp.co.isken.ocu.domain.Market;
import jp.co.isken.ocu.domain.Member;
import jp.co.isken.ocu.service.会員Facade;
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
		Member a = new Member("森下1");
		会員Facade.grant出品(a);
		a.出品する("ipad2", "", 200000);
		Item m = Item.getItem("ipad2");
		assertEquals("ipad2", m.getName());
	}
	
	@Test
	public void オークションには商品名とIDがある() {
		Member m = new Member("山田");
		Item a = new Item("ipad", m, Util.stringToDate("20120101120000"), 200000);
		assertEquals("ipad", a.getName());
		assertEquals("山田", a.getMember().getName());
	}
}
