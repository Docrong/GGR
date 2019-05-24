package com.boco.eoms.workplan.util;

/**
 * <p>Title: 常用工具类</p>
 * <p>Description: 作业计划模块公用方法集合</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 * @author eoms
 * @version 1.0
 */

import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.message.service.MsgService;
import com.boco.eoms.message.service.impl.MsgServiceImpl;
import com.boco.eoms.message.util.MsgMgrLocator;
import com.boco.eoms.workplan.cache.TawWorkplanCacheBean;

public class TawwpUtil {

	/**
	 * 获取当前系统时间
	 * 
	 * @param _dtFormat
	 *            要求显示的时间格式
	 * @return 时间字符串
	 */
	public static String getCurrentDateTime(String _dtFormat) {
		String currentdatetime = "";
		try {
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat dtFormat = new SimpleDateFormat(_dtFormat);
			currentdatetime = dtFormat.format(date);
		} catch (Exception e) {
			System.out.println("时间格式不正确");
			e.printStackTrace();
		}
		return currentdatetime;
	}

	/**
	 * 以yyyy-MM-dd HH:mm:ss显示当前格式
	 * 
	 * @return 时间字符串
	 */
	public static String getCurrentDateTime() {
		return getCurrentDateTime("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获得字符
	 * 
	 * @param _url
	 *            String
	 * @return String
	 */
	public static String getFileName(String _url) {
		String _subStr = "/";
		int wLength = _url.length();

		while (_url.indexOf(_subStr) != -1) {
			_url = _url.substring((_url.indexOf(_subStr) + 1), wLength);
			wLength = _url.length();
		}
		return _url;
	}
	public static String getFileNameSub (String _url){
		String[] _urlArray = _url.split("=");
		_url = _urlArray[1];
		return _url;
	}

	/**
	 * 创建目录 windows: D:\\tomcat\\WEB-INF\\ linux: /wwwroot/WEB-INF/
	 * 
	 * @param path
	 *            String 目录全名
	 * @throws Exception
	 *             创建异常
	 */
	public static void mkDir(String path) throws Exception {
		java.io.File dir;
		try {
			// 新建文件对象
			dir = new java.io.File(path);
			// 文件不为空
			if (dir != null && !dir.equals("")) {
				// 文件不存在
				if (!dir.exists()) {
					boolean result = dir.mkdirs();
					if (result == false) {
						throw new TawwpException("目录" + path + "创建失败");
					}
				}
			} else {
				throw new TawwpException("不能创建空目录");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("创建目录出现异常");
		}
	}

	/**
	 * 获取制定月度天数
	 * 
	 * @param _yearFlag
	 *            String 年度
	 * @param _monthFlag
	 *            String 月度
	 * @throws Exception
	 *             异常
	 * @return int 返回天数
	 */
	public static int getDayCountOfMonth(String _yearFlag, String _monthFlag)
			throws Exception {
		try {
			// 获取当月天数
			SimpleDateFormat dtFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date date = null;
			// 创建日期对象
			Calendar calendar = new GregorianCalendar();
			date = dtFormat
					.parse(_yearFlag + "-" + _monthFlag + "-01 00:00:00");
			calendar.setTime(date);
			// 获取日期该月的最大天数,5表示天
			int dayCount = calendar.getActualMaximum(calendar.DATE);
			// 返回当月天数
			return dayCount;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new TawwpException("获取当月天数出现异常");
		}
	}

	/**
	 * 取得request中的值并进行处理
	 * 
	 * @param _request
	 *            要处理的request
	 * @param _parm
	 *            要处理的参数
	 * @return 处理后的字符串
	 */
	public static String getRequestValue(HttpServletRequest _request,
			String _parm) {
		String returnValue = null;
		if (_request.getParameter(_parm) == null) {
			returnValue = "0"; // 如果request变量中为空，默认值为“0”
		} else {
			returnValue = _request.getParameter(_parm); // 如果不为空，用当前值
		}
		return returnValue; // 返回处理后的字符串
	}

	/**
	 * 取得request中的值并进行处理
	 * 
	 * @param _request
	 *            要处理的request
	 * @param _parm
	 *            要处理的参数
	 * @param _defaultvalue
	 *            如果为空，将用该值替代
	 * @return 处理后的字符串
	 */
	public static String getRequestValue(HttpServletRequest _request,
			String _parm, String _defaultvalue) {
		String returnValue = null;
		if (_request.getParameter(_parm) == null) {
			returnValue = _defaultvalue; // 如果request变量中为空，默认值为“0”
		} else {
			returnValue = _request.getParameter(_parm); // 如果不为空，用当前值
		}
		return returnValue; // 返回处理后的字符串
	}

	/**
	 * 将request中的值进行空值处理
	 * 
	 * @param _request
	 *            要处理的request
	 * @param _parm
	 *            要处理的参数
	 * @return request
	 */
	public static HttpServletRequest setRequestValue(
			HttpServletRequest _request, String _parm) {
		if (_request.getParameter(_parm) == null) {
			_request.setAttribute(_parm, "0"); // 如果request变量中为空，默认值为“0”
		} else {
			_request.setAttribute(_parm, _request.getParameter(_parm)); // 如果不为空，用当前值
		}
		return _request;
	}

	/**
	 * 将request中的值进行空值处理
	 * 
	 * @param _request
	 *            要处理的request
	 * @param _parm
	 *            要处理的参数
	 * @param _defaultvalue
	 *            如果为空，将用该值替代
	 * @return request
	 */
	public static HttpServletRequest setRequestValue(
			HttpServletRequest _request, String _parm, String _defaultvalue) {
		if (_request.getParameter(_parm) == null) {
			_request.setAttribute(_parm, _defaultvalue); // 如果request变量中为空，默认值为“0”
		} else {
			_request.setAttribute(_parm, _request.getParameter(_parm)); // 如果不为空，用当前值
		}
		return _request;
	}

	/**
	 * 取得某一天是星期几
	 * 
	 * @param _currentDate
	 *            当前天
	 * @return 星期
	 */
	public static int getWeek(String _currentDate) {
		int week = 1;
		try {
			SimpleDateFormat dtFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date date = null;
			Calendar calendar = new GregorianCalendar();
			date = dtFormat.parse(_currentDate);
			calendar.setTime(date);
			week = calendar.get(7);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return week;
	}

	/**
	 * 将周期的汉字转化为数字
	 * 
	 * @param _cycleStr
	 *            String
	 * @return int
	 */
	public static int getCycleNum(String _cycleStr) {
		int cycleNum = 7;
		String cycleNameValue = "";
		String[] cycleName = TawwpStaticVariable.CYCLENAME;
		for (int i = 0; i < 10; i++) {
			cycleNameValue = cycleName[i];
			if (_cycleStr.equals(cycleNameValue)) {
				cycleNum = i;
			}
		}
		return cycleNum;
	}

	/**
	 * 获取月度的两位格式 如：“01”
	 * 
	 * @param _currentMonth
	 *            int 月份
	 * @return String 月度的两位格式
	 */
	public static String getMonthStr(int _currentMonth) {

		String changeMonth = String.valueOf(_currentMonth);

		if (_currentMonth < 10) {
			changeMonth = "0" + _currentMonth;
		}

		return changeMonth;
	}

	public static String changeURL(String _utfinfo) {
		return URLEncoder.encode(_utfinfo);

	}

	public static String changeURL1(String _utfinfo) {
		return URLDecoder.decode(_utfinfo);
	}

	/**
	 * 转化大写字符串为小写
	 * 
	 * @param _caseStr
	 *            String
	 * @return String 小写的字符串
	 */
	public static String upCase(String _caseStr) {
		String tempstr = new String("");
		char tempch = ' ';
		for (int i = 0; i < _caseStr.length(); i++) {
			tempch = _caseStr.charAt(i);
			if (64 < _caseStr.charAt(i) && _caseStr.charAt(i) < 91) { // 是大写字母
				tempch += 32;
			}
			tempstr += tempch;
		}
		return tempstr;
	}

	/**
	 * 增加短信服务功能
	 * 
	 * @param _cruser
	 *            String 发送人列表，以逗号分割
	 * @param _content
	 *            String 发送内容
	 * @param _id
	 *            String 所属属性ID 如月计划Id或年计划ID
	 */
	public static void sendSMS(String _cruser, String _content, String _id) {
		// TODO 发送短信
		String serverid = WorkplanMgrLocator.getAttributes().getServerId();
		//MsgService msgService = MsgMgrLocator.getMsgMgr();
		MsgServiceImpl   msgService = new MsgServiceImpl();
		if (msgService.hasService(serverid).equals(
				"true")) {
			// 拼写执行人 orgIds 格式：1,admin#,1,sunshengtai#2,151
			String[] cruser = _cruser.split(",");
			StringBuffer orgIds = new StringBuffer();
			for (int i = 0; i < cruser.length; i++) {
				orgIds.append("1," + cruser[i] + "#");
			}
			// 获得当前时间 
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(currentTime);
			// 添加服务 8a44e43f1a998edb011a99b090d90004为服务模块ID
			System.out.println(orgIds.toString()+":"+_content);
			msgService.sendMsg(serverid, _content,
					_id, orgIds.toString(), dateString);
			
			 
		}

	}

	/**
	 * 增加短信服务功能
	 * 
	 * @param serverid
	 *            String 短信订阅服务ID
	 * @param _cruser
	 *            String 发送人列表，以逗号分割
	 * @param _content
	 *            String 发送内容
	 * @param _id
	 *            String 所属属性ID 如月计划Id或年计划ID
	 */
	public static void sendSMS(String serverid,String _cruser, String _content, String _id) {
		// TODO 发送短信
		MsgServiceImpl   msgService = new MsgServiceImpl();
		if (msgService.hasService(serverid).equals(
				"true")) {
			// 拼写执行人 orgIds 格式：1,admin#,1,sunshengtai#2,151
			String[] cruser = _cruser.split(",");
			StringBuffer orgIds = new StringBuffer();
			for (int i = 0; i < cruser.length; i++) {
				orgIds.append("1," + cruser[i] + "#");
			}
			// 获得当前时间 
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(currentTime);
			// 添加服务 8a44e43f1a998edb011a99b090d90004为服务模块ID

			msgService.sendMsg(serverid, _content,
					_id, orgIds.toString(), dateString);
			
			 
		}

	}

	/**
	 * 取消短信服务功能
	 * 
	 * @param _cruser
	 *            String 发送人列表，以逗号分割
	 * @param _id
	 *            String 所属属性ID 如月计划Id或年计划ID
	 */
	public static void closeSMS(String _id) {
		// TODO 发送短信
		// TawHieMonitorBO tawHieMonitorBO = new TawHieMonitorBO();
		String serverid = WorkplanMgrLocator.getAttributes().getServerId();
		//MsgService msgService = MsgMgrLocator.getMsgMgr();
		MsgServiceImpl   msgService = new MsgServiceImpl();
		if (msgService.hasService(serverid).equals(
				"true")) {

			msgService.closeMsg(serverid, _id);
		}
	}

	/**
	 * 增加Email服务功能
	 * 
	 * @param _cruser
	 *            String 发送人列表，以逗号分割
	 * @param _content
	 *            String 发送内容
	 * @param _id
	 *            String 所属属性ID 如月计划Id或年计划ID
	 */
	public static void sendMail(String _cruser, String _content, String _id) {
		// TODO 发送短信
  
		// 拼写执行人 orgIds 格式：1,admin#,1,sunshengtai#2,151
	/*	String[] cruser = _cruser.split(",");
		StringBuffer orgIds = new StringBuffer();
		for (int i = 0; i < cruser.length; i++) {
			orgIds.append("1," + cruser[i] + "#");
		}
		
		// 获得当前时间
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		// 添加服务 8a44e43f1a998edb011a99b090d90004为服务模块ID
		MsgService msgService = MsgMgrLocator.getMsgMgr();
		msgService.sendEmail4Org("8a44e43f1ac8fb2b011ac9168eda0005", _content,
				_id, orgIds.toString(), dateString);
*/
	}

	/**
	 * 取消Email服务功能
	 * 
	 * @param _cruser
	 *            String 发送人列表，以逗号分割
	 * @param _id
	 *            String 所属属性ID 如月计划Id或年计划ID
	 */
	public static void closeEmail(String _id) {
		// TODO 发送短信
		// TawHieMonitorBO tawHieMonitorBO = new TawHieMonitorBO();

		/*MsgService msgService = MsgMgrLocator.getMsgMgr();
		msgService.closeEmail("8a44e43f1ac8fb2b011ac9168eda0005", _id);*/
	}

	/**
	 * 取前天的字符串
	 * 
	 * @return String
	 */

	public static String getPreDay() {
		String preDay = "";
		GregorianCalendar strDate = new GregorianCalendar(); // 当时的日期和时间
		strDate.add(strDate.DATE, -1); // 取前一天的日期和时间(日期减 如果不够减会将月变动)
		String date = StaticMethod.Cal2String(strDate);
		preDay = getYYYYMMDD(date); // 生成 (年-月-日) 字符串

		return preDay;
	}

	/**
	 * 改变日期格式
	 * 
	 * @param DateStr
	 *            String
	 * @return String add by scropioD
	 */
	public static String getYYYYMMDD(String DateStr) {
		String YY, MM, DD;
		String ReturnDateStr;
		int s = DateStr.indexOf(" ");
		ReturnDateStr = DateStr.substring(0, s);
		Vector ss = StaticMethod.getVector(ReturnDateStr, "-");
		YY = ss.elementAt(0).toString();
		YY = YY.substring(0, 4);
		MM = ss.elementAt(1).toString();
		if (Integer.valueOf(MM).intValue() < 10) {
			MM = "0" + Integer.valueOf(MM).intValue();
		} else {
			MM = MM;
		}
		DD = ss.elementAt(2).toString();
		if (Integer.valueOf(DD).intValue() < 10) {
			DD = "0" + Integer.valueOf(DD).intValue();
		} else {
			DD = DD;
		}
		ReturnDateStr = YY + "-" + MM + "-" + DD;
		return ReturnDateStr;
	}

	public static void main(String[] args) {
		int a = 200;
		int b = 300;

		System.out.println(a / b);
		System.out.println((double) a / (double) b);
	}

	public static ArrayList getWeekend(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int length = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		ArrayList result = new ArrayList();
		int weekend = 1;
		if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
			result.add(new Integer(1));
		}
		weekend = 8 - cal.get(Calendar.DAY_OF_WEEK);
		for (int i = weekend; i <= length; i++) {
			if ((i - weekend) % 7 == 0 || (i - weekend) % 7 == 1) {
				result.add(new Integer(i));
			}
		}
		return result;
	}

	public static ArrayList getHoliday(String year, String month) {
		ArrayList result = new ArrayList();
		return result;
	}

	public static ArrayList getRestDay(int year, int month, String isHoliday,
			String isWeekend) {
		ArrayList result = new ArrayList();
		if (isHoliday.equals("1")) {
			result = getHoliday(String.valueOf(year), String.valueOf(month));
		}
		if (isWeekend.equals("1")) {
			if (result.size() > 0) {
				ArrayList tempList = getWeekend(year, month);
				for (int i = 0; i < tempList.size(); i++) {
					result.add(tempList.get(i));
				}
			} else {
				result = getWeekend(year, month);
			}
		}
		return result;

	}
	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	public static String getProvince(String unicomType) {
		// TODO Auto-generated method stub
		String province=StaticMethod.getNodeName("NEWSTRREGIONCODE");
		if(unicomType.equalsIgnoreCase("JH")){
			province=StaticMethod.getNodeName("STRREGIONCODE");
		}else if(unicomType.equalsIgnoreCase("RJH")){
			province=StaticMethod.getNodeName("STRREGIONCODE");
		}else if(unicomType.equalsIgnoreCase("WX")){
			province=StaticMethod.getNodeName("STRREGIONCODE");
		}else  if(unicomType.equalsIgnoreCase("FZ")){
			province=StaticMethod.getNodeName("STRREGIONCODE");
		}else  if(unicomType.equalsIgnoreCase("ZN")){
			province=StaticMethod.getNodeName("STRREGIONCODE");
		}else if(unicomType.equalsIgnoreCase("ZH")){
			province=StaticMethod.getNodeName("STRREGIONCODE");
		}else  if(unicomType.equalsIgnoreCase("SB")){
			province=StaticMethod.getNodeName("STRREGIONCODE");
		}else  if(unicomType.equalsIgnoreCase("GPRS")){
			province=StaticMethod.getNodeName("STRREGIONCODE");
		}else  if(unicomType.equalsIgnoreCase("WY")){
			province=StaticMethod.getNodeName("STRREGIONCODE");
		}
		 
		return province;
	}

	/**
     * 把数字的周期转换成文字的周期。	 
     */
    public static String getCycleStr(int cycle) {
	
	      String[] cycleStr = TawwpStaticVariable.CYCLENAME;
	      if (cycle <= cycleStr.length) {
		      return cycleStr[cycle];
	      } else {
		      return cycleStr[0];
	      }
	      
    }

	public static boolean isNew(String unicomType) {
		// TODO Auto-generated method stub
		boolean result=true;
		if(unicomType.equalsIgnoreCase("JH")){
			result=false;
		}else if(unicomType.equalsIgnoreCase("RJH")){
			result=false;
		}else if(unicomType.equalsIgnoreCase("WX")){
			result=false;
		}else if(unicomType.equalsIgnoreCase("FZ")){
			result=false;
		}else if(unicomType.equalsIgnoreCase("ZN")){
			result=false;
		}else if(unicomType.equalsIgnoreCase("ZH")){
			result=false;
		}else if(unicomType.equalsIgnoreCase("SB")){
			result=false;
		}else if(unicomType.equalsIgnoreCase("GPRS")){
			result=false;
		}else if(unicomType.equalsIgnoreCase("WY")){
			result=false;
		}
		 
		return result;
	}

	public static boolean copyFile(String from,  String to,boolean rewrite) {
		java.io.File filefrom = new java.io.File(from);
		java.io.File fileto = new java.io.File(to);
		if (!filefrom.exists()) {
			System.out.println("文件不存在");
			return false;
		}
		if (!filefrom.isFile()) {
			System.out.println("不能够复制文件夹");
			return false;
		}
		if (!filefrom.canRead()) {
			System.out.println("不能够读取需要复制的文件");
			return false;
		}
		if (!fileto.getParentFile().exists()) {
			fileto.getParentFile().mkdirs();
		}
		if (fileto.exists() && rewrite) {
			fileto.delete();
		}
		try {
			java.io.FileInputStream fosfrom = new java.io.FileInputStream(filefrom);
			java.io.FileOutputStream fosto = new FileOutputStream(fileto);
			byte bt[] = new byte[9999999];
			int c;
			while ( (c = fosfrom.read(bt)) > 0) {
				fosto.write(bt, 0, c);
			}
			fosfrom.close();
			fosto.close();
			return true;
		}
			catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	public static Hashtable getNetypeTable() {

		Hashtable sysHashtable = new Hashtable();
		Hashtable netHashtable = new Hashtable();
		ITawSystemDictTypeManager tawWsDictTypeDAO = (ITawSystemDictTypeManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDictTypeManager");
		List list = tawWsDictTypeDAO.getDictSonsByDictid(String
				.valueOf(TawwpStaticVariable.GZJHDICTFLAG));
		List netList = new ArrayList();
		// getDictTypeSelect(TawwpStaticVariable. GZJHDICTFLAG);
		// //获取作业计划的所有系统类别
		String[] sysType = null; 
		for (int i = 0; i < list.size(); i++) {
			TawSystemDictType tawSystemDictType = new TawSystemDictType();
			tawSystemDictType = (TawSystemDictType) list.get(i);
			sysType = new String[2];
			sysType[0] = tawSystemDictType.getDictName();
			sysType[1] = tawSystemDictType.getDictId();
			netList = tawWsDictTypeDAO.getDictSonsByDictid(sysType[1]);
			netHashtable = new Hashtable();
			for (int j = 0; j < netList.size(); j++) {

				TawSystemDictType netSystemDictType = (TawSystemDictType) netList
						.get(j);
				 
				netHashtable.put(netSystemDictType.getDictId(),
						netSystemDictType.getDictName());
			}
			sysHashtable.put(sysType[1], netHashtable);

		}
		return sysHashtable;

	}
	
	
	/*
	 * 通过ID取得相应的NAME值
	 * 参数1：type : user 用户；dept 部门； dict 字典； net 网元 
	 * 参数2：id 相应的ID
	 */
	public static String id2name(String type ,String id){
		TawWorkplanCacheBean tawWorkplanCacheBean = (TawWorkplanCacheBean) ApplicationContextHolder
		.getInstance().getBean("TawWorkplanCacheBean");
		Map workplanMap = tawWorkplanCacheBean.getWorkplanUser(); 
		String name = "";
		if(!type.equals("")||type!=null){
			if(type.equals("user")){
				Map userMap = (Map)workplanMap.get("userMap");
				name = StaticMethod.nullObject2String(userMap.get(id));
			}
			if(type.equals("dept")){
				Map deptMap = (Map)workplanMap.get("deptMap");
				name = StaticMethod.nullObject2String(deptMap.get(id));
			}
			if(type.equals("dict")){
				Map dictMap = (Map)workplanMap.get("dictMap");
				name = StaticMethod.nullObject2String(dictMap.get(id));
			}
			if(type.equals("net")){
				Map netMap = (Map)workplanMap.get("netMap");
				name = StaticMethod.nullObject2String(netMap.get(id));
			}
			
		}
		
		return name;
		
	}
	  public static Vector getDateArray(String _startDate, String _endDate) {
	    SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");

	    Date startDate = null;
	    Date endDate = null;

	    try {
	      startDate = dtFormat.parse(_startDate);
	      endDate = dtFormat.parse(_endDate);
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }

	    Calendar calendar = new GregorianCalendar();
	    Calendar calendar1 = new GregorianCalendar();
	    calendar.setTime(startDate);
	    calendar1.setTime(endDate);

	    Vector vector = new Vector();
	    vector.add(_startDate);

	    if (calendar.equals(calendar1)) {
	      return vector;
	    }

	    while (true) {
	      calendar.add(GregorianCalendar.DATE, 1);
	      vector.add(dtFormat.format(calendar.getTime()));
	      if (calendar.equals(calendar1)) {
	        break;
	      }
	    }

	    return vector;
	  }
}
