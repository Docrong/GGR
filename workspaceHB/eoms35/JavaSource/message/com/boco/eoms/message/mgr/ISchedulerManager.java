
package com.boco.eoms.message.mgr;

import java.util.List;

import com.boco.eoms.base.service.Manager;

public interface ISchedulerManager extends Manager {
	/**
     * 短信轮询
     * @param tel
     * @param msg
     * @return
     */
    public boolean smsMonitorScheduler(String mobile, String content);
    /**
     * 彩信轮询
     * @param mobiles
     * @param subject
     * @param contentList
     * @return
     */
    public boolean mmsMonitorScheduler(String mobiles, String subject, List contentList);
    /**
     * 语音轮询
     * @param t_no
     * @param t_alloc_time
     * @param t_finish_time
     * @param t_content
     * @param t_tel_num
     * @param t_tel_num2
     * @param dispatch_tel
     * @return
     */
    public boolean voiceMonitorScheduler(String t_no, String t_alloc_time, String t_finish_time, String t_content, String t_tel_num, String t_tel_num2, String dispatch_tel);

}

