package com.boco.eoms.km.core.dataservice.sql;

import java.util.Collection;
import java.util.Iterator;

import com.boco.eoms.km.core.dataservice.map.EntityMap;
import com.boco.eoms.km.core.dataservice.map.FieldMap;

public class CopySQLBuilder {

    private EntityMap entityMap;

    public CopySQLBuilder(EntityMap entityMap) {
        this.entityMap = entityMap;
    }

    public String buildSQL(String ID) throws Exception {
        String select = this.buildSelectList();

        StringBuffer sql = new StringBuffer("INSERT INTO ");
        sql.append(entityMap.getTableName());
        sql.append("_HIS (");
        sql.append(select);
        sql.append(") SELECT ");
        sql.append(select);
        sql.append(" FROM ");
        sql.append(entityMap.getTableName());
        sql.append(" WHERE ID = '");
        sql.append(ID);
        sql.append("'");

        return sql.toString();
    }

    // 动态创建SELECT字段
    private String buildSelectList() {
        StringBuffer sql = new StringBuffer(' ');
        Collection coll = entityMap.fetchFieldList();
        int length = coll.size();
        Iterator it = coll.iterator();
        for (int i = 0; i < length && it.hasNext(); ++i) {
            FieldMap field = (FieldMap) it.next();
            sql.append(field.getColumnName());
            if (i != length - 1) {
                sql.append(", ");
            }
        }
        return sql.toString();
    }

}
