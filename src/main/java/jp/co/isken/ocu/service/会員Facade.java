package jp.co.isken.ocu.service;

import jp.co.isken.ocu.domain.Member;

public class 会員Facade {

	public static void grant出品(Member a) {
		a.set出品権限(true);
		Member.update(a);
	}

	public static void grant入札(Member a) {
		a.set入札権限(true);
		Member.update(a);
	}}
