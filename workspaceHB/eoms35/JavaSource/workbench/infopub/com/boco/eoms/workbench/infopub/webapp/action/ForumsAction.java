package com.boco.eoms.workbench.infopub.webapp.action;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.workbench.infopub.mgr.IForumsManager;
import com.boco.eoms.workbench.infopub.model.Forums;
import com.boco.eoms.workbench.infopub.util.InfopubConstants;
import com.boco.eoms.workbench.infopub.util.InfopubUtil;
import com.boco.eoms.workbench.infopub.webapp.form.ForumsForm;

/**
 * 
 * <p>
 * Title:版块操作action
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 21, 2008 8:38:03 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public final class ForumsAction extends BaseAction {
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("search");
	}

	/**
	 * 删除版块，公做标记
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 判断是否拥有版块管理权限
		if (!hasPermission(
				InfopubConstants.WORKBENCH_INFOPUB_PERMISSION_FORUMS_MGR,
				request)) {
			return mapping.findForward("nopriv");
		}

		ActionMessages messages = new ActionMessages();
		ForumsForm forumsForm = (ForumsForm) form;

		// Exceptions are caught by ActionExceptionHandler
		IForumsManager mgr = (IForumsManager) getBean("IforumsManager");
		mgr.removeForums(forumsForm.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"forums.deleted"));

		// save messages in session, so they'll survive the redirect
		saveMessages(request.getSession(), messages);

		return mapping.findForward("success");
	}

	/**
	 * 显示主题列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 判断是否拥有版块管理权限
		if (!hasPermission(
				InfopubConstants.WORKBENCH_INFOPUB_PERMISSION_FORUMS_MGR,
				request)) {
			return mapping.findForward("nopriv");
		}
		IForumsManager mgr = (IForumsManager) getBean("IforumsManager");
		// 取所有主题列表
		List list = mgr.getForumss(null);
		request.setAttribute("forumsId", request.getParameter("id"));
		request.setAttribute("forums", list);
		return mapping.findForward("forumsList4Move");
	}

	/**
	 * 移动主题到另一主题
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward move(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 判断是否拥有版块管理权限
		if (!hasPermission(
				InfopubConstants.WORKBENCH_INFOPUB_PERMISSION_FORUMS_MGR,
				request)) {
			return mapping.findForward("nopriv");
		}
		IForumsManager mgr = (IForumsManager) getBean("IforumsManager");
		String forumsId = request.getParameter("forumsId");
		String toForumsId = request.getParameter("toForumsId");
		// 将主题移到另一主题
		mgr.forum2forum(forumsId, toForumsId);
		return mapping.findForward("success");
	}

	/**
	 * 修改版块或新建版块
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
		// 判断是否拥有版块管理权限
		if (!hasPermission(
				InfopubConstants.WORKBENCH_INFOPUB_PERMISSION_FORUMS_MGR,
				request)) {
			return mapping.findForward("nopriv");
		}
		ForumsForm forumsForm = (ForumsForm) form;

		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		if (forumsForm.getId() != null) {
			IForumsManager mgr = (IForumsManager) getBean("IforumsManager");
			Forums forums = mgr.getForums(forumsForm.getId());
			forumsForm = (ForumsForm) convert(forums);
			updateFormBean(mapping, request, forumsForm);
		}

		return mapping.findForward("edit");
	}

	/**
	 * 初始化添加子版块页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward initAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 判断是否拥有版块管理权限
		if (!hasPermission(
				InfopubConstants.WORKBENCH_INFOPUB_PERMISSION_FORUMS_MGR,
				request)) {
			return mapping.findForward("nopriv");
		}
		ForumsForm forumsForm = (ForumsForm) form;

		// 设置父版块id
		forumsForm.setParentId(forumsForm.getId());
		// 将id设置为空，即为添加，否则为修改
		forumsForm.setId(null);
		return mapping.findForward("edit");
	}

	/**
	 * 初始化修改版块页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward initEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ForumsForm forumsForm = (ForumsForm) form;

		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		if (forumsForm.getId() != null) {
			IForumsManager mgr = (IForumsManager) getBean("IforumsManager");
			Forums forums = mgr.getForums(forumsForm.getId());
			forumsForm = (ForumsForm) convert(forums);
		}

		return mapping.findForward("edit");
	}

	/**
	 * 保存新建后或修改后
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
		// 判断是否拥有版块管理权限
		if (!hasPermission(
				InfopubConstants.WORKBENCH_INFOPUB_PERMISSION_FORUMS_MGR,
				request)) {
			return mapping.findForward("nopriv");
		}
		// Extract attributes and parameters we will need
		ActionMessages messages = new ActionMessages();
		ForumsForm forumsForm = (ForumsForm) form;
		boolean isNew = ("".equals(forumsForm.getId()) || forumsForm.getId() == null);

		IForumsManager mgr = (IForumsManager) getBean("IforumsManager");

		Forums forums = (Forums) convert(forumsForm);

		// add success messages
		// 新建版块
		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"forums.added"));
			// 新建版块需要写新建时间，创建人
			// 创建版块时间
			forums.setCreateTime(new Date());
			// 创建人id
			forums.setCreaterId(this.getUserId(request));
			mgr.saveForums(forums);
			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return mapping.findForward("success");
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"forums.updated"));
			saveMessages(request, messages);
			// 若为修改版块，则不修改时间和创建人
			mgr.saveForums(forums);
			return mapping.findForward("success");
		}
	}

	/**
	 * 分页
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
				"forumsList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		IForumsManager mgr = (IForumsManager) getBean("IforumsManager");
		Map map = (Map) mgr.getForumss(pageIndex, pageSize);
		List list = (List) map.get("result");
		request.setAttribute(InfopubConstants.FORUMS_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}

	/**
	 * 获取信息发布版块树
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getNodes4Forum(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获取节点id
		String nodeId = request.getParameter("node");
		IForumsManager mgr = (IForumsManager) getBean("IforumsManager");
		// 取某版块对象
		List forums = mgr.getForumsByParentId(nodeId);

		// json根（树根节点）
		JSONArray root = new JSONArray();
		// 遍历子版块
		if (null != forums) {
			for (Iterator it = forums.iterator(); it.hasNext();) {
				Forums forum = (Forums) it.next();
				JSONObject item = new JSONObject();
				item.put("id", forum.getId());
				item.put("text", forum.getTitle());
				item.put(UIConstants.JSON_NODETYPE, "folder");
				// item.put("allowChild", true);
				// item.put("allowEdit", true);
				// item.put("allowDelete", true);
				item.put("leaf", InfopubUtil.isTrue(forum.getIsLeaf()));
				item.put("iconCls", "folder");
				item.put("qtip", forum.getDescription());
				// item.put("allowNewThread", true);
				// item.put("allowListThreads", true);
				// item.put("allowListUnreadThreads", true);
				item.put("allowNewForum", true);
				item.put("allowEditForum", true);
				item.put("allowDelForum", true);
				item.put("allowMoveForum", true);
				item.put("allowClick", true);

				root.put(item);
			}
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(root.toString());

	}

	/**
	 * 获取信息发布版块树
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getNodes4query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String chkType = StaticMethod.null2String(request
				.getParameter("checktype"),
				InfopubConstants.NODETYPE_INFOPUB_FORUMS);

		// 获取节点id
		String nodeId = request.getParameter("node");
		IForumsManager mgr = (IForumsManager) getBean("IforumsManager");
		// 取某版块对象
		List forums = mgr.getForumsByParentId(nodeId);

		// json根（树根节点）
		JSONArray root = new JSONArray();
		// 遍历子版块
		if (null != forums) {
			for (Iterator it = forums.iterator(); it.hasNext();) {
				Forums forum = (Forums) it.next();
				JSONObject item = new JSONObject();
				item.put("id", forum.getId());
				item.put("text", forum.getTitle());
				item.put(UIConstants.JSON_NODETYPE, "folder");
				// item.put("allowChild", true);
				// item.put("allowEdit", true);
				// item.put("allowDelete", true);
				item.put("leaf", InfopubUtil.isTrue(forum.getIsLeaf()));
				item.put("iconCls", "folder");
				item.put("qtip", forum.getDescription());
				// item.put("allowNewThread", true);
				// item.put("allowListThreads", true);
				// item.put("allowListUnreadThreads", true);
				
				item.put(UIConstants.JSON_NODETYPE, InfopubConstants.NODETYPE_INFOPUB_FORUMS);
				if (chkType.indexOf(InfopubConstants.NODETYPE_INFOPUB_FORUMS) > -1) {
					item.put("checked", false);
				}
				root.put(item);
			}
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(root.toString());

	}

	/**
	 * 初始化版块树
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 返回到树页面
		return mapping.findForward("tree");
	}

	/**
	 * 获取信息发布版块树
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getNodes4Thread(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获取节点id
		String nodeId = request.getParameter("node");
		IForumsManager mgr = (IForumsManager) getBean("IforumsManager");
		// 取某版块对象
		List forums = mgr.getForumsByParentId(nodeId);
		// FIXME 修改菜单在根节点时不显示
		// json根（树根节点）
		JSONArray root = new JSONArray();
		// 遍历子版块
		if (null != forums) {
			for (Iterator it = forums.iterator(); it.hasNext();) {
				Forums forum = (Forums) it.next();
				JSONObject item = new JSONObject();
				item.put("id", forum.getId());
				item.put("text", forum.getTitle());
				item.put(UIConstants.JSON_NODETYPE, "folder");
				item.put("allowChild", true);
				item.put("allowEdit", true);
				item.put("allowDelete", true);
				item.put("leaf", InfopubUtil.isTrue(forum.getIsLeaf()));
				item.put("iconCls", "folder");
				item.put("qtip", forum.getDescription());
				item.put("allowNewThread", true);
				item.put("allowListThreads", true);
				item.put("allowListUnreadThreads", true);
				item.put("allowClick", true);
				root.put(item);
			}
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(root.toString());

	}

}
