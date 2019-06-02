package com.boco.eoms.businessupport.order.dao;

import com.boco.eoms.base.dao.Dao;

public interface IOrderSheetJdbc extends Dao {

	//select maxcode 
	public String getMaxCode();
}
