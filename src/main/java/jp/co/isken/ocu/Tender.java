package jp.co.isken.ocu;

import java.io.Serializable;
import java.util.List;

public class Tender implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -3548491193461037272L;
	private long amount;
	private ��� ���D�� ;

	public Tender(long money, ��� ���D��) {
		this.amount = money;
		this.���D�� = ���D��;
	}

	public Tender() {
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}

	public long getAmount() {
		return this.amount;
	}

	public ��� get���D��() {
		return this.���D��;
	}

	public static List<Tender> getTenders(String itemName) {
		Auction a = Market.getAuction(itemName);
		return a.getTenders();
	}

}
