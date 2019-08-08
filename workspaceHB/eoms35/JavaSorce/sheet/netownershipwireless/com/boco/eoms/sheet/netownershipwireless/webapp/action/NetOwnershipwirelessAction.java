package com.boco.eoms.sheet.netownershipwireless.webapp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessoriesConfig;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesConfigManager;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.netownershipwireless.model.NetOwnershipwireless;
import com.boco.eoms.sheet.netownershipwireless.model.NetOwnershipAreawireless;
import com.boco.eoms.sheet.netownershipwireless.service.INetOwnershipwirelessManager;
import com.boco.eoms.sheet.netownershipwireless.webapp.form.NetOwnershipwirelessForm;

/**
 * @author weichao
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NetOwnershipwirelessAction extends BaseAction {

    /**
     * 显示新增页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showInput(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("input");
    }

    /**
     * 显示新增页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showQueryPage(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("query");
    }

    /**
     * 保存信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward xsave(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        INetOwnershipwirelessManager mgr = (INetOwnershipwirelessManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipwirelessManager");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        NetOwnershipwirelessForm net = (NetOwnershipwirelessForm) form;
        NetOwnershipwireless common = (NetOwnershipwireless) convert(net);
        if ("".equals(common.getId()) || null == common.getId()) {
            common.setCreateTime(new Date());
            common.setDeleted(Integer.valueOf("0"));
            common.setCreateUserId(sessionform.getUserid());
            common.setCreateDeptId(sessionform.getDeptid());
        }
        mgr.saveOrUpdate(common);
        return mapping.findForward("success");
    }

    /**
     * 编辑信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward xedit(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        INetOwnershipwirelessManager mgr = (INetOwnershipwirelessManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipwirelessManager");
        ITawSystemDeptManager deptMgr = (ITawSystemDeptManager) ApplicationContextHolder.getInstance().getBean("ItawSystemDeptManager");
        String id = StaticMethod.nullObject2String(request.getParameter("id"));
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String deptid = sessionform.getDeptid();
        String userid = sessionform.getUserid();
        TawSystemDept dept = deptMgr.getDeptinfobydeptid(deptid, "0");
        NetOwnershipwireless net = mgr.getObjectById(id);
        if (!"".equals(dept.getId())) {
            String areaId = dept.getAreaid();//dept表里存储的一级地市的areaId
            String d = net.getEomsCityId();//EOMS地域表的一级地市的areaId
            if (null != d && null != areaId) {
                if (d.equals(areaId) || userid.equals("admin")) {
                    request.setAttribute("ifRight", "true");
                }
            }
        }
        request.setAttribute("main", net);
        return mapping.findForward("edit");
    }

    /**
     * 编辑信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward xeditandsave(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        INetOwnershipwirelessManager mgr = (INetOwnershipwirelessManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipwirelessManager");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String id = StaticMethod.nullObject2String(request.getParameter("id"));
        NetOwnershipwireless net = mgr.getObjectById(id);
        net.setCenterId(StaticMethod.nullObject2String(request.getParameter("centerId")));
        net.setTeamRoleId(StaticMethod.nullObject2String(request.getParameter("teamRoleId")));
        net.setCcObject(StaticMethod.nullObject2String(request.getParameter("ccObject")));
        net.setTtRoleId(StaticMethod.nullObject2String(request.getParameter("ttRoleId")));
        net.setIfAutotran(StaticMethod.nullObject2String(request.getParameter("ifAutotran")));
        net.setCreateTime(new Date());
        net.setCreateDeptId(sessionform.getDeptid());
        net.setCreateUserId(sessionform.getUserid());
        mgr.saveOrUpdate(net);
        return mapping.findForward("success");
    }

    /**
     * 删除信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void xdelete(ActionMapping mapping, ActionForm form,
                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        INetOwnershipwirelessManager mgr = (INetOwnershipwirelessManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipwirelessManager");
        ITawSystemDeptManager deptMgr = (ITawSystemDeptManager) ApplicationContextHolder.getInstance().getBean("ItawSystemDeptManager");
        String id = StaticMethod.nullObject2String(request.getParameter("id"));
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String deptid = sessionform.getDeptid();
        String userid = sessionform.getUserid();
        TawSystemDept dept = deptMgr.getDeptinfobydeptid(deptid, "0");
        NetOwnershipwireless net = mgr.getObjectById(id);
        JSONArray jsonRoot = new JSONArray();
        JSONObject jitem = new JSONObject();
        if (!"".equals(dept.getId())) {
            String areaId = dept.getAreaid();//dept表里存储的一级地市的areaId
            String d = net.getEomsCityId();//EOMS地域表的一级地市的areaId
            if (null != d && null != areaId) {
                if (d.equals(areaId) || userid.equals("admin")) {
                    net.setDeleted(Integer.valueOf("1"));
                    mgr.saveOrUpdate(net);
                    jitem.put("alarmMsg", "删除成功");
                    jsonRoot.put(jitem);
                } else {
                    jitem.put("alarmMsg", "你没有权限进行此操作");
                    jsonRoot.put(jitem);
                    System.out.println("所删除的网元地市Id和操作人的地市的areaId不符。。。");
                }
            } else {
                jitem.put("alarmMsg", "你没有权限进行此操作");
                jsonRoot.put(jitem);
                System.out.println("areaId或EomsCityId不能为空。。。");
            }
        } else {
            jitem.put("alarmMsg", "你没有权限进行此操作");
            jsonRoot.put(jitem);
            System.out.println("部门信息有误。。。");
        }
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(jsonRoot.toString());
    }

    /**
     * 批量删除
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void xdeleteSome(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        INetOwnershipwirelessManager mgr = (INetOwnershipwirelessManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipwirelessManager");
        String id = StaticMethod.nullObject2String(request.getParameter("ids"));
        String[] ids = id.split(",");
        List rightList = new ArrayList();
        List netList = new ArrayList();
        JSONArray jsonRoot = new JSONArray();
        JSONObject jitem = new JSONObject();
        //判断地域信息是否一致
        for (int i = 0; i < ids.length; i++) {
            String idd = ids[i];
            NetOwnershipwireless net = mgr.getObjectById(idd);
            netList.add(net);
            if (rightList.size() == 0) {
                rightList.add(net.getEomsCityId());
            }
            if (!rightList.contains(net.getEomsCityId())) {//此处的city完了要换成eomscityid
                rightList.add(net.getCity());
                jitem.put("alarmMsg", "请选择同一地市的网元进行批量删除");
                jsonRoot.put(jitem);
                break;
            }
        }
        //根据地域信息判断是否有权限进行此操作
        if (rightList.size() == 1) {
            TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
            ITawSystemDeptManager deptMgr = (ITawSystemDeptManager) ApplicationContextHolder.getInstance().getBean("ItawSystemDeptwirelessManager");
            String deptid = sessionform.getDeptid();
            String userid = sessionform.getUserid();
            TawSystemDept dept = deptMgr.getDeptinfobydeptid(deptid, "0");
            if (!"".equals(dept.getId())) {
                String areaId = dept.getAreaid();//dept表里存储的一级地市的areaId
                String d = (String) rightList.get(0);//EOMS地域表的一级地市的areaId
                if (null != d && null != areaId) {
                    if (d.equals(areaId) || userid.equals("admin")) {
                        for (int n = 0; n < netList.size(); n++) {
                            NetOwnershipwireless net = (NetOwnershipwireless) netList.get(n);
                            net.setDeleted(Integer.valueOf("1"));
                            mgr.saveOrUpdate(net);
                        }
                        jitem.put("alarmMsg", "删除成功");
                        jsonRoot.put(jitem);
                    } else {
                        jitem.put("alarmMsg", "你没有权限进行此操作");
                        jsonRoot.put(jitem);
                        System.out.println("所删除的网元地市Id和操作人的地市的areaId不符。。。");
                    }
                } else {
                    jitem.put("alarmMsg", "你没有权限进行此操作");
                    jsonRoot.put(jitem);
                    System.out.println("areaId或EomsCityId不能为空。。。");
                }
            } else {
                jitem.put("alarmMsg", "你没有权限进行此操作");
                jsonRoot.put(jitem);
                System.out.println("部门信息有误。。。");
            }
        }

        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(jsonRoot.toString());
    }


    /**
     * 显示网元类型
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void showNetType(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        INetOwnershipwirelessManager mgr = (INetOwnershipwirelessManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipwirelessManager");
        List list = mgr.getNetType();
        JSONArray jsonRoot = new JSONArray();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            JSONObject jitem = new JSONObject();
            String netType = (String) iter.next();
            jitem.put("netType", netType);
            jsonRoot.put(jitem);
        }
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(jsonRoot.toString());
    }


    /**
     * 批量修改
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void xeditSome(ActionMapping mapping, ActionForm form,
                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        INetOwnershipwirelessManager mgr = (INetOwnershipwirelessManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipwirelessManager");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String id = StaticMethod.nullObject2String(request.getParameter("ids"));
        String[] ids = id.split(",");
        String userid = sessionform.getUserid();
        String deptid = sessionform.getDeptid();
        List rightList = new ArrayList();
        List netList = new ArrayList();
        JSONArray jsonRoot = new JSONArray();
        JSONObject jitem = new JSONObject();
        //判断地域信息是否一致
        for (int i = 0; i < ids.length; i++) {
            String idd = ids[i];
            NetOwnershipwireless net = mgr.getObjectById(idd);
            netList.add(net);
            if (rightList.size() == 0) {
                rightList.add(net.getEomsCityId());
            }
            if (!rightList.contains(net.getEomsCityId())) {//此处的city完了要换成eomscityid
                rightList.add(net.getCity());
                jitem.put("alarmMsg", "请选择同一地市的网元进行批量删除");
                jsonRoot.put(jitem);
                break;
            }
        }
        //根据地域信息判断是否有权限进行此操作
        if (rightList.size() == 1) {
            ITawSystemDeptManager deptMgr = (ITawSystemDeptManager) ApplicationContextHolder.getInstance().getBean("ItawSystemDeptManager");
            TawSystemDept dept = deptMgr.getDeptinfobydeptid(deptid, "0");
            if (!"".equals(dept.getId())) {
                String areaId = dept.getAreaid();//dept表里存储的一级地市的areaId
                String d = (String) rightList.get(0);//EOMS地域表的一级地市的areaId
                if (null != d && null != areaId) {
                    if (d.equals(areaId) || userid.equals("admin")) {
                        String center = StaticMethod.nullObject2String(request.getParameter("center"));
                        String team = StaticMethod.nullObject2String(request.getParameter("team"));
                        String cc = StaticMethod.nullObject2String(request.getParameter("cc"));
                        String ifauto = StaticMethod.nullObject2String(request.getParameter("ifauto"));
                        String tieta = StaticMethod.nullObject2String(request.getParameter("tieta"));
                        System.out.println("The function xeditSome get tietaRoleId is " + tieta);
                        Date date = new Date();
                        for (int n = 0; n < netList.size(); n++) {
                            NetOwnershipwireless net = (NetOwnershipwireless) netList.get(n);
                            net.setCreateTime(date);
                            net.setCreateDeptId(deptid);
                            net.setCreateUserId(userid);
                            net.setCenterId(center);
                            net.setTeamRoleId(team);
                            net.setTtRoleId(tieta);
                            net.setCcObject(cc);
                            net.setIfAutotran(ifauto);
                            mgr.saveOrUpdate(net);
                        }
                        jitem.put("alarmMsg", "保存成功");
                        jsonRoot.put(jitem);
                    } else {
                        jitem.put("alarmMsg", "你没有权限进行此操作");
                        jsonRoot.put(jitem);
                        System.out.println("所编辑的网元地市Id和操作人的地市的areaId不符。。。");
                    }
                } else {
                    jitem.put("alarmMsg", "你没有权限进行此操作");
                    jsonRoot.put(jitem);
                    System.out.println("areaId或EomsCityId不能为空。。。");
                }
            } else {
                jitem.put("alarmMsg", "你没有权限进行此操作");
                jsonRoot.put(jitem);
                System.out.println("部门信息有误。。。");
            }
        }

        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(jsonRoot.toString());
    }


    /**
     * 执行查询方法
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     */

    public ActionForward performQuery(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response) {

        INetOwnershipwirelessManager mgr = (INetOwnershipwirelessManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipwirelessManager");
        Map actionMap = request.getParameterMap();
        // 获取每页显示条数
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
        System.out.println("pageSize------------------------->" + pageSize);
        String exportType = StaticMethod.null2String(request.getParameter(new org.displaytag.util.ParamEncoder("taskList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));
        String pageIndexName = new org.displaytag.util.ParamEncoder("taskList").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        System.out.println("exportType------------------------>" + exportType);
        if (!exportType.equals("")) {
            pageSize = new Integer(-1);
        }
        // 当前页数
        final Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        String[] aSql = {""};
        int[] aTotal = {0};

        String queryType = StaticMethod.nullObject2String(request.getParameter("queryType"));
        List result = null;
        List exportList = new ArrayList();
        ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("id2nameService");
        if (!(exportType.equals(""))) {
            result = mgr.getQueryResult(aSql, actionMap, new Integer(0), new Integer(pageSize.intValue()), aTotal, queryType);
            HashMap dictMap = new HashMap();
            for (int i = 0; i < result.size(); i++) {
                NetOwnershipwireless net = (NetOwnershipwireless) result.get(i);
                NetOwnershipwireless newOne = new NetOwnershipwireless();
                newOne.setNetId(net.getNetId());
                newOne.setNetNameByEdis(net.getNetNameByEdis());
                newOne.setCity(net.getCity());
                newOne.setCounty(net.getCounty());
                String createDeptId = StaticMethod.nullObject2String(net.getCreateDeptId());
                if (dictMap.get(createDeptId) != null) {
                    newOne.setCreateDeptId(StaticMethod.nullObject2String(dictMap.get(createDeptId)));
                } else {
                    String createDeptName = service.id2Name(createDeptId, "tawSystemDeptDao");
                    newOne.setCreateDeptId(createDeptName);
                    dictMap.put(createDeptId, createDeptName);
                }
                String createUserId = StaticMethod.nullObject2String(net.getCreateUserId());
                if (dictMap.get(createUserId) != null) {
                    newOne.setCreateUserId(StaticMethod.nullObject2String(dictMap.get(createUserId)));
                } else {
                    String createUserName = service.id2Name(createUserId, "tawSystemUserDao");
                    newOne.setCreateUserId(createUserName);
                    dictMap.put(createUserId, createUserName);
                }
                newOne.setNetId(net.getNetId());
                newOne.setNetName(net.getNetName());
                newOne.setNetType(net.getNetType());
                String teamRoleId = StaticMethod.nullObject2String(net.getTeamRoleId());
                if (dictMap.get(teamRoleId) != null) {
                    newOne.setTeamRoleId(StaticMethod.nullObject2String(dictMap.get(teamRoleId)));
                } else {
                    String teamRoleName = service.id2Name(teamRoleId, "tawSystemSubRoleDao");
                    newOne.setTeamRoleId(teamRoleName);
                    dictMap.put(teamRoleId, teamRoleName);
                }
                String copyRoleId = StaticMethod.nullObject2String(net.getCcObject());
                if (dictMap.get(copyRoleId) != null) {
                    newOne.setCcObject(StaticMethod.nullObject2String(dictMap.get(copyRoleId)));
                } else {
                    String copyRoleName = service.id2Name(copyRoleId, "tawSystemSubRoleDao");
                    newOne.setCcObject(copyRoleName);
                    dictMap.put(copyRoleId, copyRoleName);
                }

                String ttRoleId = StaticMethod.nullObject2String(net.getTtRoleId());
                if (dictMap.get(ttRoleId) != null) {
                    newOne.setTtRoleId(StaticMethod.nullObject2String(dictMap.get(ttRoleId)));
                } else {
                    String ttRoleName = service.id2Name(ttRoleId, "tawSystemSubRoleDao");
                    newOne.setTtRoleId(ttRoleName);
                    dictMap.put(ttRoleId, ttRoleName);
                }
                String ttType = StaticMethod.nullObject2String(net.getTttype());
                if (dictMap.get(ttType) != null) {
                    newOne.setTttype(StaticMethod.nullObject2String(dictMap.get(ttType)));
                } else {
                    String ttTypeName = service.id2Name(ttType, "ItawSystemDictTypeDao");
                    newOne.setTttype(ttTypeName);
                    dictMap.put(ttType, ttTypeName);
                }
                newOne.setZhuanye("无线");
                newOne.setWorkstatprop(net.getWorkstatprop());
                newOne.setClasses(net.getClasses());
                newOne.setIfgj(net.getIfgj());
                newOne.setIfgs(net.getIfgs());
                newOne.setIfmz(net.getIfmz());
                newOne.setSaveTime(net.getSaveTime());
                newOne.setCreateTime(net.getCreateTime());
                newOne.setIfAutotran(net.getIfAutotran());
                exportList.add(newOne);
            }
            request.setAttribute("taskList", exportList);
        } else {
            result = mgr.getQueryResult(aSql, actionMap, pageIndex,
                    new Integer(pageSize.intValue()), aTotal, queryType);
            request.setAttribute("taskList", result);
        }
        Integer total = new Integer(aTotal[0]);
        if (queryType != null && queryType.equals("number")) {
            request.setAttribute("recordTotal", total);
        }

        request.setAttribute("total", total);

        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("querylist");
    }


    /**
     * 显示批量导入
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showImporPage(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("excel");
    }

    /**
     * 批量导入
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void importExcel(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String exceptionStr = "";
        JSONArray jsonRoot = new JSONArray();
        JSONObject jitem = new JSONObject();
        try {
            ITawSystemSubRoleManager subRoleMgr = (ITawSystemSubRoleManager) ApplicationContextHolder.getInstance().getBean("ItawSystemSubRoleManager");
            INetOwnershipwirelessManager mgr = (INetOwnershipwirelessManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipwirelessManager");
            ITawCommonsAccessoriesManager accessoriesManager = (ITawCommonsAccessoriesManager) ApplicationContextHolder.getInstance().getBean("ItawCommonsAccessoriesManager");
            String accessoriesname = StaticMethod.nullObject2String(request.getParameter("accessoriesname"));
            List accessoriesList = accessoriesManager.getAllFileById(accessoriesname);
            if (accessoriesList.size() > 0 && accessoriesname != null && !("".equals(accessoriesname))) {
                // 获取模板对象
                TawCommonsAccessories accessories = (TawCommonsAccessories) accessoriesList.get(0);
                // 系统存附件路径
                String accPath = StaticMethod.nullObject2String(XmlManage.getFile("/config/netownershipwireless-util.xml").getProperty("rootPath"));
                // 读取模板
                if (null == accPath || "".equals(accPath)) {
                    exceptionStr = "附件上传路径设置错误！";
                    jitem.put("alarmMsg", exceptionStr);
                    jsonRoot.put(jitem);
                    JSONUtil.print(response, jsonRoot.toString());
                }
                FileInputStream finput = new FileInputStream(accPath + accessories.getAccessoriesPath() + "/"
                        + accessoriesname.substring(1, accessoriesname.length() - 1));
                POIFSFileSystem fs = new POIFSFileSystem(finput);
                HSSFWorkbook wb = new HSSFWorkbook(fs);
                HSSFSheet sheet = wb.getSheetAt(0);
                finput.close();
                // 读取模板结束
                System.out.println("sheet.getLastRowNum():::::::::::::::" + sheet.getLastRowNum());
                //最大的Excel行数
                String maxNeNum = StaticMethod.nullObject2String(XmlManage.getFile("/config/netownershipwireless-util.xml").getProperty("maxNetNum"));
                //累计错误最多行数
                String maxWrongNum = StaticMethod.nullObject2String(XmlManage.getFile("/config/netownershipwireless-util.xml").getProperty("maxWrongNum"));
                int maxWrongX = Integer.valueOf(maxWrongNum).intValue();
                int maxNowX = 0;
                int maxNeInt = Integer.valueOf(maxNeNum).intValue();
                if (maxNeInt < 10) {
                    maxNeInt = 999;
                }


                if (sheet.getLastRowNum() < 1) {
                    exceptionStr = "模板中没有数据！请核查！";
                    jitem.put("alarmMsg", exceptionStr);
                    jsonRoot.put(jitem);
                    JSONUtil.print(response, jsonRoot.toString());
                } else if (sheet.getLastRowNum() > maxNeInt) {
                    exceptionStr = "为了保证系统运行效率！一次导入的网元数请勿大于" + maxNeInt + "！";
                    jitem.put("alarmMsg", exceptionStr);
                    jsonRoot.put(jitem);
                    JSONUtil.print(response, jsonRoot.toString());
                } else {
                    int maxrow = sheet.getPhysicalNumberOfRows();
                    int col = 16;
                    List list = new ArrayList();
                    List wlist = new ArrayList();
                    String tip = "";
                    for (int i = 1; i < maxrow; i++) {
                        HSSFRow readRow = sheet.getRow(i);
                        Map map = new HashMap();
                        for (int j = 0; j < col; j++) {
                            exceptionStr = "";
                            HSSFCell readCell = readRow.getCell((short) j);
                            int a = i + 1;
                            int b = j + 1;
                            if (j == 0) {// 第一列网元Id
                                String str = "";
                                if (readCell != null) {
                                    if (HSSFCell.CELL_TYPE_BLANK == readRow.getCell((short) j).getCellType()) {
                                        str = "";
//										if(maxNowX == maxWrongX){
//											tip =  "你上传的列表第" + a + "行第" + b+ "列出现问题。请检查！";										
//											break;
//										}else{
//											tip =  "你上传的列表第" + a + "行第" + b+ "列出现问题。请检查！";	
//											maxNowX++;
//										}
                                    } else if (HSSFCell.CELL_TYPE_NUMERIC == readRow.getCell((short) j).getCellType()) {
                                        str = String.valueOf(((long) readCell.getNumericCellValue()));
                                    } else {
                                        str = readCell.toString();
                                    }
                                    map.put("netId", str);
                                } else {
                                    if (maxNowX == maxWrongX) {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        break;
                                    } else {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        maxNowX++;
                                    }
                                }
                                if (!"".equals(tip)) {
                                    wlist.add(tip);
                                }
                            }
                            if (j == 1) {// 第二列网元Name
                                String str = "";
                                if (readCell != null) {
                                    if (HSSFCell.CELL_TYPE_BLANK == readRow.getCell((short) j).getCellType()) {
                                        str = "";
                                        if (maxNowX == maxWrongX) {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            break;
                                        } else {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            maxNowX++;
                                        }
                                    } else if (HSSFCell.CELL_TYPE_NUMERIC == readRow.getCell((short) j).getCellType()) {
                                        str = String.valueOf(((long) readCell.getNumericCellValue()));
                                    } else {
                                        str = readCell.toString();
                                    }
                                    map.put("netName", str);
                                } else {
                                    if (maxNowX == maxWrongX) {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        break;
                                    } else {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        maxNowX++;
                                    }
                                }
                                if (!"".equals(tip)) {
                                    wlist.add(tip);
                                }
                            }
                            if (j == 2) {// 第三列综资网元Name
                                String str = "";
                                if (readCell != null) {
                                    if (HSSFCell.CELL_TYPE_BLANK == readRow.getCell((short) j).getCellType()) {
                                        str = "";
//										if(maxNowX == maxWrongX){
//											tip =  "你上传的列表第" + a + "行第" + b+ "列出现问题。请检查！";										
//											break;
//										}else{
//											tip =  "你上传的列表第" + a + "行第" + b+ "列出现问题。请检查！";	
//											maxNowX++;
//										}
                                    } else if (HSSFCell.CELL_TYPE_NUMERIC == readRow.getCell((short) j).getCellType()) {
                                        str = String.valueOf(((long) readCell.getNumericCellValue()));
                                    } else {
                                        str = readCell.toString();
                                    }
                                    map.put("netNameByEdis", str);
                                } else {
                                    if (maxNowX == maxWrongX) {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        break;
                                    } else {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        maxNowX++;
                                    }
                                }
                                if (!"".equals(tip)) {
                                    wlist.add(tip);
                                }
                            }
                            if (j == 3) {// 第四列网元类型
                                String str = "";
                                if (readCell != null) {
                                    if (HSSFCell.CELL_TYPE_BLANK == readRow.getCell((short) j).getCellType()) {
                                        str = "";
                                        if (maxNowX == maxWrongX) {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            break;
                                        } else {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            maxNowX++;
                                        }
                                    } else if (HSSFCell.CELL_TYPE_NUMERIC == readRow.getCell((short) j).getCellType()) {
                                        str = String.valueOf(((long) readCell.getNumericCellValue()));
                                    } else {
                                        str = readCell.toString();
                                    }
                                    map.put("netType", str);
                                } else {
                                    if (maxNowX == maxWrongX) {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        break;
                                    } else {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        maxNowX++;
                                    }
                                }
                                if (!"".equals(tip)) {
                                    wlist.add(tip);
                                }

                            }
                            if (j == 4) {// 第五列地市
                                String str = "";
                                if (readCell != null) {
                                    if (HSSFCell.CELL_TYPE_BLANK == readRow.getCell((short) j).getCellType()) {
                                        str = "";
                                        if (maxNowX == maxWrongX) {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            break;
                                        } else {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            maxNowX++;
                                        }
                                    } else if (HSSFCell.CELL_TYPE_NUMERIC == readRow.getCell((short) j).getCellType()) {
                                        str = String.valueOf(((long) readCell.getNumericCellValue()));
                                    } else {
                                        str = readCell.toString();
                                    }
                                    map.put("city", str);
                                } else {
                                    if (maxNowX == maxWrongX) {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        break;
                                    } else {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        maxNowX++;
                                    }
                                }
                                if (!"".equals(tip)) {
                                    wlist.add(tip);
                                }

                            }
                            if (j == 5) {// 第六列区县
                                String str = "";
                                if (readCell != null) {
                                    if (HSSFCell.CELL_TYPE_BLANK == readRow.getCell((short) j).getCellType()) {
                                        str = "";
                                        if (maxNowX == maxWrongX) {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            break;
                                        } else {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            maxNowX++;
                                        }
                                    } else if (HSSFCell.CELL_TYPE_NUMERIC == readRow.getCell((short) j).getCellType()) {
                                        str = String.valueOf(((long) readCell.getNumericCellValue()));
                                    } else {
                                        str = readCell.toString();
                                    }
                                    map.put("county", str);
                                } else {
                                    if (maxNowX == maxWrongX) {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        break;
                                    } else {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        maxNowX++;
                                    }
                                }
                                if (!"".equals(tip)) {
                                    wlist.add(tip);
                                }

                            }
                            if (j == 6) {// 第七列维护班组
                                String str = "";
                                if (readCell != null) {
                                    if (HSSFCell.CELL_TYPE_BLANK == readRow.getCell((short) j).getCellType()) {
                                        str = "";
                                        if (maxNowX == maxWrongX) {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            break;
                                        } else {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            maxNowX++;
                                        }
                                    } else if (HSSFCell.CELL_TYPE_NUMERIC == readRow.getCell((short) j).getCellType()) {
                                        str = String.valueOf(((long) readCell.getNumericCellValue()));
                                    } else {
                                        str = readCell.toString();
                                    }
                                    map.put("teamId", str);
                                } else {
                                    if (maxNowX == maxWrongX) {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        break;
                                    } else {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        maxNowX++;
                                    }
                                }
                                if (!"".equals(tip)) {
                                    wlist.add(tip);
                                }

                            }
                            if (j == 7) {// 第八列抄送对象
                                String str = "";
                                if (readCell != null) {
                                    if (HSSFCell.CELL_TYPE_BLANK == readRow.getCell((short) j).getCellType()) {
                                        str = "";
                                        if (maxNowX == maxWrongX) {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            break;
                                        } else {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            maxNowX++;
                                        }
                                    } else if (HSSFCell.CELL_TYPE_NUMERIC == readRow.getCell((short) j).getCellType()) {
                                        str = String.valueOf(((long) readCell.getNumericCellValue()));
                                    } else {
                                        str = readCell.toString();
                                    }
                                    map.put("ccObject", str);
                                } else {
                                    if (maxNowX == maxWrongX) {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        break;
                                    } else {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        maxNowX++;
                                    }
                                }
                                if (!"".equals(tip)) {
                                    wlist.add(tip);
                                }

                            }
//add by fengmin
                            if (j == 8) {// 第九列铁塔班组
                                String str = "";
                                if (readCell != null) {
                                    if (HSSFCell.CELL_TYPE_BLANK == readRow.getCell((short) j).getCellType()) {
                                        str = "";
                                        if (maxNowX == maxWrongX) {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            break;
                                        } else {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            maxNowX++;
                                        }
                                    } else if (HSSFCell.CELL_TYPE_NUMERIC == readRow.getCell((short) j).getCellType()) {
                                        str = String.valueOf(((long) readCell.getNumericCellValue()));
                                    } else {
                                        str = readCell.toString();
                                    }
                                    map.put("ttRoleId", str);
                                    System.out.println("===============The str===========" + str);
                                } else {
                                    if (maxNowX == maxWrongX) {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        break;
                                    } else {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        maxNowX++;
                                    }
                                }
                                if (!"".equals(tip)) {
                                    wlist.add(tip);
                                }

                            }
                            if (j == 9) {// 第10列专业
                                String str = "";
                                if (readCell != null) {
                                    if (HSSFCell.CELL_TYPE_BLANK == readRow.getCell((short) j).getCellType()) {
                                        str = "";
                                        if (maxNowX == maxWrongX) {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            break;
                                        } else {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            maxNowX++;
                                        }
                                    } else if (HSSFCell.CELL_TYPE_NUMERIC == readRow.getCell((short) j).getCellType()) {
                                        str = String.valueOf(((long) readCell.getNumericCellValue()));
                                    } else {
                                        str = readCell.toString();
                                    }
                                    map.put("zhuanye", str);
                                } else {
                                    if (maxNowX == maxWrongX) {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        break;
                                    } else {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        maxNowX++;
                                    }
                                }
                                if (!"".equals(tip)) {
                                    wlist.add(tip);
                                }

                            }
                            if (j == 10) {// 第11列代维工作站属性
                                String str = "";
                                if (readCell != null) {
                                    if (HSSFCell.CELL_TYPE_BLANK == readRow.getCell((short) j).getCellType()) {
                                        str = "";
                                        if (maxNowX == maxWrongX) {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            break;
                                        } else {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            maxNowX++;
                                        }
                                    } else if (HSSFCell.CELL_TYPE_NUMERIC == readRow.getCell((short) j).getCellType()) {
                                        str = String.valueOf(((long) readCell.getNumericCellValue()));
                                    } else {
                                        str = readCell.toString();
                                    }
                                    map.put("workstatprop", str);
                                } else {
                                    if (maxNowX == maxWrongX) {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        break;
                                    } else {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        maxNowX++;
                                    }
                                }
                                if (!"".equals(tip)) {
                                    wlist.add(tip);
                                }

                            }
                            if (j == 11) {// 第12列铁塔属性
                                String str = "";
                                if (readCell != null) {
                                    if (HSSFCell.CELL_TYPE_BLANK == readRow.getCell((short) j).getCellType()) {
                                        str = "";
                                        if (maxNowX == maxWrongX) {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            break;
                                        } else {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            maxNowX++;
                                        }
                                    } else if (HSSFCell.CELL_TYPE_NUMERIC == readRow.getCell((short) j).getCellType()) {
                                        str = String.valueOf(((long) readCell.getNumericCellValue()));
                                    } else {
                                        str = readCell.toString();
                                    }
                                    map.put("ttType", str);
                                } else {
                                    if (maxNowX == maxWrongX) {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        break;
                                    } else {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        maxNowX++;
                                    }
                                }
                                if (!"".equals(tip)) {
                                    wlist.add(tip);
                                }

                            }
                            if (j == 12) {// 第13列类别属性
                                String str = "";
                                if (readCell != null) {
                                    if (HSSFCell.CELL_TYPE_BLANK == readRow.getCell((short) j).getCellType()) {
                                        str = "";
                                        if (maxNowX == maxWrongX) {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            break;
                                        } else {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            maxNowX++;
                                        }
                                    } else if (HSSFCell.CELL_TYPE_NUMERIC == readRow.getCell((short) j).getCellType()) {
                                        str = String.valueOf(((long) readCell.getNumericCellValue()));
                                    } else {
                                        str = readCell.toString();
                                    }
                                    map.put("classes", str);
                                } else {
                                    if (maxNowX == maxWrongX) {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        break;
                                    } else {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        maxNowX++;
                                    }
                                }
                                if (!"".equals(tip)) {
                                    wlist.add(tip);
                                }

                            }
                            if (j == 13) {// 第14列是否高山站
                                String str = "";
                                if (readCell != null) {
                                    if (HSSFCell.CELL_TYPE_BLANK == readRow.getCell((short) j).getCellType()) {
                                        str = "";
                                        if (maxNowX == maxWrongX) {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            break;
                                        } else {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            maxNowX++;
                                        }
                                    } else if (HSSFCell.CELL_TYPE_NUMERIC == readRow.getCell((short) j).getCellType()) {
                                        str = String.valueOf(((long) readCell.getNumericCellValue()));
                                    } else {
                                        str = readCell.toString();
                                    }
                                    map.put("ifgs", str);
                                } else {
                                    if (maxNowX == maxWrongX) {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        break;
                                    } else {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        maxNowX++;
                                    }
                                }
                                if (!"".equals(tip)) {
                                    wlist.add(tip);
                                }

                            }
                            if (j == 14) {// 第15列是否免责站
                                String str = "";
                                if (readCell != null) {
                                    if (HSSFCell.CELL_TYPE_BLANK == readRow.getCell((short) j).getCellType()) {
                                        str = "";
                                        if (maxNowX == maxWrongX) {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            break;
                                        } else {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            maxNowX++;
                                        }
                                    } else if (HSSFCell.CELL_TYPE_NUMERIC == readRow.getCell((short) j).getCellType()) {
                                        str = String.valueOf(((long) readCell.getNumericCellValue()));
                                    } else {
                                        str = readCell.toString();
                                    }
                                    map.put("ifmz", str);
                                } else {
                                    if (maxNowX == maxWrongX) {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        break;
                                    } else {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        maxNowX++;
                                    }
                                }
                                if (!"".equals(tip)) {
                                    wlist.add(tip);
                                }

                            }
                            if (j == 15) {// 第16列是否高等级站
                                String str = "";
                                if (readCell != null) {
                                    if (HSSFCell.CELL_TYPE_BLANK == readRow.getCell((short) j).getCellType()) {
                                        str = "";
                                        if (maxNowX == maxWrongX) {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            break;
                                        } else {
                                            tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                            maxNowX++;
                                        }
                                    } else if (HSSFCell.CELL_TYPE_NUMERIC == readRow.getCell((short) j).getCellType()) {
                                        str = String.valueOf(((long) readCell.getNumericCellValue()));
                                    } else {
                                        str = readCell.toString();
                                    }
                                    map.put("ifgj", str);
                                } else {
                                    if (maxNowX == maxWrongX) {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        break;
                                    } else {
                                        tip = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
                                        maxNowX++;
                                    }
                                }
                                if (!"".equals(tip)) {
                                    wlist.add(tip);
                                }

                            }
                            //add by fengmin end
                            if (j == 15) {
                                if (readRow.getCell((short) j) != null) {
                                    if (maxNowX == maxWrongX) {
                                        tip = "你上传的模板错误，大于" + j + "列。请检查！";
                                        if (!"".equals(tip)) {
                                            wlist.add(tip);
                                        }
                                        break;
                                    }
                                }
                            }

                        }
                        list.add(map);

                    }

                    if (wlist.size() > 10) {
                        String exp = "";
                        for (int i = 0; i < wlist.size(); i++) {
                            exp += wlist.get(i);
                        }
                        jitem.put("alarmMsg", exp);
                        jsonRoot.put(jitem);
                        JSONUtil.print(response, jsonRoot.toString());
                    } else if (list.size() == 0) {
                        exceptionStr = "你上传的列表为空或者列表格式不对。请检查！";
                        jitem.put("alarmMsg", exceptionStr);
                        jsonRoot.put(jitem);
                        JSONUtil.print(response, jsonRoot.toString());
                    } else {


                        List county = new ArrayList();
                        Map countyMap = new HashMap();
                        List n1 = new ArrayList();
                        ITawSystemDictTypeManager dictManager = (ITawSystemDictTypeManager) ApplicationContextHolder.getInstance().getBean("ItawSystemDictTypeManager");
                        TawSystemDictType tawSystemDictType = new TawSystemDictType();
                        //抄送对象
                        HashMap c1 = new HashMap();
                        List c2 = new ArrayList();
                        //维护班组
                        HashMap t1 = new HashMap();
                        List t2 = new ArrayList();

                        //铁塔班组
                        HashMap t3 = new HashMap();
                        List t4 = new ArrayList();
                        for (int i = 0; i < list.size(); i++) {
                            Map amap = (Map) list.get(i);
                            NetOwnershipwireless netOwnershipwireless = null;
                            String netIds = StaticMethod.nullObject2String(amap.get("netName"));
//							检查网元Id和名称是否重复					
                            if (n1.contains(netIds)) {
                                System.out.println("重复的网元名称" + netIds);
                                if (maxNowX == maxWrongX) {
                                    tip = "Excel中网元名称为:" + netIds + ",已经在Excel中存在;";
                                    break;
                                } else {
                                    tip = "Excel中网元名称为:" + netIds + ",已经在Excel中存在;";
                                    maxNowX++;
                                }
                                if (!"".equals(tip)) {
                                    wlist.add(tip);
                                }
                            } else {
                                netOwnershipwireless = mgr.getNetOwnershipByNetName(netIds);
                                //false为不存在，true为存在
                                if (netOwnershipwireless == null) {
                                    netOwnershipwireless = new NetOwnershipwireless();
                                }
                                n1.add(netIds);
                            }
                            TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
                            netOwnershipwireless.setCreateUserId(sessionform.getUserid());
                            netOwnershipwireless.setCreateDeptId(sessionform.getDeptid());
                            netOwnershipwireless.setCreateTime(new Date());
                            netOwnershipwireless.setDeleted(Integer.valueOf("0"));
                            netOwnershipwireless.setSaveTime(new Date());
                            String copyRoleNames = StaticMethod.nullObject2String(amap.get("ccObject"));
                            String teamRoleNames = StaticMethod.nullObject2String(amap.get("teamId"));
                            String ttRoleIdNames = StaticMethod.nullObject2String(amap.get("ttRoleId"));//铁塔班组
                            String countys = StaticMethod.nullObject2String(amap.get("county"));
                            String citys = StaticMethod.nullObject2String(amap.get("city"));

                            String netNames = StaticMethod.nullObject2String(amap.get("netName"));
                            String netTypes = StaticMethod.nullObject2String(amap.get("netType"));
                            String netNameByEdis = StaticMethod.nullObject2String(amap.get("netNameByEdis"));
                            String ttTypes = StaticMethod.nullObject2String(amap.get("ttType"));//铁塔属性
                            tawSystemDictType = dictManager.getDictByDictName(ttTypes, "1010319");
                            if (tawSystemDictType.getDictId() != null) {
                                netOwnershipwireless.setTttype(StaticMethod.null2String(tawSystemDictType.getDictId()));
                            } else {
                                System.out.println("铁塔属性不存在-->" + ttTypes);
                                if (maxNowX == maxWrongX) {
                                    tip = "Excel中的铁塔属性为:" + ttTypes + ",铁塔属性不存在;";
                                    break;
                                } else {
                                    tip = "Excel中的铁塔属性为:" + ttTypes + ",铁塔属性不存在;";
                                    maxNowX++;
                                }
                                if (!"".equals(tip)) {
                                    wlist.add(tip);
                                }
                            }
                            String zhuanye = StaticMethod.nullObject2String(amap.get("zhuanye"));
                            String workstatprop = StaticMethod.nullObject2String(amap.get("workstatprop"));
                            String ifgj = StaticMethod.nullObject2String(amap.get("ifgj"));
                            if (!ifgj.equals("是") && !ifgj.equals("否")) {
                                System.out.println("是否高等级站值为:" + ifgj + ",填写错误;");
                                if (maxNowX == maxWrongX) {
                                    tip = "Excel中的是否高等级站值为:" + ifgj + ",填写错误;";
                                    break;
                                } else {
                                    tip = "Excel中的是否高等级站值为:" + ifgj + ",填写错误;";
                                    maxNowX++;
                                }
                                if (!"".equals(tip)) {
                                    wlist.add(tip);
                                }
                            } else {
                                netOwnershipwireless.setIfgj(ifgj);
                            }
                            String ifgs = StaticMethod.nullObject2String(amap.get("ifgs"));
                            if (!ifgs.equals("是") && !ifgs.equals("否")) {
                                System.out.println("是否高山站值为:" + ifgs + ",填写错误;");
                                if (maxNowX == maxWrongX) {
                                    tip = "Excel中的是否高山站值为:" + ifgs + ",填写错误;";
                                    break;
                                } else {
                                    tip = "Excel中的是否高山站值为:" + ifgs + ",填写错误;";
                                    maxNowX++;
                                }
                                if (!"".equals(tip)) {
                                    wlist.add(tip);
                                }
                            } else {
                                netOwnershipwireless.setIfgs(ifgs);
                            }
                            String ifmz = StaticMethod.nullObject2String(amap.get("ifmz"));
                            if (!ifmz.equals("是") && !ifmz.equals("否")) {
                                System.out.println("是否免责站值为:" + ifmz + ",填写错误;");
                                if (maxNowX == maxWrongX) {
                                    tip = "Excel中的是否免责站值为:" + ifmz + ",填写错误;";
                                    break;
                                } else {
                                    tip = "Excel中的是否免责站值为:" + ifmz + ",填写错误;";
                                    maxNowX++;
                                }
                                if (!"".equals(tip)) {
                                    wlist.add(tip);
                                }
                            } else {
                                netOwnershipwireless.setIfmz(ifmz);
                            }

                            String classes = StaticMethod.nullObject2String(amap.get("classes"));
                            netOwnershipwireless.setZhuanye(zhuanye);
                            netOwnershipwireless.setWorkstatprop(workstatprop);
                            netOwnershipwireless.setClasses(classes);//类别属性
                            netOwnershipwireless.setCounty(countys);
                            netOwnershipwireless.setCity(citys);
                            netOwnershipwireless.setNetId(netIds);
                            netOwnershipwireless.setNetName(netNames);//网元名
                            netOwnershipwireless.setNetType(netTypes);//网元类型
                            netOwnershipwireless.setIfAutotran("1");//默认为不自动移交
                            netOwnershipwireless.setNetNameByEdis(netNameByEdis);
                            //判断地市信息是否正确
                            if (county.contains(countys)) {
                                NetOwnershipAreawireless area = (NetOwnershipAreawireless) countyMap.get(countys);
                                netOwnershipwireless.setCityId(area.getCityId());
                                netOwnershipwireless.setCountyId(area.getCountyId());
                                netOwnershipwireless.setEomsCity(area.getEomsCity());
                                netOwnershipwireless.setEomsCityId(area.getEomsCityId());
                                netOwnershipwireless.setEomsCounty(area.getEomsCounty());
                                netOwnershipwireless.setEomsCountyId(area.getEomsCountyId());
                            } else {
                                if (null != countys || !"".equals(countys)) {//判断countys是否为空
                                    System.out.println("The countys is " + countys);
                                    List l = mgr.getAreabycountyId(" and county='" + countys + "'");//根据名字去表里查询对应的地域信息
                                    if (l.size() > 0) {
                                        NetOwnershipAreawireless areas = (NetOwnershipAreawireless) l.get(0);
                                        county.add(countys);
                                        countyMap.put(countys, areas);
                                        netOwnershipwireless.setCityId(areas.getCityId());
                                        netOwnershipwireless.setCountyId(areas.getCountyId());
                                        netOwnershipwireless.setEomsCity(areas.getEomsCity());
                                        netOwnershipwireless.setEomsCityId(areas.getEomsCityId());
                                        netOwnershipwireless.setEomsCounty(areas.getEomsCounty());
                                        netOwnershipwireless.setEomsCountyId(areas.getEomsCountyId());
                                    } else {
                                        System.out.println("区县信息不存在-->" + countys);
                                        if (maxNowX == maxWrongX) {
                                            tip = "Excel中网元Id为:" + netIds + ",区县信息不存在;";
                                            break;
                                        } else {
                                            tip = "Excel中网元Id为:" + netIds + ",区县信息不存在;";
                                            maxNowX++;
                                        }
                                        if (!"".equals(tip)) {
                                            wlist.add(tip);
                                        }
                                    }

                                } else if (null != citys || !"".equals(citys)) {//地市是否为空
                                    List l = mgr.getAreabycountyId(" and city='" + citys + "'");//根据名字去表里查询对应的地域信息
                                    if (l.size() > 0) {
                                        NetOwnershipAreawireless areas = (NetOwnershipAreawireless) l.get(0);
                                        county.add(countys);
                                        countyMap.put(countys, areas);
                                        netOwnershipwireless.setCityId(areas.getCityId());
                                        netOwnershipwireless.setCounty(areas.getCity());
                                        netOwnershipwireless.setCountyId(areas.getCityId());
                                        netOwnershipwireless.setEomsCity(areas.getEomsCity());
                                        netOwnershipwireless.setEomsCityId(areas.getEomsCityId());
                                        netOwnershipwireless.setEomsCounty(areas.getEomsCity());
                                        netOwnershipwireless.setEomsCountyId(areas.getEomsCityId());
                                    } else {
                                        System.out.println("地市信息不存在-->" + citys);
                                        if (maxNowX == maxWrongX) {
                                            tip = "Excel中网元Id为:" + netIds + ",地市信息不存在;";
                                            break;
                                        } else {
                                            tip = "Excel中网元Id为:" + netIds + ",地市信息不存在;";
                                            maxNowX++;
                                        }
                                        if (!"".equals(tip)) {
                                            wlist.add(tip);
                                        }
                                    }

                                }
                            }
                            //抄送是否存在
                            if (c1.containsKey(copyRoleNames)) {
                                System.out.println("正确的抄送对象subRoleName：" + copyRoleNames);
                                netOwnershipwireless.setCcObject(StaticMethod.nullObject2String(c1.get(copyRoleNames)));
                            } else if (c2.contains(netIds)) {
                                System.out.println("不存在的抄送对象subRoleName：" + copyRoleNames);
                                if (maxNowX == maxWrongX) {
                                    tip = "Excel中网元Id为:" + netIds + ",抄送对象不存在;";
                                    break;
                                } else {
                                    tip = "Excel中网元Id为:" + netIds + ",抄送对象不存在;";
                                    maxNowX++;
                                }
                                if (!"".equals(tip)) {
                                    wlist.add(tip);
                                }
                            } else {
                                String copyRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/netownershipwireless-util.xml").getProperty("copyRoleId"));
                                String whereStr = " roleId='" + copyRoleId + "' and subRoleName='" + copyRoleNames + "'";
                                List subRoleIdList = subRoleMgr.getTawSystemSubRoles(whereStr);
                                if (subRoleIdList != null && subRoleIdList.size() > 0) {
                                    TawSystemSubRole copySubRole = (TawSystemSubRole) subRoleIdList.get(0);
                                    String subRoleId = StaticMethod.nullObject2String(copySubRole.getId());
                                    c1.put(copyRoleNames, subRoleId);
                                    netOwnershipwireless.setCcObject(subRoleId);
                                } else {
                                    c2.add(copyRoleNames);
                                    if (maxNowX == maxWrongX) {
                                        tip = "Excel中网元Id为:" + netIds + ",抄送对象不存在;";
                                        break;
                                    } else {
                                        tip = "Excel中网元Id为:" + netIds + ",抄送对象不存在;";
                                        maxNowX++;
                                    }
                                    if (!"".equals(tip)) {
                                        wlist.add(tip);
                                    }
                                }
                            }
                            //维护班组是否存在
                            if (t1.containsKey(teamRoleNames)) {
                                System.out.println("正确的维护班组subRoleName：" + teamRoleNames);
                                netOwnershipwireless.setTeamRoleId(StaticMethod.nullObject2String(t1.get(teamRoleNames)));
                            } else if (t2.contains(netIds)) {
                                System.out.println("不存在的维护班组subRoleName：" + teamRoleNames);
                                if (maxNowX == maxWrongX) {
                                    tip = "Excel中网元Id为:" + netIds + ",维护班组不存在;";
                                    break;
                                } else {
                                    tip = "Excel中网元Id为:" + netIds + ",维护班组不存在;";
                                    maxNowX++;
                                }
                                if (!"".equals(tip)) {
                                    wlist.add(tip);
                                }
                            } else {
                                String teamRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/netownershipwireless-util.xml").getProperty("teamRoleId"));
                                String whereStr = " roleId='" + teamRoleId + "' and subRoleName='" + teamRoleNames + "'";
                                List subRoleIdList = subRoleMgr.getTawSystemSubRoles(whereStr);
                                if (subRoleIdList != null && subRoleIdList.size() > 0) {
                                    TawSystemSubRole copySubRole = (TawSystemSubRole) subRoleIdList.get(0);
                                    String subRoleId = StaticMethod.nullObject2String(copySubRole.getId());
                                    t1.put(teamRoleNames, subRoleId);
                                    netOwnershipwireless.setTeamRoleId(subRoleId);
                                } else {
                                    t2.add(teamRoleNames);
                                    if (maxNowX == maxWrongX) {
                                        tip = "Excel中网元Id为:" + netIds + ",维护班组不存在;";
                                        break;
                                    } else {
                                        tip = "Excel中网元Id为:" + netIds + ",维护班组不存在;";
                                        maxNowX++;
                                    }
                                    if (!"".equals(tip)) {
                                        wlist.add(tip);
                                    }
                                }
                            }

                            //铁塔班组是否存在
                            if (t3.containsKey(ttRoleIdNames)) {
                                System.out.println("正确的铁塔班组subRoleName：" + ttRoleIdNames);
                                netOwnershipwireless.setTtRoleId(StaticMethod.nullObject2String(t3.get(ttRoleIdNames)));
                            } else if (t4.contains(netIds)) {
                                System.out.println("不存在的铁塔班组subRoleName：" + ttRoleIdNames);
                                if (maxNowX == maxWrongX) {
                                    tip = "Excel中网元Id为:" + netIds + ",铁塔班组不存在;";
                                    break;
                                } else {
                                    tip = "Excel中网元Id为:" + netIds + ",铁塔班组不存在;";
                                    maxNowX++;
                                }
                                if (!"".equals(tip)) {
                                    wlist.add(tip);
                                }
                            } else {
                                String ttRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/netownershipwireless-util.xml").getProperty("ttRoleId"));
                                String whereStr = " roleId='" + ttRoleId + "' and subRoleName='" + ttRoleIdNames + "'";
                                List subRoleIdList = subRoleMgr.getTawSystemSubRoles(whereStr);
                                if (subRoleIdList != null && subRoleIdList.size() > 0) {
                                    TawSystemSubRole ttSubRole = (TawSystemSubRole) subRoleIdList.get(0);
                                    String subRoleId = StaticMethod.nullObject2String(ttSubRole.getId());
                                    t3.put(ttRoleIdNames, subRoleId);
                                    netOwnershipwireless.setTtRoleId(subRoleId);
                                } else {
                                    t4.add(ttRoleIdNames);
                                    if (maxNowX == maxWrongX) {
                                        tip = "Excel中网元Id为:" + netIds + ",铁塔班组不存在;";
                                        break;
                                    } else {
                                        tip = "Excel中网元Id为:" + netIds + ",铁塔班组不存在;";
                                        maxNowX++;
                                    }
                                    if (!"".equals(tip)) {
                                        wlist.add(tip);
                                    }
                                }
                            }

                            //若以上验证都通过，则入库
                            if (c1.containsKey(copyRoleNames) && t1.containsKey(teamRoleNames) && t3.containsKey(ttRoleIdNames) && n1.contains(netIds) && county.contains(countys)) {
                                mgr.saveOrUpdate(netOwnershipwireless);//入库
                            }
                        }

                        if (wlist.size() == 0) {
                            exceptionStr = "全部提交成功！;";
                            jitem.put("alarmMsg", exceptionStr);
                            jsonRoot.put(jitem);
                            JSONUtil.print(response, jsonRoot.toString());
                        } else if (wlist.size() < 10) {
                            String exps = "";
                            for (int i = 0; i < wlist.size(); i++) {
                                exps += wlist.get(i);
                            }
                            exps = exps + " 其他提交成功！";
                            jitem.put("alarmMsg", exps);
                            jsonRoot.put(jitem);
                            JSONUtil.print(response, jsonRoot.toString());
                        } else {
                            String exps = "";
                            for (int i = 0; i < wlist.size(); i++) {
                                exps += wlist.get(i);

                            }
                            jitem.put("alarmMsg", exps + "之后的网元未提交，请修改错误网元，删除已提交部分，然后重新提交！");
                            jsonRoot.put(jitem);
                            JSONUtil.print(response, jsonRoot.toString());
                        }
                    }
                }
            } else {
                exceptionStr = exceptionStr + "系统找不到上传的Excel文件!";
                jitem.put("alarmMsg", exceptionStr);
                jsonRoot.put(jitem);
                JSONUtil.print(response, jsonRoot.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            exceptionStr = exceptionStr + "系统异常，请联系管理员!";
            jitem.put("alarmMsg", exceptionStr);
            jsonRoot.put(jitem);
            JSONUtil.print(response, jsonRoot.toString());
        }

    }

    /**
     * 根据查询条件生成模板
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void exportExcel(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //根据查询条件，获取查询结果集
        INetOwnershipwirelessManager mgr = (INetOwnershipwirelessManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipwirelessManager");
        Map actionMap = request.getParameterMap();
        String queryType = StaticMethod.null2String(request.getParameter("teamRoleIdChoice"));
        String[] aSql = {""};
        int[] aTotal = {0};
        try {
            List result = mgr.getQueryResult(aSql, actionMap, new Integer(0), new Integer(-1), aTotal, queryType);
            if (result != null && result.size() > 0) {
                int maxSheetNum = StaticMethod.nullObject2int(XmlManage.getFile("/config/netownershipwireless-util.xml").getProperty("maxNetNum"));
                int sheetNum = result.size() / maxSheetNum + 1;
                ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("id2nameService");

                //系统存附件路径
                String appId = StaticMethod.nullObject2String(request.getParameter("appId"));
                ITawCommonsAccessoriesManager attachMgr =
                        (ITawCommonsAccessoriesManager) ApplicationContextHolder.getInstance().getBean("ItawCommonsAccessoriesManager");
                String tempFilePath = attachMgr.getFilePath(appId);
                File tempFile = new File(tempFilePath);
                if (!tempFile.exists()) {
                    tempFile.mkdirs();
                }
                String fileName = tempFilePath + StaticMethod.getCurrentDateTime("yyyyMMddHHmmss") + ".xls";
                FileOutputStream out = new FileOutputStream(fileName);
                //建立一个新的工作表
                HSSFWorkbook wb = new HSSFWorkbook();
                for (int m = 0; m < sheetNum; m++) {
                    HSSFSheet sheet = wb.createSheet("sheet" + m);
                    HSSFRow row = sheet.createRow(0); // 建立新行
                    HSSFCell cell = null; // 建立新cell
                    HSSFCellStyle cellStyle = wb.createCellStyle();

                    cellStyle.setWrapText(true);
                    cellStyle.setVerticalAlignment((short) 1); // 设置垂直居中
                    cellStyle.setAlignment((short) 2); // 设置水平居中
                    cellStyle.setLocked(true);

                    String[] valueOfLine = {"网元ID", "网元名称", "综资网元名称", "网元类型", "地市", "区县", "维护班组", "抄送对象", "铁塔班组", "专业", "代维工作站属性", "铁塔属性", "类别属性", "是否高山站", "是否免责站", "是否高等级站"};
                    for (int i = 0; i < valueOfLine.length; i++) {
                        cell = row.createCell((short) i); // 建立新cell
                        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                        cell.setCellValue(valueOfLine[i]);
                    }
                    HashMap dictMap = new HashMap();
                    int resultFirstSize = m * maxSheetNum;
                    int resultEndSize = 0;
                    if (m == sheetNum - 1) {
                        resultEndSize = result.size();
                    } else {
                        resultEndSize = (m + 1) * maxSheetNum;
                    }
                    int cellCount = 0;
                    for (int i = resultFirstSize; i < resultEndSize; i++) {
                        NetOwnershipwireless net = (NetOwnershipwireless) result.get(i);
                        cellCount++;
                        row = sheet.createRow(cellCount);
                        row.createCell((short) 0).setCellValue(net.getNetId());
                        row.getCell((short) 0).setCellStyle(cellStyle);//锁定该表格

                        row.createCell((short) 1).setCellValue(net.getNetName());
                        row.createCell((short) 2).setCellValue(net.getNetNameByEdis());
                        row.createCell((short) 3).setCellValue(net.getNetType());
                        row.createCell((short) 4).setCellValue(net.getCity());
                        row.createCell((short) 5).setCellValue(net.getCounty());
                        String teamRoleId = StaticMethod.nullObject2String(net.getTeamRoleId());
                        if (dictMap.get(teamRoleId) != null) {
                            row.createCell((short) 6).setCellValue(StaticMethod.nullObject2String(dictMap.get(teamRoleId)));
                        } else {
                            String teamRoleName = service.id2Name(teamRoleId, "tawSystemSubRoleDao");
                            row.createCell((short) 6).setCellValue(teamRoleName);
                            dictMap.put(teamRoleId, teamRoleName);
                        }
                        String copyRoleId = StaticMethod.nullObject2String(net.getCcObject());
                        if (dictMap.get(copyRoleId) != null) {
                            row.createCell((short) 7).setCellValue(StaticMethod.nullObject2String(dictMap.get(copyRoleId)));
                        } else {
                            String copyRoleName = service.id2Name(copyRoleId, "tawSystemSubRoleDao");
                            row.createCell((short) 7).setCellValue(copyRoleName);
                            dictMap.put(copyRoleId, copyRoleName);
                        }

                        //add by fengmin
                        String ttRoleId = StaticMethod.nullObject2String(net.getTtRoleId());//铁塔班组
                        if (dictMap.get(ttRoleId) != null) {
                            row.createCell((short) 8).setCellValue(StaticMethod.nullObject2String(dictMap.get(ttRoleId)));
                        } else {
                            String ttRoleName = service.id2Name(ttRoleId, "tawSystemSubRoleDao");
                            row.createCell((short) 8).setCellValue(ttRoleName);
                            dictMap.put(ttRoleId, ttRoleName);
                        }
                        row.createCell((short) 9).setCellValue("无线");
                        row.createCell((short) 10).setCellValue(net.getWorkstatprop());//代维工作站属性

                        String tttype = StaticMethod.nullObject2String(net.getTttype());//铁塔属性
                        if (dictMap.get(tttype) != null) {
                            row.createCell((short) 11).setCellValue(StaticMethod.nullObject2String(dictMap.get(tttype)));
                        } else {
                            String tttypeName = service.id2Name(tttype, "ItawSystemDictTypeDao");
                            row.createCell((short) 11).setCellValue(tttypeName);
                            dictMap.put(tttype, tttypeName);
                        }
                        row.createCell((short) 12).setCellValue(net.getClasses());//类别属性
                        row.createCell((short) 13).setCellValue(net.getIfgs());//是否高山站
                        row.createCell((short) 14).setCellValue(net.getIfmz());//是否免责站
                        row.createCell((short) 15).setCellValue(net.getIfgj());//是否高等级站
                        //add by fengmin
                    }
                }
                out.flush();
                wb.write(out);
                out.close();

                File file = new File(fileName);
                // 读到流中
                InputStream inStream = new FileInputStream(file);// 文件的存放路径
                // 设置输出的格式
                response.reset();
                response.setContentType("application/x-msdownload;charset=GBK");
                response.setCharacterEncoding("UTF-8");
                String fileNameTemp = URLEncoder.encode(file.getName(), "UTF-8");
                response.setHeader("Content-Disposition", "attachment; filename="
                        + new String(fileNameTemp.getBytes("UTF-8"), "GBK"));

                // 循环取出流中的数据
                byte[] b = new byte[128];
                int len;
                while ((len = inStream.read(b)) > 0)
                    response.getOutputStream().write(b, 0, len);
                inStream.close();
            } else {
                response.sendRedirect("netownershipwireless.do?method=excelNoRecods");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ActionForward excelNoRecods(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("excelNoRecod");
    }
}
