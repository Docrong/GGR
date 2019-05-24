/**
 * 
 */
package com.boco.eoms.sheet.dealtypeconfig.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager;
import com.boco.eoms.sheet.base.util.flowdefine.xml.FlowDefine;
import com.boco.eoms.sheet.base.util.flowdefine.xml.FlowDefineExplain;
import com.boco.eoms.sheet.base.util.flowdefine.xml.PhaseId;
import com.boco.eoms.sheet.dealtypeconfig.model.DealTypeConfig;
import com.boco.eoms.sheet.dealtypeconfig.service.IDealTypeConfigManager;

/**
 * @author Administrator
 *
 */
public class DealTypeConfigUtil {
	/**
	 * 得到归档的处理对象类型
	 * @param flowName
	 * @return
	 */
	public static String getDealTypeByHold(String flowName){
		IDealTypeConfigManager manager = (IDealTypeConfigManager)ApplicationContextHolder.getInstance().getBean("iDealTypeConfigManager");
		String dealPerformerType = "";
		try{
			Map phaseIdMap = getPhaseIdMap(flowName);
			Iterator it = phaseIdMap.keySet().iterator();
			while(it.hasNext()){
				String phaseId = (String)it.next();
				String tmpstr = phaseId.toLowerCase();
				if(tmpstr.indexOf("hold")!=-1){
					DealTypeConfig config = manager.getDealTypeConfigByAdmin(flowName, phaseId);
					if(config!=null){
						dealPerformerType = config.getDealPerformerType();
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return dealPerformerType;
	}
	
	/**
	 * 根据流程名得到phaseId的Map
	 * @param flowName
	 * @return
	 * @throws Exception
	 */
	public static Map getPhaseIdMap(String flowName) throws Exception{
		ITawSystemWorkflowManager workflowmgr = (ITawSystemWorkflowManager) ApplicationContextHolder.getInstance().getBean("ITawSystemWorkflowManager");
		TawSystemWorkflow workflow = workflowmgr.getTawSystemWorkflowByName(flowName);
		String mainbeanid = workflow.getMainServiceBeanId();
		IMainService mainService = (IMainService) ApplicationContextHolder.getInstance().getBean(mainbeanid);
		
		ArrayList phaseIdList = new ArrayList();
		Map phaseIdMap = new HashMap();
		FlowDefineExplain flowDefineExplain = new FlowDefineExplain(
				flowName, mainService.getRoleConfigPath());
		FlowDefine flowDefine = flowDefineExplain.getFlowDefine();
		if (flowDefine != null) {
			PhaseId phaseIds[] = flowDefine.getPhaseId();
			for (int i = 0; i < phaseIds.length; i++) {
				PhaseId phaseId = phaseIds[i];
				if (!phaseId.getId().equals("receive")) {
					phaseIdMap.put(phaseId.getId(), phaseId.getName());
					phaseIdList.add(phaseId.getId());
				}
			}
		}
		return phaseIdMap;
	}
	
}
