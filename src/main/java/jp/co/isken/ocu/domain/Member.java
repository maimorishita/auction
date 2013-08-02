package jp.co.isken.ocu.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import jp.co.isken.ocu.util.Util;
import jp.co.isken.ocu.util.���D�G���[;
import jp.co.isken.ocu.util.�o�i�G���[;

public class Member {

	private String name;
	private boolean �o�i���� = false;
	private boolean ���D���� = false;
	private List<Item> Items = new ArrayList<Item>();
	private static List<Member> memberList = new ArrayList<Member>();

	public Member(String name) {
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

	

	public void �o�i����(String itemName, String datetime, int reservePrice) throws �o�i�G���[ {
		if (this.�o�i���� == false) {
			throw new �o�i�G���[("�o�i����������܂���B");
		}
		if (Item.getItem(itemName).getName().equals(itemName)) {
			throw new �o�i�G���[("���i�����d�����Ă��܂��B");
		}
		if ("".equals(itemName)) {
			throw new �o�i�G���[("���i���w�肵�Ă��������B");
		}

		Item Item = new Item(itemName, this, Util.stringToDate(datetime), reservePrice);
		Items.add(Item);
		Member.update(this, Item);
	}

	public static void update(Member member, Item Item) {
		Member.update(member);
		for (Iterator<Item> iterator = Item.iterator(); iterator.hasNext();) {
			Item k = iterator.next();
			if (member.getName().equals(k.getName())) {
				iterator.remove();
			}
		}
		Item.add(Item);
	}

	public void ���D����(String itemName, long money, String datestr) throws ���D�G���[ {
		if (this.���D���� == false) {
			throw new ���D�G���[("���D����������܂���B");
		}
		Item a = Item.getItem(itemName);
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
		memberList = new ArrayList<Member>();
	}

	public static List<Member> getAll() {
		return memberList;
	}

	public static Iterator<Member> iterator() {
		return memberList.iterator();
	}

	public static void update(Member member) {
		for (Iterator<Member> iterator = Member.iterator(); iterator.hasNext();) {
			Member k = iterator.next();
			if (member.getName().equals(k.getName())) {
				iterator.remove();
			}
		}
		Member.add(member);
	}

	public static void add(Member member) {
		memberList.add(member);
	}

	public void set�o�i����(boolean b) {
		this.�o�i���� = b;
	}
	
	public void set���D����(boolean b) {
		this.���D���� = b;
	}
}