
package com.boco.eoms.message.dao.hibernate;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.message.dao.SmsSpSubmitDao;
import com.boco.eoms.message.model.SmsService;
import com.boco.eoms.message.model.SmsSpSubmit;
/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-5-5 下午03:37:38
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 *
 */
public class SmsSpSubmitDaoHibernate extends BaseDaoHibernate implements SmsSpSubmitDao {

	public List getSmsSpSubmits(SmsSpSubmit smsSpSubmit) {
		return getHibernateTemplate().find("from SmsSpSubmit");
	}

	public SmsSpSubmit getSmsSpSubmit(String id) {
		SmsSpSubmit smsSpSubmit = (SmsSpSubmit) getHibernateTemplate().get(SmsSpSubmit.class, id);
        if (smsSpSubmit == null) {
            throw new ObjectRetrievalFailureException(SmsSpSubmit.class, id);
        }

        return smsSpSubmit;
	}

	public void removeSmsSpSubmit(String id) {
		getHibernateTemplate().delete(getSmsSpSubmit(id));
		
	}

	public void saveSmsSpSubmit(SmsSpSubmit smsSpSubmit) {
		if ((smsSpSubmit.getId() == null) || (smsSpSubmit.getId().equals("")))
			getHibernateTemplate().save(smsSpSubmit);
		else
			getHibernateTemplate().saveOrUpdate(smsSpSubmit);
		
	}

	
    

}
