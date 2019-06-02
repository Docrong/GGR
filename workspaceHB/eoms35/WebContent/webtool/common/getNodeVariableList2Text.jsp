<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@page import="java.util.List,
                java.util.Iterator,
                com.boco.eoms.mobilewap.webtool.bo.WebToolBO" %>
<%
//此页面用于SelectDynamic标记的jsp页面
WebToolBO webtool = new WebToolBO(request);
List list = webtool.getNodeVariableList();
String app = request.getContextPath();
%>

<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
<script language="javascript">
function valuetoparent(i){
  var text = document.getElementById("vName"+i).value;
  if(text.indexOf("List")>0&&text.length-4==text.indexOf("List")){
      document.getElementById("khdm"+i).style.display='block';       
      return;
  }
  alert("选择错误！请选择带有“List”的名称变量");

}
function nametoparent(i){
  var vname = document.getElementById("_vName"+i).value;
  var _name = document.getElementById("vName"+i).value;
  //alert("[@"+_name+":"+vname+"]");
  //opener.document.getElementById('dbName').value=text;
  opener.document.form1.text.value="[@"+_name+":"+vname+"]";
  window.close();
}
</script>
</head>
<body>
<form name="dbform" action="">
<table class="list_table">
<tr>
  <th width="30"></td>
  <th>变量名称</td>
</tr>
<%
int i=0;
for(Iterator it = list.iterator(); it.hasNext();) {
  String nameValue = (String)it.next();
  
%>
<tr>
  <th class="spec">
  <input type="radio" id="vName<%=i%>" name="vName" value="<%=nameValue %>" onclick="javascript:valuetoparent(<%=i%>)"></td>
  <td width="100"><%=nameValue%></td>
</tr>
<tr id="khdm<%=i%>" style="display:none"><td></td><th class="spec">
		请输入在此集合中字段的名称<br/>
         <input type="text" id="_vName<%=i%>" name="_vName" value="" size=30>
         <input type="button" value="确定" onclick="javascript:nametoparent(<%=i%>)">
</td></tr> 
<%i++;} %>
</table>
</form>
</body>
</html>