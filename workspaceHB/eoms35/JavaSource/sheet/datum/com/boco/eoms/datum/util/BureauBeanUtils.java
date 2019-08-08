package com.boco.eoms.datum.util;

import java.beans.*;
import java.lang.reflect.*;
import java.util.*;

import org.apache.commons.beanutils.*;
import com.boco.eoms.common.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */

public class BureauBeanUtils
        extends org.apache.commons.beanutils.BeanUtils {
    static XMLParse xml = XMLParse.getInstance();

    public BureauBeanUtils() {
        super();

    }

    /**
     * ��orig��dest��ͬ���Ե�value���Ƶ�dest��
     * ͬʱ������value���ַ������ֵ��ַ�ת�������罫DB�е�iso8859-1ת����page�е�GB2312��
     *
     * @param dest
     * @param orig
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void copyPropertiesFromDBToPage(Object dest,
                                                  Object orig) throws
            IllegalAccessException, InvocationTargetException {
        convert(dest, orig, 2);
    }

    public static void copyPropertiesFromBeanToDB(Object dest,
                                                  Object orig) throws
            IllegalAccessException, InvocationTargetException {
        convert(dest, orig, 4);
    }

    public static void copyPropertiesFromBeanToPage(Object dest, Object orig) throws
            IllegalAccessException, InvocationTargetException {
        convert(dest, orig, 1);
    }

    public static void copyPropertiesFromPageToDB(Object dest,
                                                  Object orig) throws
            IllegalAccessException, InvocationTargetException {
        convert(dest, orig, 3);
    }

    private static void convert(Object dest, Object orig,
                                int type) throws
            IllegalAccessException, InvocationTargetException {

        // Validate existence of the specified beans
        if (dest == null) {
            throw new IllegalArgumentException
                    ("No destination bean specified");
        }
        if (orig == null) {
            throw new IllegalArgumentException("No origin bean specified");
        }

        // Copy the properties, converting as necessary
        if (orig instanceof DynaBean) {
            DynaProperty origDescriptors[] =
                    ((DynaBean) orig).getDynaClass().getDynaProperties();
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                String destName = getDestRealName(name, type);
                if (!destName.equals("")) {
                    if (PropertyUtils.isWriteable(dest, destName)) {
                        Object value = ((DynaBean) orig).get(name);
                        copyProperty(dest, destName, subconvert(value, type));
                    }
                }
            }
        } else if (orig instanceof Map) {
            Iterator names = ((Map) orig).keySet().iterator();
            while (names.hasNext()) {
                String name = (String) names.next();
                String destName = getDestRealName(name, type);
                if (!destName.equals("")) {
                    if (PropertyUtils.isWriteable(dest, destName)) {
                        Object value = ((Map) orig).get(name);
                        copyProperty(dest, destName, subconvert(value, type));
                    }
                }
            }
        } else
            /* if (orig is a standard JavaBean) */ {
            PropertyDescriptor origDescriptors[] =
                    PropertyUtils.getPropertyDescriptors(orig);
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                if ("class".equals(name)) {
                    continue; // No point in trying to set an object's class
                }
                String destName = getDestRealName(name, type);
                if (!destName.equals("")) {
                    if (PropertyUtils.isReadable(orig, name) &&
                            PropertyUtils.isWriteable(dest, destName)) {
                        try {
                            Object value =
                                    PropertyUtils.getSimpleProperty(orig, name);
                            copyProperty(dest, destName, subconvert(value, type));
                        } catch (NoSuchMethodException e) {
                            ; // Should not happen
                        }
                    }
                }
            }
        }

    }

    private static void convert(Object orig, int type) throws
            IllegalAccessException, InvocationTargetException {

        // Validate existence of the specified beans
        if (orig == null) {
            throw new IllegalArgumentException("No origin bean specified");
        }

        // Copy the properties, converting as necessary
        if (orig instanceof DynaBean) {
            DynaProperty origDescriptors[] =
                    ((DynaBean) orig).getDynaClass().getDynaProperties();
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
            PropertyDescriptor origDescriptors[] =
                    PropertyUtils.getPropertyDescriptors(orig);
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                if ("class".equals(name)) {
                    continue; // No point in trying to set an object's class
                }
                if (PropertyUtils.isWriteable(orig, name)) {
                    try {
                        Object value =
                                PropertyUtils.getSimpleProperty(orig, name);
                        copyProperty(orig, name, subconvert(value, type));
                    } catch (NoSuchMethodException e) {
                        ; // Should not happen
                    }
                }
            }
        }

    }

    private static synchronized Object subconvert(Object obj, int type) {
        //ת������ 1��BeanToPage 2��DbToPage 3��PageToDb
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
            if (!(obj.getClass().getName().equals("java.lang.String"))) {
                return obj;
            }
            strorg = obj.toString().trim();
            switch (type) {
                case 1:
                    strdest = strorg;
                    break;
                case 2:
                    strdest = StaticMethod.strFromDBToPage(strorg);
                    break;
                case 3:
                    strdest = StaticMethod.strFromPageToDB(strorg);
                    break;
                case 4:
                    strdest = strorg;
                    break;
            }
            return strdest;
        }
    }

    private static synchronized String getDestRealName(String name, int type) {
        String retName = "";

        try {
            if ((type == 2) || (type == 1)) {
                //  retName = xml.getSysIdFromOkbId(okbSysName, name);
                retName = name;
            } else if ((type == 3) || (type == 4)) {
                // retName = xml.getOkbIdFromSysId(okbSysName, name);
                retName = name;
            }
        } catch (Exception e) {
            retName = "";
        }

        return retName;
    }
}
