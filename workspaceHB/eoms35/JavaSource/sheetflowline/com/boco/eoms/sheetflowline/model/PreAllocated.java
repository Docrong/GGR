package com.boco.eoms.sheetflowline.model;

import java.util.Date;


/**
 * 预分配
 * @author liendan
 *
 */
public class PreAllocated {
	
	private String id;
	
	
	private String dutyLeader;//值班长
	
	private Date startTime;//开始时间
	
	private Date endTime;//结束时间
	
	private String netTypeOne;//网络一级分类
	
	private String netTypeTwo;//网络二级分类
	
	private String netTypeThree;//网络三级分类
	
	private String vendor;//厂商
	
	private String dutyUserId;//值班人员
	
	private String faultResponseLevel;//故障响应级别
	
	private int count;//分配数量
	
	private int alreadyCount;//已经分配的数量
	
	private String isopen;//开关 0：关 1：开
	
	private Date createTime;//创建时间
	
	private String createUser;//创建人
	
	

	public PreAllocated() {
		
	}

	

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}


	public String getDutyLeader() {
		return dutyLeader;
	}

	public void setDutyLeader(String dutyLeader) {
		this.dutyLeader = dutyLeader;
	}

	public String getDutyUserId() {
		return dutyUserId;
	}

	public void setDutyUserId(String dutyUserId) {
		this.dutyUserId = dutyUserId;
	}

	

	public String getFaultResponseLevel() {
		return faultResponseLevel;
	}

	public void setFaultResponseLevel(String faultResponseLevel) {
		this.faultResponseLevel = faultResponseLevel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsopen() {
		return isopen;
	}

	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}

	public String getNetTypeOne() {
		return netTypeOne;
	}

	public void setNetTypeOne(String netTypeOne) {
		this.netTypeOne = netTypeOne;
	}

	public String getNetTypeThree() {
		return netTypeThree;
	}

	public void setNetTypeThree(String netTypeThree) {
		this.netTypeThree = netTypeThree;
	}

	public String getNetTypeTwo() {
		return netTypeTwo;
	}

	public void setNetTypeTwo(String netTypeTwo) {
		this.netTypeTwo = netTypeTwo;
	}


	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public Date getEndTime() {
		return endTime;
	}



	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}



	public Date getStartTime() {
		return startTime;
	}



	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}



	public int getAlreadyCount() {
		return alreadyCount;
	}



	public void setAlreadyCount(int alreadyCount) {
		this.alreadyCount = alreadyCount;
	}

	
	

	
}