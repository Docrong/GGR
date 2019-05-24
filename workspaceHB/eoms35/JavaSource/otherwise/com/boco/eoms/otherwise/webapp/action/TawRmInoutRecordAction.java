
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
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.message.service.impl.MsgServiceImpl;
import com.boco.eoms.otherwise.model.TawRmInoutRecord;
import com.boco.eoms.otherwise.model.TawRmTestcard;
import com.boco.eoms.otherwise.service.ITawRmInoutRecordManager;
import com.boco.eoms.otherwise.service.ITawRmTestcardManager;
import com.boco.eoms.otherwise.util.OtherwiseConstacts;
import com.boco.eoms.otherwise.util.TestcardMgrLocator;
import com.boco.eoms.otherwise.webapp.form.TawRmInoutRecordForm;
import com.boco.eoms.workbench.memo.util.MemoPage;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;

/**
 * Action class to handle CRUD on a TawRmInoutRecord object
 * @struts.action name="tawRmInoutRecordForm" path="/tawRmInoutRecords" scope="request"
 *  validate="false" parameter="method" input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * 
 * @struts.action-forward name="main" 
 *  path="/WEB-INF/pages/tawRmInoutRecord/tawRmInoutRecordTree.jsp" contextRelative="true"
 *
 * @struts.action-forward name="edit" 
 *  path="/WEB-INF/pages/tawRmInoutRecord/tawRmInoutRecordForm.jsp" contextRelative="true"
 *
 * @struts.action-forward name="list" 
 *  path="/WEB-INF/pages/tawRmInoutRecord/tawRmInoutRecordList.jsp" contextRelative="true"
 */
public final class TawRmInoutRecordAction extends BaseAction {
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

		ITawRmInoutRecordManager mgr = (ITawRmInoutRecordManager) getBean("ItawRmInoutRecordManager");
		JSONArray json = mgr.xGetChildNodes(nodeId);

		JSONUtil.print(response, json.toString());
	}

    /**
	 * ajax保存
	 */
    public void xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawRmInoutRecordForm tawRmInoutRecordForm = (TawRmInoutRecordForm) form;

		ITawRmInoutRecordManager mgr = (ITawRmInoutRecordManager) getBean("ItawRmInoutRecordManager");
		TawRmInoutRecord tawRmInoutRecord = (TawRmInoutRecord) convert(tawRmInoutRecordForm);
		mgr.saveTawRmInoutRecord(tawRmInoutRecord);
		// JSONUtil.print(response, "操作成功");
	}
    /**
	 * 根据模块或功能的编码，删除该对象
	 */
    public void xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawRmInoutRecordForm tawRmInoutRecordForm = (TawRmInoutRecordForm) form;

        ITawRmInoutRecordManager mgr = (ITawRmInoutRecordManager) getBean("ItawRmInoutRecordManager");
		mgr.removeTawRmInoutRecord(tawRmInoutRecordForm.getId());

	}
    /**
	 * ajax请求修改某节点的详细信息
	 */
    public String xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawRmInoutRecordForm tawRmInoutRecordForm = (TawRmInoutRecordForm) form;

		if (tawRmInoutRecordForm.getId() != null) {
			ITawRmInoutRecordManager mgr = (ITawRmInoutRecordManager) getBean("ItawRmInoutRecordManager");
			TawRmInoutRecord tawRmInoutRecord = (TawRmInoutRecord) convert(tawRmInoutRecordForm);

			mgr.saveTawRmInoutRecord(tawRmInoutRecord);
		   //mgr.updateTawRmInoutRecord(tawRmInoutRecord);
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
		ITawRmInoutRecordManager mgr = (ITawRmInoutRecordManager) getBean("ItawRmInoutRecordManager");
		TawRmInoutRecord tawRmInoutRecord = mgr.getTawRmInoutRecord(_strId);

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(tawRmInoutRecord);

		JSONUtil.print(response, jsonRoot.toString());
	}

	
  public ActionForward outEdit(ActionMapping mapping, ActionForm form,
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
			whereStr=" where 1=1";
		}else{
			whereStr=" where id not in("+ids+")";
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
    return mapping.findForward("outEdit");
  }
	
  public ActionForward inEdit(ActionMapping mapping, ActionForm form,
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
    return mapping.findForward("inEdit");
  }
  
  /**
   * 
   * @see 跳到测试卡出库页面
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  	public ActionForward toOutStorage(ActionMapping mapping, ActionForm form,
  			HttpServletRequest request, HttpServletResponse response)
  			throws Exception {
  		String ids[] = request.getParameterValues("ids");
  		// 未选中则失败
  		// TODO 给出提示信息
  		if (ids == null) {
  			return mapping.findForward("fail");
  		}
  		request.setAttribute("ids",ids);
  		return mapping.findForward("outStorage");
  	}
    
    /**
     * 
     * @see 跳到测试卡出库页面
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    	public ActionForward toInStorage(ActionMapping mapping, ActionForm form,
    			HttpServletRequest request, HttpServletResponse response)
    			throws Exception {
    		String ids[] = request.getParameterValues("ids");
    		// 未选中则失败
    		// TODO 给出提示信息
    		if (ids == null) {
    			return mapping.findForward("fail");
    		}
    		String idStr="";
    		for (int i = 0; i < ids.length-1; i++) {
    			String id=ids[i];
    			idStr=idStr+"'"+id+"',";
    		}
    		idStr=idStr+"'"+ids[ids.length-1]+"'";
  			ITawRmInoutRecordManager mgr = (ITawRmInoutRecordManager) getBean("ItawRmInoutRecordManager");
  			List inRecordList=mgr.getInRecordList(idStr);
  			String tmpIds="";
  			for(int i=0;i<inRecordList.size()-1;i++){
  				ListOrderedMap item = (ListOrderedMap) inRecordList.get(i);
  				String id=(String)item.getValue(0);
  				tmpIds=tmpIds+id+",";
  			}
  			ListOrderedMap item = (ListOrderedMap) inRecordList.get(inRecordList.size()-1);
				String id=(String)item.getValue(0);
  			tmpIds=tmpIds+id;
    		request.setAttribute("ids",tmpIds);
    		return mapping.findForward("inStorage");
    	}
  
 /**
 * 
 * @see 测试卡出库
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	public ActionForward outStorage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawRmInoutRecordForm tawRmInoutRecordForm = (TawRmInoutRecordForm) form;
		String ids=tawRmInoutRecordForm.getIds();
		String[] tmp=ids.split(",");
		String borrowDate=StaticMethod.nullObject2String(tawRmInoutRecordForm.getBorrowDate());
		String intendingReturnDate=StaticMethod.nullObject2String(tawRmInoutRecordForm.getIntendingReturnDate());
		String realReturnDate=StaticMethod.nullObject2String(tawRmInoutRecordForm.getRealReturnDate());
		String borrowerId=StaticMethod.nullObject2String(tawRmInoutRecordForm.getBorrowerId());
		String outType="0";
		String inType=StaticMethod.nullObject2String(tawRmInoutRecordForm.getInType());
		String inState=StaticMethod.nullObject2String(tawRmInoutRecordForm.getInState());
		String remark=StaticMethod.nullObject2String(tawRmInoutRecordForm.getRemark());
		String inStorageUserId=StaticMethod.nullObject2String(tawRmInoutRecordForm.getInStorageUserId());
		String inStorageRemark=StaticMethod.nullObject2String(tawRmInoutRecordForm.getInStorageRemark());
		ITawRmInoutRecordManager mgr = (ITawRmInoutRecordManager) getBean("ItawRmInoutRecordManager");
		ActionMessages messages = new ActionMessages();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		final String userId = sessionform.getUserid();
		
		for (int i = 0; i < tmp.length; i++) {
			String id=tmp[i];
			TawRmInoutRecord tawRmInoutRecord=new TawRmInoutRecord();
			tawRmInoutRecord.setTestcardId(id);
			tawRmInoutRecord.setBorrowDate(borrowDate);
			tawRmInoutRecord.setIntendingReturnDate(intendingReturnDate);
			tawRmInoutRecord.setRealReturnDate(realReturnDate);
			tawRmInoutRecord.setBorrowerId(borrowerId);
			tawRmInoutRecord.setUserId(userId);
			tawRmInoutRecord.setOutType(outType);
			tawRmInoutRecord.setInType(inType);
			tawRmInoutRecord.setInState(inState);
			tawRmInoutRecord.setRemark(remark);
			tawRmInoutRecord.setInStorageUserId(inStorageUserId);
			tawRmInoutRecord.setInStorageRemark(inStorageRemark);
			mgr.saveTawRmInoutRecord(tawRmInoutRecord);
			List list=mgr.getInRecordList("'"+id+"'");
			ListOrderedMap item = (ListOrderedMap) list.get(0);
			String inoutRecordid=(String)item.getValue(0);
			String content="您借的测试卡已经快要到归还日期了，请及时归还或者进行续借";
			sendSMS(borrowerId,content,inoutRecordid,intendingReturnDate);
		}
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("tawRmInoutRecord.added"));
		// save messages in session to survive a redirect
		saveMessages(request.getSession(), messages);
		return this.outEdit(mapping,form,request,response);
	}
	
	
	 /**
	 * 
	 * @see 测试卡入库
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
		public ActionForward inStorage(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			TawRmInoutRecordForm tawRmInoutRecordForm = (TawRmInoutRecordForm) form;
			String ids=tawRmInoutRecordForm.getIds();
			String[] tmp=ids.split(",");
			String realReturnDate=StaticMethod.nullObject2String(tawRmInoutRecordForm.getRealReturnDate());
			String outType=StaticMethod.nullObject2String(tawRmInoutRecordForm.getOutType());
			String inType="0";
			String inStorageRemark=StaticMethod.nullObject2String(tawRmInoutRecordForm.getInStorageRemark());
			ITawRmInoutRecordManager mgr = (ITawRmInoutRecordManager) getBean("ItawRmInoutRecordManager");
			ActionMessages messages = new ActionMessages();
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
			final String userId = sessionform.getUserid();
			
			for (int i = 0; i < tmp.length; i++) {
				String id=tmp[i];
				TawRmInoutRecord tawRmInoutRecord=mgr.getTawRmInoutRecord(id);
				tawRmInoutRecord.setRealReturnDate(realReturnDate);
				tawRmInoutRecord.setOutType(outType);
				tawRmInoutRecord.setInType(inType);
				tawRmInoutRecord.setInStorageUserId(userId);
				tawRmInoutRecord.setInStorageRemark(inStorageRemark);
				mgr.saveTawRmInoutRecord(tawRmInoutRecord);
				closeSMS(id);
			}
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("tawRmInoutRecord.added"));
			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);
			return this.inEdit(mapping,form,request,response);
		}
		
		
		 /**
		 * 
		 * @see 跳转到测试卡搜索页
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
				return mapping.findForward("inoutRecordRearch");
			}
			

			/**
			 * 
			 * @see 根据条件查询测试卡出入库记录
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
							OtherwiseConstacts.TAW_RM_INOUTRECORDLIST)
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
					
					String realReturnStartDate = StaticMethod.null2String(request.getParameter("realReturnStartDate"));
					bufferpage.append("&realReturnStartDate=" + realReturnStartDate + "");
					if (!realReturnStartDate.equals("")) {
						buffer.append(" and realReturnDate>= '" + realReturnStartDate + "'");
					}
					
					String realReturnEndDate = StaticMethod.null2String(request.getParameter("realReturnEndDate"));
					bufferpage.append("&realReturnEndDate=" + realReturnEndDate + "");
					if (!realReturnEndDate.equals("")) {
						buffer.append(" and realReturnDate<= '" + realReturnEndDate + "'");
					}
					
					String borrowerId = StaticMethod.null2String(request.getParameter("borrowerId"));
					bufferpage.append("&borrowerId=" + borrowerId + "");
					if (!borrowerId.equals("")) {
						buffer.append(" and borrowerId like '%" + borrowerId + "%'");
					}

					whereStr = buffer.toString();
					request.setAttribute("whereStr", whereStr);
					String pageStr = bufferpage.toString();
					ITawRmInoutRecordManager mgr = (ITawRmInoutRecordManager) getBean("ItawRmInoutRecordManager");
					Map map = (Map) mgr.getTawRmInoutRecords(pageIndex,new Integer(length), whereStr);
					List list = (List) map.get("result");
					String url = request.getContextPath() + "/testcard"+ mapping.getPath() + ".do";
					//			 每页显示条数
					final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
					int size = ((Integer)map.get("total")).intValue();
					String pagerHeader = MemoPage.generate(pageIndex.intValue(), size, length, url,pageStr);
					request.setAttribute("pagerHeader", pagerHeader);
					request.setAttribute(OtherwiseConstacts.TAW_RM_INOUTRECORDLIST, list);
					request.setAttribute("resultSize", map.get("total"));
					request.setAttribute("pageSize", pageSize);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return mapping.findForward("list");
			}
		  
			public ActionForward toStatInoutRecord(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				return mapping.findForward("statInoutRecord");
			}
			

			/**
			 * 
			 * @see 根据条件统计测试卡出入库记录
			 * @param mapping
			 * @param form
			 * @param request
			 * @param response
			 * @return
			 * @throws Exception
			 */

			public ActionForward statInoutRecord(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {

				int length = PAGE_LENGTH;
				try {
					String whereStr = "";
					String pageIndexName = new org.displaytag.util.ParamEncoder(
							OtherwiseConstacts.STATINOUTRECORDLIST)
							.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

					final Integer pageIndex = new Integer(GenericValidator
							.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
							: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
					
					whereStr = request.getParameter("whereStr");
					StringBuffer inoutBuffer = new StringBuffer();
					StringBuffer buffer = new StringBuffer();
					StringBuffer bufferpage = new StringBuffer();
					buffer.append(" where 1=1");
					inoutBuffer.append(" where 1=1");
					bufferpage.append("method=statInoutRecord");
					
					String cardType = StaticMethod.null2String(request.getParameter("cardType"));
					bufferpage.append("&cardType=" + cardType + "");
					if (!cardType.equals("")&&!cardType.equals("2")) {
						buffer.append(" and cardType = '" + cardType + "'");
					}

					String fromCountry = StaticMethod.null2String(request.getParameter("fromCountry"));
					bufferpage.append("&fromCountry=" + fromCountry + "");
					if (!fromCountry.equals("")&&!cardType.equals("2")) {
						buffer.append(" and fromCountry = '" + fromCountry + "'");
					}

					String fromOperator = StaticMethod.null2String(request.getParameter("fromOperator"));
					bufferpage.append("&fromOperator=" + fromOperator + "");
					if (!fromOperator.equals("")&&!cardType.equals("2")) {
						buffer.append(" and fromOperator = '" + fromOperator + "'");
					}
					
					String fromProvince = StaticMethod.null2String(request.getParameter("fromProvince"));
					bufferpage.append("&fromProvince=" + fromProvince + "");
					if (!fromProvince.equals("")&&!cardType.equals("2")) {
						buffer.append(" and fromProvince = '" + fromProvince + "'");
					}

					String fromCity = StaticMethod.null2String(request.getParameter("fromCity"));
					bufferpage.append("&fromCity=" + fromCity + "");
					if (!fromCity.equals("")&&!cardType.equals("2")) {
						buffer.append(" and fromCity = '" + fromCity + "'");
					}

					String toCountry = StaticMethod.null2String(request.getParameter("toCountry"));
					bufferpage.append("&toCountry=" + toCountry + "");
					if (!toCountry.equals("")&&!cardType.equals("2")) {
						buffer.append(" and toCountry = '" + toCountry + "'");
					}

					String toOperator = StaticMethod.null2String(request.getParameter("toOperator"));
					bufferpage.append("&toOperator=" + toOperator + "");
					if (!toOperator.equals("")&&!cardType.equals("2")) {
						buffer.append(" and toOperator = '" + toOperator + "'");
					}
					
					String toProvince = StaticMethod.null2String(request.getParameter("toProvince"));
					bufferpage.append("&toProvince=" + toProvince + "");
					if (!toProvince.equals("")&&!cardType.equals("2")) {
						buffer.append(" and toProvince = '" + toProvince + "'");
					}

					String toCity = StaticMethod.null2String(request.getParameter("toCity"));
					bufferpage.append("&toCity=" + toCity + "");
					if (!toCity.equals("")&&!cardType.equals("2")) {
						buffer.append(" and toCity = '" + toCity + "'");
					}
					/**String cardType = StaticMethod.null2String(request.getParameter("cardType"));
					bufferpage.append("&cardType=" + cardType + "");

					String fromCountry = StaticMethod.null2String(request.getParameter("fromCountry"));
					bufferpage.append("&fromCountry=" + fromCountry + "");

					String fromOperator = StaticMethod.null2String(request.getParameter("fromOperator"));
					bufferpage.append("&fromOperator=" + fromOperator + "");
					
					String fromProvince = StaticMethod.null2String(request.getParameter("fromProvince"));
					bufferpage.append("&fromProvince=" + fromProvince + "");

					String fromCity = StaticMethod.null2String(request.getParameter("fromCity"));
					bufferpage.append("&fromCity=" + fromCity + "");

					String toCountry = StaticMethod.null2String(request.getParameter("toCountry"));
					bufferpage.append("&toCountry=" + toCountry + "");

					String toOperator = StaticMethod.null2String(request.getParameter("toOperator"));
					bufferpage.append("&toOperator=" + toOperator + "");
					
					String toProvince = StaticMethod.null2String(request.getParameter("toProvince"));
					bufferpage.append("&toProvince=" + toProvince + "");

					String toCity = StaticMethod.null2String(request.getParameter("toCity"));
					bufferpage.append("&toCity=" + toCity + "");
					String ascriptionPlace="";
					String visitPlace = "";
					if(cardType.equals("0")){
						String fromTmp1=cardType+"-"+fromCountry;
						buffer.append(" and ascriptionPlace like '" + fromTmp1 + "%'");
						if(!fromOperator.equals("")){
							String fromTmp2="-"+fromOperator;
							buffer.append(" and ascriptionPlace like '%" + fromTmp2 + "'");
						}
						String toTmp1=cardType+"-"+toCountry;
						buffer.append(" and visitPlace like '" + toTmp1 + "%'");
						if(!toOperator.equals("")){
							String toTmp2="-"+toOperator;
							buffer.append(" and visitPlace like '%" + toTmp2 + "'");
						}
					}

					if(cardType.equals("1")){
						ascriptionPlace=cardType;
						if(!fromProvince.equals("")){
							ascriptionPlace=ascriptionPlace+"-"+fromProvince;
						}
						if(!fromCity.equals("")){
							ascriptionPlace=ascriptionPlace+"-"+fromCity;
						}
						visitPlace=cardType;
						if(!toProvince.equals("")){
							visitPlace=cardType+"-"+toProvince;
						}
						if(!toCity.equals("")){
							visitPlace=visitPlace+"-"+toCity;
						}
						bufferpage.append("&ascriptionPlace=" + ascriptionPlace + "");
						if (!ascriptionPlace.equals("")) {
							inoutBuffer.append(" and ascriptionPlace like '%" + ascriptionPlace + "%'");
						}
						bufferpage.append("&visitPlace=" + visitPlace + "");
						if (!visitPlace.equals("")) {
							inoutBuffer.append(" and visitPlace like '%" + visitPlace + "%'");
						}
					}
					if(cardType.equals("2")){
						ascriptionPlace="";
						visitPlace = "";
					}*/
					
					String msisdn = StaticMethod.null2String(request.getParameter("msisdn"));
					bufferpage.append("&msisdn=" + msisdn + "");
					if (!msisdn.equals("")) {
						inoutBuffer.append(" and msisdn='" + msisdn + "'");
					}
					ITawRmTestcardManager testCardMgr=(ITawRmTestcardManager)getBean("ItawRmTestcardManager");
					Map testCardMap = (Map)testCardMgr.getTawRmTestcards(pageIndex,new Integer(length), inoutBuffer.toString());
					List testCardList=(List)testCardMap.get("result");
					String testCardIdList="";
					if(testCardList!=null&&testCardList.size()!=0){
						for(int i=0;i<testCardList.size()-1;i++){
							TawRmTestcard tawRmTestcard=(TawRmTestcard)testCardList.get(i);
							testCardIdList=testCardIdList+"'"+tawRmTestcard.getId()+"',";
						}
						TawRmTestcard tawRmTestcard=(TawRmTestcard)testCardList.get(testCardList.size()-1);
						testCardIdList=testCardIdList+"'"+tawRmTestcard.getId()+"'";
						buffer.append(" and testcardId in("+testCardIdList+")");
					}else{
						buffer.append(" and testcardId in(null)");
					}
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
					
					String realReturnStartDate = StaticMethod.null2String(request.getParameter("realReturnStartDate"));
					bufferpage.append("&realReturnStartDate=" + realReturnStartDate + "");
					if (!realReturnStartDate.equals("")) {
						buffer.append(" and realReturnDate>= '" + realReturnStartDate + "'");
					}
					
					String realReturnEndDate = StaticMethod.null2String(request.getParameter("realReturnEndDate"));
					bufferpage.append("&realReturnEndDate=" + realReturnEndDate + "");
					if (!realReturnEndDate.equals("")) {
						buffer.append(" and realReturnDate<= '" + realReturnEndDate + "'");
					}

					whereStr = buffer.toString();
					request.setAttribute("whereStr", whereStr);
					System.out.println("whereStr============"+whereStr);
					String pageStr = bufferpage.toString();
					ITawRmInoutRecordManager mgr = (ITawRmInoutRecordManager) getBean("ItawRmInoutRecordManager");
					Map map = (Map) mgr.getTawRmInoutRecords(pageIndex,new Integer(length), whereStr);
					List list = (List) map.get("result");
					String url = request.getContextPath() + "/testcard"+ mapping.getPath() + ".do";
					//			 每页显示条数
					final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
					int size = ((Integer)map.get("total")).intValue();
					String pagerHeader = MemoPage.generate(pageIndex.intValue(), size, length, url,pageStr);
					request.setAttribute("pagerHeader", pagerHeader);
					request.setAttribute(OtherwiseConstacts.STATINOUTRECORDLIST, list);
					request.setAttribute("resultSize", map.get("total"));
					request.setAttribute("pageSize", pageSize);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return mapping.findForward("statInoutRecordList");
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
