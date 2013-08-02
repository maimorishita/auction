package jp.co.isken.ocu.util;

import java.util.List;
import java.util.TimerTask;

import jp.co.isken.ocu.domain.Item;
import jp.co.isken.ocu.domain.Market;
import jp.co.isken.ocu.service.ItemFacade;

public class MarcketTask extends TimerTask {
	

	@Override
	public void run() {
		List<Item> targets = ItemFacade.getFinishedItems(Market.getDate());
		for(Item each : targets){
			ItemFacade.setSuccessfulTender(each, each.getLastTender());
		}
	}
}