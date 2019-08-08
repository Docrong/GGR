package com.boco.eoms.base.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.sql.Timestamp;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;

/**
 * This class is converts a java.util.Date to a String and a String to a
 * java.util.Date.
 *
 * <p>
 * <a href="DateConverter.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class DateConverter implements Converter {

    public Object convert(Class type, Object value) {
        if (value == null) {
            return null;
        } else if (type == Timestamp.class) {
            return convertToDate(type, value, DateUtil.getDateTimePattern());
        } else if (type == Date.class) {
            //FIXME 时间需确认
//			if(value instanceof String){
//				if(((String)value).length()>11)
//					return convertToDate(type, value, DateUtil.getEomsDatePattern());					
//			}
            return convertToDate(type, value, DateUtil.getEomsDatePattern());
            //return convertToDate(type, value, DateUtil.getDatePattern());
        } else if (type == String.class) {
            return convertToString(type, value);
        }

        throw new ConversionException("Could not convert "
                + value.getClass().getName() + " to " + type.getName());
    }

    protected Object convertToDate(Class type, Object value, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        if (value instanceof String) {
            try {
                if (StringUtils.isEmpty(value.toString())) {
                    return null;
                }

                Date date = df.parse((String) value);
                if (type.equals(Timestamp.class)) {
                    return new Timestamp(date.getTime());
                }
                return date;
            } catch (Exception pe) {
                pe.printStackTrace();
                throw new ConversionException("Error converting String to Date");
            }
        }

        throw new ConversionException("Could not convert "
                + value.getClass().getName() + " to " + type.getName());
    }

    protected Object convertToString(Class type, Object value) {

        if (value instanceof Date) {
            //FIXME 时间控件类型
            DateFormat df = new SimpleDateFormat(DateUtil.getDatePattern());
            //DateFormat df = new SimpleDateFormat(DateUtil.getEomsDatePattern());
            if (value instanceof Timestamp) {
                //df = new SimpleDateFormat(DateUtil.getDateTimePattern());
                df = new SimpleDateFormat(DateUtil.getEomsDatePattern());
            }

            try {
                return df.format(value);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ConversionException("Error converting Date to String");
            }
        } else {
            return value.toString();
        }
    }
}
