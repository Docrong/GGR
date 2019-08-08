package com.boco.eoms.sheet.complaint.webapp.action;


import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.util.xml.XMLProperties;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.limit.model.LevelLimit;
import com.boco.eoms.sheet.limit.model.StepLimit;
import com.boco.eoms.sheet.limit.service.ISheetDealLimitManager;
import com.boco.eoms.sheet.limit.util.SheetLimitUtil;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.*;
import org.displaytag.util.ParamEncoder;

public class ComplaintHBAction extends ComplaintAction {
    public void showNewLimit(ActionMapping mapping,
                             ActionForm form, HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        String levelId = StaticMethod.null2String(request.getParameter("levelId"));
        ISheetDealLimitManager mgr = (ISheetDealLimitManager) getBean("iSheetDealLimitManager");
        List list = mgr.getStepLimitByLevel(levelId);
        JSONArray jsonRoot = new JSONArray();
        jsonRoot = JSONArray.fromArray(list.toArray());
        JSONUtil.print(response, jsonRoot.toString());
    }


    /**
     * 根据专业，查询时限
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void newShowLimit(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String flowTemplateName = StaticMethod.nullObject2String(request
                .getParameter("flowName"));
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("taskName"));
        Date sheetCompleteLimit = StaticMethod.nullObject2Timestamp(request
                .getParameter("sheetCompleteLimit"));
        LevelLimit levelLimit = SheetLimitUtil.makeLevelLimit(request);
        ISheetDealLimitManager mgr = (ISheetDealLimitManager) getBean("iSheetDealLimitManager");
        HashMap specialsMap = SheetLimitUtil.makeSpecialsMap(levelLimit);
        specialsMap.put("flowName", flowTemplateName);
        List sheetLimitList = mgr.getlevelLimitBySpecials(specialsMap);
        LevelLimit limit = null;
        if (sheetLimitList != null && sheetLimitList.size() > 0) {
            limit = (LevelLimit) sheetLimitList.get(0);
            Calendar calendar = Calendar.getInstance();
            if (!taskName.equals("")) {
                String levelId = limit.getId();
                List stepList = mgr.getStepLimitByLevel(levelId);
                limit = new LevelLimit();
                if (stepList != null && stepList.size() > 0) {
                    for (int i = 0; i < stepList.size(); i++) {
                        StepLimit step = (StepLimit) stepList.get(i);
                        if (step.getTaskName().equals(taskName)) {
                            calendar.add(Calendar.MINUTE, StaticMethod.null2int(step.getCompleteLimit()) + 120);
                            if (sheetCompleteLimit != null && sheetCompleteLimit.after(calendar.getTime())) {
                                limit.setAcceptLimit(step.getAcceptLimit());
                                limit.setReplyLimit(step.getCompleteLimit());
                                break;
                            } else {
                                limit.setAcceptLimit("0");
                                limit.setReplyLimit("0");
                                break;
                            }
                        }
                    }
                }
            }


        } else {
            limit = new LevelLimit();
        }
        if (limit.getAcceptLimit() == null) limit.setAcceptLimit("0");
        if (limit.getReplyLimit() == null) limit.setReplyLimit("0");
        Date currentDate = new Date();
        //将工作时间和休息时间计算进来
        int acceptLimit = SheetLimitUtil.getTimeLimitByConfig(currentDate, 0, Integer.parseInt(limit.getAcceptLimit()), flowTemplateName);
        int completeLimit = SheetLimitUtil.getTimeLimitByConfig(currentDate, acceptLimit, Integer.parseInt(limit.getReplyLimit()), flowTemplateName);
        limit.setAcceptLimit("" + acceptLimit);
        limit.setReplyLimit("" + completeLimit);
        JSONObject jsonRoot = new JSONObject();
        jsonRoot = JSONObject.fromObject(limit);
        JSONUtil.print(response, jsonRoot.toString());
    }

    public ActionForward showListAll(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ComplaintSheetMethodHB baseSheet = new ComplaintSheetMethodHB();
        baseSheet.showListUndoAll(mapping, form, request, response);
        return mapping.findForward("listall");
    }

    public ActionForward showRepeatDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String complaintTime = StaticMethod.null2String(request.getParameter("complaintTime"));
        String cusPhone = StaticMethod.null2String(request.getParameter("cusPhone"));
        String repeatNum = StaticMethod.null2String(request.getParameter("repeatNum"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date comTime = sdf.parse(complaintTime);
        Calendar cal = Calendar.getInstance();
        cal.setTime(comTime);
        if ("1".equals(repeatNum)) {
            int time = StaticMethod.nullObject2int(XmlManage.getFile("/config/complaint-util.xml").getProperty("RepeatComplaintTime"));
            cal.add(5, time);
        } else if ("2".equals(repeatNum)) {
            int time2 = StaticMethod.nullObject2int(XmlManage.getFile("/config/complaint-util.xml").getProperty("RepeatComplaintTime2"));
            cal.add(5, time2);
        }
        String beforedate = sdf.format(cal.getTime());
        String afterdate = sdf.format(comTime);
        IDownLoadSheetAccessoriesService service = (IDownLoadSheetAccessoriesService) getBean("IDownLoadSheetAccessoriesService");
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
        String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
        Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
        String order = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("o")));
        String sort = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("s")));
        String orderCondition = "";
        if (!order.equals(""))
            if (order.equals("1"))
                order = " asc";
            else
                order = " desc";
        if (!sort.equals(""))
            orderCondition = " " + sort + order;
        String exportType = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("e")));
        if (!exportType.equals(""))
            pageSize = new Integer(-1);
        String totalCount = "select * from Complaint_Main s  where s.complaintTime >= to_date('" + beforedate + "', 'yyyy-MM-dd HH24:mi:ss') " + "and s.complaintTime <= to_date('" + afterdate + "', 'yyyy-MM-dd HH24:mi:ss') and s.customPhone = '" + cusPhone + "'";
        String sql = "select  complaintm1_.sheetid,complaintm1_.complainttime,complaintm1_.customphone,complaintm1_.complaintDesc,complaintl0_.dealdesc,complaintm1_.id from  complaint_main complaintm1_ left  join complaint_link complaintl0_ on complaintl0_.mainId = complaintm1_.id and complaintl0_.operateType = '46' where customPhone = '" + cusPhone + "' and complaintm1_.complaintTime >=to_date('" + beforedate + "', 'yyyy-MM-dd HH24:mi:ss') and " + " complaintm1_.complaintTime <=to_date('" + afterdate + "', 'yyyy-MM-dd HH24:mi:ss') order by complaintm1_.sendTime ";
        if ("2".equals(repeatNum)) {
            totalCount = "select * from Complaint_Main s  where s.complaintTime >= to_date('" + beforedate + "', 'yyyy-MM-dd HH24:mi:ss') " + "and s.complaintTime <= to_date('" + afterdate + "', 'yyyy-MM-dd HH24:mi:ss') and s.complainttype2='10106250101' and s.customPhone = '" + cusPhone + "'";
            sql = "select  complaintm1_.sheetid,complaintm1_.complainttime,complaintm1_.customphone,complaintm1_.complaintDesc,complaintl0_.dealdesc,complaintm1_.id from  complaint_main complaintm1_ left  join complaint_link complaintl0_ on complaintl0_.mainId = complaintm1_.id and complaintl0_.operateType = '46' where customPhone = '" + cusPhone + "' and complaintm1_.complaintTime >=to_date('" + beforedate + "', 'yyyy-MM-dd HH24:mi:ss') and " + " complaintm1_.complaintTime <=to_date('" + afterdate + "', 'yyyy-MM-dd HH24:mi:ss') AND complaintm1_.complainttype2='10106250101' order by complaintm1_.sendTime ";
        }
        String listSql = sql;
        if (pageSize.intValue() != -1)
            if (pageIndex.intValue() == 0)
                listSql = " select * from ( " + sql + " ) where rownum <=" + pageSize.intValue();
            else
                listSql = " select * from ( select row_.*, rownum rownum_ from ( " + sql + " ) row_ ) where rownum_<=" + (pageIndex.intValue() + 1) * pageSize.intValue() + " and rownum_ > " + pageIndex.intValue() * pageSize.intValue();
        System.out.println("--ph---totalCount-" + totalCount);
        System.out.println("--ph----listSql--" + listSql);
        List totalResults = service.getSheetAccessoriesList(totalCount);
        List results = service.getSheetAccessoriesList(listSql);
        request.setAttribute("taskList", results);
        request.setAttribute("resultSize", new Integer(totalResults.size()));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("showRepeatDetail");
    }

    public ActionForward getAiNetResult(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String sheetKey = StaticMethod.null2String(request.getParameter("sheetKey"));
        System.out.println("ph:sheetKey=" + sheetKey);
        ComplaintSheetMethodHB baseSheet = new ComplaintSheetMethodHB();
        String reply = baseSheet.getAiNetResult(mapping, form, request, response);
        System.out.println("ph:reply=" + reply);
        JSONArray json = new JSONArray();
        JSONObject jitem = new JSONObject();
        jitem.put("reply", reply);
        json.put(jitem);
        response.setContentType("text/xml;charset=UTF-8");
        response.getWriter().print(json.toString());
        return null;
    }


    /**
     * 得到指定id的附件
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getAccessories(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
//		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
//		String userId = sessionform.getUserid();
//		String sheetAccessories = StaticMethod.nullObject2String(request.getParameter("sheetAccessories"));
        //INSERT INTO commonfaut_tsbk VALUES('1111111','huxing',sysdate) sheetAccessories
        String showAccessories = "SELECT * FROM commonfaut_tsbk ORDER BY createtime DESC";
        IDownLoadSheetAccessoriesService sqlMgr = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        List accessList = (List) sqlMgr.getSheetAccessoriesList(showAccessories);
        List retList = new ArrayList();
        if (accessList != null && accessList.size() > 0) {
            String accessories = StaticMethod.nullObject2String(((Map) accessList.get(0)).get("sheetAccessories"));
            if (!"".equals(accessories)) {
                accessories = "'" + accessories + "'";
                if (accessories.contains(",")) {
                    accessories = accessories.replace(",", "','");
                }
                //查询条件的附件
                String sql = "SELECT * FROM taw_commons_accessories WHERE accessoriesname IN (" + accessories + ")";
                retList = sqlMgr.getSheetAccessoriesList(sql);
            }

        }
        request.setAttribute("retList", retList);
        return mapping.findForward("commonfaulttsbk");
    }

    //saveAccessories
    public ActionForward saveAccessories(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        IDownLoadSheetAccessoriesService sqlMgr = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        String userId = sessionform.getUserid();
        String sheetAccessories = StaticMethod.nullObject2String(request.getParameter("sheetAccessories"));
        String insertSql = "INSERT INTO commonfaut_tsbk VALUES(" + sheetAccessories + ",'" + userId + "',sysdate)";
        sqlMgr.updateTasks(insertSql);

        return mapping.findForward("successtsbk");
    }


}
