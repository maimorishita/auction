package jp.co.isken.ocu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import jp.co.isken.ocu.domain.Item;
import jp.co.isken.ocu.domain.Tender;

public class ItemFacade {
	
	public static List<Item> getFinishedItems(Date date) {
		List<Item> rs = new ArrayList<Item>();
		Iterator<Item> iter = Item.iterator();
		while (iter.hasNext()) {
			Item target = iter.next();
			if (target.getTimeLimit().equals(date)) {
				rs.add(target);
			}
		}
		return rs;
	}
	
	public static void setSuccessfulTender(Item item, Tender lastTender) {
		if (item.getReservePrice() > lastTender.getAmount()) {
			item.setisCancel(true);
			return;
		}
		item.setSuccessfulTender(lastTender);
	}

}
