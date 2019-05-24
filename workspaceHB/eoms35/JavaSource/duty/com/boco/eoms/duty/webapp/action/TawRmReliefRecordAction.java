
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

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.duty.bo.TawRmAssignworkBO;
import com.boco.eoms.duty.model.TawRmReliefRecord;
import com.boco.eoms.duty.service.ITawRmReliefRecordManager;
import com.boco.eoms.duty.util.DutyConstacts;
import com.boco.eoms.duty.webapp.form.TawRmReliefRecordForm;
import com.boco.eoms.workbench.memo.util.MemoPage;
import com.boco.eoms.common.util.Pager;
import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.cptroom.service.ITawSystemCptroomManager;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivRegion;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.util.JSONUtil;

/**
 * Action class to handle CRUD on a TawRmReliefRecord object
 * @struts.action name="tawRmReliefRecordForm" path="/tawRmReliefRecords" scope="request"
 *  validate="false" parameter="method" input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * 
 * @struts.action-forward name="main" 
 *  path="/WEB-INF/pages/tawRmReliefRecord/tawRmReliefRecordTree.jsp" contextRelative="true"
 *
 * @struts.action-forward name="edit" 
 *  path="/WEB-INF/pages/tawRmReliefRecord/tawRmReliefRecordForm.jsp" contextRelative="true"
 *
 * @struts.action-forward name="list" 
 *  path="/WEB-INF/pages/tawRmReliefRecord/tawRmReliefRecordList.jsp" contextRelative="true"
 */
public final class TawRmReliefRecordAction extends BaseAction {
	private com.boco.eoms.db.util.ConnectionPool ds=com.boco.eoms.db.util.ConnectionPool.getInstance();
//	 定义页数长度
	private static int PAGE_LENGTH = 15;
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
         return mapping.findForward("search");
           //return null;
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

		ITawRmReliefRecordManager mgr = (ITawRmReliefRecordManager) getBean("ItawRmReliefRecordManager");
		JSONArray json = mgr.xGetChildNodes(nodeId);

		JSONUtil.print(response, json.toString());
	}

    /**
	 * ajax保存
	 */
    public void xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawRmReliefRecordForm tawRmReliefRecordForm = (TawRmReliefRecordForm) form;

		ITawRmReliefRecordManager mgr = (ITawRmReliefRecordManager) getBean("ItawRmReliefRecordManager");
		TawRmReliefRecord tawRmReliefRecord = (TawRmReliefRecord) convert(tawRmReliefRecordForm);
		mgr.saveTawRmReliefRecord(tawRmReliefRecord);
		// JSONUtil.print(response, "操作成功");
	}
    /**
	 * 根据模块或功能的编码，删除该对象
	 */
    public void xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawRmReliefRecordForm tawRmReliefRecordForm = (TawRmReliefRecordForm) form;

        ITawRmReliefRecordManager mgr = (ITawRmReliefRecordManager) getBean("ItawRmReliefRecordManager");
		mgr.removeTawRmReliefRecord(tawRmReliefRecordForm.getId());

	}
    /**
	 * ajax请求修改某节点的详细信息
	 */
    public String xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawRmReliefRecordForm tawRmReliefRecordForm = (TawRmReliefRecordForm) form;

		if (tawRmReliefRecordForm.getId() != null) {
			ITawRmReliefRecordManager mgr = (ITawRmReliefRecordManager) getBean("ItawRmReliefRecordManager");
			TawRmReliefRecord tawRmReliefRecord = (TawRmReliefRecord) convert(tawRmReliefRecordForm);

			mgr.saveTawRmReliefRecord(tawRmReliefRecord);
		   //mgr.updateTawRmReliefRecord(tawRmReliefRecord);
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
		ITawRmReliefRecordManager mgr = (ITawRmReliefRecordManager) getBean("ItawRmReliefRecordManager");
		TawRmReliefRecord tawRmReliefRecord = mgr.getTawRmReliefRecord(_strId);

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(tawRmReliefRecord);

		JSONUtil.print(response, jsonRoot.toString());
	}
	

    public ActionForward edit(ActionMapping mapping, ActionForm form,
         	HttpServletRequest request,HttpServletResponse response)
        	throws Exception {
		TawRmReliefRecordForm tawRmReliefRecordForm=(TawRmReliefRecordForm)form;
		try {
			// if an id is passed in, look up the user - otherwise
			// don't do anything - user is doing an add
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
			//String workSerial=sessionform.getWorkSerial();
			if (sessionform == null) {
				return mapping.findForward("timeout");
			}

			// 判断是否值班
			//if (workSerial.equals("0")) {
			//	return mapping.findForward("notonduty");
			//}
			String id = StaticMethod.nullObject2String(request
					.getParameter("id"));
			HttpSession session=request.getSession();
			String handoverId=StaticMethod.nullObject2String(session.getAttribute("myHandoverId"));
			String successorId=StaticMethod.nullObject2String(session.getAttribute("mySuccessorId"));
			String roomId=StaticMethod.nullObject2String(session.getAttribute("myRoomId"));
			String startTime=StaticMethod.nullObject2String(session.getAttribute("myStartTime"));
			String endTime=StaticMethod.nullObject2String(session.getAttribute("myEndTime"));
			String time=StaticMethod.nullObject2String(session.getAttribute("myTime"));
			session.removeAttribute("myHandoverId");
			session.removeAttribute("mySuccessorId");
			session.removeAttribute("myRoomId");
			session.removeAttribute("myStartTime");
			session.removeAttribute("myEndTime");
			session.removeAttribute("myTime");
			
			if(tawRmReliefRecordForm.getId()!=null){
				ITawRmReliefRecordManager mgr=(ITawRmReliefRecordManager)getBean("ITawRmReliefRecordManager");
				if (id != null && !id.equals("")) {
					TawRmReliefRecord tawRmReliefRecord=mgr.getTawRmReliefRecord(id);
					if(tawRmReliefRecord!=null){
						ITawSystemCptroomManager roomMgr = (ITawSystemCptroomManager) getBean("ItawSystemCptroomManager");
						ITawSystemUserManager userManager=(ITawSystemUserManager)getBean("itawSystemUserManager");
						//ITawSystemDictTypeManager dictManager=(ITawSystemDictTypeManager)getBean("ItawSystemDictTypeManager");
						String handoverName=userManager.getUserByuserid(tawRmReliefRecord.getHandoverId()).getUsername();
						String successorName=userManager.getUserByuserid(tawRmReliefRecord.getSuccessorId()).getUsername();
						String roomName=roomMgr.getTawSystemCptroomName(new Integer(tawRmReliefRecord.getRoomId()));
						String nuiteFlag=tawRmReliefRecord.getNuiteFlag();
						//System.out.println("tawRmReliefRecord.getNuiteFlag()===="+tawRmReliefRecord.getNuiteFlag());
						//String nuiteFlagName=dictManager.getDictByDictId(tawRmReliefRecord.getNuiteFlag()).getDictName();
						tawRmReliefRecordForm =(TawRmReliefRecordForm)convert(tawRmReliefRecord);
						tawRmReliefRecordForm.setHandoverName(handoverName);
						tawRmReliefRecordForm.setSuccessorName(successorName);
						tawRmReliefRecordForm.setRoomName(roomName);
						request.setAttribute("nuiteFlag", nuiteFlag);
						tawRmReliefRecordForm.setNuiteFlag(nuiteFlag);
						tawRmReliefRecordForm.setTmpHandoverId(handoverId);
						tawRmReliefRecordForm.setTmpSuccessorId(successorId);
						tawRmReliefRecordForm.setTmpRoomId(roomId);
						tawRmReliefRecordForm.setStartTime(startTime);
						tawRmReliefRecordForm.setEndTime(endTime);
						tawRmReliefRecordForm.setTmpTime(time);
					}
				}
				updateFormBean(mapping, request, tawRmReliefRecordForm);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
             return mapping.findForward("edit");
    }

    
	/**
	 * 
	 * @see 根据条件查询交接班记录
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
			TawRmReliefRecord tawRmReliefRecord=null;
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
			.getSession().getAttribute("sessionform");
			final String workSerial = sessionform.getWorkSerial();
			// 得到查询的条件
			String whereStr = " where workSerial = '" + workSerial + "'";
			List tawRmReliefRecordList = new ArrayList();
			ITawRmReliefRecordManager mgr=(ITawRmReliefRecordManager)getBean("ITawRmReliefRecordManager");
			Map map = (Map) mgr.getTawRmReliefRecords(new Integer(offset),new Integer(length), whereStr);
			List list = (List) map.get("result");
			// 分页
			for (int i = offset; i < (length + offset) && i < list.size(); i++) {
				tawRmReliefRecord=(TawRmReliefRecord)list.get(i);
				tawRmReliefRecordList.add(tawRmReliefRecord);
			}
			String url = request.getContextPath() + "/duty"+ mapping.getPath() + ".do";
			int size = Integer.parseInt((StaticMethod.null2String((String) map
					.get("total"))));
			String pagerHeader = Pager.generate(offset, size, length, url,
					"method=search");
			request.setAttribute("pagerHeader", pagerHeader);
			Iterator listIt = tawRmReliefRecordList.iterator();
			request.setAttribute("listIt", listIt);
			request.setAttribute("resultSize", map.get("total"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("list");
	}

	/**
	 * 
	 * @see 根据条件查询交接班情况
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
					DutyConstacts.TAW_RM_RELIEFRECORDLIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(GenericValidator
					.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
					: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
			
			TawRmReliefRecordForm tawRmReliefRecordForm=(TawRmReliefRecordForm)form;
			String handoverId=StaticMethod.null2String(tawRmReliefRecordForm.getTmpHandoverId());
			String successorId = StaticMethod.null2String(tawRmReliefRecordForm.getTmpSuccessorId());
			String roomId = StaticMethod.null2String(tawRmReliefRecordForm.getTmpRoomId());
			String startTime = StaticMethod.null2String(tawRmReliefRecordForm.getStartTime());
			String endTime = StaticMethod.null2String(tawRmReliefRecordForm.getEndTime());
			String time = StaticMethod.null2String(tawRmReliefRecordForm.getTmpTime());
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
			final String userId = sessionform.getUserid();
			whereStr = request.getParameter("whereStr");
			StringBuffer buffer = new StringBuffer();
			StringBuffer bufferpage = new StringBuffer();
			buffer.append(" where 1=1");
			bufferpage.append("method=searchList");
			
			bufferpage.append("&handoverId=" + handoverId + "");
			if (!handoverId.equals("")) {
				buffer.append(" and handoverId = '" + handoverId + "'");

			}
			
			bufferpage.append("&successorId=" + successorId + "");
			if (!successorId.equals("")) {
				buffer.append(" and successorId = '" + successorId + "'");
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
			
			bufferpage.append("&time=" + time + "");
			if (!time.equals("")) {
				buffer.append(" and time='" + time + "'");
			}
			//TawRmReliefRecordBO bo=new TawRmReliefRecordBO(ds);
			//bo.insertReliefRecord(handoverId,successorId,roomId,time,"aaaaaaaaaaaaaaaa","1030101",userId,workSerial);
			whereStr = buffer.toString();
			request.setAttribute("whereStr", whereStr);
			String pageStr = bufferpage.toString();
			ITawRmReliefRecordManager mgr = (ITawRmReliefRecordManager) getBean("ITawRmReliefRecordManager");
			Map map = (Map) mgr.getTawRmReliefRecords(pageIndex,new Integer(length), whereStr);
			List list = (List) map.get("result");
			String url = request.getContextPath() + "/duty"+ mapping.getPath() + ".do";
			//			 每页显示条数
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
			int size = ((Integer)map.get("total")).intValue();
			String pagerHeader = MemoPage.generate(pageIndex.intValue(), size, length, url,pageStr);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute(DutyConstacts.TAW_RM_RELIEFRECORDLIST, list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("list");
	}
	
	/**
	 * 
	 * @see 根据条件查询交接班情况
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
					DutyConstacts.TAW_RM_RELIEFRECORDLIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(GenericValidator
					.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
					: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
			
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
			final String userId = sessionform.getUserid();
			whereStr = request.getParameter("whereStr");
			StringBuffer buffer = new StringBuffer();
			StringBuffer bufferpage = new StringBuffer();
			buffer.append(" where 1=1");
			bufferpage.append("method=searchList");
			
			String handoverId = StaticMethod.null2String(request.getParameter("tmpHandoverId"));
			bufferpage.append("&handoverId=" + handoverId + "");
			if (!handoverId.equals("")) {
				buffer.append(" and handoverId = '" + handoverId + "'");

			}
			
			String successorId = StaticMethod.null2String(request.getParameter("tmpSuccessorId"));
			bufferpage.append("&successorId=" + successorId + "");
			if (!successorId.equals("")) {
				buffer.append(" and successorId = '" + successorId + "'");
			}
			
			String roomId = StaticMethod.null2String(request.getParameter("tmpRoomId"));
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
			
			String time = StaticMethod.null2String(request.getParameter("tmpTime"));
			bufferpage.append("&time=" + time + "");
			if (!time.equals("")) {
				buffer.append(" and time='" + time + "'");
			}
			//TawRmReliefRecordBO bo=new TawRmReliefRecordBO(ds);
			//bo.insertReliefRecord(handoverId,successorId,roomId,time,"aaaaaaaaaaaaaaaa","1030101",userId,workSerial);
			whereStr = buffer.toString();
			request.setAttribute("whereStr", whereStr);
			String pageStr = bufferpage.toString();
			ITawRmReliefRecordManager mgr = (ITawRmReliefRecordManager) getBean("ITawRmReliefRecordManager");
			Map map = (Map) mgr.getTawRmReliefRecords(pageIndex,new Integer(length), whereStr);
			List list = (List) map.get("result");
			String url = request.getContextPath() + "/duty"+ mapping.getPath() + ".do";
			//			 每页显示条数
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
			int size = ((Integer)map.get("total")).intValue();
			String pagerHeader = MemoPage.generate(pageIndex.intValue(), size, length, url,pageStr);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute(DutyConstacts.TAW_RM_RELIEFRECORDLIST, list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("list");
	}


	public ActionForward reliefRecordSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List roomList=this.getRoomList(request);
		request.setAttribute("roomList", roomList);
		return mapping.findForward("reliefRecordSearch");
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
