package com.boco.eoms.message.dao.hibernate;

import java.util.List;

import com.boco.eoms.message.dao.ISmsSheetContentDao;
import com.boco.eoms.message.model.SmsSheetContent;
import com.boco.eoms.sheet.base.dao.hibernate.BaseSheetDaoHibernate;

public class SmsSheetContentDaoHibernate extends BaseSheetDaoHibernate implements ISmsSheetContentDao {

    public void save(Object o) {
        getHibernateTemplate().saveOrUpdate(o);
    }

    public List getRecordByFlowNameAndType(String workFlowName, String type) {
        String sql = " from SmsSheetContent where workFlowName='" + workFlowName + "' and " +
                " type in ('" + type + "') order by processcnname desc , type desc ";
        List list = this.getHibernateTemplate().find(sql);
        return list;
    }

    public void removeRecordById(SmsSheetContent ssc) {
        this.getHibernateTemplate().delete(ssc);
    }

    public List getRecordByCondition(String sql) {
        List list = this.getHibernateTemplate().find(sql);
        return list;
    }

    public SmsSheetContent getSSCById(String id) {
        SmsSheetContent ssc = (SmsSheetContent) this.getHibernateTemplate().get(SmsSheetContent.class, id);
        return ssc;
    }

    public int modifySheetContent(String[] id, String content) {
        StringBuffer sql = new StringBuffer("update SmsSheetContent set content='" + content + "' where id in( ");
        for (int i = 0; i < id.length; i++) {
            if (i == id.length - 1) {
                sql.append("?)");
            } else {
                sql.append("?,");
            }
        }
        return this.getHibernateTemplate().bulkUpdate(sql.toString(), id);
    }

}
