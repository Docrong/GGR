/*
 * Created on 2007-8-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.prm.service.impl;

import java.lang.reflect.Method;
import java.util.Iterator;

import com.boco.eoms.prm.exceptions.PRMException;
import com.boco.eoms.prm.exceptions.PropertyNameIsNullException;
import com.boco.eoms.prm.model.P2P;
import com.boco.eoms.prm.service.IPojo2PojoService;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-6 17:25:50
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class Pojo2PojoServiceImpl extends IPojo2PojoService {

    /*
     * (non-Javadoc)
     * 
     * @see com.boco.eoms.prm.service.IPojo2PojoService#p2p(java.lang.Object,
     *      java.lang.Object, java.lang.String)
     */
    public void p2p(Object fromObj, Object toObj) throws PRMException {
        for (Iterator it = this.getValues().iterator(); it.hasNext();) {
            P2P p2p = (P2P) it.next();
            try {
                Method getterMethod = fromObj.getClass().getDeclaredMethod(
                        getterMethodName(p2p.getName()), new Class[] {});
                //取属性类型的class
                Class cls = Class.forName(p2p.getType());
                Method setterMethod = toObj.getClass().getDeclaredMethod(
                        setterMethodName(p2p.getToName()), new Class[] { cls });

                //TODO 这里为地址引用也即是浅复制，若get,set都是基本类型对象不会有问题，自定义类就会出现问题。
                Object obj = getterMethod.invoke(fromObj, new Object[] {});

                setterMethod.invoke(toObj, new Object[] { obj });

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                throw new PRMException(fromObj + "中没有"
                        + getterMethodName(p2p.getName()) + "方法或"
                        + setterMethodName(p2p.getName()) + "方法或"
                        + p2p.getType() + "not found");
            } catch (Exception e1) {
                throw new PRMException(fromObj + "中没有" + p2p.getName() + "或"
                        + toObj + "中没有" + p2p.getToName() + "方法");
            }
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.boco.eoms.prm.service.IPojo2PojoService#p2p(java.lang.Object,
     *      java.lang.String)
     */
    public Object p2p(Object formObj) throws PRMException {
        return null;
    }

    /**
     * 取getter的前缀名称，如getName应返回get
     * 
     * @return
     */
    private String getterMethodPrefixName() {
        return "get";
    }

    /**
     * setter的前缀名称，如setName应返回set
     * 
     * @return
     */
    private String setterMethodPrefixName() {
        return "set";
    }

    /**
     * 将首写字母大写
     * 
     * @param str
     *            字符串
     * @return abc返回Abc
     */
    private String first2upper(String str) throws PropertyNameIsNullException {
        if (str == null || "".equals(str.trim())) {
            throw new PropertyNameIsNullException("方法名为空");
        }
        String firstChar = str.substring(0, 1);
        return firstChar.toUpperCase() + str.substring(1);
    }

    /**
     * 取属性的getter方法名，如abc,得到getAbc
     * 
     * @param propertyName
     *            属性名
     * @return
     */
    private String getterMethodName(String propertyName)
            throws PropertyNameIsNullException {
        return getterMethodPrefixName() + first2upper(propertyName);
    }

    /**
     * 取属性的setter方法名，如abc，得到setAbc
     * 
     * @param propertyName
     *            属性名
     * @return
     */
    private String setterMethodName(String propertyName)
            throws PropertyNameIsNullException {
        return setterMethodPrefixName() + first2upper(propertyName);
    }

}
