package jp.co.isken.ocu.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import jp.co.isken.ocu.util.Util;
import jp.co.isken.ocu.util.���D�G���[;
import jp.co.isken.ocu.util.�o�i�G���[;

public class ��� {

	private String name;
	private boolean �o�i���� = false;
	private boolean ���D���� = false;
	private List<Auction> auctions = new ArrayList<Auction>();
	private static List<���> memberList = new ArrayList<���>();

	public ���(String name) {
		this.name = name;
		Market.add(this);
	}

	public String getName() {
		return this.name;
	}

	public boolean is�o�i��() {
		return �o�i����;
	}

	public boolean is���D��() {
		return ���D����;
	}

	public void grant�o�i() {
		�o�i���� = true;
		���.update(this);
	}

	public void grant���D() {
		���D���� = true;
		���.update(this);
	}

	public void �o�i����(String itemName, String datetime) throws �o�i�G���[ {
		if (this.�o�i���� == false) {
			throw new �o�i�G���[("�o�i����������܂���B");
		}
		if (Auction.getAuction(itemName).getName().equals(itemName)) {
			throw new �o�i�G���[("���i�����d�����Ă��܂��B");
		}
		if ("".equals(itemName)) {
			throw new �o�i�G���[("���i���w�肵�Ă��������B");
		}

		Auction auction = new Auction(itemName, this,
				Util.stringToDate(datetime));
		auctions.add(auction);
		���.update(this, auction);
	}
	
	public static void update(��� member, Auction auction) {
		���.update(member);
		for (Iterator<Auction> iterator = Auction.iterator(); iterator
				.hasNext();) {
			Auction k = iterator.next();
			if (member.getName().equals(k.getName())) {
				iterator.remove();
			}
		}
		Auction.add(auction);
	}

	public void ���D����(String itemName, long money, String datestr) throws ���D�G���[ {
		if (this.���D���� == false) {
			throw new ���D�G���[("���D����������܂���B");
		}
		Auction a = Auction.getAuction(itemName);
		if (a.getMember().getName().equals(this.getName())) {
			throw new ���D�G���[("�o�i�҂͓��D�ł��܂���B");
		}
		Date date = Util.stringToDate(datestr);
		if (date.after(a.getTimeLimit())) {
			throw new ���D�G���[("���D���ԊO�ł��B");
		}
		if (a.getTenders().size() > 0) {
			if (money <= a.getLastTender().getAmount()) {
				throw new ���D�G���[("�ō����D�z�����������z���w�肵�Ă��������B");
			} else {
				a.tender(money, this, date);
			}
		} else {
			a.tender(money, this, date);
		}
	}

	public Auction getSelfAuction(String itemName) {
		for (Auction auction : this.auctions) {
			if (auction.getName().equals(itemName)) {
				return auction;
			}
		}
		return new Auction();
	}

	public void setTenders(Auction auction, List<Tender> tenders) {
		for (Auction a : this.auctions) {
			if (a.equals(auction)) {
				a.setTenders(tenders);
			}
		}

	}

	public List<Auction> getAuctions() {
		return this.auctions;
	}

	public static void init() {
		memberList = new ArrayList<���>();
	}

	public static List<���> getAll() {
		return memberList;
	}

	public static Iterator<���> iterator() {
		return memberList.iterator();
	}
	
	public static void update(��� member) {
		for (Iterator<���> iterator = ���.iterator(); iterator.hasNext();) {
			��� k = iterator.next();
			if (member.getName().equals(k.getName())) {
				iterator.remove();
			}
		}
		���.add(member);
	}

	public static void add(��� member) {
		memberList.add(member);
	}
}
