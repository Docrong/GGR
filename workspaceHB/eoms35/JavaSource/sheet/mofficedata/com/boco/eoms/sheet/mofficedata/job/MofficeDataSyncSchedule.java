package com.boco.eoms.sheet.mofficedata.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.crm.model.RecordInfo;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataBuisType;
import com.boco.eoms.sheet.mofficedata.service.IMofficeDataBuisTypeManager;
import com.boco.eoms.sheet.mofficedata.util.MofficeDataInService;
import com.boco.eoms.util.StaxParser;

/**
 * 获取局数据业务类型列表文件
 * 
 * @date 2016-4-5下午02:00:45
 * @author weichao
 */
public class MofficeDataSyncSchedule implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		boolean ifRunningNow = MofficeDataJobStatic.isIfRunningNow();
		if (!ifRunningNow) {
			BocoLog
					.info(MofficeDataSyncSchedule.class,
							"MofficeDataSyncSchedule job is running wait for next Schedule");
		} else {
			MofficeDataJobStatic.setIfRunningNow(false);
			try {
				IDownLoadSheetAccessoriesService downMgr = (IDownLoadSheetAccessoriesService) ApplicationContextHolder
						.getInstance().getBean("IDownLoadSheetAccessoriesService");
				IMofficeDataBuisTypeManager buisTypeMgr = (IMofficeDataBuisTypeManager) ApplicationContextHolder
						.getInstance().getBean("iMofficeDataBuisTypeManager");
//				String op = "<opDetail><recordInfo><fieldInfo><fieldChName>业务类型1</fieldChName><fieldEnName>enName1</fieldEnName>"
//				+ "<fieldContent>标准主表中间表1</fieldContent></fieldInfo><fieldInfo><fieldChName>业务类型2</fieldChName>"
//				+ "<fieldEnName>enName2</fieldEnName><fieldContent>标准主表中间表2</fieldContent></fieldInfo></recordInfo>"
//				+ "<recordInfo><fieldInfo><fieldChName>业务类型3</fieldChName><fieldEnName>enName3</fieldEnName>"
//				+ "<fieldContent>标准主表中间表3</fieldContent></fieldInfo><fieldInfo><fieldChName>业务类型4</fieldChName>"
//				+ "<fieldEnName>enName4</fieldEnName><fieldContent>标准主表中间表4</fieldContent></fieldInfo></recordInfo></opDetail>";
				String op = MofficeDataInService.getBusiType();
				if (null != op) {
					List list = StaxParser.getInstance().getOpdetailList(op);
					BocoLog.info(this, "==");
					// 1、调接口获取数据
					String now = StaticMethod.getCurrentDateTime();
					List typeList = new ArrayList();
					if (null != list && list.size() > 0) {
						// 2、清空表数据
						buisTypeMgr.clearData();
						for (int i = 0; i < list.size(); i++) {
							RecordInfo info = (RecordInfo) list.get(i);
							Map type = new HashMap();
							type.put("id", UUIDHexGenerator.getInstance().getID());
							type.put("buisName", info.getFieldChName());
							type.put("buisValue", info.getFieldEnName());
							type.put("createTime", now);
							typeList.add(type);
						}
					}
					// 3、将数据全量更新至表中
					if (null != typeList && typeList.size() > 0) {
						//downMgr.batchExcuteSql(typeList, new MofficeDataBuisType(), "insert");
						for(int i=0;i<typeList.size();i++)
						{
							MofficeDataBuisType mdatatype= new MofficeDataBuisType();
							Map type=(Map)typeList.get(i);
							mdatatype.setId(StaticMethod.nullObject2String(type.get("id")));
							mdatatype.setBuisName(StaticMethod.nullObject2String(type.get("buisName")));
							mdatatype.setBuisValue(StaticMethod.nullObject2String(type.get("buisValue")));
							mdatatype.setCreateTime(StaticMethod.nullObject2String(type.get("createTime")));
							System.out.println("buisName="+mdatatype.getBuisName()+";buisValue="+mdatatype.getBuisValue());
							buisTypeMgr.saveOrUpdate(mdatatype);
						}
					}
				}else{
					BocoLog.info(this, "==getBusiType interfaces failed==");
				}

			} catch (Exception e) {
				BocoLog.info(this, "==MofficeDataSyncSchedule's Exception==");
				e.printStackTrace();
			} finally {
				BocoLog.info(MofficeDataSyncSchedule.class, "MofficeDataSyncSchedule Job is running over");
				MofficeDataJobStatic.setIfRunningNow(true);
			}

		}
	}
}
