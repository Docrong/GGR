<%@page contentType="text/html;charset=ISO8859_1" %>
<%@page import="java.util.*" %>
<%@page import="com.boco.eoms.resmanage.entity.*" %>
<%@page import="mcs.common.db.*" %>
<%@page import="com.boco.eoms.resmanage.operator.*" %>
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

    String sId = null;
    if (request.getParameter("id") != null)
        sId = request.getParameter("id");
    else
        sId = "2";
    String tabname = request.getParameter("distabname");
    String cityId = null;
    if (request.getParameter("cityid") != null)
        cityId = request.getParameter("cityid");
    else
        cityId = ug.getCity() + "";
    String ErrMsg = null;
    if (request.getParameter("ErrMsg") != null)
        ErrMsg = request.getParameter("ErrMsg");
    if (ErrMsg != null) {
        out.println("<script>alert('" + ErrMsg + "');history.go(-2)</script>");
    }
%>
<html>
<head>
    <script language=javascript>

        function formsubmit() {
            editform.submit();
        }

        function clearinput() {
            editform.reset();
        }

        /*function isSelectCity(formName)//是否选择了城市
        {
            if (formName.fi_city.value=="0")
            {
                alert("你没有选择城市");
                formName.fi_city.options[0].selected=true;
                formName.fi_city.focus();
                return;
            }
        }*/
    </script>
    <title>添加资源</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
    <Script src="filter.js"></Script>
</head>
<body>
<form name="editform" action="editSave.jsp" method=post>
    <%
        out.println("<tr class=td_label>");
        out.println("<td align=left width='50%'><font size=2>网络资源  >" + tabname + "的添加</font></td>");
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
            /******************	构造内容	*****************/
            out.println("<br><table class='table_show' border=0 cellspacing=1 width='100%'>");
            int colorCounter = 0;
            for (int Col = 1; Col < SysVect.size(); Col++) {
                SysColIndex = (syscolindex) SysVect.get(Col);

                int ref_flag = SysColIndex.getCi_ref_flag();
                int ass_flag = SysColIndex.getCi_ass_flag();
                int nul_flag = SysColIndex.getCi_nul_flag();
                int len = 0;
                String cc_type = SysColIndex.getCc_type();

                int pos1 = cc_type.indexOf("(");
                int pos2 = cc_type.indexOf(")");

                if (pos1 > 0 && pos2 > 0) {
                    len = Integer.parseInt(cc_type.substring(pos1 + 1, pos2));

                }
                if (ass_flag != 1 && ass_flag != 2 && !SysColIndex.getCc_code().equals("date_badge")) {
                    colorCounter++;
                    out.println("<tr bgcolor=" + ((colorCounter % 2 == 0) ? "#eaeaea" : "#eeeeee") + ">");
                    out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>" + SysColIndex.getCc_name() + "</font></td>");
                }
                //println  input text
                switch (ref_flag) {
                    case 0:
                        String strTemp = "<input type=text  name=" + SysColIndex.getCc_code() + ">";
                        if (len > 0) {
                            strTemp = "<input type=text name=" + SysColIndex.getCc_code() + " maxlength=" + len + ">";
                        }
                        if (SysColIndex.getCc_code().equals("date_badge")) {
                            strTemp = "<input type=hidden  name=" + SysColIndex.getCc_code() + " value=" + SysTime + ">";
                        }
                        if (nul_flag == 1) {
                            strTemp += "*必填";
                        }
                        out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>" + strTemp + "</font></td>");
                        //out.println("<td align=center><font size=2>"+strTemp+"</font></td>");
                        break;
                    //drop down control
                    case 1:
                        //out.println("<select name='"+SysColIndex.getCc_code()+"'>");


                        String refTableTemp = Entity.getrefTable(Integer.toString(SysColIndex.getPi_id()));
					
                 
				   /*if(refTableTemp != null)
					{ 
					 if(refTableTemp.compareTo("sys_sub_code") == 0)
						{
							out.print("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><select name='"+SysColIndex.getCc_code()+"'>");
						}
						else
						{
							out.print("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><select name='"+SysColIndex.getCc_code()+"' onchange=isSelectCity(editform)><option value=0>无</option>");
						}
					}*/
                        out.print("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>");

                        out.println("<select name='" + SysColIndex.getCc_code() + "'>");

                        refcoldata RefColData = new refcoldata();
                        Vector RefColVec = new Vector();
                        //得到参考列的id,name的value
                        RefColVec = Entity.getrefColValue(Integer.toString(SysColIndex.getPi_id()), "0");
                        // out.println("RefColVec size is: "+RefColVec.size());
                        for (int i = 0; i < RefColVec.size(); i++) {
                            int idvalue = ((refcoldata) RefColVec.elementAt(i)).getPi_idvalue();
                            String namevalue = ((refcoldata) RefColVec.elementAt(i)).getCc_namevalue();
                            out.println("<option value=" + idvalue + ">" + namevalue + "</option>");
                        }
                        out.println("</select></font></td>");
                        //out.println("</font></td>");
                        break;
                    case 2:
                        out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><textarea name=" + SysColIndex.getCc_code() + "></textarea></font></td>");
                        break;
                    default:
                        break;
                }
                if (ass_flag != 1 && ass_flag != 2) out.println("</tr>");
            }
        } else
            out.println("<br><br><br><br><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>No Info at This Id : " + sId + "</font>");
    %>
    </table>
    <br>
    <table width='100%'>
        <tr>
            <td align=center width='100%'>
                <table>
                    <tr>
                        <td width=60 align=center><a href="javascript:formsubmit()"><img
                                src="../images/button_submit.gif" alt="保存录入的数据" border=0></a></td>
                        <td width=20></td>
                        <td width=60 align=center><a href="javascript:clearinput()"><img
                                src="../images/button_cancel.gif" alt="取消录入的数据" border=0></a></td>
                    </tr>
                </table>
            </td>
        </tr>
        <input type="hidden" name="id" value=<%=sId%>></input>
        <input type="hidden" name="OptType" value="1"></input>
    </table>
</form>
</body>
</html>