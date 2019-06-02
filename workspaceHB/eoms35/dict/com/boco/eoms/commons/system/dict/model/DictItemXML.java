package com.boco.eoms.commons.system.dict.model;

/**
 * <p>
 * Title:xml字典，mapping xml配置类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-23 19:16:04
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class DictItemXML implements IDictItem {
    /**
     * 唯一标识
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 属性
     */
    private String description;

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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.boco.eoms.commons.system.dict.model.IDict#getDictDescription()
     */
    public Object getItemDescription() {
        return this.description;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.boco.eoms.commons.system.dict.model.IDict#getDictId()
     */
    public Object getItemId() {
        return this.id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.boco.eoms.commons.system.dict.model.IDict#getDictName()
     */
    public Object getItemName() {
        return this.name;
    }
}
