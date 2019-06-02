package com.boco.eoms.commons.system.dict.model;

import java.util.List;

/**
 * <p>
 * Title:某类字典
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-24 10:44:08
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class DictXML implements IDict {

    /**
     * 描述
     */
    private String description;

    /**
     * 唯一标识
     */
    private String id;

    private List dictItems;

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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

    /*
     * (non-Javadoc)
     * 
     * @see com.boco.eoms.commons.system.dict.model.IDict#getDictDescription()
     */
    public String getDictDescription() {
        return this.getDescription();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.boco.eoms.commons.system.dict.model.IDict#getDictId()
     */
    public String getDictId() {
        return this.id;
    }

 
    /**
     * @return the dictItems
     */
    public List getDictItems() {
        return dictItems;
    }
    /**
     * @param dictItems the dictItems to set
     */
    public void setDictItems(List dictItems) {
        this.dictItems = dictItems;
    }
}
