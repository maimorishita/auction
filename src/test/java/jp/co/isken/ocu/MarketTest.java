package jp.co.isken.ocu;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;

import jp.co.isken.ocu.domain.Item;
import jp.co.isken.ocu.domain.Market;
import jp.co.isken.ocu.domain.Member;
import jp.co.isken.ocu.service.���Facade;
import jp.co.isken.ocu.util.MarcketTask;
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
	public void �������ݒ肵�擾�ł���() {
		Date date = Util.stringToDate("20130101111111");
		Market.setDate(date);
		assertEquals(date, Market.getDate());
	}

	@Test
	public void �w�莞���ɍŏI���D�𗎎D�Ƃ���() throws �o�i�G���[, ���D�G���[ {
		Market.setDate(Util.stringToDate("20130625000000"));
		Member �X�� = new Member("�X��2");
		���Facade.grant���D(�X��);
		���Facade.grant�o�i(�X��);
		Member ���V = new Member("���V");
		���Facade.grant���D(���V);
		Member �X�� = new Member("�X��");
		���Facade.grant���D(�X��);
		�X��.�o�i����("MacBookAir", "20130630000000", 200000);
		�X��.���D����("MacBookAir", 100000, "20130629010000");
		���V.���D����("MacBookAir", 200000, "20130629050000");
		�X��.���D����("MacBookAir", 300000, "20130629070000");
		Market.setDate(Util.stringToDate("20130630000000"));
		MarcketTask task  = new MarcketTask();
		task.run();
		Item target = Item.getItem("MacBookAir");
		assertEquals("�X��", target.getSuccessfulTender().get���D��().getName());
		assertEquals(300000, target.getSuccessfulTender().getAmount());
	}
	
	@Test
	public void �Œᗎ�D���i�ɖ����Ȃ��ꍇ�̓L�����Z���Ƃ���() throws �o�i�G���[, ���D�G���[ {
		Market.setDate(Util.stringToDate("20130625000000"));
		Member �X�� = new Member("�X��2");
		���Facade.grant���D(�X��);
		���Facade.grant�o�i(�X��);
		Member ���V = new Member("���V");
		���Facade.grant���D(���V);
		Member �X�� = new Member("�X��");
		���Facade.grant���D(�X��);
		�X��.�o�i����("MacBookAir", "20130630000000", 200000);
		�X��.���D����("MacBookAir", 100000, "20130629010000");
		Market.setDate(Util.stringToDate("20130630000000"));
		MarcketTask task  = new MarcketTask();
		task.run();
		Item target = Item.getItem("MacBookAir");
		assertEquals(null, target.getSuccessfulTender());
		assertThat(target.isCancel(), is(true));
	}

	/**
	 * �V�i���I�e�X�g
	 */
	@Test
	public void �I�[�N�V����() {
		Market.setDate(Util.stringToDate("20130625000000"));
		Member �X�� = new Member("�X��2");
		���Facade.grant���D(�X��);
		���Facade.grant�o�i(�X��);

		Member ���V = new Member("���V");
		���Facade.grant���D(���V);

		Member �X�� = new Member("�X��");
		���Facade.grant���D(�X��);

		try {
			�X��.�o�i����("MacBookAir", "20130630000000", 200000);
			fail();
		} catch (�o�i�G���[ e) {
			assertEquals("�o�i����������܂���B", e.getMessage());
		}

		try {
			�X��.�o�i����("MacBookAir", "20130630000000", 200000);
		} catch (�o�i�G���[ e) {
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

		Item target = Item.getItem("MacBookAir");
		Market.setDate(Util.stringToDate("20130630000000"));
		MarcketTask task  = new MarcketTask();
		task.run();
		assertEquals("�X��", target.getSuccessfulTender().get���D��().getName());
		assertEquals(300000, target.getSuccessfulTender().getAmount());
	}
}
