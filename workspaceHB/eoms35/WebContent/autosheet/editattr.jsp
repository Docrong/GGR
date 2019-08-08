<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.boco.eoms.db.util.*" %>
<%@ page import="com.boco.eoms.common.util.*" %>
<%@ page import="com.boco.eoms.autosheet.util.*" %>
<% RecordSet rs = new RecordSet();
    String sheet_id = request.getParameter("sheet_id");
    String sql = "select * from taw_sheetattr where sheet_id=" + sheet_id + " order by index1";
    rs.execute(sql);
%>
<html>
<head>
    <title>编辑表单输入框名称</title>
    <meta http-equiv="Content-Type" content="text/html; charset=GB2312">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<script src="<%=request.getContextPath()%>/css/common/js/comm.js"></script>
<script src="<%=request.getContextPath()%>/css/common/js/page.js"></script>
<script src="<%=request.getContextPath()%>/css/common/js/table.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/autosheet/control.js"></script>
<script language="javascript">
    //设置表格样式
    function window

    .onload()
    {
        //参数为你要定制的表格id
        setTableStyle(table1);
    }
</script>

<body bgcolor="#FFFFFF" text="#000000">
<div align="center">
    <p><b><font size="5">编辑表单输入框名称</font></b></p>
    <table cellspacing=0 cellpadding=0 border=0 width="30%" align="center">
        <tr>
            <td nowrap height="34"><a
                    href="./sheetattr.jsp?sheetID=<%out.print(sheet_id);%>&reFactor=reFactor">增加输入框名称</a></td>
            <td nowrap height="34"><a
                    href="./sheetvalue.jsp?sheetID=<%out.print(sheet_id);%>&reFactor=reFactor">增加输入框</a></td>
        </tr>
    </table>
    <table id=table1 cellspacing=0 cellpadding=0 border=1 width="779" bordercolordark="#FFFFFF"
           bordercolorlight="#66CCFF" bgcolor=#F3F3F3 align="center">
        <thead>
        <tr class="SortTableTitle">
            <td nowrap height="34">输入框名称</td>
            <td nowrap height="34">所属部分</td>
            <td nowrap height="34">顺序号</td>
            <td height="34">表格是否换行</td>
            <td nowrap height="34">父节点</td>
            <td height="34">是否叶子节点</td>
            <td height="34">横对齐</td>
            <td height="34">纵对齐</td>
            <td nowrap height="34">跨列宽</td>
            <td nowrap height="34">跨行宽</td>
            <td nowrap height="34">显示宽度</td>
            <td height="34">显示高度</td>
            <td height="34">显示颜色</td>
            <td height="34">单元格是否换行</td>
            <td nowrap height="34">修改</td>
            <td nowrap height="34">删除</td>
        </tr>
        </thead>
        <%
            while (rs.next()) {

        %>
        <tbody>
        <tr>
            <td nowrap>&nbsp;<%out.print(StaticMethod.fromBaseEncoding(rs.getString(3)));%></td>
            <td>&nbsp;<%
                String isattach = "";
                if (rs.getString(4).equals("0"))
                    isattach = "表体";
                else if (rs.getString(4).equals("1"))
                    isattach = "表头";
                else if (rs.getString(4).equals("2"))
                    isattach = "表尾";
                out.print(isattach);%></td>
            <td>&nbsp;<%out.print(rs.getString(5));%></td>
            <td>&nbsp;<%
                String newline = "";
                if (rs.getString(6).trim().equals("0"))
                    newline = "否";
                else if (rs.getString(6).trim().equals("1"))
                    newline = "是";
                out.print(newline);%></td>

            <td>&nbsp;<%out.print(rs.getString(7));%></td>
            <td>&nbsp;<%
                String level = "";
                if (rs.getString(8).trim().equals("0"))
                    level = "否";
                else if (rs.getString(8).trim().equals("1"))
                    level = "是";
                out.print(level);%></td>
            <td nowrap>&nbsp;<%
                String align = "";
                if (rs.getString(9).trim().equals("left"))
                    align = "居左";
                else if (rs.getString(9).trim().equals("center"))
                    align = "居中";
                else if (rs.getString(9).trim().equals("right"))
                    align = "居右";
                out.print(align);%></td>
            <td nowrap>&nbsp;<%
                String valign = "";
                if (rs.getString(10).trim().equals("0"))
                    valign = "居上";
                else if (rs.getString(10).trim().equals("1"))
                    valign = "居中";
                else if (rs.getString(10).trim().equals("2"))
                    valign = "居下";
                out.print(valign);%></td>

            <td>&nbsp;<%out.print(rs.getString(11));%></td>
            <td>&nbsp;<%out.print(rs.getString(12));%></td>
            <td>&nbsp;<%out.print(rs.getString(13));%></td>
            <td>&nbsp;<%out.print(rs.getString(14));%></td>
            <td>&nbsp;<%out.print(rs.getString(16));%></td>
            <td>&nbsp;<%
                if (rs.getString(15).equals("nowrap"))
                    out.print("否");
                else
                    out.print("是");%></td>
            <td>
                <a href="./editAttributeDetail.jsp?attr_id=<%out.print(rs.getString(1));%>&sheet_id=<%out.print(sheet_id);%>">修改</a>
            </td>
            <td>
                <a href="<%=request.getContextPath()%>/sheetattrservlet?flag=delete&attr_id=<%=rs.getString(1)%>&sheet_id=<%=sheet_id%>"
                   onClick="javascript: return ConfirmDel();">删除</a></td>
        </tr>
        </tbody>
        <%}%>
    </table>
    <p align=center><a href=" <%= request.getContextPath()%>/autosheet/index.jsp">返 回</a></p>
    <%
        String url = request.getContextPath() + "/autosheet/editattr.jsp?sheet_id=" + sheet_id + "&type=attr";
        session.setAttribute("returnURL", url);
    %>

</div>
</body>
</html>
