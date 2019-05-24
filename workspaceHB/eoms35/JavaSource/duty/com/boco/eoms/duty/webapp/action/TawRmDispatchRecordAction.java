
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

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.duty.bo.TawRmAssignworkBO;
import com.boco.eoms.duty.model.TawRmDispatchRecord;
import com.boco.eoms.duty.service.ITawRmDispatchRecordManager;
import com.boco.eoms.duty.util.DutyConstacts;
import com.boco.eoms.duty.webapp.form.TawRmDispatchRecordForm;
import com.boco.eoms.workbench.memo.util.MemoPage;

import com.boco.eoms.common.util.Pager;
import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivRegion;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;

/**
 * Action class to handle CRUD on a TawRmDispatchRecord object
 * @struts.action name="tawRmDispatchRecordForm" path="/tawRmDispatchRecords" scope="request"
 *  validate="false" parameter="method" input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * 
 * @struts.action-forward name="main" 
 *  path="/WEB-INF/pages/tawRmDispatchRecord/tawRmDispatchRecordTree.jsp" contextRelative="true"
 *
 * @struts.action-forward name="edit" 
 *  path="/WEB-INF/pages/tawRmDispatchRecord/tawRmDispatchRecordForm.jsp" contextRelative="true"
 *
 * @struts.action-forward name="list" 
 *  path="/WEB-INF/pages/tawRmDispatchRecord/tawRmDispatchRecordList.jsp" contextRelative="true"
 */
public final class TawRmDispatchRecordAction extends BaseAction {
//	 定义页数长度
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

		ITawRmDispatchRecordManager mgr = (ITawRmDispatchRecordManager) getBean("ItawRmDispatchRecordManager");
		JSONArray json = mgr.xGetChildNodes(nodeId);

		JSONUtil.print(response, json.toString());
	}

    /**
	 * ajax保存
	 */
    public void xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawRmDispatchRecordForm tawRmDispatchRecordForm = (TawRmDispatchRecordForm) form;

		ITawRmDispatchRecordManager mgr = (ITawRmDispatchRecordManager) getBean("ItawRmDispatchRecordManager");
		TawRmDispatchRecord tawRmDispatchRecord = (TawRmDispatchRecord) convert(tawRmDispatchRecordForm);
		mgr.saveTawRmDispatchRecord(tawRmDispatchRecord);
		// JSONUtil.print(response, "操作成功");
	}
    /**
	 * 根据模块或功能的编码，删除该对象
	 */
    public void xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawRmDispatchRecordForm tawRmDispatchRecordForm = (TawRmDispatchRecordForm) form;

        ITawRmDispatchRecordManager mgr = (ITawRmDispatchRecordManager) getBean("ItawRmDispatchRecordManager");
		mgr.removeTawRmDispatchRecord(tawRmDispatchRecordForm.getId());

	}
    /**
	 * ajax请求修改某节点的详细信息
	 */
    public String xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawRmDispatchRecordForm tawRmDispatchRecordForm = (TawRmDispatchRecordForm) form;

		if (tawRmDispatchRecordForm.getId() != null) {
			ITawRmDispatchRecordManager mgr = (ITawRmDispatchRecordManager) getBean("ItawRmDispatchRecordManager");
			TawRmDispatchRecord tawRmDispatchRecord = (TawRmDispatchRecord) convert(tawRmDispatchRecordForm);

			mgr.saveTawRmDispatchRecord(tawRmDispatchRecord);
		   //mgr.updateTawRmDispatchRecord(tawRmDispatchRecord);
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
		ITawRmDispatchRecordManager mgr = (ITawRmDispatchRecordManager) getBean("ItawRmDispatchRecordManager");
		TawRmDispatchRecord tawRmDispatchRecord = mgr.getTawRmDispatchRecord(_strId);

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(tawRmDispatchRecord);

		JSONUtil.print(response, jsonRoot.toString());
	}
	

    public ActionForward edit(ActionMapping mapping, ActionForm form,
         	HttpServletRequest request,HttpServletResponse response)
        	throws Exception {
		TawRmDispatchRecordForm tawRmDispatchRecordForm=(TawRmDispatchRecordForm)form;
		try {
			// if an id is passed in, look up the user - otherwise
			// don't do anything - user is doing an add
			/**ITawSystemDictTypeManager dictMgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");
			List list=dictMgr.getDictSonsByDictid(String.valueOf(TawwpStaticVariable.WJSXDICTFLAG));
			request.setAttribute("dictTypeList",list);*/
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
			String workSerial=sessionform.getWorkSerial();
			String id = StaticMethod.nullObject2String(request.getParameter("id"));
			if(id==null||id.trim().equals("")){
				if (sessionform == null) {
					return mapping.findForward("timeout");
				}
				// 判断是否值班
				if (workSerial.equals("0")) {
					return mapping.findForward("notonduty1");
				}
			}
			HttpSession session=request.getSession();
			String tmpFileName=StaticMethod.nullObject2String(session.getAttribute("myFileName"));
			String tmpFileSource=StaticMethod.nullObject2String(session.getAttribute("myFileSource"));
			String tmpFileProperty=StaticMethod.nullObject2String(session.getAttribute("myTmpfileProperty"));
			String tmpTime=StaticMethod.nullObject2String(session.getAttribute("myTime"));
			String roomId=StaticMethod.nullObject2String(session.getAttribute("myRoomId"));
			String startTime=StaticMethod.nullObject2String(session.getAttribute("myStartTime"));
			String endTime=StaticMethod.nullObject2String(session.getAttribute("myEndTime"));
			String tmpDispatchDeptId=StaticMethod.nullObject2String(session.getAttribute("myDispatchDeptId"));
			String tmpDispatchDept=StaticMethod.nullObject2String(session.getAttribute("myDispatchDept"));
			String tmpDispatcherId=StaticMethod.nullObject2String(session.getAttribute("myDispatcherId"));
			String tmpDispatcher=StaticMethod.nullObject2String(session.getAttribute("myDispatcher"));
			tawRmDispatchRecordForm.setTmpFileName(tmpFileName);
			tawRmDispatchRecordForm.setTmpFileSource(tmpFileSource);
			tawRmDispatchRecordForm.setTmpFileProperty(tmpFileProperty);
			tawRmDispatchRecordForm.setTmpTime(tmpTime);
			tawRmDispatchRecordForm.setRoomId(roomId);
			tawRmDispatchRecordForm.setStartTime(startTime);
			tawRmDispatchRecordForm.setEndTime(endTime);
			tawRmDispatchRecordForm.setTmpDispatchDeptId(tmpDispatchDeptId);
			tawRmDispatchRecordForm.setTmpDispatchDept(tmpDispatchDept);
			tawRmDispatchRecordForm.setTmpDispatcherId(tmpDispatcherId);
			tawRmDispatchRecordForm.setTmpDispatcher(tmpDispatcher);
			session.removeAttribute("myFileName");
			session.removeAttribute("myFileSource");
			session.removeAttribute("myTmpfileProperty");
			session.removeAttribute("myTime");
			session.removeAttribute("myRoomId");
			session.removeAttribute("myStartTime");
			session.removeAttribute("myEndTime");
			session.removeAttribute("myDispatchDeptId");
			session.removeAttribute("myDispatchDept");
			session.removeAttribute("myDispatcherId");
			session.removeAttribute("myDispatcher");
			
			if(tawRmDispatchRecordForm.getId()!=null){
				ITawRmDispatchRecordManager mgr=(ITawRmDispatchRecordManager)getBean("ITawRmDispatchRecordManager");
				if (id != null && !id.equals("")) {
					TawRmDispatchRecord tawRmDispatchRecord=mgr.getTawRmDispatchRecord(id);
					if(tawRmDispatchRecord!=null){
						if(workSerial.equals("0")){
							
							request.setAttribute("tawRmDispatchRecord", tawRmDispatchRecord);
							return mapping.findForward("detailContent");
						}
						tawRmDispatchRecordForm =(TawRmDispatchRecordForm)convert(tawRmDispatchRecord);
						request.setAttribute("fileProperty",tawRmDispatchRecordForm.getFileProperty());
					}
				}
				request.setAttribute("recordId", id);
				/*
				 * tawWorkbenchMemoForm = (TawWorkbenchMemoForm)
				 * convert(tawWorkbenchMemo);
				 */
				updateFormBean(mapping, request, tawRmDispatchRecordForm);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
             return mapping.findForward("edit");
    }
    
    /**
	 * 
	 * @see 保存外借记录
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
		ActionMessages messages = new ActionMessages();
		TawRmDispatchRecordForm tawRmDispatchRecordForm=(TawRmDispatchRecordForm)form;
		boolean isNew=(tawRmDispatchRecordForm.getId().equals("")||tawRmDispatchRecordForm.getId()==null);
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		final String userId = sessionform.getUserid();
		final String workSerial = sessionform.getWorkSerial();
		final String roomId = sessionform.getRoomId();
		String createTime = StaticMethod.getLocalString();
		//ITawSystemUserManager usrMgr=(ITawSystemUserManager)getBean("itawSystemUserManager");
		//TawSystemUser user=usrMgr.getUserByuserid(userId);
		//String receiver=user.getUsername();
		ITawRmDispatchRecordManager mgr=(ITawRmDispatchRecordManager)getBean("ITawRmDispatchRecordManager");
		TawRmDispatchRecord TawRmDispatchRecord=(TawRmDispatchRecord)convert(tawRmDispatchRecordForm);
		TawRmDispatchRecord.setReceiver(userId);
		TawRmDispatchRecord.setUserId(userId);
		TawRmDispatchRecord.setWorkSerial(workSerial);
		TawRmDispatchRecord.setRoomId(roomId);
		TawRmDispatchRecord.setCreateTime(createTime);
		mgr.saveTawRmDispatchRecord(TawRmDispatchRecord);
		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"TawRmDispatchRecord.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return searchList2(mapping, form, request, response);
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"TawRmDispatchRecord.updated"));
			saveMessages(request, messages);

			return searchList2(mapping, form, request, response);
		}
	}
	

	/**
	 * 
	 * @see 根据条件查询外借记录
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
			String whereStr = "";
			String pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			String pageIndexName = new org.displaytag.util.ParamEncoder(
					DutyConstacts.TAW_RM_DISPATCHRECORDLIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(GenericValidator
					.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
					: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
			
			TawRmDispatchRecordForm tawRmDispatchRecordForm=(TawRmDispatchRecordForm)form;
			String roomId=StaticMethod.null2String(tawRmDispatchRecordForm.getRoomId());
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
			String userId=sessionform.getUserid();
			//final String workSerial = sessionform.getWorkSerial();
			whereStr = request.getParameter("whereStr");
			StringBuffer buffer = new StringBuffer();
			StringBuffer bufferpage = new StringBuffer();
			buffer.append(" where 1=1");
			bufferpage.append("method=search");
			
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
			
			whereStr = buffer.toString();
			request.setAttribute("whereStr", whereStr);
			String pageStr = bufferpage.toString();
			ITawRmDispatchRecordManager mgr = (ITawRmDispatchRecordManager) getBean("ITawRmDispatchRecordManager");
			Map map = (Map) mgr.getTawRmDispatchRecords(pageIndex,
					new Integer(length), whereStr);
			List list = (List) map.get("result");

			String url = request.getContextPath() + "/duty"+ mapping.getPath() + ".do";
			//			 每页显示条数
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
			int size = ((Integer)map.get("total")).intValue();
			String pagerHeader = MemoPage.generate(pageIndex.intValue(), size, length, url,pageStr);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute(DutyConstacts.TAW_RM_DISPATCHRECORDLIST, list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("list");
	}
	

	/**
	 * 
	 * @see 根据条件查询外借记录
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
					DutyConstacts.TAW_RM_DISPATCHRECORDLIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(GenericValidator
					.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
					: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
			
			TawRmDispatchRecordForm tawRmDispatchRecordForm=(TawRmDispatchRecordForm)form;
			String fileName=StaticMethod.null2String(tawRmDispatchRecordForm.getTmpFileName());
			String fileSource=StaticMethod.null2String(tawRmDispatchRecordForm.getTmpFileSource());
			String fileProperty=StaticMethod.null2String(tawRmDispatchRecordForm.getTmpFileProperty());
			String time=StaticMethod.null2String(tawRmDispatchRecordForm.getTmpTime());
			String roomId=StaticMethod.null2String(tawRmDispatchRecordForm.getRoomId());
			String startTime=StaticMethod.null2String(tawRmDispatchRecordForm.getStartTime());
			String endTime=StaticMethod.null2String(tawRmDispatchRecordForm.getEndTime());
			String dispatchDeptId=StaticMethod.null2String(tawRmDispatchRecordForm.getTmpDispatchDeptId());
			String dispatchDept=StaticMethod.null2String(tawRmDispatchRecordForm.getTmpDispatchDept());
			String dispatcherId=StaticMethod.null2String(tawRmDispatchRecordForm.getTmpDispatcherId());
			String dispatcher=StaticMethod.null2String(tawRmDispatchRecordForm.getTmpDispatcher());
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
			String userId=sessionform.getUserid();
			//final String workSerial = sessionform.getWorkSerial();
			whereStr = request.getParameter("whereStr");
			StringBuffer buffer = new StringBuffer();
			StringBuffer bufferpage = new StringBuffer();
			buffer.append(" where 1=1");
			bufferpage.append("method=searchList2");
			
			bufferpage.append("&fileName=" + fileName + "");
			if (!fileName.equals("")) {
				buffer.append(" and fileName like '%" + fileName + "%'");

			}
			
			bufferpage.append("&fileSource=" + fileSource + "");
			if (!fileSource.equals("")) {
				buffer.append(" and fileSource like '%" + fileSource + "%'");
			}
			
			bufferpage.append("&fileProperty=" + fileProperty + "");
			if (!fileProperty.equals("")&&!fileProperty.trim().equals("请选择")) {
				buffer.append(" and fileProperty='" + fileProperty + "'");
			}
			
			bufferpage.append("&time=" + time + "");
			if (!time.equals("")) {
				buffer.append(" and time='" + time + "'");
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
			
			bufferpage.append("&dispatchDeptId=" + dispatchDeptId);
			bufferpage.append("&dispatchDept=" + dispatchDept);
			if (!dispatchDeptId.equals("")) {
				buffer.append(" and dispatchDeptId='" + dispatchDeptId + "'");
			}else{
				if(!dispatchDeptId.equals("")){
					buffer.append(" and dispatchDept like '%" + dispatchDept + "%'");
				}
			}
			
			bufferpage.append("&dispatcherId=" + dispatcherId + "");
			bufferpage.append("&dispatcher=" + dispatcher);
			if (!dispatcherId.equals("")) {
				buffer.append(" and dispatcherId='" + dispatcherId + "'");
			}else{
				if(!dispatcher.equals("")){
					buffer.append(" and dispatcher like '%" + dispatcher + "%'");
				}
			}
			
			whereStr = buffer.toString();
			request.setAttribute("whereStr", whereStr);
			String pageStr = bufferpage.toString();
			ITawRmDispatchRecordManager mgr = (ITawRmDispatchRecordManager) getBean("ITawRmDispatchRecordManager");
			Map map = (Map) mgr.getTawRmDispatchRecords(pageIndex,
					new Integer(length), whereStr);
			List list = (List) map.get("result");

			String url = request.getContextPath() + "/duty"+ mapping.getPath() + ".do";
			//			 每页显示条数
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
			int size = ((Integer)map.get("total")).intValue();
			String pagerHeader = MemoPage.generate(pageIndex.intValue(), size, length, url,pageStr);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute(DutyConstacts.TAW_RM_DISPATCHRECORDLIST, list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("list");
	}
	

	/**
	 * 
	 * @see 根据条件查询外借记录
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
					DutyConstacts.TAW_RM_DISPATCHRECORDLIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(GenericValidator
					.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
					: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
			
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
			String userId=sessionform.getUserid();
			//final String workSerial = sessionform.getWorkSerial();
			whereStr = request.getParameter("whereStr");
			StringBuffer buffer = new StringBuffer();
			StringBuffer bufferpage = new StringBuffer();
			buffer.append(" where 1=1");
			bufferpage.append("method=searchList");
			
			String fileName = StaticMethod.null2String(request.getParameter("tmpFileName"));
			bufferpage.append("&fileName=" + fileName + "");
			if (!fileName.equals("")) {
				buffer.append(" and fileName like '%" + fileName + "%'");

			}
			
			String fileSource = StaticMethod.null2String(request.getParameter("tmpFileSource"));
			bufferpage.append("&fileSource=" + fileSource + "");
			if (!fileSource.equals("")) {
				buffer.append(" and fileSource like '%" + fileSource + "%'");
			}
			
			String fileProperty = StaticMethod.null2String(request.getParameter("tmpFileProperty"));
			bufferpage.append("&fileProperty=" + fileProperty + "");
			if (!fileProperty.equals("")&&!fileProperty.trim().equals("请选择")) {
				buffer.append(" and fileProperty='" + fileProperty + "'");
			}
			
			String time = StaticMethod.null2String(request.getParameter("tmpTime"));
			bufferpage.append("&time=" + time + "");
			if (!time.equals("")) {
				buffer.append(" and time='" + time + "'");
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
			
			String dispatchDeptId = StaticMethod.null2String(request.getParameter("tmpDispatchDeptId"));
			String dispatchDept = StaticMethod.null2String(request.getParameter("tmpDispatchDept"));
			bufferpage.append("&dispatchDeptId=" + dispatchDeptId);
			bufferpage.append("&dispatchDept=" + dispatchDept);
			if (!dispatchDeptId.equals("")) {
				buffer.append(" and dispatchDeptId='" + dispatchDeptId + "'");
			}else{
				if(!dispatchDeptId.equals("")){
					buffer.append(" and dispatchDept like '%" + dispatchDept + "%'");
				}
			}
			
			String dispatcherId = StaticMethod.null2String(request.getParameter("tmpDispatcherId"));
			String dispatcher = StaticMethod.null2String(request.getParameter("tmpDispatcher"));
			bufferpage.append("&dispatcherId=" + dispatcherId + "");
			bufferpage.append("&dispatcher=" + dispatcher);
			if (!dispatcherId.equals("")) {
				buffer.append(" and dispatcherId='" + dispatcherId + "'");
			}else{
				if(!dispatcher.equals("")){
					buffer.append(" and dispatcher like '%" + dispatcher + "%'");
				}
			}
			
			whereStr = buffer.toString();
			request.setAttribute("whereStr", whereStr);
			String pageStr = bufferpage.toString();
			ITawRmDispatchRecordManager mgr = (ITawRmDispatchRecordManager) getBean("ITawRmDispatchRecordManager");
			Map map = (Map) mgr.getTawRmDispatchRecords(pageIndex,new Integer(length), whereStr);
			List list = (List) map.get("result");
			String url = request.getContextPath() + "/duty"+ mapping.getPath() + ".do";
			//			 每页显示条数
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
			int size = ((Integer)map.get("total")).intValue();
			String pagerHeader = MemoPage.generate(pageIndex.intValue(), size, length, url,pageStr);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute(DutyConstacts.TAW_RM_DISPATCHRECORDLIST, list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("list");
	}


	public ActionForward dispatchRecordSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List roomList=this.getRoomList(request);
		request.setAttribute("roomList", roomList);
		return mapping.findForward("dispatchRecordSearch");
	}
	
	// 删除 返回查询页面
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages messages = new ActionMessages();
		TawRmDispatchRecordForm tawRmDispatchRecordForm = (TawRmDispatchRecordForm) form;
		try {
			// Exceptions are caught by ActionExceptionHandler
			ITawRmDispatchRecordManager mgr=(ITawRmDispatchRecordManager)getBean("ITawRmDispatchRecordManager");
			mgr.removeTawRmDispatchRecord(tawRmDispatchRecordForm.getId());

			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawRmDispatchRecordForm.deleted"));

			// save messages in session, so they'll survive the redirect
			saveMessages(request.getSession(), messages);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchList2(mapping, form, request, response);
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
