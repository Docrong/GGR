<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@page import="java.util.List,
                java.util.Iterator,
                com.boco.eoms.mobilewap.webtool.bo.WebToolBO" %>
<%
//��ҳ������SelectDynamic��ǵ�jspҳ��
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
  alert("ѡ�������ѡ����С�List�������Ʊ���");

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
  <th>��������</td>
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
		�������ڴ˼������ֶε�����<br/>
         <input type="text" id="_vName<%=i%>" name="_vName" value="" size=30>
         <input type="button" value="ȷ��" onclick="javascript:nametoparent(<%=i%>)">
</td></tr> 
<%i++;} %>
</table>
</form>
</body>
</html>