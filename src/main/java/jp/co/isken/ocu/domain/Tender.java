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
	private ��� ���D�� ;
	private Date date;

	public Tender(long money, ��� ���D��) {
		this.amount = money;
		this.���D�� = ���D��;
		date = Market.getDate();
	}

	public Tender(long money, ��� ���D��, Date date) {
		amount = money;
		this.���D�� = ���D��;
		this.date = date;

	}

	public long getAmount() {
		return this.amount;
	}

	public ��� get���D��() {
		return this.���D��;
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
