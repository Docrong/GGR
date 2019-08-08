package com.boco.eoms.sheet.netownershipwireless.qo.impl;

import com.boco.eoms.sheet.netownershipwireless.qo.INetOwnershipwirelessQo;

import java.util.Iterator;
import java.util.Map;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.qo.DataBaseTypeAdapterQO;


public class NetOwnershipwirelessQoImpl implements INetOwnershipwirelessQo {
    private String poNet;

    protected String aliasNet;

    private String fromName = " ";

    protected String clauseSql = " ";

    /**
     * 没有权限的公共查询
     *
     * @param properties
     * @return
     */
    public String getClauseSql(Map properties, String queryType) {
        //页面属性
        Iterator names = properties.keySet().iterator();
        //添加和排序
        StringBuffer where = new StringBuffer();
        StringBuffer orderWhere = new StringBuffer();
        DataBaseTypeAdapterQO dataBaseTypeAdapterQO = new DataBaseTypeAdapterQO();//时间类型的转换
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
                        name = this.getAliasNet() + "." + columnName;
                    }

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

        if ("isnull".equals(queryType)) {
            fromName = "from " + this.getPoNet() + " as " + this.getAliasNet() + " where 1=1 and " + this.getAliasNet() + ".deleted=0 and ( teamRoleId is null or teamRoleId='' ) ";
        } else if ("isnotnull".equals(queryType)) {
            fromName = "from " + this.getPoNet() + " as " + this.getAliasNet() + " where 1=1 and " + this.getAliasNet() + ".deleted=0 and teamRoleId is not null";
        } else {
            fromName = "from " + this.getPoNet() + " as " + this.getAliasNet() + " where 1=1 and " + this.getAliasNet() + ".deleted=0";
        }
        StringBuffer sql = new StringBuffer();
//		if("".equals(where) || where==null || where.length()==0){
//			sql.append(fromName).append(" and 1=2 ");
//			System.out.println("ItsspQOImpl--->82row>"+sql);
//		}else{
//			sql.append(fromName).append(where.toString());
//		}
        if (where != null && !"".equals(where)) {
            sql.append(fromName).append(where.toString());
        }

        if (!orderWhere.toString().equals("")) {
            sql.append(" order by " + orderWhere.toString());
        } else {
            sql.append(" order by " + this.getAliasNet() + ".createTime desc");
        }
        System.out.println("ItsspQOImpl--->93row>" + sql);
        return sql.toString();
    }


    public void stringExpression(Map properties, String columnName, StringBuffer where, String name, String nameValue) {
        Object expressionValue = properties.get(columnName + "StringExpression");
        System.out.println("sunchuhan test:" + nameValue);
        if (expressionValue != null && expressionValue.getClass().isArray()) {
            expressionValue = ((Object[]) expressionValue)[0];
        }
        if (expressionValue != null && expressionValue.equals("=")) {
            where.append(NetOwnershipwirelessQOhelper.addEqualClause(name, nameValue));
        } else if (expressionValue != null && expressionValue.equals("in")) {
            where.append(NetOwnershipwirelessQOhelper.addInClause(name, nameValue));
        } else {
            if (nameValue.contains(",")) {
                where.append(NetOwnershipwirelessQOhelper.addInClause(name, nameValue));
            } else {
                where.append(NetOwnershipwirelessQOhelper.addLikeClause(name, nameValue));
            }
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
            dateWhere.append(" and (").append(NetOwnershipwirelessQOhelper.addGreatOrEqualDateClause(name, (String) startDateExpressionValue, (String) startDateValue, dataBaseTypeAdapterQO));
        }
        if (endDateValue != null && !endDateValue.equals("")) {
            if (logicExpressionValue == null || logicExpressionValue.equals("")) logicExpressionValue = "and";
            dateWhere.append(" " + logicExpressionValue + " ");
            dateWhere.append(NetOwnershipwirelessQOhelper.addGreatOrEqualDateClause(name, (String) endDateExpressionValue, (String) endDateValue, dataBaseTypeAdapterQO));
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
            numberWhere.append(" and (").append(NetOwnershipwirelessQOhelper.addGreatOrEqualNumberClause(name, (String) startNumberExpressionValue, String.valueOf(startNumberValue)));
        }
        if (EndNumberValue != null && !String.valueOf(EndNumberValue).equals("")) {
            if (logicExpressionValue.equals("")) logicExpressionValue = "and";
            numberWhere.append(" " + logicExpressionValue + " ");
            numberWhere.append(NetOwnershipwirelessQOhelper.addGreatOrEqualNumberClause(name, (String) EndNumberExpressionValue, String.valueOf(EndNumberValue)));
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
            where.append(NetOwnershipwirelessQOhelper.addEqualClause(name, (String) choiceExpressionValue));
        }
    }

    public void orderExpression(Map properties, String columnName, StringBuffer orderWhere, String name) {
        Object orderExpressionValue = properties.get(columnName + "OrderExpression");
        if (orderExpressionValue != null && orderExpressionValue.getClass().isArray()) {
            orderExpressionValue = ((Object[]) orderExpressionValue)[0];
        }
        if (orderWhere.toString().equals("")) {
            orderWhere.append(NetOwnershipwirelessQOhelper.addOrderClause(name, (String) orderExpressionValue));
        } else {
            orderWhere.append("," + NetOwnershipwirelessQOhelper.addOrderClause(name, (String) orderExpressionValue));
        }

    }


    public String getAliasNet() {
        return aliasNet;
    }


    public void setAliasNet(String aliasNet) {
        this.aliasNet = aliasNet;
    }


    public String getPoNet() {
        return poNet;
    }


    public void setPoNet(String poNet) {
        this.poNet = poNet;
    }


}

