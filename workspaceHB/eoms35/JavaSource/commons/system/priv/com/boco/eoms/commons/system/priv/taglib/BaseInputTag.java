package com.boco.eoms.commons.system.priv.taglib;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.tagext.TagSupport;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Apr 11, 2008 11:48:59 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class BaseInputTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6858378781950803658L;

	/**
	 * 唯一标识
	 */
	private String id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 单击事件
	 */
	private String onClick;

	/**
	 * 类型，如type="button",type="text"
	 */
	private String type;

	/**
	 * 样式
	 */
	private String styleClass;

	/**
	 * 值
	 */
	private String value;

	/**
	 * message key,指
	 */
	private String key;

	/**
	 * 权限使用的动作url
	 */
	private String url;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOnClick() {
		return onClick;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 拼写html tag，如type="text" id="1" name="1" -> <input type="text" id="1"
	 * name="1">
	 * 
	 * @param html
	 *            如：type="text" id="1" name="1"
	 * @return <input type="text" id="1" name="1">
	 */
	protected String pieceHTMLTag(String html) {
		return "<input " + html + ">";
	}

	/**
	 * 取国际化文件中的属性值
	 * 
	 * @param key
	 *            资源文件位置
	 * @param value
	 *            property名称
	 * 
	 * @param locale
	 *            国际化语言环境
	 * 
	 * @return 资源文件中property的值
	 */
	protected String getPropertyValue(String key, String value, Locale locale) {
		// 国际化取值
		ResourceBundle resource = ResourceBundle.getBundle(key, locale);
		String _value = resource.getString(value);
		return _value;
	}

	/**
	 * 拼写属性值对
	 * 
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 * @return 如：name='value'
	 */
	protected String pieceProperty(String name, String value) {
		StringBuffer temp = new StringBuffer("");
		// 若值不为空则拼名值对串，如name='value'
		if (null != value && !"".equals(value)) {
			temp.append(name);
			temp.append("='");
			temp.append(value);
			temp.append("' ");
		}
		return temp.toString();
	}

	/**
	 * 拼写html，将id,value等拼写出后半部的html，若将来新增、修改属性仅改此
	 * 
	 * @return 拼写后的html，如"id="id" name="" value="" onClick=""
	 */
	protected String pieceHTML() {
		StringBuffer temp = new StringBuffer("");
		// input id
		temp.append(pieceProperty("id", id));
		if (null != this.key && null != this.value) {
			// name属性值要从国际化资源文件中获取
			temp.append(pieceProperty(name, getPropertyValue(this.key,
					this.value, pageContext.getRequest().getLocale())));
		}
		// input onClick
		temp.append(pieceProperty("onClick", this.onClick));
		// input styleClass
		temp.append(pieceProperty("styleClass", this.styleClass));
		// input type
		temp.append(pieceProperty("type", type));
		return temp.toString();
	}

}
