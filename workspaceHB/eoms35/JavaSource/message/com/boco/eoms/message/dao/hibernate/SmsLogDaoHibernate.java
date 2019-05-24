
package com.boco.eoms.message.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.message.dao.SmsLogDao;
import com.boco.eoms.message.model.SmsLog;
import com.boco.eoms.message.util.MsgConstants;
/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-5-5 下午03:37:22
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 *
 */
public class SmsLogDaoHibernate extends BaseDaoHibernate implements SmsLogDao {

    /**
     * @see com.boco.eoms.message.dao.SmsLogDao#getSmsLogs(com.boco.eoms.message.model.SmsLog)
     */
    public List getSmsLogs(final SmsLog smsLog) {
        return getHibernateTemplate().find("from SmsLog");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (smsLog == null) {
            return getHibernateTemplate().find("from SmsLog");
        } else {
            // filter on properties set in the smsLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(smsLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(SmsLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.message.dao.SmsLogDao#getSmsLog(String id)
     */
    public SmsLog getSmsLog(final String id) {
        SmsLog smsLog = (SmsLog) getHibernateTemplate().get(SmsLog.class, id);
        if (smsLog == null) {
            throw new ObjectRetrievalFailureException(SmsLog.class, id);
        }

        return smsLog;
    }

    /**
     * @see com.boco.eoms.message.dao.SmsLogDao#saveSmsLog(SmsLog smsLog)
     */    
    public void saveSmsLog(final SmsLog smsLog) {
		getHibernateTemplate().save(smsLog);
    }

    /**
     * @see com.boco.eoms.message.dao.SmsLogDao#removeSmsLog(String id)
     */
    public void removeSmsLog(final String id) {
        getHibernateTemplate().delete(getSmsLog(id));
    }
    /**
     * ���ڷ�ҳ��ʾ
     * curPage ��ǰҳ��
     * pageSize ÿҳ��ʾ��
     * whereStr where�������䣬������"where"��ͷ,����Ϊ��
     */
    public Map getSmsLogs(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the smsLog
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from SmsLog";
              if(whereStr!=null && whereStr.length()>0)
            		queryStr += whereStr;
            	String queryCountStr = "select count(*) " + queryStr;

							int total = ((Integer) session.createQuery(queryCountStr).iterate()
									.next()).intValue();
							Query query = session.createQuery(queryStr);
							query.setFirstResult(pageSize.intValue()
									* (curPage.intValue()));
							query.setMaxResults(pageSize.intValue());
							List result = query.list();
							HashMap map = new HashMap();
							map.put("total", new Integer(total));
							map.put("result", result);
							return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }
    public Map getSmsLogs(final Integer curPage, final Integer pageSize) {
			return this.getSmsLogs(curPage,pageSize,null);
		}

	public List listAll() {
		return this.getHibernateTemplate().find("from SmsLog");
	}

	public List listByMobile(String mobile) {
		String hql = "from SmsLog where mobile='"+mobile+"'";		
		return getHibernateTemplate().find(hql);
	}

	public List listByReceiver(String receiverId) {
		String hql = "from SmsLog where receiverId='"+receiverId+"'";		
		return getHibernateTemplate().find(hql);
	}

	public List listBySuccess(String success) {
		String hql = "from SmsLog where success='"+success+"'";
		return getHibernateTemplate().find(hql);
	}

//	public String isSendSuccess(String serviceId, String buizId, String receiverId) {
//		String success = "";		
//		String hql = "from SmsLog where serviceId='"+serviceId+"' and buizId='"+buizId+"' and receiverId='"+receiverId+"'";
//		List logList = getHibernateTemplate().find(hql);
//		if(logList != null && logList.size() != 0) {
//			SmsLog log = new SmsLog();
//			log = (SmsLog)logList.get(0);
//			success = log.getSuccess();
//			return success;
//		}
//		return success;
//	}

	public String countSuccessedMsg(String serviceId) {
		String hql = "select count('"+serviceId+"') from SmsLog where success='"+MsgConstants.SUCCESS+"'";
		List list = this.getHibernateTemplate().find(hql);
		return new Integer(list.size()).toString();
	}

	public String countFaildMsg(String serviceId) {
		String hql = "select count('"+serviceId+"') from SmsLog where success='"+MsgConstants.UNSUCCESS+"'";
		List list = this.getHibernateTemplate().find(hql);
		return new Integer(list.size()).toString();
	}

	public String countMsg(String serviceId) {
		String hql = "select count('"+serviceId+"') from SmsLog";
		List list = this.getHibernateTemplate().find(hql);
		return new Integer(list.size()).toString();
	}

	public String countSMS(String serviceId) {
		return null;
	}

	public String countFaildMsg4Id(String serviceId, String id) {
		return null;
	}

	public String countSuccessedMsg4Id(String serviceId, String id) {
		return null;
	}

}
