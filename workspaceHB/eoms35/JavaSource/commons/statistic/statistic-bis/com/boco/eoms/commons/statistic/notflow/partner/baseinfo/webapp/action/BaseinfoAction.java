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

public class BaseinfoAction extends StatAction{
	
	public ActionForward showStatisticPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		String findForward = request.getParameter("findForward");
		String excelConfigURL =request.getParameter("excelConfigURL");
		String findListForward =request.getParameter("findListForward");
		StatUtil.validataParameter(findForward,excelConfigURL,findListForward);
		
		IStatMethod statMethod = (IStatMethod) getBean(mapping.getAttribute());
		statMethod.showStatisticPage(mapping, form, request, response);
		
		String type = request.getParameter("type");
		request.setAttribute("type", type);
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		PartnerUserAndAreaMgr partnerUserAndAreaMgr = (PartnerUserAndAreaMgr) getBean("partnerUserAndAreaMgr");
		AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
        String operuserid = sessionform.getUserid();
        PartnerUserAndArea object = partnerUserAndAreaMgr.getObjectByUserId(operuserid);        
        
        List areaList = new ArrayList();
		if(type!=null&&type.equals("area")){//按地市统计
			if(object!=null){
			if(object.getAreaType().equals("province")){
		    //根据省得到省下地市
				String provinceName = "";//参数应该根据登录用户信息得到，待定方法
				areaList = areaDeptTreeMgr.getAreaByProvince(provinceName);
				request.setAttribute("areaList", areaList);
			}
			else {
				String[] areas = object.getAreaNames().split(",");
				String areaNames = "";
				for(int i = 0;i<areas.length;i++){
					areaNames += ("'"+areas[i]+"'"+",");
				}
				areaNames = areaNames.substring(0, areaNames.length()-1);
				areaList = areaDeptTreeMgr.getNodesByAreas(areaNames);
				request.setAttribute("areaList", areaList);
			}

		}
		else return mapping.findForward("noAssign");
		}
		else if(type!=null&&type.equals("dept")){//按合作伙伴名称统计 省公司用户
			if(object!=null){
				if(object.getAreaType().equals("province")){
					String provinceName = "";//参数应该根据登录用户信息得到，待定方法
					List deptList = areaDeptTreeMgr.getDeptByDeptName(provinceName);
					request.setAttribute("deptList", deptList);
				}
				else return mapping.findForward("noAssign");
			}
			else return mapping.findForward("noAssign");
		}

        return mapping.findForward(findForward);
		
	}
	
	//统计合作伙伴时showDeptStatisticPage
	public ActionForward showDeptStatisticPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		String findForward = request.getParameter("findForward");
		String excelConfigURL =request.getParameter("excelConfigURL");
		String findListForward =request.getParameter("findListForward");
		StatUtil.validataParameter(findForward,excelConfigURL,findListForward);
		
		IStatMethod statMethod = (IStatMethod) getBean(mapping.getAttribute());
		statMethod.showStatisticPage(mapping, form, request, response);
		
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		PartnerUserAndAreaMgr partnerUserAndAreaMgr = (PartnerUserAndAreaMgr) getBean("partnerUserAndAreaMgr");
		AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
        String operuserid = sessionform.getUserid();
        PartnerUserAndArea object = partnerUserAndAreaMgr.getObjectByUserId(operuserid);
		if(object!=null){
			if(object.getAreaType().equals("province")){
			    //根据省得到省下地市
					String provinceName = "";//参数应该根据登录用户信息得到，待定方法
					List areaList = areaDeptTreeMgr.getAreaByProvince(provinceName);
					request.setAttribute("areaList", areaList);
			}
			else {
				String[] areas = object.getAreaNames().split(",");
				String areaNames = "";
				for(int i = 0;i<areas.length;i++){
					areaNames += ("'"+areas[i]+"'"+",");
				}
				areaNames = areaNames.substring(0, areaNames.length()-1);
				List areaList = areaDeptTreeMgr.getNodesByAreas(areaNames);
				request.setAttribute("areaList", areaList);
			}
		}
		else return mapping.findForward("noAssign");
	    //根据省得到省下地市
//		String provinceName = "";//参数应该根据登录用户信息得到，待定方法
//		List areaList = areaDeptTreeMgr.getAreaByProvince(provinceName);
//		request.setAttribute("areaList", areaList);
		
        return mapping.findForward(findForward);
		
	}
	public ActionForward changeDep(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
		List deptListId = areaDeptTreeMgr.getNextLevelAreaDeptTrees(id);
		List deptListName = new ArrayList();
		for(int i=0;i<deptListId.size();i++){
			String tempId = ((AreaDeptTree)(deptListId.get(i))).getNodeId();
			deptListName.add(areaDeptTreeMgr.id2Name(tempId));
		}
		StringBuffer d_list = new StringBuffer();
       for(int i=0;i<deptListId.size();i++){
    	   deptListName.add(areaDeptTreeMgr.id2Name(((AreaDeptTree)(deptListId.get(i))).getNodeId()));
    	   d_list.append(	"," +((AreaDeptTree)(deptListId.get(i))).getNodeId() );
    	   d_list.append(	"," +deptListName.get(i) );
    	   
       }
       
       String aaa = d_list.toString();
       aaa=aaa.substring(1, aaa.length());
		response.setContentType("text/html; charset=GBK");
		response.getWriter().print(aaa);
		response.getWriter().flush();
		return null;
	}

}
