package com.boco.eoms.businessupport.product.util;

import java.util.Iterator;
import java.util.Map;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.qo.DataBaseTypeAdapterQO;
import com.boco.eoms.sheet.base.qo.impl.WorkSheetQOhelper;
//import com.boco.eoms.sheet.base.util.QuerySqlInit;

/**
 * <p>
 * Title:硬件维修服务确认单
 * </p>
 * <p>
 * Description:硬件维修服务确认单代码生成
 * </p>
 * <p>
 * Tue Sep 08 10:05:21 CST 2009
 * </p>
 *
 * @author husong
 * @version 3.5
 */
public class IPSpecialLineConstants {

    /**
     * list key
     */
    private String poMain;

    protected String aliasMain;

    private String fromName = " ";

    protected String clauseSql = " ";

    public final static String ipSpecialLine_LIST = "ipspeciallineList";

    public String getAclClauseSql(Map properties, Map condition) {
        String flowName = (String) condition.get("flowName");
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
                    if (!StaticMethod.nullObject2String(properties.get(columnName + "StringExpression")).equals("")) { //匹配字符串型
                        if (nameValue == null || nameValue.equals("")) continue;
                        this.stringExpression(properties, columnName, where, name, (String) nameValue);
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
        if (done != null) {
            //已处理列表
//			if (done.equals("senddone")) {
//				fromName = QuerySqlInit.getDoneTaskSql(condition);
//			//未处理列表
//			} else if (done.equals("sendundo")) {
//				fromName = QuerySqlInit.getUndoTaskSql(condition, flowName);
//					
//			}	
            //从草稿，管理者工单列表，建立工单等列表查询
        }

        StringBuffer sql = new StringBuffer();
        sql.append(fromName).append(where.toString());
        String orderCondition = (String) condition.get("orderCondition");
        String pageSize = StaticMethod.nullObject2String(condition.get("pageSize"));
//		if(!pageSize.equals("-1")){		
//		if(orderCondition != null && ! orderCondition.equals("")){
//			sql.append(" order by " + orderCondition);
//		}else{
//			if (!orderWhere.toString().equals("")) {
//				sql.append( " order by " + orderWhere.toString());
//			} else {
//				if (done.equals("sendundo")) {
//					sql.append(" order by task.createTime desc");
//				} else {
//					sql.append(" order by main.sendTime desc");
//				}
//			}	
//		}
//		}

        System.out.println("sql========" + sql);
        return sql.toString();
    }

    public void stringExpression(Map properties, String columnName, StringBuffer where, String name, String nameValue) {
        Object expressionValue = properties.get(columnName + "StringExpression");
        if (expressionValue != null && expressionValue.getClass().isArray()) {
            expressionValue = ((Object[]) expressionValue)[0];
        }
        if (expressionValue != null && expressionValue.equals("=")) {
            where.append(WorkSheetQOhelper.addEqualClause(name, nameValue));
        }
        if (expressionValue != null && expressionValue.equals("in")) {
            where.append(WorkSheetQOhelper.addInClause(name, nameValue));
        } else {
            where.append(WorkSheetQOhelper.addLikeClause(name, nameValue));
        }
    }

    public void dateExpression(Map properties, String columnName, StringBuffer where, String name, DataBaseTypeAdapterQO dataBaseTypeAdapterQO) {
        StringBuffer dateWhere = new StringBuffer();

        Object startDateExpressionValue = properties.get(columnName + "StartDateExpression");
        if (startDateExpressionValue != null && startDateExpressionValue.getClass().isArray()) {
            startDateExpressionValue = ((Object[]) startDateExpressionValue)[0];
        }

        Object startDateValue = properties.get(columnName + "StartDate");
        if (startDateValue != null && startDateValue.getClass().isArray()) {
            startDateValue = ((Object[]) startDateValue)[0];
        }

        Object logicExpressionValue = properties.get(columnName + "LogicExpression");
        if (logicExpressionValue != null && logicExpressionValue.getClass().isArray()) {
            logicExpressionValue = ((Object[]) logicExpressionValue)[0];
        }

        Object endDateExpressionValue = properties.get(columnName + "EndDateExpression");
        if (endDateExpressionValue != null && endDateExpressionValue.getClass().isArray()) {
            endDateExpressionValue = ((Object[]) endDateExpressionValue)[0];
        }

        Object endDateValue = properties.get(columnName + "EndDate");
        if (endDateValue != null && endDateValue.getClass().isArray()) {
            endDateValue = ((Object[]) endDateValue)[0];
        }

        if (startDateValue != null && !startDateValue.equals("")) {
            dateWhere.append(" and (").append(WorkSheetQOhelper.addGreatOrEqualDateClause(name, (String) startDateExpressionValue, (String) startDateValue, dataBaseTypeAdapterQO));
        }
        if (endDateValue != null && !endDateValue.equals("")) {
            if (logicExpressionValue == null || logicExpressionValue.equals("")) logicExpressionValue = "and";
            dateWhere.append(" " + logicExpressionValue + " ");
            dateWhere.append(WorkSheetQOhelper.addGreatOrEqualDateClause(name, (String) endDateExpressionValue, (String) endDateValue, dataBaseTypeAdapterQO));
            dateWhere.append(")");
        } else if ((endDateValue == null || endDateValue.equals("")) && (startDateValue != null && !startDateValue.equals(""))) {
            dateWhere.append(")");
        }
        where.append(dateWhere.toString());
        dateWhere = null;
    }

    public void numberExpression(Map properties, String columnName, StringBuffer where, String name) {
        StringBuffer numberWhere = new StringBuffer();
        Object startNumberExpressionValue = properties.get(columnName + "StartNumberExpression");
        if (startNumberExpressionValue != null && startNumberExpressionValue.getClass().isArray()) {
            startNumberExpressionValue = ((Object[]) startNumberExpressionValue)[0];
        }

        Object startNumberValue = properties.get(columnName + "StartNumber");
        if (startNumberValue != null && startNumberValue.getClass().isArray()) {
            startNumberValue = ((Object[]) startNumberValue)[0];
        }

        Object logicExpressionValue = properties.get(columnName + "LogicExpression");
        if (logicExpressionValue != null && logicExpressionValue.getClass().isArray()) {
            logicExpressionValue = ((Object[]) logicExpressionValue)[0];
        }

        Object EndNumberExpressionValue = properties.get(columnName + "EndNumberExpression");
        if (EndNumberExpressionValue != null && EndNumberExpressionValue.getClass().isArray()) {
            EndNumberExpressionValue = ((Object[]) EndNumberExpressionValue)[0];
        }

        Object EndNumberValue = properties.get(columnName + "EndNumber");
        if (EndNumberValue != null && EndNumberValue.getClass().isArray()) {
            EndNumberValue = ((Object[]) EndNumberValue)[0];
        }

        if (startNumberValue != null && !String.valueOf(startNumberValue).equals("")) {
            numberWhere.append(" and (").append(WorkSheetQOhelper.addGreatOrEqualNumberClause(name, (String) startNumberExpressionValue, String.valueOf(startNumberValue)));
        }
        if (EndNumberValue != null && !String.valueOf(EndNumberValue).equals("")) {
            if (logicExpressionValue.equals("")) logicExpressionValue = "and";
            numberWhere.append(" " + logicExpressionValue + " ");
            numberWhere.append(WorkSheetQOhelper.addGreatOrEqualNumberClause(name, (String) EndNumberExpressionValue, String.valueOf(EndNumberValue)));
            numberWhere.append(")");
        } else if ((EndNumberValue == null || EndNumberValue.equals("")) && (startNumberValue != null && !startNumberValue.equals(""))) {
            numberWhere.append(")");
        }
        where.append(numberWhere.toString());
        numberWhere = null;
    }

    public void choiceExpression(Map properties, String columnName, StringBuffer where, String name) {
        Object choiceExpressionValue = properties.get(columnName + "ChoiceExpression");
        if (choiceExpressionValue != null && choiceExpressionValue.getClass().isArray()) {
            choiceExpressionValue = ((Object[]) choiceExpressionValue)[0];
            where.append(WorkSheetQOhelper.addEqualClause(name, (String) choiceExpressionValue));
        }
    }

    public void orderExpression(Map properties, String columnName, StringBuffer orderWhere, String name) {
        Object orderExpressionValue = properties.get(columnName + "OrderExpression");
        if (orderExpressionValue != null && orderExpressionValue.getClass().isArray()) {
            orderExpressionValue = ((Object[]) orderExpressionValue)[0];
        }
        if (orderWhere.toString().equals("")) {
            orderWhere.append(WorkSheetQOhelper.addOrderClause(name, (String) orderExpressionValue));
        } else {
            orderWhere.append("," + WorkSheetQOhelper.addOrderClause(name, (String) orderExpressionValue));
        }

    }


    public String getAliasMain() {
        return aliasMain;
    }

    public void setAliasMain(String aliasMain) {
        this.aliasMain = aliasMain;
    }

    public String getClauseSql() {
        return clauseSql;
    }

    public void setClauseSql(String clauseSql) {
        this.clauseSql = clauseSql;
    }

    public String getPoMain() {
        return poMain;
    }

    public void setPoMain(String poMain) {
        this.poMain = poMain;
    }

}