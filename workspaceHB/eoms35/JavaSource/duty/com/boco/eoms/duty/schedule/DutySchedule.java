package com.boco.eoms.duty.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.duty.dao.TawRmRecordDAO;
import com.boco.eoms.duty.model.TawRmRecord;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiAssessInstance;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInstance;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiRelation;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiInstanceManager;
import com.boco.eoms.extra.supplierkpi.util.SuppStaticVariable;
import com.boco.eoms.message.service.impl.MsgServiceImpl;

/**
 * 
 * <p>
 * Title:任务调度--生成考核项实例填写表 JobSupplierkpiSchedule类
 * 
 * </p>
 * <p>
 * Description: 用于供应商管理流程生成考核项实例填写表
 * 
 * 定制时的克隆表达式: 0 1 0 1 * ?
 * </p>
 * <p>
 * Oct 24, 2007 10:32:52 AM
 * </p>
 * 
 * @author
 * @version 1.0
 * 
 */
public class DutySchedule implements Job {

	ITawSupplierkpiInstanceManager mgr = (ITawSupplierkpiInstanceManager) ApplicationContextHolder
			.getInstance().getBean("ItawSupplierkpiInstanceManager");

	String currentDate = StaticMethod.getCurrentDateTime();
	String yy = currentDate.substring(0, 10);
	private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool
			.getInstance();

	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		this.TawRmRecordEmail();
	}

	public int TawRmRecordEmail() {
		int num = 0;
		List list = new ArrayList();
		TawRmRecordDAO tawRmRecordDAO = new TawRmRecordDAO(ds);
		ITawSystemUserManager uMgr = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
		String dutyman = "";

		try {
			list = tawRmRecordDAO.retrieveRECORDExcelFile(yy);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {

			String emailStr = tawRmRecordDAO.getExcelFile(list, yy);
			if (list.size() > 0) {
				String[]  str;
				for (int i = 0; i < list.size(); i++) {
					TawRmRecord tawRmRecord = new TawRmRecord();
					tawRmRecord = (TawRmRecord) list.get(i);
					//								dutyman+=tawRmRecord.getDutyman()+",";
					if (tawRmRecord.getDutyman().indexOf(",") > 0
							&& tawRmRecord.getDutyman() != null
							&& !"".equals(tawRmRecord.getDutyman())) {
					  str=tawRmRecord.getDutyman().split(",");
					  if(str.length>0){
					  for(int l=0;l<str.length;l++){
					  List userList = uMgr.getUsersByName(str[l]);
						if (userList.size() > 0) {
							for (int j = 0; j < userList.size(); j++) {
								TawSystemUser tawSystemUser = new TawSystemUser();
								tawSystemUser = (TawSystemUser) userList.get(1);
								dutyman +="1"+tawSystemUser.getUserid() + "#";
							}
						}
					  }
					  }
					}
					else{
						  List userList = uMgr.getUsersByName(tawRmRecord.getDutyman());
							if (userList.size() > 0) {
								for (int j = 0; j < userList.size(); j++) {
									TawSystemUser tawSystemUser = new TawSystemUser();
									tawSystemUser = (TawSystemUser) userList.get(1);
									dutyman +="1"+tawSystemUser.getUserid() + "#";
								}
							}
					}
					
				}
				MsgServiceImpl msgService = new MsgServiceImpl();
				//TODO 
//				msgService.sendEmail4Org("8a44e43f1ac8fb2b011ac9168eda0005",
//						emailStr, dutyman.substring(0, dutyman.length() - 1),
//						"1", yy);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return num;
	}

	public static void main(String args[]) {
		DutySchedule dutySchedule = new DutySchedule();
		dutySchedule.TawRmRecordEmail();

	}

}
