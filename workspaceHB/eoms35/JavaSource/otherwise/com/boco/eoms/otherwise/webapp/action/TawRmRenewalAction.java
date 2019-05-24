
package com.boco.eoms.otherwise.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.message.service.impl.MsgServiceImpl;
import com.boco.eoms.otherwise.model.TawRmInoutRecord;
import com.boco.eoms.otherwise.model.TawRmRenewal;
import com.boco.eoms.otherwise.service.ITawRmInoutRecordManager;
import com.boco.eoms.otherwise.service.ITawRmRenewalManager;
import com.boco.eoms.otherwise.service.ITawRmTestcardManager;
import com.boco.eoms.otherwise.util.OtherwiseConstacts;
import com.boco.eoms.otherwise.util.TestcardMgrLocator;
import com.boco.eoms.otherwise.webapp.form.TawRmRenewalForm;
import com.boco.eoms.workbench.memo.util.MemoPage;
import com.boco.eoms.workplan.util.WorkplanMgrLocator;

import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.util.JSONUtil;

/**
 * Action class to handle CRUD on a TawRmRenewal object
 * @struts.action name="tawRmRenewalForm" path="/tawRmRenewals" scope="request"
 *  validate="false" parameter="method" input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * 
 * @struts.action-forward name="main" 
 *  path="/WEB-INF/pages/tawRmRenewal/tawRmRenewalTree.jsp" contextRelative="true"
 *
 * @struts.action-forward name="edit" 
 *  path="/WEB-INF/pages/tawRmRenewal/tawRmRenewalForm.jsp" contextRelative="true"
 *
 * @struts.action-forward name="list" 
 *  path="/WEB-INF/pages/tawRmRenewal/tawRmRenewalList.jsp" contextRelative="true"
 */
public final class TawRmRenewalAction extends BaseAction {
		// 定义页数长度
		private static int PAGE_LENGTH = 15;
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

		ITawRmRenewalManager mgr = (ITawRmRenewalManager) getBean("ItawRmRenewalManager");
		JSONArray json = mgr.xGetChildNodes(nodeId);

		JSONUtil.print(response, json.toString());
	}

    /**
	 * ajax保存
	 */
    public void xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawRmRenewalForm tawRmRenewalForm = (TawRmRenewalForm) form;

		ITawRmRenewalManager mgr = (ITawRmRenewalManager) getBean("ItawRmRenewalManager");
		TawRmRenewal tawRmRenewal = (TawRmRenewal) convert(tawRmRenewalForm);
		mgr.saveTawRmRenewal(tawRmRenewal);
		// JSONUtil.print(response, "操作成功");
	}
    /**
	 * 根据模块或功能的编码，删除该对象
	 */
    public void xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawRmRenewalForm tawRmRenewalForm = (TawRmRenewalForm) form;

        ITawRmRenewalManager mgr = (ITawRmRenewalManager) getBean("ItawRmRenewalManager");
		mgr.removeTawRmRenewal(tawRmRenewalForm.getId());

	}
    /**
	 * ajax请求修改某节点的详细信息
	 */
    public String xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawRmRenewalForm tawRmRenewalForm = (TawRmRenewalForm) form;

		if (tawRmRenewalForm.getId() != null) {
			ITawRmRenewalManager mgr = (ITawRmRenewalManager) getBean("ItawRmRenewalManager");
			TawRmRenewal tawRmRenewal = (TawRmRenewal) convert(tawRmRenewalForm);

			mgr.saveTawRmRenewal(tawRmRenewal);
		   //mgr.updateTawRmRenewal(tawRmRenewal);
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
		ITawRmRenewalManager mgr = (ITawRmRenewalManager) getBean("ItawRmRenewalManager");
		TawRmRenewal tawRmRenewal = mgr.getTawRmRenewal(_strId);

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(tawRmRenewal);

		JSONUtil.print(response, jsonRoot.toString());
	}
	
  /**
	 * 可续借列表
	 */

  public ActionForward renewEdit(ActionMapping mapping, ActionForm form,
       	HttpServletRequest request,HttpServletResponse response)
      	throws Exception {
		int offset=0;
		int length = PAGE_LENGTH;
		ITawRmInoutRecordManager inoutMgr = (ITawRmInoutRecordManager) getBean("ItawRmInoutRecordManager");
		try {
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
			if (sessionform == null) {
				return mapping.findForward("timeout");
			}
			StringBuffer buffer=new StringBuffer();
			buffer.append(" where outType<>'' and inType=''");
			Map outMap=inoutMgr.getTawRmInoutRecords(new Integer(offset),new Integer(length),buffer.toString());
			List outList=(List)outMap.get("result");
			String ids="";
			if(outList.size()==1){
				TawRmInoutRecord tawRmInoutRecord=(TawRmInoutRecord)outList.get(0);
				ids="'"+tawRmInoutRecord.getTestcardId()+"'";
			}else if(outList.size()>1){
				for(int i=0;i<outList.size()-1;i++){
					TawRmInoutRecord tawRmInoutRecord=(TawRmInoutRecord)outList.get(i);
					ids=ids+"'"+tawRmInoutRecord.getTestcardId()+"',";
				}
				TawRmInoutRecord tawRmInoutRecord=(TawRmInoutRecord)outList.get(outList.size()-1);
				ids=ids+"'"+tawRmInoutRecord.getTestcardId()+"'";
			}
			String whereStr="";
			if(ids.equals("")){
				whereStr=" where 1<>1";
			}else{
				whereStr=" where id in("+ids+")";
			}
			//			 每页显示条数
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
			ITawRmTestcardManager testCardMgr = (ITawRmTestcardManager) getBean("ItawRmTestcardManager");
			Map testCardMap=testCardMgr.getTawRmTestcards(new Integer(offset),new Integer(length),whereStr);
			List testCardList=(List)testCardMap.get("result");
			request.setAttribute(OtherwiseConstacts.TAW_RM_TESTCARDLIST, testCardList);
			request.setAttribute("resultSize", testCardMap.get("total"));
			request.setAttribute("pageSize", pageSize);
		}catch (Exception e) {
			e.printStackTrace();
		}
    return mapping.findForward("renewEdit");
  }
	
  /**
	 * 跳转到续借页面
	 */

  public ActionForward toRenewDate(ActionMapping mapping, ActionForm form,
       	HttpServletRequest request,HttpServletResponse response)
      	throws Exception {
  	String id = request.getParameter("id");
		// 未选中则失败
		// TODO 给出提示信息
		if (id == null) {
			return mapping.findForward("fail");
		}
		String idStr="'"+id+"'";
		ITawRmInoutRecordManager mgr = (ITawRmInoutRecordManager) getBean("ItawRmInoutRecordManager");
		List inRecordList=mgr.getInRecordList(idStr);
		ListOrderedMap item = (ListOrderedMap) inRecordList.get(0);
		TawRmRenewalForm tawRmRenewalForm = (TawRmRenewalForm) form;
		String borrowDate=(String)item.getValue(11);
		String intendingReturnDate=(String)item.getValue(12);
		String borrowerId=(String)item.getValue(14);
		tawRmRenewalForm.setTestcardId(id);
		tawRmRenewalForm.setBorrowDate(borrowDate);
		tawRmRenewalForm.setIntendingReturnDate(intendingReturnDate);
		tawRmRenewalForm.setBorrowerId(borrowerId);
		ITawSystemUserManager userManager=(ITawSystemUserManager)getBean("itawSystemUserManager");
		String borrowerName=userManager.id2Name(borrowerId);
		tawRmRenewalForm.setBorrowerName(borrowerName);
		//updateFormBean(mapping, request, tawRmRenewalForm);
		return mapping.findForward("renewDate");
  }
	
	
	 /**
	 * 
	 * @see 测试卡续借
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
		public ActionForward renewDate(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			TawRmRenewalForm tawRmRenewalForm = (TawRmRenewalForm) form;
			ITawRmRenewalManager mgr = (ITawRmRenewalManager) getBean("ItawRmRenewalManager");
			ActionMessages messages = new ActionMessages();
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
			final String userId = sessionform.getUserid();
			TawRmRenewal tawRmRenewal=(TawRmRenewal)convert(tawRmRenewalForm);
			String renewDate=tawRmRenewal.getRenewDate();
			tawRmRenewal.setUserId(userId);
			mgr.saveTawRmRenewal(tawRmRenewal);
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("tawRmRenewal.added"));
			ITawRmInoutRecordManager inoutMgr = (ITawRmInoutRecordManager) getBean("ItawRmInoutRecordManager");
			List inRecordList=inoutMgr.getInRecordList("'"+tawRmRenewal.getTestcardId()+"'");
			ListOrderedMap item = (ListOrderedMap) inRecordList.get(0);
			String inoutRecordId=(String)item.getValue(0);
			TawRmInoutRecord tawRmInoutRecord=inoutMgr.getTawRmInoutRecord(inoutRecordId);
			tawRmInoutRecord.setIntendingReturnDate(renewDate);
			inoutMgr.saveTawRmInoutRecord(tawRmInoutRecord);
			closeSMS(inoutRecordId);
			String borrowerId=tawRmRenewal.getBorrowerId();
			String content="您借的测试卡已经快要到归还日期了，请及时归还或者进行续借";
			sendSMS(borrowerId,content,inoutRecordId,renewDate);
			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);
			return this.renewEdit(mapping,form,request,response);
		}
		
		
		 /**
		 * 
		 * @see 跳转到测试卡续借记录搜索页
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
			public ActionForward toSearch(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				return mapping.findForward("renewRecordRearch");
			}
			

			/**
			 * 
			 * @see 根据条件查询测试卡续借记录
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

				int length = PAGE_LENGTH;
				try {
					String whereStr = "";
					String pageIndexName = new org.displaytag.util.ParamEncoder(
							OtherwiseConstacts.TAW_RM_RENEWRECORDLIST)
							.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

					final Integer pageIndex = new Integer(GenericValidator
							.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
							: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
					
					whereStr = request.getParameter("whereStr");
					StringBuffer buffer = new StringBuffer();
					StringBuffer bufferpage = new StringBuffer();
					buffer.append(" where 1=1");
					bufferpage.append("method=searchList");
					
					String borrowStartDate = StaticMethod.null2String(request.getParameter("borrowStartDate"));
					bufferpage.append("&borrowStartDate=" + borrowStartDate + "");
					if (!borrowStartDate.equals("")) {
						buffer.append(" and borrowDate>='" + borrowStartDate + "'");
					}

					String borrowEndDate = StaticMethod.null2String(request.getParameter("borrowEndDate"));
					bufferpage.append("&borrowEndDate=" + borrowEndDate + "");
					if (!borrowEndDate.equals("")) {
						buffer.append(" and borrowDate<='" + borrowEndDate + "'");
					}
					
					String intendingReturnStartDate = StaticMethod.null2String(request.getParameter("intendingReturnStartDate"));
					bufferpage.append("&intendingReturnStartDate=" + intendingReturnStartDate + "");
					if (!intendingReturnStartDate.equals("")) {
						buffer.append(" and intendingReturnDate>= '" + intendingReturnStartDate + "'");
					}

					String intendingReturnEndDate = StaticMethod.null2String(request.getParameter("intendingReturnEndDate"));
					bufferpage.append("&intendingReturnEndDate=" + intendingReturnEndDate + "");
					if (!intendingReturnEndDate.equals("")) {
						buffer.append(" and intendingReturnDate<= '" + intendingReturnEndDate + "'");
					}
					
					String renewStartDate = StaticMethod.null2String(request.getParameter("renewStartDate"));
					bufferpage.append("&renewStartDate=" + renewStartDate + "");
					if (!renewStartDate.equals("")) {
						buffer.append(" and renewDate>= '" + renewStartDate + "'");
					}
					
					String renewEndDate = StaticMethod.null2String(request.getParameter("renewEndDate"));
					bufferpage.append("&renewEndDate=" + renewEndDate + "");
					if (!renewEndDate.equals("")) {
						buffer.append(" and renewDate<= '" + renewEndDate + "'");
					}
					
					String borrowerId = StaticMethod.null2String(request.getParameter("borrowerId"));
					bufferpage.append("&borrowerId=" + borrowerId + "");
					if (!borrowerId.equals("")) {
						buffer.append(" and borrowerId like '%" + borrowerId + "%'");
					}

					whereStr = buffer.toString();
					request.setAttribute("whereStr", whereStr);
					String pageStr = bufferpage.toString();
					ITawRmRenewalManager mgr = (ITawRmRenewalManager) getBean("ItawRmRenewalManager");
					Map map = (Map) mgr.getTawRmRenewals(pageIndex,new Integer(length), whereStr);
					List list = (List) map.get("result");
					String url = request.getContextPath() + "/testcard"+ mapping.getPath() + ".do";
					//			 每页显示条数
					final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
					int size = ((Integer)map.get("total")).intValue();
					String pagerHeader = MemoPage.generate(pageIndex.intValue(), size, length, url,pageStr);
					request.setAttribute("pagerHeader", pagerHeader);
					request.setAttribute(OtherwiseConstacts.TAW_RM_RENEWRECORDLIST, list);
					request.setAttribute("resultSize", map.get("total"));
					request.setAttribute("pageSize", pageSize);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return mapping.findForward("list");
			}
			
			public static void sendSMS(String _cruser, String _content, String _id,String dateString) {  
				// TODO 发送短信  
				String serverid = TestcardMgrLocator.getAttributes().getServerId();
				//  MsgService msgService = MsgMgrLocator.getMsgMgr();  
				MsgServiceImpl   msgService = new MsgServiceImpl();  
				if (msgService.hasService(serverid).equals("true")) {   
					// 拼写执行人 orgIds 格式：1,admin#,1,sunshengtai#2,151   
					String[] cruser = _cruser.split(",");   
					StringBuffer orgIds = new StringBuffer();   
					for (int i = 0; i < cruser.length; i++) {    
						orgIds.append("1," + cruser[i] + "#");   
					}   
					//System.out.println("serverid========"+serverid);
					//System.out.println("_content========"+_content);
					//System.out.println("orgIds.toString()============"+orgIds.toString());
					//System.out.println("dateString============"+dateString);
					msgService.sendMsg(serverid,_content,_id, orgIds.toString(), dateString); 
				}  
			}

			/**
			 * 取消短信服务功能
			 * 
			 * @param _cruser
			 *            String 发送人列表，以逗号分割
			 * @param _id
			 *            String 所属属性ID 如月计划Id或年计划ID
			 */
			public static void closeSMS(String _id) {
				// TODO 发送短信
				String serverid = TestcardMgrLocator.getAttributes().getServerId();
				MsgServiceImpl   msgService = new MsgServiceImpl();
				if (msgService.hasService(serverid).equals(
						"true")) {

					msgService.closeMsg(serverid, _id);
				}
			}
}
