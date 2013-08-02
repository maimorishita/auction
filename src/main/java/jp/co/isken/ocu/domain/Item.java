package jp.co.isken.ocu.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Item {

	private String itemName;
	private Member member;
	private List<Tender> tenders = new ArrayList<Tender>();
	private Tender successfulTender;
	private Date timelimit;
	private int reservePrice;
	private boolean isCancel;
	private static List<Item> itemList = new ArrayList<Item>();

	public Item(String itemName, Member member, Date date, int reservePrice) {
		this.itemName = itemName;
		this.member = member;
		this.timelimit = date;
		this.reservePrice = reservePrice;
		this.isCancel = false;
	}

	public Item() {
		itemName = "";
	}

	public String getName() {
		return itemName;
	}

	public Item tender(long money, Member “üŽDŽÒ, Date date) {
		Tender tender = new Tender(money, “üŽDŽÒ, date);
		tenders.add(tender);
		Member.update(“üŽDŽÒ, this);
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

	public Member getMember() {
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

	public Tender getSuccessfulTender() {
		return this.successfulTender;
	}

	public boolean isCancel() {
		return this.isCancel;
	}

	public long getReservePrice() {
		return this.reservePrice;
	}

	public void setisCancel(boolean cancel) {
		this.isCancel = cancel;
	}

	public void setSuccessfulTender(Tender lastTender) {
		this.successfulTender = lastTender;
	}
}
