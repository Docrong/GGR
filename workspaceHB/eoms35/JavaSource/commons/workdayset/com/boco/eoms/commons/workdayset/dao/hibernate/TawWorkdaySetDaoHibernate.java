
package com.boco.eoms.commons.workdayset.dao.hibernate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.text.ParseException;
//import java.util.Calendar;
//import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.workdayset.dao.ITawWorkdaySetDao;
import com.boco.eoms.commons.workdayset.model.TawWorkdaySet;
import com.boco.eoms.commons.workdayset.service.ITawWorkdaySetManager;

public class TawWorkdaySetDaoHibernate extends BaseDaoHibernate implements ITawWorkdaySetDao {

    /**
     * @see com.boco.eoms.workdayset.dao.TawWorkdaySetDao#getTawWorkdaySets(com.boco.eoms.commons.workdayset.model.TawWorkdaySet)
     */
    public List getTawWorkdaySets(final TawWorkdaySet tawWorkdaySet) {
        return getHibernateTemplate().find("from TawWorkdaySet");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawWorkdaySet == null) {
            return getHibernateTemplate().find("from TawWorkdaySet");
        } else {
            // filter on properties set in the tawWorkdaySet
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawWorkdaySet).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawWorkdaySet.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.workdayset.dao.TawWorkdaySetDao#getTawWorkdaySet(String id)
     */
    public TawWorkdaySet getTawWorkdaySet(final String id) {
        TawWorkdaySet tawWorkdaySet = (TawWorkdaySet) getHibernateTemplate().get(TawWorkdaySet.class, id);
        if (tawWorkdaySet == null) {
            throw new ObjectRetrievalFailureException(TawWorkdaySet.class, id);
        }

        return tawWorkdaySet;
    }

    /**
     * @see com.boco.eoms.workdayset.dao.TawWorkdaySetDao#getTawWorkdaySetss(TawWorkdaySet tawWorkdaySet)
     */
    public ArrayList getTawWorkdaySetss(final TawWorkdaySet tawWorkdaySet){
    	String hql = " from TawWorkdaySet obj where obj.workDate='"
			+ tawWorkdaySet.getWorkDate() + "' order by obj.id";
    	
		return (ArrayList) getHibernateTemplate().find(hql);
    }
    /**
     * @see com.boco.eoms.workdayset.dao.TawWorkdaySetDao#getSubAreaid(String areaid)
     */
    public List getSubAreaid(final String areaid){
    	String hql = "from TawSystemArea obj where obj.parentAreaid='"+areaid+"'";
    	List list = getHibernateTemplate().find(hql);
    	List areaStr =new   ArrayList();
    	String temp="";
    	for(int i = 0;i<list.size();i++){
    		TawSystemArea ta = (TawSystemArea) list.get(i);
    		temp = ta.getAreaid();
    		areaStr.add(temp);
    	  
    	}
    	return areaStr;
    }
    /**
     * @see com.boco.eoms.workdayset.dao.TawWorkdaySetDao#getAreaname(final String areaid)
     */
    public String getAreaname(final String areaid){
    	String hql = "from TawSystemArea obj where obj.areaid='"+areaid+"'";
    	List list = getHibernateTemplate().find(hql);
    	List areaStr =new   ArrayList();
    	String temp="";
    	
    		TawSystemArea ta = (TawSystemArea) list.get(0);
    		temp = ta.getAreaname();	
    	return temp;
    }
   
    /**
     * @see com.boco.eoms.workdayset.dao.TawWorkdaySetDao#getInfo(String areaid)
     */
    public List getInfo(final String areaid){
    	String hql = "from TawWorkdaySet obj where obj.areaId='"+areaid+"'order by obj.workDate,obj.startTime desc";
    	List list = getHibernateTemplate().find(hql);
    	//if(list.isEmpty())
    	//	return null;
    	//else
    	return list;
    }
    
    /**
     * @see com.boco.eoms.workdayset.dao.TawWorkdaySetDao#saveTawWorkdaySet(TawWorkdaySet tawWorkdaySet)
     */    
    public void saveTawWorkdaySet(final TawWorkdaySet tawWorkdaySet) {
        if ((tawWorkdaySet.getId() == null) || (tawWorkdaySet.getId().equals("")))
			getHibernateTemplate().save(tawWorkdaySet);
		else
			getHibernateTemplate().saveOrUpdate(tawWorkdaySet);
    }
    /**
     * @see com.boco.eoms.workdayset.dao.TawWorkdaySetDao#saveCityTawWorkdaySet(List tawWorkdaySet)
     */    
    public void saveCityTawWorkdaySet(final List tawWorkdaySet){
	  
	   List twds = tawWorkdaySet;
	   
	   TawWorkdaySet temp = (TawWorkdaySet) twds.get(0);
       String workDate= temp.getWorkDate();
       String area = temp.getAreaId();
       List list = getSubAreaid(area);
       removeTawWorkdaySets(workDate,area);
   
       for(int i = 0;i<twds.size();i++){
    	   
    	   temp = (TawWorkdaySet) twds.get(i);
    	   temp.setCover("1");
    	   saveTawWorkdaySet(temp);
		  
		   for(int j=0;j<list.size();j++){
		    	
	    	   removeTawWorkdaySets(workDate,list.get(j).toString());
    	   
	    	   TawWorkdaySet taw = new TawWorkdaySet();
	    		  taw.setAreaId(list.get(j).toString());
	    		  taw.setCover("0");
	    		  taw.setCreateTime(temp.getCreateTime());
	    		  taw.setStartTime(temp.getStartTime());
	    		  taw.setEndTime(temp.getEndTime());
	    		  taw.setStatus(temp.getStatus());
	    		  taw.setWorkDate(temp.getWorkDate());
	    		  taw.setUserId(temp.getUserId());
	    		  saveTawWorkdaySet(taw);
	    	   saveTawWorkdaySet(taw);
	    	   
	       }

       }
   }
    /**
     * @see com.boco.eoms.workdayset.dao.TawWorkdaySetDao#saveAllTawWorkdaySet(List tawWorkdaySet)
     */  
    public void saveAllTawWorkdaySet(final List tawWorkdaySet){
    	List twds = tawWorkdaySet;
    	String hql = "from TawSystemArea obj where obj.areaid!='101'"; 
    	List list = (ArrayList)getHibernateTemplate().find(hql);
    	TawWorkdaySet temp = (TawWorkdaySet) twds.get(0);
    	String workDate= temp.getWorkDate();
    	removeTawWorkdaySets(workDate);
    	for(int i = 0;i<twds.size();i++){
    	
    	TawWorkdaySet set = (TawWorkdaySet) twds.get(i);
 
    	set.setCover("1"); 
    	saveTawWorkdaySet(set);
    	 
    	 for(int j = 0;j<list.size();j++){
    		
    		 TawSystemArea tsa = (TawSystemArea) list.get(j);
    		  TawWorkdaySet taw = new TawWorkdaySet();
    		  taw.setAreaId(tsa.getAreaid());
    		  taw.setCover("0");
    		  taw.setCreateTime(set.getCreateTime());
    		  taw.setStartTime(set.getStartTime());
    		  taw.setEndTime(set.getEndTime());
    		  taw.setStatus(set.getStatus());
    		  taw.setWorkDate(set.getWorkDate());
    		  taw.setUserId(set.getUserId());
    		  saveTawWorkdaySet(taw);
    		 }
    }
  }   
    /**
     * @see com.boco.eoms.workdayset.dao.TawWorkdaySetDao#removeTawWorkdaySet(String id)
     */
    public void removeTawWorkdaySet(final String id) {
        getHibernateTemplate().delete(getTawWorkdaySet(id));
    }
    /**
     * @see com.boco.eoms.workdayset.dao.TawWorkdaySetDao#removeTawWorkdaySet(TawWorkdaySet tawWorkdaySet)
     */
    public void removeTawWorkdaySet(final TawWorkdaySet tawWorkdaySet) {
    	String hql = " from TawWorkdaySet obj where obj.workDate='"
			+ tawWorkdaySet.getWorkDate() + "' order by obj.id";
    	ArrayList al = (ArrayList)getHibernateTemplate().find(hql);
    	if(al!=null){
    		getHibernateTemplate().delete(al);
    	}
    }
    /**
     * @see com.boco.eoms.workdayset.dao.TawWorkdaySetDao#removeTawWorkdaySet(String workday, String areaid)
     */
    public void removeTawWorkdaySets(final String workday,final String areaid) {
    
    	String hql = "from TawWorkdaySet obj where obj.workDate='"+ workday + "'and obj.areaId='"+areaid+"'";
    	List al = getHibernateTemplate().find(hql);
    	if(!al.isEmpty()){
    		 int count = al.size();
    		 for(int i=0;i<count;i++)
    		 {
    			 TawWorkdaySet twd = (TawWorkdaySet)al.get(i);
    		
    		removeTawWorkdaySet(twd.getId());
    	}
    }
    }
    /**
     * @see com.boco.eoms.workdayset.dao.TawWorkdaySetDao#removeTawWorkdaySet(String workday)
     */
    public void removeTawWorkdaySets(final String workday){
    	String hql = "from TawWorkdaySet obj where obj.workDate='"+ workday + "'";
    	List al = getHibernateTemplate().find(hql);
    	if(!al.isEmpty()){
    		 int count = al.size();
    		 for(int i=0;i<count;i++)
    		 {
    			 TawWorkdaySet twd = (TawWorkdaySet)al.get(i);
    		
    		removeTawWorkdaySet(twd.getId());
    }
    	}
    }
    /**
     * @see com.boco.eoms.workdayset.dao.TawWorkdaySetDao#getTawWorkdaySets(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getTawWorkdaySets(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawWorkdaySet
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawWorkdaySet";
              if(whereStr!=null && whereStr.length()>0)
            		queryStr += whereStr;
            	String queryCountStr = "select count(*) " + queryStr;

							Integer total = (Integer) session.createQuery(queryCountStr).iterate()
									.next();
							Query query = session.createQuery(queryStr);
							query.setFirstResult(pageSize.intValue()
									* (curPage.intValue()));
							query.setMaxResults(pageSize.intValue());
							List result = query.list();
							HashMap map = new HashMap();
							map.put("total", total);
							map.put("result", result);
							return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }
    /**
     * @see com.boco.eoms.workdayset.dao.TawWorkdaySetDao#getTawWorkdaySets(final Integer curPage, final Integer pageSize)
     */    
    public Map getTawWorkdaySets(final Integer curPage, final Integer pageSize) {
			return this.getTawWorkdaySets(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.workdayset.dao.TawWorkdaySetDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from TawWorkdaySet obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}
	
	
	/**
     * @see com.boco.eoms.workdayset.dao.TawWorkdaySetDao#saveCityTawWorkdaySet(List tawWorkdaySet)
     */
	public List getUnWorkday(final String areaid,  final String startdate, final String enddate)
	{
		List list = new ArrayList();
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 Date t_date1 = null;
		 Date t_date2 = null;
		 try{
			 t_date1 = format.parse(startdate);
			 t_date2 = format.parse(enddate); 
		 } catch (ParseException e)
		 {
			 e.printStackTrace();
		 }
		 for(Date i = t_date1; i.before(t_date2); i.setDate(i.getDate() + 1))
		 {
			 if(!this.isWorkday(i.toString(), areaid))
			 {
				 list.add(i.toString());
			 }
		 }	 
		 return list;
	}
	
	/**
     * @see com.boco.eoms.workdayset.dao.TawWorkdaySetDao#saveCityTawWorkdaySet(List tawWorkdaySet)
     */
	public List getWorkday(final String areaid,  final String startdate, final String enddate)
	{
		List list = new ArrayList();
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 Date t_date1 = null;
		 Date t_date2 = null;
		 try{
			 t_date1 = format.parse(startdate);
			 t_date2 = format.parse(enddate); 
		 } catch (ParseException e)
		 {
			 e.printStackTrace();
		 }
		 for(Date i = t_date1; i.before(t_date2); i.setDate(i.getDate() + 1))
		 {
			 if(this.isWorkday(i.toString(), areaid))
			 {
				 list.add(i.toString());
			 }
		 }	 
		 return list;
	}
	
	/**
     * @see com.boco.eoms.workdayset.dao.TawWorkdaySetDao#saveCityTawWorkdaySet(List tawWorkdaySet)
     */
	public List getWorktime(final String areaid,  final String date)
	{
		List list = new ArrayList();
		if(this.isWorkday(date, areaid))
		 {
			 String hql = "from TawWorkdaySet obj where obj.workDate='"+ date + "'";
		    	List al = getHibernateTemplate().find(hql);
		    	if(!al.isEmpty()){
		    		 int count = al.size();
		    		 for(int i=0;i<count;i++)
		    		 {
		    			 TawWorkdaySet twd = (TawWorkdaySet)al.get(i);
		    			 String t1 = twd.getStartTime();
						 if(!"".equals(t1)&&t1!=null)
						 {
							 list.add(t1);
							 list.add(twd.getEndTime());
						 }
		    		 }
		    	}
		 }
		return list;
	}
	
	/**
     * @see com.boco.eoms.workdayset.dao.TawWorkdaySetDao#saveCityTawWorkdaySet(List tawWorkdaySet)
     */
	public int isWorkdayT2T(final String areaid, final String date, final String starttime, final String endtime)
	{
		if (!this.isWorkday(date, areaid))
			return 0;
		else
		{
			int t1 = Integer.parseInt(starttime.substring(0, starttime.indexOf(":"))) ;
			int t2 = Integer.parseInt(endtime.substring(0, endtime.indexOf(":"))) ;
			int result1 = 0;
			int result2 = 0;
			int sum = 0;
			for(int i = t1; i< t2; i++)
			{
				sum ++;
				String ts = i+":00";
				if(this.isWorktime(areaid, date, ts))
					result1++;
				else 
					result2++;				
			}
			if(result1 == sum)
				return 1;
			else if(result2 == sum)
				return 0;
			else 
				return 2;
		}
			
	}
	/**
     * @see com.boco.eoms.workdayset.dao.TawWorkdaySetDao#saveCityTawWorkdaySet(List tawWorkdaySet)
     */
	public int isWorkdayD2D(final String areaid, final String startdate, final String enddate)
	{
		int result1 = 0;
		int result2 = 0;
		int sum = 0;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 Date t_date1 = null;
		 Date t_date2 = null;
		 try{
			 t_date1 = format.parse(startdate);
			 t_date2 = format.parse(enddate); 
		 } catch (ParseException e)
		 {
			 e.printStackTrace();
		 }
		 for(Date i = t_date1; i.before(t_date2); i.setDate(i.getDate() + 1))
		 {
			 sum++;
			 if(this.isWorkday(i.toString(), areaid))
				 result1++;
			 else result2++;
		 }
		
		 if(result1 == sum)
			 return 1;
		 else if(result2 == sum)
		 	return 0;
		 else
			 return 2;
	}
	/**
     * @see com.boco.eoms.workdayset.dao.TawWorkdaySetDao#saveCityTawWorkdaySet(List tawWorkdaySet)
     */
	public boolean isWorktime(final String areaid, final String date, final String time)
	{
		int t = Integer.parseInt(time.substring(0, time.indexOf(":"))) ;
		List timelist1 = new ArrayList();
		List timelist2 = new ArrayList();
	    TawWorkdaySet tws = new TawWorkdaySet();
		boolean isworkday = this.isWorkday(date, areaid);
		if(!isworkday)
			return false;
		else
		{
			String hql = "from TawWorkdaySet obj where obj.workDate ='" + date + "'and obj.areaId='"+areaid+"'" ;
			 List list = getHibernateTemplate().find(hql);
			 if(list.isEmpty())
			 {
				 if((t >= 8 && t <12)||(t >= 13 && t <17))
					 return true;
				 else 
					 return false;
			 }
			 else
			 {
				 for(int i = 0; i < list.size(); i++)
				 {
					 tws = (TawWorkdaySet) list.get(i);
					 String t1 = tws.getStartTime();
					 if(!"".equals(t1)&&t1!=null)
					 {
						 timelist1.add(t1.substring(0, t1.indexOf(":")));
					 	 timelist2.add(tws.getEndTime().substring(0, tws.getEndTime().indexOf(":")));
					 }
				 }
				 if(timelist1.isEmpty())
				 {
					 if((t >= 8 && t <12)||(t >= 13 && t <17))
						 return true;
					 else 
						 return false;
				 }
				 for(int j = 0; j < timelist1.size(); j++)
				 {
					 if(t >= Integer.parseInt((String)timelist1.get(j)) && t <Integer.parseInt((String)timelist2.get(j)))
						 return true;
				 }
				 return false;				 
			 }
			
		}
	}
	
	
	/**
	   * @see com.boco.eoms.workdayset.dao.TawWorkdaySetDao#isWorkday(String date,String areaid)
	   */  
		 public boolean isWorkday(final String date,final String areaid)
		 {
			 //datebase exist	 
			 TawWorkdaySet tws = new TawWorkdaySet();
			 String hql = "from TawWorkdaySet obj where obj.workDate ='" + date + "'and obj.areaId='"+areaid+"'" ;
			 List list = getHibernateTemplate().find(hql);
			 if(!list.isEmpty())
			 {
				 int count = list.size();
				 for(int i=0;i<count;i++)
				 {
					 tws = (TawWorkdaySet) list.get(i);
					 String status = tws.getStatus();
					
					 if(status.equals("1"))
						 return true;
					 else if(status.equals("0"))
						 return false;
				 }
			 }
			 //datebase not exist,judge weekday
			 DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			 Date t_date = null;
			 try{
				 t_date = format.parse(date);
			 } catch (ParseException e)
			 {
				 e.printStackTrace();
			 }
			 int dayofweek = t_date.getDay();
			 
			 if(dayofweek == 0 || dayofweek ==6)
				 return false;
			 else
				 return true;		 
		 }
		

		 /**
		   * @see com.boco.eoms.workdayset.dao.TawWorkdaySetDao#getAreaId(com.boco.eoms.commons.workdayset.model.TawWorkdaySet)
		   */  
    public String getAreaId(String deptId)
    {
	TawSystemDept dept = new TawSystemDept();
	String hql = "from TawSystemDept obj where obj.deptId ='" + deptId + "'";
	List list = getHibernateTemplate().find(hql);
	if(!list.isEmpty())
	 {
		dept = (TawSystemDept) list.listIterator().next();
		return dept.getAreaid();
		 
	 }
	return null;
	
}
}