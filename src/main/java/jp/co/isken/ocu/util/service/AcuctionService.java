package jp.co.isken.ocu.util.service;

import java.util.List;

import jp.co.isken.ocu.domain.Item;
import jp.co.isken.ocu.domain.Tender;

public class AcuctionService {

	public static List<Tender> getTrande(String item) {
		return Item.getItem(item).getTenders();
	}

}
