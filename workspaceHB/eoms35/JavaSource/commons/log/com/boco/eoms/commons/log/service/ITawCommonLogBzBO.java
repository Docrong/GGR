package com.boco.eoms.commons.log.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface ITawCommonLogBzBO {

	public abstract void saveLog(String classname, String userid,
			String operid, String remoteip, String loglevel, String message);
	
	public void saveLogging(String classname, String userid, String operid,
			String remoteip, String loglevel, String message);

	public abstract List getAllBymodelopertiems(HttpServletRequest request,final Integer curPage, 
			final Integer pageSize,final String userid,final String modelid,
			final String operid,final String starttime,final String endtime,final String issucess);

	public abstract List getAllByUserIDs(String userid, String issucess);

	public abstract List getAllBymodelids(String modelid, String issucess);

	public abstract List getAllByuseridandModelids(String userid,
			String modelid, String issucess);

	public abstract List getAllbyUseridModelidOperids(final Integer curPage, 
			final Integer pageSize,String userid,
			String modelid, String operid, String issucess);

	public abstract List getAllbyModelidAndOperids(String modelid,
			String operid, String issucess);

	public abstract List getAllByUseridAndTimes(String userid,
			String starttime, String endtime, String issucess);

	public abstract List getAllByModelidAndTimes(String modelid,
			String starttiem, String endtime, String issucess);

	public abstract List getAllByUMIDandTimes(String userid, String modelid,
			String starttime, String endtime, String issucess);

	public abstract List getAllByMidAndOperidAndTimes(String modelid,
			String operid, String startime, String endtime, String issucess);
	
	public void removeNoteBytimes(String modelid,String operid,String begintime,String endtime);

}
