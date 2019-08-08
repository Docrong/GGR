package com.boco.eoms.sheet.sheetdelete.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;
import com.boco.eoms.sheet.sheetdelete.dao.IWfSheetDeleteInfoDAO;
import com.boco.eoms.sheet.sheetdelete.model.WfSheetDeleteInfo;

public class WfSheetDeleteInfoDAOHibernate extends BaseDaoHibernate implements IWfSheetDeleteInfoDAO {

    public List getAllNotSendInfo() throws HibernateException {
        final String sql = "from WfSheetDeleteInfo as info where info.isSended = '0'";
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    Query query = session.createQuery(sql);
                    query.setFirstResult(0);
                    query.setMaxResults(15);
                    List resultList = query.list();
                    return resultList;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new HibernateException("task list error");
                }
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    public WfSheetDeleteInfo loadWfSheetDeleteInfo(String id) throws Exception {
        return (WfSheetDeleteInfo) getHibernateTemplate().get(WfSheetDeleteInfo.class, id);
    }


    public void updateSendStatusWfSheetDeleteInfo(String id, String isSended) throws HibernateException {
        WfSheetDeleteInfo info = (WfSheetDeleteInfo) getHibernateTemplate().get(
                WfSheetDeleteInfo.class, id);
        info.setIsSended(isSended);
        info.setSendTime(new Date());
        getHibernateTemplate().save(info);

    }

    public void saveOrUpdateWfInterfaceInfo(WfSheetDeleteInfo info) throws HibernateException {
        getHibernateTemplate().saveOrUpdate(info);

    }


}
