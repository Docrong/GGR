package com.boco.eoms.commons.system.dict.model;

import java.util.List;

/**
 * <p>
 * Title:字典关联
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-25 18:10:08
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class DictRelationXML implements IDictRelation {
    /**
     * 关联子项
     */
    private List dictRelationItems;

    /**
     * 关联唯一标识符
     */
    private String id;

    /**
     * 源字典id
     */
    private String sourceDictId;

    /**
     * 源字典类
     */
    private String sourceDictKey;

    /**
     * @return the dictRelationItems
     */
    public List getDictRelationItems() {
        return dictRelationItems;
    }

    /**
     * @param dictRelationItems
     *            the dictRelationItems to set
     */
    public void setDictRelationItems(List dictRelationItems) {
        this.dictRelationItems = dictRelationItems;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the sourceDictId
     */
    public String getSourceDictId() {
        return sourceDictId;
    }

    /**
     * @param sourceDictId
     *            the sourceDictId to set
     */
    public void setSourceDictId(String sourceDictId) {
        this.sourceDictId = sourceDictId;
    }

    /**
     * @return the sourceDictKey
     */
    public String getSourceDictKey() {
        return sourceDictKey;
    }

    /**
     * @param sourceDictKey
     *            the sourceDictKey to set
     */
    public void setSourceDictKey(String sourceDictKey) {
        this.sourceDictKey = sourceDictKey;
    }
}
