package com.boco.eoms.commons.system.dict.util;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-19 10:46:58
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class Util {

    /**
     * 当查找不到对应id名称时显示
     * 
     * @return
     */
    public static String idNoName() {
        return Constants.ID_NO_NAME;
    }

    /**
     * 通过注册过的dict.xml的key组合字典类别(dictId)形成xml dao中要求的dictId
     * 如：key=sample,dictId=major 方法返回sampe&major
     * 
     * @param key
     *            注册过的dict.xml的key
     * @param id
     *            字典类别
     * @return key=sample,dictId=major 方法返回sampe&major
     */
    public static String constituteDictId(String key, String id) {
        return key + Constants.DICT_ID_SPLIT_CHAR + id;
    }

    /**
     * 取字典xml地址
     * 
     * @param dictId
     *            如：key&major
     * @return classpath:com/boco/eoms/sample.xml
     */
    public static String getDictKey(String dictId) {
        return dictId.split(Constants.DICT_ID_SPLIT_CHAR)[0];
    }

    /**
     * 取字典类别id
     * 
     * @param dictId
     *            如：key&major
     * @return major
     */
    public static String getId(String dictId) {
        return dictId.split(Constants.DICT_ID_SPLIT_CHAR)[1];
    }

    /**
     * 在关联关系dict-relation.xml配置中，目的关联的多个id,以逗号隔开
     * 
     * @return
     */
    public static String getDestinationItemIdsSplit() {
        return Constants.DESTINATION_ITEM_IDS_SPLIT;
    }
}
