package jp.co.isken.ocu.service;

import jp.co.isken.ocu.domain.Member;

public class ���Facade {

	public static void grant�o�i(Member a) {
		a.set�o�i����(true);
		Member.update(a);
	}

	public static void grant���D(Member a) {
		a.set���D����(true);
		Member.update(a);
	}}
