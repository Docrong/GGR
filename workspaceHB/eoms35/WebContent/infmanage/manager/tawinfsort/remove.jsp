<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html>
<head>
<title>删除资料类别</title>
<html:base/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>

<body>
<html:form action="/TawInfSort/deldone" method="post">
  <br>
    <table border="0" width="50%" cellspacing="0" align="center">
        <tr>
	      <td width="100%" align="center" class="table_title">
		    <b>
              <bean:message key="label.remove"/>&nbsp;<bean:message key="TawInfSort.Name"/>
			</b>
	      </td>
	    </tr>
    </table>
	<html:hidden property="infSortId"/>
	<html:hidden property="infSortName"/>
    <table border="0" width="50%" cellspacing="1" cellpadding="1" class="table_show" align=center>
	  <tr class="tr_show">
		<td width="30%" height="25" class="clsfth">&nbsp;
		  &nbsp<bean:message key="TawInfSort.inf_sort_name"/><font color="#FF0000">**</font>
		</td>
		<td width="70%" height="25">
          &nbsp<bean:write name="tawInfSortForm" property="infSortName" scope="request"/>
		</td>
	  </tr>
	</table>
	<table border="0" width="50%" cellspacing="0" align="center">
		<tr>
          <td width="100%" colspan="2" height="32" align="right">
			<input  Class="clsbtn2" type="button" name="tosubmit" value="<bean:message key="label.remove"/>" onClick="toSubmit()">
	       	&nbsp;
          	<input type="button" value="<bean:message key="label.cancel"/>" onclick="history.back()" class="clsbtn2"/>
		   	&nbsp;&nbsp;
		  </td>
		</tr>
      </table>
</html:form>
</body>
</html:html>

<script language="javascript">
  function toSubmit()
  {
	window.document.tawInfSortForm.submit();
  }
</script>