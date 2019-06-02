package com.boco.eoms.commons.statistic.base.excelutil;

import java.io.OutputStream;
import java.util.Vector;

/**
* @author 李振友
*
*/
public interface IpoiExcel {
/**
     * 对Vector数据源将其里面的数据导入到excel表单
     * @param ObjectArray 数据源 用Vector对象
     * @param fieldName 导出到excel文件里的表头名
     * @param sheetName 工作表的名称
     * @param output java输出流
     */
public void ExportExcel(Vector ObjectArray,String[] fieldName,String sheetName,OutputStream output);
}

