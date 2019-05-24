package com.boco.eoms.sheet.acceptsheetrule.webapp.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Person;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.sheet.acceptsheetrule.mgr.AcceptSheetRuleMgr;
import com.boco.eoms.sheet.acceptsheetrule.model.AcceptSheetRule;
import com.boco.eoms.sheet.acceptsheetrule.util.AcceptSheetRuleConstants;
import com.boco.eoms.sheet.acceptsheetrule.webapp.form.AcceptSheetRuleForm;
import com.boco.eoms.sheet.nbproducts.model.NBProducts;
import com.boco.eoms.sheet.nbproducts.service.INBProductsManager;
import com.boco.eoms.sheet.nbproducts.webapp.form.NBProductsForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.ui.util.JSONUtil;

/**
 * <p>
 * Title:自动接单规则配置
 * </p>
 * <p>
 * Description:自动接单规则配置
 * </p>
 * <p>
 * Wed Apr 22 09:19:35 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() 史闯科
 * @moudle.getVersion() 3.5
 * 
 */
public final class AcceptSheetRuleAction extends BaseAction {
 
	/**
	 * 未指定方法时默认调用的方法
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
		return search(mapping, form, request, response);
	}
 	
 	/**
	 * 新增自动接单规则配置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("add");
	}
	
	/**
	 * 修改自动接单规则配置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AcceptSheetRuleMgr acceptSheetRuleMgr = (AcceptSheetRuleMgr) getBean("acceptSheetRuleMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		AcceptSheetRule acceptSheetRule = acceptSheetRuleMgr.getAcceptSheetRule(id);
		AcceptSheetRuleForm acceptSheetRuleForm = (AcceptSheetRuleForm) convert(acceptSheetRule);
		updateFormBean(mapping, request, acceptSheetRuleForm);
		return mapping.findForward("add");
	}

	/**
	 * 保存自动接单规则配置
	 * 
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
		String dealHuman = (String)request.getParameter("dealHuman");
		System.out.println("-----------"+dealHuman);
		AcceptSheetRuleMgr acceptSheetRuleMgr = (AcceptSheetRuleMgr) getBean("acceptSheetRuleMgr");
		AcceptSheetRuleForm acceptSheetRuleForm = (AcceptSheetRuleForm) form;
		boolean isNew = (null == acceptSheetRuleForm.getId() || "".equals(acceptSheetRuleForm.getId()));		
		AcceptSheetRule acceptSheetRule = (AcceptSheetRule) convert(acceptSheetRuleForm);
		acceptSheetRule.setDeleted(0);
		if (isNew) {
			acceptSheetRuleMgr.saveAcceptSheetRule(acceptSheetRule);
		} else {
			acceptSheetRuleMgr.saveAcceptSheetRule(acceptSheetRule);
		}
		return search(mapping, acceptSheetRuleForm, request, response);
	}
	
	/**
	 * 删除自动接单规则配置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward remove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AcceptSheetRuleMgr acceptSheetRuleMgr = (AcceptSheetRuleMgr) getBean("acceptSheetRuleMgr");
		AcceptSheetRuleForm acceptSheetRuleForm = (AcceptSheetRuleForm) form;
		AcceptSheetRule acceptSheetRule = (AcceptSheetRule) convert(acceptSheetRuleForm);
		acceptSheetRule.setDeleted(1);
		acceptSheetRuleMgr.saveAcceptSheetRule(acceptSheetRule);
		return search(mapping, acceptSheetRuleForm, request, response);
	}
	
	/**
	 * 分页显示自动接单规则配置列表
	 * 
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
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				AcceptSheetRuleConstants.ACCEPTSHEETRULE_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		AcceptSheetRuleMgr acceptSheetRuleMgr = (AcceptSheetRuleMgr) getBean("acceptSheetRuleMgr");
		Map map = (Map) acceptSheetRuleMgr.getAcceptSheetRules(pageIndex, pageSize, "");
		List acceptSheetRuleList = (List) map.get("result");
		request.setAttribute(AcceptSheetRuleConstants.ACCEPTSHEETRULE_LIST, acceptSheetRuleList);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	/**
	 * 分页显示自动接单规则配置列表，支持Atom方式接入Portal
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward search4Atom(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			// --------------用于分页，得到当前页号-------------
			final Integer pageIndex = new Integer(request
					.getParameter("pageIndex"));
			final Integer pageSize = new Integer(request
					.getParameter("pageSize"));
			AcceptSheetRuleMgr acceptSheetRuleMgr = (AcceptSheetRuleMgr) getBean("acceptSheetRuleMgr");
			Map map = (Map) acceptSheetRuleMgr.getAcceptSheetRules(pageIndex, pageSize, "");			
			//创建ATOM源
			Factory factory = Abdera.getNewFactory();
			Feed feed = factory.newFeed();
			
			// 每页显示条数
			feed.setText(map.get("total").toString());
		    OutputStream os = response.getOutputStream();
		    PrintStream ps = new PrintStream(os);
		    feed.getDocument().writeTo(ps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据过滤条件查询出所有处于所有符合条件子角色下的人员
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void searchUsers(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AcceptSheetRuleMgr acceptSheetRuleMgr = (AcceptSheetRuleMgr) getBean("acceptSheetRuleMgr");
		String deptId=StaticMethod.nullObject2String(request.getParameter("deptId"));
		String mainNetSortOne=StaticMethod.nullObject2String(request.getParameter("netSortOne"));
		String mainNetSortTwo=StaticMethod.nullObject2String(request.getParameter("netSortTwo"));
		String mainNetSortThree=StaticMethod.nullObject2String(request.getParameter("netSortThree"));
		String mainEquipmentFactory=StaticMethod.nullObject2String(request.getParameter("equipmentFactory"));
		HashMap dataMap = new HashMap();
		String roleId = StaticMethod.nullObject2String(request.getParameter("roleId"));
		dataMap.put("deptId", deptId);
		dataMap.put("mainNetSortOne", mainNetSortOne);
		dataMap.put("mainNetSortTwo", mainNetSortTwo);
		dataMap.put("mainNetSortThree", mainNetSortThree);
		dataMap.put("mainEquipmentFactory", mainEquipmentFactory);

		List userList = acceptSheetRuleMgr.getUsersByCondition(roleId, dataMap);
		
		JSONArray userArray = new JSONArray();
		for (int j = 0; j < userList.size(); j++) {
			TawSystemUser user = (TawSystemUser) userList.get(j);
			JSONObject useritem = new JSONObject();

			useritem.put("id", user.getUserid());
			useritem.put("text", user.getUsername());
			useritem.put("leaf", 1);
			useritem.put("nodeType", "user");
			useritem.put("iconCls", "user");

			userArray.put(useritem);
		}
		JSONUtil.print(response, userArray.toString());
	}
}