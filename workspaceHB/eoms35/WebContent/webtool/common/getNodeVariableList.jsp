<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@page import="java.util.List,
                java.util.Iterator,
                com.boco.eoms.mobilewap.webtool.bo.WebToolBO" %>
<%
WebToolBO webtool = new WebToolBO();
List list = webtool.getNodeVariableList();
String k=request.getParameter("k");
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
  //alert("[@"+text+"]");
  //opener.document.getElementById('text').value=text;
  opener.document.getElementById('text<%=k%>').value+="[@"+text+"]";
  window.close();
}
function nametoparent(i){
  var vname = document.getElementById("_vName"+i).value;
  var _name = document.getElementById("vName"+i).value;
  opener.document.getElementById('text<%=k%>').value+="[@"+_name+":"+vname+"]";
//  opener.document.form1.text.value+="[@"+_name+":"+vname+"]";
  window.close();
}
</script>

</head>
<body>
<form name="dbform" action="">
<table class="list_table" width="300" align="center">
<tr>
  <th width="30" class="nobg"></th>
  <th width="100%">变量名称</th>
</tr>
<%
int i=0;
for(Iterator it = list.iterator(); it.hasNext();) {
  String nameValue = (String)it.next();
  
%>
<tr>
  <th class="spec" width="30">
  <input type="radio" id="vName<%=i%>" name="vName" value="<%=nameValue %>" onclick="javascript:valuetoparent(<%=i%>)"></td>
  <td><%=nameValue%></td>
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