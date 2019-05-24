package com.boco.eoms.commons.ui.taglib;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.*;
import java.util.*;

/**
 * <p>Title: nmmis</p>
 * <p>Description: table and applet</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: boco</p>
 * @author dudajiang
 * @version 1.0
 */

public class TextDate extends BodyTagSupport {
  private String value="";
  private String day="";
  private int disday=0;
  private Vector vdate = new Vector();
  private long realtime = 0;

  public TextDate() {
  }
  public String getValue() {
    return value;
  }
  public void setValue(String value) {
    this.value = value;
    try{
      if ((value != null)&&(value != "")){
            vdate = this.getVector(value);
            int _year = Integer.parseInt(vdate.get(0).toString());
            int _month = Integer.parseInt(vdate.get(1).toString())-1;
            int _day = Integer.parseInt(vdate.get(2).toString());
            Calendar _c = Calendar.getInstance();
            _c.set(_year, _month, _day);
            realtime = _c.getTimeInMillis();
        }else{
          Calendar _c = Calendar.getInstance();
          realtime = _c.getTimeInMillis();
        }
     }catch(Exception e){
       Calendar _c = Calendar.getInstance();
       realtime = _c.getTimeInMillis();
    }
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

  public int doEndTag() throws JspTagException {
      String ls_display = "";
      try {
        Calendar c;
        c=Calendar.getInstance();
        if ((value == null)||(value == "")){
          realtime = c.getTimeInMillis();
        }
        realtime += 86400000*disday;
        c.setTimeInMillis(realtime);
        int _yy = c.get(Calendar.YEAR);
        int _mm = c.get(Calendar.MONTH)+1;
        int _dd = c.get(Calendar.DATE);
        int _week = c.get(Calendar.DAY_OF_WEEK)-1;

        //ls_display += c.get(Calendar.YEAR)+"��";
        //ls_display += _mm+"��";
        //ls_display += _dd+"��";
        //ls_display += " ����"+_week;
        ls_display = _yy+"-"+_mm+"-"+_dd;

        pageContext.getOut().write(ls_display);
      }catch(Exception e){
      }
      return EVAL_PAGE;
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


}