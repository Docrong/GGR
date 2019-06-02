package com.boco.eoms.duty.test;

import java.sql.SQLException;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.duty.dao.TawRmRecordDAO;

public class DutyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool
		.getInstance();
		TawRmRecordDAO tawRmRecordDAO =new TawRmRecordDAO(ds);
		 String currentDate = StaticMethod.getCurrentDateTime();
		  String time = currentDate.substring(0,10);
		try {
			//tawRmRecordDAO.retrieveRECORDExcelFile(time);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
