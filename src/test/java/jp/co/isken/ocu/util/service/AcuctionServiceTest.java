package jp.co.isken.ocu.util.service;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import jp.co.isken.ocu.domain.Market;
import jp.co.isken.ocu.domain.Tender;
import jp.co.isken.ocu.domain.会員;
import jp.co.isken.ocu.util.Util;
import jp.co.isken.ocu.util.入札エラー;
import jp.co.isken.ocu.util.出品エラー;

import org.junit.Test;

public class AcuctionServiceTest {

	@Test
	public void トレンドを取得する() {
		Market.setDate(Util.stringToDate("20130625000000"));
		会員 森下 = new 会員("森下2");
		森下.grant入札();
		森下.grant出品();

		会員 細澤 = new 会員("細澤");
		細澤.grant入札();

		会員 森木 = new 会員("森木");
		森木.grant入札();

		try {
			森下.出品する("MacBookAir", "20130630000000");
		} catch (出品エラー e) {
			fail();
		}

		try {
			森木.入札する("MacBookAir", 100000, "20130629010000");
		} catch (入札エラー e) {
			System.out.println(e.getMessage());
		}

		try {
			細澤.入札する("MacBookAir", 250000, "20130629050000");
		} catch (入札エラー e) {
			fail();
		}

		try {
			森木.入札する("MacBookAir", 300000, "20130629070000");
		} catch (入札エラー e) {
			fail();
		}

		/**
		 * 時刻を2013/6/30に進める
		 */
		Market.setDate(Util.stringToDate("20130630000000"));

		List<Tender> targets = AcuctionService.getTrande("MacBookAir");

		for (Tender tender : targets) {
			System.out.println(tender.getDate() + "→"
					+ tender.get入札者().getName() + ":" + tender.getAmount());
		}

		assertThat(targets.get(0).getDate() + "→"
				+ targets.get(0).get入札者().getName() + ":"
				+ targets.get(0).getAmount(),
				is("Sat Jun 29 01:00:00 JST 2013→森木:100000"));
		assertThat(targets.get(1).getDate() + "→"
				+ targets.get(1).get入札者().getName() + ":"
				+ targets.get(1).getAmount(),
				is("Sat Jun 29 05:00:00 JST 2013→細澤:250000"));
		assertThat(targets.get(2).getDate() + "→"
				+ targets.get(2).get入札者().getName() + ":"
				+ targets.get(2).getAmount(),
				is("Sat Jun 29 07:00:00 JST 2013→森木:300000"));
		assertThat(targets.size(), is(3));

		/**
		 * 未テスト
		 */
		sample s = new sample();
		s.main(targets);


	}




}
