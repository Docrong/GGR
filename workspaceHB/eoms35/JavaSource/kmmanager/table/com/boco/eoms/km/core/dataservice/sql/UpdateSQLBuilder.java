package com.boco.eoms.km.core.dataservice.sql;

import java.util.List;

import com.boco.eoms.km.core.dataservice.clause.CriteriaNode;
import com.boco.eoms.km.core.dataservice.clause.FieldNode;
import com.boco.eoms.km.core.dataservice.map.EntityMap;

public class UpdateSQLBuilder {

    private EntityMap entityMap; // 模型定义
    private List fieldNodeList;
    private List criteriaNodeList; // 查询条件

    public UpdateSQLBuilder(EntityMap anEntityMap, List aFieldNodeList, List aCriteriaNodeList) {
        this.entityMap = anEntityMap;
        this.criteriaNodeList = aCriteriaNodeList;
        this.fieldNodeList = aFieldNodeList;
    }

    public String buildSQL(List aValueList, List aDataTypes) throws Exception {
        if ((this.fieldNodeList == null) || (this.fieldNodeList.isEmpty()))
            return null;

        StringBuffer sql = new StringBuffer("UPDATE ");
        sql.append(entityMap.getTableName());
        sql.append(" SET ");

        int length = this.fieldNodeList.size();

        for (int i = 0; i < length; ++i) {
            FieldNode node = (FieldNode) this.fieldNodeList.get(i);
            sql.append(node.getUpdatePreparedString(aValueList, aDataTypes));
            if (i != length - 1) {
                sql.append(',');
            }
        }

        if ((this.criteriaNodeList != null) && (!(this.criteriaNodeList.isEmpty()))) {
            sql.append(" WHERE ");
            length = this.criteriaNodeList.size();
            for (int i = 0; i < length; ++i) {
                if (i >= 1)
                    sql.append(" and ");
                else {
                    sql.append(' ');
                }
                CriteriaNode node = (CriteriaNode) this.criteriaNodeList.get(i);
                sql.append(node.getPreparedString(aValueList, aDataTypes));
            }
        }

        return sql.toString();
    }

}
