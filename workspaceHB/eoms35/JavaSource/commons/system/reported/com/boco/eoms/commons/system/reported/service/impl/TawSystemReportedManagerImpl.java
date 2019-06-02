package com.boco.eoms.commons.system.reported.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.system.reported.service.TawSystemReportedManager;
import com.boco.eoms.commons.system.reported.dao.TawSystemReportedDao;
import com.boco.eoms.commons.system.reported.dao.TawSystemReportedUserDao;
import com.boco.eoms.commons.system.reported.model.TawSystemReported;
import com.boco.eoms.commons.system.reported.model.TawSystemReportedUser;

public class TawSystemReportedManagerImpl extends BaseManager implements
		TawSystemReportedManager {

	private TawSystemReportedDao dao;

	public void setTawSystemReportedDao(TawSystemReportedDao dao) {
		this.dao = dao;
	}

	private TawSystemReportedUserDao reportDao;

	public void setTawSystemReportedUserDao(TawSystemReportedUserDao reportDao) {
		this.reportDao = reportDao;
	}

	public TawSystemReported getTawSystemReported(final String id) {
		return dao.getTawSystemReported(new String(id));
	}

	// 保存上报信息的同时，把选择的上报人员存储到上报人员表中
	public void saveTawSystemReported(TawSystemReported tawSystemReported,
			List reportedOrgs) {
		dao.saveTawSystemReported(tawSystemReported);
		if (!reportedOrgs.isEmpty() && reportedOrgs != null) {
			for (Iterator it = reportedOrgs.iterator(); it.hasNext();) {
				TawSystemReportedUser reportedUser = (TawSystemReportedUser) it.next();
				reportedUser.setReportedId(tawSystemReported.getId());
				reportDao.saveTawSystemReportedUser(reportedUser);
			}
		}
	}

	// 删除上报配置信息的同时，删除相关的上报人员表中的数据
	public void removeTawSystemReported(final String id) {
		dao.removeTawSystemReported(id);
		List list = reportDao.getTawSystemReportedUsersByReportId(id);
		for (int i = 0; i < list.size(); i++) {
			TawSystemReportedUser tawSystemReportedUser = (TawSystemReportedUser) list
					.get(i);
			reportDao
					.removeTawSystemReportedUser(tawSystemReportedUser.getId());
		}
	}

	// 取出上报信息（用于列表）
	public Map getTawSystemReportedPage(Integer curPage, Integer pageSize,
			String whereStr) {
		return dao.getTawSystemReporteds(curPage, pageSize, whereStr);
	}

	// 修改上报信息，由于修改上报配置信息时用户会修改上报人员列表中的数据，所以需要将原列表中的数据删除，再插入用户修后的数据
	public void updateTawSystemReported(TawSystemReported tawSystemReported,
			List reportedOrgs) {
		dao.saveTawSystemReported(tawSystemReported);
		List userList = reportDao
				.getTawSystemReportedUsersByReportId(tawSystemReported.getId());
		// 删除上报人员表中的数据
		for (int i = 0; i < userList.size(); i++) {
			TawSystemReportedUser reportedUserDel = (TawSystemReportedUser) userList
					.get(i);
			reportDao.removeTawSystemReportedUser(reportedUserDel.getId());
		}
		// 将修改后的数据存放到上报人员表中
		if (!reportedOrgs.isEmpty() && reportedOrgs != null) {
			for (Iterator it = reportedOrgs.iterator(); it.hasNext();) {
				TawSystemReportedUser reportedUser = (TawSystemReportedUser)it.next();
				reportedUser.setReportedId(tawSystemReported.getId());
				reportDao.saveTawSystemReportedUser(reportedUser);
			}
		}
	}

	public List getTawSystemReportedUsersByReportedId(String reportId) {
		return reportDao.getTawSystemReportedUsersByReportId(reportId);
	}
}
