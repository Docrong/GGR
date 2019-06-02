package com.boco.eoms.commons.statistic.customstat.mgr;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.statistic.base.model.StatisticFileInfo;
import com.boco.eoms.commons.statistic.customstat.model.CustomstatRemind;

public interface ICustomstatRemindManager extends Manager {
    /*
     * 根据已生成报表获得订阅发送模型
     */
	public CustomstatRemind getCustomstatRemind(StatisticFileInfo _info);
	
	
    /*
     * 1.在订阅发送模型内数据完整后调用发邮件接口定制邮件
     * 2.删除本条订阅发送模型记录
     */
	public void setCustomstatRemind(CustomstatRemind _customstatremind) throws Exception ;
	
    /*
     * 保存
     */
	public void saveCustomstatRemind(CustomstatRemind _customstatremind);
	
    /*
     * 删除
     */	
	public void deleteCustomstatRemind(CustomstatRemind _customstatremind);
	
	/**
	 * 刷新内存
	 */
	public void flush();
}
