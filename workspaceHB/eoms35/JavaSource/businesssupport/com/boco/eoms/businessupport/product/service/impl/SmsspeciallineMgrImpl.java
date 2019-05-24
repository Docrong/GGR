package com.boco.eoms.businessupport.product.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.businessupport.product.model.Smsspecialline;
import com.boco.eoms.businessupport.product.service.SmsspeciallineMgr;
import com.boco.eoms.businessupport.product.dao.SmsspeciallineDao;

/**
 * <p>
 * Title:短彩信
 * </p>
 * <p>
 * Description:彩信报工单
 * </p>
 * <p>
 * Wed Jun 02 19:47:25 CST 2010
 * </p>
 * 
 * @author 李志刚
 * @version 3.5
 * 
 */
public class SmsspeciallineMgrImpl implements SmsspeciallineMgr {
 
	private SmsspeciallineDao  smsspeciallineDao;
 	
	public SmsspeciallineDao getSmsspeciallineDao() {
		return this.smsspeciallineDao;
	}
 	
	public void setSmsspeciallineDao(SmsspeciallineDao smsspeciallineDao) {
		this.smsspeciallineDao = smsspeciallineDao;
	}
 	
    public List getSmsspeciallines() {
    	return smsspeciallineDao.getSmsspeciallines();
    }
    
    public Smsspecialline getSmsspecialline(final String id) {
    	return smsspeciallineDao.getSmsspecialline(id);
    }
    
    public void saveSmsspecialline(Smsspecialline smsspecialline) {
    	smsspeciallineDao.saveSmsspecialline(smsspecialline);
    }
    
    public void removeSmsspecialline(final String id) {
    	smsspeciallineDao.removeSmsspecialline(id);
    }
    
    public Map getSmsspeciallines(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return smsspeciallineDao.getSmsspeciallines(curPage, pageSize, whereStr);
	}
	
}