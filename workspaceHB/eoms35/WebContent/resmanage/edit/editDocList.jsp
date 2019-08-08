<%@page contentType="text/html;charset=ISO8859_1" %>
<%@page import="java.util.*" %>
<%@page import="com.boco.eoms.resmanage.entity.*" %>
<%@page import="mcs.common.db.*,javax.servlet.http.*" %>
<%@include file="../power.jsp" %>
<%
    /**
     *@ E-DIS (四川省)
     *@ Copyright : (c) 2003
     *@ Company : BOCO.
     *@ 文档管理
     *@author:wuzongxian 2003-08-15
     *@ version 1.0
     **/
%>
<%
    /*Cookie[] cookie = request.getCookies();
    for(int i = 0; i < cookie.length; i ++)
    {
        if("UserCookie".equals(cookie[i].getName()))
        {
            Cookie cookiet = cookie[i];
            out.println("user cookie is: "+cookiet.getValue());
        }
    }*/

    if (!bflag)
        out.println("<script>alert('您已经掉线，请重新登陆！');parent.location='../index.jsp';</script>");
    request.setCharacterEncoding("GBK");

    if (request.getParameter("SucMsg") != null) {
%>
<script>
    alert('修改文档成功!');
</script>
<%
    }

    int pageid = 1;
    if (request.getParameter("pageid") != null)
        pageid = Integer.parseInt(request.getParameter("pageid"));
    String type = null;
    if (request.getParameter("type") != null)
        type = request.getParameter("type");
    else
        type = "0";
//out.println("type is: "+type);
    String cityId = null;
    if (request.getParameter("cityid") != null)
        cityId = request.getParameter("cityid");
    else
        cityId = ug.getCity() + "";
    int cityFlag = 0;
    String docPath = "";
    Vector docVec = new Vector();
    RoomOpt roomopt = new RoomOpt();
    docVec = roomopt.getDocType(Integer.parseInt(type));
    AssDocPath docpath = new AssDocPath();
    docpath = (AssDocPath) docVec.get(0);
    cityFlag = docpath.getCi_cityflag();
    docPath = docpath.getCc_path();
    String doctypeName = docpath.getCc_name();
//out.println("cityFlag is: "+cityFlag);
    String sId = null;
    if (request.getParameter("id") != null)
        sId = request.getParameter("id");
    else
        sId = "202";
%>
<html>
<head>
    <title>文档管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body bgcolor="#eeeeee" text="#000000" class="listStyle">
<td align=center><font size=2>网络基础数据->文档-><%=doctypeName%>列表显示</font></td>
<form name="entity" method=POST action="editDocInsert.jsp">
    <table width='100%'>
        <tr bgcolor=#dddddd width='100%'>
            <td align=center></td>
            <td align=center><font size=2><B>文档说明</B></font></td>
            <td align=center><font size=2><B>年月</B></font></td>
            <td align=center><font size=2><B>所属城市</B></font></td>
            <td align=center><font size=2><B>文档类型</B></font></td>
            <td align=center><font size=2><B>备注</B></font></td>
        </tr>
        <tr>
            <%
                String strSql = "";
                if (cityFlag == 0)
                    strSql = "select a.pi_id,a.cc_discription,a.cc_data,'全省',b.cc_name,a.cc_memo,a.cc_name from asd_document a ,sys_ass_path b where a.fi_doc_type=b.pi_id and fi_doc_type=" + type + " order by a.pi_id";
                else
                    strSql = "select a.pi_id,a.cc_discription,a.cc_data,c.cc_name,b.cc_name,a.cc_memo,a.cc_name,a.fi_city from asd_document a ,sys_ass_path b,edm_city c where a.fi_doc_type=b.pi_id and a.fi_city=c.pi_id and fi_doc_type=" + type + " order by a.pi_id";
//out.println("strSql is: "+strSql);
                VectorRS rs = new VectorRS();
                rs.setPageCapacity(15);                        //每页显示记录数据个数
                rs.setRS(strSql);
                rs.setCurrentPageIndex(pageid);
                if (rs.getRowCount() >= 1) {
                    for (int i = 0; i < rs.getCurrentPageRowNum(); i++) {
                        String path = docPath + "/" + rs.getString(7);
                        if (cityFlag == 1)
                            path = docPath + "/" + rs.getString(8) + "/" + rs.getString(7);
                        out.println("<tr bgcolor=" + ((i % 2 == 0) ? "#eaeaea" : "#eeeeee") + "><td align=center><input type=checkbox name='pi_id' value=" + rs.getString(1) + "></td>");
                        out.println("<td align=center><font size=2><a href=" + path + ">" + rs.getString(2) + "</a></font></td>");
                        String temp = rs.getString(3);
                        if (temp == null)
                            temp = "";
                        out.println("<td align=center><font size=2>" + temp + "</font></td>");
                        out.println("<td align=center><font size=2>" + rs.getString(4) + "</font></td>");
                        out.println("<td align=center><font size=2>" + rs.getString(5) + "</font></td>");
                        temp = rs.getString(6);
                        if (temp == null)
                            temp = "";
                        out.println("<td align=center><font size=2>" + temp + "</font></td>");
                        rs.next();
                        out.println("</tr>");
                    }
                }
            %>
        </tr>
        <table width='100%'>
            <tr>
                <td height=20 align=center width=40></td>
                <td height=20 align=center width=60>
                    <a href="javascript:inputclick()"><img src="../images/button_add.gif" alt="增加文档" border=0></a>
                </td>
                <td height=20 align=center width=60>
                    <a href="javascript:edit(entity)"><img src="../images/button_mot.gif" alt="修改选中的文档" border=0></a>
                </td>
                <td height=20 align=center width=60>
                    <a href="javascript:Del(entity)"><img src="../images/button_del.gif" alt="删除选中的文档" border=0></a>
                </td>
                <td height=20 align=center width=60>
                    <%
                        if (rs.getRowCount() != 0) {
                    %>
                    <input type=checkbox value="" name='chkall' onclick='CheckAll(entity)'><font size=2 color=#000000
                                                                                                 face='Verdana, Arial, Helvetica, sans-serif'>全选</font></input>
                    <%
                        }
                    %>
                </td>
                    <% if (rs.getRowCount()>0){%>
                <td height=20 align=right><font size=2 color=#000000
                                                face='Verdana, Arial, Helvetica, sans-serif'>第<%=rs.getCurrentPageIndex()%>
                    页 / 共<%=rs.getPageCount()%>页</font></td>
                <td height=20 align=center>
                    <%}%>
                    <%
                        if (rs.getCurrentPageIndex() != rs.getFirstPageId())
                            out.println("<font face=webdings color=#06b020>4</font><a href=editDocList.jsp?pageid=" + rs.getFirstPageId() + "&cityid=" + cityId + "&type=" + type + ">首页</a>");
                    %>
                    <%
                        if (rs.getCurrentPageIndex() != rs.getFirstPageId())
                            out.println("<font face=webdings color=#06b020>4</font><a href=editDocList.jsp?pageid=" + rs.getPreviewPageId() + "&type=" + type + "&cityid=" + cityId + ">上一页</a>");
                    %>
                    <%
                        if (rs.getCurrentPageIndex() != rs.getLastPageId() && rs.getRowCount() > 15)
                            out.println("<font face=webdings color=#06b020>4</font><a href=editDocList.jsp?pageid=" + rs.getNextPageId() + "&type=" + type + "&cityid=" + cityId + ">下一页</a>");
                    %>
                    <%
                        if (rs.getCurrentPageIndex() != rs.getLastPageId() && rs.getRowCount() > 15)
                            out.println("<font face=webdings color=#06b020>4</font><a href=editDocList.jsp?pageid=" + rs.getLastPageId() + "&cityid=" + cityId + "&type=" + type + ">末页</a>");
                    %>
                </td>
                <td></td>
                <td></td>
        </table>
        <input type=hidden name=id value='<%=sId%>'>
        <input type=hidden name=cityid value='<%=cityId%>'>
        <input type=hidden name=type value='<%=type%>'>
        <input type=hidden name=cityflag value='<%=cityFlag%>'>
        <input type=hidden name=docPath value='<%=docPath%>'>
        <input type=hidden name=cc_location value='网络基础数据->文档'>
        <input type=hidden name=distabname value='文档'>
</form>
<script language="javascript">
    function inputclick() {
        entity.submit();
    }

    function Del(form) {
        var icheck = 0;
        for (var i = 0; i < form.elements.length; i++) {
            var e = form.elements[i];
            if (e.checked == true) icheck += 1;
        }
        if (icheck > 0) {
            if (confirm("你确定要删除这些项目吗？")) {
                form.action = "editDocDel.jsp";
                form.submit();
            }
        } else {
            alert("你必须选中要删除的项目");
        }
    }

    function edit(form) {
        var icheck = 0;
        var id = 0;
        for (var i = 0; i < form.elements.length; i++) {
            var e = form.elements[i];
            if (e.checked == true) {
                icheck += 1;
            }
        }
        if (icheck == 1) {
            form.action = "editDocUpdate.jsp";
            form.submit();
        }
        if (icheck > 1) alert("你一次只能修改一个项目！");
        if (icheck == 0) alert("你必须选择一个修改的项目");
    }

    function CheckAll(form) {
        for (var i = 0; i < form.elements.length; i++) {
            var e = form.elements[i];
            if (e.name != 'chkall')
                e.checked = form.chkall.checked;
        }
    }
</script>
</body>
</html>
