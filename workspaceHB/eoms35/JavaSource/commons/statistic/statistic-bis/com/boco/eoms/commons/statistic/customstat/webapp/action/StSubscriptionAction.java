package com.boco.eoms.commons.statistic.customstat.webapp.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.job.model.TawCommonsJobsort;
import com.boco.eoms.commons.job.model.TawCommonsJobsubscibe;
import com.boco.eoms.commons.job.service.ITawCommonsJobsortManager;
import com.boco.eoms.commons.job.service.ITawCommonsJobsubscibeManager;
import com.boco.eoms.commons.statistic.base.config.excel.Excel;
import com.boco.eoms.commons.statistic.base.config.excel.Sheet;
import com.boco.eoms.commons.statistic.base.mgr.IStatConfigManager;
import com.boco.eoms.commons.statistic.base.reference.ApplicationContextHolder;
import com.boco.eoms.commons.statistic.base.util.StatUtil;
import com.boco.eoms.commons.statistic.customstat.mgr.ICustomstatRemindManager;
import com.boco.eoms.commons.statistic.customstat.mgr.IStSubscriptionManager;
import com.boco.eoms.commons.statistic.customstat.mgr.impl.StSubscriptionManagerImpl;
import com.boco.eoms.commons.statistic.customstat.mgr.impl.StatCustomConfigManager;
import com.boco.eoms.commons.statistic.customstat.model.CustomstatRemind;
import com.boco.eoms.commons.statistic.customstat.model.StSubscription;
import com.boco.eoms.commons.statistic.customstat.webapp.form.StSubscriptionForm;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.filemanager.extra.statistic.util.StaticStatistic;


public final class StSubscriptionAction extends BaseAction {
	protected Logger logger = Logger.getLogger(this.getClass());
	   String conditions = "";
	public ActionForward main(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		IStSubscriptionManager mgr = (IStSubscriptionManager) getBean("IstSubscriptionManager");
		List stSubscriptionList = mgr.getStSubscriptionsByCondition(conditions);
		request.setAttribute("stSubscriptionList", stSubscriptionList);
		return mapping.findForward("list");
	}

	/**
	 * ajax保存
	 */
	public ActionForward xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String next="";
		String deptid="";
		 String userId="";
		 String className="com.boco.eoms.commons.statistic.customstat.scheduler.StatSchedulerV35";
		StSubscriptionForm stSubscriptionForm = (StSubscriptionForm)form;
		TawSystemSessionForm sessionForm = this.getUser(request);
		deptid = sessionForm.getDeptid();
		userId = sessionForm.getUserid();
		try{
			 Map actionMap = StatUtil.ParameterMapToUtilMap(request.getParameterMap());
			    String requestURI= request.getContextPath().toString()+"/statistic/customstat/stat.do";
			    actionMap.put("requestURI", requestURI); //路径
			     String item= actionMap.get("item").toString();
			     int statMode = stSubscriptionForm.getStatMode();
			     String  sMode="";
				/**	
					weekReport   1
		            customReport 3
		            seasonReport 6
		            dailyReport  4
		            monthReport  2
		            yearReport   5
			     */
			     switch (statMode) {
			      case 1: { //周报
			    	  sMode="weekReport";
				      break;
				      }
			      case 2: { //月报
			    	  sMode="monthReport";
				      break;
				      }
			      case 3: { //自定义
			    	  sMode="customReport";
				      break;
				      }
			      case 4: { //日报
			    	  sMode="dailyReport";
				      break;
				      }
			      case 5: { //年报
			    	  sMode="yearReport";
				      break;
				      }
			      case 6: { //季报
			    	  sMode="seasonReport";
				      break;
				      }
			     }
			     actionMap.put("excelConfigURL", item);
			     actionMap.put("reportType", sMode);
			     actionMap.put("beginTime", stSubscriptionForm.getStatfromdate());
			     actionMap.put("endTime", stSubscriptionForm.getStattodate());
		StSubscription stSubscription= new StSubscription();
	    stSubscription.setSubscribeDept(Integer.parseInt(deptid));//插入部门
	    stSubscription.setSubscriber(userId);//插入人
		Timestamp subscribeTime=StaticMethod.getTimestamp();
		stSubscription.setSubscribeTime(subscribeTime); //插入时间
		
		stSubscription.setClassName(className); //循环类名
		stSubscription.setType("cron");//轮训类型
		stSubscription.setStatMode(stSubscriptionForm.getStatMode()); //定制时间类型
		stSubscription.setItem(stSubscriptionForm.getItem());//统计类别
		stSubscription.setItemName(stSubscriptionForm.getItemName());//统计类别名称
		//String subId = this.getSubId(stSubscription);
		//stSubscription.setSubId(subId);
		
		 stSubscription.setWeekselectfrom(stSubscriptionForm.getWeekselectfrom());
		stSubscription.setStatfromdate(StaticMethod.getTimestamp(stSubscriptionForm.getStatfromdate()));
		stSubscription.setStattodate(StaticMethod.getTimestamp(stSubscriptionForm.getStattodate()));
		 //下面代码得出所有参数的字符串放在备注里;
	    
	   
	     
	    Set set = actionMap.keySet();
		Iterator it = set.iterator();
		String str="";
		while(it.hasNext()){
			Object o = it.next();
			String key=(String)o;
			String value = (String)actionMap.get(o);
			str+=key+"="+value+",";
			}
		 String remark=str.substring(0, str.length()-1);
		if(remark.length()>=255){
			logger.info("remark: 参数的集合大于255个字符-------------------------");
			}
		stSubscription.setRemark(remark); //参数字符串
		String seasonSelectFrom= StaticMethod.nullObject2String(actionMap.get("seasonSelectFrom"));
		String monthSelectFrom= StaticMethod.nullObject2String(actionMap.get("monthSelectFrom")); 
		int weekSelectFrom=StaticMethod.nullObject2int(actionMap.get("weekSelectFrom")); 
		 //开始进行周期分析
		  String statFromDate =stSubscriptionForm.getStatfromdate();
	      String statToDate = stSubscriptionForm.getStattodate();
	    
	    String   cycle="";
	    switch (statMode) {
	      case StaticStatistic.WEEKLY: { //1：周报,周报的处理相对比较复杂，需要分析。例如订阅的开始时间是上周一，那么统计的时间段是上周一到上周日，自动统计程序应该在本周一运行
	        switch (weekSelectFrom) {
	          //周报的时间安排在凌晨1点
	          case 1:
	            cycle = "0 0 1 ? * TUE";
	            break;
	          case 2:
	            cycle = "0 0 1 ? * WED";
	            break;
	          case 3:
	            cycle = "0 0 1 ? * THU";
	            break;
	          case 4:
	            cycle = "0 0 1 ? * FRI";
	            break;
	          case 5:
	            cycle = "0 0 1 ? * SAT";
	            break;
	          case 6:
	            cycle = "0 0 1 ? * SUN";
	            break;
	          case 7:
	            cycle = "0 0 1 ? * MON";
	            break;
	          default:
	            cycle = "";
	         }
	        break;
	      }
	      case StaticStatistic.MONTH: { //月报，月报比较好处理，统计的时间就是上月初到月末，自动统计的时间是每月1号
	        //月报安排在monthSelectFrom号凌晨2点 
	        cycle = "0 0 2 "+monthSelectFrom+" * ?";
	        break;
	      }
	      case StaticStatistic.TIMETABLE: { //任意时间段的报表，这种类型的报表属于临时性的报表无周期性可言，系统只会统计一次，统计的时间是当前时间的第二天3点
	        String tomorrow = StaticMethod.getTimeBeginString(1);
	        GregorianCalendar calDate = StaticMethod.String2Cal(tomorrow);
	        GregorianCalendar calDate1 = StaticMethod.String2Cal(statToDate);
	        if (calDate.before(calDate1)) {
	          calDate1.add(GregorianCalendar.DATE, 1);
	          int date = calDate1.get(calDate.DATE);
	          int month = calDate1.get(calDate.MONTH) + 1;
	          int year = calDate1.get(calDate.YEAR);
	          cycle = "0 0 3 " + String.valueOf(date) + " " + String.valueOf(month) +
	              " ? " + String.valueOf(year);
	        }
	        else {
	          int date = calDate.get(calDate.DATE);
	          int month = calDate.get(calDate.MONTH) + 1;
	          int year = calDate.get(calDate.YEAR);
	          cycle = "0 0 3 " + String.valueOf(date) + " " + String.valueOf(month) +
	              " ? " + String.valueOf(year);
	        }
	        break;
	      }
	    case StaticStatistic.DAILY: { //日报
		        //日报安排在凌晨4点
		        cycle = "0 0 4 * * ?";
		        break;
		      }
	    case 5: { //年报
	        //年报安排在凌晨5点
	        cycle = "0 0 5 1 1 ?";
	        break;
	      }
	    case 6: { //季报
	        //季报安排在seasonSelectFrom号凌晨6点
	       cycle = "0 0 6 "+seasonSelectFrom+" 1/3 ?" ;
	        break;
	      } 
	      
	      default:
	        cycle = "";
	    }
	     stSubscription.setCycle(cycle);
	   
	     //判断是否已经订阅同类型的报表 
	      // stat_mode=3 自定义 : stat_mode, item, subscriber,statfromdate,  stattodate作为查询条件
	      // 其他具有周期性质的定制: stat_mode, item, subscriber,  作为查询条件
	     IStSubscriptionManager mgr = (IStSubscriptionManager) getBean("IstSubscriptionManager");
	     //得出是否之前订阅相同的定制;
	     int olddata=mgr.getOldcustomdata(String.valueOf(stSubscription.getStatMode()),stSubscription.getItem(),userId,statFromDate,statToDate,remark);
	     if(olddata>0){
	    	 next="successed";	 
	     }
	     else{
	    	 
	    //把stSubscription对象的值付给 TawCommonsJobsort和TawCommonsJobsubscibe
	     //更新TawCommonsJobsort
	     TawCommonsJobsort tawCommonsJobsort=new TawCommonsJobsort();
	     tawCommonsJobsort.setDeleted(new Integer(0));
	     tawCommonsJobsort.setJobClassName(stSubscription.getClassName()); //轮训类
	     tawCommonsJobsort.setJobSortName(stSubscription.getItem()); //任务类型名称
	     //tawCommonsJobsort.setRemark(stSubscription.getRemark());    //用来放页面参数
	     tawCommonsJobsort.setMaxExecuteTime(new Integer(5));        //最大运行时间分
	     ITawCommonsJobsortManager mgr1 = (ITawCommonsJobsortManager) getBean("ItawCommonsJobsortManager");
	     mgr1.saveTawCommonsJobsort(tawCommonsJobsort);
	     int id= tawCommonsJobsort.getId().intValue();
	  	 //=====================
	     //更新TawCommonsJobsubscibe
	     stSubscription.setJobId(String.valueOf(id));
	     String subId = this.getSubId(stSubscription);
		  stSubscription.setSubId(subId);
	     
	     TawCommonsJobsubscibe tawCommonsJobsubscibe=new TawCommonsJobsubscibe();
	     tawCommonsJobsubscibe.setDeleted(new Integer(0));
	     tawCommonsJobsubscibe.setJobCycle(stSubscription.getCycle());
	     tawCommonsJobsubscibe.setJobSortId(new Integer(id));
	     tawCommonsJobsubscibe.setJobType(stSubscription.getType());
	     tawCommonsJobsubscibe.setSubId(stSubscription.getSubId());
	     tawCommonsJobsubscibe.setSubscriberDeptId(new Integer(stSubscription.getSubscribeDept()));
	     tawCommonsJobsubscibe.setSubscriberId(stSubscription.getSubscriber());
	     //tawCommonsJobsubscibe.setSubscribeTime(StaticMethod.getCurrentDateTime());
	     ITawCommonsJobsubscibeManager mgr2 = (ITawCommonsJobsubscibeManager) getBean("stjob");
	     mgr2.saveTawCommonsJobsubscibe(tawCommonsJobsubscibe,true); 
	     String jobid= tawCommonsJobsubscibe.getId(); //把TawCommonsJobsubscibe的id值放在stSubscription的sendDeptid字段里
	     
	    
	     // 更新StSubscription
	     stSubscription.setJobId(jobid);
	     //IStSubscriptionManager mgr = (IStSubscriptionManager) getBean("IstSubscriptionManager");
	     mgr.saveStSubscription(stSubscription);
	     
	     next="success";
	     }
	     
		}catch(Exception e){
    	    logger.info(e.toString());
	   	    return mapping.findForward("fail");	
		}
		return mapping.findForward(next);
	}

	private String getSubId(StSubscription stSubscription) throws Exception {
	     String subId = "";
	    String xyzLength = "1000";
	    String regionId="JOB";
	    String jobSortId = stSubscription.getJobId();
	    String strYYMMDD = StaticMethod.getYYMMDD();
	    subId += regionId + "-" + jobSortId + "-" + strYYMMDD + "-100" ;
	    IStSubscriptionManager mgr = (IStSubscriptionManager) getBean("IstSubscriptionManager");
	    int xyz= mgr.getCountSubId(subId)+1;
	  String strXYZ = String.valueOf(StaticMethod.null2int(xyzLength) + xyz);
	  subId += "-" + strXYZ.substring(1);
	    return subId;
	  }
	
	
	/**
	 * 根据模块或功能的编码，删除该对象 ----删除3个表
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
       try{
		StSubscriptionForm stSubscriptionForm = (StSubscriptionForm) form;
		TawSystemSessionForm sessionForm = this.getUser(request);
		String curUserId = sessionForm.getUserid();
		String id = stSubscriptionForm.getId();
		 
		IStSubscriptionManager mgr = (IStSubscriptionManager) getBean("IstSubscriptionManager");
		StSubscription stSubscription=mgr.getStSubscription(stSubscriptionForm.getId());
		String jobid=stSubscription.getJobId(); 
		mgr.removeStSubscription(stSubscriptionForm.getId());//删除stSubscription
		ITawCommonsJobsubscibeManager mgr2 = (ITawCommonsJobsubscibeManager) getBean("stjob");
		TawCommonsJobsubscibe tawCommonsJobsubscibe= mgr2.getTawCommonsJobsubscibe(jobid);
		int jobSortId=tawCommonsJobsubscibe.getJobSortId().intValue();
		mgr2.removeTawCommonsJobsubscibe(jobid); //删除TawCommonsJobsubscibe
		ITawCommonsJobsortManager mgr1 = (ITawCommonsJobsortManager) getBean("ItawCommonsJobsortManager");
		mgr1.removeTawCommonsJobsort(String.valueOf(jobSortId)); //删除TawCommonsJobsubscibe
		//StSubscription stSubscription = mgr.getStSubscription(id);
       }catch(Exception e){
    	    logger.info(e.toString());
	   	    return mapping.findForward("fail");
       }
		return main(mapping, form, request, response);
	}

	/**
	 * 初始化修改信息，将实体对象信息展现在页面表单??
	 */
	public ActionForward xeditinit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StSubscriptionForm stSubscriptionForm = (StSubscriptionForm) form;
		TawSystemSessionForm sessionForm = this.getUser(request);
		String curUserId = sessionForm.getUserid();
		String id = stSubscriptionForm.getId();
		if (id != null && !id.equals("")) {
			IStSubscriptionManager mgr = (IStSubscriptionManager) getBean("IstSubscriptionManager");
			StSubscription stSubscription = mgr.getStSubscription(id);
			stSubscriptionForm = (StSubscriptionForm) convert(stSubscription);
			request.setAttribute("stSubscriptionForm", stSubscriptionForm);
				return mapping.findForward("editpage");
			
		}
		return mapping.findForward("error");
	}
/*	查看详细信息*/
	public ActionForward look(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			StSubscriptionForm stSubscriptionForm = (StSubscriptionForm) form;
			TawSystemSessionForm sessionForm = this.getUser(request);
			String curUserId = sessionForm.getUserid();
			String id = stSubscriptionForm.getId();
		if (id != null && !id.equals("")) {
			IStSubscriptionManager mgr = (IStSubscriptionManager) getBean("IstSubscriptionManager");
			StSubscription stSubscription = mgr.getStSubscription(id);
	          stSubscriptionForm = (StSubscriptionForm) convert(stSubscription);
	          int statMode=stSubscriptionForm.getStatMode();
	  		String sMode="";
	  		 switch (statMode) {
	  	      case 1: { //周报
	  	    	  sMode="周报";
	  		      break;
	  		      }
	  	      case 2: { //月报
	  	    	  sMode="月报";
	  		      break;
	  		      }
	  	      case 3: { //自定义
	  	    	  sMode="自定义";
	  		      break;
	  		      }
	  	      case 4: { //日报
	  	    	  sMode="日报";
	  		      break;
	  		      }
	  	      case 5: { //年报
	  	    	  sMode="年报";
	  		      break;
	  		      }
	  	      case 6: { //季报
	  	    	  sMode="季报";
	  		      break;
	  		      }
	  		 }
	  	    stSubscriptionForm.setItemName(sMode);
	  	    
	  	  String item=stSubscriptionForm.getItem();
			String itemname="";
			if("commonfault-onetimepass-KPI2_oracle".equals(item)){
				itemname="故障工单_一次处理完成率统计";
			}
	        if("commonfault_delay_KPI3_oracle".equals(item)){
	        	itemname="故障工单_延期解决率统计";
			}if("commonfault_intime_KPI1_oracle".equals(item)){
				itemname="故障工单_故障处理及时率统计";
			}
			stSubscriptionForm.setItem(itemname);
	          
				request.setAttribute("stSubscriptionForm", stSubscriptionForm);
				// 跳转到修改页??
			return mapping.findForward("look");
			
		}
		return mapping.findForward("error");
	}

	/**
	 * 保存修改信息 ------修改3个表
	 */
	public ActionForward xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
		StSubscriptionForm stSubscriptionForm = (StSubscriptionForm) form;
		TawSystemSessionForm sessionForm = this.getUser(request);
		String curUserId = sessionForm.getUserid();
		String name = sessionForm.getUsername();
		String id = stSubscriptionForm.getId();
		try{
		if (id != null && !id.equals("")) {
			IStSubscriptionManager mgr = (IStSubscriptionManager) getBean("IstSubscriptionManager");
			//StSubscription stSubscription = (StSubscription) convert(stSubscriptionForm);
			StSubscription stSubscription = mgr.getStSubscription(id);
			//只考虑下面的几个字段才能修改
	    	stSubscription.setRemark(stSubscriptionForm.getRemark());
	    	stSubscription.setClassName(stSubscriptionForm.getClassName());
	    	stSubscription.setType(stSubscriptionForm.getType());
	    	stSubscription.setCycle(stSubscriptionForm.getCycle());
	    	stSubscription.setSubscribeTime(StaticMethod.getTimestamp(stSubscriptionForm.getSubscribeTime()));
	    	
		    mgr.saveStSubscription(stSubscription);
		    
		   
			String jobid=stSubscription.getJobId(); 
			ITawCommonsJobsubscibeManager mgr2 = (ITawCommonsJobsubscibeManager) getBean("stjob");
			TawCommonsJobsubscibe tawCommonsJobsubscibe= mgr2.getTawCommonsJobsubscibe(jobid);
			tawCommonsJobsubscibe.setJobCycle(stSubscriptionForm.getCycle());
			tawCommonsJobsubscibe.setJobType(stSubscriptionForm.getType());
			tawCommonsJobsubscibe.setSubscribeTime(stSubscriptionForm.getSubscribeTime());
			mgr2.saveTawCommonsJobsubscibe(tawCommonsJobsubscibe, true);
			int jobsortid=tawCommonsJobsubscibe.getJobSortId().intValue();
			
			ITawCommonsJobsortManager mgr1 = (ITawCommonsJobsortManager) getBean("ItawCommonsJobsortManager");
			TawCommonsJobsort tawCommonsJobsort=mgr1.getTawCommonsJobsort(String.valueOf(jobsortid));
			tawCommonsJobsort.setJobClassName(stSubscriptionForm.getClassName());
			tawCommonsJobsort.setRemark(stSubscriptionForm.getRemark());
			mgr1.saveTawCommonsJobsort(tawCommonsJobsort);
		
 			request.setAttribute("stSubscriptionForm", stSubscriptionForm);
		}
		}catch(Exception e){
			logger.info(e.toString());
	   	    return mapping.findForward("fail");
		}
		return mapping.findForward("success");
	}
	


	/**
	 * 根据组合条件查询
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
 		
		StSubscriptionForm StSubscriptionForm = (StSubscriptionForm) form;
		IStSubscriptionManager mgr = (IStSubscriptionManager) getBean("IstSubscriptionManager");

		String	startTime = StaticMethod.null2String(request.getParameter("startTime"));
		String	endTime =  StaticMethod.null2String(request.getParameter("endTime"));
		String  statMode=StaticMethod.null2String(request.getParameter("statMode"));
		String  item=StaticMethod.null2String(request.getParameter("item"));
		//TawSystemSessionForm sessionForm = this.getUser(request);
		
		StringBuffer condition = new StringBuffer(
				"");
		
  	 if (startTime != null && !startTime.equals("")) {
			condition.append(" and subscribeTime >= to_date('" + startTime + "','YYYY-MM-DD HH24:MI:SS')");
		}
		if (endTime != null && !endTime.equals("")) {
			condition.append(" and subscribeTime <=to_date('" + endTime + "','YYYY-MM-DD HH24:MI:SS')");
		}
		if (!"-1".equals(statMode)) {
		condition.append(" and  statMode='"+statMode+"'");
		}
		if (!"-1".equals(item)) {
			condition.append(" and  item='"+item+"'");
			}
		//根据具体情况具体分析className
		condition.append(" and className='com.boco.eoms.commons.statistic.customstat.scheduler.StatSchedulerV35' order by subscribeTime desc");
		conditions = condition.toString();
		List stSubscriptionList = mgr.getStSubscriptionsByCondition(conditions);
		request.setAttribute("stSubscriptionList", stSubscriptionList);
		
		return mapping.findForward("list");
	}
	
    /*
     * 返回的LIST
     */
	public ActionForward reback(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		IStSubscriptionManager mgr = (IStSubscriptionManager) getBean("IstSubscriptionManager");
		TawSystemSessionForm sessionForm = this.getUser(request);
		List stSubscriptionList = mgr.getStSubscriptionsByCondition(conditions);
		request.setAttribute("stSubscriptionList", stSubscriptionList);

		return mapping.findForward("list");
	}
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("search");
	}

	/**
	 * ajax请求获取某节点的详细信息
	 */
	public void xget(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String _strId = request.getParameter("id");
		IStSubscriptionManager mgr = (IStSubscriptionManager) getBean("IstSubscriptionManager");
		StSubscription stSubscription = mgr.getStSubscription(_strId);

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(stSubscription);

		JSONUtil.print(response, jsonRoot.toString());
	}

	public ActionForward newAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

	 
		return mapping.findForward("addpage");
	}

	public ActionForward approve(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String _strId = request.getParameter("id");
		IStSubscriptionManager mgr = (IStSubscriptionManager) getBean("IstSubscriptionManager");
		StSubscription stSubscription = mgr.getStSubscription(_strId);

		// approve the form
		return mapping.findForward("main");
	}

	/**
	 * 根据父节点的id得到??有子节点的JSON数据
	 */
	public void xGetChildNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
	}

	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// return mapping.findForward("search");
		return null;
	}
	
	public ActionForward customstatremind(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (ApplicationContextHolder.getInstance().getCtx() == null) 
		{
			ApplicationContextHolder.getInstance().setCtx(com.boco.eoms.base.util.ApplicationContextHolder.getInstance().getCtx());
		}
		IStSubscriptionManager subscriptionmanager = (IStSubscriptionManager)getBean("IstSubscriptionManager");
		StatCustomConfigManager statcustomconfigmgr = (StatCustomConfigManager)getBean("statCustomConfigManager");
		
		List resultList = new ArrayList();
		
		List listsubscription = subscriptionmanager.getStSubscriptions();
		
    	if(statcustomconfigmgr.getExcelConfigMap() == null || statcustomconfigmgr.getQueryCongigMap() == null)
    	{
    		statcustomconfigmgr.reLoadCustomConfig();
    	}
		
		
		if(listsubscription!=null){
			for(int i = 0;i<listsubscription.size();i++){
				StSubscription stsubscription = (StSubscription)listsubscription.get(i);
				String subId = stsubscription.getSubId();//SUB_ID
				
				String remark = stsubscription.getRemark();
				Map map =StatUtil.StringToMap(remark);
				
				String excelConfigURL = (String)map.get("excelConfigURL");
				String reportIndex = (String)map.get("reportIndex");
				
				Excel e = statcustomconfigmgr.getExcelConfig(excelConfigURL);
				Sheet s = e.getSheetByIndex(reportIndex);
				
				String sheetName = s.getSheetName();//SHEETNAME
				
				String[] sub = new String[2];
				sub[0]=subId;
				sub[1]=sheetName;
				
				resultList.add(sub);
			}
		}
		request.setAttribute("sheetnamelist", resultList);
        
		return mapping.findForward("customstatremind");
	}
	
	public ActionForward customstatremindsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] Str = request.getParameter("statname").split(",");
		String subId = Str[0];
		String statname = Str[1];
		String statType = request.getParameter("stattype");
		String year =  request.getParameter("year");
		String quarter = request.getParameter("quarter");
		String month = request.getParameter("month");
		String date = request.getParameter("date");
		String week = request.getParameter("week");
		String saveUser = request.getParameter("saveuser");
        String acceptDate = request.getParameter("acceptdate"); 
        String accepttype = "";
		String[] accepttypes = request.getParameterValues("accepttype");
		if(accepttypes!=null){
			for(int i = 0;i<accepttypes.length;i++){
				accepttype = accepttype+accepttypes[i]+",";
			}
		}
		CustomstatRemind customstatremind = new CustomstatRemind();
		customstatremind.setSubId(subId);
		customstatremind.setStatName(statname);
		customstatremind.setStatType(statType);
		customstatremind.setYear(year);
		customstatremind.setQuarter(quarter);
		customstatremind.setMonth(month);
		customstatremind.setDate(date);
		customstatremind.setWeek(week);
		customstatremind.setSaveUser(saveUser);
		customstatremind.setAcceptDate(acceptDate);
		customstatremind.setAccepttype(accepttype);
		
		if (ApplicationContextHolder.getInstance().getCtx() == null) 
		{
			ApplicationContextHolder.getInstance().setCtx(com.boco.eoms.base.util.ApplicationContextHolder.getInstance().getCtx());
		}
		ICustomstatRemindManager customstatRemindManager = (ICustomstatRemindManager)getBean("CustomstatRemindManager");
		customstatRemindManager.saveCustomstatRemind(customstatremind);
		return mapping.findForward("customstatremind_success");
	}
}
