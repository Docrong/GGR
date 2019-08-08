package com.boco.eoms.commons.mms.base.test;

import java.awt.Color;

import com.boco.eoms.commons.mms.base.util.ExcelToPic;

public class TestExcelToPic {
    public static void main(String[] args) {
        ExcelToPic etp = new ExcelToPic();

        String realPath = "D:/";
        String filename = "test.gif";
        String excelName = "402881831fb5f2a2011fb5f684260004.xls";//"2008-12-05-11-57-42402881ef1e0a6dfe011e0a71932e000d.xls";//"test.xls";//"test.xls";//"402881861f7e0ec9011f7e1bb5e3000720090216160239.xls";


        etp.createRowsImage(realPath, filename, excelName, 0, Color.yellow);

        //		etp.createColunmsImage(realPath, filename, excelName);

    }
}
