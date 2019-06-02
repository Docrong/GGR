package com.boco.eoms.commons.system.dict.model;

/**
 * <p>
 * Title:无论是db,xml存储方式，字典类必须提供以下几个方法
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-24 9:26:54
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public interface IDictItem {
    /**
     * 名称
     *  
     */
    public Object getItemName();

    /**
     * 描述
     *  
     */
    public Object getItemDescription();

    /**
     * 唯一标识
     *  
     */
    public Object getItemId();
}
