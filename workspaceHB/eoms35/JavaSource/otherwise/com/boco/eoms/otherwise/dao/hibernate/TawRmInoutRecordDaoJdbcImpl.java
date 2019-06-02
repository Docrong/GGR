package com.boco.eoms.otherwise.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.dao.jdbc.BaseDaoJdbc;
import com.boco.eoms.otherwise.dao.ITawRmInoutRecordDaoJdbc;

public class TawRmInoutRecordDaoJdbcImpl extends BaseDaoJdbc implements
		ITawRmInoutRecordDaoJdbc {

	public List getInRecordList(String ids) {
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT  b.id,a.iccid,a.msisdn,a.imsi,a.pin,a.puk,a.open_account_date,a.logout_date,");
		sql.append("a.take_over_date,a.state,a.old_number,b.borrow_date,b.intending_return_date,b.user_id,b.borrower_id ");
		sql.append("from taw_rm_testcard a, taw_rm_inoutrecord b ");
		sql.append("WHERE  a.id=b.testcard_id and b.out_type<>'' and b.in_type='' ");
		sql.append("and a.id in(");
		sql.append(ids);
		sql.append(")");
		System.out.println("in finish sql====="+sql);
		List list =null;
		try{
		list = getJdbcTemplate().queryForList(sql.toString());		
		}catch(Exception ex){
			return new ArrayList();
		}
		return list;
	}
	
	public List getIdList(String whereStr) {
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT distinct a.id ");
		sql.append("from taw_rm_testcard a, taw_rm_inoutrecord b where a.id=b.testcard_id ");
		sql.append(whereStr);
		System.out.println("sql====="+sql);
		List list =null;
		try{
		list = getJdbcTemplate().queryForList(sql.toString());		
		}catch(Exception ex){
			return new ArrayList();
		}
		return list;
	}

}
