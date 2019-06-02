package com.boco.eoms.sheet.base.dao.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class EomsSheetHibernateSupport extends HibernateDaoSupport {

	protected HibernateTemplate createHibernateTemplate(SessionFactory arg0) {
		EomsSheetHibernateTemplate template = new EomsSheetHibernateTemplate();
		template.setSessionFactory(arg0);
		return template;
	}

}
