package com.boco.eoms.check.vo;
import com.boco.eoms.common.util.*;

public class TawCheckTargerVO {
	private String targer_id;
	private int targer_model_type;
	private int targer_type;
	private int targer_name;
	private String targer_per;
	private double targer_base;
	private double targer_score_base;
	private double targer_score_full;
	private double targer_add;
	private double targer_score_add;
	private double targer_del;
	private double targer_score_del;
	private String targer_remarkd;
	private String targer_deleted;
	private String targer_model_type_name;
	private String targer_type_name;
	private String targer_name_name;
	private String targer_prin;
	public String getTarger_prin() {
		return targer_prin;
	}
	public void setTarger_prin(String targer_prin) {
		this.targer_prin = targer_prin;
	}
	public String getTarger_model_type_name() {
		String targerModelTypeName=""; 
	    try
	    {
//	    	TawWsDictBO tawWsDictBO=new TawWsDictBO();
//	    	TawWsDict tawWsDict=tawWsDictBO.getDict(Integer.parseInt(StaticMethod.getNodeName("SYSTEM.DICTTYPE.kpiType")), targer_model_type);
//	    	targerModelTypeName=tawWsDict.getDictName();		    	
	    }catch(Exception e)
	    {e.printStackTrace();}
		return targerModelTypeName;
	}
	public String getTarger_name_name() {
		String targerNameName="";
		try
		{
//    	TawWsDictBO tawWsDictBO=new TawWsDictBO();
//    	TawWsDict tawWsDict=tawWsDictBO.getDict(Integer.parseInt(StaticMethod.getNodeName("SYSTEM.DICTTYPE.kpiConType")), targer_name);
//    	targerNameName=tawWsDict.getDictName();
		}catch(Exception e)
		{e.printStackTrace();}
		return targerNameName;
	}
	public String getTarger_type_name() {
		String targerTypeName="";
		try
		{
//	    	TawWsDictBO tawWsDictBO=new TawWsDictBO();
//	    	TawWsDict tawWsDict=tawWsDictBO.getDict(Integer.parseInt(StaticMethod.getNodeName("SYSTEM.DICTTYPE.kpiWebType")), targer_type);
//	    	targerTypeName=tawWsDict.getDictName();			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return targerTypeName;
	}
	public double getTarger_add() {
		return targer_add;
	}
	public void setTarger_add(double targer_add) {
		this.targer_add = targer_add;
	}
	public double getTarger_base() {
		return targer_base;
	}
	public void setTarger_base(double targer_base) {
		this.targer_base = targer_base;
	}
	public double getTarger_del() {
		return targer_del;
	}
	public void setTarger_del(double targer_del) {
		this.targer_del = targer_del;
	}
	public String getTarger_deleted() {
		return targer_deleted;
	}
	public void setTarger_deleted(String targer_deleted) {
		this.targer_deleted = targer_deleted;
	}
	public String getTarger_id() {
		return targer_id;
	}
	public void setTarger_id(String targer_id) {
		this.targer_id = targer_id;
	}
	public int getTarger_model_type() {
		return targer_model_type;
	}
	public void setTarger_model_type(int targer_model_type) {
		this.targer_model_type = targer_model_type;
	}
	public int getTarger_name() {
		return targer_name;
	}
	public void setTarger_name(int targer_name) {
		this.targer_name = targer_name;
	}
	public String getTarger_per() {
		return targer_per;
	}
	public void setTarger_per(String targer_per) {
		this.targer_per = targer_per;
	}
	public String getTarger_remarkd() {
		return targer_remarkd;
	}
	public void setTarger_remarkd(String targer_remarkd) {
		this.targer_remarkd = targer_remarkd;
	}
	public double getTarger_score_add() {
		return targer_score_add;
	}
	public void setTarger_score_add(double targer_score_add) {
		this.targer_score_add = targer_score_add;
	}
	public double getTarger_score_base() {
		return targer_score_base;
	}
	public void setTarger_score_base(double targer_score_base) {
		this.targer_score_base = targer_score_base;
	}
	public double getTarger_score_del() {
		return targer_score_del;
	}
	public void setTarger_score_del(double targer_score_del) {
		this.targer_score_del = targer_score_del;
	}
	public double getTarger_score_full() {
		return targer_score_full;
	}
	public void setTarger_score_full(double targer_score_full) {
		this.targer_score_full = targer_score_full;
	}
	public int getTarger_type() {
		return targer_type;
	}
	public void setTarger_type(int targer_type) {
		this.targer_type = targer_type;
	}

}
