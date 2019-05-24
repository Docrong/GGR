package com.boco.eoms.commons.voiceMessage.webapp.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.hongxun.dao.TawCommonHongXunDao;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.voiceMessage.bo.TawHieApplyBO;
import com.boco.eoms.commons.voiceMessage.bo.TawHieDefBO;
import com.boco.eoms.commons.voiceMessage.model.TawConMem;
import com.boco.eoms.commons.voiceMessage.webapp.form.TawHieApplyForm;

public class TawHieApplyAction extends BaseAction{
	
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward myforward = null;
		String myaction = mapping.getParameter();

		if ("ADDUSER".equalsIgnoreCase(myaction)) { 
			
			myforward = performAddUser(mapping, actionForm, request, response);
		} else if ("ADDUSERDO".equalsIgnoreCase(myaction)) {
			myforward = performAddUserDo(mapping, actionForm, request, response);
		} else if ("LISTTUSER".equalsIgnoreCase(myaction)) {
			myforward = performListTUser(mapping, actionForm, request, response);
		} else if ("QUERYUSER".equalsIgnoreCase(myaction)) {
			myforward = mapping.findForward("success");
		}
		else if ("QUERYUSERDO".equalsIgnoreCase(myaction)) {
			myforward = performListTUser(mapping, actionForm, request, response);
		}
		else if ("REDUPDATE".equalsIgnoreCase(myaction)) {
			myforward = performRedUpdate(mapping, actionForm, request, response);
		}
		else if ("REDUPDATEDO".equalsIgnoreCase(myaction)) {
			myforward = performRedUpDo(mapping, actionForm, request, response);
		}else if ("CONF".equalsIgnoreCase(myaction)) {
			myforward = performConference(mapping, actionForm, request, response);
		}else if ("ADDCONF".equalsIgnoreCase(myaction)) {
			myforward = performAddConf(mapping, actionForm, request, response);
		}else if ("ADDMEMS".equalsIgnoreCase(myaction)) {
			myforward = performAddMems(mapping, actionForm, request, response);
		} else if ("LISTCONF".equalsIgnoreCase(myaction)) {
			myforward = performListConf(mapping, actionForm, request, response);
		} else if ("LISTCONFDO".equalsIgnoreCase(myaction)) {
			myforward = performListConfDo(mapping, actionForm, request, response);
		} else if ("LISTCONMEM".equalsIgnoreCase(myaction)) {
			myforward = performListConfInfo(mapping, actionForm, request, response);
		}
		
		
		
		
		return myforward;

	}
	private ActionForward performAddUser(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawHieApplyForm form = (TawHieApplyForm) actionForm;
		int userId = 0;
		try {
			TawHieDefBO tawHieDefBO = new TawHieDefBO();
			userId = tawHieDefBO.getUserId();
			
			form.setUserId(String.valueOf(userId));
		}
		catch  (Exception e) {
			BocoLog.error(this, "添加人员界面出错 " + e.getMessage());
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	private ActionForward performAddUserDo(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawHieApplyForm form = (TawHieApplyForm) actionForm;
		try {			
			TawHieApplyBO tawHieApplyBO = new TawHieApplyBO();
			tawHieApplyBO.addUser(form);
		} 
		catch (Exception e) {
			BocoLog.error(this, "添加界面出错 " + e.getMessage());
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}
	
	private ActionForward performListTUser(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawHieApplyForm form = (TawHieApplyForm) actionForm;

		List redList = new ArrayList();
		// String deptListAll="";

		try {
			TawHieDefBO tawHieDefBO = new TawHieDefBO();
			redList = tawHieDefBO.getAllUser(form);
			// tawInformationDao.insertRedUser(com_name,xiaozu_name,name,tel,zhize,remark);

			request.setAttribute("REDLIST", redList);


		} catch (Exception e) {
			BocoLog.error(this, "查询出错 " + e.getMessage());
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	private ActionForward performRedUpdate(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		String flag = request.getParameter("flag");

		List redList = new ArrayList();
		try {
			TawHieDefBO tawHieDefBO = new TawHieDefBO();
			redList = tawHieDefBO.getOneUser(id);
			Iterator iter = redList.iterator();
			Map map = (Map) iter.next();

			request.setAttribute("id", id);
			request.setAttribute("username", map.get("username").toString()
					.trim());
			request.setAttribute("usertel", map.get("usertel")
					.toString().trim());
			request.setAttribute("user_type", map.get("user_type").toString().trim());
			request.setAttribute("user_code", map.get("user_code").toString().trim());
			request.setAttribute("flag", flag);

		} catch (Exception e) {
			BocoLog.error(this, "更新界面出错 " + e.getMessage());
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}
	
	private ActionForward performRedUpDo(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		String userName = request.getParameter("userName");
		String userTel = request.getParameter("userTel");
		String userType = request.getParameter("userType");
		String userCode = request.getParameter("userCode");

		String flag = request.getParameter("flag");

		// List redList = new ArrayList();
		// String deptListAll="";

		try {
			TawHieDefBO tawHieDefBO = new TawHieDefBO();
			if (flag.equals("0")) {
				tawHieDefBO.redDel(id);
			} else if (flag.equals("1")) {
				tawHieDefBO.redDoUpdate(id, userName, userTel, userType, userCode);
			}

		} catch (Exception e) {
			BocoLog.error(this, "更新或者删除出错 " + e.getMessage());
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}
	
	private ActionForward performConference(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {	
		try {	
			actionForm=null;
			TawHieDefBO tawHieBO = new TawHieDefBO();
            ArrayList confOrganizerList = (ArrayList)tawHieBO.getOrgDefSel();
			
			request.setAttribute("CONFORGLIST", confOrganizerList);
			//request.getSession().removeAttribute("tawHieApplyForm");
		} catch (Exception e) {
			BocoLog.error(this, "会议添加出错 " + e.getMessage());
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	private ActionForward performAddConf(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawHieApplyForm form = (TawHieApplyForm) actionForm;
		try {
			
			TawHieApplyBO tawHieApplyBO = new TawHieApplyBO();
			int confNo = 0;
			confNo = tawHieApplyBO.addConference(form);
			//System.out.println("confNo = " + confNo);
			
			form.setConfNo(confNo);		
		} catch (Exception e) {
			BocoLog.error(this, "会议添加出错 " + e.getMessage());
			return mapping.findForward("failure");
		}

		return performAllMems(mapping, actionForm, request, response);
	}
	
	private ActionForward performAllMems(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		
		try {
			

			TawHieApplyBO tawHieApplyBO = new TawHieApplyBO();
			
			ArrayList allMems = (ArrayList)tawHieApplyBO.getAllMems();
			
		
			request.setAttribute("ALLMEMS", allMems);
			
		} catch (Exception e) {
			BocoLog.error(this, "会议添加出错 " + e.getMessage());
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}
	
	private ActionForward performAddMems(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawHieApplyForm form = (TawHieApplyForm) actionForm;
		ArrayList list = new ArrayList();
		int confNo = form.getConfNo();
		
		String[] sUserIds = request.getParameterValues("seledId");
		String[] sMemberNames = request.getParameterValues("memberName");
		String[] sMemPhones = request.getParameterValues("memPhone");
		String[] sMemTypes = request.getParameterValues("memType");
		String[] sJoinModes = request.getParameterValues("joinMode");
		
		int[] userIds = new int[sUserIds.length];
		int[] memTypes = new int[sMemTypes.length];
		int[] joinModes = new int[sJoinModes.length];
		
		try {
			for (int i = 0; i < sUserIds.length; i++) {
				userIds[i] = Integer.parseInt(sUserIds[i]);
				joinModes[i] = Integer.parseInt(sJoinModes[i]);
				TawConMem tawConMem = new TawConMem();
				tawConMem.setUserId(userIds[i]);
				tawConMem.setUserName(sMemberNames[i]);
				tawConMem.setUserTel(sMemPhones[i]);
				tawConMem.setUserType(sMemTypes[i]);
				
				switch (joinModes[i]) {
				case 0:
					tawConMem.setStrJoinMode("加入");
					break;
				case 1:
					tawConMem.setStrJoinMode("监听");
					break;
				case 2:
					tawConMem.setStrJoinMode("卡拉OK");
					break;
				default:
					break;
				}
				
				list.add(tawConMem);			
			}
			
			TawHieApplyBO tawHieApplyBO = new TawHieApplyBO();
			tawHieApplyBO.addConfMems(confNo, userIds, sMemberNames, sMemPhones, memTypes, joinModes);
	
		} catch (Exception e) {
			BocoLog.error(this, "会议添加出错 " + e.getMessage());
			return mapping.findForward("failure");
		}

		return performListConfInfo(mapping, actionForm, request, response, list);
	}
	private ActionForward performListConfInfo(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, ArrayList list) {
		TawHieApplyForm form = (TawHieApplyForm) actionForm;
		try {
			

			TawHieApplyBO tawHieApplyBO = new TawHieApplyBO();
			
			List tawCI = tawHieApplyBO.getConfInfo(form);
			
			
			request.setAttribute("TAWCI", tawCI);
			request.setAttribute("TAWMEM", list);
			
		} catch (Exception e) {
			BocoLog.error(this, "会议添加出错 " + e.getMessage());
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}
	
	private ActionForward performListConf(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			TawHieDefBO tawHieBO = new TawHieDefBO();
			ArrayList confIdList = (ArrayList)tawHieBO.getConfDefSel();
			
			request.setAttribute("CONFIDLIST", confIdList);
			request.getSession().removeAttribute("tawHieApplyForm");
		} catch (Exception e) {
			BocoLog.error(this, "会议添加出错 " + e.getMessage());
			return mapping.findForward("failure");
		}
		
		return mapping.findForward("success");
	}
	
	private ActionForward performListConfDo(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawHieApplyForm form = (TawHieApplyForm) actionForm;
		try {
			
			TawHieApplyBO tawHieApplyBO = new TawHieApplyBO();
			
			List tawConf = tawHieApplyBO.getConfInfoAll(form);
			
			
			request.setAttribute("TAWCONF", tawConf);
			
		} catch (Exception e) {
			BocoLog.error(this, "会议查询出错 " + e.getMessage());
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}
	
	private ActionForward performListConfInfo(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		//TawHieApplyForm form = (TawHieApplyForm) actionForm;
		try {
			

			TawHieApplyBO tawHieApplyBO = new TawHieApplyBO();
			int confNo = Integer.parseInt(request.getParameter("confNo"));
			
			List tawCI = tawHieApplyBO.getConfInfo(confNo);
			
			List list = tawHieApplyBO.getConfMem(confNo);
			
			request.setAttribute("TAWCI", tawCI);
			request.setAttribute("TAWMEM", list);
			
		} catch (Exception e) {
			BocoLog.error(this, "会议人员明细出错 " + e.getMessage());
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}
}
