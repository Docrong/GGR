
package com.boco.eoms.message.mgr.impl;

import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.message.dao.ISchedulerDao;
import com.boco.eoms.message.mgr.ISchedulerManager;

public class SchedulerManagerImpl extends BaseManager implements ISchedulerManager {
    private ISchedulerDao dao;
    
	public void setISchedulerDao(ISchedulerDao dao) {
		this.dao = dao;
	}

	public boolean mmsMonitorScheduler(String mobiles, String subject, List contentList) {
		return dao.mmsMonitorScheduler(mobiles, subject, contentList);
	}

	public boolean smsMonitorScheduler(String tel, String msg) {
		return dao.smsMonitorScheduler(tel, msg);
	}

	public boolean voiceMonitorScheduler(String t_no, String t_alloc_time, String t_finish_time, String t_content, String t_tel_num, String t_tel_num2, String dispatch_tel) {
		return dao.voiceMonitorScheduler(t_no, t_alloc_time, t_finish_time, t_content, t_tel_num, t_tel_num2, dispatch_tel);
	} 

    
}
