package jp.co.isken.ocu.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Market {

	private static Date date;
	private static List<���> members = new ArrayList<���>();
	private static List<Auction> auctions = new ArrayList<Auction>();

	public static List<���> getMembers() {
		return Market.members;
	}

	public static void update(��� member, Auction auction) {
		update(member);
		for (Iterator<Auction> iterator = auctions.iterator(); iterator.hasNext();) {
			Auction k = iterator.next();
			if (member.getName().equals(k.getName())) {
				iterator.remove();
			}
		}
		auctions.add(auction);
	}

	public static void update(��� member) {
		for (Iterator<���> iterator = members.iterator(); iterator.hasNext();) {
			��� k = iterator.next();
			if (member.getName().equals(k.getName())) {
				iterator.remove();
			}
		}
		members.add(member);
	}

	public static void add(��� member) {
		members.add(member);
	}

	public static Auction getAuction(String itemName) {
		for (Auction a : auctions) {
			if (a.getName().equals(itemName)) {
				return a;
			}
		}
		return new Auction();
	}

	public static void setup() {
		members = new ArrayList<���>();
		date = null;
		auctions = new ArrayList<Auction>();
	}

	public static void setDate(Date date1) {
		date = date1;
	}

	public static Date getDate() {
		return date;
	}

}
