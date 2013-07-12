package jp.co.isken.ocu;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class AuctionTest {

	@BeforeClass
	public static void setup() {
		Market.setup();
	}

	@Test
	public void �I�[�N�V�����ɂ͏��i����ID������() {
		��� m = new ���("�R�c");
		Auction a = new Auction("ipad", m, Util.stringToDate("20120101120000"));
		assertEquals("ipad", a.getName());
		assertEquals("�R�c", a.getMember().getName());
	}
}
