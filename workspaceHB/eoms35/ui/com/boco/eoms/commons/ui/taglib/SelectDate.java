package com.boco.eoms.commons.ui.taglib;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.text.*;


/**
 * <p>Title: nmmis</p>
 * <p>Description: table and applet</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: boco</p>
 * @author dudajiang
 * @version 1.0
 */

public class SelectDate extends BodyTagSupport {
  private String name="";
  private String value="";
  private String formName="";
  private String day="";
  private int disday=0;
  private long realtime = 0;
  private Vector vdate = new Vector();
  private String flag = "";
  private int disflag = 0;
  private Calendar c;

  public SelectDate() {
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int doEndTag() throws JspTagException {
      String ls_display = "";
      String selectyearname = name+"year";
      String selectmonthname = name+"month";
      String selectdayname = name+"day";
      try {
        if ((value == null)||(value == "")){
          c=Calendar.getInstance();
        }
        c.set(Calendar.DATE, c.get(Calendar.DATE) + disday);
        String _yystr="",_mmstr="",_ddstr = "";
        int _yy = c.get(Calendar.YEAR);
        _yystr = _yy+"";
        int _mm = c.get(Calendar.MONTH)+1;
        _mmstr = _mm+"";
        if (_mm < 10)
          _mmstr = "0"+_mm;
        int _dd = c.get(Calendar.DATE);
        _ddstr = _dd+"";
        if (_dd < 10)
          _ddstr = "0"+_dd;
        String nowvalue = _yy+"-"+_mm+"-"+_dd;
        String nowvaluestr =  _yystr+"-"+_mmstr+"-"+_ddstr; 
        ls_display += "<INPUT type=\"hidden\" name="+ name + " id=" + name + " value=\"" + nowvaluestr +"\">";
        ls_display += "<SELECT size=1 name=\""+selectyearname+"\" onclick=\"return "+name+"_onclick()\">";
        for (int i=2000;i<2030;i++) {
          if (_yy == i){
            ls_display += "<OPTION value=\"" + i + "\" selected>" + i + "</OPTION>";
          }else{
            ls_display += "<OPTION value=\"" + i + "\">" + i + "</OPTION>";
          }
        }
        ls_display += "</SELECT>年";
        ls_display += "<SELECT size=1 name=\""+selectmonthname+"\" onclick=\"return "+name+"_onclick()\">";
        for (int i=1;i<13;i++) {
          if (_mm == i){
            ls_display += "<OPTION value=\"" + i + "\" selected>" + i + "</OPTION>";
          }else{
            ls_display += "<OPTION value=\"" + i + "\">" + i + "</OPTION>";
          }
        }
        ls_display += "</SELECT>月";
        ls_display += "<SELECT size=1 name=\""+selectdayname+"\" onclick=\"return "+name+"_onclick()\">";
        for (int i=1;i<32;i++) {
          if (_dd == i){
            ls_display += "<OPTION value=\"" + i + "\" selected>" + i + "</OPTION>";
          }else{
            ls_display += "<OPTION value=\"" + i + "\">" + i + "</OPTION>";
          }
        }
        ls_display += "</SELECT>日";
        ls_display += "<SCRIPT  LANGUAGE=javascript>";
        ls_display += "var "+formName+selectyearname+" = "+_yy+";";
        ls_display += "var "+formName+selectmonthname+" = "+_mm+";";
        ls_display += "var "+formName+selectdayname+" = "+_dd+";";
        ls_display += "var pre"+formName+selectyearname+" = "+_yy+";";
        ls_display += "var pre"+formName+selectmonthname+" = "+_mm+";";
        ls_display += "var pre"+formName+selectdayname+" = "+_dd+";";
        ls_display += "function "+name+"_onclick() {";
        ls_display += "var flag = "+disflag+";";
        ls_display += "var thisflag = 1;";
        ls_display += "var year = window."+formName+"."+selectyearname+".value;";
        ls_display += "var month = window."+formName+"."+selectmonthname+".value;";
        ls_display += "var day = window."+formName+"."+selectdayname+".value;";
        ls_display += "if (!((year % 4)==0) && (month==2) && (day==29))";
        ls_display += "{";
        ls_display += "thisflag = 0;";
        ls_display += "}";
        ls_display += "if ((month<=7) && ((month % 2)==0) && (day>=31))";
        ls_display += "{";
        ls_display += "thisflag = 0;";
        ls_display += "}";
        ls_display += "if ((month>=8) && ((month % 2)==1) && (day>=31))";
        ls_display += "{";
        ls_display += "thisflag = 0;";
        ls_display += "}";
        ls_display += "if ((month==2) && (day==30))";
        ls_display += "{";
        ls_display += "thisflag = 0;";
        ls_display += "}";
        ls_display += "if ((thisflag == 1)&&(flag != 0))";
        ls_display += "{";
        ls_display += "var init=new Date("+formName+selectyearname+","+formName+selectmonthname+","+formName+selectdayname+");";
        ls_display += "var now=new Date(year,month,day);";
        ls_display += "var initint = Date.parse(init.toString());";
        ls_display += "var nowint = Date.parse(now.toString());";
        ls_display += "var dis=nowint - initint;";
        ls_display += "var dis=dis*flag;";
        ls_display += "if (dis<0)";
        ls_display += "{thisflag=0;}";
        ls_display += "}";
        ls_display += "if (thisflag == 0)";
        ls_display += "{";
        ls_display += "window."+formName+"."+selectyearname+".value = pre"+formName+selectyearname+";";
        ls_display += "window."+formName+"."+selectmonthname+".value = pre"+formName+selectmonthname+";";
        ls_display += "window."+formName+"."+selectdayname+".value = pre"+formName+selectdayname+";";
        ls_display += "return false;";
        ls_display += "}";
        ls_display += "pre"+formName+selectyearname+"= year;";
        ls_display += "pre"+formName+selectmonthname+"= month;";
        ls_display += "pre"+formName+selectdayname+"= day;";
        ls_display += "var yearstr = year;";
        ls_display += "var monthstr = month;";
        ls_display += "var daystr = day;";
        ls_display += "if (month < 10)";
        ls_display += "monthstr = \"0\"+monthstr;";
        ls_display += "if (day < 10)";
        ls_display += "daystr = \"0\"+daystr;";
        ls_display += "window."+formName+"."+name+".value = yearstr +\"-\"+ monthstr +\"-\"+ daystr;";
        ls_display += "}";
        ls_display += "</SCRIPT>";
        pageContext.getOut().write(ls_display);
      }catch(Exception e){
      }
      return EVAL_PAGE;
  }

  public String getValue() {
    return value;
  }
  public void setValue(String value) {
    this.value = value;
    try{
      if ((value != null)&&(value != "")){
        c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date thisdate = dateFormat.parse(value);
        c.setTime(thisdate);
        }else{
          c = Calendar.getInstance();
        }
     }catch(Exception e){
       c = Calendar.getInstance();
    }
  }

  private Vector getVector(String string){
     StringTokenizer token = new StringTokenizer(string, "-");
     Vector vector = new Vector();
     if (!string.trim().equals("")) {
       try {
         while (token.hasMoreElements()) {
           vector.add(token.nextElement().toString());
         }
       }
       catch (Exception e) {
         e.printStackTrace();
       }
     }
     return vector;
   }
  public String getFormName() {
    return formName;
  }
  public void setFormName(String formName) {
    this.formName = formName;
  }
  public String getDay() {
    return day;
  }
  public void setDay(String day) {
    this.day = day;
    try{
      disday = Integer.parseInt(day);
    }catch(Exception e){
      disday = 0;
    }
  }
  public String getFlag() {
    return flag;
  }
  public void setFlag(String flag) {
    this.flag = flag;
    try{
      disflag = Integer.parseInt(flag);
    }catch(Exception e){
      disflag = 0;
    }
  }


}
