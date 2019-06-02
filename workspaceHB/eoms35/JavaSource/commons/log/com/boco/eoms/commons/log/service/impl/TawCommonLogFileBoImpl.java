package com.boco.eoms.commons.log.service.impl;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;

import com.boco.eoms.commons.log.dao.castor.TawCommonLogAppendXML;
import com.boco.eoms.commons.log.dao.castor.TawCommonLogFileOperatorHandel;

import com.boco.eoms.commons.log.model.castor.TawCommonLogFileOperator;
import com.boco.eoms.commons.log.model.castor.TawCommonLogFileOperatorMappiing;
import com.boco.eoms.commons.log.service.ITawCommonLogFileBo;
import com.boco.eoms.commons.log.service.util.TawCommonLogGetFilePath;

/**
 * 从XML中读取日志信息
 * 
 * @author panlong
 * @Mar 23, 2007 8:13:42 PM
 */
public class TawCommonLogFileBoImpl implements ITawCommonLogFileBo {

	/**
	 * 根据用户ID 模块ID 操作ID 文件路径查找日志数据 文件路径是从数据库取 在配置的时候已经配好了
	 */
	public List getLognoteBymidopids(String userid, String modelid,
			String operid, String issucess, String filepath) {

		TawCommonLogGetFilePath infofilepath = new TawCommonLogGetFilePath();

		Set infofilepathlist = new HashSet();
		List list = new ArrayList();
		List notelists = new ArrayList();
		try {
			infofilepathlist = infofilepath
					.getFilepathByUseridAndModelidAndOperids(userid, modelid,
							operid);
			TawCommonLogFileOperatorHandel handle = null;
			TawCommonLogFileOperatorMappiing contentcollection = null;

			for (Iterator itfilepath = infofilepathlist.iterator(); itfilepath
					.hasNext();) {
				String strfilepath = itfilepath.next().toString();
				handle = new TawCommonLogFileOperatorHandel("", strfilepath);
				contentcollection = handle.getElementc();
				list = contentcollection.getBocologcontents();
				for (Iterator it = list.iterator(); it.hasNext();) {
					TawCommonLogFileOperator bean = new TawCommonLogFileOperator();
					bean = (TawCommonLogFileOperator) it.next();
					if (issucess.equalsIgnoreCase("all")) {

						notelists.add(bean);
					} else if (issucess.equalsIgnoreCase("error")) {
						if (bean.getLoglevel().equalsIgnoreCase("error")) {
							notelists.add(bean);
						}
					} else {
						if (!bean.getLoglevel().equalsIgnoreCase("error")) {
							notelists.add(bean);
						}
					}
				}
			}
		} catch (Exception e) {

			BocoLog.error(this, "查询的日志文件不存在! " + e.getMessage());
		}

		return notelists;

	}

	/**
	 * 根据模块ID 操作业务ID 文件路径进行日志查询
	 */
	public List getLognoteBymodelIDandoperIDs(String modelid, String operid,
			String issucess, String filepath) {

		TawCommonLogGetFilePath infofilepath = new TawCommonLogGetFilePath();

		Set infofilepathlist = new HashSet();
		List list = new ArrayList();
		List notelists = new ArrayList();
		try {
			infofilepathlist = infofilepath.getFilepathByMOIDs(modelid, operid);
			TawCommonLogFileOperatorHandel handle = null;
			TawCommonLogFileOperatorMappiing contentcollection = null;

			for (Iterator itfilepath = infofilepathlist.iterator(); itfilepath
					.hasNext();) {
				String strfilepath = itfilepath.next().toString();
				handle = new TawCommonLogFileOperatorHandel("", strfilepath);
				contentcollection = handle.getElementc();
				list = contentcollection.getBocologcontents();
				for (Iterator it = list.iterator(); it.hasNext();) {
					TawCommonLogFileOperator bean = new TawCommonLogFileOperator();
					bean = (TawCommonLogFileOperator) it.next();
					if (issucess.equalsIgnoreCase("all")) {
						notelists.add(bean);
					} else if (issucess.equalsIgnoreCase("error")) {
						if (bean.getLoglevel().equalsIgnoreCase("error")) {
							notelists.add(bean);
						}
					} else {
						if (!bean.getLoglevel().equalsIgnoreCase("error")) {
							notelists.add(bean);
						}
					}
				}
			}
		} catch (Exception e) {

			BocoLog.error(this, "查询的日志文件不存在! " + e.getMessage());
		}

		return notelists;
	}

	/**
	 * 根据模块ID 查找模块日志信息
	 * 
	 * @throws Exception
	 */
	public List getLognoteBymodelids(String modelid, String issucess,
			String filepath) {

		TawCommonLogGetFilePath infofilepath = new TawCommonLogGetFilePath();

		Set infofilepathlist = new HashSet();
		List list = new ArrayList();
		List notelists = new ArrayList();

		try {
			infofilepathlist = infofilepath.getFilepathBymodelids(modelid);
			TawCommonLogFileOperatorHandel handle = null;
			TawCommonLogFileOperatorMappiing contentcollection = null;

			for (Iterator itfilepath = infofilepathlist.iterator(); itfilepath
					.hasNext();) {
				String strfilepath = itfilepath.next().toString();
				handle = new TawCommonLogFileOperatorHandel("", strfilepath);
				contentcollection = handle.getElementc();
				list = contentcollection.getBocologcontents();
				for (Iterator it = list.iterator(); it.hasNext();) {
					TawCommonLogFileOperator bean = new TawCommonLogFileOperator();
					bean = (TawCommonLogFileOperator) it.next();
					if (issucess.equalsIgnoreCase("all")) {
						notelists.add(bean);
					} else if (issucess.equalsIgnoreCase("error")) {
						if (bean.getLoglevel().equalsIgnoreCase("error")) {
							notelists.add(bean);
						}
					} else {
						if (!bean.getLoglevel().equalsIgnoreCase("error")) {
							notelists.add(bean);
						}
					}
				}
			}
		} catch (Exception e) {

			BocoLog.error(this, "查询日志信息出错! " + e.getMessage());
		}

		return notelists;
	}

	/**
	 * 根据模块ID 时间段 查找日志信息
	 */
	public List getLognoteBytimes(String modelid, String starttime,
			String endtime, String issucess, String filepath) {

		TawCommonLogGetFilePath infofilepath = new TawCommonLogGetFilePath();

		Set infofilepathlist = new HashSet();

		List list = new ArrayList();
		List notelists = new ArrayList();
		try {
			infofilepathlist = infofilepath.getFilepathModelidAndTimes(modelid,
					starttime, endtime);
			TawCommonLogFileOperatorHandel handle = null;
			TawCommonLogFileOperatorMappiing contentcollection = null;

			for (Iterator itfilepath = infofilepathlist.iterator(); itfilepath
					.hasNext();) {
				String strfilepath = itfilepath.next().toString();
				handle = new TawCommonLogFileOperatorHandel("", strfilepath);
				contentcollection = handle.getElementc();
				list = contentcollection.getBocologcontents();
				for (Iterator it = list.iterator(); it.hasNext();) {
					TawCommonLogFileOperator bean = new TawCommonLogFileOperator();
					bean = (TawCommonLogFileOperator) it.next();
					if (issucess.equalsIgnoreCase("all")) {

						notelists.add(bean);
					} else if (issucess.equalsIgnoreCase("error")) {
						if (bean.getLoglevel().equalsIgnoreCase("error")) {
							notelists.add(bean);
						}
					} else {
						if (!bean.getLoglevel().equalsIgnoreCase("error")) {
							notelists.add(bean);
						}
					}
				}
			}
		} catch (Exception e) {

			BocoLog.error(this, "查询日志信息出错! " + e.getMessage());
		}

		return notelists;
	}

	/**
	 * 根据模块ID 业务ID 查找日志信息
	 */
	public List getLognotebyMOid(String modelid, String operid,
			String begintime, String endtime, String issucess, String filepath) {

		TawCommonLogGetFilePath infofilepath = new TawCommonLogGetFilePath();

		Set infofilepathlist = new HashSet();
		List list = new ArrayList();
		List notelists = new ArrayList();
		try {
			infofilepathlist = infofilepath.getFilepathMOIDAndTimes(modelid,
					operid, begintime, endtime);
			TawCommonLogFileOperatorHandel handle = null;
			TawCommonLogFileOperatorMappiing contentcollection = null;

			for (Iterator itfilepath = infofilepathlist.iterator(); itfilepath
					.hasNext();) {
				String strfilepath = itfilepath.next().toString();
				handle = new TawCommonLogFileOperatorHandel("", strfilepath);
				contentcollection = handle.getElementc();
				list = contentcollection.getBocologcontents();
				for (Iterator it = list.iterator(); it.hasNext();) {
					TawCommonLogFileOperator bean = new TawCommonLogFileOperator();
					bean = (TawCommonLogFileOperator) it.next();

					if (issucess.equalsIgnoreCase("all")) {

						infofilepathlist.add(bean);
					} else if (issucess.equalsIgnoreCase("error")) {
						if (bean.getLoglevel().equalsIgnoreCase("error")) {
							infofilepathlist.add(bean);
						}
					} else {
						if (!bean.getLoglevel().equalsIgnoreCase("error")) {
							infofilepathlist.add(bean);
						}
					}

				}
			}
		} catch (Exception e) {

			BocoLog.error(this, "查询日志信息出错! " + e.getMessage());
		}

		return notelists;
	}

	/**
	 * 根据用户ID 模块ID 时间段 文件路径查找日志信息
	 */
	public List getLognoteBytimes(String userid, String modelid,
			String starttime, String endtime, String issucess, String filepath) {

		TawCommonLogGetFilePath infofilepath = new TawCommonLogGetFilePath();

		Set infofilepathlist = new HashSet();
		List list = new ArrayList();
		List notelists = new ArrayList();

		try {
			infofilepathlist = infofilepath
					.getFilepathByUseridAndModelIDAndTimes(userid, modelid,
							starttime, endtime);
			TawCommonLogFileOperatorHandel handle = null;
			TawCommonLogFileOperatorMappiing contentcollection = null;

			for (Iterator itfilepath = infofilepathlist.iterator(); itfilepath
					.hasNext();) {
				String strfilepath = itfilepath.next().toString();
				handle = new TawCommonLogFileOperatorHandel("", strfilepath);
				contentcollection = handle.getElementc();
				list = contentcollection.getBocologcontents();
				for (Iterator it = list.iterator(); it.hasNext();) {
					TawCommonLogFileOperator bean = new TawCommonLogFileOperator();
					bean = (TawCommonLogFileOperator) it.next();

					if (issucess.equalsIgnoreCase("all")) {

						notelists.add(bean);
					} else if (issucess.equalsIgnoreCase("error")) {
						if (bean.getLoglevel().equalsIgnoreCase("error")) {
							notelists.add(bean);
						}
					} else {
						if (!bean.getLoglevel().equalsIgnoreCase("error")) {
							notelists.add(bean);
						}
					}
				}
			}

		} catch (Exception e) {

			BocoLog.error(this, "查询日志信息出错! " + e.getMessage());
		}
		return notelists;
	}

	/**
	 * 根据用户ID
	 */
	public List getLognotebyUserIDs(String userID, String issucess,
			String filepath) {

		TawCommonLogGetFilePath infofilepath = new TawCommonLogGetFilePath();

		Set infofilepathlist = new HashSet();
		List list = new ArrayList();
		List notelists = new ArrayList();
		try {
			infofilepathlist = infofilepath.getFilepathByuserids(userID);
			TawCommonLogFileOperatorHandel handle = null;
			TawCommonLogFileOperatorMappiing contentcollection = null;

			for (Iterator itfilepath = infofilepathlist.iterator(); itfilepath
					.hasNext();) {
				String strfilepath = itfilepath.next().toString();

				handle = new TawCommonLogFileOperatorHandel("", strfilepath);
				contentcollection = handle.getElementc();
				list = contentcollection.getBocologcontents();
				for (Iterator it = list.iterator(); it.hasNext();) {
					TawCommonLogFileOperator bean = new TawCommonLogFileOperator();
					bean = (TawCommonLogFileOperator) it.next();
					if (issucess.equalsIgnoreCase("all")) {

						notelists.add(bean);

					} else if (issucess.equalsIgnoreCase("error")) {
						if (bean.getLoglevel().equalsIgnoreCase("error")) {
							notelists.add(bean);
						}
					} else {
						if (!bean.getLoglevel().equalsIgnoreCase("error")) {
							notelists.add(bean);
						}
					}
				}
			}
		} catch (Exception e) {

			BocoLog.error(this, "查询的日志文件不存在! " + e.getMessage());
		}

		return notelists;
	}

	/**
	 * 查找所有的日志信息
	 */
	public List getLognotes(String issucess, String filepath) {

		TawCommonLogGetFilePath infofilepath = new TawCommonLogGetFilePath();

		Set infofilepathlist = new HashSet();
		List list = new ArrayList();
		List notelists = new ArrayList();

		try {
			infofilepathlist = infofilepath.getFilepathAllTimes();
			TawCommonLogFileOperatorHandel handle = null;
			TawCommonLogFileOperatorMappiing contentcollection = null;

			for (Iterator itfilepath = infofilepathlist.iterator(); itfilepath
					.hasNext();) {
				String strfilepath = itfilepath.next().toString();
				handle = new TawCommonLogFileOperatorHandel("", strfilepath);
				contentcollection = handle.getElementc();
				list = contentcollection.getBocologcontents();
				for (Iterator it = list.iterator(); it.hasNext();) {
					TawCommonLogFileOperator bean = new TawCommonLogFileOperator();
					bean = (TawCommonLogFileOperator) it.next();
					if (issucess.equalsIgnoreCase("all")) {
						notelists.add(bean);
					} else if (issucess.equalsIgnoreCase("error")) {
						if (bean.getLoglevel().equalsIgnoreCase("error")) {
							notelists.add(bean);
						}
					} else {
						if (!bean.getLoglevel().equalsIgnoreCase("error")) {
							notelists.add(bean);
						}
					}
				}
			}

		} catch (Exception e) {

			BocoLog.error(this, "查询的日志文件不存在! " + e.getMessage());
		}

		return notelists;
	}

	/**
	 * 保存日志信息
	 */
	public void saveLogtoFile(String classname, String userID, String modelID,
			String modelname, String opername, String operID, String loglevel,
			String remotecomputerip, String filepath, String message,
			String operremark, String filecutsize) {

		String xmlfilepath = StaticMethod
				.getFilePath("claspath:com/boco/eoms/commons/log/dao/castor/tawcommonlogfileinfo.xml");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TawCommonLogAppendXML appendxml;
		String currenttime = String.valueOf(df.format(new Date()));

		String days = getOperDay("days");
		String months = getOperDay("months");
		String years = getOperDay("years");
		String filenamess = modelID + years + "-" + months + "-" + days;

		String filename = filepath + filenamess + ".xml";
		String classpath = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath()
				+ "";
		String logfilepaths = classpath.substring(1, classpath
				.lastIndexOf("/WEB-INF/classes/"));
		String logfilepath = logfilepaths.replaceAll("/", "\\\\");
		filepath = logfilepath + filename;

		File file = new File(filepath);
		appendxml = new TawCommonLogAppendXML();
		if (file.exists()
				&& file.length() <= Long.valueOf(filecutsize).longValue()) {

			appendxml.saveToxml(classname, userID, modelID, modelname, operID,
					opername, currenttime, years, months, days, loglevel,
					remotecomputerip, filepath, message, operremark);

			appendxml.saveinfoToxml(currenttime, modelID, operID, userID,
					xmlfilepath, filepath, loglevel);
		} else {

			if (file.exists()
					&& file.length() > Long.valueOf(filecutsize).longValue()) {
				String[] rename = currenttime.split(" ");
				String newfilename = rename[1];
				String oldnames = file.getName();
				//将oldnames.replace改为oldnames.replaceAll,by qjb
				String oldfilename = oldnames.replaceAll(".xml", "-");

				newfilename = file.getPath().replaceAll(oldnames, "")
						+ oldfilename + newfilename + ".xml";
				String ctnewfilename = file.getPath().replaceAll(oldnames, "")
						+ oldfilename + newfilename + "8.xml";
				File tempFile = new File(newfilename);
				if (tempFile.exists()) {
					File tempFiles = new File(ctnewfilename);
					BocoLog.info(this, file.renameTo(tempFiles) + "8");
				} else {
					BocoLog.info(this, file.renameTo(tempFile) + "");
				}
			}
			TawCommonLogFileOperatorHandel operhandel = new TawCommonLogFileOperatorHandel(
					"", filepath);
			operhandel.saveDoc(classname, userID, modelID, modelname, operID,
					opername, currenttime, years, months, days, loglevel,
					remotecomputerip, filepath, message, operremark);
			appendxml.saveinfoToxml(currenttime, modelID, operID, userID,
					xmlfilepath, filepath, loglevel);
		}

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

	public List getLognoteByModelIdAndOperidAndtimes(String modelid,
			String operid, String starttime, String endtime, String issucess,
			String filepath) {

		TawCommonLogGetFilePath infofilepath = new TawCommonLogGetFilePath();

		Set infofilepathlist = new HashSet();
		List list = new ArrayList();
		List notelists = new ArrayList();

		try {
			infofilepathlist = infofilepath.getFilepathMOIDAndTimes(modelid,
					operid, starttime, endtime);
			TawCommonLogFileOperatorHandel handle = null;
			TawCommonLogFileOperatorMappiing contentcollection = null;

			for (Iterator itfilepath = infofilepathlist.iterator(); itfilepath
					.hasNext();) {
				String strfilepath = itfilepath.next().toString();
				handle = new TawCommonLogFileOperatorHandel("", strfilepath);
				contentcollection = handle.getElementc();
				list = contentcollection.getBocologcontents();
				for (Iterator it = list.iterator(); it.hasNext();) {
					TawCommonLogFileOperator bean = new TawCommonLogFileOperator();
					bean = (TawCommonLogFileOperator) it.next();

					if (issucess.equalsIgnoreCase("all")) {
						notelists.add(bean);
					} else if (issucess.equalsIgnoreCase("error")) {
						if (bean.getLoglevel().equalsIgnoreCase("error")) {
							notelists.add(bean);
						}
					} else {
						if (!bean.getLoglevel().equalsIgnoreCase("error")) {
							notelists.add(bean);
						}
					}
				}
			}
		} catch (Exception e) {

			BocoLog.error(this, "查询的日志文件不存在! " + e.getMessage());
		}

		return notelists;
	}

	public List getLognoteByUseridAndTimes(String userid, String starttime,
			String endtime, String issucess, String filepath) {

		TawCommonLogGetFilePath infofilepath = new TawCommonLogGetFilePath();

		Set infofilepathlist = new HashSet();
		List list = new ArrayList();
		List notelists = new ArrayList();
		try {
			infofilepathlist = infofilepath.getFilepathByUseridAndTime(userid,
					starttime, endtime);
			TawCommonLogFileOperatorHandel handle = null;
			TawCommonLogFileOperatorMappiing contentcollection = null;

			for (Iterator itfilepath = infofilepathlist.iterator(); itfilepath
					.hasNext();) {
				String strfilepath = itfilepath.next().toString();
				handle = new TawCommonLogFileOperatorHandel("", strfilepath);
				contentcollection = handle.getElementc();
				list = contentcollection.getBocologcontents();
				for (Iterator it = list.iterator(); it.hasNext();) {
					TawCommonLogFileOperator bean = new TawCommonLogFileOperator();
					bean = (TawCommonLogFileOperator) it.next();

					if (issucess.equalsIgnoreCase("all")) {

						notelists.add(bean);
					} else if (issucess.equalsIgnoreCase("error")) {
						if (bean.getLoglevel().equalsIgnoreCase("error")) {
							notelists.add(bean);
						}
					} else {
						if (!bean.getLoglevel().equalsIgnoreCase("error")) {
							notelists.add(bean);
						}
					}
				}
			}
		} catch (Exception e) {

			BocoLog.error(this, "查询的日志文件不存在! " + e.getMessage());
		}

		return list;
	}

	public List getLognoteByAllTimes(String userid, String modelid,
			String operid, String starttime, String endtime, String issucess,
			String filepath) {
		TawCommonLogGetFilePath infofilepath = new TawCommonLogGetFilePath();

		Set infofilepathlist = new HashSet();

		List list = new ArrayList();
		List notelists = new ArrayList();
		try {
			infofilepathlist = infofilepath.getFilepathByUMOIDAndTimes(userid,
					modelid, operid, starttime, endtime);
			TawCommonLogFileOperatorHandel handle = null;
			TawCommonLogFileOperatorMappiing contentcollection = null;

			for (Iterator itfilepath = infofilepathlist.iterator(); itfilepath
					.hasNext();) {
				String strfilepath = itfilepath.next().toString();
				handle = new TawCommonLogFileOperatorHandel("", strfilepath);
				contentcollection = handle.getElementc();
				list = contentcollection.getBocologcontents();
				for (Iterator it = list.iterator(); it.hasNext();) {
					TawCommonLogFileOperator bean = new TawCommonLogFileOperator();
					bean = (TawCommonLogFileOperator) it.next();

					if (issucess.equalsIgnoreCase("all")) {

						notelists.add(bean);
					} else if (issucess.equalsIgnoreCase("error")) {
						if (bean.getLoglevel().equalsIgnoreCase("error")) {
							list.add(bean);
						}
					} else {
						if (!bean.getLoglevel().equalsIgnoreCase("error")) {
							notelists.add(bean);
						}
					}
				}
			}
		} catch (Exception e) {

			BocoLog.error(this, "查询的日志文件不存在! " + e.getMessage());
		}

		return list;
	}

	public List getLognoteByAllTimes(String userid, String modelid,
			String issucess, String filepath) {
		TawCommonLogGetFilePath infofilepath = new TawCommonLogGetFilePath();

		Set infofilepathlist = new HashSet();
		List list = new ArrayList();
		List notelists = new ArrayList();
		try {
			infofilepathlist = infofilepath.getFilepathByUseridAndModelIDs(
					userid, modelid);
			TawCommonLogFileOperatorHandel handle = null;
			TawCommonLogFileOperatorMappiing contentcollection = null;

			for (Iterator itfilepath = infofilepathlist.iterator(); itfilepath
					.hasNext();) {
				String strfilepath = itfilepath.next().toString();
				handle = new TawCommonLogFileOperatorHandel("", strfilepath);
				contentcollection = handle.getElementc();
				list = contentcollection.getBocologcontents();
				for (Iterator it = list.iterator(); it.hasNext();) {
					TawCommonLogFileOperator bean = new TawCommonLogFileOperator();
					bean = (TawCommonLogFileOperator) it.next();

					if (issucess.equalsIgnoreCase("all")) {

						notelists.add(bean);

					} else if (issucess.equalsIgnoreCase("error")) {
						if (bean.getLoglevel().equalsIgnoreCase("error")) {
							notelists.add(bean);
						}
					} else {
						if (!bean.getLoglevel().equalsIgnoreCase("error")) {
							notelists.add(bean);
						}
					}
				}
			}
		} catch (Exception e) {

			BocoLog.error(this, "查询的日志文件不存在! " + e.getMessage());
		}

		return list;
	}

}
