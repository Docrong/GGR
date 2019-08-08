package com.boco.eoms.datum.dao;

import java.io.Serializable;
import java.util.*;

import org.hibernate.HibernateException;


public interface ITawBureaudataApplyDao {

    public List loadList(String hql) throws HibernateException;

    public void saveOrUpdate(Object o) throws HibernateException;
}
