package com.boco.eoms.commons.system.dict.util;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-24 11:38:32
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class Constants {
    /**
     * xml字典中dictId中的分隔符，一般为key&dictId中的&
     */
    public final static String DICT_ID_SPLIT_CHAR = "#";

    /**
     * 当查找不到对应id名称时显示
     */
    public final static String ID_NO_NAME = "";

    /**
     * 下拉列表查询全部的ID
     */
    public final static String SELECT_QUERY_ALL_ID = "-1";

    /**
     * 下拉列表查询全部的name
     */
    public final static String SELECT_QUERY_ALL_NAME = "全部";

    /**
     * 在关联关系dict-relation.xml配置中，目的关联的多个id,以逗号隔开
     */
    public final static String DESTINATION_ITEM_IDS_SPLIT = ",";
}
