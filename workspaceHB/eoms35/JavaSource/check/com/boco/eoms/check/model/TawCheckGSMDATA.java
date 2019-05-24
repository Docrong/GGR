package com.boco.eoms.check.model;

import java.util.Date;

public class TawCheckGSMDATA {
	private String gsm_id;
	private Date first_result;
	private Date compress_date;
	private int ne_id;
	private int ne_type;
	private int province_id;
	private String province_zh;
	private int region_id;
	private String region_zh;
	private String ne_zh;
	private double g42;
	private double g43;
	private double g45;
	private double g49;
	private double g411;
	private double g65;
	private double g66;
	private Date insert_time;
	private String deleted;
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
	public double getG411() {
		return g411;
	}
	public void setG411(double g411) {
		this.g411 = g411;
	}
	public double getG42() {
		return g42;
	}
	public void setG42(double g42) {
		this.g42 = g42;
	}
	public double getG43() {
		return g43;
	}
	public void setG43(double g43) {
		this.g43 = g43;
	}
	public double getG45() {
		return g45;
	}
	public void setG45(double g45) {
		this.g45 = g45;
	}
	public double getG49() {
		return g49;
	}
	public void setG49(double g49) {
		this.g49 = g49;
	}
	public double getG65() {
		return g65;
	}
	public void setG65(double g65) {
		this.g65 = g65;
	}
	public double getG66() {
		return g66;
	}
	public void setG66(double g66) {
		this.g66 = g66;
	}
	public String getGsm_id() {
		return gsm_id;
	}
	public void setGsm_id(String gsm_id) {
		this.gsm_id = gsm_id;
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
	public void setNameValue(String name,double value){
		if(name.equals("G42")){
			this.setG42(value);
		}else if(name.equals("G43")){
			this.setG43(value);
		}	if(name.equals("G45")){
			this.setG45(value);
		}	if(name.equals("G49")){
			this.setG49(value);
		}	if(name.equals("G65")){
			this.setG65(value);
		}	if(name.equals("G66")){
			this.setG66(value);
		}	if(name.equals("G411")){
			this.setG411(value);
		}
	}
}
