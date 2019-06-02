package com.boco.eoms.partner.baseinfo.webapp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.partner.baseinfo.dao.AreaDeptTreeDao;
import com.boco.eoms.partner.baseinfo.mgr.AreaDeptTreeMgr;
import com.boco.eoms.partner.baseinfo.mgr.TawApparatusMgr;
import com.boco.eoms.partner.baseinfo.model.AreaDeptTree;
import com.boco.eoms.partner.baseinfo.model.TawApparatus;
import com.boco.eoms.partner.baseinfo.util.TawApparatusConstants;
import com.boco.eoms.partner.baseinfo.webapp.form.TawApparatusForm;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.dict.webapp.form.TawSystemDictTypeForm;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

/**
 * <p>
 * Title:仪器仪表
 * </p>
 * <p>
 * Description:仪器仪表
 * </p>
 * <p>
 * Wed Feb 04 16:31:56 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() fengshaohong
 * @moudle.getVersion() 3.5
 * 
 */
public final class TawApparatusAction extends BaseAction {
	Integer size = null;
String allNode="";
	Integer index = null;

	String backsql = "";

	/**
	 * 未指定方法时默认调用的方法
	 * 
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
	 * 新增仪器仪表
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
		String nodeId = StaticMethod.null2String(request.getParameter("nodeId"));
		nodeId = nodeId.substring(0, 7);
		TawApparatusForm tawApparatusForm = new TawApparatusForm();
		tawApparatusForm.setDept_id(nodeId);
		AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
		AreaDeptTree adt = areaDeptTreeMgr.getAreaDeptTreeByNodeId(nodeId);
		tawApparatusForm.setArea_id(adt.getParentNodeId());
		List listId = areaDeptTreeMgr.getAreaDeptTreesByType("area");
		List listName = new ArrayList();
		List list_id=new ArrayList();
		for(int i=0;i<listId.size();i++){
			String tempId = ((AreaDeptTree)(listId.get(i))).getNodeId();
			listName.add(areaDeptTreeMgr.id2Name(tempId));
			list_id.add(tempId);
		}
		updateFormBean(mapping, request, tawApparatusForm);
		request.setAttribute("listName", listName);
		request.setAttribute("listId", list_id);
		return mapping.findForward("edit");
	}

	/**
	 * 修改仪器仪表
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
		TawApparatusMgr tawApparatusMgr = (TawApparatusMgr) getBean("tawApparatusMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		TawApparatus tawApparatus = tawApparatusMgr.getTawApparatus(id);
		TawApparatusForm tawApparatusForm = (TawApparatusForm) convert(tawApparatus);
		updateFormBean(mapping, request, tawApparatusForm);
		AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
		List listId = areaDeptTreeMgr.getAreaDeptTreesByType("area");
		List listName = new ArrayList();
		List list_id=new ArrayList();
		for(int i=0;i<listId.size();i++){
			String tempId = ((AreaDeptTree)(listId.get(i))).getNodeId();
			listName.add(areaDeptTreeMgr.id2Name(tempId));
			list_id.add(tempId);
		}
		request.setAttribute("nodeId", tawApparatusForm.getDept_id());
		request.setAttribute("listName", listName);
		request.setAttribute("listId", list_id);
		return mapping.findForward("edit");
	}

	/**
	 * 保存仪器仪表
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
		TawApparatusMgr tawApparatusMgr = (TawApparatusMgr) getBean("tawApparatusMgr");
		TawApparatusForm tawApparatusForm = (TawApparatusForm) form;
		String apparatusId = tawApparatusForm.getApparatusId();
		String factory = tawApparatusForm.getFactory();
		if (factory.equals("1120301")) {
			String factory2 = request.getParameter("factory2");
			TawSystemDictTypeForm tawSystemDictTypeForm = new TawSystemDictTypeForm();

			ITawSystemDictTypeManager mgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");
			TawSystemDictType tawSystemDictType = (TawSystemDictType) convert(tawSystemDictTypeForm);
			tawSystemDictType.setDictName(factory2);
			String paredictid = "11203";
			tawSystemDictType.setParentDictId(paredictid);
			String newdictid = mgr.getMaxDictid(paredictid);
			TawSystemDictType dictType = new TawSystemDictType();
			dictType = mgr.getDictTypeByDictid(paredictid);
			if (tawSystemDictType.getDictId() == null
					|| tawSystemDictType.getDictId().equals("")) {
				tawSystemDictType.setDictId(newdictid);
			}
			tawSystemDictType.setDictCode(tawSystemDictType.getDictId());
			dictType.setLeaf(Integer.valueOf(StaticVariable.NOTLEAF));
			tawSystemDictType.setSysType(new Integer(dictType.getSysType()
					.intValue() + 1));
			tawSystemDictType.setLeaf(Integer.valueOf(StaticVariable.LEAF));

			mgr.saveTawSystemDictType(dictType);
			mgr.saveTawSystemDictType(tawSystemDictType);
			tawApparatusForm.setFactory(newdictid);
		}
		boolean isNew = (null == tawApparatusForm.getId() || ""
				.equals(tawApparatusForm.getId()));
		TawApparatus tawApparatus = (TawApparatus) convert(tawApparatusForm);
		TawSystemSessionForm sessionForm = this.getUser(request);
		String addMan = sessionForm.getUserid();
		Date addTime = new Date();
		String connectType = sessionForm.getContactMobile();

		if (isNew) {
			Boolean isU = tawApparatusMgr.isunique(apparatusId);
			if(!isU.booleanValue()){
				AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
				List listId = areaDeptTreeMgr.getAreaDeptTreesByType("area");
				List listName = new ArrayList();
				List list_id=new ArrayList();
				for(int i=0;i<listId.size();i++){
					String tempId = ((AreaDeptTree)(listId.get(i))).getNodeId();
					listName.add(areaDeptTreeMgr.id2Name(tempId));
					list_id.add(tempId);
				}
				updateFormBean(mapping, request, tawApparatusForm);
				request.setAttribute("listName", listName);
				request.setAttribute("listId", list_id);
				request.setAttribute("fallure", " 仪器仪表编号已经存在");
				return mapping.findForward("edit");
			}
			tawApparatus.setAddMan(addMan);
			tawApparatus.setAddTime(addTime);
			tawApparatus.setConnectType(connectType);
			tawApparatusMgr.saveTawApparatus(tawApparatus);
		} else {  
			String tempId2 = tawApparatusForm.getId();
			TawApparatus old = tawApparatusMgr.getTawApparatus(tempId2);
			String apparatusId_old = old.getApparatusId();
			if(!apparatusId_old.equals(apparatusId)){
				Boolean isU = tawApparatusMgr.isunique(apparatusId);
				if(!isU.booleanValue()){
					AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
					List listId = areaDeptTreeMgr.getAreaDeptTreesByType("area");
					List listName = new ArrayList();
					List list_id=new ArrayList();
					for(int i=0;i<listId.size();i++){
						String tempId = ((AreaDeptTree)(listId.get(i))).getNodeId();
						listName.add(areaDeptTreeMgr.id2Name(tempId));
						list_id.add(tempId);
					}
					updateFormBean(mapping, request, tawApparatusForm);
					request.setAttribute("listName", listName);
					request.setAttribute("listId", list_id);
					request.setAttribute("fallure", " 仪器仪表编号已经存在");
					return mapping.findForward("edit");
				}
			}
			
			tawApparatus.setAddMan(old.getAddMan());
			tawApparatus.setAddTime(old.getAddTime());
			tawApparatus.setConnectType(old.getConnectType());
			tawApparatusMgr.saveTawApparatus(tawApparatus);
		}
		request.setAttribute("nodeId", tawApparatusForm.getDept_id());
		return mapping.findForward("success");
	}

	/**
	 * 删除仪器仪表
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
		TawApparatusMgr tawApparatusMgr = (TawApparatusMgr) getBean("tawApparatusMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		tawApparatusMgr.removeTawApparatus(id);
		request.setAttribute("nodeId", allNode);
		return mapping.findForward("success");
	}

	/**
	 * 分页显示仪器仪表列表
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
		String isCity = "no";
		String nodeId = StaticMethod.null2String(request.getParameter("nodeId"));
		
		//2009-3-6 解决页面刷新后，链接参数为空的情况
		String in = StaticMethod.null2String(request.getParameter("in"));//此条件表示点省、地市、厂商节点的查询，调用search方法。
		if(!in.equals("")){
			request.setAttribute("inNodeId", nodeId);
			request.setAttribute("in", in);
		}
		
		if(!"null".equals(nodeId)&&nodeId!=null&&!"".equals(nodeId)){
			;
		}else{
			nodeId=(String)request.getAttribute("nodeId");
		}
		allNode=nodeId;
			AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
			AreaDeptTree adt = areaDeptTreeMgr.getAreaDeptTreeByNodeId(nodeId);
			TawApparatusForm tawApparatusForm = new TawApparatusForm();
			String type = adt.getNodeType();
			List listName = new ArrayList();
			List list_id=new ArrayList();
			if(type.equals("factory")){
				tawApparatusForm.setDept_id(nodeId);
				tawApparatusForm.setArea_id(adt.getParentNodeId());
				isCity="yes";
			}else if(type.equals("area")){
				tawApparatusForm.setArea_id(nodeId);
				tawApparatusForm.setDept_id("");
				
			}else if(type.equals("province")){
				listName.add(" ");
				list_id.add(" ");
			}else{
				isCity="yes";
				AreaDeptTree adt2 = areaDeptTreeMgr.getAreaDeptTreeByNodeId(nodeId.substring(0,7));
				tawApparatusForm.setDept_id(nodeId.substring(0,7));
				tawApparatusForm.setArea_id(adt2.getParentNodeId());
				nodeId=nodeId.substring(0,7);
			}
		//vvvvvvvvvvvvvvvv
		
		List listId = areaDeptTreeMgr.getAreaDeptTreesByType("area");
		for(int i=0;i<listId.size();i++){
			String tempId = ((AreaDeptTree)(listId.get(i))).getNodeId();
			listName.add(areaDeptTreeMgr.id2Name(tempId));
			list_id.add(tempId);
		}
		updateFormBean(mapping, request, tawApparatusForm);
		request.setAttribute("listName", listName);
		request.setAttribute("listId", list_id);
		//vvvvvvvvvvvv
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				TawApparatusConstants.TAWAPPARATUS_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		size = pageSize;
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		index = pageIndex;
		String whereStr = " and dept_id like '"+nodeId+"%'";
		TawApparatusMgr tawApparatusMgr = (TawApparatusMgr) getBean("tawApparatusMgr");
		Map map = (Map) tawApparatusMgr.getTawApparatuss(pageIndex, pageSize,
				whereStr);
		List list = (List) map.get("result");
		request.setAttribute(TawApparatusConstants.TAWAPPARATUS_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("nodeId", nodeId);
		request.setAttribute("isCity", isCity);
		return mapping.findForward("list");
	}

	/**
	 * 条件查询 分页显示仪器仪表列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawApparatusMgr tawApparatusMgr = (TawApparatusMgr) getBean("tawApparatusMgr");
		TawApparatusForm tawApparatusForm = (TawApparatusForm) form;
		String type = tawApparatusForm.getType();
		String state = tawApparatusForm.getState();
		String dept_id = tawApparatusForm.getDept_id().trim();
		String area_id = tawApparatusForm.getArea_id().trim();
		StringBuffer sql = new StringBuffer();
		String isCity = "no";
		if (!"".equals(type) && type != null) {
			sql.append(" and type= '" + type + "'");
		}
		if (!"".equals(state) && state != null) {
			sql.append(" and state= '" + state + "'");
		}
		if (!"".equals(dept_id) && dept_id != null) {
			sql.append(" and dept_id= '" + dept_id + "'");
			isCity="yes";
		}
		if (!"".equals(area_id) && area_id != null) {
			sql.append(" and area_id= '" + area_id + "'");
		}
		backsql = sql.toString();
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				TawApparatusConstants.TAWAPPARATUS_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		size = pageSize;
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		index = pageIndex;
		Map map = (Map) tawApparatusMgr.getTawApparatuss(pageIndex, pageSize,
				sql.toString());
//		vvvvvvvvvvvvvvvv
		AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
		List listId = areaDeptTreeMgr.getAreaDeptTreesByType("area");
		List listName = new ArrayList();
		List list_id=new ArrayList();
		for(int i=0;i<listId.size();i++){
			String tempId = ((AreaDeptTree)(listId.get(i))).getNodeId();
			listName.add(areaDeptTreeMgr.id2Name(tempId));
			list_id.add(tempId);
		}
		updateFormBean(mapping, request, tawApparatusForm);
		request.setAttribute("listName", listName);
		request.setAttribute("listId", list_id);
		//vvvvvvvvvvvv
		List list = (List) map.get("result");
		request.setAttribute(TawApparatusConstants.TAWAPPARATUS_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("nodeId", dept_id);
		request.setAttribute("isCity", isCity);
		return mapping.findForward("list");
	}

	public ActionForward backToSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawApparatusMgr tawApparatusMgr = (TawApparatusMgr) getBean("tawApparatusMgr");
		Map map = (Map) tawApparatusMgr.getTawApparatuss(index, size, backsql);
		List list = (List) map.get("result");
//		vvvvvvvvvvvvvvvv
		AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
		String nodeId = request.getParameter("nodeId");
		AreaDeptTree adt = areaDeptTreeMgr.getAreaDeptTreeByNodeId(nodeId);
		TawApparatusForm tawApparatusForm = new TawApparatusForm();
		tawApparatusForm.setArea_id(adt.getParentNodeId());
		List listId = areaDeptTreeMgr.getAreaDeptTreesByType("area");
		List listName = new ArrayList();
		List list_id=new ArrayList();
		for(int i=0;i<listId.size();i++){
			String tempId = ((AreaDeptTree)(listId.get(i))).getNodeId();
			listName.add(areaDeptTreeMgr.id2Name(tempId));
			list_id.add(tempId);
		}
		updateFormBean(mapping, request, tawApparatusForm);
		request.setAttribute("listName", listName);
		request.setAttribute("listId", list_id);
		//vvvvvvvvvvvv
		request.setAttribute(TawApparatusConstants.TAWAPPARATUS_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", size);
		return mapping.findForward("list");
	}

	public ActionForward xlsSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		TawApparatusMgr mgr = (TawApparatusMgr) getBean("tawApparatusMgr");
		AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
		// 首先将文件从客户端上传到服务器
		String timeTag = StaticMethod.getCurrentDateTime("yyyy_MM_dd_HHmmss");
		String sysTemPaht = request.getRealPath("/");
		TawApparatusForm accessoryForm = (TawApparatusForm) form;
		String uploadPath = "/WEB-INF/pages/partner/baseinfo/tawApparatus/upfiles";
		String filePath = sysTemPaht + uploadPath + "/" + timeTag + ".xls";
		File tempFile = new File(sysTemPaht + uploadPath);
		if (!tempFile.exists()) {
			tempFile.mkdir();
		}
		FormFile file = accessoryForm.getAccessoryName();
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
			return mapping.findForward("fail");
		}
		// 然后把文件的每一条数据读入到form中
		Workbook workbook = null;
		ArrayList formList = new ArrayList();
		ArrayList numberList = new ArrayList();
		InputStream ins = new FileInputStream(filePath);
		try {
			// 构建Workbook对象, 只读Workbook对象
			// 直接从本地文件创建Workbook, 从输入流创建Workbook
			
			workbook = Workbook.getWorkbook(ins);
			Sheet dataSheet = workbook.getSheet(0);

			// 读取数据
			for (int i = 1; i < dataSheet.getRows(); i++) {
				TawApparatus temp = new TawApparatus();
				if (dataSheet.getCell(0, i).getContents() != null
						&& !"".equals(dataSheet.getCell(0, i).getContents())){
					if(mgr.isunique(dataSheet.getCell(0, i).getContents()).booleanValue())
					{
					temp.setApparatusId(dataSheet.getCell(0, i).getContents()
							.trim());
					}else{
						numberList.add(new Integer(i+1));
						numberList.add(new Integer(1));
						continue;
					}
				}else{
					break;
//					numberList.add(new Integer(i+1));
//					numberList.add(new Integer(1));
//					continue;
				}
				if (dataSheet.getCell(1, i).getContents() != null
						&& !"".equals(dataSheet.getCell(1, i).getContents())){
					temp.setApparatusName(dataSheet.getCell(1, i).getContents()
							.trim());
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(2));
					continue;
				}
				if (dataSheet.getCell(2, i).getContents() != null
						&& !"".equals(dataSheet.getCell(2, i).getContents())){
					if(!"".equals(mgr.name2Id(dataSheet.getCell(2, i).getContents().trim(),"11203"))&&
							(mgr.name2Id(dataSheet.getCell(2, i).getContents().trim(),"11203"))!=null)
					{
					temp.setFactory(mgr.name2Id(dataSheet.getCell(2, i).getContents().trim(),"11203"));
					}else{
						numberList.add(new Integer(i+1));
						numberList.add(new Integer(3));
						continue;
					}
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(3));
					continue;
				}
				if (dataSheet.getCell(3, i).getContents() != null
						&& !"".equals(dataSheet.getCell(3, i).getContents())){
					if(!"".equals(areaDeptTreeMgr.getNodeIdByNodeName(dataSheet.getCell(3, i).getContents().trim()))&&
							(areaDeptTreeMgr.getNodeIdByNodeName(dataSheet.getCell(3, i).getContents().trim()))!=null)
					{
					temp.setArea_id(areaDeptTreeMgr.getNodeIdByNodeName(dataSheet.getCell(3, i).getContents().trim()));
					}else{
						numberList.add(new Integer(i+1));
						numberList.add(new Integer(4));
						continue;
					}
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(4));
					continue;
				}
				if (dataSheet.getCell(4, i).getContents() != null
						&& !"".equals(dataSheet.getCell(4, i).getContents())){
					if(!"".equals(areaDeptTreeMgr.getNodeIdByParentAndName(areaDeptTreeMgr.getNodeIdByNodeName(dataSheet.getCell(3, i).getContents().trim()), dataSheet.getCell(4, i).getContents().trim()))&&
							(areaDeptTreeMgr.getNodeIdByParentAndName(areaDeptTreeMgr.getNodeIdByNodeName(dataSheet.getCell(3, i).getContents().trim()), dataSheet.getCell(4, i).getContents().trim()))!=null)
					{
					temp.setDept_id(areaDeptTreeMgr.getNodeIdByNodeName(dataSheet.getCell(4, i).getContents().trim()));
					}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(5));
					continue;
					}
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(5));
					continue;
				}
				
				if (dataSheet.getCell(5, i).getContents() != null
						&& !"".equals(dataSheet.getCell(5, i).getContents())){
					if(!"".equals(mgr.name2Id(dataSheet.getCell(5, i).getContents().trim(),"11204"))&&
							(mgr.name2Id(dataSheet.getCell(5, i).getContents().trim(),"11204"))!=null)
					{
					temp.setType(mgr.name2Id(dataSheet.getCell(5, i).getContents().trim(),"11204"));
					}else{
						numberList.add(new Integer(i+1));
						numberList.add(new Integer(6));
						continue;
					}
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(6));
					continue;
				}
				if (dataSheet.getCell(6, i).getContents() != null
						&& !"".equals(dataSheet.getCell(6, i).getContents())){
					temp.setModel(dataSheet.getCell(6, i).getContents().trim());
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(7));
					continue;
				}
				if (dataSheet.getCell(7, i).getContents() != null
						&& !"".equals(dataSheet.getCell(7, i).getContents())){
					if(!"".equals(mgr.name2Id(dataSheet.getCell(7, i).getContents().trim(),"11205"))&&
							(mgr.name2Id(dataSheet.getCell(7, i).getContents().trim(),"11205"))!=null)
					{
					temp.setState(mgr.name2Id(dataSheet.getCell(7, i).getContents().trim(),"11205"));
					}else{
						numberList.add(new Integer(i+1));
						numberList.add(new Integer(8));
						continue;
					}
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(8));
					continue;
				}
				if (dataSheet.getCell(8, i).getContents() != null
						&& !"".equals(dataSheet.getCell(8, i).getContents())){
					temp.setStorage(dataSheet.getCell(8, i).getContents()
							.trim());
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(9));
					continue;
				}
				if (dataSheet.getCell(9, i).getContents() != null
						&& !"".equals(dataSheet.getCell(9, i).getContents())){
					temp.setRemark(dataSheet.getCell(9, i).getContents()
									.trim());
				}
				TawSystemSessionForm sessionForm = this.getUser(request);
				String addMan = sessionForm.getUserid();
				Date addTime = new Date();
				String connectType = sessionForm.getContactMobile();
				temp.setAddMan(addMan);
				temp.setAddTime(addTime);
				temp.setConnectType(connectType);
				formList.add(temp);
			}
			for(int i = 0;i<formList.size();i++){
				mgr.saveTawApparatus((TawApparatus) formList.get(i));
			}
			String problemRow ="";
			for(int i=0;i<numberList.size();i++){
				problemRow +=","+numberList.get(i);
			}String str ="";
			if(!"".equals(problemRow)){
			 String sub = problemRow.substring(1, problemRow.length());
			 String []array = sub.split(",");
			   str = "未成功录入！以下为不合法的未录入的信息："+"<br>";
			  for(int i=0;i<array.length;i++){
			  
			  str = str+"第"+array[i]+"行"+"第"+array[i+1]+"列;"+"<br>";
			  i++;
			  }
			}else
			{
				str ="成功录入所有信息";
			}
			request.setAttribute("problemRow", str);
		} catch (Exception e) {
			workbook.close();
			
			File fileDel = new File(filePath);
			if (fileDel.exists())
				fileDel.delete();
			e.printStackTrace();
			return mapping.findForward("fail");
		} finally {
			workbook.close();
			ins.close();
			File fileDel = new File(filePath);
			if (fileDel.exists())
				fileDel.delete();
		}
		
		return mapping.findForward("xlsInput");
		
		//return search(mapping, accessoryForm, request, response);
	
	}
	public ActionForward toXls(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("problemRow", "");
		return mapping.findForward("xlsInput");
	}
	public ActionForward changeDep(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
		List deptListId = areaDeptTreeMgr.getNextLevelAreaDeptTrees(id);
		List deptListName = new ArrayList();
		for(int i=0;i<deptListId.size();i++){
			String tempId = ((AreaDeptTree)(deptListId.get(i))).getNodeId();
			deptListName.add(areaDeptTreeMgr.id2Name(tempId));
		}
		StringBuffer d_list = new StringBuffer();
       for(int i=0;i<deptListId.size();i++){
    	   deptListName.add(areaDeptTreeMgr.id2Name(((AreaDeptTree)(deptListId.get(i))).getNodeId()));
    	   d_list.append(	"," +((AreaDeptTree)(deptListId.get(i))).getNodeId() );
    	   d_list.append(	"," +deptListName.get(i) );
    	   
       }
       
       String aaa = d_list.toString();
       aaa=aaa.substring(1, aaa.length());
		response.setContentType("text/html; charset=GBK");
		response.getWriter().print(aaa);
		response.getWriter().flush();
		return null;
	}
	public ActionForward changeDep2(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
		List deptListId = areaDeptTreeMgr.getNextLevelAreaDeptTrees(id);
		List deptListName = new ArrayList();
		for(int i=0;i<deptListId.size();i++){
			String tempId = ((AreaDeptTree)(deptListId.get(i))).getNodeId();
			deptListName.add(areaDeptTreeMgr.id2Name(tempId));
		}
		StringBuffer d_list = new StringBuffer();
       for(int i=0;i<deptListId.size();i++){
    	   deptListName.add(areaDeptTreeMgr.id2Name(((AreaDeptTree)(deptListId.get(i))).getNodeId()));
    	   d_list.append(	"," +((AreaDeptTree)(deptListId.get(i))).getNodeId() );
    	   d_list.append(	"," +deptListName.get(i) );
    	   
       }
       
       String aaa =" ,"+ d_list.toString();
		response.setContentType("text/html; charset=GBK");
		response.getWriter().print(aaa);
		response.getWriter().flush();
		return null;
	}
	public ActionForward outPutModel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		
		try{
			String sysTemPath = request.getRealPath("/");
			String url = sysTemPath + "/partner/model/tawApparatus.xls";
			File file = new File(url);
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
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			return mapping.findForward("fail");
		}
		
		return null;
	}
	/**
	 * 分页显示仪器仪表列表，支持Atom方式接入Portal
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 * public ActionForward search4Atom(ActionMapping mapping, ActionForm form,
	 * HttpServletRequest request, HttpServletResponse response) throws
	 * Exception { try { // --------------用于分页，得到当前页号------------- final Integer
	 * pageIndex = new Integer(request .getParameter("pageIndex")); final
	 * Integer pageSize = new Integer(request .getParameter("pageSize"));
	 * TawApparatusMgr tawApparatusMgr = (TawApparatusMgr)
	 * getBean("tawApparatusMgr"); Map map = (Map)
	 * tawApparatusMgr.getTawApparatuss(pageIndex, pageSize, ""); List list =
	 * (List) map.get("result"); TawApparatus tawApparatus = new TawApparatus();
	 * 
	 * //创建ATOM源 Factory factory = Abdera.getNewFactory(); Feed feed =
	 * factory.newFeed();
	 *  // 分页 for (int i = 0; i < list.size(); i++) { tawApparatus =
	 * (TawApparatus) list.get(i);
	 *  // TODO 请按照下面的实例给entry赋值 Entry entry = feed.insertEntry();
	 * entry.setTitle("<a href='" + request.getScheme() + "://" +
	 * request.getServerName() + ":" + request.getServerPort() +
	 * request.getContextPath() +
	 * "/tawApparatus/tawApparatuss.do?method=edit&id=" + tawApparatus.getId() + "'
	 * target='_blank'>" + display name for list + "</a>");
	 * entry.setSummary(summary); entry.setContent(content);
	 * entry.setLanguage(language); entry.setText(text);
	 * entry.setRights(tights);
	 *  // 以下三项需要传入Date类型或String类型（yyyy-MM-dd）的参数 entry.setUpdated(new
	 * java.util.Date()); entry.setPublished(new java.util.Date());
	 * entry.setEdited(new java.util.Date());
	 *  // 为person的name属性赋值，entry.addAuthor可以随意赋值 Person person =
	 * entry.addAuthor(userId); person.setName(userName); }
	 *  // 每页显示条数 feed.setText(map.get("total").toString()); OutputStream os =
	 * response.getOutputStream(); PrintStream ps = new PrintStream(os);
	 * feed.getDocument().writeTo(ps); } catch (Exception e) {
	 * e.printStackTrace(); } return null; }
	 */
}