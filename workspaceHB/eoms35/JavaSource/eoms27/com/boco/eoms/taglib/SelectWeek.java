package com.boco.eoms.taglib;

/**
 * <p>Title: 星期下拉选择列表</p>
 * <p>Description: 选择不同星期，获取该周的起止时间</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: boco</p>
 *
 * @author not attributable cheng
 * @version 1.0
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;

import org.apache.struts.util.LabelValueBean;

public class SelectWeek
        extends TagSupport {

    public int doEndTag() throws JspTagException {
        return EVAL_PAGE;
    }

    public int doStartTag() {

        pageContext.setAttribute("WeekList", this.getWeekList());
        return SKIP_BODY;
    }

    /**
     * 获取一年周列表
     *
     * @return
     */
    public List getWeekList() {
        List list = new ArrayList();
        for (int i = 1; i < 54; i++) {
            list.add(new org.apache.struts.util.LabelValueBean(Integer.toString(i) +
                    "周", this.getFirstDayOfWeek(i) + "," + this.getLastDayOfWeek(i)));
        }
        return list;
    }

    /**
     * @param theWeek
     * @return
     * @see 获取周一日期
     */
    public String getFirstDayOfWeek(int theWeek) {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.WEEK_OF_YEAR, theWeek);
        cal.set(cal.DAY_OF_WEEK, 2);
        cal.set(cal.HOUR_OF_DAY, 0);
        cal.set(cal.MINUTE, 0);
        cal.set(cal.SECOND, 0);
        String fromTime = null;

        try {
            java.util.Date test = cal.getTime();
            java.text.SimpleDateFormat dtFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            fromTime = dtFormat.format(test);
            test = null;
            dtFormat = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cal = null;
        }

        return fromTime;
    }

    /**
     * @param theWeek
     * @return
     * @see 获取周日日期
     */
    public String getLastDayOfWeek(int theWeek) {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.WEEK_OF_YEAR, (theWeek + 1));
        cal.set(cal.DAY_OF_WEEK, 1);
        cal.set(cal.HOUR_OF_DAY, 23);
        cal.set(cal.MINUTE, 59);
        cal.set(cal.SECOND, 59);
        String toTime = null;

        try {
            java.util.Date test = cal.getTime();
            java.text.SimpleDateFormat dtFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            toTime = dtFormat.format(test);
            test = null;
            dtFormat = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cal = null;
        }
        return toTime;
    }

    ;

}
