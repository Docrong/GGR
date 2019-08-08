package com.boco.eoms.taglib;

/**
 * <p>Title: 月份下拉选择列表</p>
 * <p>Description: 选择不同月份，获取该月份的其止时间</p>
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

public class SelectMonth
        extends TagSupport {

    public int doEndTag() throws JspTagException {
        return EVAL_PAGE;
    }

    public int doStartTag() {

        pageContext.setAttribute("MonthList", this.getMonthList());
        return SKIP_BODY;
    }

    /**
     * 获取month列表
     *
     * @return
     */
    public List getMonthList() {
        List list = new ArrayList();
        for (int i = 1; i < 13; i++) {
            list.add(new LabelValueBean(Integer.toString(i) +
                    "月", this.getFirstDayOfMonth(i) + "," + this.getLastDayOfMonth(i)));
        }
        return list;
    }

    /**
     * @param theMonth
     * @return
     * @see 获取月初日期
     */
    public String getFirstDayOfMonth(int theMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.MONTH, theMonth - 1);
        cal.set(cal.DATE, cal.getActualMinimum(cal.DAY_OF_MONTH));
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

    ;

    /**
     * @param theMonth
     * @return
     * @see 获取月末日期
     */
    public String getLastDayOfMonth(int theMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.MONTH, theMonth - 1);
        cal.set(cal.DATE, cal.getActualMinimum(cal.DAY_OF_MONTH)); //置月首
        cal.set(cal.DAY_OF_MONTH, cal.getActualMaximum(cal.DAY_OF_MONTH));
        cal.set(cal.HOUR_OF_DAY, 23);
        cal.set(cal.MINUTE, 59);
        cal.set(cal.SECOND, 59);
        String toTime = "";

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