package com.boco.eoms.message.dao;

public interface ISmsOuterConfig {
	/**
	 * 
	 * @param mobiles 发送短信的手机号码
	 * @param content 发送短信的内容
	 * @return boolean true：发送成功  false： 发送失败
	 */
	public boolean sendSms(String mobiles, String content);
	
	public boolean sendVoice(String t_no, String t_alloc_time,String t_finish_time, String t_content,String t_tel_num, String t_tel_num2, String dispatch_tel);
}
