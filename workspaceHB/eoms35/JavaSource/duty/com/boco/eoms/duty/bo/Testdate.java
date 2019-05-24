package com.boco.eoms.duty.bo;
import java.util.GregorianCalendar;
public class Testdate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GregorianCalendar cal_end_1 = new GregorianCalendar();//StaticMethod.String2Cal("2008-11-11 11:11:11");
		int maxerrortime = 300;
		cal_end_1.add(cal_end_1.MINUTE , maxerrortime);
		String time_end = String.valueOf(cal_end_1.get(cal_end_1.YEAR));
		time_end = time_end + "-"
				+ String.valueOf(cal_end_1.get(cal_end_1.MONTH) + 1);
		time_end = time_end + "-"
				+ String.valueOf(cal_end_1.get(cal_end_1.DATE));
		time_end = time_end + " "
				+ String.valueOf(cal_end_1.get(cal_end_1.HOUR_OF_DAY));
		time_end = time_end + ":"
				+ String.valueOf(cal_end_1.get(cal_end_1.MINUTE));
		time_end = time_end + ":"
				+ String.valueOf(cal_end_1.get(cal_end_1.SECOND));
		System.out.println(time_end);
	}

}
