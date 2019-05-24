package com.boco.eoms.commons.statistic.base.model;

import java.io.Serializable;
import java.util.Date;

public class BaseStat implements Serializable {

	private String mainid;
	private String linkid;
	private Date nodeacceptlimit;// DATETIME YEAR to SECOND
	private Date nodecompletelimit;// DATETIME YEAR to SECOND
	private Date operatetime;// DATETIME YEAR to SECOND
	private String operateuserid;// VARCHAR(30)
	private String operatedeptid;// VARCHAR(100)
	private String operateroleid;// VARCHAR(32)
	private int operateorgtype;// INTEGER
	private int toorgtype;// INTEGER
	private String toorgdeptid;// VARCHAR(32)
	private String toorguserid;// VARCHAR(50)
	private String toroleid;// CHAR(1000)
	private int completeflag;// INTEGER
	private Date completetime;// DATETIME YEAR to SECOND
	private int completeusetime;// INTEGER
	private String prelinkid;// VARCHAR(50)
	private String activitydefid;// VARCHAR(30)
	private double workitemid;// DECIMAL(180)
	private double activityinstid;// DECIMAL(18,0)
	private int operatetype;// INTEGER
	private String sheetid;// VARCHAR(50)
	private String title;// VARCHAR(100)
	private Date sheetcompletelimit;// DATETIME YEAR to SECOND
	private Date sendtime;// DATETIME YEAR to SECOND
	private int status;// INTEGER
	private int holdstatisfied;// INTEGER
	private int deleted;// INTEGER
	private String sheettype;// VARCHAR(50)
	private String orgid;// VARCHAR(64)
	private int orgtype;// INTEGER
	private String mainapptime;// VARCHAR(50)
	private String mainmajorid;// VARCHAR(50)
	private String mainapptype;// VARCHAR(50)
	private String mainapplycause;// VARCHAR(50)
	private String maindescription;// CHAR(1000)
	private String linkauditopinion;// VARCHAR(255)
	private String linkpassopinion;// VARCHAR(255)
	private String linkispass;// VARCHAR(10)
	private String issongshen;// VARCHAR(10)
	private String linkreplycontent;// CHAR(1000)
	private String linkmemo;// CHAR(1000)
	private String linkreason;// CHAR(1000)
	private int linkstatus;// INTEGER
	private String deptlevel;// VARCHAR(30)

}
