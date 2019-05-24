package com.boco.eoms.workplan.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.common.dao.HibernateCommitDAO;
import com.boco.eoms.workplan.dao.ITawwpLogShowDao;
import com.boco.eoms.workplan.model.TawwpNewLog;

public class TawwpLogShowDaoHibernate extends BaseDaoHibernate implements ITawwpLogShowDao{

	public TawwpNewLog getOne(String id) {

		TawwpNewLog tawwpNewLog = new TawwpNewLog();

		HibernateCommitDAO hibernateCommitDAO = new HibernateCommitDAO();

		tawwpNewLog = (TawwpNewLog) hibernateCommitDAO.load(id, tawwpNewLog
				.getClass());

		return tawwpNewLog;
	}

	public List listTable() {

		String hSql = " from TawwpNewLog as tawwpNewLog ";
		List result = getHibernateTemplate().find(hSql);
		return result;

	}

	public List listTable(String sheetId, String timeStart, String timeEnd) {
//		Date start = null;
//		Date end = null;
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			start = sdf.parse(timeStart);
//			end = sdf.parse(timeEnd);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		timeStart = timeStart.toString();
		timeEnd = timeEnd.toString();
		String hSql = "";
		hSql = " from TawwpNewLog as tawwpNewLog where tawwpNewLog.createTime>to_date('"+ timeStart +"','%Y-%m-%d %H:%M:%S') and tawwpNewLog.createTime<to_date('" + timeEnd +"','%Y-%m-%d %H:%M:%S')";
		if(sheetId!=null&&!sheetId.equals("")){
			hSql += " and logType like '%"+ sheetId + "%'";
		}
 		List result = getHibernateTemplate().find(hSql);
		return result;
	}

}
