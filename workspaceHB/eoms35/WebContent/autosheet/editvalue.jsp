<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="com.boco.eoms.autosheet.util.*"%>
<%@ page import="com.boco.eoms.db.util.*"%>
<%@ page import="com.boco.eoms.common.util.*"%>
<% RecordSet rs= new RecordSet();
   String sheet_id=request.getParameter("sheet_id");
   String sql="select * from taw_sheetvalue where sheet_id=" + sheet_id +" order by index1";
   rs.execute(sql);
   SheetValue shValue=new SheetValue(sheet_id);
   SheetAttribute shAttr= new SheetAttribute(sheet_id);
   CodeCommon comm=new CodeCommon();
   CodeTypeComsInfo codetype=   CodeTypeComsInfo.getInstance();
%>
<html>
<head>
<title>编辑表单输入框</title>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
</head>

<script src="<%=request.getContextPath()%>/css/common/js/comm.js"></script>
<script src="<%=request.getContextPath()%>/css/common/js/page.js"></script>
<script src="<%=request.getContextPath()%>/css/common/js/table.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/autosheet/control.js"></script>
<script language="javascript">
//设置表格样式
function window.onload(){
  //参数为你要定制的表格id
  setTableStyle(table1);
}
</script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
<body bgcolor="#FFFFFF" text="#000000">
  <p><b><font size="5"><center>编辑表单输入框</center></font></b></p>
<table cellspacing=0 cellpadding=0  border=0 width="30%" align="center">
<tr>

<td nowrap height="34"><a href="./sheetattr.jsp?sheetID=<%out.print(sheet_id);%>&reFactor=reFactor">增加输入框名称</a></td>
<td nowrap height="34"><a href="./sheetvalue.jsp?sheetID=<%out.print(sheet_id);%>&reFactor=reFactor">增加输入框</a></td>

</tr>
</table>
<table id=table1  cellspacing=0 cellpadding=0  border=1 width="100%" bordercolordark="#FFFFFF" bordercolorlight="#66CCFF" bgcolor=#F3F3F3 align="center">
      <thead>
      <tr class="SortTableTitle">
  <tr>
    <td>字段名</td>
    <td>所属部分</td>
    <td>顺序号</td>
    <td>表格是否换行</td>
    <td>是否为查询项</td>
    <td>所属属性</td>
    <td>单元格横对齐</td>
    <td>单元格纵对齐</td>
    <td>跨列宽</td>
    <td>跨行宽</td>
    <td>单元格宽</td>
    <td>单元格高</td>
    <td>单元格是否换行</td>
    <td>文字颜色</td>
    <td>输入框类型</td>
    <td>输入框长</td>
    <td>输入串最大长</td>
    <td>所属编码类别</td>
    <td>储存类型</td>
    <td>系统默认值</td>
    <td>数据属性</td>
    <td>修改</td>
    <td>删除</td>
  </tr>
  </thead>
  <%while(rs.next()){
    %>
 <tbody>
  <tr>
    <td>&nbsp;<%=rs.getString(3)%></td>
    <td>
  <%        String isattach="";
        if(rs.getString(4).equals("0"))
          isattach="表体";
        else if(rs.getString(4).equals("1"))
          isattach="表头";
        else if(rs.getString(4).equals("2"))
          isattach="表尾";
          out.print(isattach);%></td>
    <td>&nbsp;<%=rs.getString(5)%></td>
    <td>&nbsp;<%
        String newline="";
        if(rs.getString(6).trim().equals("0"))
          newline="否";
        else if(rs.getString(6).trim().equals("1"))
          newline="是";
          out.print(newline);%></td>

    <td>&nbsp;
       <%if(rs.getString(7).equals("1"))
            out.println("是");
          else
            out.println("否");
       %></td>
    <td nowrap>&nbsp;<%=shAttr.getAttrName(rs.getString(8))%></td>
    <td>&nbsp;<%=rs.getString(9)%></td>
    <td>&nbsp;<%         String valign="";
           if(rs.getString(10).trim().equals("0"))
             valign="居上";
           else if(rs.getString(10).trim().equals("1"))
             valign="居中";
           else if(rs.getString(10).trim().equals("2"))
             valign="居下";
          out.print(valign);%></td>
    <td>&nbsp;<%=rs.getString(11)%></td>
    <td>&nbsp;<%=rs.getString(12)%></td>
    <td nowrap>&nbsp;<%=rs.getString(13)%></td>
        <td nowrap>&nbsp;<%=rs.getString("height")%></td>

    <td>&nbsp;<%
              if(rs.getString("nowrap").equals("nowrap"))
                out.print("否");
              else
                out.print("是");%></td>
    <td>&nbsp;<%=rs.getString(16)%></td>
    <td>&nbsp;<%=comm.getInputType(StaticMethod.getIntValue(rs.getString(17),0))%></td>
    <td>&nbsp;<%=rs.getString(18)%></td>
    <td>&nbsp;<%=rs.getString(19)%></td>
    <td>&nbsp;<%=codetype.getTypeName(rs.getString(20))%></td>
    <td nowrap>&nbsp;<%=rs.getString(21)%></td>
    <td>&nbsp;<%=rs.getString("DEFAULTVAL")%></td>
    <td>&nbsp;<%=rs.getString("V_CTRL")%></td>
    <td nowrap><a href="./editValueDetail.jsp?value_id=<%=rs.getString(1)%>&sheet_id=<%=sheet_id%>">修改</a></td>
    <td nowrap><a href="<%=request.getContextPath()%>/sheetvalueservlet?flag=delete&value_id=<%=rs.getString(1)%>&sheet_id=<%=sheet_id%>" onClick = "javascript: return ConfirmDel();">删除</a></td>
  </tr>
   </tbody>
  <%}
%>
</table>
<p align=center><a href=" <%= request.getContextPath()%>/autosheet/index.jsp">返 回</a></p>
<%String url=request.getContextPath()+"/autosheet/editvalue.jsp?sheet_id="+sheet_id+"&type=value";
session.setAttribute("returnURL",url);
%>
</body>
</html>
