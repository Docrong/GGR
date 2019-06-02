package com.boco.eoms.commons.statistic.flowcontrol.webapp.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.commons.statistic.base.config.excel.Sheet;
import com.boco.eoms.commons.statistic.base.config.model.KpiConfig;
import com.boco.eoms.commons.statistic.base.config.model.KpiDefine;
import com.boco.eoms.commons.statistic.base.ehcahe.StatEhCacheBean;
import com.boco.eoms.commons.statistic.base.mgr.IStatManager;
import com.boco.eoms.commons.statistic.base.reference.UUIDHexGenerator;
import com.boco.eoms.commons.statistic.base.statinterface.GraphicsFromInterFace;
import com.boco.eoms.commons.statistic.base.util.Constants;
import com.boco.eoms.commons.statistic.base.util.SqlParamCache;
import com.boco.eoms.commons.statistic.base.util.StatCacheUtil;
import com.boco.eoms.commons.statistic.base.util.StatUtil;
import com.boco.eoms.commons.statistic.base.webapp.action.BaseStatMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

public class FlowcontrolStatMethodImpl extends BaseStatMethod implements
		IFlowcontrolStatMethod {
	private IStatManager statManager;
    public Logger logger = Logger.getLogger(this.getClass());
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
		// liquan add
	    TawSystemSessionForm sessionForm = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		String userid = sessionForm.getUserid();
		actionMap.put("userid", userid);
		// end
		actionMap.put("HttpServletRequest", request);
		actionMap.put("ActionForm", form);
		actionMap.put("requestURI", request.getRequestURI());
		
		actionMap.put("excelFileURI", Constants.EXPORTEXCELFILEPATH);//Excel文件路径
//		actionMap.put("htmlFileURI", arg1);//Html文件路径 统计报表不需要保存html文件
		actionMap.put("xmlFileURI", Constants.GRAPHICSFILEPATH);//图形Xml文件路径
		
		request = statConverter(actionMap);
	}
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
	//  liquan add
		StatUtil.setRequestAttribute(actionRequest, "reportIndex", reportIndex);
		StatUtil.setRequestAttribute(actionRequest, "excelConfigURL", excelConfigURL);
    //  end
		
		
		
		Sheet reportConfig = null;
		//add by lizhenyou 2009-4-10 动态列功能，页面需要选择需要选择的列 注意：页面必须使用dyColumSelectids固定名称
		//actionMap.put("dynamiccolumparam", "1220,1221,1222");//测试
		String dyColumSelectids = String.valueOf(actionMap.get("dynamiccolumparam"));
		if(dyColumSelectids !=null && !"".equalsIgnoreCase(dyColumSelectids) && !"null".equalsIgnoreCase(dyColumSelectids))
		{
			String[] dycs = dyColumSelectids.split(",");
			reportConfig = this.getStatManager().getStatConfigManager().getExcelConfig(excelConfigURL,dycs,Integer.parseInt(reportIndex)).getSheetByIndex(reportIndex);
		}
		else
		{
			reportConfig = this.getStatManager().getStatConfigManager().getExcelConfig(excelConfigURL).getSheetByIndex(reportIndex);
		}
		//add end
		
		String reportName = reportConfig.getSheetName();
		String kpiConfigURL = reportConfig.getQueryFileName();
		String kpiDefineName = reportConfig.getQueryName();
		
		logger.info("\nExcel名字 reportName is " + reportName);
		logger.info("\n算法配置路径 kpiConfigURL is " + kpiConfigURL);
		logger.info("\n算法配置名字 kpiDefineName is " + kpiDefineName);
		
		KpiConfig kpiConfig = this.getStatManager().getStatConfigManager().getKpiConfig(kpiConfigURL);
		kpiConfig = (KpiConfig)StatUtil.CloneObject(kpiConfig);
		KpiDefine kpiDefine = kpiConfig.getConfigByKpiDefineName(kpiDefineName);
		
		//根据查询条件得到结果集
		String[] condSql = {""};
//		String statID =java.util.UUID.randomUUID().toString();//1.5jdk
		UUIDHexGenerator uu = UUIDHexGenerator.getInstance();
        String statID = uu.getID();
		
		List listResult = this.getStatManager().getListKpiResult(kpiDefineName, actionMap,condSql,kpiConfigURL,statID);
		
		ActionForm form = (ActionForm)actionMap.get("ActionForm");
		listResult=handelResult(listResult,form,actionRequest,reportConfig,kpiDefine);
	
		   // liquan add
	         StatUtil.setRequestAttribute(actionRequest, "STATLIST", listResult);
           // end
		
		
		Sheet reportData = (Sheet)StatUtil.CloneObject(reportConfig);
		
		Map infoMap = StatUtil.getMapInfo(actionMap,kpiDefine);
		String lasttime = this.getStatManager().getStatJdbcDAO().getKpiInfo(kpiConfig.getEstModuleId());
		infoMap.put(Constants.LASTTIME, lasttime);
		
		//超链接
		String findListForward = String.valueOf(actionMap.get(("findListForward")));
		
		String dataUrl = StatUtil.getDateURL(requestURI,"showStatisticSheetList",excelConfigURL,reportIndex,findListForward,statID);
		//liquan add
		StatUtil.setRequestAttribute(actionRequest, "dataUrl", dataUrl);
		//end
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
			
			String excelConfigValue = String.valueOf(this.getStatManager().getStatConfigManager().getExcelConfigMap().get(excelConfigURL));
			
			//建立Html
			String html = this.getStatManager().getHtmlKpiString(reportData,listResult,infoMap,dataUrl,kpiDefine);
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
				excelFileName = StatUtil.foundExcelToFile(excelConfigValue,reportName,listResult ,infoMap, kpiDefine,excelFileURI,this.getStatManager(),fileIdentification,dycs,Integer.parseInt(reportIndex));
			}
			else
			{
				excelFileName = StatUtil.foundExcelToFile(excelConfigValue,reportName,listResult ,infoMap, kpiDefine,excelFileURI,this.getStatManager(),fileIdentification);
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
}
