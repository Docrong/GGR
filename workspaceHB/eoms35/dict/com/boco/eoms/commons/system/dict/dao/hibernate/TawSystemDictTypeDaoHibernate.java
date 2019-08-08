package com.boco.eoms.commons.system.dict.dao.hibernate;

// java standard library

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// spring library
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.dao.ITawSystemDictTypeDao;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;


public class TawSystemDictTypeDaoHibernate extends BaseDaoHibernate implements
        ITawSystemDictTypeDao, ID2NameDAO {

    /*
     * @see com.boco.eoms.commons.system.dict.dao.ITawSystemDictTypeDao#getTawSystemDictTypes(com.boco.eoms.commons.system.dict.model.TawSystemDictType)
     */
    public List getTawSystemDictTypes(final TawSystemDictType tawSystemDictType) {
        return getHibernateTemplate().find("from TawSystemDictType");
    }

    /*
     * @see com.boco.eoms.commons.system.dict.dao.ITawSystemDictTypeDao#getTawSystemDictType(Integer
     *      id)
     */
    public TawSystemDictType getTawSystemDictType(final Integer id) {
        TawSystemDictType tawSystemDictType = (TawSystemDictType) getHibernateTemplate()
                .get(TawSystemDictType.class, id);
        if (tawSystemDictType == null) {
            BocoLog.warn(this, "uh oh, tawSystemDictType with dictId '" + id
                    + "' not found...");
            throw new ObjectRetrievalFailureException(TawSystemDictType.class,
                    id);
        }

        return tawSystemDictType;
    }

    /*
     * @see com.boco.eoms.commons.system.dict.dao.ITawSystemDictTypeDao#saveTawSystemDictType(TawSystemDictType
     *      tawSystemDictType)
     */
    public void saveTawSystemDictType(final TawSystemDictType tawSystemDictType) {
        if ((tawSystemDictType.getId() == null)
                || (tawSystemDictType.getId().equals("")))
            getHibernateTemplate().save(tawSystemDictType);
        else
            getHibernateTemplate().saveOrUpdate(tawSystemDictType);
    }

    /*
     * @see com.boco.eoms.commons.system.dict.dao.ITawSystemDictTypeDao#removeTawSystemDictType(Integer
     *      id)
     */
    public void removeTawSystemDictType(final Integer id) {
        getHibernateTemplate().delete(getTawSystemDictType(id));
    }

    public String getParentTypeName(String _strCurCode) {
        String _strRtn = "root";

        if (_strCurCode.length() > 4) {
            String _strParentCode = _strCurCode.substring(0, _strCurCode
                    .length() - 3);
            String _strHQL = "select dict.dictName from TawSystemDictType dict where dict.dictId="
                    + _strParentCode;
            List _objResult = getHibernateTemplate().find(_strHQL);
            if (_objResult.get(0) != null) {
                _strRtn = (String) _objResult.get(0);
            }
        }
        return _strRtn;
    }

    /**
     * 根据字典ID查询字典名称
     */
    public TawSystemDictType getDictByDictId(String _strDictId) {
        String _strHQL = " from TawSystemDictType dicttype where dicttype.dictId='"
                + _strDictId + "'";
        return (TawSystemDictType) getHibernateTemplate().find(_strHQL).get(0);
    }

    public List getworkplanDictByDictId() {
        return getHibernateTemplate().find("from TawSystemDictType tawSystemDictType where tawSystemDictType.leaf in ('0','1')");
    }

    /**
     * 查询下一级信息
     *
     * @param parentdictid
     * @return
     */
    public ArrayList getDictSonsByDictid(String parentdictid) {
        String hql = " from TawSystemDictType dicttype where dicttype.parentDictId='"
                + parentdictid + "' order by dicttype.dictCode";
        return (ArrayList) getHibernateTemplate().find(hql);
    }

    /**
     * 根据字id查询字典信息
     *
     * @param dictid
     * @return
     */
    public TawSystemDictType getDictTypeByDictid(String dictid) {
        String hql = " from TawSystemDictType dicttype where dicttype.dictId='"
                + dictid + "' order by dicttype.dictCode";
        TawSystemDictType dictType = new TawSystemDictType();
        List list = getHibernateTemplate().find(hql);
        if (list != null && list.size() > 0)
            return (TawSystemDictType) getHibernateTemplate().find(hql).get(0);
        else
            return null;
    }

    /**
     * 判断是否有相同级别的字典类型
     *
     * @param systype
     * @return
     */
    public boolean isHaveSameLevel(String parentdictid, String systype) {
        boolean flag = false;
        String hql = " from TawSystemDictType dicttype where dicttype.parentDictId='"
                + parentdictid + "' and dicttype.sysType='" + systype + "'";
        List list = getHibernateTemplate().find(hql);
        if (list != null && list.size() > 0) {
            flag = true;
        }
        return flag;

    }

    /**
     * 查询code的字典信息
     *
     * @param code
     * @return
     */
    public List getDictByCode(String code) {
        String hql = " from TawSystemDictType dicttype where dicttype.dictCode='"
                + code + "' order by dicttype.dictCode";
        return (ArrayList) getHibernateTemplate().find(hql);
    }

    /*
     * id2name，即字典id转为字典名称 added by qinmin
     *
     * @see com.boco.eoms.base.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(final String id) throws DictDAOException {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                // hql，以角色ID为条件查询
                String queryStr = " from TawSystemDictType dictType where dictType.dictId=:dictId";

                Query query = session.createQuery(queryStr);
                // 角色ID号
                query.setString("dictId", id);
                // 仅查一条
                query.setFirstResult(0);
                query.setMaxResults(1);
                List list = query.list();
                TawSystemDictType dictType = null;

                if (list != null && !list.isEmpty()) {
                    // 不为空则取dept
                    dictType = (TawSystemDictType) list.iterator().next();
                } else {
                    // 为空，写入将部门名称设为未知联系人
                    dictType = new TawSystemDictType();
                    dictType.setDictName(Util.idNoName());
                }
                return dictType;
            }
        };

        TawSystemDictType dictType = null;
        try {
            dictType = (TawSystemDictType) getHibernateTemplate().execute(
                    callback);
        } catch (Exception e) {
            // 若有异常则抛出daoexception,加入DAoException是为了解藕，若抛出hibernateException，这样在换orm时，如ibatis，service就要换异常捕捉
            throw new DictDAOException(e);
        }
        return dictType.getDictName();
    }

    /**
     * 保存字典信息并返回dictId
     *
     * @param tawSystemDictType
     * @return String dictId
     * @author liqiuye 2007-11-14
     */
    public String saveTawSystemDictTypeReturnDictId(
            TawSystemDictType tawSystemDictType) {
        if ((tawSystemDictType.getId() == null)
                || (tawSystemDictType.getId().equals("")))
            getHibernateTemplate().save(tawSystemDictType);
        else
            getHibernateTemplate().saveOrUpdate(tawSystemDictType);

        return tawSystemDictType.getDictId();
    }

    /**
     * 根据 parentDictId 和 dictCode获取 TawSystemDictType
     *
     * @param tawSystemDictType
     * @param tawSystemDictCode
     * @return String dictId
     * @author liqiuye 2007-11-14
     */
    public TawSystemDictType getDictByDictType(String dictCode,
                                               String parentDictId) {
        String hql = " from TawSystemDictType dicttype where dicttype.dictCode='"
                + dictCode
                + "' and dicttype.parentDictId="
                + parentDictId
                + " order by dicttype.dictCode";
        List list = (ArrayList) getHibernateTemplate().find(hql);
        TawSystemDictType tawSystemDictType = new TawSystemDictType();
        if (list != null && list.size() > 0)
            tawSystemDictType = (TawSystemDictType) list.get(0);
        return tawSystemDictType;
    }

    /**
     * 根据 dictCode获取 TawSystemDictType
     *
     * @param tawSystemDictCode
     * @return String dictId
     * @author liqiuye 2007-11-14
     */
    public TawSystemDictType getDictByDictType(String dictCode) {
        String hql = " from TawSystemDictType dicttype where dicttype.dictCode='"
                + dictCode + "'   order by dicttype.dictCode";
        List list = (ArrayList) getHibernateTemplate().find(hql);
        TawSystemDictType tawSystemDictType = new TawSystemDictType();
        if (list != null && list.size() > 0)
            tawSystemDictType = (TawSystemDictType) list.get(0);
        return tawSystemDictType;
    }

    public boolean isCodeExist(String dictCode, String dictId) {
        boolean bool = false;
        List dictList = new ArrayList();
        String hql = " from TawSystemDictType dict where dict.dictCode='"
                + dictCode + "' and dict.dictId!='" + dictId + "'";
        dictList = getHibernateTemplate().find(hql);
        if (dictList.size() > 0) {
            bool = true;
        }
        return bool;
    }

    public String getDictIdByDictCode(String dictCode) {
        String hql = " from TawSystemDictType dicttype where dicttype.dictCode='"
                + dictCode + "' order by dicttype.dictCode";
        List list = (ArrayList) getHibernateTemplate().find(hql);
        TawSystemDictType tawSystemDictType = new TawSystemDictType();
        if (list != null && list.size() > 0)
            tawSystemDictType = (TawSystemDictType) list.get(0);
        return tawSystemDictType.getDictId();
    }

    public TawSystemDictType getDictType(int dictId, String dictType) throws SQLException {
        dictType = StaticMethod.getNodeName("SYSTEM.DICTTYPE." + dictType);
        String hql = "from TawSystemDictType dicttype "
                + "WHERE dicttype.dictId = '" + dictId
                + "'and dicttype.parentDictId = '" + dictType + "'";
        List list = (ArrayList) getHibernateTemplate().find(hql);
        TawSystemDictType tawSystemDictType = new TawSystemDictType();
        if (list != null && list.size() > 0)
            tawSystemDictType = (TawSystemDictType) list.get(0);
        return tawSystemDictType;

    }

    public TawSystemDictType getDictType(int dictId, int dictType) throws SQLException {
        String hql = "from TawSystemDictType dicttype "
                + "WHERE dicttype.dictId = '" + dictId
                + "'and dicttype.parentDictId = '" + dictType + "'";
        List list = (ArrayList) getHibernateTemplate().find(hql);
        TawSystemDictType tawSystemDictType = new TawSystemDictType();
        if (list != null && list.size() > 0)
            tawSystemDictType = (TawSystemDictType) list.get(0);
        return tawSystemDictType;

    }

    public String getDictIdByDictCode(String parentId, String dictCode) {
        String hql = " from TawSystemDictType dicttype where dicttype.parentDictId like '" + parentId + "%' and dicttype.dictCode='"
                + dictCode + "' order by dicttype.dictCode";
        List list = (ArrayList) getHibernateTemplate().find(hql);
        TawSystemDictType tawSystemDictType = new TawSystemDictType();
        if (list != null && list.size() > 0)
            tawSystemDictType = (TawSystemDictType) list.get(0);
        return tawSystemDictType.getDictId();
    }

    public List listDictInDictIds(String parentDictId, String dictIds) {
        return getHibernateTemplate().find(
                "from TawSystemDictType dicttype where dicttype.parentDictId='"
                        + parentDictId + "' and dictId in (" + dictIds + ")");
    }

    public TawSystemDictType getDictByDictName(String dictName,
                                               String parentDictId) {
        String hql = " from TawSystemDictType dicttype where dicttype.dictName='"
                + dictName
                + "' and dicttype.parentDictId="
                + parentDictId
                + " order by dicttype.dictCode";
        List list = (ArrayList) getHibernateTemplate().find(hql);
        TawSystemDictType tawSystemDictType = new TawSystemDictType();
        if (list != null && list.size() > 0)
            tawSystemDictType = (TawSystemDictType) list.get(0);
        return tawSystemDictType;
    }

}
