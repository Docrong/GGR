package com.boco.eoms.workplan.dao.hibernate;

import java.util.List;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate; 
import com.boco.eoms.workplan.dao.ITawwpWorkflowDao;
import com.boco.eoms.workplan.model.TawwpWorkflow;

public class TawwpWorkflowDaoHibernate  extends BaseDaoHibernate implements ITawwpWorkflowDao {
    
	public void saveWorkflow(TawwpWorkflow tawwpWorkflow){
		this.getHibernateTemplate().save(tawwpWorkflow);
	}
	public void deleteWorkflow(TawwpWorkflow tawwpWorkflow){
		this.getHibernateTemplate().delete(tawwpWorkflow);
	}
	public void update(TawwpWorkflow tawwpWorkflow){
		this.getHibernateTemplate().update(tawwpWorkflow);
	}       
	public TawwpWorkflow loadTawwpWorkflow(String id){
		return (TawwpWorkflow)this.getHibernateTemplate().load(TawwpWorkflow.class, id);
	}
	public List getListByExecute(String executeId){
		String hSql = "";
	    hSql =
	        "from TawwpWorkflow as tawwpworkflow where tawwpworkflow.executeId = '"+executeId+"' ";
	    return this.getHibernateTemplate().find(hSql);
	}
}
