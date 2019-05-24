package com.boco.eoms.message.service.bo;

import java.sql.Connection;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.message.model.SmsApply;

public class MsgServiceJDBC {

	public static boolean sendMsg(Connection connection, String serviceId,
			String sendContent, String id, String toOrgId, String sendTime) {
		boolean flag = false;
		TawSystemUser user = new TawSystemUser();
		SmsApply apply = null;
		String userId = "";
		String status = "true";
		// 判断该业务要发送的组织结构是否为空
		try {
			if (toOrgId != null && toOrgId.length() != 0) {
				List userList = MsgHelpJDBC.getUserList(connection, toOrgId);
				Iterator it = userList.iterator();
				while (it.hasNext()) {
					user = (TawSystemUser) it.next();
					String mobile = user.getMobile();
					userId = user.getUserid();
					apply = MsgHelpJDBC.getApply(connection, serviceId, userId);
					if (apply != null) {
						try {
							apply.setReceiverId(userId);
							MsgHelpJDBC.genMonitor(connection, apply,
									sendContent, mobile, id, sendTime);
						} catch (ParseException e) {
							e.printStackTrace();
							status = "false";
							return flag;
						}
					} else {
						status = "false";
						return flag;
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = "false";
			return flag;
		}

		return flag;
	}

	public static boolean closeMsg(Connection connection, String serviceId,
			String id) {
		boolean flag = false;
		flag = MsgHelpJDBC.closeMsg(connection, serviceId, id);
		return flag;
	}

}
