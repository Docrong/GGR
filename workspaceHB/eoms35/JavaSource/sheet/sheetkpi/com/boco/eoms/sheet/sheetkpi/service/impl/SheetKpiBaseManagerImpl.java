/**
 * 
 */
package com.boco.eoms.sheet.sheetkpi.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.hibernate.HibernateException;
import com.boco.eoms.sheet.sheetkpi.dao.ISheetKpiBaseDao;
import com.boco.eoms.sheet.sheetkpi.service.ISheetKpiBaseManager;

/**
 * @author Administrator
 *
 */
public class SheetKpiBaseManagerImpl implements ISheetKpiBaseManager {
	   private ISheetKpiBaseDao  sheetKpiBaseDao;
	
	public void excel(List list, List colMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根

	}

	/* （非 Javadoc）
	 * @see com.boco.eoms.sheet.sheetkpi.service.ISheetKpiBaseManager#getQuerySheetByCondition(java.lang.String[], java.util.Map, java.util.Map, java.lang.Integer, java.lang.Integer, int[], java.lang.String, java.lang.String)
	 */
	public List getQuerySheetByCondition(String[] hsql, Map actionForm,
			Map condition, Integer curPage, Integer pageSize, int[] aTotal,
			String dept, String xmlName) {
		String sql=null;
		condition.put("pageSize", pageSize);
		if(hsql!=null) sql=hsql[0];	
		if (sql == null || sql.equals("")) {
			 List map = sheetKpiBaseDao.getXMLList(xmlName);
			  sql = (String) map.get(0);
			  if(actionForm.get("sendTimeStartDate")!=null){
				  Object[] sendTimeStart = (Object[]) actionForm.get("sendTimeStartDate");
				  sql =sql.replace("{2}", sendTimeStart[0].toString() );				  
			  }
			  if(actionForm.get("sendTimeEndDate")!=null){
				  Object[] sendTimeEnd = (Object[]) actionForm.get("sendTimeEndDate");
				  sql =sql.replace("{1}", sendTimeEnd[0].toString());
			  }	
			  if(dept!=null&&dept!=""){
				  sql =sql.replace("{0}", dept);
			  }	
			  
		}
		List result = sheetKpiBaseDao.getQuerySheetByCondition(sql, curPage, pageSize, aTotal, dept);
		if(result!=null){
			System.out.println("查询数据条数:"+result.size());}		
		return result;
	}

	/* （非 Javadoc）
	 * @see com.boco.eoms.sheet.sheetkpi.service.ISheetKpiBaseManager#getXMLList(java.lang.String)
	 */
	public List getXMLList(String xmlName) throws HibernateException {
		
		return sheetKpiBaseDao.getXMLList(xmlName);
	}

	public ISheetKpiBaseDao getSheetKpiBaseDao() {
		return sheetKpiBaseDao;
	}

	public void setSheetKpiBaseDao(ISheetKpiBaseDao sheetKpiBaseDao) {
		this.sheetKpiBaseDao = sheetKpiBaseDao;
	}

	public List getReportByDept(String[] hsql,Map actionForm, String filename) throws HibernateException {
		String sql=null;	
		
		if(hsql!=null) sql=hsql[0];	
		if (sql == null || sql.equals("")) {
			 List map = sheetKpiBaseDao.getXMLList(filename);
			  sql = (String) map.get(0);
			  if(actionForm.get("sendTimeStartDate")!=null){
				  Object[] sendTimeStart = (Object[]) actionForm.get("sendTimeStartDate");
				  sql =sql.replace("{2}", sendTimeStart[0].toString() );				  
			  }
			  if(actionForm.get("sendTimeEndDate")!=null){
				  Object[] sendTimeEnd = (Object[]) actionForm.get("sendTimeEndDate");
				  sql =sql.replace("{1}", sendTimeEnd[0].toString());
			  }	
			  }
		List result =sheetKpiBaseDao.getReportByDept(sql,filename);
		return result;
	}

}
