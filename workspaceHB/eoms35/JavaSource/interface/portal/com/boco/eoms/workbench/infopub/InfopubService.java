package com.boco.eoms.workbench.infopub;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.util.AttachRef;
import com.boco.eoms.util.InterfaceUtil;
import com.boco.eoms.workbench.infopub.mgr.IForumsManager;
import com.boco.eoms.workbench.infopub.mgr.IThreadManager;
import com.boco.eoms.workbench.infopub.model.Thread;
import com.boco.eoms.workbench.infopub.model.ThreadAuditHistory;
import com.boco.eoms.workbench.infopub.model.ThreadPermimissionOrg;
import com.boco.eoms.workbench.infopub.model.Forums;
import com.boco.eoms.workbench.infopub.util.InfopubConstants;

public class InfopubService {
    // 测试服务接口
    public String isAlive(String serSupplier, String callTime) {
        // BocoLog.trace(this, 35, "收到握手信号");
        System.out.println("收到握手信号");
        String isAliveResult = "0";

        return isAliveResult;
    }

    /*
     * 该接口用于向外部系统提供信息发布的接口  参数名称 参数类型（长度） 参数说明 otherSystemType String(10)
     * 标识外系统类型 title String(255) 标题 forums String(100) 专题名称 threadTypeId
     * String(50) 紧急程度 reply String(10) 是否需要回复 validity
     * String(50) 有效期：单位天 canread String(500) 发布范围：如deptname#dept,liqiuye#user isIncludeSubDept
     * String(10) 是否包括子部门:1包括，0不包括 auditUser String(100) 审核人：如yuwenchao content
     * String(4096) 内容 attachRef String(2000) 附件信息列表，参见“附件信息约定”
     */
    public String newThread(String otherSystemType, String forumsTitle, String title,
                            String threadTypeId, String reply, String validity, String canread, String isIncludeSubDept, String auditUser, String content, String attachRef) {

        String result = "Success";
        IThreadManager mgr = (IThreadManager) ApplicationContextHolder.getInstance().getBean("IthreadManager");
        Thread thread = new Thread();
        thread.setCreateTime(new Date());
        thread.setEditTime(new Date());
        thread.setIsDel(Constants.NOT_DELETED_FLAG);
        thread.setThreadCount(new Integer(0));
        thread.setTitle(title);
        thread.setContent(content);
        thread.setReply(reply);
        thread.setValidity(validity);
        thread.setIsIncludeSubDept(isIncludeSubDept);
        thread.setThreadTypeId(threadTypeId);
        thread.setStatus(InfopubConstants.AUDIT_WITHOUT_SUBMIT);
        thread.setOtherSystemType(otherSystemType);//外系统
        thread.setInterFaceMethod("Web Service");

        //与专题关联
        if (forumsTitle != null && !forumsTitle.trim().equals("")) {//根据专题名得到专题对象，进而取到专题id
            IForumsManager mgr3 = (IForumsManager) ApplicationContextHolder.getInstance().getBean("IforumsManager");
            Forums forums = (Forums) mgr3.getForumsByTitle(forumsTitle);
            System.out.println("--------- forums = " + forums);
            thread.setForumsId(forums.getId());
        } else return "Failure";

        // 附件下载
        InterfaceUtil interfaceUtil = new InterfaceUtil();
        if (attachRef != null && !"".equals(attachRef)) {
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
                thread.setAccessories(fileName.substring(1, fileName.length()));
            } else {
                thread.setAccessories(fileName);
            }
        }

        //处理发布范围
        List orgList = new ArrayList();
        if (canread != null && !canread.trim().equals("")) {
            ITawSystemDeptManager mgr1 = (ITawSystemDeptManager) ApplicationContextHolder.getInstance().getBean("ItawSystemDeptManager");
            ITawSystemUserManager mgr2 = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
            String[] can = canread.split(",");
            TawSystemDept dept = null;
            TawSystemUser user = null;
            for (int i = 0; i < can.length; i++) {
                String[] cc = can[i].split("#");
                if (cc[1].equals("dept")) {
                    dept = mgr1.getDeptinfoBydeptname(cc[0], "0");
                    orgList.add(new ThreadPermimissionOrg(dept.getDeptId(), StaticVariable.PRIV_ASSIGNTYPE_DEPT, dept.getDeptName()));
                } else if (cc[1].equals("user")) {
                    user = mgr2.getUserByuserid(cc[0]);
                    orgList.add(new ThreadPermimissionOrg(user.getUserid(), StaticVariable.PRIV_ASSIGNTYPE_USER, user.getUsername()));
                }
            }
        }
        mgr.saveThread(thread, orgList);

        //处理审核人
        if (auditUser != null && !auditUser.trim().equals("")) {//信息发布表单选审核人是单选，因此这里只考虑一个审核人的情况，与ThreadAction里save方法不同
            ThreadAuditHistory threadAuditHistory = new ThreadAuditHistory();
            threadAuditHistory = threadAuditHistory.JSON2ThreadAuditHistory(auditUser.trim(),
                    StaticVariable.PRIV_ASSIGNTYPE_USER, thread.getId(), threadAuditHistory);
            mgr.saveThreadAuditHistoryDraft(threadAuditHistory);
        }

        return result;
    }

}
