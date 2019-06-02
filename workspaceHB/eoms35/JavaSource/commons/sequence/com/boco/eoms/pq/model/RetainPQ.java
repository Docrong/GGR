package com.boco.eoms.pq.model;

import java.io.Serializable;
import java.sql.Blob;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.pq.callback.RetainPQCallbackImpl;
import com.boco.eoms.pq.util.Constants;
import com.boco.eoms.pq.util.DBSerialization;
import com.boco.eoms.sequence.ISequenceCallback;
import com.boco.eoms.sequence.Job;

public class RetainPQ implements Serializable {

	/**
	 * 自动生成序列号
	 */
	private static final long serialVersionUID = -9084576828298723175L;

	/**
	 * 任务ID（主键）
	 */
	private String jobId;

	/**
	 * 要调用的类
	 */
	private Blob clz;

	/**
	 * 调用的方法
	 */
	private String methodName;

	/**
	 * 参数类型
	 */
	private Blob paramTypes;

	/**
	 * 参数
	 */
	private Blob params;

	/**
	 * 序列方法调用完成后回调（通知）的实现类
	 */
	private Blob sequenceCallback;

	/**
	 * 执行状态
	 */
	private String status;

	/**
	 * 插入队列时间
	 */
	private String insertTime;

	/**
	 * 执行失败次数
	 */
	private String failCount;

	/**
	 * 删除标志
	 */
	private String deleted;

	/**
	 * 空的构造方法
	 */
	public RetainPQ() {
		super();
	}

	/**
	 * 用MQ任务初始化一个RetainPQ
	 * 
	 * @param job
	 *            MQ任务
	 */
	public RetainPQ(Job job) {
		this.clz = DBSerialization.object2Blob(job.getClz());
		this.methodName = job.getMethodName();
		this.paramTypes = DBSerialization.object2Blob(job.getParamTypes());
		this.params = DBSerialization.object2Blob(job.getParams());
		// 强制设置Job的sequenceCallback
		this.sequenceCallback = DBSerialization
				.object2Blob(new RetainPQCallbackImpl());
		this.status = Constants.Q_STATUS_WAITING;
		this.insertTime = StaticMethod.getCurrentDateTime();
		this.setFailCount("0");
		this.setDeleted(Constants.UNDELETED);
	}

	/**
	 * 将当前PQ转换为Job
	 * 
	 * @return 任务
	 */
	public Job convert2Job() {
		Job job = new Job();
		job.setJobId(this.getJobId());
		job.setClz(DBSerialization.blob2Object(this.getClz()));
		job.setMethodName(this.getMethodName());
		job.setParamTypes((Class[]) DBSerialization.blob2Object(this
				.getParamTypes()));
		job.setParams((Object[]) DBSerialization.blob2Object(this.getParams()));
		job.setSequenceCallback((ISequenceCallback) DBSerialization
				.blob2Object(this.getSequenceCallback()));
		return job;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public Blob getClz() {
		return clz;
	}

	public void setClz(Blob clz) {
		this.clz = clz;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Blob getParamTypes() {
		return paramTypes;
	}

	public void setParamTypes(Blob paramTypes) {
		this.paramTypes = paramTypes;
	}

	public Blob getParams() {
		return params;
	}

	public void setParams(Blob params) {
		this.params = params;
	}

	public Blob getSequenceCallback() {
		return sequenceCallback;
	}

	public void setSequenceCallback(Blob sequenceCallback) {
		this.sequenceCallback = sequenceCallback;
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

	public String getFailCount() {
		return failCount;
	}

	public void setFailCount(String failCount) {
		this.failCount = failCount;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

}
