package com.boco.eoms.km.expert.mgr;

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
 */
public interface KmExpertTrainMgr {

    /**
     * 取培训经历 列表
     *
     * @return 返回培训经历列表
     */
    public List getKmExpertTrains();

    /**
     * 根据主键查询培训经历
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public KmExpertTrain getKmExpertTrain(final String id);

    /**
     * 保存培训经历
     *
     * @param kmExpertTrain 培训经历
     */
    public void saveKmExpertTrain(KmExpertTrain kmExpertTrain);

    /**
     * 根据主键删除培训经历
     *
     * @param id 主键
     */
    public void removeKmExpertTrain(final String id);

    /**
     * 根据id删除批量培训经历
     *
     * @param id 主键
     */
    public void removeKmExpertTrains(final String[] ids);

    /**
     * 根据条件分页查询培训经历
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回培训经历的分页列表
     */
    public Map getKmExpertTrains(final Integer curPage, final Integer pageSize,
                                 final String whereStr);

    public Map getKmExpertTrainsByUserId(final Integer curPage, final Integer pageSize,
                                         final String userId);
}