package com.boco.eoms.workplan.util;

/**
 * <p>Title: 静态变量类</p>
 * <p>Description: 提供作业计划模块的静态变量</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 *
 * @author eoms
 * @version 1.0
 */


import java.util.List;
import java.util.Hashtable;

public class TawwpStaticVariable {

    //周期编号对应的名称数组
    static public String[] CYCLENAME = {
            "其他", "天", "周", "半月", "月", "季度", "半年", "年"
            , "两月", "四月"};

    //审批状态数组
    static public String[] CHECKSTATE = {
            "新建", "通过", "驳回", "审批中"};

    //月份数组
    static public String[] MONTH = {
            "00", "01", "02", "03", "04", "05", "06", "07"
            , "08", "09", "10", "11", "12"};

    //元素属性数组
    static public String[] elementAttribute = {
            "value", "showType", "name", "newLine", "rows", "cols", "align",
            "valign", "x", "y", "validateType", "startTime", "endTime", "dicId", "cycle"};

    static public String[] showTypeValue = {
            "input", "RadioBox", "CheckBox", "Select",
            "MulSelect", "TextArea", "text", "UserName"};

    //系统类别网元类型结构集合
    static public Hashtable SYS_NET_INF = null;

    //系统类别结构集合
    static public Hashtable SYS_INF = null;

    //网元类型结构集合
    static public Hashtable NET_INF = null;

    //联通专有网元类型结构集合
    static public Hashtable MY_NET_TYPE_INF = null;

    //机房信息结构集合
    static public Hashtable ROOM_INF = null;

    //厂商信息结构集合
    static public List VENDOR_INF = null;

    //附加表信息结构集合
    static public Hashtable ADDONS_INF = null;
    static public Hashtable ADDONS_RANK = null;

    //作业计划的字典类型编号(ws_class)
    static public int GZJHDICTFLAG = 50;

    //文件属性的字典类型编号(ws_class)
    static public int WJSXDICTFLAG = 10307;

    //是否的字典类型编号(ws_class)
    static public int SFFLAG = 10301;

    /********************start**********************
     com.boco.eoms.common.util.FileInitServlet，在服务启动时从web中加载
     请查看com.boco.eoms.common.util.FileInitServlet
     ************************************************/
    public static String rootDir = ""; //rootDir

    public static String wwwDir = "/eoms/attachment"; //wwwDir,windows

    public static String fileDir = "../tawwpfile/attmentfile/"; //windows

    //配置服务器的访问url
    public static String serverIp = "";

    //配置导入excel文件临时目录
    public static String tmpUpDir = "workplan/tawwpfile/tempfileupload/";
    //配置导出excel文件临时目录
    public static String tmpDownDir = "workplan/tawwpfile/tempfiledownload/";

    //作业计划模块流经
    public static String workplan = "workplan/";

    //配置存放模版执行表格的目录
    public static String rootOriginXMLDir = "originXML/"; //windows

    //配置存放保存执行表格的目录
    public static String rootSaveXMLDir = "saveXML/"; //windows

    //月度统计数据文件存放的目录
    public static String statYearDir = "tawwpfile/statfile/"; //windows

    //作业计划权限管理
    static public int OPE_MODEL_MANAGE = 610; //模板作业计划
    static public int OPE_YEAR_MANAGE = 620; //年度作业计划
    static public int OPE_YEAR_CHECK = 628; //年度作业计划审批
    static public int OPE_MONTH_MANAGE = 630; //月度作业计划
    static public int OPE_MONTH_CHECK = 638;//月度作业计划审批
    static public int OPE_EXECUTE_CHECK = 648; //执行作业计划审批
    static public int OPE_NET_MANAGE = 650;//网元管理
    static public int OPE_ALL_SEARCH = 660;//全部管理查询
    static public int OPE_DEPT_SEARCH = 661;//部门管理查询
    static public int OPE_ADDONS_MANAGE = 670;//附加表管理
    static public int OPE_LOG_MANAGE = 680;//附加表管理


    //读取和生成excel的目录（作业计划总部接口使用）
    public static String modelPath = "workplan/report/model/";
    public static String yearPath = "workplan/report/year/";
    public static String monthPath = "workplan/report/month/";
    public static String dayPath = "workplan/report/day/";
    public static String netPath = "workplan/report/net/";
    //总部作业计划所属专业
    static public String[] SPECIAL = {"JH", "WX", "ZN", "FZ", "ZH"};

}
