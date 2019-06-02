package com.boco.eoms.workbench.contact.webapp.action;

import java.io.*;
import java.net.URLEncoder;

import java.util.ArrayList;
import java.util.Map;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.workbench.contact.model.TawWorkbenchContact;
import com.boco.eoms.workbench.contact.model.TawWorkbenchContactGroup;
import com.boco.eoms.workbench.contact.service.ITawWorkbenchContactGroupManager;
import com.boco.eoms.workbench.contact.service.ITawWorkbenchContactManager;
import com.boco.eoms.workbench.contact.service.bo.TawWorkbenchContactBO;
import com.boco.eoms.workbench.contact.sample.*;
import com.boco.eoms.workbench.contact.util.ContactAttriubuteLocator;
import com.boco.eoms.workbench.contact.util.ImportExcel;
import com.boco.eoms.workbench.contact.webapp.form.TawWorkbenchContactForm;

/**
 * <p>
 * Title:个人通讯录
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 22, 2008 15:59:30 AM
 * </p>
 * 
 * @author 龚玉峰
 * @version 3.5.1
 * 
 */

public final class TawWorkbenchContactAction extends BaseAction {

	TawWorkbenchContactBO contactbo = TawWorkbenchContactBO.getInstance();

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
		String usereId = sessionform.getUserid();
		JSONArray json = getUserDeptTree(nodeId, usereId, "");

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
	public JSONArray getUserDeptTree(String nodeId, String userId,
			String chkType) {

		JSONArray json = new JSONArray();
		try {
			ArrayList list = (ArrayList) contactbo.getNextLevecGroups(nodeId,
					userId, "0");
			for (int i = 0; i < list.size(); i++) {
				TawWorkbenchContactGroup group = (TawWorkbenchContactGroup) list
						.get(i);
				String group_id = group.getGroupId();
				String group_name = group.getGroupName();
				JSONObject jitem = new JSONObject();
				jitem.put("id", group_id);
				jitem.put("text", group_name);
				jitem.put(UIConstants.JSON_NODETYPE, "group");
				jitem.put("iconCls", "file");

				jitem.put("leaf", 0);

				jitem.put("allowChild", false);
				jitem.put("allowEdit", true);
				jitem.put("allowChilds", true);
				jitem.put("allowEdits", false);
				jitem.put("allowLists", false);
				jitem.put("allowImp", true);
				jitem.put("allowExp", true);
				jitem.put("allowDelete", true);
				if ("group".equalsIgnoreCase(chkType)) {
					jitem.put("checked", false);
				}

				json.put(jitem);

			}
			ArrayList contactlist = new ArrayList();
			contactlist = (ArrayList) contactbo.getNextLevecContact(userId,
					nodeId, "0");
			TawWorkbenchContact tawWorkbenchContact = new TawWorkbenchContact();
			if (contactlist.size() > 0) {
				for (int j = 0; j < contactlist.size(); j++) {
					tawWorkbenchContact = (TawWorkbenchContact) contactlist
							.get(j);

					JSONObject jitem = new JSONObject();
					jitem.put("id", tawWorkbenchContact.getId());
					jitem.put("text", tawWorkbenchContact.getContactName());
					jitem.put(UIConstants.JSON_NODETYPE, "contact");
					jitem.put("iconCls", "user");
					jitem.put("leaf", 1);
					jitem.put("allowChild", false);
					jitem.put("allowEdit", false);
					jitem.put("allowChilds", false);
					jitem.put("allowEdits", true);
					jitem.put("allowLists", true);
					jitem.put("allowImp", true);
					jitem.put("allowExp", true);
					jitem.put("allowDelete", true);
					if ("contact".equalsIgnoreCase(chkType)) {
						jitem.put("checked", false);
					}

					jitem.put("qtip", "所属部门:"
							+ tawWorkbenchContact.getDeptName()
							+ "<br \\/>联系人职位:"
							+ tawWorkbenchContact.getPosition()
							+ "<br \\/>联系人电话:" + tawWorkbenchContact.getTele()
							+ "<br \\/>地址:" + tawWorkbenchContact.getAddress()
							+ "<br \\/>电子邮件:" + tawWorkbenchContact.getEmail());
					jitem.put("qtipTitle", tawWorkbenchContact.getDeptName());

					json.put(jitem);
				}
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

		TawWorkbenchContactForm tawWorkbenchContactForm = (TawWorkbenchContactForm) form;
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String result="falsePage";
		String userId = sessionform.getUserid();
		String deptId = tawWorkbenchContactForm.getDeptId();
		TawSystemDeptBo detpBo = TawSystemDeptBo.getInstance();
		TawSystemDept tawSystemDept = new TawSystemDept();
		tawSystemDept = detpBo.getDeptinfobydeptid(deptId, "0");
		String deptName = tawSystemDept.getDeptName();
		ITawWorkbenchContactManager mgr = (ITawWorkbenchContactManager) getBean("ItawWorkbenchContactManager");
		TawWorkbenchContact tawWorkbenchContact = (TawWorkbenchContact) convert(tawWorkbenchContactForm);
		tawWorkbenchContact.setDeleted("0");
		tawWorkbenchContact.setUserId(userId);
		tawWorkbenchContact.setDeptName(deptName);
		boolean bool=mgr.getContactName(tawWorkbenchContact.getContactName(),userId);
//	     if(mgr.getContactName(tawWorkbenchContact.getContactName())){
//	        	
//	        	mgr.saveTawWorkbenchContact(tawWorkbenchContact);
//	        	return mapping.findForward("success");
//	        }
	 	if(!tawWorkbenchContactForm.getContactName().equals(tawWorkbenchContactForm.getOldContactName())){
//    		if(tawWorkbenchContactForm.getId()!=null && !"".equals(tawWorkbenchContactForm.getId())){
    			if(bool){
    				mgr.saveTawWorkbenchContact(tawWorkbenchContact);
    				result="success";
    			}
    		
	 	
//    		}
        	
    	}
    	else if(tawWorkbenchContactForm.getContactName().equals(tawWorkbenchContactForm.getOldContactName())&& tawWorkbenchContact.getId()!=null&&!"".equals(tawWorkbenchContact.getId())){
    		mgr.saveTawWorkbenchContact(tawWorkbenchContact);
    		result="success";
    	}
//    	else{
//    	request.setAttribute("falseMessage", "联系人姓名已存在!请重新填写");
//    	result="falsePage";
//    	
//    	}
	 	if(result=="falsePage"){
	 		request.setAttribute("falseMessage", "联系人姓名已存在!请重新填写");	
	 	}
	 	
	 	return mapping.findForward(result); 
   
     	
	}

	/**
	 * 删除，根据页面穿过来的nodeid 判断是组还是人员，
	 */
	public ActionForward xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String nodetype = (String) request.getParameter("nodeid");
		int i = nodetype.length();
		if (i == 32) {
			ITawWorkbenchContactManager mgr = (ITawWorkbenchContactManager) getBean("ItawWorkbenchContactManager");
			mgr.removeTawWorkbenchContact(nodetype);
		} else {
			ITawWorkbenchContactGroupManager mgr = (ITawWorkbenchContactGroupManager) getBean("ItawWorkbenchContactGroupManager");
			TawWorkbenchContactGroup tawWorkbenchContactGroup = mgr
					.getTawWorkbenchContactGroupById(nodetype);
			//mgr
					//.removeTawWorkbenchContactGroup(tawWorkbenchContactGroup
							//.getId());
			tawWorkbenchContactGroup.setDeleted("1");
			mgr.saveTawWorkbenchContactGroup(tawWorkbenchContactGroup);

		}
		return mapping.findForward("success");
	}
  
	/**
	 * ajax请求修改某节点的详细信息 zzj 2008/05/13
	 */
	public String xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawWorkbenchContactForm tawWorkbenchContactForm = (TawWorkbenchContactForm) form;

		if (tawWorkbenchContactForm.getId() != null) {
			ITawWorkbenchContactManager mgr = (ITawWorkbenchContactManager) getBean("ItawWorkbenchContactManager");
			TawWorkbenchContact tawWorkbenchContact = (TawWorkbenchContact) convert(tawWorkbenchContactForm);

			mgr.saveTawWorkbenchContact(tawWorkbenchContact);
			// mgr.updateTawWorkbenchContact(tawWorkbenchContact);
		}

		return null;
	}

	/**
	 * 得到修改页面的请求
	 */
	public ActionForward xget(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();
		ArrayList list = (ArrayList) contactbo.getNextLevecGroups("-1", userId,
				"0");

		request.setAttribute("groupList", list);

		String _strId = request.getParameter("nodeid");
		ITawWorkbenchContactManager mgr = (ITawWorkbenchContactManager) getBean("ItawWorkbenchContactManager");
		TawWorkbenchContact tawWorkbenchContact = mgr
				.getTawWorkbenchContact(_strId);
		
		request.setAttribute("tawWorkbenchContactForm", tawWorkbenchContact);
		return mapping.findForward("edit");
	}

	/**
	 * 得到显示页面的请求
	 */
	public ActionForward xlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String _strId = request.getParameter("nodeid");
		ITawWorkbenchContactManager mgr = (ITawWorkbenchContactManager) getBean("ItawWorkbenchContactManager");
		TawWorkbenchContact tawWorkbenchContact = mgr
				.getTawWorkbenchContact(_strId);
		request.setAttribute("tawWorkbenchContactForm", tawWorkbenchContact);
		return mapping.findForward("list");
	}

	/**
	 * 得到新增页面的请求
	 */
	public ActionForward saveToPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String _strId = request.getParameter("nodeid");
		TawWorkbenchContactForm tawWorkbenchContactForm = (TawWorkbenchContactForm) form;
		tawWorkbenchContactForm.setGroupId(_strId);
		request
				.setAttribute("tawWorkbenchContactForm",
						tawWorkbenchContactForm);
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
		String exceptionStr = "";

		try {
			TawWorkbenchContactForm tawWorkbenchContactForm = (TawWorkbenchContactForm) form;
			TawWorkbenchContact tawWorkbenchContact = null;
			ITawWorkbenchContactManager mgr = (ITawWorkbenchContactManager) getBean("ItawWorkbenchContactManager");
			// String path =
			// "com/boco/eoms/commons/file/sample/FMImportSample.xls";

			String timeTag = StaticMethod
					.getCurrentDateTime("yyyy_MM_dd_HHmmss");
			String sysTemPaht = request.getRealPath("/");
			String uploadPath = ContactAttriubuteLocator.getNetDiskAttributes()
					.getContactRootPath();// 取当前系统路径
			String filePath = sysTemPaht + uploadPath + "/" + timeTag + ".xls";
			File tempFile = new File(sysTemPaht + uploadPath);
			if (!tempFile.exists()) {
				tempFile.mkdir();
			}

			FormFile file = tawWorkbenchContactForm.getThisFile();

			try {
				InputStream stream = file.getInputStream(); // 把文件读入
				OutputStream outputStream = new FileOutputStream(filePath); // 建立一个上传文件的输出流

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

			// List list = new ArrayList();
			// list = contactbo.getDateFromExcel(filePath); // 取数据

			List list = new ArrayList();
			FMImportSample fmimportSample = null;

			// 以下语句读取生成的Excel文件内容
			FileInputStream fIn = new FileInputStream(filePath);
			HSSFWorkbook readWorkBook = new HSSFWorkbook(fIn);
			// HSSFSheet readSheet = readWorkBook.getSheet("firstSheet");
			HSSFSheet readSheet = readWorkBook.getSheetAt(0);
			// HSSFRow readRow = readSheet.getRow(0);

			int maxrow = readSheet.getPhysicalNumberOfRows();
			int col = readSheet.getDefaultColumnWidth();
	 
			for (int i = 1; i < maxrow; i++) {
				HSSFRow readRow = readSheet.getRow(i);
				fmimportSample = new FMImportSample();
				for (int j = 0; j < col - 1; j++) {
					exceptionStr="";
					HSSFCell readCell = readRow.getCell((short) j);
					int a = i+1;  
					int b =j +1;
					if (j == 0) {
						String str = "";
						exceptionStr = "你上传的列表第"+a+"行第"+b+"列出现问题。请检查！";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str = "";
							request.setAttribute("falseMessage",exceptionStr);
							
							return mapping.findForward("falsePage");
						} else {
							str = readCell.toString();
						}
						fmimportSample.setContactName(str);
					}
					if (j == 1) {
						String str = "";
						exceptionStr = "你上传的列表第"+a+"行第"+b+"列出现问题。请检查！";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str = "";
							request.setAttribute("falseMessage",exceptionStr);
							return mapping.findForward("falsePage");
						} else {
							str = readCell.toString();
						}
						fmimportSample.setDeptName(str);
					}
					if (j == 2) {
						String str = "";
						exceptionStr = "你上传的列表第"+a+"行第"+b+"列出现问题。请检查！";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str = "";
							request.setAttribute("falseMessage",exceptionStr);
							return mapping.findForward("falsePage");
						} else {
							str = readCell.toString();
						}
						fmimportSample.setPosition(str);
					}
					if (j == 3) {
						String str = "";
						exceptionStr = "你上传的列表第"+a+"行第"+b+"列出现问题。请检查！";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str = "";
							request.setAttribute("falseMessage",exceptionStr);
							return mapping.findForward("falsePage");
						} else {
							str = readCell.toString();
						}
						fmimportSample.setTele(str);
					}
					if (j == 4) {
						String str = ""; 
						exceptionStr = "你上传的列表第"+a+"行第"+b+"列出现问题。请检查！";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str = "";
							request.setAttribute("falseMessage",exceptionStr);
							return mapping.findForward("falsePage");
						} else {
							str = readCell.toString();
						}
						fmimportSample.setAddress(str);
					}
					if (j == 5) {
						String str = "";
						exceptionStr = "你上传的列表第"+a+"行第"+b+"列出现问题。请检查！";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK ||readCell.toString().indexOf("@")<0) {
							str = "";
							request.setAttribute("falseMessage",exceptionStr);
							return mapping.findForward("falsePage");
						} else {
							str = readCell.toString();
						}
						fmimportSample.setEmail(str);
					}
					if (j == 6) {		 
						 
						exceptionStr = "你上传的模板错误，大于"+j+"列。请检查！";
						if (readRow.getCell((short) j)!=null) {
							request.setAttribute("falseMessage",exceptionStr);
							return mapping.findForward("falsePage");
						}
					}

				}
				list.add(fmimportSample);

			}

			if (list.size() == 0) {
				request.setAttribute("falseMessage", "你上传的列表为空或者列表格式不对。请检查！");
				return mapping.findForward("falsePage");
			}
			for (int i = 0; i < list.size(); i++) {
				fm = (FMImportSample) list.get(i);
				tawWorkbenchContact = new TawWorkbenchContact();
				tawWorkbenchContact.setGroupId(tawWorkbenchContactForm
						.getGroupId());
				TawSystemSessionForm sessionform = (TawSystemSessionForm) request
						.getSession().getAttribute("sessionform");
				tawWorkbenchContact.setUserId(sessionform.getUserid());
				tawWorkbenchContact.setAddress(fm.getAddress());
				tawWorkbenchContact.setContactName(fm.getContactName());
				
				tawWorkbenchContact.setDeptName(fm.getDeptName());
				tawWorkbenchContact.setEmail(fm.getEmail());
				tawWorkbenchContact.setPosition(fm.getPosition());
				tawWorkbenchContact.setTele(fm.getTele());
				tawWorkbenchContact.setDeleted("0");
				boolean bool=mgr.getContactName(tawWorkbenchContact.getContactName(),tawWorkbenchContact.getUserId());
				if(bool){
					mgr.saveTawWorkbenchContact(tawWorkbenchContact); // 入库
				}else{
					request.setAttribute("falseMessage", "导入的联系人姓名重名，请仔细查看");
					return mapping.findForward("falsePage");
				}
				

			}
			return mapping.findForward("success");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("falseMessage", exceptionStr);
			return mapping.findForward("falsePage");
		}

	}

	/**
	 * 得到新增页面的请求
	 */
	public ActionForward importExcelToPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();
		ArrayList list = (ArrayList) contactbo.getNextLevecGroups("-1", userId,
				"0");
		TawWorkbenchContactGroup tawWorkbenchContactGroup = null;
		List groupList = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			tawWorkbenchContactGroup = (TawWorkbenchContactGroup) list.get(i);
			groupList.add(tawWorkbenchContactGroup);
		}
		request.setAttribute("groupList", groupList);
		return mapping.findForward("importPage");
	}

	/**
	 * 得到新增页面的请求
	 */
	public ActionForward expExcelToPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();
		ArrayList list = (ArrayList) contactbo.getNextLevecGroups("-1", userId,
				"0");
		TawWorkbenchContactGroup tawWorkbenchContactGroup = null;
		List groupList = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			tawWorkbenchContactGroup = (TawWorkbenchContactGroup) list.get(i);
			groupList.add(tawWorkbenchContactGroup);
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

		TawWorkbenchContactForm tawWorkbenchContactForm = (TawWorkbenchContactForm) form;
		String group_id = tawWorkbenchContactForm.getGroupId();

		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();

		ITawWorkbenchContactManager mgr = (ITawWorkbenchContactManager) getBean("ItawWorkbenchContactManager");
		List list = mgr.getNextLevecContacts(userId, group_id, "0");
		String sysTemPaht = request.getRealPath("/");
		String path = contactbo.exportModelToExcel(list, sysTemPaht);
		request.setAttribute("path", path);

		File file = new File(path);
		// 读到流中
		InputStream inStream = new FileInputStream(file);// 文件的存放路径
		// 设置输出的格式
		response.reset();
		response.setContentType("application/x-msdownload;charset=GBK");
		response.setCharacterEncoding("UTF-8");
		String fileName = URLEncoder.encode(file.getName(), "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(fileName.getBytes("UTF-8"), "GBK"));

		// 循环取出流中的数据
		byte[] b = new byte[128];
		int len;
		while ((len = inStream.read(b)) > 0)
			response.getOutputStream().write(b, 0, len);
		inStream.close();
		return mapping.findForward("exportExcelSuccess");

	}
	
	/**
	 * 分页显示信息列表，支持Atom方式接入Portal
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchAtom(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			// --------------用于分页，得到当前页号-------------
			final Integer pageIndex = new Integer(request
					.getParameter("pageIndex"));
			final Integer pageSize = new Integer(request
					.getParameter("pageSize"));
			String atomName = request.getParameter("atomName");
			ITawWorkbenchContactManager mgr = (ITawWorkbenchContactManager) getBean("ItawWorkbenchContactManager");
			String wherestr = " where 1=1";
			if(!"".equals(atomName)&&atomName!=null){
			 wherestr+= " and contactName like '%"+atomName+"%'";
			}
			Map map = (Map) mgr.getTawWorkbenchContacts(pageIndex, pageSize, wherestr);
			List list = (List) map.get("result");
			TawWorkbenchContact tawWorkbenchContact = null;
			
			//创建ATOM源
			Factory factory = Abdera.getNewFactory();
			Feed feed = factory.newFeed();
			
			// 分页
			for (int i = 0; i < list.size(); i++) {
				tawWorkbenchContact = (TawWorkbenchContact) list.get(i);
				
				// TODO 请按照下面的实例给entry赋值
				Entry entry = feed.insertEntry();
				entry.setTitle(tawWorkbenchContact.getContactName());
				entry.setSummary(tawWorkbenchContact.getTele());
				entry.setContent(request.getScheme() + "://" + request.getLocalAddr() + ":" + request.getServerPort() + request.getContextPath() + "/workbench/contact/tawWorkbenchContacts.do?method=xlist&nodeid="+tawWorkbenchContact.getId());
				// 以下三项需要传入Date类型或String类型（yyyy-MM-dd）的参数
			/*	entry.setUpdated(new java.util.Date());
				entry.setPublished(new java.util.Date());
				entry.setEdited(new java.util.Date());*/
				
				// 为person的name属性赋值，entry.addAuthor可以随意赋值
				/*Person person = entry.addAuthor(userId);
				person.setName(userName);*/
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
}
