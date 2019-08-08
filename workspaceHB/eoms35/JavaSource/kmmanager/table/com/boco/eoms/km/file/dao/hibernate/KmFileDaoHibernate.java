package com.boco.eoms.km.file.dao.hibernate;

import java.util.ArrayList;
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
import com.boco.eoms.km.file.dao.KmFileDao;
import com.boco.eoms.km.file.model.KmFile;
import com.boco.eoms.km.file.model.KmFileHis;

/**
 * <p>
 * Title:文件管理 dao的hibernate实现
 * </p>
 * <p>
 * Description:文件管理
 * </p>
 * <p>
 * Wed Mar 25 11:32:18 CST 2009
 * </p>
 *
 * @author eoms
 * @version 1.0
 */
public class KmFileDaoHibernate extends BaseDaoHibernate implements KmFileDao,
        ID2NameDAO {

    public void moveFileByNode(final String fromNodeId, final String toNodeId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String hqlUpdate = "update KmFile file set file.nodeId =? where file.nodeId=?";
                Query query = session.createQuery(hqlUpdate);
                query.setString(0, toNodeId);
                query.setString(1, fromNodeId);
                int ret = query.executeUpdate();
                return new Integer(ret);
            }
        };
        getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.km.file.KmFileDao#getFiles()
     */
    public List getFiles() {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmFile file";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.km.file.KmFileDao#getFile(java.lang.String)
     */
    public KmFile getFile(final String id) {
        KmFile kmFile = (KmFile) getHibernateTemplate().get(KmFile.class, id);
        if (kmFile == null) {
            kmFile = new KmFile();
        }
        return kmFile;
    }

    /**
     * @see com.boco.eoms.km.file.KmFileDao#getFile(java.lang.String)
     */
    public KmFileHis getFileHis(String id) {
        KmFileHis kmFileHis = (KmFileHis) getHibernateTemplate().get(KmFileHis.class, id);
        if (kmFileHis == null) {
            kmFileHis = new KmFileHis();
        }
        return kmFileHis;
    }

    /**
     * @see com.boco.eoms.km.file.KmFileDao#saveFiles(com.boco.eoms.km.file.KmFile)
     */
    public void saveFile(KmFile file) {
        if ((file.getId() == null) || (file.getId().equals("")))
            getHibernateTemplate().save(file);
        else
            getHibernateTemplate().saveOrUpdate(file);
    }

    /**
     * @see com.boco.eoms.km.file.KmFileDao#saveFiles(com.boco.eoms.km.file.KmFile)
     */
    public void saveFileHis(KmFileHis fileHis) {
        if ((fileHis.getHisId() == null) || (fileHis.getHisId().equals("")))
            getHibernateTemplate().save(fileHis);
        else
            getHibernateTemplate().saveOrUpdate(fileHis);
    }

    /**
     * @see com.boco.eoms.km.file.KmFileDao#removeFiles(java.lang.String)
     */
    public void removeFile(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String hqlUpdate = "update KmFile file set file.isDeleted=1 where file.id=?";
                Query query = session.createQuery(hqlUpdate);
                query.setString(0, id);
                int ret = query.executeUpdate();
                return new Integer(ret);
            }
        };
        getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.km.file.KmFileDao#removeFiles(java.lang.String)
     */
    public void removeFileByNodeId(final String nodeId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String hqlUpdate = "update KmFile file set file.isDeleted=1 where file.nodeId like ?";
                Query query = session.createQuery(hqlUpdate);
                query.setString(0, nodeId + '%');
                int ret = query.executeUpdate();
                return new Integer(ret);
            }
        };
        getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        KmFile file = this.getFile(id);
        if (file == null) {
            return "";
        }
        return file.getFileName();
    }

    /**
     * @see com.boco.eoms.km.file.KmFileDao#getFiles(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getFiles(final Integer curPage, final Integer pageSize,
                        final String whereStr, final String orderBy) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer queryStr = new StringBuffer("from KmFile file ");
                if (whereStr != null && whereStr.length() > 0)
                    queryStr.append(whereStr);

                StringBuffer queryCountStr = new StringBuffer("select count(file.id) ");
                queryCountStr.append(queryStr);

                queryStr.append(orderBy);

                int total = ((Integer) session.createQuery(queryCountStr.toString())
                        .iterate().next()).intValue();

                List result = new ArrayList();
                if (total > 0) {
                    Query query = session.createQuery(queryStr.toString());
                    query.setFirstResult(pageSize.intValue()
                            * (curPage.intValue()));
                    query.setMaxResults(pageSize.intValue());
                    result = query.list();
                }

                HashMap map = new HashMap();
                map.put("total", new Integer(total));
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.km.file.KmFileDao#getKmFileHistoryList(final String id)
     */
    public List getKmFileHistoryList(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmFileHis fileHis where fileId=?";
                Query query = session.createQuery(queryStr);
                query.setString(0, id);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }
}