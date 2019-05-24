<%@ page contentType="text/html; charset=gb2312"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.duty.model.*"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=GB2312">
<SCRIPT LANGUAGE="JavaScript">
<!--
if(window.operner)
   window.opener.close();
// -->
</SCRIPT>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
</HEAD>
<BODY leftmargin="0" topmargin="0" class="clssclbar">
<FORM>
<br>
<table width=100% border="0"  cellspacing="1" cellpadding="1"  class="table_show" align="center" bgColor="#f3f3f3">
<%=request.getAttribute("strTable")%>
</table>
<br>
</FORM>
</BODY>
</HTML>
