package com.boco.eoms.km.core.dataservice.sql;

import java.util.List;

import com.boco.eoms.km.core.dataservice.clause.CriteriaNode;
import com.boco.eoms.km.core.dataservice.clause.FieldNode;
import com.boco.eoms.km.core.dataservice.map.EntityMap;

public class QuerySQLBuilder {

    private EntityMap entityMap; // 模型定义
    private List fieldNodeList;
    private List criteriaNodeList; // 查询条件
    private String orderBy;

    public QuerySQLBuilder(EntityMap anEntityMap, List aFieldNodeList, List aCriteriaNodeList, String orderBy) {
        this.entityMap = anEntityMap;
        this.criteriaNodeList = aCriteriaNodeList;
        this.fieldNodeList = aFieldNodeList;
        this.orderBy = orderBy;
    }

    // 动态创建查询SQL
    public String[] buildSQL(List aValueList, List aDataTypes) {
        String[] sql = new String[2];

        StringBuffer selectSQL = new StringBuffer("SELECT ");
        buildSelectList(selectSQL, aValueList, aDataTypes);
        selectSQL.append(" FROM ");
        selectSQL.append(entityMap.getTableName());

        StringBuffer countSQL = new StringBuffer("SELECT COUNT(ID) FROM ");
        countSQL.append(entityMap.getTableName());

        StringBuffer whereSQL = new StringBuffer();
        buildWhereList(whereSQL, aValueList, aDataTypes);

        countSQL.append(whereSQL);
        selectSQL.append(whereSQL);

        selectSQL.append(orderBy);

        sql[0] = countSQL.toString();
        sql[1] = selectSQL.toString();

        return sql;
    }

    // 动态创建SELECT字段
    private void buildSelectList(StringBuffer sql, List aValueList,
                                 List aDataTypes) {
        if ((this.fieldNodeList == null) || (this.fieldNodeList.isEmpty())) {
            sql.append(" * ");
        } else {
            int length = this.fieldNodeList.size();

            for (int i = 0; i < length; ++i) {
                FieldNode node = (FieldNode) this.fieldNodeList.get(i);
                sql.append(node.getSelectPreparedString(aValueList, aDataTypes));
                if (i != length - 1) {
                    sql.append(',');
                }
            }
        }
    }

    // 动态创建WHERE条件
    private void buildWhereList(StringBuffer sql, List aValueList,
                                List aDataTypes) {
        if (this.criteriaNodeList != null && !this.criteriaNodeList.isEmpty()) {
            sql.append(" WHERE ");

            int length = this.criteriaNodeList.size();

            for (int i = 0; i < length; ++i) {
                if (i >= 1) {
                    sql.append(" and ");
                } else {
                    sql.append(' ');
                }

                CriteriaNode node = (CriteriaNode) this.criteriaNodeList.get(i);
                sql.append(node.getPreparedString(aValueList, aDataTypes));
            }
        }
    }
}