package com.boco.eoms.commons.system.dict.model;

import java.util.List;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-24 10:42:09
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public interface IDict {
    /**
     * 某类字典的描述信息
     * 
     * @return
     */
    public String getDictDescription();

    /**
     * 某类字典id
     * 
     * @return
     */
    public String getDictId();

    /**
     * 获取该字典下的所有字典项
     * 
     * @return 返回字典项列表
     */
    public List getDictItems();
}
