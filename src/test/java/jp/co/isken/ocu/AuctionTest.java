package jp.co.isken.ocu;

import static org.junit.Assert.assertEquals;
import jp.co.isken.ocu.domain.Auction;
import jp.co.isken.ocu.domain.Market;
import jp.co.isken.ocu.domain.���;
import jp.co.isken.ocu.util.Util;

import org.junit.Before;
import org.junit.Test;

public class AuctionTest {

	@Before
	public void setup() {
		Market.setup();
	}

	@Test
	public void �o�i��������I�[�N�V�������擾�ł���() throws Exception {
		��� a = new ���("�X��1");
		a.grant�o�i();
		a.�o�i����("ipad2", "");
		Auction m = Auction.getAuction("ipad2");
		assertEquals("ipad2", m.getName());
	}
	
	@Test
	public void �I�[�N�V�����ɂ͏��i����ID������() {
		��� m = new ���("�R�c");
		Auction a = new Auction("ipad", m, Util.stringToDate("20120101120000"));
		assertEquals("ipad", a.getName());
		assertEquals("�R�c", a.getMember().getName());
	}
}
