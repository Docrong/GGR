package com.boco.eoms.sheet.mofficedata.webapp.action;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.accessories.util.AccessoriesMgrLocator;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataMain;
import com.boco.eoms.sheet.mofficedata.service.IMofficeDataMainManager;
import com.boco.eoms.sheet.mofficedata.service.IMofficeDataProMatchManager;
import com.boco.eoms.sheet.mofficedata.util.MofficeDataInService;
import com.boco.eoms.sheet.mofficedata.util.MofficeUtil;

/**
 * <p>
 * Title:局数据工单流程
 * </p>
 * <p>
 * Description:局数据工单流程
 * </p>
 * <p>
 * Tue Mar 22 09:31:29 CST 2016
 * </p>
 * 
 * @author weichao
 * @version 3.5
 * 
 */

public class MofficeDataAction extends SheetAction {

	
	/**
	 * downfile
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward downfile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sheetid = StaticMethod.nullObject2String(request.getParameter("sheetid"));
		String mainStyle = StaticMethod.nullObject2String(request.getParameter("mainStyle"));
		String ftpurl=null;
		IMofficeDataMainManager mainMgr = (IMofficeDataMainManager) ApplicationContextHolder.getInstance().getBean(
		"iMofficeDataMainManager");
		MofficeDataMain baseMain = (MofficeDataMain) mainMgr.getMainBySheetId(sheetid);
		if(mainStyle.equals("mainStyle6"))
		{
			ftpurl=baseMain.getMainStyle6();
		}
		else if(mainStyle.equals("mainStyle7"))
		{
			ftpurl=baseMain.getMainStyle7();
		}
		else if(mainStyle.equals("mainStyle8"))
		{
			ftpurl=baseMain.getMainStyle8();
		}
		else if(mainStyle.equals("mainStyle9"))
		{
			ftpurl=baseMain.getMainStyle9();
		}
		else if(mainStyle.equals("mainStyle10"))
		{
			ftpurl=baseMain.getMainStyle10();
		}
		else if(mainStyle.equals("mainStyle1"))
		{
			ftpurl=java.net.URLDecoder.decode(StaticMethod.nullObject2String(request.getParameter("url")),"utf-8");
		}
		if(ftpurl!=null)
		{
			String nameurl[]=ftpurl.split("/");
			String filename;
			filename=nameurl[nameurl.length-1];
			String path = ftpurl;
			String ftpserver = "";
			String userLogin = "";
			String pwdLogin = "";
			String rootFilePath = AccessoriesMgrLocator.getAccessoriesAttributes()
			.getUploadPath();
			for (int i = 0; i < 3; i++)
				path = path.substring(path.indexOf("/") + 1);

			path = "/" + path;
			System.out.println("ftpurl=" + ftpurl + "  filename=" + filename + "  path=" + path);
			FTPClient fc = new FTPClient();
			ftpserver = StaticMethod.nullObject2String(XmlManage.getFile("/config/accessoriesServer.xml").getProperty("ftp.ip"));
			userLogin = StaticMethod.nullObject2String(XmlManage.getFile("/config/accessoriesServer.xml").getProperty("ftp.username"));
			pwdLogin = StaticMethod.nullObject2String(XmlManage.getFile("/config/accessoriesServer.xml").getProperty("ftp.password"));
			fc.connect(ftpserver);
			fc.login(userLogin, pwdLogin);
			fc.setBufferSize(1024*10);
			fc.setFileType(fc.BINARY_FILE_TYPE);
			InputStream inStream=fc.retrieveFileStream(new String(path.getBytes("gbk"),"iso8859-1"));
			//URL u = new URL(ftpurl);	
			//InputStream inStream = u.openStream();
			//
			// fileCnName = URLEncoder.encode(fileCnName, "UTF-8");
//			File newfile = new File(path);
//			if (!newfile.exists()) {
//				File oldfile = new File(new String(path.getBytes("gbk"),"utf-8"));
//				oldfile.renameTo(newfile);
//				if(!oldfile.exists())
//				{
//					request.setAttribute("noteInfo", "文件系统中的文件不存在，可能已经被删除！");
//					return mapping.findForward("view");
//				}
//			}
//			else {
//				request.setAttribute("noteInfo", "");
//			}
//			InputStream inStream=new FileInputStream(path);
			response.reset();
			response.setContentType("application/x-msdownload");
			response.setCharacterEncoding("GB2312");

			filename = new String(filename.getBytes("gbk"), "iso8859-1");
			response.addHeader("Content-Disposition",
					"attachment;filename=" + filename);
			// // 文件的存放路径
			/*
			 * javax.servlet.RequestDispatcher dispatcher = request
			 * .getRequestDispatcher(path); if (dispatcher != null) {
			 * dispatcher.forward(request, response); }
			 */
			// 循环取出流中的数据
			byte[] b = new byte[128];
			int len;
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();
			fc.disconnect();
		}
		return null;
	}
	
	public ActionForward performClaimTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
{
	String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
	String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
	IMofficeDataMainManager mainMgr = (IMofficeDataMainManager)ApplicationContextHolder.getInstance().getBean("iMofficeDataMainManager");
	String id = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
	MofficeDataMain main = (MofficeDataMain)mainMgr.getSingleMainPO(id);
	String interfaceFlag = main.getMainStyle2();
	System.out.println("接口调用成功的标志位" + interfaceFlag);
	if ("61".equals(operateType) && "OfficeMadeTask".equals(taskName) && "false".equals(interfaceFlag))
		return super.performDeal(mapping, form, request, response);
	else
		return super.performClaimTask(mapping, form, request, response);
}
	
	/**
	 * showDrawing
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showDrawing(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("draw");
	}

	/**
	 * showPic
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showPic(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("pic");
	}

	/**
	 * showKPI
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showKPI(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("kpi");
	}

	/**
	 * 获取局数据模板下载地址
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @date 2016-4-6下午02:19:29
	 * @author weichao
	 */
	public void getTemplatePath(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String tbn = StaticMethod.nullObject2String(request.getParameter("tbn"));
		if (!"".equals(tbn)) {
			// 1、调局数据接口获知下载地址
			Map map = new HashMap();
			map.put("BusiName", tbn);
			String op = InterfaceUtilProperties.getInstance().getXmlFromMap(map,
					StaticMethod.getFilePathForUrl("classpath:config/mofficedata-util.xml"), "getTemplatePath");
			String opdetail = MofficeDataInService.getTemplatePath(op);
			if (null != opdetail) {
				List attach = MofficeUtil.getAttachRefFromXml(opdetail);
				if (null != attach && attach.size() > 0) {
					com.boco.eoms.util.AttachRef tmp = (com.boco.eoms.util.AttachRef) attach.get(0);
					String url=tmp.getAttachURL().substring(tmp.getAttachURL().indexOf("@")+1);
					// 2、将数据返回前台
					JSONObject jsonRoot = new JSONObject();
					jsonRoot.put("url", "ftp://"+url);
					jsonRoot.put("filename", tmp.getAttachName());
					jsonRoot.put("status", "0");
					JSONUtil.print(response, jsonRoot.toString());
				} else {
					JSONObject jsonRoot = new JSONObject();
					jsonRoot.put("text", "局数据系统返回下载地址错误，无法解析！");
					jsonRoot.put("status", "2");
					JSONUtil.print(response, jsonRoot.toString());
				}
			} else {
				BocoLog.info(this, "==getTemplatePath interfaces failed==");
				JSONObject jsonRoot = new JSONObject();
				jsonRoot.put("text", "获取局数据系统业务标准表模板文件路径失败！");
				jsonRoot.put("status", "2");
				JSONUtil.print(response, jsonRoot.toString());
			}

		} else {
			BocoLog.info(this, "==tbn" + tbn);
			JSONObject jsonRoot = new JSONObject();
			jsonRoot.put("text", "请选择局数据业务类型！");
			jsonRoot.put("status", "2");
			JSONUtil.print(response, jsonRoot.toString());
		}

	}
	
	/**
	 * 获取制作人信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showProducers(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String mainId = StaticMethod.nullObject2String(request.getParameter("mainId"));
		String preLink = StaticMethod.nullObject2String(request.getParameter("preLink"));
		String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
		if(!"".equals(mainId)){
			IMofficeDataProMatchManager proMgr = (IMofficeDataProMatchManager)this.getBean("iMofficeDataProMatchManager");
			Integer pageSize = ((SheetAttributes) ApplicationContextHolder
					.getInstance().getBean("SheetAttributes")).getPageSize();
			String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
			String exportType = StaticMethod.null2String(request
							.getParameter(new org.displaytag.util.ParamEncoder("taskList")
							.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));
			if (!exportType.equals("")) {
				pageSize = new Integer(-1);
			} 
			
			final Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
					: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
			String hql = "from MofficeDataProMatch where mainId='"+mainId+"'";
			if ("OfficeMadeTask".equals(taskName)){
				hql = "from MofficeDataProMatch where mainId='" + mainId + "' and CORREKEY ='" + preLink + "'";
			}
			Map map = proMgr .getProMatchsByCondition(hql, pageIndex, pageSize);
			int total = Integer.parseInt(map.get("total").toString());
			List result = (List)map.get("list");
			request.setAttribute("taskList", result);
			request.setAttribute("total", Integer.valueOf(total));
			request.setAttribute("pageSize", pageSize);
		}
		return mapping.findForward("prolist");
	}

	/**
	 * 删除制作人
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void delProMatch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = StaticMethod.nullObject2String(request.getParameter("id"));
		if (!"".equals(id)) {
			IMofficeDataProMatchManager proMgr = (IMofficeDataProMatchManager)ApplicationContextHolder.getInstance()
					.getBean("iMofficeDataProMatchManager");
			boolean flag = proMgr.delById(id);
			if (flag) {
				JSONObject jsonRoot = new JSONObject();
				jsonRoot.put("status", "0");
				JSONUtil.print(response, jsonRoot.toString());
			} else {
				JSONObject jsonRoot = new JSONObject();
				jsonRoot.put("text", "删除失败！");
				jsonRoot.put("status", "2");
				JSONUtil.print(response, jsonRoot.toString());
			}
		} else {
			BocoLog.info(this, "==id" + id);
			JSONObject jsonRoot = new JSONObject();
			jsonRoot.put("text", "主键为空，删除失败！");
			jsonRoot.put("status", "2");
			JSONUtil.print(response, jsonRoot.toString());
		}
	}
	
	
}
