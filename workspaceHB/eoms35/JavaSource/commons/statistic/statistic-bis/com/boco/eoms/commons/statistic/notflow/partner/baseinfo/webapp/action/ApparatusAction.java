package com.boco.eoms.commons.statistic.notflow.partner.baseinfo.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.commons.statistic.base.util.StatUtil;
import com.boco.eoms.commons.statistic.base.webapp.action.IStatMethod;
import com.boco.eoms.commons.statistic.base.webapp.action.StatAction;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.partner.baseinfo.mgr.AreaDeptTreeMgr;
import com.boco.eoms.partner.baseinfo.mgr.PartnerUserAndAreaMgr;
import com.boco.eoms.partner.baseinfo.model.AreaDeptTree;
import com.boco.eoms.partner.baseinfo.model.PartnerUserAndArea;

public class ApparatusAction extends StatAction {
    public ActionForward changeDep(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
        List deptListId = areaDeptTreeMgr.getNextLevelAreaDeptTrees(id);
        List deptListName = new ArrayList();
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
		/*for(int i=0;i<deptListId.size();i++){
			String tempId = ((AreaDeptTree)(deptListId.get(i))).getNodeId();
			deptListName.add(areaDeptTreeMgr.id2Name(tempId));
		}*/
        StringBuffer d_list = new StringBuffer();
        for (int i = 0; i < deptListId.size(); i++) {
            deptListName.add(areaDeptTreeMgr.id2Name(((AreaDeptTree) (deptListId.get(i))).getNodeId()));
            d_list.append("," + ((AreaDeptTree) (deptListId.get(i))).getNodeId());
            d_list.append("," + deptListName.get(i));

        }

        String aaa = "1,	请选择" + d_list.toString();
        response.setContentType("text/html; charset=GBK");
        response.getWriter().print(aaa);
        response.getWriter().flush();
        return null;
    }

    public ActionForward showStatisticPage(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request, HttpServletResponse response) throws Exception {

        String findForward = request.getParameter("findForward");
        String excelConfigURL = request.getParameter("excelConfigURL");
        String findListForward = request.getParameter("findListForward");
        String state = request.getParameter("state");
        StatUtil.validataParameter(findForward, excelConfigURL, findListForward);
        IStatMethod statMethod = (IStatMethod) getBean(mapping.getAttribute());
        statMethod.showStatisticPage(mapping, form, request, response);

        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String user_deptId = sessionform.getDeptid();

        //2009-04-16 加权限控制，
        PartnerUserAndAreaMgr partnerUserAndAreaMgr = (PartnerUserAndAreaMgr) getBean("partnerUserAndAreaMgr");
        String operuserid = sessionform.getUserid();
        PartnerUserAndArea object = partnerUserAndAreaMgr.getObjectByUserId(operuserid);

        AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
        if (object != null) {
            List listName = new ArrayList();
            List list_id = new ArrayList();
            if (object.getAreaType().equals("province")) {
                //根据省得到省下地市
                String provinceName = "";//参数应该根据登录用户信息得到，待定方法
                List listId = areaDeptTreeMgr.getAreaByProvince(provinceName);
                for (int i = 0; i < listId.size(); i++) {
                    String tempId = ((AreaDeptTree) (listId.get(i))).getNodeId();
                    listName.add(areaDeptTreeMgr.id2Name(tempId));
                    list_id.add(tempId);
                }
                request.setAttribute("listName", listName);
                request.setAttribute("listId", list_id);
                request.setAttribute("user_deptId", user_deptId);
                request.setAttribute("stateFromAction", state);
            } else {
                String[] areas = object.getAreaNames().split(",");
                String areaNames = "";
                for (int i = 0; i < areas.length; i++) {
                    areaNames += ("'" + areas[i] + "'" + ",");
                }
                areaNames = areaNames.substring(0, areaNames.length() - 1);
                List listId = areaDeptTreeMgr.getNodesByAreas(areaNames);
                for (int i = 0; i < listId.size(); i++) {
                    String tempId = ((AreaDeptTree) (listId.get(i))).getNodeId();
                    listName.add(areaDeptTreeMgr.id2Name(tempId));
                    list_id.add(tempId);
                }
                request.setAttribute("listName", listName);
                request.setAttribute("listId", list_id);
                request.setAttribute("user_deptId", user_deptId);
                request.setAttribute("stateFromAction", state);
            }
        } else return mapping.findForward("noAssign");

//		List listId = areaDeptTreeMgr.getAreaDeptTreesByType("area");
//		List listName = new ArrayList();
//		List list_id=new ArrayList();
//		for(int i=0;i<listId.size();i++){
//			String tempId = ((AreaDeptTree)(listId.get(i))).getNodeId();
//			listName.add(areaDeptTreeMgr.id2Name(tempId));
//			list_id.add(tempId);
//		}
//		request.setAttribute("listName", listName);
//		request.setAttribute("listId", list_id);
//		request.setAttribute("user_deptId", user_deptId);
//		request.setAttribute("stateFromAction", state);


        return mapping.findForward(findForward);

    }
}
