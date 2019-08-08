package com.boco.eoms.sheet.numberapply.dao;

import java.util.HashMap;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.numberapply.model.NumberApplyMscid;

public interface INumberApplyMscDAO {
    /**
     * 根据id获得MSCID信息
     *
     * @param id
     * @return NumberApplyMscid
     */

    public NumberApplyMscid getNumberApplyMscid(final String id) throws HibernateException;

    /**
     * 保存MSCID的单条记录
     *
     * @param NumberApplyMscid
     * @return void
     */

    public void saveNumberApplyMscid(NumberApplyMscid numberApplyMscid) throws HibernateException;

    /**
     * 删除单条记录
     *
     * @param NumberApplyMscid
     * @return void
     */

    public void delNumberApplyMscid(NumberApplyMscid numberApplyMscid) throws HibernateException;

    /**
     * 根据mainid获得该张工单在HLRID的所有信息
     *
     * @param mainid
     * @return HashMap
     */

    public HashMap getAllNumberApplyMscidByMainid(final String mainid, final Integer pageSize, final Integer curPage) throws HibernateException;


}
