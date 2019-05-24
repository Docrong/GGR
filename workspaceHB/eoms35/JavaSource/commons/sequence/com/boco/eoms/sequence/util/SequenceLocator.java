package com.boco.eoms.sequence.util;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.sequence.ISequenceFacade;
import com.boco.eoms.sequence.SequenceRegister;

/**
 * <p>
 * Title:序列locator
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Apr 26, 2008 2:07:23 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class SequenceLocator {

	/**
	 * 获取序列facade
	 * 
	 * @return 序列facade
	 */
	public static ISequenceFacade getSequenceFacade() {
		return (ISequenceFacade) ApplicationContextHolder.getInstance()
				.getBean("SequenceFacade");
	}

	/**
	 * 获取序列注册模块
	 * 
	 * @return 序列facade
	 */
	public static SequenceRegister getSequenceRegister() {
		return (SequenceRegister) ApplicationContextHolder.getInstance()
				.getBean("SequenceRegister");
	}

}
