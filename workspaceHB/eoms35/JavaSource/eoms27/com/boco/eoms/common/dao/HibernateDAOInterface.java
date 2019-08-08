/*
 * 创建日期 2004-7-1
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.boco.eoms.common.dao;

import java.util.List;

import org.hibernate.HibernateException;
import com.boco.eoms.common.oo.DataObject;

/**
 * @author Administrator
 * <p>
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public interface HibernateDAOInterface {
    public abstract Object load(String id, Class clazz)
            throws HibernateException;

    public abstract void save(Object object) throws HibernateException;

    public abstract void saveOrUpdate(Object object) throws HibernateException;

    public abstract void update(Object object) throws HibernateException;

    public abstract void delete(DataObject object) throws HibernateException;

    public abstract List query(String query) throws HibernateException;

    public abstract void delete(String query) throws HibernateException;
}