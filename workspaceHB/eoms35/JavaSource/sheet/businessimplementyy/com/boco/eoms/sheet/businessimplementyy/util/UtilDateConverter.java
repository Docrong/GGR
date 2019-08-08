package com.boco.eoms.sheet.businessimplementyy.util;

import org.apache.commons.beanutils.Converter;

import java.util.Date;
import java.text.*;

public class UtilDateConverter implements Converter {

    public Object convert(Class arg0, Object value) {
        // TODO Auto-generated method stub
        System.out.println("UtilDateConverter=" + value);
        if (value == null) {
            return value;
        }
        if (value instanceof Date) {
            return value;
        }
        Date date = null;
        if (value instanceof String) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = sdf.parse((String) value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

}
