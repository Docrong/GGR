package com.boco.eoms.commons.job.exception;

import com.boco.eoms.commons.exceptions.base.BusinessException;
/**
 * 
 * <p>Title:
 * </p>
 * <p>Description:任务组件异常处理类
 * </p>
 * <p>Apr 10, 2007 10:32:38 AM</p> 
 *
 * @author 秦敏
 * @version 1.0
 *
 */
public class ScheduleRunException extends BusinessException {

	public ScheduleRunException(String message) {
		super(message);
	}
}
