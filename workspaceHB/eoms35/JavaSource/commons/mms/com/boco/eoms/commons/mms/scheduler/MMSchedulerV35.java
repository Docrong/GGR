package com.boco.eoms.commons.mms.scheduler;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.PieDataset;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.mms.base.config.Foot;
import com.boco.eoms.commons.mms.base.config.Reports;
import com.boco.eoms.commons.mms.base.foot.FootInfo;
import com.boco.eoms.commons.mms.base.util.Display;
import com.boco.eoms.commons.mms.base.util.ExcelToPic;
import com.boco.eoms.commons.mms.base.util.JFreeCharCreater;
import com.boco.eoms.commons.mms.base.util.MMSConstants;
import com.boco.eoms.commons.mms.base.util.ParseXML2Obj;
import com.boco.eoms.commons.mms.mmsreport.mgr.MmsreportMgr;
import com.boco.eoms.commons.mms.mmsreport.model.Mmsreport;
import com.boco.eoms.commons.mms.mmsreporttemplate.mgr.MmsreportTemplateMgr;
import com.boco.eoms.commons.mms.mmsreporttemplate.model.MmsreportTemplate;
import com.boco.eoms.commons.mms.msssubscribe.mgr.MsssubscribeMgr;
import com.boco.eoms.commons.mms.msssubscribe.model.Msssubscribe;
import com.boco.eoms.commons.mms.statreport.mgr.StatreportMgr;
import com.boco.eoms.commons.mms.statreport.model.Statreport;
import com.boco.eoms.commons.statistic.base.config.excel.Excel;
import com.boco.eoms.commons.statistic.base.config.excel.Sheet;
import com.boco.eoms.commons.statistic.base.config.graphic.GraphicConfig;
import com.boco.eoms.commons.statistic.base.config.graphic.GraphicReport;
import com.boco.eoms.commons.statistic.base.config.model.KpiConfig;
import com.boco.eoms.commons.statistic.base.config.model.KpiDefine;
import com.boco.eoms.commons.statistic.base.dao.impl.StatJdbcDAOImpl;
import com.boco.eoms.commons.statistic.base.mgr.impl.ExcelConverter;
import com.boco.eoms.commons.statistic.base.mgr.impl.ExportExcel;
import com.boco.eoms.commons.statistic.base.reference.ApplicationContextHolder;
import com.boco.eoms.commons.statistic.base.reference.ParseXmlService;
import com.boco.eoms.commons.statistic.base.util.ExcelConverterUtil;
import com.boco.eoms.commons.statistic.base.util.FileUtil;
import com.boco.eoms.commons.statistic.base.util.GraphicsReportUtil;
import com.boco.eoms.commons.statistic.base.util.StatUtil;
import com.boco.eoms.commons.statistic.customstat.util.CustomStatUtil;

public class MMSchedulerV35 implements Job{
	
	public Logger logger = Logger.getLogger(this.getClass());

	public void execute(JobExecutionContext context)
						throws org.quartz.JobExecutionException
	{
		try {
			//如果没有系统没有com.boco.eoms.base.util.ApplicationContextHolder的情况需要把InitStaticBaseApplicationContextServlet配置到web.xml中
			if (ApplicationContextHolder.getInstance().getCtx() == null) 
			{
				ApplicationContextHolder.getInstance().setCtx(com.boco.eoms.base.util.ApplicationContextHolder.getInstance().getCtx());
			}
			String JobName = "";
			if (context != null) {
				JobName = context.getJobDetail().getName();
			}
			logger.info("\n执行定制统计的JobName是 ：" + JobName);
			Calendar currtenCanlendar = Calendar.getInstance();// 开始执行统计时间
			
			//定制彩信报模板
			System.out.println("定制彩信报模板");
			MmsreportTemplateMgr mmsreportTemplateMgr = (MmsreportTemplateMgr)ApplicationContextHolder.getInstance().getBean("mmsreportTemplateMgr");
			MmsreportTemplate mmsreportTemplate = mmsreportTemplateMgr.getMmsreportTemplateForSubId(JobName);
			if(mmsreportTemplate == null)
			{
				return;
			}
			String executeCycle = mmsreportTemplate.getExecuteCycle();
			String[] statReportIds = mmsreportTemplate.getStatReportId().split(",");
			
			//彩信报实例
			System.out.println("彩信报实例");
			Mmsreport mmsreport = new Mmsreport();
			mmsreport.setMmsreport_template_id(mmsreportTemplate.getId());
			mmsreport.setMmsReportCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currtenCanlendar.getTime()));
			mmsreport.setMmsReportName(mmsreportTemplate.getMmsName() + "-" + CustomStatUtil.typeToName(executeCycle));
			mmsreport.setStatreport_id(mmsreportTemplate.getStatReportId());
			mmsreport.setMmsreportType(executeCycle);
			mmsreport.setUserid(mmsreportTemplate.getUserid());
			MmsreportMgr mmsreportMgr = (MmsreportMgr)ApplicationContextHolder.getInstance().getBean("mmsreportMgr");
			mmsreportMgr.saveMmsreport(mmsreport);
			
			//按照定制的彩信报生成彩信报需要的所有报表
			System.out.println("按照定制的彩信报生成彩信报需要的所有报表");
			for(int i=0; i<statReportIds.length;i++)
			{
				//根据彩信报报表配置的id得到excel与算法配置路径
				String sheetId = statReportIds[i];
				Reports reports = (Reports) ParseXmlService.create().xml2object(Reports.class, StaticMethod.getFilePathForUrl(MMSConstants.REPORT_CONFIG));
				com.boco.eoms.commons.mms.base.config.Sheet sh = reports.getSheetById(sheetId);
				String excelPath = sh.getExcelPath();//"classpath:config/mms/report-config/commonfault-config/oracle/statistic-config-excel-commonfault_T_resolve_KPI4_oracle.xls";
				String quaryPath = sh.getQueryPath();//"classpath:config/mms/report-config/commonfault-config/oracle/statistic-config-query-commonfault_T_resolve_KPI4_oracle.xml";			
				String sheetIndex = sh.getIndex();//"0";
				
				//解析算法配置文件查询数据库得到List
				ExcelConverter ec = new ExcelConverter();
				Excel excel = ec.parseExcelToConfig(StaticMethod.getFilePathForUrl(excelPath));
				Sheet sheet = excel.getSheetByIndex(sheetIndex);
				String kpiDefineName = sheet.getQueryName();
				KpiConfig kpiConfig = (KpiConfig)ParseXML2Obj.ParseXML2Obj(KpiConfig.class,StaticMethod.getFilePathForUrl(quaryPath));
				KpiDefine kpiDefine = kpiConfig.getConfigByKpiDefineName(kpiDefineName);
				String[] condSql = {""};
				
				//应该是从日报，月报，周报，的情况中取得时间点
				Map param = new HashMap();
				//param.put("endTime", "2008-12-03 15:16:16");
				//param.put("beginTime", "2006-12-03 15:16:16");
				Calendar beginCalendar = CustomStatUtil.getCalendar(executeCycle,
						(Calendar)StatUtil.CloneObject(currtenCanlendar));
				param.put("beginTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(beginCalendar.getTime()));// 统计开始时间
				param.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(currtenCanlendar.getTime()));// 统计结束时间
				
				StatJdbcDAOImpl sjdi = new StatJdbcDAOImpl();
				List list = sjdi.getListKpiResult(kpiDefine, param, condSql, "123");
				
//				UUIDHexGenerator uu = UUIDHexGenerator.getInstance();
//		        String uuid = uu.getID();
		        StatreportMgr statreportMgr = (StatreportMgr)ApplicationContextHolder.getInstance().getBean("statreportMgr");
		        Statreport statreport = new Statreport();
		        statreportMgr.saveStatreport(statreport);
		        String uuid = statreport.getId();
		        
				//根据模板excel与结果集List生成Excel
		        String realPath = FileUtil.getWEBURL(MMSConstants.REPORT_CONFIG) + MMSConstants.KEEP_REPORT_FILE_PATH;//"D:/";
		        //realPath = realPath.substring(1);//去掉绝对路径前的"/"
		        ExportExcel ee = new ExportExcel();
				OutputStream ops = ee.ResultExportExcel(StaticMethod.getFilePathForUrl(excelPath), 
						sheet.getSheetName(), list, param, kpiDefine);
				String excelName = uuid + ".xls";//"llllll402881841f81e969011f82dcca07000820090217141202.xls";//"2008-12-05-11-57-42402881ef1e0a6dfe011e0a71932e000d.xls";//"test.xls";//"test.xls";//"402881861f7e0ec9011f7e1bb5e3000720090216160239.xls";
				String outexcelPath = realPath + excelName;//"D:/kao.xls";
				FileUtil.writeFile((ByteArrayOutputStream)ops,outexcelPath);
				
				//绘制报表图片gif, stat(默认) 2维表格， column 柱图 ，line 线图 ,pie 饼图
				String gType = sh.getType();
				String pictureFormat = mmsreportTemplate.getPictureFormat();
				GraphicConfig gc = (GraphicConfig)GraphicsReportUtil.xml2object(GraphicConfig.class, sheet.getGraphicConfig());
				GraphicReport gr= gc.getGraphicReports()[0];
				String picfilename = uuid + "." + "gif";//"gif";
				if(MMSConstants.STAT.equalsIgnoreCase(gType))
				{
					ExcelToPic etp = new ExcelToPic();
					int headNum = sheet.getTables()[0].getHead_end();
					etp.createRowsImage(realPath, picfilename, excelName,headNum,Color.YELLOW);
				}
				else
				{
					Display display = new Display();
					display.title = gr.getTitle();
					JFreeCharCreater jcc = new JFreeCharCreater();
//						list = jcc.initList();
					ByteArrayOutputStream bos = null;
					if(MMSConstants.COLUMN.equalsIgnoreCase(gType))
					{
						CategoryDataset[] dataset = new CategoryDataset[1];
						dataset[0] = jcc.CoverterList2CategoryDataset(list,gr);
//						JFreeCharCreater.printDataSet(dataset);
						bos = jcc.CreateChart(1,dataset,display);
					}
					else if(MMSConstants.LINE.equalsIgnoreCase(gType))
					{
						CategoryDataset[] dataset = new CategoryDataset[1];
						dataset[0] = jcc.CoverterList2CategoryDataset(list,gr);
						bos = jcc.CreateChart(2,dataset,display);
					}
					else if(MMSConstants.PIE.equalsIgnoreCase(gType))
					{
						PieDataset[] dataset = new PieDataset[1];
						dataset[0] = jcc.CoverterList2PieDataset(list,gr);
						bos = jcc.CreateChart(3,dataset,display);
					}
					else if(MMSConstants.COLUMNLINE.equalsIgnoreCase(gType))
					{
						CategoryDataset datasetColumn = jcc.ConverterList2CategoryDatasetBarAndLine(list,gr,MMSConstants.COLUMN);
						CategoryDataset datasetLine = jcc.ConverterList2CategoryDatasetBarAndLine(list,gr,MMSConstants.LINE);
						CategoryDataset[] dataset = {datasetColumn,datasetLine};
						bos = jcc.CreateChart(4,dataset,display);
					}
					else
					{
						return;
					}
					
					FileUtil.writeFile(bos, realPath+picfilename);
				}
				
				//修改报表注释模板，根据注释模板替换相应位置的字符串$info$f1$info$
				Foot foot = sh.getFoot();
				String footString = "";
				if(foot != null)
				{
					footString = foot.getInfo();
					FootInfo ob = (FootInfo)Class.forName(foot.getCls()).newInstance();
					ob.setList(list);
					footString = ExcelConverterUtil.rep(ob.getInfo(),footString);
				}
				
				//保存到报表实例中
				//picUrl,excelUrl
				statreport.setMmsreport_template_id(mmsreportTemplate.getId());
				statreport.setUserid(mmsreportTemplate.getUserid());
				statreport.setMmsreport_id(mmsreport.getId());
				statreport.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currtenCanlendar.getTime()));
				statreport.setReportName(sh.getName());//报表名称
				statreport.setReportType(executeCycle);//报表类型 月，周，日
				statreport.setExcelID(excelName);//Excel报表url
				statreport.setPicID(picfilename);//图片url
				statreport.setFootInfo(footString);//报表脚注说明信息
//				statreport.setId(uuid);
				//StatreportMgr statreportMgr = (StatreportMgr)ApplicationContextHolder.getInstance().getBean("statreportMgr");
				statreportMgr.saveStatreport(statreport);
			}
			
			//发送通知，通知该条彩信已经生成完毕
			System.out.println("发送通知，通知该条彩信已经生成完毕");
			String sendStatus = notifyMessage(mmsreport);
			mmsreport.setSendStatus(sendStatus);
			mmsreportMgr.saveMmsreport(mmsreport);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("彩信报生成完毕");
	}
	
	private String notifyMessage(Mmsreport mmsreport) throws Exception
    {
    	//通知订阅模块，如果该报表被订阅则发送信息（彩信方式）
		MsssubscribeMgr mm = (MsssubscribeMgr)ApplicationContextHolder.getInstance().getBean("msssubscribeMgr");
		Msssubscribe msssubscribe  = mm.getMmsreport(mmsreport);
		
		//彩信报发送状态
		String status = "未发送";
		if(msssubscribe != null)//该报表已被订阅
		{
			//发送彩信报
			logger.info("发送彩信报");
			msssubscribe.setMmreportName(mmsreport.getMmsReportName());
			status = mm.sendMmsreport(msssubscribe,mmsreport);
			mmsreport.setSendTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
		}
		else
		{
			logger.info("该彩信报没有被订阅");
		}
		return status;
    }
	 
	/**
	 * @param args
	 * @throws JobExecutionException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws JobExecutionException, FileNotFoundException, InstantiationException, IllegalAccessException, ClassNotFoundException {
//		new MMSchedulerV35().execute(null);
//		String realPath = "/D:/ddd.gif";
//		realPath = realPath.substring(1);
//		
//		FootInfo ob = (FootInfo)Class.forName("com.boco.eoms.commons.mms.commonfault.foot.CommonfaultT4FootInfo").newInstance();
//		System.out.println(ob.getInfo());
		System.out.println(MMSConstants.MMSREPORT_FILE_RELATIVEPATH);
		
	}

}
