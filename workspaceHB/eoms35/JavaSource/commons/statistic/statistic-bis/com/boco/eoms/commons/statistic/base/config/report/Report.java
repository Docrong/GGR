package com.boco.eoms.commons.statistic.base.config.report;

import java.io.Serializable;

public class Report implements Serializable{
	
	private String id = null;
	
//	private String index = null;
//	
//	private String name = null;
	
//	public String getIndex() {
//		return index;
//	}
//
//	public void setIndex(String index) {
//		this.index = index;
//	}

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

	private DisplayInfo displayinfo = null;
	
	private Datas datas = null;

	public Datas getDatas() {
		return datas;
	}

	public void setDatas(Datas datas) {
		this.datas = datas;
	}

	public DisplayInfo getDisplayinfo() {
		return displayinfo;
	}

	public void setDisplayinfo(DisplayInfo displayinfo) {
		this.displayinfo = displayinfo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
