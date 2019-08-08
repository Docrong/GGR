package com.boco.eoms.km.kmAuditerStep.mgr.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.km.interfaces.util.KmInterProps;
import com.boco.eoms.km.kmAuditer.model.KmAuditer;
import com.boco.eoms.km.kmAuditerStep.model.KmAuditerStep;
import com.boco.eoms.km.kmAuditerStep.mgr.KmAuditerStepMgr;
import com.boco.eoms.km.kmAuditerStep.dao.KmAuditerStepDao;
import com.boco.eoms.message.service.impl.MsgServiceImpl;
import com.boco.eoms.workplan.util.WorkplanMgrLocator;

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
public class KmAuditerStepMgrImpl implements KmAuditerStepMgr {

    private KmAuditerStepDao kmAuditerStepDao;

    public KmAuditerStepDao getKmAuditerStepDao() {
        return this.kmAuditerStepDao;
    }

    public void setKmAuditerStepDao(KmAuditerStepDao kmAuditerStepDao) {
        this.kmAuditerStepDao = kmAuditerStepDao;
    }

    public List getKmAuditerSteps() {
        return kmAuditerStepDao.getKmAuditerSteps();
    }

    public KmAuditerStep getKmAuditerStep(final String id) {
        return kmAuditerStepDao.getKmAuditerStep(id);
    }

    public KmAuditerStep saveKmAuditerStep(KmAuditer kmAuditer, Map kmAuditerStepMap) {
        KmAuditerStep kmAuditerStep = new KmAuditerStep();
        String date = StaticMethod.getLocalString();
        String kmId = StaticMethod.nullObject2String(kmAuditerStepMap.get("kmId"));
        String state = StaticMethod.nullObject2String(kmAuditerStepMap.get("state"));
        String type = StaticMethod.nullObject2String(kmAuditerStepMap.get("type"));
        String cruser = "";
        kmAuditerStep.setCreateTime(date);
        kmAuditerStep.setAuditResult(StaticMethod.nullObject2String(kmAuditerStepMap.get("auditResult")));
        kmAuditerStep.setRemark(StaticMethod.nullObject2String(kmAuditerStepMap.get("remark")));
        kmAuditerStep.setOperateType("user");//类型为个人
        kmAuditerStep.setOperateId(StaticMethod.nullObject2String(kmAuditerStepMap.get("userId")));
        kmAuditerStep.setKmId(kmId);
        kmAuditerStep.setState(state);
        if (kmAuditer.getMasterAudit().equals("1030101")) {
            kmAuditerStep.setToOrgType("user");//类型为个人
            kmAuditerStep.setToOrgId(kmAuditer.getMasterId());
            cruser = "1," + kmAuditer.getMasterId();
        } else {
            kmAuditerStep.setToOrgType("subrole");//类型为角色
            kmAuditerStep.setToOrgId(kmAuditer.getRoleId());
            cruser = "3," + kmAuditer.getRoleId();
        }
        kmAuditerStepDao.saveKmAuditerStep(kmAuditerStep);
        //增加提醒短信功能
        if (state.equals("1")) {
            sendSMS(cruser, type, kmId);

        }
        return kmAuditerStep;
    }

    public void saveKmAuditerStep(KmAuditerStep kmAuditerStep) {
        kmAuditerStepDao.saveKmAuditerStep(kmAuditerStep);
    }

    public void removeKmAuditerStep(final String id) {
        kmAuditerStepDao.removeKmAuditerStep(id);
    }

    public Map getKmAuditerSteps(final Integer curPage, final Integer pageSize,
                                 final String whereStr) {
        return kmAuditerStepDao.getKmAuditerSteps(curPage, pageSize, whereStr);
    }

    public List getKmAuditerSteps(final String whereStr) {
        return kmAuditerStepDao.getKmAuditerSteps(whereStr);
    }

    public Map getContentUnAuditList(final Integer curPage, final Integer pageSize,
                                     final String userId, final List subRoleList) {
        return kmAuditerStepDao.getContentUnAuditList(curPage, pageSize, userId, subRoleList);
    }

    public Map getFileUnAuditList(final Integer curPage, final Integer pageSize,
                                  final String userId, final List subRoleList) {
        return kmAuditerStepDao.getFileUnAuditList(curPage, pageSize, userId, subRoleList);
    }

    /**
     * 增加短信服务功能
     *
     * @param _cruser  String 发送人列表，以逗号分割,格式：1,admin#,1,sunshengtai#2,151
     * @param _content String 发送内容
     * @param _id      String 所属属性ID 如月计划Id或年计划ID
     */
    public void sendSMS(String _cruser, String type, String _id) {
        String content = "";
        Properties prop = KmInterProps.getConfigure("defaultconfig.properties");
        String serverId = prop.getProperty("serverId");
        if (type.equals("file")) {
            content = "您好，知识管理系统您有一个上传文件等待审核";
        } else if (type.equals("content")) {
            content = "您好，知识管理系统您有一条知识等待审核";
        }
        //MsgService msgService = MsgMgrLocator.getMsgMgr();
        MsgServiceImpl msgService = new MsgServiceImpl();
        if (msgService.hasService(serverId).equals("true")) {
            // 获得当前时间
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(currentTime);
            // 添加服务 8a44e43f1a998edb011a99b090d90004为服务模块ID
            msgService.closeMsg(serverId, _id);
            System.out.println("删除该知识未发送所有短信完成");
            System.out.println(_cruser + ":" + content);
            msgService.sendMsg(serverId, content,
                    _id, _cruser, dateString);
        }
    }

}