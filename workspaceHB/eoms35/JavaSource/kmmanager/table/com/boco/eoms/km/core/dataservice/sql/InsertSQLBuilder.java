package com.boco.eoms.km.core.dataservice.sql;

import java.util.List;

import com.boco.eoms.km.core.dataservice.clause.FieldNode;
import com.boco.eoms.km.core.dataservice.map.EntityMap;

public class InsertSQLBuilder {

    private EntityMap entityMap;
    private List fieldNodeList;

    public InsertSQLBuilder(EntityMap entityMap, List aFieldNodeList) {
        this.entityMap = entityMap;
        this.fieldNodeList = aFieldNodeList;
    }

    public String buildSQL(List outValue, List outType)
            throws Exception {

        if ((this.fieldNodeList == null) || (this.fieldNodeList.isEmpty()))
            return null;

        StringBuffer sql = new StringBuffer("INSERT INTO ");
        sql.append(entityMap.getTableName());
        sql.append(' ');

        int length = this.fieldNodeList.size();

        for (int i = 0; i < length; ++i) {
            if (i == 0) {
                sql.append('(');
            }
            FieldNode node = (FieldNode) this.fieldNodeList.get(i);
            String colName = node.getFieldMap().getColumnMap().getColumnName();
            sql.append(colName);
            if (i == length - 1) {
                sql.append(')');
            } else {
                sql.append(',');
            }
        }

        sql.append(" VALUES ");

        for (int i = 0; i < length; ++i) {
            if (i == 0) {
                sql.append('(');
            }
            FieldNode node = (FieldNode) this.fieldNodeList.get(i);
            sql.append(node.getInsertPreparedString(outValue, outType));
            if (i == length - 1) {
                sql.append(')');
            } else {
                sql.append(',');
            }
        }

        return sql.toString();
    }

}
