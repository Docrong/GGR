<%@page contentType="text/html;charset=ISO8859_1" %>
<%@page import="java.util.*" %>
<%@page import="com.boco.eoms.resmanage.entity.*" %>
<%@page import="mcs.common.db.*,javax.servlet.http.*" %>
<%@include file="../power.jsp" %>
<%
    /**
     *@ E-DIS (�Ĵ�ʡ)
     *@ Copyright : (c) 2003
     *@ Company : BOCO.
     *@ �ĵ�����
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
        out.println("<script>alert('���Ѿ����ߣ������µ�½��');parent.location='../index.jsp';</script>");
    request.setCharacterEncoding("GBK");

    if (request.getParameter("SucMsg") != null) {
%>
<script>
    alert('�޸��ĵ��ɹ�!');
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
    <title>�ĵ�����</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body bgcolor="#eeeeee" text="#000000" class="listStyle">
<td align=center><font size=2>�����������->�ĵ�-><%=doctypeName%>�б���ʾ</font></td>
<form name="entity" method=POST action="editDocInsert.jsp">
    <table width='100%'>
        <tr bgcolor=#dddddd width='100%'>
            <td align=center></td>
            <td align=center><font size=2><B>�ĵ�˵��</B></font></td>
            <td align=center><font size=2><B>����</B></font></td>
            <td align=center><font size=2><B>��������</B></font></td>
            <td align=center><font size=2><B>�ĵ�����</B></font></td>
            <td align=center><font size=2><B>��ע</B></font></td>
        </tr>
        <tr>
            <%
                String strSql = "";
                if (cityFlag == 0)
                    strSql = "select a.pi_id,a.cc_discription,a.cc_data,'ȫʡ',b.cc_name,a.cc_memo,a.cc_name from asd_document a ,sys_ass_path b where a.fi_doc_type=b.pi_id and fi_doc_type=" + type + " order by a.pi_id";
                else
                    strSql = "select a.pi_id,a.cc_discription,a.cc_data,c.cc_name,b.cc_name,a.cc_memo,a.cc_name,a.fi_city from asd_document a ,sys_ass_path b,edm_city c where a.fi_doc_type=b.pi_id and a.fi_city=c.pi_id and fi_doc_type=" + type + " order by a.pi_id";
//out.println("strSql is: "+strSql);
                VectorRS rs = new VectorRS();
                rs.setPageCapacity(15);                        //ÿҳ��ʾ��¼���ݸ���
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
                    <a href="javascript:inputclick()"><img src="../images/button_add.gif" alt="�����ĵ�" border=0></a>
                </td>
                <td height=20 align=center width=60>
                    <a href="javascript:edit(entity)"><img src="../images/button_mot.gif" alt="�޸�ѡ�е��ĵ�" border=0></a>
                </td>
                <td height=20 align=center width=60>
                    <a href="javascript:Del(entity)"><img src="../images/button_del.gif" alt="ɾ��ѡ�е��ĵ�" border=0></a>
                </td>
                <td height=20 align=center width=60>
                    <%
                        if (rs.getRowCount() != 0) {
                    %>
                    <input type=checkbox value="" name='chkall' onclick='CheckAll(entity)'><font size=2 color=#000000
                                                                                                 face='Verdana, Arial, Helvetica, sans-serif'>ȫѡ</font></input>
                    <%
                        }
                    %>
                </td>
                    <% if (rs.getRowCount()>0){%>
                <td height=20 align=right><font size=2 color=#000000
                                                face='Verdana, Arial, Helvetica, sans-serif'>��<%=rs.getCurrentPageIndex()%>
                    ҳ / ��<%=rs.getPageCount()%>ҳ</font></td>
                <td height=20 align=center>
                    <%}%>
                    <%
                        if (rs.getCurrentPageIndex() != rs.getFirstPageId())
                            out.println("<font face=webdings color=#06b020>4</font><a href=editDocList.jsp?pageid=" + rs.getFirstPageId() + "&cityid=" + cityId + "&type=" + type + ">��ҳ</a>");
                    %>
                    <%
                        if (rs.getCurrentPageIndex() != rs.getFirstPageId())
                            out.println("<font face=webdings color=#06b020>4</font><a href=editDocList.jsp?pageid=" + rs.getPreviewPageId() + "&type=" + type + "&cityid=" + cityId + ">��һҳ</a>");
                    %>
                    <%
                        if (rs.getCurrentPageIndex() != rs.getLastPageId() && rs.getRowCount() > 15)
                            out.println("<font face=webdings color=#06b020>4</font><a href=editDocList.jsp?pageid=" + rs.getNextPageId() + "&type=" + type + "&cityid=" + cityId + ">��һҳ</a>");
                    %>
                    <%
                        if (rs.getCurrentPageIndex() != rs.getLastPageId() && rs.getRowCount() > 15)
                            out.println("<font face=webdings color=#06b020>4</font><a href=editDocList.jsp?pageid=" + rs.getLastPageId() + "&cityid=" + cityId + "&type=" + type + ">ĩҳ</a>");
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
        <input type=hidden name=cc_location value='�����������->�ĵ�'>
        <input type=hidden name=distabname value='�ĵ�'>
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
            if (confirm("��ȷ��Ҫɾ����Щ��Ŀ��")) {
                form.action = "editDocDel.jsp";
                form.submit();
            }
        } else {
            alert("�����ѡ��Ҫɾ������Ŀ");
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
        if (icheck > 1) alert("��һ��ֻ���޸�һ����Ŀ��");
        if (icheck == 0) alert("�����ѡ��һ���޸ĵ���Ŀ");
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
