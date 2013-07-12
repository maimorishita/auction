package jp.co.isken.ocu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Auction implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 2293470597107977075L;
	private String itemName;
	private ‰ïˆõ member;
	private List<Tender> tenders = new ArrayList<Tender>();
	private Date timelimit;

	public Auction(String itemName, ‰ïˆõ member, Date date) {
		this.itemName = itemName;
		this.member = member;
		this.timelimit = date;
	}

	public Auction() {
		this.itemName = "";
	}

	public String getName() {
		return itemName;
	}

	public Auction tender(long money, ‰ïˆõ “üŽDŽÒ) {
		Tender tender = new Tender(money, “üŽDŽÒ);
		tenders.add(tender);
		return this;
	}

	public Tender getLastTender() {
		return this.tenders.get(this.tenders.size()-1);
	}

	public List<Tender> getTenders(){
		return this.tenders;
	}

	public void setTenders(List<Tender> tenders) {
		this.tenders= tenders;
	}

	public ‰ïˆõ getMember() {
		return this.member;
	}
	
	public void setTimeLimit(Date date){
		this.timelimit = date;
	}

	public Date getTimeLimit() {
		return timelimit;
	}
}
