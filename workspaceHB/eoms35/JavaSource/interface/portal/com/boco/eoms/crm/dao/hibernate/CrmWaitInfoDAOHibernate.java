package com.boco.eoms.crm.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.crm.dao.ICrmWaitInfoDAO;
import com.boco.eoms.crm.model.CrmWaitInfo;

/**
 * @author
 */
public class CrmWaitInfoDAOHibernate extends BaseDaoHibernate implements
        ICrmWaitInfoDAO {

    public CrmWaitInfo loadCrmWaitInfo(String id) throws Exception {
        CrmWaitInfo info = (CrmWaitInfo) getHibernateTemplate().get(
                CrmWaitInfo.class, id);
        return info;
    }

    /**
     * 信息发送完后更改表中发送状态
     *
     * @param id
     * @param isSended
     * @throws HibernateException
     */
    public void updateSendStatusCrmWaitInfo(String id, Integer isSended)
            throws HibernateException {
        CrmWaitInfo info = (CrmWaitInfo) getHibernateTemplate().get(
                CrmWaitInfo.class, id);
        info.setIsSended(isSended);
        info.setSendtime(new Date());
        getHibernateTemplate().save(info);
    }

    /**
     * 获取所有未成功发送的信息
     *
     * @return
     * @throws HibernateException
     */
    public List getAllNotSendInfo() throws HibernateException {
        String sql = "from CrmWaitInfo as info where info.isSended = 0 or info.isSended is null";
        List list = getHibernateTemplate().find(sql);
        return list;
    }

    public void saveOrUpdateCrmWaitInfo(CrmWaitInfo info)
            throws HibernateException {
        System.out.println(info.getInterfaceType());
        getHibernateTemplate().saveOrUpdate(info);
    }

}
