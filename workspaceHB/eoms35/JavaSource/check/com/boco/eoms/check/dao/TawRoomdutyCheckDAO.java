package com.boco.eoms.check.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.print.attribute.standard.Finishings;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.boco.eoms.check.model.TawRoomdutyCheck;
import com.boco.eoms.check.model.TawRoomdutycAddonstable;
import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.db.hibernate.HibernateUtil;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.workplan.model.TawwpAddonsTable;

public class TawRoomdutyCheckDAO extends DAO {

	public TawRoomdutyCheckDAO(ConnectionPool ds) {
		super(ds);
		// TODO Auto-generated constructor stub
	}

	public TawRoomdutyCheckDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/*
	 *   ֵ��Ѳ��ʹ��
	 *   added by ly. 
	 * */
	public TawRoomdutyCheck getTawRoomdutyCheck (String roomId, String state) {
		Session s = HibernateUtil.currentSession();
		String hSql = "";
		List l = null;
		TawRoomdutyCheck tawRoomdutyCheck = null;
		hSql = "from TawRoomdutyCheck as tawRoomdutyCheck where tawRoomdutyCheck.roomid='"
				+ roomId + "' and tawRoomdutyCheck.state='" + state + "'";
		Query query = s.createQuery(hSql);
		query.setCacheable(true);
		l = query.list();
		if (l.size() > 0) {
			tawRoomdutyCheck = (TawRoomdutyCheck) l.get(0);
			Hibernate.initialize(tawRoomdutyCheck);		
			return tawRoomdutyCheck;
		} else {
			return null;
		}
		
	}

	
	
	
/*	
	*//**   �����¼����
	 * @param args
	 *//*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("begin----------------------------------------------------------");
		Session s = HibernateUtil.currentSession();
		Transaction tr = s.beginTransaction();
		TawRoomdutyCheck tawRoomdutyCheck = new TawRoomdutyCheck("1", "1", "1",
				"1", "1");
		TawwpAddonsTable tawwpAddonsTable1 = new TawwpAddonsTable("test1",
				"test1", "test1", "oo", "test1", "test1");
		TawwpAddonsTable tawwpAddonsTable2 = new TawwpAddonsTable("test2",
				"test2", "test2", "oo", "test2", "test2");
		TawRoomdutyCheck tawRoomdutyCheck = new TawRoomdutyCheck("2", "2", "2",
				"2", "2");
		TawwpAddonsTable tawwpAddonsTable1 = new TawwpAddonsTable("test3",
				"test3", "test3", "oo", "test3", "test3");
		TawwpAddonsTable tawwpAddonsTable2 = new TawwpAddonsTable("test2",
				"test3", "test3", "oo", "test3", "test3");
		
		try {
//			tawRoomdutyCheck.getAddonstable().add(tawwpAddonsTable1);
//			tawRoomdutyCheck.getAddonstable().add(tawwpAddonsTable2);
//			s.save(tawRoomdutyCheck);
			
			TawRoomdutyCheck tawRoomdutyCheck = (TawRoomdutyCheck )s.load(TawRoomdutyCheck.class, "8a41a0751660f9cb011660f9ef0f0001");
			Hibernate.initialize(tawRoomdutyCheck);	
			System.out.println(tawRoomdutyCheck.getCheckdate() + "--" + tawRoomdutyCheck.getChecktype() + "--" + ((TawwpAddonsTable) tawRoomdutyCheck.getAddonstable().iterator().next()).getModel());
			
			s.delete(tawRoomdutyCheck);
			
			tr.commit();			
		} 
		catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}
		s.close();	
		
		System.out.println("finish!----------------------------------------------------------");
		
	}*/

	public void insertTawRoomdutyCheck(String roomId, String addonsTableIDS) {
		// TODO Auto-generated method stub
		try {
		Session s = HibernateUtil.currentSession();
		TawRoomdutyCheck tawRoomDutyCheck = null;
		TawwpAddonsTable tawwpAddonsTable = null; 
		
		String[] addonsTableId = null;
		tawRoomDutyCheck = new TawRoomdutyCheck(roomId, "1", "2", "", "", "1");
		
		if (addonsTableIDS.endsWith(","))
			addonsTableIDS = addonsTableIDS.substring(0, addonsTableIDS.lastIndexOf(","));
		addonsTableId = addonsTableIDS.split(",");
		for (int i = 0; i < addonsTableId.length; i++) {
			tawwpAddonsTable = (TawwpAddonsTable) s.load(TawwpAddonsTable.class, addonsTableId[i]);
			tawRoomDutyCheck.getAddonstable().add(tawwpAddonsTable);
		}
		s.save(tawRoomDutyCheck);		
		s.beginTransaction().commit();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void modifyTawRoomdutyCheck(String tawRoomDutyCheckId, String addonsTableIDS) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.currentSession();
		TawRoomdutyCheck tawRoomDutyCheck = null;
		TawwpAddonsTable tawwpAddonsTable = null; 
		
		String[] addonsTableId = null;
		tawRoomDutyCheck = (TawRoomdutyCheck) s.load(TawRoomdutyCheck.class, tawRoomDutyCheckId);
		tawRoomDutyCheck.getAddonstable().clear();
		if (addonsTableIDS.endsWith(","))
			addonsTableIDS = addonsTableIDS.substring(0, addonsTableIDS.lastIndexOf(","));
		addonsTableId = addonsTableIDS.split(",");
		for (int i = 0; i < addonsTableId.length; i++) {
			tawwpAddonsTable = (TawwpAddonsTable) s.load(TawwpAddonsTable.class, addonsTableId[i]);
			tawRoomDutyCheck.getAddonstable().add(tawwpAddonsTable);
		}
		s.update(tawRoomDutyCheck);
		s.beginTransaction().commit();
		
		
		
	}

	/*
	 * 	��ѯ��ϵ��
	 * */	
/*	public static void main(String[] args) {
		
		System.out.println("begin----------------------------------------------------------");
		Session s = HibernateUtil.currentSession();
		Transaction tr = s.beginTransaction();
		TawRoomdutycAddonstable rel = null;
		//String hql = "from TawRoomdutycAddonstable as t where t.tawRoomdutyCheck='8a41a067167b011d01167b3e1dce0001' and t.tawWpAddonstable='8a81a2f9116d119c01116d2320560001'";
		String hql = "from TawRoomdutycAddonstable as t where t.tawRoomdutyCheck='8a41a067167b011d01167b3e1dce0001'";
		Query q = s.createQuery(hql);
		List l = q.list();
		for (int i = 0; i < l.size(); i++) {
			rel = (TawRoomdutycAddonstable) l.get(i);
			System.out.println(rel.getDataurl() + "---------------");
		}
		System.out.println("finish!");
		
	}*/

	public List getTableMapByRoomdutycheckid(String tawRoomdutyCheckId) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.currentSession();
		String hql = "from TawRoomdutycAddonstable as t where t.tawRoomdutyCheck='" + tawRoomdutyCheckId + "'";
		Query q = s.createQuery(hql);		
		return q.list();
	}
	
/*
	
	 * ɾ���j��
	 * 
	public static void main(String[] args) {
		Session s = HibernateUtil.currentSession();
		Transaction tr = s.beginTransaction();
		TawRoomdutycAddonstable rel = null;
		//String hql = "from TawRoomdutycAddonstable as t where t.tawRoomdutyCheck='8a41a067167b011d01167b3e1dce0001' and t.tawWpAddonstable='8a81a2f9116d119c01116d2320560001'";
		String hql = "from TawRoomdutycAddonstable";
		Query q = s.createQuery(hql);
		List l = q.list();
		for (int i = 0; i < l.size(); i++) {
			rel = (TawRoomdutycAddonstable) l.get(i);
			s.delete(rel);			
		}
		
		tr.commit();
		s.close();
		System.out.println("finish!");
	}
*/	
	
	
	/*
	 * 	��������ѯ��j��
	 * */
	public static void main(String[] args) {
		Session s = HibernateUtil.currentSession();
		String hql = "from TawRoomdutycAddonstable as t where t.tawRoomdutyCheck.roomid='410' and t.tawWpAddonstable.name like '%����%'";
		Query q = s.createQuery(hql);
		TawRoomdutycAddonstable rel = null;
		List l = q.list();
		for (int i = 0; i < l.size(); i++) {
			rel = (TawRoomdutycAddonstable) l.get(i);
			System.out.println(rel.getTawRoomdutyCheck().getId() + "-------------" + rel.getTawRmAddonsTable().getId() + "---==" + rel.getDataurl() ); 		
		}		
	}

	public List getRelForm(String hql) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.currentSession();
		Query q = s.createQuery(hql);
		List l = q.list();		
		return l;
	}

	public void updateTawRoomDutyCheck(String tawRoomDutyCheckId) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.currentSession();
		Transaction tr = s.beginTransaction();
		TawRoomdutyCheck tawRoomdutyCheck = null;
		tawRoomdutyCheck = (TawRoomdutyCheck) s.load(TawRoomdutyCheck.class, tawRoomDutyCheckId);
		tawRoomdutyCheck.setState("2");	//�޸�stateΪ2�� ��ʾ�ѹ鵵
		s.save(tawRoomdutyCheck);	
		tr.commit();
	}
	
	public String getTableMapByRoomdutyDataurl(String dataUrl) {
		// TODO Auto-generated method stub
		String checkType="";
		Session s = HibernateUtil.currentSession();
		TawRoomdutycAddonstable tawRoomdutycAddonstable=null;
		String hql = "from TawRoomdutycAddonstable as t where t.dataurl='" + dataUrl + "'";
		Query q = s.createQuery(hql);
		
		List list= q.list();
		if(list.size()>0){
			tawRoomdutycAddonstable=(TawRoomdutycAddonstable)list.get(0);
			checkType=tawRoomdutycAddonstable.getTawRoomdutyCheck().getChecktype();
		}
		return checkType;
	}
	
	
	
}
 