package com.boco.eoms.parter.baseinfo.contract.webapp.action;

import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.boco.eoms.parter.baseinfo.contract.mgr.TawContractMgr;
import com.boco.eoms.parter.baseinfo.contract.model.TawContract;
import com.boco.eoms.parter.baseinfo.contract.model.TawContractPay;
import com.boco.eoms.parter.baseinfo.contract.util.TawContractConstants;
import com.boco.eoms.parter.baseinfo.contract.webapp.form.TawContractForm;
import com.boco.eoms.parter.baseinfo.contract.webapp.form.TawContractPayForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

/**
 * <p>
 * Title:代维合同
 * </p>
 * <p>
 * Description:代维合同
 * </p>
 * <p>
 * Wed Apr 01 08:58:31 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() fengshaohong
 * @moudle.getVersion() 3.5
 * 
 */
public final class TawContractAction extends BaseAction {
 
	Integer size = null;
	Integer index = null;
	String backsql ="";
	String allNode="";
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
	 * 新增代维合同
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
	 * 修改代维合同
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
		TawContractMgr tawContractMgr = (TawContractMgr) getBean("tawContractMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		TawContract tawContract = tawContractMgr.getTawContract(id);
		TawContractForm tawContractForm = (TawContractForm) convert(tawContract);
		updateFormBean(mapping, request, tawContractForm);
		return mapping.findForward("edit");
	}
    public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawContractMgr tawContractMgr = (TawContractMgr) getBean("tawContractMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		TawContract tawContract = tawContractMgr.getTawContract(id);
		TawContractForm tawContractForm = (TawContractForm) convert(tawContract);
		updateFormBean(mapping, request, tawContractForm);
		return mapping.findForward("detail");
	}
	/**
	 * 保存代维合同
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
		TawContractMgr tawContractMgr = (TawContractMgr) getBean("tawContractMgr");
		TawContractForm tawContractForm = (TawContractForm) form;
		boolean isNew = (null == tawContractForm.getId() || "".equals(tawContractForm.getId()));
		TawContract tawContract = (TawContract) convert(tawContractForm);
		TawSystemSessionForm sessionForm = this.getUser(request);
		String userId = sessionForm.getUserid();
		String userName = sessionForm.getUsername();
		Date date = new Date();
		if (isNew) {
			tawContract.setCreateTime(date);
			tawContract.setCreator(userId);
			tawContract.setCreatorName(userName);
			tawContractMgr.saveTawContract(tawContract);
		} else {
			tawContractMgr.saveTawContract(tawContract);
		}
		//return search(mapping, tawContractForm, request, response);
		return mapping.findForward("success");
	}
	
	/**
	 * 删除代维合同
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
		TawContractMgr tawContractMgr = (TawContractMgr) getBean("tawContractMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		tawContractMgr.removeTawContract(id);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 分页显示代维合同列表
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
				TawContractConstants.TAWCONTRACT_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		size = pageSize;
		index = pageIndex;
		backsql = "";
		TawContractMgr tawContractMgr = (TawContractMgr) getBean("tawContractMgr");
		Map map = (Map) tawContractMgr.getTawContracts(pageIndex, pageSize, " order by createTime desc");
		List list = (List) map.get("result");
		request.setAttribute(TawContractConstants.TAWCONTRACT_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	public ActionForward xquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawContractMgr tawContractMgr = (TawContractMgr) getBean("tawContractMgr");
		TawContractForm tawContractForm = (TawContractForm) form;
		String contractname = tawContractForm.getContractName();
		String name_A = tawContractForm.getName_A();
		String name_B = tawContractForm.getName_B().trim();
		String creator = tawContractForm.getCreator().trim();
		StringBuffer sql = new StringBuffer();
		String isCity = "no";
		if(!"".equals(contractname)&&contractname!=null){
			sql.append(" and contractname like '%"+contractname+"%'");
		}if(!"".equals(name_A)&&name_A!=null){
			sql.append(" and name_A like '%"+name_A+"%'");
		}if(!"".equals(name_B)&&name_B!=null){
			sql.append(" and name_B like '%"+name_B+"%'");
			isCity="yes";
		}if(!"".equals(creator)&&creator!=null){
			sql.append(" and creatorName like '%"+creator+"%'");
		}
		sql.append(" order by createTime desc");
		backsql = sql.toString();
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				TawContractConstants.TAWCONTRACT_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		size = pageSize;
		index = pageIndex;
		Map map = (Map) tawContractMgr.getTawContracts(pageIndex, pageSize, sql.toString());
		List list = (List) map.get("result");
//		vvvvvvvvvvvvvvvv
		updateFormBean(mapping, request, tawContractForm);
		//vvvvvvvvvvvv
		request.setAttribute(TawContractConstants.TAWCONTRACT_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("isCity", isCity);
		return mapping.findForward("list");
	}
	public ActionForward backToSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawContractMgr tawContractMgr = (TawContractMgr) getBean("tawContractMgr");
		Map map = (Map) tawContractMgr.getTawContracts(index, size, backsql);
		List list = (List) map.get("result");
		request.setAttribute(TawContractConstants.TAWCONTRACT_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", size);
		return mapping.findForward("list");
	}
	public ActionForward toXls(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("problemRow", "");
		return mapping.findForward("xlsInput");
	}
	public ActionForward xlsSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		TawContractMgr mgr = (TawContractMgr) getBean("tawContractMgr");
		// 首先将文件从客户端上传到服务器
		String timeTag = StaticMethod.getCurrentDateTime("yyyy_MM_dd_HHmmss");
		String sysTemPaht = request.getRealPath("/");
		TawContractForm tawContractForm = (TawContractForm) form;
		String uploadPath = "/WEB-INF/pages/tawContract/upfiles";
		String filePath = sysTemPaht + uploadPath + "/" + timeTag + ".xls";
		File tempFile = new File(sysTemPaht + uploadPath);
		if (!tempFile.exists()) {
			tempFile.mkdir();
		}
		FormFile file = tawContractForm.getExcelInput();
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
				TawContract temp = new TawContract();
				if (dataSheet.getCell(0, i).getContents() != null
						&& !"".equals(dataSheet.getCell(0, i).getContents())){
					if(mgr.isunique(dataSheet.getCell(0, i).getContents()).booleanValue())
					{
					temp.setContractName(dataSheet.getCell(0, i).getContents()
							.trim());
					}else{
						numberList.add(new Integer(i+1));
						numberList.add(new Integer(1));
						continue;
					}
				}else{
					break;
				}
				if (dataSheet.getCell(1, i).getContents() != null
						&& !"".equals(dataSheet.getCell(1, i).getContents())){
					temp.setName_A(dataSheet.getCell(1, i).getContents().trim());
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(2));
					continue;
				}
				if (dataSheet.getCell(2, i).getContents() != null
						&& !"".equals(dataSheet.getCell(2, i).getContents())){
					temp.setName_B(dataSheet.getCell(2, i).getContents().trim());
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(3));
					continue;
				}
				if (dataSheet.getCell(3, i).getContents() != null
						&& !"".equals(dataSheet.getCell(3, i).getContents())){
					temp.setMaintenanceRange(dataSheet.getCell(3, i).getContents().trim());
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(4));
					continue;
				}
				if (dataSheet.getCell(4, i).getContents() != null
						&& !"".equals(dataSheet.getCell(4, i).getContents())){
					temp.setRight_A(dataSheet.getCell(4, i).getContents().trim());
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(5));
					continue;
				}
				if (dataSheet.getCell(5, i).getContents() != null
						&& !"".equals(dataSheet.getCell(5, i).getContents())){
					temp.setRight_B(dataSheet.getCell(5, i).getContents().trim());
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(6));
					continue;
				}
				if (dataSheet.getCell(6, i).getContents() != null
						&& !"".equals(dataSheet.getCell(6, i).getContents())){
					temp.setInterface_A(dataSheet.getCell(6, i).getContents().trim());
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(7));
					continue;
				}
				if (dataSheet.getCell(7, i).getContents() != null
						&& !"".equals(dataSheet.getCell(7, i).getContents())){
					temp.setInterface_B(dataSheet.getCell(7, i).getContents().trim());
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(8));
					continue;
				}
				if (dataSheet.getCell(8, i).getContents() != null
						&& !"".equals(dataSheet.getCell(8, i).getContents())){
					temp.setQualityCheck(dataSheet.getCell(8, i).getContents().trim());
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(9));
					continue;
				}
				if (dataSheet.getCell(9, i).getContents() != null
						&& !"".equals(dataSheet.getCell(9, i).getContents())){
					temp.setQualityChangeDeal(dataSheet.getCell(9, i).getContents().trim());
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(10));
					continue;
				}
				if (dataSheet.getCell(10, i).getContents() != null
						&& !"".equals(dataSheet.getCell(10, i).getContents())){
					temp.setBeyond(dataSheet.getCell(10, i).getContents().trim());
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(11));
					continue;
				}
				if (dataSheet.getCell(11, i).getContents() != null
						&& !"".equals(dataSheet.getCell(11, i).getContents())){
					temp.setSecret(dataSheet.getCell(11, i).getContents().trim());
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(12));
					continue;
				}
				if (dataSheet.getCell(12, i).getContents() != null
						&& !"".equals(dataSheet.getCell(12, i).getContents())){
					temp.setStopCondition(dataSheet.getCell(12, i).getContents().trim());
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(13));
					continue;
				}
				if (dataSheet.getCell(13, i).getContents() != null
						&& !"".equals(dataSheet.getCell(13, i).getContents())){
					temp.setBreachFaith(dataSheet.getCell(13, i).getContents().trim());
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(14));
					continue;
				}
				if (dataSheet.getCell(14, i).getContents() != null
						&& !"".equals(dataSheet.getCell(14, i).getContents())){
					temp.setSolve(dataSheet.getCell(14, i).getContents().trim());
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(15));
					continue;
				}
				if (dataSheet.getCell(15, i).getContents() != null
						&& !"".equals(dataSheet.getCell(15, i).getContents())){
					temp.setAdd_ons(dataSheet.getCell(15, i).getContents().trim());
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(16));
					continue;
				}
				if (dataSheet.getCell(16, i).getContents() != null
						&& !"".equals(dataSheet.getCell(16, i).getContents())){
					temp.setContact_A(dataSheet.getCell(16, i).getContents().trim());
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(17));
					continue;
				}
				if (dataSheet.getCell(17, i).getContents() != null
						&& !"".equals(dataSheet.getCell(17, i).getContents())){
					temp.setContact_B(dataSheet.getCell(17, i).getContents().trim());
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(18));
					continue;
				}
				if (dataSheet.getCell(18, i).getContents() != null
						&& !"".equals(dataSheet.getCell(18, i).getContents())){
					temp.setCost(dataSheet.getCell(18, i).getContents().trim());
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(19));
					continue;
				}
				if (dataSheet.getCell(19, i).getContents() != null
						&& !"".equals(dataSheet.getCell(19, i).getContents())){
					temp.setPayType(dataSheet.getCell(19, i).getContents().trim());
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(20));
					continue;
				}
				if (dataSheet.getCell(20, i).getContents() != null
						&& !"".equals(dataSheet.getCell(20, i).getContents())){
					temp.setPayCycle(dataSheet.getCell(20, i).getContents().trim());
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(21));
					continue;
				}
				if (dataSheet.getCell(21, i).getContents() != null
						&& !"".equals(dataSheet.getCell(21, i).getContents())){
					temp.setTimeLimit(dataSheet.getCell(21, i).getContents().trim());
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(22));
					continue;
				}
				if (dataSheet.getCell(22, i).getContents() != null
						&& !"".equals(dataSheet.getCell(22, i).getContents())){
					temp.setPayed(dataSheet.getCell(22, i).getContents().trim());
				}else{
					numberList.add(new Integer(i+1));
					numberList.add(new Integer(23));
					continue;
				}
				TawSystemSessionForm sessionForm = this.getUser(request);
				String addMan = sessionForm.getUserid();
				String creatorName = sessionForm.getUsername();
				Date addTime = new Date();
				
				temp.setCreator(addMan);
				temp.setCreateTime(addTime);
				temp.setCreatorName(creatorName);
				formList.add(temp);
			}
			for(int i = 0;i<formList.size();i++){
				mgr.saveTawContract((TawContract) formList.get(i));
			}
			String problemRow ="";
			for(int i=0;i<numberList.size();i++){
				problemRow +=","+numberList.get(i);
			}
			String str ="";
			if(!"".equals(problemRow)){
			 String sub = problemRow.substring(1, problemRow.length());
			 String []array = sub.split(",");
			   str = "成功录入！以下为不合法的未录入的信息："+"";
			  for(int i=0;i<array.length;i++){
			  
			  str = str+"第"+array[i]+"行"+"第"+array[i+1]+"列"+"";
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
	public ActionForward outPutModel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		
		try{
			String sysTemPath = request.getRealPath("");
			String url = sysTemPath + "/WEB-INF/pages/tawContract/upfiles/contract.xls";
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
	 * 代维合同付款管理 xyb
	 */

	/**
	 * 付费信息页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward payinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List list = new ArrayList();
		Map map = new HashMap();
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				TawContractConstants.TAWCONTRACT_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		size = pageSize;
		index = pageIndex;
		backsql = "";
		// String contractid = (String) request.getAttribute("id");
		TawContractMgr tawContractPayMgr = (TawContractMgr) getBean("tawContractMgr");
		TawContractPayForm tawContractpayForm = (TawContractPayForm) form;
		String contractid = tawContractpayForm.getId();//form中id是合同id
		//通过合同id得到合同
		TawContract tawContract = tawContractPayMgr.getTawContract(contractid);
		tawContractpayForm.setContractname(tawContract.getContractName());
		tawContractpayForm.setId(null);
		tawContractpayForm.setContractid(contractid);
		//如果此合同有付款信息直接取得，如果没有则new出一个
		if (null == tawContractPayMgr.getTawContractPayByContractid(contractid)) {
			TawContractPay tawContractPay = new TawContractPay();
			tawContractPay.setContractid(contractid);
			tawContractPayMgr.saveTawContractPay(tawContractPay);
		}
		
		map = (Map) tawContractPayMgr.getTawContractPays(pageIndex, pageSize,
				"where contractid = '" + contractid + "'");
		list = (List) map.get("result");
		/*tawContractpayForm.setId(((TawContractPay) tawContractPayMgr
				.getTawContractPayByContractid(contractid).get(0)).getId());*/
		request.setAttribute("payinfoList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		/*tawContractpayForm.setPayinfoList(list);
		request.setAttribute("payinfoList", list);*/
		return mapping.findForward("payinfo");
	}

	/**
	 * tawcontractpay树页面
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
		return mapping.findForward("tree");
	}

	/**
	 * 生成tawcontractpay树JSON数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 * public String getNodes(ActionMapping mapping, ActionForm form,
	 * HttpServletRequest request, HttpServletResponse response) throws
	 * Exception { String nodeId =
	 * StaticMethod.null2String(request.getParameter("node")); JSONArray
	 * jsonRoot = new JSONArray(); TawContractMgr tawContractPayMgr =
	 * (TawContractMgr) getBean("tawContractPayMgr"); // 取下级节点 List list =
	 * tawContractPayMgr.getNextLevelTawContractPays(nodeId); for (Iterator
	 * nodeIter = list.iterator(); nodeIter.hasNext();) { TawContractPay
	 * tawContractPay = (TawContractPay) nodeIter.next(); JSONObject jitem = new
	 * JSONObject(); jitem.put("id", tawContractPay.getNodeId()); // TODO 添加节点名称
	 * //jitem.put("text", your node name here); // 设置右键菜单
	 * jitem.put("allowChild", true); jitem.put("allowEdit", true);
	 * jitem.put("allowDelete", true); // 设置左键点击可触发action
	 * jitem.put("allowClick", true); // 设置是否为叶子节点 boolean leafFlag = true; if
	 * (tawContractPayMgr.isHasNextLevel(tawContractPay.getNodeId())) { leafFlag =
	 * false; } jitem.put("leaf", leafFlag); // TODO 设置鼠标悬浮提示
	 * //jitem.put("qtip", your tips here); jsonRoot.put(jitem); }
	 * JSONUtil.print(response, jsonRoot.toString()); return null; }
	 */

	/**
	 * 新增tawcontractpay
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward payadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("edit");
	}

	/**
	 * 修改tawcontractpay
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward payedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/*
		 * String nodeId =
		 * StaticMethod.null2String(request.getParameter("node"));
		 * TawContractMgr tawContractPayMgr = (TawContractMgr)
		 * getBean("tawContractPayMgr"); TawContractPay tawContractPay =
		 * tawContractPayMgr.getTawContractPayByNodeId(nodeId);
		 * TawContractPayForm tawContractPayForm = (TawContractPayForm)
		 * convert(tawContractPay); updateFormBean(mapping, request,
		 * tawContractPayForm);
		 */
		return mapping.findForward("edit");
	}

	/**
	 * 保存tawcontractpay
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward paysave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		TawContractMgr tawContractPayMgr = (TawContractMgr) getBean("tawContractMgr");
		TawContractPayForm tawContractPayForm = (TawContractPayForm) form;
		TawContractPay tawContractPay = new TawContractPay();
		tawContractPay.setContractid(tawContractPayForm.getContractid());
		tawContractPay.setContractname(tawContractPayForm.getContractname());
		tawContractPay.setPayer(tawContractPayForm.getPayer());
		tawContractPay.setOperator(sessionform.getUserid());
		tawContractPay.setPaymethod(tawContractPayForm.getPaymethod());
		tawContractPay.setPayaccount(tawContractPayForm.getPayaccount());
		String money = tawContractPayForm.getMoney();
		tawContractPay.setMoney(money);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		tawContractPay
				.setPaytime(format.parse(tawContractPayForm.getPaytime()));
		tawContractPay.setRemark(tawContractPayForm.getRemark());
		tawContractPay.setAccessory(tawContractPayForm.getAccessory());
		tawContractPayMgr.saveTawContractPay(tawContractPay);
		return mapping.findForward("success");
	}

	/**
	 * 获取付款信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 
	public ActionForward getPayinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List list = new ArrayList();
		String contractid = (String) request.getAttribute("id");
		TawContractMgr tawContractPayMgr = (TawContractMgr) getBean("tawContractMgr");
		TawContractPayForm tawContractpayForm = (TawContractPayForm) form;
		// String contractid = tawContractpayForm.getId();
		tawContractpayForm.setContractid(contractid);
		// 如果还没有付款信息就新建一个，如果已经有了就取出原有的
		if (null != tawContractPayMgr.getTawContractPayByContractid(contractid)) {
			list = tawContractPayMgr.getTawContractPayByContractid(contractid);
		} else {
			TawContractPay tawContractPay = new TawContractPay();
			tawContractPay.setContractid(contractid);
			tawContractPayMgr.saveTawContractPay(tawContractPay);
			list.add(tawContractPay);
			tawContractpayForm.setId(((TawContractPay) tawContractPayMgr
					.getTawContractPayByContractid(contractid).get(0)).getId());
		}

		tawContractpayForm.setPayinfoList(list);
		request.setAttribute("payinfoList", list);
		return mapping.findForward("payinfo");
	}*/

}