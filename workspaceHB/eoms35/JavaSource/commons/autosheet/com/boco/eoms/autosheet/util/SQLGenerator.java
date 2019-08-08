package com.boco.eoms.autosheet.util;

import com.boco.eoms.db.util.*;
import com.boco.eoms.common.util.*;

import java.util.Hashtable;

public class SQLGenerator {
    private SheetName shName = SheetName.getInstance();
    PropertyFile prop = PropertyFile.getInstance();
    private SheetAttribute shAttr;
    private SheetValue shValue;
    private CodeContentComsInfo codeContent;
    private String sheetID;
    private int keyid;

    public SQLGenerator(String sheetID) {
        this.keyid = keyid;
        this.sheetID = sheetID + "";
        shAttr = new SheetAttribute(sheetID);
        shValue = new SheetValue(sheetID);
        try {
            shName.setSheetName();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /***********生成预插入SQL********************************************************/
    public String generateInsertSQL() {
        String sql = "insert into " + prop.getProperty("pretable", "boco_") + sheetID + "(";
        String param = "";
        while (shValue.next()) {
            sql += shValue.getVName() + ",";
            param += "?,";
        }
        sql = sql.substring(0, sql.length() - 1) + ",single_id,attach_id,user_id,systime) values(" + param.substring(0, param.length() - 1) + ",?,?,?,?)";
        return sql;
    }

    /*************生成预查询SQL********************************************************/
    public String generateQuerySQL() {
        String sql = "select * from " + prop.getProperty("pretable", "boco_") + sheetID + " where ";
        String param = "";
        while (shValue.next()) {
            if (shValue.getIsQuery().equals("1")) {
                sql += shValue.getVName() + "=?  and ";
            }
        }
        sql = sql.substring(0, sql.length() - 3);
        return sql;
    }

    /*************生成预修改SQL********************************************************/
    public String generateUpdateSQL() {
        String sql = "update " + prop.getProperty("pretable", "boco_") + sheetID + " set ";
        String param = "";
        while (shValue.next()) {
            sql += shValue.getVName() + "=?,";
        }
        sql = sql.substring(0, sql.length() - 1) + " where id=?";
        return sql;
    }

    /*************生成预删除SQL********************************************************/
    public String generateDeleteSQL() {
        String sql = "delete from " + prop.getProperty("pretable", "boco_") + sheetID + " where id=?";
        return sql;
    }
}
