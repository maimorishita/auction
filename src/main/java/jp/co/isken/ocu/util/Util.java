package jp.co.isken.ocu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import jp.co.isken.ocu.domain.Item;

public class Util {

	public static Date stringToDate(String str)  {

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = null;
		try {
			date = sdf1.parse(str);
		} catch (ParseException e) {
			//atode
		}
		return date;
	}

	public void timer() throws InterruptedException {
		Calendar cal = Calendar.getInstance();
		cal.set(2008, 7 - 1, 29, 2, 30, 20);

		TimerTask task = new MarcketTask();
		Timer timer = new Timer("日付実験タイマー");

		System.out.println("main start：" + new Date());
		timer.schedule(task, cal.getTime());

		TimeUnit.SECONDS.sleep(10);
		timer.cancel();
		System.out.println("main end  ：" + new Date());
	}
}
