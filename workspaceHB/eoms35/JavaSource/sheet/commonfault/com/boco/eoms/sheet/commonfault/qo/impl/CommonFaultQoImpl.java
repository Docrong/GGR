/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.commonfault.qo.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.qo.DataBaseTypeAdapterQO;
import com.boco.eoms.sheet.base.qo.impl.WorkSheetQOImpl;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.commonfault.qo.ICommonFaultQo;
import com.boco.eoms.sheet.overtimetip.util.OvertimeTipUtil;

/**
 * @author panlong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CommonFaultQoImpl extends WorkSheetQOImpl implements ICommonFaultQo {
	private  String fromName=" ";
	/**
	 * 带有权限的SQL查询，运用待处理列表、已处理列表、草稿列表、建立工单列表、已归档工单列表、已作废工单列表的查询(添加故障工单需要显示的特殊字段)
	 * @param properties
	 * @param userId
	 * @param displayTabelObjName 列表里需要显示的对象列表如NetdataMain or NetdataLink or NetdataTask
	 * @return
	 */
	public String getAclClauseSql(Map properties, String userId, String deptId) {
		String flowName = (String)properties.get("flowName");
		HashMap cloumnMap = OvertimeTipUtil.getMainColumnByMapping(flowName);
		Iterator it = cloumnMap.keySet().iterator();
		String ruternColumnName = "";
		while(it.hasNext()){
			String tmpOvertimeClm = (String)it.next();
			ruternColumnName+=this.aliasMain+"."+(String)cloumnMap.get(tmpOvertimeClm)+",";
		}
		if(!ruternColumnName.equals("")){
			ruternColumnName = ","+ruternColumnName.substring(0,ruternColumnName.length()-1);
		}		
		//页面属性
		Iterator names = properties.keySet().iterator();
		//添加和排序
		StringBuffer where = new StringBuffer();
		StringBuffer orderWhere = new StringBuffer();
		DataBaseTypeAdapterQO dataBaseTypeAdapterQO = new DataBaseTypeAdapterQO();
		
		while (names.hasNext()) {
			String name = (String) names.next();
			if (name == null || name.equals("")) {
				continue;
			}
			//如果不是以Expression结尾的，说明是字段属性
			if (name.indexOf("Expression") == -1) {
				//判断name是不是以urgentFaultMain.sheetId形式开头。
				if (name.indexOf(".") > -1) {
					//得到属性值
					Object nameValue = properties.get(name);
					if (nameValue != null && nameValue.getClass().isArray()) {
						nameValue = ((Object[]) nameValue)[0];
					}
					String nameArray[] = name.split("\\.");
					String columnName = nameArray[1];
					if (nameArray[0].indexOf("main") > -1) {
						name = this.getAliasMain() + "." + columnName;
					} else if (nameArray[0].indexOf("task") > -1) {
						name = this.getAliasTask() + "." + columnName;
					} else {
						name = this.getAliasLink() + "." + columnName;
					}
					if (!StaticMethod.nullObject2String(properties.get(columnName + "StringExpression")).equals("")) { //匹配字符串型
						if (nameValue == null || nameValue.equals("")) continue;
						this.stringExpression(properties, columnName, where, name, (String)nameValue);
					} else if (!StaticMethod.nullObject2String(properties.get(columnName + "StartDateExpression")).equals("")) { //匹配日期型
						this.dateExpression(properties, columnName, where, name, dataBaseTypeAdapterQO);
					} else if (!StaticMethod.nullObject2String(properties.get(columnName + "StartNumberExpression")).equals("")) { //匹配数字型
						this.numberExpression(properties, columnName, where, name);
					} else if (!StaticMethod.nullObject2String(properties.get(columnName + "OrderExpression")).equals("")) { //匹配排序条件
						this.orderExpression(properties, columnName, orderWhere, name);
					} else if (!StaticMethod.nullObject2String(properties.get(columnName + "ChoiceExpression")).equals("")) { //匹配排序条件
						this.choiceExpression(properties, columnName, where, name);
					}
				} else {
					continue;
				}
			}			
		}
		
		//从页面获取是 待处理/已处理 的查询页面列表
		//得到属性值
		Object done = properties.get("doneType");
		if (done != null && done.getClass().isArray()) {
			done = ((Object[]) done)[0];
		}
		if (done != null ) { 
			//已处理列表
			if (done.equals("senddone")) {
				fromName = " select distinct " + this.getAliasMain() + " from " + this.getPoMain() + " as " + this.getAliasMain() + ", "
					     + this.getPoTask() + " as " + this.getAliasTask() + ", TawSystemUserRefRole as userrefrole where " 
					     + this.getAliasTask() + ".sheetKey=" + this.getAliasMain() + ".id " 
					     + " and ((" + this.getAliasTask() + ".taskOwner='" + userId + "' or " 
					     + this.getAliasTask() + ".taskOwner='" + deptId + "') or (" 
					     + this.getAliasTask() + ".taskOwner=userrefrole.subRoleid and userrefrole.userid='"+userId+"'))" 
					     + " and (" + this.getAliasTask() + ".taskStatus='"+Constants.TASK_STATUS_FINISHED+"' or (" + this.getAliasTask() + ".taskStatus<>'"+Constants.TASK_STATUS_FINISHED+"' and " + this.getAliasTask() + ".ifWaitForSubTask='true' and " + this.getAliasTask() + ".id not in (select " + this.getAliasTask() + ".parentTaskId from " + this.getPoTask() + " as "+ this.getAliasTask()+" where "+ this.getAliasTask()+".subTaskFlag='true' and ("+ this.getAliasTask()+".subTaskDealFalg='false' or "+ this.getAliasTask()+".subTaskDealFalg is null ) and "+ this.getAliasTask()+".taskStatus='"+Constants.TASK_STATUS_FINISHED+"')))"
		 			  	 //增加条件main.deleted<>'1' ，过滤已经被删除或隐藏的工单 add by jialei
		 		  		 + " and "+this.getAliasMain() + ".deleted<>'1' ";
			//未处理列表
			} else if (done.equals("sendundo")) {
				fromName = " select distinct " + this.getAliasTask()+ ruternColumnName + ","+ this.aliasMain + ".mainNetName from " + this.getPoTask() + " as " + this.getAliasTask() + ", "
						   + this.getPoMain() + " as " + this.getAliasMain() + ", TawSystemUserRefRole as userrefrole where " 
						   + this.getAliasTask() + ".sheetKey=" + this.getAliasMain() + ".id " 
						   + " and ((" + this.getAliasTask() + ".taskOwner='" + userId + "' or (" 
						   + this.getAliasTask() + ".taskOwner='" + deptId + "') or " 
						   + this.getAliasTask() + ".taskOwner=userrefrole.subRoleid and userrefrole.userid='"+userId+"'))" 
						   + " and ( " + this.getAliasTask() + ".taskStatus=" + Constants.TASK_STATUS_READY + " or " 
						   + this.getAliasTask() + ".taskStatus=" + Constants.TASK_STATUS_CLAIMED +" or " 
						   + this.getAliasTask() + ".taskStatus=" + Constants.TASK_STATUS_INACTIVE + ")"
						   +"  and ( " + this.getAliasMain() + ".status<>" +  Constants.SHEET_DRAFT + " and " 
						   + this.getAliasTask() + ".taskDisplayName<>'草稿') "
			  	 		   //增加条件main.deleted<>'1' ，过滤已经被删除或隐藏的工单 add by jialei				
							+ " and "+this.getAliasMain() + ".deleted<>'1'"
							+ " and ("+this.getAliasTask() + ".ifWaitForSubTask='false' or ("+this.getAliasTask() + ".ifWaitForSubTask='true' and "+this.getAliasTask() + ".id in (select "+this.getAliasTask() + ".parentTaskId from " + this.getPoTask() + " as " + this.getAliasTask() + " where " + this.getAliasTask() + ".subTaskFlag='true' and (" + this.getAliasTask() + ".subTaskDealFalg='false' or " + this.getAliasTask() + ".subTaskDealFalg is null ) and " + this.getAliasTask() + ".taskStatus='"+Constants.TASK_STATUS_FINISHED+"')))"
							;
			}	
		//从草稿，管理者工单列表，建立工单等列表查询	
		} else {
			fromName = "select distinct " + this.getAliasMain() + " from "
		             + this.getPoMain() + " as " + this.getAliasMain() +" where " 
		             + this.getAliasMain() + ".templateFlag <> 1 and "
		             + this.getAliasMain() + ".title is not null and "
					 //增加条件main.deleted<>'1' ，过滤已经被删除或隐藏的工单 add by jialei
	 		   		 + this.getAliasMain() + ".deleted<>'1' ";
					
		}

		StringBuffer sql = new StringBuffer();
		sql.append(fromName).append(where.toString());
		
		if (!orderWhere.toString().equals("")) {
			sql.append( " order by " + orderWhere.toString());
		} else {
			if (done.equals("sendundo")) {
				sql.append(" order by " + this.getAliasTask() + ".createTime desc");
			} else {
				sql.append(" order by " + this.getAliasMain() + ".sendTime desc");
			}
		}
		
		return sql.toString();
	}
	
	  public String getClauseSql(Map properties)
	    {
	        System.out.println("=====1212121========");
	        Iterator names = properties.keySet().iterator();
	        StringBuffer where = new StringBuffer();
	        StringBuffer orderWhere = new StringBuffer();
	        DataBaseTypeAdapterQO dataBaseTypeAdapterQO = new DataBaseTypeAdapterQO();
	        while(names.hasNext()) 
	        {
	            String name = (String)names.next();
	            if(name != null && !name.equals("") && name.indexOf("Expression") == -1 && name.indexOf(".") > -1)
	            {
	                Object nameValue = properties.get(name);
	                if(nameValue != null && nameValue.getClass().isArray())
	                    nameValue = ((Object[])nameValue)[0];
	                String nameArray[] = name.split("\\.");
	                String columnName = nameArray[1];
	                if(nameArray[0].indexOf("main") > -1)
	                    name = getAliasMain() + "." + columnName;
	                else
	                if(nameArray[0].indexOf("task") > -1)
	                    name = getAliasTask() + "." + columnName;
	                else
	                    name = getAliasLink() + "." + columnName;
	                if(!StaticMethod.nullObject2String(properties.get(columnName + "StringExpression")).equals(""))
	                {
	                    if(nameValue != null && !nameValue.equals(""))
	                        stringExpression(properties, columnName, where, name, (String)nameValue);
	                } else
	                if(!StaticMethod.nullObject2String(properties.get(columnName + "StartDateExpression")).equals(""))
	                    dateExpression(properties, columnName, where, name, dataBaseTypeAdapterQO);
	                else
	                if(!StaticMethod.nullObject2String(properties.get(columnName + "StartNumberExpression")).equals(""))
	                    numberExpression(properties, columnName, where, name);
	                else
	                if(!StaticMethod.nullObject2String(properties.get(columnName + "OrderExpression")).equals(""))
	                    orderExpression(properties, columnName, orderWhere, name);
	                else
	                if(!StaticMethod.nullObject2String(properties.get(columnName + "ChoiceExpression")).equals(""))
	                    choiceExpression(properties, columnName, where, name);
	            }
	        }
	        if(!where.toString().equals("") && where.toString().indexOf(getAliasLink()) > -1 && where.toString().indexOf(getAliasTask()) == -1)
	            fromName = "select distinct " + getAliasMain() + " from " + getPoMain() + " as " + getAliasMain() + "," + getPoLink() + " as " + getAliasLink() + " where " + getAliasMain() + ".id = " + getAliasLink() + ".mainId  and " + getAliasMain() + ".templateFlag <> 1 and " + getAliasLink() + ".templateFlag <> 1 and " + getAliasMain() + ".title is not null and " + getAliasMain() + ".deleted<>'1' ";
	        else
	        if(!where.toString().equals("") && where.toString().indexOf(getAliasLink()) == -1 && where.toString().indexOf(getAliasTask()) > -1)
	            fromName = "select distinct " + getAliasMain() + " from " + getPoMain() + " as " + getAliasMain() + "," + getPoTask() + " as " + getAliasTask() + " where " + getAliasMain() + ".id = " + getAliasTask() + ".sheetKey  and " + getAliasMain() + ".templateFlag <> 1 and " + getAliasMain() + ".title is not null and " + getAliasMain() + ".deleted<>'1' ";
	        else
	        if(!where.toString().equals("") && where.toString().indexOf(getAliasLink()) > -1 && where.toString().indexOf(getAliasTask()) > -1)
	            fromName = "select distinct " + getAliasMain() + " from " + getPoMain() + " as " + getAliasMain() + "," + getPoLink() + " as " + getAliasLink() + "," + getPoTask() + " as " + getAliasTask() + " where " + getAliasMain() + ".id = " + getAliasLink() + ".mainId" + " and " + getAliasMain() + ".id = " + getAliasTask() + ".sheetKey " + " and " + getAliasMain() + ".templateFlag <> 1 " + " and " + getAliasMain() + ".title is not null and " + getAliasMain() + ".deleted<>'1' ";
	        else
	            fromName = "select distinct " + getAliasMain() + " from " + getPoMain() + " as " + getAliasMain() + " where " + getAliasMain() + ".templateFlag <> 1 and " + getAliasMain() + ".title is not null and " + getAliasMain() + ".deleted<>'1' ";
	        StringBuffer sql = new StringBuffer();
	        Object sheetIdValue = properties.get("main.sheetId");
	        if(sheetIdValue != null && sheetIdValue.getClass().isArray())
	            sheetIdValue = ((Object[])sheetIdValue)[0];
	        Object titleValue = properties.get("main.title");
	        if(titleValue != null && titleValue.getClass().isArray())
	            titleValue = ((Object[])titleValue)[0];
	        System.out.println("sheetId===============>" + sheetIdValue);
	        System.out.println("title===============>" + titleValue);
//	        if(sheetIdValue != null && sheetIdValue.equals("") && titleValue != null && titleValue.equals(""))
//	            where.append(" and ").append(getAliasMain()).append(".mainAlarmSolveDate is not null ");
	        sql.append(fromName).append(where.toString());
	        if(!orderWhere.toString().equals(""))
	            sql.append(" order by " + orderWhere.toString());
	        else
	            sql.append(" order by " + getAliasMain() + ".sendTime desc");
	        System.out.println("sql===============>" + sql.toString());
	        return sql.toString();
	    }
}
