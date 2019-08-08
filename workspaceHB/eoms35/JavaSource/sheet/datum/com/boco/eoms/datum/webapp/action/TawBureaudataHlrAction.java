package com.boco.eoms.datum.webapp.action;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.datum.model.TawBureaudataCityzone;
import com.boco.eoms.datum.model.TawBureaudataHlr;
import com.boco.eoms.datum.service.ITawBureaudataBasicDAOManager;
import com.boco.eoms.datum.service.ITawBureaudataCityzoneManager;
import com.boco.eoms.datum.service.ITawBureaudataHlrDAOManager;
import com.boco.eoms.datum.util.BureauMethod;
import com.boco.eoms.datum.util.DatumWorkSheetSmsServices;
import com.boco.eoms.datum.webapp.form.TawImportExcelForm;
import com.boco.eoms.message.service.impl.MsgServiceImpl;
import com.boco.eoms.sheet.base.sms.WorkSheetSmsServices;

public class TawBureaudataHlrAction extends BaseAction {

    /***************************************************************************
     *
     * 局数据管理源数据导入初始化
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward postexcelfile(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.findForward("postexcelfile");
    }

    /***************************************************************************
     * 局数据管理源数据导入
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward importexcelfile(ActionMapping mapping,
                                         ActionForm form, HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {
        long t = System.currentTimeMillis();
        String bureauId = request.getParameter("bureauId");
        Map map = request.getParameterMap();
        BureauMethod checkexcel = null;
        Set citySet = new HashSet();// 保存地市信息,用于发送短信
        try {
            checkexcel = new BureauMethod();
            TawImportExcelForm objform = (TawImportExcelForm) form;
            FormFile file = objform.getFileName();
            int sheetId = objform.getSheetId(); // 1:未确定HLR归属的号段,2:变更批复表,3:现有HLR和号段的对应
            // 判断所传的文件是否符合标准
            // String result = checkexcel.isStandard(file.getInputStream(),
            // sheetId);
            // if (!result.equalsIgnoreCase("")) {
            // request.setAttribute("error", result);
            // return mapping.findForward("nostandard");
            // }
            List nobelonghlr = null;
            if (sheetId == 1) { // 未确定HLR归属的号段
                try {
                    nobelonghlr = checkexcel.getNobelonghlr(file
                            .getInputStream(), bureauId, citySet);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    request.setAttribute("error", nobelonghlr);
                    return mapping.findForward("nostandard");
                }

            } else if (sheetId == 2) { // 变更批复表
                try {
                    nobelonghlr = checkexcel.getApprove(file.getInputStream(),
                            bureauId, citySet);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    request.setAttribute("error", nobelonghlr);
                    return mapping.findForward("nostandard");
                }
            } else if (sheetId == 3) { // 现有HLR和号段的对应关系
                try {
                    nobelonghlr = checkexcel.getBureaudataHLR(file
                            .getInputStream());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    request.setAttribute("error", nobelonghlr);
                    return mapping.findForward("nostandard");
                }
            }

            //发送短消息
            ITawBureaudataCityzoneManager uersbean = (ITawBureaudataCityzoneManager) ApplicationContextHolder.getInstance().getBean("iTawBureaudataCityzoneManager");
            List list = uersbean.findByAll();
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    TawBureaudataCityzone obj = (TawBureaudataCityzone) list.get(i);
                    String receiverId = obj.getUserid();
                    String sendContent = "请及时处理局数据管理:" + request.getParameter("sendContent");
                    DatumWorkSheetSmsServices services = new DatumWorkSheetSmsServices();
                    services.workHLRM_NON_T("BusinessHlr", "", sendContent, 1, receiverId);
                }
            }

            ITawBureaudataBasicDAOManager base = (ITawBureaudataBasicDAOManager) getBean("iTawBureaudataBasicDAOManager");
            base.saveOrUpdateAll(nobelonghlr);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            return mapping.findForward("nostandard");
        }
        System.out.println("导入过程共花费:" + (System.currentTimeMillis() - t) / 1000
                + "ms!");
        return mapping.findForward("report");
    }

    /***************************************************************************
     * 局数据管理源数据导出
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward exportexcelfile(ActionMapping mapping,
                                         ActionForm form, HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {

        return mapping.findForward("exportexcelfile");
    }

    public ActionForward importexportexcelfile(ActionMapping mapping,
                                               ActionForm form, HttpServletRequest request,
                                               HttpServletResponse response) throws Exception {
        TawSystemSessionForm session = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        ActionForward forward = null;
        String fileName = null;
        String exportType = StaticMethod.null2String(request
                .getParameter("exportType"));
        String bureauId = StaticMethod.null2String(request
                .getParameter("bureauId"));
        ITawBureaudataBasicDAOManager base = (ITawBureaudataBasicDAOManager) getBean("iTawBureaudataBasicDAOManager");
        List excelList = null;
        if (exportType.equals("1")) {

            fileName = URLEncoder.encode(bureauId + ".xls", "UTF-8");
            Map headMap = base.getHeadsegMapForNew(bureauId);
            excelList = base.getNewBureauDataForExcel(headMap, bureauId);
            request.setAttribute("HEADMap", headMap);
            forward = mapping.findForward("segnewexport");

        } else if (exportType.equals("2")) {

            fileName = URLEncoder.encode(bureauId + ".xls", "UTF-8");
            Map headMap = base.getHeadsegMapForCityAdjust(bureauId);
            excelList = base.getCityAdjustBureauDataForExcel(headMap, bureauId);
            request.setAttribute("HEADMap", headMap);
            forward = mapping.findForward("cityadjustexport");

        } else if (exportType.equals("3")) {

            fileName = URLEncoder.encode("HLR归属变更信息表.xls", "UTF-8");
            Map headMap = base.getHeadsegMapForHLRAdjust();
            Map excelMap = base.getHLRAdjustBureauDataForExcel(headMap);
            request.setAttribute("HEADMap", headMap);
            request.setAttribute("EXCELMap", excelMap);
            forward = mapping.findForward("changingexport");

        } else if (exportType.equals("4")) {

            fileName = URLEncoder.encode("归属号段局数据.xls", "UTF-8");
            excelList = base.getAllTawBureaudata();
            forward = mapping.findForward("wholeexport");

        }
        request.setAttribute("EXCELList", excelList);
        request.setAttribute("Creator", session.getUsername());
        response.reset();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename="
                + fileName);
        return forward;
    }

    /***************************************************************************
     * 新增HLR数据初始化
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addinit(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.findForward("add");
    }

    /***************************************************************************
     * 执行添加
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
        // TawSystemSessionForm sessionform = (TawSystemSessionForm) request
        // .getSession().getAttribute("sessionform");
        // String deptId = sessionform.getDeptid();
        ITawBureaudataHlrDAOManager mgr = (ITawBureaudataHlrDAOManager) ApplicationContextHolder
                .getInstance().getBean("iTawBureaudataHlrDAOManager");
        TawBureaudataHlr obj = new TawBureaudataHlr();
        String hlrsignalid = StaticMethod.nullObject2String(request
                .getParameter("hlrsignalid"), "");
        String hlrname = StaticMethod.nullObject2String(request
                .getParameter("hlrname"), "");
        String hlrid = StaticMethod.nullObject2String(request
                .getParameter("hlrid"), "");
        obj.setHlrid(hlrid);
        obj.setHlrsignalid(hlrsignalid);
        obj.setHlrname(hlrname);
        obj.setDeleted(new Integer("0"));
        mgr.saveObject(obj);
        return mapping.findForward("success");
    }

    /***************************************************************************
     * 编辑HLR数据初始化
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward editinit(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String hlrid = StaticMethod.nullObject2String(request
                .getParameter("id"), "");
        ITawBureaudataHlrDAOManager mgr = (ITawBureaudataHlrDAOManager) ApplicationContextHolder
                .getInstance().getBean("iTawBureaudataHlrDAOManager");
        TawBureaudataHlr obj = new TawBureaudataHlr();
        obj = (TawBureaudataHlr) mgr.getObject(hlrid);
        request.setAttribute("bureaudataHlr", obj);
        return mapping.findForward("edit");
    }

    /***************************************************************************
     * 执行修改
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Map map = request.getParameterMap();
        // TawSystemSessionForm sessionform = (TawSystemSessionForm) request
        // .getSession().getAttribute("sessionform");
        // String deptId = sessionform.getDeptid();
        ITawBureaudataHlrDAOManager mgr = (ITawBureaudataHlrDAOManager) ApplicationContextHolder
                .getInstance().getBean("iTawBureaudataHlrDAOManager");
        TawBureaudataHlr obj = new TawBureaudataHlr();
        String id = StaticMethod.nullObject2String(request.getParameter("id"),
                "");
        String hlrsignalid = StaticMethod.nullObject2String(request
                .getParameter("hlrsignalid"), "");
        String hlrname = StaticMethod.nullObject2String(request
                .getParameter("hlrname"), "");
        String hlrid = StaticMethod.nullObject2String(request
                .getParameter("hlrid"), "");
        String remark = StaticMethod.nullObject2String(request
                .getParameter("remark"), "");
        Integer bureauid = StaticMethod.nullObject2Integer(request
                .getParameter("bureauid"));
        Integer belongcityid = StaticMethod.nullObject2Integer(request
                .getParameter("belongcityid"));
        Integer deleted = StaticMethod.nullObject2Integer(request
                .getParameter("deleted"));
        obj.setHlrid(hlrid);
        obj.setHlrsignalid(hlrsignalid);
        obj.setHlrname(hlrname);
        obj.setBureauid(bureauid);
        obj.setBelongcityid(belongcityid);
        obj.setRemark(remark);
        obj.setDeleted(deleted);
        obj.setId(new Integer(id));
        mgr.saveOrUpdate(obj);

        return mapping.findForward("success");
    }

    /***************************************************************************
     * 模糊查询初始化
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

        return mapping.findForward("search");
    }

    /***************************************************************************
     * 执行查询
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward searchDo(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Map map = request.getParameterMap();
        ITawBureaudataHlrDAOManager mgr = (ITawBureaudataHlrDAOManager) getBean("iTawBureaudataHlrDAOManager");
        TawBureaudataHlr obj = new TawBureaudataHlr();
        String hlrsignalid = StaticMethod.nullObject2String(request
                .getParameter("hlrsignalid"), "");
        String hlrname = StaticMethod.nullObject2String(request
                .getParameter("hlrname"), "");
        String hlrid = StaticMethod.nullObject2String(request
                .getParameter("hlrid"), "");
        String deleted = StaticMethod.nullObject2String(request
                .getParameter("deleted"), "");
        obj.setHlrid(hlrid);
        obj.setHlrsignalid(hlrsignalid);
        obj.setHlrname(hlrname);
        if ("".equals(deleted)) {
            obj.setDeleted(null);
        } else {
            obj.setDeleted(new Integer(deleted));
        }
        List list = mgr.getHlrList(obj);

        request.setAttribute("bureaudataHlrList", list);
        // request.setAttribute("pagerHeader", pagerHeader);
        // request.setAttribute("cacheid", cacheid);
        return mapping.findForward("searchDo");
    }

    /***************************************************************************
     * 查询已废弃的HLR数据初始化
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward seinit(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.findForward("seinit");
    }

    /***************************************************************************
     * 查询已废弃的HLR数据
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward seinitDo(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.findForward("seinitDo");
    }

    public ActionForward remove(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Integer id = new Integer(StaticMethod.null2String(request
                .getParameter("id")));
        ITawBureaudataHlrDAOManager mgr = (ITawBureaudataHlrDAOManager) ApplicationContextHolder
                .getInstance().getBean("iTawBureaudataHlrDAOManager");
        mgr.removeObject(TawBureaudataHlr.class, id);
        return mapping.findForward("seinitDo");
    }

    public ActionForward recover(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ITawBureaudataHlrDAOManager mgr = (ITawBureaudataHlrDAOManager) ApplicationContextHolder
                .getInstance().getBean("iTawBureaudataHlrDAOManager");
        TawBureaudataHlr obj = new TawBureaudataHlr();
        String id = StaticMethod.nullObject2String(request.getParameter("id"),
                "");
        String hlrsignalid = StaticMethod.nullObject2String(request
                .getParameter("hlrsignalid"), "");
        String hlrname = StaticMethod.nullObject2String(request
                .getParameter("hlrname"), "");
        String hlrid = StaticMethod.nullObject2String(request
                .getParameter("hlrid"), "");
        String remark = StaticMethod.nullObject2String(request
                .getParameter("remark"), "");
        Integer bureauid = StaticMethod.nullObject2Integer(request
                .getParameter("bureauid"));
        Integer belongcityid = StaticMethod.nullObject2Integer(request
                .getParameter("belongcityid"));
        Integer deleted = StaticMethod.nullObject2Integer(request
                .getParameter("deleted"));
        if (1 == deleted.intValue()) {
            deleted = new Integer(0);
        } else {
            deleted = new Integer(1);
        }
        obj.setHlrid(hlrid);
        obj.setHlrsignalid(hlrsignalid);
        obj.setHlrname(hlrname);
        obj.setBureauid(bureauid);
        obj.setBelongcityid(belongcityid);
        obj.setRemark(remark);
        obj.setDeleted(deleted);
        obj.setId(new Integer(id));
        mgr.saveOrUpdate(obj);
        return mapping.findForward("seinitDo");
    }

    public ActionForward truncate(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.findForward("seinitDo");
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.findForward("seinitDo");
    }

    public ActionForward fileAttachment(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.findForward("fileAttachment");
    }

    public ActionForward checkForm(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ITawBureaudataHlrDAOManager mgr = (ITawBureaudataHlrDAOManager) getBean("iTawBureaudataHlrDAOManager");
        String hlrsignalid = StaticMethod.nullObject2String(request
                .getParameter("hlrSignalId"), "");
        Object obj = mgr.getHlrsignalidObject(hlrsignalid);
        response.setContentType("text/xml;charset=GBK");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        if (obj != null) {
            out.write("0");
        } else {
            out.write("1");
        }
        out.close();
        return null;
    }


}
