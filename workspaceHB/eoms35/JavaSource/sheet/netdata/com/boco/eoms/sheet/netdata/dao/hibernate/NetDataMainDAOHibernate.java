
package com.boco.eoms.sheet.netdata.dao.hibernate;

import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.base.model.BaseMain;

import com.boco.eoms.sheet.netdata.dao.INetDataMainDAO;
import com.boco.eoms.sheet.netdata.model.NetDataMain;

public class NetDataMainDAOHibernate extends MainDAO implements INetDataMainDAO {



	/**
	 * @see com.boco.eoms.base.dao.Dao#saveObject(java.lang.Object)
	 */
	public void save(Object o) {
		getHibernateTemplate().save(o);
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
	 */
//	public String getMainName() {
//		// TODO Auto-generated method stub
//		return "NetDataMain";
//	}
	
	public void DeleteEarlyEmptyMain(Object mainObject){
		long DAY = 24L * 60L * 60L * 1000L;
		try{
			String sql = "from "+ mainObject.getClass().getName() +" as main where main.title is null";
			List sheetList = getHibernateTemplate().find(sql);
			if(sheetList!=null){
				int i = sheetList.size();
				while(i>0){
					i--;
					BaseMain main = (BaseMain)sheetList.get(i);
					System.out.println("new Date: "+(new Date()).getTime()/DAY);
					System.out.println("main Date:"+main.getSendTime().getTime()/DAY);
					if(((new Date()).getTime()/DAY - main.getSendTime().getTime()/DAY)>0){
						System.out.println("delete early main");
						getHibernateTemplate().delete(main);
						getHibernateTemplate().flush();
						getHibernateTemplate().clear();
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
