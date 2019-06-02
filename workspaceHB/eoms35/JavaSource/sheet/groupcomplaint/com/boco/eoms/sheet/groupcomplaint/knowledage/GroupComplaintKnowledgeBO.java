package com.boco.eoms.sheet.groupcomplaint.knowledage;


import java.util.HashMap;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.knowledge.service.KnowledgeClient;
import com.boco.eoms.knowledge.util.KnowledgeMethod;
import com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintMain;
import com.boco.eoms.sheet.groupcomplaint.service.IGroupComplaintMainManager;

public class GroupComplaintKnowledgeBO {

	/**
	 * 新增知识库
	 */
	public static String showNewknowLedage(String sheetKey,String userId) throws Exception{
		IGroupComplaintMainManager mgr=
			 (IGroupComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iGroupComplaintMainManager");
		ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
		GroupComplaintMain main = (GroupComplaintMain)mgr.getSingleMainPO(sheetKey);
		
		Map mainMap = new HashMap();
		if(main!=null){				
			mainMap.put("sheetNo", main.getSheetId());
			mainMap.put("title", main.getTitle());
			mainMap.put("urgentDegree", service.id2Name(StaticMethod.nullObject2String(main.getUrgentDegree()),"ItawSystemDictTypeDao"));
			/**
			 * 因规范修改,下面屏蔽的三个字段在新规范中已经去掉
			 */
//			mainMap.put("btype1", main.getBtype1());
			mainMap.put("bdeptContact", main.getBdeptContact());
//			mainMap.put("customerName", main.getCustomerName());
//			mainMap.put("customPhone", main.getCustomPhone());
			mainMap.put("complaintTime", main.getComplaintTime());
			mainMap.put("faultTime", main.getFaultTime());
			
//			mainMap.put("complaintAdd", main.getComplaintAdd());
//			mainMap.put("complaintDesc", main.getComplaintDesc());
//			mainMap.put("bdeptContactPhone", service.id2Name(StaticMethod.nullObject2String(main.getBdeptContactPhone()),"ItawSystemDictTypeDao"));			
//			mainMap.put("repeatGroupComplaintTimes", main.getRepeatComplaintTimes());
//			
//			mainMap.put("complaintType1", service.id2Name(StaticMethod.nullObject2String(main.getComplaintType1()),"ItawSystemDictTypeDao"));
//			mainMap.put("complaintType2", service.id2Name(StaticMethod.nullObject2String(main.getComplaintType2()),"ItawSystemDictTypeDao"));
//			mainMap.put("complaintType", service.id2Name(StaticMethod.nullObject2String(main.getComplaintType()),"ItawSystemDictTypeDao"));
//			mainMap.put("complaintReason1", main.getComplaintReason1());
//			mainMap.put("complaintReason2", main.getComplaintReason2());
//			mainMap.put("complaintReason", main.getComplaintReason());
//			
//			mainMap.put("customType", service.id2Name(StaticMethod.nullObject2String(main.getCustomType()),"ItawSystemDictTypeDao"));
//			mainMap.put("customLevel", service.id2Name(StaticMethod.nullObject2String(main.getCustomLevel()),"ItawSystemDictTypeDao"));
//			mainMap.put("customBrand", main.getCustomBrand());
//			mainMap.put("customAttribution", main.getCustomAttribution());
//			mainMap.put("startDealCity", service.id2Name(StaticMethod.nullObject2String(main.getStartDealCity()),"tawSystemAreaDao"));
//			mainMap.put("callerNo", main.getCallerNo());
//			mainMap.put("callerRegistVLR", main.getCallerRegistVLR());
//			
//			mainMap.put("callerDialUpType", service.id2Name(StaticMethod.nullObject2String(main.getCallerDialUpType()),"ItawSystemDictTypeDao"));
//			mainMap.put("callerFault", main.getCallerFault());
//			mainMap.put("callerCallOtherDesc", main.getCallerCallOtherDesc());
//			mainMap.put("aroundUserDesc", main.getAroundUserDesc());
//			
//			mainMap.put("calledPartyNo", main.getCalledPartyNo());
//			
//			mainMap.put("callerIsIntelligentUser", service.id2Name(StaticMethod.nullObject2String(main.getCallerIsIntelligentUser()),"ItawSystemDictTypeDao"));
//			mainMap.put("calledPartyRegistVLR", main.getCalledPartyRegistVLR());
//			mainMap.put("otherUserDesc", main.getOtherUserDesc());
//			mainMap.put("calledPartyCallC", main.getCalledPartyCallC());
//			mainMap.put("dealAdvice", main.getDealAdvice());
			
//			mainMap.put("faultDesc", main.getfaultDesc);
//			mainMap.put("faultArea", main.getOtherUserDesc());
//			mainMap.put("faultRoad", main.getCalledPartyCallC());
//			mainMap.put("faultNo", main.getDealAdvice());
//			mainMap.put("faultRoad1", main.getCalledPartyRegistVLR());
//			mainMap.put("faultRoad2", main.getOtherUserDesc());
//			mainMap.put("faultVill", main.getCalledPartyCallC());
//			mainMap.put("isVisit", service.id2Name(StaticMethod.nullObject2String(main.getCallerIsIntelligentUser()),"ItawSystemDictTypeDao"));
		}

		String xml = KnowledgeMethod.getKnowledgeXml(userId, "complaint", sheetKey, mainMap, null);
		return KnowledgeClient.loadAddKnowledgeService().saveXmlValue(xml);
	}


}
