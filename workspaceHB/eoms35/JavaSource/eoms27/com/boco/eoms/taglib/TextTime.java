package com.boco.eoms.taglib;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.*;
import java.util.*;

/**
 * <p>Title: nmmis</p>
 * <p>Description: table and applet</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: boco</p>
 *
 * @author dudajiang
 * @version 1.0
 */

public class TextTime extends BodyTagSupport {
    private String value = "";
    private String day = "";
    private String hour = "";
    private String min = "";
    private int disday = 0;
    private int dishour = 0;
    private int dismin = 0;
    private long realtime = 0;
    private Vector vdate = new Vector();
    private Vector vpredate = new Vector();
    private Vector vlastdate = new Vector();

    public TextTime() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        try {
            if ((value != null) && (value != "")) {
                vdate = this.getVector(value, " ");
                vpredate = this.getVector(vdate.get(0).toString(), "-");
                vlastdate = this.getVector(vdate.get(1).toString(), ":");
                int _year = this.getInt(vpredate.get(0).toString(), 2000);
                int _month = this.getInt(vpredate.get(1).toString(), 1);
                int _day = this.getInt(vpredate.get(2).toString(), 1);
                int _hour = this.getInt(vlastdate.get(0).toString(), 0);
                int _min = this.getInt(vlastdate.get(1).toString(), 0);
                int _second = this.getInt(vlastdate.get(2).toString(), 0);
                Calendar _c = Calendar.getInstance();
                _c.set(_year, _month - 1, _day, _hour, _min, _second);
                realtime = _c.getTimeInMillis();
            } else {
                Calendar _c = Calendar.getInstance();
                realtime = _c.getTimeInMillis();
            }
        } catch (Exception e) {
            Calendar _c = Calendar.getInstance();
            realtime = _c.getTimeInMillis();
        }
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
        try {
            disday = Integer.parseInt(day);
        } catch (Exception e) {
            disday = 0;
        }
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
        try {
            dishour = Integer.parseInt(hour);
        } catch (Exception e) {
            dishour = 0;
        }
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
        try {
            dismin = Integer.parseInt(min);
        } catch (Exception e) {
            dismin = 0;
        }
    }

    public int doEndTag() throws JspTagException {
        String ls_display = "";
        try {
            Calendar c;
            c = Calendar.getInstance();
            if ((value == null) || (value == "")) {
                realtime = c.getTimeInMillis();
            }
            realtime += 86400000 * disday;
            c.setTimeInMillis(realtime);
            int _mm = c.get(Calendar.MONTH) + 1;
            int _dd = c.get(Calendar.DATE);
            int _week = c.get(Calendar.DAY_OF_WEEK) - 1;

            //ls_display += c.get(Calendar.YEAR)+"年";
            //ls_display += _mm+"月";
            //ls_display += _dd+"日";
            //ls_display += " 星期"+_week;
            //ls_display += " "+c.get(Calendar.HOUR_OF_DAY)+"点";
            //ls_display += c.get(Calendar.MINUTE)+"分";
            //ls_display += c.get(Calendar.SECOND)+"秒";
            ls_display += c.get(Calendar.YEAR) + "-" + _mm + "-" + _dd + " ";
            ls_display += c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
            pageContext.getOut().write(ls_display);
        } catch (Exception e) {
        }
        return EVAL_PAGE;
    }

    private Vector getVector(String string, String tchar) {
        StringTokenizer token = new StringTokenizer(string, tchar);
        Vector vector = new Vector();
        if (!string.trim().equals("")) {
            try {
                while (token.hasMoreElements()) {
                    vector.add(token.nextElement().toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return vector;
    }

    private int getInt(String str, int i) {
        int ret = i;
        try {
            ret = Integer.parseInt(str);
        } catch (Exception e) {
            ret = i;
        }
        return ret;
    }

}