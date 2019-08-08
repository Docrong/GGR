package com.boco.eoms.km.table.dao.hibernate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.km.table.dao.KmTableGeneralDao;
import com.boco.eoms.km.table.model.KmTableGeneral;
import com.boco.eoms.km.table.util.KmTableGeneralConstants;

/**
 * <p> Title:模型信息表 dao的hibernate实现 </p>
 * <p> Description:模型信息表 </p>
 * <p> Mon Mar 02 14:55:43 CST 2009 </p>
 *
 * @author 吕卫华
 * @version 1.0
 */
public class KmTableGeneralDaoHibernate extends BaseDaoHibernate implements
        KmTableGeneralDao, ID2NameDAO {

    /**
     * 取模型信息表列表
     *
     * @return 返回模型信息表列表
     */
    public List getKmTableGenerals() {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmTableGeneral kmTableGeneral where kmTableGeneral.isDeleted='0'";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 根据主键查询模型信息表
     *
     * @param id 主键
     * @return 返回某id的模型信息表
     */
    public KmTableGeneral getKmTableGeneral(final String id) {
        KmTableGeneral kmTableGeneral = (KmTableGeneral) getHibernateTemplate().get(KmTableGeneral.class, id);
        if (kmTableGeneral == null) {
            kmTableGeneral = new KmTableGeneral();
        }
        return kmTableGeneral;
    }

    /**
     * 保存模型信息表，并将与模型关联的知识分类设置为已使用
     *
     * @param kmTableGeneral 模型信息表
     */
    public void saveKmTableGeneral(final KmTableGeneral kmTableGeneral) {
        if ((kmTableGeneral.getId() == null) || (kmTableGeneral.getId().equals(""))) {
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session)
                        throws HibernateException {
                    // 1：保存模型信息表
                    session.save(kmTableGeneral);

                    // 2：将与模型定义绑定在一起的模型分类设置为已使用状态
                    StringBuffer queryStr = new StringBuffer("update from KmTableTheme kmTableTheme ");
                    queryStr.append("set kmTableTheme.isUsed='1', ");
                    queryStr.append("kmTableTheme.tableId=? ");
                    queryStr.append("where kmTableTheme.nodeId=?");

                    Query query = session.createQuery(queryStr.toString());
                    query.setString(0, kmTableGeneral.getId());
                    query.setString(1, kmTableGeneral.getThemeId());
                    query.executeUpdate();

                    return null;
                }
            };
            getHibernateTemplate().execute(callback);
        } else {
            getHibernateTemplate().saveOrUpdate(kmTableGeneral);
        }
    }

    /**
     * 根据模型id删除模型定义、模型字段，并解除与模型绑定的模型分类
     *
     * @param id 主键
     * @author zhangxb
     */
    public void removeKmTableGeneral(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                // 删除模型定义
                StringBuffer generalHql = new StringBuffer("update from KmTableGeneral kmTableGeneral ");
                generalHql.append("set kmTableGeneral.isDeleted='1' ");
                generalHql.append("where kmTableGeneral.id=?");
                Query query = session.createQuery(generalHql.toString());
                query.setString(0, id);
                query.executeUpdate();

                // 删除模型定义的所有字段
                StringBuffer columnHql = new StringBuffer("update from KmTableColumn kmTableColumn ");
                columnHql.append("set kmTableColumn.isDeleted='1' ");
                columnHql.append("where kmTableColumn.tableId=?");
                Query query2 = session.createQuery(columnHql.toString());
                query2.setString(0, id);
                query2.executeUpdate();

                // 解除与模型绑定的模型分类
                StringBuffer themeHql = new StringBuffer("update from KmTableTheme kmTableTheme ");
                themeHql.append("set kmTableTheme.isUsed='0', kmTableTheme.tableId='' ");
                themeHql.append("where kmTableTheme.tableId=?");
                Query query3 = session.createQuery(themeHql.toString());
                query3.setString(0, id);
                int ret = query3.executeUpdate();

                return new Integer(ret);
            }
        };
        getHibernateTemplate().execute(callback);
    }

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getKmTableGenerals(final Integer curPage, final Integer pageSize, final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer queryStr = new StringBuffer("from KmTableGeneral kmTableGeneral ");
                if (whereStr != null && whereStr.length() > 0)
                    queryStr.append(whereStr);
                else
                    queryStr.append("where kmTableGeneral.isDeleted='0' ");

                StringBuffer queryCountStr = new StringBuffer("select count(kmTableGeneral.id) ");
                queryCountStr.append(queryStr);

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
     * id转name
     *
     * @param id 一般为表中的主键
     * @return 返回主键对应的name(自定义)
     * @throws DictDAOException
     */
    public String id2Name(String id) throws DictDAOException {
        if (id == null || id.equals("")) {
            return "";
        }
        KmTableGeneral kmTableGeneral = this.getKmTableGeneral(id);
        if (kmTableGeneral == null) {
            return "";
        }
        return kmTableGeneral.getTableChname();
    }

    /**
     * 根据模型分类查询模型信息表
     *
     * @param themeId 模型分类
     * @return 返回模型信息
     */
    public KmTableGeneral getKmTableGeneralByThemeId(final String themeId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer queryStr = new StringBuffer("from KmTableGeneral kmTableGeneral ");
                queryStr.append("where kmTableGeneral.themeId=? ");
                queryStr.append("and kmTableGeneral.isDeleted='0'");

                Query query = session.createQuery(queryStr.toString());
                query.setString(0, themeId);
                List list = query.list();
                if (list.iterator().hasNext()) {
                    return (KmTableGeneral) list.iterator().next();
                }
                return new KmTableGeneral();
            }
        };
        return (KmTableGeneral) getHibernateTemplate().execute(callback);
    }

    /**
     * 查询所有已创建、并且处于开放状态的未删除案例库模型
     *
     * @return 返回模型信息表的列表
     * @author zhangxb
     */
    public List getOpenKmTableGenerals() {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer queryStr = new StringBuffer("from KmTableGeneral kmTableGeneral ");
                queryStr.append("where kmTableGeneral.isCreated='1' ");
                queryStr.append("and kmTableGeneral.isOpen='1' ");
                queryStr.append("and kmTableGeneral.isDeleted='0' ");
                queryStr.append("order by kmTableGeneral.orderBy");

                Query query = session.createQuery(queryStr.toString());
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 查询所有未删除案例库模型
     *
     * @return 返回模型信息表的列表
     * @author zhangxb
     */
    public Map getKmTableGenerals(final Integer curPage, final Integer pageSize) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer queryCountStr = new StringBuffer("select count(kmTableGeneral.id) ");
                queryCountStr.append("from KmTableGeneral kmTableGeneral ");
                queryCountStr.append("where kmTableGeneral.isDeleted='0'");

                StringBuffer queryStr = new StringBuffer("from KmTableGeneral kmTableGeneral ");
                queryStr.append("where kmTableGeneral.isDeleted='0' ");
                queryStr.append("order by kmTableGeneral.createTime desc, kmTableGeneral.tableChname ");

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
     * 根据模型主键查询某模型的所有未删除的字段
     *
     * @param id 主键
     * @return 返回某id的对象
     * @author zhangxb
     */
    public List getNextLevelKmTableGenerals(final String parentTableId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer queryStr = new StringBuffer("from KmTableColumn kmTableColumn ");
                queryStr.append("where kmTableColumn.tableId=? ");
                queryStr.append("and kmTableColumn.isDeleted='0' ");
                queryStr.append("order by kmTableColumn.orderBy");

                Query query = session.createQuery(queryStr.toString());
                query.setString(0, parentTableId);
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
            minNodeId = KmTableGeneralConstants.NODEID_DEFAULT_VALUE;
        }
        return minNodeId;
    }

    private String getUsableMinNodeId(final String parentNodeId, final int length) {
        String minUsableNodeId = "";
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer queryStr = new StringBuffer("select distinct(nodeId) ");
                queryStr.append("from KmTableGeneral kmTableGeneral ");
                queryStr.append("where kmTableGeneral.nodeId like ? ");
                queryStr.append("and length(kmTableGeneral.nodeId)=?");

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
        if (hm.size() >= Integer.parseInt(KmTableGeneralConstants.NODEID_IF_MAXID)) {
            return minUsableNodeId;
        }
        long nodeIdVar = Long.valueOf(parentNodeId + KmTableGeneralConstants.NODEID_NOSON).longValue();
        while (null != hm.get(String.valueOf(nodeIdVar))) {
            nodeIdVar = nodeIdVar + 1;
        }
        return String.valueOf(nodeIdVar);
    }

    /**
     * 根据模型主键查询所有字段类型是单选字典的字段
     *
     * @param tableId
     * @return List
     * @author zhangxb
     */
    public List getKmTableColumnsIsDictByTableId(final String tableId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer queryStr = new StringBuffer("from KmTableColumn kmTableColumn ");
                queryStr.append("where kmTableColumn.tableId=? ");
                queryStr.append("and kmTableColumn.colType=5 ");
                queryStr.append("and kmTableColumn.isDeleted=0 ");
                queryStr.append("order by kmTableColumn.orderBy");

                Query query = session.createQuery(queryStr.toString());
                query.setString(0, tableId);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 根据模型主键查询所有未创建的有效字段
     *
     * @param tableId
     * @return List
     * @author zhangxb
     */
    public List getKmTableColumnsUnVisiblByTableId(final String tableId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer queryStr = new StringBuffer("from KmTableColumn kmTableColumn ");
                queryStr.append("where kmTableColumn.tableId=? ");
                queryStr.append("and kmTableColumn.isVisibl=0 ");
                queryStr.append("and kmTableColumn.isDeleted=0 ");
                queryStr.append("order by kmTableColumn.orderBy");

                Query query = session.createQuery(queryStr.toString());
                query.setString(0, tableId);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 根据模型主键将所有未创建的有效字段跟新为已创建
     *
     * @param tableId
     * @author zhangxb
     */
    public void upKmTableColumnsIsVisiblByTableId(final String tableId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer queryStr = new StringBuffer("update KmTableColumn kmTableColumn ");
                queryStr.append("set kmTableColumn.isVisibl=1 ");
                queryStr.append("where kmTableColumn.tableId=? ");
                queryStr.append("and kmTableColumn.isVisibl=0 ");
                queryStr.append("and kmTableColumn.isDeleted=0");

                Query query = session.createQuery(queryStr.toString());
                query.setString(0, tableId);
                int i = query.executeUpdate();
                return new Integer(i);
            }
        };
        getHibernateTemplate().execute(callback);
    }

    /**
     * 执行创建模型的SQL
     *
     * @param createSql 创建模型的SQL
     */
    public void createModel(String createSql) {
        Session s = this.getSessionFactory().getCurrentSession();
        try {
            s.connection().createStatement().execute(createSql);
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行修改模型的SQL
     *
     * @param list 修改模型的SQL列表
     */
    public void modifyModel(List sqlList) {
        Session s = this.getSessionFactory().getCurrentSession();
        Connection con = s.connection();
        int length = sqlList.size();
        for (int i = 0; i < length; i++) {
            try {
                con.createStatement().execute(sqlList.get(i).toString());
            } catch (HibernateException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断一个表是否已经创建
     *
     * @param tableName 表名
     */
    public boolean HasTable(String tableName) {
        Session s = this.getSessionFactory().getCurrentSession();
        Connection conn = s.connection();
        boolean result = false;
        try {
            StringBuffer queryStr = new StringBuffer("select id from ");
            queryStr.append(tableName);
            queryStr.append(" where 1=0");

            conn.createStatement().execute(queryStr.toString());
            result = true;
        } catch (Exception ex) {
            result = false;
        }
        return result;
    }

    /**
     * 判断表的某个字段是否存在
     *
     * @param tableName  表名
     * @param columnName 字段名
     */
    public boolean HasColumn(String tableName, String columnName) {
        Session s = this.getSessionFactory().getCurrentSession();
        Connection conn = s.connection();
        boolean result = false;
        try {
            StringBuffer queryStr = new StringBuffer("select ");
            queryStr.append(columnName);
            queryStr.append(" from ");
            queryStr.append(tableName);
            queryStr.append(" where 1=0");

            conn.createStatement().execute(queryStr.toString());
            result = true;
        } catch (Exception ex) {
            result = false;
        }
        return result;
    }
}