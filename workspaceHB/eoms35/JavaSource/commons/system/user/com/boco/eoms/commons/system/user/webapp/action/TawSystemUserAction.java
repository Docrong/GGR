// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TawSystemUserAction.java

package com.boco.eoms.commons.system.user.webapp.action;

import com.boco.eoms.base.util.*;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.base.webapp.listener.UserCounterListener;
import com.boco.eoms.commons.cache.application.ApplicationCacheMgr;
import com.boco.eoms.commons.log.service.logSave;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.service.ITawSystemAreaManager;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.priv.bo.TawSystemPrivAssignOut;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuManager;
import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.commons.system.role.util.RoleIdList;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawPartnersUser;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.*;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;
import com.boco.eoms.commons.system.user.sox.mgr.IUserPasswdHistoryMgr;
import com.boco.eoms.commons.system.user.util.UserAttributes;
import com.boco.eoms.commons.system.user.util.UserMgrLocator;
import com.boco.eoms.commons.system.user.webapp.form.TawSystemUserForm;
import com.boco.eoms.commons.ui.util.JSONUtil;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;

import jxl.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.apache.commons.logging.Log;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;

public final class TawSystemUserAction extends BaseAction {

    public TawSystemUserAction() {
    }

    public ActionForward xsave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        MessageResources mr = getResources(request);
        TawSystemUserForm tawSystemUserForm = (TawSystemUserForm) form;
        ITawSystemUserManager mgr = (ITawSystemUserManager) getBean("ItawSystemUserSaveManagerFlush");
        ITawPartnersUserManager pmgr = (ITawPartnersUserManager) getBean("itawPartnersUserManager");
        if (!mgr.checkUserId(tawSystemUserForm.getUserid())) {
            String msg = mr.getMessage("user.infomation.useridfailure");
            JSONUtil.fail(response, msg);
            return null;
        }
        if (!mgr.checkPasswd(tawSystemUserForm.getNewPassword())) {
            String msg = mr.getMessage("user.infomation.passwdfailure", UserMgrLocator.getUserAttributes().getPasswdLength());
            JSONUtil.fail(response, msg);
            return null;
        }
        if (mgr.isUserExist(tawSystemUserForm.getUserid())) {
            String msg = mr.getMessage("user.infomation.useridExist");
            JSONUtil.fail(response, msg);
            return null;
        }
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String operuserid = sessionform.getUserid();
        tawSystemUserForm.setOperuserid(operuserid);
        TawSystemUser tawSystemUser = (TawSystemUser) convert(tawSystemUserForm);
        tawSystemUser.setPassword((new Md5PasswordEncoder()).encodePassword(tawSystemUserForm.getNewPassword(), new String()));
        tawSystemUser.setDeleted("0");
        tawSystemUser.setOperremotip(request.getRemoteAddr());
        tawSystemUser.setSavetime(new Date());
        tawSystemUser.setEnabled(true);
        tawSystemUser.setAccountLocked(false);
        tawSystemUser.setId("");
        tawSystemUser.setDeptname(TawSystemDeptBo.getInstance().getDeptnameBydeptid(tawSystemUser.getDeptid()));
        String isPartners = tawSystemUser.getIsPartners();
        if (isPartners == null || "".equals(isPartners))
            tawSystemUser.setIsPartners("0");
        if (isPartners != null && isPartners.equals("1")) {
            TawPartnersUser tawPartnersUser = new TawPartnersUser();
            tawPartnersUser.setInCompany(tawSystemUserForm.getInCompany());
            tawPartnersUser.setBirthday(tawSystemUserForm.getBirthday());
            tawPartnersUser.setCertificationInfo(tawSystemUserForm.getCertificationInfo());
            tawPartnersUser.setEducation(tawSystemUserForm.getEducation());
            tawPartnersUser.setGraduateSchool(tawSystemUserForm.getGraduateSchool());
            tawPartnersUser.setIDNumber(tawSystemUserForm.getIDNumber());
            tawPartnersUser.setInCity(tawSystemUserForm.getInCity());
            tawPartnersUser.setInDistrict(tawSystemUserForm.getInDistrict());
            tawPartnersUser.setIsMarried(tawSystemUserForm.getIsMarried());
            tawPartnersUser.setJobType(tawSystemUserForm.getJobType());
            tawPartnersUser.setPersonDiscription(tawSystemUserForm.getPersonDiscription());
            tawPartnersUser.setPhotoInfo(tawSystemUserForm.getPhotoInfo());
            tawPartnersUser.setPoliticalStatus(tawSystemUserForm.getPoliticalStatus());
            tawPartnersUser.setPost(tawSystemUserForm.getPost());
            tawPartnersUser.setPostState(tawSystemUserForm.getPostState());
            tawPartnersUser.setSpecialties(tawSystemUserForm.getSpecialties());
            tawPartnersUser.setStaffLabel(tawSystemUserForm.getStaffLabel());
            tawPartnersUser.setStartWorkTime(tawSystemUserForm.getStartWorkTime());
            tawPartnersUser.setToDeptTime(tawSystemUserForm.getToDeptTime());
            tawPartnersUser.setUserid(tawSystemUser.getUserid());
            pmgr.saveTawPartnersUser(tawPartnersUser);
        }
        mgr.saveTawSystemUser(tawSystemUser, tawSystemUserForm.getOlduserid());
        return null;
    }

    public ActionForward saveuserinfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemUserForm tawSystemUserForm = (TawSystemUserForm) form;
        ITawSystemUserManager mgr = (ITawSystemUserManager) getBean("ItawSystemUserSaveManagerFlush");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String operuserid = sessionform.getUserid();
        tawSystemUserForm.setOperuserid(operuserid);
        TawSystemUser tawSystemUser = (TawSystemUser) convert(tawSystemUserForm);
        tawSystemUser.setDeleted("0");
        IUserPasswdHistoryMgr userPasswdHistoryMgr = (IUserPasswdHistoryMgr) getBean("userPasswdHistoryMgr");
        if (tawSystemUserForm.getNewPassword() != null && !"".equals(tawSystemUserForm.getNewPassword().trim())) {
            if (!mgr.checkPasswd(tawSystemUserForm.getNewPassword())) {
                MessageResources mr = getResources(request);
                String msg = mr.getMessage("user.infomation.passwdfailure", UserMgrLocator.getUserAttributes().getPasswdLength());
                JSONUtil.fail(response, msg);
                return null;
            }
            if (userPasswdHistoryMgr.isRepeatPasswd(tawSystemUser.getId(), tawSystemUserForm.getNewPassword(), UserMgrLocator.getUserAttributes().getPasswdRepeatNum())) {
                MessageResources mr = getResources(request);
                String msg = mr.getMessage("user.infomation.repeatpasswd", UserMgrLocator.getUserAttributes().getPasswdRepeatNum());
                JSONUtil.fail(response, msg);
                return null;
            }
            tawSystemUser.setPassword((new Md5PasswordEncoder()).encodePassword(tawSystemUserForm.getNewPassword(), new String()));
        } else {
            tawSystemUser.setPassword(tawSystemUserForm.getPassword());
        }
        tawSystemUser.setOperremotip(request.getRemoteAddr());
        tawSystemUser.setSavetime(new Date());
        tawSystemUser.setEnabled(true);
        tawSystemUser.setDeptname(TawSystemDeptBo.getInstance().getDeptnameBydeptid(tawSystemUser.getDeptid()));
        if (tawSystemUser.getIsPartners() == null || "".equals(tawSystemUser.getIsPartners()))
            tawSystemUser.setIsPartners("0");
        mgr.saveTawSystemUser(tawSystemUser, tawSystemUserForm.getOlduserid());
        return mapping.findForward("edituser");
    }

    public ActionForward editPersonal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemUserForm tawSystemUserForm = (TawSystemUserForm) form;
        ITawSystemUserManager mgr = (ITawSystemUserManager) getBean("ItawSystemUserSaveManagerFlush");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String operuserid = sessionform.getUserid();
        tawSystemUserForm.setOperuserid(operuserid);
        TawSystemUser tawSystemUser = (TawSystemUser) convert(tawSystemUserForm);
        tawSystemUser.setDeleted("0");
        IUserPasswdHistoryMgr userPasswdHistoryMgr = (IUserPasswdHistoryMgr) getBean("userPasswdHistoryMgr");
        if (tawSystemUserForm.getNewPassword() != null && !"".equals(tawSystemUserForm.getNewPassword())) {
            if (!mgr.checkPasswd(tawSystemUserForm.getNewPassword())) {
                ActionMessages messages = new ActionMessages();
                messages.add("org.apache.struts.action.GLOBAL_MESSAGE", new ActionMessage("user.infomation.passwdfailure", UserMgrLocator.getUserAttributes().getPasswdLength()));
                saveMessages(request, messages);
                return mapping.findForward("failure");
            }
            if (userPasswdHistoryMgr.isRepeatPasswd(tawSystemUser.getId(), tawSystemUserForm.getNewPassword(), UserMgrLocator.getUserAttributes().getPasswdRepeatNum())) {
                ActionMessages messages = new ActionMessages();
                messages.add("org.apache.struts.action.GLOBAL_MESSAGE", new ActionMessage("user.infomation.repeatpasswd", UserMgrLocator.getUserAttributes().getPasswdRepeatNum()));
                saveMessages(request, messages);
                return mapping.findForward("failure");
            }
            tawSystemUser.setPassword((new Md5PasswordEncoder()).encodePassword(tawSystemUserForm.getNewPassword(), new String()));
        } else {
            tawSystemUser.setPassword(tawSystemUserForm.getPassword());
        }
        tawSystemUser.setOperremotip(request.getRemoteAddr());
        tawSystemUser.setSavetime(new Date());
        tawSystemUser.setEnabled(true);
        tawSystemUser.setDeptname(TawSystemDeptBo.getInstance().getDeptnameBydeptid(tawSystemUser.getDeptid()));
        if (tawSystemUser.getIsPartners() == null || "".equals(tawSystemUser.getIsPartners()))
            tawSystemUser.setIsPartners("0");
        mgr.saveTawSystemUser(tawSystemUser, tawSystemUserForm.getOlduserid());
        return mapping.findForward("edituser");
    }

    public ActionForward getNodes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String node = StaticMethod.null2String(request.getParameter("node"), "1");
        TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();
        TawSystemUserRoleBo userrolebo = TawSystemUserRoleBo.getInstance();
        String template = StaticMethod.null2String(request.getParameter("tpl"), "tpl-user-layoutTree");
        List userlist = new ArrayList();
        List deptlist = new ArrayList();
        try {
            ITawSystemUserManager user = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
            ITawSystemDeptManager mgr = (ITawSystemDeptManager) ApplicationContextHolder.getInstance().getBean("ItawSystemDeptManager");
            deptlist = (ArrayList) deptbo.getNextLevecDepts(node, "0");
            if (deptlist.size() > 0) {
                List list = new ArrayList();
                for (int i = 0; i < deptlist.size(); i++) {
                    TawSystemDept tawSystemDept = new TawSystemDept();
                    tawSystemDept = (TawSystemDept) deptlist.get(i);
                    String deptName = tawSystemDept.getDeptName();
                    if (deptName != null && !"".equals(deptName)) {
                        int count = 0;
                        if (tawSystemDept.getDeptId().equals("1")) {
                            List lists = new ArrayList();
                            lists = mgr.getNextLevecDepts(tawSystemDept.getDeptId(), "0");
                            int allCount = user.getAllMobileBydeptid("1").size();
                            if (lists.size() > 0) {
                                for (int j = 0; j < lists.size(); j++) {
                                    TawSystemDept tawSystemDepttmp = new TawSystemDept();
                                    tawSystemDepttmp = (TawSystemDept) lists.get(j);
                                    count += user.getUserLike(tawSystemDepttmp.getDeptId()).size();
                                }

                            }
                            count += allCount;
                            System.out.println("count:" + count);
                        } else {
                            count = user.getUserLike(tawSystemDept.getDeptId()).size();
                        }
                        tawSystemDept.setDeptName(deptName + "(" + String.valueOf(count) + ")");
                        list.add(tawSystemDept);
                    }
                }

                request.setAttribute("deptlist", list);
            }
            userlist = (ArrayList) userrolebo.getUserBydeptids(node);
        } catch (Exception ex) {
            ex.printStackTrace();
            BocoLog.error(this, "生成用户管理模块树图时报错：" + ex);
        }
        request.setAttribute("deptlist", deptlist);
        request.setAttribute("userlist", userlist);
        ApplicationCacheMgr cacheMgr = (ApplicationCacheMgr) getBean("ApplicationCacheMgr");
        cacheMgr.flush("com.boco.eoms.commons.system.dept.DEPT_CATCH");
        return mapping.findForward(template);
    }

    public ActionForward getNodesNoPartner(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String node = StaticMethod.null2String(request.getParameter("node"), "1");
        TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();
        TawSystemUserRoleBo userrolebo = TawSystemUserRoleBo.getInstance();
        String template = StaticMethod.null2String(request.getParameter("tpl"), "tpl-user-layoutTree");
        List userlist = new ArrayList();
        List deptlist = new ArrayList();
        try {
            deptlist = (ArrayList) deptbo.getNextLevecDeptsNoPartner(node, "0");
            userlist = (ArrayList) userrolebo.getUserBydeptids(node);
        } catch (Exception ex) {
            BocoLog.error(this, "生成用户管理模块树图时报错：" + ex);
        }
        ITawSystemDeptManager mgr = (ITawSystemDeptManager) ApplicationContextHolder.getInstance().getBean("ItawSystemDeptManager");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        RoleIdList roleIdList = (RoleIdList) getBean("roleIdInGroup");
        List roleList = sessionform.getRolelist();
        ITawSystemAreaManager areaManager = (ITawSystemAreaManager) getBean("ItawSystemAreaManager");
        String hasRight = request.getParameter("hasRight");
        if (node.equals("-1"))
            hasRight = "0";
        request.setAttribute("hasRight", hasRight);
        request.setAttribute("areaManager", areaManager);
        request.setAttribute("roleIdList", roleIdList);
        request.setAttribute("userid", sessionform.getUserid());
        request.setAttribute("roleList", roleList);
        request.setAttribute("tawSystemDeptManager", mgr);
        request.setAttribute("deptlist", deptlist);
        request.setAttribute("userlist", userlist);
        return mapping.findForward(template);
    }

    public ActionForward getNodesPartner(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String node = StaticMethod.null2String(request.getParameter("node"), "1");
        TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();
        TawSystemUserRoleBo userrolebo = TawSystemUserRoleBo.getInstance();
        String template = StaticMethod.null2String(request.getParameter("tpl"), "tpl-user-layoutTree");
        List userlist = new ArrayList();
        List deptlist = new ArrayList();
        try {
            deptlist = (ArrayList) deptbo.getNextLevecDepts(node, "0");
            if (deptbo.isPartner(node))
                userlist = (ArrayList) userrolebo.getUserBydeptids(node);
        } catch (Exception ex) {
            BocoLog.error(this, "生成用户管理模块树图时报错：" + ex);
        }
        request.setAttribute("deptlist", deptlist);
        request.setAttribute("userlist", userlist);
        return mapping.findForward(template);
    }

    public String xdelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String _strId = request.getParameter("id");
        ITawSystemUserManager mgrflush = (ITawSystemUserManager) getBean("ItawSystemUserManagerFlush");
        ITawPartnersUserManager pmgr = (ITawPartnersUserManager) getBean("itawPartnersUserManager");
        String userid = mgrflush.getTawSystemUser(_strId).getUserid();
        if (mgrflush.getTawSystemUser(_strId).getIsPartners().equals("1")) {
            TawPartnersUser tawPartnersUser = pmgr.getPartnersUserByUserId(userid);
            if (tawPartnersUser.getId() != null)
                pmgr.removeTawPartnersUser(tawPartnersUser);
        }
        mgrflush.removeTawSystemUser(_strId);
        return null;
    }

    public String xresume(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String _strId = request.getParameter("id");
        ITawSystemUserManager mgr = (ITawSystemUserManager) getBean("itawSystemUserManager");
        mgr.resumeTawSystemUser(_strId);
        return null;
    }

    public ActionForward resume(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String _strId = request.getParameter("id");
        ITawSystemUserManager mgr = (ITawSystemUserManager) getBean("itawSystemUserManager");
        mgr.resumeTawSystemUser(_strId);
        return mapping.findForward("searchdel");
    }

    public String xget(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String _strId = request.getParameter("id");
        TawSystemUserForm tawSystemUserForm = (TawSystemUserForm) form;
        ITawSystemUserManager mgr = (ITawSystemUserManager) getBean("itawSystemUserManager");
        ITawPartnersUserManager pmgr = (ITawPartnersUserManager) getBean("itawPartnersUserManager");
        TawSystemUser _objOneOpt = new TawSystemUser();
        _objOneOpt = mgr.getTawSystemUser(_strId);
        tawSystemUserForm = (TawSystemUserForm) convert(_objOneOpt);
        tawSystemUserForm.setOlduserid(_objOneOpt.getUserid());
        tawSystemUserForm.setPassword(_objOneOpt.getPassword());
        if (_objOneOpt.getIsPartners() != null && _objOneOpt.getIsPartners().equals("1")) {
            TawPartnersUser tawPartnersUser = pmgr.getPartnersUserByUserId(_objOneOpt.getUserid());
            tawSystemUserForm.setInCompany(tawPartnersUser.getInCompany());
            tawSystemUserForm.setInCity(tawPartnersUser.getInCity());
            tawSystemUserForm.setInDistrict(tawPartnersUser.getInDistrict());
            tawSystemUserForm.setEducation(tawPartnersUser.getEducation());
            tawSystemUserForm.setBirthday(tawPartnersUser.getBirthday());
            tawSystemUserForm.setTechTitle(tawPartnersUser.getTechTitle());
            tawSystemUserForm.setIDNumber(tawPartnersUser.getIDNumber());
            tawSystemUserForm.setPostState(tawPartnersUser.getPostState());
            tawSystemUserForm.setPhotoInfo(tawPartnersUser.getPhotoInfo());
            tawSystemUserForm.setStaffLabel(tawPartnersUser.getStaffLabel());
            tawSystemUserForm.setIsMarried(tawPartnersUser.getIsMarried());
            tawSystemUserForm.setJobType(tawPartnersUser.getJobType());
            tawSystemUserForm.setPost(tawPartnersUser.getPost());
            tawSystemUserForm.setGraduateSchool(tawPartnersUser.getGraduateSchool());
            tawSystemUserForm.setSpecialties(tawPartnersUser.getSpecialties());
            tawSystemUserForm.setPoliticalStatus(tawPartnersUser.getPersonDiscription());
            tawSystemUserForm.setWorkPeriod(tawPartnersUser.getWorkPeriod());
            tawSystemUserForm.setPersonDiscription(tawPartnersUser.getPersonDiscription());
            tawSystemUserForm.setStartWorkTime(tawPartnersUser.getStartWorkTime());
            tawSystemUserForm.setToDeptTime(tawPartnersUser.getToDeptTime());
            tawSystemUserForm.setCertificationInfo(tawPartnersUser.getCertificationInfo());
        }
        JSONObject jsonRoot = new JSONObject();
        jsonRoot = JSONObject.fromObject(tawSystemUserForm);
        JSONUtil.print(response, jsonRoot.toString());
        return null;
    }

    public ActionForward editUserInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String operuserid = sessionform.getUserid();
        TawSystemUserForm tawSystemUserForm = (TawSystemUserForm) form;
        ITawSystemUserManager mgr = (ITawSystemUserManager) getBean("itawSystemUserManager");
        TawSystemUser tawSystemUser = mgr.getUserByuserid(operuserid);
        tawSystemUserForm = (TawSystemUserForm) convert(tawSystemUser);
        tawSystemUserForm.setUpdatetime(StaticMethod.getOperDay());
        updateFormBean(mapping, request, tawSystemUserForm);
        ITawSystemUserRefRoleManager userRefRoleMgr = (ITawSystemUserRefRoleManager) getBean("itawSystemUserRefRoleManager");
        List subRoleList = userRefRoleMgr.getSubRoleByUserId(operuserid, "1");
        List roleList = new ArrayList();
        HashMap hm = new HashMap();
        for (Iterator subRoleIt = subRoleList.iterator(); subRoleIt.hasNext(); ) {
            TawSystemSubRole subRole = (TawSystemSubRole) subRoleIt.next();
            if (hm.get(String.valueOf(subRole.getRoleId())) == null) {
                ITawSystemRoleManager roleMgr = (ITawSystemRoleManager) getBean("ItawSystemRoleManager");
                TawSystemRole role = roleMgr.getTawSystemRole(subRole.getRoleId());
                roleList.add(role);
                hm.put(String.valueOf(role.getRoleId()), role);
            }
        }

        request.setAttribute("subRoleList", subRoleList);
        request.setAttribute("roleList", roleList);
        return mapping.findForward("edituser");
    }

    public ActionForward saveUserLogOut(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String operuserid = sessionform.getUserid();
        request.getSession().removeAttribute("sessionform");
        request.getSession().removeAttribute("edu.yale.its.tp.cas.client.filter.user");
        TawSystemPrivAssignOut privassimgr = TawSystemPrivAssignOut.getInstance();
        String modeName = privassimgr.getNameBycode(request.getParameter("id"));
        logSave log = logSave.getInstance(modeName, operuserid, "0002", request.getRemoteAddr(), operuserid + " 于:" + StaticMethod.getCurrentDateTime() + " 登出系统.", "111");
        log.info();
        if ("sso".equals(UtilMgrLocator.getEOMSAttributes().getLoginType())) {
            String serverName = request.getServerName();
            SSOConfig ssoConfig = (SSOConfig) UtilMgrLocator.getEOMSAttributes().getRegister().get(serverName);
            String casAddr = ssoConfig.getCasLogin().trim();
            String eomsAddr = "http://" + ssoConfig.getEomsServerName().trim() + ":" + request.getServerPort() + request.getContextPath();
            response.sendRedirect(casAddr + "/logout?eomsUrl=" + eomsAddr);
            return null;
        } else {
            return mapping.findForward("relogin");
        }
    }

    public void saveUserLogOutClose(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String operuserid = sessionform.getUserid();
        request.getSession().removeAttribute("sessionform");
        TawSystemPrivAssignOut privassimgr = TawSystemPrivAssignOut.getInstance();
        String modeName = privassimgr.getNameBycode(request.getParameter("id"));
        logSave log = logSave.getInstance(modeName, operuserid, "0002", request.getRemoteAddr(), operuserid + " 于:" + StaticMethod.getCurrentDateTime() + " 登出系统.", "111");
        log.info();
    }

    public String checkUnique(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String type = StaticMethod.null2String(request.getParameter("name"));
        String value = StaticMethod.null2String(request.getParameter("value"));
        boolean flag = false;
        if (type.equalsIgnoreCase("userid")) {
            ITawSystemUserManager mgr = (ITawSystemUserManager) getBean("itawSystemUserManager");
            String id = StaticMethod.null2String(mgr.getUserByuserid(value).getId());
            String tawid = StaticMethod.null2String(mgr.getTawSystemUserByuserid(value).getId());
            if (!tawid.equals("")) {
                if ("".equals(id)) {
                    JSONUtil.print(response, "falsedelete");
                    return null;
                }
                flag = true;
            }
        } else if (type.equalsIgnoreCase("deptName")) {
            ITawSystemDeptManager mgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManagerFlush");
            flag = mgr.getDeptnameIsExist(value, String.valueOf(0));
        } else if (type.equalsIgnoreCase("areaname")) {
            ITawSystemAreaManager mgr = (ITawSystemAreaManager) getBean("ItawSystemAreaManager");
            flag = mgr.isExitAreaName(value);
        } else if (type.equalsIgnoreCase("name")) {
            ITawSystemPrivMenuManager mgr = (ITawSystemPrivMenuManager) getBean("ItawSystemPrivMenuManager");
            flag = mgr.isExits(value);
        }
        JSONUtil.print(response, (new Boolean(flag)).toString());
        return null;
    }

    public ActionForward xedit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        MessageResources mr = getResources(request);
        TawSystemUserForm tawSystemUserForm = (TawSystemUserForm) form;
        ITawSystemUserManager mgr = (ITawSystemUserManager) getBean("ItawSystemUserManagerFlush");
        ITawPartnersUserManager pmgr = (ITawPartnersUserManager) getBean("itawPartnersUserManager");
        if (!mgr.checkUserId(tawSystemUserForm.getUserid())) {
            String msg = mr.getMessage("user.infomation.useridfailure");
            JSONUtil.fail(response, msg);
            return null;
        }
        if (tawSystemUserForm.getOlduserid() != null && !tawSystemUserForm.getOlduserid().equals(tawSystemUserForm.getUserid()) && mgr.isUserExist(tawSystemUserForm.getUserid())) {
            String msg = mr.getMessage("user.infomation.useridExist");
            JSONUtil.fail(response, msg);
            return null;
        }
        IUserPasswdHistoryMgr userPasswdHistoryMgr = (IUserPasswdHistoryMgr) getBean("userPasswdHistoryMgr");
        TawSystemUser tawSystemUser = (TawSystemUser) convert(tawSystemUserForm);
        if (tawSystemUserForm.getNewPassword() != null && !"".equals(tawSystemUserForm.getNewPassword().trim())) {
            if (!mgr.checkPasswd(tawSystemUserForm.getNewPassword())) {
                String msg = mr.getMessage("user.infomation.passwdfailure", UserMgrLocator.getUserAttributes().getPasswdLength());
                JSONUtil.fail(response, msg);
                return null;
            }
            if (userPasswdHistoryMgr.isRepeatPasswd(tawSystemUser.getId(), tawSystemUserForm.getNewPassword(), UserMgrLocator.getUserAttributes().getPasswdRepeatNum())) {
                String msg = mr.getMessage("user.infomation.repeatpasswd", UserMgrLocator.getUserAttributes().getPasswdRepeatNum());
                JSONUtil.fail(response, msg);
                return null;
            }
            tawSystemUser.setPassword((new Md5PasswordEncoder()).encodePassword(tawSystemUserForm.getNewPassword(), new String()));
        } else {
            tawSystemUser.setPassword(tawSystemUserForm.getPassword());
        }
        tawSystemUser.setSavetime(new Date());
        String isPartners = tawSystemUser.getIsPartners();
        if (tawSystemUser.getIsPartners() == null || "".equals(tawSystemUser.getIsPartners()))
            tawSystemUser.setIsPartners("0");
        if (isPartners != null && isPartners.equals("1")) {
            TawPartnersUser tawPartnersUser = pmgr.getPartnersUserByUserId(tawSystemUser.getUserid());
            tawPartnersUser.setInCompany(tawSystemUserForm.getInCompany());
            tawPartnersUser.setBirthday(tawSystemUserForm.getBirthday());
            tawPartnersUser.setCertificationInfo(tawSystemUserForm.getCertificationInfo());
            tawPartnersUser.setEducation(tawSystemUserForm.getEducation());
            tawPartnersUser.setGraduateSchool(tawSystemUserForm.getGraduateSchool());
            tawPartnersUser.setIDNumber(tawSystemUserForm.getIDNumber());
            tawPartnersUser.setInCity(tawSystemUserForm.getInCity());
            tawPartnersUser.setInDistrict(tawSystemUserForm.getInDistrict());
            tawPartnersUser.setIsMarried(tawSystemUserForm.getIsMarried());
            tawPartnersUser.setJobType(tawSystemUserForm.getJobType());
            tawPartnersUser.setPersonDiscription(tawSystemUserForm.getPersonDiscription());
            tawPartnersUser.setPhotoInfo(tawSystemUserForm.getPhotoInfo());
            tawPartnersUser.setPoliticalStatus(tawSystemUserForm.getPoliticalStatus());
            tawPartnersUser.setPost(tawSystemUserForm.getPost());
            tawPartnersUser.setPostState(tawSystemUserForm.getPostState());
            tawPartnersUser.setSpecialties(tawSystemUserForm.getSpecialties());
            tawPartnersUser.setStaffLabel(tawSystemUserForm.getStaffLabel());
            tawPartnersUser.setStartWorkTime(tawSystemUserForm.getStartWorkTime());
            tawPartnersUser.setToDeptTime(tawSystemUserForm.getToDeptTime());
            pmgr.saveTawPartnersUser(tawPartnersUser);
        }
        mgr.saveTawSystemUser(tawSystemUser, tawSystemUserForm.getOlduserid());
        return null;
    }

    public void xsearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (log.isDebugEnabled())
            log.debug("Entering 'xsearch' method");
        TawSystemUserForm searchForm = (TawSystemUserForm) form;
        String userName = searchForm.getUsername();
        ITawSystemUserManager mgr = (ITawSystemUserManager) getBean("itawSystemUserManager");
        List list = new ArrayList();
        list = mgr.getUsersByName(userName);
        JSONObject jsonRoot = new JSONObject();
        jsonRoot.put("results", list.size());
        jsonRoot.put("rows", JSONArray.fromObject(list));
        JSONUtil.print(response, jsonRoot.toString());
    }

    public void xsearchPartner(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (log.isDebugEnabled())
            log.debug("Entering 'xsearch' method");
        TawSystemUserForm searchForm = (TawSystemUserForm) form;
        String userName = searchForm.getUsername();
        String cptroomname = searchForm.getFax();
        String deptname = searchForm.getPhone();
        String mobile = searchForm.getMobile();
        String email = searchForm.getEmail();
        ITawSystemUserManager mgr = (ITawSystemUserManager) getBean("itawSystemUserManager");
        List list = new ArrayList();
        list = mgr.getUsersByInfo(userName, cptroomname, deptname, mobile, email);
        List listForDel = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            TawSystemUser temp = (TawSystemUser) list.get(i);
            TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();
            if (!deptbo.isPartner(temp.getDeptid()))
                listForDel.add((new StringBuffer(String.valueOf(i))).toString());
        }

        for (int j = listForDel.size() - 1; j >= 0; j--) {
            String s = listForDel.get(j).toString();
            int jj = Integer.parseInt(s);
            list.remove(jj);
        }

        ITawSystemDictTypeManager dmgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");
        List list2 = new ArrayList();
        TawSystemUser sUser = new TawSystemUser();
        TawSystemDictType dict = new TawSystemDictType();
        for (int i = 0; i < list.size(); i++) {
            sUser = (TawSystemUser) list.get(i);
            sUser.setIsfullemploy("");
            sUser.setIsrest("");
            if (sUser.getSex() != null && !"".equals(sUser.getSex()) && sUser.getSex().length() > 4) {
                dict = dmgr.getDictTypeByDictid(sUser.getSex());
                sUser.setIsfullemploy(dict.getDictName());
            }
            if (sUser.getFamilyaddress() != null && !"".equals(sUser.getFamilyaddress()) && sUser.getSex().length() > 4) {
                dict = dmgr.getDictTypeByDictid(sUser.getFamilyaddress());
                sUser.setIsrest(dict.getDictName());
            }
            list2.add(sUser);
        }

        JSONObject jsonRoot = new JSONObject();
        jsonRoot.put("results", list2.size());
        jsonRoot.put("rows", JSONArray.fromObject(list2));
        JSONUtil.print(response, jsonRoot.toString());
    }

    public void xsearchNoPartner(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (log.isDebugEnabled())
            log.debug("Entering 'xsearch' method");
        TawSystemUserForm searchForm = (TawSystemUserForm) form;
        String userName = searchForm.getUsername();
        String deptName = searchForm.getPost();
        ITawSystemUserManager mgr = (ITawSystemUserManager) getBean("itawSystemUserManager");
        List list = new ArrayList();
        list = mgr.getUsersByName(userName, deptName);
        List listForDel = new ArrayList();
        TawSystemUser temp = null;
        TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();
        for (int i = 0; i < list.size(); i++) {
            temp = (TawSystemUser) list.get(i);
            if (deptbo.isPartner(temp.getDeptid()))
                listForDel.add((new StringBuffer(String.valueOf(i))).toString());
        }

        for (int j = listForDel.size() - 1; j >= 0; j--) {
            String s = listForDel.get(j).toString();
            int jj = Integer.parseInt(s);
            list.remove(jj);
        }

        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        ITawSystemAreaManager areaManager = (ITawSystemAreaManager) getBean("ItawSystemAreaManager");
        String hasRight = "0";
        String operuserid = sessionform.getUserid();
        if (operuserid.equals("admin"))
            hasRight = "1";
        RoleIdList roleIdList = (RoleIdList) getBean("roleIdInGroup");
        List roleList = sessionform.getRolelist();
        JSONArray json = new JSONArray();
        if (list.size() > 0) {
            TawSystemDept dept = null;
            TawSystemUser user = null;
            for (int i = 0; i < list.size(); i++) {
                user = (TawSystemUser) list.get(i);
                dept = deptbo.getDeptinfobydeptid(user.getDeptid(), "0");
                String deptId = dept.getDeptId();
                TawSystemSubRole tempRole = null;
                TawSystemArea area = null;
                String areaId = null;
                for (int k = 0; k < roleList.size(); k++) {
                    tempRole = (TawSystemSubRole) roleList.get(k);
                    if (tempRole.getRoleId() != (long) roleIdList.getUserAdminRoleId().intValue())
                        continue;
                    if (tempRole.getDeptId() != null && !tempRole.getDeptId().equals("") && (tempRole.getArea() == null || tempRole.getArea().equals(""))) {
                        area = areaManager.getAreaByAreaId(tempRole.getDeptId());
                        if (tempRole.getDeptId().equals(dept.getAreaid())) {
                            hasRight = "1";
                            break;
                        }
                        if (area != null && area.getId() != null && !area.getLeaf().equals("1")) {
                            for (areaId = dept.getAreaid(); areaId != null && !areaId.equals("-1"); ) {
                                area = areaManager.getAreaByAreaId(areaId);
                                if (area.getParentAreaid().equals(tempRole.getDeptId())) {
                                    hasRight = "1";
                                    break;
                                }
                                areaId = area.getParentAreaid();
                                hasRight = "0";
                            }

                            if (hasRight.equals("1"))
                                break;
                        } else {
                            hasRight = "0";
                        }
                        continue;
                    }
                    if (tempRole.getArea() != null && !tempRole.getArea().equals("") && (tempRole.getDeptId() == null || tempRole.getDeptId().equals(""))) {
                        if (tempRole.getArea().equals(dept.getDeptId())) {
                            hasRight = "1";
                            break;
                        }
                        TawSystemDept tsd = null;
                        for (deptId = dept.getParentDeptid(); !deptId.equals("-1"); ) {
                            tsd = deptbo.getDeptinfobydeptid(deptId, "0");
                            if (tsd.getId() != null) {
                                if (tempRole.getArea().equals(tsd.getDeptId())) {
                                    hasRight = "1";
                                    break;
                                }
                                deptId = tsd.getParentDeptid();
                                hasRight = "0";
                            } else {
                                deptId = "-1";
                                hasRight = "0";
                            }
                        }

                        if (hasRight.equals("1"))
                            break;
                        continue;
                    }
                    if (tempRole.getDeptId() != null && !tempRole.getDeptId().equals("") && tempRole.getArea() != null && !tempRole.getArea().equals("")) {
                        areaId = dept.getAreaid();
                        String isArea;
                        for (isArea = "0"; areaId != null && !areaId.equals("-1"); isArea = "0") {
                            area = areaManager.getAreaByAreaId(areaId);
                            if (area.getAreaid().equals(tempRole.getDeptId())) {
                                isArea = "1";
                                break;
                            }
                            areaId = area.getParentAreaid();
                        }

                        if (isArea.equals("1")) {
                            if (tempRole.getArea().equals(dept.getDeptId())) {
                                hasRight = "1";
                                break;
                            }
                            TawSystemDept tsd = null;
                            for (deptId = dept.getParentDeptid(); !deptId.equals("-1"); ) {
                                tsd = deptbo.getDeptinfobydeptid(deptId, "0");
                                if (tsd.getId() != null) {
                                    if (tempRole.getArea().equals(tsd.getDeptId())) {
                                        hasRight = "1";
                                        break;
                                    }
                                    deptId = tsd.getParentDeptid();
                                    hasRight = "0";
                                } else {
                                    deptId = "-1";
                                    hasRight = "0";
                                }
                            }

                            if (hasRight.equals("1"))
                                break;
                        } else {
                            hasRight = "0";
                        }
                    } else {
                        hasRight = "0";
                    }
                }

                JSONObject jitem = new JSONObject();
                jitem.put("id", user.getId());
                jitem.put("text", user.getUsername());
                jitem.put("nodeType", "user");
                jitem.put("mobile", user.getMobile());
                jitem.put("username", user.getUsername());
                jitem.put("phone", user.getPhone());
                jitem.put("deptname", dept.getDeptName());
                if (hasRight.equals("1")) {
                    jitem.put("allowChild", false);
                    jitem.put("allowDelete", true);
                    jitem.put("allowEdit", true);
                    jitem.put("allowsubroleList-mi", true);
                    jitem.put("allowadjustsubrole-mi", true);
                } else {
                    jitem.put("allowChild", false);
                    jitem.put("allowDelete", false);
                    jitem.put("allowEdit", false);
                    jitem.put("allowsubroleList-mi", false);
                    jitem.put("allowadjustsubrole-mi", false);
                }
                json.put(jitem);
            }

        }
        JSONObject jsonRoot = new JSONObject();
        jsonRoot.put("results", list.size());
        jsonRoot.put("rows", json);
        JSONUtil.print(response, jsonRoot.toString());
    }

    public ActionForward xsearchdel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            TawSystemUserForm searchForm = (TawSystemUserForm) form;
            String userName = StaticMethod.nullObject2String(searchForm.getUsername());
            ITawSystemUserManager mgr = (ITawSystemUserManager) getBean("itawSystemUserManager");
            List list = new ArrayList();
            list = mgr.getUsersDelByName(userName);
            request.setAttribute("tawSystemUserList", list);
        } catch (Exception e) {
            e.printStackTrace();
            return mapping.findForward("failure");
        }
        return mapping.findForward("searchdel");
    }

    public ActionForward xaccessory(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {
            // 首先将文件从客户端上传到服务器
            String timeTag = StaticMethod
                    .getCurrentDateTime("yyyy_MM_dd_HHmmss");
            String sysTemPaht = StaticMethod.getWebPath();
            TawSystemUserForm accessoryForm = (TawSystemUserForm) form;
            String uploadPath = "/WEB-INF/pages/tawSystemUser/upfiles";
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
                    accessoryForm = new TawSystemUserForm();
                    accessoryForm.setIsPartners("1");
                    if (dataSheet.getCell(0, i).getContents() != null
                            && !""
                            .equals(dataSheet.getCell(0, i)
                                    .getContents()))
                        accessoryForm.setUsername(dataSheet.getCell(0, i)
                                .getContents().trim());
                    if (dataSheet.getCell(1, i).getContents() != null
                            && !""
                            .equals(dataSheet.getCell(1, i)
                                    .getContents()))
                        accessoryForm.setUserid(dataSheet.getCell(1, i)
                                .getContents().trim());
                    if (dataSheet.getCell(2, i).getContents() != null
                            && !""
                            .equals(dataSheet.getCell(2, i)
                                    .getContents()))
                        accessoryForm.setNewPassword(dataSheet.getCell(2, i)
                                .getContents().trim());
                    if (dataSheet.getCell(3, i).getContents() != null
                            && !""
                            .equals(dataSheet.getCell(3, i)
                                    .getContents()))
                        accessoryForm.setDeptid(dataSheet.getCell(3, i)
                                .getContents().trim());
                    if (dataSheet.getCell(4, i).getContents() != null
                            && !""
                            .equals(dataSheet.getCell(4, i)
                                    .getContents()))
                        accessoryForm.setDeptname(dataSheet.getCell(4, i)
                                .getContents().trim());
                    if (dataSheet.getCell(5, i).getContents() != null
                            && !""
                            .equals(dataSheet.getCell(5, i)
                                    .getContents()))
                        accessoryForm.setCptroomid(dataSheet.getCell(5, i)
                                .getContents().trim());
                    if (dataSheet.getCell(6, i).getContents() != null
                            && !""
                            .equals(dataSheet.getCell(6, i)
                                    .getContents()))
                        accessoryForm.setCptroomname(dataSheet.getCell(6, i)
                                .getContents().trim());
                    if (dataSheet.getCell(7, i).getContents() != null
                            && !""
                            .equals(dataSheet.getCell(7, i)
                                    .getContents()))
                        accessoryForm.setSex(dataSheet.getCell(7, i)
                                .getContents().trim());
                    if (dataSheet.getCell(8, i).getContents() != null
                            && !""
                            .equals(dataSheet.getCell(8, i)
                                    .getContents()))
                        accessoryForm.setPhotoInfo(dataSheet.getCell(8, i)
                                .getContents().trim());
                    if (dataSheet.getCell(9, i).getContents() != null
                            && !""
                            .equals(dataSheet.getCell(9, i)
                                    .getContents()))
                        accessoryForm.setBirthday(dataSheet.getCell(9, i)
                                .getContents().trim());
                    if (dataSheet.getCell(10, i).getContents() != null
                            && !"".equals(dataSheet.getCell(10, i)
                            .getContents()))
                        accessoryForm.setEducation(dataSheet.getCell(10, i)
                                .getContents().trim());
                    if (dataSheet.getCell(11, i).getContents() != null
                            && !"".equals(dataSheet.getCell(11, i)
                            .getContents()))
                        accessoryForm.setStartWorkTime(dataSheet.getCell(11, i)
                                .getContents().trim());
                    if (dataSheet.getCell(12, i).getContents() != null
                            && !"".equals(dataSheet.getCell(12, i)
                            .getContents()))
                        accessoryForm.setToDeptTime(dataSheet.getCell(12, i)
                                .getContents().trim());
                    if (dataSheet.getCell(13, i).getContents() != null
                            && !"".equals(dataSheet.getCell(13, i)
                            .getContents()))
                        accessoryForm.setCertificationInfo(dataSheet.getCell(
                                13, i).getContents().trim());
                    if (dataSheet.getCell(14, i).getContents() != null
                            && !"".equals(dataSheet.getCell(14, i)
                            .getContents()))
                        accessoryForm.setStaffLabel(dataSheet.getCell(14, i)
                                .getContents().trim());
                    if (dataSheet.getCell(15, i).getContents() != null
                            && !"".equals(dataSheet.getCell(15, i)
                            .getContents()))
                        accessoryForm.setFamilyaddress(dataSheet.getCell(15, i)
                                .getContents().trim());
                    if (dataSheet.getCell(16, i).getContents() != null
                            && !"".equals(dataSheet.getCell(16, i)
                            .getContents()))
                        accessoryForm.setPostState(dataSheet.getCell(16, i)
                                .getContents().trim());
                    if (dataSheet.getCell(17, i).getContents() != null
                            && !"".equals(dataSheet.getCell(17, i)
                            .getContents()))
                        accessoryForm.setMobile(dataSheet.getCell(17, i)
                                .getContents().trim());
                    if (dataSheet.getCell(18, i).getContents() != null
                            && !"".equals(dataSheet.getCell(18, i)
                            .getContents()))
                        accessoryForm.setEmail(dataSheet.getCell(18, i)
                                .getContents().trim());
                    if (dataSheet.getCell(19, i).getContents() != null
                            && !"".equals(dataSheet.getCell(19, i)
                            .getContents()))
                        accessoryForm.setRemark(dataSheet.getCell(19, i)
                                .getContents().trim());

                    accessoryList.add(accessoryForm);
                }

                // 保存该Form
                MessageResources mr = this.getResources(request);
                ITawSystemUserManager mgr = (ITawSystemUserManager) getBean("ItawSystemUserSaveManagerFlush");
                ITawPartnersUserManager pmgr = (ITawPartnersUserManager) getBean("itawPartnersUserManager");
                String isRight = "Y";

                for (int i = 0; i < accessoryList.size(); ++i) {
                    accessoryForm = (TawSystemUserForm) accessoryList.get(i);
                    // 验证用户id，字母开头，允许5-16字节，允许字母数字下划线
                    if (!mgr.checkUserId(accessoryForm.getUserid())) {
                        // String msg =
                        // mr.getMessage("user.infomation.useridfailure");
                        // JSONUtil.fail(response, msg);
                        isRight = "N";
                    }

                    // 密码至少大于6位并数字加字母(至少一位大写字母）组合
                    if (!mgr.checkPasswd(accessoryForm.getNewPassword())) {
                        // String msg =
                        // mr.getMessage("user.infomation.passwdfailure",
                        // UserMgrLocator.getUserAttributes()
                        // .getPasswdLength());
                        // JSONUtil.fail(response, msg);
                        isRight = "N";
                    }
                    // 若用户存在
                    if (mgr.isUserExist(accessoryForm.getUserid())) {
                        // String msg =
                        // mr.getMessage("user.infomation.useridExist");
                        // JSONUtil.fail(response, msg);
                        isRight = "N";
                    }
                }

                if (isRight.equals("Y")) {
                    for (int i = 0; i < accessoryList.size(); ++i) {
                        accessoryForm = (TawSystemUserForm) accessoryList
                                .get(i);
                        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                                .getSession().getAttribute("sessionform");
                        String operuserid = sessionform.getUserid();
                        accessoryForm.setOperuserid(operuserid);

                        TawSystemUser tawSystemUser = (TawSystemUser) convert(accessoryForm);
                        // md5加密
                        tawSystemUser.setPassword(new Md5PasswordEncoder()
                                .encodePassword(accessoryForm.getNewPassword(),
                                        new String()));
                        tawSystemUser.setDeleted("0");
                        tawSystemUser.setOperremotip(request.getRemoteAddr());
                        tawSystemUser.setSavetime(new Date());
                        tawSystemUser.setEnabled(true);
                        tawSystemUser.setAccountLocked(false);
                        tawSystemUser.setId("");
                        tawSystemUser
                                .setDeptname(TawSystemDeptBo.getInstance()
                                        .getDeptnameBydeptid(
                                                tawSystemUser.getDeptid()));

                        TawPartnersUser tawPartnersUser = new TawPartnersUser();

                        tawPartnersUser.setInCompany(accessoryForm
                                .getInCompany());
                        tawPartnersUser
                                .setBirthday(accessoryForm.getBirthday());
                        tawPartnersUser.setCertificationInfo(accessoryForm
                                .getCertificationInfo());
                        tawPartnersUser.setEducation(accessoryForm
                                .getEducation());
                        tawPartnersUser.setGraduateSchool(accessoryForm
                                .getGraduateSchool());
                        tawPartnersUser
                                .setIDNumber(accessoryForm.getIDNumber());
                        tawPartnersUser.setInCity(accessoryForm.getInCity());
                        tawPartnersUser.setInDistrict(accessoryForm
                                .getInDistrict());
                        tawPartnersUser.setIsMarried(accessoryForm
                                .getIsMarried());
                        tawPartnersUser.setJobType(accessoryForm.getJobType());
                        tawPartnersUser.setPersonDiscription(accessoryForm
                                .getPersonDiscription());
                        tawPartnersUser.setPhotoInfo(accessoryForm
                                .getPhotoInfo());
                        tawPartnersUser.setPoliticalStatus(accessoryForm
                                .getPoliticalStatus());
                        tawPartnersUser.setPost(accessoryForm.getPost());
                        tawPartnersUser.setPostState(accessoryForm
                                .getPostState());
                        tawPartnersUser.setSpecialties(accessoryForm
                                .getSpecialties());
                        tawPartnersUser.setStaffLabel(accessoryForm
                                .getStaffLabel());

                        tawPartnersUser.setStartWorkTime(accessoryForm
                                .getStartWorkTime());
                        tawPartnersUser.setToDeptTime(accessoryForm
                                .getToDeptTime());

                        tawPartnersUser.setUserid(tawSystemUser.getUserid());// 用这个字段与TawSystemDept表关联
                        pmgr.saveTawPartnersUser(tawPartnersUser);

                        mgr.saveTawSystemUser(tawSystemUser, accessoryForm
                                .getOlduserid());
                    }
                    workbook.close();
                    File fileDel = new File(filePath);
                    if (fileDel.exists())
                        fileDel.delete();
                    return mapping.findForward("success");
                } else {
                    workbook.close();
                    File fileDel = new File(filePath);
                    if (fileDel.exists())
                        fileDel.delete();
                    return mapping.findForward("failure");
                }
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
        } catch (Exception e) {
            e.printStackTrace();
            return mapping.findForward("failure");
        }
    }

    public ActionForward xmlOutPut(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            String url = request.getParameter("url");
            url = StaticMethod.getWebPath() + url;
            File file = new File(url);
            InputStream inStream = new FileInputStream(file);
            response.reset();
            response.setContentType("application/x-msdownload;charset=GBK");
            response.setCharacterEncoding("UTF-8");
            String fileName = URLEncoder.encode(file.getName(), "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("UTF-8"), "GBK"));
            byte b[] = new byte[128];
            int len;
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return mapping.findForward("failure");
        }
        return null;
    }

    public ActionForward showOnlineUserCounter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        int count = UserCounterListener.getOnlineUserCounter();
        String aaa = String.valueOf(count);
        response.setContentType("text/html; charset=GBK");
        try {
            response.getWriter().print(aaa);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ActionForward xquery(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String q = StaticMethod.null2String(request.getParameter("q"));
        IUserMgr mgr = (IUserMgr) getBean("UserMgrImpl");
        List list = mgr.listByNameQuery(q);
        request.setAttribute("list", list);
        return mapping.findForward("tpl-user-xtree");
    }
}
