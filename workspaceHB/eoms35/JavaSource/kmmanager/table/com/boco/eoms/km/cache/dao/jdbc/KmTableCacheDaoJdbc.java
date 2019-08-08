package com.boco.eoms.km.cache.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;

import com.boco.eoms.base.dao.jdbc.BaseDaoJdbc;

import com.boco.eoms.km.core.dataservice.map.ColumnMap;
import com.boco.eoms.km.core.dataservice.map.EntityMap;
import com.boco.eoms.km.core.dataservice.map.FieldMap;
import com.boco.eoms.km.core.dataservice.map.TableMap;

public class KmTableCacheDaoJdbc extends BaseDaoJdbc {

    public EntityMap getKmEntityMap(final String table_id) {

        ConnectionCallback callback = new ConnectionCallback() {
            public Object doInConnection(Connection conn) throws SQLException, DataAccessException {
                String TABLE_NAME = getTableName(conn, table_id);

                EntityMap entityMap = new EntityMap();

                //1 创建 TABLE Map
                TableMap tableMap = new TableMap();
                tableMap.setTableName(TABLE_NAME.toUpperCase());
                tableMap.setId(table_id);

                //2 加入 TABLE Map
                entityMap.setTableMap(tableMap);

                String sql = "SELECT * FROM " + TABLE_NAME;
                System.out.println("KmTableCacheDaoJdbc.getKmEntityMap(final String table_id) = " + sql);
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int numberOfColumns = rsmd.getColumnCount();
                for (int column = 1; column <= numberOfColumns; column++) {
                    //3 创建 Column Map
                    ColumnMap colMap = new ColumnMap();
                    colMap.setColumnName(rsmd.getColumnName(column).toUpperCase());
                    colMap.setCatalogName(rsmd.getCatalogName(column));
                    colMap.setColumnClassName(rsmd.getColumnClassName(column));
                    colMap.setColumnDisplaySize(rsmd.getColumnDisplaySize(column));
                    colMap.setColumnLabel(rsmd.getColumnLabel(column));
                    colMap.setColumnType(rsmd.getColumnType(column));
                    colMap.setColumnTypeName(rsmd.getColumnTypeName(column));
                    //colModel.setPrecision(rsmd.getPrecision(column));
                    colMap.setScale(rsmd.getScale(column));
                    colMap.setSchemaName(rsmd.getSchemaName(column));
                    colMap.setTableName(rsmd.getTableName(column));
                    colMap.setAutoIncrement(rsmd.isAutoIncrement(column));
                    colMap.setCaseSensitive(rsmd.isCaseSensitive(column));
                    colMap.setCurrency(rsmd.isCurrency(column));
                    colMap.setDefinitelyWritable(rsmd.isDefinitelyWritable(column));
                    colMap.setIsNullable(rsmd.isNullable(column));
                    colMap.setReadOnly(rsmd.isReadOnly(column));
                    colMap.setSearchable(rsmd.isSearchable(column));
                    colMap.setSigned(rsmd.isSigned(column));
                    colMap.setWritable(rsmd.isWritable(column));

                    FieldMap fieldMap = getFieldMap(conn, table_id, rsmd.getColumnName(column).toUpperCase());
                    fieldMap.setColumnMap(colMap);
                    entityMap.addFieldMap(fieldMap);
                }

                return entityMap;
            }
        };
        return (EntityMap) this.getJdbcTemplate().execute(callback);
    }

    public static FieldMap getFieldMap(Connection conn, String table_id, String col_name) {
        FieldMap fieldMap = new FieldMap();

        try {
            String sql = "select ID,TABLE_ID,COL_NAME,COL_CHNAME,COL_TYPE from km_table_column t where t.table_id=? and t.col_name=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, table_id);
            ps.setString(2, col_name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                fieldMap.setId(rs.getString(1));
                fieldMap.setTableId(rs.getString(2));
                fieldMap.setFieldName(rs.getString(3).toUpperCase());
                fieldMap.setFieldChname(rs.getString(4));
                fieldMap.setFieldType(rs.getInt(5));
            } else {
                fieldMap.setId("0");
                fieldMap.setFieldChname(col_name);
                fieldMap.setFieldName(col_name.toUpperCase());
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fieldMap;
    }

    public static String getTableName(Connection conn, String table_id) {
        String TABLE_NAME = null;
        try {
            String sql = "select TABLE_NAME from km_table_general where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, table_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TABLE_NAME = rs.getString(1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return TABLE_NAME;
    }
}
