package com.boco.eoms.commons.system.dict.model;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-26 10:36:38
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public interface IDictTagHelper {

    /**
     * @param subid
     *            the subid to set
     */
    public void setSubid(String subid);

    /**
     * @param defaultId
     *            the defaultId to set
     */
    public void setDefaultId(String defaultId);

    /**
     * @param dictId
     *            the dictId to set
     */
    public void setDictId(String dictId);

    /**
     * @param isQuery
     *            the isQuery to set
     */
    public void setIsQuery(String isQuery);

    /**
     * @param itemId
     *            the itemId to set
     */
    public void setItemId(String itemId);

    /**
     * @param beanId
     *            the beanId to set
     */
    public void setBeanId(String beanId);

    /**
     * @param key
     *            the key to set
     */
    public void setKey(String key);

    /**
     * @param relationId
     *            the relationId to set
     */
    public void setRelationId(String relationId);

    /**
     * @param selectId
     *            the selectId to set
     */
    public void setSelectId(String selectId);

    /**
     * @return the isQuery
     */
    public String getIsQuery();

    /**
     * @return the itemId
     */
    public String getItemId();

    /**
     * @return the key
     */
    public String getKey();

    /**
     * @return the relationId
     */
    public String getRelationId();

    /**
     * @return the subid
     */
    public String getSubid();

    /**
     * @return the selectId
     */
    public String getSelectId();

    /**
     * 
     * @return 字典id
     */
    public String getDictId();

    /**
     * @return the beanId
     */
    public String getBeanId();

    /**
     * @return the defaultId
     */
    public String getDefaultId();

    /**
     * html 的class 属性
     * 
     * @return
     */
    public String getCls();

    /**
     * html 的cls属性设置
     * 
     * @param cls
     */
    public void setCls(String cls);

    /**
     * html的style
     * 
     * @return
     */
    public String getStyle();

    /**
     * html的style
     * 
     * @param style
     */
    public void setStyle(String style);

    /**
     * html的onchange
     * 
     * @param onchange
     */
    public void setOnchange(String onchange);

    /**
     * html的onchange
     *  
     */
    public String getOnchange();

    /**
     * html select将加入 <select ${attribute}/>
     * 
     * @param attributes
     */
    public void setAttributes(String attributes);

    /**
     * html select将加入 <select ${attribute}/>
     * 
     * @param attributes
     */
    public String getAttributes();
    
    /**
	 * @return Returns the alt.
	 */
	public String getAlt();
	/**
	 * @param alt The alt to set.
	 */
	public void setAlt(String alt);
}
