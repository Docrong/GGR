package com.boco.eoms.taglib;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.util.*;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.db.util.*;


/**
 * <p>Title: nmmis</p>
 * <p>Description: table and applet</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: boco</p>
 * @author dudajiang
 * @version 1.0
 */

public class SelectNMRegion extends BodyTagSupport {
  private String name;
  private String formName;
  private String value;
  public SelectNMRegion() {
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getFormName() {
    return formName;
  }
  public void setFormName(String formName) {
    this.formName = formName;
  }
  public String getValue() {
    return value;
  }
  public void setValue(String value) {
    this.value = value;
  }

  public int doEndTag() throws JspTagException {
        String ls_display = "";
        String nowvalue = value;
        try {
          RecordSet rs = new RecordSet();
           String SQL = "SELECT ZH_LABEL,NE_ID FROM TIC_NE_DEF WHERE NE_TYPE=10003 ORDER BY ZH_LABEL";
           rs.executeSql("BocoNM",SQL);
           String srcStr = "";
           String desStr = "";
           String valueStr = ","+value+",";
           while (rs.next()) {
             String now_id = ","+rs.getString("NE_ID")+",";
             if (valueStr.indexOf(now_id) == -1) {
               srcStr += " <option value=\""+rs.getString("NE_ID")+"\">";
               srcStr += rs.getString("ZH_LABEL");
               srcStr += " </option>";
             }else{
               desStr += " <option value=\""+rs.getString("NE_ID")+"\">";
               desStr += rs.getString("ZH_LABEL");
               desStr += " </option>";
            }
          }

          String selectSrcName = name+"SRC";
          String selectDesName = name+"DES";
          ls_display += " <table width=\"100%\">";
          ls_display += " <INPUT type=\"hidden\" name="+ name + " id=" + name + " value=\"" + nowvalue +"\">";
          ls_display += " <tr>";
          ls_display += " <td width=\"40%\" align=\"center\">";

          ls_display += " <select size=5 name=\""+selectSrcName+"\" style=\"width:100\" onclick=\"return "+name+"_single_move(this.form."+selectSrcName+",this.form."+selectDesName+")\">";
          ls_display += srcStr;
          ls_display += " </select>";
          ls_display += " </td>";
          ls_display += " <td width=\"20%\" align=\"center\" valign = \"center\">";
          ls_display += " <input type=\"button\" name=\"btnAddAll\" value=\">>\" onclick=\"return "+name+"_all_move(this.form."+selectSrcName+",this.form."+selectDesName+")\">";
          ls_display += " <p>";
          ls_display += " <input type=\"button\" name=\"btnAddAll\" value=\"<<\" onclick=\"return "+name+"_all_move(this.form."+selectDesName+",this.form."+selectSrcName+")\">";
          ls_display += " </td>";
          ls_display += " <td width=\"40%\" align=\"center\">";
          ls_display += " <select size=5 name=\""+selectDesName+"\" style=\"width:100\" onclick=\"return "+name+"_single_move(this.form."+selectDesName+",this.form."+selectSrcName+")\">";
          ls_display += desStr;
          ls_display += " </select></td></tr></table>";
          ls_display += " <SCRIPT  LANGUAGE=javascript>";
          ls_display += " function "+name+"_single_move(src,des) {";
          ls_display += " var sindex=src.selectedIndex;";
          ls_display += " var dlength=des.length;";
          ls_display += " if(sindex>=0){";
          ls_display += " var stext=src.options[sindex].text;";
          ls_display += " var svalue=src.options[sindex].value;";
          ls_display += " var tag=0;";
          ls_display += " for(i=0; i<dlength; i++){";
          ls_display += " if(svalue==des.options[i].value) {";
          ls_display += " tag=1; ";
          ls_display += " src.options[sindex]=null;";
          ls_display += " break;";
          ls_display += " }";
          ls_display += " }";
          ls_display += " if(tag==0) {";
          ls_display += " des.options[dlength]=new Option(stext,dlength);";
          ls_display += " des.options[dlength].value=svalue;";
          ls_display += " src.options[sindex]=null;";
          ls_display += " src.selectedIndex=-1;";
          ls_display += " }";
          ls_display += " }";
          ls_display += " var str = \"\";";
          ls_display += " for(i=0;i<window."+formName+"."+selectDesName+".length-1;i++)";
          ls_display += " str += window."+formName+"."+selectDesName+".options[i].value+\",\";";
          ls_display += " var ii = window."+formName+"."+selectDesName+".length;";
          ls_display += " if (ii>0)";
          ls_display += " str += window."+formName+"."+selectDesName+".options[ii-1].value;";

          ls_display += " if (str != \"\")";
          ls_display += " window."+formName+"."+name+".value = str;";
          ls_display += " else";
          ls_display += " window."+formName+"."+name+".value = \"-99\";";
          ls_display += " }";

          ls_display += " function "+name+"_all_move(src,des) {";
          ls_display += " var slength=src.length;";
          ls_display += " var dlength=des.length;";
          ls_display += " for(i=slength-1; i>=0; i--){";
          ls_display += " var stext=src.options[i].text;";
          ls_display += " var svalue=src.options[i].value;";
          ls_display += " des.options[dlength+slength-i-1]=new Option(stext,dlength);";
          ls_display += " des.options[dlength+slength-i-1].value=svalue;";
          ls_display += " src.options[i]=null;";
          ls_display += " }";
          ls_display += " var str = \"\";";
          ls_display += " for(i=0;i<window."+formName+"."+selectDesName+".length-1;i++)";
          ls_display += " str += window."+formName+"."+selectDesName+".options[i].value+\",\";";
          ls_display += " var ii = window."+formName+"."+selectDesName+".length;";
          ls_display += " if (ii>0)";
          ls_display += " str += window."+formName+"."+selectDesName+".options[ii-1].value;";

          ls_display += " if (str != \"\")";
          ls_display += " window."+formName+"."+name+".value = str;";
          ls_display += " else";
          ls_display += " window."+formName+"."+name+".value = \"-99\";";
          ls_display += " }";

          ls_display += " </SCRIPT>";
          pageContext.getOut().write(ls_display);
        }catch(Exception e){
        }
        return EVAL_PAGE;
    }

}
