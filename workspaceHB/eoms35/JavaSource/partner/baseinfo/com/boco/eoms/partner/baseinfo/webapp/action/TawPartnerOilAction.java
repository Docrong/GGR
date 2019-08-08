package com.boco.eoms.partner.baseinfo.webapp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
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
import com.boco.eoms.partner.baseinfo.mgr.TawPartnerOilMgr;
import com.boco.eoms.partner.baseinfo.model.AreaDeptTree;
import com.boco.eoms.partner.baseinfo.model.TawPartnerOil;
import com.boco.eoms.partner.baseinfo.util.TawPartnerOilConstants;
import com.boco.eoms.partner.baseinfo.webapp.form.TawApparatusForm;
import com.boco.eoms.partner.baseinfo.webapp.form.TawPartnerOilForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

/**
 * <p>
 * Title:油机信息
 * </p>
 * <p>
 * Description:油机信息
 * </p>
 * <p>
 * Thu Feb 05 13:56:15 CST 2009
 * </p>
 *
 * @moudle.getAuthor() fengshaohong
 * @moudle.getVersion() 3.5
 */
public final class TawPartnerOilAction extends BaseAction {
    Integer size = null;
    Integer index = null;
    String backsql = "";
    String allNode = "";

    /**
     * 未指定方法时默认调用的方法
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
     * 新增油机信息
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
        nodeId = nodeId.substring(0, 7);
        TawPartnerOilForm tawPartnerOilForm = new TawPartnerOilForm();
        tawPartnerOilForm.setDept_id(nodeId);
        AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
        AreaDeptTree adt = areaDeptTreeMgr.getAreaDeptTreeByNodeId(nodeId);
        tawPartnerOilForm.setArea_id(adt.getParentNodeId());
        List listId = areaDeptTreeMgr.getAreaDeptTreesByType("area");
        List listName = new ArrayList();
        List list_id = new ArrayList();
        for (int i = 0; i < listId.size(); i++) {
            String tempId = ((AreaDeptTree) (listId.get(i))).getNodeId();
            listName.add(areaDeptTreeMgr.id2Name(tempId));
            list_id.add(tempId);
        }
        updateFormBean(mapping, request, tawPartnerOilForm);
        request.setAttribute("listName", listName);
        request.setAttribute("listId", list_id);
        return mapping.findForward("edit");
    }

    /**
     * 修改油机信息
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
        TawPartnerOilMgr tawPartnerOilMgr = (TawPartnerOilMgr) getBean("tawPartnerOilMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        TawPartnerOil tawPartnerOil = tawPartnerOilMgr.getTawPartnerOil(id);
        TawPartnerOilForm tawPartnerOilForm = (TawPartnerOilForm) convert(tawPartnerOil);
        updateFormBean(mapping, request, tawPartnerOilForm);
        AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
        List listId = areaDeptTreeMgr.getAreaDeptTreesByType("area");
        List listName = new ArrayList();
        List list_id = new ArrayList();
        for (int i = 0; i < listId.size(); i++) {
            String tempId = ((AreaDeptTree) (listId.get(i))).getNodeId();
            listName.add(areaDeptTreeMgr.id2Name(tempId));
            list_id.add(tempId);
        }
        request.setAttribute("listName", listName);
        request.setAttribute("listId", list_id);
        return mapping.findForward("edit");
    }

    /**
     * 保存油机信息
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
        TawPartnerOilMgr tawPartnerOilMgr = (TawPartnerOilMgr) getBean("tawPartnerOilMgr");
        TawPartnerOilForm tawPartnerOilForm = (TawPartnerOilForm) form;
        String id = tawPartnerOilForm.getOil_number();

        boolean isNew = (null == tawPartnerOilForm.getId() || "".equals(tawPartnerOilForm.getId()));
        TawPartnerOil tawPartnerOil = (TawPartnerOil) convert(tawPartnerOilForm);
        TawSystemSessionForm sessionForm = this.getUser(request);
        String addMan = sessionForm.getUserid();
        Date addTime = new Date();
        String connectType = sessionForm.getContactMobile();
        if (isNew) {
            Boolean isU = tawPartnerOilMgr.isunique(id);
            if (!isU.booleanValue()) {
                AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
                List listId = areaDeptTreeMgr.getAreaDeptTreesByType("area");
                List listName = new ArrayList();
                List list_id = new ArrayList();
                for (int i = 0; i < listId.size(); i++) {
                    String tempId = ((AreaDeptTree) (listId.get(i))).getNodeId();
                    listName.add(areaDeptTreeMgr.id2Name(tempId));
                    list_id.add(tempId);
                }
                updateFormBean(mapping, request, tawPartnerOilForm);
                request.setAttribute("listName", listName);
                request.setAttribute("listId", list_id);
                request.setAttribute("fallure", " 仪器仪表编号已经存在");
                return mapping.findForward("edit");
            }
            tawPartnerOil.setAddMan(addMan);
            tawPartnerOil.setAddTime(addTime);
            tawPartnerOil.setConnectType(connectType);
            tawPartnerOilMgr.saveTawPartnerOil(tawPartnerOil);
        } else {
            String old_id = tawPartnerOilForm.getId();
            TawPartnerOil old = tawPartnerOilMgr.getTawPartnerOil(old_id);
            String old_num = old.getOil_number();
            if (!old_num.equals(id)) {
                Boolean isU = tawPartnerOilMgr.isunique(id);
                if (!isU.booleanValue()) {
                    AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
                    List listId = areaDeptTreeMgr.getAreaDeptTreesByType("area");
                    List listName = new ArrayList();
                    List list_id = new ArrayList();
                    for (int i = 0; i < listId.size(); i++) {
                        String tempId = ((AreaDeptTree) (listId.get(i))).getNodeId();
                        listName.add(areaDeptTreeMgr.id2Name(tempId));
                        list_id.add(tempId);
                    }
                    updateFormBean(mapping, request, tawPartnerOilForm);
                    request.setAttribute("listName", listName);
                    request.setAttribute("listId", list_id);
                    request.setAttribute("fallure", " 仪器仪表编号已经存在");
                    return mapping.findForward("edit");
                }
            }

            tawPartnerOil.setAddMan(old.getAddMan());
            tawPartnerOil.setAddTime(old.getAddTime());
            tawPartnerOil.setConnectType(old.getConnectType());
            tawPartnerOilMgr.saveTawPartnerOil(tawPartnerOil);
        }
        request.setAttribute("nodeId", tawPartnerOilForm.getDept_id());
        //return search(mapping, tawPartnerOilForm, request, response);
        return mapping.findForward("success");
    }

    /**
     * 删除油机信息
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
        TawPartnerOilMgr tawPartnerOilMgr = (TawPartnerOilMgr) getBean("tawPartnerOilMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        tawPartnerOilMgr.removeTawPartnerOil(id);
        request.setAttribute("nodeId", allNode);
        //return search(mapping, form, request, response);
        return mapping.findForward("success");
    }

    /**
     * 分页显示油机信息列表
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
        String isCity = "no";
        String nodeId = StaticMethod.null2String(request.getParameter("nodeId"));

        //2009-3-6 解决页面刷新后，链接参数为空的情况
        String in = StaticMethod.null2String(request.getParameter("in"));//此条件表示点省、地市、厂商节点的查询，调用search方法。
        if (!in.equals("")) {
            request.setAttribute("inNodeId", nodeId);
            request.setAttribute("in", in);
        }

        if (!"null".equals(nodeId) && nodeId != null && !"".equals(nodeId)) {
            ;
        } else {
            nodeId = (String) request.getAttribute("nodeId");
        }
        allNode = nodeId;
        AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
        AreaDeptTree adt = areaDeptTreeMgr.getAreaDeptTreeByNodeId(nodeId);
        TawPartnerOilForm tawPartnerOilForm = new TawPartnerOilForm();
        String type = adt.getNodeType();
        List listName = new ArrayList();
        List list_id = new ArrayList();
        if (type.equals("factory")) {
            tawPartnerOilForm.setDept_id(nodeId);
            tawPartnerOilForm.setArea_id(adt.getParentNodeId());
            isCity = "yes";
        } else if (type.equals("area")) {
            tawPartnerOilForm.setArea_id(nodeId);
            tawPartnerOilForm.setDept_id("");

        } else if (type.equals("province")) {
            listName.add(" ");
            list_id.add(" ");
        } else {
            isCity = "yes";
            AreaDeptTree adt2 = areaDeptTreeMgr.getAreaDeptTreeByNodeId(nodeId.substring(0, 7));
            tawPartnerOilForm.setDept_id(nodeId.substring(0, 7));
            tawPartnerOilForm.setArea_id(adt2.getParentNodeId());
            nodeId = nodeId.substring(0, 7);
        }
        //vvvvvvvvvvvvvvvv

        List listId = areaDeptTreeMgr.getAreaDeptTreesByType("area");
        for (int i = 0; i < listId.size(); i++) {
            String tempId = ((AreaDeptTree) (listId.get(i))).getNodeId();
            listName.add(areaDeptTreeMgr.id2Name(tempId));
            list_id.add(tempId);
        }
        updateFormBean(mapping, request, tawPartnerOilForm);
        request.setAttribute("listName", listName);
        request.setAttribute("listId", list_id);
        //vvvvvvvvvvvv
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                TawPartnerOilConstants.TAWPARTNEROIL_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        size = pageSize;
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        index = pageIndex;
        String whereStr = " and dept_id like '" + nodeId + "%'";
        TawPartnerOilMgr tawPartnerOilMgr = (TawPartnerOilMgr) getBean("tawPartnerOilMgr");
        Map map = (Map) tawPartnerOilMgr.getTawPartnerOils(pageIndex, pageSize, whereStr);
        List list = (List) map.get("result");
        request.setAttribute(TawPartnerOilConstants.TAWPARTNEROIL_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("nodeId", nodeId);
        request.setAttribute("isCity", isCity);

        return mapping.findForward("list");
    }

    /**
     * 分页显示油机信息列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward xquery(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawPartnerOilMgr tawPartnerOilMgr = (TawPartnerOilMgr) getBean("tawPartnerOilMgr");
        TawPartnerOilForm tawPartnerOilForm = (TawPartnerOilForm) form;
        String oil_number = tawPartnerOilForm.getOil_number();
        String factory = tawPartnerOilForm.getFactory();
        String type = tawPartnerOilForm.getType();
        String dept_id = tawPartnerOilForm.getDept_id().trim();
        String area_id = tawPartnerOilForm.getArea_id().trim();
        String storage = tawPartnerOilForm.getStorage();
        String state = tawPartnerOilForm.getState();
        StringBuffer sql = new StringBuffer();
        String isCity = "no";
        if (!"".equals(type) && type != null) {
            sql.append(" and type= '" + type + "'");
        }
        if (!"".equals(state) && state != null) {
            sql.append(" and state= '" + state + "'");
        }
        if (!"".equals(area_id) && area_id != null) {
            sql.append(" and area_id= '" + area_id + "'");
        }
        if (!"".equals(dept_id) && dept_id != null) {
            sql.append(" and dept_id= '" + dept_id + "'");
            isCity = "yes";
        }
        if (!"".equals(oil_number) && oil_number != null) {
            sql.append(" and oil_number= '" + oil_number + "'");
        }
        if (!"".equals(factory) && factory != null) {
            sql.append(" and factory= '" + factory + "'");
        }
        if (!"".equals(storage) && storage != null) {
            sql.append(" and storage like '%" + storage + "%'");
        }
        backsql = sql.toString();

        String pageIndexName = new org.displaytag.util.ParamEncoder(
                TawPartnerOilConstants.TAWPARTNEROIL_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        size = pageSize;
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        index = pageIndex;
        Map map = (Map) tawPartnerOilMgr.getTawPartnerOils(pageIndex, pageSize, backsql);
        List list = (List) map.get("result");
//		vvvvvvvvvvvvvvvv
        AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
        List listId = areaDeptTreeMgr.getAreaDeptTreesByType("area");
        List listName = new ArrayList();
        List list_id = new ArrayList();
        for (int i = 0; i < listId.size(); i++) {
            String tempId = ((AreaDeptTree) (listId.get(i))).getNodeId();
            listName.add(areaDeptTreeMgr.id2Name(tempId));
            list_id.add(tempId);
        }
        updateFormBean(mapping, request, tawPartnerOilForm);
        request.setAttribute("listName", listName);
        request.setAttribute("listId", list_id);
        //vvvvvvvvvvvv
        request.setAttribute(TawPartnerOilConstants.TAWPARTNEROIL_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("nodeId", dept_id);
        request.setAttribute("isCity", isCity);
        return mapping.findForward("list");
    }

    public ActionForward backToSearch(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawPartnerOilMgr tawPartnerOilMgr = (TawPartnerOilMgr) getBean("tawPartnerOilMgr");
        Map map = (Map) tawPartnerOilMgr.getTawPartnerOils(index, size, backsql);
        List list = (List) map.get("result");
        request.setAttribute(TawPartnerOilConstants.TAWPARTNEROIL_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", size);
        return mapping.findForward("list");
    }

    public ActionForward xlsSave(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {

        TawPartnerOilMgr mgr = (TawPartnerOilMgr) getBean("tawPartnerOilMgr");
        AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
        // 首先将文件从客户端上传到服务器
        String timeTag = StaticMethod.getCurrentDateTime("yyyy_MM_dd_HHmmss");
        String sysTemPaht = request.getRealPath("/");
        TawPartnerOilForm accessoryForm = (TawPartnerOilForm) form;
        String uploadPath = "/WEB-INF/pages/partner/baseinfo/tawPartnerOil/upfiles";
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
                TawPartnerOil temp = new TawPartnerOil();
                if (dataSheet.getCell(0, i).getContents() != null
                        && !"".equals(dataSheet.getCell(0, i).getContents())) {
                    if (mgr.isunique(dataSheet.getCell(0, i).getContents()).booleanValue()) {
                        temp.setOil_number(dataSheet.getCell(0, i).getContents()
                                .trim());
                    } else {
                        numberList.add(new Integer(i + 1));
                        numberList.add(new Integer(1));
                        continue;
                    }
                } else {
                    break;
//					numberList.add(new Integer(i+1));
//					numberList.add(new Integer(1));
//					continue;
                }
                if (dataSheet.getCell(1, i).getContents() != null
                        && !"".equals(dataSheet.getCell(1, i).getContents())) {
                    if (!"".equals(mgr.name2Id(dataSheet.getCell(1, i).getContents().trim(), "11208")) &&
                            (mgr.name2Id(dataSheet.getCell(1, i).getContents().trim(), "11208")) != null) {
                        temp.setFactory(mgr.name2Id(dataSheet.getCell(1, i).getContents().trim(), "11208"));
                    } else {
                        numberList.add(new Integer(i + 1));
                        numberList.add(new Integer(2));
                        continue;
                    }
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(2));
                    continue;
                }
                if (dataSheet.getCell(2, i).getContents() != null
                        && !"".equals(dataSheet.getCell(2, i).getContents())) {
                    if (!"".equals(areaDeptTreeMgr.getNodeIdByNodeName(dataSheet.getCell(2, i).getContents().trim())) &&
                            (areaDeptTreeMgr.getNodeIdByNodeName(dataSheet.getCell(2, i).getContents().trim())) != null) {
                        temp.setArea_id(areaDeptTreeMgr.getNodeIdByNodeName(dataSheet.getCell(2, i).getContents().trim()));
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
                if (dataSheet.getCell(3, i).getContents() != null
                        && !"".equals(dataSheet.getCell(3, i).getContents())) {
                    if (!"".equals(areaDeptTreeMgr.getNodeIdByParentAndName(areaDeptTreeMgr.getNodeIdByNodeName(dataSheet.getCell(2, i).getContents().trim()), dataSheet.getCell(3, i).getContents().trim())) &&
                            (areaDeptTreeMgr.getNodeIdByParentAndName(areaDeptTreeMgr.getNodeIdByNodeName(dataSheet.getCell(2, i).getContents().trim()), dataSheet.getCell(3, i).getContents().trim())) != null) {
                        temp.setDept_id(areaDeptTreeMgr.getNodeIdByNodeName(dataSheet.getCell(3, i).getContents().trim()));
                    } else {
                        numberList.add(new Integer(i + 1));
                        numberList.add(new Integer(4));
                        continue;
                    }
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(4));
                    continue;
                }

                if (dataSheet.getCell(4, i).getContents() != null
                        && !"".equals(dataSheet.getCell(4, i).getContents())) {
                    if (!"".equals(mgr.name2Id(dataSheet.getCell(4, i).getContents().trim(), "11209")) &&
                            (mgr.name2Id(dataSheet.getCell(4, i).getContents().trim(), "11209")) != null) {
                        temp.setType(mgr.name2Id(dataSheet.getCell(4, i).getContents().trim(), "11209"));
                    } else {
                        numberList.add(new Integer(i + 1));
                        numberList.add(new Integer(5));
                        continue;
                    }
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(5));
                    continue;
                }
                if (dataSheet.getCell(5, i).getContents() != null
                        && !"".equals(dataSheet.getCell(5, i).getContents())) {
                    if (!"".equals(mgr.name2Id(dataSheet.getCell(5, i).getContents().trim(), "11207")) &&
                            (mgr.name2Id(dataSheet.getCell(5, i).getContents().trim(), "11207")) != null) {
                        temp.setPower(mgr.name2Id(dataSheet.getCell(5, i).getContents().trim(), "11207"));
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
                if (dataSheet.getCell(6, i).getContents() != null
                        && !"".equals(dataSheet.getCell(6, i).getContents())) {
                    if (!"".equals(mgr.name2Id(dataSheet.getCell(6, i).getContents().trim(), "11206")) &&
                            (mgr.name2Id(dataSheet.getCell(6, i).getContents().trim(), "11206")) != null) {
                        temp.setKind(mgr.name2Id(dataSheet.getCell(6, i).getContents().trim(), "11206"));
                    } else {
                        numberList.add(new Integer(i + 1));
                        numberList.add(new Integer(7));
                        continue;
                    }
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(7));
                    continue;
                }
                if (dataSheet.getCell(7, i).getContents() != null
                        && !"".equals(dataSheet.getCell(7, i).getContents())) {
                    if (!"".equals(mgr.name2Id(dataSheet.getCell(7, i).getContents().trim(), "11205")) &&
                            (mgr.name2Id(dataSheet.getCell(7, i).getContents().trim(), "11205")) != null) {
                        temp.setState(mgr.name2Id(dataSheet.getCell(7, i).getContents().trim(), "11205"));
                    } else {
                        numberList.add(new Integer(i + 1));
                        numberList.add(new Integer(8));
                        continue;
                    }
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(8));
                    continue;
                }
                if (dataSheet.getCell(8, i).getContents() != null
                        && !"".equals(dataSheet.getCell(8, i).getContents())) {
                    temp.setStorage(dataSheet.getCell(8, i).getContents()
                            .trim());
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(9));
                    continue;
                }
                if (dataSheet.getCell(9, i).getContents() != null
                        && !"".equals(dataSheet.getCell(9, i).getContents())) {
                    temp.setRemark(dataSheet.getCell(9, i).getContents()
                            .trim());
                }
                TawSystemSessionForm sessionForm = this.getUser(request);
                String addMan = sessionForm.getUserid();
                Date addTime = new Date();
                String connectType = sessionForm.getContactMobile();
                temp.setAddMan(addMan);
                temp.setAddTime(addTime);
                temp.setConnectType(connectType);
                formList.add(temp);
            }
            for (int i = 0; i < formList.size(); i++) {
                mgr.saveTawPartnerOil((TawPartnerOil) formList.get(i));
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

    public ActionForward toXls(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("problemRow", "");
        return mapping.findForward("xlsInput");
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

    public ActionForward changeDep2(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        String aaa = " ," + d_list.toString();
        response.setContentType("text/html; charset=GBK");
        response.getWriter().print(aaa);
        response.getWriter().flush();
        return null;
    }

    public ActionForward outPutModel(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response) {
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {
            String sysTemPath = request.getRealPath("/");
            String url = sysTemPath + "/partner/model/partneroil.xls";
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
    /**
     * 分页显示油机信息列表，支持Atom方式接入Portal
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception

    public ActionForward search4Atom(ActionMapping mapping, ActionForm form,
    HttpServletRequest request, HttpServletResponse response)
    throws Exception {
    try {
    // --------------用于分页，得到当前页号-------------
    final Integer pageIndex = new Integer(request
    .getParameter("pageIndex"));
    final Integer pageSize = new Integer(request
    .getParameter("pageSize"));
    TawPartnerOilMgr tawPartnerOilMgr = (TawPartnerOilMgr) getBean("tawPartnerOilMgr");
    Map map = (Map) tawPartnerOilMgr.getTawPartnerOils(pageIndex, pageSize, "");
    List list = (List) map.get("result");
    TawPartnerOil tawPartnerOil = new TawPartnerOil();

    //创建ATOM源
    Factory factory = Abdera.getNewFactory();
    Feed feed = factory.newFeed();

    // 分页
    for (int i = 0; i < list.size(); i++) {
    tawPartnerOil = (TawPartnerOil) list.get(i);

    // TODO 请按照下面的实例给entry赋值
    Entry entry = feed.insertEntry();
    entry.setTitle("<a href='" + request.getScheme() + "://"
    + request.getServerName() + ":"
    + request.getServerPort()
    + request.getContextPath()
    + "/tawPartnerOil/tawPartnerOils.do?method=edit&id="
    + tawPartnerOil.getId() + "' target='_blank'>"
    + display name for list + "</a>");
    entry.setSummary(summary);
    entry.setContent(content);
    entry.setLanguage(language);
    entry.setText(text);
    entry.setRights(tights);

    // 以下三项需要传入Date类型或String类型（yyyy-MM-dd）的参数
    entry.setUpdated(new java.util.Date());
    entry.setPublished(new java.util.Date());
    entry.setEdited(new java.util.Date());

    // 为person的name属性赋值，entry.addAuthor可以随意赋值
    Person person = entry.addAuthor(userId);
    person.setName(userName);
    }

    // 每页显示条数
    feed.setText(map.get("total").toString());
    OutputStream os = response.getOutputStream();
    PrintStream ps = new PrintStream(os);
    feed.getDocument().writeTo(ps);
    } catch (Exception e) {
    e.printStackTrace();
    }
    return null;
    }
     */
}