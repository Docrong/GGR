package com.boco.eoms.check.model;
import java.util.*;
public class TawCheckCDMADATA {
private String cdma_id;
private Date first_result;
private Date compress_date;
private int ne_id;
private int ne_type;
private int province_id;
private String province_zh;
private int region_id;
private String region_zh;
private String ne_zh;
private double c42;
private double c43;
private double c45;
private double c411;
private double c412;
private double c64;
private double c65;
private double c66;
private double vir_p26;
private Date insert_time;
private String deleted;
public double getC411() {
	return c411;
}
public void setC411(double c411) {
	this.c411 = c411;
}
public double getC412() {
	return c412;
}
public void setC412(double c412) {
	this.c412 = c412;
}
public double getC42() {
	return c42;
}
public void setC42(double c42) {
	this.c42 = c42;
}
public double getC43() {
	return c43;
}
public void setC43(double c43) {
	this.c43 = c43;
}
public double getC45() {
	return c45;
}
public void setC45(double c45) {
	this.c45 = c45;
}
public double getC64() {
	return c64;
}
public void setC64(double c64) {
	this.c64 = c64;
}
public double getC65() {
	return c65;
}
public void setC65(double c65) {
	this.c65 = c65;
}
public double getC66() {
	return c66;
}
public void setC66(double c66) {
	this.c66 = c66;
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
public double getVir_p26() {
	return vir_p26;
}
public void setVir_p26(double vir_p26) {
	this.vir_p26 = vir_p26;
}
public String getCdma_id() {
	return cdma_id;
}
public void setCdma_id(String cdma_id) {
	this.cdma_id = cdma_id;
}

public void setNameValue(String name,double value){
	if(name.equals("C42")){
		this.setC42(value);
	}else if(name.equals("C43")){
		this.setC43(value);
	}	if(name.equals("C45")){
		this.setC45(value);
	}	if(name.equals("C411")){
		this.setC411(value);
	}	if(name.equals("C64")){
		this.setC64(value);
	}	if(name.equals("C65")){
		this.setC65(value);
	}	if(name.equals("C412")){
		this.setC412(value);
	}	if(name.equals("VIR_P26")){
		this.setVir_p26(value);
	}
}
}
