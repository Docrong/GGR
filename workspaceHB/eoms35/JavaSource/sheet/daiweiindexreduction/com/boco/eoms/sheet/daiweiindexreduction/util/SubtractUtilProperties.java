package com.boco.eoms.sheet.daiweiindexreduction.util;


import java.util.HashMap;
import java.util.Map;


/**
 * 把vulMap传入的 记录参数 转变成 核减表 的对应记录
 *
 * @author WL
 */


public class SubtractUtilProperties {

    private static SubtractUtilProperties _crmUtilProperties;

    public static SubtractUtilProperties getInstance() {
        _crmUtilProperties = new SubtractUtilProperties();
        return _crmUtilProperties;
    }

    /**
     * 将SubtractTable数据 与 核减表 字段对应，并存放到map中
     *
     * @param vulMap
     * @return map
     * @throws Exception
     */
    public Map getMapFromVulMapMethod(Map vuLMap) throws Exception {
        return this.getMapFromVulMap(vuLMap);
    }

    /**
     * 将SubtractTable数据与核减表字段对应，并存放到map中  11个字段
     *
     * @param vulMap
     * @return map
     * @throws Exception
     */
    public Map getMapFromVulMap(Map vuLMap) throws Exception {
        Map map = null;
        map = new HashMap();
        try {

            map.put("title", vuLMap.get("Title"));// 主题
            map.put("serialId", vuLMap.get("serialId"));// 工单流水号
            map.put("finalOpinion", vuLMap.get("finalOpinion"));// 终审意见
            map.put("finalCompleteLimit", vuLMap.get("finalCompleteLimit"));// 终审时间
            map.put("preliminaryOpinion", vuLMap.get("preliminaryOpinion"));// 初审意见
            map.put("preliminaryExplanation", vuLMap.get("preliminaryExplanation"));// 初审说明
            map.put("firstRejection", vuLMap.get("firstRejection"));// 第一次驳回说明
            map.put("secondRejection", vuLMap.get("secondRejection"));// 第二次驳回说明
            map.put("subtractProfessional", vuLMap.get("subtractProfessional"));// 核减专业
            map.put("subtractrCategory", vuLMap.get("subtractrCategory"));// 核减理由类别
            map.put("applicationReduction", vuLMap.get("applicationReduction"));// 申请核减说明

            // 有问题 ????

            return map;
        } catch (Exception err) {
            err.printStackTrace();
            throw new Exception("生成map出错：" + err.getMessage());
        }
    }

}
