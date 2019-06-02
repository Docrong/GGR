package com.boco.eoms.sheet.commonchangedeploy.dao.hibernate;


import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.commonchangedeploy.dao.ICommonChangeDeployMainDAO;
import com.boco.eoms.sheet.commonchangedeploy.model.CommonChangeDeployMain;

/**
 * <p>
 * Title:通用变更配置工单
 * </p>
 * <p>
 * Description:通用变更配置工单
 * </p>
 * <p>
 * Fri Jun 12 09:08:01 CST 2009
 * </p>
 * 
 * @author yangwei
 * @version 3.5
 * 
 */
 
 public class CommonChangeDeployMainDAOHibernate extends MainDAO implements ICommonChangeDeployMainDAO {
 	  
 	  /* (non-Javadoc)
	   * @see com.boco.eoms.sheet.base.dao.IMainDAO#loadSinglePO(java.lang.String)
	   *
	   */
	   public BaseMain loadSinglePO(String id) throws HibernateException {
	    	CommonChangeDeployMain main = (CommonChangeDeployMain) getHibernateTemplate().get(CommonChangeDeployMain.class,id);
		  	return main;
	   }
	   
	   /* (non-Javadoc)
	   * @see com.boco.eoms.sheet.base.dao.IMainDAO#loadSinglePOByProcessId(java.lang.String)
	   *
	   */
	   public BaseMain loadSinglePOByProcessId(String processId) throws HibernateException {
		 	CommonChangeDeployMain main = new  CommonChangeDeployMain();
		 	
		 	String sql = " from CommonChangeDeployMain as main where main.piid ='" + processId + "'";
			List list = getHibernateTemplate().find(sql);
			
			if (list.size() == 0) {
				main = null;
			} else {
				main = (CommonChangeDeployMain) list.get(0);
			}
			
			return main;
	  }
	 
	  /* (non-Javadoc)
	   * @see com.boco.eoms.sheet.base.dao.IMainDAO#deleteMain(java.lang.String)
	   *
	   */
	   public void deleteMain(String id) throws HibernateException {
		   CommonChangeDeployMain main = (CommonChangeDeployMain) getHibernateTemplate().get(CommonChangeDeployMain.class,id);
		   main.setDeleted(new Integer(1));
		   getHibernateTemplate().save(main);
	   }
	   
	  /**
	    * @see com.boco.eoms.base.dao.Dao#saveObject(java.lang.Object)
	    */
	  public void save(Object o) {
		 getHibernateTemplate().save(o);
	  }
	  
	   /* (non-Javadoc)
	    * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
	    */
	  public String getMainName() {
		return "CommonChangeDeployMain";
	  }
 
 }
 



