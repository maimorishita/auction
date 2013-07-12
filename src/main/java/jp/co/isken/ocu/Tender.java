package jp.co.isken.ocu;

import java.io.Serializable;
import java.util.List;

public class Tender implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -3548491193461037272L;
	private long amount;
	private ‰ïˆõ “üDÒ ;

	public Tender(long money, ‰ïˆõ “üDÒ) {
		this.amount = money;
		this.“üDÒ = “üDÒ;
	}

	public Tender() {
		// TODO ©“®¶¬‚³‚ê‚½ƒRƒ“ƒXƒgƒ‰ƒNƒ^[EƒXƒ^ƒu
	}

	public long getAmount() {
		return this.amount;
	}

	public ‰ïˆõ get“üDÒ() {
		return this.“üDÒ;
	}

	public static List<Tender> getTenders(String itemName) {
		Auction a = Market.getAuction(itemName);
		return a.getTenders();
	}

}
