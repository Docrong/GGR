package com.boco.eoms.cutapply.webapp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.cutapply.mgr.CutApplyMgr;
import com.boco.eoms.cutapply.model.CutApply;
import com.boco.eoms.cutapply.webapp.form.CutApplyForm;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.cutapply.mgr.CutApplyAdminMgr;
import com.boco.eoms.cutapply.model.CutApplyAdmin;
import com.boco.eoms.cutapply.webapp.form.CutApplyAdminForm;

/**
 * <p>
 * Title:干线割接管理
 * </p>
 * <p>
 * Description:干线割接管理
 * </p>
 * <p>
 * Thu Apr 02 16:59:37 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() wangsixuan
 * @moudle.getVersion() 3.5
 * 
 */
public final class CutApplyAction extends BaseAction {

	static String tempSql = "";
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
	 * 新增干线割接管理
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
		TawSystemSessionForm sessionForm = this.getUser(request);
		CutApplyForm cutApplyForm = new CutApplyForm();
		cutApplyForm.setUserId(sessionForm.getUserid());
		cutApplyForm.setDeptId(sessionForm.getDeptid());
		updateFormBean(mapping, request, cutApplyForm);
		return mapping.findForward("edit");
	}

	/**
	 * 修改干线割接管理
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
		CutApplyMgr cutApplyMgr = (CutApplyMgr) getBean("cutApplyMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		CutApply cutApply = cutApplyMgr.getCutApply(id);
		CutApplyForm cutApplyForm = (CutApplyForm) convert(cutApply);
		updateFormBean(mapping, request, cutApplyForm);
		return mapping.findForward("edit");
	}

	/**
	 * 查看干线割接管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xget(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CutApplyMgr cutApplyMgr = (CutApplyMgr) getBean("cutApplyMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		CutApply cutApply = cutApplyMgr.getCutApply(id);
		CutApplyForm cutApplyForm = (CutApplyForm) convert(cutApply);
		updateFormBean(mapping, request, cutApplyForm);
		return mapping.findForward("view");
	}

	/**
	 * 保存干线割接管理
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
		CutApplyMgr cutApplyMgr = (CutApplyMgr) getBean("cutApplyMgr");
		CutApplyForm cutApplyForm = (CutApplyForm) form;
		boolean isNew = (null == cutApplyForm.getId() || "".equals(cutApplyForm
				.getId()));
		CutApply cutApply = (CutApply) convert(cutApplyForm);
		if (isNew) {
			TawSystemSessionForm sessionForm = this.getUser(request);
			cutApply.setUserId(sessionForm.getUserid());
			cutApply.setDeptId(sessionForm.getDeptid());
			cutApplyMgr.saveCutApply(cutApply);
		} else {
			String id = StaticMethod.null2String(request.getParameter("id"));
			CutApply cutApplyOld = cutApplyMgr.getCutApply(id);
			cutApply.setUserId(cutApplyOld.getUserId());
			cutApply.setDeptId(cutApplyOld.getDeptId());
			cutApplyMgr.saveCutApply(cutApply);
		}
		return mapping.findForward("success");
	}

	/**
	 * 删除干线割接管理
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
		CutApplyMgr cutApplyMgr = (CutApplyMgr) getBean("cutApplyMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		cutApplyMgr.removeCutApply(id);
		return mapping.findForward("success");
	}

	/**
	 * 分页显示干线割接管理列表
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
		CutApplyMgr cutApplyMgr = (CutApplyMgr) getBean("cutApplyMgr");
		CutApplyForm cutApplyForm = (CutApplyForm) form;
		tempSql = "";
		String hql = "";
		String userId = StaticMethod
				.null2String(request.getParameter("userId"));
		String deptId = StaticMethod
				.null2String(request.getParameter("deptId"));
		if (userId != null && !("").equals(userId)) {
			hql = hql + " and c.userId ='" + userId + "'";
		}
		if (deptId != null && !("").equals(deptId)) {
			hql = hql + " and c.deptId ='" + deptId + "'";
		}

		String cutStartTime = cutApplyForm.getCutStartTime();
		String cutEndTime = cutApplyForm.getCutEndTime();
		String areaId = cutApplyForm.getAreaId();
		String isAffect = cutApplyForm.getIsAffect();
		if (cutStartTime != null && !"".equals(cutStartTime)) {
			hql = hql + " and c.cutStartTime >='" + cutStartTime + "'";
		}
		if (cutEndTime != null && !"".equals(cutEndTime)) {
			hql = hql + " and c.cutEndTime <='" + cutEndTime + "'";
		}
		if (areaId != null && !"".equals(areaId)) {
			hql = hql + " and c.areaId ='" + areaId + "'";
		}
		if (isAffect != null && !"".equals(isAffect)) {
			hql = hql + " and c.isAffect ='" + isAffect + "'";
		}
		tempSql = hql;
		List list = cutApplyMgr.getCutApplysByCondition(hql);
		List cutApplyList = new ArrayList();
		TawSystemSessionForm sessionForm = this.getUser(request);
		// 找出管理员
		CutApplyAdminMgr cutApplyAdminMgr = (CutApplyAdminMgr) getBean("cutApplyAdminMgr");
		List adminList = cutApplyAdminMgr.getCutApplyAdmins();
		for (int i = 0; i < list.size(); i++) {
			CutApply ca = (CutApply) list.get(i);
			CutApplyForm caf = (CutApplyForm) convert(ca);
			caf.setIsEdit("0");
			// 谁创建的谁有权限编辑和删除
			if (sessionForm.getUserid().equals(ca.getUserId())) {
				caf.setIsEdit("1");
			}
			if (caf.getIsEdit().equals("0")) {
				// 如果是管理员角色的话，有权限编辑和删除
				for (int j = 0; j < adminList.size(); j++) {
					CutApplyAdmin caa = (CutApplyAdmin) adminList.get(j);
					if (sessionForm.getUserid().equals(caa.getUserId())) {
						caf.setIsEdit("1");
					}
				}
			}
			cutApplyList.add(caf);
		}

		request.setAttribute("cutApplyList", cutApplyList);
		return mapping.findForward("list");
	}

	/**
	 * 查询初始化
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("query");
	}

	// 人员管理list
	public ActionForward adminList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CutApplyAdminMgr cutApplyAdminMgr = (CutApplyAdminMgr) getBean("cutApplyAdminMgr");
		List list = cutApplyAdminMgr.getCutApplyAdmins();

		List cutApplyAdminList = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			CutApplyAdmin ca = (CutApplyAdmin) list.get(i);
			CutApplyAdminForm caf = (CutApplyAdminForm) convert(ca);
			ITawSystemUserManager mgr = (ITawSystemUserManager) getBean("ItawSystemUserSaveManagerFlush");
			TawSystemUser tsu = mgr.getUserByuserid(caf.getUserId());
			caf.setDeptId(tsu.getDeptid());
			cutApplyAdminList.add(caf);
		}
		request.setAttribute("cutApplyAdminList", cutApplyAdminList);
		return mapping.findForward("adminList");
	}

	// 删除管理员角色
	public ActionForward removeAdmin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CutApplyAdminMgr cutApplyAdminMgr = (CutApplyAdminMgr) getBean("cutApplyAdminMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		cutApplyAdminMgr.removeCutApplyAdmin(id);
		return mapping.findForward("success");
	}

	// 新增管理员角色初始化
	public ActionForward addAdmin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("addAdmin");
	}

	// 新增管理员角色
	public ActionForward saveAdmin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CutApplyAdminMgr cutApplyAdminMgr = (CutApplyAdminMgr) getBean("cutApplyAdminMgr");
		String userId = StaticMethod
				.null2String(request.getParameter("userId"));
		if (userId != null && !"".equals(userId)) {
			List list = cutApplyAdminMgr
					.getCutApplyAdminsByCondition(" and c.userId = '" + userId
							+ "'");
			if (list.isEmpty()) {
				CutApplyAdmin caa = new CutApplyAdmin();
				caa.setUserId(userId);
				cutApplyAdminMgr.saveCutApplyAdmin(caa);
			}
		}
		return mapping.findForward("success");
	}
	
	
	//批量录入初始化
	public ActionForward accessoryInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("accessory");
	}
	
	/*
	 * 附件批量录入
	 */
	public ActionForward xaccessory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 首先将文件从客户端上传到服务器
		String timeTag = StaticMethod.getCurrentDateTime("yyyy_MM_dd_HHmmss");
		String sysTemPaht = StaticMethod.getWebPath();
		CutApplyForm accessoryForm = (CutApplyForm) form;
		String uploadPath = "/WEB-INF/pages/cutApply/upfiles";
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
			return mapping.findForward("failure");
		}

		// 然后把文件的每一条数据读入到form中
		Workbook workbook = null;
		ArrayList accessoryList = new ArrayList();
		try {
			// 构建Workbook对象, 只读Workbook对象
			// 直接从本地文件创建Workbook, 从输入流创建Workbook
			InputStream ins = new FileInputStream(filePath);
			workbook = Workbook.getWorkbook(ins);
			Sheet dataSheet = workbook.getSheet(0);

			// 读取数据
			for (int i = 1; i < dataSheet.getRows(); i++) {
				CutApply ca = new CutApply();
				if (dataSheet.getCell(0, i).getContents() != null
						&& !"".equals(dataSheet.getCell(0, i).getContents()))
					ca.setUserId(dataSheet.getCell(0, i)
							.getContents().trim());
				if (dataSheet.getCell(1, i).getContents() != null
						&& !"".equals(dataSheet.getCell(1, i).getContents()))
					ca.setDeptId(dataSheet.getCell(1, i)
							.getContents().trim());
				if (dataSheet.getCell(2, i).getContents() != null
						&& !"".equals(dataSheet.getCell(2, i).getContents()))
					ca.setContext(dataSheet.getCell(2, i)
							.getContents().trim());
				if (dataSheet.getCell(3, i).getContents() != null
						&& !"".equals(dataSheet.getCell(3, i).getContents()))
					ca.setAreaId(dataSheet.getCell(3, i)
							.getContents().trim());
				if (dataSheet.getCell(4, i).getContents() != null
						&& !"".equals(dataSheet.getCell(4, i).getContents()))
					ca.setCutStartTime(dataSheet.getCell(4, i)
							.getContents().trim());
				if (dataSheet.getCell(5, i).getContents() != null
						&& !"".equals(dataSheet.getCell(5, i).getContents()))
					ca.setCutEndTime(dataSheet.getCell(5, i)
							.getContents().trim());
				if (dataSheet.getCell(6, i).getContents() != null
						&& !"".equals(dataSheet.getCell(6, i).getContents()))
					ca.setManager(dataSheet.getCell(6, i)
							.getContents().trim());
				if (dataSheet.getCell(7, i).getContents() != null
						&& !"".equals(dataSheet.getCell(7, i).getContents()))
					ca.setPhone(dataSheet.getCell(7, i)
							.getContents().trim());
				if (dataSheet.getCell(8, i).getContents() != null
						&& !"".equals(dataSheet.getCell(8, i).getContents()))
					ca.setFileId(dataSheet.getCell(8, i)
							.getContents().trim());
				if (dataSheet.getCell(9, i).getContents() != null
						&& !"".equals(dataSheet.getCell(9, i).getContents()))
					ca.setIsAffect(dataSheet.getCell(9, i)
							.getContents().trim());
				if (dataSheet.getCell(10, i).getContents() != null
						&& !"".equals(dataSheet.getCell(10, i).getContents()))
					ca.setAffectStartTime(dataSheet.getCell(10, i)
							.getContents().trim());
				if (dataSheet.getCell(11, i).getContents() != null
						&& !"".equals(dataSheet.getCell(11, i).getContents()))
					ca.setAffectEndTime(dataSheet.getCell(11, i)
							.getContents().trim());
				if (dataSheet.getCell(12, i).getContents() != null
						&& !"".equals(dataSheet.getCell(12, i).getContents()))
					ca.setAffectInfo(dataSheet.getCell(12, i)
							.getContents().trim());
				if (dataSheet.getCell(13, i).getContents() != null
						&& !"".equals(dataSheet.getCell(13, i).getContents()))
					ca.setRemark(dataSheet.getCell(13, i)
							.getContents().trim());

				accessoryList.add(ca);
			}

			workbook.close();
			File fileDel = new File(filePath);
			if (fileDel.exists())
				fileDel.delete();
			
			//保存记录
			ITawSystemUserManager userMgr = (ITawSystemUserManager) getBean("ItawSystemUserSaveManagerFlush");
			CutApplyMgr cutApplyMgr = (CutApplyMgr) getBean("cutApplyMgr"); 
			boolean isRight = false;
			for(int i=0; i<accessoryList.size(); i++){
				CutApply ca = (CutApply)accessoryList.get(i);
				
				List userList= userMgr.getUsersByName(ca.getUserId(), ca.getDeptId());
				for(int j=0; j<userList.size(); j++){
					TawSystemUser tsu = (TawSystemUser)userList.get(j);
					if(tsu.getUsername().equals(ca.getUserId())&&tsu.getDeptname().equals(ca.getDeptId())&&!isRight){
						ca.setUserId(tsu.getUserid());
						ca.setDeptId(tsu.getDeptid());
						ca.setAreaId(cutApplyMgr.name2Id(ca.getAreaId(), "11601"));
						ca.setIsAffect(cutApplyMgr.name2Id(ca.getIsAffect(), "11602"));
						isRight = true;
					}
				}
				if(isRight){
					cutApplyMgr.saveCutApply(ca);
					isRight = false;
				}else{
					return mapping.findForward("fail");
				}
			}
			return mapping.findForward("success");
		} catch (Exception e) {
			workbook.close();
			File fileDel = new File(filePath);
			if (fileDel.exists())
				fileDel.delete();
			e.printStackTrace();
			return mapping.findForward("fail");
		} finally {
			workbook.close();
			File fileDel = new File(filePath);
			if (fileDel.exists())
				fileDel.delete();
		}
	}

	// 模板文件下载
	public ActionForward xmlOutPut(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String url = request.getParameter("url");
			url = StaticMethod.getWebPath() + url;
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
	
    //导出到Excel
	public ActionForward exportXml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			CutApplyMgr cutApplyMgr = (CutApplyMgr) getBean("cutApplyMgr"); 
			ITawSystemUserManager mgr = (ITawSystemUserManager) getBean("ItawSystemUserSaveManagerFlush");
			ITawSystemDictTypeManager dictMgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");
			
			List tempList = cutApplyMgr.getCutApplysByCondition(tempSql);
			Vector objectArray = new Vector(tempList.size()+1);
			Vector fName = new Vector(14);
			String[] fieldName = {"割接申请人姓名","割接申请人部门","割接内容","割接所属地州","割接开始时间	","割接结束时间","割接现场负责人",
					"联系电话","申请割接工单号或公文号","是否影响业务","影响业务开始时间","影响业务结束时间","影响业务描述","备注"};
			for (int i = 0; i < fieldName.length; i++) {
				fName.add(fieldName[i]);
			}
			objectArray.add(fName);
			
			for(int i=0; i<tempList.size(); i++){
				CutApply ca = (CutApply)tempList.get(i);
				Vector o = new Vector(14);
				TawSystemUser tsu = mgr.getUserByuserid(ca.getUserId());
				o.add(tsu.getUsername());
				o.add(tsu.getDeptname());
				o.add(ca.getContext());
				o.add(((TawSystemDictType)dictMgr.getDictTypeByDictid(ca.getAreaId())).getDictName());
				o.add(ca.getCutStartTime());
				o.add(ca.getCutEndTime());
				o.add(ca.getManager());
				o.add(ca.getPhone());
				o.add(ca.getFileId());
				o.add(((TawSystemDictType)dictMgr.getDictTypeByDictid(ca.getIsAffect())).getDictName());
				o.add(ca.getAffectStartTime());
				o.add(ca.getAffectEndTime());
				o.add(ca.getAffectInfo());
				o.add(ca.getRemark());
				objectArray.add(o);
			}
					
			String timeTag = StaticMethod.getCurrentDateTime("yyyy_MM_dd_HHmmss");
			String sysTemPaht = StaticMethod.getWebPath();
			String uploadPath = "/WEB-INF/pages/cutApply/upfiles";
			String url = sysTemPaht + uploadPath + "/" + "exportData" + ".xls";
			File tempFile = new File(sysTemPaht + uploadPath);
			if (!tempFile.exists()) {
				tempFile.mkdir();
			}
			
			File outFile = new File(url);
			InputStream ins = new FileInputStream(url);
			Workbook workbook = Workbook.getWorkbook(ins);
            // 利用已经创建的Excel工作薄创建新的可写入的Excel工作薄
            WritableWorkbook wwb = Workbook.createWorkbook(outFile, workbook);
			WritableSheet  dataSheet=wwb.getSheet(0);
			dataSheet.setName("干线切割管理批量录入表");
		
			for (int i = 0; i < objectArray.size(); i++) {
				Vector tmp = (Vector) objectArray.get(i);
				for (int j = 0; j < tmp.size(); j++) {			
					Label lbl = new Label(j, i, "1");               	
                    lbl.setString(String.valueOf(tmp.get(j)));
                    dataSheet.addCell(lbl);		
				}
			}
			int m = objectArray.size();
			while(dataSheet.getCell(0, m).getContents() != null
					&& !"".equals(dataSheet.getCell(0, m).getContents())){
				dataSheet.removeRow(m);
			}
			wwb.write();
            wwb.close();
            workbook.close();
			
			// 读到流中
			InputStream inStream = new FileInputStream(outFile);// 文件的存放路径
			// 设置输出的格式
			response.reset();
			response.setContentType("application/x-msdownload;charset=GBK");
			response.setCharacterEncoding("UTF-8");
			String fileName = URLEncoder.encode(outFile.getName(), "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ new String(fileName.getBytes("UTF-8"), "GBK"));

			// 循环取出流中的数据
			byte[] b = new byte[128];
			int len;
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
		return null;
	}
}