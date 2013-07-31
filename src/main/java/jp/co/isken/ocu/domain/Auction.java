package jp.co.isken.ocu.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Auction {

	private String itemName;
	private ��� member;
	private List<Tender> tenders = new ArrayList<Tender>();
	private Date timelimit;
	private static List<Auction> itemList = new ArrayList<Auction>();

	public Auction(String itemName, ��� member, Date date) {
		this.itemName = itemName;
		this.member = member;
		this.timelimit = date;
	}

	public Auction() {
		this.itemName = "";
	}

	public String getName() {
		return itemName;
	}

	public Auction tender(long money, ��� ���D��, Date date) {
		Tender tender = new Tender(money, ���D��, date);
		tenders.add(tender);
		���.update(���D��, this);
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

	public ��� getMember() {
		return this.member;
	}

	public void setTimeLimit(Date date) {
		this.timelimit = date;
	}

	public Date getTimeLimit() {
		return timelimit;
	}

	public static void init() {
		itemList = new ArrayList<Auction>();
	}

	public static Iterator<Auction> iterator() {
		return itemList.iterator();
	}

	public static void add(Auction auction) {
		itemList.add(auction);
	}

	public static Auction getAuction(String itemName) {
		Iterator<Auction> iter = Auction.iterator();
		while (iter.hasNext()) {
			Auction target = iter.next();
			if (target.getName().equals(itemName)) {
				return target;
			}
		}
		return new Auction();
	}

}
