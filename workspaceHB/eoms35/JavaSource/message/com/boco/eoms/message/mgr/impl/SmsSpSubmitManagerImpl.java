
package com.boco.eoms.message.mgr.impl;

import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.message.dao.SmsSpSubmitDao;
import com.boco.eoms.message.mgr.ISmsSpSubmitManager;
import com.boco.eoms.message.model.SmsSpSubmit;
/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-5-5 下午03:40:02
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 *
 */
public class SmsSpSubmitManagerImpl extends BaseManager implements ISmsSpSubmitManager {
	
    private SmsSpSubmitDao dao;
    

	public SmsSpSubmitDao getSmsSpSubmitDao() {
		return dao;
	}

	public void setSmsSpSubmitDao(SmsSpSubmitDao dao) {
		this.dao = dao;
	}

	public SmsSpSubmit getSmsSpSubmit(String id) {
		// TODO Auto-generated method stub
		return dao.getSmsSpSubmit(id);
	}

	public List getSmsSpSubmits(SmsSpSubmit smsSpSubmit) {
		// TODO Auto-generated method stub
		return dao.getSmsSpSubmits(smsSpSubmit);
	}

	public void removeSmsSpSubmit(String id) {
		dao.removeSmsSpSubmit(id);
		
	}

	public void saveSmsSpSubmit(SmsSpSubmit smsSpSubmit) {
		dao.saveSmsSpSubmit(smsSpSubmit);
		
	}

    
	
}
