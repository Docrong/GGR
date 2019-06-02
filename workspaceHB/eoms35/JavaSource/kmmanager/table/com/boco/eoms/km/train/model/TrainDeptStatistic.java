package com.boco.eoms.km.train.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:报名信息
 * </p>
 * <p>
 * Description:报名信息
 * </p>
 * <p>
 * Fri Jul 10 10:50:46 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public class TrainDeptStatistic extends BaseObject {

	/**
	 * 主键
	 */
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 部门名字
	 */
	private String trainDept;
	
	/**
	 *  培训次数
	 */
	private Integer trainCount;
	
	/**
	 * 培训天数
	 */
	private Integer timeCount;

	public boolean equals(Object o) {
		if( o instanceof TrainDeptStatistic ) {
			TrainDeptStatistic trainEnter=(TrainDeptStatistic)o;
			if (this.id != null || this.id.equals(trainEnter.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public String getTrainDept() {
		return trainDept;
	}

	public void setTrainDept(String trainDept) {
		this.trainDept = trainDept;
	}

	public Integer getTrainCount() {
		return trainCount;
	}

	public void setTrainCount(Integer trainCount) {
		this.trainCount = trainCount;
	}

	public Integer getTimeCount() {
		return timeCount;
	}

	public void setTimeCount(Integer timeCount) {
		this.timeCount = timeCount;
	}
}