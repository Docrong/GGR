package com.boco.eoms.duty.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.duty.model.TawRmExchangePer;

public interface ITawRmExchangePerDao extends Dao {
	 public List getTawRmExchangePer(final String workserial);
	 public void saveTawRmExchangePer(final TawRmExchangePer tawRmExchangePer);

}
