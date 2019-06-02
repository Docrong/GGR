package com.boco.eoms.commons.statistic.base.webapp.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.ValidationException;

import com.boco.eoms.commons.statistic.base.config.excel.Excel;
import com.boco.eoms.commons.statistic.base.config.excel.Sheet;
import com.boco.eoms.commons.statistic.base.config.model.ConditionParam;
import com.boco.eoms.commons.statistic.base.config.model.FieldDefine;
import com.boco.eoms.commons.statistic.base.config.model.KpiConfig;
import com.boco.eoms.commons.statistic.base.config.model.KpiDefine;
import com.boco.eoms.commons.statistic.base.config.model.SummaryDefine;
import com.boco.eoms.commons.statistic.base.config.report.Data;
import com.boco.eoms.commons.statistic.base.config.report.Datas;
import com.boco.eoms.commons.statistic.base.config.report.DisplayInfo;
import com.boco.eoms.commons.statistic.base.config.report.Field;
import com.boco.eoms.commons.statistic.base.config.report.Info;
import com.boco.eoms.commons.statistic.base.config.report.Report;
import com.boco.eoms.commons.statistic.base.config.report.Reports;
import com.boco.eoms.commons.statistic.base.ehcahe.StatEhCacheBean;
import com.boco.eoms.commons.statistic.base.mgr.IStatManager;
import com.boco.eoms.commons.statistic.base.reference.StaticMethod;
import com.boco.eoms.commons.statistic.base.reference.UUIDHexGenerator;
import com.boco.eoms.commons.statistic.base.statinterface.GraphicsFromInterFace;
import com.boco.eoms.commons.statistic.base.util.Constants;
import com.boco.eoms.commons.statistic.base.util.FileUtil;
import com.boco.eoms.commons.statistic.base.util.SqlParamCache;
import com.boco.eoms.commons.statistic.base.util.StatCacheUtil;
import com.boco.eoms.commons.statistic.base.util.StatUtil;

public class BaseStatMethod implements IStatMethod {
	private IStatManager statManager;
    public Logger logger = Logger.getLogger(this.getClass());

    //*****************************及时统计**************************************
    
	public void performStatistic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Map actionMap = StatUtil.ParameterMapToUtilMap(request.getParameterMap());
		
		//add by lizhenyou 2009-4-16 页面动态列参数
		String[] dynamiccolumparams = request.getParameterValues("dynamiccolumparam");
		if(dynamiccolumparams != null && dynamiccolumparams.length>=0)
		{
			String dynamiccolumparamString = "";
			for(int i=0;i<dynamiccolumparams.length;i++){
				if(i==dynamiccolumparams.length-1){
					dynamiccolumparamString = dynamiccolumparamString + dynamiccolumparams[i];
				}else{
					dynamiccolumparamString = dynamiccolumparamString + dynamiccolumparams[i]+ ",";
				}
			}
			actionMap.put("dynamiccolumparam", dynamiccolumparamString);
		}
		
		//根据type修改 beginTime 和 endTime
		actionMap = StatUtil.modActionMap(actionMap);
		actionMap.put("HttpServletRequest", request);
		actionMap.put("ActionForm", form);
		actionMap.put("requestURI", request.getRequestURI());
		
		actionMap.put("excelFileURI", Constants.EXPORTEXCELFILEPATH);//Excel文件路径
//		actionMap.put("htmlFileURI", arg1);//Html文件路径 统计报表不需要保存html文件
		actionMap.put("xmlFileURI", Constants.GRAPHICSFILEPATH);//图形Xml文件路径
		
		request = statConverter(actionMap);
	}
	
	/**
	 * 通过算法配置查询数据库
	 * @param kpiDefine 算法配置对象
	 * @param param map参数，目的是替换kpiDefine中的值
	 * @return List结果集
	 * list结果集中存放的是多个map
	 * @throws Exception 
	 */
	public List getStatList(KpiDefine kpiDefine,Map param) throws Exception
	{
		List list = null;
		UUIDHexGenerator uu = UUIDHexGenerator.getInstance();
        String statID = uu.getID();
		list = statManager.getStatJdbcDAO().getListKpiResult(kpiDefine, param, new String[1],statID);
		return list;
	}
	
	/**
	 * 生成报表
	 * @param actionMap
	 * @return
	 * @throws Exception
	 */
	public HttpServletRequest statConverter(Map actionMap) throws Exception
	{
		logger.info("\n统计条件 Map is " + actionMap);
		
		HttpServletRequest actionRequest = null;
		if(actionMap.get("HttpServletRequest") != null)
		{
			actionRequest = (HttpServletRequest)actionMap.get("HttpServletRequest");
		}
		String requestURI = String.valueOf(actionMap.get("requestURI"));//Detail连接url
		String reportIndex = String.valueOf(actionMap.get("reportIndex"));
		String excelConfigURL = String.valueOf(actionMap.get("excelConfigURL"));
		
		Sheet reportConfig = null;
		//add by lizhenyou 2009-4-10 动态列功能，页面需要选择需要选择的列 注意：页面必须使用dyColumSelectids固定名称
		//actionMap.put("dynamiccolumparam", "1220,1221,1222");//测试
		String dyColumSelectids = String.valueOf(actionMap.get("dynamiccolumparam"));
		if(dyColumSelectids !=null && !"".equalsIgnoreCase(dyColumSelectids) && !"null".equalsIgnoreCase(dyColumSelectids))
		{
			String[] dycs = dyColumSelectids.split(",");
			reportConfig = statManager.getStatConfigManager().getExcelConfig(excelConfigURL,dycs,Integer.parseInt(reportIndex)).getSheetByIndex(reportIndex);
		}
		else
		{
			reportConfig = statManager.getStatConfigManager().getExcelConfig(excelConfigURL).getSheetByIndex(reportIndex);
		}
		//add end
		
		String reportName = reportConfig.getSheetName();
		String kpiConfigURL = reportConfig.getQueryFileName();
		String kpiDefineName = reportConfig.getQueryName();
		
		logger.info("\nExcel名字 reportName is " + reportName);
		logger.info("\n算法配置路径 kpiConfigURL is " + kpiConfigURL);
		logger.info("\n算法配置名字 kpiDefineName is " + kpiDefineName);
		
		KpiConfig kpiConfig = statManager.getStatConfigManager().getKpiConfig(kpiConfigURL);
		kpiConfig = (KpiConfig)StatUtil.CloneObject(kpiConfig);
		KpiDefine kpiDefine = kpiConfig.getConfigByKpiDefineName(kpiDefineName);
		
		//根据查询条件得到结果集
		String[] condSql = {""};
//		String statID =java.util.UUID.randomUUID().toString();//1.5jdk
		UUIDHexGenerator uu = UUIDHexGenerator.getInstance();
        String statID = uu.getID();
		
		List listResult = statManager.getListKpiResult(kpiDefineName, actionMap,condSql,kpiConfigURL,statID);
		
		ActionForm form = (ActionForm)actionMap.get("ActionForm");
		listResult=handelResult(listResult,form,actionRequest,reportConfig,kpiDefine);
		
		//======================
		
		Sheet reportData = (Sheet)StatUtil.CloneObject(reportConfig);
		
		Map infoMap = StatUtil.getMapInfo(actionMap,kpiDefine);
		String lasttime = statManager.getStatJdbcDAO().getKpiInfo(kpiConfig.getEstModuleId());
		infoMap.put(Constants.LASTTIME, lasttime);
		
		//超链接
		String findListForward = String.valueOf(actionMap.get(("findListForward")));
		
		String dataUrl = StatUtil.getDateURL(requestURI,"showStatisticSheetList",excelConfigURL,reportIndex,findListForward,statID);
		
		//设置最大显示行数
		int listSize = reportData.getShowmaxrow();
        if(listSize != -1 && listSize < listResult.size())
        {
        	List tmpList = new ArrayList();
        	for(int i=0;i<listSize;i++)
        	{
        		tmpList.add(listResult.get(i));
        	}
        	listResult = tmpList;
        }
        
        boolean graphicswitch = false;//图形报表开关
        if(reportData.getGraphicConfigObj()!= null)
        {
        	graphicswitch = true;
        }
        
        String reportFromType = String.valueOf(actionMap.get(("reportFromType")));//报表类型
		//保存缓存（图形报表需要保存缓存）
        if((graphicswitch && "StatFrom".equalsIgnoreCase(reportFromType)) || "StatFrom_graphicsFrom".equalsIgnoreCase(reportFromType))
        {
        	String javavision = System.getProperty("java.version");
        	if(javavision.startsWith("1.5"))
        	{
        		logger.info("\n保存统计信息到缓存中");
        		StatEhCacheBean statEhCacheBean = new StatEhCacheBean();
        		statEhCacheBean.setAll(listResult,reportData,infoMap,kpiDefine,dataUrl,excelConfigURL);
        		StatCacheUtil.saveCache(statID ,statEhCacheBean);
        		StatUtil.setRequestAttribute(actionRequest, "statEhCacheID", statID);
        		logger.info("\n保存统计信息到缓存成功");
        	}
        }
	//===================================================================
		//判断显示报表类型（reportFromType ：StatFrom（统计报表），graphicsFrom（图型报表）,StatFrom_graphicsFrom统计报表的同时显示图型报表）
		if(reportFromType == null || "".equalsIgnoreCase(reportFromType) || "null".equalsIgnoreCase(reportFromType))
		{
			reportFromType = "StatFrom";//默认为统计报表
//			reportFromType = "graphicsFrom";//默认为图形报表
		}
		
		logger.info("\n报表类型 reportFromType is " + reportFromType);
		
		if("StatFrom".equalsIgnoreCase(reportFromType) || "StatFrom_graphicsFrom".equalsIgnoreCase(reportFromType))
		{
			String htmlFileURI = actionMap.get("htmlFileURI") != null?String.valueOf(actionMap.get("htmlFileURI")):null;
			String excelFileURI = actionMap.get("excelFileURI") != null?String.valueOf(actionMap.get("excelFileURI")):null;
			Date cuttentDate = new Date();
			String saveTime = new SimpleDateFormat("yyyyMMddHHmmss").format(cuttentDate);
			String fileIdentification = statID + saveTime;
			
			String excelConfigValue = String.valueOf(statManager.getStatConfigManager().getExcelConfigMap().get(excelConfigURL));
			
			//建立Html
			String html = statManager.getHtmlKpiString(reportData,listResult,infoMap,dataUrl,kpiDefine);
			StatUtil.setRequestAttribute(actionRequest, "html", html);
			if(htmlFileURI != null)
			{
				String htmlFileName = StatUtil.foundHtmlToFile(html, excelConfigValue, htmlFileURI, fileIdentification);
				//保存到Map中
				actionMap.put("htmlFilePath",  "/"+ htmlFileURI + htmlFileName);
				actionMap.put("detailId", statID);
				actionMap.put("saveTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cuttentDate));
				actionMap.put("detailParamsMap", SqlParamCache.Instance().getParams(statID));
			}

			//add by lizhenyou 2009-4-16 动态列功能
			String excelFileName = "";
			if(dyColumSelectids!=null && !"".equalsIgnoreCase(dyColumSelectids) && !"null".equalsIgnoreCase(dyColumSelectids))
			{
				String[] dycs = dyColumSelectids.split(",");
				excelFileName = StatUtil.foundExcelToFile(excelConfigValue,reportName,listResult ,infoMap, kpiDefine,excelFileURI,statManager,fileIdentification,dycs,Integer.parseInt(reportIndex));
			}
			else
			{
				excelFileName = StatUtil.foundExcelToFile(excelConfigValue,reportName,listResult ,infoMap, kpiDefine,excelFileURI,statManager,fileIdentification);
			}
			
			StatUtil.setRequestAttribute(actionRequest, "excelFilePath",  "/"+ excelFileURI + excelFileName);
			StatUtil.setRequestAttribute(actionRequest, "excelFileName", /*reportName+*/excelFileName);
			actionMap.put("excelFilePath",  "/"+ excelFileURI + excelFileName);
			actionMap.put("excelFileName",  /*reportName+*/excelFileName);
			actionMap.put("statName",  reportName);
		}
		
		if((graphicswitch && "graphicsFrom".equalsIgnoreCase(reportFromType)) || "StatFrom_graphicsFrom".equalsIgnoreCase(reportFromType))
		{
			String xmlFileURI = actionMap.get("xmlFileURI") != null?String.valueOf(actionMap.get("xmlFileURI")):null;
			//建立图形报表Doucument
			GraphicsFromInterFace.GraphicsFromConverter(reportData,listResult,actionRequest,xmlFileURI);
		}
		
		StatUtil.setRequestAttribute(actionRequest, "graphicswitch", String.valueOf(graphicswitch));
		if(reportData.getGraphicConfigObj() != null)
		{
			StatUtil.setRequestAttribute(actionRequest, "graphicReport", reportData.getGraphicConfigObj().getGraphicReports(reportData.getQueryName()));
		}
		
		StatUtil.setRequestAttribute(actionRequest, "reportFromType", reportFromType);
		
		logger.info("统计完成");
		return actionRequest;
	}
	
	protected List handelResult(List listResult,ActionForm form,
			HttpServletRequest request,Sheet reportConfig, KpiDefine kpiDefine) throws Exception {
		return listResult;
	}
	
	public void showStatisticPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String excelConfigURL = request.getParameter("excelConfigURL");
		String findListForward = request.getParameter("findListForward");
		logger.info("\n Excel 路径（key） excelConfigURL is: " + excelConfigURL);
		logger.info("\n detail 页面路径（Forward） findListForward is: " + findListForward);
		Excel excelConfig = statManager.getStatConfigManager().getExcelConfig(excelConfigURL);
		request.setAttribute("excelConfig", excelConfig);
		request.setAttribute("excelConfigURL", excelConfigURL);
		request.setAttribute("findListForward", findListForward);
	}

	public void showStatisticSheetList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map actionMap = StatUtil.ParameterMapToUtilMap(request.getParameterMap());
		
		String excelConfigURL = request.getParameter("excelConfigURL");
		String statID = request.getParameter("statID");

		//获取每页显示条数
//		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
//				.getInstance().getBean("SheetAttributes")).getPageSize();
		int pageSize = 15;

		String pageIndexName = new org.displaytag.util.ParamEncoder("process")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		//当前页数
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));


		int[] total = {-1};

		//        Integer total =
		// statManager.getTotalCount(statManager.getListCountSqlByName(kpiDefineName,
		//                    queryDefineName, fieldAsName, summaryData));

		//分页取得列表
		List result = statManager.getDetailList(total,pageIndex.intValue(), pageSize, actionMap ,excelConfigURL,statID);

		//        List result = statManager.getMainList(pageIndex, new Integer(pageSize
		//                .intValue()), statManager.getlistSqlByName(kpiDefineName,
		//                    queryDefineName, fieldAsName, summaryData));

		request.setAttribute("estList", result);
		request.setAttribute("total", new Integer(total[0]));
		request.setAttribute("pageSize", new Integer(pageSize));
	}
	
	/**
	 * 显示图形报表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public void showGraphicsStatisticPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取缓存
		StatEhCacheBean statEhCacheBean = (StatEhCacheBean)StatCacheUtil.getCache(request.getParameter("statEhCacheID"));
		List listResult = statEhCacheBean.getListResult();
		Sheet sheet = statEhCacheBean.getSheet();
		
//		建立图形报表Doucument
		GraphicsFromInterFace.GraphicsFromConverter(sheet,listResult,request,Constants.GRAPHICSFILEPATH);
	}
	
	/**
	 * 按照统计接入规范 接入门户系统
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void buildStatObject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		System.out.println("组成统计的接入规范数据");
		Map actionMap = StatUtil.ParameterMapToUtilMap(request.getParameterMap());
		//根据type修改 beginTime 和 endTime
		actionMap = StatUtil.modActionMap(actionMap);
		actionMap.put("HttpServletRequest", request);
		actionMap.put("ActionForm", form);
		actionMap.put("requestURI", request.getRequestURI());
		actionMap.put("excelFileURI", Constants.EXPORTEXCELFILEPATH);//Excel文件路径
//		actionMap.put("htmlFileURI", arg1);//Html文件路径 统计报表不需要保存html文件
		actionMap.put("xmlFileURI", Constants.GRAPHICSFILEPATH);//图形Xml文件路径
		String reportIndex = String.valueOf(actionMap.get("reportIndex"));
		String excelConfigURL = String.valueOf(actionMap.get("excelConfigURL"));
		Sheet reportConfig = statManager.getStatConfigManager().getExcelConfig(excelConfigURL).getSheetByIndex(reportIndex);
		String reportName = reportConfig.getSheetName();
		String kpiConfigURL = reportConfig.getQueryFileName();
		String kpiDefineName = reportConfig.getQueryName();
		logger.info("\nExcel名字 reportName is " + reportName);
		logger.info("\n算法配置路径 kpiConfigURL is " + kpiConfigURL);
		logger.info("\n算法配置名字 kpiDefineName is " + kpiDefineName);
		KpiConfig kpiConfig = statManager.getStatConfigManager().getKpiConfig(kpiConfigURL);
		kpiConfig = (KpiConfig)StatUtil.CloneObject(kpiConfig);
		KpiDefine kpiDefine = kpiConfig.getConfigByKpiDefineName(kpiDefineName);
		
		//根据查询条件得到结果集
		String[] condSql = {""};
//		String statID =java.util.UUID.randomUUID().toString();//1.5jdk
		UUIDHexGenerator uu = UUIDHexGenerator.getInstance();
        String statID = uu.getID();
		
		List listResult = statManager.getListKpiResult(kpiDefineName, actionMap,condSql,kpiConfigURL,statID);
		String url = "http://127.0.0.1:8085/zy/statistic/commonfault/stat.do?method=showStatisticSheetList&statID=" + statID
						+ "&reportIndex=" + reportIndex + "&excelConfigURL=" + excelConfigURL;
		String xml = buildStatXML(listResult,kpiDefine,actionMap,url);
		response.getOutputStream().write(xml.getBytes("UTF-8"));
		System.out.println(listResult);
	}
	
	/**
	 * 创建门户接入规范的xml格式
	 * @param listResult
	 * @param kpiDefine
	 * @param actionMap
	 * @param url
	 * @return
	 * @throws MarshalException
	 * @throws ValidationException
	 * @throws IOException
	 * @throws MappingException
	 */
	private String buildStatXML(List listResult,KpiDefine kpiDefine,Map actionMap,String url) throws MarshalException, ValidationException, IOException, MappingException
	{
		String xml = "";
		Reports reports = new Reports();
		Report[] report = new Report[1];
		report[0] = new Report();
		
		//display-info
		DisplayInfo displayinfo = new DisplayInfo();
		report[0].setDisplayinfo(displayinfo);
		ConditionParam[] conditionParams = kpiDefine.getConditionParams();
		List infolist = new ArrayList();
		for(int k=0;k<conditionParams.length;k++)
		{
			ConditionParam conditionParam = conditionParams[k];
			String name = conditionParam.getPageName();
			String value = String.valueOf(actionMap.get(name));
			Info info = new Info();
			info.setName(name);
			info.setValue(value);
			infolist.add(info);
		}
		displayinfo.setInfosList(infolist);
		//data
		Datas datas = new Datas();
		report[0].setDatas(datas);
		List datalist = new ArrayList();
		for(int i=0;i<listResult.size();i++)
		{
			Map map = (Map)listResult.get(i);
			Data data = new Data();
			List fieldlist = new ArrayList();
			//汇总字段
			SummaryDefine[] summaryDefines = kpiDefine.getSummaryDefines();
			String summaryStr = "";
			for(int n=0;n<summaryDefines.length;n++ )
			{
				SummaryDefine sdf = summaryDefines[n];
				String id = sdf.getId();
				String id2name = sdf.getId2nameService();
				String value = String.valueOf(map.get(id));
				Field field = new Field();
				field.setId(id);
				field.setId2name(id2name);
				if(id2name != null)//id转name
				{
					value = StatUtil.id2Name(value, id2name);
				}
				field.setValue(value);
				if(!"".equalsIgnoreCase(summaryStr))
				{
					summaryStr += "&";
				}
				summaryStr += id + "=" + id2name;
				fieldlist.add(field);
			}
			
			//数据字段
			List fdlist = kpiDefine.getAllFieldDefines();
			for(int j=0;j<fdlist.size();j++)
			{
				FieldDefine fd = (FieldDefine)fdlist.get(j);
				Field field = new Field();
				String id = fd.getId();
				String value=String.valueOf(map.get(id));
				String furl = url + "&fieldId=" + id;
				field.setId(id);
				field.setValue(value);
				field.setUrl(furl);
				fieldlist.add(field);
			}
			data.setFieldsList(fieldlist);
			datalist.add(data);
		}
		datas.setDatasList(datalist);
		report[0].setId(String.valueOf(actionMap.get("stat_report_id")));//从请求中传入
		reports.setReports(report);
		
		OutputStream os = reports2XML(reports);
		xml = FileUtil.OutputStreamToString(os);
//		FileUtil.writeFile(xml, "F:/test.xml");
		return xml;
	}
	
	private OutputStream reports2XML(Reports reports) throws IOException, MappingException, MarshalException, ValidationException
	{
//      输出默认的mapping 可以照这默认的改成自己下需要的 xml格式
//		MappingTool mt = new MappingTool();
//        mt.addClass(Reports.class);
//        mt.write(new OutputStreamWriter(System.out));
        
//		//利用castor影射为HTMLStream
        Mapping mapping = new Mapping(); //用于加载mapping.xml
        String path = "classpath:config/statistic/base-config/statReportToXmlMapping.xml";
        mapping.loadMapping(StaticMethod.getFilePathForUrl(path));

        OutputStream stream = new ByteArrayOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(stream, "UTF-8");
        Marshaller tmpM = new Marshaller(osw);
        tmpM.setMapping(mapping);
        tmpM.marshal(reports);
        osw.close();
        stream.close();
		
		return stream;
	}
	
	/**
	 * 导出Excel 不能通过action下载文件，fiter会过滤掉文件流
	 */

	public IStatManager getStatManager() {
		return statManager;
	}

	public void setStatManager(IStatManager statManager) {
		this.statManager = statManager;
	}
}
