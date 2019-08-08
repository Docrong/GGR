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
import com.boco.eoms.partner.baseinfo.mgr.TawPartnerCarMgr;
import com.boco.eoms.partner.baseinfo.model.AreaDeptTree;
import com.boco.eoms.partner.baseinfo.model.TawPartnerCar;
import com.boco.eoms.partner.baseinfo.util.TawPartnerCarConstants;
import com.boco.eoms.partner.baseinfo.webapp.form.TawApparatusForm;
import com.boco.eoms.partner.baseinfo.webapp.form.TawPartnerCarForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.duty.bo.TawModelAssignXLSBO;
import com.boco.eoms.duty.bo.TawRmModelSetBo;
import com.boco.eoms.duty.model.TawModelCopy;

/**
 * <p>
 * Title:车辆管理
 * </p>
 * <p>
 * Description:车辆管理
 * </p>
 * <p>
 * Thu Feb 05 13:54:40 CST 2009
 * </p>
 *
 * @moudle.getAuthor() fengshaohong
 * @moudle.getVersion() 3.5
 */
public final class TawPartnerCarAction extends BaseAction {
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
     * 新增车辆管理
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
        TawPartnerCarMgr mgr = (TawPartnerCarMgr) getBean("tawPartnerCarMgr");
        TawPartnerCarForm tawPartnerCarForm = new TawPartnerCarForm();
        tawPartnerCarForm.setDept_id(nodeId);
        AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
        AreaDeptTree adt = areaDeptTreeMgr.getAreaDeptTreeByNodeId(nodeId);
        tawPartnerCarForm.setArea_id(adt.getParentNodeId());
        List listId = areaDeptTreeMgr.getAreaDeptTreesByType("area");
        List listName = new ArrayList();
        List list_id = new ArrayList();
        for (int i = 0; i < listId.size(); i++) {
            String tempId = ((AreaDeptTree) (listId.get(i))).getNodeId();
            listName.add(areaDeptTreeMgr.id2Name(tempId));
            list_id.add(tempId);
        }

        String[] all = mgr.getDictIdByParentId("11210");
        tawPartnerCarForm.setArrayPieceAll(all);
        request.setAttribute("tawPartnerCarForm", tawPartnerCarForm);
        request.setAttribute("listName", listName);
        request.setAttribute("listId", list_id);
        return mapping.findForward("edit");
    }

    /**
     * 修改车辆管理
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
        TawPartnerCarMgr tawPartnerCarMgr = (TawPartnerCarMgr) getBean("tawPartnerCarMgr");
        TawPartnerCarMgr mgr = (TawPartnerCarMgr) getBean("tawPartnerCarMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        TawPartnerCar tawPartnerCar = tawPartnerCarMgr.getTawPartnerCar(id);
        TawPartnerCarForm tawPartnerCarForm = (TawPartnerCarForm) convert(tawPartnerCar);
        String[] all = mgr.getDictIdByParentId("11210");
        tawPartnerCarForm.setArrayPieceAll(all);
        tawPartnerCarForm.setArrayPiece((tawPartnerCarForm.getPiece()).split("'"));
        updateFormBean(mapping, request, tawPartnerCarForm);
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
     * 保存车辆管理
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
        TawPartnerCarMgr tawPartnerCarMgr = (TawPartnerCarMgr) getBean("tawPartnerCarMgr");
        TawPartnerCarForm tawPartnerCarForm = (TawPartnerCarForm) form;
        String id = tawPartnerCarForm.getCar_number();

        String[] piece = request.getParameterValues("arrayPiece");
        String pieceTemp = "";
        for (int i = 0; i < piece.length; i++) {
            pieceTemp += piece[i] + "'";
        }
        pieceTemp = pieceTemp.substring(0, pieceTemp.length() - 1);
        boolean isNew = (null == tawPartnerCarForm.getId() || "".equals(tawPartnerCarForm.getId()));
        TawPartnerCar tawPartnerCar = (TawPartnerCar) convert(tawPartnerCarForm);
        TawSystemSessionForm sessionForm = this.getUser(request);
        String addMan = sessionForm.getUserid();
        Date addTime = new Date();
        String connectType = sessionForm.getContactMobile();
        if (isNew) {
            Boolean isU = tawPartnerCarMgr.isunique(id);
            if (!isU.booleanValue()) {
                AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
                List listId = areaDeptTreeMgr.getAreaDeptTreesByType("area");
                List listName = new ArrayList();
                List list_id = new ArrayList();
                for (int i = 0; i < listId.size(); i++) {
                    String[] all = tawPartnerCarMgr.getDictIdByParentId("11210");
                    tawPartnerCarForm.setArrayPieceAll(all);
                    String tempId = ((AreaDeptTree) (listId.get(i))).getNodeId();
                    listName.add(areaDeptTreeMgr.id2Name(tempId));
                    list_id.add(tempId);
                }

                updateFormBean(mapping, request, tawPartnerCarForm);

                request.setAttribute("listName", listName);
                request.setAttribute("listId", list_id);
                request.setAttribute("fallure", " 仪器仪表编号已经存在");
                return mapping.findForward("edit");
            }
            tawPartnerCar.setAddMan(addMan);
            tawPartnerCar.setAddTime(addTime);
            tawPartnerCar.setConnectType(connectType);
            tawPartnerCar.setPiece(pieceTemp);
            tawPartnerCarMgr.saveTawPartnerCar(tawPartnerCar);
        } else {
            String tempId2 = tawPartnerCarForm.getId();
            TawPartnerCar old = tawPartnerCarMgr.getTawPartnerCar(tempId2);
            String carnum_old = old.getCar_number();
            if (!id.equals(carnum_old)) {
                Boolean isU = tawPartnerCarMgr.isunique(id);
                if (!isU.booleanValue()) {
                    AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
                    List listId = areaDeptTreeMgr.getAreaDeptTreesByType("area");
                    List listName = new ArrayList();
                    List list_id = new ArrayList();
                    for (int i = 0; i < listId.size(); i++) {
                        String[] all = tawPartnerCarMgr.getDictIdByParentId("11210");
                        tawPartnerCarForm.setArrayPieceAll(all);
                        String tempId = ((AreaDeptTree) (listId.get(i))).getNodeId();
                        listName.add(areaDeptTreeMgr.id2Name(tempId));
                        list_id.add(tempId);
                    }

                    updateFormBean(mapping, request, tawPartnerCarForm);

                    request.setAttribute("listName", listName);
                    request.setAttribute("listId", list_id);
                    request.setAttribute("fallure", " 仪器仪表编号已经存在");
                    return mapping.findForward("edit");
                }
            }

            tawPartnerCar.setAddMan(old.getAddMan());
            tawPartnerCar.setAddTime(old.getAddTime());
            tawPartnerCar.setConnectType(old.getConnectType());
            tawPartnerCar.setPiece(pieceTemp);
            tawPartnerCarMgr.saveTawPartnerCar(tawPartnerCar);
        }
        request.setAttribute("nodeId", tawPartnerCarForm.getDept_id());
        return mapping.findForward("success");
    }

    /**
     * 删除车辆管理
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
        TawPartnerCarMgr tawPartnerCarMgr = (TawPartnerCarMgr) getBean("tawPartnerCarMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        tawPartnerCarMgr.removeTawPartnerCar(id);
        request.setAttribute("nodeId", allNode);
        return mapping.findForward("success");
    }

    /**
     * 分页显示车辆管理列表
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
        TawPartnerCarForm tawPartnerCarForm = new TawPartnerCarForm();
        String type = adt.getNodeType();
        List listName = new ArrayList();
        List list_id = new ArrayList();
        if (type.equals("factory")) {
            tawPartnerCarForm.setDept_id(nodeId);
            tawPartnerCarForm.setArea_id(adt.getParentNodeId());
            isCity = "yes";
        } else if (type.equals("area")) {
            tawPartnerCarForm.setArea_id(nodeId);
            tawPartnerCarForm.setDept_id("");

        } else if (type.equals("province")) {
            listName.add(" ");
            list_id.add(" ");
        } else {
            isCity = "yes";
            AreaDeptTree adt2 = areaDeptTreeMgr.getAreaDeptTreeByNodeId(nodeId.substring(0, 7));
            tawPartnerCarForm.setDept_id(nodeId.substring(0, 7));
            tawPartnerCarForm.setArea_id(adt2.getParentNodeId());
            nodeId = nodeId.substring(0, 7);
        }
//			vvvvvvvvvvvvvvvv
        List listId = areaDeptTreeMgr.getAreaDeptTreesByType("area");
        for (int i = 0; i < listId.size(); i++) {
            String tempId = ((AreaDeptTree) (listId.get(i))).getNodeId();
            listName.add(areaDeptTreeMgr.id2Name(tempId));
            list_id.add(tempId);
        }
        updateFormBean(mapping, request, tawPartnerCarForm);
        request.setAttribute("listName", listName);
        request.setAttribute("listId", list_id);
        //vvvvvvvvvvvv
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                TawPartnerCarConstants.TAWPARTNERCAR_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        size = pageSize;
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        index = pageIndex;
        String whereStr = " and dept_id like '" + nodeId + "%'";
        TawPartnerCarMgr tawPartnerCarMgr = (TawPartnerCarMgr) getBean("tawPartnerCarMgr");
        Map map = (Map) tawPartnerCarMgr.getTawPartnerCars(pageIndex, pageSize, whereStr);
        List list = (List) map.get("result");
        request.setAttribute(TawPartnerCarConstants.TAWPARTNERCAR_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("nodeId", nodeId);
        request.setAttribute("isCity", isCity);
        return mapping.findForward("list");
    }

    /**
     * 条件查询
     * 分页显示车辆管理列表
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
        TawPartnerCarMgr tawPartnerCarMgr = (TawPartnerCarMgr) getBean("tawPartnerCarMgr");
        TawPartnerCarForm tawPartnerCarForm = (TawPartnerCarForm) form;
        String car_number = tawPartnerCarForm.getCar_number();
        String piece = tawPartnerCarForm.getPiece();
        String dept_id = tawPartnerCarForm.getDept_id().trim();
        String area_id = tawPartnerCarForm.getArea_id().trim();
        StringBuffer sql = new StringBuffer();
        String isCity = "no";
        if (!"".equals(car_number) && car_number != null) {
            sql.append(" and car_number like '%" + car_number + "%'");
        }
        if (!"".equals(piece) && piece != null) {
            sql.append(" and piece like '%" + piece + "%'");
        }
        if (!"".equals(dept_id) && dept_id != null) {
            sql.append(" and dept_id= '" + dept_id + "'");
            isCity = "yes";
        }
        if (!"".equals(area_id) && area_id != null) {
            sql.append(" and area_id= '" + area_id + "'");
        }
        backsql = sql.toString();

        String pageIndexName = new org.displaytag.util.ParamEncoder(
                TawPartnerCarConstants.TAWPARTNERCAR_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        size = pageSize;
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        index = pageIndex;
        Map map = (Map) tawPartnerCarMgr.getTawPartnerCars(pageIndex, pageSize, sql.toString());
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
        updateFormBean(mapping, request, tawPartnerCarForm);
        request.setAttribute("listName", listName);
        request.setAttribute("listId", list_id);
        //vvvvvvvvvvvv
        request.setAttribute(TawPartnerCarConstants.TAWPARTNERCAR_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("nodeId", dept_id);
        request.setAttribute("isCity", isCity);
        return mapping.findForward("list");
    }

    public ActionForward backToSearch(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawPartnerCarMgr tawPartnerCarMgr = (TawPartnerCarMgr) getBean("tawPartnerCarMgr");
        Map map = (Map) tawPartnerCarMgr.getTawPartnerCars(index, size, backsql);
        List list = (List) map.get("result");
        request.setAttribute(TawPartnerCarConstants.TAWPARTNERCAR_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", size);
        return mapping.findForward("list");
    }

    public ActionForward xlsSave(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {

        TawPartnerCarMgr mgr = (TawPartnerCarMgr) getBean("tawPartnerCarMgr");
        AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
        // 首先将文件从客户端上传到服务器
        String timeTag = StaticMethod.getCurrentDateTime("yyyy_MM_dd_HHmmss");
        String sysTemPaht = request.getRealPath("/");
        TawPartnerCarForm accessoryForm = (TawPartnerCarForm) form;
        String uploadPath = "/WEB-INF/pages/partner/baseinfo/tawPartnerCar/upfiles";
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
                TawPartnerCar temp = new TawPartnerCar();
                if (dataSheet.getCell(0, i).getContents() != null
                        && !"".equals(dataSheet.getCell(0, i).getContents())) {
                    if (mgr.isunique(dataSheet.getCell(0, i).getContents()).booleanValue()) {
                        temp.setCar_number(dataSheet.getCell(0, i).getContents()
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
                    if (!"".equals(areaDeptTreeMgr.getNodeIdByNodeName(dataSheet.getCell(1, i).getContents().trim())) &&
                            (areaDeptTreeMgr.getNodeIdByNodeName(dataSheet.getCell(1, i).getContents().trim())) != null) {
                        temp.setArea_id(areaDeptTreeMgr.getNodeIdByNodeName(dataSheet.getCell(1, i).getContents().trim()));
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
                    if (!"".equals(areaDeptTreeMgr.getNodeIdByParentAndName(areaDeptTreeMgr.getNodeIdByNodeName(dataSheet.getCell(1, i).getContents().trim()), dataSheet.getCell(2, i).getContents().trim())) &&
                            (areaDeptTreeMgr.getNodeIdByParentAndName(areaDeptTreeMgr.getNodeIdByNodeName(dataSheet.getCell(1, i).getContents().trim()), dataSheet.getCell(2, i).getContents().trim())) != null) {
                        temp.setDept_id(areaDeptTreeMgr.getNodeIdByNodeName(dataSheet.getCell(2, i).getContents().trim()));
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

                    temp.setStart_time(StaticMethod.String2Cal(dataSheet.getCell(3, i).getContents()
                            .trim()).getGregorianChange());
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(4));
                    continue;
                }
                if (dataSheet.getCell(4, i).getContents() != null
                        && !"".equals(dataSheet.getCell(4, i).getContents())) {
                    if (!"".equals(mgr.name2Id(dataSheet.getCell(4, i).getContents().trim(), "11210")) &&
                            (mgr.name2Id(dataSheet.getCell(4, i).getContents().trim(), "11210")) != null) {
                        temp.setPiece(mgr.name2Id(dataSheet.getCell(4, i).getContents().trim(), "11210"));
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
                    temp.setRemark(dataSheet.getCell(5, i).getContents()
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
                mgr.saveTawPartnerCar((TawPartnerCar) formList.get(i));
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
            String url = sysTemPath + "/partner/model/partnercar.xls";
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
     * 分页显示车辆管理列表，支持Atom方式接入Portal
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
    TawPartnerCarMgr tawPartnerCarMgr = (TawPartnerCarMgr) getBean("tawPartnerCarMgr");
    Map map = (Map) tawPartnerCarMgr.getTawPartnerCars(pageIndex, pageSize, "");
    List list = (List) map.get("result");
    TawPartnerCar tawPartnerCar = new TawPartnerCar();

    //创建ATOM源
    Factory factory = Abdera.getNewFactory();
    Feed feed = factory.newFeed();

    // 分页
    for (int i = 0; i < list.size(); i++) {
    tawPartnerCar = (TawPartnerCar) list.get(i);

    // TODO 请按照下面的实例给entry赋值
    Entry entry = feed.insertEntry();
    entry.setTitle("<a href='" + request.getScheme() + "://"
    + request.getServerName() + ":"
    + request.getServerPort()
    + request.getContextPath()
    + "/tawPartnerCar/tawPartnerCars.do?method=edit&id="
    + tawPartnerCar.getId() + "' target='_blank'>"
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