<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@ page import="java.util.List,
                 java.util.Iterator,
                 com.boco.eoms.mobilewap.webtool.vo.CardVO,
                 com.boco.eoms.mobilewap.webtool.bo.WebToolBO" %>
<%
WebToolBO webtool = new WebToolBO(request);
List list = webtool.getCardList();
String app = request.getContextPath();
%>

<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
<script language="javascript">
function valuetoparent(i){
  var text = document.getElementById('dbName'+i).value;
//  alert(text);
  opener.document.form1.hrefCardId.value=text;
  window.close();
}
</script>
</head>
<body>
<form name="dbform" action="">
<table class="list_table">
<tr>
  <th width="30"></td>
  <th width="100">ҳ������</td>
  <th width="170">����</td>
</tr>
<%
int i=0;
for(Iterator it = list.iterator(); it.hasNext();) {
    CardVO vo = (CardVO)it.next();
%>
<tr>
  <th class="spec"><input type="radio" id="dbName<%=i %>" name="dbName" value="<%=vo.getCardId() %>" onclick="javascript:valuetoparent(<%=i %>)"></td>
  <td><%=vo.getName() %></td>
  <td><%=vo.getDescription() %></td>
</tr>
<%i++;} %>
</table>
</form>
</body>
</html>