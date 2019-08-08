package com.boco.eoms.commons.log.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.log.dao.TawCommonLogDeployDao;
import com.boco.eoms.commons.log.model.TawCommonLogOperator;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.service.ITawSystemAreaManager;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.priv.bo.TawSystemPrivAssignOut;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;


public class logSave {

    private logSave() {
        logSaveInfo = new LogSaveInfo();
    }

    private logSave(String classname, String userid, String operid,
                    String remoteip, String message, String modelid) {
        logSaveInfo = new LogSaveInfo();
        logSaveInfo.setClassname(classname);
        logSaveInfo.setUserid(userid);
        logSaveInfo.setOperid(operid);
        logSaveInfo.setRemoteip(remoteip);
        logSaveInfo.setMessage(message);
        logSaveInfo.setModelid(modelid);
    }

    private logSave(String classname, String userid, String operid,
                    String remoteip, String message, String modelid, String modelname, String url, String operatetime) {
        logSaveInfo = new LogSaveInfo();
        logSaveInfo.setClassname(classname);
        logSaveInfo.setUserid(userid);
        logSaveInfo.setOperid(operid);
        logSaveInfo.setRemoteip(remoteip);
        logSaveInfo.setMessage(message);
        logSaveInfo.setModelid(modelid);
        logSaveInfo.setModelname(modelname);
        logSaveInfo.setUrl(url);
        logSaveInfo.setOpertime(operatetime);
    }

    private LogSaveInfo logSaveInfo;


    private TawCommonLogDeployDao tawlogdeoploydao;
    private TawCommonLogOperatorManager logopermanager;

    //private static logSave instance = new logSave();
    public static logSave getInstance(String classname, String userid, String operid,
                                      String remoteip, String message, String modelid) {
        logSave instance = new logSave(classname, userid, operid,
                remoteip, message, modelid);
        return instance;
    }


    public static logSave getInstance(String classname, String userid, String operid,
                                      String remoteip, String message, String modelid, String modelName, String url, String operatetime) {
        logSave instance = new logSave(classname, userid, operid,
                remoteip, message, modelid, modelName, url, operatetime);
        return instance;
    }

    public void info() {
        String loglevel = "info";
        tawlogdeoploydao = (TawCommonLogDeployDao) ApplicationContextHolder
                .getInstance().getBean("tawCommonLogDeployDao");
        TawCommonLogOperator operm = new TawCommonLogOperator();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = String.valueOf(df.format(new Date()));
        ITawSystemUserManager uMgr = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
        ITawSystemDeptManager dMgr = (ITawSystemDeptManager) ApplicationContextHolder.getInstance().getBean("ItawSystemDeptManager");
        ITawSystemAreaManager aMgr = (ITawSystemAreaManager) ApplicationContextHolder.getInstance().getBean("ItawSystemAreaManager");
        TawSystemPrivAssignOut privassimgr = TawSystemPrivAssignOut.getInstance();
        TawSystemUser user = (TawSystemUser) uMgr.getUserByuserid(logSaveInfo.getUserid());
        String username = user.getUsername();
        String deptid = user.getDeptid();
        String deptname = user.getDeptname();
        String areaname = "";
        String areaid = StaticMethod.null2String(((TawSystemDept) dMgr.getDeptinfobydeptid(deptid, "0")).getAreaid());
        System.out.println("***********************areaid*********************************" + areaid);
        if (areaid != null && !("").equals(areaid)) {
            TawSystemArea tempArea = (TawSystemArea) aMgr.getAreaByAreaId(areaid);
            if (tempArea != null) {
                areaname = tempArea.getAreaname();
                operm.setAreaid(areaid);
                operm.setAreaname(areaname);
            } else {
                operm.setAreaid(areaid);
                operm.setAreaname("娌℃湁璇ュ湴鍩燂紝璇锋牎瀵归儴闂ㄦ墍灞炲湴鍩熸槸鍚﹀瓨鍦�");
            }
        }
        operm.setDeptid(deptid);
        operm.setUsername(username);
        operm.setDeptname(deptname);
        operm.setBeginnotetime(str);
        operm.setBzremark("");
        operm.setCurrentday(getOperDay("days"));
        operm.setCurrentmonth(getOperDay("months"));
        operm.setModelid(logSaveInfo.getModelid());
        //operm.setModelname(this.modelname);
        operm.setModelname(privassimgr.getNameBycode(logSaveInfo.getModelid()));
        operm.setNotemessage(logSaveInfo.getMessage().toString());
        operm.setOperid(logSaveInfo.getOperid());
        operm.setOpername(logSaveInfo.getClassname());
        operm.setOperremark(logSaveInfo.getMessage());
        operm.setOperatetime(logSaveInfo.getOpertime());
        operm.setRemoteip(logSaveInfo.getRemoteip());
        operm.setUrl(logSaveInfo.getUrl());
        operm.setUserid(logSaveInfo.getUserid());
        operm.setIssucess(loglevel);

        logopermanager = (TawCommonLogOperatorManager) ApplicationContextHolder
                .getInstance().getBean("tawCommonLogOperatorManager");
        logopermanager.saveTawCommonLogOperator(operm);
    }

    public void warn() {
        // TODO Auto-generated method stub
        String loglevel = "warn";
        tawlogdeoploydao = (TawCommonLogDeployDao) ApplicationContextHolder
                .getInstance().getBean("tawCommonLogDeployDao");
        TawCommonLogOperator operm = new TawCommonLogOperator();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = String.valueOf(df.format(new Date()));

        ITawSystemUserManager uMgr = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
        ITawSystemDeptManager dMgr = (ITawSystemDeptManager) ApplicationContextHolder.getInstance().getBean("ItawSystemDeptManager");
        ITawSystemAreaManager aMgr = (ITawSystemAreaManager) ApplicationContextHolder.getInstance().getBean("ItawSystemAreaManager");
        TawSystemUser user = (TawSystemUser) uMgr.getUserByuserid(logSaveInfo.getUserid());
        String username = user.getUsername();
        String deptid = user.getDeptid();
        String deptname = user.getDeptname();
        String areaname = "";
        String areaid = StaticMethod.null2String(((TawSystemDept) dMgr.getDeptinfobydeptid(deptid, "0")).getAreaid());
        System.out.println("***********************areaid*********************************" + areaid);
        if (areaid != null && !("").equals(areaid)) {
            TawSystemArea tempArea = (TawSystemArea) aMgr.getAreaByAreaId(areaid);
            if (tempArea != null) {
                areaname = tempArea.getAreaname();
                operm.setAreaid(areaid);
                operm.setAreaname(areaname);
            } else {
                operm.setAreaid(areaid);
                operm.setAreaname("娌℃湁璇ュ湴鍩燂紝璇锋牎瀵归儴闂ㄦ墍灞炲湴鍩熸槸鍚﹀瓨鍦�");
            }
        }
        operm.setDeptid(deptid);
        operm.setUsername(username);
        operm.setDeptname(deptname);
        operm.setBeginnotetime(str);
        operm.setBzremark("");
        operm.setCurrentday(getOperDay("days"));
        operm.setCurrentmonth(getOperDay("months"));
        operm.setModelid(logSaveInfo.getModelid());
        operm.setModelname("");
        operm.setNotemessage(logSaveInfo.getMessage().toString());
        operm.setOperid(logSaveInfo.getOperid());
        operm.setOpername("");
        operm.setOperremark("");
        operm.setRemoteip(logSaveInfo.getModelid());
        operm.setUrl(logSaveInfo.getUrl());
        operm.setUserid(logSaveInfo.getUserid());
        operm.setIssucess(loglevel);

        logopermanager = (TawCommonLogOperatorManager) ApplicationContextHolder
                .getInstance().getBean("tawCommonLogOperatorManager");
        logopermanager.saveTawCommonLogOperator(operm);
    }

    public void error() {
        // TODO Auto-generated method stub
        String loglevel = "error";
        tawlogdeoploydao = (TawCommonLogDeployDao) ApplicationContextHolder
                .getInstance().getBean("tawCommonLogDeployDao");
        TawCommonLogOperator operm = new TawCommonLogOperator();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = String.valueOf(df.format(new Date()));

        ITawSystemUserManager uMgr = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
        ITawSystemDeptManager dMgr = (ITawSystemDeptManager) ApplicationContextHolder.getInstance().getBean("ItawSystemDeptManager");
        ITawSystemAreaManager aMgr = (ITawSystemAreaManager) ApplicationContextHolder.getInstance().getBean("ItawSystemAreaManager");
        TawSystemUser user = (TawSystemUser) uMgr.getUserByuserid(logSaveInfo.getUserid());
        String username = user.getUsername();
        String deptid = user.getDeptid();
        String deptname = user.getDeptname();
        String areaname = "";
        String areaid = StaticMethod.null2String(((TawSystemDept) dMgr.getDeptinfobydeptid(deptid, "0")).getAreaid());
        System.out.println("***********************areaid*********************************" + areaid);
        if (areaid != null && !("").equals(areaid)) {
            TawSystemArea tempArea = (TawSystemArea) aMgr.getAreaByAreaId(areaid);
            if (tempArea != null) {
                areaname = tempArea.getAreaname();
                operm.setAreaid(areaid);
                operm.setAreaname(areaname);
            } else {
                operm.setAreaid(areaid);
                operm.setAreaname("娌℃湁璇ュ湴鍩燂紝璇锋牎瀵归儴闂ㄦ墍灞炲湴鍩熸槸鍚﹀瓨鍦�");
            }
        }

        operm.setDeptid(deptid);
        operm.setUsername(username);
        operm.setDeptname(deptname);
        operm.setBeginnotetime(str);
        operm.setBzremark("");
        operm.setCurrentday(getOperDay("days"));
        operm.setCurrentmonth(getOperDay("months"));
        operm.setModelid(logSaveInfo.getModelid());
        operm.setModelname("");
        operm.setNotemessage(logSaveInfo.getMessage().toString());
        operm.setOperid(logSaveInfo.getOperid());
        operm.setOpername("");
        operm.setOperremark("");
        operm.setRemoteip(logSaveInfo.getRemoteip());
        operm.setUrl(logSaveInfo.getClassname());
        operm.setUserid(logSaveInfo.getUserid());
        operm.setIssucess(loglevel);

        logopermanager = (TawCommonLogOperatorManager) ApplicationContextHolder
                .getInstance().getBean("tawCommonLogOperatorManager");
        logopermanager.saveTawCommonLogOperator(operm);
    }

    private static String getOperDay(String condition) {
        String strday = "";
        Calendar currentCalendars = Calendar.getInstance();

        String years = String.valueOf(currentCalendars.get(Calendar.YEAR));
        String months = String
                .valueOf(currentCalendars.get(Calendar.MONTH) + 1);
        String days = String.valueOf(currentCalendars
                .get(Calendar.DAY_OF_MONTH));

        if (condition.equalsIgnoreCase("years")) {
            strday = years;
        } else if (condition.equalsIgnoreCase("months")) {
            strday = months;
        } else if (condition.endsWith("days")) {
            strday = days;
        }
        return strday;
    }

    /**
     * 鑾峰彇鏃ュ織淇℃伅瀵硅薄
     *
     * @return
     */
    public LogSaveInfo getLogSaveInfo() {
        return logSaveInfo;
    }

    /**
     * 璁剧疆鏃ュ織淇℃伅瀵硅薄
     *
     * @param logSaveInfo
     */
    public void setLogSaveInfo(LogSaveInfo logSaveInfo) {
        this.logSaveInfo = logSaveInfo;
    }
}

/**
 * 澧炲姞 绾綍鏃ュ織淇℃伅鐨勭被
 *
 * @author wangbeiying
 */
class LogSaveInfo {
    private String classname;
    private String userid;
    private String operid;
    private String remoteip;
    private String message;
    private String modelid;
    private String modelname;
    private String url;
    private String opertime;

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getModelid() {
        return modelid;
    }

    public void setModelid(String modelid) {
        this.modelid = modelid;
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public String getOperid() {
        return operid;
    }

    public void setOperid(String operid) {
        this.operid = operid;
    }

    public String getOpertime() {
        return opertime;
    }

    public void setOpertime(String opertime) {
        this.opertime = opertime;
    }

    public String getRemoteip() {
        return remoteip;
    }

    public void setRemoteip(String remoteip) {
        this.remoteip = remoteip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }


}
