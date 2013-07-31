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
	private List<Item> Items = new ArrayList<Item>();
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
		if (Item.getItem(itemName).getName().equals(itemName)) {
			throw new 出品エラー("商品名が重複しています。");
		}
		if ("".equals(itemName)) {
			throw new 出品エラー("商品を指定してください。");
		}

		Item Item = new Item(itemName, this,
				Util.stringToDate(datetime));
		Items.add(Item);
		会員.update(this, Item);
	}
	
	public static void update(会員 member, Item Item) {
		会員.update(member);
		for (Iterator<Item> iterator = Item.iterator(); iterator
				.hasNext();) {
			Item k = iterator.next();
			if (member.getName().equals(k.getName())) {
				iterator.remove();
			}
		}
		Item.add(Item);
	}

	public void 入札する(String itemName, long money, String datestr) throws 入札エラー {
		if (this.入札権限 == false) {
			throw new 入札エラー("入札権限がありません。");
		}
		Item a = Item.getItem(itemName);
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

	public Item getSelfItem(String itemName) {
		for (Item Item : this.Items) {
			if (Item.getName().equals(itemName)) {
				return Item;
			}
		}
		return new Item();
	}

	public void setTenders(Item Item, List<Tender> tenders) {
		for (Item a : this.Items) {
			if (a.equals(Item)) {
				a.setTenders(tenders);
			}
		}

	}

	public List<Item> getItems() {
		return this.Items;
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
