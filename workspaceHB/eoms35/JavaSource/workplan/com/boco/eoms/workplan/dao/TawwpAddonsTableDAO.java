package com.boco.eoms.workplan.dao;

/**
 * <p>Title: 附加表DAO类</p>
 * <p>Description: 附加表的增删改以及查询操作</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 *
 * @author eoms
 * @version 1.0
 */

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.boco.eoms.common.dao.HibernateDAO;
import com.boco.eoms.db.hibernate.HibernateUtil;
import com.boco.eoms.workplan.model.TawwpAddonsTable;

public class TawwpAddonsTableDAO
        extends HibernateDAO {

    /**
     * 保存附加表
     *
     * @param _tawwpAddonsTable TawwpAddonsTable 附加表类
     * @throws Exception 操作异常
     */
    public void saveAddonsTable(TawwpAddonsTable _tawwpAddonsTable) throws
            Exception {
        this.save(_tawwpAddonsTable);
    }

    /**
     * 删除附加表
     *
     * @param _tawwpAddonsTable TawwpAddonsTable 附加表类
     * @throws Exception 操作异常
     */
    public void deleteAddonsTable(TawwpAddonsTable _tawwpAddonsTable) throws
            Exception {
        this.delete(_tawwpAddonsTable);
    }

    /**
     * 修改附加表
     *
     * @param _tawwpAddonsTable TawwpAddonsTable 附加表类
     * @throws Exception 操作异常
     */
    public void updateAddonsTable(TawwpAddonsTable _tawwpAddonsTable) throws
            Exception {
        this.update(_tawwpAddonsTable);
    }

    /**
     * 查询附加表信息
     *
     * @param id String 年度作业计划标识
     * @return TawwpYearPlan 年度作业计划类
     * @throws Exception 操作异常
     */
    public TawwpAddonsTable loadAddonsTable(String id) throws Exception {
        return (TawwpAddonsTable) this.load(id, TawwpAddonsTable.class);
    }

    /**
     * 查询所有附加表
     *
     * @return List 附加表类列表
     * @throws Exception 操作异常
     */

    public List listAddonsTable() throws Exception {
        Session s = HibernateUtil.currentSession();
        String hSql = "";
        hSql =
                "from TawwpAddonsTable as tawwpaddonstable";

        Query query = s.createQuery(hSql);
        query.setCacheable(true);
        return query.list();
    }

    /**
     * 查询符合模块的附加表
     *
     * @param _model String 模版编号
     * @return List 附加表类列表
     * @throws Exception 操作异常
     */
    public List listAddonsTable(String _model) throws Exception {
        Session s = HibernateUtil.currentSession();
        String hSql = "";
        hSql =
                "from TawwpAddonsTable as tawwpaddonstable where tawwpaddonstable.model='" +
                        _model + "' order by name";

        Query query = s.createQuery(hSql);
        query.setCacheable(true);
        return query.list();
    }


}
