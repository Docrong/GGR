package com.boco.eoms.commons.ui.taglib;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.text.SimpleDateFormat;
import com.boco.eoms.common.util.StaticMethod;

/**
 * <p>Title: nmmis</p>
 * <p>Description: table and applet</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: boco</p>
 * @author dudajiang
 * @version 1.0
 */

public class SelectTime
    extends BodyTagSupport {
  private String name;
  private String value;
  private String formName;
  private String day;
  private String hour;
  private String min;
  private int disday = 0;
  private int dishour = 0;
  private int dismin = 0;
  private int level = 0;
  private String timelevel = "0";
  private Calendar _c ;
  private SimpleDateFormat dateFormat = new SimpleDateFormat(
    "yyyy-MM-dd HH:mm:ss");

  public SelectTime() {
    _c = Calendar.getInstance();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
    try {
      Date date = dateFormat.parse(value);
      _c.setTime(date);
    }
    catch (Exception e) {
    }
  }

  public String getFormName() {
    return formName;
  }

  public void setFormName(String formName) {
    this.formName = formName;
  }

  public int doEndTag() throws JspTagException {
    String ls_display = "";
    String selectyearname = name + "year";
    String selectmonthname = name + "month";
    String selectdayname = name + "day";
    String selecthourname = name + "hour";
    String selectminutename = name + "minute";
    String selectsecondname = name + "second";
    String nodisplayBegin = "<DIV style=\"display:none\" >";
    String nodisplayEnd = "</DIV>";;

    try {
      if (StaticMethod.null2String(value).equals("")) {
        _c = Calendar.getInstance();
      }
      _c.add(Calendar.DATE,disday);
      _c.add(Calendar.HOUR,dishour);
      _c.add(Calendar.MINUTE,dismin);
      int _yy = _c.get(Calendar.YEAR);
      int _mm = _c.get(Calendar.MONTH) + 1;
      int _dd = _c.get(Calendar.DATE);
      int _hh = _c.get(Calendar.HOUR_OF_DAY);
      int _mi = _c.get(Calendar.MINUTE);
      int _ss = _c.get(Calendar.SECOND);
      String _yystr = "", _mmstr = "", _ddstr = "";
      _yystr = _yy + "";
      _mmstr = _mm + "";
      if (_mm < 10)
        _mmstr = "0" + _mm;
      _ddstr = _dd + "";
      if (_dd < 10)
        _ddstr = "0" + _dd;
      if (level == 1) {
        _mi = 0;
        _ss = 0;
      }
      else if (level == 2) {
        _yy = 0;
        _mm = 0;
        _dd = 0;
      }

      String nowvaluestr = _yystr + "-" + _mmstr + "-" + _ddstr + " " + _hh +
          ":" + _mi + ":" + _ss;
      ls_display += "<INPUT type=\"hidden\" name=" + name + " id=" + name +
          " value=\"" + nowvaluestr + "\">";
      if (level==2)
        ls_display += nodisplayBegin;
      ls_display += "<SELECT size=1 name=\"" + selectyearname +
          "\" onclick=\"return " + name + "_onclick()\">";
      for (int i = 2000; i < 2030; i++) {
        if (_yy == i) {
          ls_display += "<OPTION value=\"" + i + "\" selected>" + i +
              "</OPTION>";
        }
        else {
          ls_display += "<OPTION value=\"" + i + "\">" + i + "</OPTION>";
        }
      }

      ls_display += "</SELECT>��";
      ls_display += "<SELECT size=1 name=\"" + selectmonthname +
          "\" onclick=\"return " + name + "_onclick()\">";
      for (int i = 1; i < 13; i++) {
        if (_mm == i) {
          ls_display += "<OPTION value=\"" + i + "\" selected>" + i +
              "</OPTION>";
        }
        else {
          ls_display += "<OPTION value=\"" + i + "\">" + i + "</OPTION>";
        }
      }
      ls_display += "</SELECT>��";
      ls_display += "<SELECT size=1 name=\"" + selectdayname +
          "\" onclick=\"return " + name + "_onclick()\">";
      for (int i = 1; i < 32; i++) {
        if (_dd == i) {
          ls_display += "<OPTION value=\"" + i + "\" selected>" + i +
              "</OPTION>";
        }
        else {
          ls_display += "<OPTION value=\"" + i + "\">" + i + "</OPTION>";
        }
      }
      ls_display += "</SELECT>��";

      if (level==2)
        ls_display += nodisplayEnd;

      ls_display += "<SELECT size=1 name=\"" + selecthourname +
          "\" onclick=\"return " + name + "_onclick()\">";
      for (int i = 0; i < 24; i++) {
        if (_hh == i) {
          ls_display += "<OPTION value=\"" + i + "\" selected>" + i +
              "</OPTION>";
        }
        else {
          ls_display += "<OPTION value=\"" + i + "\">" + i + "</OPTION>";
        }
      }
      ls_display += "</SELECT>��";

      if (level==1)
        ls_display += nodisplayBegin;

      ls_display += "<SELECT size=1 name=\"" + selectminutename +
          "\" onclick=\"return " + name + "_onclick()\" >";
      for (int i = 0; i < 60; i++) {
        if (_mi == i) {
          ls_display += "<OPTION value=\"" + i + "\" selected>" + i +
              "</OPTION>";
        }
        else {
          ls_display += "<OPTION value=\"" + i + "\">" + i + "</OPTION>";
        }
      }
      ls_display += "</SELECT>��";
      ls_display += "<SELECT size=1 name=\"" + selectsecondname +
          "\" onclick=\"return " + name + "_onclick()\" >";
      for (int i = 0; i < 60; i++) {
        if (_ss == i) {
          ls_display += "<OPTION value=\"" + i + "\" selected>" + i +
              "</OPTION>";
        }
        else {
          ls_display += "<OPTION value=\"" + i + "\">" + i + "</OPTION>";
        }
      }
      ls_display += "</SELECT>��";
      if (level==1)
        ls_display += nodisplayEnd;
      ls_display += "<SCRIPT  LANGUAGE=javascript>";
      ls_display += "function " + name + "_onclick() {";
      ls_display += "var year = window." + formName + "." + selectyearname +
          ".value;";
      ls_display += "var month = window." + formName + "." + selectmonthname +
          ".value;";
      ls_display += "var day = window." + formName + "." + selectdayname +
          ".value;";
      ls_display += "var hour = window." + formName + "." + selecthourname +
          ".value;";
      ls_display += "var minute = window." + formName + "." + selectminutename +
          ".value;";
      ls_display += "var second = window." + formName + "." + selectsecondname +
          ".value;";
      ls_display += "if (!((year % 4)==0) && (month==2) && (day==29))";
      ls_display += "{";
      ls_display += "day = 1;";
      ls_display += "window." + formName + "." + selectdayname + ".value = 1;";
      ls_display += "}";
      ls_display += "if ((month<=7) && ((month % 2)==0) && (day>=31))";
      ls_display += "{";
      ls_display += "day = 1;";
      ls_display += "window." + formName + "." + selectdayname + ".value = 1;";
      ls_display += "}";
      ls_display += "if ((month>=8) && ((month % 2)==1) && (day>=31))";
      ls_display += "{";
      ls_display += "day = 1;";
      ls_display += "window." + formName + "." + selectdayname + ".value = 1;";
      ls_display += "}";
      ls_display += "if ((month==2) && (day==30))";
      ls_display += "{";
      ls_display += "day = 1;";
      ls_display += "window." + formName + "." + selectdayname + ".value = 1;";
      ls_display += "}";
      ls_display += "var yearstr = year;";
      ls_display += "var monthstr = month;";
      ls_display += "var daystr = day;";
      ls_display += "if (month < 10)";
      ls_display += "monthstr = \"0\"+monthstr;";
      ls_display += "if (day < 10)";
      ls_display += "daystr = \"0\"+daystr;";
      ls_display += "window." + formName + "." + name + ".value = yearstr +\"-\"+ monthstr +\"-\"+ daystr+ \" \" +hour+\":\"+minute+\":\"+second;";
      ls_display += "}";
      ls_display += "</SCRIPT>";
      pageContext.getOut().write(ls_display);
    }
    catch (Exception e) {
    }
    return EVAL_PAGE;
  }

  public String getDay() {
    return day;
  }

  public void setDay(String day) {
    this.day = day;
    try {
      disday = Integer.parseInt(day);
    }
    catch (Exception e) {
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
    }
    catch (Exception e) {
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
    }
    catch (Exception e) {
      dismin = 0;
    }
  }

  public String getTimelevel() {
    return timelevel;
  }

  public void setTimelevel(String timelevel) {
    this.timelevel = timelevel;
    try {
      level = Integer.parseInt(timelevel);
    }
    catch (Exception e) {
      level = 0;
    }

  }
}
