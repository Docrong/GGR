package com.boco.eoms.sheet.complaint.knowledage;


import java.util.HashMap;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.knowledge.service.KnowledgeClient;
import com.boco.eoms.knowledge.util.KnowledgeMethod;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.complaint.model.ComplaintMain;
import com.boco.eoms.sheet.complaint.service.IComplaintMainManager;

public class ComplaintKnowledgeBO {

	/**
	 * 新增知识库
	 */
	public static String showNewknowLedage(String sheetKey,String userId) throws Exception{
		IComplaintMainManager mgr=
			 (IComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iComplaintMainManager");
//		ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
		ComplaintMain main = (ComplaintMain)mgr.getSingleMainPO(sheetKey);
		
		Map mainMap = new HashMap();
		if(main!=null){
			
//			mainMap.put("sheetNo", main.getSheetId());
//			mainMap.put("title", main.getTitle());
//			mainMap.put("urgentDegree", service.id2Name(StaticMethod.nullObject2String(main.getUrgentDegree()),"ItawSystemDictTypeDao"));
//			mainMap.put("btype1", main.getBtype1());
//			mainMap.put("bdeptContact", main.getBdeptContact());
//			mainMap.put("customerName", main.getCustomerName());
//			mainMap.put("customPhone", main.getCustomPhone());
//			mainMap.put("complaintTime", main.getComplaintTime());
//			mainMap.put("faultTime", main.getFaultTime());
//			
//			mainMap.put("complaintAdd", main.getComplaintAdd());
//			mainMap.put("complaintDesc", main.getComplaintDesc());
//			mainMap.put("bdeptContactPhone", service.id2Name(StaticMethod.nullObject2String(main.getBdeptContactPhone()),"ItawSystemDictTypeDao"));			
//			mainMap.put("repeatComplaintTimes", main.getRepeatComplaintTimes());
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
			mainMap = SheetBeanUtils.bean2Map(main);
		}

		String xml = KnowledgeMethod.getKnowledgeXml(userId, "complaint", sheetKey, mainMap, null);
		return KnowledgeClient.loadAddKnowledgeService().saveXmlValue(xml);
	}
	/**
	 * 按网络三级类别检索工单，返回知识库查询结果页面。
	 * @param sheetKey
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public static String searchSheet(String sheetKey,String userId) throws Exception{
		IComplaintMainManager mgr=
			 (IComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iComplaintMainManager");
//		ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
		
		ComplaintMain main = (ComplaintMain)mgr.getSingleMainPO(sheetKey);
		Map mainMap = new HashMap();
		if(main!=null){
//			String complaintType = service.id2Name(StaticMethod.nullObject2String(main.getComplaintType()),"ItawSystemDictTypeDao");
//			String complaintType1 = service.id2Name(StaticMethod.nullObject2String(main.getComplaintType1()),"ItawSystemDictTypeDao");
//			String complaintType2 = service.id2Name(StaticMethod.nullObject2String(main.getComplaintType2()),"ItawSystemDictTypeDao");
			mainMap.put("complaintType1", main.getComplaintType1());
			mainMap.put("complaintType2", main.getComplaintType2());
			mainMap.put("complaintType", main.getComplaintType());
			mainMap.put("complaintType4", main.getComplaintType4());
			mainMap.put("complaintType5", main.getComplaintType5());
			mainMap.put("complaintType6", main.getComplaintType6());
			mainMap.put("complaintType7", main.getComplaintType7());
		}
//		List list = mgr.getMainList(mainMap);
//		
//		String sheetIds = "";
//		if(list!=null){
//			for(int i=0;i<list.size();i++){
//				ComplaintMain ComplaintMain = (ComplaintMain)list.get(i);
//				sheetIds += ComplaintMain.getId() + ",";
//			}
//		}
//		if(sheetIds.length()>0)
//			sheetIds = sheetIds.substring(0,sheetIds.length()-1);
		
		String xml = KnowledgeMethod.getKnowledgeXml(userId, "complaint", sheetKey, mainMap, null);
		return KnowledgeClient.loadAddKnowledgeService().searchXmlValue(xml);
		
	}

}
