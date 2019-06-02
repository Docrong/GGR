package com.boco.eoms.message.dao;

import java.io.FileNotFoundException;
import java.util.List;

public interface IMmsOuterConfig {
	/**
	 * 
	 * @param mobiles 发送短信的手机号码
	 * @param content 发送短信的内容
	 * @return int 发送标志码
	 */
	public int sendMms(String mobiles, String subject, List contentList) throws FileNotFoundException;
}
