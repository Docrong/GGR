package com.boco.eoms.sheet.safetyproblem.dao;

import java.util.HashMap;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.IMainDAO;

public interface ISafetyProblemMainDAO extends IMainDAO {
    public abstract HashMap getSameDeptStartList(String s, Integer integer,
                                                 Integer integer1, Object obj) throws HibernateException;

    public abstract HashMap getDiffDeptStartList(String s, Integer integer,
                                                 Integer integer1, Object obj) throws HibernateException;

    public abstract Integer getSameDeptStartCount(String s, Object obj)
            throws HibernateException;

    public abstract Integer getDiffDeptStartCount(String s, Object obj)
            throws HibernateException;
}
