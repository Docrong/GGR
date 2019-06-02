package com.boco.eoms.commons.statistic.base.anychart.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BlockBean {
	
	//单挑记录-多条记录-BLOCK
	private String type;
	
	//判断BLOCK类型：line、lines、column、columns
	private String style;
	
	//判断是否需要分拆周期
	private String cycle;	
	//直接应用-计算应用
	private String reference;	
	//block节点属性
	private HashMap attibute;
	
	//SQL集合
	private List sqls = new ArrayList();
	
	//多指标 n sqls n sql
	private List sqlss = new ArrayList();
	
	//SETBEANS 集合
	private List sets = new ArrayList();
	
	//DEMO使用-后期撇弃
	private String sql;
	
	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public BlockBean(){
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public HashMap getAttibute() {
		return attibute;
	}
	
	public void setAttibute(HashMap attibute) {
		this.attibute = attibute;
	}
	
	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public List getSqls() {
		return sqls;
	}
	/**
	public void setSqls(String sql) {
		this.sqls.add(sql);
	}
	**/
	public void setSqls(HashMap hmp) {
		this.sqls.add(hmp);
	}
	
	public void setSqls(List sqls) {
		this.sqls = sqls;
	}

	public List getSets() {
		return sets;
	}

	public void setSets(List sets) {
		//this.sets = sets;
		this.sets.add(sets);
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public List getSqlss() {
		return sqlss;
	}

	public void setSqlss(List sqlss) {
		this.sqlss.add(sqlss);
	}
}
