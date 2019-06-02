package com.boco.eoms.sparepart.util;

import java.util.Date;

import junit.framework.TestCase;

public class StaticPartMethodTest extends TestCase {

	public void testGetOrdernumber() {
		Date date=new Date();
		System.out.println((int)(Math.random()*10));
		System.out.println(date.getTime());
		System.out.println(date.getMonth());
		System.out.println(date.getYear());
		for (int i=0;i<10;i++){
			System.out.println(StaticPartMethod.getOrdernumber("23"));
		}
		for (int i=0;i<10;i++){
			System.out.println(StaticPartMethod.getOrdernumber("23"));
		}
	}

}
