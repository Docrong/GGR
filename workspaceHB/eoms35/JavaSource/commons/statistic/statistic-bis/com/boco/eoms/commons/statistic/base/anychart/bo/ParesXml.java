package com.boco.eoms.commons.statistic.base.anychart.bo;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Element;

import com.boco.eoms.commons.statistic.base.anychart.bean.BlockBean;
import com.boco.eoms.commons.statistic.base.anychart.dao.XmlDao;
import com.boco.eoms.commons.statistic.base.anychart.util.ConnectionFactory;

public class ParesXml {
	
	/**
	 * 一个block节点：一个SQL集合
	 * @param sql
	 * @param request
	 * @return
	 */
	public static void CreateSqls(BlockBean bb,Element sql,HttpServletRequest request){
		
		String time = "";//TIME:NAME/ARGUMENT
		if(sql.element("time")!=null){
			time = sql.element("time").getTextTrim();
		}
		
		Element select = sql.element("select");
		String groupby = "";
		String orderby = "";
		
		//先拼接SQL：group,order最后+
		String sqlstr = select.getTextTrim();
		if(sql.element("group")!=null){
			Element group = sql.element("group");
			if(!"".equals(group.getTextTrim())){
				groupby = " GROUP BY "+ group.getTextTrim();
			}
		}
		if(sql.element("order")!=null){
			Element order = sql.element("order");
			if(!"".equals(order.getTextTrim())){
				orderby = " ORDER BY " + order.getTextTrim();
			}
		}
		
		//Parse Element parameter
		String tmpsql = ParseParameter(sqlstr,sql,request);
		
		//分类拼接：产生多条SQL,也可能单条SQL就已经可以完成预定目标
		if(sql.element("assort")!=null){
			Element assort = sql.element("assort");
			String columnname = assort.attribute("columnname").getValue();
			String requestname =  assort.attribute("requestname").getValue();
			String operation = assort.attribute("operate").getValue();
			
			String setname = assort.attribute("setname").getValue();
			String setargument = assort.attribute("setargument").getValue();
			boolean issetname = false,issetargument = false;
			if("true".equals(setname)){
				issetname = true;
			}
			if("true".equals(setargument)){
				issetargument = true;
			}
			
			String[]  apps = request.getParameterValues(requestname);
			Connection cn = null;		
			try{
				cn = ConnectionFactory.getConnection();
				for(int i=0;i<apps.length;i++){
					
					HashMap hmp = new HashMap();
					String sq = "";
					if(!"all".equals(apps[i])){
						sq = tmpsql + " (" + columnname + operation + "'" + apps[i] + "') " + groupby + orderby;
					}else{
						sq = tmpsql + " (" + columnname + " IS NOT NULL ) " + groupby + orderby;
					}
					if(issetname || issetargument){
						Element childsql = assort.element("sql");
						String temp = childsql.getTextTrim();
						if(issetname && !"all".equals(apps[i])){
							String namevalue = XmlDao.getValue(temp, apps[i],cn);
							hmp.put("namevalue", namevalue);
						}else if(issetname && "all".equals(apps[i])){
							hmp.put("namevalue", "合计");
						}
						if(issetargument && !"all".equals(apps[i])){
							String argumentvalue = XmlDao.getValue(temp, apps[i],cn);
							hmp.put("argumentvalue", argumentvalue);
						}else if(issetargument && "all".equals(apps[i])){
							hmp.put("argumentvalue", "合计");
						}
					}
					if(!"".equals(time)){
						if("NAME".equals(time)){
							hmp.put("namevalue", "TIME");
						}
						if("ARGUMENT".equals(time)){
							hmp.put("argumentvalue", "TIME");
						}
					}				
					hmp.put("sql", sq);//查询语句				
					bb.setSqls(hmp);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				ConnectionFactory.closeConnection(cn);
			}
		}else{
			HashMap hmp = new HashMap();
			if(!"".equals(time)){
				if("NAME".equals(time)){
					hmp.put("namevalue", "TIME");
				}
				if("ARGUMENT".equals(time)){
					hmp.put("argumentvalue", "TIME");
				}
			}
			tmpsql = tmpsql.trim();
			if(tmpsql.endsWith("AND")){
				tmpsql = tmpsql.substring(0,tmpsql.length()-3);
			}			
			tmpsql = tmpsql + groupby + orderby;
			hmp.put("sql", tmpsql);//查询语句		
			bb.setSqls(hmp);
		}
	}
	
	/**
	 * 一个block节点：一个SQL集合
	 * @param sql
	 * @param request
	 * @return
	 */
	public static void CreateSqlList(BlockBean bb,Element sql,HttpServletRequest request){
		
		String time = "";//TIME:NAME/ARGUMENT
		if(sql.element("time")!=null){
			time = sql.element("time").getTextTrim();
		}
		
		Element select = sql.element("select");
		String groupby = "";
		String orderby = "";
		
		//先拼接SQL：group,order最后+
		String sqlstr = select.getTextTrim();
		if(sql.element("group")!=null){
			Element group = sql.element("group");
			if(!"".equals(group.getTextTrim())){
				groupby = " GROUP BY "+ group.getTextTrim();
			}
		}
		if(sql.element("order")!=null){
			Element order = sql.element("order");
			if(!"".equals(order.getTextTrim())){
				orderby = " ORDER BY " + order.getTextTrim();
			}
		}
		
		//Parse Element parameter
		String tmpsql = ParseParameter(sqlstr,sql,request);
		
		//一定为2个
		List assorts = sql.elements("assort");
		Element assort1 = (Element)assorts.get(0);
		Element assort2 = (Element)assorts.get(1);		
		
		String columnname = assort1.attribute("columnname").getValue();
		String requestname =  assort1.attribute("requestname").getValue();
		String operation = assort1.attribute("operate").getValue();
		
		String setname = assort1.attribute("setname").getValue();
		String setargument = assort1.attribute("setargument").getValue();
		boolean issetname = false,issetargument = false;
		if("true".equals(setname)){
			issetname = true;
		}
		if("true".equals(setargument)){
			issetargument = true;
		}
		
		String[]  apps = request.getParameterValues(requestname);
		
		List tmps = new ArrayList();
		
		Connection cn = null;
		
		try{
			cn = ConnectionFactory.getConnection();
			for(int k=0;k<apps.length;k++){
				HashMap hmp = new HashMap();
				String sq = "";
				if(!"all".equals(apps[k])){
					sq = tmpsql + " (" + columnname + operation + "'" + apps[k] + "')" + " AND ";
				}else{
					sq = tmpsql + " (" + columnname + " IS NOT NULL)" + " AND ";
				}
				if(issetname || issetargument){
					Element childsql = assort1.element("sql");
					String temp = childsql.getTextTrim();
					if(issetname && !"all".equals(apps[k])){
						String namevalue = XmlDao.getValue(temp, apps[k],cn);
						hmp.put("namevalue", namevalue);
					}else if(issetname && "all".equals(apps[k])){
						hmp.put("namevalue", "合计");
					}
					if(issetargument && !"all".equals(apps[k])){
						String argumentvalue = XmlDao.getValue(temp, apps[k],cn);
						hmp.put("argumentvalue", argumentvalue);
					}else if(issetargument && "all".equals(apps[k])){
						hmp.put("argumentvalue", "合计");
					}
				}
				if(!"".equals(time)){
					if("NAME".equals(time)){
						hmp.put("namevalue", "TIME");
					}
					if("ARGUMENT".equals(time)){
						hmp.put("argumentvalue", "TIME");
					}
				}				
				hmp.put("sql", sq);//查询语句
				tmps.add(hmp);
			}
			String columnname2 = assort2.attribute("columnname").getValue();
			String requestname2 =  assort2.attribute("requestname").getValue();
			String operation2 = assort2.attribute("operate").getValue();
			
			setname = assort2.attribute("setname").getValue();
			setargument = assort2.attribute("setargument").getValue();
			
			issetname = false;
			issetargument = false;
			if("true".equals(setname)){
				issetname = true;
			}
			if("true".equals(setargument)){
				issetargument = true;
			}
			
			String[]  apps2 = request.getParameterValues(requestname2);
			for(int j=0;j<tmps.size();j++){
				List sqlss = new ArrayList();
				HashMap tmphmp = (HashMap)tmps.get(j);	
				String tmpsql2 = (String)tmphmp.get("sql");
				for(int k=0;k<apps2.length;k++){
					HashMap tmp = new HashMap();
					if(tmphmp.get("namevalue")!=null){
						tmp.put("namevalue", tmphmp.get("namevalue"));
					}
					if(tmphmp.get("argumentvalue")!=null){
						tmp.put("argumentvalue", tmphmp.get("argumentvalue"));
					}		
					String sq = "";
					if(!"all".equals(apps2[k])){
						sq = tmpsql2 + " (" + columnname2 + operation2 + "'" + apps2[k] + "')" + groupby + orderby;
					}else{
						//全部
						sq = tmpsql2 + " (" + columnname2 + " IS NOT NULL )" + groupby + orderby;
					}
					if(issetname || issetargument){
						Element childsql = assort2.element("sql");
						String temp = childsql.getTextTrim();
						if(issetname && !"all".equals(apps2[k])){
							String namevalue = XmlDao.getValue(temp, apps2[k],cn);
							tmp.put("namevalue", namevalue);
						}else if(issetname && "all".equals(apps2[k])){
							tmp.put("namevalue", "合计");
						}
						if(issetargument && !"all".equals(apps2[k])){
							String argumentvalue = XmlDao.getValue(temp, apps2[k],cn);
							tmp.put("argumentvalue", argumentvalue);
						}else if(issetargument && "all".equals(apps2[k])){
							tmp.put("argumentvalue", "合计");
						}
					}
					tmp.put("sql", sq);//查询语句
					sqlss.add(tmp);
				}
				bb.setSqlss(sqlss);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(cn);
		}	
	}
	
	/**
	 * Parse Element parameter
	 * @param sqlstr
	 * @param sql
	 * @param request
	 * @return
	 */
	public static String ParseParameter(String sqlstr,Element sql,HttpServletRequest request){
		List parameters = sql.elements("parameter");
		for(int i=0;i<parameters.size();i++){
			Element parameter = (Element)parameters.get(i);
			String columnname = parameter.attribute("columnname").getValue();
			String requestname =  parameter.attribute("requestname").getValue();
			String operation = parameter.attribute("operate").getValue();
			String type = parameter.attribute("type").getValue();
			String[]  apps = request.getParameterValues(requestname);
			String sq = "(";			
			if(apps!=null){
				for(int j=0;j<apps.length;j++){
					if(j==apps.length-1){
						if(!"all".equals(apps[j])){
							sq = sq + columnname + operation + "'" + apps[j] + "'";
						}else{
							sq = sq + columnname + " IS NOT NULL ";
						}
					}else{
						if(!"all".equals(apps[j])){
							sq = sq + columnname + operation + "'" + apps[j] + "' " + type + " ";
						}else{
							sq = sq + columnname + " IS NOT NULL " + type + " ";
						}
					}
				}
				sq = sq + ") AND ";
			}
			sqlstr = sqlstr + sq;
		}
		return sqlstr;
	}
}
