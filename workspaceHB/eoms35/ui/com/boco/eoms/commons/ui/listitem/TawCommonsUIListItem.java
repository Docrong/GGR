package com.boco.eoms.commons.ui.listitem;

/**
 * @author panlong
 *
 */
public class TawCommonsUIListItem implements java.io.Serializable{
	
	//主键ID
	public String itemId;
	
	//名称
	public String name;
	
	//文本
	public String text;
	
	//保存的值
	public String value;
	
	//链接地址
	public String url;

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return Returns the itemId.
	 */
	public String getItemId() {
		return itemId;
	}
	/**
	 * @param itemId The itemId to set.
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return Returns the text.
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text The text to set.
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
