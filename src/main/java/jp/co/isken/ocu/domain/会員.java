package jp.co.isken.ocu.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.co.isken.ocu.util.Util;
import jp.co.isken.ocu.util.���D�G���[;
import jp.co.isken.ocu.util.�o�i�G���[;

public class ��� implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6017649804846731734L;
	private String name;
	private boolean �o�i���� = false;
	private boolean ���D���� = false;
	private List<Auction> auctions = new ArrayList<Auction>();

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
		Market.update(this);
	}

	public void grant���D() {
		���D���� = true;
		Market.update(this);
	}

	public void �o�i����(String itemName, String datetime) throws �o�i�G���[ {
		if (this.�o�i���� == false) {
			throw new �o�i�G���[("�o�i����������܂���B");
		}
		if (Market.getAuction(itemName).getName().equals(itemName)) {
			throw new �o�i�G���[("���i�����d�����Ă��܂��B");
		}
		if ("".equals(itemName)) {
			throw new �o�i�G���[("���i���w�肵�Ă��������B");
		}


		Auction auction = new Auction(itemName, this,
				Util.stringToDate(datetime));
		auctions.add(auction);
		Market.update(this, auction);
	}

	public void ���D����(String itemName, long money, String datestr) throws ���D�G���[ {
		if (this.���D���� == false) {
			throw new ���D�G���[("���D����������܂���B");
		}
		Auction a = Market.getAuction(itemName);
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
}
