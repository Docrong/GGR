package com.boco.eoms.pq.model;

import com.boco.eoms.pq.util.DBSerialization;

public class HoldPQVO {

	/**
	 * 任务ID（主键）
	 */
	private String jobId;

	/**
	 * 要调用的类
	 */
	private String clz;

	/**
	 * 调用的方法
	 */
	private String method;

	/**
	 * 执行状态
	 */
	private String status;

	/**
	 * 插入队列时间
	 */
	private String insertTime;

	/**
	 * 空的构造方法
	 */
	public HoldPQVO() {
		super();
	}

	/**
	 * 通过HoldPQ构造一个VO
	 */
	public HoldPQVO(HoldPQ holdPQ) {
		this.jobId = holdPQ.getJobId();
		this.clz = DBSerialization.blob2Object(holdPQ.getClz()).getClass()
				.getName();

		// 构造完整方法名
		this.method = holdPQ.getMethodName();
		this.method += "(";
		Class[] paramTypes = (Class[]) DBSerialization.blob2Object(holdPQ
				.getParamTypes());
		Object[] params = (Object[]) DBSerialization.blob2Object(holdPQ
				.getParams());
		if (paramTypes != null && params != null && paramTypes.length > 0
				&& params.length > 0 && paramTypes.length == params.length) {
			for (int i = 0; i < paramTypes.length; i++) {
				this.method += paramTypes[i].getSimpleName() + " "
						+ params[i].toString() + ", ";
			}
		}
		if (this.method.endsWith(", ")) {
			this.method = this.method.substring(0, this.method.length() - 2);
		}
		this.method += ")";

		this.insertTime = holdPQ.getInsertTime();
		this.status = holdPQ.getStatus();

	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getClz() {
		return clz;
	}

	public void setClz(String clz) {
		this.clz = clz;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

}