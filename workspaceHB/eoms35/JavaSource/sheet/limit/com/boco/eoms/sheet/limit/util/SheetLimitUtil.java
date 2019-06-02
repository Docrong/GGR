/**
 * 
 */
package com.boco.eoms.sheet.limit.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.limit.model.LevelLimit;
import com.boco.eoms.sheet.limit.model.StepLimit;
import com.boco.eoms.sheet.limit.util.SheetInfo;
import com.boco.eoms.sheet.limit.util.TimeFilter;
import com.boco.eoms.sheet.limit.util.TimeMapSchema;
/**
 * @author Administrator
 *
 */
public class SheetLimitUtil {
	public static HashMap WeekMap = new HashMap();
	static{
		WeekMap.put("星期一", new Integer(Calendar.MONDAY));
		WeekMap.put("星期二", new Integer(Calendar.TUESDAY));
		WeekMap.put("星期三", new Integer(Calendar.WEDNESDAY));
		WeekMap.put("星期四", new Integer(Calendar.THURSDAY));
		WeekMap.put("星期五", new Integer(Calendar.FRIDAY));
		WeekMap.put("星期六", new Integer(Calendar.SATURDAY));
		WeekMap.put("星期日", new Integer(Calendar.SUNDAY));
	}
	
	/**
	 * 得到根据specialitys查询的sql语句
	 * 
	 * @param obj
	 * @return HashMap
	 */
	public static String makeSqlBySpecialsMap(HashMap specialsMap){
		Iterator keyIt = specialsMap.keySet().iterator();
		StringBuffer sb = new StringBuffer();
		while(keyIt.hasNext()){
			String key = (String)keyIt.next();
			String value = StaticMethod.nullObject2String(specialsMap.get(key));
			if(value.equals("")){
				sb.append("(levelLimit."+key+" is null or levelLimit."+key+"='') and ");
			}else{
				sb.append("levelLimit."+key+"='"+specialsMap.get(key)+"' and ");
			}
		}
		return sb.substring(0, sb.length()-4);
	}
	/**
	 * 得到根据specialitys查询的sql语句
	 * 
	 * @param obj
	 * @return HashMap
	 */
	public static HashMap makeSpecialsMap(LevelLimit levelLimit){
		HashMap specialsMap = new HashMap();
		specialsMap.put("level1", levelLimit.getLevel1());
		specialsMap.put("level2", levelLimit.getLevel2());
		specialsMap.put("level3", levelLimit.getLevel3());
		specialsMap.put("specialty1", levelLimit.getSpecialty1());
		specialsMap.put("specialty2", levelLimit.getSpecialty2());
		specialsMap.put("specialty3", levelLimit.getSpecialty3());
		specialsMap.put("specialty4", levelLimit.getSpecialty4());
		specialsMap.put("specialty5", levelLimit.getSpecialty5());
		specialsMap.put("specialty6", levelLimit.getSpecialty6());
		specialsMap.put("specialty7", levelLimit.getSpecialty7());
		specialsMap.put("specialty8", levelLimit.getSpecialty8());
		return specialsMap;
	}
	/**
	 * 得到根据specialityMap得到levelLimit对象
	 * 
	 * @param obj
	 * @return HashMap
	 */
	public static LevelLimit makelevelLimitByMap(HashMap specialsMap){
		LevelLimit levelLimit = new LevelLimit();
		levelLimit.setLevel1(StaticMethod.nullObject2String(specialsMap.get("level1")));
		levelLimit.setLevel2(StaticMethod.nullObject2String(specialsMap.get("level2")));
		levelLimit.setLevel3(StaticMethod.nullObject2String(specialsMap.get("level3")));
		levelLimit.setSpecialty1(StaticMethod.nullObject2String(specialsMap.get("specialty1")));
		levelLimit.setSpecialty2(StaticMethod.nullObject2String(specialsMap.get("specialty2")));
		levelLimit.setSpecialty3(StaticMethod.nullObject2String(specialsMap.get("specialty3")));
		levelLimit.setSpecialty4(StaticMethod.nullObject2String(specialsMap.get("specialty4")));
		levelLimit.setSpecialty5(StaticMethod.nullObject2String(specialsMap.get("specialty5")));
		levelLimit.setSpecialty6(StaticMethod.nullObject2String(specialsMap.get("specialty6")));
		levelLimit.setSpecialty7(StaticMethod.nullObject2String(specialsMap.get("specialty7")));
		levelLimit.setSpecialty8(StaticMethod.nullObject2String(specialsMap.get("specialty8")));
		return levelLimit;
	}
	/**
	 * 得到根据specialitys查询的sql语句，如果全匹配时没有查询到记录，则查询上一级是否有记录
	 * 
	 * @param obj
	 * @return HashMap
	 */
	public static String makeTopSpecialsMap(HashMap specialsMap){
		String tmpKey = null;
		int i = 0;
		if(specialsMap.size()>0){
			Iterator it = specialsMap.keySet().iterator();
			while(it.hasNext()){
				String key = (String)it.next();
				String value = StaticMethod.nullObject2String(specialsMap.get(key));
				if(!value.equals("")){
					if(key.indexOf("specialty")!=-1){
						try{
							if(i < Integer.parseInt(key.substring(key.length()-1))){
								i = Integer.parseInt(key.substring(key.length()-1));
								tmpKey = key;
							}
						}catch(Exception e){}
					}
				}
			}
		}
		return tmpKey;
	}
	/**
	 * 得到根据specialitys查询的sql语句
	 * 
	 * @param obj
	 * @return HashMap
	 */
	public static LevelLimit makeLevelLimit(HttpServletRequest request) throws Exception{
		LevelLimit levelLimit = new LevelLimit();
		Method[] methods = levelLimit.getClass().getMethods();
		for(int i=0;i<methods.length;i++){
			Method tmpMethod = methods[i];
			String methodstr = tmpMethod.getName();
			if(methodstr.substring(0,3).equals("set")){
				methodstr = methodstr.substring(3);
				String fieldstr = methodstr.substring(0,1).toLowerCase()+methodstr.substring(1);
				String fieldValue = StaticMethod.nullObject2String(request.getParameter(fieldstr));
				String setMethod = "set"+ StaticMethod.firstToUpperCase(fieldstr);
				Method setterMethod = levelLimit.getClass().getMethod(setMethod, new Class[] {fieldValue.getClass()});
				setterMethod.invoke(levelLimit,new Object[] {fieldValue});
			}
		}
		return levelLimit;
	}
	/**
	 * 根据映射读取工单中相应的数据，并将映射中的字段和值放入map中
	 * 
	 * @param obj
	 * @return HashMap
	 */
	public static HashMap getConditionByMapping(Object obj,String flowName){
		String getMethod,beanValueId;
		Method getterMethod;
		Object beanValue;
		HashMap map = new HashMap();
		try{
			//读取配置文件
			TimeMapSchema schema = TimeMapSchema.getInstance();
			List filterList = schema.getTimeFilterListByFlow(flowName);
			if(filterList!=null&&filterList.size()>0){
				for(int i=0;i<filterList.size();i++){
					//得到映射字段的字段名
					TimeFilter filter = (TimeFilter)filterList.get(i);
					String specialty = filter.getName();
					SheetInfo info = (SheetInfo)filter.getSheetInfo().get(0);
					String columnName = info.getName();
					//得到对应字段的值
					getMethod = "get" + StaticMethod.firstToUpperCase(columnName);
					getterMethod = obj.getClass().getMethod(getMethod, new Class[] {});
					beanValue = getterMethod.invoke(obj,new Object[] {});
					beanValueId = StaticMethod.nullObject2String(beanValue);
					
					map.put(specialty, beanValueId);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 根据映射读取工单中相应的数据，并将映射中的字段和值放入map中
	 * 
	 * @param obj
	 * @return HashMap
	 */
	public static HashMap getStepLimitValueMap(Object obj,String flowName){
		String getMethod,beanValueId;
		Method getterMethod;
		Object beanValue;
		HashMap map = new HashMap();
		try{
			//读取配置文件
			TimeMapSchema schema = TimeMapSchema.getInstance();
			List filterList = schema.getTimeFilterListByFlow(flowName);
			if(filterList!=null&&filterList.size()>0){
				for(int i=0;i<filterList.size();i++){
					//得到映射字段的字段名
					TimeFilter filter = (TimeFilter)filterList.get(i);
					String specialty = filter.getName();
					//得到对应字段的值
					getMethod = "get" + StaticMethod.firstToUpperCase(specialty);
					getterMethod = obj.getClass().getMethod(getMethod, new Class[] {});
					beanValue = getterMethod.invoke(obj,new Object[] {});
					beanValueId = StaticMethod.nullObject2String(beanValue);
					
					map.put(specialty, beanValueId);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 根据映射读取工单中映射中的字段
	 * 
	 * @param obj
	 * @return HashMap
	 */
	public static HashMap getColumnByMapping(String flowName){
		HashMap map = new HashMap();
		try{
			TimeMapSchema schema = TimeMapSchema.getInstance();
			List filterList = schema.getTimeFilterListByFlow(flowName);
			if(filterList!=null&&filterList.size()>0){
				for(int i=0;i<filterList.size();i++){
					//得到映射字段的字段名
					TimeFilter filter = (TimeFilter)filterList.get(i);
					String specialty = filter.getName();
					SheetInfo info = (SheetInfo)filter.getSheetInfo().get(0);
					String columnCnName = info.getCnName();
					map.put(specialty, columnCnName);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 根据映射读取工单中映射中的字段在页面中的html元素和属性
	 * 
	 * @param obj
	 * @return HashMap
	 */
	public static HashMap getHtmlByMapping(String flowName,LevelLimit levelLimit){
		HashMap map = new HashMap();
		List list = new ArrayList();
		HashMap htmlMap = new HashMap();
		HashMap columnMap = new HashMap();
		try{
			TimeMapSchema schema = TimeMapSchema.getInstance();
			List filterList = schema.getTimeFilterListByFlow(flowName);
			if(filterList!=null&&filterList.size()>0){
				for(int i=0;i<filterList.size();i++){
					//得到映射字段的字段名
					TimeFilter filter = (TimeFilter)filterList.get(i);
					String specialty = filter.getName();
					SheetInfo info = (SheetInfo)filter.getSheetInfo().get(0);
					String columnCnName = info.getCnName();
					columnMap.put(specialty, columnCnName);
					list.add(specialty);
					//得到页面中的值
					HashMap valueMap = getStepLimitValueMap(levelLimit,flowName);
					if(filter.getHtmlType().equals("dict")){
						//根据sub得到字典的关联下一级
						String sub = filter.getSub();
						if(sub!=null&&!sub.equals("")){
							TimeFilter tempfilter = schema.getTimeFilterBybusinessName(flowName, sub);
							if(tempfilter!=null){
								sub = tempfilter.getName();
							}
							filter.setSub(sub);
						}
						//根据dict得到字典在页面中的值
						String dictid = filter.getDictId();
						if(dictid==null||dictid.equals("")){
							TimeFilter tempfilter = schema.getTimeFilterBySub(flowName, filter.getName());
							if(tempfilter==null){
								tempfilter = schema.getTimeFilterBySub(flowName, filter.getBusinessName());
							}
							String tmpspecialty = tempfilter.getName();
							String value = (String)valueMap.get(tmpspecialty);
							filter.setDictId(value);
						}
					}else if(filter.getHtmlType().equals("text")){
						
					}
					htmlMap.put(specialty, filter);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		map.put("columnList", list);
		map.put("columnMap", columnMap);
		map.put("htmlMap", htmlMap);
		return map;
	}
	/**
	 * 根据taskFlowName得到相应的flowName
	 * 
	 * @param taskFlowName
	 * @return String
	 */
	public static String getFlowNameBytaskFlowName(String taskFlowName){
		String flowName = null;
		try{
			TimeMapSchema schema = TimeMapSchema.getInstance();
			flowName = schema.getFlowNameByTaskFlowName(taskFlowName);
			if(flowName==null||flowName.equals(""))
				flowName=taskFlowName;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flowName;
	}
	/**
	 * 根据taskFlowName判断是否配置了处理时限
	 * 
	 * @param taskFlowName
	 * @return boolean
	 */
	public static boolean ifSupportTime(String taskFlowName){
		boolean SupportOverTime = false;
		try{
			String flowName="";
			TimeMapSchema schema = TimeMapSchema.getInstance();
			flowName = schema.getFlowNameByTaskFlowName(taskFlowName);
			if(flowName!=null&&!flowName.equals("")){
				SupportOverTime=true;
			}else{
				System.out.println("没有配置该工单的处理时限！");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return SupportOverTime;
	}
	/**
	 * 根据Flow得到不需要配置处理时限的步骤名
	 * 
	 * @param FlowName
	 * @return String
	 */
	public static String getExceptTask(String flowName){
		String exceptTask = null;
		try{
			TimeMapSchema schema = TimeMapSchema.getInstance();
			exceptTask = schema.getExceptTaskByFlow(flowName);
		}catch(Exception e){
			e.printStackTrace();
		}
		return exceptTask;
	}
	/**
	 * 读取配置文件得到该工单的工作时间，如果不是7－24工作时间，则返回包括休息时间的时限值（分钟）
	 * @param date
	 * @param limit
	 * @param flowName
	 * @return
	 */
	public static int getTimeLimitByConfig(Date date,int relateLimit,int limit,String flowName){
		//System.out.println("getTimeLimitByConfig() Start!!!");
		String type = "";
		List workTimeList = null;
		List dayOffList = null;
		String cumulativeType = "";
		//读取配置文件sheet-sheetLimit-mapping.xml得到工作时间配置
		try{
			TimeMapSchema schema = TimeMapSchema.getInstance();
			TimeWorktime timeWorkTime = schema.getTimeWorkTime(flowName);
			if(timeWorkTime==null){
				type = "7-24";
			}else{
				type = timeWorkTime.getType();
				cumulativeType = timeWorkTime.getCumulativeType();
				if(!type.equals("7-24")){
					workTimeList = timeWorkTime.getWorkTime();
					dayOffList = timeWorkTime.getDayOff();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//循环得到工作时间和休息时间
		List beginWork = new ArrayList();//工作开始时间
		List endWork = new ArrayList();//工作结束时间
		List holidayList = new ArrayList();//休息时间
		
		if(!type.equals("7-24")){
			if(workTimeList!=null&&workTimeList.size()>0){
				for(int i=0;i<workTimeList.size();i++){
					Calendar calBegin = Calendar.getInstance();
					Calendar calEnd = Calendar.getInstance();
					
					WorkTime tmpWorkTime = (WorkTime)workTimeList.get(i);
					String startTime = tmpWorkTime.getWorkStartTime();
					String endTime = tmpWorkTime.getWorkEndTime();
					if(startTime.indexOf(":")!=-1){
						int tempint = startTime.indexOf(":");
						String tmpstr1 = startTime.substring(0,tempint);
						String tmpstr2 = startTime.substring(tempint+1);
						calBegin.set(Calendar.HOUR_OF_DAY, Integer.parseInt(tmpstr1));
						calBegin.set(Calendar.MINUTE, Integer.parseInt(tmpstr2));
						calBegin.set(Calendar.SECOND, 0);
						calBegin.set(Calendar.MILLISECOND, 0);
						beginWork.add(calBegin);
						
						tempint = endTime.indexOf(":");
						tmpstr1 = endTime.substring(0,tempint);
						tmpstr2 = endTime.substring(tempint+1);
						calEnd.set(Calendar.HOUR_OF_DAY, Integer.parseInt(tmpstr1));
						calEnd.set(Calendar.MINUTE, Integer.parseInt(tmpstr2));
						calEnd.set(Calendar.SECOND, 0);
						calEnd.set(Calendar.MILLISECOND, 0);
						endWork.add(calEnd);
					}
				}
			}
			if(dayOffList!=null&&dayOffList.size()>0){
				for(int i=0;i<dayOffList.size();i++){
					DayOff tmpDayOff = (DayOff)dayOffList.get(i);
					if(tmpDayOff!=null){
						String value = tmpDayOff.getValue();
						if(value!=null&&!value.equals("")){
							holidayList.add(WeekMap.get(value));
						}
					}
				}
			}
			Calendar cal =  Calendar.getInstance();
			cal.setTime(date);
			//如果是累积的方式计算时限
			if(cumulativeType.equals("yes")){
				cal.add(Calendar.MINUTE, relateLimit);
			}
			try{
				long returnvalue = getRestTimeLimitMillSecond(cal, beginWork, endWork, limit, holidayList);
				limit = Integer.parseInt(Long.toString(returnvalue/(1000*60)));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(!cumulativeType.equals("yes")){
			limit = limit - relateLimit;
		}
		return limit;
	}
	
	
	/**
	 * 考虑工作时间后的时限配置，其中cal一般为当前时间或派单时间，beginWork为工作开始时间的列表，
	 * endWork为工作结束时间的列表，dealLimit为时限值（最大值为525600），holidayOfWeek为周末休息日期（周六为7、周日为1）
	 * @param beginWork
	 * @param endWork
	 * @param dealLimit
	 * @return restTimeLimit 真实的时限（分钟）
	 * @throws Exception
	 */
	  private static long getRestTimeLimitMillSecond(Calendar cal,List beginWork, List endWork, long dealLimit, List holidayOfWeek) throws Exception {
		  //System.out.println("getRestTimeLimitMillSecond() Start!!!");
		  System.out.println("limit: "+dealLimit);
		long restTimeLimit = 0;
		try {
			if (beginWork != null && endWork != null && beginWork.size() != 0
					&& beginWork.size() == endWork.size()) {
				int ibeginlength = beginWork.size();
				long iTimeleft = dealLimit * 60 * 1000;

				// 开始时间，暂定为：系统当前时间
				if(cal==null){
					cal = Calendar.getInstance();
				}
				Calendar calBegin = (Calendar) cal.clone();
				Calendar calEnd = (Calendar) cal.clone();
				Calendar calEndTemp = (Calendar) cal.clone();
				
				while (iTimeleft > 0) {
					for (int j = 0; j < ibeginlength; j++) {
						long limit = 0;
						long limitSecond = 0;
						long iLeft = 0;
						//为工作时间赋日期值
						calBegin = (Calendar) beginWork.get(j);
						calEnd = (Calendar) endWork.get(j);

						String year = String.valueOf(cal.get(Calendar.YEAR));
						String month = String.valueOf(cal.get(Calendar.MONTH));
						String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
						
						calBegin.set(Calendar.YEAR, Integer.parseInt(year));
						calBegin.set(Calendar.MONTH,Integer.parseInt(month));
						calBegin.set(Calendar.DATE, Integer.parseInt(day));

						calEnd.set(Calendar.YEAR, Integer.parseInt(year));
						calEnd.set(Calendar.MONTH, Integer.parseInt(month));
						calEnd.set(Calendar.DATE, Integer.parseInt(day));
						
						//如果一天有多个工作时间段
						if (j != 0) {
							calEndTemp = (Calendar) endWork.get(j - 1);
							calEndTemp.set(Calendar.YEAR, Integer.parseInt(year));
							calEndTemp.set(Calendar.MONTH, Integer.parseInt(month));
							calEndTemp.set(Calendar.DATE, Integer.parseInt(day));
							//在上一段工作时间结束的时点派单
							if (calEndTemp.equals(cal)) {
								long tempLong = calBegin.getTimeInMillis()- cal.getTimeInMillis();
								limit = tempLong;
								limitSecond = tempLong;

								String sec = String.valueOf(limitSecond);
								cal.add(Calendar.MILLISECOND, Integer.parseInt(sec));
								restTimeLimit += limit;
							}
						}
						
						
						if (cal.equals(calBegin)|| (cal.after(calBegin) && cal.before(calEnd))) {
							//处于上班时间
							//该时限离工作结束还有多长时间
							long tempLong = calEnd.getTimeInMillis()- cal.getTimeInMillis();
							limit = tempLong;
							iLeft = tempLong;
							limitSecond = tempLong;
						}else if(cal.before(calBegin)&&(j==0||(j>0&&cal.after((Calendar)(endWork.get(j-1)))))){
							//如果早于该段的上班时间
							long tempLong = calBegin.getTimeInMillis()- cal.getTimeInMillis();
							String sec = String.valueOf(tempLong);
							cal.add(Calendar.MILLISECOND, Integer.parseInt(sec));
							restTimeLimit += tempLong;
								
							tempLong = calEnd.getTimeInMillis()- cal.getTimeInMillis();
							limit = tempLong;
							iLeft = tempLong;
							limitSecond = tempLong;
						}else {
							//在非工作时间派单
							//如果是最后的工作开始时间，则取得第二天的第一个工作开始时间
							if (j + 1 >= beginWork.size()) {
								calBegin = (Calendar) beginWork.get(0);
								calBegin.set(Calendar.YEAR, Integer.parseInt(year));
								calBegin.set(Calendar.MONTH, Integer.parseInt(month));
								calBegin.set(Calendar.DATE, Integer.parseInt(day));
								calBegin.add(Calendar.MINUTE, 24 * 60);
								//如果有周末休息时间
								if(holidayOfWeek!=null&&holidayOfWeek.size()>0){
									int dayofweek = calBegin.get(Calendar.DAY_OF_WEEK);
									while(holidayOfWeek.contains(new Integer(dayofweek))){
										calBegin.add(Calendar.MINUTE, 24 * 60);
										dayofweek = calBegin.get(Calendar.DAY_OF_WEEK);
									}
								}
							} else {
								continue;
							}
							long tempLong = calBegin.getTimeInMillis() - cal.getTimeInMillis();
							limit = tempLong;
							limitSecond = tempLong;

						}
						//判断加上时限后是否还在本段工作时间内
						if (iTimeleft - iLeft <= 0) {
							String sec = String.valueOf(iTimeleft);
							cal.add(Calendar.MILLISECOND, Integer.parseInt(sec));
							restTimeLimit += iTimeleft;
							iLeft = iTimeleft;
						} else {
							String sec = String.valueOf(limitSecond);
							cal.add(Calendar.MILLISECOND, Integer.parseInt(sec));
							restTimeLimit += limit;
						}
						iTimeleft = iTimeleft - iLeft;
					}
				}
			}
		} catch (Exception e) {
			restTimeLimit = dealLimit * 60 * 1000;
		}
		System.out.println("real limit : " + restTimeLimit/(60*1000));
		//System.out.println("getRestTimeLimitMillSecond() End!!!");
		return restTimeLimit;
	}
	  
	/**
	 * 将日期和延迟加到一起，返回yyyy-MM-dd HH:mm:ss格式的日期字符串
	 * @param c
	 * @param millSecond
	 * @return
	 */
	public static String getLocalStringByMillSecond(Calendar c, long millSecond) {
		if (c == null) {
			c = Calendar.getInstance();
		}
		//System.out.println("long max:"+Long.MAX_VALUE/1000*60);
		String senond = "";
		while (millSecond > Integer.MAX_VALUE) {
			senond = String.valueOf(Integer.MAX_VALUE);
			c.add(Calendar.MILLISECOND, Integer.parseInt(senond));
			millSecond -= Integer.MAX_VALUE;
		}
		senond = String.valueOf(millSecond);
		c.add(Calendar.MILLISECOND, Integer.parseInt(senond));
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
		String date = dateFormat.format(c.getTime());

		return date;
	}
	  
	  
	  
	  
	  
	  public static void main(String[] avg) {
		List beginWork = new ArrayList();
		List endWork = new ArrayList();
		List holidayList = new ArrayList();

		Calendar calBegin1 = Calendar.getInstance();
		Calendar calEnd1 = Calendar.getInstance();

		Calendar calBegin2 = Calendar.getInstance();
		Calendar calEnd2 = Calendar.getInstance();
		calBegin1.set(Calendar.HOUR_OF_DAY, 8);
		calBegin1.set(Calendar.MINUTE, 30);
		calBegin1.set(Calendar.SECOND, 0);
		calBegin1.set(Calendar.MILLISECOND, 0);

		calEnd1.set(Calendar.HOUR_OF_DAY, 17);
		calEnd1.set(Calendar.MINUTE, 30);
		calEnd1.set(Calendar.SECOND, 0);
		calEnd1.set(Calendar.MILLISECOND, 0);

		calBegin2.set(Calendar.HOUR_OF_DAY, 14);
		calBegin2.set(Calendar.MINUTE, 30);
		calBegin2.set(Calendar.SECOND, 0);
		calBegin2.set(Calendar.MILLISECOND, 0);

		calEnd2.set(Calendar.HOUR_OF_DAY, 18);
		calEnd2.set(Calendar.MINUTE, 0);
		calEnd2.set(Calendar.SECOND, 0);
		calEnd2.set(Calendar.MILLISECOND, 0);

		beginWork.add(calBegin1);
		endWork.add(calEnd1);
		beginWork.add(calBegin2);
		endWork.add(calEnd2);

		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 8);
		c.set(Calendar.MINUTE, 30);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		try {
			//long returnvalue = getRestTimeLimitMillSecond(c1, beginWork, endWork, 525600, holidayList);
			Date tmpDate = c.getTime();
			Calendar c3 = (Calendar)c.clone();
			
			int returnvalue = getTimeLimitByConfig(tmpDate,541,539,"SoftChangeMainProcess");
			c3.add(Calendar.MINUTE, 541);
			String tmp = getLocalStringByMillSecond(c3, returnvalue*60*1000);

			System.out.println("tmp:" + tmp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
