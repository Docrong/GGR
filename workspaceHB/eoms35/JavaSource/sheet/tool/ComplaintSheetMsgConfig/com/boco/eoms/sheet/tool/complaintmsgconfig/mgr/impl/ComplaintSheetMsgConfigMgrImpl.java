package com.boco.eoms.sheet.tool.complaintmsgconfig.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.sheet.tool.complaintmsgconfig.model.ComplaintSheetMsgConfig;
import com.boco.eoms.sheet.tool.complaintmsgconfig.mgr.IComplaintSheetMsgConfigMgr;
import com.boco.eoms.sheet.tool.complaintmsgconfig.dao.IComplaintSheetMsgConfigDao;

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
public class ComplaintSheetMsgConfigMgrImpl implements IComplaintSheetMsgConfigMgr {

    private IComplaintSheetMsgConfigDao complaintSheetMsgConfigDao;

    public IComplaintSheetMsgConfigDao getComplaintSheetMsgConfigDao() {
        return this.complaintSheetMsgConfigDao;
    }

    public void setComplaintSheetMsgConfigDao(IComplaintSheetMsgConfigDao complaintSheetMsgConfigDao) {
        this.complaintSheetMsgConfigDao = complaintSheetMsgConfigDao;
    }

    public List getComplaintSheetMsgConfigs() {
        return complaintSheetMsgConfigDao.getComplaintSheetMsgConfigs();
    }

    public ComplaintSheetMsgConfig getComplaintSheetMsgConfig(final String id) {
        return complaintSheetMsgConfigDao.getComplaintSheetMsgConfig(id);
    }

    public void saveComplaintSheetMsgConfig(ComplaintSheetMsgConfig complaintSheetMsgConfig) {
        complaintSheetMsgConfigDao.saveComplaintSheetMsgConfig(complaintSheetMsgConfig);
    }

    public void removeComplaintSheetMsgConfig(final String id) {
        complaintSheetMsgConfigDao.removeComplaintSheetMsgConfig(id);
    }

    public Map getComplaintSheetMsgConfigs(final Integer curPage, final Integer pageSize,
                                           final String whereStr) {
        return complaintSheetMsgConfigDao.getComplaintSheetMsgConfigs(curPage, pageSize, whereStr);
    }

    /**
     * 根据地域ID跟投诉分类查询对应的短信通知对象
     *
     * @param areaId        地域ID
     * @param complaintType 投诉分类
     * @return 返回对应的短信通知对象
     */
    public String getNotifyUser(String areaId, String complaintType) {
        return complaintSheetMsgConfigDao.getNotifyUser(areaId, complaintType);
    }

    /**
     * 根据条件查询投诉工单短信配置信息是否存在
     *
     * @param areaId        地域ID
     * @param complaintType 投诉分类
     * @return true or false
     */
    public Object getComplaintSheetMsgConfig(String areaId, String complaintType) {
        return complaintSheetMsgConfigDao.getComplaintSheetMsgConfig(areaId, complaintType);
    }

    /**
     * 更新短信通知对象
     *
     * @param userId 需要更新的用户ID
     */
    public void updateUserId(String userId) {
        complaintSheetMsgConfigDao.updateUserId(userId);
    }
}