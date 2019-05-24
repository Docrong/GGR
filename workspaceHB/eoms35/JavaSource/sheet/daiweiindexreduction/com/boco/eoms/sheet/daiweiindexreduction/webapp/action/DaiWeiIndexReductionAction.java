package com.boco.eoms.sheet.daiweiindexreduction.webapp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.accessories.util.AccessoriesMgrLocator;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.daiweiindexreduction.job.DateIfEqualShow;
import com.boco.eoms.sheet.daiweiindexreduction.model.DaiSubtractTable;
import com.boco.eoms.sheet.daiweiindexreduction.service.IDaiSubtractTableMgr;


/**
 * <p>
 * Title:代维公司指标核减流程
 * </p>
 * <p>
 * Description:代维公司指标核减流程
 * </p>
 * <p>
 * Tue Aug 01 17:34:54 CST 2017
 * </p>
 * 
 * @author wangmingming
 * @version 1.0
 * 
 */
 
 public class DaiWeiIndexReductionAction extends SheetAction  {
 	
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
				.getParameter("reason")); // 获取核减理由是否成立  是或否
		String ifShowOther = StaticMethod.nullObject2String(request
				.getParameter("ifShowOther")); // 获取显示页面  新建或重派页面
		String ifSeeHe = StaticMethod.nullObject2String(request
				.getParameter("ifSeeHe")); // 获取页面   核减状态
		String ifSend = StaticMethod.nullObject2String(request
				.getParameter("ifSend")); // 获取显示页面  重派页面 的两列
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();  // 获取分页
		// 当前页数
		String pageIndexName = new org.displaytag.util.ParamEncoder("datasList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

		IDaiSubtractTableMgr subtractTableMgr = (IDaiSubtractTableMgr) getBean("daiSubtractTableMgr"); // 调用类service

		// 分页查询
		StringBuilder condition = new StringBuilder(" where 1 = 1 ");
		if (!"".equals(refSheetId)) { // 单表增加一个main表的关联外键 id
			condition.append(" and reserveB = '" + refSheetId + "'AND serialid NOT IN (SELECT h.sheetid FROM dwcommonfaulthj h WHERE h.subtracttype='101032001')");
		}
		if (!"".equals(reason)) { // 单表里增加reserveC字段  是或否
			condition.append(" and reserveC= '" + reason + "'");
		}
		List result = subtractTableMgr.getDaiSubtractTablesByCondition(
				condition.toString());
		Integer total = Integer.valueOf(0);
		List datasList = new ArrayList();
		if (result != null && result.size() > 0) {
			total = new Integer(result.size());
			datasList = (List) result;
		}
		/*为否的条数*/
		int noPass = 0;
		DaiSubtractTable table = null;
		for(int i=0 ;i < datasList.size(); i++){ // 遍历datasList 
			table = (DaiSubtractTable) datasList.get(i);
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
		return mapping.findForward("daiSubtractTableSearch");
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

		IDaiSubtractTableMgr subtractTableMgr = (IDaiSubtractTableMgr) ApplicationContextHolder
				.getInstance().getBean("daiSubtractTableMgr");
		String condition = " where reserveB ='" + refSheetId + "' and reserveC ='1030102'"; 
		List queryList = subtractTableMgr
				.getDaiSubtractTablesByCondition(condition);
		if (queryList != null && queryList.size() > 0) {
			OutputStream output = null;
			InputStream inStream = null;
			try {
				String rootFilePath = AccessoriesMgrLocator
					.getTawCommonsAccessoriesManagerCOS().getFilePath("daiweiindexreduction");
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
					DaiSubtractTable subtractTable = (DaiSubtractTable)queryList.get(i); // 转化
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
				String excelFileName = "20170815.xlsx";
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

	/* 获取子角色 public
	 * @param roleId
	 * @param areaId
	 * @param sheetId
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSubRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception { // getSubRole 方法
				String subRoleId = ""; 
				String subRoleName= "";
				String type= "subrole";
				String mainSubtractProfessional = StaticMethod.nullObject2String(request.getParameter("mainSubtractProfessional"));
				String mainSubType = StaticMethod.nullObject2String(request.getParameter("mainSubType"));/*核减理由类别*/
				//String getsubrole = StaticMethod.nullObject2String(XmlManage.getFile("/config/nwcomplaint-util.xml").getProperty("getsubrole"));
				IDownLoadSheetAccessoriesService sqlMgr=(IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().
				getBean("IDownLoadSheetAccessoriesService");
				//限制传入的字典值长度和核减理由长度字典值长度一致，避免短于核减理由长度字典值长度的字典值被匹配到
				if(mainSubType==null||mainSubType.length()!=11)
				{
					mainSubType="00000000000";
				}
				// 子角色集合
				List roleList=sqlMgr.getSheetAccessoriesList(" select id,SUBROLENAME from taw_system_sub_role where roleid='199602'  and type2 like '%"+mainSubType+"%' ");
				String result= "";
				Map roleMap = new HashMap(); // 放到map
				if(roleList!=null && 0<roleList.size()){
					roleMap = (Map) roleList.get(0); // 获取
					subRoleId = StaticMethod.nullObject2String(roleMap.get("id"));
					subRoleName = StaticMethod.nullObject2String(roleMap.get("SUBROLENAME"));
					result = "{subRoleId:'"+subRoleId+"',subRoleName:'"+subRoleName+"',type:'"+type+"'}";
				}else{
					result ="{subRoleId:'"+subRoleId+"',subRoleName:'未匹配到对应角色请联系管理员分配角色!!',type:'"+type+"'}";
				}
				JSONUtil.print(response, result);
				return null;
		}
	
	
	public ActionForward getHandleTime(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception { 
		String time="";
		String mainSubtractIndexName = StaticMethod.nullObject2String(request.getParameter("mainSubtractIndexName"));
		ID2NameService id2NameService = (ID2NameService) ApplicationContextHolder
		.getInstance().getBean("id2nameService"); 
		String IndexName = id2NameService
		.id2Name(mainSubtractIndexName, "ItawSystemDictTypeDao");  // 用 ID2NameService 把对象转化为 Name
		IDownLoadSheetAccessoriesService sqlMgr=(IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().
		getBean("IDownLoadSheetAccessoriesService");
		// 子角色集合
		List roleList=sqlMgr.getSheetAccessoriesList("select advance_time1 from advance_time a where a.zb_name='"+IndexName+"' ");
		String result= "";
		 /*核减指标允许的时间*/ 
        DateIfEqualShow autoShowDai = new DateIfEqualShow(); // 核减允许的时间类
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-01 00:00:00");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        Calendar nowcal = Calendar.getInstance();  
		Date d1 = sdf1.parse(sdf.format(nowcal.getTime()));
		System.out.println("------" + sdf1.parse(sdf.format(nowcal.getTime()))); // 当前月1号
		long now = nowcal.getTimeInMillis();/*当前时间*/
		
		// 需要核减工单 新建权限测试语句
		nowcal.setTime(new Date());  
		//nowcal.setTime(sdf1.parse("2018-02-13 00:03:00"));
		
		Map roleMap = new HashMap(); // 放到map
		if(roleList !=null && 0<roleList.size()){
			roleMap = (Map) roleList.get(0); // 获取
			time = StaticMethod.nullObject2String(roleMap.get("advance_time1"));
			System.out.println(""+time);

			if(!"".equals(time) || ("".equals(time) || time ==null)){
				
				if(("".equals(time) || time ==null) || "0".equals(time)){
					
					long preStart1 = autoShowDai.addDate01ByHours(d1,8);/*当月01号+9*/
					long preEnd = autoShowDai.addDate01ByHours(d1,10);/*当月01号+10*/
					System.out.println("==========当前时间 now 为=========="+now);
					System.out.println("==========当前时间 preStart1 为=========="+preStart1);
					System.out.println("==========当前时间 currEnd 为=========="+preEnd);
					if(!(now > preStart1 && now < preEnd)) {// 当前时间，不符合要求的时间
							time ="5";
						    result = "{time:'"+time+"'}";
							System.out.println("-----------------------不符合要求！！！");  
					} else {  // 当前时间，符合要求！！				
						System.out.println("=======================符合要求！！！");
					}
				}else{
					
					long preStart1 = autoShowDai.addDate01ByHours(d1,8-Integer.parseInt(time));/*当月01号+9*/
					long preEnd = autoShowDai.addDate01ByHours(d1,10-Integer.parseInt(time));/*当月01号+10*/
					System.out.println("==========当前时间 now 为=========="+now);
					System.out.println("==========当前时间 preStart1 为=========="+preStart1);
					System.out.println("==========当前时间 currEnd 为=========="+preEnd);
					if(!(now > preStart1 && now < preEnd)) {// 当前时间，不符合要求的时间 
							time ="5";
						    result = "{time:'"+time+"'}";
							System.out.println("-----------------------不符合要求！！！");
					} else{  // 当前时间，符合要求！！	
						result = "{time:'"+time+"'}";
						System.out.println("=======================符合要求！！！");
					}
				}
				}
				
		}else{
			System.out.println("-----------------------未查到相关的数据，请核查！！！");
		}
		
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
 



