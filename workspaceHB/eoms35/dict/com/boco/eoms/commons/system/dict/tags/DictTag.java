package com.boco.eoms.commons.system.dict.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.model.DictTagHelper;
import com.boco.eoms.commons.system.dict.model.IDictTagHelper;

/**
 * <p>
 * Title:字典标签
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-25 12:44:34
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class DictTag extends TagSupport implements IDictTagHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6655621190100556376L;

	/**
	 * html select将加入 <select ${attributes}/>
	 */
	private String attributes;

	/**
	 * html的onchange
	 */
	private String onchange;

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
	 * 关联字典对应的字典id
	 */
	private String relationId;

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

	/**
	 * spring中的beanId
	 */
	private String beanId;

	/**
	 * 关联select id
	 */
	private String subid;

	/**
	 * alt属性
	 */
	private String alt;

	

	/**
	 * @return Returns the alt.
	 */
	public String getAlt() {
		return alt;
	}

	/**
	 * @param alt
	 *            The alt to set.
	 */
	public void setAlt(String alt) {
		this.alt = alt;
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

	/**
	 * @param defaultId
	 *            the defaultId to set
	 */
	public void setDefaultId(String defaultId) {
		this.defaultId = defaultId;
	}

	/**
	 * @param dict
	 *            the dict to set
	 */
	public void setDict(DictTagHelper dict) {
		this.dict = dict;
	}

	/**
	 * @param dictId
	 *            the dictId to set
	 */
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	/**
	 * @return the beanId
	 */
	public String getBeanId() {
		return beanId;
	}

	/**
	 * @return the defaultId
	 */
	public String getDefaultId() {
		return defaultId;
	}

	/**
	 * @return the dictId
	 */
	public String getDictId() {
		return dictId;
	}

	/**
	 * @param isQuery
	 *            the isQuery to set
	 */
	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}

	/**
	 * @param itemId
	 *            the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/**
	 * @param beanId
	 *            the beanId to set
	 */
	public void setBeanId(String beanId) {
		this.beanId = beanId;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @param relationId
	 *            the relationId to set
	 */
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	/**
	 * @param selectId
	 *            the selectId to set
	 */
	public void setSelectId(String selectId) {
		this.selectId = selectId;
	}

	/**
	 * @return the isQuery
	 */
	public String getIsQuery() {
		return isQuery;
	}

	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @return the relationId
	 */
	public String getRelationId() {
		return relationId;
	}

	/**
	 * @return the selectId
	 */
	public String getSelectId() {
		return selectId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		// 标签起始时就创建辅助类
		init();
		// 执行策略类的doStartTag
		return this.helper.doStartTag(this.dict, this.pageContext);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		// 执行策略类的doEngTag方法
		return this.helper.doEndTag(this.dict, this.pageContext);
	}

	/**
	 * 做为参数传递给策略类
	 */
	private DictTagHelper dict;

	private IDictTagStrategyHelper helper;

	/**
	 * html的style
	 */
	private String style;

	/**
	 * html的cls
	 */
	private String cls;

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
	 * 创建dictTagHelper对象，此对象传递给策略类
	 * 
	 */
	private void init() {
		dict = new DictTagHelper();
		dict.setDefaultId(this.defaultId);
		dict.setDictId(this.dictId);
		dict.setIsQuery(this.isQuery);
		dict.setItemId(this.itemId);
		dict.setKey(this.key);
		dict.setRelationId(this.relationId);
		dict.setSelectId(this.selectId);
		dict.setSubid(this.subid);
		dict.setCls(this.cls);
		dict.setStyle(this.style);
		dict.setAttributes(this.attributes);
		dict.setOnchange(this.onchange);
		dict.setAlt(this.alt);
		// 创建策略类，其中真正对标签操作
		helper = (IDictTagStrategyHelper) ApplicationContextHolder
				.getInstance().getBean(this.beanId);
	}

	/**
	 * @return the attribute
	 */
	public String getAttributes() {
		return attributes;
	}

	/**
	 * @param attribute
	 *            the attribute to set
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

	
}
