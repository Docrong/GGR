package com.boco.eoms.duty.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.boco.eoms.base.api.EOMSMgr;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.dict.model.DictItemXML;
import com.boco.eoms.commons.system.dict.util.Constants;
import com.boco.eoms.duty.webapp.form.AttemperContrastForm;
import com.boco.eoms.duty.webapp.form.TawRmDutyEventForm;

public class CommonTools {
	
	/**
	 * 返回时间值差
	 * @param 开始时间和结束时间
	 * @return 时间值差
	 */
	public static int getResumeTimeSlot(String beginTime,String endTime){
		int timeSlot = 0;
		try {
            if (beginTime != null && endTime != null) {
              SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              Calendar begin = Calendar.getInstance();
              begin.setTime(dateFormat.parse(beginTime));
              Calendar end = Calendar.getInstance();
              end.setTime(dateFormat.parse(endTime));
              timeSlot = (int) (end.getTimeInMillis() - begin.getTimeInMillis()) /
                  (1000 * 60);
            }
          }  catch(Exception e) {
            e.printStackTrace();
          }

          return timeSlot;
	}
	
	/**
	 * 判断一个时间是否在另一时间前
	 * @param date1 String
	 * @param date2 String
	 * @return boolean
	 */
	public static boolean isDateBefore(String date1, String date2) {
		try {
			if (date1 == null || date1.equals(""))
				return true;
			if (date2 == null || date2.equals(""))
				return false;
			DateFormat df = DateFormat.getDateTimeInstance();
			return df.parse(date1).before(df.parse(date2));
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 根据小时来算时间
	 *
	 * @param disday
	 *            float
	 * @return String
	 */
	public static String getTimeStringByHour(long dishour) {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long timeCount = 0;
		Date date = null;

		try {
			timeCount = System.currentTimeMillis() + dishour * 60 * 60 * 1000;
			date = new Date(timeCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return StaticMethod.getLocalString(d.format(date));
	}
	
	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 *
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}
	

	/**
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	 */
	public static String getTimeString(int delay) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String mdate = "";
			Date d = strToDate(StaticMethod.getCurrentDateTime());
			long myTime = (d.getTime() / 1000) + delay * 24 * 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return StaticMethod.getLocalString(mdate);
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 解析类别
	 */
	public static List parseTypeList(String typeIdList){
		List typeList = new ArrayList();
		if(typeIdList!=null) {
			typeIdList = typeIdList.replaceAll("#","");
			String typeArray[] = typeIdList.split(",");
			for(int i=0;i<typeArray.length;i++) {
				typeList.add(typeArray[i]);
			}
			return typeList;
		} else {
			return new ArrayList();
		}
	}
	
	/**
	 * 返回类别数据字典
	 */
	public static String getTypeName(List typeList) {
		StringBuffer typeName = new StringBuffer();
		if(typeList.size()>0)
		typeName.append(EOMSMgr.getSysMgrs().getDictMgrs().getID2NameMgr().id2Name((String)typeList.get(0), "tawSystemDictTypeDao"));
		
		for(int i=1;i<typeList.size();i++) {
			typeName.append("," + EOMSMgr.getSysMgrs().getDictMgrs().getID2NameMgr().id2Name((String)typeList.get(i), "tawSystemDictTypeDao"));
		}

		return typeName.toString();
	}
	
	/**
	 * 返回类别数据字典,数据字典类型是从XML获取
	 */
	public static String getTypeNameFromXML(List typeList) throws Exception {
		StringBuffer typeName = new StringBuffer();
		try {
			String dictId = "dict-duty" + Constants.DICT_ID_SPLIT_CHAR + AttemperContrastForm.CABLECLASSDICT;
			if(typeList.size()>0)
			typeName.append(EOMSMgr.getSysMgrs().getDictMgrs().getXMLDictMgr().itemId2name(dictId,(String)typeList.get(0)));
			
			for(int i=1;i<typeList.size();i++) {
				typeName.append("," + EOMSMgr.getSysMgrs().getDictMgrs().getXMLDictMgr().itemId2name(dictId,(String)typeList.get(i)));
			}
		} catch(Exception e) {
			
		}
		return typeName.toString();
	}
	
	/**
	 * 返回数据字典,数据字典类型是从XML获取
	 */
	public static String getDictNameFromXML(String dictId,String itemId) throws Exception {
		return EOMSMgr.getSysMgrs().getDictMgrs().getXMLDictMgr().itemId2name(dictId,itemId).toString();
	}
	
	/**
	 * 返回类别数据字典,数据字典类型是从XML获取
	 */
	public static String getTypeNameFromXML(List typeList,String dictIdName) throws Exception {
		StringBuffer typeName = new StringBuffer();
		try {
			String dictId = "dict-duty" + Constants.DICT_ID_SPLIT_CHAR + dictIdName;
			if(typeList.size()>0)
			typeName.append(EOMSMgr.getSysMgrs().getDictMgrs().getXMLDictMgr().itemId2name(dictId,(String)typeList.get(0)));
			
			for(int i=1;i<typeList.size();i++) {
				typeName.append("," + EOMSMgr.getSysMgrs().getDictMgrs().getXMLDictMgr().itemId2name(dictId,(String)typeList.get(i)));
			}
		} catch(Exception e) {
			
		}
		return typeName.toString();
	}
	
	/**
	 * 获取故障事件机房映像信息
	 */
	public static Map getTawRmDutyEventMap() throws Exception{
		List list = getTawRmDutyEventList();
		Map roomMap = new HashMap();
		for(int i=0;i<list.size();i++) {
			DictItemXML dictItemXML = (DictItemXML)list.get(i);
			roomMap.put(dictItemXML.getItemId(), dictItemXML);
		}
		return roomMap;
	}
	
	/**
	 * 根据机房ID获取对应的相关部门ID号，数据从数据字典中获取
	 */
	public static String getItemId(String roomId) throws Exception{
		List list = getTawRmDutyEventList();
		for(int i=0;i<list.size();i++) {
			DictItemXML dictItemXML = (DictItemXML)list.get(i);
			if(roomId.equals(dictItemXML.getItemDescription())) {
				return dictItemXML.getItemDescription().toString();
			}
		}
		return "";
	}
	
	/**
	 * 获取故障事件机房映像信息
	 */
	public static List getTawRmDutyEventList() throws Exception{
		String dictId = "dict-duty" + Constants.DICT_ID_SPLIT_CHAR + TawRmDutyEventForm.REGIONSDICT;
		return EOMSMgr.getSysMgrs().getDictMgrs().getXMLDictMgr().getDictItems(dictId);
	}
	
	/**
	 * 得到某个时间前几小时的时间s
	 * @param str
	 * @param i
	 * @return
	 */
	public static String getTimeString(String str,int i) {
	    String time = "";

	    Vector temp = StaticMethod.getVector(str, " ");

	    if (!temp.isEmpty()) {
	      Vector first = StaticMethod.getVector(temp.get(0).toString(), "-");
	      Vector last = StaticMethod.getVector(temp.get(1).toString(), ":");

	      int year = Integer.parseInt(first.get(0).toString());
	      int month = Integer.parseInt(first.get(1).toString());
	      int day = Integer.parseInt(first.get(2).toString());
	      int hour = Integer.parseInt(last.get(0).toString());
	      String minute = last.get(1).toString();
	      String second = last.get(2).toString();
	      hour += i ;
	      if (hour>24)
	      {
	        day += hour/24;
	        hour = hour-24*(hour/24);
	         if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
	           if(day>31){
	             if(month!=12){
	               month += 1;
	               day = day - 31;
	             }
	             else{
	               day = day - 31;
	               month=1;
	               year +=1;
	             }
	           }
	         }
	         else{
	           if(day>30){
	             if(month!=12){
	               month += 1;
	               day = day - 30;
	             }
	             else{
	               day = day - 30;
	               month=1;
	               year +=1;
	             }
	           }
	         }
	      }
	      String yearString = Integer.toString(year);
	      String monthString = Integer.toString(month);
	      String dayString = Integer.toString(day);
	      String hourString = Integer.toString(hour);
	      if (monthString.length() == 1)
	        monthString = "0" + monthString;
	      if (dayString.length() == 1)
	        dayString = "0" + dayString;
	      if (hourString.length() == 1)
	        hourString = "0" + hourString;
	      if (minute.length() == 1)
	        minute = "0" + minute;
	      if (second.length() == 1)
	        second = "0" + second;

	      time = yearString + "-" + monthString + "-" + dayString + " " + hourString + ":" + minute + ":" +
	          second;
	    }

	    return time;
	  }
	
	 /**
	 * 判断是哪个省的程序
	 * @return String
	 */
	public static boolean ifRegions(){
		if(StaticMethod.getNodeName("STRREGIONCODE").equals("YN"))return true;
		else return false;
	}

}
