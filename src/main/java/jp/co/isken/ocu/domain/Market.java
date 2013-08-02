package jp.co.isken.ocu.domain;

import java.util.Date;

public class Market {

	private static Date date;

	public static void add(Member member) {
		Member.add(member);
	}

	public static void setup() {
		Member.init();
		date = null;
		Item.init();
	}

	public static void setDate(Date _date) {
		date = _date;
	}

	public static Date getDate() {
		return date;
	}

}
