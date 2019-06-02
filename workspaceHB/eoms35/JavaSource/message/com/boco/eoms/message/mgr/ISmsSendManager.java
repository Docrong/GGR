package com.boco.eoms.message.mgr;

import com.boco.eoms.base.service.Manager;

public interface ISmsSendManager extends Manager {
	public String sendSms(String tel,String msg);
}
