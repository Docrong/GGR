package com.boco.eoms.taglib;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.db.util.BocoConnection;
import com.boco.eoms.db.util.ConnectionPool;

public class SelectReci extends BodyTagSupport {
    private String name;
    private String formName;
    private String value;
    private ConnectionPool pool = ConnectionPool.getInstance();

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
        BocoConnection conn = pool.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BocoConnection subconn = pool.getConnection();
        PreparedStatement subpstmt = null;
        ResultSet subRS = null;
        try {
            String SQL = "SELECT id,name FROM taw_eventdic WHERE parent_id = " + 1;
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            String srcStr = "";
            String desStr = "";
            String hiddenStr = "";
            String valueStr = value;
            srcStr += " <option value=\"-1\" selected>";
            srcStr += "专业类型";
            srcStr += " </option>";

            while (rs.next()) {
                int src_id = rs.getInt("id");
                String src_value = StaticMethod.dbNull2String(rs.getString("name"));
                String subStr = "SELECT id,name FROM taw_eventdic WHERE parent_id = ?";
                subpstmt = subconn.prepareStatement(subStr);
                subpstmt.setInt(1, src_id);
                subRS = subpstmt.executeQuery();
                String hiddentemp1 = "";
                String hiddentemp2 = "";
                String destemp = "";
                boolean findValue = false;
                while (subRS.next()) {
                    hiddentemp1 += subRS.getString("id") + ",";
                    hiddentemp2 += StaticMethod.dbNull2String(subRS.getString("name")) + ",";
                    destemp += "";
                    if (valueStr.equals(subRS.getString("id") + "")) {
                        destemp += " <option value=\"" + subRS.getString("DICT_ID") + "\" selected>";
                        destemp += StaticMethod.dbNull2String(subRS.getString("name"));
                        destemp += " </option>";
                        findValue = true;
                    } else {
                        destemp += " <option value=\"" + subRS.getString("id") + "\">";
                        destemp += StaticMethod.dbNull2String(subRS.getString("name"));
                        destemp += " </option>";
                    }
                }

                if (hiddentemp1.length() > 0) {
                    hiddentemp1 = hiddentemp1.substring(0, hiddentemp1.length() - 1);
                    hiddentemp2 = hiddentemp2.substring(0, hiddentemp2.length() - 1);
                    hiddenStr += " <INPUT type=\"hidden\" name=\"" + name + "_ID" +
                            src_id + "\" id=\"" + name + "_ID" + src_id + "\" value=\"" +
                            hiddentemp1 + "\">";
                    hiddenStr += " <INPUT type=\"hidden\" name=\"" + name + "_Value" +
                            src_id + "\" id=\"" + name + "_Value" + src_id + "\" value=\"" +
                            hiddentemp2 + "\">";
                    //取得两个select的option值
                    if (findValue) {
                        desStr = destemp;
                        srcStr += " <option value=\"" + src_id + "\" selected>";
                        srcStr += src_value;
                        srcStr += " </option>";
                    } else {
                        srcStr += " <option value=\"" + src_id + "\">";
                        srcStr += src_value;
                        srcStr += " </option>";
                    }
                }
            }

            String selectSrcName = name + "SRC";
            String selectDesName = name + "DES";
            ls_display += " <table width=\"100%\">";
            ls_display += hiddenStr;
            ls_display += " <INPUT type=\"hidden\" name=" + name + " id=" + name + " value=\"" + value + "\">";
            ls_display += " <tr>";
            ls_display += " <td width=\"50%\" align=\"center\">";

            ls_display += " 专业类别 :<select size=1 name=\"" + selectSrcName + "\" style=\"width:100\" onclick=\"return " + name + "_all_move(this.form." + selectSrcName + ",this.form." + selectDesName + ")\">";
            ls_display += srcStr;
            ls_display += " </select>";
            ls_display += " </td>";
            ls_display += " <td width=\"50%\" align=\"center\">";
            ls_display += " 网元类别 :<select size=1 name=\"" + selectDesName + "\" style=\"width:100\" onclick=\"return " + name + "_single_click(this.form." + selectDesName + ")\">";
            ls_display += desStr;
            ls_display += " </select></td></tr></table>";
            ls_display += " <SCRIPT  LANGUAGE=javascript>";
            ls_display += " function " + name + "_all_move(src,des) {";
            ls_display += " var sindex=src.selectedIndex;";
            ls_display += " var oindex=src[sindex].value;";
            ls_display += " var deslength=des.length;";
            ls_display += " if(src[sindex].value !=-1) {";
            ls_display += " if(sindex>=0){";
            ls_display += " for(i=deslength-1;i>0; i--)";
            ls_display += " des.options[i]=null;";
            ls_display += " id=\"" + name + "_ID\"" + "+oindex;";
            ls_display += " value=\"" + name + "_Value\"" + "+oindex;";
            ls_display += " var idstr = document." + formName + "[id].value;";
            ls_display += " var valuestr = document." + formName + "[value].value;";
            ls_display += " if (idstr!='') {";
            ls_display += " des_id = new Array();";
            ls_display += " des_value = new Array();";
            ls_display += " des_id = idstr.split(\",\");";
            ls_display += " des_value = valuestr.split(\",\");";
            ls_display += " var desnum=des_id.length;";
            ls_display += " for(i=0;i<desnum;i++) {";
            ls_display += " des.options[i]=new Option(des_value[i],i);";
            ls_display += " des.options[i].value=des_id[i];";
            ls_display += " }}}";
            ls_display += " window." + formName + "." + name + ".value = des.options[0].value;}";
            ls_display += " else {";
            ls_display += " for(i=deslength-1;i>=0; i--)";
            ls_display += " des.options[i]=null;";
            ls_display += " window." + formName + "." + name + ".value = '';";
            ls_display += " }";
            ls_display += " return false;";
            ls_display += " }";

            ls_display += " function " + name + "_single_click(src) {";
            ls_display += " if(src.selectedIndex!=-1) {";
            ls_display += " var slength=src.selectedIndex;";
            ls_display += " window." + formName + "." + name + ".value = src.options[slength].value;}";
            ls_display += " return false;";
            ls_display += " }";

            ls_display += " </SCRIPT>";
            pageContext.getOut().write(ls_display);
        } catch (Exception e) {
        } finally {
            conn.close();
            pstmt = null;
            rs = null;
            subconn.close();
            subpstmt = null;
            subRS = null;
        }

        return EVAL_PAGE;
    }

}
