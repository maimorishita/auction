package jp.co.isken.ocu.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Tender implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -3548491193461037272L;
	private long amount;
	private Member “üDÒ ;
	private Date date;

	public Tender(long money, Member “üDÒ) {
		this.amount = money;
		this.“üDÒ = “üDÒ;
		date = Market.getDate();
	}

	public Tender(long money, Member “üDÒ, Date date) {
		amount = money;
		this.“üDÒ = “üDÒ;
		this.date = date;

	}

	public long getAmount() {
		return this.amount;
	}

	public Member get“üDÒ() {
		return this.“üDÒ;
	}

	public static List<Tender> getTenders(String itemName) {
		Item a = Item.getItem(itemName);
		return a.getTenders();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(){
		date = Market.getDate();
	}
}
