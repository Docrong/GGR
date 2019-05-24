
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
import com.boco.eoms.duty.model.TawRmArticle;
import com.boco.eoms.duty.model.TawRmLoanRecord;
import com.boco.eoms.duty.service.ITawRmArticleManager;
import com.boco.eoms.duty.service.ITawRmLoanRecordManager;
import com.boco.eoms.duty.util.DutyConstacts;
import com.boco.eoms.duty.webapp.form.TawRmLoanRecordForm;
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
 * Action class to handle CRUD on a TawRmLoanRecord object
 * @struts.action name="tawRmLoanRecordForm" path="/tawRmLoanRecords" scope="request"
 *  validate="false" parameter="method" input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * 
 * @struts.action-forward name="main" 
 *  path="/WEB-INF/pages/tawRmLoanRecord/tawRmLoanRecordTree.jsp" contextRelative="true"
 *
 * @struts.action-forward name="edit" 
 *  path="/WEB-INF/pages/tawRmLoanRecord/tawRmLoanRecordForm.jsp" contextRelative="true"
 *
 * @struts.action-forward name="list" 
 *  path="/WEB-INF/pages/tawRmLoanRecord/tawRmLoanRecordList.jsp" contextRelative="true"
 */
public final class TawRmLoanRecordAction extends BaseAction {
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

		ITawRmLoanRecordManager mgr = (ITawRmLoanRecordManager) getBean("ItawRmLoanRecordManager");
		JSONArray json = mgr.xGetChildNodes(nodeId);

		JSONUtil.print(response, json.toString());
	}

    /**
	 * ajax保存
	 */
    public void xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawRmLoanRecordForm tawRmLoanRecordForm = (TawRmLoanRecordForm) form;

		ITawRmLoanRecordManager mgr = (ITawRmLoanRecordManager) getBean("ItawRmLoanRecordManager");
		TawRmLoanRecord tawRmLoanRecord = (TawRmLoanRecord) convert(tawRmLoanRecordForm);
		mgr.saveTawRmLoanRecord(tawRmLoanRecord);
		// JSONUtil.print(response, "操作成功");
	}
    /**
	 * 根据模块或功能的编码，删除该对象
	 */
    public void xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawRmLoanRecordForm tawRmLoanRecordForm = (TawRmLoanRecordForm) form;

        ITawRmLoanRecordManager mgr = (ITawRmLoanRecordManager) getBean("ItawRmLoanRecordManager");
		mgr.removeTawRmLoanRecord(tawRmLoanRecordForm.getId());

	}
    /**
	 * ajax请求修改某节点的详细信息
	 */
    public String xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawRmLoanRecordForm tawRmLoanRecordForm = (TawRmLoanRecordForm) form;

		if (tawRmLoanRecordForm.getId() != null) {
			ITawRmLoanRecordManager mgr = (ITawRmLoanRecordManager) getBean("ItawRmLoanRecordManager");
			TawRmLoanRecord tawRmLoanRecord = (TawRmLoanRecord) convert(tawRmLoanRecordForm);

			mgr.saveTawRmLoanRecord(tawRmLoanRecord);
		   //mgr.updateTawRmLoanRecord(tawRmLoanRecord);
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
		ITawRmLoanRecordManager mgr = (ITawRmLoanRecordManager) getBean("ItawRmLoanRecordManager");
		TawRmLoanRecord tawRmLoanRecord = mgr.getTawRmLoanRecord(_strId);

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(tawRmLoanRecord);

		JSONUtil.print(response, jsonRoot.toString());
	}
	

    public ActionForward edit(ActionMapping mapping, ActionForm form,
         	HttpServletRequest request,HttpServletResponse response)
        	throws Exception {
		TawRmLoanRecordForm tawRmLoanRecordForm=(TawRmLoanRecordForm)form;
		try {
			// if an id is passed in, look up the user - otherwise
			// don't do anything - user is doing an add
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
			String tmpArticleName=StaticMethod.nullObject2String(session.getAttribute("myTmpArticleName"));
			String tmpPiece=StaticMethod.nullObject2String(session.getAttribute("myTmpPiece"));
			String tmpBorrowerName=StaticMethod.nullObject2String(session.getAttribute("myTmpBorrowerName"));
			String tmpLoanTime=StaticMethod.nullObject2String(session.getAttribute("myTmpLoanTime"));
			String tmpReturnTime=StaticMethod.nullObject2String(session.getAttribute("myTmpReturnTime"));
			String roomId=StaticMethod.nullObject2String(session.getAttribute("myRoomId"));
			String startTime=StaticMethod.nullObject2String(session.getAttribute("myStartTime"));
			String endTime=StaticMethod.nullObject2String(session.getAttribute("myEndTime"));
			tawRmLoanRecordForm.setTmpArticleName(tmpArticleName);
			tawRmLoanRecordForm.setTmpPiece(tmpPiece);
			tawRmLoanRecordForm.setTmpBorrowerName(tmpBorrowerName);
			tawRmLoanRecordForm.setTmpLoanTime(tmpLoanTime);
			tawRmLoanRecordForm.setTmpReturnTime(tmpReturnTime);
			tawRmLoanRecordForm.setRoomId(roomId);
			tawRmLoanRecordForm.setStartTime(startTime);
			tawRmLoanRecordForm.setEndTime(endTime);
			session.removeAttribute("myTmpArticleName");
			session.removeAttribute("myTmpPiece");
			session.removeAttribute("myTmpBorrowerName");
			session.removeAttribute("myTmpLoanTime");
			session.removeAttribute("myTmpReturnTime");
			session.removeAttribute("myRoomId");
			session.removeAttribute("myStartTime");
			session.removeAttribute("myEndTime");
			if(tawRmLoanRecordForm.getId()!=null){
				ITawRmLoanRecordManager mgr=(ITawRmLoanRecordManager)getBean("ITawRmLoanRecordManager");
				if (id != null && !id.equals("")) {
					TawRmLoanRecord tawRmLoanRecord=mgr.getTawRmLoanRecord(id);
					if(tawRmLoanRecord!=null){
						if(workSerial.equals("0")){
							request.setAttribute("tawRmLoanRecord", tawRmLoanRecord);
							return mapping.findForward("detailContent");
						}
						tawRmLoanRecordForm =(TawRmLoanRecordForm)convert(tawRmLoanRecord);
					}
					request.setAttribute("returnTime",tawRmLoanRecord.getReturnTime());
				}
				request.setAttribute("recordId", id);
				/*
				 * tawWorkbenchMemoForm = (TawWorkbenchMemoForm)
				 * convert(tawWorkbenchMemo);
				 */
				updateFormBean(mapping, request, tawRmLoanRecordForm);
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
		TawRmLoanRecordForm tawRmLoanRecordForm=(TawRmLoanRecordForm)form;
		int piece = Integer.parseInt(tawRmLoanRecordForm.getPiece());
		boolean isNew=(tawRmLoanRecordForm.getId().equals("")||tawRmLoanRecordForm.getId()==null);
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		final String userId = sessionform.getUserid();
		final String workSerial = sessionform.getWorkSerial();
		final String roomId = sessionform.getRoomId();
		ITawRmArticleManager mgra = (ITawRmArticleManager) getBean("ITawRmArticleManager");
		String articleId=request.getParameter("articleId");
		StringBuffer sql = new StringBuffer();
		if("".equals(articleId)||articleId==null){
			sql.append(" and articleName= '"+tawRmLoanRecordForm.getArticleName()+"'");
			sql.append(" and articleType= '"+tawRmLoanRecordForm.getArticleType()+"'");
			List list = mgra.getTawRmArticleByCondition(sql.toString());
			if(list.isEmpty()){
				request.setAttribute("failReason", "要借的物品在库存中不存在！");
				return mapping.findForward("fail");
			}else{
				TawRmArticle temp =(TawRmArticle) list.get(0);
				articleId = temp.getId();
				tawRmLoanRecordForm.setArticleId(articleId);
			}
		}
		
		TawRmArticle tawRmArticle = mgra.getTawRmArticle(articleId);
		int onlineNum = tawRmArticle.getOnlineNum();
		if(piece>onlineNum){      
			request.setAttribute("failReason", "外借物品数量不能大于库存数量");
			return mapping.findForward("fail");
		}
			tawRmArticle.setOnlineNum(onlineNum-piece);
			tawRmArticle.setLoanNum(tawRmArticle.getLoanNum()+piece);
			mgra.saveTawRmArticle(tawRmArticle);
			
		ITawRmLoanRecordManager mgr=(ITawRmLoanRecordManager)getBean("ITawRmLoanRecordManager");
		TawRmLoanRecord TawRmLoanRecord=(TawRmLoanRecord)convert(tawRmLoanRecordForm);
		TawRmLoanRecord.setUserId(userId);
		TawRmLoanRecord.setWorkSerial(workSerial);
		TawRmLoanRecord.setRoomId(roomId);
		String createTime = StaticMethod.getLocalString();
		TawRmLoanRecord.setCreateTime(createTime);
		mgr.saveTawRmLoanRecord(TawRmLoanRecord);
		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"TawRmLoanRecord.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return searchList2(mapping, form, request, response);
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"TawRmLoanRecord.updated"));
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
			String pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			TawRmLoanRecord tawRmLoanRecord=null;
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			final String workSerial = sessionform.getWorkSerial();
			// 得到查询的条件
			String whereStr = " where workSerial = '" + workSerial + "'";
			List tawRmLoanRecordList = new ArrayList();
			ITawRmLoanRecordManager mgr=(ITawRmLoanRecordManager)getBean("ITawRmLoanRecordManager");
			Map map = (Map) mgr.getTawRmLoanRecords(new Integer(offset),new Integer(length), whereStr);
			List list = (List) map.get("result");
			// 分页
			for (int i = offset; i < (length + offset) && i < list.size(); i++) {
				tawRmLoanRecord=(TawRmLoanRecord)list.get(i);
				tawRmLoanRecordList.add(tawRmLoanRecord);
			}
			String url = request.getContextPath() + "/duty"+ mapping.getPath() + ".do";
			int size = Integer.parseInt((StaticMethod.null2String((String) map
					.get("total"))));
			String pagerHeader = Pager.generate(offset, size, length, url,
					"method=search");
			request.setAttribute("pagerHeader", pagerHeader);
			Iterator listIt = tawRmLoanRecordList.iterator();
			request.setAttribute("listIt", listIt);
			request.setAttribute("resultSize", map.get("total"));
		}catch (Exception e) {
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
					DutyConstacts.TAW_RM_LOANRECORDLIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(GenericValidator
					.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
					: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
			
			TawRmLoanRecordForm tawRmLoanRecordForm = (TawRmLoanRecordForm) form;
			String articleName=StaticMethod.null2String(tawRmLoanRecordForm.getTmpArticleName());
			String piece=StaticMethod.null2String(tawRmLoanRecordForm.getTmpPiece());
			String borrowerName=StaticMethod.null2String(tawRmLoanRecordForm.getTmpBorrowerName());
			String loanTime=StaticMethod.null2String(tawRmLoanRecordForm.getTmpLoanTime());
			String returnTime=StaticMethod.null2String(tawRmLoanRecordForm.getTmpReturnTime());
			String roomId=StaticMethod.null2String(tawRmLoanRecordForm.getRoomId());
			String startTime=StaticMethod.null2String(tawRmLoanRecordForm.getStartTime());
			String endTime=StaticMethod.null2String(tawRmLoanRecordForm.getEndTime());
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
			//final String workSerial = sessionform.getWorkSerial();
			String userId = sessionform.getUserid();
			whereStr = request.getParameter("whereStr");
			StringBuffer buffer = new StringBuffer();
			StringBuffer bufferpage = new StringBuffer();
			//buffer.append(" where 1=1 and userId = '" + userId + "'");
			buffer.append(" where 1=1");
			bufferpage.append("method=searchList");
			
			bufferpage.append("&articleName=" + articleName + "");
			if (!articleName.equals("")) {
				buffer.append(" and articleName like '%" + articleName + "%'");

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
			
			bufferpage.append("&piece=" + piece + "");
			if (!piece.equals("")) {
				buffer.append(" and piece='" + piece + "'");
			}
			
			bufferpage.append("&borrowerName=" + borrowerName + "");
			if (!borrowerName.equals("")) {
				buffer.append(" and borrowerName like '%" + borrowerName + "%'");

			}
			
			bufferpage.append("&loanTime=" + loanTime + "");
			if (!loanTime.equals("")) {
				buffer.append(" and loanTime='" + loanTime + "'");
			}
			
			bufferpage.append("&returnTime=" + returnTime + "");
			if (!returnTime.equals("")) {
				buffer.append(" and returnTime='" + returnTime + "'");
			}
			
			whereStr = buffer.toString();
			request.setAttribute("whereStr", whereStr);
			String pageStr = bufferpage.toString();
			ITawRmLoanRecordManager mgr = (ITawRmLoanRecordManager) getBean("ITawRmLoanRecordManager");
			Map map = (Map) mgr.getTawRmLoanRecords(pageIndex,
					new Integer(length), whereStr);
			List list = (List) map.get("result");

			String url = request.getContextPath() + "/duty"+ mapping.getPath() + ".do";
			//			 每页显示条数
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
			int size = ((Integer)map.get("total")).intValue();
			String pagerHeader = MemoPage.generate(pageIndex.intValue(), size, length, url,pageStr);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute(DutyConstacts.TAW_RM_LOANRECORDLIST, list);
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
					DutyConstacts.TAW_RM_LOANRECORDLIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(GenericValidator
					.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
					: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
			
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
			//final String workSerial = sessionform.getWorkSerial();
			String userId = sessionform.getUserid();
			whereStr = request.getParameter("whereStr");
			StringBuffer buffer = new StringBuffer();
			StringBuffer bufferpage = new StringBuffer();
			//buffer.append(" where 1=1 and userId = '" + userId + "'");
			buffer.append(" where 1=1");
			bufferpage.append("method=searchList");
			
			String articleName = StaticMethod.null2String(request.getParameter("tmpArticleName"));
			bufferpage.append("&articleName=" + articleName + "");
			if (!articleName.equals("")) {
				buffer.append(" and articleName like '%" + articleName + "%'");

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
			
			String piece = StaticMethod.null2String(request.getParameter("tmpPiece"));
			bufferpage.append("&piece=" + piece + "");
			if (!piece.equals("")) {
				buffer.append(" and piece='" + piece + "'");
			}
			
			String borrowerName = StaticMethod.null2String(request.getParameter("tmpBorrowerName"));
			bufferpage.append("&borrowerName=" + borrowerName + "");
			if (!borrowerName.equals("")) {
				buffer.append(" and borrowerName like '%" + borrowerName + "%'");

			}
			
			String loanTime = StaticMethod.null2String(request.getParameter("tmpLoanTime"));
			bufferpage.append("&loanTime=" + loanTime + "");
			if (!loanTime.equals("")) {
				buffer.append(" and loanTime='" + loanTime + "'");
			}
			
			String returnTime = StaticMethod.null2String(request.getParameter("tmpReturnTime"));
			bufferpage.append("&returnTime=" + returnTime + "");
			if (!returnTime.equals("")) {
				buffer.append(" and returnTime='" + returnTime + "'");
			}
			
			whereStr = buffer.toString();
			request.setAttribute("whereStr", whereStr);
			String pageStr = bufferpage.toString();
			ITawRmLoanRecordManager mgr = (ITawRmLoanRecordManager) getBean("ITawRmLoanRecordManager");
			Map map = (Map) mgr.getTawRmLoanRecords(pageIndex,
					new Integer(length), whereStr);
			List list = (List) map.get("result");
			String url = request.getContextPath() + "/duty"+ mapping.getPath() + ".do";
			//			 每页显示条数
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
			int size = ((Integer)map.get("total")).intValue();
			String pagerHeader = MemoPage.generate(pageIndex.intValue(), size, length, url,pageStr);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute(DutyConstacts.TAW_RM_LOANRECORDLIST, list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("list");
	}


	public ActionForward loanRecordSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List roomList=this.getRoomList(request);
		request.setAttribute("roomList", roomList);
		return mapping.findForward("loanRecordSearch");
	}
	
	// 删除 返回查询页面
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages messages = new ActionMessages();
		TawRmLoanRecordForm tawRmLoanRecordForm = (TawRmLoanRecordForm) form;
		try {
			// Exceptions are caught by ActionExceptionHandler
			ITawRmLoanRecordManager mgr=(ITawRmLoanRecordManager)getBean("ITawRmLoanRecordManager");
			ITawRmArticleManager mgr2 = (ITawRmArticleManager) getBean("ITawRmArticleManager");
			String id = tawRmLoanRecordForm.getId();
			String articleId = tawRmLoanRecordForm.getArticleId();
			int piece = Integer.parseInt(tawRmLoanRecordForm.getPiece());
			TawRmArticle tawRmArticle = mgr2.getTawRmArticle(articleId);
			tawRmArticle.setOnlineNum(tawRmArticle.getOnlineNum()+piece);
			tawRmArticle.setLoanNum(tawRmArticle.getLoanNum()-piece);
			mgr2.saveTawRmArticle(tawRmArticle);
			mgr.removeTawRmLoanRecord(tawRmLoanRecordForm.getId());

			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawRmLoanRecordForm.deleted"));

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
