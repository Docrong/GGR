<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="java.util.Hashtable"%>
<%@ page import ="java.util.Enumeration"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpNetVO"%>
<script type="text/javascript" src="../css/functions.js"></script>

<script language="javascript">

function SubmitCheck(){

  frmReg = document.forms[0];


  if( !JustifyNull1(frmReg.name))
  {
    alert( "<bean:message key="netedit.title.warnNetName" />" );
    frmReg.name.focus();
    return false;
  }

  return true;
}


</script>

<%
TawwpNetVO tawwpNetVO = null;
tawwpNetVO = (TawwpNetVO)request.getAttribute("net");
%>
<form name="tawgznetform" method="post" action="netmodify.do?netid=<%=tawwpNetVO.getId()%>" onsubmit='return SubmitCheck()'>

<table width="500" class="formTable">
  <caption><bean:message key="netedit.title.formTitle" /></caption>
  <tr>
    <td class="label" width="100">
      <bean:message key="netedit.title.formSysType" />
    </td>
    <td width="400">
	<%=tawwpNetVO.getSysTypeName()%>
    </td>
  </tr>
  <tr>
    <td class="label">
      <bean:message key="netedit.title.formNetType" />
    </td>
    <td width="400">
      <%=tawwpNetVO.getNetTypeName()%>
    </td>
  </tr>
  <tr>
    <td class="label">
      专业代码
    </td>
    <td width="400">
      <%=tawwpNetVO.getMynettypeName() %>
    </td>
  </tr>
  <tr>
    <td class="label">
      <bean:message key="netedit.title.formNetName" />
    </td>
    <td>
      <input type="text" name="name" size="40" value="<%=tawwpNetVO.getName()%>" Class="text">
    </td>
  </tr>
  <tr>
    <td class="label">
      <bean:message key="netedit.title.formSerialNo" />
    </td>
    <td>
      <%=tawwpNetVO.getSerialNo()%>
    </td>
  </tr>
  <tr>
    <td class="label">
      <bean:message key="netedit.title.formRoomName" />
    </td>
    <td>
      <%=tawwpNetVO.getRoomName()%>
    </td>
  </tr>

  <tr>
    <td class="label">
      <bean:message key="netedit.title.formDeptName" />
    </td>
    <td >
      <%=tawwpNetVO.getDeptName()%>
    </td>
  </tr>
  <tr>
    <td class="label">
      <bean:message key="netedit.title.formVendor" />
    </td>
    <td>
      <%=tawwpNetVO.getVendor()%>
    </td>
  </tr>
</table>
<br>
<input type="submit" value="<bean:message key="netedit.title.formSubmit" />" name="B1"  Class="submit">
<input type="button" value="<bean:message key="netedit.title.formBack" />" onclick="javascript:window.history.back();" class="button">


</form>

<%@ include file="/common/footer_eoms.jsp"%>



