package com.boco.eoms.duty.displaytag.support;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.duty.model.TawRmVisitRecord;
import com.boco.eoms.duty.model.TawRmVisitRecordStat;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.duty.model.TawRmVisitRecord;

public class VisitListDisplaytagDecorator extends TableDecorator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getUserId(){
		ITawSystemUserManager userManager=(ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		TawRmVisitRecord tawRmVisitRecord=(TawRmVisitRecord)getCurrentRowObject();
		String userName="";
		userName=userManager.getUserByuserid(tawRmVisitRecord.getUserId()).getUsername();
		return userName;
	}

	public String getRoomId(){
		String roomName = "";
		TawRmVisitRecordStat tawRmVisitRecordStat = (TawRmVisitRecordStat)getCurrentRowObject();
		TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
		try{
			roomName = cptroomBO.getTawSystemCptroomById(Integer.valueOf(tawRmVisitRecordStat.getRoomId()),0).getRoomname();
		}catch(Exception e){
			e.printStackTrace();
		}
		return roomName;
	}
	
	public String getVisiterCount(){
		String url = "";
		TawRmVisitRecordStat stat = (TawRmVisitRecordStat)getCurrentRowObject();
		url = "<a href='tawRmVisitRecord.do?method=searchList";
		if(stat.getRoomId() != null || !stat.getRoomId().equals("")){
			url += "&roomId=" + stat.getRoomId();
		}
		String startTime = stat.getTimeDefinedStart();
		url += "&startTime=" + startTime.subSequence(0,startTime.length()-2);
		String endTime = stat.getTimeDefinedEnd();
		url += "&endTime=" + endTime.subSequence(0,endTime.length()-2);
		url += "&tmpCompany=" + stat.getCompanyStat();
		url += "'>" + stat.getVisiterCount();
		return url;
	}
	
	/**
	 * 统计中的公司名称
	 * @return
	 */
	public String getCompanyStat(){
		TawRmVisitRecordStat stat = (TawRmVisitRecordStat)getCurrentRowObject();
		String companyName = "";
		try {
			companyName = (String) DictMgrLocator.getDictService()
					.itemId2name(
							Util.constituteDictId("dict-plancontent",
									"company"), stat.getCompanyStat());
		} catch (DictServiceException e) {
			companyName = Util.idNoName();
		}
		return companyName;
	}

	/**
	 * 查询中的公司名称
	 * @return
	 */
	public String getCompany(){
		TawRmVisitRecord record = (TawRmVisitRecord)getCurrentRowObject();
		String companyName = "";
		try {
			companyName = (String) DictMgrLocator.getDictService()
					.itemId2name(
							Util.constituteDictId("dict-plancontent",
									"company"), record.getCompany());
		} catch (DictServiceException e) {
			companyName = Util.idNoName();
		}
		return companyName;
	}
}
