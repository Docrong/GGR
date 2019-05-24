package com.boco.eoms.commons.message.util;

import java.io.Serializable;

public class TawCommonMessageDictUtil implements Serializable {

//	private String issendimediat;
//	private String issendimediatname;
//	
//	private String issendnight;
//	private String issendnightname;
//	
//	private String urgency;
//	private String urgencyname;
//	
//	private String messagetype;
//	private String messagetypename;
	
	public String getIssendimediat(String name) {
		if( name == null ){
			return "";
		}
		if( name.equals("YES") ){
			return "1";
		}else if( name.equals("NO")){
			return "0";
		}
		return "";
	}
	public String getIssendimediatname(String num) {
		if( num == null ){
			return "否";
		}
		if( num.equals("110301") ){
			return "是";
		}else if( num.equals("110302")){
			return "否";
		}
		return "错误";
	} 
	public String getIssendnight(String name) {
		if( name == null ){
			return "";
		}
		if( name.equals("YES") ){
			return "110201";
		}else if( name.equals("NO")){
			return "110202";
		}
		return "";
	}
	public String getIssendnightname(String num) {
		if( num == null ){
			return "";
		}
		if( num.equals("110201")){
			return "是";
		}else if( num.equals("110202")){
			return "否";
		}
		return "错误";
	}
	public String getMessagetype(String name) {
		if( name == null ){
			return "";
		}
		if( name.equals("MOBILE") ){
			return "1";
		}else if( name.equals("EMAIL")){
			return "2";
		}else if( name.equals("SOUND")){
			return "3";
		}
		return "";
	}
	public String getMessagetypename(String num) {
		if( num == null ){
			return "";
		}
		if( num.equals("110402")){
			return "手机";
		}else if( num.equals("110401")){
			return "EMAIL";
		}else if( num.equals("110403")){
			return "语音";
		}
		return "错误";
	}
	
	public String getUrgency(String name) {
		if( name == null ){
			return "";
		}
		if( name.equals("JY") ){
			return "1";
		}else if( name.equals("YB")){
			return "2";
		}
		return "";
	}
	public String getUrgencyname(String num) {
		if( num == null ){
			return "一般";
		}
		if( num.equals("110501")){
			return "紧急";
		}else if( num.equals("110502")){
			return "一般";
		}
		return "错误";
	}
	
	
	
}
