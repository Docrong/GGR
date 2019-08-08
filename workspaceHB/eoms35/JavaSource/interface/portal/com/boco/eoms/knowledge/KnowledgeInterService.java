package com.boco.eoms.knowledge;

import com.boco.eoms.knowledge.util.KnowledgeService;;

/**
 * 知识库接口
 *
 * @author xqz
 */
public class KnowledgeInterService {
    /**
     * 通过环节信息查询工单
     *
     * @param xmlDoc
     * @return
     */
    public String getMainFromLink(String xmlDoc) {
        try {
            System.out.println("获取工单环节信息接口:" + xmlDoc);
            String sheetIds = KnowledgeService.getMainFromLink(xmlDoc);
            System.out.println("sheetIds=" + sheetIds);
            return sheetIds;
        } catch (Exception err) {
            System.out.println("获取工单环节信息失败：");
            err.printStackTrace();
            return "";
        }
    }

    /**
     * 工单信息推送接口
     *
     * @param sheetKey
     * @return
     */
    public String selectedKnowledge(String tableId, String sheetKey) {
        try {
            System.out.println("工单信息推送接口:" + sheetKey);
            return KnowledgeService.selectedKnowledge(tableId, sheetKey);
        } catch (Exception err) {
            System.out.println("工单信息推送失败：");
            err.printStackTrace();
            return "";
        }
    }

    /**
     * 获取工单环节信息
     *
     * @param sheetKey
     * @return
     */
    public String getLinks(String sheetKey, String tableId) {
        try {
            System.out.println("环节信息传送接口:sheetKey=" + sheetKey + " tableId=" + tableId);
            return KnowledgeService.getLinks(sheetKey, tableId);
        } catch (Exception err) {
            System.out.println("环节信息传送失败：");
            err.printStackTrace();
            return "";
        }
    }

}
