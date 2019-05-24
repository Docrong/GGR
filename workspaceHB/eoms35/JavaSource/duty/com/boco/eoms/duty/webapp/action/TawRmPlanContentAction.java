
package com.boco.eoms.duty.webapp.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.duty.bo.TawRmAssignworkBO;
import com.boco.eoms.duty.dao.TawRmAssignworkDAO;
import com.boco.eoms.duty.model.TawRmAssignwork;
import com.boco.eoms.duty.model.TawRmPlanContent;
import com.boco.eoms.duty.service.ITawRmPlanContentManager;
import com.boco.eoms.duty.util.DutyConstacts;
import com.boco.eoms.duty.webapp.form.TawRmPlanContentForm;
import com.boco.eoms.workbench.memo.util.MemoPage;
import com.boco.eoms.workbench.netdisk.webapp.util.FilePathProcessor;
import com.boco.eoms.workplan.mgr.ITawwpExecuteMgr;
import com.boco.eoms.workplan.model.TawwpExecuteContent;

import com.boco.eoms.common.util.Pager;
import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivRegion;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;

/**
 * Action class to handle CRUD on a TawRmPlanContent object
 * @struts.action name="tawRmPlanContentForm" path="/tawRmPlanContents" scope="request"
 *  validate="false" parameter="method" input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * 
 * @struts.action-forward name="main" 
 *  path="/WEB-INF/pages/tawRmPlanContent/tawRmPlanContentTree.jsp" contextRelative="true"
 *
 * @struts.action-forward name="edit" 
 *  path="/WEB-INF/pages/tawRmPlanContent/tawRmPlanContentForm.jsp" contextRelative="true"
 *
 * @struts.action-forward name="list" 
 *  path="/WEB-INF/pages/tawRmPlanContent/tawRmPlanContentList.jsp" contextRelative="true"
 */
public final class TawRmPlanContentAction extends BaseAction {
	// 定义页数长度
	private static int PAGE_LENGTH = 15;
	private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool.getInstance();
	/**
	 * 生成树JSON数据
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
		String nodeId = FilePathProcessor.recoverPath(request
				.getParameter("node"));
		
		JSONArray jsonRoot = new JSONArray();
		if ("-1".equals(nodeId)) { // 初始化三个节点
			jsonRoot = initNodes(request);
		} 
		/**else {
			String nodeType = request.getParameter("nodetype");z
			if ("1".equals(nodeType)) {
				//jsonRoot = getMyFolderNodes(nodeId);
			}
		}*/
		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null;
	}
	
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
         //return mapping.findForward("search");
           return null;
    }
     public ActionForward main(ActionMapping mapping, ActionForm form,
     	HttpServletRequest request,HttpServletResponse response)
    	throws Exception {
         return mapping.findForward("workLogMain");
    }
 	/**
	 * 根据父节点的id得到�?有子节点的JSON数据
	 */
	public void xGetChildNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = request.getParameter("node");

		ITawRmPlanContentManager mgr = (ITawRmPlanContentManager) getBean("ItawRmPlanContentManager");
		JSONArray json = mgr.xGetChildNodes(nodeId);

		JSONUtil.print(response, json.toString());
	}

    /**
	 * ajax保存
	 */
    public void xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawRmPlanContentForm tawRmPlanContentForm = (TawRmPlanContentForm) form;

		ITawRmPlanContentManager mgr = (ITawRmPlanContentManager) getBean("ItawRmPlanContentManager");
		TawRmPlanContent tawRmPlanContent = (TawRmPlanContent) convert(tawRmPlanContentForm);
		mgr.saveTawRmPlanContent(tawRmPlanContent);
		// JSONUtil.print(response, "操作成功");
	}
    /**
	 * 根据模块或功能的编码，删除该对象
	 */
    public void xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawRmPlanContentForm tawRmPlanContentForm = (TawRmPlanContentForm) form;

        ITawRmPlanContentManager mgr = (ITawRmPlanContentManager) getBean("ItawRmPlanContentManager");
		mgr.removeTawRmPlanContent(tawRmPlanContentForm.getId());

	}
    /**
	 * ajax请求修改某节点的详细信息
	 */
    public String xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawRmPlanContentForm tawRmPlanContentForm = (TawRmPlanContentForm) form;

		if (tawRmPlanContentForm.getId() != null) {
			ITawRmPlanContentManager mgr = (ITawRmPlanContentManager) getBean("ItawRmPlanContentManager");
			TawRmPlanContent tawRmPlanContent = (TawRmPlanContent) convert(tawRmPlanContentForm);

			mgr.saveTawRmPlanContent(tawRmPlanContent);
		   //mgr.updateTawRmPlanContent(tawRmPlanContent);
		}

		return null;
	}

     /**
	 * ajax请求获取某节点的详细信息
	 */
	public void xget(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String _strId = request.getParameter("id");
		ITawRmPlanContentManager mgr = (ITawRmPlanContentManager) getBean("ItawRmPlanContentManager");
		TawRmPlanContent tawRmPlanContent = mgr.getTawRmPlanContent(_strId);

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(tawRmPlanContent);

		JSONUtil.print(response, jsonRoot.toString());
	}

	/**
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private JSONArray initNodes(HttpServletRequest request) {
		MessageResources mr = this.getResources(request);
		String str1 = mr.getMessage("tawRmdutyRecord.workplan");
		JSONArray jsonRoot = new JSONArray();
		/*JSONObject workplan = new JSONObject();
		workplan.put("id", "1");
		workplan.put("text", str1);
		workplan.put("nodetype", "1");
		workplan.put("allowWorkplan", true);
		workplan.put("allowWorkplanQuery", true);
		workplan.put("allowGdRecord", false);
		workplan.put("allowGdRecordQuery", false);
		workplan.put("allowDutyRecord", false);
		workplan.put("allowDutyRecordQuery", false);
		workplan.put("allowSfRecord", false);
		workplan.put("allowSfRecordQuery", false);
		workplan.put("allowPersonCheckin", false);
		workplan.put("allowPersonCheckinQuery", false);
		workplan.put("allowWpCheckin", false);
		workplan.put("allowWpCheckinQuery", false);
		workplan.put("allowRelief", false);
		workplan.put("allowReliefQuery", false);
		workplan.put("allowLogUniteQuery", false);
		workplan.put("allowLogUnite", false);
		workplan.put("leaf", true);
		workplan.put("iconCls", "file");
		JSONObject gdRecord = new JSONObject();
		String str2 = mr.getMessage("tawRmdutyRecord.worksheet");
		gdRecord.put("id", "2");
		gdRecord.put("text", str2);
		gdRecord.put("nodetype", "2");
		gdRecord.put("allowWorkplan", false);
		gdRecord.put("allowWorkplanQuery", false);
		gdRecord.put("allowGdRecord", true);
		gdRecord.put("allowGdRecordQuery", true);
		gdRecord.put("allowDutyRecord", false);
		gdRecord.put("allowDutyRecordQuery", false);
		gdRecord.put("allowSfRecord", false);
		gdRecord.put("allowSfRecordQuery", false);
		gdRecord.put("allowPersonCheckin", false);
		gdRecord.put("allowPersonCheckinQuery", false);
		gdRecord.put("allowWpCheckin", false);
		gdRecord.put("allowWpCheckinQuery", false);
		gdRecord.put("allowRelief", false);
		gdRecord.put("allowReliefQuery", false);
		gdRecord.put("allowLogUniteQuery", false);
		gdRecord.put("allowLogUnite", false);
		gdRecord.put("leaf", true);
		gdRecord.put("iconCls", "file");*/
		JSONObject dutyRecord = new JSONObject();
		String str3 = mr.getMessage("tawRmdutyRecord.dutynote");
		dutyRecord.put("id", "3");
		dutyRecord.put("text", str3);
		dutyRecord.put("nodetype", "3");
		dutyRecord.put("allowWorkplan", false);
		dutyRecord.put("allowWorkplanQuery", false);
		dutyRecord.put("allowGdRecord", false);
		dutyRecord.put("allowGdRecordQuery", false);
		dutyRecord.put("allowDutyRecord", true);
		dutyRecord.put("allowDutyRecordQuery", true);
		dutyRecord.put("allowSfRecord", false);
		dutyRecord.put("allowSfRecordQuery", false);
		dutyRecord.put("allowPersonCheckin", false);
		dutyRecord.put("allowPersonCheckinQuery", false);
		dutyRecord.put("allowWpCheckin", false);
		dutyRecord.put("allowWpCheckinQuery", false);
		dutyRecord.put("allowRelief", false);
		dutyRecord.put("allowReliefQuery", false);
		dutyRecord.put("allowLogUniteQuery", false);
		dutyRecord.put("allowLogUnite", false);
		dutyRecord.put("leaf", true);
		dutyRecord.put("iconCls", "file");
		JSONObject sfRecord = new JSONObject();
		String str4 = mr.getMessage("tawRmdutyRecord.context");
		sfRecord.put("id", "4");
		sfRecord.put("text", str4);
		sfRecord.put("nodetype", "4");
		sfRecord.put("allowWorkplan", false);
		sfRecord.put("allowWorkplanQuery", false);
		sfRecord.put("allowGdRecord", false);
		sfRecord.put("allowGdRecordQuery", false);
		sfRecord.put("allowDutyRecord", false);
		sfRecord.put("allowDutyRecordQuery", false);
		sfRecord.put("allowSfRecord", true);
		sfRecord.put("allowSfRecordQuery", true);
		sfRecord.put("allowPersonCheckin", false);
		sfRecord.put("allowPersonCheckinQuery", false);
		sfRecord.put("allowWpCheckin", false);
		sfRecord.put("allowWpCheckinQuery", false);
		sfRecord.put("allowRelief", false);
		sfRecord.put("allowReliefQuery", false);
		sfRecord.put("allowLogUniteQuery", false);
		sfRecord.put("allowLogUnite", false);
		sfRecord.put("leaf", true);
		sfRecord.put("iconCls", "file");
		JSONObject personCheckin = new JSONObject();
		String str5 = mr.getMessage("tawRmdutyRecord.userRecord");
		personCheckin.put("id", "5");
		personCheckin.put("text", str5);
		personCheckin.put("nodetype", "5");
		personCheckin.put("allowWorkplan", false);
		personCheckin.put("allowWorkplanQuery", false);
		personCheckin.put("allowGdRecord", false);
		personCheckin.put("allowGdRecordQuery", false);
		personCheckin.put("allowDutyRecord", false);
		personCheckin.put("allowDutyRecordQuery", false);
		personCheckin.put("allowSfRecord", false);
		personCheckin.put("allowSfRecordQuery", false);
		personCheckin.put("allowPersonCheckin", true);
		personCheckin.put("allowPersonCheckinQuery", true);
		personCheckin.put("allowPersonCheckinStat", true);
		personCheckin.put("allowWpCheckin", false);
		personCheckin.put("allowWpCheckinQuery", false);
		personCheckin.put("allowRelief", false);
		personCheckin.put("allowReliefQuery", false);
		personCheckin.put("allowLogUniteQuery", false);
		personCheckin.put("allowLogUnite", false);
		personCheckin.put("leaf", true);
		personCheckin.put("iconCls", "file");
		JSONObject wpCheckin = new JSONObject();
		String str6 = mr.getMessage("tawRmdutyRecord.loadRoecord");
		wpCheckin.put("id", "6");
		wpCheckin.put("text", str6);
		
		wpCheckin.put("nodetype", "6");
		wpCheckin.put("allowWorkplan", false);
		wpCheckin.put("allowWorkplanQuery", false);
		wpCheckin.put("allowGdRecord", false);
		wpCheckin.put("allowGdRecordQuery", false);
		wpCheckin.put("allowDutyRecord", false);
		wpCheckin.put("allowDutyRecordQuery", false);
		wpCheckin.put("allowSfRecord", false);
		wpCheckin.put("allowSfRecordQuery", false);
		wpCheckin.put("allowPersonCheckin", false);
		wpCheckin.put("allowPersonCheckinQuery", false);
		wpCheckin.put("allowWpCheckin", true);
		wpCheckin.put("allowWpCheckinQuery", true);
		wpCheckin.put("allowRelief", false);
		wpCheckin.put("allowReliefQuery", false);
		wpCheckin.put("allowLogUniteQuery", false);
		wpCheckin.put("allowLogUnite", false);
		wpCheckin.put("leaf", true);
		wpCheckin.put("iconCls", "file");
		JSONObject relief  = new JSONObject();
		String str7 = mr.getMessage("tawRmdutyRecord.changeduty");
		relief .put("id", "7");
		relief .put("text", str7);
		relief .put("nodetype", "7");
		relief.put("allowWorkplan", false);
		relief.put("allowWorkplanQuery", false);
		relief.put("allowGdRecord", false);
		relief.put("allowGdRecordQuery", false);
		relief.put("allowDutyRecord", false);
		relief.put("allowDutyRecordQuery", false);
		relief.put("allowSfRecord", false);
		relief.put("allowSfRecordQuery", false);
		relief.put("allowPersonCheckin", false);
		relief.put("allowPersonCheckinQuery", false);
		relief.put("allowWpCheckin", false);
		relief.put("allowWpCheckinQuery", false);
		relief.put("allowRelief", true);
		relief.put("allowReliefQuery", true);
		relief.put("allowLogUniteQuery", false);
		relief.put("allowLogUnite", false);
		relief .put("leaf", true);
		relief .put("iconCls", "file");
		JSONObject logUnite = new JSONObject();
		logUnite.put("id", "8");
		String str8 = mr.getMessage("tawRmdutyRecord.record");
		logUnite.put("text", str8);
		logUnite.put("nodetype", "8");
		logUnite.put("allowWorkplan", false);
		logUnite.put("allowWorkplanQuery", false);
		logUnite.put("allowGdRecord", false);
		logUnite.put("allowGdRecordQuery", false);
		logUnite.put("allowDutyRecord", false);
		logUnite.put("allowDutyRecordQuery", false);
		logUnite.put("allowSfRecord", false);
		logUnite.put("allowSfRecordQuery", false);
		logUnite.put("allowPersonCheckin", false);
		logUnite.put("allowPersonCheckinQuery", false);
		logUnite.put("allowWpCheckin", false);
		logUnite.put("allowWpCheckinQuery", false);
		logUnite.put("allowRelief", false);
		logUnite.put("allowReliefQuery", false);
		logUnite.put("allowLogUniteQuery", true);
		logUnite.put("allowLogUnite", true);
		logUnite.put("leaf", true);
		logUnite.put("iconCls", "file");
		//jsonRoot.put(workplan);
		//jsonRoot.put(gdRecord);
		jsonRoot.put(dutyRecord);
		jsonRoot.put(sfRecord);
		jsonRoot.put(personCheckin);
		jsonRoot.put(wpCheckin);
		jsonRoot.put(relief);
		jsonRoot.put(logUnite);
		return jsonRoot;
	}
	
    public ActionForward edit(ActionMapping mapping, ActionForm form,
         	HttpServletRequest request,HttpServletResponse response)
        	throws Exception {
		int offset=0;
		int length = PAGE_LENGTH;
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		TawRmAssignworkDAO tawRmAssignworkDAO=new TawRmAssignworkDAO(ds);
		try {
			HttpSession session=request.getSession();
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
			String workSerial=sessionform.getWorkSerial();
			if (sessionform == null) {
				return mapping.findForward("timeout");
			}

			// 判断是否值班
			if (workSerial.equals("0")) {
				return mapping.findForward("notonduty1");
			}
			String roomId=sessionform.getRoomId();
			String start_time="";
			String end_time="";
			TawRmAssignwork tawRmAssignwork=tawRmAssignworkDAO.retrieve(Integer.parseInt(workSerial));
			if(tawRmAssignwork!=null){
				start_time=tawRmAssignwork.getStarttimeDefined();
				end_time=tawRmAssignwork.getEndtimeDefined();
			}
			List list=tawwpExecuteMgr.getExecuteContentList(start_time, end_time, roomId);
			List newList=new ArrayList();
			for(int i=0;i<list.size();i++){
				TawwpExecuteContent tawwpExecuteContent=(TawwpExecuteContent)list.get(i);
				ITawRmPlanContentManager planContentMgr=(ITawRmPlanContentManager)getBean("ITawRmPlanContentManager");
				String whereStr=" where executecontentId='"+tawwpExecuteContent.getId()+"'";
				Map map=planContentMgr.getTawRmPlanContents(new Integer(offset),new Integer(length),whereStr);
				List planContentList=(List) map.get("result");
				if(planContentList==null||planContentList.size()==0){
					newList.add(tawwpExecuteContent);
				}
			}
			if(newList==null||newList.size()==0){
				return mapping.findForward("warning");
			}
			session.setAttribute("executeContentlist", newList);
			Iterator listIt = newList.iterator();
			request.setAttribute("listIt", listIt);
		}catch (Exception e) {
			e.printStackTrace();
		}
      return mapping.findForward("edit");
    }
    
    public ActionForward view(ActionMapping mapping, ActionForm form,
         	HttpServletRequest request,HttpServletResponse response)
        	throws Exception {
		try {
			ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
			String excutecontentId = StaticMethod.nullObject2String(request.getParameter("excutecontentId"));
			TawwpExecuteContent tawwpExecuteContent=tawwpExecuteMgr.loadExecuteContent(excutecontentId);
			request.setAttribute("tawwpExecuteContent", tawwpExecuteContent);
		}catch (Exception e) {
			e.printStackTrace();
		}
        return mapping.findForward("view");
    }
    
    public ActionForward viewContent(ActionMapping mapping, ActionForm form,
         	HttpServletRequest request,HttpServletResponse response)
        	throws Exception {
		try {
			// if an id is passed in, look up the user - otherwise
			// don't do anything - user is doing an add
			HttpSession session=request.getSession();
			String id = StaticMethod.nullObject2String(request.getParameter("id"));
			TawRmPlanContentForm tawRmPlanContentForm = (TawRmPlanContentForm) form;
			String monthplanName=StaticMethod.nullObject2String(session.getAttribute("myMonthplanName"));
			String roomId=StaticMethod.nullObject2String(session.getAttribute("myRoomId"));
			String startTime=StaticMethod.nullObject2String(session.getAttribute("myStartTime"));
			String endTime=StaticMethod.nullObject2String(session.getAttribute("myEndTime"));
			String month=StaticMethod.nullObject2String(session.getAttribute("myMonth"));
			tawRmPlanContentForm.setMonthplanName(monthplanName);
			tawRmPlanContentForm.setRoomId(roomId);
			tawRmPlanContentForm.setStartTime(startTime);
			tawRmPlanContentForm.setEndTime(endTime);
			tawRmPlanContentForm.setMonth(month);
			session.removeAttribute("myMonthplanName");
			session.removeAttribute("myRoomId");
			session.removeAttribute("myStartTime");
			session.removeAttribute("myEndTime");
			session.removeAttribute("myMonth");
			//String str = request.getParameter("folderPath");
			ITawRmPlanContentManager mgr=(ITawRmPlanContentManager)getBean("ITawRmPlanContentManager");
			TawRmPlanContent tawRmPlanContent=mgr.getTawRmPlanContent(id);
			request.setAttribute("tawRmPlanContent", tawRmPlanContent);
		}catch (Exception e) {
			e.printStackTrace();
		}
        return mapping.findForward("viewContent");
    }
    
    /**
	 * 
	 * @see 保存日常维护作业计划内容
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
		HttpSession session=request.getSession();
		ActionMessages messages = new ActionMessages();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		final String roomId = sessionform.getRoomId();
		final String workSerial = sessionform.getWorkSerial();
		final String userId = sessionform.getUserid();
		List executeContentlist=(List)session.getAttribute("executeContentlist");
		ITawRmPlanContentManager mgr=(ITawRmPlanContentManager)getBean("ITawRmPlanContentManager");
		for(int i=0;i<executeContentlist.size();i++){
			TawwpExecuteContent tawwpExecuteContent=(TawwpExecuteContent)executeContentlist.get(i);
			TawRmPlanContent tawRmPlanContent=new TawRmPlanContent();
			tawRmPlanContent.setExecutecontentId(tawwpExecuteContent.getId());
			tawRmPlanContent.setDeptId(tawwpExecuteContent.getDeptId());
			String month=tawwpExecuteContent.getTawwpMonthPlan().getYearFlag()+"-"+tawwpExecuteContent.getTawwpMonthPlan().getMonthFlag();
			tawRmPlanContent.setMonth(month);
			tawRmPlanContent.setContent(tawwpExecuteContent.getContent());
			tawRmPlanContent.setMonthplanName(tawwpExecuteContent.getName());
			tawRmPlanContent.setRoomId(roomId);
			tawRmPlanContent.setUserId(userId);
			tawRmPlanContent.setWorkSerial(workSerial);
			tawRmPlanContent.setExecuteFlag(tawwpExecuteContent.getExecuteFlag());
			String createTime = StaticMethod.getLocalString();
			tawRmPlanContent.setCreateTime(createTime);
			mgr.saveTawRmPlanContent(tawRmPlanContent);
		}
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("tawRmPlanContent.added"));
		// save messages in session to survive a redirect
		saveMessages(request.getSession(), messages);
		session.removeAttribute("executeContentlist");
		return mapping.findForward("successok");
	}
	
	/**
	 * 
	 * @see 根据条件查询日常维护作业计划内容结果集
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
		int offset;
		int length = PAGE_LENGTH;
		try {
			String pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			TawRmPlanContent tawRmPlanContent=null;
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			//final String userId = sessionform.getUserid();
			//final String deptId = sessionform.getDeptid();
			//ITawSystemUserManager userManager=(ITawSystemUserManager)getBean("itawSystemUserManager");
			//final String roomId = StaticMethod.null2String(userManager.getUserByuserid(userId).getCptroomid());
			final String workSerial = sessionform.getWorkSerial();
			// 得到查询的条件
			String whereStr = " where workSerial = '"+ workSerial + "'";
			List tawRmPlanContentList = new ArrayList();
			ITawRmPlanContentManager mgr=(ITawRmPlanContentManager)getBean("ITawRmPlanContentManager");
			Map map = (Map) mgr.getTawRmPlanContents(new Integer(offset),new Integer(length), whereStr);
			List list = (List) map.get("result");
			// 分页
			for (int i = offset; i < (length + offset) && i < list.size(); i++) {
				tawRmPlanContent=(TawRmPlanContent)list.get(i);
				tawRmPlanContentList.add(tawRmPlanContent);
			}
			String url = request.getContextPath() + "/duty"+ mapping.getPath() + ".do";
			int size = Integer.parseInt((StaticMethod.null2String((String) map
					.get("total"))));
			String pagerHeader = Pager.generate(offset, size, length, url,
					"method=search");
			request.setAttribute("pagerHeader", pagerHeader);
			Iterator listIt = tawRmPlanContentList.iterator();
			request.setAttribute("listIt", listIt);
			request.setAttribute("resultSize", map.get("total"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("list");
	}
	

	/**
	 * 
	 * @see 根据条件查询日常维护作业计划内容结果集
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public ActionForward searchList2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int offset;
		int length = PAGE_LENGTH;
		try {
			String whereStr = "";
			String pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			String pageIndexName = new org.displaytag.util.ParamEncoder(
					DutyConstacts.TAW_RM_WORKORDERRECORDLIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(GenericValidator
					.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
					: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
			
			TawRmPlanContentForm tawRmPlanContentForm = (TawRmPlanContentForm) form;
			String monthplanName=StaticMethod.null2String(tawRmPlanContentForm.getMonthplanName());
			String roomId=StaticMethod.null2String(tawRmPlanContentForm.getRoomId());
			String startTime=StaticMethod.null2String(tawRmPlanContentForm.getStartTime());
			String endTime=StaticMethod.null2String(tawRmPlanContentForm.getEndTime());
			String month=StaticMethod.null2String(tawRmPlanContentForm.getMonth());
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
			String userId = sessionform.getUserid();
			//String deptId = sessionform.getDeptid();
			//String roomId = sessionform.getRoomId();
			//final String workSerial = sessionform.getWorkSerial();
			whereStr = request.getParameter("whereStr");
			//TawRmPlanContent tawRmPlanContent = null;
			StringBuffer buffer = new StringBuffer();
			StringBuffer bufferpage = new StringBuffer();
			//List tawRmPlanContentList = new ArrayList();
			//buffer.append(" where workSerial = '"+ workSerial + "'");
			buffer.append(" where 1=1 ");
			bufferpage.append("method=searchList");
			bufferpage.append("&monthplanName=" + monthplanName + "");
			if (!monthplanName.equals("")) {
				buffer.append(" and monthplanName like '%" + monthplanName + "%'");

			}

			bufferpage.append("&roomId=" + roomId + "");
			if(!userId.equals(StaticVariable.ADMIN)){
				if (!roomId.equals("")) {
					buffer.append(" and roomId ='" + roomId + "'");

				}else{
					List roomList=this.getRoomList(request);
					buffer.append(" and roomId in (");
					for(int i=0;i<roomList.size()-1;i++){
						TawSystemCptroom tawSystemCptroom=(TawSystemCptroom)roomList.get(i);
						String id=tawSystemCptroom.getId().toString();
						buffer.append(id+",");
					}
					TawSystemCptroom tawSystemCptroom=(TawSystemCptroom)roomList.get(roomList.size()-1);
					String id=tawSystemCptroom.getId().toString();
					buffer.append(id+")");
				}
			}
			
			bufferpage.append("&startTime=" + startTime + "");
			if (!startTime.equals("")) {
				buffer.append(" and createTime>= '" + startTime + "'");

			}
			
			bufferpage.append("&endTime=" + endTime + "");
			if (!endTime.equals("")) {
				buffer.append(" and createTime<= '" + endTime + "'");

			}
			
			String yearflag= StaticMethod.null2String(request.getParameter("yearflag"));
			String monthflag=StaticMethod.null2String(request.getParameter("monthflag"));
			if(yearflag.trim().equals("请选择")){
				yearflag="";
			}
			if(monthflag.trim().equals("请选择")){
				monthflag="";
			}
			bufferpage.append("&month=" + month + "");
			if (!month.equals("")&&!month.equals("-")) {
				buffer.append(" and month like '%" + month + "%'");
			}
			whereStr = buffer.toString();
			request.setAttribute("whereStr", whereStr);
			String pageStr = bufferpage.toString();
			ITawRmPlanContentManager mgr = (ITawRmPlanContentManager) getBean("ITawRmPlanContentManager");
			Map map = (Map) mgr.getTawRmPlanContents(pageIndex,
					new Integer(length), whereStr);
			List list = (List) map.get("result");

			// 分叶
			//for (int i = offset; i < (length + offset) && i < list.size(); i++) {
			//	tawRmPlanContent = (TawRmPlanContent) list.get(i);
			//	tawRmPlanContentList.add(tawRmPlanContent);
			//}

			String url = request.getContextPath() + "/duty"+ mapping.getPath() + ".do";
			int size = ((Integer)map.get("total")).intValue();
			//			 每页显示条数
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
					.getPageSize();
			String pagerHeader = MemoPage.generate(pageIndex.intValue(), size, length, url,pageStr);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute(DutyConstacts.TAW_RM_PLANCONTENTLIST, list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
			//Iterator listIt = tawRmPlanContentList.iterator();
			//request.setAttribute("listIt", listIt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("list");
	}
	
	/**
	 * 
	 * @see 根据条件查询日常维护作业计划内容结果集
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public ActionForward searchList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int offset;
		int length = PAGE_LENGTH;
		try {
			String whereStr = "";
			String pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}

			String pageIndexName = new org.displaytag.util.ParamEncoder(
					DutyConstacts.TAW_RM_WORKORDERRECORDLIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(GenericValidator
					.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
					: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
			
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
			String userId = sessionform.getUserid();
			//String deptId = sessionform.getDeptid();
			//String roomId = sessionform.getRoomId();
			//final String workSerial = sessionform.getWorkSerial();
			whereStr = request.getParameter("whereStr");
			//TawRmPlanContent tawRmPlanContent = null;
			StringBuffer buffer = new StringBuffer();
			StringBuffer bufferpage = new StringBuffer();
			//List tawRmPlanContentList = new ArrayList();
			//buffer.append(" where workSerial = '"+ workSerial + "'");
			buffer.append(" where 1=1 ");
			bufferpage.append("method=searchList");
			String monthplanName = StaticMethod.null2String(request
					.getParameter("monthplanName"));
			bufferpage.append("&monthplanName=" + monthplanName + "");
			if (!monthplanName.equals("")) {
				buffer.append(" and monthplanName like '%" + monthplanName + "%'");

			}

			String roomId = StaticMethod.null2String(request.getParameter("roomId"));
			bufferpage.append("&roomId=" + roomId + "");
			if(!userId.equals(StaticVariable.ADMIN)){
				if (!roomId.equals("")) {
					buffer.append(" and roomId ='" + roomId + "'");

				}else{
					List roomList=this.getRoomList(request);
					buffer.append(" and roomId in (");
					for(int i=0;i<roomList.size()-1;i++){
						TawSystemCptroom tawSystemCptroom=(TawSystemCptroom)roomList.get(i);
						String id=tawSystemCptroom.getId().toString();
						buffer.append(id+",");
					}
					TawSystemCptroom tawSystemCptroom=(TawSystemCptroom)roomList.get(roomList.size()-1);
					String id=tawSystemCptroom.getId().toString();
					buffer.append(id+")");
				}
			}
			
			String startTime = StaticMethod.null2String(request.getParameter("startTime"));
			bufferpage.append("&startTime=" + startTime + "");
			if (!startTime.equals("")) {
				buffer.append(" and createTime>= '" + startTime + "'");

			}
			
			String endTime = StaticMethod.null2String(request.getParameter("endTime"));
			bufferpage.append("&endTime=" + endTime + "");
			if (!endTime.equals("")) {
				buffer.append(" and createTime<= '" + endTime + "'");

			}
			
			String yearflag= StaticMethod.null2String(request.getParameter("yearflag"));
			String monthflag=StaticMethod.null2String(request.getParameter("monthflag"));
			if(yearflag.trim().equals("请选择")){
				yearflag="";
			}
			if(monthflag.trim().equals("请选择")){
				monthflag="";
			}
			String month = yearflag+"-"+monthflag;
			bufferpage.append("&month=" + month + "");
			if (!month.equals("")&&!month.equals("-")) {
				buffer.append(" and month like '%" + month + "%'");
			}
			whereStr = buffer.toString();
			request.setAttribute("whereStr", whereStr);
			String pageStr = bufferpage.toString();
			ITawRmPlanContentManager mgr = (ITawRmPlanContentManager) getBean("ITawRmPlanContentManager");
			Map map = (Map) mgr.getTawRmPlanContents(pageIndex,
					new Integer(length), whereStr);
			List list = (List) map.get("result");
//			 每页显示条数
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
					.getPageSize();
			// 分叶
			//for (int i = offset; i < (length + offset) && i < list.size(); i++) {
			//	tawRmPlanContent = (TawRmPlanContent) list.get(i);
			//	tawRmPlanContentList.add(tawRmPlanContent);
			//}

			String url = request.getContextPath() + "/duty"+ mapping.getPath() + ".do";
			int size = ((Integer)map.get("total")).intValue();
			String pagerHeader = MemoPage.generate(pageIndex.intValue(), size, length, url,pageStr);
			request.setAttribute("pagerHeader", pagerHeader);
			//Iterator listIt = tawRmPlanContentList.iterator();
			//request.setAttribute("listIt", listIt);
			request.setAttribute(DutyConstacts.TAW_RM_PLANCONTENTLIST, list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("list");
	}


	public ActionForward allSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//List roomList=this.getRoomList(request);
		//request.setAttribute("roomList", roomList);
		return mapping.findForward("allSearch");
	}
	public ActionForward planSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List roomList=this.getRoomList(request);
		request.setAttribute("roomList", roomList);
		return mapping.findForward("planSearch");
	}
	
	// 删除 返回查询页面
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages messages = new ActionMessages();
		TawRmPlanContentForm tawRmPlanContentForm = (TawRmPlanContentForm) form;
		try {
			// Exceptions are caught by ActionExceptionHandler
			ITawRmPlanContentManager mgr=(ITawRmPlanContentManager)getBean("ITawRmPlanContentManager");
			mgr.removeTawRmPlanContent(tawRmPlanContentForm.getId());

			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawRmPlanContent.deleted"));

			// save messages in session, so they'll survive the redirect
			saveMessages(request.getSession(), messages);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return search(mapping, form, request, response);
	}
	private List getRoomList(HttpServletRequest request){
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		TawRmAssignworkBO tawRmAssignworkBO = null;
		Vector SelectRoom = new Vector();
		TawSystemCptroom tawApparatusroom = null;
		TawSystemPrivRegion tawSystemPrivRegion = null;
		List roomList=new ArrayList();
		TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
		TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();
		if (saveSessionBeanForm.getUserid().equals(StaticVariable.ADMIN)) {
			tawRmAssignworkBO = new TawRmAssignworkBO(ds);
			try {
				SelectRoom = tawRmAssignworkBO.getRoomSelect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tawApparatusroom = null;
			if (SelectRoom.size() > 0) {
				for (int i = 0; i < SelectRoom.size(); i++) {
					tawApparatusroom = cptroomBO.getTawSystemCptroomById(
							new Integer((String) SelectRoom.elementAt(i)),
							0);
					if (tawApparatusroom != null) {
						roomList.add(tawApparatusroom);
					}
				}
			}
		} else {
			SelectRoom = StaticMethod
					.list2vector(privBO
							.getPermissions(
									saveSessionBeanForm.getUserid(),
									com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,
									com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM));

			if (SelectRoom.size() > 0) {
				tawApparatusroom = null;
				for (int i = 0; i < SelectRoom.size(); i++) {
					tawSystemPrivRegion = (TawSystemPrivRegion) SelectRoom
							.elementAt(i);
					tawApparatusroom = cptroomBO.getTawSystemCptroomById(
							new Integer(tawSystemPrivRegion.getRegionid()),
							0);
					if (tawApparatusroom != null) {
						roomList.add(tawApparatusroom);
					}
				}
			} 
		}
		return roomList;
	}
}
