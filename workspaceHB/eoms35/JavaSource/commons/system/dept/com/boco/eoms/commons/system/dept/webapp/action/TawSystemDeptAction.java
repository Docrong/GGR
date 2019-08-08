package com.boco.eoms.commons.system.dept.webapp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;

import com.boco.eoms.common.util.UUIDHexGenerator;
import com.boco.eoms.commons.cache.application.ApplicationCacheMgr;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.service.ITawSystemAreaManager;
import com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.dept.model.TawPartnersDept;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawPartnersDeptManager;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dept.webapp.form.TawSystemDeptForm;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.role.service.bo.TawSystemSubRoleManagerBO;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawPartnersUser;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawPartnersUserManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;
import com.boco.eoms.commons.system.user.webapp.form.TawSystemUserForm;
import com.boco.eoms.commons.ui.util.JSONUtil;

/**
 * Action class to handle CRUD on a TawSystemDept object
 *
 * @struts.action name="tawSystemDeptForm" path="/tawSystemDepts"
 * scope="request" validate="false" parameter="method"
 * input="mainMenu"
 * @struts.action name="tawSystemDeptForm" path="/editTawSystemDept"
 * scope="request" validate="false" parameter="method"
 * input="list"
 * @struts.action name="tawSystemDeptForm" path="/saveTawSystemDept"
 * scope="request" validate="true" parameter="method"
 * input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 * path="/WEB-INF/pages/tawSystemDept/tawSystemDeptForm.jsp"
 * @struts.action-forward name="list"
 * path="/WEB-INF/pages/tawSystemDept/tawSystemDeptList.jsp"
 * @struts.action-forward name="search" path="/tawSystemDepts.html"
 * redirect="true"
 */
public final class TawSystemDeptAction extends BaseAction {

    TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();

    // 树图页面方案所用到的保存
    public ActionForward xsave(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // Extract attributes and parameters we will need
        ActionMessages messages = new ActionMessages();
        TawSystemDeptForm tawSystemDeptForm = (TawSystemDeptForm) form;
        boolean isNew = ("".equals(tawSystemDeptForm.getId()) || tawSystemDeptForm
                .getId() == null);

        ITawSystemDeptManager mgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManager");
        ITawSystemDeptManager flushmgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManagerFlush");

        ITawPartnersDeptManager pmgr = (ITawPartnersDeptManager) getBean("ItawPartnersDeptManager");

        TawSystemDept tawSystemDept = (TawSystemDept) convert(tawSystemDeptForm);

        String strs = StaticMethod.getOperDay();
        String newlinkId = "";

        String paredeptid = tawSystemDept.getParentDeptid();

        TawSystemDept parentDept = mgr.getDeptinfobydeptid(paredeptid, "0");

        if (paredeptid == null || paredeptid.equals("")
                || paredeptid.equals("-1")) {

            tawSystemDept.setParentDeptid("-1");
            tawSystemDept.setDeleted("0");
            newlinkId = mgr.getNewLinkid("1", "0");
            // deptId生成32位字符串
            tawSystemDept.setDeptId(newlinkId);
            // 保存linkId和parentLinkId
            tawSystemDept.setLinkid(newlinkId);
            tawSystemDept.setParentLinkid("-1");
            tawSystemDept.setOrdercode(new Integer(0));
            tawSystemDept.setOperremoteip(request.getRemoteAddr());
            tawSystemDept.setOpertime(strs);
            tawSystemDept.setLeaf("1");

        } else {
            // newdeptid = mgr.getNewDeptid(paredeptid, "0");
            if (paredeptid.equals("-1")) {
                tawSystemDept.setLeaf("0");
                tawSystemDept.setOrdercode(new Integer(0));
                if (tawSystemDept.getOpertime().equals("")) {
                    tawSystemDept.setOpertime(strs);
                }
            } else {
                // 父部门
                TawSystemDept sysdept = new TawSystemDept();
                sysdept = deptbo.getDeptinfobydeptid(paredeptid, "0");
                if (tawSystemDept.getDeptId() == null
                        || tawSystemDept.getDeptId().equals("")) {
                    // 根据父部门linkId生成新的linkId
                    newlinkId = mgr.getNewLinkid(sysdept.getLinkid(), "0");
                    tawSystemDept.setDeptId(newlinkId);
                    // 该linkid作为nodeId维持树形结构
                    tawSystemDept.setLinkid(newlinkId);
                    tawSystemDept.setParentLinkid(sysdept.getLinkid());
                }

                tawSystemDept.setLeaf("1");
                int oerdercode = sysdept.getOrdercode().intValue();
                sysdept.setLeaf("0");
                flushmgr.saveTawSystemDept(sysdept);
                tawSystemDept.setOrdercode(new Integer(oerdercode + 1));
                if (tawSystemDept.getOpertime().equals("")) {
                    tawSystemDept.setOpertime(strs);
                }
            }
            tawSystemDept.setDeleted("0");
            tawSystemDept.setOperremoteip(request.getRemoteAddr());
            tawSystemDept.setUpdatetime(strs);

        }
        // 给字段isDaiweiRoot赋值，isDaiweiRoot='1'表示该节点是代维部门根节点，isDaiweiRoot='0'表示该部门不是根节点。
        // isDaiweiRoot字段的作用在于为构造代维人员选择树 2008-11-17 liujinlong
        if (parentDept != null) {
            if (parentDept.getIsDaiweiRoot() != null
                    && parentDept.getIsDaiweiRoot().equals("1"))
                tawSystemDept.setIsDaiweiRoot("0");// 表示该部门不是代维根节点
            else
                tawSystemDept.setIsDaiweiRoot("1");// isDaiweiRoot='1'表示该部门是代维根节点
        }
        // ---------------------------

        tawSystemDept.setAreaid(tawSystemDeptForm.getNewAreaId());
        tawSystemDept.setRegionflag(new Integer(0));
        tawSystemDept.setTmpdeptid(tawSystemDept.getDeptId());
        if (tawSystemDept.getIsPartners() == null || "".equals(tawSystemDept.getIsPartners()))
            tawSystemDept.setIsPartners("0");
        flushmgr.saveTawSystemDept(tawSystemDept);

        // 若为代维公司，还得保存表TawPartnersDept 2008-11-10 liujinlong
        String isPartners = tawSystemDept.getIsPartners();
        if (isPartners != null && isPartners.equals("1")) {// isPartners=1表示代维公司
            TawPartnersDept tawPartnersDept = new TawPartnersDept();

            tawPartnersDept.setBankAccount(tawSystemDeptForm.getBankAccount());
            tawPartnersDept
                    .setBaseStations(tawSystemDeptForm.getBaseStations());
            tawPartnersDept.setBusinessLicense(tawSystemDeptForm
                    .getBusinessLicense());
            tawPartnersDept.setCertificationDept(tawSystemDeptForm
                    .getCertificationDept());
            tawPartnersDept.setCompanyType(tawSystemDeptForm.getCompanyType());
            tawPartnersDept.setCompanyWeb(tawSystemDeptForm.getCompanyWeb());
            tawPartnersDept.setContacter(tawSystemDeptForm.getContacter());
            tawPartnersDept.setFixAsset(tawSystemDeptForm.getFixAsset());
            tawPartnersDept.setIntermediateTitle(tawSystemDeptForm
                    .getIntermediateTitle());
            tawPartnersDept.setIsUnicomAssociation(tawSystemDeptForm
                    .getIsUnicomAssociation());
            tawPartnersDept.setJuniorTitle(tawSystemDeptForm.getJuniorTitle());
            tawPartnersDept.setLines(tawSystemDeptForm.getLines());
            tawPartnersDept
                    .setManagePeople(tawSystemDeptForm.getManagePeople());
            tawPartnersDept.setMicrowaves(tawSystemDeptForm.getMicrowaves());
            tawPartnersDept.setOwnCars(tawSystemDeptForm.getOwnCars());
            tawPartnersDept.setOwnInstrument(tawSystemDeptForm
                    .getOwnInstrument());
            tawPartnersDept
                    .setPeopleNumber(tawSystemDeptForm.getPeopleNumber());
            tawPartnersDept.setPowerAndSet(tawSystemDeptForm.getPowerAndSet());
            tawPartnersDept.setProfessionalLevel(tawSystemDeptForm
                    .getProfessionalLevel());
            tawPartnersDept.setQualification(tawSystemDeptForm
                    .getQualification());
            tawPartnersDept.setQualificationValidity(tawSystemDeptForm
                    .getQualificationValidity());
            tawPartnersDept
                    .setRegisterFund(tawSystemDeptForm.getRegisterFund());
            tawPartnersDept.setSeniorTitle(tawSystemDeptForm.getSeniorTitle());
            tawPartnersDept.setSetupTime(tawSystemDeptForm.getSetupTime());
            tawPartnersDept.setThirdServiceContract(tawSystemDeptForm
                    .getThirdServiceContract());
            tawPartnersDept.setAttachName(tawSystemDeptForm.getAttachName());
            tawPartnersDept.setTowers(tawSystemDeptForm.getTowers());
            tawPartnersDept.setWorkers(tawSystemDeptForm.getWorkers());

            tawPartnersDept.setDeptId(tawSystemDept.getDeptId());// 用这个字段与TawSystemDept表关联
            pmgr.saveTawPartnersDept(tawPartnersDept);
        }
        // ---------------------
        // 保存部门与岗位的关联关系
        /*
         * String postID = tawSystemDeptForm.getNewPostId(); String postIds[] =
         * postID.split(","); ITawSystemDeptRefPostManager deptrefpostmgr =
         * (ITawSystemDeptRefPostManager)
         * getBean("ItawSystemDeptRefPostManager"); for (int i = 0; i <
         * postIds.length; i++) { TawSystemDeptRefPost deptrefpost = new
         * TawSystemDeptRefPost();
         * deptrefpost.setDeptId(tawSystemDept.getDeptId());
         * deptrefpost.setPostId(new Long(postIds[i]).longValue());
         * deptrefpostmgr.saveTawSystemDeptRefPost(deptrefpost); }
         */
        // add success messages
        if (isNew) {
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "tawSystemDept.added"));

            // save messages in session to survive a redirect
            saveMessages(request.getSession(), messages);

            // 保存新建的部门id，使转向后可刷新树图上的相应节点
            request.setAttribute("lastNewId", paredeptid);

            return null;
        } else {
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "tawSystemDept.updated"));
            saveMessages(request, messages);

            // 保存编辑的部门id，使转向后可刷新树图上的相应节点
            request.setAttribute("lastEditId", tawSystemDept.getDeptId());

            return null;

        }
    }

    /**
     * 根据父节点的id得到所有子节点的JSON数据
     */
    public ActionForward getNodesPartner(ActionMapping mapping,
                                         ActionForm form, HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {

        TawSystemDeptForm tawSystemDeptForm = (TawSystemDeptForm) form;
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String operuserid = sessionform.getUserid();
        tawSystemDeptForm.setOperuserid(operuserid);

        String nodeId = request.getParameter("node");

        ArrayList list = new ArrayList();
        try {
            TawSystemDept sept = deptbo.getDeptinfobydeptid(nodeId, "0");
            if (sept.getTmpdeptid() != null && !"".equals(sept.getTmpdeptid())) {
                list = (ArrayList) deptbo.getNextLevecDepts(
                        sept.getTmpdeptid(), "0");
            } else {
                list = (ArrayList) deptbo.getNextLevecDepts(nodeId, "0");
            }
            ITawSystemUserManager user = (ITawSystemUserManager) ApplicationContextHolder
                    .getInstance().getBean("itawSystemUserManager");

            ITawSystemDeptManager mgr = (ITawSystemDeptManager) ApplicationContextHolder
                    .getInstance().getBean("ItawSystemDeptManager");
            // deptlist = (ArrayList) deptbo.getNextLevecDepts(node, "0");
            if (list.size() > 0) {
                List deptlist = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    TawSystemDept tawSystemDept = new TawSystemDept();
                    tawSystemDept = (TawSystemDept) list.get(i);
                    String deptName = tawSystemDept.getDeptName();

                    if (deptName != null && !"".equals(deptName)) {
                        int count = 0;
                        if (tawSystemDept.getDeptId().equals("1")) {
                            int allCount = user.getAllMobileBydeptid("1")
                                    .size();
                            List lists = new ArrayList();
                            lists = mgr.getNextLevecDepts(tawSystemDept
                                    .getDeptId(), "0");
                            if (lists.size() > 0) {
                                for (int j = 0; j < lists.size(); j++) {
                                    TawSystemDept tawSystemDepttmp = new TawSystemDept();
                                    tawSystemDepttmp = (TawSystemDept) lists
                                            .get(j);
                                    count += user.getUserLikePartner(
                                            tawSystemDepttmp.getDeptId())
                                            .size();
                                }

                            }
                            count += allCount - 1;
                            System.out.println("count:" + count);
                        } else {
                            count = user.getUserLikePartner(tawSystemDept.getDeptId())
                                    .size();
                        }
                        if (tawSystemDept.getIsPartners() != null && !"".equals(tawSystemDept.getIsPartners()) && tawSystemDept.getIsPartners().equals("1")) {
                            tawSystemDept.setDeptName(deptName + "("
                                    + String.valueOf(count) + ")");

                        } else
                            tawSystemDept.setDeptName(deptName);

                        deptlist.add(tawSystemDept);
                    }
                }

                request.setAttribute("list", list);
                ApplicationCacheMgr cacheMgr = (ApplicationCacheMgr) this
                        .getBean("ApplicationCacheMgr");
                cacheMgr.flush("com.boco.eoms.commons.system.dept.DEPT_CATCH");
            }

        } catch (Exception ex) {
            BocoLog.error(this, "生成部门树图时报错：" + ex);
        }

        // request.setAttribute("list", list);
        return mapping.findForward("tpl-dept-layoutTree");
    }

    /**
     * wangsixuan add: 根据父节点的id得到所有子节点的JSON数据,不包括代维
     */
    public ActionForward getNodes(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        TawSystemDeptForm tawSystemDeptForm = (TawSystemDeptForm) form;
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String operuserid = sessionform.getUserid();
        tawSystemDeptForm.setOperuserid(operuserid);

        String nodeId = request.getParameter("node");

        ArrayList list = new ArrayList();
        try {
            TawSystemDept sept = deptbo.getDeptinfobydeptid(nodeId, "0");
            if (sept.getTmpdeptid() != null && !"".equals(sept.getTmpdeptid())) {
                list = (ArrayList) deptbo.getNextLevecDeptsNoPartner(
                        sept.getTmpdeptid(), "0");
            } else {
                list = (ArrayList) deptbo.getNextLevecDeptsNoPartner(nodeId, "0");
            }
            ITawSystemUserManager user = (ITawSystemUserManager) ApplicationContextHolder
                    .getInstance().getBean("itawSystemUserManager");

            ITawSystemDeptManager mgr = (ITawSystemDeptManager) ApplicationContextHolder
                    .getInstance().getBean("ItawSystemDeptManager");
            // deptlist = (ArrayList) deptbo.getNextLevecDepts(node, "0");
            if (list.size() > 0) {
                List deptlist = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    TawSystemDept tawSystemDept = new TawSystemDept();
                    tawSystemDept = (TawSystemDept) list.get(i);
                    String deptName = tawSystemDept.getDeptName();

                    if (deptName != null && !"".equals(deptName)) {
                        int count = 0;
                        if (tawSystemDept.getDeptId().equals("1")) {
                            int allCount = user.getAllMobileBydeptid("1")
                                    .size();
                            List lists = new ArrayList();
                            lists = mgr.getNextLevecDepts(tawSystemDept
                                    .getDeptId(), "0");
                            if (lists.size() > 0) {
                                for (int j = 0; j < lists.size(); j++) {
                                    TawSystemDept tawSystemDepttmp = new TawSystemDept();
                                    tawSystemDepttmp = (TawSystemDept) lists
                                            .get(j);
                                    count += user.getUserLike(
                                            tawSystemDepttmp.getDeptId())
                                            .size();
                                }

                            }
                            count += allCount - 1;
                            System.out.println("count:" + count);
                        } else {
                            count = user.getUserLike(tawSystemDept.getDeptId())
                                    .size();
                        }
                        tawSystemDept.setDeptName(deptName + "("
                                + String.valueOf(count) + ")");

                        deptlist.add(tawSystemDept);
                    }
                }

                request.setAttribute("list", list);
                ApplicationCacheMgr cacheMgr = (ApplicationCacheMgr) this
                        .getBean("ApplicationCacheMgr");
                cacheMgr.flush("com.boco.eoms.commons.system.dept.DEPT_CATCH");
            }

        } catch (Exception ex) {
            BocoLog.error(this, "生成部门树图时报错：" + ex);
        }
        /*
         * for(int i = 0;i < list.size(); i++) { TawSystemDept septTemp =
         * (TawSystemDept)list.get(i);
         * if(("1").equals(septTemp.getIsPartners())) list.remove(i); }
         */
        request.setAttribute("list", list);
        return mapping.findForward("tpl-dept-layoutTree");
    }

    /**
     * 根据模块或功能的编码，删除该对象。
     * 页面访问：http://hostname:port/webappname/tawSystemDept.html?method=xdelete&id=inputvalue
     */
    public String xdelete(ActionMapping mapping, ActionForm form,
                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String _strId = request.getParameter("id");
        String paretndeptid = request.getParameter("parentDeptid");
        ITawSystemDeptManager mgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManager");

        ITawPartnersDeptManager pmgr = (ITawPartnersDeptManager) getBean("ItawPartnersDeptManager");

        ITawSystemDeptManager flushmgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManagerFlush");
        TawSystemDept deptinfo = new TawSystemDept();
        deptinfo = mgr.getDeptinfobydeptid(_strId, "0");
        // Exceptions are caught by ActionExceptionHandler

        List list = new ArrayList();
        list = deptbo.getSamelevelDeptbyDeptids(deptinfo.getParentDeptid(),
                StaticVariable.UNSTRSELETED, String.valueOf(deptinfo
                        .getOrdercode()));
        if (list != null) {
            if (list.size() == 1
                    && !(deptinfo.getParentDeptid().equalsIgnoreCase("-1"))) {
                TawSystemDept dept = new TawSystemDept();
                dept = deptbo.getFdept(_strId, StaticVariable.UNSTRSELETED);
                dept.setLeaf(StaticVariable.UNSTRLEAF);
                flushmgr.saveTawSystemDept(dept);
            }
        }
        List sonlist = new ArrayList();
        sonlist = deptbo.getChilddepts(_strId, StaticVariable.UNSTRSELETED);
        flushmgr.removeTawSystemDeptforCatch(deptinfo.getId(), paretndeptid,
                deptinfo);
        // mgr.removeTawSystemDept(deptinfo.getId());
        if (sonlist != null && sonlist.size() > 0) {
            TawSystemDept sysdept = new TawSystemDept();
            for (int i = 0; i < sonlist.size(); i++) {
                sysdept = null;
                sysdept = (TawSystemDept) sonlist.get(i);

                // -----若该系统部门还关联代维公司，则一并删除代维公司（附加表）2008-11-11 liujinlong
                if (sysdept.getIsPartners().equals("1")) {
                    List partnersDeptList = pmgr
                            .getPartnersDeptByDeptId(sysdept.getDeptId());
                    if (partnersDeptList != null && partnersDeptList.size() > 0) {
                        TawPartnersDept tawPartnersDept = (TawPartnersDept) partnersDeptList
                                .get(0);
                        pmgr.removeTawPartnersDept(tawPartnersDept);
                    }
                }
                // -----------------------------------

                flushmgr.removeTawSystemDeptforCatch(sysdept.getId(),
                        paretndeptid, sysdept);
                // mgr.removeTawSystemDept(sysdept.getId());
            }
        }

        // 删除部门时需要将部门下的人员进行删除标识
        TawSystemUserRoleBo userbo = TawSystemUserRoleBo.getInstance();
        userbo.removeUserbydeptid(_strId);
        // 删除部门时删除相应的角色
        TawSystemSubRoleManagerBO.deleteSubRoleByDeptId(_strId);
        return null;
    }

    /**
     * ajax请求获取某节点的详细信息。 mios 070724
     */
    public void xget(ActionMapping mapping, ActionForm form,
                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String _strId = request.getParameter("id");

        ITawSystemDeptManager mgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManager");
        ITawSystemAreaManager areamgr = (ITawSystemAreaManager) getBean("ItawSystemAreaManager");

        ITawPartnersDeptManager pmgr = (ITawPartnersDeptManager) getBean("ItawPartnersDeptManager");

        TawSystemDept _objOneOpt = new TawSystemDept();
        _objOneOpt = mgr.getDeptinfobydeptid(_strId,
                StaticVariable.UNSTRSELETED);
        TawSystemDeptForm tawSystemdeptForm = (TawSystemDeptForm) convert(_objOneOpt);

        // -----若该系统部门还关联代维公司，则读出代维公司的信息（附加表）2008-11-12 liujinlong
        if (_objOneOpt.getIsPartners() != null
                && _objOneOpt.getIsPartners().equals("1")) {
            List partnersDeptList = pmgr.getPartnersDeptByDeptId(_objOneOpt
                    .getDeptId());
            if (partnersDeptList != null && partnersDeptList.size() > 0) {
                TawPartnersDept tawPartnersDept = (TawPartnersDept) partnersDeptList
                        .get(0);
                tawSystemdeptForm.setFixAsset(tawPartnersDept.getFixAsset());
                tawSystemdeptForm.setQualification(tawPartnersDept
                        .getQualification());
                tawSystemdeptForm.setRegisterFund(tawPartnersDept
                        .getRegisterFund());
                tawSystemdeptForm.setCompanyType(tawPartnersDept
                        .getCompanyType());
                tawSystemdeptForm.setSetupTime(tawPartnersDept.getSetupTime());
                tawSystemdeptForm.setPeopleNumber(tawPartnersDept
                        .getPeopleNumber());
                tawSystemdeptForm
                        .setCompanyWeb(tawPartnersDept.getCompanyWeb());
                tawSystemdeptForm.setContacter(tawPartnersDept.getContacter());
                tawSystemdeptForm.setBusinessLicense(tawPartnersDept
                        .getBusinessLicense());
                tawSystemdeptForm.setCertificationDept(tawPartnersDept
                        .getCertificationDept());
                tawSystemdeptForm.setProfessionalLevel(tawPartnersDept
                        .getProfessionalLevel());
                tawSystemdeptForm.setIsUnicomAssociation(tawPartnersDept
                        .getIsUnicomAssociation());
                tawSystemdeptForm.setManagePeople(tawPartnersDept
                        .getManagePeople());
                tawSystemdeptForm.setSeniorTitle(tawPartnersDept
                        .getSeniorTitle());
                tawSystemdeptForm.setIntermediateTitle(tawPartnersDept
                        .getIntermediateTitle());
                tawSystemdeptForm.setJuniorTitle(tawPartnersDept
                        .getJuniorTitle());
                tawSystemdeptForm.setWorkers(tawPartnersDept.getWorkers());
                tawSystemdeptForm.setOwnCars(tawPartnersDept.getOwnCars());
                tawSystemdeptForm.setOwnInstrument(tawPartnersDept
                        .getOwnInstrument());
                tawSystemdeptForm.setLines(tawPartnersDept.getLines());
                tawSystemdeptForm.setBaseStations(tawPartnersDept
                        .getBaseStations());
                tawSystemdeptForm.setTowers(tawPartnersDept.getTowers());
                tawSystemdeptForm
                        .setMicrowaves(tawPartnersDept.getMicrowaves());
                tawSystemdeptForm.setPowerAndSet(tawPartnersDept
                        .getPowerAndSet());
                tawSystemdeptForm.setQualificationValidity(tawPartnersDept
                        .getQualificationValidity());
                tawSystemdeptForm.setBankAccount(tawPartnersDept
                        .getBankAccount());
                tawSystemdeptForm.setThirdServiceContract(tawPartnersDept
                        .getThirdServiceContract());
                tawSystemdeptForm
                        .setAttachName(tawPartnersDept.getAttachName());
            }
        }
        // -----------------------------------

        if (!(StaticMethod.null2String(_objOneOpt.getAreaid()).equals("null") || ""
                .equals(_objOneOpt.getAreaid()))) {
            String areaname = null;
            try {
                areaname = areamgr.getAreaByAreaId(_objOneOpt.getAreaid())
                        .getAreaname();
            } catch (RuntimeException re) {
                areaname = "";
            }
            tawSystemdeptForm.setAreaid(areaname);

            tawSystemdeptForm.setNewAreaId(_objOneOpt.getAreaid());
        }
        JSONObject jsonRoot = JSONObject.fromObject(tawSystemdeptForm);

        // 添加父部门名称
        jsonRoot.remove("parentDeptName");
        jsonRoot.put("parentDeptName", mgr
                .id2Name(_objOneOpt.getParentDeptid()));

        JSONUtil.print(response, jsonRoot.toString());
    }

    /**
     * ajax请求修改某节点的详细信息。 mios 070724
     */
    public ActionForward xedit(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemDeptForm tawSystemdeptForm = (TawSystemDeptForm) form;
        ITawSystemDeptManager mgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManagerFlush");
        ITawPartnersDeptManager pmgr = (ITawPartnersDeptManager) getBean("ItawPartnersDeptManager");

        TawSystemDept dept = new TawSystemDept();
        if (tawSystemdeptForm.getId() != null) {
            if (tawSystemdeptForm.getParentDeptid() != null
                    && tawSystemdeptForm.getDeptId() != null
                    && !"".equals(tawSystemdeptForm.getDeptId())
                    && !"".equals(tawSystemdeptForm.getParentDeptid())) {
                // 获得要修改的部门
                dept = mgr.getDeptinfobydeptid(tawSystemdeptForm.getDeptId(),
                        "0");
                // 若父部门Id有变化
                if (!dept.getParentDeptid().equals(
                        tawSystemdeptForm.getParentDeptid())) {
                    // 获得原来的父部门
                    TawSystemDept oldParentDept = mgr.getDeptinfobydeptid(dept
                            .getParentDeptid(), "0");
                    // 获得新的父部门
                    TawSystemDept newParentDept = mgr.getDeptinfobydeptid(
                            tawSystemdeptForm.getParentDeptid(), "0");
                    // 修改要修改部门的linkId、parentLinkId、parentDeptId
                    String newParentLinkId = newParentDept.getLinkid();
                    String newLinkId = mgr.getNewLinkid(newParentLinkId, "0");
                    String newParentDeptId = newParentDept.getDeptId();
                    tawSystemdeptForm.setLinkid(newLinkId);
                    tawSystemdeptForm.setParentLinkid(newParentLinkId);
                    tawSystemdeptForm.setParentDeptid(newParentDeptId);
                    // 新父部门设置为非叶子节点
                    newParentDept.setLeaf("0");
                    mgr.saveTawSystemDept(newParentDept);

                    // 移动要修改部门的所有子部门
                    List subDeptList = mgr.getNextLikeDepts(dept.getLinkid(),
                            "0");
                    for (Iterator it = subDeptList.iterator(); it.hasNext(); ) {
                        TawSystemDept subDept = (TawSystemDept) it.next();
                        // parentDeptId不变，linkId和parentLinkId前面几位替换为新的linkId
                        if (subDept.getLinkid().startsWith(dept.getLinkid())
                                && subDept.getParentLinkid().startsWith(
                                dept.getLinkid())) {
                            subDept.setLinkid(subDept.getLinkid().replaceFirst(
                                    dept.getLinkid(), newLinkId));
                            subDept.setParentLinkid(subDept.getParentLinkid()
                                    .replaceFirst(dept.getLinkid(), newLinkId));
                            mgr.saveTawSystemDept(subDept);
                        }
                    }

                    // 保存修改后的部门
                    tawSystemdeptForm.setDeleted(StaticVariable.UNSTRSELETED);
                    String strs = StaticMethod.getOperDay();
                    tawSystemdeptForm.setUpdatetime(strs);
                    dept = (TawSystemDept) convert(tawSystemdeptForm);
                    dept.setAreaid(tawSystemdeptForm.getNewAreaId());
                    mgr.saveTawSystemDept(dept);

                    // 设置原来父部门的叶子节点标志
                    List list = mgr.getNextLikeDepts(oldParentDept.getLinkid(),
                            "0");
                    if (!list.iterator().hasNext()) {
                        oldParentDept.setLeaf("1");
                        mgr.saveTawSystemDept(oldParentDept);
                    }
                } else {
                    // 保存修改后的部门
                    tawSystemdeptForm.setDeleted(StaticVariable.UNSTRSELETED);
                    String strs = StaticMethod.getOperDay();
                    tawSystemdeptForm.setUpdatetime(strs);
                    dept = (TawSystemDept) convert(tawSystemdeptForm);
                    dept.setAreaid(tawSystemdeptForm.getNewAreaId());
                    mgr.saveTawSystemDept(dept);
                }

            } else {
                // 保存修改后的部门
                tawSystemdeptForm.setDeleted(StaticVariable.UNSTRSELETED);
                String strs = StaticMethod.getOperDay();
                tawSystemdeptForm.setUpdatetime(strs);
                dept = (TawSystemDept) convert(tawSystemdeptForm);
                dept.setAreaid(tawSystemdeptForm.getNewAreaId());
                mgr.saveTawSystemDept(dept);
            }

            // 若为代维公司，还得保存表TawPartnersDept 2008-11-10 liujinlong
            String isPartners = dept.getIsPartners();
            if (isPartners != null && isPartners.equals("1")) {// isPartners=1表示代维公司
                List partnersDeptList = pmgr.getPartnersDeptByDeptId(dept
                        .getDeptId());
                if (partnersDeptList != null && partnersDeptList.size() > 0) {
                    TawPartnersDept tawPartnersDept = (TawPartnersDept) partnersDeptList
                            .get(0);
                    tawPartnersDept.setBankAccount(tawSystemdeptForm
                            .getBankAccount());
                    tawPartnersDept.setBaseStations(tawSystemdeptForm
                            .getBaseStations());
                    tawPartnersDept.setBusinessLicense(tawSystemdeptForm
                            .getBusinessLicense());
                    tawPartnersDept.setCertificationDept(tawSystemdeptForm
                            .getCertificationDept());
                    tawPartnersDept.setCompanyType(tawSystemdeptForm
                            .getCompanyType());
                    tawPartnersDept.setCompanyWeb(tawSystemdeptForm
                            .getCompanyWeb());
                    tawPartnersDept.setContacter(tawSystemdeptForm
                            .getContacter());
                    tawPartnersDept
                            .setFixAsset(tawSystemdeptForm.getFixAsset());
                    tawPartnersDept.setIntermediateTitle(tawSystemdeptForm
                            .getIntermediateTitle());
                    tawPartnersDept.setIsUnicomAssociation(tawSystemdeptForm
                            .getIsUnicomAssociation());
                    tawPartnersDept.setJuniorTitle(tawSystemdeptForm
                            .getJuniorTitle());
                    tawPartnersDept.setLines(tawSystemdeptForm.getLines());
                    tawPartnersDept.setManagePeople(tawSystemdeptForm
                            .getManagePeople());
                    tawPartnersDept.setMicrowaves(tawSystemdeptForm
                            .getMicrowaves());
                    tawPartnersDept.setOwnCars(tawSystemdeptForm.getOwnCars());
                    tawPartnersDept.setOwnInstrument(tawSystemdeptForm
                            .getOwnInstrument());
                    tawPartnersDept.setPeopleNumber(tawSystemdeptForm
                            .getPeopleNumber());
                    tawPartnersDept.setPowerAndSet(tawSystemdeptForm
                            .getPowerAndSet());
                    tawPartnersDept.setProfessionalLevel(tawSystemdeptForm
                            .getProfessionalLevel());
                    tawPartnersDept.setQualification(tawSystemdeptForm
                            .getQualification());
                    tawPartnersDept.setQualificationValidity(tawSystemdeptForm
                            .getQualificationValidity());
                    tawPartnersDept.setRegisterFund(tawSystemdeptForm
                            .getRegisterFund());
                    tawPartnersDept.setSeniorTitle(tawSystemdeptForm
                            .getSeniorTitle());
                    tawPartnersDept.setSetupTime(tawSystemdeptForm
                            .getSetupTime());
                    tawPartnersDept.setThirdServiceContract(tawSystemdeptForm
                            .getThirdServiceContract());
                    tawPartnersDept.setAttachName(tawSystemdeptForm
                            .getAttachName());
                    tawPartnersDept.setTowers(tawSystemdeptForm.getTowers());
                    tawPartnersDept.setWorkers(tawSystemdeptForm.getWorkers());

                    // tawPartnersDept = (TawPartnersDept)
                    // convert(tawSystemdeptForm);
                    pmgr.saveTawPartnersDept(tawPartnersDept);
                }
            }
            // -----------------------

            ApplicationCacheMgr cacheMgr = (ApplicationCacheMgr) this
                    .getBean("ApplicationCacheMgr");
            // 刷新所有组件缓存
            cacheMgr.flush("com.boco.eoms.commons.system.dept.DEPT_CATCH");

        }

        return null;
    }

    // AJAX方式进行搜索请求时的数据处理
    public void xsearchNoPartner(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'xsearch' method");
        }

        TawSystemDeptForm searchForm = (TawSystemDeptForm) form;
        String word = searchForm.getDeptName();

        ITawSystemDeptManager mgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManagerFlush");

        List list = new ArrayList();
        list = mgr.searchByName(word);

        // 创建JSON对象
        JSONObject jsonRoot = new JSONObject();

        // 将查询结果的行数放入JSON对象中
        jsonRoot.put("results", list.size());

        // 将查询记录转换为JSON数组放入JSON对象中
        jsonRoot.put("rows", JSONArray.fromObject(list));

        JSONUtil.print(response, jsonRoot.toString());

    }

    /**
     * 根据关键字查询部门名称，得到部门列表，只是代维部门 2008-12-3 wangsixuan
     */
    public void xsearchPartner(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'xsearch' method");
        }

        TawSystemDeptForm searchForm = (TawSystemDeptForm) form;
        String word = searchForm.getDeptName();

        String contacter = searchForm.getDeptmanager();
        String deptphone = searchForm.getDeptphone();
        ITawSystemDeptManager mgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManagerFlush");
        List list = new ArrayList();
        list = mgr.searchByNamePartner(word, contacter, deptphone);

        ITawSystemAreaManager pmgr = (ITawSystemAreaManager) getBean("ItawSystemAreaManager");
        ITawSystemDictTypeManager dmgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");
        List list2 = new ArrayList();
        TawSystemDept sDept = new TawSystemDept();
        TawSystemArea sArea = new TawSystemArea();
        TawSystemDictType dict = new TawSystemDictType();
        for (int i = 0; i < list.size(); i++) {
            sDept = (TawSystemDept) list.get(i);
            if (sDept.getAreaid() != null && !"".equals(sDept.getAreaid())) {
                sArea = pmgr.getAreaByAreaId(sDept.getAreaid());
                sDept.setDeptemail(sArea.getAreaname());
            }
            sDept.setTmpdeptid("");
            if (sDept.getTmporarybegintime() != null && !"".equals(sDept.getTmporarybegintime()) && sDept.getTmporarybegintime().length() > 4) {
                dict = dmgr.getDictTypeByDictid(sDept.getTmporarybegintime());
                sDept.setTmpdeptid(dict.getDictName());
            }
            list2.add(sDept);
        }


        // 创建JSON对象
        JSONObject jsonRoot = new JSONObject();

        // 将查询结果的行数放入JSON对象中
        jsonRoot.put("results", list2.size());

        // 将查询记录转换为JSON数组放入JSON对象中
        jsonRoot.put("rows", JSONArray.fromObject(list2));

        JSONUtil.print(response, jsonRoot.toString());

    }

    /**
     * 根据关键字查询部门名称，得到部门列表，只是非代维部门 2008-12-3 wangsixuan
     */
    public void xsearch(ActionMapping mapping, ActionForm form,
                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'xsearch' method");
        }

        TawSystemDeptForm searchForm = (TawSystemDeptForm) form;
        String word = searchForm.getDeptName();

        ITawSystemDeptManager mgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManagerFlush");

        List list = new ArrayList();
        list = mgr.searchByNameNoPartner(word);

        // 创建JSON对象
        JSONObject jsonRoot = new JSONObject();

        // 将查询结果的行数放入JSON对象中
        jsonRoot.put("results", list.size());

        // 将查询记录转换为JSON数组放入JSON对象中
        jsonRoot.put("rows", JSONArray.fromObject(list));

        JSONUtil.print(response, jsonRoot.toString());

    }

    /**
     * 代维部门用户树 for ext 2008-11-17 liujinlong
     *
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public void userFromDaiweiDept(ActionMapping mapping,
                                   ActionForm actionForm, HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {

        String id = StaticMethod.null2String(request.getParameter("node"),
                StaticVariable.ProvinceID + "");
        // String chkType = StaticMethod.null2String(request
        // .getParameter("checktype"));
        String showType = request.getParameter("showType");
        JSONArray jsonRoot = deptbo.getDaiweiDeptUserTree(id, showType);
        JSONUtil.print(response, jsonRoot.toString());
    }

    /*
     * 附件批量录入
     */
    public ActionForward xaccessory(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 首先将文件从客户端上传到服务器
        String timeTag = StaticMethod.getCurrentDateTime("yyyy_MM_dd_HHmmss");
        String sysTemPaht = StaticMethod.getWebPath();
        TawSystemDeptForm accessoryForm = (TawSystemDeptForm) form;
        String uploadPath = "/WEB-INF/pages/tawSystemDept/upfiles";
        String filePath = sysTemPaht + uploadPath + "/" + timeTag + ".xls";
        File tempFile = new File(sysTemPaht + uploadPath);
        if (!tempFile.exists()) {
            tempFile.mkdir();
        }

        FormFile file = accessoryForm.getAccessoryName();
        try {
            InputStream stream = file.getInputStream(); // 把文件读入
            OutputStream outputStream = new FileOutputStream(filePath); // 建立一个上传文件的输出流

            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return mapping.findForward("failure");
        }

        // 然后把文件的每一条数据读入到form中
        Workbook workbook = null;
        ArrayList accessoryList = new ArrayList();
        try {
            // 构建Workbook对象, 只读Workbook对象
            // 直接从本地文件创建Workbook, 从输入流创建Workbook
            InputStream ins = new FileInputStream(filePath);
            workbook = Workbook.getWorkbook(ins);
            Sheet dataSheet = workbook.getSheet(0);

            // 读取数据
            for (int i = 1; i < dataSheet.getRows(); i++) {
                accessoryForm = new TawSystemDeptForm();
                accessoryForm.setIsPartners("1");
                if (dataSheet.getCell(0, i).getContents() != null
                        && !"".equals(dataSheet.getCell(0, i).getContents()))
                    accessoryForm.setDeptName(dataSheet.getCell(0, i)
                            .getContents().trim());
                if (dataSheet.getCell(1, i).getContents() != null
                        && !"".equals(dataSheet.getCell(1, i).getContents()))
                    accessoryForm.setParentDeptid(dataSheet.getCell(1, i)
                            .getContents().trim());
                if (dataSheet.getCell(2, i).getContents() != null
                        && !"".equals(dataSheet.getCell(2, i).getContents()))
                    accessoryForm.setParentDeptName(dataSheet.getCell(2, i)
                            .getContents().trim());
                if (dataSheet.getCell(3, i).getContents() != null
                        && !"".equals(dataSheet.getCell(3, i).getContents()))
                    accessoryForm.setCompanyType(dataSheet.getCell(3, i)
                            .getContents().trim());
                if (dataSheet.getCell(4, i).getContents() != null
                        && !"".equals(dataSheet.getCell(4, i).getContents()))
                    accessoryForm.setTmporarybegintime(dataSheet.getCell(4, i)
                            .getContents().trim());
                if (dataSheet.getCell(5, i).getContents() != null
                        && !"".equals(dataSheet.getCell(5, i).getContents()))
                    accessoryForm.setTmporarystopTime(dataSheet.getCell(5, i)
                            .getContents().trim());
                if (dataSheet.getCell(6, i).getContents() != null
                        && !"".equals(dataSheet.getCell(6, i).getContents()))
                    accessoryForm.setDeptmanager(dataSheet.getCell(6, i)
                            .getContents().trim());
                if (dataSheet.getCell(7, i).getContents() != null
                        && !"".equals(dataSheet.getCell(7, i).getContents()))
                    accessoryForm.setAreaid(dataSheet.getCell(7, i)
                            .getContents().trim());
                if (dataSheet.getCell(8, i).getContents() != null
                        && !"".equals(dataSheet.getCell(8, i).getContents()))
                    accessoryForm.setAttachName(dataSheet.getCell(8, i)
                            .getContents().trim());
                if (dataSheet.getCell(9, i).getContents() != null
                        && !"".equals(dataSheet.getCell(9, i).getContents()))
                    accessoryForm.setRegisterFund(dataSheet.getCell(9, i)
                            .getContents().trim());
                if (dataSheet.getCell(10, i).getContents() != null
                        && !"".equals(dataSheet.getCell(10, i).getContents()))
                    accessoryForm.setDeptphone(dataSheet.getCell(10, i)
                            .getContents().trim());
                if (dataSheet.getCell(11, i).getContents() != null
                        && !"".equals(dataSheet.getCell(11, i).getContents()))
                    accessoryForm.setDeptmobile(dataSheet.getCell(11, i)
                            .getContents().trim());
                if (dataSheet.getCell(12, i).getContents() != null
                        && !"".equals(dataSheet.getCell(12, i).getContents()))
                    accessoryForm.setDeptfax(dataSheet.getCell(12, i)
                            .getContents().trim());
                if (dataSheet.getCell(13, i).getContents() != null
                        && !"".equals(dataSheet.getCell(13, i).getContents()))
                    accessoryForm.setRemark(dataSheet.getCell(13, i)
                            .getContents().trim());
                if (dataSheet.getCell(14, i).getContents() != null
                        && !"".equals(dataSheet.getCell(14, i).getContents()))
                    accessoryForm.setTmporaryManager(dataSheet.getCell(14, i)
                            .getContents().trim());
                if (dataSheet.getCell(15, i).getContents() != null
                        && !"".equals(dataSheet.getCell(15, i).getContents()))
                    accessoryForm.setThirdServiceContract(dataSheet.getCell(15,
                            i).getContents().trim());
                if (dataSheet.getCell(16, i).getContents() != null
                        && !"".equals(dataSheet.getCell(16, i).getContents()))
                    accessoryForm.setAttachName(dataSheet.getCell(16, i)
                            .getContents().trim());

                accessoryList.add(accessoryForm);
            }

            // 保存该Form
            ITawSystemDeptManager mgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManager");
            ITawSystemDeptManager flushmgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManagerFlush");
            ITawPartnersDeptManager pmgr = (ITawPartnersDeptManager) getBean("ItawPartnersDeptManager");

            String isRight = "Y";

            for (int i = 0; i < accessoryList.size(); ++i) {
                accessoryForm = (TawSystemDeptForm) accessoryList.get(i);
                TawSystemDept tawSystemDept = (TawSystemDept) convert(accessoryForm);

                String strs = StaticMethod.getOperDay();
                String newlinkId = "";

                String paredeptid = tawSystemDept.getParentDeptid();

                TawSystemDept parentDept = mgr.getDeptinfobydeptid(paredeptid,
                        "0");

                if (paredeptid == null || paredeptid.equals("")
                        || paredeptid.equals("-1")) {

                    tawSystemDept.setParentDeptid("-1");
                    tawSystemDept.setDeleted("0");
                    // deptId生成32位字符串
                    newlinkId = mgr.getNewLinkid("1", "0");
                    tawSystemDept.setDeptId(newlinkId);
                    // 保存linkId和parentLinkId
                    tawSystemDept.setLinkid(newlinkId);
                    tawSystemDept.setParentLinkid("-1");
                    tawSystemDept.setOrdercode(new Integer(0));
                    tawSystemDept.setOperremoteip(request.getRemoteAddr());
                    tawSystemDept.setOpertime(strs);
                    tawSystemDept.setLeaf("1");

                } else {
                    // newdeptid = mgr.getNewDeptid(paredeptid, "0");
                    if (paredeptid.equals("-1")) {
                        tawSystemDept.setLeaf("0");
                        tawSystemDept.setOrdercode(new Integer(0));
                        if (tawSystemDept.getOpertime() == null || "".equals(tawSystemDept.getOpertime())) {
                            tawSystemDept.setOpertime(strs);
                        }
                    } else {
                        // 父部门
                        TawSystemDept sysdept = new TawSystemDept();
                        sysdept = deptbo.getDeptinfobydeptid(paredeptid, "0");
                        if (tawSystemDept.getDeptId() == null
                                || tawSystemDept.getDeptId().equals("")) {
                            // 根据父部门linkId生成新的linkId
                            newlinkId = mgr.getNewLinkid(sysdept.getLinkid(),
                                    "0");
                            tawSystemDept.setDeptId(newlinkId);
                            // 该linkid作为nodeId维持树形结构
                            tawSystemDept.setLinkid(newlinkId);
                            tawSystemDept.setParentLinkid(sysdept.getLinkid());
                        }

                        tawSystemDept.setLeaf("1");
                        int oerdercode = sysdept.getOrdercode().intValue();
                        sysdept.setLeaf("0");
                        flushmgr.saveTawSystemDept(sysdept);
                        tawSystemDept.setOrdercode(new Integer(oerdercode + 1));
                        if (tawSystemDept.getOpertime() == null || "".equals(tawSystemDept.getOpertime())) {
                            tawSystemDept.setOpertime(strs);
                        }
                    }
                    tawSystemDept.setDeleted("0");
                    tawSystemDept.setOperremoteip(request.getRemoteAddr());
                    tawSystemDept.setUpdatetime(strs);

                }
                // 给字段isDaiweiRoot赋值，isDaiweiRoot='1'表示该节点是代维部门根节点，isDaiweiRoot='0'表示该部门不是根节点。
                // isDaiweiRoot字段的作用在于为构造代维人员选择树 2008-11-17 liujinlong
                if (parentDept != null) {
                    if (parentDept.getIsDaiweiRoot() != null
                            && parentDept.getIsDaiweiRoot().equals("1"))
                        tawSystemDept.setIsDaiweiRoot("0");// 表示该部门不是代维根节点
                    else
                        tawSystemDept.setIsDaiweiRoot("1");// isDaiweiRoot='1'表示该部门是代维根节点
                }
                // ---------------------------

                tawSystemDept.setRegionflag(new Integer(0));
                tawSystemDept.setTmpdeptid(tawSystemDept.getDeptId());
                flushmgr.saveTawSystemDept(tawSystemDept);

                String isPartners = "1";
                if (isPartners != null && isPartners.equals("1")) {// isPartners=1表示代维公司
                    TawPartnersDept tawPartnersDept = new TawPartnersDept();

                    tawPartnersDept.setBankAccount(accessoryForm
                            .getBankAccount());
                    tawPartnersDept.setBaseStations(accessoryForm
                            .getBaseStations());
                    tawPartnersDept.setBusinessLicense(accessoryForm
                            .getBusinessLicense());
                    tawPartnersDept.setCertificationDept(accessoryForm
                            .getCertificationDept());
                    tawPartnersDept.setCompanyType(accessoryForm
                            .getCompanyType());
                    tawPartnersDept
                            .setCompanyWeb(accessoryForm.getCompanyWeb());
                    tawPartnersDept.setContacter(accessoryForm.getContacter());
                    tawPartnersDept.setFixAsset(accessoryForm.getFixAsset());
                    tawPartnersDept.setIntermediateTitle(accessoryForm
                            .getIntermediateTitle());
                    tawPartnersDept.setIsUnicomAssociation(accessoryForm
                            .getIsUnicomAssociation());
                    tawPartnersDept.setJuniorTitle(accessoryForm
                            .getJuniorTitle());
                    tawPartnersDept.setLines(accessoryForm.getLines());
                    tawPartnersDept.setManagePeople(accessoryForm
                            .getManagePeople());
                    tawPartnersDept
                            .setMicrowaves(accessoryForm.getMicrowaves());
                    tawPartnersDept.setOwnCars(accessoryForm.getOwnCars());
                    tawPartnersDept.setOwnInstrument(accessoryForm
                            .getOwnInstrument());
                    tawPartnersDept.setPeopleNumber(accessoryForm
                            .getPeopleNumber());
                    tawPartnersDept.setPowerAndSet(accessoryForm
                            .getPowerAndSet());
                    tawPartnersDept.setProfessionalLevel(accessoryForm
                            .getProfessionalLevel());
                    tawPartnersDept.setQualification(accessoryForm
                            .getQualification());
                    tawPartnersDept.setQualificationValidity(accessoryForm
                            .getQualificationValidity());
                    tawPartnersDept.setRegisterFund(accessoryForm
                            .getRegisterFund());
                    tawPartnersDept.setSeniorTitle(accessoryForm
                            .getSeniorTitle());
                    tawPartnersDept.setSetupTime(accessoryForm.getSetupTime());
                    tawPartnersDept.setThirdServiceContract(accessoryForm
                            .getThirdServiceContract());
                    tawPartnersDept
                            .setAttachName(accessoryForm.getAttachName());
                    tawPartnersDept.setTowers(accessoryForm.getTowers());
                    tawPartnersDept.setWorkers(accessoryForm.getWorkers());

                    tawPartnersDept.setDeptId(tawSystemDept.getDeptId());// 用这个字段与TawSystemDept表关联
                    pmgr.saveTawPartnersDept(tawPartnersDept);
                }
            }

            workbook.close();
            File fileDel = new File(filePath);
            if (fileDel.exists())
                fileDel.delete();
            return mapping.findForward("success");
        } catch (Exception e) {
            workbook.close();
            File fileDel = new File(filePath);
            if (fileDel.exists())
                fileDel.delete();
            e.printStackTrace();
            return mapping.findForward("failure");
        } finally {
            workbook.close();
            File fileDel = new File(filePath);
            if (fileDel.exists())
                fileDel.delete();
        }
    }

    // 模板文件下载
    public ActionForward xmlOutPut(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            String url = request.getParameter("url");
            url = StaticMethod.getWebPath() + url;
            File file = new File(url);
            // 读到流中
            InputStream inStream = new FileInputStream(file);// 文件的存放路径
            // 设置输出的格式
            response.reset();
            response.setContentType("application/x-msdownload;charset=GBK");
            response.setCharacterEncoding("UTF-8");
            String fileName = URLEncoder.encode(file.getName(), "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String(fileName.getBytes("UTF-8"), "GBK"));

            // 循环取出流中的数据
            byte[] b = new byte[128];
            int len;
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (Exception e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
            return mapping.findForward("failure");
        }
        return null;
    }
}
