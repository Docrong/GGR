package com.boco.eoms.commons.log.service;

import java.util.List;

public interface ITawCommonLogFileBo {

	public void saveLogtoFile(String classname, String userID, String modelID,
			String modelname, String opername, String operID, String loglevel,
			String remotecomputerip, String filepath, String message,
			String operremark, String filecutsize);

	public abstract List getLognotes(String issucess, String filepath);

	public abstract List getLognotebyUserIDs(String userID, String issucess,
			String filepath);

	public abstract List getLognoteBymodelids(String modelid, String issucess,
			String filepath);

	public abstract List getLognotebyMOid(String modelid, String operid,
			String begintime, String endtime, String issucess, String filepath);

	public abstract List getLognoteBymodelIDandoperIDs(String modelid,
			String operid, String issucess, String filepath);

	public abstract List getLognoteBytimes(String modelid, String starttime,
			String endtime, String issucess, String filepath);

	public abstract List getLognoteBymidopids(String userID, String modelid,
			String operid, String issucess, String filepath);

	public abstract List getLognoteBytimes(String userid, String modelid,
			String starttime, String endtime, String issucess, String filepath);

	public abstract List getLognoteByModelIdAndOperidAndtimes(String modelid,
			String operid, String starttime, String endtime, String issucess,
			String filepath);

	public abstract List getLognoteByUseridAndTimes(String userid,
			String starttime, String endtime, String issucess, String filepath);

	public abstract List getLognoteByAllTimes(String userid, String modelid,
			String operid, String starttime, String endtime, String issucess,
			String filepath);

	public abstract List getLognoteByAllTimes(String userid, String modelid,
			String issucess, String filepath);
}
