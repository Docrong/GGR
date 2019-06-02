package com.boco.eoms.filemanager.topic;

/**
 * 共通宏定义
 * User: lxl
 * Date: 2003-9-5
 * Time: 16:29:30
 * To change this template use Options | File Templates.
 */

public class LBCommonMacro {
    //type 宏定义
    public static final String MACRO_TYPE_ID = "1";

    public static final String MACRO_TYPE_NAME = "文本";


    //html 注释
    public static final String MACRO_NOTE_BEGIN = "<!--";
    public static final String MACRO_NOTE_END = "-->";


    //enabled
    public static final String MACRO_HTML_ENABLED = "enabled";
    //disabled
    public static final String MACRO_HTML_DISABLED = "disabled";
    //checked
    public static final String MACRO_HTML_CHECKED = "checked";
    //selected
    public static final String MACRO_HTML_SELECTED = "selected";

    //log 类型
    public static final String[] MACRO_LOG_TYPE_ID = {"0", "1", "2", "3"};
    public static final String[] MACRO_LOG_TYPE_NAME = {"全部", "用户登录", "license", "权限"};
    //log 是否成功
    public static final String[] MACRO_LOG_SUCCESS_ID = {"0", "1", "2"};
    public static final String[] MACRO_LOG_SUCCESS_NAME = {"全部", "成功", "失败"};

    //文件路径分割符
    public static final String MACRO_PATH_DIV = "/";
}