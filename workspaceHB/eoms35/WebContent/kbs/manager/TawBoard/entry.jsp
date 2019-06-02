<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ page import="java.util.*,java.text.SimpleDateFormat"%>
<script language="javascript">
function check_info()
{
  if (trim(tawBoardForm.boardName.value)=="")
  {
    alert("\u4E13\u9898\u540D\u79F0\u4E0D\u80FD\u4E3A\u7A7A\uFF0C\u8BF7\u8F93\u5165");
    return false;
  }
  else if (tawBoardForm.keepTime.value.indexOf(".")!=-1)
  {
    alert("信息保留时间只能输入整数");
    return false;
  }
  else if (tawBoardForm.parentId.value==0)
  {
    alert("没有权限操作带*号的专题！");
    return false;
  }

  //tawBoardForm.submit();
  return true;
}
function trim(TempStr)
{
  return TempStr = TempStr.replace(/(^\s*)|(\s*$)/g, "");
}
</script>
<html:form  method="post" action="/TawBoard/save">
<html:hidden property="strutsAction" />
<%
Date currentDate = new Date();
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
String date = dateFormat.format(currentDate);
String infoType=String.valueOf(request.getParameter("infoType"));
%>
<input type="hidden" name="infoType" value="<%=infoType%>">
<c:choose>
  <c:when test="${requestScope['tawBoardForm'].strutsAction != 1}">
    <html:hidden property="boardId"/>
  </c:when>
</c:choose>
<table align="center" width="70%" cellspacing="1" border="0" cellpadding="1">
  <tr>
    <td class="table_title" width="100%">
      <c:choose>
        <c:when test="${requestScope['tawBoardForm'].strutsAction == 1}">
          <bean:message key="label.add"/>
        </c:when>
        <c:otherwise>
          <bean:message key="label.edit"/>
        </c:otherwise>
      </c:choose>
      &nbsp;<bean:message key="label.board"/>
    </td>
  </tr>
</table>
<table border="0" width="50%" cellspacing="1" cellpadding="1" class="table_show" align=center>
  <tr class="tr_show">
    <td width="25%" class="clsfth"><bean:message key="TawBoard.boardName"/></td>
    <td width="75%"><html:text property="boardName" size="20"/><font color="red">**</font></td>
  </tr>
   <tr class="tr_show">
   <td width="25%" class="clsfth">主题编码</td>
   <td width="75%"><html:text property="boardCode" size="20"/></td>
  </tr>

  <tr class="tr_show">
    <td width="25%" class="clsfth"><bean:message key="TawBoard.parentId"/></td>
    <td width="75%">
      <html:select property="parentId">
        <html:options collection="BOARDS" property="value" labelProperty="label"/>
      </html:select>
    </td>
  </tr>




  <tr class="tr_show">
    <td width="25%" class="clsfth"><bean:message key="TawBoard.deptId"/></td>
    <td width="75%">
      <html:select property="deptId">
        <html:options collection="DEPTS" property="value" labelProperty="label"/>
      </html:select>
    </td>
  </tr>

  <c:choose>
  <c:when test="${requestScope['tawBoardForm'].strutsAction == 1}">

  </c:when>
  <c:otherwise>
  <tr class="tr_show">
    <td width="25%" class="clsfth"><bean:message key="TawBoard.createTime"/></td>
    <td width="75%"><bean:write name="tawBoardForm" property="createTime" scope="request"/></td>
  </tr>
  <tr class="tr_show">
    <td width="25%" class="clsfth"><bean:message key="TawBoard.modiTime"/></td>
    <td width="75%"><%=date%></td>
  </tr>
  </c:otherwise>
  </c:choose>
  <tr class="tr_show">
    <td width="25%" class="clsfth"><bean:message key="TawBoard.comments"/></td>
    <td width="75%"><html:textarea property="comments" rows="6" cols="46"/></td>
  </tr>
</table>
<table align="center" width="70%" cellspacing="1" border="0" cellpadding="1">
  <tr>
    <td height="32" align="right" width="100%">
      <input type="submit" Class="clsbtn2" value="<bean:message key="label.save"/>" onclick="return check_info();">&nbsp;
      <input type="button" Class="clsbtn2" value="<bean:message key="label.cancel"/>" onclick="history.back();">
    </td>
  </tr>
</table>
</html:form>


