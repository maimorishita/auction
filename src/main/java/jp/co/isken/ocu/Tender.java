package jp.co.isken.ocu;

import java.io.Serializable;
import java.util.List;

public class Tender implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -3548491193461037272L;
	private long amount;
	private 会員 入札者 ;

	public Tender(long money, 会員 入札者) {
		this.amount = money;
		this.入札者 = 入札者;
	}

	public Tender() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public long getAmount() {
		return this.amount;
	}

	public 会員 get入札者() {
		return this.入札者;
	}

	public static List<Tender> getTenders(String itemName) {
		Auction a = Market.getAuction(itemName);
		return a.getTenders();
	}

}
