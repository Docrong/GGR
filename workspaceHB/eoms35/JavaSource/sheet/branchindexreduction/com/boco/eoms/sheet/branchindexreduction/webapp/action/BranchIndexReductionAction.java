package com.boco.eoms.sheet.branchindexreduction.webapp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.common.resource.ExcelUtil;
import com.boco.eoms.commons.accessories.util.AccessoriesMgrLocator;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.branchindexreduction.model.SubtractTable;
import com.boco.eoms.sheet.branchindexreduction.service.ISubtractTableMgr;


/**
 * <p>
 * Title:分公司指标核减流程
 * </p>
 * <p>
 * Description:分公司指标核减流程
 * </p>
 * <p>
 * Fri Jul 28 15:47:20 CST 2017
 * </p>
 * 
 * @author wangmingming
 * @version 1.0
 * 
 */

public class BranchIndexReductionAction extends SheetAction {

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
	public ActionForward showDrawing(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
	public ActionForward showPic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
	public ActionForward showKPI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("kpi");
	}

	/**
	 * 查询核减内容单表 search 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param
	 * @return 
	 * @throws Exception
	 */
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 定义一个refSheetId 关联id
		String refSheetId = StaticMethod.nullObject2String(request
				.getParameter("refSheetId"));
		String reason = StaticMethod.nullObject2String(request
				.getParameter("reason"));
		String ifShowOther = StaticMethod.nullObject2String(request
				.getParameter("ifShowOther"));
		String ifSeeHe = StaticMethod.nullObject2String(request
				.getParameter("ifSeeHe")); // 获取页面   核减状态
		String ifSend = StaticMethod.nullObject2String(request
				.getParameter("ifSend")); // 获取显示页面  重派页面 的两列
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();
		// 当前页数
		String pageIndexName = new org.displaytag.util.ParamEncoder("datasList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

		ISubtractTableMgr subtractTableMgr = (ISubtractTableMgr) getBean("subtractTableMgr");

		// 分页查询
		StringBuilder condition = new StringBuilder(" where 1 = 1 ");
		if (!"".equals(refSheetId)) {
			condition.append(" and reserveB = '" + refSheetId + "'AND serialid NOT IN (SELECT h.sheetid FROM commonfaulthj h WHERE decode(h.subtracttype,'101032001','101410301','101032002','101410302','101032003','101410303') =mainsubtractindexname)"); 
		}
		if (!"".equals(reason)) {
			condition.append(" and reserveC= '" + reason + "'");
		}
		System.out.println("@@@@@@@@@@@@pageIndex="+pageIndex);
		System.out.println("@@@@@@@@@@@@pageSize="+pageSize);
		List result = subtractTableMgr.getSubtractTablesByCondition(
				condition.toString());
		Integer total = Integer.valueOf(0);
		List datasList = new ArrayList();
		if (result != null && result.size() > 0) {
			total = new Integer(result.size());
			datasList = (List) result;
		}
		/*为否的条数*/
		int noPass = 0;
		SubtractTable table = null;
		for(int i=0 ;i < datasList.size(); i++){
			table = (SubtractTable) datasList.get(i);
			if("1030102".equals(StaticMethod.nullObject2String(table.getReserveC()))){
				noPass ++;
			}
		}
		request.setAttribute("noPass", noPass+"");
		request.setAttribute("ifShowOther", ifShowOther);
		request.setAttribute("ifSeeHe", ifSeeHe);
		request.setAttribute("ifSend", ifSend);
		request.setAttribute("pageSize", total);
		request.setAttribute("total", total);
		request.setAttribute("datasList", datasList);
		return mapping.findForward("subtractTableSearch");
	}

	/**
	 * 工单基本详情列表数据导出 2007版.xlsx
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */

	public void exportList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String refSheetId = StaticMethod.nullObject2String(request
				.getParameter("refSheetId"));     // main与单表关联外键

		ISubtractTableMgr subtractTableMgr = (ISubtractTableMgr) ApplicationContextHolder
				.getInstance().getBean("subtractTableMgr");
		String condition = " where reserveB ='" + refSheetId + "' and reserveC ='1030102'"; 
		List queryList = subtractTableMgr
				.getSubtractTablesByCondition(condition);
		if (queryList != null && queryList.size() > 0) {
			OutputStream output = null;
			InputStream inStream = null;
			try {
				String rootFilePath = AccessoriesMgrLocator
					.getTawCommonsAccessoriesManagerCOS().getFilePath("branchindexreduction");
				String fileName = StaticMethod.getCurrentDateTime("yyyyMMddHHmmss") + ".xlsx";
				File file = null;
				file = new File(rootFilePath);
				if (!file.exists()) {
					file.mkdirs();  
				}
				file = new File(rootFilePath,fileName);
				output = new FileOutputStream(file);
				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet sheet = null;
				XSSFRow row = null;
				XSSFCell cell = null;
				sheet = wb.createSheet();
				row = sheet.createRow(0);
				/*写表数据*/ 
				String[] titles = {"核减内容","核减理由是否成立","核减说明","工单流水号","工单主题","派单时间","故障分类","故障历时(清除时间-发生时间)","处理历时（恢复时间-第一次转单时间）","故障发生时间",
						"告警平台清除时间","最终处理措施（T1，T2）","T2接单时间","T2处理时限","T2最终处理时间","最终处理地市","最终处理班组","最终处理区县","综合代维区县","最终处理班组属性"};
				for(int i = 0; i < titles.length; i++){
					cell = row.createCell(i);
					cell.setCellValue(titles[i]);
				}
				ID2NameService id2NameService = (ID2NameService) ApplicationContextHolder
					.getInstance().getBean("id2nameService");
				for(int i = 0; queryList != null && i<queryList.size(); i++){
					SubtractTable subtractTable = (SubtractTable)queryList.get(i); // 转化
					row = sheet.createRow(i + 1);
					cell = row.createCell(i + 1);
					row.createCell(0).setCellValue(id2NameService.id2Name(StaticMethod.null2String(subtractTable.getSubtractProfessional()), "ItawSystemDictTypeDao"));
					row.createCell(1).setCellValue(id2NameService.id2Name(StaticMethod.null2String(subtractTable.getSubtractrCategory()),"ItawSystemDictTypeDao"));
					row.createCell(2).setCellValue(StaticMethod.null2String(subtractTable.getApplicationReduction()));
					row.createCell(3).setCellValue(StaticMethod.null2String(subtractTable.getSerialId()));
					row.createCell(4).setCellValue(StaticMethod.null2String(subtractTable.getTitle()));
					
					row.createCell(5).setCellValue((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(subtractTable.getMainDispatchTime()));
					row.createCell(6).setCellValue(StaticMethod.null2String(subtractTable.getMainFaultClass()));
					row.createCell(7).setCellValue(StaticMethod.null2String(subtractTable.getMainFaultDurat()));
					row.createCell(8).setCellValue(StaticMethod.null2String(subtractTable.getMainProDurat()));
					row.createCell(9).setCellValue((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(subtractTable.getMainFaultFaTime()));
					
					if(subtractTable.getMainFaultQiTime()!=null)
						row.createCell(10).setCellValue((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(subtractTable.getMainFaultQiTime()));
					else
						row.createCell(10).setCellValue("");
					if(subtractTable.getMainFinalMeasures()!=null)
						row.createCell(11).setCellValue(StaticMethod.null2String(subtractTable.getMainFinalMeasures()));
					else
						row.createCell(11).setCellValue("");
					if(subtractTable.getMainT2Time()!=null)
						row.createCell(12).setCellValue((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(subtractTable.getMainT2Time()));
					else
						row.createCell(12).setCellValue("");	
					if(subtractTable.getMainT2Limit()!=null)
						row.createCell(13).setCellValue((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(subtractTable.getMainT2Limit()));
					else
						row.createCell(13).setCellValue("");
					if(subtractTable.getMainT2FinalTime()!=null)
						row.createCell(14).setCellValue((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(subtractTable.getMainT2FinalTime()));
					else
						row.createCell(14).setCellValue("");
					row.createCell(15).setCellValue(StaticMethod.null2String(subtractTable.getMainFinalCtiy()));
					row.createCell(16).setCellValue(StaticMethod.null2String(subtractTable.getMainFinalTeam()));
					row.createCell(17).setCellValue(StaticMethod.null2String(subtractTable.getMainFinalCounty()));
					row.createCell(18).setCellValue(StaticMethod.null2String(subtractTable.getMainDaiCounty()));
					row.createCell(19).setCellValue(StaticMethod.null2String(subtractTable.getMainFinalAttributes()));
					
				}
 				output.flush();
				wb.write(output);
				/*导出客户端-下载*/
				String excelFileName = "1212.xlsx";
				inStream = new FileInputStream(file);
				response.reset();
				response.setContentType("application/x-msdownload");
				response.setCharacterEncoding("GB2312");
				response.addHeader("Content-Disposition", "attachment;filename="
					+ excelFileName);
				// 循环取出流中的数据
				byte[] b = new byte[128]; 
				int len;
				while ((len = inStream.read(b)) > 0)
					response.getOutputStream().write(b, 0, len);
				inStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(output != null){
					output.close();
				}
				if(inStream != null){
					inStream.close();
				}
			}
		}
	}
	
	/**
	 * 初审环节  add by wmm 20180202
	 * 申请核减的工单列表数据导出 2007版.xlsx
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */

	public void exportListAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String refSheetId = StaticMethod.nullObject2String(request
				.getParameter("refSheetId"));     // main与单表关联外键
		ISubtractTableMgr subtractTableMgr = (ISubtractTableMgr) ApplicationContextHolder
				.getInstance().getBean("subtractTableMgr");
		String condition = " where reserveB ='" + refSheetId + "'"; 
		List queryList = subtractTableMgr
				.getSubtractTablesByCondition(condition);
		if (queryList != null && queryList.size() > 0) {
			OutputStream output = null;
			InputStream inStream = null;
			try {
				String rootFilePath = AccessoriesMgrLocator
					.getTawCommonsAccessoriesManagerCOS().getFilePath("branchindexreduction");
				String fileName = StaticMethod.getCurrentDateTime("yyyyMMddHHmmss")+UUIDHexGenerator.getInstance().getID() + ".xlsx";
				File file = null;
				file = new File(rootFilePath);
				if (!file.exists()) {
					file.mkdirs();  
				}
				file = new File(rootFilePath,fileName);
				output = new FileOutputStream(file);
				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet sheet = null;
				XSSFRow row = null;
				XSSFCell cell = null;
				
				//新建sheet
				sheet = wb.createSheet();
				row = sheet.createRow(0);//第一个sheet的第一行为标题
				/*写标题数据*/ 
				String[] titles = {"核减理由是否成立","说明","核减内容","核减理由类别","核减说明","工单流水号","工单主题","派单时间","故障分类","故障历时(清除时间-发生时间)","处理历时（恢复时间-第一次转单时间）","故障发生时间",
						"告警平台清除时间","最终处理措施（T1，T2）","T2接单时间","T2处理时限","T2最终处理时间","最终处理地市","最终处理班组","最终处理区县","综合代维区县","最终处理班组属性"};
				for(int i = 0; i < titles.length; i++){
					cell = row.createCell(i);
					cell.setCellValue(titles[i]);// 循环标题列数据
				}
				
				ID2NameService id2NameService = (ID2NameService) ApplicationContextHolder
					.getInstance().getBean("id2nameService");
				for(int i = 0; queryList != null && i<queryList.size(); i++){
					SubtractTable subtractTable = (SubtractTable)queryList.get(i); // 转化
					row = sheet.createRow(i + 1);
					cell = row.createCell(i + 1);
					
					if (subtractTable.getReserveC() == null)
					{
						List downData = new ArrayList();
						String str1[] = {
							"是", "否"
						};
						downData.add(str1);
						String downRows[] = {
							"0"
						};
						for (int r = 0; r < downRows.length; r++)
						{
							String dlData[] = (String[])downData.get(r);
							int rownum = Integer.parseInt(downRows[r]);
							System.out.println("-------------------downRows[r]-----------" + downRows[r]);
							System.out.println("-------------------dlData.length-----------" + dlData.length);
							if (dlData.length < 5)
								sheet.addValidationData(setDataValidation(sheet, dlData, 1, 5000, rownum, rownum));
						}

						row.createCell(0).setCellValue("请选择");
					}

					row.createCell(1).setCellValue(StaticMethod.null2String(subtractTable.getReserveD()));
					
					row.createCell(2).setCellValue(id2NameService.id2Name(StaticMethod.null2String(subtractTable.getSubtractProfessional()), "ItawSystemDictTypeDao"));
					row.createCell(3).setCellValue(id2NameService.id2Name(StaticMethod.null2String(subtractTable.getSubtractrCategory()),"ItawSystemDictTypeDao"));
					row.createCell(4).setCellValue(StaticMethod.null2String(subtractTable.getApplicationReduction()));
					row.createCell(5).setCellValue(StaticMethod.null2String(subtractTable.getSerialId()));
					row.createCell(6).setCellValue(StaticMethod.null2String(subtractTable.getTitle()));
					
					if(subtractTable.getMainDispatchTime()!=null)
						row.createCell(7).setCellValue((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(subtractTable.getMainDispatchTime()));
					else 
						row.createCell(7).setCellValue("");
					
					row.createCell(8).setCellValue(StaticMethod.null2String(subtractTable.getMainFaultClass()));
					row.createCell(9).setCellValue(StaticMethod.null2String(subtractTable.getMainFaultDurat()));
					row.createCell(10).setCellValue(StaticMethod.null2String(subtractTable.getMainProDurat()));
					
					if(subtractTable.getMainFaultFaTime()!=null)
						row.createCell(11).setCellValue((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(subtractTable.getMainFaultFaTime()));
					else
						row.createCell(11).setCellValue("");
					
					if(subtractTable.getMainFaultQiTime()!=null)
						row.createCell(12).setCellValue((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(subtractTable.getMainFaultQiTime()));
					else
						row.createCell(12).setCellValue("");
					
					row.createCell(13).setCellValue(StaticMethod.null2String(subtractTable.getMainFinalMeasures()));
					
					if(subtractTable.getMainT2Time()!=null)
						row.createCell(14).setCellValue((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(subtractTable.getMainT2Time()));
					else
						row.createCell(14).setCellValue("");
					
					if(subtractTable.getMainT2Limit()!=null)
						row.createCell(15).setCellValue((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(subtractTable.getMainT2Limit()));
					else
						row.createCell(15).setCellValue("");
					
					if(subtractTable.getMainT2FinalTime()!=null)
						row.createCell(16).setCellValue((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(subtractTable.getMainT2FinalTime()));
					else
						row.createCell(16).setCellValue("");
					
					row.createCell(17).setCellValue(StaticMethod.null2String(subtractTable.getMainFinalCtiy()));
					row.createCell(18).setCellValue(StaticMethod.null2String(subtractTable.getMainFinalTeam()));
					row.createCell(19).setCellValue(StaticMethod.null2String(subtractTable.getMainFinalCounty()));
					row.createCell(20).setCellValue(StaticMethod.null2String(subtractTable.getMainDaiCounty()));
					row.createCell(21).setCellValue(StaticMethod.null2String(subtractTable.getMainFinalAttributes()));
					
			 
				}
 				output.flush();
				wb.write(output);
				/*导出客户端-下载*/
				String excelFileName = "20180129.xlsx";
				inStream = new FileInputStream(file);
				response.reset();
				response.setContentType("application/x-msdownload");
				response.setCharacterEncoding("GB2312");
				response.addHeader("Content-Disposition", "attachment;filename="
					+ excelFileName);
				// 循环取出流中的数据
				byte[] b = new byte[128]; 
				int len;
				while ((len = inStream.read(b)) > 0)
					response.getOutputStream().write(b, 0, len);
				inStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(output != null){
					output.close();
				}
				if(inStream != null){
					inStream.close();
				}
			}
		}
	}
	
	 /**
     * 
     * @Title: setDataValidation 
     * @Description: 下拉列表元素不多的情况(255以内的下拉)
     * @param @param sheet
     * @param @param textList
     * @param @param firstRow
     * @param @param endRow
     * @param @param firstCol
     * @param @param endCol
     * @param @return
     * @return DataValidation
     * @throws
     */
    private static DataValidation setDataValidation(Sheet sheet, String[] textList, int firstRow, int endRow, int firstCol, int endCol) {

        DataValidationHelper helper = sheet.getDataValidationHelper();
        //加载下拉列表内容
        DataValidationConstraint constraint = helper.createExplicitListConstraint(textList);
        //DVConstraint constraint = new DVConstraint();
        constraint.setExplicitListValues(textList);
        
        //设置数据有效性加载在哪个单元格上。四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList((short) firstRow, (short) endRow, (short) firstCol, (short) endCol);
    
        //数据有效性对象
        DataValidation data_validation = helper.createValidation(constraint, regions);
        //DataValidation data_validation = new DataValidation(regions, constraint);
    
        return data_validation;
    }

	
	/* 获取子角色 public
	 * @param roleId
	 * @param areaId
	 * @param sheetId
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSubRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String subRoleId = ""; 
		String subRoleName= "";
		String type= "subrole";
		String mainSubtractProfessional = StaticMethod.nullObject2String(request.getParameter("mainSubtractProfessional"));
		//String getsubrole = StaticMethod.nullObject2String(XmlManage.getFile("/config/nwcomplaint-util.xml").getProperty("getsubrole"));
		IDownLoadSheetAccessoriesService sqlMgr=(IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
//		限制传入的字典值长度和核减理由长度字典值长度一致，避免短于核减理由长度字典值长度的字典值被匹配到
		System.out.println("lyg=="+mainSubtractProfessional);
		System.out.println("mainSubtractProfessional.length()=="+mainSubtractProfessional.length());
		if(mainSubtractProfessional==null||mainSubtractProfessional.length()!=11)
		{
			mainSubtractProfessional="00000000000";
		}
		List roleList=sqlMgr.getSheetAccessoriesList(" select id,SUBROLENAME from taw_system_sub_role where roleid='99502' and type1 like '%"+mainSubtractProfessional+"%' ");
		String result= "";
		Map roleMap = new HashMap();
		if(roleList!=null && 0<roleList.size()){
			roleMap = (Map) roleList.get(0);
			subRoleId=StaticMethod.nullObject2String(roleMap.get("id"));
			subRoleName=StaticMethod.nullObject2String(roleMap.get("SUBROLENAME"));
			result ="{subRoleId:'"+subRoleId+"',subRoleName:'"+subRoleName+"',type:'"+type+"'}";
		}else{
			result ="{subRoleId:'"+subRoleId+"',subRoleName:'未匹配到对应角色请联系管理员分配角色!!',type:'"+type+"'}";
		}
		System.out.println("lyg=="+result);
		JSONUtil.print(response, result);
		return null;
	}
	
	
	/* 获取子角色 public
	 * @param roleId
	 * @param areaId
	 * @param sheetId
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSubRoleId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String subRoleId = ""; 
		String subRoleName= "";
		String type= "subrole";
		
		String mainSubtractReason = StaticMethod.nullObject2String(request.getParameter("mainSubtractReason"));
		
		IDownLoadSheetAccessoriesService sqlMgr=(IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
		List roleList=sqlMgr.getSheetAccessoriesList(" SELECT t.id,t.subrolename FROM indexReduction_subrole i,taw_system_sub_role t WHERE i.subroleid = t.id  AND dictid = '"+mainSubtractReason+"' ");
		String result= "";
		Map roleMap = new HashMap();
		if(roleList!=null && 0<roleList.size()){
			roleMap = (Map) roleList.get(0);
			subRoleId=StaticMethod.nullObject2String(roleMap.get("id"));
			subRoleName=StaticMethod.nullObject2String(roleMap.get("SUBROLENAME"));
			result ="{subRoleId:'"+subRoleId+"',subRoleName:'"+subRoleName+"',type:'"+type+"'}";
		}else{
			result ="{subRoleId:'"+subRoleId+"',subRoleName:'未匹配到对应角色请联系管理员分配角色!!',type:'"+type+"'}";
		}
		System.out.println("lyg1=="+result);
		JSONUtil.print(response, result);
		return null;
	}
	
	public ActionForward showDraftPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		IDownLoadSheetAccessoriesService sqlMgr=(IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
		String sql="select areaid keyId,areaname keyName from taw_system_area where parentareaid='18' AND areaid NOT IN ('1817','1818') order by areaid";
		List cityList = sqlMgr.getSheetAccessoriesList(sql);
		request.setAttribute("cityList", cityList);
		return super.showDraftPage(mapping, form, request, response);
	}
}