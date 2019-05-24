package com.boco.eoms.check.model;

import java.util.Date;

public class TawCheckTransData {
	private String trans_id;
	private Date first_result;
	private Date compress_date;
	private int ne_id;
	private int ne_type;
	private int province_id;
	private String province_zh;
	private int region_id;
	private String region_zh;
	private String ne_zh;
	private double t42;
	private double t43;
	private double t45;
	private double t49;
	private double t411;
	private double t65;
	private double t66;
	private Date insert_time;
	private String deleted;
	public TawCheckTransData(){
		
	}
	public Date getCompress_date() {
		return compress_date;
	}
	public void setCompress_date(Date compress_date) {
		this.compress_date = compress_date;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public Date getFirst_result() {
		return first_result;
	}
	public void setFirst_result(Date first_result) {
		this.first_result = first_result;
	}
	public double getT411() {
		return t411;
	}
	public void setT411(double t411) {
		this.t411 = t411;
	}
	public double getT42() {
		return t42;
	}
	public void setT42(double t42) {
		this.t42 = t42;
	}
	public double getT43() {
		return t43;
	}
	public void setT43(double t43) {
		this.t43 = t43;
	}
	public double getT45() {
		return t45;
	}
	public void setT45(double t45) {
		this.t45 = t45;
	}
	public double getT49() {
		return t49;
	}
	public void setT49(double t49) {
		this.t49 = t49;
	}
	public double getT65() {
		return t65;
	}
	public void setT65(double t65) {
		this.t65 = t65;
	}
	public double getT66() {
		return t66;
	}
	public void setT66(double t66) {
		this.t66 = t66;
	}
	public String getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(String trans_id) {
		this.trans_id = trans_id;
	}
	public Date getInsert_time() {
		return insert_time;
	}
	public void setInsert_time(Date insert_time) {
		this.insert_time = insert_time;
	}
	public int getNe_id() {
		return ne_id;
	}
	public void setNe_id(int ne_id) {
		this.ne_id = ne_id;
	}
	public int getNe_type() {
		return ne_type;
	}
	public void setNe_type(int ne_type) {
		this.ne_type = ne_type;
	}
	public String getNe_zh() {
		return ne_zh;
	}
	public void setNe_zh(String ne_zh) {
		this.ne_zh = ne_zh;
	}
	public int getProvince_id() {
		return province_id;
	}
	public void setProvince_id(int province_id) {
		this.province_id = province_id;
	}
	public String getProvince_zh() {
		return province_zh;
	}
	public void setProvince_zh(String province_zh) {
		this.province_zh = province_zh;
	}
	public int getRegion_id() {
		return region_id;
	}
	public void setRegion_id(int region_id) {
		this.region_id = region_id;
	}
	public String getRegion_zh() {
		return region_zh;
	}
	public void setRegion_zh(String region_zh) {
		this.region_zh = region_zh;
	}
}
