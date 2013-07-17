package jp.co.isken.ocu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import jp.co.isken.ocu.domain.Auction;
import jp.co.isken.ocu.domain.Market;
import jp.co.isken.ocu.domain.���;
import jp.co.isken.ocu.util.Util;
import jp.co.isken.ocu.util.���D�G���[;
import jp.co.isken.ocu.util.�o�i�G���[;

import org.junit.Before;
import org.junit.Test;

public class MarketTest {

	@Before
	public void setup() {
		Market.setup();
	}

	@Test
	public void ������擾�ł���() throws Exception {
		��� a = new ���("�X��1");
		a.grant�o�i();
		a.�o�i����("ipad2", "");
		List<���> m = Market.getMembers();
		assertEquals(1, m.size());
	}

	@Test
	public void �o�i��������I�[�N�V�������擾�ł���() throws Exception {
		��� a = new ���("�X��1");
		a.grant�o�i();
		a.�o�i����("ipad2", "");
		Auction m = Market.getAuction("ipad2");
		assertEquals("ipad2", m.getName());
	}

	@Test
	public void �������ݒ肵�擾�ł���() {
		Date date = Util.stringToDate("20130101111111");
		Market.setDate(date);
		assertEquals(date, Market.getDate());
	}

	/**
	 * �V�i���I�e�X�g
	 */
	@Test
	public void �I�[�N�V����() {
		Market.setDate(Util.stringToDate("20130625000000"));
		��� �X�� = new ���("�X��2");
		�X��.grant���D();
		�X��.grant�o�i();

		��� ���V = new ���("���V");
		���V.grant���D();

		��� �X�� = new ���("�X��");
		�X��.grant���D();

		try {
			�X��.�o�i����("MacBookAir", "20130630000000");
			fail();
		} catch (�o�i�G���[ e) {
			assertEquals("�o�i����������܂���B", e.getMessage());
		}

		try {
			�X��.�o�i����("MacBookAir", "20130630000000");
		}catch (�o�i�G���[ e) {
			fail();
		}

		try {
			�X��.���D����("MacBookAir", 100000, "20130629000000");
		} catch (���D�G���[ e) {
			assertEquals("�o�i�҂͓��D�ł��܂���B", e.getMessage());
		}

		try {
			�X��.���D����("MacBookAir", 100000, "20130629010000");
		} catch (���D�G���[ e) {
			System.out.println(e.getMessage());
		}

		try {
			���V.���D����("MacBookAir", 100000, "20130629030000");
			fail();
		} catch (���D�G���[ e) {
			assertEquals("�ō����D�z�����������z���w�肵�Ă��������B", e.getMessage());
		}

		try {
			���V.���D����("MacBookAir", 200000, "20130629050000");
		} catch (���D�G���[ e) {
			fail();
		}

		try {
			�X��.���D����("MacBookAir", 300000, "20130629070000");
		} catch (���D�G���[ e) {
			fail();
		}

		/**
		 * ������2013/6/30�ɐi�߂�
		 */
		Market.setDate(Util.stringToDate("20130630000000"));

		try {
			���V.���D����("MacBookAir", 400000, "2013063000001");
			fail();
		} catch (���D�G���[ e) {
			assertEquals("���D���ԊO�ł��B", e.getMessage());
		}

		Auction target = Market.getAuction("MacBookAir");
		assertEquals("�X��", target.getLastTender().get���D��().getName());
		assertEquals(300000, target.getLastTender().getAmount());
	}
}
