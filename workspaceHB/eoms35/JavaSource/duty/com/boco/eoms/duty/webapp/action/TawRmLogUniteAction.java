
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
import com.boco.eoms.duty.dao.TawRmAssignSubDAO;
import com.boco.eoms.duty.dao.TawRmAssignworkDAO;
import com.boco.eoms.duty.model.TawRmAssignwork;
import com.boco.eoms.duty.model.TawRmDispatchRecord;
import com.boco.eoms.duty.model.TawRmLoanRecord;
import com.boco.eoms.duty.model.TawRmLogUnite;
import com.boco.eoms.duty.model.TawRmPlanContent;
import com.boco.eoms.duty.model.TawRmReliefRecord;
import com.boco.eoms.duty.model.TawRmVisitRecord;
import com.boco.eoms.duty.model.TawRmWorkorderRecord;
import com.boco.eoms.duty.service.ITawRmDispatchRecordManager;
import com.boco.eoms.duty.service.ITawRmLoanRecordManager;
import com.boco.eoms.duty.service.ITawRmLogUniteManager;
import com.boco.eoms.duty.service.ITawRmPlanContentManager;
import com.boco.eoms.duty.service.ITawRmReliefRecordManager;
import com.boco.eoms.duty.service.ITawRmVisitRecordManager;
import com.boco.eoms.duty.service.ITawRmWorkorderRecordManager;
import com.boco.eoms.duty.util.DutyConstacts;
import com.boco.eoms.duty.webapp.form.TawRmLogUniteForm;
import com.boco.eoms.workbench.memo.model.TawWorkbenchMemo;
import com.boco.eoms.workbench.memo.service.ITawWorkbenchMemoManager;
import com.boco.eoms.workbench.memo.util.MemoPage;

import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivRegion;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.util.JSONUtil;

/**
 * Action class to handle CRUD on a TawRmLogUnite object
 * @struts.action name="tawRmLogUniteForm" path="/tawRmLogUnites" scope="request"
 *  validate="false" parameter="method" input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * 
 * @struts.action-forward name="main" 
 *  path="/WEB-INF/pages/tawRmLogUnite/tawRmLogUniteTree.jsp" contextRelative="true"
 *
 * @struts.action-forward name="edit" 
 *  path="/WEB-INF/pages/tawRmLogUnite/tawRmLogUniteForm.jsp" contextRelative="true"
 *
 * @struts.action-forward name="list" 
 *  path="/WEB-INF/pages/tawRmLogUnite/tawRmLogUniteList.jsp" contextRelative="true"
 */
public final class TawRmLogUniteAction extends BaseAction {
	// 定义页数长度
	private static int PAGE_LENGTH = 100;
	private static int offset=0;
	private com.boco.eoms.db.util.ConnectionPool ds=com.boco.eoms.db.util.ConnectionPool.getInstance();
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

		ITawRmLogUniteManager mgr = (ITawRmLogUniteManager) getBean("ItawRmLogUniteManager");
		JSONArray json = mgr.xGetChildNodes(nodeId);

		JSONUtil.print(response, json.toString());
	}

    /**
	 * ajax保存
	 */
    public void xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawRmLogUniteForm tawRmLogUniteForm = (TawRmLogUniteForm) form;

		ITawRmLogUniteManager mgr = (ITawRmLogUniteManager) getBean("ItawRmLogUniteManager");
		TawRmLogUnite tawRmLogUnite = (TawRmLogUnite) convert(tawRmLogUniteForm);
		mgr.saveTawRmLogUnite(tawRmLogUnite);
		// JSONUtil.print(response, "操作成功");
	}
    /**
	 * 根据模块或功能的编码，删除该对象
	 */
    public void xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawRmLogUniteForm tawRmLogUniteForm = (TawRmLogUniteForm) form;

        ITawRmLogUniteManager mgr = (ITawRmLogUniteManager) getBean("ItawRmLogUniteManager");
		mgr.removeTawRmLogUnite(tawRmLogUniteForm.getId());

	}
    /**
	 * ajax请求修改某节点的详细信息
	 */
    public String xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawRmLogUniteForm tawRmLogUniteForm = (TawRmLogUniteForm) form;

		if (tawRmLogUniteForm.getId() != null) {
			ITawRmLogUniteManager mgr = (ITawRmLogUniteManager) getBean("ItawRmLogUniteManager");
			TawRmLogUnite tawRmLogUnite = (TawRmLogUnite) convert(tawRmLogUniteForm);

			mgr.saveTawRmLogUnite(tawRmLogUnite);
		   //mgr.updateTawRmLogUnite(tawRmLogUnite);
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
		ITawRmLogUniteManager mgr = (ITawRmLogUniteManager) getBean("ItawRmLogUniteManager");
		TawRmLogUnite tawRmLogUnite = mgr.getTawRmLogUnite(_strId);

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(tawRmLogUnite);

		JSONUtil.print(response, jsonRoot.toString());
	}
	
    /**
	 * 
	 * @see 保存已合并日志
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
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		String roomId=sessionform.getRoomId();
		TawRmLogUniteForm tawRmLogUniteForm=(TawRmLogUniteForm)form;
		ITawRmLogUniteManager logUniteMgr=(ITawRmLogUniteManager)getBean("ITawRmLogUniteManager");
		TawRmLogUnite tawRmLogUnite=(TawRmLogUnite)convert(tawRmLogUniteForm);
		tawRmLogUnite.setRoomId(roomId);
		logUniteMgr.saveTawRmLogUnite(tawRmLogUnite);
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("TawRmLogUnite.added"));
		// save messages in session to survive a redirect
		saveMessages(request.getSession(), messages);
		return mapping.findForward("successok");
	}

	public ActionForward toLogUnite(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int length = PAGE_LENGTH;
		try{
			ITawRmLogUniteManager logUniteMgr=(ITawRmLogUniteManager)getBean("ITawRmLogUniteManager");
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
			String workSerial=sessionform.getWorkSerial();
			if (sessionform == null) {
				return mapping.findForward("timeout");
			}

			// 判断是否值班
			if (workSerial.equals("0")) {
				return mapping.findForward("notonduty1");
			}
			//String roomId=sessionform.getRoomId();
			String whereStr=" where workSerial = '"+ workSerial+"'";
			Map map=logUniteMgr.getTawRmLogUnites(new Integer(offset),new Integer(length),whereStr);
			List logUniteList=(List) map.get("result");
			if(logUniteList!=null&&logUniteList.size()>0){
				return mapping.findForward("warning");
			}
			TawRmLogUniteForm tawRmLogUniteForm=(TawRmLogUniteForm)form;
			TawRmAssignSubDAO tawRmAssignSubDAO = new TawRmAssignSubDAO(ds);
			TawRmAssignworkDAO tawRmAssignworkDAO=new TawRmAssignworkDAO(ds);
			ITawSystemUserManager userMgr=(ITawSystemUserManager)getBean("itawSystemUserManager");
			String nuiteUserId=sessionform.getUserid();
			List userIdList=tawRmAssignSubDAO.getUserIdListByWorkSerial(workSerial);
			String userNameListStr="";
			if(userIdList!=null&&userIdList.size()>0){
				for(int i=0;i<userIdList.size()-1;i++){
					String userId=(String)userIdList.get(i);
					String userName="";
					if(userId!=null){
						userName=StaticMethod.null2String(userMgr.getUserByuserid(userId).getUsername());
					}
					userNameListStr=userNameListStr+userName+",";
				}
				String userId=(String)userIdList.get(userIdList.size()-1);
				String userName=StaticMethod.null2String(userMgr.getUserByuserid(userId).getUsername());
				userNameListStr=userNameListStr+userName;
			}
			TawRmAssignwork tawRmAssignwork=tawRmAssignworkDAO.retrieve(Integer.parseInt(workSerial));
			String start_time=tawRmAssignwork.getStarttimeDefined();
			String end_time=tawRmAssignwork.getEndtimeDefined();
			String planContentStr=getPlanContentStr(userIdList,workSerial);
			String workOrderStr=getWorkOrderStr(userIdList,workSerial);
			String workbenchMemoStr=getWorkbenchMemoStr(userIdList,start_time,end_time);
			String dispatchRecordStr=getDispatchRecordStr(userIdList,workSerial);
			String visitRecordStr=getVisitRecordStr(userIdList,workSerial);
			String loanRecordStr=getLoanRecordStr(userIdList,workSerial);
			String reliefRecordStr=getReliefRecordStr(userIdList,workSerial);
			tawRmLogUniteForm.setWorkerNames(userNameListStr);
			tawRmLogUniteForm.setPlanContent(planContentStr);
			tawRmLogUniteForm.setWorkOrder(workOrderStr);
			tawRmLogUniteForm.setWorkbenchMemo(workbenchMemoStr);
			tawRmLogUniteForm.setDispatchRecord(dispatchRecordStr);
			tawRmLogUniteForm.setVisitRecord(visitRecordStr);
			tawRmLogUniteForm.setLoanRecord(loanRecordStr);
			tawRmLogUniteForm.setReliefRecord(reliefRecordStr);
			tawRmLogUniteForm.setBeginTime(start_time);
			tawRmLogUniteForm.setEndTime(end_time);
			tawRmLogUniteForm.setUserId(nuiteUserId);
			tawRmLogUniteForm.setWorkSerial(workSerial);
			//tawRmLogUniteForm.setRoomId(roomId);
			updateFormBean(mapping, request, tawRmLogUniteForm);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("edit");
	}
	
	private String getPlanContentStr(List userIdList,String workSerial){
		 
		int length = PAGE_LENGTH;
		ITawSystemUserManager userMgr=(ITawSystemUserManager)getBean("itawSystemUserManager");
		ITawRmPlanContentManager planContentMgr=(ITawRmPlanContentManager)getBean("ITawRmPlanContentManager");
		StringBuffer planContent=new StringBuffer();
		if(userIdList!=null&&userIdList.size()>0){
			for(int i=0;i<userIdList.size();i++){
				String userId=(String)userIdList.get(i);
				String userName=userMgr.getUserByuserid(userId).getUsername();
				String whereStr=" where workSerial = '"+ workSerial + "' and userId='"+userId+"'";
				Map map=planContentMgr.getTawRmPlanContents(new Integer(offset),new Integer(length),whereStr);
				List planContentList=(List) map.get("result");
				planContent.append(userName+":");
				planContent.append("{");
				if(planContentList!=null&&planContentList.size()>0){
					for(int j=0;j<planContentList.size()-1;j++){
						TawRmPlanContent planContentObj=(TawRmPlanContent)planContentList.get(j);
						planContent.append("(");
						planContent.append(planContentObj.getDeptId());
						planContent.append("||");
						planContent.append(planContentObj.getMonth());
						planContent.append("||");
						planContent.append(planContentObj.getContent());
						planContent.append("||");
						planContent.append(planContentObj.getMonthplanName());
						planContent.append("||");
						planContent.append(planContentObj.getRoomId());
						planContent.append(")");
						planContent.append(",");
					}
					TawRmPlanContent planContentObj=(TawRmPlanContent)planContentList.get(planContentList.size()-1);
					planContent.append("(");
					planContent.append(planContentObj.getDeptId());
					planContent.append("||");
					planContent.append(planContentObj.getMonth());
					planContent.append("||");
					planContent.append(planContentObj.getContent());
					planContent.append("||");
					planContent.append(planContentObj.getMonthplanName());
					planContent.append("||");
					planContent.append(planContentObj.getRoomId());
					planContent.append(")");
				}
				planContent.append("}");
			}
		}
		return planContent.toString();
	}
	
	private String getWorkOrderStr(List userIdList,String workSerial){
		 
		int length = PAGE_LENGTH;
		ITawSystemUserManager userMgr=(ITawSystemUserManager)getBean("itawSystemUserManager");
		ITawRmWorkorderRecordManager workorderRecordMgr=(ITawRmWorkorderRecordManager)getBean("ITawRmWorkorderRecordManager");
		StringBuffer workorderRecord=new StringBuffer();
		if(userIdList!=null&&userIdList.size()>0){
			for(int i=0;i<userIdList.size();i++){
				String userId=(String)userIdList.get(i);
				String userName=userMgr.getUserByuserid(userId).getUsername();
				String whereStr=" where workSerial = '"+ workSerial + "' and userId='"+userId+"'";
				Map map=workorderRecordMgr.getTawRmWorkorderRecords(new Integer(offset),new Integer(length),whereStr);
				List workorderRecordList=(List) map.get("result");
				workorderRecord.append(userName+":");
				workorderRecord.append("{");
				if(workorderRecordList!=null&&workorderRecordList.size()>0){
					for(int j=0;j<workorderRecordList.size()-1;j++){
						TawRmWorkorderRecord orderRecordObj=(TawRmWorkorderRecord)workorderRecordList.get(j);
						workorderRecord.append("(");
						workorderRecord.append(orderRecordObj.getWorkOrderId());
						workorderRecord.append("||");
						workorderRecord.append(orderRecordObj.getTitle());
						workorderRecord.append("||");
						workorderRecord.append(orderRecordObj.getReceiver());
						workorderRecord.append("||");
						workorderRecord.append(orderRecordObj.getSender());
						workorderRecord.append("||");
						workorderRecord.append(orderRecordObj.getReplyTime());
						workorderRecord.append("||");
						workorderRecord.append(orderRecordObj.getReceiveTime());
						workorderRecord.append("||");
						workorderRecord.append(orderRecordObj.getOrderType());
						workorderRecord.append("||");
						workorderRecord.append(orderRecordObj.getOrderState());
						workorderRecord.append(")");
						workorderRecord.append(",");
					}
					TawRmWorkorderRecord orderRecordObj=(TawRmWorkorderRecord)workorderRecordList.get(workorderRecordList.size()-1);
					workorderRecord.append("(");
					workorderRecord.append(orderRecordObj.getWorkOrderId());
					workorderRecord.append("||");
					workorderRecord.append(orderRecordObj.getTitle());
					workorderRecord.append("||");
					workorderRecord.append(orderRecordObj.getReceiver());
					workorderRecord.append("||");
					workorderRecord.append(orderRecordObj.getSender());
					workorderRecord.append("||");
					workorderRecord.append(orderRecordObj.getReplyTime());
					workorderRecord.append("||");
					workorderRecord.append(orderRecordObj.getReceiveTime());
					workorderRecord.append("||");
					workorderRecord.append(orderRecordObj.getOrderType());
					workorderRecord.append("||");
					workorderRecord.append(orderRecordObj.getOrderState());
					workorderRecord.append(")");
				}
				workorderRecord.append("}");
			}
		}
		return workorderRecord.toString();
	}
	
	private String getWorkbenchMemoStr(List userIdList,String startTime,String endTime){
		 
		int length = PAGE_LENGTH;
		ITawSystemUserManager userMgr=(ITawSystemUserManager)getBean("itawSystemUserManager");
		ITawWorkbenchMemoManager workbenchMemoMgr=(ITawWorkbenchMemoManager)getBean("ItawWorkbenchMemoManager");
		StringBuffer workbenchMemo=new StringBuffer();
		if(userIdList!=null&&userIdList.size()>0){
			for(int i=0;i<userIdList.size();i++){
				String userId=(String)userIdList.get(i);
				String userName=userMgr.getUserByuserid(userId).getUsername();
				String whereStr=" where creattime >= '"+ startTime + "' and creattime<='"+endTime+ "' and userid='"+userId+"'";
				Map map=workbenchMemoMgr.getTawWorkbenchMemos(new Integer(offset),new Integer(length),whereStr);
				List workbenchMemoList=(List) map.get("result");
				workbenchMemo.append(userName+":");
				workbenchMemo.append("{");
				if(workbenchMemoList!=null&&workbenchMemoList.size()>0){
					for(int j=0;j<workbenchMemoList.size()-1;j++){
						TawWorkbenchMemo workbenchMemoObj=(TawWorkbenchMemo)workbenchMemoList.get(j);
						workbenchMemo.append("(");
						workbenchMemo.append(workbenchMemoObj.getTitle());
						workbenchMemo.append("||");
						workbenchMemo.append(workbenchMemoObj.getContent());
						workbenchMemo.append("||");
						workbenchMemo.append(workbenchMemoObj.getCreattime());
						workbenchMemo.append("||");
						workbenchMemo.append(workbenchMemoObj.getLevel());
						workbenchMemo.append("||");
						workbenchMemo.append(workbenchMemoObj.getSendflag());
						workbenchMemo.append("||");
						workbenchMemo.append(workbenchMemoObj.getReciever());
						workbenchMemo.append("||");
						workbenchMemo.append(workbenchMemoObj.getSendmanner());
						workbenchMemo.append(")");
						workbenchMemo.append(",");
					}
					TawWorkbenchMemo workbenchMemoObj=(TawWorkbenchMemo)workbenchMemoList.get(workbenchMemoList.size()-1);
					workbenchMemo.append("(");
					workbenchMemo.append(workbenchMemoObj.getTitle());
					workbenchMemo.append("||");
					workbenchMemo.append(workbenchMemoObj.getContent());
					workbenchMemo.append("||");
					workbenchMemo.append(workbenchMemoObj.getCreattime());
					workbenchMemo.append("||");
					workbenchMemo.append(workbenchMemoObj.getLevel());
					workbenchMemo.append("||");
					workbenchMemo.append(workbenchMemoObj.getSendflag());
					workbenchMemo.append("||");
					workbenchMemo.append(workbenchMemoObj.getReciever());
					workbenchMemo.append("||");
					workbenchMemo.append(workbenchMemoObj.getSendmanner());
					workbenchMemo.append(")");
				}
				workbenchMemo.append("}");
			}
		}
		return workbenchMemo.toString();
	}
	
	private String getDispatchRecordStr(List userIdList,String workSerial){
		int offset=0;
		int length = PAGE_LENGTH;
		ITawSystemUserManager userMgr=(ITawSystemUserManager)getBean("itawSystemUserManager");
		ITawRmDispatchRecordManager dispatchRecordMgr=(ITawRmDispatchRecordManager)getBean("ITawRmDispatchRecordManager");
		StringBuffer dispatchRecord=new StringBuffer();
		if(userIdList!=null&&userIdList.size()>0){
			for(int i=0;i<userIdList.size();i++){
				String userId=(String)userIdList.get(i);
				String userName=userMgr.getUserByuserid(userId).getUsername();
				String whereStr=" where workSerial = '"+ workSerial + "' and userId='"+userId+"'";
				System.out.println("whereStr=========="+whereStr);
				Map map=dispatchRecordMgr.getTawRmDispatchRecords(new Integer(offset),new Integer(length),whereStr);
				List dispatchRecordList=(List) map.get("result");
				dispatchRecord.append(userName+":");
				dispatchRecord.append("{");
				if(dispatchRecordList!=null&&dispatchRecordList.size()>0){
					for(int j=0;j<dispatchRecordList.size()-1;j++){
						TawRmDispatchRecord dispatchRecordObj=(TawRmDispatchRecord)dispatchRecordList.get(j);
						dispatchRecord.append("(");
						dispatchRecord.append(dispatchRecordObj.getFileName());
						dispatchRecord.append("||");
						dispatchRecord.append(dispatchRecordObj.getFileSource());
						dispatchRecord.append("||");
						dispatchRecord.append(dispatchRecordObj.getFileProperty());
						dispatchRecord.append("||");
						dispatchRecord.append(dispatchRecordObj.getTime());
						dispatchRecord.append("||");
						dispatchRecord.append(dispatchRecordObj.getDispatchDeptId());
						dispatchRecord.append("||");
						dispatchRecord.append(dispatchRecordObj.getDispatchDept());
						dispatchRecord.append("||");
						dispatchRecord.append(dispatchRecordObj.getDispatcherId());
						dispatchRecord.append("||");
						dispatchRecord.append(dispatchRecordObj.getDispatcher());
						dispatchRecord.append("||");
						dispatchRecord.append(dispatchRecordObj.getReceiver());
						dispatchRecord.append("||");
						dispatchRecord.append(dispatchRecordObj.getExcuteRequest());
						dispatchRecord.append("||");
						dispatchRecord.append(dispatchRecordObj.getRemark());
						dispatchRecord.append(")");
						dispatchRecord.append(",");
					}
					TawRmDispatchRecord dispatchRecordObj=(TawRmDispatchRecord)dispatchRecordList.get(dispatchRecordList.size()-1);
					dispatchRecord.append("(");
					dispatchRecord.append(dispatchRecordObj.getFileName());
					dispatchRecord.append("||");
					dispatchRecord.append(dispatchRecordObj.getFileSource());
					dispatchRecord.append("||");
					dispatchRecord.append(dispatchRecordObj.getFileProperty());
					dispatchRecord.append("||");
					dispatchRecord.append(dispatchRecordObj.getTime());
					dispatchRecord.append("||");
					dispatchRecord.append(dispatchRecordObj.getDispatchDeptId());
					dispatchRecord.append("||");
					dispatchRecord.append(dispatchRecordObj.getDispatchDept());
					dispatchRecord.append("||");
					dispatchRecord.append(dispatchRecordObj.getDispatcherId());
					dispatchRecord.append("||");
					dispatchRecord.append(dispatchRecordObj.getDispatcher());
					dispatchRecord.append("||");
					dispatchRecord.append(dispatchRecordObj.getReceiver());
					dispatchRecord.append("||");
					dispatchRecord.append(dispatchRecordObj.getExcuteRequest());
					dispatchRecord.append("||");
					dispatchRecord.append(dispatchRecordObj.getRemark());
					dispatchRecord.append(")");
				}
				dispatchRecord.append("}");
			}
		}
		return dispatchRecord.toString();
	}
	
	private String getVisitRecordStr(List userIdList,String workSerial){
		 
		int length = PAGE_LENGTH;
		ITawSystemUserManager userMgr=(ITawSystemUserManager)getBean("itawSystemUserManager");
		ITawRmVisitRecordManager visitRecordMgr=(ITawRmVisitRecordManager)getBean("ITawRmVisitRecordManager");
		StringBuffer visitRecord=new StringBuffer();
		if(userIdList!=null&&userIdList.size()>0){
			for(int i=0;i<userIdList.size();i++){
				String userId=(String)userIdList.get(i);
				String userName=userMgr.getUserByuserid(userId).getUsername();
				String whereStr=" where workSerial = '"+ workSerial + "' and userId='"+userId+"'";
				Map map=visitRecordMgr.getTawRmVisitRecords(new Integer(offset),new Integer(length),whereStr);
				List visitRecordList=(List) map.get("result");
				visitRecord.append(userName+":");
				visitRecord.append("{");
				if(visitRecordList!=null&&visitRecordList.size()>0){
					for(int j=0;j<visitRecordList.size()-1;j++){
						TawRmVisitRecord visitRecordObj=(TawRmVisitRecord)visitRecordList.get(j);
						visitRecord.append("(");
						visitRecord.append(visitRecordObj.getVisitorName());
						visitRecord.append("||");
						visitRecord.append(visitRecordObj.getCompany());
						visitRecord.append("||");
						visitRecord.append(visitRecordObj.getVisitTime());
						visitRecord.append("||");
						visitRecord.append(visitRecordObj.getLeftTime());
						visitRecord.append("||");
						visitRecord.append(visitRecordObj.getReason());
						visitRecord.append("||");
						visitRecord.append(visitRecordObj.getReceiver());
						visitRecord.append(")");
						visitRecord.append(",");
					}
					TawRmVisitRecord visitRecordObj=(TawRmVisitRecord)visitRecordList.get(visitRecordList.size()-1);
					visitRecord.append("(");
					visitRecord.append(visitRecordObj.getVisitorName());
					visitRecord.append("||");
					visitRecord.append(visitRecordObj.getCompany());
					visitRecord.append("||");
					visitRecord.append(visitRecordObj.getVisitTime());
					visitRecord.append("||");
					visitRecord.append(visitRecordObj.getLeftTime());
					visitRecord.append("||");
					visitRecord.append(visitRecordObj.getReason());
					visitRecord.append("||");
					visitRecord.append(visitRecordObj.getReceiver());
					visitRecord.append(")");
				}
				visitRecord.append("}");
			}
		}
		return visitRecord.toString();
	}
	
	private String getLoanRecordStr(List userIdList,String workSerial){
		 
		int length = PAGE_LENGTH;
		ITawSystemUserManager userMgr=(ITawSystemUserManager)getBean("itawSystemUserManager");
		ITawRmLoanRecordManager loanRecordMgr=(ITawRmLoanRecordManager)getBean("ITawRmLoanRecordManager");
		StringBuffer loanRecord=new StringBuffer();
		if(userIdList!=null&&userIdList.size()>0){
			for(int i=0;i<userIdList.size();i++){
				String userId=(String)userIdList.get(i);
				String userName=userMgr.getUserByuserid(userId).getUsername();
				String whereStr=" where workSerial = '"+ workSerial + "' and userId='"+userId+"'";
				Map map=loanRecordMgr.getTawRmLoanRecords(new Integer(0),new Integer(length),whereStr);
				List loanRecordList=(List) map.get("result");
				loanRecord.append(userName+":");
				loanRecord.append("{");
				if(loanRecordList!=null&&loanRecordList.size()>0){
					for(int j=0;j<loanRecordList.size()-1;j++){
						TawRmLoanRecord loanRecordObj=(TawRmLoanRecord)loanRecordList.get(j);
						loanRecord.append("(");
						loanRecord.append(loanRecordObj.getArticleName());
						loanRecord.append("||");
						loanRecord.append(loanRecordObj.getPiece());
						loanRecord.append("||");
						loanRecord.append(loanRecordObj.getBorrowerName());
						loanRecord.append("||");
						loanRecord.append(loanRecordObj.getLoanTime());
						loanRecord.append("||");
						loanRecord.append(loanRecordObj.getReturnTime());
						loanRecord.append(")");
						loanRecord.append(",");
					}
					TawRmLoanRecord loanRecordObj=(TawRmLoanRecord)loanRecordList.get(loanRecordList.size()-1);
					loanRecord.append("(");
					loanRecord.append(loanRecordObj.getArticleName());
					loanRecord.append("||");
					loanRecord.append(loanRecordObj.getPiece());
					loanRecord.append("||");
					loanRecord.append(loanRecordObj.getBorrowerName());
					loanRecord.append("||");
					loanRecord.append(loanRecordObj.getLoanTime());
					loanRecord.append("||");
					loanRecord.append(loanRecordObj.getReturnTime());
					loanRecord.append(")");
				}
				loanRecord.append("}");
			}
		}
		return loanRecord.toString();
	}
	
	private String getReliefRecordStr(List userIdList,String workSerial){
		 
		int length = PAGE_LENGTH;
		ITawSystemUserManager userMgr=(ITawSystemUserManager)getBean("itawSystemUserManager");
		ITawRmReliefRecordManager reliefRecordMgr=(ITawRmReliefRecordManager)getBean("ITawRmReliefRecordManager");
		StringBuffer reliefRecord=new StringBuffer();
		if(userIdList!=null&&userIdList.size()>0){
			for(int i=0;i<userIdList.size();i++){
				String userId=(String)userIdList.get(i);
				String userName=userMgr.getUserByuserid(userId).getUsername();
				String whereStr=" where workSerial = '"+ workSerial + "' and userId='"+userId+"' and nuite_flag='"+DutyConstacts.NUITE_FLAG+"'";
				Map map=reliefRecordMgr.getTawRmReliefRecords(new Integer(offset),new Integer(length),whereStr);
				List reliefRecordList=(List) map.get("result");
				reliefRecord.append(userName+":");
				reliefRecord.append("{");
				if(reliefRecordList!=null&&reliefRecordList.size()>0){
					for(int j=0;j<reliefRecordList.size()-1;j++){
						TawRmReliefRecord reliefRecordObj=(TawRmReliefRecord)reliefRecordList.get(j);
						reliefRecord.append("(");
						reliefRecord.append(reliefRecordObj.getHandoverId());
						reliefRecord.append("||");
						reliefRecord.append(reliefRecordObj.getSuccessorId());
						reliefRecord.append("||");
						reliefRecord.append(reliefRecordObj.getRoomId());
						reliefRecord.append("||");
						reliefRecord.append(reliefRecordObj.getTime());
						reliefRecord.append(")");
						reliefRecord.append(",");
					}
					TawRmReliefRecord reliefRecordObj=(TawRmReliefRecord)reliefRecordList.get(reliefRecordList.size()-1);
					reliefRecord.append("(");
					reliefRecord.append(reliefRecordObj.getHandoverId());
					reliefRecord.append("||");
					reliefRecord.append(reliefRecordObj.getSuccessorId());
					reliefRecord.append("||");
					reliefRecord.append(reliefRecordObj.getRoomId());
					reliefRecord.append("||");
					reliefRecord.append(reliefRecordObj.getTime());
					reliefRecord.append(")");
				}
				reliefRecord.append("}");
			}
		}
		return reliefRecord.toString();
	}


	public ActionForward logUniteSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List roomList=this.getRoomList(request);
		request.setAttribute("roomList", roomList);
		return mapping.findForward("logUniteSearch");
	}
    
    public ActionForward viewContent(ActionMapping mapping, ActionForm form,
         	HttpServletRequest request,HttpServletResponse response)
        	throws Exception {
		try {
			// if an id is passed in, look up the user - otherwise
			// don't do anything - user is doing an add
			TawRmLogUniteForm tawRmLogUniteForm=(TawRmLogUniteForm)form;
			String id = StaticMethod.nullObject2String(request.getParameter("id"));
			HttpSession session=request.getSession();
			String roomId=StaticMethod.nullObject2String(session.getAttribute("myRoomId"));
			String beginTime=StaticMethod.nullObject2String(session.getAttribute("myBeginTime"));
			String endTime=StaticMethod.nullObject2String(session.getAttribute("myEndTime"));
			tawRmLogUniteForm.setRoomId(roomId);
			tawRmLogUniteForm.setBeginTime(beginTime);
			tawRmLogUniteForm.setEndTime(endTime);
			session.removeAttribute("myRoomId");
			session.removeAttribute("myBeginTime");
			session.removeAttribute("myEndTime");
			//String str = request.getParameter("folderPath");
			ITawRmLogUniteManager mgr = (ITawRmLogUniteManager) getBean("ITawRmLogUniteManager");
			TawRmLogUnite tawRmLogUnite=mgr.getTawRmLogUnite(id);
			request.setAttribute("tawRmLogUnite", tawRmLogUnite);
		}catch (Exception e) {
			e.printStackTrace();
		}
        return mapping.findForward("viewContent");
    }


	/**
	 * 
	 * @see 根据条件查询日志合并记录
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
					DutyConstacts.TAW_RM_LOGUNITELIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(GenericValidator
					.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
					: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
			
			TawRmLogUniteForm tawRmLogUniteForm=(TawRmLogUniteForm)form;
			String roomId=tawRmLogUniteForm.getRoomId();
			String beginTime=tawRmLogUniteForm.getBeginTime();
			String endTime=tawRmLogUniteForm.getEndTime();
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
			String userId=sessionform.getUserid();
			whereStr = request.getParameter("whereStr");
			StringBuffer buffer = new StringBuffer();
			StringBuffer bufferpage = new StringBuffer();
			buffer.append(" where 1=1");
			bufferpage.append("method=searchList");

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
			
			bufferpage.append("&beginTime=" + beginTime + "");
			if (!beginTime.equals("")) {
				buffer.append(" and beginTime>= '" + beginTime + "'");

			}
			
			bufferpage.append("&endTime=" + endTime + "");
			if (!endTime.equals("")) {
				buffer.append(" and endTime<= '" + endTime + "'");

			}
			
			whereStr = buffer.toString();
			request.setAttribute("whereStr", whereStr);
			String pageStr = bufferpage.toString();
			ITawRmLogUniteManager mgr = (ITawRmLogUniteManager) getBean("ITawRmLogUniteManager");
			Map map = (Map) mgr.getTawRmLogUnites(pageIndex,
					new Integer(length), whereStr);
			List list = (List) map.get("result");
			String url = request.getContextPath() + "/duty"+ mapping.getPath() + ".do";
			//			 每页显示条数
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
			int size = ((Integer)map.get("total")).intValue();
			String pagerHeader = MemoPage.generate(pageIndex.intValue(), size, length, url,pageStr);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute(DutyConstacts.TAW_RM_LOGUNITELIST, list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("list");
	}


	/**
	 * 
	 * @see 根据条件查询日志合并记录
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
					DutyConstacts.TAW_RM_LOGUNITELIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(GenericValidator
					.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
					: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
			
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
			String userId=sessionform.getUserid();
			whereStr = request.getParameter("whereStr");
			StringBuffer buffer = new StringBuffer();
			StringBuffer bufferpage = new StringBuffer();
			buffer.append(" where 1=1");
			bufferpage.append("method=searchList");

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
			
			String beginTime = StaticMethod.null2String(request.getParameter("beginTime"));
			bufferpage.append("&beginTime=" + beginTime + "");
			if (!beginTime.equals("")) {
				buffer.append(" and beginTime>= '" + beginTime + "'");

			}
			
			String endTime = StaticMethod.null2String(request.getParameter("endTime"));
			bufferpage.append("&endTime=" + endTime + "");
			if (!endTime.equals("")) {
				buffer.append(" and endTime<= '" + endTime + "'");

			}
			
			whereStr = buffer.toString();
			request.setAttribute("whereStr", whereStr);
			String pageStr = bufferpage.toString();
			ITawRmLogUniteManager mgr = (ITawRmLogUniteManager) getBean("ITawRmLogUniteManager");
			Map map = (Map) mgr.getTawRmLogUnites(pageIndex,
					new Integer(length), whereStr);
			List list = (List) map.get("result");
			String url = request.getContextPath() + "/duty"+ mapping.getPath() + ".do";
			//			 每页显示条数
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
			int size = ((Integer)map.get("total")).intValue();
			String pagerHeader = MemoPage.generate(pageIndex.intValue(), size, length, url,pageStr);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute(DutyConstacts.TAW_RM_LOGUNITELIST, list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("list");
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
