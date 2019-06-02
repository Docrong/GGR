package com.boco.eoms.sheet.groupcomplaint.knowledage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.dict.service.IDictService;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.knowledge.service.IKnowledgeService;
import com.boco.eoms.knowledge.util.KnowledgeMethod;
import com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintLink;
import com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintMain;
import com.boco.eoms.sheet.groupcomplaint.service.IGroupComplaintLinkManager;
import com.boco.eoms.sheet.groupcomplaint.service.IGroupComplaintMainManager;

public class GroupComplaintKnowledgeServiceImpl implements IKnowledgeService{

	public String getLinks(String sheetKey) throws Exception {
		List linkArray = new ArrayList();
		IGroupComplaintLinkManager iGroupComplaintLinkManager=
			 (IGroupComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iGroupComplaintLinkManager");
		ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
		IDictService dictService = (IDictService) ApplicationContextHolder.getInstance().getBean("DictService");
		
		List linkList = (List)iGroupComplaintLinkManager.getLinksByMainId(sheetKey);
		if(linkList!=null){
			for(int i=0;i<linkList.size();i++){
				GroupComplaintLink link = (GroupComplaintLink)linkList.get(i);
				Map linkMap = new HashMap();
				String taskName = StaticMethod.nullObject2String(dictService.itemId2description(Util.constituteDictId("dict-sheet-groupcomplaint","mainOperateType"),link.getOperateType()));
				linkMap.put("taskName", taskName);
				linkMap.put("linkTransmitReason", link.getLinkTransmitReason());
//				linkMap.put("linkChangeSheetId", StaticMethod.nullObject2String(link.getLinkChangeSheetId()));
//				linkMap.put("linkIfInvokeChange", service.id2Name(StaticMethod.nullObject2String(link.getLinkIfInvokeChange()),"ItawSystemDictTypeDao"));
//				linkMap.put("linkFaultStartTime", link.getLinkFaultStartTime());
//				linkMap.put("linkFaultEndTime", StaticMethod.nullObject2String(link.getLinkFaultEndTime()));
//				linkMap.put("linkFaultGenerantPlace", StaticMethod.nullObject2String(link.getLinkFaultGenerantPlace()));
//				linkMap.put("linkPlaceDesc", StaticMethod.nullObject2String(link.getLinkPlaceDesc()));
				linkMap.put("ndeptContact", link.getNdeptContact());
				linkMap.put("ndeptContactPhone", link.getNdeptContactPhone());
				linkMap.put("dealResult", service.id2Name(StaticMethod.nullObject2String(link.getDealResult()),"ItawSystemDictTypeDao"));
				linkMap.put("dealDesc", StaticMethod.nullObject2String(link.getDealDesc()));
				linkMap.put("issueEliminatTime", StaticMethod.nullObject2String(link.getIssueEliminatTime()));
				linkMap.put("issueEliminatReason", StaticMethod.nullObject2String(link.getIssueEliminatReason()));
				//linkMap.put("compProp", service.id2Name(StaticMethod.nullObject2String(link.getcompProp),"ItawSystemDictTypeDao"));
				//linkMap.put("isReplied", service.id2Name(StaticMethod.nullObject2String(link.getisReplied),"ItawSystemDictTypeDao"));
				linkMap.put("linkCheckResult", StaticMethod.nullObject2String(link.getLinkCheckResult()));
				linkMap.put("linkCheckIdea", StaticMethod.nullObject2String(link.getLinkCheckIdea()));
				linkMap.put("linkUntreadReason", StaticMethod.nullObject2String(link.getLinkUntreadReason(),"ItawSystemDictTypeDao"));
				linkMap.put("linkTransmitContent", StaticMethod.nullObject2String(link.getLinkTransmitContent(),"ItawSystemDictTypeDao"));
				linkMap.put("linkExamineContent", StaticMethod.nullObject2String(link.getLinkExamineContent(),"ItawSystemDictTypeDao"));
				linkMap.put("linkIfDeferResolve", service.id2Name(StaticMethod.nullObject2String(link.getLinkIfDeferResolve()),"ItawSystemDictTypeDao"));
				linkMap.put("linkIfInvokeCaseDatabase", service.id2Name(StaticMethod.nullObject2String(link.getLinkIfInvokeCaseDatabase()),"ItawSystemDictTypeDao"));
				linkArray.add(linkMap);
			}
		}
		return KnowledgeMethod.getKnowledgeXml("", "", sheetKey, null, linkArray);
	}

	public String getMainFromLink(Map map) throws Exception{
		String sheetIds = "";
		IGroupComplaintMainManager mgr=
			 (IGroupComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iGroupComplaintMainManager");
		IGroupComplaintLinkManager mgr2=
			 (IGroupComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iGroupComplaintLinkManager");		
		List list = mgr.getMainByLink(map, mgr2.getLinkObject());
		if(list!=null){
			for(int i=0;i<list.size();i++){
				GroupComplaintMain main = (GroupComplaintMain)list.get(i);
				sheetIds += main.getId() + ",";
			}
		}
		if(sheetIds.length()>0)
			sheetIds = sheetIds.substring(0,sheetIds.length()-1);
		return sheetIds;
	}

}
