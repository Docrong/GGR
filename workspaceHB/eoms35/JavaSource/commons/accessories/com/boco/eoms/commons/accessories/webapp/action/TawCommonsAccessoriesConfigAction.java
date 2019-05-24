package com.boco.eoms.commons.accessories.webapp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.accessories.exception.AccessoriesConfigException;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessoriesConfig;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesConfigManager;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager;
import com.boco.eoms.commons.accessories.util.AccessoriesAttributes;
import com.boco.eoms.commons.accessories.util.AccessoriesMgrLocator;
import com.boco.eoms.commons.accessories.webapp.form.TawCommonsAccessoriesConfigForm;
import com.boco.eoms.commons.accessories.util.ZipDownload;

/**
 * Action class to handle CRUD on a TawCommonsAccessoriesConfig object
 * 
 * @struts.action name="tawCommonsAccessoriesConfigForm"
 *                path="/tawCommonsAccessoriesConfigs" scope="request"
 *                validate="false" parameter="method" input="mainMenu"
 * @struts.action name="tawCommonsAccessoriesConfigForm"
 *                path="/editTawCommonsAccessoriesConfig" scope="request"
 *                validate="false" parameter="method" input="list"
 * @struts.action name="tawCommonsAccessoriesConfigForm"
 *                path="/saveTawCommonsAccessoriesConfig" scope="request"
 *                validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/tawCommonsAccessoriesConfig/tawCommonsAccessoriesConfigForm.jsp"
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/tawCommonsAccessoriesConfig/tawCommonsAccessoriesConfigList.jsp"
 * @struts.action-forward name="search"
 *                        path="/tawCommonsAccessoriesConfigs.html"
 *                        redirect="true"
 */
public final class TawCommonsAccessoriesConfigAction extends BaseAction {
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("search");
	}

	/***************************************************************************
	 * 删除附件配置信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 秦敏
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}

		try {
			ActionMessages messages = new ActionMessages();
			TawCommonsAccessoriesConfigForm tawCommonsAccessoriesConfigForm = (TawCommonsAccessoriesConfigForm) form;

			// Exceptions are caught by ActionExceptionHandler
			ITawCommonsAccessoriesConfigManager mgr = (ITawCommonsAccessoriesConfigManager) getBean("ItawCommonsAccessoriesConfigManager");
			mgr.removeTawCommonsAccessoriesConfig(new Integer(
					tawCommonsAccessoriesConfigForm.getAppId()));

			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawCommonsAccessoriesConfig.deleted"));

			// save messages in session, so they'll survive the redirect
			saveMessages(request.getSession(), messages);
		} catch (AccessoriesConfigException e) {
			return mapping.findForward("failure");
		}
		return mapping.findForward("search");
	}

	/**
	 * 编辑附件配置信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 秦敏
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'edit' method");
		}
		String appId = StaticMethod.nullObject2String(request
				.getParameter("id"));
		TawCommonsAccessoriesConfigForm tawCommonsAccessoriesConfigForm = (TawCommonsAccessoriesConfigForm) form;
		ITawCommonsAccessoriesConfigManager mgr = (ITawCommonsAccessoriesConfigManager) getBean("ItawCommonsAccessoriesConfigManager");
		if (appId != null && !appId.equals("")) {
			TawCommonsAccessoriesConfig tawCommonsAccessoriesConfig = mgr
					.getTawCommonsAccessoriesConfig(appId);
			if (tawCommonsAccessoriesConfig != null) {
				tawCommonsAccessoriesConfigForm = (TawCommonsAccessoriesConfigForm) convert(tawCommonsAccessoriesConfig);
			}
			updateFormBean(mapping, request, tawCommonsAccessoriesConfigForm);
		}
		// 获取系统应用模块信息
		List applicationTag = mgr.getApplicationInfo();
		request.setAttribute("applicationList", applicationTag);
		return mapping.findForward("edit");
	}

	/**
	 * 保存附件配置信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 秦敏
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}

		try {
			// Extract attributes and parameters we will need
			ActionMessages messages = new ActionMessages();
			TawCommonsAccessoriesConfigForm tawCommonsAccessoriesConfigForm = (TawCommonsAccessoriesConfigForm) form;
			String allowFileType = "";
			String[] allowFileTypes = tawCommonsAccessoriesConfigForm
					.getAllowFileType();
			for (int i = 0; i < allowFileTypes.length; i++) {
				allowFileType = allowFileType + "," + allowFileTypes[i];
			}
			if (allowFileType.indexOf(",") == 0)
				allowFileType = allowFileType.substring(1);
			tawCommonsAccessoriesConfigForm.setAllowFileType(null);
			ITawCommonsAccessoriesConfigManager mgr = (ITawCommonsAccessoriesConfigManager) getBean("ItawCommonsAccessoriesConfigManager");
			TawCommonsAccessoriesConfig tawCommonsAccessoriesConfig = (TawCommonsAccessoriesConfig) convert(tawCommonsAccessoriesConfigForm);
			tawCommonsAccessoriesConfig.setAllowFileType(allowFileType);
			mgr.saveTawCommonsAccessoriesConfig(tawCommonsAccessoriesConfig);
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawCommonsAccessoriesConfig.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

		} catch (AccessoriesConfigException e) {
			return mapping.findForward("failure");
		}
		return mapping.findForward("search");

	}

	/**
	 * 查询附件配置信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 秦敏
	 */
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		try {
			ITawCommonsAccessoriesConfigManager mgr = (ITawCommonsAccessoriesConfigManager) getBean("ItawCommonsAccessoriesConfigManager");
			List configInfoList = mgr.getTawCommonsAccessoriesConfigs();
			request.setAttribute(Constants.TAWCOMMONSACCESSORIESCONFIG_LIST,
					configInfoList);
		} catch (AccessoriesConfigException e) {
			return mapping.findForward("failure");
		}
		return mapping.findForward("list");
	}

	/**
	 * 查询附件配置信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 秦敏
	 */
	public ActionForward download(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}

		try {
			String id = StaticMethod.nullObject2String(request
					.getParameter("id"));
			ITawCommonsAccessoriesManager mgr = (ITawCommonsAccessoriesManager) getBean("ItawCommonsAccessoriesManager");
			TawCommonsAccessories accessories = (TawCommonsAccessories) mgr
					.getTawCommonsAccessories(id);
			if (accessories != null) {
				String fileCnName = accessories.getAccessoriesCnName();
				String fileName = accessories.getAccessoriesName();
				// String path = accessories.getAccessoriesPath();
				String rootFilePath = AccessoriesMgrLocator
						.getTawCommonsAccessoriesManagerCOS().getFilePath(
								accessories.getAppCode());
				String path = rootFilePath + fileName;
				// 若文件系统中的文件不存在则给出提示
				File file = new File(path);
				if (!file.exists()) {
					request.setAttribute("noteInfo", "文件系统中的文件不存在，可能已经被删除！");
					String fileIdList = StaticMethod.nullObject2String(request
							.getParameter("filelist"));
					request.setAttribute("fileIdlist", fileIdList);
					return mapping.findForward("view");
				}
				else {
					request.setAttribute("noteInfo", "");
				}
				InputStream inStream = new FileInputStream(path);
				//
				// fileCnName = URLEncoder.encode(fileCnName, "UTF-8");
				response.reset();
				response.setContentType("application/x-msdownload");
				response.setCharacterEncoding("GB2312");

				fileCnName = new String(fileCnName.getBytes("gbk"), "iso8859-1");
				response.addHeader("Content-Disposition",
						"attachment;filename=" + fileCnName);
				// // 文件的存放路径
				/*
				 * javax.servlet.RequestDispatcher dispatcher = request
				 * .getRequestDispatcher(path); if (dispatcher != null) {
				 * dispatcher.forward(request, response); }
				 */
				// 循环取出流中的数据
				byte[] b = new byte[8192];
				int len;
				while ((len = inStream.read(b)) > 0)
					response.getOutputStream().write(b, 0, len);
				inStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 批量导出附件 生成一个zip文件 放置在一个临时目录下
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author
	 */
	public ActionForward downloadzip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}

		try {
			ITawCommonsAccessoriesManager mgr = (ITawCommonsAccessoriesManager) getBean("ItawCommonsAccessoriesManager");
			TawCommonsAccessories accessories = null;
			String idlist = StaticMethod.nullObject2String(request
					.getParameter("idlist"));
			String[] idlistarray = idlist.split(",");
			String url = "";
			for (int i = 1; i < idlistarray.length; i++) {
				String id = idlistarray[i];
				accessories = (TawCommonsAccessories) mgr
						.getTawCommonsAccessories(id);
				url = url
						+ "#"
						+ AccessoriesMgrLocator
								.getTawCommonsAccessoriesManagerCOS()
								.getFilePath(accessories.getAppCode())
						+ accessories.getAccessoriesName();
				}
			if (!url.equals("")) {
				url = url.substring(1, url.length());
				
				//String path =  StaticMethod.getWebPath()+"/accessories/uploadfile";
				String path=AccessoriesMgrLocator
				             .getAccessoriesAttributes().getUploadPath();
				System.out.println(url);
				String[] urlArray = url.split("#");
				ZipDownload zip = new ZipDownload();
				String zipurl = zip.ZipUtil(urlArray, path);
				String[] zipName = zipurl.split("/"); 
				String name = zipName[zipName.length - 1];
				if (accessories != null) {

					InputStream inStream = new FileInputStream(zipurl);// 文件的存放路径
					// 设置输出的格式
					response.reset();
					response
							.setContentType("application/x-msdownload;charset=GBK");
					response.setCharacterEncoding("UTF-8");

					response
							.setHeader("Content-Disposition",
									"attachment; filename="
											+ new String(
													name.getBytes("UTF-8"),
													"GBK"));

					byte[] b = new byte[128];
					int len;
					while ((len = inStream.read(b)) > 0)
						response.getOutputStream().write(b, 0, len);
					inStream.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 默认执行方法
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 秦敏
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
}
