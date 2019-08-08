package com.boco.eoms.knowledge.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.dict.service.IDictService;
import com.boco.eoms.sheet.commonfault.model.CommonFaultLink;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultLinkManager;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultMainManager;
import com.boco.eoms.commons.system.dict.util.Util;

/**
 * 提供给知识管理调用的接口
 *
 * @author IBM
 */
public class KnowledgeService {
    /**
     * 知识库通过环节信息查询工单
     *
     * @param xmlDoc
     * @return
     */
    public static String getMainFromLink(String xmlDoc) throws Exception {
        Map map = KnowledgeMethod.getMapFromXml(xmlDoc);
        String sheetIds = "";
        ICommonFaultMainManager mgr =
                (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
        List list = mgr.getMainByLink(map);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                CommonFaultMain main = (CommonFaultMain) list.get(i);
                sheetIds += main.getId() + ",";
            }
        }
        if (sheetIds.length() > 0)
            sheetIds = sheetIds.substring(0, sheetIds.length() - 1);
        return sheetIds;
    }

    /**
     * 接口服务用于知识管理获取某工单的环节信息
     *
     * @param sheetKey
     * @return
     * @throws Exception
     */
    public static String getLinks(String sheetKey, String tableId) throws Exception {
        List linkArray = new ArrayList();
        ICommonFaultLinkManager iCommonFaultLinkManager =
                (ICommonFaultLinkManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
        ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
        IDictService dictService = (IDictService) ApplicationContextHolder.getInstance().getBean("DictService");

        List linkList = (List) iCommonFaultLinkManager.getLinksByMainId(sheetKey);
        if (linkList != null) {
            for (int i = 0; i < linkList.size(); i++) {
                CommonFaultLink link = (CommonFaultLink) linkList.get(i);
                Map linkMap = new HashMap();
                String taskName = StaticMethod.nullObject2String(dictService.itemId2description(Util.constituteDictId("dict-sheet-commonfault", "mainOperateType"), link.getOperateType()));
                linkMap.put("taskName", taskName);
                linkMap.put("linkFaultResponseLevel", service.id2Name(StaticMethod.nullObject2String(link.getLinkFaultResponseLevel()), "ItawSystemDictTypeDao"));
                linkMap.put("linkUrgentFaultLog", StaticMethod.nullObject2String(link.getLinkUrgentFaultLog()));
                linkMap.put("linkIfMutualCommunication", service.id2Name(StaticMethod.nullObject2String(link.getLinkIfMutualCommunication()), "ItawSystemDictTypeDao"));
                linkMap.put("linkIfSafe", service.id2Name(StaticMethod.nullObject2String(link.getLinkIfSafe()), "ItawSystemDictTypeDao"));
                linkMap.put("linkFaultFirstDealDesc", StaticMethod.nullObject2String(link.getLinkFaultFirstDealDesc()));
                linkMap.put("linkFaultDesc", StaticMethod.nullObject2String(link.getLinkFaultDesc()));
                linkMap.put("linkTransmitReason", StaticMethod.nullObject2String(link.getLinkTransmitReason()));
                linkMap.put("linkFaultReasonSort", service.id2Name(StaticMethod.nullObject2String(link.getLinkFaultReasonSort()), "ItawSystemDictTypeDao"));
                linkMap.put("linkFaultReasonSubsection", service.id2Name(StaticMethod.nullObject2String(link.getLinkFaultReasonSubsection()), "ItawSystemDictTypeDao"));
                linkMap.put("linkDealStep", StaticMethod.nullObject2String(link.getLinkDealStep()));
                linkMap.put("linkFaultAvoidTime", StaticMethod.nullObject2String(link.getLinkFaultAvoidTime()));
                linkMap.put("linkOperRenewTime", StaticMethod.nullObject2String(link.getLinkOperRenewTime()));
                linkMap.put("linkAffectTimeLength", StaticMethod.nullObject2String(link.getLinkAffectTimeLength()));
                linkMap.put("linkIfExcuteNetChange", service.id2Name(StaticMethod.nullObject2String(link.getLinkIfExcuteNetChange()), "ItawSystemDictTypeDao"));
                linkMap.put("linkFaultDealResult", StaticMethod.nullObject2String(link.getLinkFaultDealResult()));
                linkMap.put("linkFaultReason", StaticMethod.nullObject2String(link.getLinkFaultReason()));
                linkMap.put("linkIfFinallySolveProject", service.id2Name(StaticMethod.nullObject2String(link.getLinkIfFinallySolveProject()), "ItawSystemDictTypeDao"));
                linkMap.put("linkIfAddCaseDataBase", service.id2Name(StaticMethod.nullObject2String(link.getLinkIfAddCaseDataBase()), "ItawSystemDictTypeDao"));
                linkMap.put("linkIfGreatFault", service.id2Name(StaticMethod.nullObject2String(link.getLinkIfGreatFault()), "ItawSystemDictTypeDao"));
                linkArray.add(linkMap);
            }
        }
        return KnowledgeMethod.getKnowledgeXml("", "", sheetKey, null, linkArray);
    }

    public static String selectedKnowledge(String table, String sheetKey) {
        return "";
    }


    public static void main(String[] args) {
        try {
            String xml = "<root>" +
                    "<message>" +
                    "<fieldInfo>" +
                    "<fieldChName>EOMS用户名称</fieldChName>" +
                    "<fieldEnName>userName</fieldEnName>" +
                    "<fieldContent>gzjdr</fieldContent>" +
                    "</fieldInfo>" +

                    "</message>" +
                    "<detail>" +
                    "<main>" +
                    "<col name=\"operateUserId\" value=\"admin\" />" +
                    "<col name=\"completeFlag\" value=\"2\" />" +
                    "</main>" +
                    "</detail>" +
                    "</root>";
            String str = KnowledgeService.getMainFromLink(xml);
            System.out.println("str=" + str);
        } catch (Exception err) {

        }
    }
}
