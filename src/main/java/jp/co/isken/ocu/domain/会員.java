package jp.co.isken.ocu.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.co.isken.ocu.util.Util;
import jp.co.isken.ocu.util.入札エラー;
import jp.co.isken.ocu.util.出品エラー;

public class 会員 implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6017649804846731734L;
	private String name;
	private boolean 出品権限 = false;
	private boolean 入札権限 = false;
	private List<Auction> auctions = new ArrayList<Auction>();

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
		Market.update(this);
	}

	public void grant入札() {
		入札権限 = true;
		Market.update(this);
	}

	public void 出品する(String itemName, String datetime) throws 出品エラー {
		if (this.出品権限 == false) {
			throw new 出品エラー("出品権限がありません。");
		}
		if (Market.getAuction(itemName).getName().equals(itemName)) {
			throw new 出品エラー("商品名が重複しています。");
		}
		if ("".equals(itemName)) {
			throw new 出品エラー("商品を指定してください。");
		}


		Auction auction = new Auction(itemName, this,
				Util.stringToDate(datetime));
		auctions.add(auction);
		Market.update(this, auction);
	}

	public void 入札する(String itemName, long money, String datestr) throws 入札エラー {
		if (this.入札権限 == false) {
			throw new 入札エラー("入札権限がありません。");
		}
		Auction a = Market.getAuction(itemName);
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
}
