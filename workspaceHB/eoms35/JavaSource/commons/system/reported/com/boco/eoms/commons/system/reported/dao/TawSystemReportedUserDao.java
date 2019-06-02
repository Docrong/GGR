package com.boco.eoms.commons.system.reported.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.system.reported.model.TawSystemReportedUser;

public interface TawSystemReportedUserDao extends Dao {

	public void saveTawSystemReportedUser(final TawSystemReportedUser tawSystemReportedUser);
	public void removeTawSystemReportedUser(final String id);
	public TawSystemReportedUser getTawSystemReportedUser(final String id);
	public List getTawSystemReportedUsers(final TawSystemReportedUser tawSystemReportedUser);
	public List getTawSystemReportedUsersByReportId(final String reportId);
}
