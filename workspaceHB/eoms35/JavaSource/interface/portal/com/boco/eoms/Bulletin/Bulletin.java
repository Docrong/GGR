package com.boco.eoms.Bulletin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.FileDownLoad;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.interfaceMonitoring.mgr.InterfaceMonitoringMgr;
import com.boco.eoms.commons.system.user.util.UserMgrLocator;
import com.boco.eoms.util.AttachRef;
import com.boco.eoms.util.InterfaceUtil;
import com.boco.eoms.util.InterfaceUtilVariable;
import com.boco.eoms.workbench.infopub.mgr.IThreadManager;
import com.boco.eoms.workbench.infopub.model.Thread;
import com.boco.eoms.workbench.infopub.model.ThreadHistory;
import com.boco.eoms.workbench.infopub.model.ThreadPermimissionOrg;
import com.boco.eoms.workbench.infopub.util.InfopubConstants;
import com.boco.eoms.base.util.FileDownLoad;
import com.boco.eoms.util.BulletinMgrLocator;

public class Bulletin {
    // 测试服务接口
    public String isAlive(String serSupplier, String callTime) {
        // BocoLog.trace(this, 35, "收到握手信号");
        System.out.println("收到握手信号");
        String isAliveResult = "0";

        return isAliveResult;
    }

    /*
     * 该接口适用于EOMS系统/CRM系统向对方系统发布公告信息 参数名称 参数类型（长度） 参数说明 sheetType Integer(10)
     * 工单类型（代码规则参见“附录A.2”） serviceType Integer(10) 产品类型（代码规则参见“附录A.4”） serialNo
     * String(40) CRM工单编号 serSupplier String(16) 服务提供方，格式为：省份编码_系统名称 serCaller
     * String(16) 服务调用方，格式为：省份编码_系统名称 callerPwd String(50) 服务调用方密码(可选) callTime
     * String(20) 服务调用时间 attachRef String(2000) 附件信息列表，参见“附件信息约定” opPerson
     * String(20) 操作人 opCorp String(20) 操作人单位 opDepart String(20)
     * 操作人所属部门（内容为操作人所属部门，例如客服中心、计费中心等） opContact String(80) 操作人联系方式（可以为空）
     * opTime String(20) 操作时间 opDetail String 详细信息，参见“详细信息约定”
     */

    public String newBulletin(Integer sheetType, Integer serviceType,
                              String serialNo, String serSupplier, String serCaller,
                              String callerPwd, String callTime, String attachRef,
                              String opPerson, String opCorp, String opDepart, String opContact,
                              String opTime, String opDetail) throws Exception {
        String errList = "0";

        if (serialNo != null && !"".equals(serialNo)) {
            try {
                InterfaceUtil doc = new InterfaceUtil();
                System.out.print("getLocalString:" + doc.getSerialNo());
                HashMap sheetMap = new HashMap();
                sheetMap = doc.xmlElements(opDetail, sheetMap, "FieldContent");
                Thread thread = new Thread();
                thread.setTitle(String.valueOf(sheetMap.get("title")));
                String threadTypeId = StaticMethod.nullObject2String(sheetMap
                        .get("urgentDegree"));
                if (threadTypeId
                        .equals(InterfaceUtilVariable.BULLETION_THREADTYPE_URGENT)) {
                    thread.setThreadTypeId("1");
                } else {
                    thread.setThreadTypeId("2");
                }
                thread.setValidity(String.valueOf(sheetMap.get("availTime")));
                thread.setContent(String.valueOf(sheetMap.get("noticeDesc")));
                thread.setCreateTime(new Date());
                thread.setIsDel("0");
                thread.setEditTime(new Date());
                thread.setForumsId(BulletinMgrLocator.getAttributes()
                        .getBulletinForumsId());
                thread.setThreadCount(new Integer(0));
                // thread.setThreadCount(java.lang.Integer.parseInt("0"));
                String reply = StaticMethod.nullObject2String(sheetMap
                        .get("isNeedReploy"));
                if (reply.equals(InterfaceUtilVariable.BULLETION_REPLY_NAME)) {
                    thread.setReply("1");
                } else {
                    thread.setReply("2");
                }

                thread.setSerialNo(serialNo);
                thread.setCreaterName(opPerson);
                thread.setCreaterId(InterfaceUtilVariable.BULLETION_CREATER_ID);
                InterfaceUtil interfaceUtil = new InterfaceUtil();
                List threadPermimissionOrgs = new ArrayList();
                threadPermimissionOrgs.add(new ThreadPermimissionOrg(
                        InterfaceUtilVariable.BULLETION_DEPT_ID,
                        StaticVariable.PRIV_ASSIGNTYPE_DEPT,
                        InterfaceUtilVariable.BULLETION_DEPT_NAME));
                IThreadManager msg = (IThreadManager) ApplicationContextHolder
                        .getInstance().getBean("IthreadManager");
                thread.setStatus(InfopubConstants.NO_AUDIT);
                // 附件下载
                if (attachRef != null && !"".equals(attachRef) && !"<attachRef></attachRef>".equals(attachRef)) {
                    String fileName = "";
                    List list = new ArrayList();
                    list = interfaceUtil.getAttachRefFromXml(attachRef);
                    if (list.size() > 0) {

                        for (int i = 0; i < list.size(); i++) {
                            AttachRef attach = new AttachRef();
                            attach = (AttachRef) list.get(i);
                            String url = attach.getAttachURL();
                            fileName += "," + attach.getAttachName();
                            interfaceUtil.fileDownLoad(url);
                        }
                    }
                    if (fileName.substring(0, 1).equals(",")) {
                        thread.setAccessories(fileName.substring(1, fileName
                                .length()));
                    } else {
                        thread.setAccessories(fileName);
                    }
                }
                // 附件下载
                msg.saveThread(thread, threadPermimissionOrgs);
            } catch (Exception e) {
                e.printStackTrace();
                errList = e.toString();
            }
        } else {
            errList = "工单编号不能为空";
        }
        return errList;
    }

    /*
     * 该接口适用于EOMS系统/CRM系统向对方发送对公告的回复意见。 参数名称 参数类型（长度） 参数说明 sheetType Integer(10)
     * 工单类型（代码规则参见“附录A.2”） serviceType Integer(10) 产品类型（代码规则参见“附录A.4”） serialNo
     * String(40) CRM工单编号 serSupplier String(16) 服务提供方，格式为：省份编码_系统名称 serCaller
     * String(16) 服务调用方，格式为：省份编码_系统名称 callerPwd String(50) 服务调用方密码(可选) callTime
     * String(20) 服务调用时间 attachRef String(2000) 附件信息列表，参见“附件信息约定” opPerson
     * String(20) 操作人 opCorp String(20) 操作人单位 opDepart String(20)
     * 操作人所属部门（内容为操作人所属部门，例如客服中心、计费中心等） opContact String(80) 操作人联系方式（可以为空）
     * opTime String(20) 操作时间 opDetail String 详细信息，参见“详细信息约定”
     */
    public String confirmBulletin(Integer sheetType, Integer serviceType,
                                  String serialNo, String serSupplier, String serCaller,
                                  String callerPwd, String callTime, String attachRef,
                                  String opPerson, String opCorp, String opDepart, String opContact,
                                  String opTime, String opDetail) throws Exception {
        String errList = "0";
        if (serialNo != null && !"".equals(serialNo)) {
            try {
                InterfaceUtil doc = new InterfaceUtil();
                HashMap sheetMap = new HashMap();
                sheetMap = doc.xmlElements(opDetail, sheetMap, "FieldContent");
                ThreadHistory threadHistory = new ThreadHistory();
                Thread thread = new Thread();
                IThreadManager msg = (IThreadManager) ApplicationContextHolder
                        .getInstance().getBean("IthreadManager");
                thread = msg.getSerialNoToThread(serialNo);
                String comments = StaticMethod.nullObject2String(sheetMap
                        .get("reployDesc"));
                threadHistory.setComments(comments);
                String replyresult = StaticMethod.nullObject2String(sheetMap
                        .get("reployResult"));
                if (replyresult
                        .equals(InterfaceUtilVariable.BULLETION_REPLYRESULT)) {
                    threadHistory.setReplyresult("1");
                } else {
                    threadHistory.setReplyresult("2");
                }
                threadHistory.setReadTime(new Date());
                threadHistory.setThreadId(thread.getId());
                threadHistory
                        .setUserId(InterfaceUtilVariable.BULLETION_CREATER_ID);
                threadHistory.setHistoryType("1");
                msg.saveThreadHistory(thread, threadHistory);
            } catch (Exception e) {
                e.printStackTrace();
                errList = e.toString();
            }
        } else {
            errList = "工单编号不能为空";
        }
        return errList;
    }
}
