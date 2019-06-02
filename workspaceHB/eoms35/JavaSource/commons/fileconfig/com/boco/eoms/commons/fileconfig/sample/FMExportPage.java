package com.boco.eoms.commons.fileconfig.sample;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 27, 2007 11:15:55 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class FMExportPage {

	/**
	 * 页码名称
	 */
	private String name;

	/**
	 * 第几页
	 */
	private Integer num;

	/**
	 * list.add(object),object的包名类名
	 */
	private String className;

	/**
	 * 导出标题样式
	 */
	private FMExportTitles titles;

	/**
	 * xml头内存
	 */
	private List headers = new ArrayList();

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List getHeaders() {
		return headers;
	}

	public void setHeaders(List headers) {
		this.headers = headers;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public FMExportTitles getTitles() {
		return titles;
	}

	public void setTitles(FMExportTitles titles) {
		this.titles = titles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
