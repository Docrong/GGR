
package com.boco.eoms.message.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2009-7-21 下午03:40:02
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 * 
 */
public interface ISchedulerDao extends Dao {

    /**
     * 短信轮询
     * @param tel
     * @param msg
     * @return
     */
    public boolean smsMonitorScheduler(String mobiles, String content);
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

