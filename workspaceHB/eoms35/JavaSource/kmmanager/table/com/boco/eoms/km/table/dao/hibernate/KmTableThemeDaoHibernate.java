package com.boco.eoms.km.table.dao.hibernate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.km.table.dao.KmTableThemeDao;
import com.boco.eoms.km.table.model.KmTableTheme;
import com.boco.eoms.km.table.util.KmTableThemeConstants;

/**
 * <p> Title:模型分类表 dao的hibernate实现 </p>
 * <p> Description:模型分类表 </p>
 * <p> Mon Mar 02 14:55:43 CST 2009 </p>
 *
 * @author 何胜利
 * @version 1.0
 */
public class KmTableThemeDaoHibernate extends BaseDaoHibernate implements
        KmTableThemeDao, ID2NameDAO {

    /**
     * 根据主键查询模型分类表
     *
     * @param id 主键
     * @return 返回某id的模型分类表
     */
    public KmTableTheme getKmTableTheme(final String id) {
        KmTableTheme kmTableTheme = (KmTableTheme) getHibernateTemplate().get(KmTableTheme.class, id);
        if (kmTableTheme == null) {
            kmTableTheme = new KmTableTheme();
        }
        return kmTableTheme;
    }

    /**
     * 保存模型分类表
     *
     * @param kmTableTheme 模型分类表
     */
    public void saveKmTableTheme(final KmTableTheme kmTableTheme) {
        if ((kmTableTheme.getId() == null) || (kmTableTheme.getId().equals(""))) {
            kmTableTheme.setIsUsed("0");
            getHibernateTemplate().save(kmTableTheme);
        } else {
            getHibernateTemplate().saveOrUpdate(kmTableTheme);
        }
    }

    /**
     * id转name
     *
     * @param id 一般为表中的主键
     * @return 返回主键对应的name(自定义)
     * @throws DictDAOException
     */
    public String id2Name(String id) throws DictDAOException {
        String themeName = "";
        if (id != null && !id.equals("")) {
            KmTableTheme kmTableTheme = this.getKmTableThemeByNodeId(id);
            if (kmTableTheme == null) {
                return "";
            } else {
                themeName = kmTableTheme.getThemeName();
            }
            while (kmTableTheme.getParentNodeId().length() > 3) {
                kmTableTheme = this.getKmTableThemeByNodeId(kmTableTheme.getParentNodeId());
                if (kmTableTheme == null) {
                    return themeName;
                } else {
                    themeName = kmTableTheme.getThemeName() + " -> " + themeName;
                }
            }
        }
        return themeName;
    }

    /**
     * 根据节点id查询模型分类表
     *
     * @param nodeId 节点id
     * @return 返回某节点id的对象
     */
    public KmTableTheme getKmTableThemeByNodeId(final String nodeId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String hql = " from KmTableTheme kmTableTheme where kmTableTheme.nodeId=?";
                Query query = session.createQuery(hql);
                query.setString(0, nodeId);
                List list = query.list();
                if (list.iterator().hasNext()) {
                    return (KmTableTheme) list.iterator().next();
                }
                return new KmTableTheme();
            }
        };
        return (KmTableTheme) getHibernateTemplate().execute(callback);
    }

    /**
     * 根据节点id删除模型分类表
     *
     * @param nodeId 节点id
     */
    public Integer removeKmTableThemeByNodeId(final String nodeId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer queryStr = new StringBuffer("update KmTableTheme kmTableTheme ");
                queryStr.append("set kmTableTheme.isDeleted='1' ");
                queryStr.append("where kmTableTheme.nodeId like ? ");

                Query query = session.createQuery(queryStr.toString());
                query.setString(0, nodeId + '%');
                int i = query.executeUpdate();
                return new Integer(i);
            }
        };
        return (Integer) getHibernateTemplate().execute(callback);
    }

    /**
     * 查询下一级节点
     *
     * @param parentNodeId 父节点id
     * @return 下级节点列表
     */
    public List getNextLevelKmTableThemes(final String parentNodeId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer queryStr = new StringBuffer("from KmTableTheme kmTableTheme ");
                queryStr.append("where kmTableTheme.parentNodeId=? ");
                queryStr.append("and kmTableTheme.isDeleted='0' ");
                queryStr.append("order by kmTableTheme.orderBy");

                Query query = session.createQuery(queryStr.toString());
                query.setString(0, parentNodeId);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 生成一个可用的节点id
     *
     * @param parentNodeId 父节点id
     * @param length       节点长度
     * @return
     */
    public String getUsableNodeId(final String parentNodeId, final int length) {
        String minNodeId = getUsableMinNodeId(parentNodeId, length);
        if (null == minNodeId || "".equals(minNodeId)) {
            minNodeId = KmTableThemeConstants.NODEID_DEFAULT_VALUE;
        }
        return minNodeId;
    }

    private String getUsableMinNodeId(final String parentNodeId, final int length) {
        String minUsableNodeId = "";
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer queryStr = new StringBuffer("select distinct(nodeId) ");
                queryStr.append("from KmTableTheme kmTableTheme ");
                queryStr.append("where kmTableTheme.nodeId like ? ");
                queryStr.append("and length(kmTableTheme.nodeId)=?");

                Query query = session.createQuery(queryStr.toString());
                query.setString(0, parentNodeId + '%');
                query.setInteger(1, length);
                return query.list();
            }
        };
        List list = (List) getHibernateTemplate().execute(callback);
        HashMap hm = new HashMap();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            String nodeId = it.next().toString();
            hm.put(nodeId, nodeId);
        }
        // 防止下面的while出现死循环,如果所有Id全部被占用则返回一个空值
        if (hm.size() >= Integer.parseInt(KmTableThemeConstants.NODEID_IF_MAXID)) {
            return minUsableNodeId;
        }
        long nodeIdVar = Long.valueOf(parentNodeId + KmTableThemeConstants.NODEID_NOSON).longValue();
        while (null != hm.get(String.valueOf(nodeIdVar))) {
            nodeIdVar = nodeIdVar + 1;
        }
        return String.valueOf(nodeIdVar);
    }

    /**
     * 查询所有子节点（按nodeId排序）
     *
     * @param parentNodeId 父节点id
     */
    public List getChildNodes(final String parentNodeId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer queryStr = new StringBuffer("from KmTableTheme kmTableTheme ");
                queryStr.append("where kmTableTheme.parentNodeId like ? ");
                queryStr.append("order by kmTableTheme.nodeId");

                Query query = session.createQuery(queryStr.toString());
                query.setString(0, parentNodeId + '%');
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 查询下一级节点 用户查询已经和模型绑定的主题分类
     *
     * @param parentNodeId 父节点id
     * @return 下级节点列表
     * @author zhangxb
     */
    public List getNextLevelShowThemes(final String parentNodeId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer queryStr = new StringBuffer("from KmTableTheme kmTableTheme ");
                queryStr.append("where kmTableTheme.isDeleted=0 ");
                queryStr.append("and kmTableTheme.parentNodeId=? ");
                if (parentNodeId.equals("1")) {
                    queryStr.append("and kmTableTheme.isUsed='1' ");
                    queryStr.append("and kmTableTheme.leaf='0' ");
                }
                queryStr.append("order by kmTableTheme.nodeId");

                Query query = session.createQuery(queryStr.toString());
                query.setString(0, parentNodeId);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 知识列表:查询第一层已经创建表的知识库分类 用户查询已经和模型绑定的主题分类
     *
     * @return 下级节点列表
     * @author zhangxb
     */
    public List getFirstCreateThemes() {
        StringBuffer queryStr = new StringBuffer("select kmTableTheme ");
        queryStr.append("from KmTableTheme kmTableTheme, KmTableGeneral kmTableGeneral ");
        queryStr.append("where kmTableGeneral.isCreated='1' ");
        queryStr.append("and kmTableGeneral.isDeleted='0' ");
        queryStr.append("and kmTableTheme.tableId=kmTableGeneral.id ");
        queryStr.append("and kmTableTheme.isDeleted='0' ");
        queryStr.append("and kmTableTheme.parentNodeId='1' ");
        queryStr.append("order by kmTableTheme.nodeId");

        return getHibernateTemplate().find(queryStr.toString());
    }

    /**
     * 根据是否已经使用字段查询第一级模型分类
     *
     * @param parentNodeId 父节点
     * @param isUsed       是否已经使用
     * @return
     */
    public List getFirstLevelByParentNodeIdAndIsUsed(final String parentNodeId, final String isUsed) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer queryStr = new StringBuffer("from KmTableTheme kmTableTheme ");
                queryStr.append("where kmTableTheme.parentNodeId=? ");
                queryStr.append("and kmTableTheme.isUsed=? ");
                queryStr.append("and kmTableTheme.isDeleted='0' ");
                queryStr.append("order by kmTableTheme.nodeId");

                Query query = session.createQuery(queryStr.toString());
                query.setString(0, parentNodeId);
                query.setString(1, isUsed);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }
}