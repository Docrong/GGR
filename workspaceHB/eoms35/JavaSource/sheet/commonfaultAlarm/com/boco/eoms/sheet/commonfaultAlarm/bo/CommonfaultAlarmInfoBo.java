package com.boco.eoms.sheet.commonfaultAlarm.bo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.sheet.commonfaultAlarm.model.AlarmsolvedateConfig;
import com.boco.eoms.sheet.commonfaultAlarm.service.IAlarmsolveDateManager;

public class CommonfaultAlarmInfoBo {
	static private CommonfaultAlarmInfoBo instance;

	static synchronized public CommonfaultAlarmInfoBo getInstance() {
		if (instance == null) {
			instance = new CommonfaultAlarmInfoBo();
		}
		return instance;
	}

	private CommonfaultAlarmInfoBo() {
	}

	public void sendInfo() {
		try{
			IAlarmsolveDateManager infoManager = (IAlarmsolveDateManager) ApplicationContextHolder
					.getInstance().getBean("iAlarmsolveDateManagerImpl");	
			
			
			List list = infoManager.findbyall(0);
			System.out.println("CommonfaultAlarmInfoBo listSize:" + list.size());
			for (int i = 0; i < list.size(); i++) {
				try{
					AlarmsolvedateConfig info = (AlarmsolvedateConfig) list.get(i);				
					String alarmnum = info.getAlarmnum();
					System.out.println("alarmnum:" +alarmnum);
					if(alarmnum==null||alarmnum.length()<=0)
						continue;
					/***
					 * 同步告警平台告警清除时间
					 * infoManager.updateAlarmsolveDate(info); 以后可以修改为接口方式调用同步数据
					 */
					infoManager.updateAlarmsolveDate(info);
					System.out.println("更新成功");
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}catch(Exception err){
			err.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
	}
}
