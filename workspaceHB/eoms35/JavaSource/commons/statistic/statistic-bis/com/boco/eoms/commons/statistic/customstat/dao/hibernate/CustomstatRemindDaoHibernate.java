package com.boco.eoms.commons.statistic.customstat.dao.hibernate;

import java.util.List;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.statistic.customstat.dao.ICustomstatRemindDao;
import com.boco.eoms.commons.statistic.customstat.model.CustomstatRemind;

public class CustomstatRemindDaoHibernate extends BaseDaoHibernate implements ICustomstatRemindDao{

	public void deleteCustomstatRemind(CustomstatRemind _customstatremind) {
		
		this.removeObject(CustomstatRemind.class, _customstatremind.getId());
	}

	public CustomstatRemind getCustomstatRemind(CustomstatRemind _customstatremind) {
 		CustomstatRemind returnCustomstatRemind = null;
		String hql = "from CustomstatRemind customstatRemind";
		if(_customstatremind!=null){
			hql = hql + " where 1=1 ";
		    if(_customstatremind.getSubId()!=null&&!_customstatremind.getSubId().equals("")){
		 	    hql = hql +  "and customstatRemind.subId='"+_customstatremind.getSubId()+"' ";
		    }
		    if(_customstatremind.getStatType()!=null&&!_customstatremind.getStatType().equals("")){
		    	hql = hql +  "and customstatRemind.statType='"+_customstatremind.getStatType()+"' ";
		    	if(_customstatremind.getStatType().equals("1")){
		    		hql = hql + "and customstatRemind.year='"+_customstatremind.getYear()+"' ";
		    	}else if(_customstatremind.getStatType().equals("2")){
		    		hql = hql + "and customstatRemind.year='"+_customstatremind.getYear()+"' ";
		    		hql = hql + "and customstatRemind.quarter='"+_customstatremind.getQuarter()+"' ";
		    	}else if(_customstatremind.getStatType().equals("3")){
		    		hql = hql + "and customstatRemind.year='"+_customstatremind.getYear()+"' ";
		    		hql = hql + "and customstatRemind.month='"+_customstatremind.getMonth()+"' ";
		    	}else if(_customstatremind.getStatType().equals("4")){
		    		hql = hql + "and customstatRemind.date='"+_customstatremind.getDate()+"' ";
		    	}else if(_customstatremind.getStatType().equals("5")){
		    		hql = hql + "and customstatRemind.year='"+_customstatremind.getYear()+"' ";
		    		hql = hql + "and customstatRemind.week='"+_customstatremind.getWeek()+"' ";
		    	}	    	
		    }
		}
		List resultList = this.getHibernateTemplate().find(hql);
		if(resultList!=null&&resultList.size()>0){
			returnCustomstatRemind = (CustomstatRemind)resultList.get(0);
		}
		return returnCustomstatRemind;
	}

	public void saveCustomstatRemind(CustomstatRemind _customstatremind) {
//		this.getHibernateTemplate().save(_customstatremind);	
		this.saveObject(_customstatremind);
		
	}
	
	public void flush()
	{
		this.getSession().flush();
	}

}
