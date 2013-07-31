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
	private ‰ïˆõ “üŽDŽÒ ;
	private Date date;

	public Tender(long money, ‰ïˆõ “üŽDŽÒ) {
		this.amount = money;
		this.“üŽDŽÒ = “üŽDŽÒ;
		date = Market.getDate();
	}

	public Tender(long money, ‰ïˆõ “üŽDŽÒ, Date date) {
		amount = money;
		this.“üŽDŽÒ = “üŽDŽÒ;
		this.date = date;

	}

	public long getAmount() {
		return this.amount;
	}

	public ‰ïˆõ get“üŽDŽÒ() {
		return this.“üŽDŽÒ;
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
