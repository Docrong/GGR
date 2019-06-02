package com.boco.eoms.km.expert.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.km.expert.model.KmExpertTrain;

/**
 * <p>
 * Title:培训经历
 * </p>
 * <p>
 * Description:培训经历
 * </p>
 * <p>
 * Mon Jun 15 18:07:23 CST 2009
 * </p>
 * 
 * @author zhangxb
 * @version 1.0
 * 
 */
public interface KmExpertTrainDao extends Dao {

    /**
    *
    *取培训经历列表
    * @return 返回培训经历列表
    */
    public List getKmExpertTrains();
    
    /**
    * 根据主键查询培训经历
    * @param id 主键
    * @return 返回某id的培训经历
    */
    public KmExpertTrain getKmExpertTrain(final String id);
    
    /**
    *
    * 保存培训经历    
    * @param kmExpertTrain 培训经历
    * 
    */
    public void saveKmExpertTrain(KmExpertTrain kmExpertTrain);
    
    /**
    * 根据id删除培训经历
    * @param id 主键
    * 
    */
    public void removeKmExpertTrain(final String id);
    
    /**
     * 根据id删除批量培训经历
     * @param id 主键
     * 
     */
     public void removeKmExpertTrains(final String[] ids);
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getKmExpertTrains(final Integer curPage, final Integer pageSize,
			final String whereStr);

    public Map getKmExpertTrainsByUserId(final Integer curPage, final Integer pageSize,
			final String userId);
}