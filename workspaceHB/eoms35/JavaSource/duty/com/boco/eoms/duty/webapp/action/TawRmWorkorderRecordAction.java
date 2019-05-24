
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

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.duty.bo.TawRmAssignworkBO;
import com.boco.eoms.duty.model.TawRmWorkorderRecord;
import com.boco.eoms.duty.service.ITawRmWorkorderRecordManager;
import com.boco.eoms.duty.util.DutyConstacts;
import com.boco.eoms.duty.webapp.form.TawRmWorkorderRecordForm;
import com.boco.eoms.workbench.memo.util.MemoPage;

import com.boco.eoms.common.util.Pager;
import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivRegion;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.commons.ui.util.JSONUtil;

/**
 * Action class to handle CRUD on a TawRmWorkorderRecord object
 * @struts.action name="tawRmWorkorderRecordForm" path="/tawRmWorkorderRecords" scope="request"
 *  validate="false" parameter="method" input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * 
 * @struts.action-forward name="main" 
 *  path="/WEB-INF/pages/tawRmWorkorderRecord/tawRmWorkorderRecordTree.jsp" contextRelative="true"
 *
 * @struts.action-forward name="edit" 
 *  path="/WEB-INF/pages/tawRmWorkorderRecord/tawRmWorkorderRecordForm.jsp" contextRelative="true"
 *
 * @struts.action-forward name="list" 
 *  path="/WEB-INF/pages/tawRmWorkorderRecord/tawRmWorkorderRecordList.jsp" contextRelative="true"
 */
public final class TawRmWorkorderRecordAction extends BaseAction {
	// 定义页数长度
	private static int PAGE_LENGTH = 15;
	private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool.getInstance();
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
         return mapping.findForward("main");
    }
 	/**
	 * 根据父节点的id得到�?有子节点的JSON数据
	 */
	public void xGetChildNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = request.getParameter("node");

		ITawRmWorkorderRecordManager mgr = (ITawRmWorkorderRecordManager) getBean("ItawRmWorkorderRecordManager");
		JSONArray json = mgr.xGetChildNodes(nodeId);

		JSONUtil.print(response, json.toString());
	}

    /**
	 * ajax保存
	 */
    public void xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawRmWorkorderRecordForm tawRmWorkorderRecordForm = (TawRmWorkorderRecordForm) form;

		ITawRmWorkorderRecordManager mgr = (ITawRmWorkorderRecordManager) getBean("ItawRmWorkorderRecordManager");
		TawRmWorkorderRecord tawRmWorkorderRecord = (TawRmWorkorderRecord) convert(tawRmWorkorderRecordForm);
		mgr.saveTawRmWorkorderRecord(tawRmWorkorderRecord);
		// JSONUtil.print(response, "操作成功");
	}
    /**
	 * 根据模块或功能的编码，删除该对象
	 */
    public void xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawRmWorkorderRecordForm tawRmWorkorderRecordForm = (TawRmWorkorderRecordForm) form;

        ITawRmWorkorderRecordManager mgr = (ITawRmWorkorderRecordManager) getBean("ItawRmWorkorderRecordManager");
		mgr.removeTawRmWorkorderRecord(tawRmWorkorderRecordForm.getId());

	}
    /**
	 * ajax请求修改某节点的详细信息
	 */
    public String xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawRmWorkorderRecordForm tawRmWorkorderRecordForm = (TawRmWorkorderRecordForm) form;

		if (tawRmWorkorderRecordForm.getId() != null) {
			ITawRmWorkorderRecordManager mgr = (ITawRmWorkorderRecordManager) getBean("ItawRmWorkorderRecordManager");
			TawRmWorkorderRecord tawRmWorkorderRecord = (TawRmWorkorderRecord) convert(tawRmWorkorderRecordForm);

			mgr.saveTawRmWorkorderRecord(tawRmWorkorderRecord);
		   //mgr.updateTawRmWorkorderRecord(tawRmWorkorderRecord);
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
		ITawRmWorkorderRecordManager mgr = (ITawRmWorkorderRecordManager) getBean("ItawRmWorkorderRecordManager");
		TawRmWorkorderRecord tawRmWorkorderRecord = mgr.getTawRmWorkorderRecord(_strId);

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(tawRmWorkorderRecord);

		JSONUtil.print(response, jsonRoot.toString());
	}
    
    public ActionForward view(ActionMapping mapping, ActionForm form,
         	HttpServletRequest request,HttpServletResponse response)
        	throws Exception {
		try {
			HttpSession session=request.getSession();
			List newUndoList=(List)session.getAttribute("newUndoList");
			List newFinishList=(List)session.getAttribute("newFinishList");
			String sheetId = StaticMethod.nullObject2String(request.getParameter("sheetId"));
			ListOrderedMap item=null;
			String orderState="";
			String orderType="";
			for(int i=0;i<newUndoList.size();i++){
				ListOrderedMap tmpItem = (ListOrderedMap) newUndoList.get(i);
				if(((String)tmpItem.getValue(0)).equals(sheetId)){
					item=tmpItem;
					orderState="10";
					orderType="0";
				}
			}
			for(int i=0;i<newFinishList.size();i++){
				ListOrderedMap tmpItem = (ListOrderedMap) newFinishList.get(i);
				if(((String)tmpItem.getValue(0)).equals(sheetId)){
					item=tmpItem;
					orderState="12";
					orderType="0";
				}
			}
			request.setAttribute("item", item);
			request.setAttribute("orderState", orderState);
			request.setAttribute("orderType", orderType);
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
			TawRmWorkorderRecordForm tawRmWorkorderRecordForm = (TawRmWorkorderRecordForm) form;
			String id = StaticMethod.nullObject2String(request.getParameter("id"));
			String title=StaticMethod.nullObject2String(session.getAttribute("myTitle"));
			String workOrderId=StaticMethod.nullObject2String(session.getAttribute("myWorkOrderId"));
			String orderState=StaticMethod.nullObject2String(session.getAttribute("myOrderState"));
			String replyTime=StaticMethod.nullObject2String(session.getAttribute("myReplyTime"));
			String roomId=StaticMethod.nullObject2String(session.getAttribute("myRoomId"));
			String startTime=StaticMethod.nullObject2String(session.getAttribute("myStartTime"));
			String endTime=StaticMethod.nullObject2String(session.getAttribute("myEndTime"));
			tawRmWorkorderRecordForm.setTitle(title);
			tawRmWorkorderRecordForm.setWorkOrderId(workOrderId);
			tawRmWorkorderRecordForm.setOrderState(orderState);
			tawRmWorkorderRecordForm.setReplyTime(replyTime);
			tawRmWorkorderRecordForm.setRoomId(roomId);
			tawRmWorkorderRecordForm.setStartTime(startTime);
			tawRmWorkorderRecordForm.setEndTime(endTime);
			session.removeAttribute("myTitle");
			session.removeAttribute("myWorkOrderId");
			session.removeAttribute("myOrderState");
			session.removeAttribute("myReplyTime");
			session.removeAttribute("myRoomId");
			session.removeAttribute("myStartTime");
			session.removeAttribute("myEndTime");
			ITawRmWorkorderRecordManager mgr=(ITawRmWorkorderRecordManager)getBean("ITawRmWorkorderRecordManager");
			TawRmWorkorderRecord tawRmWorkorderRecord=mgr.getTawRmWorkorderRecord(id);
			request.setAttribute("tawRmWorkorderRecord", tawRmWorkorderRecord);
		}catch (Exception e) {
			e.printStackTrace();
		}
        return mapping.findForward("viewContent");
    }
    
    /**
	 * 
	 * @see 保存工单日志
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
		final String roomId = StaticMethod.nullObject2String(sessionform.getRoomId());
		final String workSerial = StaticMethod.nullObject2String(sessionform.getWorkSerial());
		final String userId = StaticMethod.nullObject2String(sessionform.getUserid());
		List newUndoList=(List)session.getAttribute("newUndoList");
		List newFinishList=(List)session.getAttribute("newFinishList");
		ITawRmWorkorderRecordManager mgr=(ITawRmWorkorderRecordManager)getBean("ITawRmWorkorderRecordManager");
		String orderType="0";
		for(int i=0;i<newUndoList.size();i++){
			ListOrderedMap item = (ListOrderedMap) newUndoList.get(i);
			TawRmWorkorderRecord tawRmWorkorderRecord=new TawRmWorkorderRecord();
			String sheetId = StaticMethod.nullObject2String(item.getValue(0));
			String title=StaticMethod.nullObject2String(item.getValue(1));
			String replyTime=StaticMethod.nullObject2String(item.getValue(2));
			String mainId=StaticMethod.nullObject2String(item.getValue(3));
			String senderId=mgr.getUserIdByMainId(mainId);
			String subProcessInstId=StaticMethod.nullObject2String(item.getValue(4));
			String processInstId=StaticMethod.nullObject2String(item.getValue(5));
			String activityInstId=StaticMethod.nullObject2String(item.getValue(6));
			String workItemId=StaticMethod.nullObject2String(item.getValue(7));
			String activityDefId=StaticMethod.nullObject2String(item.getValue(8));
			String activityInstName=StaticMethod.nullObject2String(item.getValue(9));
			String sheetType=StaticMethod.nullObject2String(item.getValue(10));
			tawRmWorkorderRecord.setWorkOrderId(sheetId);
			tawRmWorkorderRecord.setTitle(title);
			tawRmWorkorderRecord.setReceiver(userId);
			tawRmWorkorderRecord.setSender(senderId);
			tawRmWorkorderRecord.setReplyTime(replyTime);
			tawRmWorkorderRecord.setOrderType(orderType);
			tawRmWorkorderRecord.setOrderState("10");
			tawRmWorkorderRecord.setSubProcessInstId(subProcessInstId);
			tawRmWorkorderRecord.setProcessInstId(processInstId);
			tawRmWorkorderRecord.setActivityInstId(activityInstId);
			tawRmWorkorderRecord.setWorkItemId(workItemId);
			tawRmWorkorderRecord.setActivityDefId(activityDefId);
			tawRmWorkorderRecord.setActivityInstName(activityInstName);
			tawRmWorkorderRecord.setSheetType(sheetType);
			tawRmWorkorderRecord.setRoomId(roomId);
			tawRmWorkorderRecord.setUserId(userId);
			tawRmWorkorderRecord.setWorkSerial(workSerial);
			String createTime = StaticMethod.getLocalString();
			tawRmWorkorderRecord.setReceiveTime(createTime);
			tawRmWorkorderRecord.setCreateTime(createTime);
			mgr.saveTawRmWorkorderRecord(tawRmWorkorderRecord);
		}
		
		for(int i=0;i<newFinishList.size();i++){
			ListOrderedMap item = (ListOrderedMap) newFinishList.get(i);
			TawRmWorkorderRecord tawRmWorkorderRecord=new TawRmWorkorderRecord();
			String sheetId = StaticMethod.nullObject2String(item.getValue(0));
			String title=StaticMethod.nullObject2String(item.getValue(1));
			String replyTime=StaticMethod.nullObject2String(item.getValue(2));
			String mainId=StaticMethod.nullObject2String(item.getValue(3));
			String senderId=mgr.getUserIdByMainId(mainId);
			String subProcessInstId=StaticMethod.nullObject2String(item.getValue(4));
			String processInstId=StaticMethod.nullObject2String(item.getValue(5));
			String activityInstId=StaticMethod.nullObject2String(item.getValue(6));
			String workItemId=StaticMethod.nullObject2String(item.getValue(7));
			String activityDefId=StaticMethod.nullObject2String(item.getValue(8));
			String activityInstName=StaticMethod.nullObject2String(item.getValue(9));
			String sheetType=StaticMethod.nullObject2String(item.getValue(10));
			tawRmWorkorderRecord.setWorkOrderId(sheetId);
			tawRmWorkorderRecord.setTitle(title);
			tawRmWorkorderRecord.setReceiver(userId);
			tawRmWorkorderRecord.setSender(senderId);
			tawRmWorkorderRecord.setReplyTime(replyTime);
			tawRmWorkorderRecord.setOrderType(orderType);
			tawRmWorkorderRecord.setOrderState("12");
			tawRmWorkorderRecord.setSubProcessInstId(subProcessInstId);
			tawRmWorkorderRecord.setProcessInstId(processInstId);
			tawRmWorkorderRecord.setActivityInstId(activityInstId);
			tawRmWorkorderRecord.setWorkItemId(workItemId);
			tawRmWorkorderRecord.setActivityDefId(activityDefId);
			tawRmWorkorderRecord.setActivityInstName(activityInstName);
			tawRmWorkorderRecord.setSheetType(sheetType);
			tawRmWorkorderRecord.setRoomId(roomId);
			tawRmWorkorderRecord.setUserId(userId);
			tawRmWorkorderRecord.setWorkSerial(workSerial);
			String createTime = StaticMethod.getLocalString();
			tawRmWorkorderRecord.setReceiveTime(createTime);
			tawRmWorkorderRecord.setCreateTime(createTime);
			mgr.saveTawRmWorkorderRecord(tawRmWorkorderRecord);
		}
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("tawRmWorkorderRecord.added"));
		// save messages in session to survive a redirect
		saveMessages(request.getSession(), messages);
		session.removeAttribute("newUndoList");
		session.removeAttribute("newFinishList");
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
			TawRmWorkorderRecord tawRmWorkorderRecord=null;
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
			final String workSerial = sessionform.getWorkSerial();
			// 得到查询的条件
			String whereStr = " where workSerial = '"+ workSerial + "'";
			List tawRmWorkorderRecordList = new ArrayList();
			ITawRmWorkorderRecordManager mgr=(ITawRmWorkorderRecordManager)getBean("ITawRmWorkorderRecordManager");
			Map map = (Map) mgr.getTawRmWorkorderRecords(new Integer(offset),new Integer(length), whereStr);
			List list = (List) map.get("result");
			// 分页
			for (int i = offset; i < (length + offset) && i < list.size(); i++) {
				tawRmWorkorderRecord=(TawRmWorkorderRecord)list.get(i);
				tawRmWorkorderRecordList.add(tawRmWorkorderRecord);
			}
			String url = request.getContextPath() + "/duty"+ mapping.getPath() + ".do";
			int size = Integer.parseInt((StaticMethod.null2String((String) map
					.get("total"))));
			String pagerHeader = Pager.generate(offset, size, length, url,
					"method=search");
			request.setAttribute("pagerHeader", pagerHeader);
			Iterator listIt = tawRmWorkorderRecordList.iterator();
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
			String userId=sessionform.getUserid();
			whereStr = request.getParameter("whereStr");
			StringBuffer buffer = new StringBuffer();
			StringBuffer bufferpage = new StringBuffer();
			//buffer.append(" where workSerial = '"+ workSerial + "'");
			buffer.append(" where 1=1 ");
			bufferpage.append("method=searchList");
			String title = StaticMethod.null2String(request.getParameter("title"));
			bufferpage.append("&title=" + title + "");
			if (!title.equals("")) {
				buffer.append(" and title like '%" + title + "%'");
			}

			String workOrderId = StaticMethod.null2String(request.getParameter("workOrderId"));
			bufferpage.append("&workOrderId=" + workOrderId + "");
			if (!workOrderId.equals("")) {
				buffer.append(" and workOrderId like '%" + workOrderId + "%'");
			}

			String orderState = StaticMethod.null2String(request.getParameter("orderState"));
			if(orderState.trim().equals("请选择")){
				orderState="";
			}
			bufferpage.append("&orderState=" + orderState + "");
			if (!orderState.equals("")&&!orderState.trim().equals("请选择")) {
				buffer.append(" and orderState='" + orderState + "'");
			}
			
			String replyTime = StaticMethod.null2String(request.getParameter("replyTime"));
			bufferpage.append("&replyTime=" + replyTime + "");
			if (!replyTime.equals("")) {
				buffer.append(" and replyTime= '" + replyTime + "'");
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
			whereStr = buffer.toString();
			request.setAttribute("whereStr", whereStr);
			String pageStr = bufferpage.toString();
			ITawRmWorkorderRecordManager mgr=(ITawRmWorkorderRecordManager)getBean("ITawRmWorkorderRecordManager");
			Map map = (Map) mgr.getTawRmWorkorderRecords(pageIndex,new Integer(length), whereStr);
			List list = (List) map.get("result");
			//			 每页显示条数
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
			String url = request.getContextPath() + "/duty"+ mapping.getPath() + ".do";
			int size = ((Integer)map.get("total")).intValue();
			String pagerHeader = MemoPage.generate(pageIndex.intValue(), size, length, url,pageStr);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute(DutyConstacts.TAW_RM_WORKORDERRECORDLIST, list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
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
			
			TawRmWorkorderRecordForm tawRmWorkorderRecordForm = (TawRmWorkorderRecordForm) form;
			String title=StaticMethod.null2String(tawRmWorkorderRecordForm.getTitle());
			String workOrderId=StaticMethod.null2String(tawRmWorkorderRecordForm.getWorkOrderId());
			String orderState=StaticMethod.null2String(tawRmWorkorderRecordForm.getOrderState());
			String replyTime=StaticMethod.null2String(tawRmWorkorderRecordForm.getReplyTime());
			String roomId=StaticMethod.null2String(tawRmWorkorderRecordForm.getRoomId());
			String startTime=StaticMethod.null2String(tawRmWorkorderRecordForm.getStartTime());
			String endTime=StaticMethod.null2String(tawRmWorkorderRecordForm.getEndTime());
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
			String userId=sessionform.getUserid();
			whereStr = request.getParameter("whereStr");
			StringBuffer buffer = new StringBuffer();
			StringBuffer bufferpage = new StringBuffer();
			//buffer.append(" where workSerial = '"+ workSerial + "'");
			buffer.append(" where 1=1 ");
			bufferpage.append("method=searchList");
			bufferpage.append("&title=" + title + "");
			if (!title.equals("")) {
				buffer.append(" and title like '%" + title + "%'");
			}

			bufferpage.append("&workOrderId=" + workOrderId + "");
			if (!workOrderId.equals("")) {
				buffer.append(" and workOrderId like '%" + workOrderId + "%'");
			}

			bufferpage.append("&orderState=" + orderState + "");
			if (!orderState.equals("")&&!orderState.trim().equals("请选择")) {
				buffer.append(" and orderState='" + orderState + "'");
			}
			
			bufferpage.append("&replyTime=" + replyTime + "");
			if (!replyTime.equals("")) {
				buffer.append(" and replyTime= '" + replyTime + "'");
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
			whereStr = buffer.toString();
			request.setAttribute("whereStr", whereStr);
			String pageStr = bufferpage.toString();
			ITawRmWorkorderRecordManager mgr=(ITawRmWorkorderRecordManager)getBean("ITawRmWorkorderRecordManager");
			Map map = (Map) mgr.getTawRmWorkorderRecords(pageIndex,
					new Integer(length), whereStr);
			List list = (List) map.get("result");
			//			 每页显示条数
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
			String url = request.getContextPath() + "/duty"+ mapping.getPath() + ".do";
			int size = ((Integer)map.get("total")).intValue();
			String pagerHeader = MemoPage.generate(pageIndex.intValue(), size, length, url,pageStr);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute(DutyConstacts.TAW_RM_WORKORDERRECORDLIST, list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("list");
	}
	
	public ActionForward workOrderSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List roomList=this.getRoomList(request);
		request.setAttribute("roomList", roomList);
		return mapping.findForward("workOrderSearch");
	}
	
    public ActionForward edit(ActionMapping mapping, ActionForm form,
         	HttpServletRequest request,HttpServletResponse response)
        	throws Exception {
		int offset=0;
		int length = PAGE_LENGTH;
		ITawRmWorkorderRecordManager workOrderMgr = (ITawRmWorkorderRecordManager) getBean("ITawRmWorkorderRecordManager");
		ITawSystemUserRefRoleManager userRefRoleMgr = (ITawSystemUserRefRoleManager) getBean("itawSystemUserRefRoleManager");
		ITawSystemUserManager userMgr = (ITawSystemUserManager) getBean("itawSystemUserManager");
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
			//String workSerial=sessionform.getWorkSerial();
			//String roomId=sessionform.getRoomId();
			String userId=sessionform.getUserid();
			String userName=userMgr.id2Name(userId);
			List roleIdList=userRefRoleMgr.getRoleidByuserid(userId);
			String roleIdListStr="";
			if(roleIdList!=null&&roleIdList.size()!=0){
				for(int i=0;i<roleIdList.size()-1;i++){
					String roleId=(String)roleIdList.get(i);
					roleIdListStr=roleIdListStr+"'"+roleId+"',";
				}
				String roleId=(String)roleIdList.get(roleIdList.size()-1);
				roleIdListStr=roleIdListStr+"'"+roleId+"'";
			}
			List undoList=workOrderMgr.getUndoWorkOrderList(userId, roleIdListStr);
			List finishList=workOrderMgr.getFinishWorkOrderList(userId, userName);
			List newUndoList=new ArrayList();
			List newFinishList=new ArrayList();
			for(int i=0;i<undoList.size();i++){
				ListOrderedMap item = (ListOrderedMap) undoList.get(i);
				String sheetId = (String)item.getValue(0);
				String whereStr=" where workOrderId='"+sheetId+"' and orderState='10'";
				Map map=workOrderMgr.getTawRmWorkorderRecords(new Integer(offset),new Integer(length),whereStr);
				List workOrderUndoList=(List) map.get("result");
				if(workOrderUndoList==null||workOrderUndoList.size()==0){
					newUndoList.add(item);
				}
			}
			for(int i=0;i<finishList.size();i++){
				ListOrderedMap item = (ListOrderedMap) finishList.get(i);
				String sheetId = (String)item.getValue(0);
				String whereStr=" where workOrderId='"+sheetId+"' and orderState='12'";
				Map map=workOrderMgr.getTawRmWorkorderRecords(new Integer(offset),new Integer(length),whereStr);
				List workOrderFinistList=(List) map.get("result");
				if(workOrderFinistList==null||workOrderFinistList.size()==0){
					newFinishList.add(item);
				}
			}
			if((newUndoList==null||newUndoList.size()==0)&&(newFinishList==null||newFinishList.size()==0)){
				return mapping.findForward("warning");
			}
			session.setAttribute("newUndoList", newUndoList);
			session.setAttribute("newFinishList", newFinishList);
			/**Iterator undoListIt = newUndoList.iterator();
			Iterator finishListIt = newFinishList.iterator();
			request.setAttribute("undoListIt", undoListIt);
			request.setAttribute("finishListIt", finishListIt);*/
		}catch (Exception e) {
			e.printStackTrace();
		}
      return mapping.findForward("edit");
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
