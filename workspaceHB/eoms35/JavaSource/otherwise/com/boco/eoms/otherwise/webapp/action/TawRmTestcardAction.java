
package com.boco.eoms.otherwise.webapp.action;

import java.util.List;
import java.util.Map;

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
import com.boco.eoms.otherwise.model.TawRmTestcard;
import com.boco.eoms.otherwise.service.ITawRmInoutRecordManager;
import com.boco.eoms.otherwise.service.ITawRmTestcardManager;
import com.boco.eoms.otherwise.util.OtherwiseConstacts;
import com.boco.eoms.otherwise.webapp.form.TawRmTestcardForm;
import com.boco.eoms.workbench.memo.util.MemoPage;
import com.boco.eoms.workbench.netdisk.webapp.util.FilePathProcessor;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;

/**
 * Action class to handle CRUD on a TawRmTestcard object
 * @struts.action name="tawRmTestcardForm" path="/tawRmTestcards" scope="request"
 *  validate="false" parameter="method" input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * 
 * @struts.action-forward name="main" 
 *  path="/WEB-INF/pages/tawRmTestcard/tawRmTestcardTree.jsp" contextRelative="true"
 *
 * @struts.action-forward name="edit" 
 *  path="/WEB-INF/pages/tawRmTestcard/tawRmTestcardForm.jsp" contextRelative="true"
 *
 * @struts.action-forward name="list" 
 *  path="/WEB-INF/pages/tawRmTestcard/tawRmTestcardList.jsp" contextRelative="true"
 */
public final class TawRmTestcardAction extends BaseAction {
	// 定义页数长度
	private static int PAGE_LENGTH = 15;
	//private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool.getInstance();
		
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
				jsonRoot = initNodes();
			}
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
         return mapping.findForward("testcardMain");
    }
 	/**
	 * 根据父节点的id得到�?有子节点的JSON数据
	 */
	public void xGetChildNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = request.getParameter("node");

		ITawRmTestcardManager mgr = (ITawRmTestcardManager) getBean("ItawRmTestcardManager");
		JSONArray json = mgr.xGetChildNodes(nodeId);

		JSONUtil.print(response, json.toString());
	}

    /**
	 * ajax保存
	 */
    public void xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawRmTestcardForm tawRmTestcardForm = (TawRmTestcardForm) form;

		ITawRmTestcardManager mgr = (ITawRmTestcardManager) getBean("ItawRmTestcardManager");
		TawRmTestcard tawRmTestcard = (TawRmTestcard) convert(tawRmTestcardForm);
		mgr.saveTawRmTestcard(tawRmTestcard);
		// JSONUtil.print(response, "操作成功");
	}
    /**
	 * 根据模块或功能的编码，删除该对象
	 */
    public void xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawRmTestcardForm tawRmTestcardForm = (TawRmTestcardForm) form;

        ITawRmTestcardManager mgr = (ITawRmTestcardManager) getBean("ItawRmTestcardManager");
		mgr.removeTawRmTestcard(tawRmTestcardForm.getId());

	}
    /**
	 * ajax请求修改某节点的详细信息
	 */
    public String xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawRmTestcardForm tawRmTestcardForm = (TawRmTestcardForm) form;

		if (tawRmTestcardForm.getId() != null) {
			ITawRmTestcardManager mgr = (ITawRmTestcardManager) getBean("ItawRmTestcardManager");
			TawRmTestcard tawRmTestcard = (TawRmTestcard) convert(tawRmTestcardForm);

			mgr.saveTawRmTestcard(tawRmTestcard);
		   //mgr.updateTawRmTestcard(tawRmTestcard);
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
		ITawRmTestcardManager mgr = (ITawRmTestcardManager) getBean("ItawRmTestcardManager");
		TawRmTestcard tawRmTestcard = mgr.getTawRmTestcard(_strId);

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(tawRmTestcard);

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
	private JSONArray initNodes() {
		JSONArray jsonRoot = new JSONArray();
		JSONObject testcardRecord = new JSONObject();
		testcardRecord.put("id", "1");
		testcardRecord.put("text", "测试卡基本资料管理");
		testcardRecord.put("nodetype", "1");
		testcardRecord.put("allowTestCardAdd", true);
		testcardRecord.put("allowTestCardQuery", true);
		testcardRecord.put("allowOutAdd", false);
		testcardRecord.put("allowInAdd", false);
		testcardRecord.put("allowInOutQuery", false);
		testcardRecord.put("allowRenewAdd", false);
		testcardRecord.put("allowRenewSearch", false);
		testcardRecord.put("allowStatTestCard", false);
		testcardRecord.put("allowStatInoutRecord", false);
		testcardRecord.put("leaf", true);
		testcardRecord.put("iconCls", "file");
		JSONObject inoutRecord = new JSONObject();
		inoutRecord.put("id", "2");
		inoutRecord.put("text", "测试卡出入库管理");
		inoutRecord.put("nodetype", "2");
		inoutRecord.put("allowTestCardAdd", false);
		inoutRecord.put("allowTestCardQuery", false);
		inoutRecord.put("allowOutAdd", true);
		inoutRecord.put("allowInAdd", true);
		inoutRecord.put("allowInOutQuery", true);
		inoutRecord.put("allowRenewAdd", false);
		inoutRecord.put("allowRenewSearch", false);
		inoutRecord.put("allowStatTestCard", false);
		inoutRecord.put("allowStatInoutRecord", false);
		inoutRecord.put("leaf", true);
		inoutRecord.put("iconCls", "file");
		JSONObject renewRecord = new JSONObject();
		renewRecord.put("id", "3");
		renewRecord.put("text", "测试卡续借");
		renewRecord.put("nodetype", "3");
		renewRecord.put("allowTestCardAdd", false);
		renewRecord.put("allowTestCardQuery", false);
		renewRecord.put("allowOutAdd", false);
		renewRecord.put("allowInAdd", false);
		renewRecord.put("allowInOutQuery", false);
		renewRecord.put("allowRenewAdd", true);
		renewRecord.put("allowRenewSearch", true);
		renewRecord.put("allowStatTestCard", false);
		renewRecord.put("allowStatInoutRecord", false);
		renewRecord.put("leaf", true);
		renewRecord.put("iconCls", "file");
		JSONObject statTestCard = new JSONObject();
		statTestCard.put("id", "4");
		statTestCard.put("text", "测试卡查询统计");
		statTestCard.put("nodetype", "4");
		statTestCard.put("allowTestCardAdd", false);
		statTestCard.put("allowTestCardQuery", false);
		statTestCard.put("allowOutAdd", false);
		statTestCard.put("allowInAdd", false);
		statTestCard.put("allowInOutQuery", false);
		statTestCard.put("allowRenewAdd", false);
		statTestCard.put("allowRenewSearch", false);
		statTestCard.put("allowStatTestCard", true);
		statTestCard.put("allowStatInoutRecord", true);
		statTestCard.put("leaf", true);
		statTestCard.put("iconCls", "file");
		jsonRoot.put(testcardRecord);
		jsonRoot.put(inoutRecord);
		jsonRoot.put(renewRecord);
		jsonRoot.put(statTestCard);
		return jsonRoot;
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
					OtherwiseConstacts.TAW_RM_TESTCARDLIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(GenericValidator
					.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
					: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

	  	TawRmTestcardForm tawRmTestcardForm=(TawRmTestcardForm)form;
			String cardType=StaticMethod.null2String(tawRmTestcardForm.getTmpCardType());
			String fromProvince=StaticMethod.nullObject2String(tawRmTestcardForm.getTmpFromProvince());
			String fromCity=StaticMethod.nullObject2String(tawRmTestcardForm.getTmpFromCity());
			String fromCountry=StaticMethod.nullObject2String(tawRmTestcardForm.getTmpFromCountry());
			String fromOperator=StaticMethod.nullObject2String(tawRmTestcardForm.getTmpFromOperator());
			String toProvince=StaticMethod.nullObject2String(tawRmTestcardForm.getTmpToProvince());
			String toCity=StaticMethod.nullObject2String(tawRmTestcardForm.getTmpToCity());
			String toCountry=StaticMethod.nullObject2String(tawRmTestcardForm.getTmpToCountry());
			String toOperator=StaticMethod.nullObject2String(tawRmTestcardForm.getTmpToOperator());
			String supplyer=StaticMethod.null2String(tawRmTestcardForm.getTmpSupplyer());
			String iccid=StaticMethod.null2String(tawRmTestcardForm.getTmpIccid());
			String msisdn=StaticMethod.null2String(tawRmTestcardForm.getTmpMsisdn());
			String imsi=StaticMethod.null2String(tawRmTestcardForm.getTmpImsi());
			String pin=StaticMethod.null2String(tawRmTestcardForm.getTmpPin());
			String puk=StaticMethod.null2String(tawRmTestcardForm.getTmpPuk());
			String openAccountDate=StaticMethod.null2String(tawRmTestcardForm.getTmpOpenAccountDate());
			String logoutDate=StaticMethod.null2String(tawRmTestcardForm.getTmpLogoutDate());
			String takeOverDate=StaticMethod.null2String(tawRmTestcardForm.getTmpTakeOverDate());
			String state=StaticMethod.null2String(tawRmTestcardForm.getTmpState());
			whereStr = request.getParameter("whereStr");
			StringBuffer buffer = new StringBuffer();
			StringBuffer bufferpage = new StringBuffer();
			buffer.append(" where 1=1");
			bufferpage.append("method=searchList");
			
			bufferpage.append("&cardType=" + cardType + "");
			if (!cardType.equals("")&&!cardType.equals("2")) {
				buffer.append(" and cardType = '" + cardType + "'");
			}

			bufferpage.append("&fromCountry=" + fromCountry + "");
			if (!fromCountry.equals("")&&!cardType.equals("2")) {
				buffer.append(" and fromCountry = '" + fromCountry + "'");
			}

			bufferpage.append("&fromOperator=" + fromOperator + "");
			if (!fromOperator.equals("")&&!cardType.equals("2")) {
				buffer.append(" and fromOperator = '" + fromOperator + "'");
			}
			
			bufferpage.append("&fromProvince=" + fromProvince + "");
			if (!fromProvince.equals("")&&!cardType.equals("2")) {
				buffer.append(" and fromProvince = '" + fromProvince + "'");
			}

			bufferpage.append("&fromCity=" + fromCity + "");
			if (!fromCity.equals("")&&!cardType.equals("2")) {
				buffer.append(" and fromCity = '" + fromCity + "'");
			}

			bufferpage.append("&toCountry=" + toCountry + "");
			if (!toCountry.equals("")&&!cardType.equals("2")) {
				buffer.append(" and toCountry = '" + toCountry + "'");
			}

			bufferpage.append("&toOperator=" + toOperator + "");
			if (!toOperator.equals("")&&!cardType.equals("2")) {
				buffer.append(" and toOperator = '" + toOperator + "'");
			}
			
			bufferpage.append("&toProvince=" + toProvince + "");
			if (!toProvince.equals("")&&!cardType.equals("2")) {
				buffer.append(" and toProvince = '" + toProvince + "'");
			}

			bufferpage.append("&toCity=" + toCity + "");
			if (!toCity.equals("")&&!cardType.equals("2")) {
				buffer.append(" and toCity = '" + toCity + "'");
			}
			
			bufferpage.append("&supplyer=" + supplyer + "");
			if (!supplyer.equals("")) {
				buffer.append(" and supplyer like '%" + supplyer + "%'");
			}
			
			bufferpage.append("&iccid=" + iccid + "");
			if (!iccid.equals("")) {
				buffer.append(" and iccid = '" + iccid + "'");
			}
			
			bufferpage.append("&msisdn=" + msisdn + "");
			if (!msisdn.equals("")) {
				buffer.append(" and msisdn = '" + msisdn + "'");
			}
			
			bufferpage.append("&imsi=" + imsi + "");
			if (!imsi.equals("")) {
				buffer.append(" and imsi = '" + imsi + "'");
			}
			
			bufferpage.append("&pin=" + pin + "");
			if (!pin.equals("")) {
				buffer.append(" and pin = '" + pin + "'");
			}
			
			bufferpage.append("&puk=" + puk + "");
			if (!puk.equals("")) {
				buffer.append(" and puk = '" + puk + "'");
			}
			
			bufferpage.append("&openAccountDate=" + openAccountDate + "");
			if (!openAccountDate.equals("")) {
				buffer.append(" and openAccountDate='" + openAccountDate + "'");
			}
			
			bufferpage.append("&logoutDate=" + logoutDate + "");
			if (!logoutDate.equals("")) {
				buffer.append(" and logoutDate='" + logoutDate + "'");
			}
			
			bufferpage.append("&takeOverDate=" + takeOverDate + "");
			if (!takeOverDate.equals("")) {
				buffer.append(" and takeOverDate='" + takeOverDate + "'");
			}
			
			bufferpage.append("&state=" + state + "");
			if (!state.equals("")) {
				buffer.append(" and state='" + state + "'");
			}
			
			whereStr = buffer.toString();
			request.setAttribute("whereStr", whereStr);
			String pageStr = bufferpage.toString();
			ITawRmTestcardManager mgr=(ITawRmTestcardManager)getBean("ItawRmTestcardManager");
			Map map = (Map) mgr.getTawRmTestcards(pageIndex,new Integer(length), whereStr);
			List list = (List) map.get("result");
			String url = request.getContextPath() + "/testcard"+ mapping.getPath() + ".do";
			//			 每页显示条数
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
			int size = ((Integer)map.get("total")).intValue();
			String pagerHeader = MemoPage.generate(pageIndex.intValue(), size, length, url,pageStr);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute(OtherwiseConstacts.TAW_RM_TESTCARDLIST, list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("list");
	}
	

	/**
	 * 
	 * @see 根据条件查询测试卡记录
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
					OtherwiseConstacts.TAW_RM_TESTCARDLIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(GenericValidator
					.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
					: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
			
			whereStr = request.getParameter("whereStr");
			StringBuffer buffer = new StringBuffer();
			StringBuffer bufferpage = new StringBuffer();
			buffer.append(" where 1=1");
			bufferpage.append("method=searchList");

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
			
			/**String ascriptionPlace="";
			String visitPlace = "";
			if(cardType.equals("0")){
				String fromTmp1=cardType+"-"+fromCountry;
				ascriptionPlace=cardType+"-"+fromCountry;
				buffer.append(" and ascriptionPlace like '" + fromTmp1 + "%'");
				if(!fromOperator.equals("")){
					String fromTmp2="-"+fromOperator;
					ascriptionPlace=ascriptionPlace+fromTmp2;
					buffer.append(" and ascriptionPlace like '%" + fromTmp2 + "'");
				}
				String toTmp1=cardType+"-"+toCountry;
				visitPlace=cardType+"-"+toCountry;
				buffer.append(" and visitPlace like '" + toTmp1 + "%'");
				if(!toOperator.equals("")){
					String toTmp2="-"+toOperator;
					visitPlace=visitPlace+toTmp2;
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
				if (!ascriptionPlace.equals("")) {
					buffer.append(" and ascriptionPlace like '%" + ascriptionPlace + "%'");
				}
				
				bufferpage.append("&visitPlace=" + visitPlace + "");
				if (!visitPlace.equals("")) {
					buffer.append(" and visitPlace like '%" + visitPlace + "%'");
				}
			}
			if(cardType.equals("2")){
				ascriptionPlace="";
				visitPlace = "";
			}
			bufferpage.append("&ascriptionPlace=" + ascriptionPlace + "");
			bufferpage.append("&visitPlace=" + visitPlace + "");*/
			String supplyer = StaticMethod.null2String(request.getParameter("supplyer"));
			bufferpage.append("&supplyer=" + supplyer + "");
			if (!supplyer.equals("")) {
				buffer.append(" and supplyer like '%" + supplyer + "%'");
			}
			
			String iccid = StaticMethod.null2String(request.getParameter("iccid"));
			bufferpage.append("&iccid=" + iccid + "");
			if (!iccid.equals("")) {
				buffer.append(" and iccid = '" + iccid + "'");
			}
			
			String msisdn = StaticMethod.null2String(request.getParameter("msisdn"));
			bufferpage.append("&msisdn=" + msisdn + "");
			if (!msisdn.equals("")) {
				buffer.append(" and msisdn = '" + msisdn + "'");
			}
			
			String imsi = StaticMethod.null2String(request.getParameter("imsi"));
			bufferpage.append("&imsi=" + imsi + "");
			if (!imsi.equals("")) {
				buffer.append(" and imsi = '" + imsi + "'");
			}
			
			String pin = StaticMethod.null2String(request.getParameter("pin"));
			bufferpage.append("&pin=" + pin + "");
			if (!pin.equals("")) {
				buffer.append(" and pin = '" + pin + "'");
			}
			
			String puk = StaticMethod.null2String(request.getParameter("puk"));
			bufferpage.append("&puk=" + puk + "");
			if (!puk.equals("")) {
				buffer.append(" and puk = '" + puk + "'");
			}
			
			String openAccountDate = StaticMethod.null2String(request.getParameter("openAccountDate"));
			bufferpage.append("&openAccountDate=" + openAccountDate + "");
			if (!openAccountDate.equals("")) {
				buffer.append(" and openAccountDate='" + openAccountDate + "'");
			}
			
			String logoutDate = StaticMethod.null2String(request.getParameter("logoutDate"));
			bufferpage.append("&logoutDate=" + logoutDate + "");
			if (!logoutDate.equals("")) {
				buffer.append(" and logoutDate='" + logoutDate + "'");
			}
			
			String takeOverDate = StaticMethod.null2String(request.getParameter("takeOverDate"));
			bufferpage.append("&takeOverDate=" + takeOverDate + "");
			if (!takeOverDate.equals("")) {
				buffer.append(" and takeOverDate='" + takeOverDate + "'");
			}
			
			String state = StaticMethod.null2String(request.getParameter("state"));
			bufferpage.append("&state=" + state + "");
			if (!state.equals("")) {
				buffer.append(" and state='" + state + "'");
			}

			whereStr = buffer.toString();
			request.setAttribute("whereStr", whereStr);
			System.out.println("whereStr============="+whereStr);
			String pageStr = bufferpage.toString();
			ITawRmTestcardManager mgr=(ITawRmTestcardManager)getBean("ItawRmTestcardManager");
			Map map = (Map) mgr.getTawRmTestcards(pageIndex,new Integer(length), whereStr);
			List list = (List) map.get("result");
			String url = request.getContextPath() + "/testcard"+ mapping.getPath() + ".do";
			//			 每页显示条数
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
			int size = ((Integer)map.get("total")).intValue();
			String pagerHeader = MemoPage.generate(pageIndex.intValue(), size, length, url,pageStr);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute(OtherwiseConstacts.TAW_RM_TESTCARDLIST, list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("list");
	}

	


  public ActionForward edit(ActionMapping mapping, ActionForm form,
       	HttpServletRequest request,HttpServletResponse response)
      	throws Exception {
  	TawRmTestcardForm tawRmTestcardForm=(TawRmTestcardForm)form;
	try {
		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		//String workSerial=sessionform.getWorkSerial();
		String id = StaticMethod.nullObject2String(request.getParameter("id"));
		if(id==null||id.trim().equals("")){
			if (sessionform == null) {
				return mapping.findForward("timeout");
			}
		}
		HttpSession session=request.getSession();
		String tmpCardType=StaticMethod.nullObject2String(session.getAttribute("myCardType"));
		String tmpFromProvince=StaticMethod.nullObject2String(session.getAttribute("myFromProvince"));
		String tmpFromCity=StaticMethod.nullObject2String(session.getAttribute("myFromCity"));
		String tmpFromCountry=StaticMethod.nullObject2String(session.getAttribute("myFromCountry"));
		String tmpFromOperator=StaticMethod.nullObject2String(session.getAttribute("myFromOperator"));
		String tmpToProvince=StaticMethod.nullObject2String(session.getAttribute("myToProvince"));
		String tmpToCity=StaticMethod.nullObject2String(session.getAttribute("myToCity"));
		String tmpToCountry=StaticMethod.nullObject2String(session.getAttribute("myToCountry"));
		String tmpToOperator=StaticMethod.nullObject2String(session.getAttribute("myToOperator"));
		String tmpSupplyer=StaticMethod.nullObject2String(session.getAttribute("mySupplyer"));
		String tmpIccid=StaticMethod.nullObject2String(session.getAttribute("myIccid"));
		String tmpMsisdn=StaticMethod.nullObject2String(session.getAttribute("myMsisdn"));
		String tmpImsi=StaticMethod.nullObject2String(session.getAttribute("myImsi"));
		String tmpPin=StaticMethod.nullObject2String(session.getAttribute("myPin"));
		String tmpOpenAccountDate=StaticMethod.nullObject2String(session.getAttribute("myOpenAccountDate"));
		String tmpLogoutDate=StaticMethod.nullObject2String(session.getAttribute("myLogoutDate"));
		String tmpTakeOverDate=StaticMethod.nullObject2String(session.getAttribute("myTakeOverDate"));
		String tmpState=StaticMethod.nullObject2String(session.getAttribute("myState"));
		tawRmTestcardForm.setTmpCardType(tmpCardType);
		tawRmTestcardForm.setTmpFromProvince(tmpFromProvince);
		tawRmTestcardForm.setFromCity(tmpFromCity);
		tawRmTestcardForm.setFromCountry(tmpFromCountry);
		tawRmTestcardForm.setFromOperator(tmpFromOperator);
		tawRmTestcardForm.setToProvince(tmpToProvince);
		tawRmTestcardForm.setTmpToCity(tmpToCity);
		tawRmTestcardForm.setTmpToCountry(tmpToCountry);
		tawRmTestcardForm.setTmpToOperator(tmpToOperator);
		tawRmTestcardForm.setTmpSupplyer(tmpSupplyer);
		tawRmTestcardForm.setTmpIccid(tmpIccid);
		tawRmTestcardForm.setTmpMsisdn(tmpMsisdn);
		tawRmTestcardForm.setTmpImsi(tmpImsi);
		tawRmTestcardForm.setTmpPin(tmpPin);
		tawRmTestcardForm.setTmpOpenAccountDate(tmpOpenAccountDate);
		tawRmTestcardForm.setTmpLogoutDate(tmpLogoutDate);
		tawRmTestcardForm.setTmpTakeOverDate(tmpTakeOverDate);
		tawRmTestcardForm.setTmpState(tmpState);
		session.removeAttribute("myCardType");
		session.removeAttribute("myFromProvince");
		session.removeAttribute("myFromCity");
		session.removeAttribute("myFromCountry");
		session.removeAttribute("myFromOperator");
		session.removeAttribute("myToProvince");
		session.removeAttribute("myToCity");
		session.removeAttribute("myToCountry");
		session.removeAttribute("myToOperator");
		session.removeAttribute("mySupplyer");
		session.removeAttribute("myIccid");
		session.removeAttribute("myMsisdn");
		session.removeAttribute("myImsi");
		session.removeAttribute("myPin");
		session.removeAttribute("myOpenAccountDate");
		session.removeAttribute("myLogoutDate");
		session.removeAttribute("myTakeOverDate");
		session.removeAttribute("myState");
		if(tawRmTestcardForm.getId()!=null){
			ITawRmTestcardManager mgr=(ITawRmTestcardManager)getBean("ItawRmTestcardManager");
			if (id != null && !id.equals("")) {
				TawRmTestcard tawRmTestcard=mgr.getTawRmTestcard(id);
				if(tawRmTestcard!=null){
					tawRmTestcardForm =(TawRmTestcardForm)convert(tawRmTestcard);
				}
			}
			request.setAttribute("recordId", id);
			String cardType=tawRmTestcardForm.getCardType();
			request.setAttribute("cardType",cardType);
			if(cardType.equals("1")){
				String fromProvince=tawRmTestcardForm.getFromProvince();
				String fromCity=tawRmTestcardForm.getFromCity();
				String toProvince=tawRmTestcardForm.getToProvince();
				String toCity=tawRmTestcardForm.getToCity();
				request.setAttribute("fromProvince",fromProvince);
				request.setAttribute("fromCity",fromCity);
				request.setAttribute("toProvince",toProvince);
				request.setAttribute("toCity",toCity);
			}
			/**String[] fromTmp=tawRmTestcardForm.getAscriptionPlace().split("-");
			String[] toTmp=tawRmTestcardForm.getVisitPlace().split("-");
			String cardType=fromTmp[0];
			request.setAttribute("cardType",cardType);
			if(cardType.equals("0")){
				String fromCountry=fromTmp[1];
				String fromOperator=fromTmp[2];
				String toCountry=toTmp[1];
				String toOperator=toTmp[2];
				tawRmTestcardForm.setFromCountry(fromCountry);
				tawRmTestcardForm.setFromOperator(fromOperator);
				tawRmTestcardForm.setToCountry(toCountry);
				tawRmTestcardForm.setToOperator(toOperator);
			}else if(cardType.equals("1")){
				String fromProvince=fromTmp[1];
				String fromCity=fromTmp[2];
				String toProvince=toTmp[1];
				String toCity=toTmp[2];
				tawRmTestcardForm.setFromProvince(fromProvince);
				tawRmTestcardForm.setFromCity(fromCity);
				tawRmTestcardForm.setToProvince(toProvince);
				tawRmTestcardForm.setToCity(toCity);
				request.setAttribute("fromProvince",fromProvince);
				request.setAttribute("fromCity",fromCity);
				request.setAttribute("toProvince",toProvince);
				request.setAttribute("toCity",toCity);
			}*/
			String iccid=tawRmTestcardForm.getIccid();
			String msisdn=tawRmTestcardForm.getMsisdn();
			String imsi=tawRmTestcardForm.getImsi();
			session.setAttribute("oldIccid",iccid);
			session.setAttribute("oldMsisdn",msisdn);
			session.setAttribute("oldImsi",imsi);
			updateFormBean(mapping, request, tawRmTestcardForm);
		}else{
			request.setAttribute("cardType","0");
		}
		
		String supplyer=StaticMethod.null2String(tawRmTestcardForm.getSupplyer());
		String fromCountry=StaticMethod.null2String(tawRmTestcardForm.getFromCountry());
		request.setAttribute("supplyer",supplyer);
		request.setAttribute("fromCountry",fromCountry);
	}catch (Exception e) {
		e.printStackTrace();
	}
    return mapping.findForward("edit");
  }
	
	// 删除 返回查询页面
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages messages = new ActionMessages();
  	TawRmTestcardForm tawRmTestcardForm=(TawRmTestcardForm)form;
		try {
			// Exceptions are caught by ActionExceptionHandler
			ITawRmTestcardManager mgr=(ITawRmTestcardManager)getBean("ItawRmTestcardManager");
			mgr.removeTawRmTestcard(tawRmTestcardForm.getId());

			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawRmTestcardForm.deleted"));

			// save messages in session, so they'll survive the redirect
			saveMessages(request.getSession(), messages);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchList2(mapping, form, request, response);
	}
  
	
 /**
 * 
 * @see 保存测试卡记录
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
	TawRmTestcardForm tawRmTestcardForm=(TawRmTestcardForm)form;
	//String cardType=tawRmTestcardForm.getCardType();
	HttpSession session=request.getSession();
	/**if(cardType.equals("0")){
		String fromCountry=tawRmTestcardForm.getFromCountry();
		String fromOperator=tawRmTestcardForm.getFromOperator();
		String ascriptionPlace=cardType+"-"+fromCountry+"-"+fromOperator;
		String toCountry=tawRmTestcardForm.getToCountry();
		String toOperator=tawRmTestcardForm.getToOperator();
		String visitPlace=cardType+"-"+toCountry+"-"+toOperator;
		tawRmTestcardForm.setAscriptionPlace(ascriptionPlace);
		tawRmTestcardForm.setVisitPlace(visitPlace);
	}else if(cardType.equals("1")){
		String fromProvince=tawRmTestcardForm.getFromProvince();
		String fromCity=tawRmTestcardForm.getFromCity();
		String ascriptionPlace=cardType+"-"+fromProvince+"-"+fromCity;
		tawRmTestcardForm.setAscriptionPlace(ascriptionPlace);
		String toProvince=tawRmTestcardForm.getToProvince();
		String toCity=tawRmTestcardForm.getToCity();
		String visitPlace=cardType+"-"+toProvince+"-"+toCity;
		tawRmTestcardForm.setVisitPlace(visitPlace);
	}*/
	String oldIccid=StaticMethod.nullObject2String(session.getAttribute("oldIccid"));
	String oldMsisdn=StaticMethod.nullObject2String(session.getAttribute("oldMsisdn"));
	String oldImsi=StaticMethod.nullObject2String(session.getAttribute("oldImsi"));
	session.removeAttribute("oldIccid");
	session.removeAttribute("oldMsisdn");
	session.removeAttribute("oldImsi");
	int length = PAGE_LENGTH;
	StringBuffer buffer = new StringBuffer();
	boolean isNew=(tawRmTestcardForm.getId().equals("")||tawRmTestcardForm.getId()==null);
	buffer.append(" where 1<>1");
	String iccid = StaticMethod.null2String(request.getParameter("iccid"));
	if(!iccid.trim().equals(oldIccid)&&!isNew){
		if (!iccid.trim().equals("")) {
			buffer.append(" or iccid = '" + iccid + "'");
		}
	}
	if(isNew){
		if (!iccid.trim().equals("")) {
			buffer.append(" or iccid = '" + iccid + "'");
		}
	}
	
	String msisdn = StaticMethod.null2String(request.getParameter("msisdn"));
	if(!msisdn.trim().equals(oldMsisdn)&&!isNew){
		if (!msisdn.trim().equals("")) {
			buffer.append(" or msisdn = '" + msisdn + "'");
		}
	}
	if(isNew){
		if (!msisdn.trim().equals("")) {
			buffer.append(" or msisdn = '" + msisdn + "'");
		}
	}
	String imsi = StaticMethod.null2String(request.getParameter("imsi"));
	if(!imsi.trim().equals(oldImsi)&&!isNew){
		if (!imsi.trim().equals("")) {
			buffer.append(" or imsi = '" + imsi + "'");
		}
	}
	if(isNew){
		if (!imsi.trim().equals("")) {
			buffer.append(" or imsi = '" + imsi + "'");
		}
	}
	String whereStr=buffer.toString();
	String pageIndexName = new org.displaytag.util.ParamEncoder(
			OtherwiseConstacts.TAW_RM_TESTCARDLIST)
			.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

	final Integer pageIndex = new Integer(GenericValidator
			.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
			: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
	
	ITawRmTestcardManager mgr=(ITawRmTestcardManager)getBean("ItawRmTestcardManager");
	Map map = (Map) mgr.getTawRmTestcards(pageIndex,new Integer(length), whereStr);
	List list = (List) map.get("result");
	if(list.size()>0){
		return mapping.findForward("warning");
	}
	TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
	final String userId = sessionform.getUserid();
	TawRmTestcard tawRmTestcard=(TawRmTestcard)convert(tawRmTestcardForm);
	tawRmTestcard.setUserId(userId);
	mgr.saveTawRmTestcard(tawRmTestcard);
	if (isNew) {
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"TawRmTestcard.added"));

		// save messages in session to survive a redirect
		saveMessages(request.getSession(), messages);

		return search(mapping, form, request, response);
	} else {
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"TawRmTestcard.updated"));
		saveMessages(request, messages);

		return search(mapping, form, request, response);
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
	int length = PAGE_LENGTH;
	try {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				OtherwiseConstacts.TAW_RM_TESTCARDLIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		
		StringBuffer bufferpage = new StringBuffer();
		bufferpage.append("method=search");

		String pageStr = bufferpage.toString();
		ITawRmTestcardManager mgr=(ITawRmTestcardManager)getBean("ItawRmTestcardManager");
		Map map = (Map) mgr.getTawRmTestcards(pageIndex,new Integer(length));
		List list = (List) map.get("result");
		String url = request.getContextPath() + "/testcard"+ mapping.getPath() + ".do";
		//			 每页显示条数
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
		int size = ((Integer)map.get("total")).intValue();
		String pagerHeader = MemoPage.generate(pageIndex.intValue(), size, length, url,pageStr);
		request.setAttribute("pagerHeader", pagerHeader);
		request.setAttribute(OtherwiseConstacts.TAW_RM_TESTCARDLIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return mapping.findForward("list");
}

	public ActionForward testCardSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("cardType","2");
		return mapping.findForward("testCardSearch");
	}
	
	public ActionForward toStatTestCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("statTestCard");
	}
	
	public ActionForward statTestCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int length = PAGE_LENGTH;
		try {
			String whereStr = "";
			String pageIndexName = new org.displaytag.util.ParamEncoder(
					OtherwiseConstacts.STATTESTCARDLIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(GenericValidator
					.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
					: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
			
			whereStr = request.getParameter("whereStr");
			StringBuffer buffer = new StringBuffer();
			StringBuffer bufferpage = new StringBuffer();
			buffer.append(" where 1=1");
			bufferpage.append("method=statTestCard");

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
				ascriptionPlace=cardType+"-"+fromCountry;
				if(!fromOperator.equals("")){
					ascriptionPlace=ascriptionPlace+"-"+fromOperator;
				}
				visitPlace=cardType+"-"+toCountry;
				if(!toOperator.equals("")){
					visitPlace=visitPlace+"-"+toOperator;
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
			}
			if(cardType.equals("2")){
				ascriptionPlace="";
				visitPlace = "";
			}
			bufferpage.append("&ascriptionPlace=" + ascriptionPlace + "");
			request.setAttribute("ascriptionPlace",ascriptionPlace);
			request.setAttribute("visitPlace",visitPlace);
			if (!ascriptionPlace.equals("")) {
				buffer.append(" and ascriptionPlace like '%" + ascriptionPlace + "%'");
			}
			bufferpage.append("&visitPlace=" + visitPlace + "");
			if (!visitPlace.equals("")) {
				buffer.append(" and visitPlace like '%" + visitPlace + "%'");
			}*/
			
			String state = StaticMethod.null2String(request.getParameter("state"));
			bufferpage.append("&state=" + state + "");
			if (!state.equals("")) {
				buffer.append(" and state='" + state + "'");
			}
			
			StringBuffer inoutBuffer = new StringBuffer();
			inoutBuffer.append("");
			String borrowerId = StaticMethod.null2String(request.getParameter("borrowerId"));
			bufferpage.append("&borrowerId=" + borrowerId + "");
			if (!borrowerId.equals("")) {
				inoutBuffer.append(" and b.borrower_id='" + borrowerId + "'");
			}
			String borrowStartDate = StaticMethod.null2String(request.getParameter("borrowStartDate"));
			bufferpage.append("&borrowStartDate=" + borrowStartDate + "");
			if (!borrowStartDate.equals("")) {
				inoutBuffer.append(" and b.borrow_date>='" + borrowStartDate + "'");
			}

			String borrowEndDate = StaticMethod.null2String(request.getParameter("borrowEndDate"));
			bufferpage.append("&borrowEndDate=" + borrowEndDate + "");
			if (!borrowEndDate.equals("")) {
				inoutBuffer.append(" and b.borrow_date<='" + borrowEndDate + "'");
			}
			
			String intendingReturnStartDate = StaticMethod.null2String(request.getParameter("intendingReturnStartDate"));
			bufferpage.append("&intendingReturnStartDate=" + intendingReturnStartDate + "");
			if (!intendingReturnStartDate.equals("")) {
				inoutBuffer.append(" and b.intending_return_date>= '" + intendingReturnStartDate + "'");
			}

			String intendingReturnEndDate = StaticMethod.null2String(request.getParameter("intendingReturnEndDate"));
			bufferpage.append("&intendingReturnEndDate=" + intendingReturnEndDate + "");
			if (!intendingReturnEndDate.equals("")) {
				inoutBuffer.append(" and b.intending_return_date<= '" + intendingReturnEndDate + "'");
			}
			if(!inoutBuffer.toString().equals("")){
				ITawRmInoutRecordManager inoutMgr = (ITawRmInoutRecordManager) getBean("ItawRmInoutRecordManager");
				List idList=inoutMgr.getIdList(inoutBuffer.toString());
				String tmp="";
				if(idList!=null&&idList.size()!=0){
					for(int i=0;i<idList.size()-1;i++){
						ListOrderedMap item = (ListOrderedMap) idList.get(i);
	  				String id=(String)item.getValue(0);
	  				tmp=tmp+"'"+id+"',";
					}
					ListOrderedMap item = (ListOrderedMap) idList.get(idList.size()-1);
					String id=(String)item.getValue(0);
					tmp=tmp+"'"+id+"'";
					buffer.append(" and id in (" + tmp + ")");
				}else{
					buffer.append(" and id in (null)");
				}
			}
			whereStr = buffer.toString();
			request.setAttribute("whereStr", whereStr);
			String pageStr = bufferpage.toString();
			ITawRmTestcardManager mgr=(ITawRmTestcardManager)getBean("ItawRmTestcardManager");
			Map map = (Map) mgr.getTawRmTestcards(pageIndex,new Integer(length), whereStr);
			List list = (List) map.get("result");
			String url = request.getContextPath() + "/testcard"+ mapping.getPath() + ".do";
			//			 每页显示条数
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
			int size = ((Integer)map.get("total")).intValue();
			String pagerHeader = MemoPage.generate(pageIndex.intValue(), size, length, url,pageStr);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute(OtherwiseConstacts.STATTESTCARDLIST, list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("statTestCardList");
	}

	
	public ActionForward statTestCard2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int length = PAGE_LENGTH;
		try {
			TawRmTestcardForm tawRmTestcardForm=(TawRmTestcardForm)form;
			String whereStr = "";
			String pageIndexName = new org.displaytag.util.ParamEncoder(
					OtherwiseConstacts.STATTESTCARDLIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(GenericValidator
					.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
					: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
			
			whereStr = request.getParameter("whereStr");
			StringBuffer buffer = new StringBuffer();
			StringBuffer bufferpage = new StringBuffer();
			buffer.append(" where 1=1");
			bufferpage.append("method=statTestCard");
			
			/**String ascriptionPlace = tawRmTestcardForm.getAscriptionPlace();
			bufferpage.append("&ascriptionPlace=" + ascriptionPlace + "");
			if (!ascriptionPlace.equals("")) {
				buffer.append(" and ascriptionPlace like '%" + ascriptionPlace + "%'");
			}
			String visitPlace = tawRmTestcardForm.getVisitPlace();
			bufferpage.append("&visitPlace=" + visitPlace + "");
			if (!visitPlace.equals("")) {
				buffer.append(" and visitPlace like '%" + visitPlace + "%'");
			}*/
			
			String state = tawRmTestcardForm.getState();
			bufferpage.append("&state=" + state + "");
			if (!state.equals("")) {
				buffer.append(" and state='" + state + "'");
			}
			
			StringBuffer inoutBuffer = new StringBuffer();
			String borrowerId = tawRmTestcardForm.getBorrowerId();
			bufferpage.append("&borrowerId=" + borrowerId + "");
			if (!borrowerId.equals("")) {
				inoutBuffer.append(" and b.borrower_id='" + borrowerId + "'");
			}
			String borrowStartDate = tawRmTestcardForm.getBorrowStartDate();
			bufferpage.append("&borrowStartDate=" + borrowStartDate + "");
			if (!borrowStartDate.equals("")) {
				inoutBuffer.append(" and b.borrow_date>='" + borrowStartDate + "'");
			}

			String borrowEndDate = tawRmTestcardForm.getBorrowEndDate();
			bufferpage.append("&borrowEndDate=" + borrowEndDate + "");
			if (!borrowEndDate.equals("")) {
				inoutBuffer.append(" and b.borrow_date<='" + borrowEndDate + "'");
			}
			
			String intendingReturnStartDate = tawRmTestcardForm.getIntendingReturnStartDate();
			bufferpage.append("&intendingReturnStartDate=" + intendingReturnStartDate + "");
			if (!intendingReturnStartDate.equals("")) {
				inoutBuffer.append(" and b.intending_return_date>= '" + intendingReturnStartDate + "'");
			}

			String intendingReturnEndDate = tawRmTestcardForm.getIntendingReturnEndDate();
			bufferpage.append("&intendingReturnEndDate=" + intendingReturnEndDate + "");
			if (!intendingReturnEndDate.equals("")) {
				inoutBuffer.append(" and b.intending_return_date<= '" + intendingReturnEndDate + "'");
			}
			ITawRmInoutRecordManager inoutMgr = (ITawRmInoutRecordManager) getBean("ItawRmInoutRecordManager");
			List idList=inoutMgr.getIdList(inoutBuffer.toString());
			String tmp="";
			if(idList!=null&&idList.size()!=0){
				for(int i=0;i<idList.size()-1;i++){
					ListOrderedMap item = (ListOrderedMap) idList.get(i);
  				String id=(String)item.getValue(0);
  				tmp=tmp+"'"+id+"',";
				}
				ListOrderedMap item = (ListOrderedMap) idList.get(idList.size()-1);
				String id=(String)item.getValue(0);
				tmp=tmp+"'"+id+"'";
				buffer.append(" and id in (" + tmp + ")");
			}
			whereStr = buffer.toString();
			request.setAttribute("whereStr", whereStr);
			String pageStr = bufferpage.toString();
			ITawRmTestcardManager mgr=(ITawRmTestcardManager)getBean("ItawRmTestcardManager");
			Map map = (Map) mgr.getTawRmTestcards(pageIndex,new Integer(length), whereStr);
			List list = (List) map.get("result");
			String url = request.getContextPath() + "/testcard"+ mapping.getPath() + ".do";
			//			 每页显示条数
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
			int size = ((Integer)map.get("total")).intValue();
			String pagerHeader = MemoPage.generate(pageIndex.intValue(), size, length, url,pageStr);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute(OtherwiseConstacts.STATTESTCARDLIST, list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("statTestCardList");
	}

  
  public ActionForward viewContent(ActionMapping mapping, ActionForm form,
       	HttpServletRequest request,HttpServletResponse response)
      	throws Exception {
	try {
		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		HttpSession session=request.getSession();
		String id = StaticMethod.nullObject2String(request.getParameter("id"));
		TawRmTestcardForm tawRmTestcardForm=(TawRmTestcardForm)form;
		String state=StaticMethod.nullObject2String(session.getAttribute("myState"));
		String borrowerId=StaticMethod.nullObject2String(session.getAttribute("myBorrowerId"));
		String borrowStartDate=StaticMethod.nullObject2String(session.getAttribute("myBorrowStartDate"));
		String borrowEndDate=StaticMethod.nullObject2String(session.getAttribute("myBorrowEndDate"));
		String intendingReturnStartDate=StaticMethod.nullObject2String(session.getAttribute("myIntendingReturnStartDate"));
		String intendingReturnEndDate=StaticMethod.nullObject2String(session.getAttribute("myIntendingReturnEndDate"));
		//tawRmTestcardForm.setAscriptionPlace(ascriptionPlace);
		//tawRmTestcardForm.setVisitPlace(visitPlace);
		tawRmTestcardForm.setState(state);
		tawRmTestcardForm.setBorrowerId(borrowerId);
		tawRmTestcardForm.setBorrowStartDate(borrowStartDate);
		tawRmTestcardForm.setBorrowEndDate(borrowEndDate);
		tawRmTestcardForm.setIntendingReturnStartDate(intendingReturnStartDate);
		tawRmTestcardForm.setIntendingReturnEndDate(intendingReturnEndDate);
		session.removeAttribute("myAscriptionPlace");
		session.removeAttribute("myVisitPlace");
		session.removeAttribute("myState");
		session.removeAttribute("myBorrowerId");
		session.removeAttribute("myBorrowStartDate");
		session.removeAttribute("myBorrowEndDate");
		session.removeAttribute("myIntendingReturnStartDate");
		session.removeAttribute("myIntendingReturnEndDate");
		ITawRmTestcardManager mgr=(ITawRmTestcardManager)getBean("ItawRmTestcardManager");
		TawRmTestcard tawRmTestcard=mgr.getTawRmTestcard(id);
		request.setAttribute("tawRmTestcard", tawRmTestcard);
	}catch (Exception e) {
		e.printStackTrace();
	}
      return mapping.findForward("viewContent");
  }
}
