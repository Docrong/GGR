package com.boco.eoms.commons.statistic.customstat.webapp.action;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.commons.statistic.base.mgr.IStatConfigManager;
import com.boco.eoms.commons.statistic.base.model.StatisticFileInfo;
import com.boco.eoms.commons.statistic.base.reference.ApplicationContextHolder;
import com.boco.eoms.commons.statistic.base.util.Constants;
import com.boco.eoms.commons.statistic.base.util.ExcelConverterUtil;
import com.boco.eoms.commons.statistic.base.util.StatManagerMethod;
import com.boco.eoms.commons.statistic.base.util.StatUtil;
import com.boco.eoms.commons.statistic.base.webapp.action.BaseStatMethod;
import com.boco.eoms.commons.statistic.customstat.mgr.ICustomstatRemindManager;
import com.boco.eoms.commons.statistic.customstat.mgr.impl.StatCustomConfigManager;
import com.boco.eoms.commons.statistic.customstat.model.CustomstatRemind;
import com.boco.eoms.commons.statistic.customstat.test.InitMap;
import com.boco.eoms.commons.statistic.customstat.util.CustomStatCache;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

public class CustomStatMethodImpl extends BaseStatMethod implements
		ICustomStatMethod {
	
	private final String SUPPERUSER = "admin";
	
	//*******************************查看统计****************************************
	
	/**
	 * 已经统计出来文件的信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void statistFileInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("分页 已经统计出来文件的信息");
		
		String subscibeid = request.getParameter("subscibeid");
		
		int pageSize = 15;
		
		String pageIndexName = new org.displaytag.util.ParamEncoder("process")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		//当前页数
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		
		int statTotal = getStatManager().getStatistiTotalCount("select count(subscibeid) from StatisticFileInfo where subscibeid='" + subscibeid + "'");
		
		List satatistFIleList = getStatManager().getDetailStatisticFileList("from StatisticFileInfo where subscibeid='" + subscibeid + "'" + " order by reportType",statTotal, pageSize, pageIndex.intValue());
		
		request.setAttribute("estList", satatistFIleList);
		request.setAttribute("total", new Integer(statTotal));
		request.setAttribute("pageSize", new Integer(pageSize));
		
	}
	
	public void seachSatatistFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		this.logger.info("\n查询定制统计出来的报表");
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		String subscriberId = sessionform.getUserid();
		request.setAttribute("subscriberId", subscriberId);
	}
	
	/**
	 * 查看统计出来报表文件的信息(报表)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void showAlreadySatatistFileInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
			
			IStatConfigManager statCustomConfigManager = (IStatConfigManager)ApplicationContextHolder.getInstance().getBean("statCustomConfigManager");
	    	if(statCustomConfigManager.getExcelConfigMap() == null || statCustomConfigManager.getQueryCongigMap() == null)
	    	{
				reLoadCustomConfig(null,null,null,null);//读取定制配置文件
	    	}
	    	
			this.logger.info("查看统计出来文件的信息");
			String seachReportType = request.getParameter("seachReportType");
			String subscriberId = request.getParameter("userByDeptid");
			String checked = request.getParameter("checked");
			String statName = request.getParameter("statName");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			String readedState = request.getParameter("readedState");
			
			//获取缓存start
			String id = request.getParameter("id");
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
			String userid = sessionform.getUserid();
			if(userid == null || userid.equalsIgnoreCase(""))
			{
				new Exception("请先登陆系统");
			}
			if(id != null && !id.equalsIgnoreCase(""))
			{
				String cacheKey = userid + id;
				Map map = getCustomStatCache(cacheKey);
				seachReportType = String.valueOf(map.get("seachReportType"));
				subscriberId = String.valueOf(map.get("subscriberId"));
				checked = String.valueOf(map.get("checked"));
				statName = String.valueOf(map.get("statName"));
				beginTime = String.valueOf(map.get("beginTime"));
				endTime = String.valueOf(map.get("endTime"));
				readedState = String.valueOf(map.get("readedState"));
			}
			//获取缓存end
			
			String where = "where 1=1 ";
			if(!"".equalsIgnoreCase(seachReportType) && seachReportType != null)
			{
				where += " and stat.reportType='" + seachReportType + "'";
			}
			if((!"".equalsIgnoreCase(beginTime) && beginTime != null)
					&&(!"".equalsIgnoreCase(endTime) && endTime != null))
			{
				where += " and stat.saveTime>='" + beginTime + "' and stat.saveTime<='" + endTime + "'";
			}
			if(!"".equalsIgnoreCase(subscriberId) && subscriberId != null && !"null".equalsIgnoreCase(subscriberId))
			{
				//为选中的人员加单引号例如 178，445，s31l,结果为 '178'，'445'，'s31l'
				String paramValue = "";
				String[] paramValues = subscriberId.split(",");
				for (int i = 0; i < paramValues.length; i++) {
					paramValues[i] = "'" + paramValues[i] + "'";

					paramValue += paramValues[i];
					if (i < paramValues.length - 1)
						paramValue += ",";
				}
				
				where += " and stat.subscriberId in (" + paramValue + ")";
			}
			if(!"".equalsIgnoreCase(checked) && checked != null)
			{
				where += " and stat.checked='" + checked.trim() + "'";
			}
			if(!"".equalsIgnoreCase(readedState) && readedState != null)
			{
				where += " and stat.readedState='" + readedState.trim() + "'";
			}
			if(!"".equalsIgnoreCase(statName) && statName != null)
			{
				where += " and stat.statName='" + statName.trim() + "'";
			}
			
			int pageSize = 15;
			
			String pageIndexName = new org.displaytag.util.ParamEncoder("process")
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			//当前页数
			final Integer pageIndex = new Integer(GenericValidator
					.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
					: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
			String totalStr = "select count(stat.id) from StatisticFileInfo stat " + where;
			this.logger.info("\ntotalStr :" + totalStr);
			int statTotal = getStatManager().getStatistiTotalCount(totalStr);
			
			String queryStr = "from StatisticFileInfo stat " + where + "order by stat.saveTime";
			this.logger.info("\nqueryStr :" + queryStr);
			List satatistFIleList = getStatManager().getDetailStatisticFileList(queryStr,statTotal, pageSize, pageIndex.intValue());
			
			//保存查询信息到内存中start
			Map customStaMap = new HashMap();
			customStaMap.put("seachReportType", seachReportType);
			customStaMap.put("subscriberId", subscriberId);
			customStaMap.put("checked", checked);
			customStaMap.put("statName", statName);
			customStaMap.put("beginTime", beginTime);
			customStaMap.put("beginTime", beginTime);
			customStaMap.put("readedState", readedState);
			
			for(int i=0;i<satatistFIleList.size();i++)
			{
				StatisticFileInfo sfi = (StatisticFileInfo)satatistFIleList.get(i);
				String cacheKey = userid + sfi.getId();
				CustomStatCache.Instance().putParams(cacheKey, customStaMap);
			}
			//保存查询信息到内存中end

			request.setAttribute("estList", satatistFIleList);
			request.setAttribute("total", new Integer(statTotal));
			request.setAttribute("pageSize", new Integer(pageSize));
	}
	
	private Map getCustomStatCache(String id)
	{
		return CustomStatCache.Instance().getParams(id);
	}
	
    public void showSatatistFileResult(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String htmlFilePath = request.getParameter("htmlFilePath");
		String excelFilePath= request.getParameter("excelFilePath");
		String excelFileName= request.getParameter("excelFileName");
		
		String id = request.getParameter("id");
		
		htmlFilePath= new String(htmlFilePath.getBytes("ISO-8859-1"), "UTF-8");
		htmlFilePath= new String(htmlFilePath.getBytes("ISO-8859-1"), "UTF-8");
		excelFileName= new String(excelFileName.getBytes("ISO-8859-1"), "UTF-8");
		
		String htmlString = ExcelConverterUtil.InputStreamToString(new FileInputStream(StatUtil.getWEBABSURL() + htmlFilePath));
    	
		request.setAttribute("html", htmlString);
		request.setAttribute("excelFilePath", excelFilePath);
		request.setAttribute("excelFileName", excelFileName);
		request.setAttribute("id", id);
	}
    
	public void deleteSatatistFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		String htmlFilePath = request.getParameter("htmlFilePath");
		String excelFilePath= request.getParameter("excelFilePath");
		String excelFileName= request.getParameter("excelFileName");
		
		String id = request.getParameter("id");
		
		htmlFilePath= new String(htmlFilePath.getBytes("ISO-8859-1"), "UTF-8");
		htmlFilePath= new String(htmlFilePath.getBytes("ISO-8859-1"), "UTF-8");
		excelFileName= new String(excelFileName.getBytes("ISO-8859-1"), "UTF-8");
		
		//删除文件和数据库信息
		boolean delete = getStatManager().deleteCustomStatistic(id);
		
		if(delete)//true:数据库删除成功
		{
			//删除文件
			String ABSURL = StatUtil.getWEBABSURL();
			String ABSURL1 = ABSURL + htmlFilePath;
			String ABSURL2 = ABSURL + excelFilePath;
			
			StatManagerMethod.deleteFile(ABSURL1);
			StatManagerMethod.deleteFile(ABSURL2);
		}
	}
    
	public void updateSatatistFileInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		this.logger.info(" 更新统计出来文件的信息");
		String id = request.getParameter("id");
		String checked = request.getParameter("checked");
		String readedState = request.getParameter("readedState");
		
		String quary = "from StatisticFileInfo stat where id = '" +  id + "'";
		List list = getStatManager().getAlreadyCustomStatisticFilterList(null,quary);
		
		if(list != null && list.size() != 0)
		{
			StatisticFileInfo sfi = (StatisticFileInfo)list.get(0);
			if(checked != null)
			{
				//审核状态（通过或未通过）
				sfi.setChecked(checked);
				//审核人
				TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
				String checkerId = sessionform.getUserid();
				sfi.setCheckerId(checkerId);
				//审核时间
				String checkTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				sfi.setCheckTime(checkTime);
			}
			if(readedState != null)
			{
				//设置阅读状态（已读或未读）
				sfi.setReadedState(readedState);
			}
			
			getStatManager().saveStatisticInfo(sfi);
		}
	}
	
   /**
    * 从新读取配置文件，在增加定制报表的时候需要刷新内存
    * @param mapping
    * @param form
    * @param request
    * @param response
    */
    public void reLoadCustomConfig(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	StatCustomConfigManager statCustomConfigManager = (StatCustomConfigManager)ApplicationContextHolder.getInstance().getBean("statCustomConfigManager");
    	statCustomConfigManager.reLoadCustomConfig();
    }
    
    //*******************************执行定制的统计****************************************
    public void runCustomStatistic(Map map) throws Exception
    {
    	IStatConfigManager statCustomConfigManager = (IStatConfigManager)ApplicationContextHolder.getInstance().getBean("statCustomConfigManager");
    	if(statCustomConfigManager.getExcelConfigMap() == null || statCustomConfigManager.getQueryCongigMap() == null)
    	{
			reLoadCustomConfig(null,null,null,null);//读取定制配置文件
    	}
    	
    	String htmlFileURI = "";
    	String excelFileURI = "";
    	String xmlFileURI = "";
    	String reportType = String.valueOf(map.get("reportType"));//年year，季season ，月month, week周,daily日报
    	
    	if("yearReport".equalsIgnoreCase(reportType))
    	{
    		htmlFileURI = Constants.CUSTOMSTATISTICYEARFILE;
        	excelFileURI = Constants.CUSTOMSTATISTICYEARFILE;
        	xmlFileURI =  Constants.CUSTOMSTATISTICYEARFILE;
    	}
    	else if("seasonReport".equalsIgnoreCase(reportType))
    	{
    		htmlFileURI = Constants.CUSTOMSTATISTICSEASONFILE;
        	excelFileURI = Constants.CUSTOMSTATISTICSEASONFILE;
        	xmlFileURI =  Constants.CUSTOMSTATISTICSEASONFILE;
    	}
    	else if("monthReport".equalsIgnoreCase(reportType))
    	{
    		htmlFileURI = Constants.CUSTOMSTATISTICMONTHFILE;
        	excelFileURI = Constants.CUSTOMSTATISTICMONTHFILE;
        	xmlFileURI =  Constants.CUSTOMSTATISTICMONTHFILE;
    	}
    	else if("weekReport".equalsIgnoreCase(reportType))
    	{
    		htmlFileURI = Constants.CUSTOMSTATISTICWEEKFILE;
        	excelFileURI = Constants.CUSTOMSTATISTICWEEKFILE;
        	xmlFileURI =  Constants.CUSTOMSTATISTICWEEKFILE;
    	}
    	else if("dailyReport".equalsIgnoreCase(reportType))
    	{
    		htmlFileURI = Constants.CUSTOMSTATISTICDAILYFILE;
        	excelFileURI = Constants.CUSTOMSTATISTICDAILYFILE;
        	xmlFileURI =  Constants.CUSTOMSTATISTICDAILYFILE;
    	}
    	else if("customReport".equalsIgnoreCase(reportType))
    	{
    		htmlFileURI = Constants.CUSTOMSTATISTICCUSTOMFILE;
        	excelFileURI = Constants.CUSTOMSTATISTICCUSTOMFILE;
        	xmlFileURI =  Constants.CUSTOMSTATISTICCUSTOMFILE;
    	}
    	
    	map.put("htmlFileURI", htmlFileURI);
    	map.put("excelFileURI", excelFileURI);
    	map.put("xmlFileURI", xmlFileURI);
    	try {
    		//生成报表
			statConverter(map);
			
			//保存统计报表信息
			StatisticFileInfo statisticFileInfo = saveStatisticInfo(map);
			
			notifyMessage(statisticFileInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private  void notifyMessage(StatisticFileInfo statisticFileInfo) throws Exception
    {
    	//通知订阅模块，如果该报表被订阅则发送信息（短信提醒，邮件方式）
		ICustomstatRemindManager isrm = (ICustomstatRemindManager)ApplicationContextHolder.getInstance().getBean("CustomstatRemindManager");
		CustomstatRemind csr = isrm.getCustomstatRemind(statisticFileInfo);
		if(csr != null)//该报表已被订阅
		{
			csr.setExcelUrl(statisticFileInfo.getExcelFilePath());//设置附件文件的urL
			isrm.setCustomstatRemind(csr);
		}
    }
    
	/**
	 * 按照已经定制的统计进行统计
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public void runCustomStatistic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
			{
    	//查询数据库得到统计需要的条件,需要根据定制的类型计算beginTime与endTime
    	Map map = InitMap.initMap();
		runCustomStatistic(map);
	}
	
    /**
     * 保存统计报表信息
     * @param map
     * @return
     */
    private StatisticFileInfo saveStatisticInfo(Map map)
    {
    	StatisticFileInfo statisticFileInfo = null;
    	try
    	{
    		//保存统计结果信息到数据库
    		statisticFileInfo = new StatisticFileInfo();
    		statisticFileInfo.setStatisticFileInfo(map);
    		getStatManager().saveStatisticInfo(statisticFileInfo);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	return statisticFileInfo;
    }
}
