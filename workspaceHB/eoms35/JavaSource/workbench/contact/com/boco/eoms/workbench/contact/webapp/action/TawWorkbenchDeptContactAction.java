package com.boco.eoms.workbench.contact.webapp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.message.util.MsgConstants;
import com.boco.eoms.workbench.contact.model.TawWorkbenchDeptContact;
import com.boco.eoms.workbench.contact.model.TawWorkbenchDeptContactGroup;
import com.boco.eoms.workbench.contact.sample.FMImportSample;
import com.boco.eoms.workbench.contact.service.ITawWorkbenchDeptContactGroupManager;
import com.boco.eoms.workbench.contact.service.ITawWorkbenchDeptContactManager;
import com.boco.eoms.workbench.contact.service.bo.TawWorkbenchContactBO;
import com.boco.eoms.workbench.contact.service.bo.TawWorkbenchDeptContactBO;
import com.boco.eoms.workbench.contact.util.ContactAttriubuteLocator;
import com.boco.eoms.workbench.contact.util.ImportExcel;
import com.boco.eoms.workbench.contact.webapp.form.TawWorkbenchDeptContactForm;

/**
 * <p>
 * Title:部门通讯�? * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 30, 2008 21:59:30 PM
 * </p>
 * 
 * @author 孙圣�? * @version 3.5.1
 * 
 */

public final class TawWorkbenchDeptContactAction extends BaseAction {

	TawWorkbenchDeptContactBO contactbo = TawWorkbenchDeptContactBO.getInstance();
	TawWorkbenchContactBO contactBo = TawWorkbenchContactBO.getInstance();

	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// return mapping.findForward("search");
		return null;
	}

	/**
	 * 首页
	 */
	public ActionForward treeMain(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("contactMain");
	}

	/**
	 * 根据父节点的id得到�?有子节点的JSON数据
	 */
	public String getNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String nodeId = request.getParameter("node");
		String deptId = sessionform.getDeptid();
		String userId = sessionform.getUserid();
		JSONArray json = null;
		if("-1".equals(nodeId)) {
			json = getDeptUserTree(request, nodeId, deptId, "");
		} else {
			json = getUserDeptTree(request, nodeId, userId, "");
		}	

		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(json.toString());

		return null;
	}

	/**
	 * 用于用户管理的部门用户树
	 * 
	 * @param deptid
	 * @return
	 */
	public JSONArray getUserDeptTree(HttpServletRequest request, String nodeId, String userId,
			String chkType) {

		JSONArray json = new JSONArray();
		try {
			ArrayList contactlist = new ArrayList();
			contactlist = (ArrayList) contactbo.getNextLevecContact(userId,
					nodeId, "0");
			TawWorkbenchDeptContact tawWorkbenchDeptContact = new TawWorkbenchDeptContact();
			if (contactlist.size() > 0) {
				for (int j = 0; j < contactlist.size(); j++) {
					tawWorkbenchDeptContact = (TawWorkbenchDeptContact) contactlist
							.get(j);

					JSONObject jitem = new JSONObject();
					jitem.put("id", tawWorkbenchDeptContact.getId());
					jitem.put("text", tawWorkbenchDeptContact.getContactName());
					jitem.put(UIConstants.JSON_NODETYPE, "contact");
					jitem.put("iconCls", "user");
					jitem.put("leaf", 1);
					//判断当前登录用户是否有权限（是否是管理员或者是当前用户建立的人员）
					if(hasPriv(request,tawWorkbenchDeptContact.getUserId())) {
						jitem.put("allowChild", false);
						jitem.put("allowEdit", false);
						jitem.put("allowChilds", false);
						jitem.put("allowEdits", true);
						jitem.put("allowLists", true);
						jitem.put("allowImport", true);
						jitem.put("allowImp", true);
						jitem.put("allowExp", true);
						jitem.put("allowDelete", true);
					} else {
						jitem.put("allowChild", false);
						jitem.put("allowEdit", false);
						jitem.put("allowChilds", false);
						jitem.put("allowEdits", false);
						jitem.put("allowLists", true);
						jitem.put("allowImport", false);
						jitem.put("allowImp", false);
						jitem.put("allowExp", false);
						jitem.put("allowDelete", false);
					}
					if ("contact".equalsIgnoreCase(chkType)) {
						jitem.put("checked", false);
					}

					jitem.put("qtip", "所属部�?"
							+ tawWorkbenchDeptContact.getDeptName()
							+ "<br \\/>联系人职�?"
							+ tawWorkbenchDeptContact.getPosition()
							+ "<br \\/>联系人电�?" + tawWorkbenchDeptContact.getTele()
							+ "<br \\/>地址:" + tawWorkbenchDeptContact.getAddress()
							+ "<br \\/>电子邮件:" + tawWorkbenchDeptContact.getEmail());
					jitem.put("qtipTitle", tawWorkbenchDeptContact.getDeptName());

					json.put(jitem);
				}
			}
		} catch (Exception ex) {
			BocoLog.error(this, "生成部门用户树图时报错：" + ex);
		}

		return json;
	}
	/**
	 * 用于用户管理的部门用户树
	 * 
	 * @param deptid
	 * @return
	 */
	public JSONArray getDeptUserTree(HttpServletRequest request, String nodeId, String deptId,
			String chkType) {

		JSONArray json = new JSONArray();
		try {
			ArrayList list = (ArrayList) contactbo.getNextLevecGroups(nodeId,
					deptId, "0");
			for (int i = 0; i < list.size(); i++) {
				TawWorkbenchDeptContactGroup group = (TawWorkbenchDeptContactGroup) list
						.get(i);
				String group_id = group.getGroupId();
				String group_name = group.getGroupName();
				JSONObject jitem = new JSONObject();
				jitem.put("id", group_id);
				jitem.put("text", group_name);
				jitem.put(UIConstants.JSON_NODETYPE, "group");
				jitem.put("iconCls", "file");

				jitem.put("leaf", 0);
				//判断当前登录用户是否有权限（是否是管理员或者是当前用户建立的分组）
				if(hasPriv(request,group.getUserId())) {
					jitem.put("allowChild", false);
					jitem.put("allowEdit", true);
					jitem.put("allowChilds", true);
					jitem.put("allowEdits", false);
					jitem.put("allowLists", false);
					jitem.put("allowImport", true);
					jitem.put("allowImp", true);
					jitem.put("allowExp", true);
					jitem.put("allowDelete", true);
				} else {
					jitem.put("allowChild", false);
					jitem.put("allowEdit", false);
					jitem.put("allowChilds", false);
					jitem.put("allowEdits", false);
					jitem.put("allowLists", false);
					jitem.put("allowImport", false);
					jitem.put("allowImp", false);
					jitem.put("allowExp", false);
					jitem.put("allowDelete", false);
				}
				
				if ("group".equalsIgnoreCase(chkType)) {
					jitem.put("checked", false);
				}

				json.put(jitem);

			}
		} catch (Exception ex) {
			BocoLog.error(this, "生成部门用户树图时报错：" + ex);
		}

		return json;
	}

	/**
	 * 保存
	 */
	public ActionForward xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawWorkbenchDeptContactForm tawWorkbenchDeptContactForm = (TawWorkbenchDeptContactForm) form;
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();
		String deptId = tawWorkbenchDeptContactForm.getDeptId();
		TawSystemDeptBo detpBo = TawSystemDeptBo.getInstance();
		String deptName = detpBo.getDeptnameBydeptid(deptId);
		ITawWorkbenchDeptContactManager mgr = (ITawWorkbenchDeptContactManager) getBean("ItawWorkbenchDeptContactManager");
		TawWorkbenchDeptContact tawWorkbenchDeptContact = (TawWorkbenchDeptContact) convert(tawWorkbenchDeptContactForm);
		tawWorkbenchDeptContact.setDeleted("0");
		tawWorkbenchDeptContact.setUserId(userId);
		tawWorkbenchDeptContact.setDeptName(deptName);

		mgr.saveTawWorkbenchDeptContact(tawWorkbenchDeptContact);
		return mapping.findForward("success");
	}

	/**
	 * 删除，根据页面穿过来的nodeid 判断是组还是人员�?	 */
	public ActionForward xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		String nodetype = (String) request.getParameter("nodeid");
		int i = nodetype.length();
		if (i == 32) {
			ITawWorkbenchDeptContactManager mgr = (ITawWorkbenchDeptContactManager) getBean("ItawWorkbenchDeptContactManager");			
			mgr.removeTawWorkbenchDeptContact(nodetype);
		} else {
			ITawWorkbenchDeptContactGroupManager mgr = (ITawWorkbenchDeptContactGroupManager) getBean("ItawWorkbenchDeptContactGroupManager");
			TawWorkbenchDeptContactGroup tawWorkbenchDeptContactGroup = mgr
					.getTawWorkbenchDeptContactGroupById(nodetype);			
			//mgr.removeTawWorkbenchDeptContactGroup(tawWorkbenchDeptContactGroup.getId());
			mgr.saveTawWorkbenchDeptContactGroup(tawWorkbenchDeptContactGroup);

		}
		return mapping.findForward("success");
	}

	/**
	 * ajax请求修改某节点的详细信息 zzj 2008/05/13
	 */
	public String xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawWorkbenchDeptContactForm tawWorkbenchDeptContactForm = (TawWorkbenchDeptContactForm) form;

		if (tawWorkbenchDeptContactForm.getId() != null) {
			ITawWorkbenchDeptContactManager mgr = (ITawWorkbenchDeptContactManager) getBean("ItawWorkbenchDeptContactManager");
			TawWorkbenchDeptContact tawWorkbenchDeptContact = (TawWorkbenchDeptContact) convert(tawWorkbenchDeptContactForm);

			mgr.saveTawWorkbenchDeptContact(tawWorkbenchDeptContact);
			// mgr.updateTawWorkbenchContact(tawWorkbenchContact);
		}

		return null;
	}

	/**
	 * 得到修改页面的请�?	 */
	public ActionForward xget(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		String _strId = request.getParameter("nodeid");
		ITawWorkbenchDeptContactManager mgr = (ITawWorkbenchDeptContactManager) getBean("ItawWorkbenchDeptContactManager");
		TawWorkbenchDeptContact tawWorkbenchDeptContact = mgr
				.getTawWorkbenchDeptContact(_strId);
		
		
		request.setAttribute("tawWorkbenchDeptContactForm", tawWorkbenchDeptContact);
		return mapping.findForward("edit");
	}

	/**
	 * 得到显示页面的请�?	 */
	public ActionForward xlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String _strId = request.getParameter("nodeid");
		ITawWorkbenchDeptContactManager mgr = (ITawWorkbenchDeptContactManager) getBean("ItawWorkbenchDeptContactManager");
		TawWorkbenchDeptContact tawWorkbenchDeptContact = mgr
				.getTawWorkbenchDeptContact(_strId);
		request.setAttribute("tawWorkbenchDeptContactForm", tawWorkbenchDeptContact);
		return mapping.findForward("list");
	}

	/**
	 * 得到新增页面的请�?	 */
	public ActionForward saveToPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String _strId = request.getParameter("nodeid");
		TawWorkbenchDeptContactForm tawWorkbenchDeptContactForm = (TawWorkbenchDeptContactForm) form;
		tawWorkbenchDeptContactForm.setGroupId(_strId);
		request
				.setAttribute("tawWorkbenchDeptContactForm",
						tawWorkbenchDeptContactForm);
		return mapping.findForward("saveToPage");
	}

	/**
	 * 导入。首先把选择的文件附入服务的某个地方。在根据路径导入到数据库
	 * 
	 * 
	 */
	public ActionForward importExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		try {
			TawWorkbenchDeptContactForm tawWorkbenchDeptContactForm = (TawWorkbenchDeptContactForm) form;
			TawWorkbenchDeptContact tawWorkbenchDeptContact = null;
			ITawWorkbenchDeptContactManager mgr = (ITawWorkbenchDeptContactManager) getBean("ItawWorkbenchDeptContactManager");
			// String path =
			// "com/boco/eoms/commons/file/sample/FMImportSample.xls";

			String timeTag = StaticMethod
					.getCurrentDateTime("yyyy_MM_dd_HHmmss");
			String sysTemPaht = request.getRealPath("/");
			String uploadPath = ContactAttriubuteLocator.getNetDiskAttributes()
					.getContactRootPath();// 取当前系统路�?			
			String filePath =sysTemPaht+ uploadPath + "/"+timeTag + ".xls";
			File tempFile = new File(sysTemPaht+uploadPath);
			if (!tempFile.exists()) {
				tempFile.mkdir();
			}

			FormFile file = tawWorkbenchDeptContactForm.getThisFile();

			try {
				InputStream stream = file.getInputStream(); // 把文件读�?				OutputStream outputStream = new FileOutputStream(filePath); // 建立一个上传文件的输出�?
				OutputStream outputStream = new FileOutputStream(filePath);
				int bytesRead = 0;
				byte[] buffer = new byte[8192];
				while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				outputStream.close();
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			FMImportSample fm = new FMImportSample();
			ImportExcel importExcel = new ImportExcel();
			List list = new ArrayList();
			list = contactbo.getDateFromExcel(filePath); // 取数�?			
			for (int i = 0; i < list.size(); i++) {
				fm = (FMImportSample) list.get(i);
				tawWorkbenchDeptContact = new TawWorkbenchDeptContact();
				tawWorkbenchDeptContact.setGroupId(tawWorkbenchDeptContactForm
						.getGroupId());
				TawSystemSessionForm sessionform = (TawSystemSessionForm) request
						.getSession().getAttribute("sessionform");
				tawWorkbenchDeptContact.setUserId(sessionform.getUserid());
				tawWorkbenchDeptContact.setAddress(fm.getAddress());
				tawWorkbenchDeptContact.setContactName(fm.getContactName());
				tawWorkbenchDeptContact.setDeptName(fm.getDeptName());
				tawWorkbenchDeptContact.setEmail(fm.getEmail());
				tawWorkbenchDeptContact.setPosition(fm.getPosition());
				tawWorkbenchDeptContact.setTele(fm.getTele());
				tawWorkbenchDeptContact.setDeleted("0");

				mgr.saveTawWorkbenchDeptContact(tawWorkbenchDeptContact); // 入库

			}
			return mapping.findForward("success");
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("false");
		}

	}

	/**
	 * 得到新增页面的请�?	 */
	public ActionForward importExcelToPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ITawWorkbenchDeptContactGroupManager mgr = (ITawWorkbenchDeptContactGroupManager) getBean("ItawWorkbenchDeptContactGroupManager");
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();
		ArrayList list = (ArrayList) mgr.getOwnerGroup("-1", userId,"0");
		TawWorkbenchDeptContactGroup tawWorkbenchDeptContactGroup = null;
		List groupList = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			tawWorkbenchDeptContactGroup = (TawWorkbenchDeptContactGroup) list.get(i);
			groupList.add(tawWorkbenchDeptContactGroup);
		}
		request.setAttribute("groupList", groupList);
		return mapping.findForward("importPage");
	}

	/**
	 * 得到新增页面的请�?	 */
	public ActionForward expExcelToPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ITawWorkbenchDeptContactGroupManager mgr = (ITawWorkbenchDeptContactGroupManager) getBean("ItawWorkbenchDeptContactGroupManager");
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();
		ArrayList list = (ArrayList) mgr.getOwnerGroup("-1", userId,"0");
		TawWorkbenchDeptContactGroup tawWorkbenchDeptContactGroup = null;
		List groupList = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			tawWorkbenchDeptContactGroup = (TawWorkbenchDeptContactGroup) list.get(i);
			groupList.add(tawWorkbenchDeptContactGroup);
		}
		request.setAttribute("groupList", groupList);
		return mapping.findForward("exportPage");
	}

	/**
	 * 导出
	 */
	public ActionForward exportExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawWorkbenchDeptContactForm tawWorkbenchDeptContactForm = (TawWorkbenchDeptContactForm) form;
		String group_id = tawWorkbenchDeptContactForm.getGroupId();

		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();

		ITawWorkbenchDeptContactManager mgr = (ITawWorkbenchDeptContactManager) getBean("ItawWorkbenchDeptContactManager");
		List list = mgr.getNextLevecContacts(userId, group_id, "0");
		String sysTemPaht = request.getRealPath("/");
		String path = contactbo.exportModelToExcel(list,sysTemPaht);
		request.setAttribute("path", path);

		File file = new File(path);
		// 读到流中
		InputStream inStream = new FileInputStream(file);// 文件的存放路�?		// 设置输出的格�?		response.reset();
		response.setContentType("application/x-msdownload;charset=GBK");
		response.setCharacterEncoding("UTF-8");
		String fileName = URLEncoder.encode(file.getName(), "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(fileName.getBytes("UTF-8"), "GBK"));

		// 循环取出流中的数�?		
		byte[] b = new byte[128];
		int len;
		while ((len = inStream.read(b)) > 0)
			response.getOutputStream().write(b, 0, len);
		inStream.close();
		return null;

	}
	public ActionForward impFromDeptTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ITawSystemUserManager uMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		ITawWorkbenchDeptContactManager mgr = (ITawWorkbenchDeptContactManager) getBean("ItawWorkbenchDeptContactManager");
		TawSystemUser user = null;
		String groupId = request.getParameter("nodeid");
		String usersId = request.getParameter("userId");
		List useridList = getDataFromJson(usersId);
//		JSONArray jsonDept = JSONArray.fromString(usersId);
		for (Iterator it = useridList.iterator(); it.hasNext();) {
			TawWorkbenchDeptContact contact = new TawWorkbenchDeptContact();
			String id = (String)it.next();
//			JSONObject org = (JSONObject) it.next();
//			String id = org.getString(UIConstants.JSON_ID);
			user = uMgr.getUserByuserid(id);
			contact.setAddress(user.getFamilyaddress());
			contact.setContactName(user.getUsername());
			contact.setDeleted(Constants.NOT_DELETED_FLAG);
			contact.setDeptId(user.getDeptid());
			contact.setDeptName(user.getDeptname());
			contact.setEmail(user.getEmail());
			contact.setGroupId(groupId);
			contact.setTele(user.getMobile());
			contact.setUserId(this.getUser(request).getUserid());
			mgr.saveTawWorkbenchDeptContact(contact);
		}
		return mapping.findForward("success");
	}
	public static List getDataFromJson(String json) {
		ITawSystemUserManager uMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		JSONArray jsonDept = JSONArray.fromString(json);
		List userList = new ArrayList();
		for (Iterator it = jsonDept.iterator(); it.hasNext();) {
			JSONObject org = (JSONObject) it.next();
			// 部门id
			String id = org.getString(UIConstants.JSON_ID);
			// 部门名称
//			String name = org.getString(UIConstants.JSON_NAME);
			// 节点类型
			String nodeType = org.getString(UIConstants.JSON_NODETYPE);
			// 获取部门id
			// 若为部门则写入权限，限制部门范围
			if (MsgConstants.USER.equals(nodeType)) {
				userList.add(id);
			} else if(MsgConstants.DEPT.equals(nodeType)) {
				userList.addAll(uMgr.getUserIdsBydeptid(id));
			}
		}
		return userList;
	}
	/**
	 * 判断用户是否有权限（如果是管理员或者该业务是当前用户建立的就有权限�?	 * 目前判断的管理员不是超级管理员，是新增用户的时候是否选择了“是否管理员”的项，
	 * 如果要求必须是超级管理员才能修改要修改this.getUser(request).isAdmin()方法，用“admin”和当前用户做判�?	 * 将this.getUser(request).isAdmin()修改�?admin".equals(this.getUser(request).getUserid())
	 * @param request 
	 * @param userId 业务中用�?	 * @return true：有权限  false：无权限
	 */
	public boolean hasPriv(HttpServletRequest request, String userId) {
		boolean isHave = false;
		if(this.getUser(request).isAdmin() || (this.getUser(request).getUserid()).equals(userId)) {
			isHave = true;
		}
		return isHave;
	}
}
