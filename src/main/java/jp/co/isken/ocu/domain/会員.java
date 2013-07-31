package jp.co.isken.ocu.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import jp.co.isken.ocu.util.Util;
import jp.co.isken.ocu.util.入札エラー;
import jp.co.isken.ocu.util.出品エラー;

public class 会員 {

	private String name;
	private boolean 出品権限 = false;
	private boolean 入札権限 = false;
	private List<Auction> auctions = new ArrayList<Auction>();
	private static List<会員> memberList = new ArrayList<会員>();

	public 会員(String name) {
		this.name = name;
		Market.add(this);
	}

	public String getName() {
		return this.name;
	}

	public boolean is出品者() {
		return 出品権限;
	}

	public boolean is入札者() {
		return 入札権限;
	}

	public void grant出品() {
		出品権限 = true;
		会員.update(this);
	}

	public void grant入札() {
		入札権限 = true;
		会員.update(this);
	}

	public void 出品する(String itemName, String datetime) throws 出品エラー {
		if (this.出品権限 == false) {
			throw new 出品エラー("出品権限がありません。");
		}
		if (Auction.getAuction(itemName).getName().equals(itemName)) {
			throw new 出品エラー("商品名が重複しています。");
		}
		if ("".equals(itemName)) {
			throw new 出品エラー("商品を指定してください。");
		}

		Auction auction = new Auction(itemName, this,
				Util.stringToDate(datetime));
		auctions.add(auction);
		会員.update(this, auction);
	}
	
	public static void update(会員 member, Auction auction) {
		会員.update(member);
		for (Iterator<Auction> iterator = Auction.iterator(); iterator
				.hasNext();) {
			Auction k = iterator.next();
			if (member.getName().equals(k.getName())) {
				iterator.remove();
			}
		}
		Auction.add(auction);
	}

	public void 入札する(String itemName, long money, String datestr) throws 入札エラー {
		if (this.入札権限 == false) {
			throw new 入札エラー("入札権限がありません。");
		}
		Auction a = Auction.getAuction(itemName);
		if (a.getMember().getName().equals(this.getName())) {
			throw new 入札エラー("出品者は入札できません。");
		}
		Date date = Util.stringToDate(datestr);
		if (date.after(a.getTimeLimit())) {
			throw new 入札エラー("入札時間外です。");
		}
		if (a.getTenders().size() > 0) {
			if (money <= a.getLastTender().getAmount()) {
				throw new 入札エラー("最高入札額よりも高い金額を指定してください。");
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
		memberList = new ArrayList<会員>();
	}

	public static List<会員> getAll() {
		return memberList;
	}

	public static Iterator<会員> iterator() {
		return memberList.iterator();
	}
	
	public static void update(会員 member) {
		for (Iterator<会員> iterator = 会員.iterator(); iterator.hasNext();) {
			会員 k = iterator.next();
			if (member.getName().equals(k.getName())) {
				iterator.remove();
			}
		}
		会員.add(member);
	}

	public static void add(会員 member) {
		memberList.add(member);
	}
}
