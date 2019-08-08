package com.boco.eoms.eva.dao.hibernate;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.eva.dao.IEvaTemplateDao;
import com.boco.eoms.eva.model.EvaTemplate;
import com.boco.eoms.eva.util.EvaConstants;

public class EvaTemplateDaoHibernate extends BaseDaoHibernate implements
        IEvaTemplateDao, ID2NameDAO {

    public EvaTemplate getTemplate(String id) {
        EvaTemplate template = (EvaTemplate) getHibernateTemplate().get(
                EvaTemplate.class, id);
        if (null == template) {
            // throw new ObjectRetrievalFailureException(EvaTemplate.class, id);
            template = new EvaTemplate();
        }
        return template;
    }

    public void removeTemplate(EvaTemplate template) {
        getHibernateTemplate().delete(template);
    }

    public void saveTemplate(EvaTemplate template) {
        if (null == template.getId() || "".equals(template.getId())) {
            getHibernateTemplate().save(template);
        } else {
            getHibernateTemplate().saveOrUpdate(template);
        }
    }

    public String id2Name(String id) {
        String templateName = "";
        EvaTemplate template = getTemplate(id);
        if (null != template.getId() && !"".equals(template.getId())) {
            if (null != template.getTemplateName()
                    && !"".equals(template.getTemplateName())) {
                templateName = template.getTemplateName();
            } else {
                templateName = EvaConstants.NODE_NONAME;
            }
        } else {
            templateName = EvaConstants.NODE_NONAME;
        }
        return templateName;
    }
}
