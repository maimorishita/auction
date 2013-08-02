package jp.co.isken.ocu;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import jp.co.isken.ocu.domain.Market;
import jp.co.isken.ocu.domain.Tender;
import jp.co.isken.ocu.domain.Member;
import jp.co.isken.ocu.service.AcuctionService;
import jp.co.isken.ocu.service.sample;
import jp.co.isken.ocu.service.���Facade;
import jp.co.isken.ocu.util.Util;
import jp.co.isken.ocu.util.���D�G���[;
import jp.co.isken.ocu.util.�o�i�G���[;

import org.junit.Before;
import org.junit.Test;

public class AcuctionServiceTest {
	
	@Before
	public void setup() {
		Market.setup();
	}

	@Test
	public void �g�����h���擾����() {
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
		} catch (�o�i�G���[ e) {
			fail();
		}

		try {
			�X��.���D����("MacBookAir", 100000, "20130629010000");
		} catch (���D�G���[ e) {
			System.out.println(e.getMessage());
		}

		try {
			���V.���D����("MacBookAir", 250000, "20130629050000");
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

		List<Tender> targets = AcuctionService.getTrande("MacBookAir");

		for (Tender tender : targets) {
			System.out.println(tender.getDate() + "��"
					+ tender.get���D��().getName() + ":" + tender.getAmount());
		}

		assertThat(targets.get(0).getDate() + "��"
				+ targets.get(0).get���D��().getName() + ":"
				+ targets.get(0).getAmount(),
				is("Sat Jun 29 01:00:00 JST 2013���X��:100000"));
		assertThat(targets.get(1).getDate() + "��"
				+ targets.get(1).get���D��().getName() + ":"
				+ targets.get(1).getAmount(),
				is("Sat Jun 29 05:00:00 JST 2013�����V:250000"));
		assertThat(targets.get(2).getDate() + "��"
				+ targets.get(2).get���D��().getName() + ":"
				+ targets.get(2).getAmount(),
				is("Sat Jun 29 07:00:00 JST 2013���X��:300000"));
		assertThat(targets.size(), is(3));

		/**
		 * ���e�X�g
		 */
		sample s = new sample();
		s.main(targets);

	}

}
