<%@page contentType="text/html;charset=ISO8859_1" %>
<%@page import="java.util.*" %>
<%@page import="com.boco.eoms.resmanage.entity.*" %>
<%@page import="com.boco.eoms.resmanage.operator.*" %>
<%@page import="mcs.common.db.*" %>
<%@include file="../power.jsp" %>
<%
    /**
     *@ E-DIS (四川省)
     *@ Copyright : (c) 2003
     *@ Company : BOCO.
     *@ 显示资源列表信息
     *@ version 1.0
     **/
%>
<%
    String sId = null;
    if (request.getParameter("id") != null)
        sId = request.getParameter("id");
    else
        sId = "2";
    String pi_id = null;
    if (request.getParameter("pi_id") != null)
        pi_id = request.getParameter("pi_id");
    String cityId = null;
    if (request.getParameter("cityid") != null)
        cityId = request.getParameter("cityid");
    else
        cityId = ug.getCity() + "";
    String tabname = request.getParameter("distabname");
    String cc_location = "";
    cc_location = request.getParameter("cc_location");

%>
<html>
<head>
    <title>修改资源</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body>
<form name="editform" action="editSave.jsp">
    <%
        out.println("<tr class=td_label>");
        out.println("<td align=left width='50%'><font size=2>" + cc_location + "->" + tabname + "的修改</font></td>");
    %>

    <%
        Date date = new Date();
        String SysTime = mtools.CovDate(date);
        entityoperate Entity = new entityoperate();
        syscolindex SysColIndex = new syscolindex();

        Vector SysVect = new Vector();

        SysVect = Entity.getcolinfor(sId);

        int colNum = SysVect.size();                //列信息

        if (colNum != 0) {
            coldata colData = new coldata();

            out.println("<br><table bgcolor=#dddddd width='100%' border=0 cellspacing=1>");
            int colorCounter = 0;
            for (int Col = 0; Col < SysVect.size(); Col++) {
                SysColIndex = (syscolindex) SysVect.get(Col);

                int ref_flag = SysColIndex.getCi_ref_flag();
                int ass_flag = SysColIndex.getCi_ass_flag();
                int nul_flag = SysColIndex.getCi_nul_flag();
                int read_flag = SysColIndex.getCi_read_flag();
                //========得到列的值-----
                String colValue = Entity.getColValue(sId, SysColIndex.getCc_code(), pi_id);
                //out.println(SysColIndex.getCc_code()+ "value is: " +colValue+"&nbsp");
                int len = 0;
                String cc_type = SysColIndex.getCc_type();

                int pos1 = cc_type.indexOf("(");
                int pos2 = cc_type.indexOf(")");

                if (pos1 > 0 && pos2 > 0) {
                    len = Integer.parseInt(cc_type.substring(pos1 + 1, pos2));

                }
                String colValueTemp = colValue;
                if (colValue == null)
                    colValue = "";

                if (Col == 0) {
                    out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>" + "<input name=" + SysColIndex.getCc_code() + "   type=hidden value=" + colValue + "></input>" + "</font></td>");
                } else {
                    if (ass_flag != 1 && ass_flag != 2 && !SysColIndex.getCc_code().equals("date_badge") && read_flag != 1) {
                        colorCounter++;
                        out.println("<tr bgcolor=" + ((colorCounter % 2 == 0) ? "#eaeaea" : "#eeeeee") + ">");
                        out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>" + SysColIndex.getCc_name() + "</font></td>");
                    }
                    //println  input text
                    switch (ref_flag) {
                        case 0:
                            String strTemp = "<input type=text  name=" + SysColIndex.getCc_code() + " value=" + colValue + ">";
                            if (len > 0) {
                                strTemp = "<input type=text name=" + SysColIndex.getCc_code() + " maxlength=" + len + " value=" + colValue + ">";
                            }
                            if (SysColIndex.getCc_code().equals("date_badge")) {
                                strTemp = "<input type=hidden  name=" + SysColIndex.getCc_code() + " value=" + SysTime + ">";
                            }
                            if (read_flag == 1) {
                                strTemp = "<input type=hidden  name=" + SysColIndex.getCc_code() + "  value=" + colValue + ">";
                            }
                            if (nul_flag == 1) {
                                strTemp += "*必填";
                            }
                            out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>" + strTemp + "</font></td>");
                            //out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+"<input name="+SysColIndex.getCc_code()+"   value="+colValue+"></input>"+"</font></td>");
                            break;
                        //drop down control
                        case 1:
                            out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>");
                            out.println("<select name=" + SysColIndex.getCc_code() + ">");

                            refcoldata RefColData = new refcoldata();

                            Vector RefColVec = new Vector();
                            Vector RefColVecTemp = new Vector();


                            //得到参考列的id,name的value
                            RefColVec = Entity.getrefColValueByCity(Integer.toString(SysColIndex.getPi_id()), "0", cityId);

                            RefColVecTemp = Entity.getrefColValueByCity(Integer.toString(SysColIndex.getPi_id()), colValue, cityId);
                            if (SysColIndex.getCc_code().equals("fi_equipsupplier")) {
                                out.println("RefColVecTemp size is: " + RefColVecTemp.size());
                            }
                            //out.println("pi id is : " +SysColIndex.getPi_id());
                            //out.println("col values is :" +colValue);
                            String refTableTemp = Entity.getrefTable(Integer.toString(SysColIndex.getPi_id()));
					/*if (refTableTemp!=null)
					{
						if (!refTableTemp.equalsIgnoreCase("sys_sub_code"))
					     {
						  out.println("<option value=0>无</option>");
						 }
					}*/
                            if (colValueTemp == null) {
                                out.println("refcolvalue size is:" + RefColVec.size());
                                for (int col = 0; col < RefColVec.size(); col++) {
                                    int idvalue = ((refcoldata) RefColVec.elementAt(col)).getPi_idvalue();
                                    String namevalue = ((refcoldata) RefColVec.elementAt(col)).getCc_namevalue();
                                    out.println("<option value=" + idvalue + ">" + namevalue + "</option>");
                                }
                            } else {
                                for (int i = 0; i < RefColVec.size(); i++) {
                                    if (RefColVecTemp.size() != 0) {
                                        for (int j = 0; j < RefColVecTemp.size(); j++) {
                                            int idvalue = ((refcoldata) RefColVec.elementAt(i)).getPi_idvalue();

                                            int idvalueTemp =
                                                    ((refcoldata) RefColVecTemp.elementAt(j)).getPi_idvalue();


                                            String namevalue = ((refcoldata) RefColVecTemp.elementAt(j)).getCc_namevalue();


                                            if (idvalue == idvalueTemp) {
                                                out.println("<option  value=" + idvalue + "  selected>" + namevalue + "</option>");
                                            } else {
                                                namevalue = ((refcoldata) RefColVec.elementAt(i)).getCc_namevalue();
                                                out.println("<option        value=" + idvalue + ">" + namevalue + "</option>");
                                            }
                                        }
                                    } else {
                                        int idvalue = ((refcoldata) RefColVec.elementAt(i)).getPi_idvalue();
                                        String namevalue = ((refcoldata) RefColVec.elementAt(i)).getCc_namevalue();
                                        out.println("<option value=" + idvalue + ">" + namevalue + "</option>");

                                    }
                                }
                            }
                            //=======

                            out.println("</select>");

                            out.println("</font></td>");
                            break;
                        case 2:
                            out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>" + "<textarea name=" + SysColIndex.getCc_code() + " value=" + colValue + "></textarea>" + "</font></td>");
                            break;

                        default:
                            break;
                    }
                    if (ass_flag != 1 && ass_flag != 2) out.println("</tr>");

                }
            }
        } else
            out.println("<br><br><br><br><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>No Info at This Id : " + sId + "</font>");
    %>

    <br>
    <table width='100%'>
        <tr>
            <td align=center width='100%'>
                <table>
                    <tr>
                        <td width=60 align=center><a href="javascript:formsubmit()"><img
                                src="../images/button_submit.gif" alt="保存修改的数据" border=0></a></td>
                        <td width=60 align=center><a href="javascript:clearinput()"><img
                                src="../images/button_cancel.gif" alt="取消录入的数据" border=0></a></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <input type="hidden" name="id" value=<%=sId%>></input>
    <input type="hidden" name="cityid" value=<%=cityId%>></input>
    <input type="hidden" name="OptType" value='3'></input>
    <!-- <input type="submit" value="提交"></input>
     --></form>
<script language=javascript>
    function formsubmit() {
        editform.submit();
    }

    function clearinput() {
        editform.reset();
    }
</script>
</table>
</body>
</html>