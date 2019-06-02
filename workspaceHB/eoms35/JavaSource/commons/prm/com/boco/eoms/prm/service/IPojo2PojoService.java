/*
 * Created on 2007-8-6
 * 
 * TODO To change the template for this generated file go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.prm.service;

import java.util.List;

import com.boco.eoms.prm.exceptions.PRMException;

/**
 * <p>
 * Title:pojo ocpy pojo的service
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-6 17:25:28
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public abstract class IPojo2PojoService {
    private List values;

    /**
     * 设置p2p列表，主要是name=属性名，value=属性值
     * 
     * @param values
     */
    public void setValues(List values) {
        this.values = values;
    }

    /**
     * 取p2p列表
     * 
     * @return
     */
    public List getValues() {
        return values;
    }

    /**
     * 对象to对象之间的复制，通过beanId做关系的映射
     * 
     * @param formObj
     *            源object
     * @param beanId
     *            spring维护的映射配置文件id
     * @return 复制后的对象
     * @throws PRMException
     *             匹配出错的异常,配置出错的异常
     */
    public abstract Object p2p(Object formObj) throws PRMException;

    /**
     * 对象to对象之间的复制，通过beanId做关系的映射
     * 
     * @param fromObj
     *            源object
     * @param toObj
     *            复制后的对象
     * @param beanId
     *            spring维护的映射配置文件id
     * @throws PRMException
     *             匹配出错的异常,配置出错的异常
     */
    public abstract void p2p(Object fromObj, Object toObj) throws PRMException;
}
