package com.boco.eoms.duty.webapp.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.common.util.Pager;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.duty.dao.TawRmAssignSubDAO;
import com.boco.eoms.duty.dao.TawRmAssignworkDAO;
import com.boco.eoms.duty.dao.TawRmDutySheetDao;
import com.boco.eoms.duty.dao.TawRmSysteminfoDAO;
import com.boco.eoms.duty.model.TawRmAssignSub;
import com.boco.eoms.duty.model.TawRmAssignwork;
import com.boco.eoms.duty.model.TawRmDoneTask;
import com.boco.eoms.duty.model.TawRmUnDoneTask;

public class TawRmDutySheetAction extends BaseAction {

    private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool
            .getInstance();

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) {
        ActionForward myforward = null;
        String myaction = mapping.getParameter();
        if ("show".equalsIgnoreCase(myaction)) {
            myforward = performView(mapping, form, request, response);
        } else if ("showAll".equalsIgnoreCase(myaction)) {
            myforward = performViewAll(mapping, form, request, response);
        }
        return myforward;
    }

    /**
     * 相关工单（填写，修改，删除值班记录）
     * ID：EOMS-FANGYONGFENG-20090922
     * 开发时间：2009-09-22
     * 邮箱：fangyongfeng@boco.com.cn
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    private ActionForward performView(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String roomid = saveSessionBeanForm.getRoomId();
        TawRmSysteminfoDAO dao = new TawRmSysteminfoDAO(ds);
        TawRmAssignworkDAO tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);
        Vector vector = null;
        TawRmAssignwork tr = null;
        String workSerial = request.getParameter("workSerial");
        if ("".equals(workSerial) || workSerial == null) {
            workSerial = saveSessionBeanForm.getWorkSerial();
        }
        try {
            tr = tawRmAssignworkDAO.retrieve(Integer.parseInt(workSerial));
            vector = dao.getVectordutysheet(tr.getRoomId());//Integer.parseInt(roomid)
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ArrayList list = new ArrayList();
        ArrayList listname = new ArrayList();
        String userId = saveSessionBeanForm.getUserid();
        String deptId = saveSessionBeanForm.getDeptid();
        TawRmDutySheetDao sheetdao = new TawRmDutySheetDao(ds);
        for (int i = 0; i < vector.size(); i++) {
            ArrayList list1 = (ArrayList) sheetdao.getshowListbydutysheet(userId, deptId, vector.elementAt(i).toString(), tr.getStarttimeDefined(), tr.getEndtimeDefined());
            listname.add(vector.elementAt(i).toString());
            list.add(list1);
        }
        request.setAttribute("list", list);
        request.setAttribute("listname", listname);
        return mapping.findForward("show");
    }

    /**
     * 分页显示查询值班记录
     * ID：EOMS-FANGYONGFENG-20090922
     * 开发时间：2009-09-22
     * 邮箱：fangyongfeng@boco.com.cn
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    private ActionForward performViewAll(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        String workSerial = request.getParameter("workSerial");
        TawRmSysteminfoDAO dao = new TawRmSysteminfoDAO(ds);
        TawRmAssignworkDAO tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);
        Vector vector = null;
        TawRmAssignwork tr = null;
        ArrayList undoList = new ArrayList();
        ArrayList doneList = new ArrayList();
        String undoPageOffset = null;
        String donePageOffset = null;
        String url = null;
        String undoPagerHeader = null;
        String donePagerHeader = null;
        try {
            //分页显示
            int length = 15;//每页显示记录数
            TawRmDutySheetDao sheetdao = new TawRmDutySheetDao(ds);
            String condition = "where workserial='" + workSerial + "'";
            int undoListSize = sheetdao.getSize("taw_rm_undonetask", condition);
            int doneListSize = sheetdao.getSize("taw_rm_donetask", condition);
            url = request.getContextPath() + "/duty" + mapping.getPath() + ".do";
            String para = "workSerial=" + workSerial + "&diff=undo";

//			未处理
            int undooffset;
            undoPageOffset = request.getParameter("pager.offset");
            if (undoPageOffset == null || undoPageOffset.equals("")) {
                undooffset = 0;
            } else {
                undooffset = Integer.parseInt(undoPageOffset);
            }
            undoList = (ArrayList) sheetdao.getUndoTaskByWorkserial(undooffset, length, workSerial);
            undoPagerHeader = Pager.generate(undooffset, undoListSize, length, url, para);

//			已处理
            String para1 = "workSerial=" + workSerial + "&diff=done";
            int doneoffset;
            donePageOffset = request.getParameter("pager.offset1");
            if (donePageOffset == null || donePageOffset.equals("")) {
                doneoffset = 0;
            } else {
                doneoffset = Integer.parseInt(donePageOffset);
            }
            doneList = (ArrayList) sheetdao.getDoneTaskByWorkserial(doneoffset, length, workSerial);
            //donePagerHeader = Pager.generateForTab(doneoffset, doneListSize, length, url,para1);

            //更改type的显示方式
            tr = tawRmAssignworkDAO.retrieve(Integer.parseInt(workSerial));
            vector = dao.getVectordutysheet(tr.getRoomId());
            for (int i = 0; i < vector.size(); i++) {
                //获得字典值
                String temp = (String) DictMgrLocator.getDictService()
                        .itemId2description(Util.constituteDictId("dict-sheet", "sheet"), vector.elementAt(i).toString());
                for (int j = 0; j < undoList.size(); j++) {
                    TawRmUnDoneTask undotask = (TawRmUnDoneTask) undoList.get(j);
                    if (temp.equals(undotask.getType())) {
                        undotask.setType(vector.elementAt(i).toString());
                    }
                }
                for (int j = 0; j < doneList.size(); j++) {
                    TawRmDoneTask donetask = (TawRmDoneTask) doneList.get(j);
                    if (temp.equals(donetask.getType())) {
                        donetask.setType(vector.elementAt(i).toString());
                    }
                }
            }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        request.setAttribute("workSerial", workSerial);
        request.setAttribute("undoList", undoList);
        request.setAttribute("doneList", doneList);
        request.setAttribute("undoPagerHeader", undoPagerHeader);
        request.setAttribute("donePagerHeader", donePagerHeader);
        return mapping.findForward("show");
    }
}
