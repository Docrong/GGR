package com.boco.eoms.km.table.util;

import com.boco.eoms.km.rule.webapp.form.KmRuleForm;
import com.boco.eoms.km.table.model.KmTableColumn;
import com.boco.eoms.km.table.webapp.form.KmTableGeneralForm;

public class KmTableUtil {
    public static String getColumnTypeForOracle(KmTableColumn kmTableColumn) {
        String colType = "";
        int i = kmTableColumn.getColType().intValue();
        if (i == 1) {//普通文本
            colType = " VARCHAR2(" + kmTableColumn.getColSize() + ")";
        } else if (i == 2) {//大文本域
            colType = " VARCHAR2(2000)";
        } else if (i == 3) {//数字类型
            colType = " NUMBER";
        } else if (i == 4) {//日期时间
            colType = " TIMESTAMP(6)";
        } else if (i == 5) {//单选字典
            colType = " VARCHAR2(64)";
        } else if (i == 6) {//多选字典
            colType = " VARCHAR2(256)";
        } else if (i == 7) {//附件上传
            colType = " VARCHAR2(1000)";
        }
        return colType;
    }

    public static String getColumnTypeForInformix(KmTableColumn kmTableColumn) {
        String colType = "";
        int i = kmTableColumn.getColType().intValue();
        if (i == 1) {//普通文本
            colType = " CHAR(" + kmTableColumn.getColSize() + ")";
        } else if (i == 2) {//大文本域
            colType = " LVARCHAR(2000)";
        } else if (i == 3) {//数字类型
            colType = " INTEGER";
        } else if (i == 4) {//日期时间
            colType = " DATETIME YEAR TO SECOND";
        } else if (i == 5) {//单选字典
            colType = " NVARCHAR(64)";
        } else if (i == 6) {//多选字典
            colType = " NVARCHAR(255)";
        } else if (i == 7) {//附件上传
            colType = " LVARCHAR(1000)";
        }
        return colType;
    }

    public static String getQueryCondition(KmTableGeneralForm kmTableGeneralForm) {
        String queryCon = " where kmTableGeneral.isDeleted='0' ";
        if (kmTableGeneralForm.getThemeId() != null && kmTableGeneralForm.getThemeId() != "") {
            queryCon += " and kmTableGeneral.themeId like '" + kmTableGeneralForm.getThemeId() + "%'";
        }
        if (kmTableGeneralForm.getTableChname() != null && kmTableGeneralForm.getTableChname() != "") {
            queryCon += " and kmTableGeneral.tableChname like '%" + kmTableGeneralForm.getTableChname() + "%'";
        }

        //queryCon+=" and kmTableGeneral.isDeleted='0'";
        return queryCon;
    }

    public static String getQueryCondition(KmRuleForm kmRuleForm) {
        String queryCon = " where kmRule.isDeleted='0' ";
        if (kmRuleForm.getContentId() != null && kmRuleForm.getContentId() != "") {
            queryCon += " and kmRule.contentId='" + kmRuleForm.getContentId() + "'";
        }
        if (kmRuleForm.getRuleName() != null && kmRuleForm.getRuleName() != "") {
            queryCon += " and kmRule.ruleName like '%" + kmRuleForm.getRuleName() + "%'";
        }
        //queryCon+=" and kmRule.isDeleted='0' ";
        return queryCon;
    }

}
