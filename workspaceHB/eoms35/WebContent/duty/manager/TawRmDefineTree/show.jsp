<%@ page contentType="text/html; charset=gb2312"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.duty.model.*"%>
<html>
<head>
<title>table</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
<!---->

</head>
<body leftmargin="0" topmargin="0" class="clssclbar">
<%
String roomId = String.valueOf(request.getAttribute("roomId"));

Vector vecDefinetable = (Vector) request.getAttribute("vecDefinetable");
String roomName=(String)request.getAttribute("roomName");
if(roomName==null)
   roomName=request.getParameter("roomName");
%>
<form id = = "form" name = "form" method="post" action="save.do">
<input type="hidden" name="roomName" value="<%=roomName%>">
  <table border="0"  cellspacing="1" cellpadding="1" width="684" height="19" class="table_show" align="center" bgColor="#f3f3f3">
    <tr class="tr_show">
      <td class="clsscd1" align="center" colspan="4">
        &nbsp;编辑自定义值班记录类别
      </td>
    </tr>
    <tr class="tr_show">
      <td class="clsfth" width="59" height="19">
        <p align="center">机房</td>
      <td class="clsfth" width="205" height="1">
          <p align="center">标题
      </td>
      <td class="clsfth" width="166" height="1" colspan="2">
        <p align="center">操作</td>
    </tr>
<%
int size=vecDefinetable.size();
if(size==0)
{
%>
   <tr class="tr_show">
      <td class="clsfth" width="59" height="30" colspan="4"><%=roomName%></td>
    </tr>
   <tr class="tr_show">
      <td class="clsfth" width="59" height="30"></td>
      <td class="clsfth" width="205" height="30">
      </td>
      <td class="clsfth" width="66" height="30">
          <input type="button" Class="clsbtn2" name="load" value="新增" onclick="javascript:fun_new('<%=roomId%>');">&nbsp;
      </td>
      <td class="clsfth" width="66" height="30">
       <%if(Integer.valueOf(roomId).intValue()>-1){%>
         <input type="button" Class="clsbtn2" name="load" value="导入系统设置" onclick="javascript:fun_load('<%=roomId%>')">&nbsp;&nbsp;&nbsp;&nbsp;
      <%}%>
      </td>
    </tr>
<%
}else
{
  TawRmDefineTable  tawRmDefineTable=null;
  for (int i =0;i<vecDefinetable.size();i++){
    tawRmDefineTable=(TawRmDefineTable)vecDefinetable.elementAt(i);
%>
    <tr class="tr_show">
   <%if(i==0){%>
      <td class="clsfth" width="59" height="30" rowspan="<%=vecDefinetable.size()%>"><%=roomName%></td>
   <%}%>
      <td class="clsfth" width="214" height="30">
        <p align="center"><a href="../TawRmDefinemain/show.do?roomId=<%=roomId%>&tableId=<%=tawRmDefineTable.getTableId()%>&tableDesc=<%=tawRmDefineTable.getTableDesc()%>&roomName=<%=roomName%>"><%=tawRmDefineTable.getTableDesc()%></a></td>
      <td class="clsfth" width="48" height="30">
         <input type="button" Class="clsbtn2"  name="load" value="修改" onclick="javascript:fun_update('<%=roomId%>','<%=String.valueOf(tawRmDefineTable.getTableId()).trim()%>','<%=tawRmDefineTable.getTableDesc().trim()%>');">&nbsp;
      </td>
      <td class="clsfth" width="52" height="30">
          <input type="button" Class="clsbtn2" name="load" value="删除" onclick="javascript:fun_delete('<%=roomId%>','<%=String.valueOf(tawRmDefineTable.getTableId()).trim()%>');">&nbsp;
     </td>
    </tr>
<%
}
%>
   <tr class="tr_show">
      <td class="clsfth" width="59" height="30">
          <input type="button" Class="clsbtn2" name="load" value="新增" onclick="javascript:fun_new('<%=roomId%>');">&nbsp;
      </td>
      <td class="clsfth" width="205" height="30">
          <input type="hidden">
      </td>
      <td class="clsfth" width="66" height="30" colspan="2">
          <input type="button" Class="clsbtn2" name="load" value="预览" onclick="javascript:fun_preview('<%=roomId%>');">&nbsp;
      </td>
    </tr>
<%
}
%>
  </table>
</form>
</body>
<SCRIPT LANGUAGE=javascript>
<!--
function fun_delete(roomId,tableId)
{
    document.all.form.action="delete.do?roomId="+roomId+"&tableId="+tableId;
    document.all.form.submit();
}
function fun_new(roomId)
{
    var oriUrl="show.do";
    var url="../clip.jsp?roomId="+roomId+"&action=ADD&roomName=<%=roomName%>&oriUrl="+oriUrl;;
    window.open(url,"","width=350,height=120,left=200,top=150");
}
function fun_update(roomId,tableId,tableDesc)
{
    var oriUrl="show.do";
    var url="../clip.jsp?roomId="+roomId+"&tableId="+tableId+"&record_table="+tableDesc+"&action=UPT&roomName=<%=roomName%>&oriUrl="+oriUrl;
    window.open(url,"","width=350,height=120,left=200,top=150");
}
function fun_load(roomId)
{
    document.all.form.action="load.do?roomId="+roomId;
    document.all.form.submit();
}
function fun_preview(roomId)
{
    document.all.form.action="preview.do?roomId="+roomId;
    document.all.form.submit();
}
/*
function fun_show_sub(roomId,tableId)
{
    document.all.form.action="../TawRmDefinesub/show.do?roomId="+roomId+"&mainId="+tableId;
    document.all.form.submit();
}
*/

//-->
</SCRIPT>
</html>
