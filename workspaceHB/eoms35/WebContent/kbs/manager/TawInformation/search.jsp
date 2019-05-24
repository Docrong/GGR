<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="java.util.*,java.text.SimpleDateFormat,com.boco.eoms.kbs.util.*"%>
<%@ page import="com.boco.eoms.common.util.*,com.boco.eoms.common.controller.*"%>

<%
String path = request.getContextPath();
%>

<link title="style" href="<%=path%>/css/wsstyle.css" type="text/css" rel="stylesheet">
<link rel="StyleSheet" href="<%=path%>/css/tree.css" type="text/css">
<link rel="stylesheet" href="<%=path%>/css/table_style.css" type="text/css">
<?import namespace=BOCOimplementation="<%=path%>/css/button/genericButton.htc"/>
<script type="text/javascript" src="<%=path%>/css/onlytree.js"></script>
<script language="javascript" src="<%=path%>/css/table_calendar.js"></script>
<script type="text/javascript" src="<%=path%>/css/finallytree.js"></script>

<script language="javascript">
function onQuery()
{
  tawInformationForm.submit();
}
</script>
<html:form  method="post" action="/TawInformation/searchDo.do">

<br>
<table border="0" width="70%"  cellspacing="1" cellpadding="1" align=center>
  <tr>
    <td class="table_title"  width="100%">
      查询
    </td>
  </tr>
</table>
<table border="0" width="70%" cellspacing="1" cellpadding="1" class="table_show" align=center>
   <tr class="tr_show">
    <td width="25%" class="clsfth">资料编号</td>
    <td width="75%"><html:text property="code"  size="20" styleClass="clstext"/></td>
  </tr>
  <tr class="tr_show">
    <td width="25%" class="clsfth">资料名称</td>
    <td width="75%"><html:text property="name" size="20" styleClass="clstext"/></td>
  </tr>
  <tr class="tr_show">
    <td width="25%" class="clsfth">所属部门</td>
    <td width="75%">
      <html:select property="belongDept">
        <html:options collection="DEPTS" property="value" labelProperty="label"/>
      </html:select>
    </td>
  </tr>
  <tr class="tr_show">
    <td width="25%" class="clsfth">专题</td>
    <td width="75%">
      <html:select property="boardId">
        <html:options collection="BOARDS" property="value" labelProperty="label"/>
      </html:select>
      <html:checkbox property="confirmed" value="1"/>搜索子专题
    </td>
  </tr>
   <tr class="tr_show">
    <td width="25%" class="clsfth">适用范围</td>
    <td width="75%"><html:text property="usedArea" size="20" styleClass="clstext"/></td>
  </tr>
   <tr class="tr_show">
    <td width="25%" class="clsfth">关键字</td>
    <td width="75%"><html:text property="keyword" size="20" styleClass="clstext"/></td>
  </tr>
  <tr class="tr_show">
    <td width="25%" class="clsfth">内容</td>
    <td width="65%"><html:text property="content" styleClass="clstext"/></td>
  </tr>
</table>
<table border="0" width="70%" cellspacing="1" cellpadding="1" align=center>
  <tr>
    <td width="100%" align="right" height="32">
      <input type="button" Class="clsbtn2" value="<bean:message key="label.ok"/>" onclick="return onQuery();">
      <input type="button" Class="clsbtn2" value="<bean:message key="label.cancel"/>" onclick="history.back();">    </td>
  </tr>
</table>
</html:form>
