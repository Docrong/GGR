package com.boco.eoms.km.train.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.km.train.model.TrainPlan;

/**
 * <p>
 * Title:培训计划
 * </p>
 * <p>
 * Description:培训及哈
 * </p>
 * <p>
 * Fri Jul 10 10:50:46 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public interface TrainPlanDao extends Dao {

    /**
    *
    *取培训计划列表
    * @return 返回培训计划列表
    */
    public List getTrainPlans();
    
    /**
    * 根据主键查询培训计划
    * @param id 主键
    * @return 返回某id的培训计划
    */
    public TrainPlan getTrainPlan(final String id);
    
    /**
    *
    * 保存培训计划    
    * @param trainPlan 培训计划
    * 
    */
    public void saveTrainPlan(TrainPlan trainPlan);
    
    /**
    * 根据id删除培训计划
    * @param id 主键
    * 
    */
    public void removeTrainPlan(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getTrainPlans(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
}