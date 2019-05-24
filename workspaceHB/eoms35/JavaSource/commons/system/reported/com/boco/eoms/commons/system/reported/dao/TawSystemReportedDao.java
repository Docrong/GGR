package com.boco.eoms.commons.system.reported.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.system.reported.model.TawSystemReported;

public interface TawSystemReportedDao extends Dao {
	public void saveTawSystemReported(final TawSystemReported tawSystemReported);
	public void removeTawSystemReported(final String id);
	public TawSystemReported getTawSystemReported(final String id);
	public Map getTawSystemReporteds(final Integer curPage, final Integer pageSize,
			final String whereStr);
	public List getReportedUsers(final Integer modelId,	final Integer functionId, final String userId);
	public List getTawSystemReporteds(final TawSystemReported tawSystemReported);
}
