
package com.boco.eoms.duty.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.duty.dao.ITawExpertInfoDao;
import com.boco.eoms.duty.dao.ITawRmExchangePerDao;
import com.boco.eoms.duty.dao.TawRmRecordDAO;
import com.boco.eoms.duty.model.TawExpertInfo;
import com.boco.eoms.duty.model.TawRmExchangePer;
import com.boco.eoms.duty.model.TawRmLoanRecord;
import com.boco.eoms.duty.service.ITawExpertInfoManager;
import com.boco.eoms.duty.service.ITawRmExchangePerManager;

public class TawRmExchangePerManagerImpl extends BaseManager implements ITawRmExchangePerManager{
    private ITawRmExchangePerDao tawRmExchangePerDao;


	public List getTawRmExchangePer(String workserial) {
		// TODO Auto-generated method stub
		return this.tawRmExchangePerDao.getTawRmExchangePer(workserial);
	}

	public ITawRmExchangePerDao getTawRmExchangePerDao() {
		return tawRmExchangePerDao;
	}

	public void setTawRmExchangePerDao(ITawRmExchangePerDao tawRmExchangePerDao) {
		this.tawRmExchangePerDao = tawRmExchangePerDao;
	}

	public void saveTawRmExchangePer(TawRmExchangePer tawRmExchangePer) {
		this.tawRmExchangePerDao.saveTawRmExchangePer(tawRmExchangePer);
		
	}

    
}
