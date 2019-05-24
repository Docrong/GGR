package com.boco.eoms.commons.system.user.sox.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.DateUtil;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.util.UserMgrLocator;
import com.boco.eoms.message.service.impl.MsgServiceImpl;

/**
 * 
 * <p>
 * Title:任务调度--每日检查用户密码是否快超时，提供短息提醒服务
 * 
 * </p>
 * <p>
 * Description: 每日检查用户密码是否快失效，提供短息提醒服务
 * 
 * 定制时的克隆表达式: 0 1 0 1 * ?
 * </p>
 * <p>
 * Dec 4, 2008 16:32:52 PM
 * </p>
 * 
 * @author wangbeiying
 * @version 1.0
 * 
 */
public class SoxRemindSchedule implements Job {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		ITawSystemUserManager userManager = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemUserSaveManagerFlush");
		TawSystemUser user = null;
		MsgServiceImpl msgService = new MsgServiceImpl();
		List remindUsers = new ArrayList();
		try {
			List userList = userManager.getTawSystemUsers(null);
			// 计算失效天数
			int unavailableDay = UserMgrLocator.getUserAttributes()
					.getPasswdAvailableDay().intValue()
					- UserMgrLocator.getUserAttributes()
							.getPasswdUnavailableRemindDay().intValue();
			// 遍历用户，查找快失效
			for (Iterator it = userList.iterator(); it.hasNext();) {
				user = (TawSystemUser) it.next();
				if (new Date().compareTo(DateUtil.addDate(user.getSavetime(),
						unavailableDay)) > 0) {
					remindUsers.add(user.getMobile());
				}
			}
		} catch (Exception e) {

		}
		// 发短信通知
		String remindWord = UserMgrLocator.getUserAttributes()
				.getPasswdRemindWords();
		String mobile = null;
		StringBuffer mobiles = new StringBuffer();
		int count = 0;
		int mobileLen = 0;
		for (Iterator it = remindUsers.iterator(); it.hasNext();) {
			mobile = ((String) it.next()).trim();
			mobileLen = mobile.length();
			
			if (mobileLen < 11 && mobileLen > 20) // 电话号码验证，少于11位，中断该次循环
				continue;
			else if (!isNumeric(mobile)) {// 电话号码验证，非数字，中断该次循环
				continue;
			}
			
			// 拼接手机号码串
			mobiles.append(mobile + ",");
			count++;
			/*
			 * 考虑到短息端号码串长度限制，每5个用户派发一次短息。或者还没到5个用户， 但是已经没有下一个用户了，同样也发
			 */
			if (5 == count || !it.hasNext()) {
				msgService.sendMsgByCondition(remindWord, mobiles.substring(0,
						mobiles.length() - 1));
				count = 0;
			}
		}
	}

	private boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

}
