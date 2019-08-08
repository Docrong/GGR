package com.boco.eoms.sheet.supervisetask.webapp.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.poi.hslf.record.MainMaster;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commontask.model.CommonTaskMain;
//import com.boco.eoms.sheet.listedregulation.model.ListedRegulationMain;
//import com.boco.eoms.sheet.listedregulation.model.ListedRegulationTask;
import com.boco.eoms.sheet.listedregulation.model.ListedRegulationMain;
import com.boco.eoms.sheet.supervisetask.model.SuperviseTaskMainDuty;
import com.boco.eoms.sheet.supervisetask.model.SuperviseTaskRecord;
import com.boco.eoms.sheet.supervisetask.model.SuperviseTaskRule;
import com.boco.eoms.sheet.supervisetask.service.SuperviseTaskManager;

public class SuperviseTaskAction extends SheetAction {

    static SuperviseTaskManager superviseTaskManager = (SuperviseTaskManager) ApplicationContextHolder.getInstance().getBean("iSuperviseTaskManager");

    public ActionForward supervisetaskRuleAdd(ActionMapping mapping, ActionForm form,
                                              HttpServletRequest request, HttpServletResponse response) throws Exception {


        return mapping.findForward("supervisetaskRuleAdd");
    }

    public ActionForward supervisetaskRuleEdit(ActionMapping mapping, ActionForm form,
                                               HttpServletRequest request, HttpServletResponse response) {
        String id = StaticMethod.null2String(request.getParameter("id"));
        SuperviseTaskManager superviseTaskManager2 = (SuperviseTaskManager) ApplicationContextHolder.getInstance().getBean("iSuperviseTaskManager");
        SuperviseTaskRule t = superviseTaskManager.getSuperviseTaskRuleById(id);
        request.setAttribute("object", t);

        return mapping.findForward("supervisetaskRuleEdit");
    }

    //批量删除
    public ActionForward supervisetaskRuleDelete(ActionMapping mapping, ActionForm form,
                                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        String piids = StaticMethod.null2String(request.getParameter("piids"));
        SuperviseTaskRule t = superviseTaskManager.getSuperviseTaskRuleById(piids);
        String[] ids = piids.split(",");
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] != null && !ids[i].equals("")) {
                String sql = "update Supervise_task_rule set deleted='1' where id = '" + ids[i] + "' ";
                IDownLoadSheetAccessoriesService service = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
//				int ifsuccess = service.excuteUpdate(sql);
//				BocoLog.info(this, "update Supervise_task_rule set deleted='1' where id = "+ids[i]+",ifsuccess="+ifsuccess);

            }
        }
        System.out.println("piids-->" + piids);


        return mapping.findForward("supervisetaskRuleList");
    }

    public ActionForward supervisetaskRuleUnique(ActionMapping mapping, ActionForm form,
                                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        String major = request.getParameter("major");
        String listedRegulationType = request.getParameter("listedRegulationType");
        String listedRegulationCycle = request.getParameter("listedRegulationCycle");
        String city = request.getParameter("city");
        String country = request.getParameter("country");

        String sql = "select * from supervise_task_rule where deleted='0' and major='" + major + "' and listedRegulationType='" +
                listedRegulationType + "' and listedRegulationCycle='" + listedRegulationCycle + "' and city='" + city + "'";
        String countSql = "select count(*) from sms_rule";

        IDownLoadSheetAccessoriesService service = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        List taskList = service.getSheetAccessoriesList(sql);
        if (taskList.size() > 1) {
            request.setAttribute("unique", "false");
        } else {
            request.setAttribute("unique", "true");
        }

        return null;
    }

    public ActionForward supervisetaskRuleAddSave(ActionMapping mapping, ActionForm form,
                                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = StaticMethod.null2String(request.getParameter("id"));

        String major = request.getParameter("major");
        String mainNetSortOne = request.getParameter("mainNetSortOne");
        String mainNetSortTwo = request.getParameter("mainNetSortTwo");
        String mainNetSortThree = request.getParameter("mainNetSortThree");
        String city = request.getParameter("toDeptId");
        String country = request.getParameter("mainFaultGenerantCitySubCode");
        String listedRegulationType = request.getParameter("listedRegulationType");
        String listedRegulationCycle = request.getParameter("listedRegulationCycle");
        String[] superviseType = request.getParameterValues("superviseType");//IVR||SMS
        String acceptOverTime1 = request.getParameter("acceptOverTime1");
        String acceptOverTime2 = request.getParameter("acceptOverTime2");
        String acceptOverTime3 = request.getParameter("acceptOverTime3");
        String acceptOverTime4 = request.getParameter("acceptOverTime4");
        String dealOverTime1 = request.getParameter("dealOverTime1");
        String dealOverTime2 = request.getParameter("dealOverTime2");
        String dealOverTime3 = request.getParameter("dealOverTime3");
        String dealOverTime4 = request.getParameter("dealOverTime4");
//		String[] noticeUser1=request.getParameterValues("noticeUserId");
//		String[] noticeUser2=request.getParameterValues("noticeUserId2");
//		String[] noticeUser3=request.getParameterValues("noticeUserId3");
//		String[] noticeUser4=request.getParameterValues("noticeUserId4");

        String[] noticeUserIds = request.getParameterValues("noticeUserId");
        String noticeUserName = StaticMethod.null2String(request.getParameter("noticeUserName"));
        String[] noticeUserId2s = request.getParameterValues("noticeUserId2");
        String noticeUserName2 = StaticMethod.null2String(request.getParameter("noticeUserName2"));
        String[] noticeUserId3s = request.getParameterValues("noticeUserId3");
        String noticeUserName3 = StaticMethod.null2String(request.getParameter("noticeUserName3"));
        String[] noticeUserId4s = request.getParameterValues("noticeUserId4");
        String noticeUserName4 = StaticMethod.null2String(request.getParameter("noticeUserName4"));
        String noticeUserId = "";
        if (noticeUserIds == null) {
            noticeUserIds = new String[1];
            noticeUserIds[0] = "";
        }
        if (noticeUserId2s == null) {
            noticeUserId2s = new String[1];
            noticeUserId2s[0] = "";
        }
        if (noticeUserId3s == null) {
            noticeUserId3s = new String[1];
            noticeUserId3s[0] = "";
        }
        if (noticeUserId4s == null) {
            noticeUserId4s = new String[1];
            noticeUserId4s[0] = "";
        }
        for (int i = 0; noticeUserId != null && i < noticeUserIds.length; i++) {
            noticeUserId += noticeUserIds[i] + ",";
        }

        String noticeUserId2 = "";
        for (int i = 0; noticeUserId2s != null && i < noticeUserId2s.length; i++) {
            noticeUserId2 += noticeUserId2s[i] + ",";
        }

        String noticeUserId3 = "";
        for (int i = 0; noticeUserId3s != null && i < noticeUserId3s.length; i++) {
            noticeUserId3 += noticeUserId3s[i] + ",";
        }

        String noticeUserId4 = "";
        for (int i = 0; noticeUserId4s != null && i < noticeUserId4s.length; i++) {
            noticeUserId4 += noticeUserId4s[i] + ",";
        }

        IDownLoadSheetAccessoriesService service = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
//		List taskList = service.getSheetAccessoriesList(sql);

        noticeUserId = "".equals(noticeUserId) ? "" : noticeUserId.substring(0, noticeUserId.length() - 1);
        noticeUserId2 = "".equals(noticeUserId2) ? "" : noticeUserId2.substring(0, noticeUserId2.length() - 1);
        noticeUserId3 = "".equals(noticeUserId3) ? "" : noticeUserId3.substring(0, noticeUserId3.length() - 1);
        noticeUserId4 = "".equals(noticeUserId4) ? "" : noticeUserId4.substring(0, noticeUserId4.length() - 1);
//		String[]userids1=noticeUserId.split(",");
//		String users1="";
//		for(int i=0;i<userids1.length;i++){
//			if(userids1[i]!=null&&!userids1[i].equals("")){
//				String sql1="select userids from sms_rule_userrefpost where id ='"+userids1[i]+"'";
//				List userlist1=service.getSheetAccessoriesList(sql1);
//				if(userlist1.size()>0){
//				Map map=(Map) userlist1.get(0);
//					
//				}
//			}
//		}
//		String[]userids2=noticeUserId2.split(",");
//		for(int i=0;i<userids2.length;i++){
//			
//		}
//		String[]userids3=noticeUserId3.split(",");
//		for(int i=0;i<userids1.length;i++){
//			
//		}
//		String[]userids4=noticeUserId4.split(",");
//		for(int i=0;i<userids4.length;i++){
//			
//		}


        SuperviseTaskRule t = null;
        if (id.equals("")) {//id为空,新增
            t = new SuperviseTaskRule();
        } else {
            t = superviseTaskManager.getSuperviseTaskRuleById(id);
        }

        String superviseTypeStr = "";//[IVR,SMS]
        for (int i = 0; i < superviseType.length; i++) {
            if (superviseType[i].equals("SMS")) {
                superviseTypeStr += "SMS";
            }
            if (superviseType[i].equals("IVR")) {
                superviseTypeStr += "IVR";
            }
        }

        t.setDeleted("0");
        t.setMajor(major);
        t.setMainNetSortOne(mainNetSortOne);
        t.setMainNetSortTwo(mainNetSortTwo);
        t.setMainNetSortThree(mainNetSortThree);
        t.setCity(city);
        t.setCountry(country);
        t.setListedRegulationCycle(listedRegulationCycle);
        t.setListedRegulationType(listedRegulationType);
        t.setSuperviseType(superviseTypeStr);
        t.setAcceptOverTime1(acceptOverTime1);//受理超时督办1
        t.setAcceptOverTime2(acceptOverTime2);
        t.setAcceptOverTime3(acceptOverTime3);
        t.setAcceptOverTime4(acceptOverTime4);
        t.setDealOverTime1(dealOverTime1);//处理超时督办1
        t.setDealOverTime2(dealOverTime2);
        t.setDealOverTime3(dealOverTime3);
        t.setDealOverTime4(dealOverTime4);
        t.setNoticeUser1(noticeUserId);
        t.setNoticeUser2(noticeUserId2);
        t.setNoticeUser3(noticeUserId3);
        t.setNoticeUser4(noticeUserId4);


        //唯一性约束
        this.supervisetaskRuleUnique(mapping, form, request, response);
        if (request.getAttribute("unique").equals("true")) {
            superviseTaskManager.savesupervisetaskRule(t);
        } else {
            System.out.println("不满足唯一性约束条件");
        }

        request.setAttribute("saveflag", "ok");//关闭刷新窗口
        request.setAttribute("object", t);

        return mapping.findForward("supervisetaskRuleAdd");
//		return this.supervisetaskRuleList(mapping, form, request, response);
    }

    public ActionForward supervisetaskRuleList(ActionMapping mapping, ActionForm form,
                                               HttpServletRequest request, HttpServletResponse response) {
        String major = request.getParameter("major");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String mainNetSortOne = request.getParameter("mainNetSortOne");
        String mainNetSortTwo = request.getParameter("mainNetSortTwo");
        String mainNetSortThree = request.getParameter("mainNetSortThree");
        String listedRegulationType = request.getParameter("listedRegulationType");
        String listedRegulationCycle = request.getParameter("listedRegulationCycle");

        Map maptj = new HashMap();
        maptj.put("major", major);
        maptj.put("city", city);
        maptj.put("country", country);
        maptj.put("mainNetSortOne", mainNetSortOne);
        maptj.put("mainNetSortTwo", mainNetSortTwo);
        maptj.put("mainNetSortThree", mainNetSortThree);
        maptj.put("listedRegulationType", listedRegulationType);
        maptj.put("listedRegulationCycle", listedRegulationCycle);

        Integer pageSize = ((SheetAttributes) ApplicationContextHolder
                .getInstance().getBean("SheetAttributes")).getPageSize();
        String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        Map result = superviseTaskManager.supervisetaskRuleList(new Integer(1), new Integer(-1), maptj);
//		Map result=superviseTaskManager.supervisetaskRuleList(pageIndex, pageSize, maptj);
        List resultList = (List) result.get("result");

        request.setAttribute("taskList", resultList);
        request.setAttribute("pageSize", "15");
        request.setAttribute("total", result.get("total"));
        return mapping.findForward("supervisetaskRuleList");
    }

    public ActionForward supervisetaskRuleView(ActionMapping mapping, ActionForm form,
                                               HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        SuperviseTaskRule t = superviseTaskManager.getSuperviseTaskRuleById(id);
        request.setAttribute("object", t);
        return mapping.findForward("supervisetaskRuleView");
    }

    public ActionForward deletesupervisetaskRule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = StaticMethod.null2String(request.getParameter("id"));
        String type = StaticMethod.null2String(request.getParameter("type"));//批量删除标识plshanchu
        String sql = "update Supervise_task_rule set deleted='1' where id = '" + id + "' ";
        IDownLoadSheetAccessoriesService service = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
//		int ifsuccess = service.excuteUpdate(sql);
        service.updateTasks(sql);
//		BocoLog.info(this, "update Supervise_task_rule set deleted='1' where id = "+id+",ifsuccess="+ifsuccess);

//		批量删除
        if (type.equals("plshanchu")) {
            String piids = StaticMethod.null2String(request.getParameter("piids"));
            String[] ids = piids.split(",");
            for (int i = 0; i < ids.length; i++) {
                if (ids[i] != null && !ids[i].equals("")) {
                    sql = "update Supervise_task_rule set deleted='1' where id = '" + ids[i] + "' ";
                    service.updateTasks(sql);
                }
            }
        }

        return supervisetaskRuleList(mapping, form, request, response);
    }

    public ActionForward supervisetaskRecordView(ActionMapping mapping, ActionForm form,
                                                 HttpServletRequest request, HttpServletResponse response) {
        String id = StaticMethod.null2String(request.getParameter("id"));
        SuperviseTaskRecord t = superviseTaskManager.getSuperviseTaskRecordById(id);
        request.setAttribute("object", t);

        return mapping.findForward("supervisetaskRecordView");
    }

    public ActionForward supervisetaskRecordList(ActionMapping mapping, ActionForm form,
                                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String major = request.getParameter("major");
        String mainNetSortOne = request.getParameter("mainNetSortOne");
        String mainNetSortTwo = request.getParameter("mainNetSortTwo");
        String mainNetSortThree = request.getParameter("mainNetSortThree");
        String city = request.getParameter("city");
        String listedRegulationType = request.getParameter("listedRegulationType");
        String listedRegulationCycle = request.getParameter("listedRegulationCycle");
        String phone = StaticMethod.null2String(request.getParameter("phone"));

        String exportType = StaticMethod
                .null2String(request
                        .getParameter(new org.displaytag.util.ParamEncoder(
                                "taskList")
                                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));


        Map maptj = new HashMap();
        maptj.put("startTime", startTime);
        maptj.put("endTime", endTime);
        maptj.put("major", major);
        maptj.put("mainNetSortOne", mainNetSortOne);
        maptj.put("mainNetSortTwo", mainNetSortTwo);
        maptj.put("mainNetSortThree", mainNetSortThree);
        maptj.put("listedRegulationType", listedRegulationType);
        maptj.put("listedRegulationCycle", listedRegulationCycle);
        maptj.put("city", city);
        maptj.put("phone", phone);

        Integer pageSize = ((SheetAttributes) ApplicationContextHolder
                .getInstance().getBean("SheetAttributes")).getPageSize();
        String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        Map result = superviseTaskManager.supervisetaskRecordList(pageIndex, pageSize, maptj);
        List resultList = (List) result.get("result");

//		在这里重写导出
        if (!exportType.equals("")) {
            result = superviseTaskManager.supervisetaskRecordList(pageIndex, new Integer(-1), maptj);
            resultList = (List) result.get("result");
            superviseTaskManager.exportRecordExcel(request, response, resultList);
        }

        request.setAttribute("taskList", resultList);

        request.setAttribute("pageSize", "15");
        request.setAttribute("total", result.get("total"));
        return mapping.findForward("supervisetaskRecordList");
    }

    //挂牌工单处理超时通知
    public ActionForward supervisetaskRecordAddSave(ActionMapping mapping, ActionForm form,
                                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        superviseTaskManager.supervisetaskRecordAddSaveDeal(mapping, form, request, response);

        return null;//由AJAX触发
    }

    //	挂牌工单接单超时通知
    public ActionForward supervisetaskRecordAddSaveAccept(ActionMapping mapping, ActionForm form,
                                                          HttpServletRequest request, HttpServletResponse response) throws Exception {
//		superviseTaskManager.supervisetaskRecordAddSaveAccept(mapping, form, request, response);

        return null;//由AJAX触发
    }

    //任务完成,删除记录
    public ActionForward supervisetaskRecordDoneDeal(ActionMapping mapping, ActionForm form,
                                                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        superviseTaskManager.supervisetaskRecordDone(mapping, form, request, response);

        return null;
    }

    //	接单完成,删除记录
    public ActionForward supervisetaskRecordDoneAccept(ActionMapping mapping, ActionForm form,
                                                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = StaticMethod.null2String(request.getParameter("id"));
//		ListedRegulationMain main=superviseTaskManager.getListedRegulationMainById(id);

//		IDownLoadSheetAccessoriesService service = (IDownLoadSheetAccessoriesService)ApplicationContextHolder
//													.getInstance().getBean("IDownLoadSheetAccessoriesService");
//		String sql="delete from sms_monitor where service_id='"+main.getSheetId()+"'";
//		String sql2="delete from complaint_sms where sheetid='"+main.getSheetId()+"'";
//		try{
//			service.executeProcedure(sql);
//			service.executeProcedure(sql2);
//		}catch(Exception e){
//			e.printStackTrace();
//		}


        return null;
    }

    //获得地市区县关系
    public ActionForward getPostInfo(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception {

        String mainNetSortOne = StaticMethod.null2String(request.getParameter("mainNetSortOne"));
        String mainNetSortTwo = StaticMethod.null2String(request.getParameter("mainNetSortTwo"));
        String mainNetSortThree = StaticMethod.null2String(request.getParameter("mainNetSortThree"));
        String city = StaticMethod.null2String(request.getParameter("city"));
        String country = StaticMethod.null2String(request.getParameter("country"));
        String major = StaticMethod.null2String(request.getParameter("major"));

        IDownLoadSheetAccessoriesService service = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        StringBuilder sql = new StringBuilder("select * from sms_rule_userrefpost where 1=1 ");
        /**
         * 通知人员跟三级网络分类无关
         if(!"".equals(mainNetSortOne)){
         sql.append("and mainNetSortOne='"+mainNetSortOne+"' ");
         }
         if(!"".equals(mainNetSortTwo)){
         sql.append("and mainNetSortTwo='"+mainNetSortTwo+"' ");
         }
         if(!"".equals(mainNetSortThree)){
         sql.append("and mainNetSortThree='"+mainNetSortThree+"' ");
         }
         */
        if (!"".equals(city)) {
            sql.append("and city='" + city + "' ");
        }
        if (!"".equals(country)) {
            sql.append("and (country='" + country + "' or country is null)");
        } else {
            sql.append("and country is null");
        }
        if (!major.equals("")) {
            sql.append("and major='" + major + "'");
        }


        List dataList = service.getSheetAccessoriesList(sql.toString());
        JSONArray root = new JSONArray();
        if (dataList != null && !dataList.isEmpty()) {
            JSONObject o = null;
            Map dataMap = null;
            for (int i = 0; i < dataList.size(); i++) {
                dataMap = (Map) dataList.get(i);
                o = new JSONObject();
                o.put("id", StaticMethod.nullObject2String(dataMap.get("id")));
                o.put("name", StaticMethod.nullObject2String(dataMap.get("postName")));
                root.put(o);
            }
        }
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(root.toString());

        return null;
    }

    //督办任务 看板 一级视图
    public ActionForward supervisetaskBoard(ActionMapping mapping, ActionForm form,
                                            HttpServletRequest request, HttpServletResponse response) throws Exception {
        IDownLoadSheetAccessoriesService service = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        try {


            TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                    .getSession().getAttribute("sessionform");
            String userid = sessionform.getUserid();
            System.out.println("查询督办看板一级视图");
            String sql1 = " SELECT COUNT (DISTINCT M .SHEETID) nums FROM " +
                    " (SELECT A .noticeuserid, A .noticemobile,A .*FROM " +
                    " sms_record A WHERE A .dispatchtime - SYSDATE <= 1 / 24 " +
                    " AND A .ifload = 0 AND NOTICEUSERID = 'xiongchiliang') M";
            List sqllist1 = service.getSheetAccessoriesList(sql1);//即将进入本级督办
            String count1 = "0";
            System.out.println("即将进入本级督办序列sqllist1:" + sqllist1);
            if (sqllist1 != null && sqllist1.size() > 0) {
                Map map = (Map) sqllist1.get(0);
                Object obj = map.get("nums");
                count1 = String.valueOf(obj);
            }
            String sql2commonfault = " SELECT count(DISTINCT m.SHEETID) nums FROM( " +
                    " select a.noticeuserid,a.noticemobile,a.*  from " +
                    " sms_record a where a.ifload = 1 and exists " +
                    " (select 1 from commonfault_main b where b.sheetid = a.sheetid and b.status = 0))m";
            String sql2listedregulation = " SELECT count(DISTINCT m.SHEETID) nums FROM( " +
                    " select a.noticeuserid,a.noticemobile,a.*  from " +
                    " sms_record a where a.ifload = 1 and exists " +
                    " (select 1 from listedregulation_main b where b.sheetid = a.sheetid and b.status = 0))m";
            List commonfaultlist2 = service.getSheetAccessoriesList(sql2commonfault);
            List listedregulation2 = service.getSheetAccessoriesList(sql2listedregulation);
            System.out.println("已经进入本级督办序列:" + commonfaultlist2);
            System.out.println("已经进入本级督办序列:" + listedregulation2);
            String count2 = "0";
            if (commonfaultlist2 != null && commonfaultlist2.size() > 0) {
                Map map = (Map) commonfaultlist2.get(0);
                Object obj = map.get("nums");
                count2 = String.valueOf(obj);
            }
            if (listedregulation2 != null && listedregulation2.size() > 0) {
                Map map = (Map) listedregulation2.get(0);
                count2 = String.valueOf(Integer.parseInt(count2) + Integer.parseInt(map.get("nums").toString()));
            }
            String sql3commonfault = "SELECT count( distinct m.sheetid) nums from( " +
                    " select a.noticeuserid,a.noticemobile,a.* from sms_record a where a.ifload = 1 and " +
                    " exists (select 1 from sms_record b where b.sheetid = a.sheetid " +
                    " and b.dispatchtime >a.dispatchtime and b.ifload = 1) " +
                    " and exists (select 1 from commonfault_main b where b.sheetid = a.sheetid and b.status = 0))m";
            String sql3listedregulation = "SELECT count( distinct m.sheetid) nums from( " +
                    " select a.noticeuserid,a.noticemobile,a.* from sms_record a where a.ifload = 1 and " +
                    " exists (select 1 from sms_record b where b.sheetid = a.sheetid " +
                    " and b.dispatchtime >a.dispatchtime and b.ifload = 1) " +
                    " and exists (select 1 from listedregulation_main b where b.sheetid = a.sheetid and b.status = 0))m";
            List commonfaultlist3 = service.getSheetAccessoriesList(sql3commonfault);
            List listedregulation3 = service.getSheetAccessoriesList(sql3listedregulation);
            System.out.println("已经进入上级督办序列:" + commonfaultlist3);
            System.out.println("已经进入上级督办序列:" + listedregulation3);
            String count3 = "0";
            if (commonfaultlist3 != null && commonfaultlist3.size() > 0) {
                Map map = (Map) commonfaultlist3.get(0);
                Object obj = map.get("nums");
                count3 = String.valueOf(obj);
            }
            if (listedregulation3 != null && listedregulation3.size() > 0) {
                Map map = (Map) listedregulation3.get(0);
                count3 = String.valueOf(Integer.parseInt(count3) + Integer.parseInt(map.get("nums").toString()));
            }

            request.setAttribute("count1", count1);
            request.setAttribute("count2", count2);
            request.setAttribute("count3", count3);
            request.setAttribute("userid", userid);
        } catch (Exception e) {
            System.out.println("督办看板异常");
            e.printStackTrace();
        }
        return mapping.findForward("supervisetaskBoard");
    }

    //督办任务 看板 二级视图
    public ActionForward supervisetaskBoardDetail2(ActionMapping mapping, ActionForm form,
                                                   HttpServletRequest request, HttpServletResponse response) throws Exception {
        IDownLoadSheetAccessoriesService service = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        String status = StaticMethod.null2String(request.getParameter("status"));
        String userid = StaticMethod.null2String(request.getParameter("userid"));
        List resultlist = new ArrayList();
        String sql = "";


        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userphone = sessionform.getContactMobile();

        Integer pageSize = ((SheetAttributes) ApplicationContextHolder
                .getInstance().getBean("SheetAttributes")).getPageSize();
        String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

        if (status.equals("0")) {//即将来到本级督办

            Map maptj = new HashMap();
            maptj.put("status", "0");
            Map result = superviseTaskManager.getBoardDetail2(pageIndex, pageSize, maptj);
            request.setAttribute("total", result.get("total"));
            List list = (List) result.get("result");
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {

                    Object[] objarr = (Object[]) list.get(i);
                    String sheetid = (String) objarr[0];
                    String title = (String) objarr[1];
                    if (title.contains("SuperviseTaskRecord")) {//挂牌整治工单
                        String querymain = "select sheetid,sendUserid,sendcontact,mainListedTime from listedregulation_main where sheetid='" + sheetid + "'";
                        List listmain = service.getSheetAccessoriesList(querymain);
                        System.out.println(querymain);
                        if (listmain != null && listmain.size() > 0) {
                            Map map2 = (Map) listmain.get(0);
                            Date mainListedTime = (Date) map2.get("mainListedTime");
                            long diff = new Date().getTime() - mainListedTime.getTime();
                            long day = diff / (1000 * 24 * 60 * 60);
                            long hour = diff % (1000 * 24 * 60 * 60) / (1000 * 60 * 60);
                            map2.put("costtime", day + "天" + hour + "小时");
                            map2.put("workflowType", "commonfault");
                            resultlist.add(map2);
                            System.out.println(map2);
                        }
                    } else {//故障工单
                        String querymain = "select mainFaultGenerantTime,sheetid,sendUserid,sendcontact from commonfault_main where sheetid='" + sheetid + "'";
                        List listmain = service.getSheetAccessoriesList(querymain);
                        System.out.println(querymain);
                        if (listmain != null && listmain.size() > 0) {
                            Map map2 = (Map) listmain.get(0);
                            Date mainFaultGenerantTime = (Date) map2.get("mainFaultGenerantTime");
                            long diff = new Date().getTime() - mainFaultGenerantTime.getTime();
                            long day = diff / (1000 * 24 * 60 * 60);
                            long hour = diff % (1000 * 24 * 60 * 60) / (1000 * 60 * 60);
                            map2.put("costtime", day + "天" + hour + "小时");
                            map2.put("workflowType", "commonfault");
                            resultlist.add(map2);
                            System.out.println(map2);
                        }

                    }
                    System.out.println(sheetid);
                }
            }


        } else if (status.equals("1")) {//已来到本级督办
            Map maptj = new HashMap();
            maptj.put("status", "1");
            Map result = superviseTaskManager.getBoardDetail2(pageIndex, pageSize, maptj);
            request.setAttribute("total", result.get("total"));
            List list = (List) result.get("result");
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    String sheetid = (String) list.get(0);
                    String querymain = "select mainFaultGenerantTime,sheetid,sendUserid,sendcontact from commonfault_main where sheetid='" + sheetid + "'";
                    List listmain = service.getSheetAccessoriesList(querymain);
                    System.out.println(querymain);
                    if (listmain != null && listmain.size() > 0) {
                        Map map2 = (Map) listmain.get(0);
                        Date mainFaultGenerantTime = (Date) map2.get("mainFaultGenerantTime");
                        long diff = new Date().getTime() - mainFaultGenerantTime.getTime();
                        long day = diff / (1000 * 24 * 60 * 60);
                        long hour = diff % (1000 * 24 * 60 * 60) / (1000 * 60 * 60);
                        map2.put("costtime", day + "天" + hour + "小时");
                        map2.put("workflowType", "commonfault");
                        resultlist.add(map2);
                        System.out.println(map2);
                    }
                    querymain = "select sheetid,sendUserid,sendcontact,mainListedTime from listedregulation_main where sheetid='" + sheetid + "'";
                    listmain = service.getSheetAccessoriesList(querymain);
                    System.out.println(querymain);
                    if (listmain != null && listmain.size() > 0) {
                        Map map2 = (Map) listmain.get(0);
                        Date mainListedTime = (Date) map2.get("mainListedTime");
                        long diff = new Date().getTime() - mainListedTime.getTime();
                        long day = diff / (1000 * 24 * 60 * 60);
                        long hour = diff % (1000 * 24 * 60 * 60) / (1000 * 60 * 60);
                        map2.put("costtime", day + "天" + hour + "小时");
                        map2.put("workflowType", "listedregulation");
                        resultlist.add(map2);
                        System.out.println(map2);
                    }
                }
            }

        } else if (status.equals("2")) {//已提交到上级督办
            Map maptj = new HashMap();
            maptj.put("status", "2");
            Map result = superviseTaskManager.getBoardDetail2(pageIndex, pageSize, maptj);
            request.setAttribute("total", result.get("total"));
            List list = (List) result.get("result");
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    String sheetid = (String) list.get(0);
                    String querymain = "select mainFaultGenerantTime,sheetid,sendUserid,sendcontact from commonfault_main where sheetid='" + sheetid + "'";
                    List listmain = service.getSheetAccessoriesList(querymain);
                    System.out.println(querymain);
                    if (listmain != null && listmain.size() > 0) {
                        Map map2 = (Map) listmain.get(0);
                        Date mainFaultGenerantTime = (Date) map2.get("mainFaultGenerantTime");
                        long diff = new Date().getTime() - mainFaultGenerantTime.getTime();
                        long day = diff / (1000 * 24 * 60 * 60);
                        long hour = diff % (1000 * 24 * 60 * 60) / (1000 * 60 * 60);
                        map2.put("costtime", day + "天" + hour + "小时");
                        map2.put("workflowType", "commonfault");
                        resultlist.add(map2);
                        System.out.println(map2);
                    }
                    querymain = "select sheetid,sendUserid,sendcontact,mainListedTime from listedregulation_main where sheetid='" + sheetid + "'";
                    listmain = service.getSheetAccessoriesList(querymain);
                    System.out.println(querymain);
                    if (listmain != null && listmain.size() > 0) {
                        Map map2 = (Map) listmain.get(0);
                        Date mainListedTime = (Date) map2.get("mainListedTime");
                        long diff = new Date().getTime() - mainListedTime.getTime();
                        long day = diff / (1000 * 24 * 60 * 60);
                        long hour = diff % (1000 * 24 * 60 * 60) / (1000 * 60 * 60);
                        map2.put("costtime", day + "天" + hour + "小时");
                        map2.put("workflowType", "listedregulation");
                        resultlist.add(map2);
                        System.out.println(map2);
                    }
                }
            }
        }
        System.out.println(resultlist);


        request.setAttribute("status", status);

        request.setAttribute("pageSize", pageSize);
        request.setAttribute("taskList", resultlist);
        return mapping.findForward("supervisetaskBoardDetail2");
    }

    //督办任务 看板 三级视图
    public ActionForward supervisetaskBoardDetail3(ActionMapping mapping, ActionForm form,
                                                   HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sheetid = StaticMethod.null2String(request.getParameter("sheetid"));
        sheetid = "JX-051-190513-24071";
        String workflowType = StaticMethod.null2String(request.getParameter("workflowType"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        IDownLoadSheetAccessoriesService service = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        List resultList = new ArrayList();
        JSONObject record0 = new JSONObject();
        JSONObject record1 = new JSONObject();
        JSONObject record2 = new JSONObject();
        JSONObject record3 = new JSONObject();
        JSONObject record4 = new JSONObject();
        Date date = new Date();
        String sql = "select t1.noticeusername,t1.noticeuserid,t1.noticemobile ,t2.* from sms_record t1,( " +
                " select dispatchtime,row_number() over(order by dispatchtime)rn " +
                " from sms_record where sheetid='" + sheetid + "' and ifload<>-1 group BY dispatchtime)t2 " +
                " where t1.dispatchtime=t2.dispatchtime order by t2.rn";
        if (workflowType.equals("commonfault")) {
			/*rows like blow
			 |noticeusername--用户名CN|noticeuserid--用户|noticemobile--手机|dispatchtime--转发时间|rn--督办等级|
			 */
            List querylist = service.getSheetAccessoriesList(sql);
            System.out.println("querylist:" + querylist);
            if (querylist != null && querylist.size() > 0) {
                String createusersql = "select m.*,u.username from commonfault_main m,taw_system_user u " +
                        "where m.senduserid=u.userid and sheetid='HB-051-150514-12858'";
                System.out.println("createusersql:" + createusersql);
                List list = service.getSheetAccessoriesList(createusersql);
                if (list != null && list.size() > 0) {
                    Map map = (Map) list.get(0);
                    Map map2 = (Map) querylist.get(0);
                    String userid = (String) map.get("senduserid");
                    String contact = (String) map.get("sendcontact");
                    String username = String.valueOf(map.get("username"));
                    String dispatchtime = String.valueOf(map2
                            .get("dispatchtime"));
                    String status = "";
                    String className = "";
                    if (date.after(df.parse(dispatchtime))) {
                        status = "pass";
                        className = "e-pass";
                    } else if ((date.getTime() + 1000 * 60 * 60) < df.parse(
                            dispatchtime).getTime()) {
                        status = "already";
                        className = "e-already";
                    } else {
                        status = "undo";
                        className = "e-undo";
                    }
                    record0.put("position", "责任人");
                    record0.put("person", username);
                    record0.put("time", dispatchtime);
                    record0.put("phone", contact);
                    record0.put("order", "1");
                    record0.put("statue", status);
                    record0.put("className", className);

                } else {
                    record0.put("position", "责任人");
                    record0.put("person", "");
                    record0.put("time", "");
                    record0.put("phone", "");
                    record0.put("order", "1");
                    record0.put("statue", "pass");
                    record0.put("className", "e-pass");
                }
                System.out.println(list);

                for (int i = 0; i < querylist.size(); i++) {
                    Map map = (Map) querylist.get(i);
                    String rn = String.valueOf(map.get("rn"));
                    String noticeuserid = String.valueOf(map.get("noticeusername"));
                    String noticemobile = String.valueOf(map.get("noticemobile"));
                    String dispatchtime = String.valueOf(map.get("dispatchtime"));
                    System.out.println("rn" + rn);
                    System.out.println("dispatchtime" + dispatchtime);

                    String status = "";
                    String className = "";
                    if (date.after(df.parse(dispatchtime))) {
                        status = "pass";
                        className = "e-pass";
                    } else if ((date.getTime() + 1000 * 60 * 60) < df.parse(dispatchtime).getTime()) {
                        status = "already";
                        className = "e-already";
                    } else {
                        status = "undo";
                        className = "e-undo";
                    }
                    if (rn.equals("1")) {
                        if (!record1.has("position")) {
                            record1.put("position", "一级督办");
                        }
                        if (!record1.has("time")) {
                            record1.put("time", dispatchtime);
                        }
                        if (!record1.has("statue")) {
                            record1.put("statue", status);
                        }
                        if (!record1.has("order")) {
                            record1.put("order", "2");
                        }
                        if (!record1.has("className")) {
                            record1.put("className", className);
                        }
                        record1.put("person", noticeuserid);
                        record1.put("phone", noticemobile);
                    } else if (rn.equals("2")) {
                        if (!record2.has("position")) {
                            record2.put("position", "二级督办");
                        }
                        if (!record2.has("time")) {
                            record2.put("time", dispatchtime);
                        }
                        if (!record2.has("statue")) {
                            record2.put("statue", status);
                        }
                        if (!record2.has("order")) {
                            record2.put("order", "2");
                        }
                        if (!record2.has("className")) {
                            record2.put("className", className);
                        }

                        record2.put("person", noticeuserid);
                        record2.put("phone", noticemobile);

                    } else if (rn.equals("3")) {
                        if (!record3.has("position")) {
                            record3.put("position", "三级督办");
                        }
                        if (!record3.has("time")) {
                            record3.put("time", dispatchtime);
                        }
                        if (!record3.has("statue")) {
                            record3.put("statue", status);
                        }
                        if (!record3.has("order")) {
                            record3.put("order", "4");
                        }
                        if (!record3.has("className")) {
                            record3.put("className", className);
                        }
                        record3.put("phone", noticemobile);
                        record3.put("person", noticeuserid);
                    } else if (rn.equals("4")) {
                        if (!record4.has("position")) {
                            record4.put("position", "四级督办");
                        }
                        if (!record4.has("time")) {
                            record4.put("time", dispatchtime);
                        }
                        if (!record4.has("statue")) {
                            record4.put("statue", status);
                        }
                        if (!record4.has("order")) {
                            record4.put("order", "5");
                        }
                        if (!record4.has("className")) {
                            record4.put("className", className);
                        }
                        record4.put("phone", noticemobile);
                        record4.put("person", noticeuserid);
                    }
                }
            }


        } else if (workflowType.equals("listedregulation")) {
            List querylist = service.getSheetAccessoriesList(sql);
            System.out.println("querylist:" + querylist);
            if (querylist != null && querylist.size() > 0) {
                String createusersql = "select m.*,u.username from listedregulation_main m,taw_system_user u "
                        + "where m.senduserid=u.userid and sheetid='HB-051-150514-12858'";
                System.out.println("createusersql:" + createusersql);
                List list = service.getSheetAccessoriesList(createusersql);
                if (list != null && list.size() > 0) {
                    Map map = (Map) list.get(0);
                    Map map2 = (Map) querylist.get(0);
                    String userid = (String) map.get("senduserid");
                    String contact = (String) map.get("sendcontact");
                    String username = String.valueOf(map.get("username"));
                    String dispatchtime = String.valueOf(map2
                            .get("dispatchtime"));
                    String status = "";
                    String className = "";
                    if (date.after(df.parse(dispatchtime))) {
                        status = "pass";
                        className = "e-pass";
                    } else if ((date.getTime() + 1000 * 60 * 60) < df.parse(
                            dispatchtime).getTime()) {
                        status = "already";
                        className = "e-already";
                    } else {
                        status = "undo";
                        className = "e-undo";
                    }
                    record0.put("position", "责任人");
                    record0.put("person", username);
                    record0.put("time", dispatchtime);
                    record0.put("phone", contact);
                    record0.put("order", "1");
                    record0.put("statue", status);
                    record0.put("className", className);

                } else {
                    record0.put("position", "责任人");
                    record0.put("person", "");
                    record0.put("time", "");
                    record0.put("phone", "");
                    record0.put("order", "1");
                    record0.put("statue", "pass");
                    record0.put("className", "e-pass");
                }
                System.out.println(list);

                for (int i = 0; i < querylist.size(); i++) {
                    Map map = (Map) querylist.get(i);
                    String rn = String.valueOf(map.get("rn"));
                    String noticeuserid = String.valueOf(map.get("noticeusername"));
                    String noticemobile = String.valueOf(map.get("noticemobile"));
                    String dispatchtime = String.valueOf(map.get("dispatchtime"));
                    System.out.println("rn" + rn);
                    System.out.println("dispatchtime" + dispatchtime);

                    String status = "";
                    String className = "";
                    if (date.after(df.parse(dispatchtime))) {
                        status = "pass";
                        className = "e-pass";
                    } else if ((date.getTime() + 1000 * 60 * 60) < df.parse(dispatchtime).getTime()) {
                        status = "already";
                        className = "e-already";
                    } else {
                        status = "undo";
                        className = "e-undo";
                    }

                    if (rn.equals("1")) {
                        if (!record1.has("position")) {
                            record1.put("position", "一级督办");
                        }
                        if (!record1.has("time")) {
                            record1.put("time", dispatchtime);
                        }
                        if (!record1.has("statue")) {
                            record1.put("statue", status);
                        }
                        if (!record1.has("order")) {
                            record1.put("order", "2");
                        }
                        if (!record1.has("className")) {
                            record1.put("className", className);
                        }
                        record1.put("person", noticeuserid);
                        record1.put("phone", noticemobile);
                    } else if (rn.equals("2")) {
                        if (!record2.has("position")) {
                            record2.put("position", "二级督办");
                        }
                        if (!record2.has("time")) {
                            record2.put("time", dispatchtime);
                        }
                        if (!record2.has("statue")) {
                            record2.put("statue", status);
                        }
                        if (!record2.has("order")) {
                            record2.put("order", "2");
                        }
                        if (!record2.has("className")) {
                            record2.put("className", className);
                        }

                        record2.put("person", noticeuserid);
                        record2.put("phone", noticemobile);

                    } else if (rn.equals("3")) {
                        if (!record3.has("position")) {
                            record3.put("position", "三级督办");
                        }
                        if (!record3.has("time")) {
                            record3.put("time", dispatchtime);
                        }
                        if (!record3.has("statue")) {
                            record3.put("statue", status);
                        }
                        if (!record3.has("order")) {
                            record3.put("order", "4");
                        }
                        if (!record3.has("className")) {
                            record3.put("className", className);
                        }
                        record3.put("phone", noticemobile);
                        record3.put("person", noticeuserid);
                    } else if (rn.equals("4")) {
                        if (!record4.has("position")) {
                            record4.put("position", "四级督办");
                        }
                        if (!record4.has("time")) {
                            record4.put("time", dispatchtime);
                        }
                        if (!record4.has("statue")) {
                            record4.put("statue", status);
                        }
                        if (!record4.has("order")) {
                            record4.put("order", "5");
                        }
                        if (!record4.has("className")) {
                            record4.put("className", className);
                        }
                        record4.put("phone", noticemobile);
                        record4.put("person", noticeuserid);
                    }
                }
            }
        }
        System.out.println("SuperviseTaskBoardDetail3:");
        resultList.add(record0);
        resultList.add(record1);
        resultList.add(record2);
        resultList.add(record3);
        resultList.add(record4);
        request.setAttribute("resultList", resultList);

        System.out.println("result" + resultList);
        request.setAttribute("workflowType", workflowType);
        request.setAttribute("sheetid", sheetid);
        return mapping.findForward("supervisetaskBoardDetail3");
    }

    public ActionForward supervisetaskBoardCount(ActionMapping mapping, ActionForm form,
                                                 HttpServletRequest request, HttpServletResponse response) throws Exception {

        IDownLoadSheetAccessoriesService service = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder
                .getInstance().getBean("SheetAttributes")).getPageSize();
        String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        Map maptj = new HashMap();
        Map resultmap = superviseTaskManager.BoardCountList(pageIndex, pageSize, maptj);
        String total = String.valueOf(resultmap.get("total"));
        List result = (List) resultmap.get("result");//查询出sheetid和工单类型
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List resultList = new ArrayList();
        for (int i = 0; i < result.size(); i++) {
            Object[] obj = (Object[]) result.get(i);
            String sheetid = (String) obj[0];
            String type = (String) obj[1];
            String sendtime = String.valueOf(obj[2]);
            System.out.println("sheetid:" + sheetid + ",type:" + type);
            Map map = new HashMap();
            map.put("sheetid", sheetid);
            map.put("workflowType", type);
            map.put("sendtime", sendtime);
            //查询督办级别,人员
            //|noticeusername--用户名CN|noticeuserid--用户|noticemobile--手机|dispatchtime--转发时间|rn--督办等级|
            String querylevel = "select t1.noticeusername,t1.noticeuserid,t1.noticemobile ,t2.* from sms_record t1,( \n" +
                    "		 select dispatchtime,row_number() over(order by dispatchtime)rn \n" +
                    "		 from sms_record where sheetid='JX-051-190513-24071' and ifload<>-1 group BY dispatchtime)t2 \n" +
                    "		 where t1.dispatchtime=t2.dispatchtime order by t2.rn";
            List levelList = service.getSheetAccessoriesList(querylevel);
            String status = "";
            String username = "";
            String mobile = "";
            for (int j = 0; j < levelList.size(); j++) {
                Map map2 = (Map) levelList.get(j);
                String rn = String.valueOf(map2.get("rn"));
                String noticeusername = String.valueOf(map2.get("noticeusername"));
                String noticemobile = String.valueOf(map2.get("noticemobile"));
                String dispatchtime = String.valueOf(map2.get("dispatchtime"));
                if (date.after(df.parse(dispatchtime))) {
                    status = "pass";
                } else if ((date.getTime() + 1000 * 60 * 60) < df.parse(dispatchtime).getTime()) {
                    status = "already";
                } else {
                    status = "undo";
                }
                map.put("status", status);
                if (rn.equals("1")) {
                    map.put("username1", noticeusername);
                    map.put("status1", status);
                    map.put("mobile1", noticemobile);
                } else if (rn.equals("2")) {
                    map.put("username2", noticeusername);
                    map.put("status2", status);
                    map.put("mobile2", noticemobile);
                } else if (rn.equals("3")) {
                    map.put("username3", noticeusername);
                    map.put("status3", status);
                    map.put("mobile3", noticemobile);
                } else if (rn.equals("4")) {
                    map.put("username4", noticeusername);
                    map.put("status4", status);
                    map.put("mobile4", noticemobile);
                }
            }
            resultList.add(map);
        }
        System.out.println("Supervisetask result:" + resultList);


        request.setAttribute("pageSize", pageSize);
        request.setAttribute("taskList", resultList);
        request.setAttribute("total", new Integer(total));
        return mapping.findForward("supervisetaskBoardCount");
    }

    public ActionForward supervisetaskBoardCountPerson(ActionMapping mapping, ActionForm form,
                                                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        List result = new ArrayList();
        Map map = new HashMap();
        map.put("test", "test");
        map.put("test2", "test2");
        result.add(map);
        result.add(map);

        request.setAttribute("taskList", result);
        request.setAttribute("pageSize", new Integer(15));
        request.setAttribute("total", new Integer(15));

        return mapping.findForward("supervisetaskBoardCountPerson");
    }

    public ActionForward supervisetaskBoardMainAdd(ActionMapping mapping, ActionForm form,
                                                   HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = StaticMethod.null2String(request.getParameter("sheetmainId"));
        CommonTaskMain main = superviseTaskManager.getCommonTaskById(id);
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String createtime = sdf.format(new Date());
        String workflowType = StaticMethod.null2String(request.getParameter("workflowType"));


        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userid = sessionform.getUserid();
        String deptid = String.valueOf(sessionform.getDeptid());
        request.setAttribute("month", new Integer(month));
        request.setAttribute("createtime", createtime);
        request.setAttribute("object", main);
        request.setAttribute("userid", userid);
        request.setAttribute("deptid", deptid);
        request.setAttribute("workflowType", workflowType);
        return mapping.findForward("supervisetaskBoardMainAdd");
    }

    public ActionForward supervisetaskBoardMainAddSave(ActionMapping mapping, ActionForm form,
                                                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        String month = StaticMethod.null2String(request.getParameter("month"));//月份
        String workflowType = StaticMethod.null2String(request.getParameter("workflowType"));//工单类型
        String sheetId = StaticMethod.null2String(request.getParameter("sheetId"));//工单id
        String createtime = StaticMethod.null2String(request.getParameter("createtime"));//创建时间
        String workflowStatus = StaticMethod.null2String(request.getParameter("workflowStatus"));//工单状态
        String errorSource = StaticMethod.null2String(request.getParameter("errorSource"));//故障来源
        String cityid = StaticMethod.null2String(request.getParameter("toDeptId"));//地市
        String userid = StaticMethod.null2String(request.getParameter("sendUserid"));//问题派发人
        String deptid = StaticMethod.null2String(request.getParameter("deptid"));//派发部门
        String errorDesc = StaticMethod.null2String(request.getParameter("errorDesc"));//故障描述
        String expectGoal = StaticMethod.null2String(request.getParameter("expectGoal"));//评估目标

        Map map = new HashMap();
        map.put("month", month);
        map.put("workflowType", workflowType);
        map.put("sheetId", sheetId);
        map.put("createtime", createtime);
        map.put("workflowStatus", workflowStatus);
        map.put("errorSource", errorSource);
        map.put("cityid", cityid);
        map.put("userid", userid);
        map.put("deptid", deptid);
        map.put("errorDesc", errorDesc);
        map.put("expectGoal", expectGoal);

        SuperviseTaskMainDuty t = new SuperviseTaskMainDuty();
        t.setDeleted("0");
        t.setSendtime(createtime);
        t.setWorkflowType(workflowType);
        t.setWorkflowStatus(workflowStatus);
        t.setSheetid(sheetId);
        t.setCityid(cityid);
        t.setUserid(userid);
        t.setDeptid(deptid);
        t.setErrorDesc(errorDesc);
        t.setErrorSource(errorSource);
        t.setExpectGoal(expectGoal);
        try {

            superviseTaskManager.supervisetaskMainDutySave(t);
        } catch (Exception e) {
            System.out.println("supervisetaskBoardMainAddSave Error");
            e.printStackTrace();

        }

//		模拟一条数据
        JSONObject whereadd = new JSONObject();
        whereadd.put("appkey", "localtask_ktyp4s34pjxg2dz5ewyd6iycs9nldgc2");//秘钥
        whereadd.put("method", "add");//add,find
        whereadd.put("asktype", workflowType);
        whereadd.put("asksource", errorSource);
        whereadd.put("askdesc", errorDesc);
        whereadd.put("askres", expectGoal);
        whereadd.put("receivedepart", cityid);//待办接收地市
        whereadd.put("createuser", userid);//EMIS系统用户英文名
        whereadd.put("xuhao", sheetId);//工单sheetID

        System.out.println(whereadd);
        System.out.println(whereadd.toString());

        superviseTaskManager.newBulletin1(whereadd.toString());

        request.setAttribute("saveflag", "ok");
        return this.supervisetaskBoardMainAdd(mapping, form, request, response);
    }


}
