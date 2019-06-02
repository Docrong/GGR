package com.boco.eoms.commons.file.config.model;

/**
 * <p>
 * Title:<font family="宋体" weight="bold" style="italic" size="12" color="red" >
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 27, 2007 11:26:27 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class FMExportFont {

	/**
	 * family="宋体"
	 */
	private String family;

	/**
	 * weight="bold"
	 */
	private String weight;

	/**
	 * style="italic"
	 */
	private String style;

	/**
	 * size="12"
	 */
	private Integer size;

	/**
	 * color="red"
	 */
	private String color;

	/**
	 * 要在pdf,word,excel写入的文本标题内容
	 */
	private String text;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
