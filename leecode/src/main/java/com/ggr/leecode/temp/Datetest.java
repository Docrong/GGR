package com.ggr.leecode.temp;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Datetest {

    public static void main(String[] args) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            GregorianCalendar c = new GregorianCalendar();
            int hour = c.getTime().getHours();
            if (hour >= 8 && hour < 16)
                c.add(10, 168);
            else
            if (hour >= 16 && hour < 24)
            {
                c.add(5, 1);
                c.set(11, 10);
                c.set(12, 0);
            } else
            if (hour < 8)
            {
                c.set(11, 10);
                c.set(12, 0);
            }
            String datestr = dateFormat.format(c.getTime());
            System.out.println("sheetAcceptLimit:" + datestr);
//            map.put("sheetAcceptLimit", datestr);
//            return map;

    }
}
