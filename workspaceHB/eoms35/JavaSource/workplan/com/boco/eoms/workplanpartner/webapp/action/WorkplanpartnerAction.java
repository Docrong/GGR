package com.boco.eoms.workplanpartner.webapp.action;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.workplan.mgr.ITawwpStatMgr;
import com.boco.eoms.workplan.util.TawwpUtil;
import com.boco.eoms.workplan.vo.TawwpStatVO;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;

/**
 * <p>
 * Title:workplanpartner
 * </p>
 * <p>
 * Description:workplanpartner
 * </p>
 * <p>
 * Thu Jun 25 08:55:55 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() xiangyibiao
 * @moudle.getVersion() 3.5
 * 
 */

public final class WorkplanpartnerAction extends BaseAction {

	/**
	 * 未指定方法时默认调用的方法
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return tree(mapping, form, request, response);
	}

	/**
	 * workplanpartner树页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("tree");
	}

	/**
	 * 生成workplanpartner树JSON数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String getNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = StaticMethod.null2String(request.getParameter("node"));
		JSONArray jsonRoot = new JSONArray();
		ITawSystemDictTypeManager tawWsDictTypeDAO = (ITawSystemDictTypeManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDictTypeManager");
		List list = tawWsDictTypeDAO.getDictSonsByDictid(nodeId);

		for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {

			TawSystemDictType tawSystemDictType = (TawSystemDictType) nodeIter
					.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", tawSystemDictType.getDictId());
			// TODO 添加节点名称
			jitem.put("text", tawSystemDictType.getDictName());
			// 设置右键菜单
			jitem.put("allowChild", true);
			jitem.put("allowEdit", true);
			jitem.put("allowDelete", true);
			// 设置左键点击可触发action
			jitem.put("allowClick", true);
			// 设置是否为叶子节点
			boolean leafFlag = true;

			List tlist = tawWsDictTypeDAO.getDictSonsByDictid(tawSystemDictType
					.getDictId());
			if (!tlist.isEmpty()) {
				leafFlag = false;
			}
			if ("九个分公司".equals(tawSystemDictType.getDictName())) {
				leafFlag = true;
			}
			jitem.put("leaf", leafFlag);
			// TODO 设置鼠标悬浮提示
			jitem.put("qtip", "");
			jsonRoot.put(jitem);
		}
		JSONUtil.print(response, jsonRoot.toString());
		return null;
	}

	/**
	 * 按人员统计代维作业
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward personstat(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String monthflag = (String) request.getSession().getAttribute(
				"monthflag");
		String yearflag = (String) request.getSession()
				.getAttribute("yearflag");
		//List namelist = (List) request.getSession().getAttribute("namelist");
		String deptid = (String) request.getParameter("deptid");
		String workplanid = (String) request.getParameter("workplanid");
		//int i = Integer.parseInt(itemp);
		//String deptname = (String) namelist.get(i);

		ITawwpStatMgr tawwpStatMgr = (ITawwpStatMgr) getBean("tawwpStatMgr");
		ITawSystemDeptManager tawSystemDeptManager = (ITawSystemDeptManager) getBean("ItawSystemDeptManager");
		//if (tawSystemDeptManager.getDeptnameIsExist(deptname, "0")) {
			Enumeration enumeration = null;
			Object object = null;
			TawwpStatVO tawwpStatVO = null;
			Hashtable hashtable = new Hashtable();
			List completelist = new ArrayList();
			TawSystemDept tawSystemDept = tawSystemDeptManager.getDeptinfobydeptid(deptid, "0");
			String deptname = tawSystemDept.getDeptName();
			//String deptId = tawSystemDept.getDeptId();
			try {
				hashtable = tawwpStatMgr.statMonthPlanUserByDept(deptid,workplanid,
						yearflag, monthflag);
			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			enumeration = hashtable.keys();
			// 循环增加统计数据
			while (enumeration.hasMoreElements()) {
				object = enumeration.nextElement();
				tawwpStatVO = (TawwpStatVO) hashtable.get(object);
				completelist.add(tawwpStatVO);
				/*
				 * int allcount = tawwpStatVO.getAllCount(); int outTimeCount =
				 * tawwpStatVO.getAlloutTimeCount(); int inTimeCount =
				 * tawwpStatVO.getAllinTimeCount(); String allComplete =
				 * String.valueOf((outTimeCount+inTimeCount)/allcount); String
				 * allinTimeComplete = String.valueOf(inTimeCount/allcount);
				 */
			}
			request.setAttribute("completelist", completelist);
			request.setAttribute("deptname", deptname);

		return mapping.findForward("personStat");
	}

	/**
	 * 按部门统计代维作业
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deptStat(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = StaticMethod.null2String(request.getParameter("node"));
		if ("104010101".equals(nodeId)) {
			String yearFlag = request.getParameter("yearflag");
			String monthFlag = request.getParameter("monthflag");
			int dayNow = StaticMethod.null2int(TawwpUtil
					.getCurrentDateTime("dd"));
			String yesrNow = TawwpUtil.getCurrentDateTime("yyyy");
			String monthNow = TawwpUtil.getCurrentDateTime("MM");
			if (yearFlag == null) {
				yearFlag = TawwpUtil.getCurrentDateTime("yyyy");
				monthFlag = TawwpUtil.getCurrentDateTime("MM");
			} else {
				if (StaticMethod.null2int(monthFlag) < 10) {
					monthFlag = "0" + monthFlag;
				}
			}

			ITawSystemDictTypeManager tawWsDictTypeDAO = (ITawSystemDictTypeManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDictTypeManager");
			List listt = tawWsDictTypeDAO.getDictSonsByDictid(nodeId);
			List namelist = new ArrayList();
			List idlist = new ArrayList();
			List completelist = new ArrayList();
			//List intimecompleteList = new ArrayList();
			//List alltimecompleteList = new ArrayList();
			for (Iterator nodeIter = listt.iterator(); nodeIter.hasNext();) {
				TawSystemDictType tawSystemDictType = (TawSystemDictType) nodeIter
						.next();
				String deptname = tawSystemDictType.getDictName();
				namelist.add(deptname);
				idlist.add(tawSystemDictType.getDictId());
				
				// //////////////////////
				ITawwpStatMgr tawwpStatMgr = (ITawwpStatMgr) getBean("tawwpStatMgr");
				ITawSystemDeptManager tawSystemDeptManager = (ITawSystemDeptManager) getBean("ItawSystemDeptManager");
				if (tawSystemDeptManager.getDeptnameIsExist(deptname, "0")) {
					Enumeration enumeration = null;
					Object object = null;
					TawwpStatVO tawwpStatVO = null;
					Hashtable hashtable = new Hashtable();
					
					TawSystemDept tawSystemDept = tawSystemDeptManager
							.getDeptinfoBydeptname(deptname, "0");
					String deptId = tawSystemDept.getDeptId();
					try {
						hashtable = tawwpStatMgr.statMonthPlanDept("", deptId,
								yearFlag, monthFlag);
					} catch (RuntimeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					enumeration = hashtable.keys();
					float allCompletef = 0;
					//float inTimeCompletef = 0;
					
					enumeration = hashtable.keys();
					// 循环增加统计数据
					while (enumeration.hasMoreElements()) {
						object = enumeration.nextElement();
						tawwpStatVO = (TawwpStatVO) hashtable.get(object);
						completelist.add(tawwpStatVO);
						/*
						  int allcount = tawwpStatVO.getAllCount(); 
						  int outTimeCount = tawwpStatVO.getAlloutTimeCount(); 
						  int inTimeCount = tawwpStatVO.getAllinTimeCount(); 
						  float allcountf = Float.parseFloat(allcount+"");
						  float outTimeCountf = Float.parseFloat(outTimeCount+"");
						  float inTimeCountf = Float.parseFloat(inTimeCount+"");
						  allCompletef += ((outTimeCountf+inTimeCountf)/allcountf);
						  inTimeCompletef += (inTimeCountf/allcountf);
						  String allComplete = String.valueOf(((outTimeCountf+inTimeCountf)/allcountf)*100)+"%";
						  String inTimeComplete = String.valueOf((inTimeCountf/allcountf)*100)+"%";					
						  intimecompleteList.add(inTimeComplete);
						  alltimecompleteList.add(allComplete);*/
						 
						  // /////////////////////////////////
					}
				}

			}
			
			try {/*
				request.setAttribute("alltimecompleteList", alltimecompleteList);
				request.setAttribute("intimecompleteList", intimecompleteList);*/
				request.setAttribute("completelist", completelist);
				request.setAttribute("namelist", namelist);
				request.setAttribute("idlist", idlist);
				request.setAttribute("yearflag", yearFlag);
				request.setAttribute("monthflag", monthFlag);
				request.setAttribute("dayNow", Integer.toString(dayNow));
				request.setAttribute("yearNow", yesrNow);
				request.setAttribute("monthNow", monthNow);
				request.setAttribute("nodeId", nodeId);
				return mapping.findForward("deptStat");
				// return mapping.findForward("success");
			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("failure");
			}
		} else {
			return null;
		}

	}

	/**
	 * 保存workplanpartner
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WorkplanpartnerMgr workplanpartnerMgr = (WorkplanpartnerMgr) getBean("workplanpartnerMgr");
		//WorkplanpartnerForm workplanpartnerForm = (WorkplanpartnerForm) form;
		//Workplanpartner workplanpartner = (Workplanpartner) convert(workplanpartnerForm);
		workplanpartnerMgr.saveWorkplanpartner(workplanpartner);
		return mapping.findForward("success");
	}*/

	/**
	 * 删除workplanpartner
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 
	public ActionForward remove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = StaticMethod
				.null2String(request.getParameter("nodeId"));
		WorkplanpartnerMgr workplanpartnerMgr = (WorkplanpartnerMgr) getBean("workplanpartnerMgr");
		workplanpartnerMgr.removeWorkplanpartnerByNodeId(nodeId);
		return mapping.findForward("success");
	}*/

	/**
	 * 初始化节点
	 * 
	 * @return
	 */
	private JSONArray initNodes(Hashtable hashtable) {

		Hashtable sysInfHashtable = hashtable;

		String sysTypeId = null;

		JSONArray jsonRoot = new JSONArray();

		Enumeration sysTypeEnumeration = sysInfHashtable.keys();
		while (sysTypeEnumeration.hasMoreElements()) {
			sysTypeId = (String) sysTypeEnumeration.nextElement();
			String sysType = (String) sysInfHashtable.get(sysTypeId);

			JSONObject sysTypeObj = new JSONObject();
			sysTypeObj.put("id", sysTypeId);
			sysTypeObj.put("text", sysType);
			sysTypeObj.put(UIConstants.JSON_NODETYPE, "sysType");
			sysTypeObj.put("allowChild", true);
			sysTypeObj.put("allowEdit", true);
			sysTypeObj.put("allowDelete", true);
			sysTypeObj.put("leaf", false);
			sysTypeObj.put("iconCls", "folder");
			if (sysType != null) {
				jsonRoot.put(sysTypeObj);
			}
		}

		return jsonRoot;
	}

	/**
	 * 获得子结得到网元类型
	 * 
	 * @param hashtable
	 * @param sysTypeId
	 * @return
	 */
	private JSONArray getSubNodes(Hashtable hashtable, String sysTypeId) {
		JSONArray jsonRoot = new JSONArray();

		Enumeration netTypeEnumeration = null;
		String netTypeId = null;
		Hashtable netHashtable = (Hashtable) hashtable.get(sysTypeId);

		netTypeEnumeration = netHashtable.keys();

		while (netTypeEnumeration.hasMoreElements()) {
			netTypeId = (String) netTypeEnumeration.nextElement();
			String netType = (String) netHashtable.get(netTypeId);

			JSONObject netTypeObj = new JSONObject();
			netTypeObj.put("id", netTypeId);
			netTypeObj.put("text", netType);
			netTypeObj.put(UIConstants.JSON_NODETYPE, "netType");
			netTypeObj.put("allowChild", true);
			netTypeObj.put("allowEdit", true);
			netTypeObj.put("allowDelete", true);
			netTypeObj.put("leaf", true);
			netTypeObj.put("iconCls", "file");
			if (netType != null) {
				jsonRoot.put(netTypeObj);
			}
		}
		return jsonRoot;
	}
}