/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.complaint.dao.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetStaticMethod;
import com.boco.eoms.sheet.complaint.dao.IComplaintMainDAO;
import com.boco.eoms.sheet.complaint.model.ComplaintMain;

/**
 * @author
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ComplaintMainDaoHibernate extends MainDAO implements
        IComplaintMainDAO {


	
	/**
	 * 通过告警号获取工单
	 * @param alarmId 告警号
	 * @return
	 * @throws HibernateException
	 */
	public BaseMain getMainByAlarmId(String alarmId, Object mainObject)
	throws HibernateException {
		BaseMain main = new BaseMain();
		String sql = "from "+ mainObject.getClass().getName() +" as main where main.mainAlarmNum ='"
				+ alarmId + "'";
		List list = getHibernateTemplate().find(sql);
		if (list.size() == 0) {
			main = null;
		} else {
			main = (BaseMain) list.get(0);
		}
		return main;
	} 
	public List getMainByLink(Map map, Object mainObject, Object linkObject){
		if(map==null)
			return null;

		String hql = "select distinct main from "+ mainObject.getClass().getName() 
		+" main, "+ linkObject.getClass().getName() +" link where main.id=link.mainId ";
		Set set = map.keySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			String key = StaticMethod.nullObject2String(iterator.next());
			String value = StaticMethod.nullObject2String(map.get(key));	
			if(value.length()>0){
				hql += " and ";
				hql += " link."+key+" like '%"+value+"%'";
			}
		}
		
		List list = getHibernateTemplate().find(hql);
		return list;
	}
	public List getMainList(Map map, Object mainObject){
		if(map==null)
			return null;
		boolean isfirst = true;

		String hql = "select distinct main from "+ mainObject.getClass().getName() +" main ";
		Set set = map.keySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			String key = StaticMethod.nullObject2String(iterator.next());
			String value = StaticMethod.nullObject2String(map.get(key));	
			if(value.length()>0){
				if(isfirst){
					hql += " where ";
					isfirst = false;
				}else{
					hql += " and ";									
				}
				hql += " main."+key+" like '%"+value+"%'";	
			}
		}
		if(isfirst)
			return null;
		
		List list = getHibernateTemplate().find(hql);
		return list;
	}

	
	/**
	 * 待质检（后质检）的已归档工单数
	 * @return
	 * @throws HibernateException
	 */	
	public Integer getCountUndoForCheck(final Object mainObject) throws HibernateException {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				//hql，以备资源名称＋系统角色＋资源类别＋本机管理IP地址为条件查询
				String queryStr = "from " + mainObject.getClass().getName() + " alias where "
						+ "alias.status=:status ";
				//取列表数量
				String queryCountStr = "select count(alias.id) from " + mainObject.getClass().getName()
						+ " alias where alias.templateFlag <> 1 and alias.mainIfCheck <> '1' and alias.mainIfCheck <> '2' and (alias.status=:status or alias.status=:status1)";
				Query query = session.createQuery(queryCountStr);
				//TODO 归档标记，需确认
				query.setInteger("status", Constants.SHEET_HOLD.intValue());
				query.setInteger("status1", Constants.ACTION_FORCE_HOLD);
				Integer total = (Integer) query.iterate().next();
				return total;
			}
		};
		return (Integer) getHibernateTemplate().execute(callback);
	}	

	/**
	 * 待质检（后质检）的已归档工单列表
	 * @return
	 * @throws HibernateException
	 */	
	public List getListUndoForCheck(final Integer curPage, final Integer pageSize, final Object mainObject)throws HibernateException {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				//hql，以备资源名称＋系统角色＋资源类别＋本机管理IP地址为条件查询
				String queryStr = "from " + mainObject.getClass().getName() + " alias where "
						+ "alias.templateFlag <> 1 and alias.mainIfCheck <> '1' and alias.mainIfCheck <> '2' and (alias.status=:status or alias.status=:status1) order by alias.sendTime desc";
		
				Query query = session.createQuery(queryStr);
				//设置归档标记
				query.setInteger("status", Constants.SHEET_HOLD.intValue());
				query.setInteger("status1", Constants.ACTION_FORCE_HOLD);
				
				//分页查询条
				query.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
		
				query.setMaxResults(pageSize.intValue());
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}	

	/**
	 * 已质检（后质检）的已归档工单数
	 * mainIfCheck = '2'
	 * @return
	 * @throws HibernateException
	 */	
	public Integer getCountDoneForCheck(final Object mainObject) throws HibernateException {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				//hql，以备资源名称＋系统角色＋资源类别＋本机管理IP地址为条件查询
				String queryStr = "from " + mainObject.getClass().getName() + " alias where "
						+ "alias.status=:status ";
				//取列表数量
				String queryCountStr = "select count(alias.id) from " + mainObject.getClass().getName()
						+ " alias where alias.templateFlag <> 1 and alias.mainIfCheck = '2' and (alias.status=:status or alias.status=:status1)";
				Query query = session.createQuery(queryCountStr);
				//TODO 归档标记，需确认
				query.setInteger("status", Constants.SHEET_HOLD.intValue());
				query.setInteger("status1", Constants.ACTION_FORCE_HOLD);
				Integer total = (Integer) query.iterate().next();
				return total;
			}
		};
		return (Integer) getHibernateTemplate().execute(callback);
	}	

	/**
	 * 已质检（后质检）的已归档工单列表
	 * mainIfCheck = '2'
	 * @return
	 * @throws HibernateException
	 */	
	public List getListDoneForCheck(final Integer curPage, final Integer pageSize, final Object mainObject)throws HibernateException {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				//hql，以备资源名称＋系统角色＋资源类别＋本机管理IP地址为条件查询
				String queryStr = "from " + mainObject.getClass().getName() + " alias where "
						+ "alias.templateFlag <> 1 and alias.mainIfCheck = '2' and (alias.status=:status or alias.status=:status1) order by alias.sendTime desc";
		
				Query query = session.createQuery(queryStr);
				//设置归档标记
				query.setInteger("status", Constants.SHEET_HOLD.intValue());
				query.setInteger("status1", Constants.ACTION_FORCE_HOLD);
				
				//分页查询条
				query.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
		
				query.setMaxResults(pageSize.intValue());
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}	
	
	/**
     * 获取某段时间内归档的且可以上传的工单
     */
    public List getHoldedSheetListByTime(Object object,String startTime,String endTime){
		String sql = "from " + object.getClass().getName() + " main where main.endTime between "
				+ SheetStaticMethod.getDBDateToString(startTime) + " and " + SheetStaticMethod.getDBDateToString(endTime) + " and main.status='1' and main.reportFlag=1";
		return getHibernateTemplate().find(sql);
    }
    

	public int getCustomPhoneBySendTime(String beforedate, String afterdate,String customPhone) {
		 int repeatCount=0;
		 String sql="select count(s.id) as count from ComplaintMain s where s.customPhone='"+customPhone+"' and s.complaintTime >= to_date('"+beforedate+"','yyyy-MM-dd HH24:mi:ss') and  s.complaintTime <= to_date('"+afterdate+"','yyyy-MM-dd HH24:mi:ss')";
		 List resultList=getHibernateTemplate().find(sql);
		 if(null!=resultList&&resultList.size()>0){
			 repeatCount=((Integer) resultList.get(0)).intValue();
		 }
		 return repeatCount;
			
	}
	
	public int getCustomPhoneBySendTime1(String beforedate, String afterdate,String customPhone) {
		 int repeatCount=0;
		 String sql="select count(s.id) as count from ComplaintMain s where s.customPhone='"+customPhone+"' and s.complaintTime >= to_date('"+beforedate+"','yyyy-MM-dd HH24:mi:ss') and  s.complaintTime <= to_date('"+afterdate+"','yyyy-MM-dd HH24:mi:ss') and s.complaintType2='10106250101'";
		 List resultList=getHibernateTemplate().find(sql);
		 if(null!=resultList&&resultList.size()>0){
			 repeatCount=((Integer) resultList.get(0)).intValue();
		 }
		 return repeatCount;
			
	}
	public List ifneedAutotran(final String complaintType1, final String complaintType2, final String complaintType, final String complaintType4, final String complaintType5, final String complaintType6, final String complaintType7)
	throws HibernateException
{
	HibernateCallback callback = new HibernateCallback() {

		public Object doInHibernate(Session session)
			throws HibernateException
		{
			String queryStr = "select a.* from complaint_autocheck a where  complainttype7 = '" + complaintType7 + "' or (complainttype6 = '" + complaintType6 + "' and complainttype7 is null) or (complainttype5 = '" + complaintType5 + "' and complainttype6 is null) or (complainttype4 = '" + complaintType4 + "' and complainttype5 is null) or (complainttype3 = '" + complaintType + "' and complainttype4 is null) or (complainttype2 = '" + complaintType2 + "' and complainttype3 is null) or (complainttype1 = '" + complaintType1 + "' and complainttype2 is null) and (colseswitch ='N' or colseswitch ='n')";
			Query query = session.createSQLQuery(queryStr);
			ArrayList queryList = new ArrayList();
			queryList = (ArrayList)query.list();
			return queryList;
		}

	};
	return (List)getHibernateTemplate().execute(callback);
}

}
