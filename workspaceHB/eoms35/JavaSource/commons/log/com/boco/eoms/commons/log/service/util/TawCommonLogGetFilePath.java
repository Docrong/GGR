package com.boco.eoms.commons.log.service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.boco.eoms.commons.log.dao.castor.TawCommonFileInfoHandlel;
import com.boco.eoms.commons.log.model.castor.TawCommonLogFileInfo;

public class TawCommonLogGetFilePath {

	public Set getFilepathByuserids(String userid) throws Exception {

		TawCommonLogFileInfo fileinfo = new TawCommonLogFileInfo();
		List list = new ArrayList();
		TawCommonFileInfoHandlel infohandle = new TawCommonFileInfoHandlel();
		list = infohandle.getFileInfO();
		Set filepathlist = new HashSet();
		for (Iterator it = list.iterator(); it.hasNext();) {
			fileinfo = (TawCommonLogFileInfo) it.next();
			if (fileinfo.getUserid().equalsIgnoreCase(userid)) {
				filepathlist.add(fileinfo.getFilepath());
			}
		}
		return filepathlist;
	}

	public Set getFilepathBymodelids(String modelid) throws Exception {

		TawCommonLogFileInfo fileinfo = new TawCommonLogFileInfo();

		Set filepathlist = new HashSet();
		List list = new ArrayList();
		TawCommonFileInfoHandlel infohandle = new TawCommonFileInfoHandlel();
		list = infohandle.getFileInfO();
		for (Iterator it = list.iterator(); it.hasNext();) {
			fileinfo = (TawCommonLogFileInfo) it.next();
			if (fileinfo.getModeid().equalsIgnoreCase(modelid)) {
				filepathlist.add(fileinfo.getFilepath());
			}
		}
		return filepathlist;
	}

	public Set getFilepathByUseridAndModelIDs(String userid, String modelid)
			throws Exception {
		TawCommonLogFileInfo fileinfo = new TawCommonLogFileInfo();
		List list = new ArrayList();
		TawCommonFileInfoHandlel infohandle = new TawCommonFileInfoHandlel();
		list = infohandle.getFileInfO();
		Set filepathlist = new HashSet();
		for (Iterator it = list.iterator(); it.hasNext();) {
			fileinfo = (TawCommonLogFileInfo) it.next();
			if (fileinfo.getModeid().equalsIgnoreCase(modelid)
					&& fileinfo.getUserid().equalsIgnoreCase(userid)) {
				filepathlist.add(fileinfo.getFilepath());
			}
		}
		return filepathlist;
	}

	public Set getFilepathByMOIDs(String modelid, String operid)
			throws Exception {
		TawCommonLogFileInfo fileinfo = new TawCommonLogFileInfo();
		List list = new ArrayList();
		TawCommonFileInfoHandlel infohandle = new TawCommonFileInfoHandlel();
		list = infohandle.getFileInfO();
		Set filepathlist = new HashSet();
		for (Iterator it = list.iterator(); it.hasNext();) {
			fileinfo = (TawCommonLogFileInfo) it.next();
			if (fileinfo.getModeid().equalsIgnoreCase(modelid)
					&& fileinfo.getOperid().equals(operid)) {
				filepathlist.add(fileinfo.getFilepath());
			}
		}
		return filepathlist;
	}

	public Set getFilepathByUseridAndModelidAndOperids(String userid,
			String modelid, String operid) throws Exception {
		TawCommonLogFileInfo fileinfo = new TawCommonLogFileInfo();
		List list = new ArrayList();
		TawCommonFileInfoHandlel infohandle = new TawCommonFileInfoHandlel();
		list = infohandle.getFileInfO();
		Set filepathlist = new HashSet();
		for (Iterator it = list.iterator(); it.hasNext();) {
			fileinfo = (TawCommonLogFileInfo) it.next();
			if (fileinfo.getModeid().equalsIgnoreCase(modelid)
					&& fileinfo.getUserid().equalsIgnoreCase(userid)
					&& fileinfo.getOperid().equals(operid)) {
				filepathlist.add(fileinfo.getFilepath());
			}
		}
		return filepathlist;
	}

	public Set getFilepathByUseridAndTime(String userid, String starttime,
			String endtime) throws Exception {
		TawCommonLogFileInfo fileinfo = new TawCommonLogFileInfo();
		List list = new ArrayList();
		TawCommonFileInfoHandlel infohandle = new TawCommonFileInfoHandlel();
		list = infohandle.getFileInfO();
		Set filepathset = new HashSet();
		for (Iterator it = list.iterator(); it.hasNext();) {
			fileinfo = (TawCommonLogFileInfo) it.next();
			if (fileinfo.getUserid().equalsIgnoreCase(userid)
					&& getFlag(fileinfo.getSavetime(), starttime, endtime)) {
				filepathset.add(fileinfo.getFilepath());
			}
		}
		return filepathset;
	}

	public Set getFilepathByUseridAndModelIDAndTimes(String userid,
			String modelid, String starttime, String endtime) throws Exception {
		TawCommonLogFileInfo fileinfo = new TawCommonLogFileInfo();
		List list = new ArrayList();
		TawCommonFileInfoHandlel infohandle = new TawCommonFileInfoHandlel();
		list = infohandle.getFileInfO();
		Set filepathset = new HashSet();
		for (Iterator it = list.iterator(); it.hasNext();) {
			fileinfo = (TawCommonLogFileInfo) it.next();
			if (fileinfo.getUserid().equalsIgnoreCase(userid)
					&& fileinfo.getModeid().equals(modelid)
					&& getFlag(fileinfo.getSavetime(), starttime, endtime)) {
				filepathset.add(fileinfo.getFilepath());
			}
		}
		return filepathset;
	}

	public Set getFilepathByUMOIDAndTimes(String userid, String modelid,
			String operid, String starttime, String endtime) throws Exception {
		TawCommonLogFileInfo fileinfo = new TawCommonLogFileInfo();
		List list = new ArrayList();
		TawCommonFileInfoHandlel infohandle = new TawCommonFileInfoHandlel();
		list = infohandle.getFileInfO();
		Set filepathset = new HashSet();
		for (Iterator it = list.iterator(); it.hasNext();) {
			fileinfo = (TawCommonLogFileInfo) it.next();
			if (fileinfo.getUserid().equalsIgnoreCase(userid)
					&& fileinfo.getOperid().equals(operid)
					&& fileinfo.getModeid().equals(modelid)
					&& getFlag(fileinfo.getSavetime(), starttime, endtime)) {
				filepathset.add(fileinfo.getFilepath());
			}
		}
		return filepathset;
	}

	public Set getFilepathMOIDAndTimes(String modelid, String operid,
			String starttime, String endtime) throws Exception {
		TawCommonLogFileInfo fileinfo = new TawCommonLogFileInfo();
		List list = new ArrayList();
		TawCommonFileInfoHandlel infohandle = new TawCommonFileInfoHandlel();
		list = infohandle.getFileInfO();
		Set filepathset = new HashSet();
		for (Iterator it = list.iterator(); it.hasNext();) {
			fileinfo = (TawCommonLogFileInfo) it.next();
			if (fileinfo.getOperid().equals(operid)
					&& fileinfo.getModeid().equals(modelid)
					&& getFlag(fileinfo.getSavetime(), starttime, endtime)) {
				filepathset.add(fileinfo.getFilepath());
			}
		}
		return filepathset;
	}

	public Set getFilepathModelidAndTimes(String modelid, String starttime,
			String endtime) throws Exception {
		TawCommonLogFileInfo fileinfo = new TawCommonLogFileInfo();
		List list = new ArrayList();
		TawCommonFileInfoHandlel infohandle = new TawCommonFileInfoHandlel();
		list = infohandle.getFileInfO();
		Set filepathset = new HashSet();
		for (Iterator it = list.iterator(); it.hasNext();) {
			fileinfo = (TawCommonLogFileInfo) it.next();
			if (fileinfo.getModeid().equals(modelid)
					&& getFlag(fileinfo.getSavetime(), starttime, endtime)) {
				filepathset.add(fileinfo.getFilepath());
			}
		}
		return filepathset;
	}

	public Set getFilepathAllTimes() throws Exception {
		TawCommonLogFileInfo fileinfo = new TawCommonLogFileInfo();
		List list = new ArrayList();
		TawCommonFileInfoHandlel infohandle = new TawCommonFileInfoHandlel();
		list = infohandle.getFileInfO();
		Set filepathset = null;
		for (Iterator it = list.iterator(); it.hasNext();) {
			fileinfo = (TawCommonLogFileInfo) it.next();
			filepathset = new HashSet();
			filepathset.add(fileinfo.getFilepath());

		}
		return filepathset;
	}

	private boolean getFlag(String savetime, String starttime, String endtime) {
		boolean flag = false;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		try {
			Date startdate = df.parse(starttime);
			Date enddate = df.parse(endtime);
			Date savedate = df.parse(savetime);

			if (startdate.before(savedate) && savedate.after(enddate)) {
				flag = true;
			}

		} catch (ParseException e) {

			e.printStackTrace();
		}

		return flag;
	}
}
