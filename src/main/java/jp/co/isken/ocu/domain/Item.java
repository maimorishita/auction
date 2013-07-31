package jp.co.isken.ocu.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Item {

	private String itemName;
	private ‰ïˆõ member;
	private List<Tender> tenders = new ArrayList<Tender>();
	private Date timelimit;
	private static List<Item> itemList = new ArrayList<Item>();

	public Item(String itemName, ‰ïˆõ member, Date date) {
		this.itemName = itemName;
		this.member = member;
		this.timelimit = date;
	}

	public Item() {
		this.itemName = "";
	}

	public String getName() {
		return itemName;
	}

	public Item tender(long money, ‰ïˆõ “üŽDŽÒ, Date date) {
		Tender tender = new Tender(money, “üŽDŽÒ, date);
		tenders.add(tender);
		‰ïˆõ.update(“üŽDŽÒ, this);
		return this;
	}

	public Tender getLastTender() {
		return this.tenders.get(this.tenders.size() - 1);
	}

	public List<Tender> getTenders() {
		return this.tenders;
	}

	public void setTenders(List<Tender> tenders) {
		this.tenders = tenders;
	}

	public ‰ïˆõ getMember() {
		return this.member;
	}

	public void setTimeLimit(Date date) {
		this.timelimit = date;
	}

	public Date getTimeLimit() {
		return timelimit;
	}

	public static void init() {
		itemList = new ArrayList<Item>();
	}

	public static Iterator<Item> iterator() {
		return itemList.iterator();
	}

	public static void add(Item Item) {
		itemList.add(Item);
	}

	public static Item getItem(String itemName) {
		Iterator<Item> iter = Item.iterator();
		while (iter.hasNext()) {
			Item target = iter.next();
			if (target.getName().equals(itemName)) {
				return target;
			}
		}
		return new Item();
	}

}
