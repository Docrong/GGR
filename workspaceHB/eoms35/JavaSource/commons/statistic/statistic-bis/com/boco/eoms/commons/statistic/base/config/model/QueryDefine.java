/*
 * Created on 2008-1-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.statistic.base.config.model;

import java.io.Serializable;


/**
 * @author liuxy
 * <p>
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class QueryDefine implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 7418064198633254323L;
    private String name;
    private String statSqlParams = "";
    //add by lizhenyou
    private String statSqlDicts;

    private String percentCountHsql;

    private String percentListHsql;
    private String percentCountSql;
    private boolean percentCountNeedSumary = true;
    //	private String countListSql;
//	private String listPercentCount;	
    private String from = "";
    private String where = "";
    private String orderBy = "";
    private String betweenTimeColumn = "";
    private String betweenTimeMappingProperty = "";
    private FieldDefine fieldDefines[];
    private String select = "";
    private String groupBy = "";

    //动态列的动态条件
    //private DynamicColumParam[] dynamicColumParams = null;

    //动态列的动态条件 id
    private String dynamicColumId;

    public FieldDefine getFieldDefineByViewName(String viewName)
            throws Exception {
        FieldDefine fieldDefine = null;
        boolean isDefined = false;
        for (int i = 0; i < fieldDefines.length; i++) {
            if (fieldDefines[i].getViewName().equals(viewName)) {
                if (isDefined == true) {
                    throw new Exception("重复定义!");
                }
                isDefined = true;
                fieldDefine = fieldDefines[i];
            }
        }
        if (isDefined == false) {
            throw new Exception("没有定义!");
        }

        return fieldDefine;
    }

    /**
     * @return Returns the fieldDefines.
     */
    public FieldDefine[] getFieldDefines() {
        return fieldDefines;
    }

    /**
     * @param fieldDefines The fieldDefines to set.
     */
    public void setFieldDefines(FieldDefine[] fieldDefines) {
        this.fieldDefines = fieldDefines;
    }

    /**
     * @return Returns the percentCountSql.
     */
    public String getPercentCountSql() {
        return percentCountSql;
    }

    /**
     * @param percentCountSql The percentCountSql to set.
     */
    public void setPercentCountSql(String percentCountSql) {
        this.percentCountSql = percentCountSql;
    }

    /**
     * @return Returns the where.
     */
    public String getWhere() {
        return where;
    }

    /**
     * @param where The where to set.
     */
    public void setWhere(String where) {
        this.where = where;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the from.
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from The from to set.
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return Returns the orderBy.
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * @param orderBy The orderBy to set.
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * @return Returns the betweenTimeColumn.
     */
    public String getBetweenTimeColumn() {
        return betweenTimeColumn;
    }

    /**
     * @param betweenTimeColumn The betweenTimeColumn to set.
     */
    public void setBetweenTimeColumn(String betweenTimeColumn) {
        this.betweenTimeColumn = betweenTimeColumn;
    }

    /**
     * @param asName
     * @return
     */
    public FieldDefine getFieldDefineByAsName(String asName)
            throws Exception {
        FieldDefine fieldDefine = null;
        boolean isDefined = false;
        for (int i = 0; i < fieldDefines.length; i++) {
            if (fieldDefines[i].getId().equals(asName)) {
                if (isDefined == true) {
                    throw new Exception("asName重复定义!");
                }
                isDefined = true;
                fieldDefine = fieldDefines[i];
            }
        }
        if (isDefined == false) {
            throw new Exception("asName没有定义!");
        }

        return fieldDefine;
    }

    /**
     * @return Returns the percentCountHsql.
     */
    public String getPercentCountHsql() {
        return percentCountHsql;
    }

    /**
     * @param percentCountHsql The percentCountHsql to set.
     */
    public void setPercentCountHsql(String percentCountHsql) {
        this.percentCountHsql = percentCountHsql;
    }

    /**
     * @return Returns the percentListHsql.
     */
    public String getPercentListHsql() {
        return percentListHsql;
    }

    /**
     * @param percentListHsql The percentListHsql to set.
     */
    public void setPercentListHsql(String percentListHsql) {
        this.percentListHsql = percentListHsql;
    }

    /**
     * @return Returns the betweenTimeMappingProperty.
     */
    public String getBetweenTimeMappingProperty() {

        return (betweenTimeMappingProperty == null || betweenTimeMappingProperty.equals(""))
                ? betweenTimeColumn : betweenTimeMappingProperty;
    }

    /**
     * @param betweenTimeMappingProperty The betweenTimeMappingProperty to set.
     */
    public void setBetweenTimeMappingProperty(String betweenTimeMappingProperty) {
        this.betweenTimeMappingProperty = betweenTimeMappingProperty;
    }

    /**
     * @return Returns the percentCountNeedSumary.
     */
    public boolean isPercentCountNeedSumary() {
        return percentCountNeedSumary;
    }

    /**
     * @param percentCountNeedSumary The percentCountNeedSumary to set.
     */
    public void setPercentCountNeedSumary(boolean percentCountNeedSumary) {
        this.percentCountNeedSumary = percentCountNeedSumary;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    public String getStatSqlParams() {
        return statSqlParams;
    }

    public void setStatSqlParams(String statSqlParams) {
        this.statSqlParams = statSqlParams;
    }

    public String getStatSqlDicts() {
        return statSqlDicts;
    }

    public void setStatSqlDicts(String statSqlDicts) {
        this.statSqlDicts = statSqlDicts;
    }
//	public DynamicColumParam[] getDynamicColumParams() {
//		return dynamicColumParams;
//	}
//	public void setDynamicColumParams(DynamicColumParam[] dynamicColumParams) {
//		this.dynamicColumParams = dynamicColumParams;
//	}

    public String getDynamicColumId() {
        return dynamicColumId;
    }

    public void setDynamicColumId(String dynamicColumId) {
        this.dynamicColumId = dynamicColumId;
    }
}
