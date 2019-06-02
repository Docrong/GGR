package com.boco.eoms.parter.baseinfo.contract.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.parter.baseinfo.contract.model.*;
import com.boco.eoms.parter.baseinfo.contract.mgr.*;
import com.boco.eoms.parter.baseinfo.contract.util.TawContractConstants;
import com.boco.eoms.parter.baseinfo.contract.dao.*;

/**
 * <p>
 * Title:代维合同
 * </p>
 * <p>
 * Description:代维合同
 * </p>
 * <p>
 * Wed Apr 01 08:58:31 CST 2009
 * </p>
 * 
 * @author fengshaohong
 * @version 3.5
 * 
 */
public class TawContractMgrImpl implements TawContractMgr {
 
	private TawContractDao  tawContractDao;
 	
	public TawContractDao getTawContractDao() {
		return this.tawContractDao;
	}
 	
	public void setTawContractDao(TawContractDao tawContractDao) {
		this.tawContractDao = tawContractDao;
	}
 	
    public List getTawContracts() {
    	return tawContractDao.getTawContracts();
    }
    
    public TawContract getTawContract(final String id) {
    	return tawContractDao.getTawContract(id);
    }
    
    public void saveTawContract(TawContract tawContract) {
    	tawContractDao.saveTawContract(tawContract);
    }
    
    public void removeTawContract(final String id) {
    	tawContractDao.removeTawContract(id);
    }
    
    public Map getTawContracts(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return tawContractDao.getTawContracts(curPage, pageSize, whereStr);
	}
    public Boolean isunique(final String contractName) {
    	return tawContractDao.isunique(contractName);
    }
    
    
    /**
     * 代维合同付款管理
     */
    public Map getTawContractPays(final Integer curPage, final Integer pageSize,
			final String whereStr) {
    	return tawContractDao.getTawContractPays(curPage, pageSize, whereStr);
    }
    
	public TawContractPay getTawContractPay(final String id) {
    	return tawContractDao.getTawContractPay(id);
    }
	
	public List getTawContractPayByContractid(final String contractid) {
		List list = tawContractDao.getTawContractPayByContractid(contractid);
		return  list;
}
    
    public void saveTawContractPay(TawContractPay tawContractPay) {    	
    		tawContractDao.saveTawContractPay(tawContractPay);
    }
    
    
}