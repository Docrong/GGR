package com.boco.eoms.commons.system.dict.model;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-24 19:38:16
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class DictTagHelper implements IDictTagHelper {
	
	private String alt;
	/**
	 * @return Returns the alt.
	 */
	public String getAlt() {
		return alt;
	}
	/**
	 * @param alt The alt to set.
	 */
	public void setAlt(String alt) {
		this.alt = alt;
	}
    /**
     * html的onchange
     */
    private String onchange;

    /**
     * @return the attributes
     */
    public String getAttributes() {
        return attributes;
    }

    /**
     * @param attributes
     *            the attributes to set
     */
    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    /**
     * @return the onchange
     */
    public String getOnchange() {
        return onchange;
    }

    /**
     * @param onchange
     *            the onchange to set
     */
    public void setOnchange(String onchange) {
        this.onchange = onchange;
    }

    /**
     * html select将加入 <select ${attributes}/>
     */
    private String attributes;

    /**
     * 关联id
     */
    private String relationId;

    /**
     * 对应applicationContext-dict.xml配置时的key值，通过该key可以取得 xml path
     */
    private String key;

    /**
     * 字典id，标识某类字典，如：专业、
     */
    private String dictId;

    /**
     * 某类字典项id
     */
    private String itemId;

    /**
     * 要关联select标签Id的名称
     */
    private String selectId;

    /**
     * 是否为查询，查询带“全部”
     */
    private String isQuery;

    /**
     * 默认select标签option位置
     */
    private String defaultId;

    private String beanId;
  

    /**
     * @return the beanId
     */
    public String getBeanId() {
        return beanId;
    }

    /**
     * @param beanId
     *            the beanId to set
     */
    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }

    /**
     * @return the defaultId
     */
    public String getDefaultId() {
        return defaultId;
    }

    /**
     * @param defaultId
     *            the defaultId to set
     */
    public void setDefaultId(String defaultId) {
        this.defaultId = defaultId;
    }

    /**
     * @return the dictId
     */
    public String getDictId() {
        return dictId;
    }

    /**
     * @param dictId
     *            the dictId to set
     */
    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    /**
     * @return the isQuery
     */
    public String getIsQuery() {
        return isQuery;
    }

    /**
     * @param isQuery
     *            the isQuery to set
     */
    public void setIsQuery(String isQuery) {
        this.isQuery = isQuery;
    }

    /**
     * @return the itemId
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * @param itemId
     *            the itemId to set
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the selectId
     */
    public String getSelectId() {
        return selectId;
    }

    /**
     * @param selectId
     *            the selectId to set
     */
    public void setSelectId(String selectId) {
        this.selectId = selectId;
    }

    /**
     * @return the relationId
     */
    public String getRelationId() {
        return relationId;
    }

    /**
     * @param relationId
     *            the relationId to set
     */
    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    /**
     * 关联select id
     */
    private String subid;

    /**
     * html的class
     */
    private String cls;

    /**
     * html的style
     */
    private String style;

    /**
     * @return the cls
     */
    public String getCls() {
        return cls;
    }

    /**
     * @param cls
     *            the cls to set
     */
    public void setCls(String cls) {
        this.cls = cls;
    }

    /**
     * @return the style
     */
    public String getStyle() {
        return style;
    }

    /**
     * @param style
     *            the style to set
     */
    public void setStyle(String style) {
        this.style = style;
    }

    /**
     * @return the subid
     */
    public String getSubid() {
        return subid;
    }

    /**
     * @param subid
     *            the subid to set
     */
    public void setSubid(String subid) {
        this.subid = subid;
    }
	
}
