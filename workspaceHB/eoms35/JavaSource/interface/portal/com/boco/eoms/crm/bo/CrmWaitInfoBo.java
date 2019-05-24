package com.boco.eoms.crm.bo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.crm.model.CrmWaitInfo;
import com.boco.eoms.crm.service.ICrmWaitInfoManager;

public class CrmWaitInfoBo {
	static private CrmWaitInfoBo instance;

	static synchronized public CrmWaitInfoBo getInstance() {
		if (instance == null) {
			instance = new CrmWaitInfoBo();
		}
		return instance;
	}

	private CrmWaitInfoBo() {
	}

	/**
	 * 查询接口表中未发送的接口数据并发送
	 *
	 */
	public void sendInfo() {
		try{
			ICrmWaitInfoManager infoManager = (ICrmWaitInfoManager) ApplicationContextHolder
					.getInstance().getBean("ICrmWaitInfoManager");
			List list = infoManager.getAllNotSendInfo();
			System.out.println("listSize:" + list.size());
			for (int i = 0; i < list.size(); i++) {
				CrmWaitInfo info = (CrmWaitInfo) list.get(i);
				String returnType = this.sendInfo(info);
				// 接口成功执行后，更改数据库中信息的状态
				if (returnType.equals("0")) {
					info.setIsSended(new Integer(1));
					info.setSendtime(new Date());
					infoManager.saveOrUpdateCrmWaitInfo(info);
				}
			}
		}catch(Exception err){
			System.out.println("发送crm接口数据失败");
			err.printStackTrace();
		}
	}
	/**
	 * 调用crm接口，发送数据
	 * @param info
	 * @return
	 */
	public String sendInfo(CrmWaitInfo info){
		System.out.println("发送接口数据：sheetkey="+info.getSerialNo());
		
		String attachRef = CrmLoader.createAttachRefXml(info.getAttachRef());
		
		int sheetType = 0;
		if(info.getSheetType()!=null)
			sheetType = info.getSheetType().intValue();
		int serviceType = 0;
		if(info.getServiceType()!=null)
			serviceType = info.getServiceType().intValue();
		String serialNo = info.getSerialNo();
		String opDetail = switchInfoType(info);
		System.out.println("opDetail="+opDetail);
		String iType = info.getInterfaceType();
		String returnType = "";
		if (iType.equals("confirmWorkSheet")) {
			returnType = CrmLoader.confirmWorkSheet(sheetType, serviceType,
					serialNo, opDetail,attachRef);
		} else if (iType.equals("notifyWorkSheet")) {
			returnType = CrmLoader.notifyWorkSheet(sheetType, serviceType,
					serialNo, opDetail,attachRef);
		} else if (iType.equals("withdrawWorkSheet")) {
			returnType = CrmLoader.withdrawWorkSheet(sheetType,
					serviceType, serialNo, opDetail,attachRef);
		} else if (iType.equals("replyWorkSheet")) {
			returnType = CrmLoader.replyWorkSheet(sheetType, serviceType,
					serialNo, opDetail,attachRef);
		}
		System.out.println("returnType:" + returnType);
		return returnType;
	}

	/**
	 * 根据不同的接口类型和工单类型组合成不同的opDetail
	 * 
	 * @param info
	 * @return
	 */
	private String switchInfoType(CrmWaitInfo info) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String opDetail = "";
		String iType = info.getInterfaceType();
		int sheetType = 0;
		if(info.getSheetType()!=null)
			sheetType = info.getSheetType().intValue();
		int serviceType = 0;
		if(info.getServiceType()!=null)
			serviceType = info.getServiceType().intValue();
		List chNameList = new ArrayList();
		List enName = new ArrayList();
		List contentList = new ArrayList();
		
		if(info.getReturnType()!=null){
			chNameList.add("回复类型");
			enName.add("returnType");
			contentList.add(info.getReturnType());
		}
		if (iType.equals("confirmWorkSheet")
				|| iType.equals("withdrawWorkSheet")) {// 受理、驳回接口
			chNameList.add("操作类型");
			enName.add("opType");
			contentList.add(info.getOpType());
			chNameList.add("操作说明"); 
			enName.add("opDesc");
			contentList.add(info.getOpDesc());
			opDetail = CrmLoader.createOpDetailXml(chNameList, enName,
					contentList);

		} else if (iType.equals("notifyWorkSheet")) {// 阶段回复接口
			if (sheetType == 56 || sheetType == 57) {// 个人投诉、集团客户投诉工单
				chNameList.add("阶段回复说明");
				enName.add("phaseReployDesc");
				contentList.add(info.getPhaseReployDesc());
				chNameList.add("延期解决标示");
				enName.add("isDeferReploy");
				contentList.add(info.getIsDeferReploy());
			} else {
				chNameList.add("操作类型");
				enName.add("opType");
				contentList.add(info.getOpType());
				chNameList.add("操作说明");
				enName.add("opDesc");
				contentList.add(info.getOpDesc());
			}
			opDetail = CrmLoader.createOpDetailXml(chNameList, enName,
					contentList);
		} else if (iType.equals("replyWorkSheet")) {// 回复接口
			chNameList.add("网络部门联系人");
			enName.add("ndeptContact");
			contentList.add(info.getNdeptContact());
			chNameList.add("网络部门联系人电话");
			enName.add("ndeptContactPhone");
			contentList.add(info.getNdeptContactPhone());
			chNameList.add("处理说明");
			enName.add("dealDesc");
			contentList.add(info.getDealDesc());
			chNameList.add("处理结果");
			enName.add("dealResult");
			contentList.add(info.getDealResult());
			if (sheetType == 56) {// 个人投诉工单
				chNameList.add("投诉性质");
				enName.add("compProp");
				contentList.add(info.getCompProp());
				chNameList.add("是否已答复客户");
				enName.add("isReplied");
				contentList.add(info.getIsReplied());
				chNameList.add("问题消除时间");
				enName.add("issueEliminatTime");
				contentList.add(info.getIssueEliminatTime());
				chNameList.add("问题原因");
				enName.add("issueEliminatReason");
				contentList.add(info.getIssueEliminatReason());

			} else if (sheetType == 57) {// 集团客户投诉工单
				chNameList.add("投诉性质");
				enName.add("compProp");
				contentList.add(info.getCompProp());
				chNameList.add("是否已答复客户");
				enName.add("isReplied");
				contentList.add(info.getIsReplied());
				chNameList.add("工单派单是否准确");
				enName.add("isCorrect");
				contentList.add(info.getIsCorrect());
				chNameList.add("问题消除时间");
				enName.add("issueEliminatTime");
				contentList.add(info.getIssueEliminatTime());
	
				chNameList.add("故障处理范围");
				enName.add("affectedAreas");
				contentList.add(info.getAffectedAreas());
				chNameList.add("问题原因");
				enName.add("issueEliminatReason");
				contentList.add(info.getIssueEliminatReason());
			} else if (sheetType == 32 || sheetType == 33) {// 业务开通工单和业务变更工单
				if (serviceType == 1) {// GPRS
					chNameList.add("apnid");
					enName.add("apnID");
					contentList.add(info.getApnid());
				} else if (serviceType == 2 || serviceType == 3) {// 短信和彩信
					chNameList.add("登录网关用户名");
					enName.add("loginUserName");
					contentList.add(info.getLoginUserName());
					chNameList.add("登录网关密码");
					enName.add("loginUserPassWord");
					contentList.add(info.getLoginUserPassWord());
				} else if (serviceType == 4) {// 传输专线
					chNameList.add("网络资源能力确认");
					enName.add("netResCapacity");
					contentList.add(info.getNetResCapacity());
					chNameList.add("客户端工程能力确认");
					enName.add("clientPgmCapacity");
					contentList.add(info.getClientPgmCapacity());
					chNameList.add("预计完成天数");
					enName.add("expectFinishdays");
					contentList.add(info.getExpectFinishdays());
					chNameList.add("传输专线电路代号");
					enName.add("circuitCode");
					contentList.add(info.getCircuitCode());
				}
			} else if (sheetType == 34) {// 业务拆除工单

			} else if (sheetType == 31) {// 资源确认工单

			}
			opDetail = CrmLoader.createOpDetailXml(chNameList, enName,
					contentList);
		}
		return opDetail;
	}

	public static void main(String[] args) {
		CrmWaitInfoBo bo = new CrmWaitInfoBo();
		bo.sendInfo();

		// ICrmWaitInfoManager iCrmWaitInfoManager =
		// (ICrmWaitInfoManager)ApplicationContextHolder.getInstance().getBean("ICrmWaitInfoManager");
		// CrmWaitInfo info=new CrmWaitInfo();
		// info.setInterfaceType("replyWorkSheet");
		// info.setSheetType(new Integer(32));
		// info.setServiceType(new Integer(2));
		// info.setSerialNo("serialNo");
		// info.setIsSended(new Integer(0));
		// info.setOpType("opType");
		// info.setOpDesc("opDesc");
		// info.setCompProp("compProp");
		// info.setIsReplied(new Integer(0));
		// info.setIsCorrect(new Integer(0));
		// info.setIssueEliminatTime(new Date());
		// info.setAffectedAreas("affectedAreas");
		// info.setIssueEliminatReason("issudReason");
		// info.setLoginUserName("loginName");
		// info.setLoginUserPassWord("loginPassword");
		// info.setNetResCapacity("netRes");
		// info.setClientPgmCapacity("clientPgm");
		// info.setExpectFinishdays("2");
		// info.setCircuitCode("cCode");
		// iCrmWaitInfoManager.saveCrmWaitInfo(info);
	}
}
