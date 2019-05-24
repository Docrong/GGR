package com.boco.eoms.km.train.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.km.train.model.TrainEnter;

/**
 * <p>
 * Title:报名信息
 * </p>
 * <p>
 * Description:报名信息
 * </p>
 * <p>
 * Fri Jul 10 10:50:46 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public interface TrainEnterDao extends Dao {

    /**
    *
    *取报名信息列表
    * @return 返回报名信息列表
    */
    public List getTrainEnters();
    
    /**
    * 根据主键查询报名信息
    * @param id 主键
    * @return 返回某id的报名信息
    */
    public TrainEnter getTrainEnter(final String id);
    
    /**
	 * 培训计划的报名信息
	 * @return
	 */
	public List getTrainEntersByPlanId(final String trainPlanId);
    
    /**
    *
    * 保存报名信息    
    * @param trainEnter 报名信息
    * 
    */
    public void saveTrainEnter(TrainEnter trainEnter);
    
    /**
    * 根据id删除报名信息
    * @param id 主键
    * 
    */
    public void removeTrainEnter(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getTrainEnters(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
}