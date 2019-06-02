package com.boco.eoms.sequence;

/**
 * <p>
 * Title:调用类封装
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Apr 24, 2008 10:09:48 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class Job {
	/**
	 * @param clz
	 *            要调用的类
	 * @param methodName
	 *            调用类的某个方法
	 * @param paramTypes
	 *            参数类型
	 * @param params
	 *            参数
	 * @param sequenceCallback
	 *            序列方法调用完成后回调（通知）的实现类
	 */
	public Job(Object clz, String methodName, Class[] paramTypes,
			Object[] params, ISequenceCallback sequenceCallback) {
		super();
		this.clz = clz;
		this.methodName = methodName;
		this.paramTypes = paramTypes;
		this.params = params;
		this.sequenceCallback = sequenceCallback;
	}

	/**
	 * 要调用的类
	 */
	private Object clz;

	/**
	 * 调用类的某个方法
	 */
	private String methodName;

	/**
	 * 参数类型
	 */
	private Class[] paramTypes;

	/**
	 * 参数
	 */
	private Object[] params;

	/**
	 * 序列方法调用完成后回调（通知）的实现类
	 */
	private ISequenceCallback sequenceCallback;

	/**
	 * 队列持久化唯一标识
	 */
	private String jobId;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	/**
	 * @return the clz
	 */
	public Object getClz() {
		return clz;
	}

	/**
	 * @param clz
	 *            the clz to set
	 */
	public void setClz(Object clz) {
		this.clz = clz;
	}

	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodName
	 *            the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * @return the paramTypes
	 */
	public Class[] getParamTypes() {
		return paramTypes;
	}

	/**
	 * @param paramTypes
	 *            the paramTypes to set
	 */
	public void setParamTypes(Class[] paramTypes) {
		this.paramTypes = paramTypes;
	}

	/**
	 * @return the params
	 */
	public Object[] getParams() {
		return params;
	}

	/**
	 * @param params
	 *            the params to set
	 */
	public void setParams(Object[] params) {
		this.params = params;
	}

	/**
	 * @return the sequenceCallback
	 */
	public ISequenceCallback getSequenceCallback() {
		return sequenceCallback;
	}

	/**
	 * @param sequenceCallback
	 *            the sequenceCallback to set
	 */
	public void setSequenceCallback(ISequenceCallback sequenceCallback) {
		this.sequenceCallback = sequenceCallback;
	}

	/**
	 * 
	 */
	public Job() {
		super();
	}

}
