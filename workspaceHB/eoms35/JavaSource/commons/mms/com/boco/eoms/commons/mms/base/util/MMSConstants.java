package com.boco.eoms.commons.mms.base.util;

import java.io.FileNotFoundException;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.statistic.base.util.Constants;

public class MMSConstants {

    /**
     * 彩信报报表配置文件
     */
    public final static String REPORT_CONFIG = "classpath:config/mms/report-config/report-config.xml";//

    /**
     * 彩信报文件保存路径
     */
    public final static String KEEP_REPORT_FILE_PATH = "mms/mmsfile/";

    /**
     * 彩信报首页路径
     */
    public final static String FIST_PAGE_GIF = "classpath:config/aoyunbiaoti.gif";

    /**
     * 彩信报文件存放相对路径
     */
    public final static String MMSREPORT_FILE_RELATIVEPATH = initMmsreportFileRelativePath();


    public static String initMmsreportFileRelativePath() {
        String path = "";
        try {
            path = StaticMethod.getFilePathForUrl(REPORT_CONFIG);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //去掉前面的"/"
        path = path.substring(1);
        return path.substring(0, path.indexOf(Constants.WEB_INF)) + KEEP_REPORT_FILE_PATH;
    }

//	type: stat(默认) 2维表格， column 柱图 ，line 线图 ,pie 饼图 ，columnline

    public final static String STAT = "stat";

    public final static String COLUMN = "column";

    public final static String LINE = "line";

    public final static String PIE = "pie";

    public final static String COLUMNLINE = "columnline";

    public final static String PERCENT = "Percent";

    public final static String[] G_TYPE = {COLUMN, LINE, COLUMNLINE};

    /**
     * 彩信报图片文件路径
     */
    //public final static  String KEEP_REPORT_PIC_FILE_PATH = "pic/";

    /**
     * 彩信报报表保存路径
     */
    //public final static  String KEEP_REPORT_STAT_FILE_PATH = "stat/";

    /**
     * 彩信报html页面保存路径
     */
    //public final static  String KEEP_REPORT_HTML_FILE_PATH = "html/";

}
