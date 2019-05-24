package com.boco.eoms.taskplan.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.Dao;

public interface TaskplanqueryDao extends Dao{
	
	public Map getTaskplanbycon(final Integer curPage, final Integer pageSize,
			final  String project_name, final String project_decompos,
			final String deptid,final String stakeholders,final String fenye) ;

}
