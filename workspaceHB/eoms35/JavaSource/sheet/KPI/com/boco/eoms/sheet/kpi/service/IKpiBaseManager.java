package com.boco.eoms.sheet.kpi.service;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

public interface IKpiBaseManager {
    /***************************************************************************
     * 解析XML配置文件获取列名称(中,英),sql
     *
     * @return
     * @throws HibernateException
     */
    public List getXMLList(String xmlName) throws HibernateException;

    /**
     * 获取SQl查询结果集
     *
     * @return
     * @throws HibernateException
     */
    public List getQuerySheetByCondition(String[] hsql, Map actionForm, Map condition, Integer curPage,
                                         Integer pageSize, int[] aTotal, String queryType, String xmlName);


    public List getReportByDept(String[] hsql, Map actionForm, String filename) throws HibernateException;


    public void excel(List list, List colMap, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
