package com.boco.eoms.commons.message.msg.dao;

import java.io.Serializable;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.InitializingBean;

import com.boco.eoms.base.model.BaseObject;

/*
 * 
 * @author panlong
 * Jan 7, 2008 9:03:50 AM
 */
public class TawCommonMessageMobile extends BaseObject implements Serializable {

	
	private String sms_host_ip;
	private String sms_port;
	private String sms_user;
	private String sms_pwd;
	private String sms_icp_id;
	private String sms_icp_code;
	
	

	public String getSms_host_ip() {
		return sms_host_ip;
	}

	public void setSms_host_ip(String sms_host_ip) {
		this.sms_host_ip = sms_host_ip;
	}

	public String getSms_port() {
		return sms_port;
	}

	public void setSms_port(String sms_port) {
		this.sms_port = sms_port;
	}

	public String getSms_user() {
		return sms_user;
	}

	public void setSms_user(String sms_user) {
		this.sms_user = sms_user;
	}

	public String getSms_pwd() {
		return sms_pwd;
	}

	public void setSms_pwd(String sms_pwd) {
		this.sms_pwd = sms_pwd;
	}

	public String getSms_icp_id() {
		return sms_icp_id;
	}

	public void setSms_icp_id(String sms_icp_id) {
		this.sms_icp_id = sms_icp_id;
	}

	public String getSms_icp_code() {
		return sms_icp_code;
	}

	public void setSms_icp_code(String sms_icp_code) {
		this.sms_icp_code = sms_icp_code;
	}

	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}


	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}


	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	

}

