package com.boco.eoms.workplan.vo;

/**
 * <p>Title: 作业计划日志信息数据显示类</p>
 * <p>Description: 提供页面的所需的数据封装</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 *
 * @author eoms
 * @version 1.0
 */

public class TawwpLogVO {

    private String id; //标识
    private String cruser; //执行人
    private String cruserName; //执行人名称
    private String crtime; //执行时间
    private String content; //执行内容
    private String logType; //执行类型
    private String logTyepName; //执行类型名称

    //执行类型名称数组
    public static String[] LOGTPYE = {
            "错误", "操作", "系统", "接口"};

    public static String LOGPATH = "";

    public String getId() {
        if (id == null) {
            id = "";
        }

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCruser() {
        if (cruser == null) {
            cruser = "";
        }

        return cruser;
    }

    public void setCruser(String cruser) {
        this.cruser = cruser;
    }

    public String getCruserName() {
        if (cruserName == null) {
            cruserName = "";
        }

        return cruserName;
    }

    public void setCruserName(String cruserName) {
        this.cruserName = cruserName;
    }

    public String getCrtime() {
        if (crtime == null) {
            crtime = "";
        }

        return crtime;
    }

    public void setCrtime(String crtime) {
        this.crtime = crtime;
    }

    public String getContent() {
        if (content == null) {
            content = "";
        }

        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLogTyepName() {
        if (logTyepName == null) {
            logTyepName = "";
        }

        return logTyepName;
    }

    public void setLogTyepName(String logTyepName) {
        this.logTyepName = logTyepName;
    }

    public String getLogType() {
        if (logType == null) {
            logType = "";
        }

        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    /**
     * 获取日志类型名称
     *
     * @param _logType String 日志类型标识
     * @return String 日志类型名称
     */
    public static String getLogTypeName(String _logType) {
        int logType = Integer.parseInt(_logType);
        //初始化数据
        String logTypeName = "";
        //如果日志类型编号在执行人类型范围以内
        if (logType < TawwpLogVO.LOGTPYE.length && logType >= 0) {
            //取出日志类型显示名称
            logTypeName = TawwpLogVO.LOGTPYE[logType];
        }
        //返回日志类型名称
        return logTypeName;
    }

}
