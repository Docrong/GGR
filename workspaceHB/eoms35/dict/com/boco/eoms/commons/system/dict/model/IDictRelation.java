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
 * Date:2007-10-25 16:01:22
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public interface IDictRelation {
    //	<dict-relation id="majorrelation" sourceDictKey="sample"
    //	sourceDictId="major">
    //	<item-relation sourceItemId="1" destinationDictKey="sample"
    //		destinationDictId="3" />
    //	<item-relation sourceItemId="2" destinationDictKey="sample"
    //		destinationDictId="exigence" />
    //</dict-relation>

    /**
     * 获取源字典类
     * 
     * @return 返回字典类
     */
    public String getSourceDictKey();

    /**
     * 获取源字典id
     * 
     * @return 返回字典id
     */
    public String getSourceDictId();

    /**
     * 获取关联关系中的唯一标识id
     * 
     * @return 返回关联关系中的唯一标识id
     */
    public String getId();

    /**
     * 获取字典关联项子项
     * 
     * @return 返回字典关联项子项list
     */
    public List getDictRelationItems();

}
