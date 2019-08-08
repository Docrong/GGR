package com.boco.eoms.partner.baseinfo.webapp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.partner.baseinfo.mgr.AreaDeptTreeMgr;
import com.boco.eoms.partner.baseinfo.mgr.PartnerUserMgr;
import com.boco.eoms.partner.baseinfo.mgr.TawApparatusMgr;
import com.boco.eoms.partner.baseinfo.model.AreaDeptTree;
import com.boco.eoms.partner.baseinfo.model.PartnerUser;
import com.boco.eoms.partner.baseinfo.model.TawApparatus;
import com.boco.eoms.partner.baseinfo.util.PartnerUserConstants;
import com.boco.eoms.partner.baseinfo.util.RoleIdList;
import com.boco.eoms.partner.baseinfo.webapp.form.PartnerDeptForm;
import com.boco.eoms.partner.baseinfo.webapp.form.PartnerUserForm;
import com.boco.eoms.partner.baseinfo.webapp.form.TawApparatusForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.util.xml.XmlManage;

/**
 * <p>
 * Title:��f��Ϣ
 * </p>
 * <p>
 * Description:��f��Ϣ
 * </p>
 * <p>
 * Tue Feb 10 17:33:14 CST 2009
 * </p>
 *
 * @moudle.getAuthor() liujinlong
 * @moudle.getVersion() 3.5
 */
public final class PartnerUserAction extends BaseAction {

    public static String defaultTreeNodeId = "";//当save 或 remove 方法，调用 search方法时，利用这个变量，定位到相应人力信息的树节点下

    /**
     * δָ������ʱĬ�ϵ��õķ���
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return search(mapping, form, request, response);
    }

    /**
     * ������f��Ϣ
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String nodeId = StaticMethod.null2String(request.getParameter("nodeId"));
        AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
        AreaDeptTree user = areaDeptTreeMgr.getAreaDeptTreeByNodeId(nodeId);//人力信息节点
        AreaDeptTree factory = areaDeptTreeMgr.getAreaDeptTreeByNodeId(user.getParentNodeId());//人力信息父节点——厂商（部门），利用这个对象得到厂商名，给字段所属公司赋值
        AreaDeptTree area = areaDeptTreeMgr.getAreaDeptTreeByNodeId(factory.getParentNodeId());
        PartnerUserForm partnerUserForm = new PartnerUserForm();//(PartnerUserForm) form;

        String deptId = factory.getNodeId();
        partnerUserForm.setDeptId(deptId);
        partnerUserForm.setAreaId(area.getNodeId());
        List listId = areaDeptTreeMgr.getAreaDeptTreesByType("area");//得到所有地市
        List listName = new ArrayList();
        List list_id = new ArrayList();
        for (int i = 0; i < listId.size(); i++) {
            String tempId = ((AreaDeptTree) (listId.get(i))).getNodeId();
            listName.add(areaDeptTreeMgr.id2Name(tempId));
            list_id.add(tempId);
        }


//    	partnerUserForm.setDeptName(factory.getNodeName());
//    	partnerUserForm.setAreaName(area.getNodeName());
        partnerUserForm.setTreeNodeId(nodeId);//存人力信息节点的nodeId
        request.setAttribute("hasRightForDel", "1");
        request.setAttribute("treeNodeId", nodeId);
        request.setAttribute("listName", listName);
        request.setAttribute("listId", list_id);
        request.setAttribute("partnerUserForm", partnerUserForm);
        //updateFormBean(mapping, request, partnerUserForm);
        return mapping.findForward("edit");
    }

    /**
     * �޸���f��Ϣ
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PartnerUserMgr partnerUserMgr = (PartnerUserMgr) getBean("partnerUserMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        PartnerUser partnerUser = partnerUserMgr.getPartnerUser(id);
        PartnerUserForm partnerUserForm = (PartnerUserForm) convert(partnerUser);
        updateFormBean(mapping, request, partnerUserForm);
        AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");

        List listId = areaDeptTreeMgr.getAreaDeptTreesByType("area");
        List listName = new ArrayList();
        List list_id = new ArrayList();
        for (int i = 0; i < listId.size(); i++) {
            String tempId = ((AreaDeptTree) (listId.get(i))).getNodeId();
            listName.add(areaDeptTreeMgr.id2Name(tempId));
            list_id.add(tempId);
        }

        //add 王思轩 当前用户是否后有合作伙伴管理角色下的 合作伙伴人力资源管理 角色
        String hasRightForDel = "0";
        TawSystemSessionForm sessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        //2009-5-26 新建类RoleIdList，定义角色属性
        RoleIdList roleIdList = (RoleIdList) getBean("roleIdList");

        if (sessionForm.getUserid().equals("admin")) {
            hasRightForDel = "1";
        } else {
            List roleList = sessionForm.getRolelist();
            for (int i = 0; i < roleList.size(); i++) {
                TawSystemSubRole tempRole = (TawSystemSubRole) roleList.get(i);
                //2009-5-26 登陆用户角色是合作伙伴人力信息管理员，才能删除
                if (tempRole.getRoleId() == roleIdList.getUserRoleId().intValue()) {//PartnerUserConstants.PARTNER_USER_ROLEID
                    hasRightForDel = "1";
                }
            }
        }
        request.setAttribute("hasRightForDel", hasRightForDel);
        request.setAttribute("partnerUserForm", partnerUserForm);
        request.setAttribute("listName", listName);
        request.setAttribute("listId", list_id);
        return mapping.findForward("edit");
    }

    /**
     * ������f��Ϣ
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PartnerUserMgr partnerUserMgr = (PartnerUserMgr) getBean("partnerUserMgr");
        AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
        PartnerUserForm partnerUserForm = (PartnerUserForm) form;
        boolean isNew = (null == partnerUserForm.getId() || "".equals(partnerUserForm.getId()));
        PartnerUser partnerUser = (PartnerUser) convert(partnerUserForm);
        String areaId = partnerUser.getAreaId();
        String deptId = partnerUser.getDeptId();
        if (areaId != null && !areaId.equals("")) {
            String areaName = areaDeptTreeMgr.getAreaDeptTreeByNodeId(areaId).getNodeName();
            partnerUser.setAreaName(areaName);
        }
        if (deptId != null && !deptId.equals("")) {
            String deptName = areaDeptTreeMgr.getAreaDeptTreeByNodeId(deptId).getNodeName();
            partnerUser.setDeptName(deptName);
        }

        AreaDeptTree user = areaDeptTreeMgr.getAreaDeptTreeByNodeId(partnerUser.getTreeNodeId());//人力信息节点
        AreaDeptTree factory = areaDeptTreeMgr.getAreaDeptTreeByNodeId(user.getParentNodeId());
        //如果地市、厂商被修改了，那么此记录应显示在所修改的地市、厂商下的人力信息节点下
        if (!factory.getNodeId().equals(partnerUser.getDeptId())) {
            AreaDeptTree factory1 = areaDeptTreeMgr.getAreaDeptTreeByNodeId(partnerUser.getDeptId());
            List list = areaDeptTreeMgr.getChildLeafNodes(factory1.getNodeId(), "user");
            AreaDeptTree user1 = (AreaDeptTree) list.get(0);
            partnerUser.setTreeNodeId(user1.getNodeId());
        }

        //add 王思轩 当前用户是否后有合作伙伴管理角色下的 合作伙伴人力资源管理 角色
        String hasRightForDel = "0";
        TawSystemSessionForm sessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        if (sessionForm.getUserid().equals("admin")) {
            hasRightForDel = "1";
        } else {
            List roleList = sessionForm.getRolelist();
            for (int i = 0; i < roleList.size(); i++) {
                TawSystemSubRole tempRole = (TawSystemSubRole) roleList.get(i);
                if (tempRole.getRoleId() == PartnerUserConstants.PARTNER_USER_ROLEID) {
                    hasRightForDel = "1";
                }
            }
        }
        request.setAttribute("hasRightForDel", hasRightForDel);

        if (isNew) {
            if (partnerUserMgr.isunique(partnerUser.getUserId()).booleanValue()) {
                partnerUserMgr.savePartnerUser(partnerUser);
            } else {
                updateFormBean(mapping, request, partnerUserForm);

                List listId = areaDeptTreeMgr.getAreaDeptTreesByType("area");
                List listName = new ArrayList();
                List list_id = new ArrayList();
                for (int i = 0; i < listId.size(); i++) {
                    String tempId = ((AreaDeptTree) (listId.get(i))).getNodeId();
                    listName.add(areaDeptTreeMgr.id2Name(tempId));
                    list_id.add(tempId);
                }
                request.setAttribute("partnerUserForm", partnerUserForm);
                request.setAttribute("listName", listName);
                request.setAttribute("listId", list_id);
                request.setAttribute("fallure", " 用户ID已经存在");
                return mapping.findForward("edit");
            }
        } else {
            PartnerUser puser = partnerUserMgr.getPartnerUser(partnerUser.getId());
            if (puser != null && puser.getUserId().equals(partnerUser.getUserId())) {//userid不曾修改，则直接保存
                partnerUserMgr.savePartnerUser(partnerUser);
            } else {//userid 被修改后则判断是否与系统中已经存在的userid重复
                if (partnerUserMgr.isunique(partnerUser.getUserId()).booleanValue()) {
                    partnerUserMgr.savePartnerUser(partnerUser);
                } else {
                    updateFormBean(mapping, request, partnerUserForm);

                    List listId = areaDeptTreeMgr.getAreaDeptTreesByType("area");
                    List listName = new ArrayList();
                    List list_id = new ArrayList();
                    for (int i = 0; i < listId.size(); i++) {
                        String tempId = ((AreaDeptTree) (listId.get(i))).getNodeId();
                        listName.add(areaDeptTreeMgr.id2Name(tempId));
                        list_id.add(tempId);
                    }
                    request.setAttribute("partnerUserForm", partnerUserForm);
                    request.setAttribute("listName", listName);
                    request.setAttribute("listId", list_id);
                    request.setAttribute("fallure", " 用户ID已经存在");
                    return mapping.findForward("edit");
                }
            }
        }
        this.defaultTreeNodeId = partnerUser.getTreeNodeId();

        request.setAttribute("areaName", partnerUser.getAreaName());
        request.setAttribute("deptName", partnerUser.getDeptName());
        return mapping.findForward("success");
    }

    /**
     * ɾ����f��Ϣ
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward remove(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PartnerUserMgr partnerUserMgr = (PartnerUserMgr) getBean("partnerUserMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        if (!id.equals("")) {
            partnerUserMgr.removePartnerUser(id);
        }
        String treeNodeId = request.getParameter("treeNodeId");
        this.defaultTreeNodeId = treeNodeId;
        String[] ids = request.getParameterValues("checkbox11");
        if (ids != null) {
            for (int i = 0; i < ids.length; i++) {
                partnerUserMgr.removePartnerUser(ids[i]);
            }
        }

        return mapping.findForward("success");
    }

    /**
     * ��ҳ��ʾ��f��Ϣ�б�
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                PartnerUserConstants.PARTNERUSER_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        PartnerUserMgr partnerUserMgr = (PartnerUserMgr) getBean("partnerUserMgr");
        AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");

        String nodeId = StaticMethod.null2String(request.getParameter("nodeId"));//人力信息树节点
        String in = StaticMethod.null2String(request.getParameter("in"));//此条件表示点省、地市、厂商节点的查询，调用search方法。
        request.setAttribute("nodeId", nodeId);

        String whereStr = " where 1=1";
        if (!in.equals("")) {//在省、地市、厂商下直接点查询
            String nodes = areaDeptTreeMgr.getStringNodeIdByLeaf(nodeId, "user");
            whereStr += " and treeNodeId in (" + nodes + ")";
            request.setAttribute("inNodeId", nodeId);
            request.setAttribute("in", in);
        } else if (!nodeId.equals("")) {
            whereStr += " and treeNodeId  = " + nodeId;
            request.setAttribute("treeNodeId", nodeId);

            //----------------
            PartnerUserForm partnerUserForm = new PartnerUserForm();
            List listId = areaDeptTreeMgr.getAreaDeptTreesByType("area");
            List listName = new ArrayList();
            List list_id = new ArrayList();
            for (int i = 0; i < listId.size(); i++) {
                String tempId = ((AreaDeptTree) (listId.get(i))).getNodeId();
                listName.add(areaDeptTreeMgr.id2Name(tempId));
                list_id.add(tempId);
            }
            request.setAttribute("partnerUserForm", partnerUserForm);
            request.setAttribute("listName", listName);
            request.setAttribute("listId", list_id);
            AreaDeptTree user = areaDeptTreeMgr.getAreaDeptTreeByNodeId(nodeId);//人力信息节点
            AreaDeptTree factory = areaDeptTreeMgr.getAreaDeptTreeByNodeId(user.getParentNodeId());//人力信息父节点——厂商（部门），利用这个对象得到厂商名，给字段所属公司赋值
            AreaDeptTree area = areaDeptTreeMgr.getAreaDeptTreeByNodeId(factory.getParentNodeId());
            partnerUserForm.setDeptId(factory.getNodeId());
            partnerUserForm.setAreaId(area.getNodeId());
            //---------------------

        } else if (this.defaultTreeNodeId != null && !this.defaultTreeNodeId.equals("")) {
            whereStr += " and treeNodeId  = " + this.defaultTreeNodeId;
            request.setAttribute("treeNodeId", this.defaultTreeNodeId);
            request.setAttribute("nodeId", this.defaultTreeNodeId);

            //-----------------
            PartnerUserForm partnerUserForm = new PartnerUserForm();
            List listId = areaDeptTreeMgr.getAreaDeptTreesByType("area");
            List listName = new ArrayList();
            List list_id = new ArrayList();
            for (int i = 0; i < listId.size(); i++) {
                String tempId = ((AreaDeptTree) (listId.get(i))).getNodeId();
                listName.add(areaDeptTreeMgr.id2Name(tempId));
                list_id.add(tempId);
            }
            request.setAttribute("partnerUserForm", partnerUserForm);
            request.setAttribute("listName", listName);
            request.setAttribute("listId", list_id);
            AreaDeptTree user = areaDeptTreeMgr.getAreaDeptTreeByNodeId(this.defaultTreeNodeId);//人力信息节点
            AreaDeptTree factory = areaDeptTreeMgr.getAreaDeptTreeByNodeId(user.getParentNodeId());//人力信息父节点——厂商（部门），利用这个对象得到厂商名，给字段所属公司赋值
            AreaDeptTree area = areaDeptTreeMgr.getAreaDeptTreeByNodeId(factory.getParentNodeId());
            partnerUserForm.setDeptId(factory.getNodeId());
            partnerUserForm.setAreaId(area.getNodeId());
            //-----------------------

            this.defaultTreeNodeId = "";
        }
        //组装查询条件
        if (request.getParameter("nameSearch") != null && !request.getParameter("nameSearch").equals("")) {
            whereStr += " and name like '%" + request.getParameter("nameSearch") + "%'";
        }
        //当 in 参数为空时
        if (request.getParameter("areaId") != null && !request.getParameter("areaId").equals("")) {
            String areaName = areaDeptTreeMgr.getAreaDeptTreeByNodeId(request.getParameter("areaId")).getNodeName();
            whereStr += " and areaName like '%" + areaName + "%'";
        }
        if (request.getParameter("deptId") != null && !request.getParameter("deptId").equals("")) {
            String deptName = areaDeptTreeMgr.getAreaDeptTreeByNodeId(request.getParameter("deptId")).getNodeName();
            whereStr += " and deptName like '%" + deptName + "%'";
        }
        //当 in 参数不为空时 表示在省、地市、厂商下点查询
        if (request.getParameter("areaName") != null && !request.getParameter("areaName").equals("")) {
            whereStr += " and areaName like '%" + request.getParameter("areaName") + "%'";
        }
        if (request.getParameter("deptName") != null && !request.getParameter("deptName").equals("")) {
            whereStr += " and deptName like '%" + request.getParameter("deptName") + "%'";
        }
        //------------------
        if (request.getParameter("phoneSearch") != null && !request.getParameter("phoneSearch").equals("")) {
            whereStr += " and phone like '%" + request.getParameter("phoneSearch") + "%'";
        }
        if (request.getParameter("emailSearch") != null && !request.getParameter("emailSearch").equals("")) {
            whereStr += " and email like '%" + request.getParameter("emailSearch") + "%'";
        }

        Map map = (Map) partnerUserMgr.getPartnerUsers(pageIndex, pageSize, whereStr);
        List list = (List) map.get("result");
        request.setAttribute(PartnerUserConstants.PARTNERUSER_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }

    public ActionForward changeDep(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
        List deptListId = areaDeptTreeMgr.getNextLevelAreaDeptTrees(id);
        List deptListName = new ArrayList();
        for (int i = 0; i < deptListId.size(); i++) {
            String tempId = ((AreaDeptTree) (deptListId.get(i))).getNodeId();
            deptListName.add(areaDeptTreeMgr.id2Name(tempId));
        }
        StringBuffer d_list = new StringBuffer();
        for (int i = 0; i < deptListId.size(); i++) {
            deptListName.add(areaDeptTreeMgr.id2Name(((AreaDeptTree) (deptListId.get(i))).getNodeId()));
            d_list.append("," + ((AreaDeptTree) (deptListId.get(i))).getNodeId());
            d_list.append("," + deptListName.get(i));

        }

        String aaa = d_list.toString();
        aaa = aaa.substring(1, aaa.length());
        response.setContentType("text/html; charset=GBK");
        response.getWriter().print(aaa);
        response.getWriter().flush();
        return null;
    }

    //批量导入
    public ActionForward toXls(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("problemRow", "");

        String treeNodeId = request.getParameter("treeNodeId");
        if (treeNodeId != null && !treeNodeId.equals("")) {
            request.setAttribute("treeNodeId", treeNodeId);
        } else if (this.defaultTreeNodeId != null && !this.defaultTreeNodeId.equals("")) {
            request.setAttribute("treeNodeId", this.defaultTreeNodeId);
            this.defaultTreeNodeId = "";
        }
        return mapping.findForward("xlsInput");
    }

    public ActionForward xlsSave(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        PartnerUserMgr partnerUserMgr = (PartnerUserMgr) getBean("partnerUserMgr");
        AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
        // 首先将文件从客户端上传到服务器
        String timeTag = StaticMethod.getCurrentDateTime("yyyy_MM_dd_HHmmss");
        String sysTemPaht = request.getRealPath("/");
        PartnerUserForm partnerUserForm = (PartnerUserForm) form;
        String uploadPath = "/WEB-INF/pages/partner/baseinfo/upUserfiles";
        String filePath = sysTemPaht + uploadPath + "/" + timeTag + ".xls";
        File tempFile = new File(sysTemPaht + uploadPath);
        if (!tempFile.exists()) {
            tempFile.mkdir();
        }
        FormFile file = partnerUserForm.getAccessoryName();
//		String treeNodeId = partnerUserForm.getTreeNodeId();//存人力信息树节点nodeId
//		this.defaultTreeNodeId = treeNodeId;
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
            return mapping.findForward("fail");
        }
        // 然后把文件的每一条数据读入到form中
        Workbook workbook = null;
        ArrayList formList = new ArrayList();
        ArrayList numberList = new ArrayList();
        InputStream ins = new FileInputStream(filePath);
        try {
            // 构建Workbook对象, 只读Workbook对象
            // 直接从本地文件创建Workbook, 从输入流创建Workbook

            workbook = Workbook.getWorkbook(ins);
            Sheet dataSheet = workbook.getSheet(0);

            // 读取数据
            for (int i = 1; i < dataSheet.getRows(); i++) {
                PartnerUser partnerUser = new PartnerUser();
//				partnerUser.setTreeNodeId(treeNodeId);//
                if (dataSheet.getCell(0, i).getContents() != null && !"".equals(dataSheet.getCell(0, i).getContents())) {
                    partnerUser.setName(dataSheet.getCell(0, i).getContents().trim());
                } else {
                    break;
//					numberList.add(new Integer(i+1));
//					numberList.add(new Integer(1));
//					continue;
                }

                if (dataSheet.getCell(1, i).getContents() != null && !"".equals(dataSheet.getCell(1, i).getContents())) {
                    partnerUser.setPersonCardNo(dataSheet.getCell(1, i).getContents().trim());
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(2));
                    continue;
                }
                if (dataSheet.getCell(2, i).getContents() != null && !"".equals(dataSheet.getCell(2, i).getContents())) {
                    partnerUser.setAreaName(dataSheet.getCell(2, i).getContents().trim());
                    String nodeId = areaDeptTreeMgr.getNodeIdByNodeName(dataSheet.getCell(2, i).getContents().trim());
                    if (nodeId != null) {
                        partnerUser.setAreaId(areaDeptTreeMgr.getNodeIdByNodeName(dataSheet.getCell(2, i).getContents().trim()));
                    } else {
                        numberList.add(new Integer(i + 1));
                        numberList.add(new Integer(3));
                        continue;
                    }
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(3));
                    continue;
                }
                if (dataSheet.getCell(3, i).getContents() != null && !"".equals(dataSheet.getCell(3, i).getContents())) {
                    partnerUser.setDeptName(dataSheet.getCell(3, i).getContents().trim());
                    String parentNodeId = areaDeptTreeMgr.getNodeIdByNodeName(dataSheet.getCell(2, i).getContents().trim());
                    if (parentNodeId != null) {
                        String deptId = areaDeptTreeMgr.getNodeIdByParentAndName(parentNodeId, dataSheet.getCell(3, i).getContents().trim());
                        if (deptId != null) partnerUser.setDeptId(deptId);
                        else {
                            numberList.add(new Integer(i + 1));
                            numberList.add(new Integer(4));
                            continue;
                        }
                    }
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(4));
                    continue;
                }

                //给导入的记录treeNodeId字段赋值。
                String treeNodeId = areaDeptTreeMgr.getLeafNodeIdByNames(dataSheet.getCell(2, i).getContents().trim(),
                        dataSheet.getCell(3, i).getContents().trim(), "user");
                if (treeNodeId != null) {
                    partnerUser.setTreeNodeId(treeNodeId);
                    this.defaultTreeNodeId = treeNodeId;
                } else {
                    treeNodeId = partnerUserForm.getTreeNodeId();
                    partnerUser.setTreeNodeId(treeNodeId);
                    this.defaultTreeNodeId = treeNodeId;
                }

                if (dataSheet.getCell(4, i).getContents() != null && !"".equals(dataSheet.getCell(4, i).getContents())) {
                    partnerUser.setAreaNames(dataSheet.getCell(4, i).getContents().trim());
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(4));
                    continue;
                }
                if (dataSheet.getCell(5, i).getContents() != null && !"".equals(dataSheet.getCell(5, i).getContents())) {
                    if (partnerUserMgr.isunique(dataSheet.getCell(5, i).getContents()).booleanValue()) {
                        partnerUser.setUserId(dataSheet.getCell(5, i).getContents().trim());
                    } else {
                        numberList.add(new Integer(i + 1));
                        numberList.add(new Integer(6));
                        continue;
                    }
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(6));
                    continue;
                }
                if (dataSheet.getCell(6, i).getContents() != null && !"".equals(dataSheet.getCell(6, i).getContents())) {
                    partnerUser.setSex(dataSheet.getCell(6, i).getContents().trim());
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(7));
                    continue;
                }
                if (dataSheet.getCell(7, i).getContents() != null && !"".equals(dataSheet.getCell(7, i).getContents())) {
                    partnerUser.setPhoto(dataSheet.getCell(7, i).getContents().trim());
                }
                if (dataSheet.getCell(8, i).getContents() != null && !"".equals(dataSheet.getCell(8, i).getContents())) {
                    partnerUser.setBirthdey(dataSheet.getCell(8, i).getContents().trim());
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(9));
                    continue;
                }
                if (dataSheet.getCell(9, i).getContents() != null && !"".equals(dataSheet.getCell(9, i).getContents())) {
                    partnerUser.setDiploma(dataSheet.getCell(9, i).getContents().trim());
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(10));
                    continue;
                }
                if (dataSheet.getCell(10, i).getContents() != null && !"".equals(dataSheet.getCell(10, i).getContents())) {
                    partnerUser.setWorkTime(dataSheet.getCell(10, i).getContents().trim());
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(11));
                    continue;
                }
                if (dataSheet.getCell(11, i).getContents() != null && !"".equals(dataSheet.getCell(11, i).getContents())) {
                    partnerUser.setDeptWorkTime(dataSheet.getCell(11, i).getContents().trim());
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(12));
                    continue;
                }
                if (dataSheet.getCell(12, i).getContents() != null && !"".equals(dataSheet.getCell(12, i).getContents())) {
                    partnerUser.setLicenseType(dataSheet.getCell(12, i).getContents().trim());
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(13));
                    continue;
                }
                if (dataSheet.getCell(13, i).getContents() != null && !"".equals(dataSheet.getCell(13, i).getContents())) {
                    partnerUser.setLicenseNo(dataSheet.getCell(13, i).getContents().trim());
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(14));
                    continue;
                }
                if (dataSheet.getCell(14, i).getContents() != null && !"".equals(dataSheet.getCell(14, i).getContents())) {
                    partnerUser.setPost(dataSheet.getCell(14, i).getContents().trim());
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(15));
                    continue;
                }
                if (dataSheet.getCell(15, i).getContents() != null && !"".equals(dataSheet.getCell(15, i).getContents())) {
                    partnerUser.setPostState(dataSheet.getCell(15, i).getContents().trim());
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(16));
                    continue;
                }
                if (dataSheet.getCell(16, i).getContents() != null && !"".equals(dataSheet.getCell(16, i).getContents())) {
                    partnerUser.setPhone(dataSheet.getCell(16, i).getContents().trim());
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(17));
                    continue;
                }
                if (dataSheet.getCell(17, i).getContents() != null && !"".equals(dataSheet.getCell(17, i).getContents())) {
                    if (PartnerUserConstants.emailFormat(dataSheet.getCell(17, i).getContents().trim())) {
                        partnerUser.setEmail(dataSheet.getCell(17, i).getContents().trim());
                    } else {
                        numberList.add(new Integer(i + 1));
                        numberList.add(new Integer(18));
                        continue;
                    }
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(18));
                    continue;
                }
                TawSystemSessionForm sessionForm = this.getUser(request);
//				String addMan = sessionForm.getUserid();
//				Date addTime = new Date();
//				String connectType = sessionForm.getContactMobile();
//				temp.setAddMan(addMan);
//				temp.setAddTime(addTime);
//				temp.setConnectType(connectType);
                formList.add(partnerUser);
            }
            for (int i = 0; i < formList.size(); i++) {
                partnerUserMgr.savePartnerUser((PartnerUser) formList.get(i));
            }
            String problemRow = "";
            for (int i = 0; i < numberList.size(); i++) {
                problemRow += "," + numberList.get(i);
            }
            String str = "";
            if (!"".equals(problemRow)) {
                String sub = problemRow.substring(1, problemRow.length());
                String[] array = sub.split(",");
                str = "未成功录入！以下为不合法的未录入的信息：" + "<br>";
                for (int i = 0; i < array.length; i++) {

                    str = str + "第" + array[i] + "行" + "第" + array[i + 1] + "列;" + "<br>";
                    i++;
                }
            } else {
                str = "成功录入所有信息";
            }
            request.setAttribute("problemRow", str);
        } catch (Exception e) {
            workbook.close();

            File fileDel = new File(filePath);
            if (fileDel.exists())
                fileDel.delete();
            e.printStackTrace();
            return mapping.findForward("fail");
        } finally {
            workbook.close();
            ins.close();
            File fileDel = new File(filePath);
            if (fileDel.exists())
                fileDel.delete();
        }

        return mapping.findForward("xlsInput");

        //return search(mapping, accessoryForm, request, response);
    }

    //导出模板
    public ActionForward outPutModel(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response) {
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {
            String sysTemPath = request.getRealPath("/");
            String url = sysTemPath + "/partner/model/partnerUser.xls";
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
            return mapping.findForward("fail");
        }

        return null;
    }
}