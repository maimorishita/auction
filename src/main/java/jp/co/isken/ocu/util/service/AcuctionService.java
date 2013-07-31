package jp.co.isken.ocu.util.service;

import java.util.List;

import jp.co.isken.ocu.domain.Auction;
import jp.co.isken.ocu.domain.Tender;

public class AcuctionService {

	public static List<Tender> getTrande(String item) {
		return Auction.getAuction(item).getTenders();
	}

}
