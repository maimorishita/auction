package jp.co.isken.ocu;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Market {

	private static Date date;
	private static List<���> members = new ArrayList<���>();

	public static List<���> getMembers() {
		return Market.members;
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
		for (��� e : members) {
			for (Auction a : e.getAuctions()) {
				if (a.getName().equals(itemName)) {
					return a;
				}
			}
		}
		return new Auction();
	}

	public static void setup() {
		members = new ArrayList<���>();
		date = null;
	}

	public static void setDate(Date date1) {
		date = date1;
	}

	public static Date getDate() {
		return date;
	}

}
