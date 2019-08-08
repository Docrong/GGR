package com.boco.eoms.eva.mgr;

import java.util.ArrayList;

import com.boco.eoms.eva.model.EvaKpi;

/**
 * <p>
 * Title:指标业务方法类
 * </p>
 * <p>
 * Description:指标业务方法类
 * </p>
 * <p>
 * Date:2008-11-20 上午11:06:40
 * </p>
 *
 * @author 李秋野
 * @version 3.5.1
 */
public interface IEvaKpiMgr {

    /**
     * 根据主键id查询指标
     *
     * @param id 主键
     * @return
     */
    public EvaKpi getKpi(String id);

    /**
     * 根据主键id标志和删除查询指标
     *
     * @param id      主键
     * @param deleted 删除标志
     * @return
     */
    public EvaKpi getKpi(String id, String deleted);

    /**
     * 保存指标类型
     *
     * @param EvaKpi 指标
     */
    public void saveKpi(EvaKpi kpi);

    /**
     * 保存指标类型，同时保存节点
     *
     * @param kpi          指标
     * @param parentNodeId 父节点ID
     */
    public void saveKpiAndNode(EvaKpi kpi, String parentNodeId);

    /**
     * 删除kpi指标
     *
     * @param kpi 指标
     */
    public void removeKpi(EvaKpi kpi);

    /**
     * 通过节点Id取指标
     *
     * @param nodeId
     */
    public EvaKpi getKpiByNodeId(String nodeId);

    /**
     * 查询某指标分类下的所有指标
     *
     * @param parentNodeId 指标分类id
     * @return
     */
    public ArrayList listKpiOfType(String parentNodeId);

    /**
     * 删除某指标分类下的所有指标
     *
     * @param parentNodeId 指标分类id
     * @return
     */
    public void removeKpiOfType(final String parentNodeId);

    /**
     * 获得某指标在某模板的某个周期的考核分数
     *
     * @param templateId 模板Id
     * @param kpiId      指标Id
     * @param date       日期
     * @return
     */
    public Float getScoreOfKpi(String templateId, String kpiId, String date);

    /**
     * 根据指标Id返回指标名称
     *
     * @param id 指标Id
     * @return
     */
    public String id2Name(String id);

    /**
     * 计算某节点下目前可分配权重
     *
     * @param parentNodeId 父节点Id
     * @return
     */
    public Float getNextLevelUsableWt(String parentNodeId);

    /**
     * 计算某节点目前可调整权重的最小值
     *
     * @param parentNodeId 父节点Id
     * @param kpiId        指标Id（修改指标时计算权重传入kpiId，新增指标时计算权重则传入空字符串）
     * @return
     */
    public Float getMinWt(String parentNodeId, String kpiId);

    /**
     * 计算某节点目前可调整权重的最大值
     *
     * @param parentNodeId 父节点Id
     * @param kpiId        指标Id（修改指标时计算权重传入kpiId，新增指标时计算权重则传入空字符串）
     * @return
     */
    public Float getMaxWt(String parentNodeId, String kpiId);
}
