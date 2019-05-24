<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.boco.eoms.common.util.*,com.boco.eoms.kbs.util.*"%>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>

<table width="80%" align="center">
<tr >
<td colspan="5" width="100%" class="table_title"><bean:message key="label.view"/></td>
</tr>
<tr>
<td>
<logic:iterate id="tawInformation" name="TAWINFORMATIONS" type="com.boco.eoms.kbs.model.TawInformation">
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
<tr class="tr_show">
    <td nowrap width="20%" class="clsfth">
          &nbsp;资料编号
    </td>
    <td width="25%">
          <bean:write name="tawInformation" property="code" scope="page"/>
    </td>

    <td width="20%" class="clsfth">
          &nbsp;发布时间
    </td>
    <td width="25%">
          <bean:write name="tawInformation" property="publicTime" scope="page"/>
    </td>
    <td width="10%">

          	<a href="edit.do?boardId=<bean:write name="tawInformation" property="boardId" scope="page"/>&id=<bean:write name="tawInformation" property="id" scope="page"/>">
                      <bean:message key="label.modi"/>
          	</a>
          	<a href="trash.do?boardId=<bean:write name="tawInformation" property="boardId" scope="page"/>&id=<bean:write name="tawInformation" property="id" scope="page"/>" onclick='return confirm("确定要删除信息吗？");'>
                      <bean:message key="label.remove"/>
          	</a>
    </td>
</tr>
<tr class="tr_show">
 <td nowrap  class="clsfth" >
          &nbsp;资料名称
    </td>
    <td colspan="4" >
          <bean:write name="tawInformation" property="name" scope="page"/>
    </td>
</tr>

<tr class="tr_show">
  <td  class="clsfth">&nbsp;发布人</td>
  <td colspan="4" ><bean:write name="tawInformation" property="publicDeptName" scope="page"/><bean:write name="tawInformation" property="publicerName" scope="page"/></td>
</tr>

<tr class="tr_show">
  <td  class="clsfth" align="right">&nbsp;发布时间</td>
  <td  align="left" colspan="4">
 <bean:write name="tawInformation" property="publicTime" scope="page"/>
  </td>
</tr>

 <tr class="tr_show">
    <td  height="25"  class="clsfth">&nbsp;所属部门</td>
    <td  height="25" colspan="4">
       <bean:write name="tawInformation" property="belongDeptName" scope="page"/>
    </td>
 </tr>

<tr class="tr_show">
  <td  class="clsfth">&nbsp;适用范围</td>
  <td colspan="4">
    <bean:write name="tawInformation" property="usedArea" scope="page"/>
  </td>
</tr>
<tr class="tr_show">
    <td  class="clsfth">
          &nbsp;资料内容
    </td>
    <td  colspan="4">
          <bean:write name="tawInformation" property="content" scope="page"/>
    </td>
</tr>

<tr class="tr_show">
    <td   class="clsfth">
          &nbsp;附件
    </td>
    <td  colspan="4">
    <eoms:attachment name="tawInformation" property="attachName" scope="page"  idField="attachName"  appCode="kbs"  viewFlag="Y" />
    </td>
</tr>
</table>
</logic:iterate>

<%
String board_id=String.valueOf(request.getParameter("boardId"));
String id = String.valueOf(request.getParameter("id"));
%>
<tr>
	<td align="right" height="32">
              	<input type="button" Class="clsbtn2" value="<bean:message key="label.cancel"/>" onclick="history.back();">
	</td>
</tr>
</table>
<script language="javascript">
function goAdd()
{
  window.location="add.do?boardId=<%=board_id%>";
}

</script>
