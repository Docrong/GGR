<%@page contentType="text/html;charset=gb2312" %>
<%@page import="java.util.*" %>
<%@page import="com.boco.eoms.resmanage.entity.*" %>
<%@page import="com.boco.eoms.resmanage.entity.*,com.boco.eoms.common.util.*" %>
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

    request.setCharacterEncoding("GBK");

    String cityId = null;
    if (request.getParameter("cityid") != null)
        cityId = request.getParameter("cityid");
    else
        cityId = ug.getCity() + "";
    String sId = null;
    if (request.getParameter("id") != null)
        sId = request.getParameter("id");
    else
        sId = "2";
    String pi_id = null;
    if (request.getParameter("pi_id") != null)
        pi_id = request.getParameter("pi_id");

%>
<html>
<head>
    <title>修改资源</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body>
<form name="editform" action="editSave.jsp" method=post>
    <%
        LogOpt logopt = new LogOpt();
        entityoperate Entity = new entityoperate();
        syscolindex SysColIndex = new syscolindex();
        String tabcode = Entity.getTableName(sId);
        String devType = null;
        devType = logopt.getdevTypeById(tabcode, pi_id);
        //得到位置信息
        systabindex SysTab = new systabindex();
        Vector TabVect = new Vector();
        TabVect = Entity.getTabinfor(sId);
        String tabname = null;
        String cc_location = "";
        for (int i = 0; i < TabVect.size(); i++) {
            SysTab = (systabindex) TabVect.get(i);
            tabname = StaticMethod.dbNull2String(SysTab.getCc_name());
            cc_location = StaticMethod.dbNull2String(SysTab.getCc_location());
        }
        out.println("<tr class=td_label>");
        out.println("<td align=left width='50%'><font size=2>" + cc_location + "->" + tabname + "的修改</font></td>");
        Vector SysVect = new Vector();

        SysVect = Entity.getcolinfor(sId);

        int colNum = SysVect.size();                //列信息

        if (colNum != 0) {
            coldata colData = new coldata();

            out.println("<br><table width='100%' border=0 cellspacing=1>");
            int colorCounter = 0;
            for (int Col = 0; Col < SysVect.size(); Col++) {
                SysColIndex = (syscolindex) SysVect.get(Col);

                int ref_flag = SysColIndex.getCi_ref_flag();
                int ass_flag = SysColIndex.getCi_ass_flag();
                //========得到列的值-----
                String colValue = Entity.getColValue(sId, SysColIndex.getCc_code(), pi_id);

                if (colValue == null)
                    colValue = "";

                if (Col == 0) {
                    out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>" + "<input name=" + SysColIndex.getCc_code() + "   type=hidden value=" + colValue + "></input>" + "</font></td>");
                } else {
                    if (ass_flag != 1 && ass_flag != 2) {
                        colorCounter++;
                        out.println("<tr class='tr_show'>");
                        out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>" + StaticMethod.dbNull2String(SysColIndex.getCc_name()) + "</font></td>");
                    }
                    //println  input text
                    switch (ref_flag) {
                        case 0:
                            out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>" + "<input name=" + SysColIndex.getCc_code() + "   value=" + StaticMethod.dbNull2String(colValue) + "></input>" + "</font>");
                            if (SysColIndex.getCc_type().equalsIgnoreCase("date")) {
                                //out.println("日期格式:yyyy-mm-dd");
    %>
    <%
            }
            out.println("</td>");
            break;
        //drop down control
        case 1:
            //out.println("SysColIndex.getCc_code() is: "+SysColIndex.getCc_code());
            if (SysColIndex.getCc_code().equals("fi_deviceclass")) {
                //out.println("devType is: "+devType);
                if (sId.equals("10")) {
							/*out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>");
							out.println("<select name=fi_deviceclass>");
							out.println("<option  value=4>机房</option>");
							out.println("<option  value=550>局房</option>");
							out.println("</select></td>");
							*/
    %>
    <td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>
        <select name=fi_deviceclass>
            <option value=4>机房</option>
            <option value=550>局房</option>
        </select>
    </td>
    <%
                                } else {
                                    out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>");
                                    out.println("<select name=" + SysColIndex.getCc_code() + ">");
                                    Vector devT = new Vector();
                                    devT = logopt.getTabInforByType(sId);
                                    systabindex TabIndex = new systabindex();
                                    for (int i = 0; i < devT.size(); i++) {
                                        TabIndex = (systabindex) devT.get(i);
                                        if (devType.equals(Integer.toString(TabIndex.getPi_id())))
                                            out.println("<option  value=" + TabIndex.getPi_id() + " selected>" + StaticMethod.dbNull2String(TabIndex.getCc_name()) + "</option>");
                                        else
                                            out.println("<option  value=" + TabIndex.getPi_id() + ">" + StaticMethod.dbNull2String(TabIndex.getCc_name()) + "</option>");
                                    }
                                    out.println("</select>");
                                    out.println("</font></td>");
                                }
                                /*out.println("<input type=hidden value="+devType+" name="+SysColIndex.getCc_code()+"></input>");*/
                            }
                            if (SysColIndex.getCc_code().equals("fi_device")) {
                                if (sId.equals("8") || sId.equals("9")) {
                                    devType = "33";
                                }
                                out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>");
                                out.println("<select name=" + SysColIndex.getCc_code() + ">");
                                String TabName = Entity.getTableName(devType);
                                //out.println("TabName is:"+TabName);
                                Vector TabVec = new Vector();
                                TabClass tabclass = new TabClass();
                                TabVec = Entity.getTabVecByTabName(TabName, cityId);
                                //out.println("TabVec size is :"+TabVec.size());
                                for (int i = 0; i < TabVec.size(); i++) {
                                    tabclass = (TabClass) TabVec.get(i);
                                    if (colValue.equals(Integer.toString(tabclass.getPi_id())))
                                        out.println("<option  value=" + tabclass.getPi_id() + "  selected>" + StaticMethod.dbNull2String(tabclass.getCc_name()) + "</option>");
                                    else
                                        out.println("<option  value=" + tabclass.getPi_id() + ">" + StaticMethod.dbNull2String(tabclass.getCc_name()) + "</option>");
                                }
                                out.println("</select>");
                                out.println("</font></td>");
                            }
                            if (!SysColIndex.getCc_code().equals("fi_device") && !SysColIndex.getCc_code().equals("fi_deviceclass")) {
                                out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>");
                                out.println("<select name=" + SysColIndex.getCc_code() + ">");
                                refcoldata RefColData = new refcoldata();

                                Vector RefColVec = new Vector();
                                Vector RefColVecTemp = new Vector();


                                //得到参考列的id,name的value
                                RefColVec = Entity.getrefColValueByCity(Integer.toString(SysColIndex.getPi_id()), "0", cityId);
                                String refTableTemp = Entity.getrefTable(Integer.toString(SysColIndex.getPi_id()));
                                if (colValue.equals("")) {
                                    out.println("refcolvalue size is:" + RefColVec.size());
                                    for (int col = 0; col < RefColVec.size(); col++) {
                                        int idvalue = ((refcoldata) RefColVec.elementAt(col)).getPi_idvalue();
                                        String namevalue = ((refcoldata) RefColVec.elementAt(col)).getCc_namevalue();
                                        out.println("<option value=" + idvalue + ">" + StaticMethod.dbNull2String(namevalue) + "</option>");
                                    }
                                } else {
                                    if (RefColVec.size() != 0) {
                                        for (int j = 0; j < RefColVec.size(); j++) {
                                            int idvalue = ((refcoldata) RefColVec.elementAt(j)).getPi_idvalue();
                                            String namevalue = ((refcoldata) RefColVec.elementAt(j)).getCc_namevalue();
                                            if (Integer.parseInt(colValue) == idvalue) {
                                                out.println("<option  value=" + idvalue + "  selected>" + StaticMethod.dbNull2String(namevalue) + "</option>");
                                            } else {
                                                out.println("<option value=" + idvalue + ">" + StaticMethod.dbNull2String(namevalue) + "</option>");
                                            }
                                        }
                                    }
                                }
                                out.println("</select>");
                                out.println("</font></td>");
                            }
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
                        <td width=60 align=center>
                            <input type="button" name="b_001" value="保  存" alt="保存录入的数据"
                                   onclick="javascript:formsubmit()" class="clsbtn2"></td>
                        <td width=20></td>
                        <td width=60 align=center>
                            <input type="button" name="b_001" value="取  消" alt="取消录入的数据"
                                   onclick="javascript:clearinput()" class="clsbtn2"></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <input type="hidden" name="id" value=<%=sId%>></input>
    <input type="hidden" name="OptType" value='3'></input>
</form>
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
