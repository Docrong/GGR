package com.boco.eoms.km.kmAuditerStep.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.km.kmAuditer.model.KmAuditer;
import com.boco.eoms.km.kmAuditerStep.model.KmAuditerStep;

/**
 * <p>
 * Title:知识管理审核步骤
 * </p>
 * <p>
 * Description:知识管理审核步骤
 * </p>
 * <p>
 * Mon May 04 14:42:01 CST 2009
 * </p>
 *
 * @author 戴志刚
 * @version 1.0
 */
public interface KmAuditerStepMgr {

    /**
     * 取知识管理审核步骤 列表
     *
     * @return 返回知识管理审核步骤列表
     */
    public List getKmAuditerSteps();

    /**
     * 根据主键查询知识管理审核步骤
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public KmAuditerStep getKmAuditerStep(final String id);

    /**
     * 保存知识管理审核步骤
     *
     * @param kmAuditerStep 知识管理审核步骤
     */
    public void saveKmAuditerStep(KmAuditerStep kmAuditerStep);

    /**
     * 根据主键删除知识管理审核步骤
     *
     * @param id 主键
     */
    public void removeKmAuditerStep(final String id);

    /**
     * 根据条件分页查询知识管理审核步骤
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回知识管理审核步骤的分页列表
     */
    public Map getKmAuditerSteps(final Integer curPage, final Integer pageSize,
                                 final String whereStr);

    /**
     * 根据条件查询知识管理审核步骤
     */
    public List getKmAuditerSteps(final String whereStr);

    public KmAuditerStep saveKmAuditerStep(KmAuditer kmAuditer, Map kmAuditerStepMap);

    public Map getContentUnAuditList(final Integer curPage, final Integer pageSize,
                                     final String userId, final List subRoleList);

    public Map getFileUnAuditList(final Integer curPage, final Integer pageSize,
                                  final String userId, final List subRoleList);

    /**
     * 增加短信服务功能
     *
     * @param _cruser  String 发送人列表，以逗号分割,格式：1,admin#,1,sunshengtai#2,151
     * @param _content String 发送内容
     * @param _id      String 所属属性ID 如月计划Id或年计划ID
     */
    public void sendSMS(String _cruser, String type, String _id);
}