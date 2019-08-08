/**
 * @author 赵川
 * @version 2.0
 * @see <p>功能描述：用于值班日值、交接班等功能的类。</p>
 */

package com.boco.eoms.duty.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.common.util.CacheManager;
import com.boco.eoms.common.util.Pager;
import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivRegion;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.bo.TawSystemTreeBo;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.duty.bo.TawRmAssignworkBO;
import com.boco.eoms.duty.bo.TawRmRecordBO;
import com.boco.eoms.duty.bo.TawRmRecordPerBO;
import com.boco.eoms.duty.bo.TawRmReliefRecordBO;
import com.boco.eoms.duty.dao.TawConfRoomSheetDAO;
import com.boco.eoms.duty.dao.TawDutySheetDAO;
import com.boco.eoms.duty.dao.TawRmAssignSubDAO;
import com.boco.eoms.duty.dao.TawRmAssignworkDAO;
import com.boco.eoms.duty.dao.TawRmCycleTableDAO;
import com.boco.eoms.duty.dao.TawRmCycleTableSubDAO;
import com.boco.eoms.duty.dao.TawRmDutySheetDao;
import com.boco.eoms.duty.dao.TawRmDutyfileDAO;
import com.boco.eoms.duty.dao.TawRmHangoverDAO;
import com.boco.eoms.duty.dao.TawRmRecordDAO;
import com.boco.eoms.duty.dao.TawRmRecordPerDAO;
import com.boco.eoms.duty.dao.TawRmRecordSubDAO;
import com.boco.eoms.duty.dao.TawRmSysteminfoDAO;
import com.boco.eoms.duty.dao.TawSheetStatDAO;
import com.boco.eoms.duty.dao.TawUserRoomDAO;
import com.boco.eoms.duty.mgr.TawRmThingMgr;
import com.boco.eoms.duty.model.TawDutySheet;
import com.boco.eoms.duty.model.TawRmAssignwork;
import com.boco.eoms.duty.model.TawRmCycleTableSub;
import com.boco.eoms.duty.model.TawRmDoneTask;
import com.boco.eoms.duty.model.TawRmExchangePer;
import com.boco.eoms.duty.model.TawRmHangover;
import com.boco.eoms.duty.model.TawRmRecord;
import com.boco.eoms.duty.model.TawRmRecordSub;
import com.boco.eoms.duty.model.TawRmSysteminfo;
import com.boco.eoms.duty.model.TawRmUnDoneTask;
import com.boco.eoms.duty.service.ITawRmDutyEventManager;
import com.boco.eoms.duty.service.ITawRmExchangePerManager;
import com.boco.eoms.duty.util.CommonTools;
import com.boco.eoms.duty.util.DutyMgrLocator;
import com.boco.eoms.duty.util.StaticDutycheck;
import com.boco.eoms.workbench.netdisk.fileupload.UploadProcessor;
import com.boco.eoms.workbench.netdisk.webapp.util.FilePathProcessor;
import com.boco.eoms.workplan.mgr.ITawwpExecuteMgr;
import com.boco.eoms.commons.system.dict.util.Util;

public class TawRmRecordAction extends Action {
    private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool
            .getInstance();

    private static int PAGE_LENGTH = 20;

    private ITawRmExchangePerManager tawRmExchangePerManager;

    // \u56FD\u9645\u5316
    static {
        ResourceBundle prop = ResourceBundle
                .getBundle("resources.application_zh_CN");
        try {
            PAGE_LENGTH = Integer.parseInt(prop.getString("list.page.length"));
        } catch (Exception e) {
        }
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) {
        ActionForward myforward = null;
        String myaction = mapping.getParameter();

        if (isCancelled(request)) {
            return mapping.findForward("cancel");
        }
        if ("".equalsIgnoreCase(myaction)) {
            myforward = mapping.findForward("failure");
        } else if ("VIEW".equalsIgnoreCase(myaction)) {
            myforward = performView(mapping, form, request, response);
        } else if ("EDIT".equalsIgnoreCase(myaction)) {
            myforward = performEdit(mapping, form, request, response);
        } else if ("ADD".equalsIgnoreCase(myaction)) {
            myforward = performAdd(mapping, form, request, response);
        } else if ("REMOVE".equalsIgnoreCase(myaction)) {
            myforward = performRemove(mapping, form, request, response);
        } else if ("TRASH".equalsIgnoreCase(myaction)) {
            myforward = performTrash(mapping, form, request, response);
        } else if ("LIST".equalsIgnoreCase(myaction)) {
            myforward = performList(mapping, form, request, response);
        } else if ("RECORD".equalsIgnoreCase(myaction)) {
            myforward = performRecord(mapping, form, request, response);
        } else if ("RECORDMAIN".equalsIgnoreCase(myaction)) {
            myforward = performRecordMain(mapping, form, request, response);
        } else if ("YNRECORDMAIN".equalsIgnoreCase(myaction)) {
            myforward = performYnRecordMain(mapping, form, request, response);
        } else if ("SAVEMAIN".equalsIgnoreCase(myaction)) {
            myforward = performSaveMain(mapping, form, request, response);
        } else if ("YNSAVEMAIN".equalsIgnoreCase(myaction)) {
            myforward = performYnSaveMain(mapping, form, request, response);
        } else if ("SAVEPER".equalsIgnoreCase(myaction)) {
            myforward = performSavePer(mapping, form, request, response);
        } else if ("DELETEPER".equalsIgnoreCase(myaction)) {
            myforward = performDeletePer(mapping, form, request, response);
        } else if ("UPDATEPER".equalsIgnoreCase(myaction)) {
            myforward = performUpdatePer(mapping, form, request, response);
        } else if ("GETROOM".equalsIgnoreCase(myaction)) {
            myforward = performGetRoom(mapping, form, request, response);
        } else if ("STATISTICDATE".equalsIgnoreCase(myaction)) {
            myforward = performStatisticDate(mapping, form, request, response);
        } else if ("DUTY".equalsIgnoreCase(myaction)) {
            myforward = performDuty(mapping, form, request, response);
        }
        // BEGIN BY 何毅 20070122
        else if ("CHECKHOLD".equalsIgnoreCase(myaction)) {
            myforward = performCheckHold(mapping, form, request, response);
        } else if ("CHECKDETAIL".equalsIgnoreCase(myaction)) {
            myforward = performCheckDetail(mapping, form, request, response);
        } else if ("CHECKLIST".equalsIgnoreCase(myaction)) {
            myforward = performCheckList(mapping, form, request, response);
        } else if ("ADDONS".equalsIgnoreCase(myaction)) {
            myforward = performAddons(mapping, form, request, response);
        } else if ("GETNODES".equalsIgnoreCase(myaction)) {
            try {
                performgGetNodes(mapping, form, request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if ("UPLOAD".equalsIgnoreCase(myaction)) {
            try {
                myforward = performUpLoad(mapping, form, request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("DOWNLOAD".equalsIgnoreCase(myaction)) {
            myforward = performDownLoad(mapping, form, request, response);
        } else if ("UPLOADDETAIL".equalsIgnoreCase(myaction)) {
            myforward = performUploadDetail(mapping, form, request, response);
        } else if ("UPLOADFINISH".equalsIgnoreCase(myaction)) {
            myforward = performUploadFinish(mapping, form, request, response);
        } else if ("CYCLETABLE".equalsIgnoreCase(myaction)) {
            myforward = performCycleTable(mapping, form, request, response);
        }

        // end by heyi
        /*
         * else if ("SAVEDEF".equalsIgnoreCase(myaction)) { myforward =
         * performSaveDef(mapping, form, request, response); } else if
         * ("SAVEDEFEDIT".equalsIgnoreCase(myaction)) { myforward =
         * performSaveDefEdit(mapping, form, request, response); }
         */
        else if ("SAVEPEREDIT".equalsIgnoreCase(myaction)) {
            myforward = performSavePerEdit(mapping, form, request, response);
        } else if ("DELETEPEREDIT".equalsIgnoreCase(myaction)) {
            myforward = performDeletePerEdit(mapping, form, request, response);
        } else if ("UPDATEPEREDIT".equalsIgnoreCase(myaction)) {
            myforward = performUpdatePerEdit(mapping, form, request, response);
        } else if ("QUERY".equalsIgnoreCase(myaction)) {
            myforward = performQuery(mapping, form, request, response);
        } else if ("SEARCH".equalsIgnoreCase(myaction)) {
            myforward = performSearch(mapping, form, request, response);
        } else if ("SEARCH7DAYS".equalsIgnoreCase(myaction)) {
            myforward = performSearch7Days(mapping, form, request, response);
        } else if ("DEVOLVE".equalsIgnoreCase(myaction)) {
            myforward = performDevolve(mapping, form, request, response);
        } else if ("YNDEVOLVE".equalsIgnoreCase(myaction)) {
            myforward = performYnDevolve(mapping, form, request, response);
        } else if ("COALITION".equalsIgnoreCase(myaction)) {
            myforward = performCoalition(mapping, form, request, response);
        } else if ("OLDCOALITION".equalsIgnoreCase(myaction)) {
            myforward = performOldCoalition(mapping, form, request, response);
        } else if ("EXCHANGE".equalsIgnoreCase(myaction)) {
            myforward = performExchange(mapping, form, request, response);
        } else if ("SUBMIT".equalsIgnoreCase(myaction)) {
            myforward = performSubmit(mapping, form, request, response);
        } else if ("SUBMIT2".equalsIgnoreCase(myaction)) {
            myforward = performSubmit2(mapping, form, request, response);
        } else if ("SUBMIT3".equalsIgnoreCase(myaction)) {
            myforward = performSubmit3(mapping, form, request, response);
        } else if ("VIEWEDIT".equalsIgnoreCase(myaction)) {
            myforward = performViewedit(mapping, form, request, response);
        } else if ("SEARCHEDIT".equalsIgnoreCase(myaction)) {
            myforward = performSearchedit(mapping, form, request, response);
        } else if ("QUERYEDIT".equalsIgnoreCase(myaction)) {
            myforward = performQueryedit(mapping, form, request, response);
        } else if ("SAVEEDIT".equalsIgnoreCase(myaction)) {
            myforward = performSaveedit(mapping, form, request, response);
        } else if ("EDITMAIN".equalsIgnoreCase(myaction)) {
            myforward = performEditMain(mapping, form, request, response);
        } else if ("UPDATEMAIN".equalsIgnoreCase(myaction)) {
            myforward = performUpdateMain(mapping, form, request, response);
        } else if ("QUERYDELETE".equalsIgnoreCase(myaction)) {
            myforward = performQuerydelete(mapping, form, request, response);
        } else if ("SEARCHDELETE".equalsIgnoreCase(myaction)) {
            myforward = performSearchdelete(mapping, form, request, response);
        } else if ("VIEWDELETE".equalsIgnoreCase(myaction)) {
            myforward = performViewdelete(mapping, form, request, response);
        } else if ("DODELETE".equalsIgnoreCase(myaction)) {
            myforward = performDodelete(mapping, form, request, response);
        } else if ("LOGOFF".equalsIgnoreCase(myaction)) {
            myforward = performLogoff(mapping, form, request, response);
        } else if ("STATISTICQUERY".equalsIgnoreCase(myaction)) {
            myforward = performStatisticQuery(mapping, form, request, response);
        } else if ("STATISTICSHOW".equalsIgnoreCase(myaction)) {
            myforward = performStatisticShow(mapping, form, request, response);
        } else if ("SAVEMERGE".equalsIgnoreCase(myaction)) {
            myforward = performSaveMerge(mapping, form, request, response);
        } else if ("VIEWMAIN".equalsIgnoreCase(myaction)) {
            myforward = performViewMain(mapping, form, request, response);
        } else if ("AUDIT".equalsIgnoreCase(myaction)) {
            myforward = performAudit(mapping, form, request, response);
        } else if ("DUTYSTATISTIC".equalsIgnoreCase(myaction)) {
            myforward = performDutyStatistic(mapping, form, request, response);
        } else if ("DUTYSTATISTICSHOW".equalsIgnoreCase(myaction)) {
            myforward = performDutyStatisticShow(mapping, form, request,
                    response);
        } else if ("AUDITBATCH".equalsIgnoreCase(myaction)) {
            myforward = performAuditBatch(mapping, form, request, response);
        } else if ("EXPORT".equalsIgnoreCase(myaction)) { // 值班日志导出
            myforward = performExport(mapping, form, request, response);
        } else if ("ADDONSLIST".equalsIgnoreCase(myaction)) { // 显示列表
            myforward = performAddonsList(mapping, form, request, response);
        } else if ("ADDONSMOVE".equalsIgnoreCase(myaction)) { // 删除
            myforward = performAddonsMove(mapping, form, request, response);
        } else if ("MONTHREPORT".equalsIgnoreCase(myaction)) { // 月报统计
            myforward = performMonthReport(mapping, form, request, response);
        } else if ("REPORT".equalsIgnoreCase(myaction)) { // 月报统计
            myforward = performReport(mapping, form, request, response);
        } else if ("SELECT_ROOM".equalsIgnoreCase(myaction)) { // 未查看接班内容统计
            myforward = performSelectRoom(mapping, form, request, response);
        } else if ("QUERYDUTY".equalsIgnoreCase(myaction)) { // 未查看接班内容统计
            myforward = performQueryDuty(mapping, form, request, response);
        } else if ("QUERYRESULT".equalsIgnoreCase(myaction)) { // 未查看接班内容统计
            myforward = performQueryResult(mapping, form, request, response);
        } else if ("UPDATERECORD".equalsIgnoreCase(myaction)) {
            myforward = performUpdateRecord(mapping, form, request, response);
        } else if ("CHECKROOM".equalsIgnoreCase(myaction)) {
            myforward = performcheckRoom(mapping, form, request, response);
        } else if ("DELETEMAIN".equalsIgnoreCase(myaction)) {
            myforward = performDeleteMain(mapping, form, request, response);
        }

        /*
         * else if ("DUTYCHECK".equalsIgnoreCase(myaction)) { myforward =
         * performDutycheck(mapping, form, request, response); }
         */

        /*
         * else if ("RECORDTABLE".equalsIgnoreCase(myaction)) { myforward =
         * performRecordTable(mapping, form, request, response); }
         */
        else {
            myforward = mapping.findForward("failure");
        }
        return myforward;
    }

    private ActionForward performDuty(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response) {
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        return mapping.findForward("success");
    }

    private ActionForward performUpdateMain(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        TawRmRecord tawRmRecord = null;
        String dutyrecord = null;
        String notes = null;
        // logBO logbo = null;

        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        TawRmRecordForm form = (TawRmRecordForm) actionForm;
        try {
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            tawRmRecord = new TawRmRecord();
            int workserial = form.getId();
            dutyrecord = form.getDutyrecord().trim();
            notes = form.getNotes().trim();
            if (com.boco.eoms.common.util.StaticMethod.getDbType().equals(
                    StaticVariable.ORACLE)) {
                tawRmRecord = tawRmRecordDAO.retrieve(workserial);
            } else {
                dutyrecord = form.getDutyrecord().trim();
                notes = form.getNotes().trim();
                tawRmRecord = tawRmRecordDAO.retrieve1(workserial);
            }
            if (tawRmRecord != null) {
                tawRmRecord.setDutyrecord(dutyrecord);
                tawRmRecord.setNotes(notes);
                tawRmRecordDAO.update(tawRmRecord);
            }
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            tawRmRecord = null;
            dutyrecord = null;
            notes = null;
        }
        return mapping.findForward("success");

    }

    private ActionForward performAudit(ActionMapping mapping,
                                       ActionForm actionForm, HttpServletRequest request,
                                       HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        TawRmRecord tawRmRecord = null;
        // logBO logbo = null;
        String auContent = null;
        String auTime = null;
        String auditor = null;

        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        TawRmRecordForm form = (TawRmRecordForm) actionForm;
        try {
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            tawRmRecord = new TawRmRecord();
            int workserial = form.getId();

            auditor = saveSessionBeanForm.getUserid();
            auTime = StaticMethod.getCurrentDateTime();
            auContent = form.getAuContent();
            if (com.boco.eoms.common.util.StaticMethod.getDbType().equals(
                    StaticVariable.ORACLE)) {
                tawRmRecord = tawRmRecordDAO.retrieve(workserial);
            } else {

                tawRmRecord = tawRmRecordDAO.retrieve1(workserial);
            }
            if (tawRmRecord != null) {
                tawRmRecord.setAuTime(auTime);
                tawRmRecord.setAuditor(auditor);
                tawRmRecord.setAuContent(auContent);
                tawRmRecordDAO.update(tawRmRecord);
            }
            request.setAttribute("id", new Integer(workserial));
            request.setAttribute("setStrutsAction", "1");
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            tawRmRecord = null;
            // logbo = null;
        }
        return mapping.findForward("success");

    }

    private ActionForward performEditMain(ActionMapping mapping,
                                          ActionForm actionForm, HttpServletRequest request,
                                          HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        TawRmRecord tawRmRecord = null;
        TawUserRoomDAO tawUserRoomDAO = null;
        String roomName = null;
        TawRmDutyfileDAO tawRmDutyfileDAO = null;
        Vector vecDutyFile = null;
        List tawRmRecord_dutyman = null;
        // 判断超时
        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        TawRmRecordForm form = (TawRmRecordForm) actionForm;
        try {
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("workserial", id + "");
            tawRmRecord = tawRmRecordDAO.retrieve(id);
            if (tawRmRecord == null) {
                ActionErrors aes = new ActionErrors();
                aes.add(aes.GLOBAL_ERROR, new ActionError(
                        "error.object.notfound", "TawRmRecord"));
                saveErrors(request, aes);
            } else {
                tawRmRecord.setStarttimeDefined("");
                tawRmRecord.setStarttimesub("");
                tawRmRecord.setStatement("");
                tawRmRecord.setEndtimeDefined("");
                tawRmRecord.setDutymansub("");
                tawRmRecord.setDutymaster("");
                tawRmRecord.setDutyrecord(tawRmRecord.getDutyrecord().trim());
                tawRmRecord.setNotes(tawRmRecord.getNotes().trim());

                org.apache.commons.beanutils.BeanUtils.populate(form,
                        org.apache.commons.beanutils.BeanUtils
                                .describe(tawRmRecord));
                // 机房名称
                int roomid = tawRmRecord.getRoomId();
                tawUserRoomDAO = new TawUserRoomDAO(ds);
                roomName = tawUserRoomDAO.getRoomName(roomid);
                request.setAttribute("roomName", roomName);
                // 完成状态
                int intFlag = tawRmRecord.getFlag();
                request.setAttribute("intFlag", String.valueOf(intFlag));
                // 附件
                tawRmDutyfileDAO = new TawRmDutyfileDAO(ds);
                vecDutyFile = tawRmDutyfileDAO.getDutyFile(id);
                request.setAttribute("vecDutyFile", vecDutyFile);
                // 值班人
                tawRmRecord_dutyman = tawRmRecordDAO.listDutyman(id);
                request
                        .setAttribute("TAWRMRECORD_DUTYMAN",
                                tawRmRecord_dutyman);
                // 遗留问题
                TawRmHangoverDAO tawRmHangoverDAO = new TawRmHangoverDAO(ds);
                TawRmHangover tawRmHangover = tawRmHangoverDAO.retrieve(id, 0);
                String strHangQuestion = "";
                if (tawRmHangover != null) {
                    strHangQuestion = tawRmHangover.getHangQuestion();
                }
                request.setAttribute("strHangQuestion", strHangQuestion);
            }
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            tawRmRecord = null;
            tawUserRoomDAO = null;
            roomName = null;
            tawRmDutyfileDAO = null;
            vecDutyFile = null;
            tawRmRecord_dutyman = null;
        }
        return mapping.findForward("success");

    }

    private ActionForward performUpdatePerEdit(ActionMapping mapping,
                                               ActionForm actionForm, HttpServletRequest request,
                                               HttpServletResponse response) {

        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        String Dutyrecord = null;
        String DutyrecordId = null;
        // 判断超时
        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {
            TawRmRecordForm tawRmRecordForm = (TawRmRecordForm) actionForm;
            if (tawRmRecordForm.getId() == 0) {
                int intWorkserial = Integer.parseInt(request
                        .getParameter("WORKSERIAL"));
                tawRmRecordForm.setId(intWorkserial);
            }
            int workserial = tawRmRecordForm.getId();
            // 定义数据库连接和DAO
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            int record_id = Integer.parseInt(String.valueOf(request
                    .getParameter("record_id")));
            DutyrecordId = "Dutyrecord" + String.valueOf(record_id);
            Dutyrecord = String.valueOf(request.getParameter(DutyrecordId))
                    .trim();
            String CompleteFlag = "complete_flag" + String.valueOf(record_id);
            int complete_flag = Integer.parseInt(request
                    .getParameter(CompleteFlag));
            TawRmRecordPerDAO tawRmRecordPerDAO = new TawRmRecordPerDAO(ds);
            java.util.Date date = new java.util.Date();
            java.text.SimpleDateFormat dtFormat = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            String strDate = dtFormat.format(date);
            tawRmRecordPerDAO.updatePerRecord(record_id, Dutyrecord,
                    complete_flag, saveSessionBeanForm.getUserid(),
                    saveSessionBeanForm.getUsername(), strDate);
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
        }
        return mapping.findForward("success");
    }

    private ActionForward performDeletePerEdit(ActionMapping mapping,
                                               ActionForm actionForm, HttpServletRequest request,
                                               HttpServletResponse response) {

        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        // 判断超时
        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {
            TawRmRecordForm tawRmRecordForm = (TawRmRecordForm) actionForm;
            if (tawRmRecordForm.getId() == 0) {
                int intWorkserial = Integer.parseInt(request
                        .getParameter("WORKSERIAL"));
                tawRmRecordForm.setId(intWorkserial);
            }
            int workserial = tawRmRecordForm.getId();
            // 定义数据库连接和DAO
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            int record_id = Integer.parseInt(String.valueOf(request
                    .getParameter("record_id")));
            TawRmRecordPerDAO tawRmRecordPerDAO = new TawRmRecordPerDAO(ds);
            tawRmRecordPerDAO.deletePerRecord(record_id);
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;

        }

        return mapping.findForward("success");
    }

    private ActionForward performSavePerEdit(ActionMapping mapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) {

        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        String strDate = null;
        String DutyMan = null;
        String DutyManName = null;
        String Dutyrecord = null;
        // 判断超时
        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {
            TawRmRecordForm tawRmRecordForm = (TawRmRecordForm) actionForm;
            if (tawRmRecordForm.getId() == 0) {
                int intWorkserial = Integer.parseInt(request
                        .getParameter("WORKSERIAL"));
                tawRmRecordForm.setId(intWorkserial);
            }
            int workserial = tawRmRecordForm.getId();
            // 定义数据库连接和DAO
            tawRmRecordDAO = new TawRmRecordDAO(ds);

            // 2003-12-20 , zc, 修改时间格式以满足unix系统
            java.util.Date date = new java.util.Date();
            java.text.SimpleDateFormat dtFormat = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            strDate = dtFormat.format(date);

            DutyMan = saveSessionBeanForm.getUserid();
            DutyManName = (saveSessionBeanForm.getUsername());
            Dutyrecord = String.valueOf(request.getParameter("Dutyrecord"))
                    .trim();
            int complete_flag = 1;
            TawRmRecordPerDAO tawRmRecordPerDAO = new TawRmRecordPerDAO(ds);
            String typename = String.valueOf(request.getParameter("typename"))
                    .trim();
            tawRmRecordPerDAO.insertPerRecord(workserial, strDate, DutyMan,
                    DutyManName, Dutyrecord, complete_flag, "", typename, "");

        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            strDate = null;
            DutyMan = null;
            DutyManName = null;
            Dutyrecord = null;
        }

        return mapping.findForward("success");
    }

    private ActionForward performUpdatePer(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {

        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        String Dutyrecord = null;
        String DutyrecordId = null;
        // 判断超时
        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {
            TawRmRecordForm tawRmRecordForm = (TawRmRecordForm) actionForm;
            if (tawRmRecordForm.getId() == 0) {
                int intWorkserial = Integer.parseInt(request
                        .getParameter("WORKSERIAL"));
                tawRmRecordForm.setId(intWorkserial);
            }
            int workserial = tawRmRecordForm.getId();
            // 定义数据库连接和DAO
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            int record_id = Integer.parseInt(String.valueOf(request
                    .getParameter("record_id")));
            DutyrecordId = "Dutyrecord" + String.valueOf(record_id);
            Dutyrecord = String.valueOf(request.getParameter(DutyrecordId))
                    .trim();
            String CompleteFlag = "complete_flag" + String.valueOf(record_id);
            int complete_flag = Integer.parseInt(request
                    .getParameter(CompleteFlag));
            TawRmRecordPerDAO tawRmRecordPerDAO = new TawRmRecordPerDAO(ds);
            java.util.Date date = new java.util.Date();
            java.text.SimpleDateFormat dtFormat = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            String strDate = dtFormat.format(date);
            System.out.println(saveSessionBeanForm.getUsername());
            tawRmRecordPerDAO.updatePerRecord(record_id, Dutyrecord,
                    complete_flag, saveSessionBeanForm.getUserid(),
                    saveSessionBeanForm.getUsername(), strDate);
            System.out.println(saveSessionBeanForm.getUsername());
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;

        }

        return mapping.findForward("success");
    }

    private ActionForward performDeletePer(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {

        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        // 判断超时
        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {
            TawRmRecordForm tawRmRecordForm = (TawRmRecordForm) actionForm;
            if (tawRmRecordForm.getId() == 0) {
                int intWorkserial = Integer.parseInt(request
                        .getParameter("WORKSERIAL"));
                tawRmRecordForm.setId(intWorkserial);
            }
            int workserial = tawRmRecordForm.getId();
            // 定义数据库连接和DAO
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            int record_id = Integer.parseInt(String.valueOf(request
                    .getParameter("record_id")));
            TawRmRecordPerDAO tawRmRecordPerDAO = new TawRmRecordPerDAO(ds);
            tawRmRecordPerDAO.deletePerRecord(record_id);
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;

        }

        return mapping.findForward("success");
    }

    private ActionForward performSavePer(ActionMapping mapping,
                                         ActionForm actionForm, HttpServletRequest request,
                                         HttpServletResponse response) {

        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        String strDate = null;
        String DutyMan = null;
        String DutyManName = null;
        String Dutyrecord = null;
        // 判断超时
        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {
            TawRmRecordForm tawRmRecordForm = (TawRmRecordForm) actionForm;
            if (tawRmRecordForm.getId() == 0) {
                int intWorkserial = Integer.parseInt(request
                        .getParameter("WORKSERIAL"));
                tawRmRecordForm.setId(intWorkserial);
            }
            int workserial = tawRmRecordForm.getId();
            // 定义数据库连接和DAO
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            // 2003-12-20 , zc, 修改时间格式以满足unix系统
            java.util.Date date = new java.util.Date();
            java.text.SimpleDateFormat dtFormat = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            strDate = dtFormat.format(date);

            DutyMan = saveSessionBeanForm.getUserid();
            DutyManName = saveSessionBeanForm.getUsername();
            Dutyrecord = String.valueOf(request.getParameter("Dutyrecord"))
                    .trim();
            int complete_flag = 1;
            TawRmRecordPerDAO tawRmRecordPerDAO = new TawRmRecordPerDAO(ds);
            // String typename=StaticMethod.PageNStrRev("值班");
            String typename = String.valueOf(request.getParameter("typename"))
                    .trim();
            tawRmRecordPerDAO.insertPerRecord(workserial, strDate, DutyMan,
                    DutyManName, Dutyrecord, complete_flag, "", typename, "");
            TawRmRecordPerBO tawRmRecordPerBo = new TawRmRecordPerBO();
            // 模拟调用与工单和作业计划的接口(之后要删除)
            // tawRmRecordPerBo.PassToDuty(workserial,DutyMan,DutyManName,"test","testadd","http://",1,"111");
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            strDate = null;
            DutyMan = null;
            DutyManName = null;
            Dutyrecord = null;
        }

        return mapping.findForward("success");
    }

    private ActionForward performSaveSubEdit(ActionMapping mapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) {

        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        String strDate = null;
        String DutyMan = null;
        String DutyManName = null;
        String Dutyrecord = null;
        // 判断超时
        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {
            TawRmRecordForm tawRmRecordForm = (TawRmRecordForm) actionForm;
            if (tawRmRecordForm.getId() == 0) {
                int intWorkserial = Integer.parseInt(request
                        .getParameter("WORKSERIAL"));
                tawRmRecordForm.setId(intWorkserial);
            }
            int workserial = tawRmRecordForm.getId();
            // 定义数据库连接和DAO
            tawRmRecordDAO = new TawRmRecordDAO(ds);

            // 2003-12-20 , zc, 修改时间格式以满足unix系统
            java.util.Date date = new java.util.Date();
            java.text.SimpleDateFormat dtFormat = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            strDate = dtFormat.format(date);

            DutyMan = saveSessionBeanForm.getUserid();
            DutyManName = com.boco.eoms.common.util.StaticMethod
                    .dbNStrRev(saveSessionBeanForm.getUsername());
            Dutyrecord = String.valueOf(request.getParameter("Dutyrecord"))
                    .trim();
            int complete_flag = Integer.parseInt(request
                    .getParameter("complete_flag"));
            TawRmRecordPerDAO tawRmRecordPerDAO = new TawRmRecordPerDAO(ds);
            String typename = String.valueOf(request.getParameter("typename"))
                    .trim();
            tawRmRecordPerDAO.insertPerRecord(workserial, strDate, DutyMan,
                    DutyManName, Dutyrecord, complete_flag, "", typename, "");
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            strDate = null;
            DutyMan = null;
            DutyManName = null;
            Dutyrecord = null;
        }

        return mapping.findForward("success");
    }

    /*
     * private ActionForward performSaveDef(ActionMapping mapping, ActionForm
     * actionForm, HttpServletRequest request, HttpServletResponse response) {
     * SaveSessionBeanForm saveSessionBeanForm = null; TawRmRecordDAO
     * tawRmRecordDAO = null; Vector vecDefId = null; String Duty_record = null;
     * String DutyRecordId = null; TawRmRecordLockDAO tawRmRecordLockDAO = null;
     * //判断超时 saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
     * getAttribute("SaveSessionBeanForm"); if (saveSessionBeanForm == null) {
     * return mapping.findForward("timeout"); } try { TawRmRecordForm
     * tawRmRecordForm = (TawRmRecordForm) actionForm; if
     * (tawRmRecordForm.getId() == 0) { int intWorkserial =
     * Integer.parseInt(request.getParameter("WORKSERIAL"));
     * tawRmRecordForm.setId(intWorkserial); } int workserial =
     * tawRmRecordForm.getId(); //定义数据库连接和DAO tawRmRecordDAO = new
     * TawRmRecordDAO(ds); TawRmDefinemainDAO tawRmDefinemainDAO = new
     * TawRmDefinemainDAO(ds); vecDefId =
     * tawRmDefinemainDAO.getVecDefId(workserial); for (int i = 0; i <
     * vecDefId.size(); i++) { Duty_record = ""; DutyRecordId = "DutyRecord" +
     * String.valueOf(vecDefId.elementAt(i)); Duty_record =
     * String.valueOf(request.getParameter(DutyRecordId)).trim();
     * tawRmDefinemainDAO.updateDefRecord(Integer.parseInt(String.valueOf(
     * vecDefId.elementAt(i))), Duty_record); } //zc2004-03-08修改 //删除记录消息
     * tawRmRecordLockDAO = new TawRmRecordLockDAO(ds);
     * tawRmRecordLockDAO.insert_lock(saveSessionBeanForm.getWorkSerial(),
     * saveSessionBeanForm.getWrf_UserID(),
     * saveSessionBeanForm.getWrf_UserName());
     * //////////////////////////////////////////// } catch (Exception e) {
     * generalError(request, e); return mapping.findForward("failure"); }
     * finally { saveSessionBeanForm = null; tawRmRecordDAO = null;
     * tawRmRecordLockDAO = null; } return mapping.findForward("success"); }
     */

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 编辑值班记录查询界面
     */
    private ActionForward performQueryedit(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        Vector SelectRoom = null;
        Vector SelectRoomName = null;
        TawRmAssignworkBO tawRmAssignworkBO = null;
        // edit by wangheqi
        TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
        TawSystemCptroom tawApparatusroom = null;

        // TawApparatusroomDAO tawApparatusroomDAO = null;
        // TawApparatusroom tawApparatusroom = null;
        // edit by wangheqi
        TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();
        ;
        // TawValidatePrivBO tawValidatePrivBO = null;
        String strSelectRoomName = null;

        try {
            String roomId = request.getParameter("typeId");
            request.setAttribute("roomId", roomId);
            /*
             * SelectRoom = new Vector(); SelectRoomName = new Vector();
             *
             * if (saveSessionBeanForm.getUserid().equals(StaticVariable.ADMIN)) {
             * tawRmAssignworkBO = new TawRmAssignworkBO(ds); SelectRoom =
             * tawRmAssignworkBO.getRoomSelect(); } else { // tawValidatePrivBO =
             * new TawValidatePrivBO(ds); // SelectRoom = //
             * tawValidatePrivBO.validatePriv(saveSessionBeanForm.getWrf_UserID(), //
             * 2003, 2); SelectRoom = StaticMethod .list2vector(privBO
             * .getPermissions( saveSessionBeanForm.getUserid(),
             * com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,
             * com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM)); } //
             * zc 3-5 增加功能，满足本机房人员可以进行查询操作的要求 if (SelectRoom.size() == 0) {
             * String roomId = String.valueOf(saveSessionBeanForm.getRoomId());
             * if (!roomId.equals("0")) { SelectRoom.add(roomId); } } // if
             * (SelectRoom.size() > 0) { // tawApparatusroomDAO = new
             * TawApparatusroomDAO(ds); tawApparatusroom = null;
             * strSelectRoomName = ""; Vector removeEle = new Vector(); for (int
             * i = 0; i < SelectRoom.size(); i++) { tawApparatusroom = cptroomBO
             * .getTawSystemCptroomById(new Integer(String
             * .valueOf(SelectRoom.elementAt(i))), 0); if (tawApparatusroom !=
             * null) { strSelectRoomName = StaticMethod
             * .null2String(tawApparatusroom.getRoomname());
             * SelectRoomName.add(strSelectRoomName); } else {
             * removeEle.add(SelectRoom.elementAt(i)); } }
             * SelectRoom.removeAll(removeEle); } else { return
             * mapping.findForward("nopriv"); }
             * request.setAttribute("SelectRoom", SelectRoom);
             * request.setAttribute("SelectRoomName", SelectRoomName);
             */
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            SelectRoom = null;
            SelectRoomName = null;
            tawRmAssignworkBO = null;
            cptroomBO = null;
            // tawApparatusroomDAO = null;
            tawApparatusroom = null;
            strSelectRoomName = null;
            privBO = null;
            // tawValidatePrivBO = null;
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 值班记录查询界面
     */
    private ActionForward performQuery(ActionMapping mapping,
                                       ActionForm actionForm, HttpServletRequest request,
                                       HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        Vector SelectRoom = null;
        Vector SelectRoomName = null;
        TawRmAssignworkBO tawRmAssignworkBO = null;
        // edit by wangheqi
        TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();
        // TawValidatePrivBO tawValidatePrivBO = null;
        // edit by wangheqi
        TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
        TawSystemCptroom tawApparatusroom = null;

        // TawApparatusroomDAO tawApparatusroomDAO = null;
        // TawApparatusroom tawApparatusroom = null;
        String strSelectRoomName = null;

        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {

            String roomId = request.getParameter("typeId");
            request.setAttribute("roomId", roomId);
            /*
             * if (saveSessionBeanForm.getUserid().equals(StaticVariable.ADMIN)) {
             * tawRmAssignworkBO = new TawRmAssignworkBO(ds); SelectRoom =
             * tawRmAssignworkBO.getRoomSelect(); } else { // tawValidatePrivBO =
             * new TawValidatePrivBO(ds); // SelectRoom = //
             * tawValidatePrivBO.validatePriv(saveSessionBeanForm.getWrf_UserID(), //
             * 2002, 2); SelectRoom = StaticMethod .list2vector(privBO
             * .getPermissions( saveSessionBeanForm.getUserid(),
             * com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,
             * com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM)); } //
             * zc 3-5 增加功能，满足本机房人员可以进行查询操作的要求 if (SelectRoom.size() == 0) {
             * String roomId = String.valueOf(saveSessionBeanForm.getRoomId());
             * if (!roomId.equals("0")) { SelectRoom.add(roomId); } } // if
             * (SelectRoom.size() > 0) { // tawApparatusroomDAO = new
             * TawApparatusroomDAO(ds); // tawApparatusroom = null;
             * strSelectRoomName = ""; Vector removeEle = new Vector(); for (int
             * i = 0; i < SelectRoom.size(); i++) { tawApparatusroom = cptroomBO
             * .getTawSystemCptroomById(new Integer(String
             * .valueOf(SelectRoom.elementAt(i))), 0); if (tawApparatusroom !=
             * null) { strSelectRoomName = StaticMethod
             * .null2String(tawApparatusroom.getRoomname());
             * SelectRoomName.add(strSelectRoomName); } else {
             * removeEle.add(SelectRoom.elementAt(i)); } }
             * SelectRoom.removeAll(removeEle); } else { return
             * mapping.findForward("nopriv"); }
             */

        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            SelectRoom = null;
            SelectRoomName = null;
            tawRmAssignworkBO = null;
            privBO = null;
            // tawValidatePrivBO = null;
            cptroomBO = null;
            // tawApparatusroomDAO = null;
            tawApparatusroom = null;
            strSelectRoomName = null;
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 值班记录查询列表
     */
    private ActionForward performSearch(ActionMapping mapping,
                                        ActionForm actionForm, HttpServletRequest request,
                                        HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        String pageOffset = null;
        String starttime = null;
        String endtime = null;
        String paramCondition = null;
        String strCondition = null;
        List tawRmRecords = null;
        String objKey = null;
        String url = null;
        String pagerHeader = null;
        String dutyRecord = null;

        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {

            tawRmRecordDAO = new TawRmRecordDAO(ds);

            int offset;
            int length = PAGE_LENGTH;
            pageOffset = request.getParameter("pager.offset");
            if (pageOffset == null || pageOffset.equals("")) {
                offset = 0;
            } else {
                offset = Integer.parseInt(pageOffset);
            }

            dutyRecord = com.boco.eoms.common.util.StaticMethod
                    .null2String((request.getParameter("dutyRecord")));
            starttime = request.getParameter("starttime");
            endtime = request.getParameter("endtime");
            paramCondition = "starttime=" + starttime + "&endtime=" + endtime
                    + "&roomId="
                    + String.valueOf(request.getParameter("roomId"));
            // zc 3-5 增加功能，满足可以根据值班记录匹配查询操作的要求

            if (!dutyRecord.equals("")) {
                paramCondition = paramCondition + "&dutyRecord=" + dutyRecord;
            }
            // /////////////////////////////////////////////////
            strCondition = "where 1=1 ";
            if (starttime != null && !(starttime.trim().equals(""))) {
                starttime = starttime + " 00:00:00";
                strCondition = strCondition + "and starttime >= '" + starttime
                        + "'";
            }
            if (endtime != null && !(endtime.trim().equals(""))) {
                endtime = endtime + " 23:59:59";
                strCondition = strCondition + "and endtime<= '" + endtime + "'";
            }
            // strCondition = " where starttime >= '" + starttime +"' and
            // starttime <= '" + endtime + "' ";
            int roomId = Integer.parseInt(request.getParameter("roomId"));
            if (roomId > 0) {
                strCondition = strCondition + " and room_id = " + roomId + " ";
            } else {
                strCondition = strCondition + " ";
            }
            // zc 3-5 增加功能，满足可以根据值班记录匹配查询操作的要求
            if (!dutyRecord.equals("")) {
                strCondition = strCondition + " and dutyrecord like '%"
                        + dutyRecord + "%'";
            }
            // /////////////////////////////////////////////////

            tawRmRecords = tawRmRecordDAO.search(offset, length, strCondition);
            request.setAttribute("strCondition", strCondition);
            String[] objKeys = {"TawRmRecord", "search"};
            objKey = CacheManager.createKey(objKeys);

            Integer size = (Integer) SizeCacheManager.getCache(objKey);
            if (size == null) {
                size = new Integer(tawRmRecordDAO.getSize("taw_rm_record",
                        strCondition));
                SizeCacheManager.putCache(size, objKey, 0);
            }
            int intSize = tawRmRecordDAO.getSize("taw_rm_record", strCondition);
            url = request.getContextPath() + "/duty" + mapping.getPath()
                    + ".do";

            pagerHeader = Pager.generate(offset, intSize, length, url,
                    paramCondition);
            request.setAttribute("pagerHeader", pagerHeader);
            request.setAttribute("TAWRMRECORDS", tawRmRecords);
            request.setAttribute("AUDITPRIV", String.valueOf(tawRmRecordDAO
                    .getAuditPriv(saveSessionBeanForm.getUserid(), roomId)));
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            pageOffset = null;
            starttime = null;
            endtime = null;
            paramCondition = null;
            strCondition = null;
            tawRmRecords = null;
            objKey = null;
            url = null;
            pagerHeader = null;
        }
        return mapping.findForward("success");
    }

    private ActionForward performSearch7Days(ActionMapping mapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;

        List tawRmRecords = null;

        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            tawRmRecords = tawRmRecordDAO.search7Days(Integer
                    .parseInt(saveSessionBeanForm.getDeptid()));

            request.setAttribute("TAWRMRECORDS", tawRmRecords);
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 值班记录编辑查询列表
     */
    private ActionForward performSearchedit(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        String pageOffset = null;
        String starttime = null;
        String endtime = null;
        String paramCondition = null;
        String strCondition = null;
        // edit by wangheqi
        TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();

        // TawValidatePrivBO tawValidatePrivBO = null;
        List tawRmRecords = null;
        String objKey = null;
        String url = null;
        String pagerHeader = null;

        // saveSessionBeanForm =
        // (SaveSessionBeanForm) request.getSession().getAttribute(
        // "SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {

            tawRmRecordDAO = new TawRmRecordDAO(ds);

            int offset;
            int length = PAGE_LENGTH;
            pageOffset = request.getParameter("pager.offset");
            if (pageOffset == null || pageOffset.equals("")) {
                offset = 0;
            } else {
                offset = Integer.parseInt(pageOffset);
            }

            starttime = request.getParameter("starttime");
            endtime = request.getParameter("endtime");
            String start_time = "";
            String end_time = "";
            String time_fromyear = request.getParameter("starttimeyear");
            String time_frommonth = request.getParameter("starttimemonth");

            String time_fromday = request.getParameter("starttimeday");

            start_time = time_fromyear + "-" + time_frommonth + "-"
                    + time_fromday;
            String time_toyear = request.getParameter("endtimeyear");
            String time_tomonth = request.getParameter("endtimemonth");
            String time_today = request.getParameter("endtimeday");

            end_time = time_toyear + "-" + time_tomonth + "-" + time_today;
            paramCondition = "starttime=" + starttime + "&endtime=" + endtime
                    + "&roomId="
                    + String.valueOf(request.getParameter("roomId"));
            starttime = starttime + " 00:00:00";
            start_time = start_time + " 00:00:00";
            end_time = end_time + " 23:59:59";
            endtime = endtime + " 23:59:59";
            strCondition = " where starttime >= '" + starttime
                    + "' and starttime <= '" + endtime + "' ";
            int roomId = Integer.parseInt(request.getParameter("roomId"));
            if (roomId > 0) {
                strCondition = strCondition + " and room_id = " + roomId;
            }
            // tawValidatePrivBO = new TawValidatePrivBO(ds);
            // if(!tawValidatePrivBO.validatePriv(saveSessionBeanForm.getWrf_UserID(),2003,roomId,2)){
            if (/*
             * !tawValidatePrivBO.validatePriv(saveSessionBeanForm.getUserid(),
             * mapping.getPath(), roomId)) {
             */
                    !privBO.hasPermission(saveSessionBeanForm.getUserid(), mapping
                            .getPath(), String.valueOf(roomId))) {
                strCondition = strCondition
                        + " and id in (select id from taw_rm_assignwork where"
                        + " dutymaster = '" + saveSessionBeanForm.getUserid()
                        + "' and room_id = " + roomId
                        + " and starttime_defined >= '" + starttime
                        + "' and starttime_defined <= '" + endtime + "' )";
            }
            strCondition = strCondition + " ";
            // System.out.println("starttime;"+starttime);
            // System.out.println("endtime:"+endtime);
            // int flag = Integer.parseInt(request.getParameter("flag"));

            tawRmRecords = tawRmRecordDAO.search(offset, length, strCondition);

            String[] objKeys = {"TawRmRecord", "search"};
            objKey = CacheManager.createKey(objKeys);

            Integer size = (Integer) SizeCacheManager.getCache(objKey);
            if (size == null) {
                size = new Integer(tawRmRecordDAO.getSize("taw_rm_record",
                        strCondition));
                SizeCacheManager.putCache(size, objKey, 0);
            }
            int intSize = tawRmRecordDAO.getSize("taw_rm_record", strCondition);
            url = request.getContextPath() + "/duty" + mapping.getPath()
                    + ".do";
            // String pagerHeader = Pager.generate(offset, size.intValue(),
            // length, url);

            pagerHeader = Pager.generate(offset, intSize, length, url,
                    paramCondition);
            request.setAttribute("pagerHeader", pagerHeader);

            request.setAttribute("TAWRMRECORDS", tawRmRecords);
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            pageOffset = null;
            starttime = null;
            endtime = null;
            paramCondition = null;
            strCondition = null;
            privBO = null;
            // tawValidatePrivBO = null;
            tawRmRecords = null;
            objKey = null;
            url = null;
            pagerHeader = null;

        }

        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 值班记录列表，目前不用
     */
    private ActionForward performList(ActionMapping mapping,
                                      ActionForm actionForm, HttpServletRequest request,
                                      HttpServletResponse response) {
        TawRmRecordDAO tawRmRecordDAO = null;
        String pageOffset = null;
        List tawRmRecords = null;
        String objKey = null;
        String url = null;

        try {
            tawRmRecordDAO = new TawRmRecordDAO(ds);

            int offset;
            int length = PAGE_LENGTH;
            pageOffset = request.getParameter("pager.offset");
            if (pageOffset == null || pageOffset.equals("")) {
                offset = 0;
            } else {
                offset = Integer.parseInt(pageOffset);
            }

            tawRmRecords = tawRmRecordDAO.list(offset, length);

            String[] objKeys = {"TawRmRecord", "list"};
            objKey = CacheManager.createKey(objKeys);
            Integer size = (Integer) SizeCacheManager.getCache(objKey);
            if (size == null) {
                size = new Integer(tawRmRecordDAO.getSize("taw_rm_record", ""));
                SizeCacheManager.putCache(size, objKey, 0);

            }

            url = request.getContextPath() + "/" + mapping.getPath() + ".do";
            String pagerHeader = Pager.generate(offset, size.intValue(),
                    length, url);
            request.setAttribute("pagerHeader", pagerHeader);

            request.setAttribute("TAWRMRECORDS", tawRmRecords);
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            tawRmRecordDAO = null;
            pageOffset = null;
            tawRmRecords = null;
            objKey = null;
            url = null;
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 填写值班记录
     */
    private ActionForward performRecord(ActionMapping mapping,
                                        ActionForm actionForm, HttpServletRequest request,
                                        HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        TawUserRoomDAO tawUserRoomDAO = null;
        TawRmRecord tawRmRecord = null;
        Vector vecDefRecord = null;
        Vector vecSubRecords = null;
        // TawRmRecordLockDAO tawRmRecordLockDAO = null;

        // 判断超时
        // saveSessionBeanForm =
        // (SaveSessionBeanForm) request.getSession().getAttribute(
        // "SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        // 判断是否值班
        if (saveSessionBeanForm.getWorkSerial().equals("0")) {
            // DutySchedule dutySchedule =new DutySchedule();
            // dutySchedule.TawRmRecordEmail();
            return mapping.findForward("notonduty");
        }

        try {
            // 定义数据库连接和DAO
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            tawUserRoomDAO = new TawUserRoomDAO(ds);

            // 获得用户ID，值班号，机房ID及名称
            int workserial = Integer.parseInt(saveSessionBeanForm
                    .getWorkSerial());
            request.setAttribute("workserial", String.valueOf(workserial));
            int roomid = Integer.parseInt(saveSessionBeanForm.getRoomId());
            String roomName = tawUserRoomDAO.getRoomName(roomid);
            request.setAttribute("roomName", roomName); // 机房名称
            request.setAttribute("roomId", String.valueOf(roomid)); // 机房名称
            String userId = StaticMethod.null2String(saveSessionBeanForm
                    .getUserid());

            // List tawRmRecords =
            // tawRmRecordDAO.search7Days(saveSessionBeanForm.
            // getWrf_DeptID());
            // request.setAttribute("TAWRMRECORDS", tawRmRecords);

            // 判断值班住记录是否存在

            boolean CheckMainRecordExist = tawRmRecordDAO
                    .receiverCheckMainRecordExist(workserial);
            if (CheckMainRecordExist == false) {
                // 如果不存在创建值班主记录
                tawRmRecordDAO.getNewManiRecord(workserial, roomid + "");
            }
            // 判断值班子记录是否存在
            boolean CheckSubRecordExist = tawRmRecordDAO
                    .receiverCheckSubRecordExist(workserial, userId);
            // 如果不存在生成值班子记录
            if (CheckSubRecordExist == false) {
                tawRmRecordDAO.getSaveSubRecord(workserial, userId);
            }
            /*
             * TawRmDefinemainDAO tawRmDefinemainDAO = new
             * TawRmDefinemainDAO(ds); boolean CheckDefRecordExist =
             * tawRmDefinemainDAO.CheckDefRecordExist( workserial);
             * //如果不存在生成值班子记录 if (CheckDefRecordExist == false) {
             * tawRmDefinemainDAO.getNewDefRecord(workserial, roomid); }
             */

            // zc2004-03-08修改
            // 删除记录消息
            // tawRmRecordLockDAO = new TawRmRecordLockDAO(ds);
            // tawRmRecordLockDAO.delete(saveSessionBeanForm.getWrf_UserID());
            // 得到值班主记录对应的FORM
            TawRmRecordForm tawRmRecordForm = (TawRmRecordForm) actionForm;
            tawRmRecord = tawRmRecordDAO.retrieve(workserial);
            org.apache.commons.beanutils.BeanUtils.populate(tawRmRecordForm,
                    org.apache.commons.beanutils.BeanUtils
                            .describe(tawRmRecord));

            /*
             * //自定义值班记录数据 vecDefRecord = new Vector(); TawRmDefineTableDAO
             * tawRmDefineTableDAO = new TawRmDefineTableDAO(ds); vecDefRecord =
             * tawRmDefineTableDAO.retrieveVecOnlyByRoomId(roomid);
             * //vecDefRecord=tawRmDefinemainDAO.getVecDefRecord(workserial);
             * request.setAttribute("vecDefRecord", vecDefRecord);
             */

            Vector vecPerRecords = new Vector();
            TawRmRecordPerDAO tawRmRecordPerDAO = new TawRmRecordPerDAO(ds);
            vecPerRecords = tawRmRecordPerDAO.getVecPerRecord(workserial);
            request.setAttribute("vecPerRecords", vecPerRecords);

            // 附加表的填写
            // 得到本机房的所有的附加表列表
            List sheetList = new ArrayList();
            TawConfRoomSheetDAO tawConfRoomSheetDAO = new TawConfRoomSheetDAO(
                    ds);

            sheetList = tawRmRecordDAO.getAddons(saveSessionBeanForm
                    .getRoomId());
            request.setAttribute("SHEETLIST", sheetList);
            if (tawRmRecordForm != null) {
                tawRmRecordForm.setSheetCount(sheetList.size());

            }
            tawConfRoomSheetDAO = null;

            // 得到本班次已经填写的附加表的sheet_id和sheet_name
            Vector bocoVector = new Vector();
            TawDutySheetDAO tawDutySheetDAO = new TawDutySheetDAO(ds);
            bocoVector = tawDutySheetDAO.getSheetListByWorkSerial(Integer
                    .parseInt(saveSessionBeanForm.getWorkSerial()));

            // 得到每一个附加表中的记录Id
            int sheetId = 0;
            String strBocoId = "";
            for (int i = 0; i < bocoVector.size(); i++) {
                TawDutySheet tawDutySheet = (TawDutySheet) bocoVector
                        .elementAt(i);
                sheetId = tawDutySheet.getSheetId();
                strBocoId = tawDutySheetDAO.getBocoIdsBySheetId(sheetId,
                        Integer.parseInt(saveSessionBeanForm.getWorkSerial()));
                tawDutySheet.setStrBocoId(strBocoId);
            }
            if (tawRmRecordForm != null) {
                tawRmRecordForm.setRealCount(bocoVector.size());
            }
            request.setAttribute("FILLEDSHEETLIST", bocoVector);

            // zhiban zhouqi fujian
            TawRmSysteminfoDAO tawRmSysteminfoDAO = new TawRmSysteminfoDAO(ds);
            TawRmSysteminfo tawRmSysteminfo = tawRmSysteminfoDAO
                    .retrieve(roomid + "");
            int cycleTime = 0;
            if (tawRmSysteminfo != null) {
                cycleTime = tawRmSysteminfo.getCycleTime(); // 取周期时间
            }
            TawRmAssignworkDAO tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);
            TawRmAssignwork tawRmAssignwork = tawRmAssignworkDAO
                    .retrieve(workserial);
            String startdate = tawRmAssignwork.getStarttimeDefined();
            // String enddate = tawRmAssignwork.getEndtimeDefined();
            String time = StaticMethod.getCurrentDateTime();
            int distance = StaticMethod.getTimeDistance(startdate, time);
            ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
            int sell = -1;
            if (cycleTime != 0) {
                sell = distance / cycleTime;
            }

            request.setAttribute("sell", sell + "");
            List cycleList = new ArrayList();
            TawRmCycleTableDAO tawRmCycleTableDAO = new TawRmCycleTableDAO(ds);

            cycleList = tawRmCycleTableDAO.getCycleTable(saveSessionBeanForm
                    .getRoomId());
            String startDuty = saveSessionBeanForm.getStartDuty();
            String endDuty = saveSessionBeanForm.getEndDuty();
            List cutoversheetlist = new ArrayList();
            List faultsheetlist = new ArrayList();
            // TODO 值班记录工单连接
            /*
             * List cutoversheetlist=new ArrayList(); List faultsheetlist=new
             * ArrayList(); >>>>>>> .r12573 List monthPlanVOList = null; List
             * faultreportsheetlist = new ArrayList(); String roomId =
             * saveSessionBeanForm.getRoomId(); cutoversheetlist =
             * tawRmRecordDAO.searchCutoversheetlist(0, 0, startDuty, endDuty,
             * userId); faultreportsheetlist =
             * tawRmRecordDAO.searchFaultreportsheetlist(0, 0, startDuty,
             * endDuty, userId); faultsheetlist =
             * tawRmRecordDAO.searchFaultsheetlist(0, 0, startDuty, endDuty,
             * userId); request.setAttribute("cutoversheetlist",
             * cutoversheetlist); request.setAttribute("faultreportsheetlist",
             * faultreportsheetlist); request.setAttribute("faultsheetlist",
             * faultsheetlist); String dutyStartDate =
             * StaticMethod.getCurrentDateTime(
             * saveSessionBeanForm.getStartDuty(), "yyyy-MM-dd HH:mm:ss") + "";
             * String dutyEndDate = StaticMethod.getCurrentDateTime(
             * saveSessionBeanForm.getEndDuty(), "yyyy-MM-dd HH:mm:ss") + "";
             * String yearFlag = TawwpUtil.getCurrentDateTime("yyyy"); String
             * monthFlag = TawwpUtil.getCurrentDateTime("MM");
             *
             * monthPlanVOList = tawwpExecuteMgr.listDutyExecutePlan(String
             * .valueOf(roomId), yearFlag, monthFlag, dutyStartDate,
             * dutyEndDate); request.setAttribute("monthPlanVOList",
             * monthPlanVOList);
             */
            if (tawRmRecordForm != null) {
                tawRmRecordForm.setCycleCount(cycleList.size());
            }
            request.setAttribute("CYCLELIST", cycleList);

            request.setAttribute("dutyType", "值班");

        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            tawRmRecord = null;
            vecDefRecord = null;
            vecSubRecords = null;
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 交接班界面
     */
    private ActionForward performDevolve(ActionMapping mapping,
                                         ActionForm actionForm, HttpServletRequest request,
                                         HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        TawUserRoomDAO tawUserRoomDAO = null;
        TawRmRecordSubDAO tawRmRecordSubDAO = null;
        TawRmAssignworkDAO tawRmAssignworkDAO = null;
        TawRmSysteminfoDAO tawRmSysteminfoDAO = null;
        TawRmSysteminfo tawRmSysteminfo = null;
        GregorianCalendar cal_now = null;
        TawRmAssignwork tawRmAssignwork = null;
        String strEndTimeDefined = null;
        GregorianCalendar cal_EndTimeDefined = null;
        GregorianCalendar cal_end = null;
        GregorianCalendar cal_start = null;
        String userId = null;
        String roomName = null;
        List tawRmRecord_dutyman = null;
        List tawRmRecord_receivermaster = null;
        TawRmRecord tawRmRecord = null;
        String user_id = null;
        TawRmDutyfileDAO tawRmDutyfileDAO = null;
        Vector vecDutyFile = null;

        // 判断超时
        // saveSessionBeanForm =
        // (SaveSessionBeanForm) request.getSession().getAttribute(
        // "SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        // 判断是否值班
        if (saveSessionBeanForm.getWorkSerial().equals("0")) {
            return mapping.findForward("notonduty");
        }

        try {
            // 定义数据库连接和DAO
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            tawUserRoomDAO = new TawUserRoomDAO(ds);
            tawRmRecordSubDAO = new TawRmRecordSubDAO(ds);
            tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);

            // 取得最大误差时间
            int roomid = Integer.parseInt(saveSessionBeanForm.getRoomId());
            int workserial = Integer.parseInt(saveSessionBeanForm
                    .getWorkSerial());
            tawRmSysteminfoDAO = new TawRmSysteminfoDAO(ds);
            tawRmSysteminfo = tawRmSysteminfoDAO.retrieve(roomid + "");

            String exchangeFlag = "0";
            if (tawRmSysteminfo != null) {
                exchangeFlag = String
                        .valueOf(tawRmSysteminfo.getExchangeFlag());
            }
            request.setAttribute("EXCHANGEFLAG", exchangeFlag);
            // ///////////////////////////////////

            if (exchangeFlag.equals("0") || exchangeFlag.equals("1")) {
                // 判断是否值班班长
                if (!saveSessionBeanForm.getIsDutyMaster()) {
                    return mapping.findForward("notdutymaster");
                }
            }

            int maxerrortime = 0;
            if (tawRmSysteminfo != null) {
                maxerrortime = tawRmSysteminfo.getMaxerrortime();
            }
            // 当前时间,最大误差时间
            cal_now = new GregorianCalendar();
            tawRmAssignwork = tawRmAssignworkDAO.retrieve(workserial);
            strEndTimeDefined = String.valueOf(tawRmAssignwork
                    .getEndtimeDefined());
            cal_EndTimeDefined = StaticMethod.String2Cal(strEndTimeDefined);
            cal_end = StaticMethod.String2Cal(strEndTimeDefined);
            cal_end.add(cal_end.MINUTE, maxerrortime);
            cal_start = StaticMethod.String2Cal(strEndTimeDefined);
            cal_start.add(cal_start.MINUTE, -maxerrortime);
//			if (cal_now.before(cal_start) || cal_now.after(cal_end)) {
//				request.setAttribute("maxerrortime", String
//						.valueOf(maxerrortime));
//				request.setAttribute("strEndTimeDefined", strEndTimeDefined);
//				return mapping.findForward("notexchangetime");
//			}

            // 获得相关参数
            int offset;
            int length = PAGE_LENGTH;
            String pageOffset = request.getParameter("pager.offset");
            if (pageOffset == null || pageOffset.equals("")) {
                offset = 0;
            } else {
                offset = Integer.parseInt(pageOffset);
            }
            // 获得用户ID，值班号，机房ID及名称
            userId = StaticMethod.null2String(saveSessionBeanForm.getUserid());
            roomName = saveSessionBeanForm.getRoomname();
            request.setAttribute("roomName", roomName); // 机房名称
            // 获得当前时间
            java.util.Date date = new java.util.Date();
            java.text.SimpleDateFormat dtFormat = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            String strDutyDate = dtFormat.format(date);
            //
            tawRmRecord_dutyman = tawRmRecordDAO.listDutyman(workserial);
            tawRmRecord_receivermaster = tawRmRecordDAO.listReceiverMaster(
                    offset, length, userId, strDutyDate, roomid);
            request.setAttribute("TAWRMRECORD_DUTYMAN", tawRmRecord_dutyman); // 值班人
            request.setAttribute("TAWRMRECORD_RECEIVERMASTER",
                    tawRmRecord_receivermaster); // 接班班长

            // 得到值班主记录对应的FORM
            tawRmRecord = tawRmRecordDAO.retrieve(workserial);
            TawRmRecordForm tawRmRecordForm = (TawRmRecordForm) actionForm;
            tawRmRecord.setStarttimeDefined("");
            tawRmRecord.setStarttimesub("");
            tawRmRecord.setStatement("");
            tawRmRecord.setEndtimeDefined("");
            tawRmRecord.setDutymansub("");
            tawRmRecord.setDutymaster("");
            org.apache.commons.beanutils.BeanUtils.populate(tawRmRecordForm,
                    org.apache.commons.beanutils.BeanUtils
                            .describe(tawRmRecord));
            user_id = saveSessionBeanForm.getUserid();

            // 附件
            tawRmDutyfileDAO = new TawRmDutyfileDAO(ds);
            vecDutyFile = tawRmDutyfileDAO.getDutyFile(workserial);
            request.setAttribute("vecDutyFile", vecDutyFile);

            Vector vecDefRecord = new Vector();
            /*
             * TawRmDefinemainDAO tawRmDefinemainDAO = new
             * TawRmDefinemainDAO(ds); vecDefRecord =
             * tawRmDefinemainDAO.getVecDefRecord(workserial);
             * TawRmDefineTableDAO tawRmDefineTableDAO = new
             * TawRmDefineTableDAO(ds); vecDefRecord =
             * tawRmDefineTableDAO.retrieveVec(roomid);
             * request.setAttribute("vecDefRecord", vecDefRecord);
             */
            Vector vecPerRecords = new Vector();
            TawRmRecordPerDAO tawRmRecordPerDAO = new TawRmRecordPerDAO(ds);
            vecPerRecords = tawRmRecordPerDAO.getVecPerRecord(workserial);
            request.setAttribute("vecPerRecords", vecPerRecords);

            // 附加表的记录显示：
            // 得到本班次已经填写的附加表的sheet_id和sheet_name
            Vector bocoVector = new Vector();
            TawDutySheetDAO tawDutySheetDAO = new TawDutySheetDAO(ds);
            bocoVector = tawDutySheetDAO.getSheetListByWorkSerial(workserial);

            // 得到每一个附加表中的记录Id
            int sheetId = 0;
            String strBocoId = "";
            for (int i = 0; i < bocoVector.size(); i++) {
                TawDutySheet tawDutySheet = (TawDutySheet) bocoVector
                        .elementAt(i);
                sheetId = tawDutySheet.getSheetId();
                strBocoId = tawDutySheetDAO.getBocoIdsBySheetId(sheetId,
                        workserial);
                tawDutySheet.setStrBocoId(strBocoId);
            }
            tawRmRecordForm.setRealCount(bocoVector.size());
            request.setAttribute("FILLEDSHEETLIST", bocoVector);

            // 周期
            Vector cycleVectot = new Vector();
            TawRmCycleTableSubDAO tawRmCycleTableSub = new TawRmCycleTableSubDAO(
                    ds);
            cycleVectot = tawRmCycleTableSub
                    .getSheetListByWorkSerial(workserial);
            tawRmRecordForm.setCycleCount(cycleVectot.size());
            request.setAttribute("CYCLETABLELIST", cycleVectot);
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            tawUserRoomDAO = null;
            tawRmRecordSubDAO = null;
            tawRmAssignworkDAO = null;
            tawRmSysteminfoDAO = null;
            tawRmSysteminfo = null;
            cal_now = null;
            tawRmAssignwork = null;
            strEndTimeDefined = null;
            cal_EndTimeDefined = null;
            cal_end = null;
            cal_start = null;
            userId = null;
            roomName = null;
            tawRmRecord_dutyman = null;
            tawRmRecord_receivermaster = null;
            tawRmRecord = null;
            user_id = null;
            tawRmDutyfileDAO = null;
            vecDutyFile = null;
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 交接班界面(云南省填写界面跟其它省不一样)
     */
    private ActionForward performYnDevolve(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        TawUserRoomDAO tawUserRoomDAO = null;
        TawRmRecordSubDAO tawRmRecordSubDAO = null;
        TawRmAssignworkDAO tawRmAssignworkDAO = null;
        TawRmSysteminfoDAO tawRmSysteminfoDAO = null;
        TawRmSysteminfo tawRmSysteminfo = null;
        GregorianCalendar cal_now = null;
        TawRmAssignwork tawRmAssignwork = null;
        String strEndTimeDefined = null;
        GregorianCalendar cal_EndTimeDefined = null;
        GregorianCalendar cal_end = null;
        GregorianCalendar cal_start = null;
        String userId = null;
        String roomName = null;
        List tawRmRecord_dutyman = null;
        List tawRmRecord_receivermaster = null;
        TawRmRecord tawRmRecord = null;
        String user_id = null;
        TawRmDutyfileDAO tawRmDutyfileDAO = null;
        Vector vecDutyFile = null;

        // 判断超时
        // saveSessionBeanForm =
        // (SaveSessionBeanForm) request.getSession().getAttribute(
        // "SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        // 判断是否值班
        if (saveSessionBeanForm.getWorkSerial().equals("0")) {
            return mapping.findForward("notonduty");
        }

        try {
            // 定义数据库连接和DAO
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            tawUserRoomDAO = new TawUserRoomDAO(ds);
            tawRmRecordSubDAO = new TawRmRecordSubDAO(ds);
            tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);

            // 取得最大误差时间
            int roomid = Integer.parseInt(saveSessionBeanForm.getRoomId());
            int workserial = Integer.parseInt(saveSessionBeanForm
                    .getWorkSerial());
            tawRmSysteminfoDAO = new TawRmSysteminfoDAO(ds);
            tawRmSysteminfo = tawRmSysteminfoDAO.retrieve(roomid + "");

            String exchangeFlag = "0";
            if (tawRmSysteminfo != null) {
                exchangeFlag = String
                        .valueOf(tawRmSysteminfo.getExchangeFlag());
            }
            request.setAttribute("EXCHANGEFLAG", exchangeFlag);
            // ///////////////////////////////////

            if (exchangeFlag.equals("0") || exchangeFlag.equals("1")) {
                // 判断是否值班班长
                if (!saveSessionBeanForm.getIsDutyMaster()) {
                    return mapping.findForward("notdutymaster");
                }
            }

            int maxerrortime = 0;
            if (tawRmSysteminfo != null) {
                maxerrortime = tawRmSysteminfo.getMaxerrortime();
            }
            // 当前时间,最大误差时间
            cal_now = new GregorianCalendar();
            tawRmAssignwork = tawRmAssignworkDAO.retrieve(workserial);
            strEndTimeDefined = String.valueOf(tawRmAssignwork
                    .getEndtimeDefined());
            cal_EndTimeDefined = StaticMethod.String2Cal(strEndTimeDefined);
            cal_end = StaticMethod.String2Cal(strEndTimeDefined);
            cal_end.add(cal_end.MINUTE, maxerrortime);
            cal_start = StaticMethod.String2Cal(strEndTimeDefined);
            cal_start.add(cal_start.MINUTE, -maxerrortime);
            if (cal_now.before(cal_start) || cal_now.after(cal_end)) {
                request.setAttribute("maxerrortime", String
                        .valueOf(maxerrortime));
                request.setAttribute("strEndTimeDefined", strEndTimeDefined);
                return mapping.findForward("notexchangetime");
            }

            // 获得相关参数
            int offset;
            int length = PAGE_LENGTH;
            String pageOffset = request.getParameter("pager.offset");
            if (pageOffset == null || pageOffset.equals("")) {
                offset = 0;
            } else {
                offset = Integer.parseInt(pageOffset);
            }
            // 获得用户ID，值班号，机房ID及名称
            userId = StaticMethod.null2String(saveSessionBeanForm.getUserid());
            roomName = saveSessionBeanForm.getRoomname();
            request.setAttribute("roomName", roomName); // 机房名称
            // 获得当前时间
            java.util.Date date = new java.util.Date();
            java.text.SimpleDateFormat dtFormat = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            String strDutyDate = dtFormat.format(date);
            //
            tawRmRecord_dutyman = tawRmRecordDAO.listDutyman(workserial);
            tawRmRecord_receivermaster = tawRmRecordDAO.listReceiverMaster(
                    offset, length, userId, strDutyDate, roomid);
            request.setAttribute("TAWRMRECORD_DUTYMAN", tawRmRecord_dutyman); // 值班人
            request.setAttribute("TAWRMRECORD_RECEIVERMASTER",
                    tawRmRecord_receivermaster); // 接班班长

            // 得到值班主记录对应的FORM
            tawRmRecord = tawRmRecordDAO.retrieve(workserial);
            TawRmRecordForm tawRmRecordForm = (TawRmRecordForm) actionForm;
            tawRmRecord.setStarttimeDefined("");
            tawRmRecord.setStarttimesub("");
            tawRmRecord.setStatement("");
            tawRmRecord.setEndtimeDefined("");
            tawRmRecord.setDutymansub("");
            tawRmRecord.setDutymaster("");
            org.apache.commons.beanutils.BeanUtils.populate(tawRmRecordForm,
                    org.apache.commons.beanutils.BeanUtils
                            .describe(tawRmRecord));
            user_id = saveSessionBeanForm.getUserid();

            // 附件
            tawRmDutyfileDAO = new TawRmDutyfileDAO(ds);
            vecDutyFile = tawRmDutyfileDAO.getDutyFile(workserial);
            request.setAttribute("vecDutyFile", vecDutyFile);

            Vector vecDefRecord = new Vector();
            /*
             * TawRmDefinemainDAO tawRmDefinemainDAO = new
             * TawRmDefinemainDAO(ds); vecDefRecord =
             * tawRmDefinemainDAO.getVecDefRecord(workserial);
             * TawRmDefineTableDAO tawRmDefineTableDAO = new
             * TawRmDefineTableDAO(ds); vecDefRecord =
             * tawRmDefineTableDAO.retrieveVec(roomid);
             * request.setAttribute("vecDefRecord", vecDefRecord);
             */
            Vector vecPerRecords = new Vector();
            TawRmRecordPerDAO tawRmRecordPerDAO = new TawRmRecordPerDAO(ds);
            vecPerRecords = tawRmRecordPerDAO.getVecPerRecord(workserial);
            request.setAttribute("vecPerRecords", vecPerRecords);

            // 附加表的记录显示：
            // 得到本班次已经填写的附加表的sheet_id和sheet_name
            Vector bocoVector = new Vector();
            TawDutySheetDAO tawDutySheetDAO = new TawDutySheetDAO(ds);
            bocoVector = tawDutySheetDAO.getSheetListByWorkSerial(workserial);

            // 得到每一个附加表中的记录Id
            int sheetId = 0;
            String strBocoId = "";
            for (int i = 0; i < bocoVector.size(); i++) {
                TawDutySheet tawDutySheet = (TawDutySheet) bocoVector
                        .elementAt(i);
                sheetId = tawDutySheet.getSheetId();
                strBocoId = tawDutySheetDAO.getBocoIdsBySheetId(sheetId,
                        workserial);
                tawDutySheet.setStrBocoId(strBocoId);
            }
            tawRmRecordForm.setRealCount(bocoVector.size());
            request.setAttribute("FILLEDSHEETLIST", bocoVector);

            // 周期
            Vector cycleVectot = new Vector();
            TawRmCycleTableSubDAO tawRmCycleTableSub = new TawRmCycleTableSubDAO(
                    ds);
            cycleVectot = tawRmCycleTableSub
                    .getSheetListByWorkSerial(workserial);
            tawRmRecordForm.setCycleCount(cycleVectot.size());
            request.setAttribute("CYCLETABLELIST", cycleVectot);

            // 获取本班次的事件,事件要属于本机房新增或者是相关机房
            ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
            String condition = " AND tawRmDutyEvent.workserial="
                    + saveSessionBeanForm.getWorkSerial() + " ";

            Map map = (Map) mgr.getEventByCondition(new Integer(0),
                    new Integer(10000), condition);
            List list = (List) map.get("result"); // 取所有这个主记录的子记录 为了在页面显示
            request.setAttribute("TawRmDutyEventList", list);
            request.setAttribute("resultSize", map.get("total"));
            request.setAttribute("pageSize", new Integer(length));

            // 当班次的物件管理
            TawRmThingMgr tawRmThingMgr = (TawRmThingMgr) getBean("tawRmThingMgr");
            String room_id = saveSessionBeanForm.getRoomId();
            request.setAttribute("Thinglist", tawRmThingMgr
                    .getThingList(room_id));

        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            tawUserRoomDAO = null;
            tawRmRecordSubDAO = null;
            tawRmAssignworkDAO = null;
            tawRmSysteminfoDAO = null;
            tawRmSysteminfo = null;
            cal_now = null;
            tawRmAssignwork = null;
            strEndTimeDefined = null;
            cal_EndTimeDefined = null;
            cal_end = null;
            cal_start = null;
            userId = null;
            roomName = null;
            tawRmRecord_dutyman = null;
            tawRmRecord_receivermaster = null;
            tawRmRecord = null;
            user_id = null;
            tawRmDutyfileDAO = null;
            vecDutyFile = null;
        }
        return mapping.findForward("success");
    }

    private ActionForward performViewMain(ActionMapping mapping,
                                          ActionForm actionForm, HttpServletRequest request,
                                          HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        TawRmRecord tawRmRecord = null;
        TawUserRoomDAO tawUserRoomDAO = null;
        String roomName = null;
        TawRmDutyfileDAO tawRmDutyfileDAO = null;
        Vector vecDutyFile = null;
        List tawRmRecord_dutyman = null;
        // logBO logbo = null;
        // 判断超时
        // saveSessionBeanForm =
        // (SaveSessionBeanForm) request.getSession().getAttribute(
        // "SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        TawRmRecordForm form = (TawRmRecordForm) actionForm;
        try {
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            int id = Integer.parseInt(request.getParameter("id"));
            String setStrutsAction = StaticMethod.nullObject2String(request
                    .getParameter("setStrutsAction"));
            tawRmRecord = tawRmRecordDAO.retrieve(id);
            if (tawRmRecord == null) {
                ActionErrors aes = new ActionErrors();
                aes.add(aes.GLOBAL_ERROR, new ActionError(
                        "error.object.notfound", "TawRmRecord"));
                saveErrors(request, aes);
            } else {
                tawRmRecord.setStarttimeDefined("");
                tawRmRecord.setStarttimesub("");
                tawRmRecord.setStatement("");
                tawRmRecord.setEndtimeDefined("");
                tawRmRecord.setDutymansub("");
                tawRmRecord.setDutymaster("");

                org.apache.commons.beanutils.BeanUtils.populate(form,
                        org.apache.commons.beanutils.BeanUtils
                                .describe(tawRmRecord));
                // 机房名称
                int roomid = tawRmRecord.getRoomId();
                tawUserRoomDAO = new TawUserRoomDAO(ds);
                roomName = tawUserRoomDAO.getRoomName(roomid);
                request.setAttribute("roomName", roomName);
                // 完成状态
                int intFlag = tawRmRecord.getFlag();
                request.setAttribute("intFlag", String.valueOf(intFlag));
                // 附件
                tawRmDutyfileDAO = new TawRmDutyfileDAO(ds);
                vecDutyFile = tawRmDutyfileDAO.getDutyFile(id);
                request.setAttribute("vecDutyFile", vecDutyFile);
                // 值班人
                tawRmRecord_dutyman = tawRmRecordDAO.listDutyman(id);
                request
                        .setAttribute("TAWRMRECORD_DUTYMAN",
                                tawRmRecord_dutyman);
                // 遗留问题
                TawRmHangoverDAO tawRmHangoverDAO = new TawRmHangoverDAO(ds);
                TawRmHangover tawRmHangover = tawRmHangoverDAO.retrieve(id, 0);
                String strHangQuestion = "";
                if (tawRmHangover != null) {
                    strHangQuestion = tawRmHangover.getHangQuestion();
                }
                request.setAttribute("strHangQuestion", strHangQuestion);
                request.setAttribute("setStrutsAction", setStrutsAction);
                request
                        .setAttribute("AUDITPRIV", String
                                .valueOf(tawRmRecordDAO
                                        .getAuditPriv(saveSessionBeanForm
                                                .getUserid(), roomid)));
                request.setAttribute("dutycheck", tawRmRecord.getDutycheck());

                String strId = String.valueOf(id);
                for (int i = 1; i <= StaticDutycheck.dutycheckcount; i++) {
                    String checkId = StaticDutycheck.getDutycheckId(i);
                    request.setAttribute(checkId, tawRmRecordDAO.getCheckman(
                            strId, checkId));
                }

            }
            // logbo = new logBO(ds);
            // boolean bool =
            // logbo.insertLogToDB(saveSessionBeanForm.getWrf_UserID(),
            // "查询值班记录", StaticVariable.OPER,
            // request.getRemoteAddr());
            request.setAttribute("dutyId", id + "");
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            tawRmRecord = null;
            tawUserRoomDAO = null;
            roomName = null;
            tawRmDutyfileDAO = null;
            vecDutyFile = null;
            tawRmRecord_dutyman = null;
            // logbo = null;
        }
        return mapping.findForward("success");

    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 值班记录编辑界面
     */
    private ActionForward performViewedit(ActionMapping mapping,
                                          ActionForm actionForm, HttpServletRequest request,
                                          HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        TawRmRecord tawRmRecord = null;
        Vector vecDefRecord = null;
        Vector vecSubRecords = null;

        // 判断超时
        // saveSessionBeanForm =
        // (SaveSessionBeanForm) request.getSession().getAttribute(
        // "SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {
            // 定义数据库连接和DAO
            tawRmRecordDAO = new TawRmRecordDAO(ds);

            TawRmRecordForm tawRmRecordForm = (TawRmRecordForm) actionForm;
            if (tawRmRecordForm.getId() == 0) {
                int intWorkserial = Integer
                        .parseInt(request.getParameter("id"));
                tawRmRecordForm.setId(intWorkserial);
            }
            int workserial = tawRmRecordForm.getId();

            // 得到值班主记录对应的FORM
            tawRmRecord = tawRmRecordDAO.retrieve(workserial);
            int roomId = tawRmRecord.getRoomId();
            org.apache.commons.beanutils.BeanUtils.populate(tawRmRecordForm,
                    org.apache.commons.beanutils.BeanUtils
                            .describe(tawRmRecord));
            vecDefRecord = new Vector();
            /*
             * TawRmDefinemainDAO tawRmDefinemainDAO = new
             * TawRmDefinemainDAO(ds);
             * vecDefRecord=tawRmDefinemainDAO.getVecDefRecord(workserial);
             * TawRmDefineTableDAO tawRmDefineTableDAO = new
             * TawRmDefineTableDAO(ds); vecDefRecord =
             * tawRmDefineTableDAO.retrieveVec(tawRmRecord.getRoomId());
             * request.setAttribute("vecDefRecord", vecDefRecord);
             */

            Vector vecPerRecords = new Vector();
            TawRmRecordPerDAO tawRmRecordPerDAO = new TawRmRecordPerDAO(ds);
            vecPerRecords = tawRmRecordPerDAO.getVecPerRecord(workserial);
            request.setAttribute("vecPerRecords", vecPerRecords);
            // 附加表的记录显示：
            // 得到本班次已经填写的附加表的sheet_id和sheet_name
            Vector bocoVector = new Vector();
            TawDutySheetDAO tawDutySheetDAO = new TawDutySheetDAO(ds);
            bocoVector = tawDutySheetDAO.getSheetListByWorkSerial(workserial);

            // 得到每一个附加表中的记录Id
            int sheetId = 0;
            String strBocoId = "";
            for (int i = 0; i < bocoVector.size(); i++) {
                TawDutySheet tawDutySheet = (TawDutySheet) bocoVector
                        .elementAt(i);
                sheetId = tawDutySheet.getSheetId();
                strBocoId = tawDutySheetDAO.getBocoIdsBySheetId(sheetId,
                        workserial);
                tawDutySheet.setStrBocoId(strBocoId);
            }
            tawRmRecordForm.setRealCount(bocoVector.size());
            request.setAttribute("FILLEDSHEETLIST", bocoVector);

            // 周期
            request.setAttribute("dutyType", "值班");
            Vector cycleVectot = new Vector();
            TawRmCycleTableSubDAO tawRmCycleTableSub = new TawRmCycleTableSubDAO(
                    ds);
            cycleVectot = tawRmCycleTableSub
                    .getSheetListByWorkSerial(workserial);
            tawRmRecordForm.setCycleCount(cycleVectot.size());
            request.setAttribute("CYCLETABLELIST", cycleVectot);
            /*
             * List cutoversheetlist = new ArrayList(); List faultsheetlist =
             * new ArrayList(); List faultreportsheetlist = new ArrayList();
             * List monthPlanVOList = new ArrayList(); cutoversheetlist =
             * tawRmRecordDAO.retrieveAdditionalList( workserial, roomId,
             * "cutoversheet"); faultsheetlist =
             * tawRmRecordDAO.retrieveAdditionalList(workserial, roomId,
             * "faultsheet"); faultreportsheetlist =
             * tawRmRecordDAO.retrieveAdditionalList( workserial, roomId,
             * "faultreportsheet"); monthPlanVOList =
             * tawRmRecordDAO.retrieveAdditionalList(workserial, roomId,
             * "plan");
             */
            // TODO ...
            /*
             * List cutoversheetlist=new ArrayList(); List faultsheetlist=new
             * ArrayList(); List faultreportsheetlist=new ArrayList(); List
             * monthPlanVOList=new ArrayList();
             * cutoversheetlist=tawRmRecordDAO.retrieveAdditionalList(workserial,roomId,"cutoversheet");
             * faultsheetlist=tawRmRecordDAO.retrieveAdditionalList(workserial,roomId,"faultsheet");
             * faultreportsheetlist=tawRmRecordDAO.retrieveAdditionalList(workserial,roomId,"faultreportsheet");
             * monthPlanVOList=tawRmRecordDAO.retrieveAdditionalList(workserial,roomId,"plan");
             * >>>>>>> .r12573 request.setAttribute("cutoversheetlist",
             * cutoversheetlist); request.setAttribute("faultreportsheetlist",
             * faultreportsheetlist); request.setAttribute("faultsheetlist",
             * faultsheetlist); request.setAttribute("monthPlanVOList",
             * monthPlanVOList);
             */
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            tawRmRecord = null;
            vecDefRecord = null;
            vecSubRecords = null;

        }
        return mapping.findForward("success");

    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 值班记录查看界面
     */
    private ActionForward performView(ActionMapping mapping,
                                      ActionForm actionForm, HttpServletRequest request,
                                      HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        TawRmRecord tawRmRecord = null;
        Vector vecDefRecord = null;
        Vector vecSubRecords = null;

        // 判断超时
        // saveSessionBeanForm =
        // (SaveSessionBeanForm) request.getSession().getAttribute(
        // "SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {
            // 定义数据库连接和DAO
            tawRmRecordDAO = new TawRmRecordDAO(ds);

            // 得到值班主记录对应的FORM
            TawRmRecordForm tawRmRecordForm = (TawRmRecordForm) actionForm;
            if (tawRmRecordForm.getId() == 0) {
                int intWorkserial = Integer
                        .parseInt(request.getParameter("id"));
                tawRmRecordForm.setId(intWorkserial);
            }
            int workserial = tawRmRecordForm.getId();

            tawRmRecord = tawRmRecordDAO.retrieve(workserial);
            int roomId = tawRmRecord.getRoomId();
            org.apache.commons.beanutils.BeanUtils.populate(tawRmRecordForm,
                    org.apache.commons.beanutils.BeanUtils
                            .describe(tawRmRecord));
            if (!"".equals(tawRmRecordForm.getAuContent())) {
                request.setAttribute("setStrutsAction", "1");
                /*
                 * vecDefRecord = new Vector(); TawRmDefineTableDAO
                 * tawRmDefineTableDAO = new TawRmDefineTableDAO(ds);
                 * //vecDefRecord=tawRmDefinemainDAO.getVecDefRecord(workserial);
                 * vecDefRecord =
                 * tawRmDefineTableDAO.retrieveVec(tawRmRecord.getRoomId());
                 * request.setAttribute("vecDefRecord", vecDefRecord);
                 */

            }
            Vector vecPerRecords = new Vector();
            TawRmRecordPerDAO tawRmRecordPerDAO = new TawRmRecordPerDAO(ds);
            vecPerRecords = tawRmRecordPerDAO.getVecPerRecord(workserial);
            request.setAttribute("vecPerRecords", vecPerRecords);

            // 附加表的记录显示：
            // 得到本班次已经填写的附加表的sheet_id和sheet_name
            Vector bocoVector = new Vector();
            TawDutySheetDAO tawDutySheetDAO = new TawDutySheetDAO(ds);
            bocoVector = tawDutySheetDAO.getSheetListByWorkSerial(workserial);

            // 得到每一个附加表中的记录Id
            int sheetId = 0;
            String strBocoId = "";
            for (int i = 0; i < bocoVector.size(); i++) {
                TawDutySheet tawDutySheet = (TawDutySheet) bocoVector
                        .elementAt(i);
                sheetId = tawDutySheet.getSheetId();
                strBocoId = tawDutySheetDAO.getBocoIdsBySheetId(sheetId,
                        workserial);
                tawDutySheet.setStrBocoId(strBocoId);
            }
            tawRmRecordForm.setRealCount(bocoVector.size());
            request.setAttribute("FILLEDSHEETLIST", bocoVector);

            // 周期
            Vector cycleVectot = new Vector();
            TawRmCycleTableSubDAO tawRmCycleTableSub = new TawRmCycleTableSubDAO(
                    ds);
            cycleVectot = tawRmCycleTableSub
                    .getSheetListByWorkSerial(workserial);
            tawRmRecordForm.setCycleCount(cycleVectot.size());
            request.setAttribute("CYCLETABLELIST", cycleVectot);
            /*
             * List cutoversheetlist = new ArrayList(); List faultsheetlist =
             * new ArrayList(); List faultreportsheetlist = new ArrayList();
             * List monthPlanVOList = new ArrayList(); cutoversheetlist =
             * tawRmRecordDAO.retrieveAdditionalList( workserial, roomId,
             * "cutoversheet"); faultsheetlist =
             * tawRmRecordDAO.retrieveAdditionalList(workserial, roomId,
             * "faultsheet"); faultreportsheetlist =
             * tawRmRecordDAO.retrieveAdditionalList( workserial, roomId,
             * "faultreportsheet"); monthPlanVOList =
             * tawRmRecordDAO.retrieveAdditionalList(workserial, roomId,
             * "plan");
             */
            // TODO ....
            /*
             * List cutoversheetlist=new ArrayList(); List faultsheetlist=new
             * ArrayList(); List faultreportsheetlist=new ArrayList(); List
             * monthPlanVOList =new ArrayList();
             * cutoversheetlist=tawRmRecordDAO.retrieveAdditionalList(workserial,roomId,"cutoversheet");
             * faultsheetlist=tawRmRecordDAO.retrieveAdditionalList(workserial,roomId,"faultsheet");
             * faultreportsheetlist=tawRmRecordDAO.retrieveAdditionalList(workserial,roomId,"faultreportsheet");
             * monthPlanVOList=tawRmRecordDAO.retrieveAdditionalList(workserial,roomId,"plan");
             * >>>>>>> .r12573 request.setAttribute("cutoversheetlist",
             * cutoversheetlist); request.setAttribute("faultreportsheetlist",
             * faultreportsheetlist); request.setAttribute("faultsheetlist",
             * faultsheetlist); request.setAttribute("monthPlanVOList",
             * monthPlanVOList);
             */
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            tawRmRecord = null;
            vecDefRecord = null;
            vecSubRecords = null;

        }
        return mapping.findForward("success");

    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 合并值班记录
     */
    private ActionForward performCoalition(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        String coalitionRecord = null;

        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        TawRmRecordForm form = (TawRmRecordForm) actionForm;
        try {
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            int workserial = Integer.parseInt(saveSessionBeanForm
                    .getWorkSerial());
            coalitionRecord = "";
            // coalitionRecord = tawRmRecordDAO.coalitionSub(workserial);
            coalitionRecord = tawRmRecordDAO.coalitionSub2(workserial);
            System.out.println("coalitionRecord=" + coalitionRecord);
            request.setAttribute("COALITIONRECORD", coalitionRecord); // 合成的值班记录
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            coalitionRecord = null;
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 合并值班记录
     */
    private ActionForward performOldCoalition(ActionMapping mapping,
                                              ActionForm actionForm, HttpServletRequest request,
                                              HttpServletResponse response) {
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        TawRmRecordDAO tawRmRecordDAO = null;
        String coalitionRecord = null;

        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        TawRmRecordForm form = (TawRmRecordForm) actionForm;
        try {
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            int workserial = Integer.parseInt(saveSessionBeanForm
                    .getWorkSerial());
            coalitionRecord = "";
            coalitionRecord = tawRmRecordDAO.coalitionSub(workserial);
            System.out.println("coalitionRecord=" + coalitionRecord);
            request.setAttribute("COALITIONRECORD", coalitionRecord); // 合成的值班记录
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            coalitionRecord = null;
        }
        return mapping.findForward("success");
    }

    /**
     * @see 生成交接班页面，输出需要交接班的人员，出口是exchange.jsp 判断是否超时 如果没有合成日志先合成 判断交接班方式
     * exchangeFlag： 0，班长交接 ； 输出交接班人员（本班次和下班次的班长）
     * 1，正常班，不交接，未进入本方法，在上一个方法中已经转移到saveMerge方法中了 ； 2，任意人交接 ；
     * 输出交接班人员（本班次和下班次的人员列表） 3，所有人交接 ； 输出交接班人员（本班次和下班次的人员列表）
     */

    private ActionForward performExchange(ActionMapping mapping,
                                          ActionForm actionForm, HttpServletRequest request,
                                          HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        TawRmSysteminfoDAO tawRmSysteminfoDAO = null;
        TawRmRecord tawRmRecord = null;
        String coalitionRecord = null;
        String dutymanid = null;
        String dutymanname = null;
        String strDutymasterId = null;
        String dutyMasterName = null;
        TawRmRecordSub tawRmRecordSub = null;
        TawRmSysteminfo tawRmSysteminfo = null;
        Vector vecDutyManId = null;
        Vector vecDutyManName = null;
        Vector vecReceiverId = null;
        Vector vecReceiverName = null;
        int int_stagger = 0;

        // 判断是否超时
        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        int workserial = Integer.parseInt(saveSessionBeanForm.getWorkSerial());
        int roomid = Integer.parseInt(saveSessionBeanForm.getRoomId());
        TawRmRecordForm form = (TawRmRecordForm) actionForm;
        try {
            // 根据附加表得到遗留问题
            // 得到本班次已经填写的附加表的sheet_id和sheet_name
            /*
             * for (int i = 0; i < bocoVector.size(); i++) { TawDutySheet
             * tawDutySheet = (TawDutySheet) bocoVector.elementAt(i); int
             * sheetId1 = tawDutySheet.getSheetId(); String strBocoId =
             * tawDutySheetDAO.getBocoIdsBySheetId(sheetId1,saveSessionBeanForm.getWorkSerial());
             * tawDutySheet.setStrBocoId(strBocoId); }
             */
            Vector bocoVector = new Vector();
            TawDutySheetDAO tawDutySheetDAO = new TawDutySheetDAO(ds);
            bocoVector = tawDutySheetDAO.getSheetListByWorkSerial(Integer
                    .parseInt(saveSessionBeanForm.getWorkSerial()));

            //

            // 得到每一个附加表中的记录Id
            int sheetId = 0;
            String strSql = "";
            String strBocoIds = "";

            form.setRealCount(bocoVector.size());
            // request.setAttribute("FILLEDSHEETLIST", bocoVector);

            // 根据Sql串得到遗留问题
            String strSheetHQ = "";
            String strHQ = "";
            String strSheetHeader = "";

            for (int i = 0; i < bocoVector.size(); i++) {
                TawDutySheet tawDutySheet = (TawDutySheet) bocoVector
                        .elementAt(i);
                sheetId = tawDutySheet.getSheetId();
                strBocoIds = tawDutySheetDAO.getBocoIdsBySheetId(sheetId,
                        Integer.parseInt(saveSessionBeanForm.getWorkSerial()));
                strSql = this.getStrSql(roomid, sheetId, strBocoIds);
                System.out.println("sql==" + strSql);
                if (!"".equals(strSql)) {
                    strSheetHeader = tawDutySheet.getSheetName() + "遗留问题:\r";
                    strSheetHQ = strSheetHeader
                            + tawDutySheetDAO.getHangQuesByCond(strSql);
                    strHQ = strHQ + strSheetHQ;
                }
            }
            // strHQ=StaticMethod.PageNStrRev(strHQ);
            // strHQ = form.getHangQuestion()+"\r"+ strHQ;
            // form.setHangQuestion(strHQ);
            request.setAttribute("strHQ", strHQ);

            // 判断交接班方式
            String exchangeFlag = "0";
            tawRmSysteminfoDAO = new TawRmSysteminfoDAO(ds);
            tawRmSysteminfo = tawRmSysteminfoDAO.retrieve(roomid + "");
            if (tawRmSysteminfo != null) {
                exchangeFlag = String
                        .valueOf(tawRmSysteminfo.getExchangeFlag());

                int_stagger = tawRmSysteminfo.getStaggertime();

            }
            request.setAttribute("EXCHANGEFLAG", exchangeFlag);
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            // 取出session中的信息
            tawRmRecord = new TawRmRecord();
            form.setDutymaster("");
            org.apache.commons.beanutils.BeanUtils.populate(tawRmRecord,
                    org.apache.commons.beanutils.BeanUtils.describe(form));

            // 如果没有合成日志先合成
            if (form.getDutyrecord().trim().equals("")) {
                coalitionRecord = tawRmRecordDAO.coalitionSub(workserial);
                tawRmRecord.setDutyrecord(coalitionRecord);
            }

            // 更新值班纪录，主要是合成的值班纪录
            // 判断值班住记录是否存在
            boolean CheckMainRecordExist = tawRmRecordDAO
                    .receiverCheckMainRecordExist(workserial);
            // 不存在生成main Record
            if (CheckMainRecordExist == false) {

                tawRmRecordDAO.getNewManiRecord(workserial, roomid + "");
                // 保存值班纪录
                tawRmRecordSub = new TawRmRecordSub();
                tawRmRecordDAO.saveRecord(tawRmRecord, tawRmRecordSub,
                        saveSessionBeanForm.getIsDutyMaster(), workserial,
                        dutymanid);
            } else {
                tawRmRecordDAO.update(tawRmRecord);
            }

            Vector vecPerRecords = new Vector();
            TawRmRecordPerDAO tawRmRecordPerDAO = new TawRmRecordPerDAO(ds);
            vecPerRecords = tawRmRecordPerDAO.getVecPerRecord(workserial, 0);
            request.setAttribute("vecPerRecords", vecPerRecords);

            // 班长交接 ； 输出交接班人员（本班次和下班次的班长）
            if (exchangeFlag.equals("0")) {
                // 下页需要的参数
                // 取得本班次班长信息
                dutymanid = saveSessionBeanForm.getUserid(); // 登陆的值班人的id
                dutymanname = saveSessionBeanForm.getUsername(); // 登陆的值班人
                request.setAttribute("DUTYMANID", dutymanid);
                request.setAttribute("DUTYMANNAME", dutymanname); // 交班人性名
                // 取得接班人的班号

                int intWorkSerialTemp = tawRmRecordDAO.getExSerial(workserial,
                        2, int_stagger);
                request.setAttribute("strNextWorkSerial", String
                        .valueOf(intWorkSerialTemp));
                // 取得此班次得直班班长
                strDutymasterId = tawRmRecordDAO
                        .receiverDutyMaster(intWorkSerialTemp);
                request.setAttribute("RECEIVERMASTERID", strDutymasterId);
                // 根据id取出下班班长的姓名
                dutyMasterName = tawRmRecordDAO.getUserName(strDutymasterId);
                request.setAttribute("RECEIVERMASTERNAME", dutyMasterName); // 接班人姓名
                //
                request.setAttribute("strWorkSerial", String
                        .valueOf(workserial));
                return mapping.findForward("success");
            }
            // 任意人交接 ； 输出交接班人员（本班次和下班次的人员列表）
            if (exchangeFlag.equals("2")) {
                int intWorkSerialTemp = tawRmRecordDAO.getExSerial(workserial,
                        2, int_stagger);
                request.setAttribute("strNextWorkSerial", String
                        .valueOf(intWorkSerialTemp));
                request.setAttribute("strWorkSerial", String
                        .valueOf(workserial));
                vecDutyManId = tawRmRecordDAO.getOneAssignMember(workserial);
                vecDutyManName = tawRmRecordDAO
                        .getOneAssignMemberName(workserial);
                request.setAttribute("vecDutyManId", vecDutyManId);
                request.setAttribute("vecDutyManName", vecDutyManName);
                vecReceiverId = tawRmRecordDAO
                        .getOneAssignMember(intWorkSerialTemp);
                vecReceiverName = tawRmRecordDAO
                        .getOneAssignMemberName(intWorkSerialTemp);
                request.setAttribute("vecReceiverId", vecReceiverId);
                request.setAttribute("vecReceiverName", vecReceiverName);
                return mapping.findForward("success2");
            }

            // 所有人交接 ； 输出交接班人员（本班次和下班次的人员列表）
            if (exchangeFlag.equals("3")) {
                int intWorkSerialTemp = tawRmRecordDAO.getExSerial(workserial,
                        2, int_stagger);
                request.setAttribute("strNextWorkSerial", String
                        .valueOf(intWorkSerialTemp));
                request.setAttribute("strWorkSerial", String
                        .valueOf(workserial));
                vecDutyManId = tawRmRecordDAO.getOneAssignMember(workserial);
                vecDutyManName = tawRmRecordDAO
                        .getOneAssignMemberName(workserial);
                request.setAttribute("vecDutyManId", vecDutyManId);
                request.setAttribute("vecDutyManName", vecDutyManName);
                vecReceiverId = tawRmRecordDAO
                        .getOneAssignMember(intWorkSerialTemp);
                vecReceiverName = tawRmRecordDAO
                        .getOneAssignMemberName(intWorkSerialTemp);
                request.setAttribute("vecReceiverId", vecReceiverId);
                request.setAttribute("vecReceiverName", vecReceiverName);
                return mapping.findForward("success3");
            }

        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            tawRmSysteminfoDAO = null;
            tawRmRecord = null;
            coalitionRecord = null;
            dutymanid = null;
            dutymanname = null;
            strDutymasterId = null;
            dutyMasterName = null;
            tawRmRecordSub = null;
            tawRmSysteminfo = null;
            vecDutyManId = null;
            vecDutyManName = null;
            vecReceiverId = null;
            vecReceiverName = null;
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 无需交接班的值班日值整合工作
     */
    private ActionForward performSaveMerge(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        TawRmRecord tawRmRecord = null;
        String coalitionRecord = null;
        String dutymanid = null;
        String dutymanname = null;
        String strDutymasterId = null;
        String dutyMasterName = null;
        TawRmRecordSub tawRmRecordSub = null;

        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        String roomid = saveSessionBeanForm.getRoomId();
        TawRmRecordForm form = (TawRmRecordForm) actionForm;
        try {
            // 判断是否试值班班长，是值班班长可以交接班
            if (saveSessionBeanForm.getIsDutyMaster()) {
                tawRmRecordDAO = new TawRmRecordDAO(ds);
                // 取出session中的信息
                int workserial = Integer.parseInt(saveSessionBeanForm
                        .getWorkSerial());
                tawRmRecord = new TawRmRecord();
                form.setDutymaster("");
                org.apache.commons.beanutils.BeanUtils.populate(tawRmRecord,
                        org.apache.commons.beanutils.BeanUtils.describe(form));
                form.setHander(com.boco.eoms.common.util.StaticMethod
                        .null2String((form.getHander())));
                form.setDutyman(com.boco.eoms.common.util.StaticMethod
                        .null2String((form.getDutyman())));
                form.setReceiver(com.boco.eoms.common.util.StaticMethod
                        .null2String((form.getReceiver())));
                // 如果没有合成日志先合成
                if (form.getDutyrecord().trim().equals("")) {
                    coalitionRecord = tawRmRecordDAO.coalitionSub(workserial);
                    tawRmRecord.setDutyrecord(coalitionRecord);
                }
                // 取得本班次班长信息
                dutymanid = saveSessionBeanForm.getUserid(); // 登陆的值班人的id
                dutymanname = saveSessionBeanForm.getUsername(); // 登陆的值班人
                // 更新值班纪录，主要是合成的值班纪录
                // 判断值班住记录是否存在
                boolean CheckMainRecordExist = tawRmRecordDAO
                        .receiverCheckMainRecordExist(workserial);
                /*
                 * // begin by 何毅,对没有归档的人考核 Vector listBynotHold = new Vector(); //
                 * 故障工单 Vector listdatasheet = new Vector(); // 局数据工单 Vector
                 * listtasksheet = new Vector(); // 任务工单 Vector listrequsheet =
                 * new Vector(); // 申请工单 TawSheetStatDAO tawSheetStatDAO = new
                 * TawSheetStatDAO(ds); List list =
                 * tawSheetStatDAO.getTime(Integer
                 * .parseInt(saveSessionBeanForm.getWorkSerial()), Integer
                 * .parseInt(saveSessionBeanForm.getRoomId())); String startTime =
                 * list.get(0).toString(); String endTime =
                 * list.get(1).toString(); String dutyDate =
                 * list.get(2).toString(); String dutyMaster =
                 * list.get(3).toString();
                 * StaticMethod.getTimestamp(startTime).getTime(); String url =
                 * "";
                 *
                 * java.util.Calendar startData =
                 * java.util.Calendar.getInstance(); java.util.Calendar endData =
                 * java.util.Calendar.getInstance();
                 *
                 * startData.setTime((java.util.Date) StaticMethod
                 * .getTimestamp(startTime)); endData.setTime((java.util.Date)
                 * StaticMethod .getTimestamp(endTime));
                 * startData.add(java.util.Calendar.HOUR_OF_DAY, -2);
                 * endData.add(java.util.Calendar.HOUR_OF_DAY, -2);
                 * java.text.SimpleDateFormat sf = new
                 * java.text.SimpleDateFormat( "yyyy-MM-dd HH:mm:ss"); startTime =
                 * sf.format(startData.getTime()); endTime =
                 * sf.format(endData.getTime());
                 *  /* // 故障工单考核 listBynotHold =
                 * tawSheetStatDAO.getAutoSheetSelect( "faultsheet",
                 * Integer.parseInt(saveSessionBeanForm .getDeptid()), Integer
                 * .parseInt(saveSessionBeanForm.getRoomId()),
                 * Integer.parseInt(saveSessionBeanForm.getWorkSerial()),
                 * startTime, endTime, dutyDate, dutyMaster); if
                 * (listBynotHold.size() != 0) { TawSheetStat tawSheetStat = new
                 * TawSheetStat(); TawSheetStatDAO statDAO = new
                 * TawSheetStatDAO(ds); for (int i = 0; i <
                 * listBynotHold.size(); i++) { tawSheetStat = (TawSheetStat)
                 * listBynotHold .elementAt(i); if (statDAO
                 * .getIfStat(tawSheetStat.getSheetId(),
                 * tawSheetStat.getDutyId(), "taw_fault_stat_duty") == false) {
                 * statDAO.insert(tawSheetStat, "taw_fault_stat_duty",
                 * startTime, endTime, url); } } } // 局数据工单考核 listdatasheet =
                 * tawSheetStatDAO.getAutoSheetSelect("datasheet",
                 * Integer.parseInt(saveSessionBeanForm.getDeptid()),
                 * Integer.parseInt(saveSessionBeanForm.getRoomId()),
                 * Integer.parseInt(saveSessionBeanForm.getWorkSerial()),
                 * startTime, endTime, dutyDate, dutyMaster); if
                 * (listdatasheet.size() != 0) { TawSheetStat tawSheetStat = new
                 * TawSheetStat(); TawSheetStatDAO statDAO = new
                 * TawSheetStatDAO(ds); for (int i = 0; i <
                 * listdatasheet.size(); i++) { tawSheetStat = (TawSheetStat)
                 * listdatasheet .elementAt(i); if (statDAO
                 * .getIfStat(tawSheetStat.getSheetId(),
                 * tawSheetStat.getDutyId(), "taw_fault_stat_duty") == false) {
                 * statDAO.insert(tawSheetStat, "taw_fault_stat_duty",
                 * startTime, endTime, url); } } } // 任务工单考核 listtasksheet =
                 * tawSheetStatDAO.getAutoSheetSelect("tasksheet",
                 * Integer.parseInt(saveSessionBeanForm.getDeptid()),
                 * Integer.parseInt(saveSessionBeanForm.getRoomId()),
                 * Integer.parseInt(saveSessionBeanForm.getWorkSerial()),
                 * startTime, endTime, dutyDate, dutyMaster); if
                 * (listtasksheet.size() != 0) { TawSheetStat tawSheetStat = new
                 * TawSheetStat(); TawSheetStatDAO statDAO = new
                 * TawSheetStatDAO(ds); for (int i = 0; i <
                 * listtasksheet.size(); i++) { tawSheetStat = (TawSheetStat)
                 * listtasksheet .elementAt(i); if (statDAO
                 * .getIfStat(tawSheetStat.getSheetId(),
                 * tawSheetStat.getDutyId(), "taw_fault_stat_duty") == false) {
                 * statDAO.insert(tawSheetStat, "taw_fault_stat_duty",
                 * startTime, endTime, url); } } } // 申请工单 listrequsheet =
                 * tawSheetStatDAO.getAutoSheetSelect("requsheet",
                 * Integer.parseInt(saveSessionBeanForm.getDeptid()),
                 * Integer.parseInt(saveSessionBeanForm.getRoomId()),
                 * Integer.parseInt(saveSessionBeanForm.getWorkSerial()),
                 * startTime, endTime, dutyDate, dutyMaster); if
                 * (listrequsheet.size() != 0) { TawSheetStat tawSheetStat = new
                 * TawSheetStat(); TawSheetStatDAO statDAO = new
                 * TawSheetStatDAO(ds); for (int i = 0; i <
                 * listrequsheet.size(); i++) { tawSheetStat = (TawSheetStat)
                 * listrequsheet .elementAt(i); if (statDAO
                 * .getIfStat(tawSheetStat.getSheetId(),
                 * tawSheetStat.getDutyId(), "taw_fault_stat_duty") == false) {
                 * statDAO.insert(tawSheetStat, "taw_fault_stat_duty",
                 * startTime, endTime, url); } } }
                 */
                // end by 何毅
                // 不存在生成main Record

                if (CheckMainRecordExist == false) {
                    tawRmRecordDAO.getNewManiRecord(workserial, roomid);
                    // 保存值班纪录
                    tawRmRecordSub = new TawRmRecordSub();
                    tawRmRecordDAO.saveRecord(tawRmRecord, tawRmRecordSub,
                            saveSessionBeanForm.getIsDutyMaster(), workserial,
                            dutymanid);
                } else {
                    tawRmRecordDAO.update(tawRmRecord);
                }
            } else {
                return mapping.findForward("notdutymaster");
            }
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            tawRmRecord = null;
            coalitionRecord = null;
            dutymanid = null;
            dutymanname = null;
            strDutymasterId = null;
            dutyMasterName = null;
            tawRmRecordSub = null;
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 值班记录编辑保存
     */
    private ActionForward performSaveedit(ActionMapping mapping,
                                          ActionForm actionForm, HttpServletRequest request,
                                          HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        TawRmRecord tawRmRecord = null;
        String dutyrecord = null;
        String notes = null;
        // logBO logbo = null;

        // saveSessionBeanForm =
        // (SaveSessionBeanForm) request.getSession().getAttribute(
        // "SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        TawRmRecordForm form = (TawRmRecordForm) actionForm;

        try {

            tawRmRecordDAO = new TawRmRecordDAO(ds);
            tawRmRecord = new TawRmRecord();
            int workserial = form.getId();
            notes = form.getNotes().trim();
            if (com.boco.eoms.common.util.StaticMethod.getDbType().equals(
                    StaticVariable.ORACLE)) {
                tawRmRecord = tawRmRecordDAO.retrieve(workserial);
            } else {
                notes = form.getNotes().trim();
                tawRmRecord = tawRmRecordDAO.retrieve1(workserial);
            }
            if (tawRmRecord != null) {
                tawRmRecord.setNotes(notes);
                tawRmRecordDAO.update(tawRmRecord);
            }
            // logbo = new logBO(ds);
            // boolean bool =
            // logbo.insertLogToDB(saveSessionBeanForm.getWrf_UserID(),
            // "修改值班记录", "operation");
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            tawRmRecord = null;
            dutyrecord = null;
            notes = null;
            // logbo = null;

        }
        return mapping.findForward("success");

    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 值班记录保存
     */
    private ActionForward performSaveMain(ActionMapping mapping,
                                          ActionForm actionForm, HttpServletRequest request,
                                          HttpServletResponse response) {

        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        TawRmRecordDAO tawRmRecordDAO = null;
        TawRmRecord tawRmRecord = null;
        String userId = null;
        TawRmRecordSub tawRmRecordSub = null;
        String roomid = "";
        String typemode = "";
        int typeMode = 0;
        typemode = StaticMethod.null2String(request.getParameter("typeMode"));
        typeMode = Integer.parseInt(typemode);

        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        TawRmRecordForm form = (TawRmRecordForm) actionForm;

        try {
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            tawRmRecord = new TawRmRecord();
            form.setDutymaster("");
            org.apache.commons.beanutils.BeanUtils.populate(tawRmRecord,
                    org.apache.commons.beanutils.BeanUtils.describe(form));
            userId = StaticMethod.null2String(saveSessionBeanForm.getUserid());
            roomid = StaticMethod.null2String(saveSessionBeanForm.getRoomId());
            int workserial = Integer.parseInt(saveSessionBeanForm
                    .getWorkSerial());
            boolean isDutymaster = saveSessionBeanForm.getIsDutyMaster();
            // 判断登陆人是否当班?
            boolean IsDutyMan = tawRmRecordDAO.receiverIsDutyMan(workserial,
                    userId);
            // 如果登陆人当班
            if (IsDutyMan = true) {
                // 判断值班住记录是否存在
                boolean CheckMainRecordExist = tawRmRecordDAO
                        .receiverCheckMainRecordExist(workserial);
                if (CheckMainRecordExist == false) {
                    tawRmRecordDAO.getNewManiRecord(workserial, roomid);
                }
                // 判断值班子记录是否存在
                boolean CheckSubRecordExist = tawRmRecordDAO
                        .receiverCheckSubRecordExist(workserial, userId);
                // 如果不存在生成值班子记录
                if (CheckSubRecordExist == false) {
                    tawRmRecordDAO.getSaveSubRecord(workserial, userId);
                }
                // 保存值班纪录,值班子记录

                tawRmRecordSub = new TawRmRecordSub();
                // tawRmRecordSub.setDutyman();
                int id = tawRmRecord.getId();
                tawRmRecordDAO.saveRecord1(tawRmRecord, tawRmRecordSub,
                        isDutymaster, workserial, userId, typeMode);
                String startDuty = saveSessionBeanForm.getStartDuty();
                String endDuty = saveSessionBeanForm.getEndDuty();
                /*
                 * List cutoversheetlist = new ArrayList(); List faultsheetlist =
                 * new ArrayList(); List faultreportsheetlist = new ArrayList();
                 * String dutyStartDate = StaticMethod.getCurrentDateTime(
                 * saveSessionBeanForm.getStartDuty(), "yyyy-MM-dd HH:mm:ss") +
                 * ""; String dutyEndDate = StaticMethod
                 * .getCurrentDateTime(saveSessionBeanForm.getEndDuty(),
                 * "yyyy-MM-dd HH:mm:ss") + "";
                 */
                // TODO 值班记录工单连接
                /*
                 * List cutoversheetlist=new ArrayList(); List
                 * faultsheetlist=new ArrayList(); List faultreportsheetlist=new
                 * ArrayList(); String dutyStartDate =
                 * StaticMethod.getCurrentDateTime(saveSessionBeanForm.getStartDuty(),"yyyy-MM-dd
                 * HH:mm:ss") + ""; String dutyEndDate =
                 * StaticMethod.getCurrentDateTime(saveSessionBeanForm.getEndDuty(),"yyyy-MM-dd
                 * HH:mm:ss") + ""; ITawwpExecuteMgr tawwpExecuteMgr =
                 * (ITawwpExecuteMgr) getBean("tawwpExecuteMgr"); String
                 * yearFlag = TawwpUtil.getCurrentDateTime("yyyy"); String
                 * monthFlag = TawwpUtil.getCurrentDateTime("MM");
                 *
                 * cutoversheetlist = tawRmRecordDAO.searchCutoversheetlist(0,
                 * 0, startDuty, endDuty, userId); faultreportsheetlist =
                 * tawRmRecordDAO .searchFaultreportsheetlist(0, 0, startDuty,
                 * endDuty, userId); faultsheetlist =
                 * tawRmRecordDAO.searchFaultsheetlist(0, 0, startDuty, endDuty,
                 * userId); int roomId =
                 * java.lang.Integer.parseInt(saveSessionBeanForm .getRoomId());
                 * List monthPlanVOList = tawwpExecuteMgr.listDutyExecutePlan(
                 * String.valueOf(roomId), yearFlag, monthFlag, dutyStartDate,
                 * dutyEndDate); int count =
                 * tawRmRecordDAO.retrieveTmp(workserial, roomId); if (count ==
                 * 0) { TawRmRecord test = new TawRmRecord();
                 * faultsheetlist.add(test); faultreportsheetlist.add(test);
                 * cutoversheetlist.add(test);
                 * tawRmRecordDAO.getAdditionallist(roomId, workserial,
                 * saveSessionBeanForm.getUserid(), "", "", "", faultsheetlist,
                 * "faultsheet"); tawRmRecordDAO.getAdditionallist(roomId,
                 * workserial, saveSessionBeanForm.getUserid(), "", "", "",
                 * faultreportsheetlist, "faultreportsheet");
                 * tawRmRecordDAO.getAdditionallist(roomId, workserial,
                 * saveSessionBeanForm.getUserid(), "", "", "",
                 * cutoversheetlist, "cutoversheet"); } else if (count <
                 * cutoversheetlist.size() + faultreportsheetlist.size() +
                 * faultsheetlist.size()) {
                 * tawRmRecordDAO.deleteAdditionallist(roomId, workserial);
                 * tawRmRecordDAO.getAdditionallist(roomId, workserial,
                 * saveSessionBeanForm.getUserid(), "", "", "", faultsheetlist,
                 * "faultsheet"); tawRmRecordDAO.getAdditionallist(roomId,
                 * workserial, saveSessionBeanForm.getUserid(), "", "", "",
                 * faultreportsheetlist, "faultreportsheet");
                 * tawRmRecordDAO.getAdditionallist(roomId, workserial,
                 * saveSessionBeanForm.getUserid(), "", "", "",
                 * cutoversheetlist, "cutoversheet"); tawRmRecordDAO
                 * .getAdditionallist( roomId, workserial,
                 * saveSessionBeanForm.getUserid(), "", "", "<a href=" + "'" +
                 * "../tawwpexecute/executeadds.do?monthplanid=",
                 * cutoversheetlist, "plan"); }
                 */
                // logbo = new logBO(ds);
                // boolean bool =
                // logbo.insertLogToDB(saveSessionBeanForm.getUserid(),
                // "保存值班记录", StaticVariable.OPER,
                // request.getRemoteAddr());
            } else { // 如果不是当班人，提示没有此权限
                return mapping.findForward("purview");
            }
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            tawRmRecord = null;
            userId = null;
            tawRmRecordSub = null;
            // logbo = null;

        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 云南值班记录保存
     */
    private ActionForward performYnSaveMain(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {

        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        TawRmRecordDAO tawRmRecordDAO = null;
        TawRmRecord tawRmRecord = null;
        String userId = null;
        TawRmRecordSub tawRmRecordSub = null;
        String roomid = "";
        String typemode = "";
        int typeMode = 0;
        typemode = StaticMethod.null2String(request.getParameter("typeMode"));
        typeMode = Integer.parseInt(typemode);

        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        TawRmRecordForm form = (TawRmRecordForm) actionForm;

        try {
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            tawRmRecord = new TawRmRecord();
            form.setDutymaster("");
            org.apache.commons.beanutils.BeanUtils.populate(tawRmRecord,
                    org.apache.commons.beanutils.BeanUtils.describe(form));
            userId = StaticMethod.null2String(saveSessionBeanForm.getUserid());
            roomid = StaticMethod.null2String(saveSessionBeanForm.getRoomId());
            int workserial = Integer.parseInt(saveSessionBeanForm
                    .getWorkSerial());
            boolean isDutymaster = saveSessionBeanForm.getIsDutyMaster();
            // 判断登陆人是否当班?
            boolean IsDutyMan = tawRmRecordDAO.receiverIsDutyMan(workserial,
                    userId);
            // 如果登陆人当班
            if (IsDutyMan = true) {
                // 判断值班住记录是否存在
                boolean CheckMainRecordExist = tawRmRecordDAO
                        .receiverCheckMainRecordExist(workserial);
                if (CheckMainRecordExist == false) {
                    tawRmRecordDAO.getNewManiRecord(workserial, roomid);
                }
                // 判断值班子记录是否存在
                boolean CheckSubRecordExist = tawRmRecordDAO
                        .receiverCheckSubRecordExist(workserial, userId);
                // 如果不存在生成值班子记录
                if (CheckSubRecordExist == false) {
                    tawRmRecordDAO.getSaveSubRecord(workserial, userId);
                }
                // 保存值班纪录,值班子记录

                tawRmRecordSub = new TawRmRecordSub();
                tawRmRecordSub.setNetfault(tawRmRecord.getNetfault());
                tawRmRecordSub.setImportantaffair(tawRmRecord
                        .getImportantaffair());
                tawRmRecordSub.setHarmony(tawRmRecord.getHarmony());
                tawRmRecordSub.setOtheraffair(tawRmRecord.getOtheraffair());

                tawRmRecordDAO.saveRecord1(tawRmRecord, tawRmRecordSub,
                        isDutymaster, workserial, userId, typeMode);
            }
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            tawRmRecord = null;
            userId = null;
            tawRmRecordSub = null;
        }
        return mapping.findForward("success");
    }

    /**
     * @see 班长交接班，如果密码正确交接成功，出口是offduty.jsp，错误返回 判断是否超时 判断密码是否正确 改主记录
     * 改自己的子记录，确定是否早退 清除Session
     */
    private ActionForward performSubmit(ActionMapping mapping,
                                        ActionForm actionForm, HttpServletRequest request,
                                        HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */

        TawRmRecordDAO tawRmRecordDAO = null;
        String strNextWorkSerial = null;
        String userId = null;
        TawRmRecord tawRmRecord = null;
        SimpleDateFormat dateFormat = null;
        String date = null;
        TawRmRecordSubDAO tawRmRecordSubDAO = null;
        TawRmRecordSub tawRmRecordSub = null;
        TawRmSysteminfoDAO tawRmSysteminfoDAO = null;
        TawRmSysteminfo tawRmSysteminfo = null;
        GregorianCalendar cal_start = null;
        String time_start = null;
        TawRmReliefRecordBO tawRmReliefRecordBO = null;
        int int_stagger = 0;

        TawRmExchangePer per = new TawRmExchangePer();

        // logBO logbo = null;

        // 判断超时
        // saveSessionBeanForm =
        // (SaveSessionBeanForm) request.getSession().getAttribute(
        // "SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        TawRmRecordForm form = (TawRmRecordForm) actionForm;
        form.setStrutsAction(TawRmRecordForm.EDIT);
        try {

            tawRmReliefRecordBO = new TawRmReliefRecordBO(ds);
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            // 判断密码是否正确
            strNextWorkSerial = String.valueOf(request
                    .getParameter("boolNextWorkSerial"));
            boolean boolNextWorkSerial = false;
            if (!strNextWorkSerial.equals("-1")) {
                boolNextWorkSerial = true;
            }
            new Md5PasswordEncoder().encodePassword("pass", null);
            boolean psSubmit = tawRmRecordDAO.getVerifyUser(request
                    .getParameter("Hander"), new Md5PasswordEncoder()
                    .encodePassword(request.getParameter("HanderPwd"), null));
            boolean psSubmitr = true;
            if (boolNextWorkSerial) {
                psSubmitr = tawRmRecordDAO.getVerifyUser(request
                        .getParameter("Receiver"), new Md5PasswordEncoder()
                        .encodePassword(request.getParameter("ReceiverPwd"),
                                null));
                boolNextWorkSerial = !psSubmitr;
            }
            if (!(psSubmit && psSubmitr)) {
                return mapping.findForward("passwordwrong");
            }
            int workserial = Integer.parseInt(saveSessionBeanForm
                    .getWorkSerial());
            int roomId = Integer.parseInt(saveSessionBeanForm.getRoomId());

            if (psSubmit && !boolNextWorkSerial) {
                // 改主记录

                userId = saveSessionBeanForm.getUserid();
                tawRmRecord = tawRmRecordDAO.retrieve1(workserial);
                java.util.Date currentDate = new java.util.Date();
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = dateFormat.format(currentDate);
                // tawRmRecord.setStarttime(date);
                tawRmRecord.setEndtime(date);
                tawRmRecord.setFlag(1);
                tawRmRecordDAO.update(tawRmRecord);
                // 改子记录结束时间
                tawRmRecordDAO.updateSub_setEnd(workserial);
                // 改自己的子记录
                // 取得自己的值班纪录
                tawRmRecordSubDAO = new TawRmRecordSubDAO(ds);
                tawRmRecordSub = tawRmRecordSubDAO.retrieve_sub1(workserial,
                        userId);
                // 取得最大误差时间
                tawRmSysteminfoDAO = new TawRmSysteminfoDAO(ds);
                tawRmSysteminfo = tawRmSysteminfoDAO.retrieve(roomId + "");
                int maxerrortime = 0;
                if (tawRmSysteminfo != null) {
                    maxerrortime = tawRmSysteminfo.getMaxerrortime();
                    int_stagger = tawRmSysteminfo.getStaggertime();
                }
                // 当前时间+最大误差时间，是否属于下班次
                cal_start = new GregorianCalendar();
                cal_start.add(cal_start.MINUTE, maxerrortime);
                time_start = StaticMethod.Cal2String(cal_start);
                int workserial2 = tawRmRecordDAO.receiverWorkSerial(
                        roomId + "", time_start, userId);
                int intWorkflag = tawRmRecordSub.getWorkflag();
                if (workserial2 == workserial) {
                    // 早退
                    if (intWorkflag < 3) {
                        intWorkflag += 2;
                    }
                } else {
                    if (intWorkflag == 0) {
                        intWorkflag = 4;
                    }
                }
                tawRmRecordSub.setWorkflag(intWorkflag);
                tawRmRecordSub.setEndtime(date);
                tawRmRecordSubDAO.update(tawRmRecordSub);

                int intWorkSerialTemp = tawRmRecordDAO.getExSerial(workserial,
                        2, int_stagger);
                if (intWorkSerialTemp >= 0) {
                    TawRmRecordPerDAO tawRmRecordPerDAO = new TawRmRecordPerDAO(
                            ds);
                    tawRmRecordPerDAO.hangover(workserial, intWorkSerialTemp);
                }

                tawRmReliefRecordBO.insertReliefRecord(request
                                .getParameter("Hander"), request
                                .getParameter("Receiver"), roomId + "", date, "", "1",
                        userId, workserial + "", date);
                if (actionForm != null) {
                    if (actionForm instanceof TawRmRecordForm) {
                        TawRmRecordForm trrf = (TawRmRecordForm) actionForm;
                        per.setAlAlarmNum(trrf.getAlAlarmNum());
                        per.setWorkSerial(workserial2 + "");
                        per.setExchangeTime(trrf.getExchangeTime());
                        per.setPrAlarmNums(trrf.getPrAlarmNums());
                        per.setSendSheetNum(trrf.getSendSheetNum());
                        per.setNoAlarmNum(trrf.getNoAlarmNum());
                        per.setOperationalState(trrf.getOperationalState());
                        per.setYeLinetype(trrf.getYeLinetype());
                        per.setToLinetype(trrf.getToLinetype());
                        per.setDifLinetype(trrf.getDifLinetype());
                        per.setLeaveSheetNum(trrf.getLeaveSheetNum());
                        per.setLeaveSheetNums(trrf.getLeaveSheetNums());
                        per.setChiefSheetNum(trrf.getChiefSheetNum());
                        per.setPurpose(trrf.getPurpose());
                        per.setReceiver(request.getParameter("Receiver"));
                        per.setCompleteFlag(trrf.getCompleteFlag());
                        per.setBalance(trrf.getBalance());
                        per.setAttentive(trrf.getAttentive());
                        per.setCity(trrf.getCity());
                        per.setSheetId(trrf.getSheetId());
                        per.setReason(trrf.getReason());
                        per.setPhone(trrf.getPhone());
                        per.setPerson(trrf.getPerson());
                        per.setRate(trrf.getRate());
                        per.setFlag(trrf.getExFlag());
                    }
                }
                Object mgr = getBean("tawRmExchangePerManager");
                tawRmExchangePerManager = (ITawRmExchangePerManager) mgr;
                tawRmExchangePerManager.saveTawRmExchangePer(per);

                /**
                 * 交接班之后保存工单处理相关信息
                 * ID:EOMS-方永峰-20090922
                 * 邮箱：fangyongfeng@boco.com.cn
                 * 时间：2009-09-22
                 * @param
                 */
                TawRmAssignSubDAO tawRmAssignSubDAO = new TawRmAssignSubDAO(ds);
                List list = tawRmAssignSubDAO.getUserIdListByWorkSerial(saveSessionBeanForm.getWorkSerial());
                for (int i = 0; i < list.size(); i++) {
                    saveDutyInfo(list.get(i).toString(), saveSessionBeanForm.getDeptid(), saveSessionBeanForm.getWorkSerial());
                }
                /**
                 * 修改结束：EOMS-方永峰-20090922
                 */

                // TawRmExchangePerManagerImpl trepmi =
                // (TawRmExchangePerManagerImpl)mgr;
                // trepmi.saveTawRmExchangePer(per);

                // mgr.saveTawRmExchangePer(per);
                /*
                 * // begin by 何毅,对没有归档的人考核 Vector listBynotHold = new Vector(); //
                 * 故障工单 Vector listdatasheet = new Vector(); // 局数据工单 Vector
                 * listtasksheet = new Vector(); // 任务工单 Vector listrequsheet =
                 * new Vector(); // 申请工单 TawSheetStatDAO tawSheetStatDAO = new
                 * TawSheetStatDAO(ds); List list =
                 * tawSheetStatDAO.getTime(Integer
                 * .parseInt(saveSessionBeanForm.getWorkSerial()), Integer
                 * .parseInt(saveSessionBeanForm.getRoomId())); String startTime =
                 * list.get(0).toString(); String endTime =
                 * list.get(1).toString(); String dutyDate =
                 * list.get(2).toString(); String dutyMaster =
                 * list.get(3).toString(); String url = "";
                 * StaticMethod.getTimestamp(startTime).getTime();
                 *
                 * java.util.Calendar startData =
                 * java.util.Calendar.getInstance(); java.util.Calendar endData =
                 * java.util.Calendar.getInstance();
                 *
                 * startData.setTime((java.util.Date) StaticMethod
                 * .getTimestamp(startTime)); endData.setTime((java.util.Date)
                 * StaticMethod .getTimestamp(endTime));
                 * startData.add(java.util.Calendar.HOUR_OF_DAY, -2);
                 * endData.add(java.util.Calendar.HOUR_OF_DAY, -2);
                 * java.text.SimpleDateFormat sf = new
                 * java.text.SimpleDateFormat( "yyyy-MM-dd HH:mm:ss"); startTime =
                 * sf.format(startData.getTime()); endTime =
                 * sf.format(endData.getTime());
                 */

                // 故障工单考核
                /*
                 * listBynotHold = tawSheetStatDAO.getAutoSheetSelect(
                 * "faultsheet", Integer.parseInt(saveSessionBeanForm
                 * .getDeptid()), Integer
                 * .parseInt(saveSessionBeanForm.getRoomId()),
                 * Integer.parseInt(saveSessionBeanForm.getWorkSerial()),
                 * startTime, endTime, dutyDate, dutyMaster); if
                 * (listBynotHold.size() != 0) { TawSheetStat tawSheetStat = new
                 * TawSheetStat(); TawSheetStatDAO statDAO = new
                 * TawSheetStatDAO(ds); for (int i = 0; i <
                 * listBynotHold.size(); i++) { tawSheetStat = (TawSheetStat)
                 * listBynotHold .elementAt(i); if (statDAO
                 * .getIfStat(tawSheetStat.getSheetId(),
                 * tawSheetStat.getDutyId(), "taw_fault_stat_duty") == false) {
                 * statDAO.insert(tawSheetStat, "taw_fault_stat_duty",
                 * startTime, endTime, url); } } } // 局数据工单考核 listdatasheet =
                 * tawSheetStatDAO.getAutoSheetSelect("datasheet",
                 * Integer.parseInt(saveSessionBeanForm.getDeptid()),
                 * Integer.parseInt(saveSessionBeanForm.getRoomId()),
                 * Integer.parseInt(saveSessionBeanForm.getWorkSerial()),
                 * startTime, endTime, dutyDate, dutyMaster); if
                 * (listdatasheet.size() != 0) { TawSheetStat tawSheetStat = new
                 * TawSheetStat(); TawSheetStatDAO statDAO = new
                 * TawSheetStatDAO(ds); for (int i = 0; i <
                 * listdatasheet.size(); i++) { tawSheetStat = (TawSheetStat)
                 * listdatasheet .elementAt(i); if (statDAO
                 * .getIfStat(tawSheetStat.getSheetId(),
                 * tawSheetStat.getDutyId(), "taw_fault_stat_duty") == false) {
                 * statDAO.insert(tawSheetStat, "taw_fault_stat_duty",
                 * startTime, endTime, url); } } } // 任务工单考核 listtasksheet =
                 * tawSheetStatDAO.getAutoSheetSelect("tasksheet",
                 * Integer.parseInt(saveSessionBeanForm.getDeptid()),
                 * Integer.parseInt(saveSessionBeanForm.getRoomId()),
                 * Integer.parseInt(saveSessionBeanForm.getWorkSerial()),
                 * startTime, endTime, dutyDate, dutyMaster); if
                 * (listtasksheet.size() != 0) { TawSheetStat tawSheetStat = new
                 * TawSheetStat(); TawSheetStatDAO statDAO = new
                 * TawSheetStatDAO(ds); for (int i = 0; i <
                 * listtasksheet.size(); i++) { tawSheetStat = (TawSheetStat)
                 * listtasksheet .elementAt(i); if (statDAO
                 * .getIfStat(tawSheetStat.getSheetId(),
                 * tawSheetStat.getDutyId(), "taw_fault_stat_duty") == false) {
                 * statDAO.insert(tawSheetStat, "taw_fault_stat_duty",
                 * startTime, endTime, url); } } } // 申请工单 listrequsheet =
                 * tawSheetStatDAO.getAutoSheetSelect("requsheet",
                 * Integer.parseInt(saveSessionBeanForm.getDeptid()),
                 * Integer.parseInt(saveSessionBeanForm.getRoomId()),
                 * Integer.parseInt(saveSessionBeanForm.getWorkSerial()),
                 * startTime, endTime, dutyDate, dutyMaster); if
                 * (listrequsheet.size() != 0) { TawSheetStat tawSheetStat = new
                 * TawSheetStat(); TawSheetStatDAO statDAO = new
                 * TawSheetStatDAO(ds); for (int i = 0; i <
                 * listrequsheet.size(); i++) { tawSheetStat = (TawSheetStat)
                 * listrequsheet .elementAt(i); if (statDAO
                 * .getIfStat(tawSheetStat.getSheetId(),
                 * tawSheetStat.getDutyId(), "taw_fault_stat_duty") == false) {
                 * statDAO.insert(tawSheetStat, "taw_fault_stat_duty",
                 * startTime, endTime, url); } } }
                 */
                // end by 何毅
                // 清除Session
                saveSessionBeanForm.setIsDutyMaster(false);
                saveSessionBeanForm.setWorkSerial("0");
                saveSessionBeanForm.setRoomId("0");
                // logbo = new logBO(ds);
                // boolean bool =
                // logbo.insertLogToDB(saveSessionBeanForm.getWrf_UserID(),
                // "交接班", StaticVariable.OPER,
                // request.getRemoteAddr());
            } else {
                return mapping.findForward("failure");
            }
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            strNextWorkSerial = null;
            userId = null;
            tawRmRecord = null;
            dateFormat = null;
            date = null;
            tawRmRecordSubDAO = null;
            tawRmRecordSub = null;
            tawRmSysteminfoDAO = null;
            tawRmSysteminfo = null;
            cal_start = null;
            time_start = null;
            // logbo = null;
        }
        return mapping.findForward("success");
    }

    /**
     * @see 任意人交接班，如果密码正确交接成功，出口是offduty.jsp，错误返回 判断是否超时 判断密码是否正确 改主记录
     * 改自己的子记录，确定是否早退 清除Session
     */
    private ActionForward performSubmit2(ActionMapping mapping,
                                         ActionForm actionForm, HttpServletRequest request,
                                         HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        String strNextWorkSerial = null;
        String userId = null;
        TawRmRecord tawRmRecord = null;
        SimpleDateFormat dateFormat = null;
        String date = null;
        TawRmRecordSubDAO tawRmRecordSubDAO = null;
        TawRmRecordSub tawRmRecordSub = null;
        TawRmSysteminfoDAO tawRmSysteminfoDAO = null;
        TawRmSysteminfo tawRmSysteminfo = null;
        GregorianCalendar cal_start = null;
        String time_start = null;
        int stagger = 0;
        TawRmExchangePer per = new TawRmExchangePer();
        // logBO logbo = null;

        // 判断超时
        // saveSessionBeanForm =
        // (SaveSessionBeanForm) request.getSession().getAttribute(
        // "SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        TawRmRecordForm form = (TawRmRecordForm) actionForm;
        form.setStrutsAction(TawRmRecordForm.EDIT);
        try {
            TawRmReliefRecordBO tawRmReliefRecordBO = new TawRmReliefRecordBO(ds);
            tawRmRecordDAO = new TawRmRecordDAO(ds);

            // 判断密码是否正确
            strNextWorkSerial = String.valueOf(request
                    .getParameter("boolNextWorkSerial"));
            boolean boolNextWorkSerial = false;
            if (!strNextWorkSerial.equals("-1")) {
                boolNextWorkSerial = true;
            }
            boolean psSubmit = tawRmRecordDAO.getVerifyUser(request
                    .getParameter("Hander"), new Md5PasswordEncoder()
                    .encodePassword(request.getParameter("HanderPwd"), null));
            boolean psSubmitr = true;
            if (boolNextWorkSerial) {
                psSubmitr = tawRmRecordDAO.getVerifyUser(request
                        .getParameter("Receiver"), new Md5PasswordEncoder()
                        .encodePassword(request.getParameter("ReceiverPwd"),
                                null));
                boolNextWorkSerial = !psSubmitr;
            }
            if (!(psSubmit && psSubmitr)) {
                return mapping.findForward("passwordwrong");
            }

            if (psSubmit && !boolNextWorkSerial) {

                // 改主记录
                int workserial = Integer.parseInt(saveSessionBeanForm
                        .getWorkSerial());
                int roomId = Integer.parseInt(saveSessionBeanForm.getRoomId());
                userId = saveSessionBeanForm.getUserid();
                tawRmRecord = tawRmRecordDAO.retrieve1(workserial);
                java.util.Date currentDate = new java.util.Date();
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = dateFormat.format(currentDate);
                // tawRmRecord.setStarttime(date);
                tawRmRecord.setEndtime(date);
                tawRmRecord.setFlag(1);
                tawRmRecordDAO.update(tawRmRecord);
                // 改子记录结束时间
                tawRmRecordDAO.updateSub_setEnd(workserial);
                // 改自己的子记录
                // 取得自己的值班纪录
                tawRmRecordSubDAO = new TawRmRecordSubDAO(ds);
                tawRmRecordSub = tawRmRecordSubDAO.retrieve_sub1(workserial,
                        userId);
                // 取得最大误差时间
                tawRmSysteminfoDAO = new TawRmSysteminfoDAO(ds);
                tawRmSysteminfo = tawRmSysteminfoDAO.retrieve(roomId + "");
                int maxerrortime = 0;
                if (tawRmSysteminfo != null) {
                    stagger = tawRmSysteminfo.getStaggertime();
                    maxerrortime = tawRmSysteminfo.getMaxerrortime();
                }
                // 当前时间+最大误差时间，是否属于下班次
                cal_start = new GregorianCalendar();
                cal_start.add(cal_start.MINUTE, maxerrortime);
                time_start = StaticMethod.Cal2String(cal_start);
                int workserial2 = tawRmRecordDAO.receiverWorkSerial(
                        roomId + "", time_start, userId);
                int intWorkflag = intWorkflag = tawRmRecordSub.getWorkflag();
                if (workserial2 == workserial) {
                    // 早退
                    if (intWorkflag < 3) {
                        intWorkflag += 2;
                    }
                } else {
                    if (intWorkflag == 0) {
                        intWorkflag = 4;
                    }
                }
                tawRmRecordSub.setWorkflag(intWorkflag);
                tawRmRecordSub.setEndtime(date);
                tawRmRecordSubDAO.update(tawRmRecordSub);

                int intWorkSerialTemp = tawRmRecordDAO.getExSerial(workserial,
                        2, stagger);
                if (intWorkSerialTemp >= 0) {
                    TawRmRecordPerDAO tawRmRecordPerDAO = new TawRmRecordPerDAO(
                            ds);
                    tawRmRecordPerDAO.hangover(workserial, intWorkSerialTemp);
                }
                tawRmReliefRecordBO.insertReliefRecord(request
                                .getParameter("Hander"), request
                                .getParameter("Receiver"), roomId + "", date, "", "1",
                        userId, workserial + "", date);
                if (actionForm != null) {
                    if (actionForm instanceof TawRmRecordForm) {
                        TawRmRecordForm trrf = (TawRmRecordForm) actionForm;
                        per.setAlAlarmNum(trrf.getAlAlarmNum());
                        per.setWorkSerial(saveSessionBeanForm.getWorkSerial());
                        per.setExchangeTime(trrf.getExchangeTime());
                        per.setPrAlarmNums(trrf.getPrAlarmNums());
                        per.setSendSheetNum(trrf.getSendSheetNum());
                        per.setNoAlarmNum(trrf.getNoAlarmNum());
                        per.setOperationalState(trrf.getOperationalState());
                        per.setYeLinetype(trrf.getYeLinetype());
                        per.setToLinetype(trrf.getToLinetype());
                        per.setReceiver(request.getParameter("Receiver"));
                        per.setDifLinetype(trrf.getDifLinetype());
                        per.setLeaveSheetNum(trrf.getLeaveSheetNum());
                        per.setLeaveSheetNums(trrf.getLeaveSheetNums());
                        per.setChiefSheetNum(trrf.getChiefSheetNum());
                        per.setPurpose(trrf.getPurpose());
                        per.setCompleteFlag(trrf.getCompleteFlag());
                        per.setBalance(trrf.getBalance());
                        per.setAttentive(trrf.getAttentive());
                        per.setCity(trrf.getCity());
                        per.setSheetId(trrf.getSheetId());
                        per.setReason(trrf.getReason());
                        per.setPhone(trrf.getPhone());
                        per.setPerson(trrf.getPerson());
                        per.setRate(trrf.getRate());
                        per.setFlag(trrf.getExFlag());
                    }
                }
                Object mgr = getBean("tawRmExchangePerManager");
                tawRmExchangePerManager = (ITawRmExchangePerManager) mgr;
                tawRmExchangePerManager.saveTawRmExchangePer(per);

                /**
                 * 交接班之后保存工单处理相关信息
                 * ID:EOMS-方永峰-20090922
                 * 邮箱：fangyongfeng@boco.com.cn
                 * 时间：2009-09-22
                 * @param
                 */
                TawRmAssignSubDAO tawRmAssignSubDAO = new TawRmAssignSubDAO(ds);
                List list = tawRmAssignSubDAO.getUserIdListByWorkSerial(saveSessionBeanForm.getWorkSerial());
                for (int i = 0; i < list.size(); i++) {
                    saveDutyInfo(list.get(i).toString(), saveSessionBeanForm.getDeptid(), saveSessionBeanForm.getWorkSerial());
                }
                /**
                 * 修改结束：EOMS-方永峰-20090922
                 */

                // begin by 何毅,对没有归档的人考核
                /*
                 * Vector listBynotHold = new Vector(); // 故障工单 Vector
                 * listdatasheet = new Vector(); // 局数据工单 Vector listtasksheet =
                 * new Vector(); // 任务工单 Vector listrequsheet = new Vector(); //
                 * 申请工单 TawSheetStatDAO tawSheetStatDAO = new
                 * TawSheetStatDAO(ds); List list =
                 * tawSheetStatDAO.getTime(Integer
                 * .parseInt(saveSessionBeanForm.getWorkSerial()), Integer
                 * .parseInt(saveSessionBeanForm.getRoomId())); String startTime =
                 * list.get(0).toString(); String endTime =
                 * list.get(1).toString(); String dutyDate =
                 * list.get(2).toString(); String dutyMaster =
                 * list.get(3).toString(); String url = ""; //
                 * StaticMethod.getTimestamp(startTime).getTime();
                 *
                 * java.util.Calendar startData =
                 * java.util.Calendar.getInstance(); java.util.Calendar endData =
                 * java.util.Calendar.getInstance();
                 *
                 * startData.setTime((java.util.Date) StaticMethod
                 * .getTimestamp(startTime)); endData.setTime((java.util.Date)
                 * StaticMethod .getTimestamp(endTime));
                 * startData.add(java.util.Calendar.HOUR_OF_DAY, -2);
                 * endData.add(java.util.Calendar.HOUR_OF_DAY, -2);
                 * java.text.SimpleDateFormat sf = new
                 * java.text.SimpleDateFormat( "yyyy-MM-dd HH:mm:ss"); startTime =
                 * sf.format(startData.getTime());
                 */
                // endTime = sf.format(endData.getTime());
                /*
                 * // 故障工单考核 listBynotHold = tawSheetStatDAO.getAutoSheetSelect(
                 * "faultsheet", Integer.parseInt(saveSessionBeanForm
                 * .getDeptid()), Integer
                 * .parseInt(saveSessionBeanForm.getRoomId()),
                 * Integer.parseInt(saveSessionBeanForm.getWorkSerial()),
                 * startTime, endTime, dutyDate, dutyMaster); if
                 * (listBynotHold.size() != 0) { TawSheetStat tawSheetStat = new
                 * TawSheetStat(); TawSheetStatDAO statDAO = new
                 * TawSheetStatDAO(ds); for (int i = 0; i <
                 * listBynotHold.size(); i++) { tawSheetStat = (TawSheetStat)
                 * listBynotHold .elementAt(i); if (statDAO
                 * .getIfStat(tawSheetStat.getSheetId(),
                 * tawSheetStat.getDutyId(), "taw_fault_stat_duty") == false) {
                 * statDAO.insert(tawSheetStat, "taw_fault_stat_duty",
                 * startTime, endTime, url); } } } // 局数据工单考核 listdatasheet =
                 * tawSheetStatDAO.getAutoSheetSelect("datasheet",
                 * Integer.parseInt(saveSessionBeanForm.getDeptid()),
                 * Integer.parseInt(saveSessionBeanForm.getRoomId()),
                 * Integer.parseInt(saveSessionBeanForm.getWorkSerial()),
                 * startTime, endTime, dutyDate, dutyMaster); if
                 * (listdatasheet.size() != 0) { TawSheetStat tawSheetStat = new
                 * TawSheetStat(); TawSheetStatDAO statDAO = new
                 * TawSheetStatDAO(ds); for (int i = 0; i <
                 * listdatasheet.size(); i++) { tawSheetStat = (TawSheetStat)
                 * listdatasheet .elementAt(i); if (statDAO
                 * .getIfStat(tawSheetStat.getSheetId(),
                 * tawSheetStat.getDutyId(), "taw_fault_stat_duty") == false) {
                 * statDAO.insert(tawSheetStat, "taw_fault_stat_duty",
                 * startTime, endTime, url); } } } // 任务工单考核 listtasksheet =
                 * tawSheetStatDAO.getAutoSheetSelect("tasksheet",
                 * Integer.parseInt(saveSessionBeanForm.getDeptid()),
                 * Integer.parseInt(saveSessionBeanForm.getRoomId()),
                 * Integer.parseInt(saveSessionBeanForm.getWorkSerial()),
                 * startTime, endTime, dutyDate, dutyMaster); if
                 * (listtasksheet.size() != 0) { TawSheetStat tawSheetStat = new
                 * TawSheetStat(); TawSheetStatDAO statDAO = new
                 * TawSheetStatDAO(ds); for (int i = 0; i <
                 * listtasksheet.size(); i++) { tawSheetStat = (TawSheetStat)
                 * listtasksheet .elementAt(i); if (statDAO
                 * .getIfStat(tawSheetStat.getSheetId(),
                 * tawSheetStat.getDutyId(), "taw_fault_stat_duty") == false) {
                 * statDAO.insert(tawSheetStat, "taw_fault_stat_duty",
                 * startTime, endTime, url); } } } // 申请工单 listrequsheet =
                 * tawSheetStatDAO.getAutoSheetSelect("requsheet",
                 * Integer.parseInt(saveSessionBeanForm.getDeptid()),
                 * Integer.parseInt(saveSessionBeanForm.getRoomId()),
                 * Integer.parseInt(saveSessionBeanForm.getWorkSerial()),
                 * startTime, endTime, dutyDate, dutyMaster); if
                 * (listrequsheet.size() != 0) { TawSheetStat tawSheetStat = new
                 * TawSheetStat(); TawSheetStatDAO statDAO = new
                 * TawSheetStatDAO(ds); for (int i = 0; i <
                 * listrequsheet.size(); i++) { tawSheetStat = (TawSheetStat)
                 * listrequsheet .elementAt(i); if (statDAO
                 * .getIfStat(tawSheetStat.getSheetId(),
                 * tawSheetStat.getDutyId(), "taw_fault_stat_duty") == false) {
                 * statDAO.insert(tawSheetStat, "taw_fault_stat_duty",
                 * startTime, endTime, url); } } }
                 */
                // end by 何毅
                // 清除Session
                saveSessionBeanForm.setIsDutyMaster(false);
                saveSessionBeanForm.setWorkSerial("0");
                saveSessionBeanForm.setRoomId("0");
                // logbo = new logBO(ds);
                // boolean bool =
                // logbo.insertLogToDB(saveSessionBeanForm.getWrf_UserID(),
                // "交接班", StaticVariable.OPER,
                // request.getRemoteAddr());
            } else {
                return mapping.findForward("failure");
            }
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            strNextWorkSerial = null;
            userId = null;
            tawRmRecord = null;
            dateFormat = null;
            date = null;
            tawRmRecordSubDAO = null;
            tawRmRecordSub = null;
            tawRmSysteminfoDAO = null;
            tawRmSysteminfo = null;
            cal_start = null;
            time_start = null;
            // logbo = null;
        }
        return mapping.findForward("success");

    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return 判断是否超时 判断密码是否正确 改主记录 改自己的子记录，确定是否早退 清除Session
     * @see 所有人交接班，如果密码正确交接成功，出口是offduty.jsp，错误返回
     */
    private ActionForward performSubmit3(ActionMapping mapping,
                                         ActionForm actionForm, HttpServletRequest request,
                                         HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        String strNextWorkSerial = null;
        String userId = null;
        TawRmRecord tawRmRecord = null;
        SimpleDateFormat dateFormat = null;
        String date = null;
        TawRmRecordSubDAO tawRmRecordSubDAO = null;
        TawRmRecordSub tawRmRecordSub = null;
        TawRmSysteminfoDAO tawRmSysteminfoDAO = null;
        TawRmSysteminfo tawRmSysteminfo = null;
        GregorianCalendar cal_start = null;
        String time_start = null;
        int staggerTime = 0;
        int roomId = 0;
        TawRmExchangePer per = new TawRmExchangePer();
        // logBO logbo = null;

        // 判断超时
        // saveSessionBeanForm =
        // (SaveSessionBeanForm) request.getSession().getAttribute(
        // "SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        // 判断是否值班
        if (saveSessionBeanForm.getWorkSerial().equals("0")) {
            return mapping.findForward("notonduty");
        }

        TawRmRecordForm form = (TawRmRecordForm) actionForm;
        form.setStrutsAction(TawRmRecordForm.EDIT);
        try {
            TawRmReliefRecordBO tawRmReliefRecordBO = new TawRmReliefRecordBO(ds);
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            roomId = Integer.parseInt(saveSessionBeanForm.getRoomId());
            tawRmSysteminfoDAO = new TawRmSysteminfoDAO(ds);
            tawRmSysteminfo = tawRmSysteminfoDAO.retrieve(roomId + "");
            if (tawRmSysteminfo != null) {
                staggerTime = tawRmSysteminfo.getStaggertime();
            }
            // 判断密码是否正确
            strNextWorkSerial = String.valueOf(request
                    .getParameter("boolNextWorkSerial"));
            boolean boolNextWorkSerial = false;
            if (!strNextWorkSerial.equals("-1")) {
                boolNextWorkSerial = true;
            }
            boolean psSubmit = true;
            boolean psSubmitr = true;

            int workserial = Integer.parseInt(saveSessionBeanForm
                    .getWorkSerial());
            Vector vecDutyManId = tawRmRecordDAO.getOneAssignMember(workserial);
            String strTemp = "";
            for (int i = 0; i < vecDutyManId.size(); i++) {
                strTemp = "hander_" + String.valueOf(vecDutyManId.elementAt(i));
                strTemp = request.getParameter(strTemp);
                psSubmit = psSubmit
                        && tawRmRecordDAO.getVerifyUser(String
                                .valueOf(vecDutyManId.elementAt(i)),
                        new Md5PasswordEncoder().encodePassword(
                                strTemp, null));
            }

            if (psSubmit && boolNextWorkSerial) {
                int intWorkSerialTemp = tawRmRecordDAO.getExSerial(workserial,
                        2, staggerTime);
                Vector vecReceiverId = tawRmRecordDAO
                        .getOneAssignMember(intWorkSerialTemp);
                for (int i = 0; i < vecReceiverId.size(); i++) {
                    strTemp = "receiver_"
                            + String.valueOf(vecReceiverId.elementAt(i));
                    strTemp = request.getParameter(strTemp);

                    psSubmitr = psSubmitr
                            && tawRmRecordDAO.getVerifyUser(String
                                    .valueOf(vecDutyManId.elementAt(i)),
                            new Md5PasswordEncoder().encodePassword(
                                    strTemp, null));
                }
                boolNextWorkSerial = !psSubmitr;
            }

            if (!(psSubmit && psSubmitr)) {
                return mapping.findForward("passwordwrong");
            }

            if (psSubmit && !boolNextWorkSerial) {
                // 改主记录

                userId = saveSessionBeanForm.getUserid();
                tawRmRecord = tawRmRecordDAO.retrieve1(workserial);
                java.util.Date currentDate = new java.util.Date();
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = dateFormat.format(currentDate);
                // tawRmRecord.setStarttime(date);
                tawRmRecord.setEndtime(date);
                tawRmRecord.setFlag(1);
                tawRmRecordDAO.update(tawRmRecord);
                // 改子记录结束时间
                tawRmRecordDAO.updateSub_setEnd(workserial);
                // 改自己的子记录
                // 取得自己的值班纪录
                tawRmRecordSubDAO = new TawRmRecordSubDAO(ds);
                tawRmRecordSub = tawRmRecordSubDAO.retrieve_sub1(workserial,
                        userId);
                // 取得最大误差时间

                int maxerrortime = 0;
                if (tawRmSysteminfo != null) {
                    maxerrortime = tawRmSysteminfo.getMaxerrortime();
                }
                // 当前时间+最大误差时间，是否属于下班次
                cal_start = new GregorianCalendar();
                cal_start.add(cal_start.MINUTE, maxerrortime);
                time_start = StaticMethod.Cal2String(cal_start);
                int workserial2 = tawRmRecordDAO.receiverWorkSerial(
                        roomId + "", time_start, userId);
                int intWorkflag = intWorkflag = tawRmRecordSub.getWorkflag();
                if (workserial2 == workserial) {
                    // 早退
                    if (intWorkflag < 3) {
                        intWorkflag += 2;
                    }
                } else {
                    if (intWorkflag == 0) {
                        intWorkflag = 4;
                    }
                }
                tawRmRecordSub.setWorkflag(intWorkflag);
                tawRmRecordSub.setEndtime(date);
                tawRmRecordSubDAO.update(tawRmRecordSub);

                int intWorkSerialTemp = tawRmRecordDAO.getExSerial(workserial,
                        2, staggerTime);
                if (intWorkSerialTemp >= 0) {
                    TawRmRecordPerDAO tawRmRecordPerDAO = new TawRmRecordPerDAO(
                            ds);
                    tawRmRecordPerDAO.hangover(workserial, intWorkSerialTemp);
                }
                tawRmReliefRecordBO.insertReliefRecord(request
                                .getParameter("Hander"), request
                                .getParameter("Receiver"), roomId + "", date, "", "1",
                        userId, workserial + "", date);
                if (actionForm != null) {
                    if (actionForm instanceof TawRmRecordForm) {
                        TawRmRecordForm trrf = (TawRmRecordForm) actionForm;
                        per.setAlAlarmNum(trrf.getAlAlarmNum());
                        per.setWorkSerial(saveSessionBeanForm.getWorkSerial());
                        per.setExchangeTime(trrf.getExchangeTime());
                        per.setPrAlarmNums(trrf.getPrAlarmNums());
                        per.setSendSheetNum(trrf.getSendSheetNum());
                        per.setNoAlarmNum(trrf.getNoAlarmNum());
                        per.setOperationalState(trrf.getOperationalState());
                        per.setYeLinetype(trrf.getYeLinetype());
                        per.setToLinetype(trrf.getToLinetype());
                        per.setDifLinetype(trrf.getDifLinetype());
                        per.setLeaveSheetNum(trrf.getLeaveSheetNum());
                        per.setLeaveSheetNums(trrf.getLeaveSheetNums());
                        per.setChiefSheetNum(trrf.getChiefSheetNum());
                        per.setPurpose(trrf.getPurpose());
                        per.setReceiver(request.getParameter("Receiver"));
                        per.setCompleteFlag(trrf.getCompleteFlag());
                        per.setBalance(trrf.getBalance());
                        per.setAttentive(trrf.getAttentive());
                        per.setCity(trrf.getCity());
                        per.setSheetId(trrf.getSheetId());
                        per.setReason(trrf.getReason());
                        per.setPhone(trrf.getPhone());
                        per.setPerson(trrf.getPerson());
                        per.setRate(trrf.getRate());
                        per.setFlag(trrf.getExFlag());
                    }
                }
                Object mgr = getBean("tawRmExchangePerManager");
                tawRmExchangePerManager = (ITawRmExchangePerManager) mgr;
                tawRmExchangePerManager.saveTawRmExchangePer(per);

                /**
                 * 交接班之后保存工单处理相关信息
                 * ID:EOMS-方永峰-20090922
                 * 邮箱：fangyongfeng@boco.com.cn
                 * 时间：2009-09-22
                 * @param
                 */
                saveDutyInfo(saveSessionBeanForm.getUserid(), saveSessionBeanForm.getDeptid(), saveSessionBeanForm.getUserid());
                /**
                 * 修改结束：EOMS-方永峰-20090922
                 */

                // begin by 何毅,对没有归档的人考核
                /*
                 * Vector listBynotHold = new Vector(); // 故障工单 Vector
                 * listdatasheet = new Vector(); // 局数据工单 Vector listtasksheet =
                 * new Vector(); // 任务工单 Vector listrequsheet = new Vector(); //
                 * 申请工单 TawSheetStatDAO tawSheetStatDAO = new
                 * TawSheetStatDAO(ds); List list =
                 * tawSheetStatDAO.getTime(Integer
                 * .parseInt(saveSessionBeanForm.getWorkSerial()), Integer
                 * .parseInt(saveSessionBeanForm.getRoomId())); String startTime =
                 * list.get(0).toString(); String endTime =
                 * list.get(1).toString(); String dutyDate =
                 * list.get(2).toString(); String dutyMaster =
                 * list.get(3).toString(); String url = "";
                 * StaticMethod.getTimestamp(startTime).getTime();
                 *
                 * java.util.Calendar startData =
                 * java.util.Calendar.getInstance(); java.util.Calendar endData =
                 * java.util.Calendar.getInstance();
                 *
                 * startData.setTime((java.util.Date) StaticMethod
                 * .getTimestamp(startTime)); endData.setTime((java.util.Date)
                 * StaticMethod .getTimestamp(endTime));
                 * startData.add(java.util.Calendar.HOUR_OF_DAY, -2);
                 * endData.add(java.util.Calendar.HOUR_OF_DAY, -2);
                 * java.text.SimpleDateFormat sf = new
                 * java.text.SimpleDateFormat( "yyyy-MM-dd HH:mm:ss"); startTime =
                 * sf.format(startData.getTime()); endTime =
                 * sf.format(endData.getTime());
                 */
                // 故障工单考核
                /*
                 * listBynotHold = tawSheetStatDAO.getAutoSheetSelect(
                 * "faultsheet", Integer.parseInt(saveSessionBeanForm
                 * .getDeptid()), Integer
                 * .parseInt(saveSessionBeanForm.getRoomId()),
                 * Integer.parseInt(saveSessionBeanForm.getWorkSerial()),
                 * startTime, endTime, dutyDate, dutyMaster); if
                 * (listBynotHold.size() != 0) { TawSheetStat tawSheetStat = new
                 * TawSheetStat(); TawSheetStatDAO statDAO = new
                 * TawSheetStatDAO(ds); for (int i = 0; i <
                 * listBynotHold.size(); i++) { tawSheetStat = (TawSheetStat)
                 * listBynotHold .elementAt(i); if (statDAO
                 * .getIfStat(tawSheetStat.getSheetId(),
                 * tawSheetStat.getDutyId(), "taw_fault_stat_duty") == false) {
                 * statDAO.insert(tawSheetStat, "taw_fault_stat_duty",
                 * startTime, endTime, url); } } } // 局数据工单考核 listdatasheet =
                 * tawSheetStatDAO.getAutoSheetSelect("datasheet",
                 * Integer.parseInt(saveSessionBeanForm.getDeptid()),
                 * Integer.parseInt(saveSessionBeanForm.getRoomId()),
                 * Integer.parseInt(saveSessionBeanForm.getWorkSerial()),
                 * startTime, endTime, dutyDate, dutyMaster); if
                 * (listdatasheet.size() != 0) { TawSheetStat tawSheetStat = new
                 * TawSheetStat(); TawSheetStatDAO statDAO = new
                 * TawSheetStatDAO(ds); for (int i = 0; i <
                 * listdatasheet.size(); i++) { tawSheetStat = (TawSheetStat)
                 * listdatasheet .elementAt(i); if (statDAO
                 * .getIfStat(tawSheetStat.getSheetId(),
                 * tawSheetStat.getDutyId(), "taw_fault_stat_duty") == false) {
                 * statDAO.insert(tawSheetStat, "taw_fault_stat_duty",
                 * startTime, endTime, url); } } } // 任务工单考核 listtasksheet =
                 * tawSheetStatDAO.getAutoSheetSelect("tasksheet",
                 * Integer.parseInt(saveSessionBeanForm.getDeptid()),
                 * Integer.parseInt(saveSessionBeanForm.getRoomId()),
                 * Integer.parseInt(saveSessionBeanForm.getWorkSerial()),
                 * startTime, endTime, dutyDate, dutyMaster); if
                 * (listtasksheet.size() != 0) { TawSheetStat tawSheetStat = new
                 * TawSheetStat(); TawSheetStatDAO statDAO = new
                 * TawSheetStatDAO(ds); for (int i = 0; i <
                 * listtasksheet.size(); i++) { tawSheetStat = (TawSheetStat)
                 * listtasksheet .elementAt(i); if (statDAO
                 * .getIfStat(tawSheetStat.getSheetId(),
                 * tawSheetStat.getDutyId(), "taw_fault_stat_duty") == false) {
                 * statDAO.insert(tawSheetStat, "taw_fault_stat_duty",
                 * startTime, endTime, url); } } } // 申请工单 listrequsheet =
                 * tawSheetStatDAO.getAutoSheetSelect("requsheet",
                 * Integer.parseInt(saveSessionBeanForm.getDeptid()),
                 * Integer.parseInt(saveSessionBeanForm.getRoomId()),
                 * Integer.parseInt(saveSessionBeanForm.getWorkSerial()),
                 * startTime, endTime, dutyDate, dutyMaster); if
                 * (listrequsheet.size() != 0) { TawSheetStat tawSheetStat = new
                 * TawSheetStat(); TawSheetStatDAO statDAO = new
                 * TawSheetStatDAO(ds); for (int i = 0; i <
                 * listrequsheet.size(); i++) { tawSheetStat = (TawSheetStat)
                 * listrequsheet .elementAt(i); if (statDAO
                 * .getIfStat(tawSheetStat.getSheetId(),
                 * tawSheetStat.getDutyId(), "taw_fault_stat_duty") == false) {
                 * statDAO.insert(tawSheetStat, "taw_fault_stat_duty",
                 * startTime, endTime, url); } } }
                 */
                // end by 何毅
                // 清除Session
                saveSessionBeanForm.setIsDutyMaster(false);
                saveSessionBeanForm.setWorkSerial("0");
                saveSessionBeanForm.setRoomId("0");
                // logbo = new logBO(ds);
                // boolean bool =
                // logbo.insertLogToDB(saveSessionBeanForm.getWrf_UserID(),
                // "交接班", StaticVariable.OPER,
                // request.getRemoteAddr());

            } else {
                return mapping.findForward("failure");
            }
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            strNextWorkSerial = null;
            userId = null;
            tawRmRecord = null;
            dateFormat = null;
            date = null;
            tawRmRecordSubDAO = null;
            tawRmRecordSub = null;
            tawRmSysteminfoDAO = null;
            tawRmSysteminfo = null;
            cal_start = null;
            time_start = null;
            // logbo = null;
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 退出值班
     */
    private ActionForward performLogoff(ActionMapping mapping,
                                        ActionForm actionForm, HttpServletRequest request,
                                        HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        String userId = null;
        TawRmRecordSubDAO tawRmRecordSubDAO = null;
        TawRmRecordSub tawRmRecordSub = null;
        TawRmSysteminfoDAO tawRmSysteminfoDAO = null;
        TawRmSysteminfo tawRmSysteminfo = null;
        GregorianCalendar cal_start = null;
        String time_start = null;
        TawRmRecordDAO tawRmRecordDAO = null;
        // logBO logbo = null;

        // 判断超时
        // saveSessionBeanForm =
        // (SaveSessionBeanForm) request.getSession().getAttribute(
        // "SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        // 判断是否值班
        if (saveSessionBeanForm.getWorkSerial().equals("0")) {
            return mapping.findForward("notonduty");
        }

        try {

            int workserial = Integer.parseInt(saveSessionBeanForm
                    .getWorkSerial());
            int roomId = Integer.parseInt(saveSessionBeanForm.getRoomId());
            userId = saveSessionBeanForm.getUserid();
            // 改自己的子记录
            // 取得自己的值班纪录
            tawRmRecordSubDAO = new TawRmRecordSubDAO(ds);
            tawRmRecordSub = tawRmRecordSubDAO.retrieve_sub(workserial, userId);
            // 取得最大误差时间
            tawRmSysteminfoDAO = new TawRmSysteminfoDAO(ds);
            tawRmSysteminfo = tawRmSysteminfoDAO.retrieve(roomId + "");
            int maxerrortime = 0;
            if (tawRmSysteminfo != null) {
                maxerrortime = tawRmSysteminfo.getMaxerrortime();
            }
            // 当前时间+最大误差时间，是否属于下班次
            cal_start = new GregorianCalendar();
            cal_start.add(cal_start.MINUTE, maxerrortime);
            time_start = StaticMethod.Cal2String(cal_start);
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            int workserial2 = tawRmRecordDAO.receiverWorkSerial(roomId + "",
                    time_start, userId);
            int intWorkflag = tawRmRecordSub.getWorkflag();
            if (workserial2 == workserial) {
                // 早退
                if (intWorkflag < 3) {
                    intWorkflag += 2;
                }
            } else {
                if (intWorkflag == 0) {
                    intWorkflag = 4;
                }
            }
            java.util.Date currentDate = new java.util.Date();
            SimpleDateFormat dateFormat = null;
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = dateFormat.format(currentDate);

            tawRmRecordSub.setWorkflag(intWorkflag);
            tawRmRecordSub.setEndtime(date);
            tawRmRecordSubDAO.update(tawRmRecordSub);

            // 清除Session
            saveSessionBeanForm.setIsDutyMaster(false);
            saveSessionBeanForm.setWorkSerial("0");
            saveSessionBeanForm.setRoomId("0");
            // logbo = new logBO(ds);
            // boolean bool =
            // logbo.insertLogToDB(saveSessionBeanForm.getWrf_UserID(),
            // "退出值班", StaticVariable.OPER,
            // request.getRemoteAddr());
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            userId = null;
            tawRmRecordSubDAO = null;
            tawRmRecordSub = null;
            tawRmSysteminfoDAO = null;
            tawRmSysteminfo = null;
            cal_start = null;
            time_start = null;
            tawRmRecordDAO = null;
            // logbo = null;
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 值班记录编辑
     */
    private ActionForward performEdit(ActionMapping mapping,
                                      ActionForm actionForm, HttpServletRequest request,
                                      HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        TawRmRecord tawRmRecord = null;
        TawUserRoomDAO tawUserRoomDAO = null;
        String roomName = null;
        TawRmDutyfileDAO tawRmDutyfileDAO = null;
        Vector vecDutyFile = null;
        List tawRmRecord_dutyman = null;
        // 判断超时
        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        TawRmRecordForm form = (TawRmRecordForm) actionForm;
        try {
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            int id = Integer.parseInt(request.getParameter("id"));
            tawRmRecord = tawRmRecordDAO.retrieve(id);
            if (tawRmRecord == null) {
                ActionErrors aes = new ActionErrors();
                aes.add(aes.GLOBAL_ERROR, new ActionError(
                        "error.object.notfound", "TawRmRecord"));
                saveErrors(request, aes);
            } else {
                tawRmRecord.setStarttimeDefined("");
                tawRmRecord.setStarttimesub("");
                tawRmRecord.setStatement("");
                tawRmRecord.setEndtimeDefined("");
                tawRmRecord.setDutymansub("");
                tawRmRecord.setDutymaster("");
                tawRmRecord.setDutyrecord(tawRmRecord.getDutyrecord().trim());
                tawRmRecord.setNotes(tawRmRecord.getNotes().trim());
                org.apache.commons.beanutils.BeanUtils.populate(form,
                        org.apache.commons.beanutils.BeanUtils
                                .describe(tawRmRecord));
                // 机房名称
                int roomid = tawRmRecord.getRoomId();
                tawUserRoomDAO = new TawUserRoomDAO(ds);
                roomName = tawUserRoomDAO.getRoomName(roomid);
                request.setAttribute("roomName", roomName);
                // 完成状态
                int intFlag = tawRmRecord.getFlag();
                request.setAttribute("intFlag", String.valueOf(intFlag));
                // 附件
                tawRmDutyfileDAO = new TawRmDutyfileDAO(ds);
                vecDutyFile = tawRmDutyfileDAO.getDutyFile(id);
                request.setAttribute("vecDutyFile", vecDutyFile);
                // 值班人
                tawRmRecord_dutyman = tawRmRecordDAO.listDutyman(id);
                request
                        .setAttribute("TAWRMRECORD_DUTYMAN",
                                tawRmRecord_dutyman);
            }
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            tawRmRecord = null;
            tawUserRoomDAO = null;
            roomName = null;
            tawRmDutyfileDAO = null;
            vecDutyFile = null;
            tawRmRecord_dutyman = null;
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 值班记录删除查询
     */
    private ActionForward performQuerydelete(ActionMapping mapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        Vector SelectRoom = null;
        Vector SelectRoomName = null;
        TawRmAssignworkBO tawRmAssignworkBO = null;
        // edit by wangheqi
        TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();

        // TawValidatePrivBO tawValidatePrivBO = null;
        // edit by wangheqi
        TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
        TawSystemCptroom tawApparatusroom = null;

        // TawApparatusroomDAO tawApparatusroomDAO = null;
        // TawApparatusroom tawApparatusroom = null;
        String strSelectRoomName = null;

        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {

            String roomId = request.getParameter("typeId");
            request.setAttribute("roomId", roomId);
            /*
             * SelectRoom = new Vector(); SelectRoomName = new Vector();
             *
             * if (saveSessionBeanForm.getUserid().equals(StaticVariable.ADMIN)) {
             * tawRmAssignworkBO = new TawRmAssignworkBO(ds); SelectRoom =
             * tawRmAssignworkBO.getRoomSelect(); } else { // tawValidatePrivBO =
             * new TawValidatePrivBO(ds); // SelectRoom = //
             * tawValidatePrivBO.validatePriv(saveSessionBeanForm.getWrf_UserID(), //
             * 2004, 2); SelectRoom = StaticMethod .list2vector(privBO
             * .getPermissions( saveSessionBeanForm.getUserid(),
             * com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,
             * com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM)); }
             * if (SelectRoom.size() > 0) { // tawApparatusroomDAO = new
             * TawApparatusroomDAO(ds); tawApparatusroom = null;
             * strSelectRoomName = ""; Vector removeEle = new Vector(); for (int
             * i = 0; i < SelectRoom.size(); i++) { tawApparatusroom = cptroomBO
             * .getTawSystemCptroomById(new Integer(String
             * .valueOf(SelectRoom.elementAt(i))), 0); if (tawApparatusroom !=
             * null) { strSelectRoomName = StaticMethod
             * .null2String(tawApparatusroom.getRoomname());
             *
             * SelectRoomName.add(strSelectRoomName); } else {
             * removeEle.add(SelectRoom.elementAt(i)); } }
             * SelectRoom.removeAll(removeEle); } else { return
             * mapping.findForward("nopriv"); }
             * request.setAttribute("SelectRoom", SelectRoom);
             * request.setAttribute("SelectRoomName", SelectRoomName);
             */
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            SelectRoom = null;
            SelectRoomName = null;
            tawRmAssignworkBO = null;
            privBO = null;
            // tawValidatePrivBO = null;
            // tawApparatusroomDAO = null;
            cptroomBO = null;
            tawApparatusroom = null;
            strSelectRoomName = null;

        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 值班记录删除查询列表
     */
    private ActionForward performSearchdelete(ActionMapping mapping,
                                              ActionForm actionForm, HttpServletRequest request,
                                              HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        String pageOffset = null;
        String starttime = null;
        String endtime = null;
        String paramCondition = null;
        String strCondition = null;
        List tawRmRecords = null;
        String objKey = null;

        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            int offset;
            int length = PAGE_LENGTH;
            pageOffset = request.getParameter("pager.offset");
            if (pageOffset == null || pageOffset.equals("")) {
                offset = 0;
            } else {
                offset = Integer.parseInt(pageOffset);
            }

            starttime = request.getParameter("starttime");
            endtime = request.getParameter("endtime");
            paramCondition = "starttime=" + starttime + "&endtime=" + endtime
                    + "&roomId="
                    + String.valueOf(request.getParameter("roomId"));
            starttime = starttime + " 00:00:00";
            endtime = endtime + " 23:59:59";
            strCondition = " where starttime >= '" + starttime
                    + "' and starttime <= '" + endtime + "' ";
            int roomId = Integer.parseInt(request.getParameter("roomId"));
            if (roomId > 0) {
                strCondition = strCondition + " and room_id = " + roomId + " ";
            } else {
                strCondition = strCondition + " ";
            }

            tawRmRecords = tawRmRecordDAO.search(offset, length, strCondition);

            String[] objKeys = {"TawRmRecord", "search"};
            objKey = CacheManager.createKey(objKeys);

            Integer size = (Integer) SizeCacheManager.getCache(objKey);
            if (size == null) {
                size = new Integer(tawRmRecordDAO.getSize("taw_rm_record",
                        strCondition));
                SizeCacheManager.putCache(size, objKey, 0);
            }
            int intSize = tawRmRecordDAO.getSize("taw_rm_record", strCondition);
            String url = request.getContextPath() + "/duty" + mapping.getPath()
                    + ".do";

            String pagerHeader = Pager.generate(offset, intSize, length, url,
                    paramCondition);
            request.setAttribute("pagerHeader", pagerHeader);

            request.setAttribute("TAWRMRECORDS", tawRmRecords);
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            pageOffset = null;
            starttime = null;
            endtime = null;
            paramCondition = null;
            strCondition = null;
            tawRmRecords = null;
            objKey = null;
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 值班记录删除查看
     */
    private ActionForward performViewdelete(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        TawRmRecord tawRmRecord = null;
        Vector vecDefRecord = null;
        Vector vecSubRecords = null;

        // 判断超时
        // saveSessionBeanForm =
        // (SaveSessionBeanForm) request.getSession().getAttribute(
        // "SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {
            // 定义数据库连接和DAO
            tawRmRecordDAO = new TawRmRecordDAO(ds);

            // 得到值班主记录对应的FORM
            TawRmRecordForm tawRmRecordForm = (TawRmRecordForm) actionForm;
            if (tawRmRecordForm.getId() == 0) {
                int intWorkserial = Integer
                        .parseInt(request.getParameter("id"));
                tawRmRecordForm.setId(intWorkserial);
            }
            int workserial = tawRmRecordForm.getId();
            tawRmRecord = tawRmRecordDAO.retrieve(workserial);
            org.apache.commons.beanutils.BeanUtils.populate(tawRmRecordForm,
                    org.apache.commons.beanutils.BeanUtils
                            .describe(tawRmRecord));
            vecDefRecord = new Vector();

            /*
             * //TawRmDefinemainDAO tawRmDefinemainDAO = new
             * TawRmDefinemainDAO(ds);
             * //vecDefRecord=tawRmDefinemainDAO.getVecDefRecord(workserial);
             * TawRmDefineTableDAO tawRmDefineTableDAO = new
             * TawRmDefineTableDAO(ds); vecDefRecord =
             * tawRmDefineTableDAO.retrieveVec(tawRmRecord.getRoomId());
             * request.setAttribute("vecDefRecord", vecDefRecord);
             */
            Vector vecPerRecords = new Vector();
            TawRmRecordPerDAO tawRmRecordPerDAO = new TawRmRecordPerDAO(ds);
            vecPerRecords = tawRmRecordPerDAO.getVecPerRecord(workserial);
            request.setAttribute("vecPerRecords", vecPerRecords);

            // 附加表的记录显示：
            // 得到本班次已经填写的附加表的sheet_id和sheet_name
            Vector bocoVector = new Vector();
            TawDutySheetDAO tawDutySheetDAO = new TawDutySheetDAO(ds);
            bocoVector = tawDutySheetDAO.getSheetListByWorkSerial(workserial);

            // 得到每一个附加表中的记录Id
            int sheetId = 0;
            String strBocoId = "";
            for (int i = 0; i < bocoVector.size(); i++) {
                TawDutySheet tawDutySheet = (TawDutySheet) bocoVector
                        .elementAt(i);
                sheetId = tawDutySheet.getSheetId();
                strBocoId = tawDutySheetDAO.getBocoIdsBySheetId(sheetId,
                        workserial);
                tawDutySheet.setStrBocoId(strBocoId);
            }
            tawRmRecordForm.setRealCount(bocoVector.size());
            request.setAttribute("FILLEDSHEETLIST", bocoVector);

            // 周期
            Vector cycleVectot = new Vector();
            TawRmCycleTableSubDAO tawRmCycleTableSub = new TawRmCycleTableSubDAO(
                    ds);
            cycleVectot = tawRmCycleTableSub
                    .getSheetListByWorkSerial(workserial);
            tawRmRecordForm.setCycleCount(cycleVectot.size());
            request.setAttribute("CYCLETABLELIST", cycleVectot);

        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            tawRmRecord = null;
            vecDefRecord = null;
            vecSubRecords = null;

        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 值班记录删除结果
     */
    private ActionForward performDodelete(ActionMapping mapping,
                                          ActionForm actionForm, HttpServletRequest request,
                                          HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordBO tawRmRecordBO = null;
        // 判断超时
        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        TawRmRecordForm form = (TawRmRecordForm) actionForm;
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            tawRmRecordBO = new TawRmRecordBO(ds);
            tawRmRecordBO.deleteRmRecord(id);
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordBO = null;
        }
        return mapping.findForward("successok");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 值班统计条件
     */
    // add by gong
    private ActionForward performStatisticQuery(ActionMapping mapping,
                                                ActionForm actionForm, HttpServletRequest request,
                                                HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */

        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {
            // 权限
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;

        }
        return mapping.findForward("success");
    }

    private ActionForward performGetRoom(ActionMapping mapping,
                                         ActionForm actionForm, HttpServletRequest request,
                                         HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        Vector SelectRoom = null;

        TawRmAssignworkBO tawRmAssignworkBO = null;
        TawSystemPrivRegion tawSystemPrivRegion = null;
        // edit by wangheqi
        TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();
        JSONArray json = new JSONArray();
        // TawValidatePrivBO tawValidatePrivBO = null;
        // edit by wangheqi
        TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
        TawSystemCptroom tawApparatusroom = null;

        // TawApparatusroomDAO tawApparatusroomDAO = null;
        // TawApparatusroom tawApparatusroom = null;
        String strSelectRoomName = null;

        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {
            SelectRoom = new Vector();

            if (saveSessionBeanForm.getUserid().equals(StaticVariable.ADMIN)) {
                tawRmAssignworkBO = new TawRmAssignworkBO(ds);
                SelectRoom = tawRmAssignworkBO.getRoomSelect();
                tawApparatusroom = null;
                strSelectRoomName = "";
                Vector removeEle = new Vector();
                if (SelectRoom.size() > 0) {
                    for (int i = 0; i < SelectRoom.size(); i++) {
                        JSONObject jitem = new JSONObject();
                        // tawSystemPrivRegion =
                        // (TawSystemPrivRegion)SelectRoom.elementAt(i);
                        tawApparatusroom = cptroomBO.getTawSystemCptroomById(
                                new Integer((String) SelectRoom.elementAt(i)),
                                0);
                        if (tawApparatusroom != null) {
                            strSelectRoomName = StaticMethod
                                    .null2String(tawApparatusroom.getRoomname());

                            jitem.put("id", (String) SelectRoom.elementAt(i));
                            jitem.put("text", strSelectRoomName);
                            jitem.put(UIConstants.JSON_NODETYPE, "cptroom");
                            jitem.put("allowChild", true);
                            jitem.put("allowDelete", true);
                            jitem.put("leaf", tawApparatusroom.getLeaf());
                            json.put(jitem);
                        } else {
                            removeEle.add(SelectRoom.elementAt(i));
                        }
                    }
                    SelectRoom.removeAll(removeEle);
                } else {
                    return mapping.findForward("nopriv");
                }
            } else {
                // tawValidatePrivBO = new TawValidatePrivBO(ds);
                // SelectRoom =
                // tawValidatePrivBO.validatePriv(saveSessionBeanForm.getWrf_UserID(),
                // 2051, 2);
                SelectRoom = StaticMethod
                        .list2vector(privBO
                                .getPermissions(
                                        saveSessionBeanForm.getUserid(),
                                        com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,
                                        com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM));

                if (SelectRoom.size() > 0) {
                    // tawApparatusroomDAO = new TawApparatusroomDAO(ds);
                    tawApparatusroom = null;
                    strSelectRoomName = "";
                    Vector removeEle = new Vector();
                    for (int i = 0; i < SelectRoom.size(); i++) {
                        JSONObject jitem = new JSONObject();
                        tawSystemPrivRegion = (TawSystemPrivRegion) SelectRoom
                                .elementAt(i);
                        tawApparatusroom = cptroomBO.getTawSystemCptroomById(
                                new Integer(tawSystemPrivRegion.getRegionid()),
                                0);
                        if (tawApparatusroom != null) {
                            strSelectRoomName = StaticMethod
                                    .null2String(tawApparatusroom.getRoomname());

                            jitem.put("id", tawSystemPrivRegion.getRegionid());
                            jitem.put("text", strSelectRoomName);
                            jitem.put(UIConstants.JSON_NODETYPE, "cptroom");
                            jitem.put("allowChild", true);
                            jitem.put("allowDelete", true);
                            jitem.put("leaf", tawApparatusroom.getLeaf());
                            json.put(jitem);
                        } else {
                            removeEle.add(SelectRoom.elementAt(i));
                        }
                    }
                    SelectRoom.removeAll(removeEle);
                } else {
                    return mapping.findForward("nopriv");
                }
            }
            response.setContentType("text/xml;charset=UTF-8");
            response.getWriter().print(json.toString());

        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;

            tawRmAssignworkBO = null;
            privBO = null;
            // tawValidatePrivBO = null;
            cptroomBO = null;
            // tawApparatusroomDAO = null;
            tawApparatusroom = null;
            strSelectRoomName = null;
        }
        return null;
    }

    private ActionForward performStatisticDate(ActionMapping mapping,
                                               ActionForm actionForm, HttpServletRequest request,
                                               HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        Vector SelectRoom = null;
        Vector SelectRoomId = null;
        Vector SelectRoomName = null;
        TawRmAssignworkBO tawRmAssignworkBO = null;
        TawSystemPrivRegion tawSystemPrivRegion = null;
        // edit by wangheqi
        TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();

        // TawValidatePrivBO tawValidatePrivBO = null;
        // edit by wangheqi
        TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
        TawSystemCptroom tawApparatusroom = null;

        // TawApparatusroomDAO tawApparatusroomDAO = null;
        // TawApparatusroom tawApparatusroom = null;
        String strSelectRoomName = null;

        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {
            String roomId = request.getParameter("typeId");
            request.setAttribute("typeId", roomId);
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            SelectRoom = null;
            SelectRoomName = null;
            tawRmAssignworkBO = null;
            privBO = null;
            // tawValidatePrivBO = null;
            cptroomBO = null;
            // tawApparatusroomDAO = null;
            tawApparatusroom = null;
            strSelectRoomName = null;
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 值班统计结果
     */
    private ActionForward performStatisticShow(ActionMapping mapping,
                                               ActionForm actionForm, HttpServletRequest request,
                                               HttpServletResponse response) {

        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        String starttime = null;
        String endtime = null;
        Vector StatisticsVector = null;

        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {

            tawRmRecordDAO = new TawRmRecordDAO(ds);

            starttime = request.getParameter("starttime");
            endtime = request.getParameter("endtime");
            starttime = StaticMethod.getAddZero(starttime) + " 00:00:00";
            endtime = StaticMethod.getAddZero(endtime) + " 23:59:59";
            int roomId = Integer.parseInt(request.getParameter("roomId"));
            StatisticsVector = tawRmRecordDAO.getStatisticsVector(roomId,
                    starttime, endtime);
            request.setAttribute("STATISTICSVECTOR", StatisticsVector);

        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            starttime = null;
            endtime = null;
            StatisticsVector = null;
        }

        return mapping.findForward("success");

    }

    private ActionForward performRecordMain(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {
        ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String startTime = saveSessionBeanForm.getStartDuty();
        String endTime = saveSessionBeanForm.getEndDuty();
        List eventList = new ArrayList();
        // mgr.getTawRmDutyEventByTime(startTime, endTime);
        request.setAttribute("eventList", eventList);
        TawRmRecordDAO tawRmRecordDAO = null;
        TawUserRoomDAO tawUserRoomDAO = null;
        TawRmRecordSubDAO tawRmRecordSubDAO = null;
        String pageOffset = null;
        String userId = null;
        String roomName = null;
        String strDutyDate = null;
        TawRmHangoverDAO tawRmHangoverDAO = null;
        TawRmHangover tawRmHangover = null;
        String strHangQuestion = null;
        TawRmRecord tawRmRecord = null;
        String user_id = null;
        ArrayList list_temperature = null;
        ArrayList list_wet = null;
        TawRmRecordSub tawRmRecordSub = null;
        String Starttime = null;
        String Starttime_Defined = null;
        String Endtime_Defined = null;
        String Statement = null;
        String Workflag = null;
        String Status = null;

        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        // 判断是否值班
        if (saveSessionBeanForm.getWorkSerial().equals("0")) {

        }

        try {

            TawRmRecordForm tawRmRecordForm = (TawRmRecordForm) actionForm;
            if (tawRmRecordForm.getId() == 0) {
                int intWorkserial = Integer.parseInt(request
                        .getParameter("WORKSERIAL"));
                tawRmRecordForm.setId(intWorkserial);
            }
            int workserial = tawRmRecordForm.getId();

            // 定义数据库连接和DAO
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            tawUserRoomDAO = new TawUserRoomDAO(ds);
            tawRmRecordSubDAO = new TawRmRecordSubDAO(ds);
            // 获得相关参数
            int offset;
            int length = PAGE_LENGTH;
            pageOffset = request.getParameter("pager.offset");
            if (pageOffset == null || pageOffset.equals("")) {
                offset = 0;
            } else {
                offset = Integer.parseInt(pageOffset);
            }
            // 获得用户ID，值班号，机房ID及名称
            userId = StaticMethod.null2String(saveSessionBeanForm.getUserid());
            int roomid = Integer.parseInt(saveSessionBeanForm.getRoomId());

            roomName = saveSessionBeanForm.getRoomname();
            request.setAttribute("roomName", roomName); // 机房名称
            // 获得当前时间
            java.util.Date date = new java.util.Date();
            java.text.SimpleDateFormat dtFormat = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            strDutyDate = dtFormat.format(date);
            // 判断值班住记录是否存在

            boolean CheckMainRecordExist = tawRmRecordDAO
                    .receiverCheckMainRecordExist(workserial);
            if (CheckMainRecordExist == false) {
                // 如果不存在创建值班主记录
                tawRmRecordDAO.getNewManiRecord(workserial, roomid + "");
            }
            // 判断值班子记录是否存在
            boolean CheckSubRecordExist = tawRmRecordDAO
                    .receiverCheckSubRecordExist(workserial, userId);
            // 如果不存在生成值班子记录
            if (CheckSubRecordExist == false) {
                tawRmRecordDAO.getSaveSubRecord(workserial, userId);
            }

            // 遗留问题
            tawRmHangoverDAO = new TawRmHangoverDAO(ds);
            tawRmHangover = tawRmHangoverDAO.retrieve(workserial, 0);
            strHangQuestion = "";
            if (tawRmHangover != null) {
                strHangQuestion = tawRmHangover.getHangQuestion();
            }
            request.setAttribute("strHangQuestion", strHangQuestion);
            // 得到值班主记录对应的FORM
            tawRmRecord = tawRmRecordDAO.retrieve(workserial);

            // tawRmRecord.setStarttimeDefined("");
            tawRmRecord.setStarttimesub("");
            tawRmRecord.setStatement("");
            tawRmRecord.setEndtimeDefined("");
            tawRmRecord.setDutymansub("");
            tawRmRecord.setDutymaster("");

            org.apache.commons.beanutils.BeanUtils.populate(tawRmRecordForm,
                    org.apache.commons.beanutils.BeanUtils
                            .describe(tawRmRecord));
            user_id = saveSessionBeanForm.getUserid();
            list_temperature = new ArrayList();
            for (int i = -30; i < 51; i++) {
                list_temperature.add(new org.apache.struts.util.LabelValueBean(
                        String.valueOf(i), String.valueOf(i)));
            }
            list_wet = new ArrayList();
            for (int i = 1; i < 101; i++) {
                list_wet.add(new org.apache.struts.util.LabelValueBean(String
                        .valueOf(i), String.valueOf(i)));
            }
            request.setAttribute("LISTTEMPERATURE", list_temperature);
            request.setAttribute("LISTWET", list_wet);
            // 得到值班子记录字段

            int type = 1;
            String trans = "", swtch = "", data = "", system = "", other = "";
            String dutySummary = "", importRecord = "", importFault = "", netCut = "", netKPI = "", importantsocietydisaster = "", needcorrespond = "", handoverproceeding = "";
            List listjl = new ArrayList();
            listjl = (List) tawRmRecordSubDAO
                    .retrieve_sub2(workserial, user_id);
            if (listjl.size() == 12) {
                trans = listjl.get(0).toString();
                swtch = listjl.get(1).toString();
                data = listjl.get(2).toString();
                system = listjl.get(3).toString();
                other = listjl.get(4).toString();
                dutySummary = listjl.get(5).toString();
                importRecord = listjl.get(6).toString();
                importFault = listjl.get(7).toString();
                netCut = listjl.get(8).toString();
                netKPI = listjl.get(9).toString();
                // importantsocietydisaster = listjl.get(10).toString();
                needcorrespond = listjl.get(10).toString();
                handoverproceeding = listjl.get(11).toString();
            }
            request.setAttribute("trans", trans);
            request.setAttribute("swtch", swtch);
            request.setAttribute("data", data);
            request.setAttribute("system", system);
            request.setAttribute("other", other);
            request.setAttribute("dutySummary", dutySummary);
            request.setAttribute("importRecord", importRecord);
            request.setAttribute("importFault", importFault);
            request.setAttribute("netCut", netCut);
            request.setAttribute("netKPI", netKPI);
            request.setAttribute("importantsocietydisaster",
                    importantsocietydisaster);
            request.setAttribute("needcorrespond", needcorrespond);
            request.setAttribute("handoverproceeding", handoverproceeding);
            type = tawRmRecordSubDAO.retrieve_sub0(workserial, user_id);
            String typemode = String.valueOf(type);
            request.setAttribute("typemode", typemode);

            // 得到值班子记录字段
            tawRmRecordSub = tawRmRecordSubDAO
                    .retrieve_sub(workserial, user_id);
            Starttime = tawRmRecordSub.getStarttime();
            request.setAttribute("STARTTIME", Starttime);
            Starttime_Defined = tawRmRecordSub.getStarttimeDefined();
            request.setAttribute("STARTTIMEDEFINED", Starttime_Defined);
            Endtime_Defined = tawRmRecordSub.getEndtimeDefined();
            request.setAttribute("ENDTIMEDEFINED", Endtime_Defined);
            Statement = StaticMethod.null2String(tawRmRecordSub.getStatement())
                    .trim();
            request.setAttribute("STATEMENT", Statement);
            // 为了在页面中显示
            // Workflag = String.valueOf(tawRmRecordSub.getWorkflag());
            // request.setAttribute("WORKFALG", Workflag);
            int WorkFlagInt = tawRmRecordSub.getWorkflag();
            String WorkFlagStr = "";
            switch (WorkFlagInt) {
                case 0:
                    WorkFlagStr = "未刷卡";
                    break;
                case 1:
                    WorkFlagStr = "迟到";
                    break;
                case 2:
                    WorkFlagStr = "早退";
                    break;
                case 3:
                    WorkFlagStr = "迟到、早退";
                    break;
                default:
                    WorkFlagStr = "正常";
                    break;
            }
            request.setAttribute("WORKFALG", WorkFlagStr);

            int StatusInt = Integer.parseInt(String.valueOf(tawRmRecordSub
                    .getStatus()));
            String StatusStr = "";
            switch (StatusInt) {
                case 0:
                    StatusStr = "可修改";
                    break;
                case 1:
                    StatusStr = "不可改";
                    break;
                default:
                    StatusStr = "可修改";
                    break;
            }
            request.setAttribute("STATUS", StatusStr);
            request.setAttribute("StatusInt", StatusInt + "");
            // 插入值班日检内容
            request.setAttribute("dutychecksub", tawRmRecordSub.getDutycheck());
            request.setAttribute("dutycheck", tawRmRecord.getDutycheck());
            String strId = String.valueOf(tawRmRecord.getId());
            for (int i = 1; i <= StaticDutycheck.dutycheckcount; i++) {
                String checkId = StaticDutycheck.getDutycheckId(i);
                request.setAttribute(checkId, tawRmRecordDAO.getCheckman(strId,
                        checkId));
            }

            // 个人值班记录连接
            List tawRmRecord_dutyman = tawRmRecordDAO.listDutyman(workserial); // 值班人
            request.setAttribute("TAWRMRECORD_DUTYMAN", tawRmRecord_dutyman);

            request.setAttribute("NO", "无");

        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            tawUserRoomDAO = null;
            tawRmRecordSubDAO = null;
            pageOffset = null;
            userId = null;
            roomName = null;
            strDutyDate = null;
            tawRmHangoverDAO = null;
            tawRmHangover = null;
            strHangQuestion = null;
            tawRmRecord = null;
            user_id = null;
            list_temperature = null;
            list_wet = null;
            tawRmRecordSub = null;
            Starttime = null;
            Starttime_Defined = null;
            Endtime_Defined = null;
            Statement = null;
            Workflag = null;
            Status = null;
        }
        return mapping.findForward("success");

    }

    /**
     * 云南监控纪要填写界面
     *
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    private ActionForward performYnRecordMain(ActionMapping mapping,
                                              ActionForm actionForm, HttpServletRequest request,
                                              HttpServletResponse response) {
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String startTime = saveSessionBeanForm.getStartDuty();
        String endTime = saveSessionBeanForm.getEndDuty();
        TawRmRecordDAO tawRmRecordDAO = null;
        TawUserRoomDAO tawUserRoomDAO = null;
        TawRmRecordSubDAO tawRmRecordSubDAO = null;
        String pageOffset = null;
        String userId = null;
        String roomName = null;
        String strDutyDate = null;
        TawRmHangoverDAO tawRmHangoverDAO = null;
        TawRmHangover tawRmHangover = null;
        String strHangQuestion = null;
        TawRmRecord tawRmRecord = null;
        String user_id = null;
        ArrayList list_temperature = null;
        ArrayList list_wet = null;
        TawRmRecordSub tawRmRecordSub = null;
        String Starttime = null;
        String Starttime_Defined = null;
        String Endtime_Defined = null;
        String Statement = null;
        String Workflag = null;
        String Status = null;

        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {

            TawRmRecordForm tawRmRecordForm = (TawRmRecordForm) actionForm;
            if (tawRmRecordForm.getId() == 0) {
                int intWorkserial = Integer.parseInt(request
                        .getParameter("WORKSERIAL"));
                tawRmRecordForm.setId(intWorkserial);
            }
            int workserial = tawRmRecordForm.getId();

            // 定义数据库连接和DAO
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            tawUserRoomDAO = new TawUserRoomDAO(ds);
            tawRmRecordSubDAO = new TawRmRecordSubDAO(ds);
            // 获得相关参数
            int offset;
            int length = PAGE_LENGTH;
            pageOffset = request.getParameter("pager.offset");
            if (pageOffset == null || pageOffset.equals("")) {
                offset = 0;
            } else {
                offset = Integer.parseInt(pageOffset);
            }
            // 获得用户ID，值班号，机房ID及名称
            userId = StaticMethod.null2String(saveSessionBeanForm.getUserid());
            int roomid = Integer.parseInt(saveSessionBeanForm.getRoomId());

            roomName = saveSessionBeanForm.getRoomname();
            request.setAttribute("roomName", roomName); // 机房名称
            // 获得当前时间
            java.util.Date date = new java.util.Date();
            java.text.SimpleDateFormat dtFormat = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            strDutyDate = dtFormat.format(date);
            // 判断值班住记录是否存在

            boolean CheckMainRecordExist = tawRmRecordDAO
                    .receiverCheckMainRecordExist(workserial);
            if (CheckMainRecordExist == false) {
                // 如果不存在创建值班主记录
                tawRmRecordDAO.getNewManiRecord(workserial, roomid + "");
            }
            // 判断值班子记录是否存在
            boolean CheckSubRecordExist = tawRmRecordDAO
                    .receiverCheckSubRecordExist(workserial, userId);
            // 如果不存在生成值班子记录
            if (CheckSubRecordExist == false) {
                tawRmRecordDAO.getSaveSubRecord(workserial, userId);
            }

            // 遗留问题
			/* 云南李江红注释----BEGIN------4
			tawRmHangoverDAO = new TawRmHangoverDAO(ds);
			tawRmHangover = tawRmHangoverDAO.retrieve(workserial, 0);
			strHangQuestion = "";
			if (tawRmHangover != null) {
				strHangQuestion = tawRmHangover.getHangQuestion();
			}
			request.setAttribute("strHangQuestion", strHangQuestion);
			 云南李江红注释----BEGIN------4*/
            // 得到值班主记录对应的FORM
            tawRmRecord = tawRmRecordDAO.retrieve(workserial);

            // tawRmRecord.setStarttimeDefined("");
            tawRmRecord.setStarttimesub("");
            tawRmRecord.setStatement("");
            tawRmRecord.setEndtimeDefined("");
            tawRmRecord.setDutymansub("");
            tawRmRecord.setDutymaster("");

            org.apache.commons.beanutils.BeanUtils.populate(tawRmRecordForm,
                    org.apache.commons.beanutils.BeanUtils
                            .describe(tawRmRecord));
            user_id = saveSessionBeanForm.getUserid();
			/* 云南李江红注释----BEGIN------3
			list_temperature = new ArrayList();
			for (int i = -30; i < 51; i++) {
				list_temperature.add(new org.apache.struts.util.LabelValueBean(
						String.valueOf(i), String.valueOf(i)));
			}
			list_wet = new ArrayList();
			for (int i = 1; i < 101; i++) {
				list_wet.add(new org.apache.struts.util.LabelValueBean(String
						.valueOf(i), String.valueOf(i)));
			}
			request.setAttribute("LISTTEMPERATURE", list_temperature);
			request.setAttribute("LISTWET", list_wet);
			云南李江红注释----END------3*/
            // 得到值班子记录字段

            int type = 1;
			/* 云南李江红注释----BEGIN------1
			String trans = "", swtch = "", data = "", system = "", other = "";
			String dutySummary = "", importRecord = "", importFault = "", netCut = "", netKPI = "", importantsocietydisaster = "", needcorrespond = "", handoverproceeding = "";
			List listjl = new ArrayList();
			listjl = (List) tawRmRecordSubDAO
					.retrieve_sub2(workserial, user_id);
			if (listjl.size() == 12) {
				trans = listjl.get(0).toString();
				swtch = listjl.get(1).toString();
				data = listjl.get(2).toString();
				system = listjl.get(3).toString();
				other = listjl.get(4).toString();
				dutySummary = listjl.get(5).toString();
				importRecord = listjl.get(6).toString();
				importFault = listjl.get(7).toString();
				netCut = listjl.get(8).toString();
				netKPI = listjl.get(9).toString();
				// importantsocietydisaster = listjl.get(10).toString();
				needcorrespond = listjl.get(10).toString();
				handoverproceeding = listjl.get(11).toString();
			}
			request.setAttribute("trans", trans);
			request.setAttribute("swtch", swtch);
			request.setAttribute("data", data);
			request.setAttribute("system", system);
			request.setAttribute("other", other);
			request.setAttribute("dutySummary", dutySummary);
			request.setAttribute("importRecord", importRecord);
			request.setAttribute("importFault", importFault);
			request.setAttribute("netCut", netCut);
			request.setAttribute("netKPI", netKPI);
			request.setAttribute("importantsocietydisaster",
					importantsocietydisaster);
			request.setAttribute("needcorrespond", needcorrespond);
			request.setAttribute("handoverproceeding", handoverproceeding);
			云南李江红注释----END------1*/
            type = tawRmRecordSubDAO.retrieve_sub0(workserial, user_id);
            String typemode = String.valueOf(type);
            request.setAttribute("typemode", typemode);

            // 得到值班子记录字段
			/* 云南李江红注释----BEGIN------2
			tawRmRecordSub = tawRmRecordSubDAO
					.retrieve_sub(workserial, user_id);
			Starttime = tawRmRecordSub.getStarttime();
			request.setAttribute("STARTTIME", Starttime);
			Starttime_Defined = tawRmRecordSub.getStarttimeDefined();
			request.setAttribute("STARTTIMEDEFINED", Starttime_Defined);
			Endtime_Defined = tawRmRecordSub.getEndtimeDefined();
			request.setAttribute("ENDTIMEDEFINED", Endtime_Defined);
			Statement = StaticMethod.null2String(tawRmRecordSub.getStatement())
					.trim();
			request.setAttribute("STATEMENT", Statement);

			int WorkFlagInt = tawRmRecordSub.getWorkflag();
			String WorkFlagStr = "";
			switch (WorkFlagInt) {
			case 0:
				WorkFlagStr = "未刷卡";
				break;
			case 1:
				WorkFlagStr = "迟到";
				break;
			case 2:
				WorkFlagStr = "早退";
				break;
			case 3:
				WorkFlagStr = "迟到、早退";
				break;
			default:
				WorkFlagStr = "正常";
				break;
			}
			request.setAttribute("WORKFALG", WorkFlagStr);

			int StatusInt = Integer.parseInt(String.valueOf(tawRmRecordSub
					.getStatus()));
			String StatusStr = "";
			switch (StatusInt) {
			case 0:
				StatusStr = "可修改";
				break;
			case 1:
				StatusStr = "不可改";
				break;
			default:
				StatusStr = "可修改";
				break;
			}
			request.setAttribute("STATUS", StatusStr);
			request.setAttribute("StatusInt", StatusInt + "");
			// 插入值班日检内容
			request.setAttribute("dutychecksub", tawRmRecordSub.getDutycheck());
			request.setAttribute("dutycheck", tawRmRecord.getDutycheck());
			String strId = String.valueOf(tawRmRecord.getId());
			for (int i = 1; i <= StaticDutycheck.dutycheckcount; i++) {
				String checkId = StaticDutycheck.getDutycheckId(i);
				request.setAttribute(checkId, tawRmRecordDAO.getCheckman(strId,
						checkId));
			}

			// 个人值班记录连接
			List tawRmRecord_dutyman = tawRmRecordDAO.listDutyman(workserial); // 值班人
			request.setAttribute("TAWRMRECORD_DUTYMAN", tawRmRecord_dutyman);
			云南李江红注释----END------2*/
            request.setAttribute("NO", "无");

        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            tawUserRoomDAO = null;
            tawRmRecordSubDAO = null;
            pageOffset = null;
            userId = null;
            roomName = null;
            strDutyDate = null;
            tawRmHangoverDAO = null;
            tawRmHangover = null;
            strHangQuestion = null;
            tawRmRecord = null;
            user_id = null;
            list_temperature = null;
            list_wet = null;
            tawRmRecordSub = null;
            Starttime = null;
            Starttime_Defined = null;
            Endtime_Defined = null;
            Statement = null;
            Workflag = null;
            Status = null;
        }
        return mapping.findForward("success");

    }

    private ActionForward performAdd(ActionMapping mapping,
                                     ActionForm actionForm, HttpServletRequest request,
                                     HttpServletResponse response) {
        TawRmRecordForm form = (TawRmRecordForm) actionForm;
        form.setStrutsAction(TawRmRecordForm.ADD);
        return mapping.findForward("success");
    }

    private ActionForward performRemove(ActionMapping mapping,
                                        ActionForm actionForm, HttpServletRequest request,
                                        HttpServletResponse response) {
        return performView(mapping, actionForm, request, response);
    }

    private ActionForward performTrash(ActionMapping mapping,
                                       ActionForm actionForm, HttpServletRequest request,
                                       HttpServletResponse response) {
        TawRmRecordForm form = (TawRmRecordForm) actionForm;
        try {

            TawRmRecordDAO tawRmRecordDAO = new TawRmRecordDAO(ds);

            int id = Integer.parseInt(request.getParameter("id"));
            tawRmRecordDAO.delete(id);
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }

    private void sqlDuplicateError(HttpServletRequest request, String objName) {
        ActionErrors aes = new ActionErrors();
        aes.add(aes.GLOBAL_ERROR, new ActionError("errors.database.duplicate",
                objName));
        saveErrors(request, aes);
    }

    private void generalError(HttpServletRequest request, Exception e) {
        ActionErrors aes = new ActionErrors();
        aes.add(aes.GLOBAL_ERROR, new ActionError("error.general", e
                .getMessage()));
        saveErrors(request, aes);
        e.printStackTrace();
    }

    private String getStrSql(int roomId, int sheetId, String bocoIds) {
        String sql = "";
        try {
            // 根据机房id,sheet_id得到遗留问题的sql
            TawDutySheetDAO tawDutySheetDAO = new TawDutySheetDAO(ds);
            if (tawDutySheetDAO.verifyHangQuestion(sheetId, roomId)) {
                // 得到遗留问题条件及遗留问题的Id
                int byAttrId = tawDutySheetDAO.getAttrId(sheetId, roomId, 0);
                int toAttrId = tawDutySheetDAO.getAttrId(sheetId, roomId, 1);
                // 根据attrId得到遗留问题的字段名称
                String byAttrField = tawDutySheetDAO.getFieldNameById(byAttrId);
                String toAttrField = tawDutySheetDAO.getFieldNameById(toAttrId);

                // 拼结sql串
                sql = "SELECT " + String.valueOf(toAttrField) + " FROM boco_"
                        + String.valueOf(sheetId) + " WHERE (" + byAttrField
                        + " IS NULL OR " + byAttrField + "='') AND id in ("
                        + bocoIds + ")";
                System.out.println(sql);
            } else {
                // 不需要进入遗留问题
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sql;
    }

    /**
     * add by 邓林华
     *
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 值班班次功能的查询页面
     */

    private ActionForward performDutyStatistic(ActionMapping mapping,
                                               ActionForm actionForm, HttpServletRequest request,
                                               HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        Vector SelectRoom = null;
        Vector SelectRoomName = null;
        TawRmAssignworkBO tawRmAssignworkBO = null;
        // edit by wangheqi
        TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();

        // TawValidatePrivBO tawValidatePrivBO = null;
        // edit by wangheqi
        TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
        TawSystemCptroom tawApparatusroom = null;

        // TawApparatusroomDAO tawApparatusroomDAO = null;
        // TawApparatusroom tawApparatusroom = null;
        String strSelectRoomName = null;

        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {
            SelectRoom = new Vector();
            SelectRoomName = new Vector();

            if (saveSessionBeanForm.getUserid().equals(StaticVariable.ADMIN)) {
                tawRmAssignworkBO = new TawRmAssignworkBO(ds);
                SelectRoom = tawRmAssignworkBO.getRoomSelect();
            } else {
                // tawValidatePrivBO = new TawValidatePrivBO(ds);
                // SelectRoom =
                // tawValidatePrivBO.validatePriv(saveSessionBeanForm.getWrf_UserID(),
                // 2051, 2);
                SelectRoom = StaticMethod
                        .list2vector(privBO
                                .getPermissions(
                                        saveSessionBeanForm.getUserid(),
                                        com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,
                                        com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM));
            }
            if (SelectRoom.size() > 0) {
                // tawApparatusroomDAO = new TawApparatusroomDAO(ds);
                tawApparatusroom = null;
                strSelectRoomName = "";
                Vector removeEle = new Vector();
                for (int i = 0; i < SelectRoom.size(); i++) {
                    tawApparatusroom = cptroomBO
                            .getTawSystemCptroomById(new Integer(String
                                    .valueOf(SelectRoom.elementAt(i))), 0);
                    if (tawApparatusroom != null) {
                        strSelectRoomName = StaticMethod
                                .null2String(tawApparatusroom.getRoomname());
                        SelectRoomName.add(strSelectRoomName);
                    } else {
                        removeEle.add(SelectRoom.elementAt(i));
                    }
                }
                SelectRoom.removeAll(removeEle);
            } else {
                return mapping.findForward("nopriv");
            }
            request.setAttribute("SelectRoom", SelectRoom);
            request.setAttribute("SelectRoomName", SelectRoomName);
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            SelectRoom = null;
            SelectRoomName = null;
            tawRmAssignworkBO = null;
            privBO = null;
            // tawValidatePrivBO = null;
            cptroomBO = null;
            // tawApparatusroomDAO = null;
            tawApparatusroom = null;
            strSelectRoomName = null;
        }
        return mapping.findForward("success");
    }

    /**
     * add by 邓林华
     *
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 值班班次统计结果
     */
    private ActionForward performDutyStatisticShow(ActionMapping mapping,
                                                   ActionForm actionForm, HttpServletRequest request,
                                                   HttpServletResponse response) {

        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        String starttime = null;
        String endtime = null;
        List dutyList = null;

        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {

            tawRmRecordDAO = new TawRmRecordDAO(ds);

            starttime = request.getParameter("starttime");
            endtime = request.getParameter("endtime");
            starttime = StaticMethod.getAddZero(starttime) + " 00:00:00";
            endtime = StaticMethod.getAddZero(endtime) + " 23:59:59";
            int roomId = Integer.parseInt(request.getParameter("roomId"));
            dutyList = tawRmRecordDAO.getDutyStatistics(roomId, starttime,
                    endtime);
            request.setAttribute("DUTYLIST", dutyList);

        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            starttime = null;
            endtime = null;
            dutyList = null;
        }

        return mapping.findForward("success");

    }

    private ActionForward performAuditBatch(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        TawRmRecord tawRmRecord = null;
        // logBO logbo = null;
        String auContent = null;
        String auTime = null;
        String auditor = null;
        String chkSel = StaticMethod
                .null2String(request.getParameter("chkSel"));

        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        TawRmRecordForm form = (TawRmRecordForm) actionForm;
        try {
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            tawRmRecord = new TawRmRecord();

            auditor = saveSessionBeanForm.getUserid();
            auTime = StaticMethod.getCurrentDateTime();
            auContent = form.getAuContent();

            if (tawRmRecord != null) {
                tawRmRecord.setAuTime(auTime);
                tawRmRecord.setAuditor(auditor);
                tawRmRecord.setAuContent(auContent);
                tawRmRecordDAO.auditBacth(tawRmRecord, chkSel);
            }
            // logbo = new logBO(ds);
            // boolean bool =
            // logbo.insertLogToDB(saveSessionBeanForm.getWrf_UserID(),
            // "批量审核值班记录", StaticVariable.OPER,
            // request.getRemoteAddr());
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            tawRmRecord = null;
            // logbo = null;
        }

        return mapping.findForward("success");

    }

    /*
     * private ActionForward performDutycheck(ActionMapping mapping, ActionForm
     * actionForm, HttpServletRequest request, HttpServletResponse response) {
     * SaveSessionBeanForm saveSessionBeanForm = null; TawRmRecordDAO
     * tawRmRecordDAO = null;
     *
     * try{ String workserial =
     * StaticMethod.null2String(request.getParameter("id")); String dutycheckId =
     * StaticMethod.null2String(request.getParameter("dutycheckId"));
     *
     * saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
     * getAttribute("SaveSessionBeanForm"); if (saveSessionBeanForm == null) {
     * return mapping.findForward("timeout"); } tawRmRecordDAO = new
     * TawRmRecordDAO(ds);
     *
     * request.setAttribute("checkman" , tawRmRecordDAO.getCheckman(workserial,
     * dutycheckId)); } catch (Exception e) { generalError(request, e); return
     * mapping.findForward("failure"); } finally { saveSessionBeanForm = null;
     * tawRmRecordDAO = null; }
     *
     * return mapping.findForward("success"); }
     */
    private ActionForward performCheckHold(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {

        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        String Dutyrecord = null;
        String DutyrecordId = null;
        // 判断超时
        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        Vector SelectRoom = null;
        Vector SelectRoomName = null;
        TawRmAssignworkBO tawRmAssignworkBO = null;
        // edit by wangheqi
        TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();

        // TawValidatePrivBO tawValidatePrivBO=null;
        // edit by wangheqi
        TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
        TawSystemCptroom tawApparatusroom = null;

        // TawApparatusroomDAO tawApparatusroomDAO=null;
        // TawApparatusroom tawApparatusroom=null;
        String strSelectRoomName = null;

        try {
            SelectRoom = new Vector();
            SelectRoomName = new Vector();
            // if(saveSessionBeanForm.getWrf_UserID().equals(StaticVariable.ADMIN)){
            tawRmAssignworkBO = new TawRmAssignworkBO(ds);
            SelectRoom = tawRmAssignworkBO.getRoomSelect();
            // }
            // else{
            // tawValidatePrivBO = new TawValidatePrivBO(ds);
            // SelectRoom =
            // tawValidatePrivBO.validatePriv(saveSessionBeanForm.getWrf_UserID(),
            // 2032, 2);
            // SelectRoom =
            // tawValidatePrivBO.validatePriv(saveSessionBeanForm.getWrf_UserID(),mapping.getPath());
            // }
            if (SelectRoom.size() > 0) {
                // tawApparatusroomDAO = new TawApparatusroomDAO(ds);
                tawApparatusroom = null;
                strSelectRoomName = "";
                Vector removeEle = new Vector();
                for (int i = 0; i < SelectRoom.size(); i++) {
                    tawApparatusroom = cptroomBO
                            .getTawSystemCptroomById(new Integer(String
                                    .valueOf(SelectRoom.elementAt(i))), 0);
                    if (tawApparatusroom != null) {
                        strSelectRoomName = StaticMethod
                                .null2String(tawApparatusroom.getRoomname());
                        SelectRoomName.add(strSelectRoomName);
                    } else {
                        removeEle.add(SelectRoom.elementAt(i));
                    }
                }
                SelectRoom.removeAll(removeEle);
            } else {
                return mapping.findForward("failure");
            }

        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;

        }
        request.setAttribute("SelectRoom", SelectRoom);
        request.setAttribute("SelectRoomName", SelectRoomName);
        return mapping.findForward("success");
    }

    private ActionForward performCheckDetail(ActionMapping mapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) {

        // SaveSessionBeanForm saveSessionBeanForm = null;
        // TawRmRecordDAO tawRmRecordDAO = null;
        //
        // //判断超时
        // saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
        // getAttribute("SaveSessionBeanForm");
        // if (saveSessionBeanForm == null) {
        // return mapping.findForward("timeout");
        // }

        String startTime = request.getParameter("starttime");
        String endTime = request.getParameter("endtime");
        String roomId = request.getParameter("roomId");
        List datelist = new ArrayList();
        try {
            TawSheetStatDAO tawSheetStatDAO = new TawSheetStatDAO(ds);
            List list = tawSheetStatDAO.getCheck(startTime, endTime, roomId);
            String datemix = tawSheetStatDAO.getDatemix();
            String dateMax = tawSheetStatDAO.getDatemax();
            if (list.size() != 0) {
                long a = StaticMethod.getTimestamp(datemix + " 00:00:00")
                        .getTime();
                long b = StaticMethod.getTimestamp(dateMax + " 00:00:00")
                        .getTime();
                long c = StaticMethod.getTimestamp(startTime + " 00:00:00")
                        .getTime();
                long d = StaticMethod.getTimestamp(endTime + " 00:00:00")
                        .getTime();
                if (c > a) {
                    a = c;
                    datemix = startTime;

                }
                if (d < b) {
                    b = d;

                }
                int num = (int) ((b - a) / (1000 * 60 * 60 * 24));

                if ((d < a && c < a) || (c > b && d > b)) {
                } else {
                    datelist.add(datemix.substring(0, 10));
                    for (int i = 0; i < num; i++) {
                        // StaticMethod.getTimestamp(startTime).getTime();
                        java.util.Calendar startData = java.util.Calendar
                                .getInstance();
                        startData.setTime((java.util.Date) StaticMethod
                                .getTimestamp(datemix + " 00:00:00"));

                        startData.add(java.util.Calendar.DAY_OF_YEAR, 1);

                        java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat(
                                "yyyy-MM-dd");
                        datemix = sf.format(startData.getTime());
                        datelist.add(datemix);
                    }
                }
            }
            request.setAttribute("STARTTIME", startTime);
            request.setAttribute("ENDTIME", endTime);
            request.setAttribute("ROOMID", roomId);

            request.setAttribute("DATELIST", datelist);
            request.setAttribute("CHECKLIST", list);

        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            // saveSessionBeanForm = null;
            // tawRmRecordDAO = null;

        }

        return mapping.findForward("success");
    }

    private ActionForward performCheckList(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {

        String dutyId = request.getParameter("dutyId");
        String startTime = request.getParameter("starttime");
        String endTime = request.getParameter("endtime");
        String roomId = request.getParameter("roomId");

        try {
            TawSheetStatDAO tawSheetStatDAO = new TawSheetStatDAO(ds);
            List list = tawSheetStatDAO.getCheckList(dutyId);

            request.setAttribute("DUTYID", dutyId);
            request.setAttribute("STARTTIME", startTime);
            request.setAttribute("ENDTIME", endTime);
            request.setAttribute("CHECKLIST", list);
            request.setAttribute("ROOMID", roomId);
            // request.setAttribute("DATELIST", datelist);
            // request.setAttribute("CHECKLIST", list);

        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            // saveSessionBeanForm = null;
            // tawRmRecordDAO = null;

        }

        return mapping.findForward("success");
    }

    // 添加excel导出方法
    private ActionForward performExport(ActionMapping mapping,
                                        ActionForm actionForm, HttpServletRequest request,
                                        HttpServletResponse response) {

        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        TawRmRecordBO tawRmRecordBO = null;
        String strCondition = null;

        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        try {
            tawRmRecordBO = new TawRmRecordBO(ds);
            strCondition = request.getParameter("strCondition");

            String exportUrl = tawRmRecordBO.exportToExcel(strCondition);

            File file = new File(exportUrl);
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

            e.printStackTrace();

        }
        return null;

    }

    private ActionForward performAddons(ActionMapping mapping,
                                        ActionForm actionForm, HttpServletRequest request,
                                        HttpServletResponse response) {

        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String typeId = request.getParameter("typeId");
        String nodeFolderPath = FilePathProcessor.recoverPath(request
                .getParameter("typeId"));
        request.getSession().setAttribute(
                UploadProcessor.NET_DISK_OPER_FOLDERPATH_KEY, nodeFolderPath);

        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        try {
            request.setAttribute("typeId", typeId);
            // String uploadPath =
            // DutyMgrLocator.getAttributes().getDutyRootPath();
        } catch (Exception e) {

            e.printStackTrace();

        }
        return mapping.findForward("success");

    }

    private ActionForward performUpLoad(ActionMapping mapping,
                                        ActionForm actionForm, HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = sessionform.getUserid();
        TawRmAddonsTableForm tawRmAddonsTableForm = (TawRmAddonsTableForm) actionForm;
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=GBK");
        String roomid = request.getParameter("typeId");
        TawRmRecordBO tawRmRecordBO = new TawRmRecordBO(ds);
        String timeTag = StaticMethod.getCurrentDateTime("yyyyMMddHHmmss");
        String timeDate = StaticMethod
                .getCurrentDateTime("yyyy-MM-dd HH:mm:ss");
        String uploadPath = DutyMgrLocator.getAttributes().getDutyRootPath()
                + "/";
        String dbPath = uploadPath + timeTag + ".xls";
        String sysTemPaht = request.getRealPath("/"); // 取当前系统路径
        String path = sysTemPaht + uploadPath;
        String filePath = sysTemPaht + dbPath;
        File tempFile = new File(path);
        if (!tempFile.exists()) {
            tempFile.mkdir();
        }
        request.setAttribute("typeId", roomid);
        FormFile file = tawRmAddonsTableForm.getThisFile();

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
            //

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            tawRmAddonsTableForm.setCreatTime(timeDate);
            tawRmAddonsTableForm.setCreatUser(userId);

            String loginType = UtilMgrLocator.getEOMSAttributes()
                    .getLoginType();
            if ("sso".equals(loginType)) {
                tawRmAddonsTableForm.setRemark(tawRmAddonsTableForm.getRemark());
                tawRmAddonsTableForm.setName(tawRmAddonsTableForm.getName());
            } else {
                tawRmAddonsTableForm.setRemark(new String(tawRmAddonsTableForm
                        .getRemark().getBytes("ISO-8859-1"), "UTF-8"));
                tawRmAddonsTableForm.setName(new String(tawRmAddonsTableForm
                        .getName().getBytes("ISO-8859-1"), "UTF-8"));
            }


            tawRmAddonsTableForm.setUrl(dbPath);
            tawRmAddonsTableForm.setRoomId(roomid);
            tawRmRecordBO.insert(tawRmAddonsTableForm);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward("success");

    }

    private ActionForward performgGetNodes(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {

        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        try {
            String nodeId = FilePathProcessor.recoverPath(request
                    .getParameter("node"));

            TawSystemTreeBo treebo = TawSystemTreeBo.getInstance();
            JSONArray jsonRoot = treebo.getCptroomTree(nodeId, "");

            response.setContentType("text/xml;charset=UTF-8");
            response.getWriter().print(jsonRoot.toString());

        } catch (Exception e) {

            e.printStackTrace();

        }
        return null;

    }

    // 显示列表
    private ActionForward performAddonsList(ActionMapping mapping,
                                            ActionForm form, HttpServletRequest request,
                                            HttpServletResponse response) {
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        List list = new ArrayList();
        TawRmRecordDAO tawRmRecordDAO = null;
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        String roomId = request.getParameter("typeId");
        if (roomId == null) {
            roomId = (String) request.getAttribute("typeId");
        }
        request.setAttribute("typeId", roomId);
        try {
            tawRmRecordDAO = new TawRmRecordDAO(ds);

            list = tawRmRecordDAO.getAddons(roomId);
            request.setAttribute("AddonsList", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("success");
    }

    // 删除
    private ActionForward performAddonsMove(ActionMapping mapping,
                                            ActionForm form, HttpServletRequest request,
                                            HttpServletResponse response) {
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        String id = request.getParameter("id");
        TawRmRecordDAO tawRmRecordDAO = null;
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        String roomId = request.getParameter("roomId");
        request.setAttribute("typeId", roomId);

        try {
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            tawRmRecordDAO.deleteAddons(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("success");
    }

    // xiazai
    private ActionForward performDownLoad(ActionMapping mapping,
                                          ActionForm form, HttpServletRequest request,
                                          HttpServletResponse response) {
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        TawRmRecordDAO tawRmRecordDAO = null;
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        String id = request.getParameter("id");
        String roomId = request.getParameter("roomId");
        request.setAttribute("typeId", roomId);

        try {
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            String url = tawRmRecordDAO.getAddonsUrl(id);
            String uploadPath = DutyMgrLocator.getAttributes()
                    .getDutyRootPath();
            String systemPath = StaticMethod.getWebPath(); // 取当前系统路径
            String path = systemPath + "/" + url;
            File file = new File(path);
            // 读到流中
            InputStream inStream = new FileInputStream("/" + file);// 文件的存放路径
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
            e.printStackTrace();
        }
        return null;
    }

    private ActionForward performUploadDetail(ActionMapping mapping,
                                              ActionForm form, HttpServletRequest request,
                                              HttpServletResponse response) {
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        TawRmRecordDAO tawRmRecordDAO = null;
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        HttpSession session = request.getSession();

        try {

            /*
             * SmartUpload mySmartUpload = new SmartUpload(); // Initialization
             * mySmartUpload.initialize(session); // Upload
             * mySmartUpload.upload(); com.jspsmart.upload.File myFile = null;
             * myFile = mySmartUpload.getFiles().getFile(1);
             * myFile.saveAs(request.getParameter("filePath"));
             */

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("success");
    }

    private ActionForward performUploadFinish(ActionMapping mapping,
                                              ActionForm form, HttpServletRequest request,
                                              HttpServletResponse response) {
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String workSerialId = saveSessionBeanForm.getWorkSerial();
        String id = request.getParameter("id");
        String path = request.getParameter("path");
        /* String url = request.getParameter("url"); */

        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        try {

            String strAutoSheetId = (String) request.getSession().getAttribute(
                    "autosheet_id");
            if (strAutoSheetId == null || strAutoSheetId.equals("")) {
                strAutoSheetId = "15";
            }
            String sheetid = "";
            int autoSheetId = Integer.parseInt(strAutoSheetId);
            TawDutySheet tawDutySheet = new TawDutySheet();
            TawDutySheetDAO tawDutySheetDAO = new TawDutySheetDAO(ds);

            tawDutySheet.setBoco_Id(autoSheetId);
            tawDutySheet.setSheetId(Integer.parseInt(id));
            tawDutySheet.setWorkserial(Integer.parseInt(workSerialId));
            tawDutySheet.setOper_Id(saveSessionBeanForm.getUserid());
            tawDutySheet.setOper_Time(StaticMethod.getLocalString());
            tawDutySheet.setUrl(path);
            /*
             * if(url!=null){ sheetid = tawDutySheetDAO.getIdByUrl(url);
             * if(sheetid!=null){ tawDutySheet.setId(sheetid); } }
             */

            tawDutySheetDAO.insert(tawDutySheet);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("success");
    }

    // 周期记录
    private ActionForward performCycleTable(ActionMapping mapping,
                                            ActionForm form, HttpServletRequest request,
                                            HttpServletResponse response) {
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String workSerialId = saveSessionBeanForm.getWorkSerial();
        String id = request.getParameter("id");
        String path = request.getParameter("path");
        int cycle = Integer.parseInt(request.getParameter("cycle"));
        String url = request.getParameter("url");
        if (!url.equals("")) {
            return mapping.findForward("success");
        }

        /* String url = request.getParameter("url"); */

        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        try {

            String strAutoSheetId = (String) request.getSession().getAttribute(
                    "autosheet_id");
            if (strAutoSheetId == null || strAutoSheetId.equals("")) {
                strAutoSheetId = "15";
            }
            String sheetid = "";
            int autoSheetId = Integer.parseInt(strAutoSheetId);
            TawRmCycleTableSub tawRmCycleTableSub = new TawRmCycleTableSub();
            TawRmCycleTableSubDAO tawRmCycleTableSubDAO = new TawRmCycleTableSubDAO(
                    ds);

            tawRmCycleTableSub.setBoco_Id(autoSheetId);
            tawRmCycleTableSub.setSheetId(Integer.parseInt(id));
            tawRmCycleTableSub.setWorkserial(Integer.parseInt(workSerialId));
            tawRmCycleTableSub.setOper_Id(saveSessionBeanForm.getUserid());
            tawRmCycleTableSub.setOper_Time(StaticMethod.getLocalString());
            tawRmCycleTableSub.setUrl(path);
            tawRmCycleTableSub.setCycle(cycle);
            /*
             * if(url!=null){ sheetid = tawDutySheetDAO.getIdByUrl(url);
             * if(sheetid!=null){ tawDutySheet.setId(sheetid); } }
             */

            tawRmCycleTableSubDAO.insert(tawRmCycleTableSub);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("success");
    }

    // 月报统计选择页面
    private ActionForward performReport(ActionMapping mapping,
                                        ActionForm actionForm, HttpServletRequest request,
                                        HttpServletResponse response) {
        String roomId = StaticMethod.nullObject2String(request
                .getParameter("typeId"));
        request.setAttribute("roomId", roomId);
        String tempYear = StaticMethod.getLocalString().substring(0, 4);
        int year = StaticMethod.null2int(tempYear);
        // 产生选择年的列表（当前时间的前5年的时间）
        List listYear = new ArrayList();
        int temp = 0;
        for (int i = 0; i < 5; i++) {
            String years = "";
            temp = year - i;
            years = Integer.toString(temp);
            listYear.add(years);
        }
        String Month = Integer.toString(StaticMethod.null2int(StaticMethod
                .getLocalString().substring(5, 7)));
        request.setAttribute("Month", Month);
        request.setAttribute("Year", tempYear);
        request.setAttribute("listYear", listYear);
        return mapping.findForward("success");
    }

    // 月报统计结果(excel文件)
    private ActionForward performMonthReport(ActionMapping mapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) {

        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        TawRmRecordBO tawRmRecordBO = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        TawRmRecordForm form = (TawRmRecordForm) actionForm;
        String strCondition = "";

        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        try {
            tawRmRecordBO = new TawRmRecordBO(ds);
            String beginTime = form.getReportYear() + "-"
                    + form.getReportMonth() + "-1 00:00:00";
            Date date = sdf.parse(form.getReportYear() + "-"
                    + form.getReportMonth() + "-1");
            String endTime = sdf.format(tawRmRecordBO.getLastDayOfMonth(date))
                    + " 23:59:59";

            strCondition = "where 1=1 and starttime >= '" + beginTime
                    + "' and endtime <='" + endTime + "' and room_id = "
                    + form.getRoomId();

            String exportUrl = tawRmRecordBO.exportToExcel(strCondition);

            File file = new File(exportUrl);
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

            e.printStackTrace();

        }
        return null;

    }

    private ActionForward performUpdateRecord(ActionMapping mapping,
                                              ActionForm actionForm, HttpServletRequest request,
                                              HttpServletResponse response) {
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        TawRmRecordDAO tawRmRecordDAO = null;
        TawRmRecord tawRmRecord = null;
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        TawRmRecordForm form = (TawRmRecordForm) actionForm;
        try {
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            tawRmRecord = new TawRmRecord();
            tawRmRecord.setId(form.getId());
            tawRmRecord.setNetfault(form.getNetfault());
            tawRmRecord.setImportantaffair(form.getImportantaffair());
            tawRmRecord.setHarmony(form.getHarmony());
            tawRmRecord.setOtheraffair(form.getOtheraffair());
            // tawRmRecordDAO.updateRecord(tawRmRecord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tawRmRecordDAO = null;
            tawRmRecord = null;
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward performcheckRoom(ActionMapping mapping,
                                          ActionForm form, HttpServletRequest request,
                                          HttpServletResponse response) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object getBean(String name) {
        ApplicationContext ctx = WebApplicationContextUtils
                .getRequiredWebApplicationContext(servlet.getServletContext());
        return ctx.getBean(name);
    }

    private ActionForward performSelectRoom(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        Vector SelectRoom = null;
        Vector SelectRoomName = null;
        TawRmAssignworkBO tawRmAssignworkBO = null;
        TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
        TawSystemCptroom tawApparatusroom = null;
        TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();
        String strSelectRoomName = null;

        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {
            /*
             * SelectRoom = new Vector(); SelectRoomName = new Vector();
             * if(saveSessionBeanForm.getUserid().equals(StaticVariable.ADMIN)){
             * tawRmAssignworkBO = new TawRmAssignworkBO(ds); SelectRoom =
             * tawRmAssignworkBO.getRoomSelect(); } else{ //tawValidatePrivBO =
             * new TawValidatePrivBO(ds); //SelectRoom =
             * tawValidatePrivBO.validatePriv(saveSessionBeanForm.getWrf_UserID(),
             * 2032, 2); SelectRoom =
             * StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM)); }
             * if (SelectRoom.size() > 0) { //tawApparatusroomDAO = new
             * TawApparatusroomDAO(ds); tawApparatusroom = null;
             * strSelectRoomName = ""; Vector removeEle=new Vector(); for (int i =
             * 0; i < SelectRoom.size(); i++) { tawApparatusroom =
             * cptroomBO.getTawSystemCptroomById(new Integer(
             * String.valueOf(SelectRoom.elementAt(i))),0); //tawApparatusroom =
             * tawApparatusroomDAO.retrieve(Integer.parseInt( //
             * String.valueOf(SelectRoom.elementAt(i)))); if (tawApparatusroom !=
             * null) { strSelectRoomName =
             * StaticMethod.null2String(tawApparatusroom. getRoomname());
             * SelectRoomName.add(strSelectRoomName); } else {
             * removeEle.add(SelectRoom.elementAt(i)); } }
             * SelectRoom.removeAll(removeEle); } else{ return
             * mapping.findForward("nopriv"); }
             * request.setAttribute("SelectRoom",SelectRoom);
             * request.setAttribute("SelectRoomName",SelectRoomName);
             */
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            SelectRoom = null;
            SelectRoomName = null;
            tawRmAssignworkBO = null;
            privBO = null;
            // tawValidatePrivBO=null;
            cptroomBO = null;
            // tawApparatusroomDAO=null;
            tawApparatusroom = null;
            strSelectRoomName = null;
        }
        return mapping.findForward("success");
    }

    private ActionForward performQueryDuty(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String roomId = request.getParameter("roomId");
        request.setAttribute("roomId", roomId);
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        // Vector SelectRoom=null;
        // Vector SelectRoomName=null;
        // TawRmAssignworkBO tawRmAssignworkBO=null;
        // edit by wangheqi
        // TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();
        // TawValidatePrivBO tawValidatePrivBO=null;
        // edit by wangheqi
        // TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
        // TawSystemCptroom tawApparatusroom = null;
        // TawApparatusroomDAO tawApparatusroomDAO=null;
        // TawApparatusroom tawApparatusroom =null;
        // String strSelectRoomName=null;
        // saveSessionBeanForm =
        // (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
        // TawRmSysteminfoForm form = (TawRmSysteminfoForm) actionForm;
        // form.setStrutsAction(TawRmSysteminfoForm.SELECT_ROOM);
        try {
            if (saveSessionBeanForm == null) {
                return mapping.findForward("timeout");
            }
            /*
             * SelectRoom = new Vector(); SelectRoomName = new Vector();
             *
             * if(saveSessionBeanForm.getUserid().equals(StaticVariable.ADMIN)){
             * tawRmAssignworkBO = new TawRmAssignworkBO(ds); SelectRoom =
             * tawRmAssignworkBO.getRoomSelect(); } else{ //tawValidatePrivBO =
             * new TawValidatePrivBO(ds); //SelectRoom =
             * tawValidatePrivBO.validatePriv(saveSessionBeanForm.getWrf_UserID(),
             * 2033, 2); SelectRoom =
             * StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM)); }
             * //zc 1-6 增加功能，满足本机房人员可以进行查询操作的要求 if (SelectRoom.size()==0){
             * String roomId = String.valueOf(saveSessionBeanForm.getRoomId());
             * if (!roomId.equals("0")){ SelectRoom.add(roomId); } } // if
             * (SelectRoom.size() > 0) { //tawApparatusroomDAO = new
             * TawApparatusroomDAO(ds); //tawApparatusroom = null;
             * strSelectRoomName = ""; Vector removeEle=new Vector(); for (int i =
             * 0; i < SelectRoom.size(); i++) { tawApparatusroom =
             * cptroomBO.getTawSystemCptroomById(new Integer(
             * String.valueOf(SelectRoom.elementAt(i))),0); if (tawApparatusroom !=
             * null) { strSelectRoomName =
             * StaticMethod.null2String(tawApparatusroom. getRoomname());
             * SelectRoomName.add(strSelectRoomName); } else {
             * removeEle.add(SelectRoom.elementAt(i)); } }
             * SelectRoom.removeAll(removeEle); } else{ return
             * mapping.findForward("nopriv"); }
             * request.setAttribute("SelectRoom",SelectRoom);
             * request.setAttribute("SelectRoomName",SelectRoomName);
             */
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            // SelectRoom=null;
            // SelectRoomName=null;
            // tawRmAssignworkBO=null;
            // privBO = null;
            // tawValidatePrivBO=null;
            // cptroomBO = null;
            // tawApparatusroomDAO=null;
            // tawApparatusroom =null;
            // strSelectRoomName=null;
        }
        return mapping.findForward("success");
    }

    private ActionForward performQueryResult(ActionMapping mapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) {
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        TawRmRecordDAO tawRmRecordDAO = new TawRmRecordDAO();
        String time_from = null;
        String time_to = null;
        String room_name = null;
        List list = new ArrayList();
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        String room = request.getParameter("roomId");
        int roomId = Integer.parseInt(room);
        request.setAttribute("roomId", request.getParameter("roomId"));

        String time_fromyear = request.getParameter("time_fromyear");
        String time_frommonth = request.getParameter("time_frommonth");
        String time_fromday = request.getParameter("time_fromday");
        time_from = time_fromyear + "-" + time_frommonth + "-" + time_fromday;
        String time_toyear = request.getParameter("time_toyear");
        String time_tomonth = request.getParameter("time_tomonth");
        String time_today = request.getParameter("time_today");
        time_to = time_toyear + "-" + time_tomonth + "-" + time_today;

        time_from = StaticMethod.getAddZero(time_from);
        time_to = StaticMethod.getAddZero(time_to);
        // String starttime=StaticMethod.getTimestamp(time_from);
        // Date endtime=StaticMethod.getTimestamp(time_from);
        try {
            list = tawRmRecordDAO.getTawRmExchangeTime(room, time_from,
                    time_to, "0");

            /*
             * //测试 Map m=new HashMap(); m.put("workserial", "韩璐");
             * m.put("starttime_defined", "2009-04-08 00:40:45");
             * m.put("endtime_defined", "2009-04-22 00:40:45"); list.add(m); Map
             * m1=new HashMap(); m1.put("workserial", "韩璐");
             * m1.put("starttime_defined", "2009-04-08 00:40:45");
             * m1.put("endtime_defined", "2009-04-22 00:40:45"); list.add(m1);
             */
            request.setAttribute("TAWRMEXCHANGEPER", list);
            request.setAttribute("ROOMNAME", room_name);
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            time_from = null;
            time_to = null;
            room_name = null;

        }
        return mapping.findForward("success");
    }

    private ActionForward performDeleteMain(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        TawRmRecordDAO tawRmRecordDAO = null;
        TawRmRecord tawRmRecord = null;
        TawUserRoomDAO tawUserRoomDAO = null;
        String roomName = null;
        TawRmDutyfileDAO tawRmDutyfileDAO = null;
        Vector vecDutyFile = null;
        List tawRmRecord_dutyman = null;
        // logBO logbo = null;
        // 判断超时
        // saveSessionBeanForm =
        // (SaveSessionBeanForm) request.getSession().getAttribute(
        // "SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        TawRmRecordForm form = (TawRmRecordForm) actionForm;
        try {
            tawRmRecordDAO = new TawRmRecordDAO(ds);
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("workserial", id + "");
            String setStrutsAction = StaticMethod.nullObject2String(request
                    .getParameter("setStrutsAction"));
            tawRmRecord = tawRmRecordDAO.retrieve(id);
            if (tawRmRecord == null) {
                ActionErrors aes = new ActionErrors();
                aes.add(aes.GLOBAL_ERROR, new ActionError(
                        "error.object.notfound", "TawRmRecord"));
                saveErrors(request, aes);
            } else {
                tawRmRecord.setStarttimeDefined("");
                tawRmRecord.setStarttimesub("");
                tawRmRecord.setStatement("");
                tawRmRecord.setEndtimeDefined("");
                tawRmRecord.setDutymansub("");
                tawRmRecord.setDutymaster("");

                org.apache.commons.beanutils.BeanUtils.populate(form,
                        org.apache.commons.beanutils.BeanUtils
                                .describe(tawRmRecord));
                // 机房名称
                int roomid = tawRmRecord.getRoomId();
                tawUserRoomDAO = new TawUserRoomDAO(ds);
                roomName = tawUserRoomDAO.getRoomName(roomid);
                request.setAttribute("roomName", roomName);
                // 完成状态
                int intFlag = tawRmRecord.getFlag();
                request.setAttribute("intFlag", String.valueOf(intFlag));
                // 附件
                tawRmDutyfileDAO = new TawRmDutyfileDAO(ds);
                vecDutyFile = tawRmDutyfileDAO.getDutyFile(id);
                request.setAttribute("vecDutyFile", vecDutyFile);
                // 值班人
                tawRmRecord_dutyman = tawRmRecordDAO.listDutyman(id);
                request
                        .setAttribute("TAWRMRECORD_DUTYMAN",
                                tawRmRecord_dutyman);
                // 遗留问题
                TawRmHangoverDAO tawRmHangoverDAO = new TawRmHangoverDAO(ds);
                TawRmHangover tawRmHangover = tawRmHangoverDAO.retrieve(id, 0);
                String strHangQuestion = "";
                if (tawRmHangover != null) {
                    strHangQuestion = tawRmHangover.getHangQuestion();
                }
                request.setAttribute("strHangQuestion", strHangQuestion);
                request.setAttribute("setStrutsAction", setStrutsAction);
                request
                        .setAttribute("AUDITPRIV", String
                                .valueOf(tawRmRecordDAO
                                        .getAuditPriv(saveSessionBeanForm
                                                .getUserid(), roomid)));
                request.setAttribute("dutycheck", tawRmRecord.getDutycheck());

                String strId = String.valueOf(id);
                for (int i = 1; i <= StaticDutycheck.dutycheckcount; i++) {
                    String checkId = StaticDutycheck.getDutycheckId(i);
                    request.setAttribute(checkId, tawRmRecordDAO.getCheckman(
                            strId, checkId));
                }

            }
            // logbo = new logBO(ds);
            // boolean bool =
            // logbo.insertLogToDB(saveSessionBeanForm.getWrf_UserID(),
            // "查询值班记录", StaticVariable.OPER,
            // request.getRemoteAddr());
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            tawRmRecordDAO = null;
            tawRmRecord = null;
            tawUserRoomDAO = null;
            roomName = null;
            tawRmDutyfileDAO = null;
            vecDutyFile = null;
            tawRmRecord_dutyman = null;
            // logbo = null;
        }
        return mapping.findForward("success");

    }

    /**
     * 交接班之后保存工单处理相关信息
     * ID:EOMS-方永峰-20090922
     * 邮箱：fangyongfeng@boco.com.cn
     * 时间：2009-09-22
     *
     * @param
     */
    private void saveDutyInfo(String userId, String deptId, String workserial) {
        Vector vector = null;
        TawRmAssignworkDAO tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);
        TawRmSysteminfoDAO dao = new TawRmSysteminfoDAO(ds);
        TawRmAssignwork tawRmAssignwork;
        try {
            tawRmAssignwork = tawRmAssignworkDAO.retrieve(Integer.parseInt(workserial));

            vector = dao.getVectordutysheet(tawRmAssignwork.getRoomId());
            ArrayList list = new ArrayList();
            TawRmDutySheetDao sheetdao = new TawRmDutySheetDao(ds);
            for (int i = 0; i < vector.size(); i++) {
                ArrayList list1 = (ArrayList) sheetdao.getshowListbydutysheet(userId, deptId, vector.elementAt(i).toString(), tawRmAssignwork.getStarttimeDefined(), tawRmAssignwork.getEndtimeDefined());
                list.add(list1);
            }
            String type = "";
            for (int i = 0; i < list.size(); i++) {
                type = (String) DictMgrLocator.getDictService().itemId2description(Util.constituteDictId("dict-sheet", "sheet"), vector.elementAt(i).toString());

                List list1 = (List) list.get(i);
                List list2 = (List) list1.get(0);
                List list3 = (List) list1.get(1);
                for (int m = 0; m < list2.size(); m++) {
                    TawRmUnDoneTask task = (TawRmUnDoneTask) list2.get(m);
                    sheetdao.insertTaskbytype(task, type, userId, workserial);
                }
                for (int n = 0; n < list3.size(); n++) {
                    TawRmDoneTask task = (TawRmDoneTask) list3.get(n);
                    sheetdao.saveTaskbyType(task, type, userId, workserial);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
