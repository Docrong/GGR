package com.boco.eoms.common.util;

import org.apache.commons.beanutils.*;

import java.beans.IndexedPropertyDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.boco.eoms.common.util.StaticMethod;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company:
 * </p>
 *
 * @author not attributable
 * @version 1.0
 */

public class MyBeanUtils extends org.apache.commons.beanutils.BeanUtils {
    public MyBeanUtils() {
        super();

    }

    public static void populateBoco(Object bean, Map properties)
            throws IllegalAccessException, InvocationTargetException {

        // Do nothing unless both arguments have been specified
        if ((bean == null) || (properties == null)) {
            return;
        }

        // Loop through the property name/value pairs to be set
        Iterator names = properties.keySet().iterator();
        while (names.hasNext()) {

            // Identify the property name and value(s) to be assigned
            String name = (String) names.next();
            if (name == null) {
                continue;
            }
            Object value = properties.get(name);

            setProperty(bean, name, StaticMethod.dbNull2String(StaticMethod
                    .nullObject2String(value)));
        }

    }

    public static void populate(Object bean, Map properties)
            throws IllegalAccessException, InvocationTargetException {

        // Do nothing unless both arguments have been specified
        if ((bean == null) || (properties == null)) {
            return;
        }

        // Loop through the property name/value pairs to be set
        Iterator names = properties.keySet().iterator();
        while (names.hasNext()) {

            String name = (String) names.next();

            // Identify the property name and value(s) to be assigned
            if (name == null) {
                continue;
            }
            Object value = properties.get(name);

            try {
                Class clazz = PropertyUtils.getPropertyType(bean, name);

                if (null == clazz) {
                    continue;
                }
                String className = clazz.getName();

                if (className.equalsIgnoreCase("java.sql.Timestamp")) {
                    if (value.equals("")) {
                        continue;
                    }
                }
                setProperty(bean, name, value);
                ;
            } catch (NoSuchMethodException e) {
                continue;
            }
        }

    }

    /**
     * 把orig和dest相同属性的value复制到dest中
     * 同时包括了value中字符串部分的字符转换（例如将DB中的iso8859-1转换成page中的GB2312）
     *
     * @param dest
     * @param orig
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void copyPropertiesFromDBToPage(Object dest, Object orig)
            throws IllegalAccessException, InvocationTargetException {
        // convert(dest, orig, 2);
        // 不做转换
        convert(dest, orig, 4);
    }

    public static void copyPropertiesFromPageToDB(Object dest, Object orig)
            throws IllegalAccessException, InvocationTargetException {
        convert(dest, orig, 3);
    }

    public static void copyPropertiesFromDBToPage(Object orig)
            throws IllegalAccessException, InvocationTargetException {
        convert(orig, 2);
    }

    public static void copyPropertiesFromPageToDB(Object orig)
            throws IllegalAccessException, InvocationTargetException {
        convert(orig, 3);
    }

    private static void convert(Object dest, Object orig, int type)
            throws IllegalAccessException, InvocationTargetException {

        // Validate existence of the specified beans
        if (dest == null) {
            throw new IllegalArgumentException("No destination bean specified");
        }
        if (orig == null) {
            throw new IllegalArgumentException("No origin bean specified");
        }

        // Copy the properties, converting as necessary
        if (orig instanceof DynaBean) {
            DynaProperty origDescriptors[] = ((DynaBean) orig).getDynaClass()
                    .getDynaProperties();
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                if (PropertyUtils.isWriteable(dest, name)) {
                    Object value = ((DynaBean) orig).get(name);
                    copyProperty(dest, name, subconvert(value, type));
                }
            }
        } else if (orig instanceof Map) {
            Iterator names = ((Map) orig).keySet().iterator();
            while (names.hasNext()) {
                String name = (String) names.next();
                if (PropertyUtils.isWriteable(dest, name)) {
                    Object value = ((Map) orig).get(name);
                    copyProperty(dest, name, subconvert(value, type));
                }
            }
        } else
            /* if (orig is a standard JavaBean) */ {
            PropertyDescriptor origDescriptors[] = PropertyUtils
                    .getPropertyDescriptors(orig);
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                if ("class".equals(name)) {
                    continue; // No point in trying to set an object's class
                }
                if (PropertyUtils.isReadable(orig, name)
                        && PropertyUtils.isWriteable(dest, name)) {
                    try {
                        Object value = PropertyUtils.getSimpleProperty(orig,
                                name);
                        copyProperty(dest, name, subconvert(value, type));
                    } catch (NoSuchMethodException e) {
                        ; // Should not happen
                    }
                }
            }
        }

    }

    private static void convert(Object orig, int type)
            throws IllegalAccessException, InvocationTargetException {

        // Validate existence of the specified beans
        if (orig == null) {
            throw new IllegalArgumentException("No origin bean specified");
        }

        // Copy the properties, converting as necessary
        if (orig instanceof DynaBean) {
            DynaProperty origDescriptors[] = ((DynaBean) orig).getDynaClass()
                    .getDynaProperties();
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                Object value = ((DynaBean) orig).get(name);
                copyProperty(orig, name, subconvert(value, type));
            }
        } else if (orig instanceof Map) {
            Iterator names = ((Map) orig).keySet().iterator();
            while (names.hasNext()) {
                String name = (String) names.next();
                Object value = ((Map) orig).get(name);
                ((Map) orig).put(name, subconvert(value, type));
            }
        } else
            /* if (orig is a standard JavaBean) */ {
            PropertyDescriptor origDescriptors[] = PropertyUtils
                    .getPropertyDescriptors(orig);
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                if ("class".equals(name)) {
                    continue; // No point in trying to set an object's class
                }
                if (PropertyUtils.isWriteable(orig, name)) {
                    try {
                        Object value = PropertyUtils.getSimpleProperty(orig,
                                name);
                        copyProperty(orig, name, subconvert(value, type));
                    } catch (NoSuchMethodException e) {
                        ; // Should not happen
                    }
                }
            }
        }

    }

    private static synchronized Object subconvert(Object obj, int type) {
        // 转换类型 1：BeanToPage 2：DbToPage 3：PageToDb
        if (null == obj) {
            return null;
        } else {
            String strorg = "";
            String strdest = "";
            if (obj.getClass().getName().equals("java.sql.Timestamp")) {
                strorg = obj.toString();
                strdest = strorg.substring(0, strorg.lastIndexOf("."));
                return strdest;
            }
            if (!(obj.getClass().getName().equals("java.lang.String")))
                return obj;
            strorg = obj.toString();
            switch (type) {
                case 1:
                    strdest = StaticMethod.strFromBeanToPage(strorg);
                    break;
                case 2:
                    strdest = StaticMethod.strFromDBToPage(strorg);
                    break;
                case 3:
                    strdest = StaticMethod.strFromPageToDB(strorg);
                    break;
                // 不做转换，modified by leo
                case 4:
                    strdest = strorg == null ? "" : strorg;
            }
            return strdest;
        }
    }
}