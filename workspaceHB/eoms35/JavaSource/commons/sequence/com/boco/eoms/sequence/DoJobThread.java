package com.boco.eoms.sequence;

import org.apache.log4j.Logger;

import com.boco.eoms.sequence.util.MethodUtil;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Apr 26, 2008 3:39:24 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class DoJobThread implements Runnable {
	/**
	 * log4j
	 */
	private static Logger logger = Logger.getLogger(SequenceObserver.class);

	/**
	 * 序列
	 */
	private Sequence sequence;

	/**
	 * 构造方法
	 * 
	 * @param sequence
	 *            待执行的序列
	 */
	public DoJobThread(Sequence sequence) {
		this.sequence = sequence;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		Job job = null;
		// 执行序列中的方法
		for (; !sequence.hasNext();) {
			try {
				job = sequence.next();
				Object result = MethodUtil.invoke(job);
				// 若（通知）回调方法不为空
				if (job.getSequenceCallback() != null) {
					job.getSequenceCallback().success(job, result);
				}
			} catch (Exception e) {
				logger.error(e);
				// 出错调用
				if (job.getSequenceCallback() != null) {
					job.getSequenceCallback().fail(job, e);
				}
				// e.printStackTrace();
			} finally {
			}
		}
		
		sequence.setThreadRunning(false);
	}
}
