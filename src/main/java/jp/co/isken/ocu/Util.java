package jp.co.isken.ocu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
