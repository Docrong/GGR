<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<script language="JavaScript">
       function GoBack(){
          window.history.back()
       }
</script>
<body>
  <table border="0" width="95%" align=center>
    <tr>
      <td class="table_title" align="center">
        <b>${eoms:a2u('������ѯ')}������Ϣ</b>
      </td>
    </tr>
</table>
  <table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
    <tr class="td_label" align="center">
    <td >${eoms:a2u('���')}</td>
     <td>${eoms:a2u('������רҵ')}</td>
	  <td>${eoms:a2u('����Сרҵ')}</td>    
     <td>${eoms:a2u('��Ԫ����')}</td>
	  <td>${eoms:a2u('����')}</td>
	  <td>${eoms:a2u('״̬')}</td>
     <td>${eoms:a2u('�汾��')}</td>
     <td>${eoms:a2u('���к�')}</td>
     <td>${eoms:a2u('���ʱ��')}</td>
     <td>${eoms:a2u('���λ��')}</td>
    </tr>
    <%int i=0;%>
    <logic:iterate id="statlist" name="statlist" type="com.boco.eoms.sparepart.model.TawPart">
    <a href="../part/amplyview.do?id=<bean:write name="statlist" property="id" scope="page"/>">
    <%i=i+1;%>
    <tr class="tr_show" align="center" style="cursor:hand" onmousemove="this.style.backgroundColor='#87CEEB'" onmouseout="this.style.backgroundColor=''">
      <td><%=i%></td>
      <td><bean:write name="statlist" property="nettype" scope="page"/></td>
		<td><bean:write name="statlist" property="subdept" scope="page"/></td>
       <td><bean:write name="statlist" property="necode" scope="page"/></td>
		 <td><bean:write name="statlist" property="objecttype" scope="page"/></td>
		 <td><bean:write name="statlist" property="state" scope="page"/></td>
       <td><bean:write name="statlist" property="version" scope="page"/></td>
       <td><bean:write name="statlist" property="serialno" scope="page"/></td>
       <td><bean:write name="statlist" property="intime" scope="page"/></td>
       <td><bean:write name="statlist" property="position" scope="page"/></td>
    </tr>
    </a>
    </logic:iterate>
  </table>
</body>
</html>
