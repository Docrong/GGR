/*
 * Created on 2008-5-12
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.sheet.expert.dao.hibernate;


import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.sheet.expert.dao.TawSheetExpertDao;
import com.boco.eoms.commons.sheet.expert.model.TawSheetExpert;
import com.boco.eoms.commons.sheet.special.model.TawSheetSpecial;
/**
 * @author admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TawSheetExpertDaoHibernate extends BaseDaoHibernate implements TawSheetExpertDao {
	
	 public List getTawSheetExperts(TawSheetExpert tawSheetExpert){
	 	List list = null;
		return list;
	 }
	 
	 public TawSheetExpert getTawSheetExpert(final Integer id){
	 	
	 	TawSheetExpert tawSheetExpert = ( TawSheetExpert ) getHibernateTemplate().get(TawSheetExpert.class, id);
	 	if( tawSheetExpert == null){
	 		 throw new ObjectRetrievalFailureException(TawSheetExpert.class, id);
	 	}
	 	return tawSheetExpert;
	 
	 }
	 
	 public void saveTawSheetExpert( final TawSheetExpert tawSheetExpert){
	 	if((tawSheetExpert.getId() == null) || tawSheetExpert.getId().equals("")){
	 		
	 	     getHibernateTemplate().save(tawSheetExpert);
	 	}else{
	 		
	 	     getHibernateTemplate().saveOrUpdate(tawSheetExpert);
	 	}
	 	
	 
	 }
	 
	 public void removeTawSheetExpert(final Integer id){
	 	
	 	getHibernateTemplate().delete(getTawSheetExpert(id));
	 	
	 }
	 
	 public TawSheetExpert getExpertBySpecialId(String specialid){
	 	String hql = " from TawSheetExpert tse where tse.speId='"+specialid+"' and tse.expertState = '2' order by tse.acceptCount asc";
	 	return (TawSheetExpert)getHibernateTemplate().find(hql).get(0);
	 
	 }
	 
	 /**
	     * 根据专业ID查询专家信息
	     * @param areaid
	     * @return
	     */
	    public List getExpertsBySpecialId(String specialid){
	    	
	    	String hql = " from TawSheetExpert spe where spe.speId ='"+specialid+"'";
	    	return (ArrayList)getHibernateTemplate().find(hql);
	    }
	    /**
	     * 根据专业userId更新
	     * @param areaid
	     * @return
	     */
		 public void updtaeExpertByExpertName(String expertName,String oldExpertName){
			
		 	String hql = " from TawSheetExpert tse where tse.expertName='"+oldExpertName+"'";
		 	List list = (ArrayList)getHibernateTemplate().find(hql);
			
		 	if(list.size()>0){
		 	    for( int i = 0;i < list.size();i++){
		 	    	TawSheetExpert expert = (TawSheetExpert)list.get(i);
		 	    	expert.setExpertName(expertName);
		 	    	getHibernateTemplate().saveOrUpdate(expert);
		 	    
		 	    }	
		 	
		 	}
		
		 
		 }
		 /**
		     * 根据专业userId删除
		     * @param areaid
		     * @return
		     */
		 public void deleteTawSheetExpert(String expertName){
		  	String hql = " from TawSheetExpert tse where tse.expertName='"+expertName+"'";
		 	TawSheetExpert expert = (TawSheetExpert)getHibernateTemplate().find(hql).get(0);
		 	removeTawSheetExpert(expert.getId());
		 }
		 /**
		     * 根据专业userId更新
		     * @param areaid
		     * @return
		     */
		 public void updateTawSheetExpert(String expertName){
		  	String hql = " from TawSheetExpert tse where tse.expertName='"+expertName+"'";
		  	List list = (ArrayList)getHibernateTemplate().find(hql);
		  	if(list.size() > 0){
		  		TawSheetExpert expert = (TawSheetExpert)list.get(0);
			 	expert.setAcceptCount(expert.getAcceptCount()+1);
			 	getHibernateTemplate().saveOrUpdate(expert);
		  	}
		 	
		 	
		 }
}

