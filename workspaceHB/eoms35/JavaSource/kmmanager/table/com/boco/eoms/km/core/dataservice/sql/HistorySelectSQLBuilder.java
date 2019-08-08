package com.boco.eoms.km.core.dataservice.sql;

import java.sql.Types;
import java.util.List;

import com.boco.eoms.km.core.dataservice.clause.CriteriaNode;
import com.boco.eoms.km.core.dataservice.clause.FieldNode;
import com.boco.eoms.km.core.dataservice.map.EntityMap;

public class HistorySelectSQLBuilder {

    private EntityMap entityMap; // 模型定义
    private List fieldNodeList;
    private List criteriaNodeList; // 查询条件
    private String orderBy;
    private String VERSION;

    public HistorySelectSQLBuilder(EntityMap anEntityMap, List aFieldNodeList, List aCriteriaNodeList, String orderBy, String VERSION) {
        this.entityMap = anEntityMap;
        this.criteriaNodeList = aCriteriaNodeList;
        this.fieldNodeList = aFieldNodeList;
        this.orderBy = orderBy;
        this.VERSION = VERSION;
    }

    // 动态创建查询SQL
    public String buildSQL(List aValueList, List aDataTypes) {
        StringBuffer sql = new StringBuffer("SELECT ");
        buildSelectList(sql, aValueList, aDataTypes);
        sql.append(" FROM ");
        sql.append(entityMap.getTableName());
        sql.append("_HIS ");
        buildWhereList(sql, aValueList, aDataTypes);
//		sql.append(" AND VERSION=? ");
        sql.append(orderBy);

//		aValueList.add(this.VERSION);
//		aDataTypes.add(new Integer(Types.INTEGER));

        return sql.toString();
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