
package com.boco.eoms.sheet.softwareLoad.model;

import java.util.Date;

import com.boco.eoms.sheet.base.model.BaseLink;

public class SoftwareLoadLink extends BaseLink
{
  
	private Date linkLoadtime ;
	private String linkLoadingsite;
	private Date linkTestTime; 
	private String linkTestsites;
	private String linkThrough ;
	private String linkPointName;
	private String linkTestresults;
	public String getLinkLoadingsite() {
		return linkLoadingsite;
	}
	public void setLinkLoadingsite(String linkLoadingsite) {
		this.linkLoadingsite = linkLoadingsite;
	}
	public String getLinkPointName() {
		return linkPointName;
	}
	public void setLinkPointName(String linkPointName) {
		this.linkPointName = linkPointName;
	}
	public String getLinkTestresults() {
		return linkTestresults;
	}
	public void setLinkTestresults(String linkTestresults) {
		this.linkTestresults = linkTestresults;
	}
	public String getLinkTestsites() {
		return linkTestsites;
	}
	public void setLinkTestsites(String linkTestsites) {
		this.linkTestsites = linkTestsites;
	}
	public String getLinkThrough() {
		return linkThrough;
	}
	public void setLinkThrough(String linkThrough) {
		this.linkThrough = linkThrough;
	}
	public Date getLinkLoadtime() {
		return linkLoadtime;
	}
	public void setLinkLoadtime(Date linkLoadtime) {
		this.linkLoadtime = linkLoadtime;
	}
	public Date getLinkTestTime() {
		return linkTestTime;
	}
	public void setLinkTestTime(Date linkTestTime) {
		this.linkTestTime = linkTestTime;
	}

}
