/*
 * Created on 2006-7-22
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sparepart.util;

import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.db.util.ConnectionPool;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EarlyWarningSQL {
	
	public EarlyWarningSQL(){
		
    }
	
	private String time1;
	
	private String time2;
	
	private String time3;
	
	public static final int TYPE = 406176;
	
	public static final int COMPARE = 406115;
	
	public static final int OUT = 1;
	
	public static final int IN = 2;
	
	public static final int BACK = 3;
	
	public static final int STOCK = 4;
	
	public static final int UPDATE = 5;
	
	public static final int SUB = 6;
	
	public String getEarlyWarningSQL() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select name,sum(item1) item1,sum(item2) item2,sum(item3) item3,sum(item4) item4 from ( ");
		sql.append(" select taw_dept.deptname name,count(*) item1,0 item2,0 item3,0 item4 ");
		sql.append(" from taw_sp_sparepart  taw_sp_sparepart,taw_sp_storage taw_sp_storage,taw_system_dept taw_dept ");
		sql.append(" where  taw_sp_sparepart.storageid = taw_sp_storage.id ");
		sql.append(" and taw_sp_storage.dept_id = taw_dept.deptid ");
		sql.append(" and taw_sp_sparepart.state = 12 ");
		sql.append(" and taw_sp_sparepart.updatetime >= "+this.time1+" ");
		sql.append(" group by taw_dept.deptname ");
		sql.append(" union ");
		sql.append(" select taw_dept.deptname,count(*) item1,0 item2,0 item3,0 item4 ");
		sql.append(" from taw_sp_sparepart taw_sp_sparepart,taw_sp_storage taw_sp_storage,taw_system_dept taw_dept ");
		sql.append(" where  taw_sp_sparepart.storageid = taw_sp_storage.id ");
		sql.append(" and taw_sp_storage.dept_id = taw_dept.deptid ");
		sql.append(" and taw_sp_sparepart.state = 13 ");
		sql.append(" and taw_sp_sparepart.updatetime >= "+this.time2+" ");
		sql.append(" group by taw_dept.deptname ");
		sql.append(" union ");
		sql.append(" select d.deptname name,0 item1,0 item2,count(*) item3,0 item4  from ( ");
		sql.append(" select taw_sp_sparepart.storageid id,count(*) num ");
		sql.append(" from taw_sp_sparepart taw_sp_sparepart ");
		sql.append(" where taw_sp_sparepart.state = 11 ");
		sql.append(" group by taw_sp_sparepart.storageid ");
		sql.append(" ) a,taw_sp_remind b,taw_sp_storage c,taw_system_dept d ");
		sql.append(" where a.id = c.id ");
		sql.append(" and c.id = b.storageid ");
		sql.append(" and c.dept_id = d.deptid ");
		sql.append(" and (a.num < b.lowerlimit or a.num > b.upperlimit) ");
		sql.append(" group by d.deptname ");
		sql.append(" union ");
		sql.append(" select d.dept_name,0 item1,0 item2,0 item3,count(*) item4 from ( ");
		sql.append(" select storageid,count(*) num1 ");
		sql.append(" from taw_sp_sparepart ");
		sql.append(" where state= 13 ");
		sql.append(" group by storageid ");
		sql.append(" ) a,( ");
		sql.append(" select storageid,count(*) num2 ");
		sql.append(" from taw_sp_sparepart  ");
		sql.append(" group by storageid ");
		sql.append(" ) b,taw_sp_storage c,taw_system_dept d ");
		sql.append(" where a.storageid = c.id ");
		sql.append(" and b.storageid = c.id ");
		sql.append(" and c.dept_id = d.deptid ");
		sql.append(" and a.num1*0.5 < b.num2 ");
		sql.append(" group by d.deptname ");
		sql.append(" ) group by name ");
		
		return sql.toString();
	}
	
	public String getBack(String name) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select taw_dept.dept_name name,count(*) num ");
		sql.append(" from taw_sp_sparepart  taw_sp_sparepart,taw_sp_storage taw_sp_storage,taw_dept taw_dept,taw_sp_classmsg taw_sp_classmsg ");
		sql.append(" where  taw_sp_sparepart.storageid = taw_sp_storage.id ");
		sql.append(" and taw_sp_storage.dept_id = taw_dept.dept_id ");
		sql.append(" and taw_sp_sparepart.supplier = taw_sp_classmsg.id ");
		sql.append(" and taw_sp_sparepart.state = 14 ");
		sql.append(" and taw_sp_classmsg.cname = '"+name+"' ");
		sql.append(" and taw_sp_sparepart.updatetime < "+this.time3+" ");
		sql.append(" group by taw_dept.dept_name ");
		
		return sql.toString();
	}
	public void setTime1(String time1) {
		this.time1 = time1;
	}

	public void setTime2(String time2) {
		this.time2 = time2;
	}

	public void setTime3(String time3) {
		this.time3 = time3;
	}
	
	public String getOutAndIn(int status,String deptName,String time) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select taw_sp_sparepart.* ");
		sql.append(" from taw_sp_sparepart taw_sp_sparepart,taw_sp_storage taw_sp_storage,taw_dept taw_dept ");
		sql.append(" where  taw_sp_sparepart.storageid = taw_sp_storage.id ");
		sql.append(" and taw_sp_storage.dept_id = taw_dept.dept_id ");
		sql.append(" and taw_sp_sparepart.state = '"+status+"' ");
		sql.append(" and taw_dept.dept_name = '"+deptName+"' ");
		sql.append(" and taw_sp_sparepart.updatetime < "+time+" ");
		
		return sql.toString();
	}
	
	public String getStock(int status,String deptName) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct c.*,d.dept_name from ( ");
		sql.append(" select taw_sp_sparepart.storageid id,count(*) num ");
		sql.append(" from taw_sp_sparepart taw_sp_sparepart ");
		sql.append(" where taw_sp_sparepart.state = '"+status+"' ");
		sql.append(" group by taw_sp_sparepart.storageid ");
		sql.append(" ) a,taw_sp_remind b,taw_sp_storage c,taw_dept d ");
		sql.append(" where a.id = c.id ");
		sql.append(" and c.id = b.storageid ");
		sql.append(" and c.dept_id = d.dept_id ");
		sql.append(" and d.dept_name = '"+deptName+"' ");
		sql.append(" and (a.num < b.lowerlimit or a.num > b.upperlimit) ");		
		
		return sql.toString();
	}
	
	public String getUpdate(String ratio,String deptName) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct c.*,d.dept_name from ( ");
		sql.append(" select storageid,count(*) num1 ");
		sql.append(" from taw_sp_sparepart  ");
		sql.append(" where state= 13 ");
		sql.append(" group by storageid ");
		sql.append(" ) a,( ");
		sql.append(" select storageid,count(*) num2 ");
		sql.append(" from taw_sp_sparepart  ");
		sql.append(" group by storageid ");
		sql.append(" ) b, ");
		sql.append(" taw_sp_storage c,taw_dept d,taw_sp_sparepart e ");
		sql.append(" where a.storageid = c.id ");
		sql.append(" and b.storageid = c.id ");
		sql.append(" and e.storageid = c.id ");
		sql.append(" and c.dept_id = d.dept_id ");
		sql.append(" and d.dept_name = '"+deptName+"' ");
		sql.append(" and a.num1*'"+ratio+"' < b.num2 ");
		
		return sql.toString();
	}
	
	public String getBack1(String name,int status,String time,String deptName) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select taw_sp_sparepart.* ");
		sql.append(" from taw_sp_sparepart  taw_sp_sparepart,taw_sp_storage taw_sp_storage,taw_dept taw_dept,taw_sp_classmsg taw_sp_classmsg ");
		sql.append(" where  taw_sp_sparepart.storageid = taw_sp_storage.id ");
		sql.append(" and taw_sp_storage.dept_id = taw_dept.dept_id ");
		sql.append(" and taw_sp_sparepart.supplier = taw_sp_classmsg.id ");
		sql.append(" and taw_sp_sparepart.state = '"+status+"' ");
		sql.append(" and taw_sp_classmsg.cname = '"+name+"' ");
		sql.append(" and taw_dept.dept_name = '"+deptName+"' ");
		sql.append(" and taw_sp_sparepart.updatetime < "+time1+" ");
		
		return sql.toString();
	}
}
