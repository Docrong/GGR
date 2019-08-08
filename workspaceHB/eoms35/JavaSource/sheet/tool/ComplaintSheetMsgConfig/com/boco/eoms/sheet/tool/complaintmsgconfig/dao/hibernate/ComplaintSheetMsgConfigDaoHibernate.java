package com.boco.eoms.sheet.tool.complaintmsgconfig.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.sheet.tool.complaintmsgconfig.dao.IComplaintSheetMsgConfigDao;
import com.boco.eoms.sheet.tool.complaintmsgconfig.model.ComplaintSheetMsgConfig;

/**
 * <p>
 * Title:投诉工单短信配置类 dao的hibernate实现
 * </p>
 * <p>
 * Description:投诉工单短信配置类
 * </p>
 * <p>
 * Mon Sep 14 10:06:54 CST 2009
 * </p>
 *
 * @author qinmin
 * @version 1.0
 */
public class ComplaintSheetMsgConfigDaoHibernate extends BaseDaoHibernate implements IComplaintSheetMsgConfigDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.sheet.tool.complaintmsgconfig.IComplaintSheetMsgConfigDao#getComplaintSheetMsgConfigs()
     */
    public List getComplaintSheetMsgConfigs() {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from ComplaintSheetMsgConfig complaintSheetMsgConfig";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.sheet.tool.complaintmsgconfig.IComplaintSheetMsgConfigDao#getComplaintSheetMsgConfig(java.lang.String)
     */
    public ComplaintSheetMsgConfig getComplaintSheetMsgConfig(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from ComplaintSheetMsgConfig complaintSheetMsgConfig where complaintSheetMsgConfig.id=:id";
                Query query = session.createQuery(queryStr);
                query.setString("id", id);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (ComplaintSheetMsgConfig) result.iterator().next();
                } else {
                    return new ComplaintSheetMsgConfig();
                }
            }
        };
        return (ComplaintSheetMsgConfig) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.sheet.tool.complaintmsgconfig.IComplaintSheetMsgConfigDao#saveComplaintSheetMsgConfigs(com.boco.eoms.sheet.tool.complaintmsgconfig.ComplaintSheetMsgConfig)
     */
    public void saveComplaintSheetMsgConfig(final ComplaintSheetMsgConfig complaintSheetMsgConfig) {
        if ((complaintSheetMsgConfig.getId() == null) || (complaintSheetMsgConfig.getId().equals("")))
            getHibernateTemplate().save(complaintSheetMsgConfig);
        else
            getHibernateTemplate().saveOrUpdate(complaintSheetMsgConfig);
    }

    /**
     * @see com.boco.eoms.sheet.tool.complaintmsgconfig.IComplaintSheetMsgConfigDao#removeComplaintSheetMsgConfigs(java.lang.String)
     */
    public void removeComplaintSheetMsgConfig(final String id) {
        getHibernateTemplate().delete(getComplaintSheetMsgConfig(id));
    }

    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        ComplaintSheetMsgConfig complaintSheetMsgConfig = this.getComplaintSheetMsgConfig(id);
        if (complaintSheetMsgConfig == null) {
            return "";
        }
        //TODO 请修改代码
        return complaintSheetMsgConfig.getId();
    }

    /**
     * @see com.boco.eoms.sheet.tool.complaintmsgconfig.IComplaintSheetMsgConfigDao#getComplaintSheetMsgConfigs(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getComplaintSheetMsgConfigs(final Integer curPage, final Integer pageSize,
                                           final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from ComplaintSheetMsgConfig complaintSheetMsgConfig";
                if (whereStr != null && whereStr.length() > 0)
                    queryStr += whereStr;
                String queryCountStr = "select count(*) " + queryStr;

                int total = ((Integer) session.createQuery(queryCountStr)
                        .iterate().next()).intValue();
                Query query = session.createQuery(queryStr);
                query
                        .setFirstResult(pageSize.intValue()
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

    /**
     * 根据地域ID跟投诉分类查询对应的短信通知对象
     *
     * @param areaId        地域ID
     * @param complaintType 投诉分类
     * @return 返回对应的短信通知对象
     */
    public String getNotifyUser(final String areaId, final String complaintType) {
        String userId = "";
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from ComplaintSheetMsgConfig complaintSheetMsgConfig " +
                        " where complaintSheetMsgConfig.areaId like '%" + areaId + "%'and complaintSheetMsgConfig.complaintType like '%" + complaintType + "%'";
                Query query = session.createQuery(queryStr);
//				query.setString("areaId", areaId);
//				query.setString("complaintType", complaintType);
//				query.setFirstResult(0);
//				query.setMaxResults(1);
                List result = query.list();
                return result;
            }
        };
        List listconfig = (List) getHibernateTemplate().execute(callback);
        String configs = "";
        for (int i = 0; i < listconfig.size(); i++) {
            ComplaintSheetMsgConfig config = (ComplaintSheetMsgConfig) listconfig.get(i);
            configs += config.getNotifyUserIds();
            if (listconfig.size() - 1 > i) {
                configs += ",";
            }
        }
        System.out.println("wanghao修改投诉大面积投诉短信接收人：" + configs);
        return configs;
    }

    /**
     * 根据条件查询投诉工单短信配置信息是否存在
     *
     * @param areaId        地域ID
     * @param complaintType 投诉分类
     * @return true or false
     */
    public Object getComplaintSheetMsgConfig(final String areaId, final String complaintType) {
        boolean flag = false;
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from ComplaintSheetMsgConfig complaintSheetMsgConfig " +
                        " where complaintSheetMsgConfig.areaId=:areaId and complaintSheetMsgConfig.complaintType=:complaintType";
                Query query = session.createQuery(queryStr);
                query.setString("areaId", areaId);
                query.setString("complaintType", complaintType);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (ComplaintSheetMsgConfig) result.iterator().next();
                } else {
                    return null;
                }
            }
        };
        return getHibernateTemplate().execute(callback);
    }

    /**
     * 更新短信通知对象
     *
     * @param userId 需要更新的用户ID
     */
    public void updateUserId(String userId) {

    }
}