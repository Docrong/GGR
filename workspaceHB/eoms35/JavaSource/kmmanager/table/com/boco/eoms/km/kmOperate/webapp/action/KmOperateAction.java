package com.boco.eoms.km.kmOperate.webapp.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
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
import com.boco.eoms.km.kmAuditer.mgr.KmAuditerMgr;
import com.boco.eoms.km.kmAuditer.model.KmAuditer;
import com.boco.eoms.km.kmAuditer.webapp.form.KmAuditerForm;
import com.boco.eoms.km.kmOperate.mgr.KmOperateMgr;
import com.boco.eoms.km.kmOperate.model.KmOperate;
import com.boco.eoms.km.kmOperate.util.KmOperateConstants;
import com.boco.eoms.km.kmOperate.webapp.form.KmOperateForm;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;
import com.boco.eoms.commons.ui.util.UIConstants;

/**
 * <p>
 * Title:知识管理权限配置
 * </p>
 * <p>
 * Description:知识管理权限配置
 * </p>
 * <p>
 * Fri May 22 14:03:33 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() 戴志刚
 * @moudle.getVersion() 1.0
 * 
 */
public final class KmOperateAction extends BaseAction {
 
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
	 * 新增知识管理权限配置
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
		return mapping.findForward("edit");
	}
	
	/**
	 * 修改知识管理权限配置
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
		KmOperateMgr kmOperateMgr = (KmOperateMgr) getBean("kmOperateMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		if(id.equals("")){
			id = StaticMethod.nullObject2String(request.getAttribute("id"));
		}
		KmOperate kmOperate = kmOperateMgr.getKmOperate(id);
		KmOperateForm kmOperateForm = (KmOperateForm) convert(kmOperate);
		updateFormBean(mapping, request, kmOperateForm);
		return mapping.findForward("edit");
	}
	
	/**
	 * 配置知识管理权限
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward userTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	String nodeId = StaticMethod.null2String(request.getParameter("node"));
    	String nodeType = StaticMethod.null2String(request.getParameter("nodeType"));
		KmOperateMgr kmOperateMgr = (KmOperateMgr) getBean("kmOperateMgr");
		request.setAttribute("nodeId", nodeId);
		request.setAttribute("operType", nodeType);
//		String id = StaticMethod.null2String(request.getParameter("id"));
//		KmOperate kmOperate = kmOperateMgr.getKmOperate(nodeId,"file");
//		KmOperateForm kmOperateForm = (KmOperateForm) convert(kmOperate);
//		updateFormBean(mapping, request, kmOperateForm);
		return mapping.findForward("userTree");

	}
	
	/**
	 * 配置知识管理权限
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward operateConfig(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	String nodeId = StaticMethod.null2String(request.getParameter("nodeId"));
    	String nodeType = StaticMethod.null2String(request.getParameter("nodeType"));
    	String orgId = StaticMethod.null2String(request.getParameter("orgId"));
    	String orgType = StaticMethod.null2String(request.getParameter("orgType"));
		KmOperateMgr kmOperateMgr = (KmOperateMgr) getBean("kmOperateMgr");
		KmOperate kmOperate = kmOperateMgr.getKmOperate(nodeId,nodeType,orgId,orgType);
		KmOperateForm kmOperateForm = (KmOperateForm) convert(kmOperate);
		if(kmOperateForm.getId()==null){
			kmOperateForm.setNodeId(nodeId);
			kmOperateForm.setNodeType(nodeType);
			kmOperateForm.setOrgId(orgId);
			kmOperateForm.setOrgType(orgType);
		}
		updateFormBean(mapping, request, kmOperateForm);
		return mapping.findForward("edit");

	}
	/**
	 * 保存知识管理权限配置
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
		KmOperateMgr kmOperateMgr = (KmOperateMgr) getBean("kmOperateMgr");
		KmOperateForm kmOperateForm = (KmOperateForm) form;
		boolean isNew = (null == kmOperateForm.getId() || "".equals(kmOperateForm.getId()));
		KmOperate kmOperate = (KmOperate) convert(kmOperateForm);
		if (isNew) {
			kmOperateMgr.saveKmOperate(kmOperate);
		} else {
			kmOperateMgr.saveKmOperate(kmOperate);
		}
		request.setAttribute("id",kmOperate.getId());
		return edit(mapping, kmOperateForm, request, response);
	}
	
	/**
	 * 删除知识管理权限配置
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
		KmOperateMgr kmOperateMgr = (KmOperateMgr) getBean("kmOperateMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		kmOperateMgr.removeKmOperate(id);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 分页显示知识管理权限配置列表
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
				KmOperateConstants.KMOPERATE_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		KmOperateMgr kmOperateMgr = (KmOperateMgr) getBean("kmOperateMgr");
		Map map = (Map) kmOperateMgr.getKmOperates(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute(KmOperateConstants.KMOPERATE_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	/**
	 * 分页显示知识管理权限配置列表，支持Atom方式接入Portal
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 
	public ActionForward search4Atom(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			// --------------用于分页，得到当前页号-------------
			final Integer pageIndex = new Integer(request
					.getParameter("pageIndex"));
			final Integer pageSize = new Integer(request
					.getParameter("pageSize"));
			KmOperateMgr kmOperateMgr = (KmOperateMgr) getBean("kmOperateMgr");
			Map map = (Map) kmOperateMgr.getKmOperates(pageIndex, pageSize, "");
			List list = (List) map.get("result");
			KmOperate kmOperate = new KmOperate();
			
			//创建ATOM源
			Factory factory = Abdera.getNewFactory();
			Feed feed = factory.newFeed();
			
			// 分页
			for (int i = 0; i < list.size(); i++) {
				kmOperate = (KmOperate) list.get(i);
				
				// TODO 请按照下面的实例给entry赋值
				Entry entry = feed.insertEntry();
				entry.setTitle("<a href='" + request.getScheme() + "://"
						+ request.getServerName() + ":"
						+ request.getServerPort()
						+ request.getContextPath()
						+ "/kmOperate/kmOperates.do?method=edit&id="
						+ kmOperate.getId() + "' target='_blank'>"
						+ display name for list + "</a>");
				entry.setSummary(summary);
				entry.setContent(content);
				entry.setLanguage(language);
				entry.setText(text);
				entry.setRights(tights);
				
				// 以下三项需要传入Date类型或String类型（yyyy-MM-dd）的参数
				entry.setUpdated(new java.util.Date());
				entry.setPublished(new java.util.Date());
				entry.setEdited(new java.util.Date());
				
				// 为person的name属性赋值，entry.addAuthor可以随意赋值
				Person person = entry.addAuthor(userId);
				person.setName(userName);
			}
			
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
	*/
	
	/**
	 * 部门和用户树
	 * @template tpl-user-xtree-fromdept
	 */
	public ActionForward userFromDept(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
	
		String node = StaticMethod.null2String(request.getParameter("node"),
				StaticVariable.ProvinceID + "");
		String selfFlag = StaticMethod.null2String(request.getParameter("noself"),"");
		TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();
		TawSystemUserRoleBo userrolebo = TawSystemUserRoleBo.getInstance();
		String template = StaticMethod.null2String(request.getParameter("tpl"),
		"tpl-user-xtree-fromdept");
		ArrayList userlist = new ArrayList();
		ArrayList deptlist = new ArrayList();
		try{
			deptlist = (ArrayList) deptbo.getNextLevecDepts(node, "0");
			if (selfFlag.equals("true")) {//不包括自己的人员list
				TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
				userlist = (ArrayList) userrolebo.getUserBydeptidsNoSelf(node,sessionform.getUserid());
			} else {
				userlist = (ArrayList) userrolebo.getUserBydeptids(node);
			}
			
		} catch (Exception ex) {
			BocoLog.error(this, "生成部门用户树图时报错：" + ex);
		}
//		request.setAttribute("deptlist", deptlist);
//		request.setAttribute("userlist", userlist);

		JSONArray json = new JSONArray();
		if (deptlist.size() > 0){
	    	for (int i = 0; i < deptlist.size(); i++) {
				TawSystemDept subDept = (TawSystemDept) deptlist.get(i);			
				JSONObject jitem = new JSONObject();
				jitem.put(UIConstants.JSON_ID, subDept.getDeptId());
				jitem.put(UIConstants.JSON_TEXT, subDept.getDeptName());
				jitem.put(UIConstants.JSON_NODETYPE, UIConstants.NODETYPE_DEPT);
				jitem.put("iconCls", "dept");
				jitem.put("allowChild", true);		
				jitem.put("allowClick", true);
				//判断是否还有子节点
				List flaguser = userrolebo
							.getUserBydeptids(subDept.getDeptId());
				List flagdept = deptbo.getNextLevecDepts(subDept.getDeptId(),
							"0");
				if (flagdept == null || flagdept.size() <= 0) {
					if (flaguser == null || flaguser.size() <= 0) {
						jitem.put("leaf", 1);
					}
				} else {
					jitem.put("leaf", 0);
				}
				json.put(jitem);				
			}
		}
			
		if (userlist.size() > 0) {
			for (int j = 0; j < userlist.size(); j++) {
				TawSystemUser sysuser = (TawSystemUser) userlist.get(j);			
				JSONObject jitem = new JSONObject();
				jitem.put(UIConstants.JSON_ID, sysuser.getUserid());
				jitem.put(UIConstants.JSON_TEXT, sysuser.getUsername());
				jitem.put(UIConstants.JSON_NODETYPE, UIConstants.NODETYPE_USER);
				jitem.put("leaf", 1);
				jitem.put("iconCls", "user");
				jitem.put("mobile",sysuser.getMobile());
				jitem.put("allowChild", true);
				jitem.put("allowClick", true);
				json.put(jitem);
			}
		}
		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(json.toString());
		
		
		return null;
	}
}