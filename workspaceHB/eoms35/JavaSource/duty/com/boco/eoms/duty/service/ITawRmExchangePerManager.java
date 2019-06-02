package com.boco.eoms.duty.service;

import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.duty.model.TawRmExchangePer;

public interface ITawRmExchangePerManager extends Manager {
	 public List getTawRmExchangePer(final String workserial);
	 public void saveTawRmExchangePer(final TawRmExchangePer tawRmExchangePer);

}
