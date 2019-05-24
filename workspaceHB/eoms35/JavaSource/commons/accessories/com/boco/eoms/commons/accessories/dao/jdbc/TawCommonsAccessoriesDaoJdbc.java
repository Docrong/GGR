package com.boco.eoms.commons.accessories.dao.jdbc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import com.boco.eoms.commons.accessories.model.TawCommonsApplication;

public class TawCommonsAccessoriesDaoJdbc extends JdbcDaoSupport {
  public static  final String TBL_SYSTEM_APPLICATION = "Taw_System_Application";	
  
  public  List getTawSystemApplications() {
	 ArrayList _objReturnList = new ArrayList();
	 String _strSQL = "select APP_ID,APP_NAME from "
	             + TBL_SYSTEM_APPLICATION;

	 List _objList = getJdbcTemplate().queryForList(_strSQL);
	 Iterator _objIterator = _objList.iterator();
	 while (_objIterator.hasNext()) {
	    	 TawCommonsApplication _objApp = new TawCommonsApplication();
	         Map _objMap = (Map) _objIterator.next();
	         String _strAppId = _objMap.get("APP_ID").toString().trim();
	         int _iAppId = Integer.parseInt(_strAppId);
	         _objApp.setAppId(_iAppId);
	         _objApp.setAppName(_objMap.get("APP_NAME").toString().trim());

	         _objReturnList.add(_objApp);
	     }

	     return _objReturnList;
	 }
	 /**
	  * 根据应用模块ID号查询模块名称
	  * @param applicationId 应用模块ID
	  * @return
	  * @author 秦敏
	  */
	 public String getApplicatioNameById(int applicationId){

	     String applicationName="";
	     String _strSQL = "select APP_NAME from "
	             + TBL_SYSTEM_APPLICATION+" where app_id="+applicationId;

	     List _objList = getJdbcTemplate().queryForList(_strSQL);
	     Iterator _objIterator = _objList.iterator();
	     while (_objIterator.hasNext()) {
	        Map _objMap = (Map) _objIterator.next();
	        applicationName=_objMap.get("APP_NAME").toString().trim();
	     }
	     return applicationName;
	 }

}
