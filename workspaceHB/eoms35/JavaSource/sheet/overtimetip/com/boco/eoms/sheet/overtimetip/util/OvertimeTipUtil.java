/**
 * 
 */
package com.boco.eoms.sheet.overtimetip.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.sms.WorkSheetSmsServices;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.overtimetip.model.OvertimeTip;
import com.boco.eoms.sheet.overtimetip.service.IOvertimeTipManager;


/**
 * @author jialei
 *
 */
public class OvertimeTipUtil {
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
	 * 根据映射读取工单中相应的数据，并将映射中的字段和值放入map中(只取超时字段)
	 * 
	 * @param flowName
	 * @return HashMap
	 */
	public static HashMap getMainColumnByMapping(String flowName){
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
					String overTimeFlag = filter.getOverTimeFlag();
					if(overTimeFlag != null && overTimeFlag.equals("true")){
					SheetInfo info = (SheetInfo)filter.getSheetInfo().get(0);
					String columnName = info.getName();
					
					map.put(specialty, columnName);
					}
				}
			}else{
				String tmpFlowName = schema.getFlowNameByTaskFlowName(flowName);
				filterList = schema.getTimeFilterListByFlow(tmpFlowName);
				if(filterList!=null&&filterList.size()>0){
					for(int i=0;i<filterList.size();i++){
						//得到映射字段的字段名
						TimeFilter filter = (TimeFilter)filterList.get(i);
						String specialty = filter.getName();
						String overTimeFlag = filter.getOverTimeFlag();
						if(overTimeFlag != null && overTimeFlag.equals("true")){
						SheetInfo info = (SheetInfo)filter.getSheetInfo().get(0);
						String columnName = info.getName();
						map.put(specialty, columnName);
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 根据映射读取工单中相应的数据，并将映射中的字段和值放入map中(取超时以外的字段字段)
	 * 
	 * @param flowName
	 * @return HashMap
	 */
	public static HashMap getNotOverTimeColumnByMapping(String flowName){
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
					String overTimeFlag = filter.getOverTimeFlag();
					if(overTimeFlag != null && overTimeFlag.equals("false")){
					SheetInfo info = (SheetInfo)filter.getSheetInfo().get(0);
					String columnName = info.getName();
					
					map.put(specialty, columnName);
					}
				}
			}else{
				String tmpFlowName = schema.getFlowNameByTaskFlowName(flowName);
				filterList = schema.getTimeFilterListByFlow(tmpFlowName);
				if(filterList!=null&&filterList.size()>0){
					for(int i=0;i<filterList.size();i++){
						//得到映射字段的字段名
						TimeFilter filter = (TimeFilter)filterList.get(i);
						String specialty = filter.getName();
						String overTimeFlag = filter.getOverTimeFlag();
						if(overTimeFlag != null && overTimeFlag.equals("false")){
						SheetInfo info = (SheetInfo)filter.getSheetInfo().get(0);
						String columnName = info.getName();
						map.put(specialty, columnName);
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}	
	
	/**
	 * 根据映射读取工单中相应的数据，并将映射中的字段和值放入map中(获取所有字段)
	 * 
	 * @param flowName
	 * @return HashMap
	 */
	public static HashMap getAllMainColumnByMapping(String flowName){
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
					
					map.put(specialty, columnName);
				}
			}else{
				String tmpFlowName = schema.getFlowNameByTaskFlowName(flowName);
				filterList = schema.getTimeFilterListByFlow(tmpFlowName);
				if(filterList!=null&&filterList.size()>0){
					for(int i=0;i<filterList.size();i++){
						//得到映射字段的字段名
						TimeFilter filter = (TimeFilter)filterList.get(i);
						String specialty = filter.getName();
						SheetInfo info = (SheetInfo)filter.getSheetInfo().get(0);
						String columnName = info.getName();
						map.put(specialty, columnName);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		map.put("title", "title");
		return map;
	}	
	

	/**
	 * 根据映射读取工单中相应的数据，并将映射中的字段和值放入map中
	 * 
	 * @param obj
	 * @return HashMap
	 */
	public static HashMap getOvertimetipValueMap(Object obj,String flowName){
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
	public static HashMap getHtmlByMapping(String flowName,OvertimeTip overtimetip){
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
					if(filter.getOverTimeFlag() != null && filter.getOverTimeFlag().equals("true") ){
					String specialty = filter.getName();
					SheetInfo info = (SheetInfo)filter.getSheetInfo().get(0);
					String columnCnName = info.getCnName();
					columnMap.put(specialty, columnCnName);
					list.add(specialty);
					//得到页面中的值
					HashMap valueMap = getOvertimetipValueMap(overtimetip,flowName);
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
	 * 根据taskFlowName判断是否配置了超时提醒
	 * 
	 * @param taskFlowName
	 * @return boolean
	 */
	public static boolean ifSupportOverTime(String taskFlowName){
		boolean SupportOverTime = false;
		try{
			String flowName="";
			TimeMapSchema schema = TimeMapSchema.getInstance();
			flowName = schema.getFlowNameByTaskFlowName(taskFlowName);
			if(flowName!=null&&!flowName.equals("")){
				SupportOverTime=true;
			}else{
				System.out.println("没有配置该工单的超时提醒！");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return SupportOverTime;
	}
	/**
	 * 判断overtimeTip对象是否符合条件
	 * 
	 * @param taskFlowName
	 * @return boolean
	 */
	public static boolean ifMatch(Map condition,OvertimeTip overtimeTip){
		boolean ifmatch = true;
		if(overtimeTip.getUserId().equals("system")){
			ifmatch = true;
		}else{
			Iterator it = condition.keySet().iterator();
			while(it.hasNext()){
				String special = (String)it.next();
				String value = (String)condition.get(special);
				if(special.equals("specialty1")){
					if(overtimeTip.getSpecialty1()==null||!overtimeTip.getSpecialty1().equals(value)){
							ifmatch=false;
					}
				}
				if(special.equals("specialty2")){
					if(overtimeTip.getSpecialty2()==null||!overtimeTip.getSpecialty2().equals(value)){
						ifmatch=false;
					}
				}
				if(special.equals("specialty3")){
					if(overtimeTip.getSpecialty3()==null||!overtimeTip.getSpecialty3().equals(value)){
						ifmatch=false;
					}
				}
				if(special.equals("specialty4")){
					if(overtimeTip.getSpecialty4()==null||!overtimeTip.getSpecialty4().equals(value)){
						ifmatch=false;
					}
				}
				if(special.equals("specialty5")){
					if(overtimeTip.getSpecialty5()==null||!overtimeTip.getSpecialty5().equals(value)){
						ifmatch=false;
					}
				}
				if(special.equals("specialty6")){
					if(overtimeTip.getSpecialty6()==null||!overtimeTip.getSpecialty6().equals(value)){
						ifmatch=false;
					}
				}
				if(special.equals("specialty7")){
					if(overtimeTip.getSpecialty7()==null||!overtimeTip.getSpecialty7().equals(value)){
						ifmatch=false;
					}
				}
				if(special.equals("specialty8")){
					if(overtimeTip.getSpecialty8()==null||!overtimeTip.getSpecialty8().equals(value)){
						ifmatch=false;
					}
				}
				if(special.equals("specialty9")){
					if(overtimeTip.getSpecialty9()==null||!overtimeTip.getSpecialty9().equals(value)){
						ifmatch=false;
					}
				}
				if(special.equals("specialty10")){
					if(overtimeTip.getSpecialty10()==null||!overtimeTip.getSpecialty10().equals(value)){
						ifmatch=false;
					}
				}
			}
		}
		return ifmatch;
	}
	/**
	 * 根据完成时限、超时配置得到超时类型：0未超时，1已超时，2将要超时
	 * @param columnMap TODO
	 * @param timeList为该用户在该工单的所有超时配置通过getEffectOvertimeTip得到
	 * 
	 * @param conditionMap为工单分类的字段值:specialty1-101010401  specialty2-10101040101 specialty3-1010104010101
	 * @return String
	 */
	public static String setOvertimeTipFlag(HashMap columnMap,Date completeTimeLimit, HashMap conditionMap,List timeList, String flowName){
		Iterator it = columnMap.keySet().iterator();
		int j=0;
		while(it.hasNext()){
			j++;
			conditionMap.remove(it.next());
		}		
		String overtimeType = "0";
		if(completeTimeLimit==null){
			return "0";
		}
		if(completeTimeLimit.before(new Date())){
			return "1";
		}
		Date setTime = null;
		String systemLimit = "";
		String tmpFlowName = "";
		String limit = "";
		if(timeList!=null&&timeList.size()>0){
			//如果有角色细分
			if(conditionMap.size()>0){
				for(int i=0;i<timeList.size();i++){
					OvertimeTip tempTip = (OvertimeTip)timeList.get(i);
					if(OvertimeTipUtil.ifMatch(conditionMap,tempTip)){
						if(tempTip.getUserId().equals("system")){
							if(tmpFlowName==null||tmpFlowName.equals("")){
								systemLimit = tempTip.getOvertimeLimit();
								tmpFlowName = tempTip.getFlowName();
							}
						}else{
							if(setTime==null){
								setTime = tempTip.getSetTime();
								limit = tempTip.getOvertimeLimit();
							}else{
								if(setTime.before(tempTip.getSetTime())){
									setTime = tempTip.getSetTime();
									limit = tempTip.getOvertimeLimit();
								}
							}
						}
					}
				}
			}else{
				//没有角色细分
				for(int i=0;i<timeList.size();i++){
					OvertimeTip tempTip = (OvertimeTip)timeList.get(i);
					if(tempTip.getUserId().equals("system")){
						systemLimit = tempTip.getOvertimeLimit();
					}else{
						if(setTime==null){
							setTime = tempTip.getSetTime();
							limit = tempTip.getOvertimeLimit();
						}else{
							if(setTime.before(tempTip.getSetTime())){
								setTime = tempTip.getSetTime();
								limit = tempTip.getOvertimeLimit();
							}
						}
					}
				}
			}
			//如果没有配置则使用系统默认的设置
			if(limit.equals("")){
				limit = systemLimit;
			}
			//判断是否超时或将要超时
			if(!limit.equals("")){
				if((completeTimeLimit.getTime()-new Date().getTime())/(1000*60)<=Integer.parseInt(limit)){
					overtimeType="2";
				}
			}
		}
		return overtimeType;
	}
	/**
	 * 得到工单的剩余时间
	 * @param columnMap TODO
	 * @param timeList为该用户在该工单的所有超时配置通过getEffectOvertimeTip得到
	 * 
	 * @param conditionMap为工单分类的字段值:specialty1-101010401  specialty2-10101040101 specialty3-1010104010101
	 * @return String
	 */
	public static long getOvertime(HashMap columnMap,Date completeTimeLimit, HashMap conditionMap,List timeList, String flowName){
		Iterator it = columnMap.keySet().iterator();
		int j=0;
		while(it.hasNext()){
			j++;
			conditionMap.remove(it.next()); 
		}		 
		if(completeTimeLimit==null){
			return 0;
		}
		return (completeTimeLimit.getTime()-new Date().getTime())/(1000*60);
	}
	/**
	 * 发送短信
	 * 
	 * @param obj
	 * @return HashMap
	 */
	public static void sendMsg(String flowName,String sheetKey,String sheetId,String receiverId) throws Exception {
		WorkSheetSmsServices service=new WorkSheetSmsServices();
		String title="工单超时提醒";
		int  receiveType=Constants.SMS_RECEIVE_TYPE_USER;
		service.workSM_NON_T(flowName, sheetKey, sheetId, title, receiveType,
                             receiverId);
	}
}
