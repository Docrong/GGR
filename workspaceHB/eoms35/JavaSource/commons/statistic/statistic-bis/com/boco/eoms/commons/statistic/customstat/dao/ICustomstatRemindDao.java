package com.boco.eoms.commons.statistic.customstat.dao;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.statistic.customstat.model.CustomstatRemind;

public interface ICustomstatRemindDao extends Dao{
    /*
     * 获得订阅发送模型
     */
	public CustomstatRemind getCustomstatRemind(CustomstatRemind _customstatremind);
	
	
    /*
     * 保存
     */
	public void saveCustomstatRemind(CustomstatRemind _customstatremind);
	
    /*
     * 删除
     */	
	public void deleteCustomstatRemind(CustomstatRemind _customstatremind);
	
	/**
	 * 刷新内存中的对象
	 */
	public void flush();
	
	
}
