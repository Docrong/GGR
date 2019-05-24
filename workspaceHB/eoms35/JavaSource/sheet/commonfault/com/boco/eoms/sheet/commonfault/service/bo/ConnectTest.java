package com.boco.eoms.sheet.commonfault.service.bo;


import java.util.List;



import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;

public class ConnectTest extends BaseDaoHibernate {


	
	public void connectInfo() {
	    try {
	    	String sql = "select sysdate from dual";
	        String timeDate="";     	
    		List list1 = getHibernateTemplate().find(sql);
    		if (list1.size()>0){
    		timeDate = list1.get(0).toString();
    		System.out.println("----connectTime----"+timeDate);
    		}
    		

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


	
}
