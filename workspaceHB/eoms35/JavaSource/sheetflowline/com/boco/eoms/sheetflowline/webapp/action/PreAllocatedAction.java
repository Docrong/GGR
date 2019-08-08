package com.boco.eoms.sheetflowline.webapp.action;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheetflowline.mgr.IPreAllocatedMgr;
import com.boco.eoms.sheetflowline.model.PreAllocated;


public class PreAllocatedAction extends BaseAction {


    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        request.setAttribute("jsonString1", "[]");
        request.setAttribute("jsonString2", "[]");
        return mapping.findForward("add");
    }

    public ActionForward saveObject(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = TawSystemSessionForm.getUserid();
        //获取页面参数
        Map map = request.getParameterMap();
        PreAllocated object = new PreAllocated();
        SheetBeanUtils.populateMap2Bean(object, map);
        object.setCreateTime(new Date());
        object.setCreateUser(userId);

        IPreAllocatedMgr mgr = (IPreAllocatedMgr) getBean("preAllocatedMgr");
        mgr.saveObject(object);//保存对象
        return mapping.findForward("success");
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        String ids = StaticMethod.nullObject2String(request.getParameter("ids"));//获取所check的对象
        String inids = "";
        IPreAllocatedMgr mgr = (IPreAllocatedMgr) getBean("preAllocatedMgr");
        if (!"".equals(ids)) {
            String[] idArray = ids.split(",");
            for (int i = 0; i < idArray.length; i++) {
                String id = idArray[i];
                inids = "'" + id + "'," + inids;
            }
            inids = inids.substring(0, inids.length() - 1);
            String sql = "delete from PreAllocated where id in (" + inids + ")";
            mgr.executeHsql(sql);
        }
        return mapping.findForward("success");
    }

    public ActionForward showList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        // 获取每页显示条数
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder
                .getInstance().getBean("SheetAttributes")).getPageSize();
        // 当前页数
        String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

        String exportType = StaticMethod
                .null2String(request
                        .getParameter(new org.displaytag.util.ParamEncoder(
                                "taskList")
                                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));
        if (!exportType.equals("")) {
            pageSize = new Integer(-1);
        }
        IPreAllocatedMgr mgr = (IPreAllocatedMgr) getBean("preAllocatedMgr");
        Map map = mgr.listPreAllocated(pageIndex, pageSize);
        int total = ((Integer) map.get("taskTotal")).intValue();
        List taskOvertimeList = (List) map.get("taskList");
        request.setAttribute("taskList", taskOvertimeList);
        request.setAttribute("total", new Integer(total));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }

    public ActionForward queryList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        // 获取每页显示条数
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder
                .getInstance().getBean("SheetAttributes")).getPageSize();
        // 当前页数
        String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

        String exportType = StaticMethod
                .null2String(request
                        .getParameter(new org.displaytag.util.ParamEncoder(
                                "taskList")
                                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));
        if (!exportType.equals("")) {
            pageSize = new Integer(-1);
        }
        String dutyLeaderName = request.getParameter("dutyLeaderName");
        String dutyUserName = request.getParameter("dutyUserName");
        Map map = request.getParameterMap();
        PreAllocated object = new PreAllocated();
        SheetBeanUtils.populateMap2Bean(object, map);
        Map paramMap = SheetBeanUtils.bean2Map(object);
        IPreAllocatedMgr mgr = (IPreAllocatedMgr) getBean("preAllocatedMgr");
        Map returnmap = mgr.listPreAllocated(paramMap, pageIndex, pageSize);
        List taskList = (List) returnmap.get("taskList");
        Integer taskTotal = (Integer) returnmap.get("taskTotal");
        JSONArray jsonRoot1 = new JSONArray();
        JSONArray jsonRoot2 = new JSONArray();
        if (object.getDutyUserId() != null) {
            JSONObject jitem1 = new JSONObject();
            jitem1.put("id", object.getDutyUserId());
            jitem1.put("name", dutyUserName);
            jsonRoot1.put(jitem1);
        }
        if (object.getDutyUserId() != null) {
            JSONObject jitem2 = new JSONObject();
            jitem2.put("id", object.getDutyLeader());
            jitem2.put("name", dutyLeaderName);
            jsonRoot2.put(jitem2);
        }
        String jsonString1 = jsonRoot1.toString();//值班人员
        String jsonString2 = jsonRoot2.toString();//值班长
        request.setAttribute("jsonString1", jsonString1);
        request.setAttribute("jsonString2", jsonString2);
        request.setAttribute("taskList", taskList);
        request.setAttribute("total", taskTotal);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("object", object);
        return mapping.findForward("list");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        String id = StaticMethod.nullObject2String(request.getParameter("id"));
        IPreAllocatedMgr mgr = (IPreAllocatedMgr) getBean("preAllocatedMgr");
        PreAllocated object = mgr.getPreAllocated(id);
        ITawSystemUserManager userManager = (ITawSystemUserManager) getBean("itawSystemUserManager");
        TawSystemUser user = userManager.getUserByuserid(object.getDutyUserId());
        TawSystemUser leaderuser = userManager.getUserByuserid(object.getDutyLeader());
        String dutyUserName = "";
        String leaderUserName = "";
        if (user != null) {
            dutyUserName = user.getUsername();
        }
        if (leaderuser != null) {
            leaderUserName = leaderuser.getUsername();
        }
        JSONArray jsonRoot1 = new JSONArray();
        JSONArray jsonRoot2 = new JSONArray();
        JSONObject jitem1 = new JSONObject();
        jitem1.put("id", object.getDutyUserId());
        jitem1.put("name", dutyUserName);
        jsonRoot1.put(jitem1);
        JSONObject jitem2 = new JSONObject();
        jitem2.put("id", object.getDutyLeader());
        jitem2.put("name", leaderUserName);
        jsonRoot2.put(jitem2);
        String jsonString1 = jsonRoot1.toString();//值班人员
        String jsonString2 = jsonRoot2.toString();//值班长
        request.setAttribute("jsonString1", jsonString1);
        request.setAttribute("jsonString2", jsonString2);
        request.setAttribute("object", object);
        return mapping.findForward("add");
    }

    public ActionForward query(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        return mapping.findForward("query");
    }

    public void matchPreAllocateRule(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        String mainNetSortOne = StaticMethod.nullObject2String(request.getParameter("mainNetSortOne"));
        String mainNetSortTwo = StaticMethod.nullObject2String(request.getParameter("mainNetSortTwo"));
        String mainNetSortThree = StaticMethod.nullObject2String(request.getParameter("mainNetSortThree"));
        String mainFaultResponseLevel = StaticMethod.nullObject2String(request.getParameter("mainFaultResponseLevel"));
        String mainEquipmentFactory = StaticMethod.nullObject2String(request.getParameter("mainEquipmentFactory"));
        IPreAllocatedMgr mgr = (IPreAllocatedMgr) getBean("preAllocatedMgr");
        List list = mgr.search(mainNetSortOne, mainNetSortTwo, mainNetSortThree, mainEquipmentFactory, mainFaultResponseLevel, StaticMethod.getCurrentDateTime());
        if (list != null && list.size() > 0) {//查出符合条件预分配的人员列表
            JSONObject jitem = new JSONObject();
            for (int i = 0; i < list.size(); i++) {
                PreAllocated object = (PreAllocated) list.get(i);
                if (object.getCount() >= 0 && object.getAlreadyCount() > 0 && object.getAlreadyCount() < object.getCount()) {//查询有空余空间的人员，如果有人已经被分配过，先可一个人用，
                    jitem.put("text", object.getDutyUserId());
                    JSONUtil.print(response, jitem.toString());
                    object.setAlreadyCount(object.getAlreadyCount() + 1);
                    mgr.updateObject(object);
                    break;
                } else {//都为0或者都达到上限的情况
                    jitem.put("text", object.getDutyUserId());
                    JSONUtil.print(response, jitem.toString());
                    if (object.getAlreadyCount() != 0) {
                        String hsql = "update PreAllocated set alreadyCount=0 where netTypeOne='" + mainNetSortOne + "' and netTypeTwo='" + mainNetSortTwo + "' and (netTypeThree='" + mainNetSortThree + "' or netTypeThree is null) and faultResponseLevel='" + mainFaultResponseLevel + "' and vendor='" + mainEquipmentFactory + "'";
                        mgr.executeHsql(hsql); //将达到上限的调整为0
                    }
                    object.setAlreadyCount(1);
                    mgr.updateObject(object);
                    break;

                }
            }

        } else {
            List list1 = mgr.search("", "", "", "", "", StaticMethod.getCurrentDateTime());
            if (list1 != null && list1.size() > 0) {
                PreAllocated object1 = (PreAllocated) list1.get(0);
                JSONObject jitem1 = new JSONObject();
                jitem1.put("text", object1.getDutyUserId());
                JSONUtil.print(response, jitem1.toString());
            }
        }
    }

    public ActionForward showImportPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        return mapping.findForward("import");
    }

    public ActionForward preAllocateImportSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                               HttpServletResponse response) throws Exception {
//		 获取当前用户的session中的信息
        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = TawSystemSessionForm.getUserid();
        String userName = TawSystemSessionForm.getUsername();
        String deptName = TawSystemSessionForm.getDeptname();
        try {
            //使用工具类
            TawExcelImportUtil importUtil = new TawExcelImportUtil();
            //将文件上传到相应位置，并返回上传路径
            File newExcelFile = importUtil.pushExcelFile(request, userName, deptName);
            // 将Excel里面的数据导入到数据库
            List errorList = importUtil.processExcel(newExcelFile, 0, 0, importUtil.getSheetType(), importUtil.getColseSwitch(), userId);
            if (errorList.size() > 0) {
                request.setAttribute("err", errorList.get(0));
                return mapping.findForward("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("success");

    }

    public void preSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
        Map map = request.getParameterMap();
        PreAllocated object = new PreAllocated();
        SheetBeanUtils.populateMap2Bean(object, map);
        IPreAllocatedMgr mgr = (IPreAllocatedMgr) getBean("preAllocatedMgr");
        String hsql = "from PreAllocated where netTypeOne='" + object.getNetTypeOne() + "' and netTypeTwo='" + object.getNetTypeTwo() + "'";
        List list = mgr.getLists(hsql);
        String text = "";
        if (list != null && list.size() > 0) {
            PreAllocated preallocate = (PreAllocated) list.get(0);
            String netTypeThree = preallocate.getNetTypeThree();
            if ((netTypeThree == null && !"".equals(object.getNetTypeThree()))) {
                text = "该网元类型下的三级分类应该为空";

            }
            if (netTypeThree != null && "".equals(object.getNetTypeThree())) {
                text = "该网元类型下的三级分类应该不为空";
            }

        }
        if (!"".equals(text)) {
            JSONObject jitem = new JSONObject();
            jitem.put("text", text);
            JSONUtil.print(response, jitem.toString());
        } else {
            JSONUtil.print(response, null);
        }
    }

}
