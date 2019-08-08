package com.boco.eoms.sheet.tool.complaintmsgconfig.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.sheet.tool.complaintmsgconfig.model.ComplaintSheetMsgConfig;

/**
 * <p>
 * Title:投诉工单短信配置类
 * </p>
 * <p>
 * Description:投诉工单短信配置类
 * </p>
 * <p>
 * Mon Sep 14 10:06:54 CST 2009
 * </p>
 *
 * @author qinmin
 * @version 1.0
 */
public interface IComplaintSheetMsgConfigMgr {

    /**
     * 取投诉工单短信配置类 列表
     *
     * @return 返回投诉工单短信配置类列表
     */
    public List getComplaintSheetMsgConfigs();

    /**
     * 根据主键查询投诉工单短信配置类
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public ComplaintSheetMsgConfig getComplaintSheetMsgConfig(final String id);

    /**
     * 保存投诉工单短信配置类
     *
     * @param complaintSheetMsgConfig 投诉工单短信配置类
     */
    public void saveComplaintSheetMsgConfig(ComplaintSheetMsgConfig complaintSheetMsgConfig);

    /**
     * 根据主键删除投诉工单短信配置类
     *
     * @param id 主键
     */
    public void removeComplaintSheetMsgConfig(final String id);

    /**
     * 根据条件分页查询投诉工单短信配置类
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回投诉工单短信配置类的分页列表
     */
    public Map getComplaintSheetMsgConfigs(final Integer curPage, final Integer pageSize,
                                           final String whereStr);

    /**
     * 根据地域ID跟投诉分类查询对应的短信通知对象
     *
     * @param areaId        地域ID
     * @param complaintType 投诉分类
     * @return 返回对应的短信通知对象
     */
    public String getNotifyUser(String areaId, String complaintType);

    /**
     * 根据条件查询投诉工单短信配置信息是否存在
     *
     * @param areaId        地域ID
     * @param complaintType 投诉分类
     * @return true or false
     */
    public Object getComplaintSheetMsgConfig(String areaId, String complaintType);

    /**
     * 更新短信通知对象
     *
     * @param userId 需要更新的用户ID
     */
    public void updateUserId(String userId);

}