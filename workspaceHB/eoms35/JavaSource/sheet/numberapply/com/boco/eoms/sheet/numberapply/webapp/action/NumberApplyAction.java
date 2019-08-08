
package com.boco.eoms.sheet.numberapply.webapp.action;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.statistic.base.util.StatUtil;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.numberapply.model.NumberApplyHlrid;
import com.boco.eoms.sheet.numberapply.model.NumberApplyMain;
import com.boco.eoms.sheet.numberapply.model.NumberApplyMscid;
import com.boco.eoms.sheet.numberapply.service.INumberApplyBatchHlrManager;
import com.boco.eoms.sheet.numberapply.service.INumberApplyBatchMscManager;
import com.boco.eoms.sheet.numberapply.service.INumberApplyHlrManager;
import com.boco.eoms.sheet.numberapply.service.INumberApplyMainManager;
import com.boco.eoms.sheet.numberapply.service.INumberApplyMscManager;
import com.boco.eoms.sheet.numberapply.service.impl.RelationObjectManager;
import com.boco.eoms.sheet.numberapply.util.Constants;
import com.boco.eoms.workplan.util.TawwpUtil;

/**
 * <p>
 * Title:码号资源申请工单
 * </p>
 * <p>
 * Description:码号资源申请工单
 * </p>
 * <p>
 * Mon Sep 27 18:07:01 CST 2010
 * </p>
 *
 * @author liuyang
 * @version 3.5
 */

public class NumberApplyAction extends SheetAction {

    /**
     * showDrawing
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showDrawing(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward("draw");

    }

    /**
     * showPic
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showPic(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward("pic");
    }

    /**
     * showKPI
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showKPI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward("kpi");
    }

    // add by zhouzhe begin 2010-10-14

    /**
     * 新增单条记录(由actionForword判定跳转到那个页面)
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showOneAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String actionForword = StaticMethod.nullObject2String(request.getParameter("actionForword"));
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        request.setAttribute("sheetKey", sheetKey);
        NumberApplyHlrid numberApplyHlrid = new NumberApplyHlrid();
        NumberApplyMscid numberApplyMscid = new NumberApplyMscid();

        request.setAttribute("numberApplyHlrid", numberApplyHlrid);
        request.setAttribute("numberApplyMscid", numberApplyMscid);
        request.setAttribute("type", "new");
        return mapping.findForward(actionForword);
    }

    /**
     * 新增单条记录的保存（由actionForword判断保存的类型和跳转的界面）
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward performSignalSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获得工单的id
        String mainid = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        // 获得跳转的actionForword
        String actionForword = StaticMethod.nullObject2String(request.getParameter("actionForword"));
        String type = StaticMethod.nullObject2String(request.getParameter("type"));

        // 获得界面上的各种属性值(HLR和MSC共有的字段)
        String id = request.getParameter("id");
        String netName = StaticMethod.nullObject2String(request.getParameter("netName"));
        String netId = StaticMethod.nullObject2String(request.getParameter("netId"));
        String netProp = StaticMethod.nullObject2String(request.getParameter("netProp"));
        String buildAddress = StaticMethod.nullObject2String(request.getParameter("buildAddress"));
        String supplier = StaticMethod.nullObject2String(request.getParameter("supplier"));
        String hardwareFlatRoof = StaticMethod.nullObject2String(request.getParameter("hardwareFlatRoof"));
        String softwareVersion = StaticMethod.nullObject2String(request.getParameter("softwareVersion"));
        String capability = StaticMethod.nullObject2String(request.getParameter("capability"));
        String commondLink = StaticMethod.nullObject2String(request.getParameter("commondLink"));
        String portCount = StaticMethod.nullObject2String(request.getParameter("portCount"));
        String coverArea = StaticMethod.nullObject2String(request.getParameter("coverArea"));
        String areaNumber = StaticMethod.nullObject2String(request.getParameter("areaNumber"));
        String city = StaticMethod.nullObject2String(request.getParameter("city"));
        String deviceName = StaticMethod.nullObject2String(request.getParameter("deviceName"));
        String attachArea = StaticMethod.nullObject2String(request.getParameter("attachArea"));
        String fileNumber = StaticMethod.nullObject2String(request.getParameter("fileNumber"));
        if (actionForword.equals("hlrlist")) {
            INumberApplyHlrManager numberApplyHlrManager = (INumberApplyHlrManager) ApplicationContextHolder.getInstance().getBean("iNumberApplyHlrManager");
            NumberApplyHlrid numberApplyHlrid = new NumberApplyHlrid();
            // 将属性封装在类中
            numberApplyHlrid.setId(id);
            numberApplyHlrid.setMainid(mainid);
            numberApplyHlrid.setNetName(netName);
            numberApplyHlrid.setNetId(netId);
            numberApplyHlrid.setNetProp(netProp);
            numberApplyHlrid.setBuildAddress(buildAddress);
            numberApplyHlrid.setSupplier(supplier);
            numberApplyHlrid.setHardwareFlatRoof(hardwareFlatRoof);
            numberApplyHlrid.setSoftwareVersion(softwareVersion);
            numberApplyHlrid.setCapability(capability);
            numberApplyHlrid.setCommondLink(commondLink);
            numberApplyHlrid.setPortCount(portCount);
            numberApplyHlrid.setCoverArea(coverArea);
            numberApplyHlrid.setAreaNumber(areaNumber);
            numberApplyHlrid.setCity(city);
            numberApplyHlrid.setDeviceName(deviceName);
            numberApplyHlrid.setAttachArea(attachArea);
            numberApplyHlrid.setFileNumber(fileNumber);
            numberApplyHlrid.setIsVaild("0");// 初始保存时设置为”0“，”0“表示不生效，当点击派发的时候设置为”1“，表示生效
            // 保存
            numberApplyHlrManager.saveNumberApplyHlrid(numberApplyHlrid);
        } else {
            // 从页面获得属性
            String netType = StaticMethod.nullObject2String(request.getParameter("netType"));
            String connNet = StaticMethod.nullObject2String(request.getParameter("connNet"));
            String equipmentArc = StaticMethod.nullObject2String(request.getParameter("equipmentArc"));
            String iptotalNum = StaticMethod.nullObject2String(request.getParameter("iptotalNum"));
            String caps = StaticMethod.nullObject2String(request.getParameter("caps"));
            String maxSourceNum = StaticMethod.nullObject2String(request.getParameter("maxSourceNum"));
            String maxTargetNum = StaticMethod.nullObject2String(request.getParameter("maxTargetNum"));
            String coverAreaRange = StaticMethod.nullObject2String(request.getParameter("coverAreaRange"));
            String portNum = StaticMethod.nullObject2String(request.getParameter("portNum"));

            INumberApplyMscManager numberApplyMscManager = (INumberApplyMscManager) ApplicationContextHolder.getInstance().getBean("iNumberApplyMscManager");
            NumberApplyMscid numberApplyMscid = new NumberApplyMscid();

            // 封装
            numberApplyMscid.setId(id);
            numberApplyMscid.setMainid(mainid);
            numberApplyMscid.setNetName(netName);
            numberApplyMscid.setNetId(netId);
            numberApplyMscid.setNetProp(netProp);
            numberApplyMscid.setBuildAddress(buildAddress);
            numberApplyMscid.setSupplier(supplier);
            numberApplyMscid.setHardwareFlatRoof(hardwareFlatRoof);
            numberApplyMscid.setSoftwareVersion(softwareVersion);
            numberApplyMscid.setCapability(capability);
            numberApplyMscid.setCommondLink(commondLink);
            numberApplyMscid.setPortCount(portCount);
            numberApplyMscid.setCoverArea(coverArea);
            numberApplyMscid.setAreaNumber(areaNumber);
            numberApplyMscid.setCity(city);
            numberApplyMscid.setDeviceName(deviceName);
            numberApplyMscid.setAttachArea(attachArea);
            numberApplyMscid.setFileNumber(fileNumber);
            numberApplyMscid.setNetType(netType);
            numberApplyMscid.setConnNet(connNet);
            numberApplyMscid.setEquipmentArc(equipmentArc);
            numberApplyMscid.setIptotalNum(iptotalNum);
            numberApplyMscid.setCaps(caps);
            numberApplyMscid.setMaxSourceNum(maxSourceNum);
            numberApplyMscid.setMaxTargetNum(maxTargetNum);
            numberApplyMscid.setCoverAreaRange(coverAreaRange);
            numberApplyMscid.setPortNum(portNum);
            numberApplyMscid.setIsVaild("0");// 初始保存时设置为”0“，”0“表示不生效，当点击派发的时候设置为”1“，表示生效
            // 保存
            numberApplyMscManager.saveNumberApplyMscid(numberApplyMscid);
        }
        request.setAttribute("sheetKey", mainid);
        request.setAttribute("actionForword", actionForword);
        request.setAttribute("type", type);
        return mapping.findForward("hlrsuccess");
    }

    /**
     * 显示动态表的列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 从request中取得工单的id
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        String actionForword = StaticMethod.nullObject2String(request.getParameter("actionForword"));
        String stylepage = StaticMethod.nullObject2String(request.getParameter("stylepage"));
        // 获取每页显示条数
        Integer pageSize = Constants.CURRENT_PAGESIZE;
        // 当前页数
        String pageIndexName = new org.displaytag.util.ParamEncoder("taskList").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageIndex =
                new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        // 先申明一个要查询对象的Map
        Map map = new HashMap();
        // 声明一个List对像
        List showList = new ArrayList();
        // 获得的总条数
        Integer total;
        if (actionForword.equals("hlrlist")) {
            INumberApplyHlrManager numberApplyHlrManager = (INumberApplyHlrManager) ApplicationContextHolder.getInstance().getBean("iNumberApplyHlrManager");
            map = numberApplyHlrManager.getAllNumberApplyHlridByMainid(sheetKey, pageSize, pageIndex);
        } else {
            INumberApplyMscManager numberApplyMscManager = (INumberApplyMscManager) ApplicationContextHolder.getInstance().getBean("iNumberApplyMscManager");
            map = numberApplyMscManager.getAllNumberApplyMscidByMainid(sheetKey, pageSize, pageIndex);
        }
        showList = (List) map.get("taskList");
        total = (Integer) map.get("total");
        // 将分页后的列表写入页面供使用
        request.setAttribute("taskList", showList);
        request.setAttribute("total", total);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("stylepage", stylepage);
        return mapping.findForward(actionForword);
    }

    /**
     * 查询详细信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward performSelect(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 获得主键id
        String id = StaticMethod.nullObject2String(request.getParameter("id"));
        // 获得标识actionForword
        String actionForword = StaticMethod.nullObject2String(request.getParameter("actionForword"));
        if (actionForword.equals("hlrdetail")) {
            INumberApplyHlrManager numberApplyHlrManager = (INumberApplyHlrManager) ApplicationContextHolder.getInstance().getBean("iNumberApplyHlrManager");
            NumberApplyHlrid numberApplyHlrid = new NumberApplyHlrid();
            numberApplyHlrid = numberApplyHlrManager.getNumberApplyHlrid(id);
            request.setAttribute("numberApplyHlrid", numberApplyHlrid);
        } else {
            INumberApplyMscManager numberApplyMscManager = (INumberApplyMscManager) ApplicationContextHolder.getInstance().getBean("iNumberApplyMscManager");
            NumberApplyMscid numberApplyMscid = new NumberApplyMscid();
            numberApplyMscid = numberApplyMscManager.getNumberApplyMscid(id);
            request.setAttribute("numberApplyMscid", numberApplyMscid);
        }
        return mapping.findForward(actionForword);
    }

    public ActionForward performEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获得主键id
        String id = StaticMethod.nullObject2String(request.getParameter("id"));
        // 获得标识actionForword
        String actionForword = StaticMethod.nullObject2String(request.getParameter("actionForword"));
        if (actionForword.equals("hlrnew")) {
            INumberApplyHlrManager numberApplyHlrManager = (INumberApplyHlrManager) ApplicationContextHolder.getInstance().getBean("iNumberApplyHlrManager");
            NumberApplyHlrid numberApplyHlrid = new NumberApplyHlrid();
            numberApplyHlrid = numberApplyHlrManager.getNumberApplyHlrid(id);
            request.setAttribute("sheetKey", numberApplyHlrid.getMainid());
            request.setAttribute("numberApplyHlrid", numberApplyHlrid);
        } else {
            INumberApplyMscManager numberApplyMscManager = (INumberApplyMscManager) ApplicationContextHolder.getInstance().getBean("iNumberApplyMscManager");
            NumberApplyMscid numberApplyMscid = new NumberApplyMscid();
            numberApplyMscid = numberApplyMscManager.getNumberApplyMscid(id);
            request.setAttribute("sheetKey", numberApplyMscid.getMainid());
            request.setAttribute("numberApplyMscid", numberApplyMscid);
        }
        request.setAttribute("type", "edit");
        return mapping.findForward(actionForword);
    }

    /**
     * 删除单条信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward performDel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获得主键id
        String id = StaticMethod.nullObject2String(request.getParameter("id"));
        // 获得标识actionForword
        String actionForword = StaticMethod.nullObject2String(request.getParameter("actionForword"));
        if (actionForword.equals("hlrlist")) {
            INumberApplyHlrManager numberApplyHlrManager = (INumberApplyHlrManager) ApplicationContextHolder.getInstance().getBean("iNumberApplyHlrManager");
            NumberApplyHlrid numberApplyHlrid = new NumberApplyHlrid();
            numberApplyHlrid = numberApplyHlrManager.getNumberApplyHlrid(id);
            numberApplyHlrManager.delNumberApplyHlrid(numberApplyHlrid);
            request.setAttribute("sheetKey", numberApplyHlrid.getMainid());
        } else {
            INumberApplyMscManager numberApplyMscManager = (INumberApplyMscManager) ApplicationContextHolder.getInstance().getBean("iNumberApplyMscManager");
            NumberApplyMscid numberApplyMscid = new NumberApplyMscid();
            numberApplyMscid = numberApplyMscManager.getNumberApplyMscid(id);
            numberApplyMscManager.delNumberApplyMscid(numberApplyMscid);
            request.setAttribute("sheetKey", numberApplyMscid.getMainid());
        }
        request.setAttribute("actionForword", actionForword);
        request.setAttribute("type", "DEL");
        return mapping.findForward("hlrsuccess");
    }

    /**
     * 批量导入新增
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward performBatchAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 决定附件类型（HLR 、MSC）
        String actionForword = StaticMethod.nullObject2String(request.getParameter("actionForword"));
        // 工单主键
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        request.setAttribute("sheetKey", sheetKey);
        request.setAttribute("actionForword", actionForword);
        return mapping.findForward("batchinto");
    }

    /**
     * 解析excel并且保存
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward performBatchSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        String actionForword = (String) request.getParameter("actionForword");
        NumberApplyFrom numberApplyFrom = (NumberApplyFrom) form;
        // 取得上传的文件
        FormFile formFile = numberApplyFrom.getTheFile();
        if (formFile.getFileSize() > 0) {
            if (TawwpUtil.upCase(formFile.getFileName()).endsWith("xls")) {
                // 声明一个List,将取得的数据放进里面
                List resultList = new ArrayList();
                // 把文件读入
                InputStream inputStream = formFile.getInputStream();
                // 得到excel的workbook
                Workbook workbook = Workbook.getWorkbook(inputStream);
                // 得到excel的sheets
                Sheet sheets[] = workbook.getSheets();
                if (actionForword.equals("hlrlist")) {
                    if (sheets.length > 0) {
                        Sheet sheet = sheets[0];
                        // 获得excel的行数
                        int rows = sheet.getRows();
                        // 获得excel的列数
                        int cols = sheet.getColumns();
                        // 循环得到每个单元格中的值
                        for (int i = 2; i < rows; i++) {
                            String supplier = sheet.getCell(5, i).getContents();
                            if ((!sheet.getCell(0, i).isHidden()) && (!("#N/A!").equals(supplier))) {
                                // 每行的值放在一个List里
                                List list = new ArrayList();
                                for (int j = 0; j < cols; j++) {
                                    Cell cell = sheet.getCell(j, i);
                                    String content = cell.getContents();
                                    list.add(content);
                                }
                                resultList.add(list);
                            }
                        }
                    }

                    INumberApplyBatchHlrManager numberApplyBatchHlrManager =
                            (INumberApplyBatchHlrManager) ApplicationContextHolder.getInstance().getBean("iNumberApplyBatchHlrManager");
                    List result = numberApplyBatchHlrManager.batchInsert(sheetKey, resultList);
                    if (result.size() > 0) {
                        request.setAttribute("result", result);
                        request.setAttribute("actionForword", actionForword);
                        request.setAttribute("type", "BatchInsert");
                        request.setAttribute("sheetKey", sheetKey);
                        return mapping.findForward("batchDealError");
                    } else {
                        request.setAttribute("actionForword", actionForword);
                        request.setAttribute("type", "BatchInsert");
                        request.setAttribute("sheetKey", sheetKey);
                        return mapping.findForward("hlrsuccess");
                    }
                } else {
                    if (sheets.length > 0) {
                        Sheet sheet = sheets[0];
                        // 获得excel的行数
                        int rows = sheet.getRows();
                        // 获得excel的列数
                        int cols = sheet.getColumns();
                        // 循环得到每个单元格中的值
                        for (int i = 2; i < rows; i++) {
                            String supplier = sheet.getCell(8, i).getContents();
                            if ((!sheet.getCell(0, i).isHidden()) && (!("#N/A!").equals(supplier))) {
                                // 每行的值放在一个List里
                                List list = new ArrayList();
                                for (int j = 0; j < cols; j++) {
                                    Cell cell = sheet.getCell(j, i);
                                    String content = cell.getContents();
                                    list.add(content);
                                }
                                resultList.add(list);
                            }
                        }
                    }
                    INumberApplyBatchMscManager numberApplyBatchMscManager =
                            (INumberApplyBatchMscManager) ApplicationContextHolder.getInstance().getBean("iNumberApplyBatchMscManager");
                    List result = numberApplyBatchMscManager.batchInsert(sheetKey, resultList);
                    if (result.size() > 0) {
                        request.setAttribute("result", result);
                        request.setAttribute("actionForword", actionForword);
                        request.setAttribute("type", "BatchInsert");
                        request.setAttribute("sheetKey", sheetKey);
                        return mapping.findForward("batchDealError");
                    } else {
                        request.setAttribute("actionForword", actionForword);
                        request.setAttribute("type", "BatchInsert");
                        request.setAttribute("sheetKey", sheetKey);
                        return mapping.findForward("hlrsuccess");
                    }
                }

            } else {
                String message = "文件格式错误，请重新上传";
                request.setAttribute("actionForword", actionForword.startsWith("hlr") ? "hlrnew" : "mscnew");
                request.setAttribute("message", message);
                request.setAttribute("sheetKey", sheetKey);
                request.setAttribute("type", "1");// type 为‘1’时表示格式错误
                return mapping.findForward("batchDealError");
            }
        } else {
            String message = "文件内容为空，请重新上传";
            request.setAttribute("actionForword", actionForword.startsWith("hlr") ? "hlrnew" : "mscnew");
            request.setAttribute("message", message);
            request.setAttribute("sheetKey", sheetKey);
            request.setAttribute("type", "2");// type为‘2’时表示文件内容为空
            return mapping.findForward("batchDealError");
        }
    }

    /**
     * 显示手动分配页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showManual(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获得该条记录的主键id
        String id = StaticMethod.nullObject2String(request.getParameter("id"));
        // 获得标识actionForword
        String actionForword = StaticMethod.nullObject2String(request.getParameter("actionForword"));
        // 工单的主键id
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        // 获得原纪录中24位信令点、14位信令点和id（hlrid、mscid）的值
        String commond24 = StaticMethod.nullObject2String(request.getParameter("commond24"));
        String commond14 = StaticMethod.nullObject2String(request.getParameter("commond14"));
        String commondid = StaticMethod.nullObject2String(request.getParameter("commondid"));

        INumberApplyMainManager numberApplyMainManager = (INumberApplyMainManager) ApplicationContextHolder.getInstance().getBean("iNumberApplyMainManager");
        NumberApplyMain numberApplyMain = new NumberApplyMain();
        numberApplyMain = (NumberApplyMain) numberApplyMainManager.getSingleMainPO(sheetKey);
        String mainResourceType = StaticMethod.nullObject2String(numberApplyMain.getMainResourceType());
        String mainHLRResource = StaticMethod.nullObject2String(numberApplyMain.getMainHLRResource());
        String mainMSCResource = StaticMethod.nullObject2String(numberApplyMain.getMainMSCResource());
        // 获得组合后的字典值
        List dataMoudle = new ArrayList();
        dataMoudle = numberApplyMainManager.analyzeDictionary(mainResourceType, mainHLRResource, mainMSCResource);

        request.setAttribute("id", id);
        request.setAttribute("sheetKey", sheetKey);
        request.setAttribute("dataMoudle", dataMoudle);
        request.setAttribute("actionForword", actionForword);
        request.setAttribute("commond24", commond24);
        request.setAttribute("commond14", commond14);
        request.setAttribute("commondid", commondid);
        request.setAttribute("actionForword", actionForword);
        return mapping.findForward("Manual");
    }

    /**
     * 手动分配
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward performManual(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 每页显示条数
        final Integer pagesize = Constants.CURRENT_PAGESIZE;

        // 获得标识actionForword
        String actionForword = StaticMethod.nullObject2String(request.getParameter("actionForword"));
        // 工单的主键id
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));

        INumberApplyMainManager numberApplyMainManager = (INumberApplyMainManager) ApplicationContextHolder.getInstance().getBean("iNumberApplyMainManager");
        NumberApplyMain numberApplyMain = new NumberApplyMain();
        numberApplyMain = (NumberApplyMain) numberApplyMainManager.getSingleMainPO(sheetKey);
        String mainResourceType = StaticMethod.nullObject2String(numberApplyMain.getMainResourceType());
        String mainHLRResource = StaticMethod.nullObject2String(numberApplyMain.getMainHLRResource());
        String mainMSCResource = StaticMethod.nullObject2String(numberApplyMain.getMainMSCResource());
        List dataMoudle = new ArrayList();

        // 获得配置文件的bean
        RelationObjectManager objectManager = (RelationObjectManager) ApplicationContextHolder.getInstance().getBean("RelationObjectManager");
        Map needQueryObjectMap = new HashMap();
        // 从Spring配置件中取出要查询的对象表
        Map queryObjectMap = objectManager.getFromQueryDataMoudle();
        List list = new ArrayList();
        list = numberApplyMainManager.analyzeDictionary(mainResourceType, mainHLRResource, mainMSCResource);
        for (int i = 0; i < list.size(); i++) {
            // 从queryObjectMap得到要查询的对象
            String queryObjectName = StaticMethod.nullObject2String(queryObjectMap.get(list.get(i)));
            needQueryObjectMap.put(queryObjectName, list.get(i));
            dataMoudle.add(list.get(i));
        }
        // 主要是获得需要查询的Module对象（MSC或HLR） end

        // 得到查询的对像后,从各个表里查出相关数据入到queryData Map 中 start
        // 得到queryManager
        INumberApplyMainManager queryManager = (INumberApplyMainManager) ApplicationContextHolder.getInstance().getBean("iNumberApplyMainManager");
        if (needQueryObjectMap.size() > 0) {
            Set queryObj = needQueryObjectMap.keySet();
            for (Iterator it = queryObj.iterator(); it.hasNext(); ) {
                String objName = (String) it.next();
                String hql = " from " + objName + " where isOccupation = 0";
                String condictions = " where isOccupation = 0";
                String pageIndexName = new org.displaytag.util.ParamEncoder(objName).encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
                final Integer pageIndex =
                        new Integer(
                                GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
                Map map = queryManager.getListByHqlBy(pageIndex, pagesize, hql, condictions, objName);
                // 将查询的结果抛到页面，以供显示list列表，供用户选择
                request.setAttribute(objName, map.get("taskList"));
                request.setAttribute(objName + "size", map.get("total"));
            }
        }
        request.setAttribute("dataMoudle", dataMoudle);
        request.setAttribute("pageSize", pagesize);
        request.setAttribute("actionForword", actionForword);
        return mapping.findForward(actionForword);
    }

    /**
     * 保存手动选择的资源并更新源资源中的状态
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward performManualSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 记录的主键id
        String id = StaticMethod.nullObject2String(request.getParameter("id"));
        // 工单的主键id
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        // 区别msc和hlr的标识
        String actionForword = StaticMethod.nullObject2String(request.getParameter("actionForword"));

        Map map = new HashMap();

        // 重新手动选中的24位信令点、14位信令点和id（hlrid、mscid）的id和值 start
        String radioidId = StaticMethod.nullObject2String(request.getParameter("radioidId"));
        map.put("radioidId", radioidId);
        String radioidValue = StaticMethod.nullObject2String(request.getParameter("radioidValue"));
        map.put("radioidValue", radioidValue);
        String radio24Id = StaticMethod.nullObject2String(request.getParameter("radio24Id"));
        map.put("radio24Id", radio24Id);
        String radio24Value = StaticMethod.nullObject2String(request.getParameter("radio24Value"));
        map.put("radio24Value", radio24Value);
        String radio14Id = StaticMethod.nullObject2String(request.getParameter("radio14Id"));
        map.put("radio14Id", radio14Id);
        String radio14Value = StaticMethod.nullObject2String(request.getParameter("radio14Value"));
        map.put("radio14Value", radio14Value);
        // 重新手动选中的24位信令点、14位信令点和id（hlrid、mscid）的id和值 end

        // 获得原纪录中24位信令点、14位信令点和id（hlrid、mscid）的值 start
        String commond24 = StaticMethod.nullObject2String(request.getParameter("commond24"));
        map.put("commond24", commond24);
        String commond14 = StaticMethod.nullObject2String(request.getParameter("commond14"));
        map.put("commond14", commond14);
        String commondid = StaticMethod.nullObject2String(request.getParameter("commondid"));
        map.put("commondid", commondid);
        // 获得原纪录中24位信令点、14位信令点和id（hlrid、mscid）的值 end

        INumberApplyMainManager queryManager = (INumberApplyMainManager) ApplicationContextHolder.getInstance().getBean("iNumberApplyMainManager");
        RelationObjectManager objectManager = (RelationObjectManager) ApplicationContextHolder.getInstance().getBean("RelationObjectManager");
        NumberApplyMain numberApplyMain = (NumberApplyMain) queryManager.getSingleMainPO(sheetKey);
        String mainResourceType = StaticMethod.nullObject2String(numberApplyMain.getMainResourceType());
        String mainHLRResource = StaticMethod.nullObject2String(numberApplyMain.getMainHLRResource());
        String mainMSCResource = StaticMethod.nullObject2String(numberApplyMain.getMainMSCResource());
        List list = queryManager.analyzeDictionary(mainResourceType, mainHLRResource, mainMSCResource);

        // 要释放的资源的类的map
        Map fromQueryDataMoudle = objectManager.getFromQueryDataMoudle();
        // 释放资源需要更新的列的map
        Map fromUpdateColumn = objectManager.getFromUpdateColumn();
        // 获得手动分配时从页面传来的id的名称map
        Map fromManualId = objectManager.getFromManualId();
        // 获得手动分配时从页面传过来的选中的值的名称的map
        Map fromManualValue = objectManager.getFromManualValue();
        // 根据字典值获得手动分配时从页面传过来的该条记录原来的24位信令点、14位信令点或者是hlrid或mscid的值的名称的map
        Map fromManualResource = objectManager.getFromManualResource();
        // 根据字典值获得的动态表
        Map fromUpdateDynamic = objectManager.getFromUpdateDynamic();
        // 动态表中需要更新的列
        Map neeeUpdateColumn = objectManager.getNeeeUpdateColumn();

        for (int i = 0; i < list.size(); i++) {
            // 释放原来选中的资源 Start
            // 选中资源的id
            String fromManualIdss = StaticMethod.nullObject2String(map.get(fromManualId.get(list.get(i))));
            if (!"".equals(fromManualIdss)) {
                // 更新资源的列的值
                String fromManualResourceValue = StaticMethod.nullObject2String(map.get(fromManualResource.get(list.get(i))));
                // 获得要释放的资源的model类
                String fromQueryDateModels = StaticMethod.nullObject2String(fromQueryDataMoudle.get(list.get(i)));
                // 获得要更新的列
                String fromUpdateColumns = StaticMethod.nullObject2String(fromUpdateColumn.get(list.get(i)));
                queryManager.updateResourceClear(fromQueryDateModels, sheetKey, fromUpdateColumns, fromManualResourceValue);
            }
            // 释放原来选中的资源 end

            // 更新动态表中的值 Start
            // 更新动态表的列的值
            String fromManualValues = StaticMethod.nullObject2String(map.get(fromManualValue.get(list.get(i))));
            if (!"".equals(fromManualValues)) {
                // 类名
                String fromUpdateDynamics = StaticMethod.nullObject2String(fromUpdateDynamic.get(list.get(i)));
                // 动态表中需要更新的列名
                String neeeUpdateColumns = StaticMethod.nullObject2String(neeeUpdateColumn.get(list.get(i)));
                queryManager.updateDynamicModel(fromUpdateDynamics, neeeUpdateColumns, fromManualValues, id);
            }
            // 更新动态表中的值 end

            // 更新资源表中的状态 Start
            // 选中的记录的id
            String fromManualIds = StaticMethod.nullObject2String(map.get(fromManualId.get(list.get(i))));
            if (!"".equals(fromManualIds)) {
                // 需要更新的资源的model类名
                String fromQueryDateModelss = StaticMethod.nullObject2String(fromQueryDataMoudle.get(list.get(i)));
                queryManager.updateResourceOccupied(fromQueryDateModelss, sheetKey, fromManualIds);
            }
            // 更新资源表中的状态 end
        }
        request.setAttribute("sheetKey", sheetKey);
        request.setAttribute("actionForword", actionForword);
        request.setAttribute("type", "manual");
        return mapping.findForward("hlrsuccess");
    }

    /**
     * 自动分配
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward performAutoManual(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 工单的主键id
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        // 跳转的标识
        String actionForword = StaticMethod.nullObject2String(request.getParameter("actionForword"));

        INumberApplyMainManager numberApplyMainManager = (INumberApplyMainManager) ApplicationContextHolder.getInstance().getBean("iNumberApplyMainManager");
        NumberApplyMain numberApplyMain = new NumberApplyMain();
        numberApplyMain = (NumberApplyMain) numberApplyMainManager.getSingleMainPO(sheetKey);
        String mainResourceType = StaticMethod.nullObject2String(numberApplyMain.getMainResourceType());
        String mainHLRResource = StaticMethod.nullObject2String(numberApplyMain.getMainHLRResource());
        String mainMSCResource = StaticMethod.nullObject2String(numberApplyMain.getMainMSCResource());

        // 主要是获得需要查询的Module对象（MSC或HLR）start
        // 获得配置文件的bean
        RelationObjectManager objectManager = (RelationObjectManager) ApplicationContextHolder.getInstance().getBean("RelationObjectManager");
        // 从Spring配置件中取出要查询的对象表
        Map queryObjectMap = objectManager.getFromQueryDataMoudle();
        Map needQueryObjectMap = new HashMap();
        List lists = new ArrayList();

        // 需要更新的列名//key:动态表的对象名 更新的列名（多列）
        Map neeeUpdateColumnMap = objectManager.getNeeeUpdateColumn();
        List needUpdateColumnList = new ArrayList();
        // 获得拆分后的字典值的组合
        lists = numberApplyMainManager.analyzeDictionary(mainResourceType, mainHLRResource, mainMSCResource);
        for (int i = 0; i < lists.size(); i++) {
            String queryObjectName = StaticMethod.nullObject2String(queryObjectMap.get(lists.get(i)));
            // 从Spring配置件中取出要查询的对象表
            needQueryObjectMap.put(queryObjectName, lists.get(i));
            // 需要更新的列名//key:动态表的对象名 更新的列名（多列）
            needUpdateColumnList.add(neeeUpdateColumnMap.get(lists.get(i)));
        }

        // 主要是获得需要查询的Module对象（MSC或HLR） end

        // 得到查询的对像后,从各个表里查出相关数据入到queryData Map 中 start
        // 得到queryManager
        Map queryData = new HashMap();
        INumberApplyMainManager queryManager = (INumberApplyMainManager) ApplicationContextHolder.getInstance().getBean("iNumberApplyMainManager");
        if (needQueryObjectMap.size() > 0) {
            Set queryObj = needQueryObjectMap.keySet();
            for (Iterator it = queryObj.iterator(); it.hasNext(); ) {
                String objName = (String) it.next();
                String hql = " from " + objName + " where isOccupation = 0";
                List list = queryManager.getListByHql(hql);
                queryData.put(objName, list);
            }
        }
        // 得到查询的对象后,从各个表里查出相关数据入到queryData Map 中 end

        // 所有的SQL语句对象名
        List sqlArray = new ArrayList();

        Object[] needUpdateColumnArray = needUpdateColumnList.toArray();

        // 从Spring配置件中取出要查询的对象表和对象Module对象
        Map sydictObjectMap = objectManager.getNeedUpdateObject();
        String moduleObject = StaticMethod.nullObject2String(sydictObjectMap.get(mainResourceType + "Module"));
        String moduleTableName = StaticMethod.nullObject2String(sydictObjectMap.get(mainResourceType + "Table"));

        // 拼写从动态表中查询需要更新的记录的hql
        String hql = " from " + moduleObject + " where mainid = '" + sheetKey + "'and isVaild = 1";

        // 查询需要更新的记录
        List needUpateResources = queryManager.getListByHql(hql);

        // 找到列名更新值所对应的表 key:申请分类＋列名 value：对应表的对象名 + "," + 对象的属性名
        Map valueTableMapping = objectManager.getNeedValueMappingTableColumn();

        // 记录需要更新状态的源数据Id
        Map resourceIdValueMap = new HashMap();

        // 开始拼更新SQL语句
        // 第一层是需要更新的记录
        for (int i = 0; i < needUpateResources.size(); i++) {
            Object needObject = needUpateResources.get(i);
            Map needObjectMap = SheetBeanUtils.bean2Map(needObject);
            StringBuffer sql = new StringBuffer();
            sql.append(" update ").append(moduleTableName).append(" set ");
            boolean isInsert = false;
            // 找到需要更新的对象属性
            for (int j = 0; j < needUpdateColumnArray.length; j++) {
                String needColumanName = (String) needUpdateColumnArray[j];
                String updateTabeObjectName = StaticMethod.nullObject2String(valueTableMapping.get(mainResourceType + needColumanName));
                String[] updateTabeObjectNameArray = updateTabeObjectName.split(",");
                Object obj = queryData.get(updateTabeObjectNameArray[0]);
                if (obj != null) {
                    List valueList = (List) obj;
                    // 转换在map
                    if (valueList.size() > i) {
                        // if (j != 0) {
                        // sql.append(",");
                        // }
                        Object valueObject = valueList.get(i);
                        Map valueObjectMap = SheetBeanUtils.bean2Map(valueObject);
                        // 记录源值的ID
                        if (resourceIdValueMap.get(updateTabeObjectNameArray[0]) == null) {
                            List list = new ArrayList();
                            list.add(valueObjectMap.get("id"));
                            resourceIdValueMap.put(updateTabeObjectNameArray[0], list);
                        } else {
                            List list = (List) resourceIdValueMap.get(updateTabeObjectNameArray[0]);
                            list.add(valueObjectMap.get("id"));
                        }

                        String valueString = StaticMethod.nullObject2String(valueObjectMap.get(updateTabeObjectNameArray[1]));
                        sql.append(needColumanName + " = '" + valueString + "'").append(",");
                        isInsert = true;
                    }

                }
            }

            if (isInsert) {
                String id = StaticMethod.nullObject2String(needObjectMap.get("id"));
                sql.delete(sql.length() - 1, sql.length());
                sql.append(" where ").append(" id = '").append(id).append("'");
                sqlArray.add(sql.toString());
            }

        }

        // 更新之前将源表中的和现工单有关的资源释放，是为了避免用户多次点击自动分配资源造成的资源浪费 Start

        if (needQueryObjectMap.size() > 0) {
            Set queryObj = needQueryObjectMap.keySet();
            for (Iterator it = queryObj.iterator(); it.hasNext(); ) {
                String objName = (String) it.next();
                String hql1 = " update " + objName + " set  isOccupation = 0 ,sheetKey = '' where sheetKey = '" + sheetKey + "'";
                // 执行更新语句
                queryManager.updateModelByHql(hql1);
            }
        }
        // 更新之前将源表中的和现工单有关的资源释放，是为了避免用户多次点击自动分配资源造成的资源浪费 end

        // /resourceIdValueMap
        // 更新源表中的资源的状态
        StringBuffer resourceValueHql = null;
        Set modelKeySet = resourceIdValueMap.keySet();
        Map idRepeatMap = new HashMap();

        for (Iterator it = modelKeySet.iterator(); it.hasNext(); ) {
            String mdoelKey = (String) it.next();
            List ids = (List) resourceIdValueMap.get(mdoelKey);
            if (ids.size() > 0) {
                resourceValueHql = new StringBuffer();
                resourceValueHql.append(" update ").append(mdoelKey).append(" set sheetKey = '").append(sheetKey).append("',").append(" isOccupation = 2 ").append(
                        " where ");
            }
            for (int i = 0; i < ids.size(); i++) {
                if (idRepeatMap.get(ids.get(i)) == null) {
                    if (i > 0) {
                        resourceValueHql.append(" or ");
                    }

                    resourceValueHql.append(" (id = '").append(ids.get(i)).append("')");
                    idRepeatMap.put(ids.get(i), ids.get(i));
                }

            }
            if (ids.size() > 0) {
                // 跟新源model类中的字段的sheetKey和状态的属性，isOccupation
                // 为‘2’表示表示该资源已经被占用但是没有归档，‘0’表示未被占用，‘1’表示已经归档
                queryManager.updateModelByHql(resourceValueHql.toString());
            }

        }
        // 更新动态表中的字段的值
        INumberApplyBatchHlrManager numberApplyBatchHlrManager =
                (INumberApplyBatchHlrManager) ApplicationContextHolder.getInstance().getBean("iNumberApplyBatchHlrManager");
        numberApplyBatchHlrManager.batchUpdate(sqlArray);

        request.setAttribute("actionForword", actionForword);
        return null;
    }

    /**
     * 手动配置中的快速查询
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showQuicQuery(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获得页面的参数的map
        Map condiction = StatUtil.ParameterMapToUtilMap(request.getParameterMap());
        String modelname = StaticMethod.nullObject2String(condiction.get("modelname"));
        String actionForword = StaticMethod.nullObject2String(condiction.get("actionForword"));
        // 获取每页显示条数
        Integer pageSize = Constants.CURRENT_PAGESIZE;
        // 当前页数
        String pageIndexName = new org.displaytag.util.ParamEncoder("taskList").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageIndex =
                new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        // 获得HashMap
        INumberApplyMainManager numberApplyMainManager = (INumberApplyMainManager) ApplicationContextHolder.getInstance().getBean("iNumberApplyMainManager");
        Map map = new HashMap();
        map = numberApplyMainManager.getListByCondiction(pageIndex, pageSize, condiction, modelname);
        List list = new ArrayList();
        list = (List) map.get("taskList");
        Integer total;
        total = (Integer) map.get("total");
        // 放入页面
        request.setAttribute(modelname, list);
        request.setAttribute(modelname + "size", total);
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward(actionForword);
    }

    // add by zhouzhe end 2010-10-14
}
