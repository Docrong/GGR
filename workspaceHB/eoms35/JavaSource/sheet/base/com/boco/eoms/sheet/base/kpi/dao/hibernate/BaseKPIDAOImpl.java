/*
 * Created on 2008-1-10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.kpi.dao.hibernate;

import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.sheet.base.kpi.dao.IBaseKPIDAO;
import com.boco.eoms.sheet.base.model.BaseCollect;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;


/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BaseKPIDAOImpl extends BaseDaoHibernate implements IBaseKPIDAO{
//	protected String beanId;
	protected BaseLink link;
	protected BaseMain main;
	protected BaseCollect collectPojo;
	
	/**
	 * @return Returns the collectPojo.
	 */
	public BaseCollect getCollectPojo() {
		return collectPojo;
	}
	/**
	 * @param collectPojo The collectPojo to set.
	 */
	public void setCollectPojo(BaseCollect collectPojo) {
		this.collectPojo = collectPojo;
	}
	/**
	 * @return Returns the link.
	 */
	public BaseLink getLink() {
		return link;
	}
	/**
	 * @param link The link to set.
	 */
	public void setLink(BaseLink link) {
		this.link = link;
	}
	/**
	 * @return Returns the main.
	 */
	public BaseMain getMain() {
		return main;
	}
	/**
	 * @param main The main to set.
	 */
	public void setMain(BaseMain main) {
		this.main = main;
	}
	
	/**
	 * 获取采集数据
	 * @return
	 */
	public List getBaseData(String startDate){
		String linkName = this.getLinkName();
		String mainName = this.getMainName();
		
		String hql = "from " + linkName + " link," + linkName + " preLink," + mainName + " main where link.mainId=main.id and link.preLinkId=preLink.id";
//		String hql = "from " + linkName + " link," + mainName + " main where link.mainId=main.id";
		if(startDate != null && startDate.length()>0)
			hql += " and (main.endTime>=to_date('"+startDate+"') or main.endTime is null)";
		return getHibernateTemplate().find(hql);
	}
	public List getCollects(){
		String pojoName = this.getCollectName();
		String hql = "from " + pojoName;
		return getHibernateTemplate().find(hql);
	}
	public void saveOrUpdateAll(final List list){
//		getHibernateTemplate().saveOrUpdateAll(list);
		HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
            	session.setFlushMode(FlushMode.AUTO); 
            	for(int i=0;i<list.size();i++){
            	  session.saveOrUpdate(list.get(i));
            	}
            	session.flush(); 
            	return null;
            }
        };
        getHibernateTemplate().execute(callback);
	}
	/**
	 * 获取前一条link
	 * @param linkId
	 * @return
	 */
	public BaseLink getPreLink(String linkId){
		BaseLink link = (BaseLink)getHibernateTemplate().get(BaseLink.class,linkId);
		return (BaseLink)getHibernateTemplate().get(BaseLink.class,link.getPreLinkId());
	}
	/**
	 * 删除上次执行到目前为止,结束的和所有未结束的工单KPI数据
	 * @param startDate 上次执行的时间
	 */
	public void deleteData(String startDate){
		String mainName = this.getMainName();
		String pojoName = this.getCollectName();
		
		String hql = "from " + mainName + " main, "+pojoName+" c where main.id=c.mainId";
		if(startDate != null && startDate.length()>0)
			hql += " and (main.endTime>=to_date('"+startDate+"') or main.endTime is null)";
		List list = getHibernateTemplate().find(hql);
//		getHibernateTemplate().delete(list);
		for(int i=0;i<list.size();i++){
			Object[] obj = (Object[])list.get(i);
			BaseCollect collect = (BaseCollect)obj[1];
			getHibernateTemplate().delete(collect);
		}
	}
	public String getMainName(){
		String mainClassName = main.getClass().getName();
		return mainClassName.substring(mainClassName.lastIndexOf(".")+1);
	}
	public String getLinkName(){
		String linkClassName = link.getClass().getName();
		return linkClassName.substring(linkClassName.lastIndexOf(".")+1);
	}
	public String getCollectName(){
		String className = collectPojo.getClass().getName();
		return className.substring(className.lastIndexOf(".")+1);
	}
}
