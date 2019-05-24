package com.boco.eoms.remind.service.test;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.remind.model.RemindInfo;
import com.boco.eoms.remind.service.RemindService;

public class RemindServiceImplTest1 implements RemindService {

	public List getRemindInfoList(String userId) {
		RemindInfo remind = new RemindInfo();
		remind.setRemindTitle("模块1提醒");
		remind.setRemindContent("用户:" + userId + "  在模块1的提醒信息");
		remind.setUrl("/workbench/infopub/threadAuditHistory.do?threadId=8a8082ee1c87fe48011c889fdc9b000b&method=edit");
		List list = new ArrayList();
		list.add(remind);
		return list;
	}

}
