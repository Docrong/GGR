package com.boco.eoms.knowledge.service;

import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.knowledge.util.KnowledgeMethod;
import com.boco.eoms.knowledge.util.SheetMapingSchema;

/**
 * 提供给知识管理调用的接口
 *
 * @author IBM
 */
public class KnowledgeBO {
    /**
     * 知识库通过环节信息查询工单
     *
     * @param xmlDoc
     * @return
     */
    public static String getMainFromLink(String xmlDoc) throws Exception {
        Map map = KnowledgeMethod.getMapFromXml(xmlDoc);
        Map mainMap = (Map) map.get("mainMap");
        Map megMap = (Map) map.get("megMap");

        String tableId = StaticMethod.nullObject2String(megMap.get("sheetName"));
        String beanId = SheetMapingSchema.getInstance().getBeanId(tableId);
        IKnowledgeService ks = (IKnowledgeService) ApplicationContextHolder.getInstance().getBean(beanId);

        return ks.getMainFromLink(mainMap);
    }

    /**
     * 接口服务用于知识管理获取某工单的环节信息
     *
     * @param sheetKey
     * @return
     * @throws Exception
     */
    public static String getLinks(String sheetKey, String tableId) throws Exception {
        String beanId = SheetMapingSchema.getInstance().getBeanId(tableId);
        IKnowledgeService ks = (IKnowledgeService) ApplicationContextHolder.getInstance().getBean(beanId);
        return ks.getLinks(sheetKey);
    }

    public static String selectedKnowledge(String table, String sheetKey) {
        return "";
    }


    public static void main(String[] args) {
        try {
            String xml = "<root>" +
                    "<message>" +
                    "<userId>EOMS用户名称</userId>" +
                    "<sheetName>CommonFault</sheetName>" +
                    "<sheetId>gzjdr</sheetId>" +
                    "</message>" +
                    "<detail>" +
                    "<main>" +
                    "<col name=\"operateUserId\" value=\"admin\" />" +
                    "<col name=\"completeFlag\" value=\"2\" />" +
                    "</main>" +
                    "</detail>" +
                    "</root>";
            String str = KnowledgeBO.getMainFromLink(xml);
            System.out.println("str=" + str);
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
