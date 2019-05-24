package com.boco.eoms.sheet.interfaceBase.service.impl;

import java.util.Date;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.sheet.interfaceBase.model.WfInterfaceInfo;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceInfoManager;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceOperateManager;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.ILinkService;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.task.ITask;

public abstract class WfInterfaceOperateManagerAbstract implements IWfInterfaceOperateManager{
	public boolean sendData(WfInterfaceInfo info) {
		boolean result = false;
		try{
			
			//由于接口改造成实时调用，此时link表记录还没有生产，因此在传LinkBeanId和linkid时传的是taskBeanId
			String mainBeanId = info.getMainBeanId();
			String linkBeanId = info.getLinkBeanId();
			
			IMainService iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean(mainBeanId);
			ILinkService iLinkService = (ILinkService)ApplicationContextHolder.getInstance().getBean(linkBeanId);
			
			BaseMain main = iMainService.getSingleMainPO(info.getSheetKey());
			BaseLink link = iLinkService.getSingleLinkPO(info.getLinkId());
			
			System.out.println("isSended=" + info.getIsSended());
			System.out.println("UN_SEND=" + InterfaceConstants.UN_SEND);
			System.out.println("IS_UNREADY=" + InterfaceConstants.IS_UNREADY);
			if(info.getIsSended()==null || info.getIsSended().equals("") || info.getIsSended().equals(InterfaceConstants.UN_SEND)){
				System.out.println("start sendFlowInterfaceData");
				result = sendFlowInterfaceData(main,link,info.getInterfaceType(),info.getMainBeanId(),info.getServiceType());
			}
			else if(info.getIsSended().equals(InterfaceConstants.IS_UNREADY)){
				System.out.println("start dealUnReadyData");
				result = this.dealUnReadyData(main,link,info.getInterfaceType(),info.getMainBeanId(),info.getServiceType());
			}
			if(result){
				IWfInterfaceInfoManager infoManager = (IWfInterfaceInfoManager) ApplicationContextHolder
				.getInstance().getBean("iWfInterfaceInfoManager");	
				
				info.setIsSended("1");
				info.setSendTime(new Date());
				infoManager.saveOrUpdateWfInterfaceInfo(info);
			}
		}catch(Exception err){
			err.printStackTrace();
		}
		return result;
	}

	public abstract boolean sendFlowInterfaceData(BaseMain main, BaseLink link,String interfaceType,String methodType,String serviceType);
	public boolean dealUnReadyData(BaseMain main, BaseLink link,String interfaceType,String methodType,String serviceType){
		return false;
	}
	
	
	public boolean sendData(WfInterfaceInfo info,BaseLink link) {
		boolean result = false;
		try{
			
			//由于接口改造成实时调用，此时link表记录还没有生产，因此在传LinkBeanId和linkid时传的是taskBeanId和taskId
			String mainBeanId = info.getMainBeanId();
			String taskBeanId = info.getLinkBeanId();
			String taskId = info.getLinkId();
			
			IMainService iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean(mainBeanId);
			ITaskService iTaskService = (ITaskService)ApplicationContextHolder.getInstance().getBean(taskBeanId);
			
			BaseMain main = iMainService.getSingleMainPO(info.getSheetKey());
			//得到当期taskid的task记录，并将PreLinkid赋值给linkid
			List taskList = iTaskService.getTasksByCondition(" id ='"+taskId+"'");
			String linkId = "";
			if(taskList != null && taskList.size()>0){
				linkId = StaticMethod.nullObject2String(((ITask)taskList.get(0)).getPreLinkId());
				link.setId(linkId);
			}
			System.out.println("lyg===sheetId="+main.getSheetId());
			System.out.println("lyg===linkId="+linkId);
			System.out.println("isSended=" + info.getIsSended());
			System.out.println("UN_SEND=" + InterfaceConstants.UN_SEND);
			System.out.println("IS_UNREADY=" + InterfaceConstants.IS_UNREADY);
			if(info.getIsSended()==null || info.getIsSended().equals("") || info.getIsSended().equals(InterfaceConstants.UN_SEND)){
				System.out.println("start sendFlowInterfaceData");
				result = sendFlowInterfaceData(main,link,info.getInterfaceType(),info.getMainBeanId(),info.getServiceType());
			}
			else if(info.getIsSended().equals(InterfaceConstants.IS_UNREADY)){
				System.out.println("start dealUnReadyData");
				result = this.dealUnReadyData(main,link,info.getInterfaceType(),info.getMainBeanId(),info.getServiceType());
			}
			if(result){
				IWfInterfaceInfoManager infoManager = (IWfInterfaceInfoManager) ApplicationContextHolder
				.getInstance().getBean("iWfInterfaceInfoManager");	
				
				info.setIsSended("1");
				info.setSendTime(new Date());
				infoManager.saveOrUpdateWfInterfaceInfo(info);
			}
		}catch(Exception err){
			err.printStackTrace();
		}
		return result;
	}
	

}
