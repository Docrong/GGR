<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpStubUserVO"%>
<script language="JavaScript">

function GoBack()
{
  window.history.back()
}

</script>
<%
  TawwpStubUserVO tawwpStubUserVO = (TawwpStubUserVO)request.getAttribute("tawwpstubuservo");
%>

<form name="reportform" method="POST" action="../tawwpstubuser/stubusersave.do" onsubmit="onSubmit();">
<!--  body begin  -->
<br>

<table width="400" class="formTable">
  <caption><bean:message key="stubuserview.title.formTitle" /></caption>
  <tr>
    <td width="100" class="label">
      <bean:message key="stubuserview.title.formCruser" />
    </td>
    <td width="300">
      <%=tawwpStubUserVO.getCruserName()%>
    </td>
  </tr>
  <tr id="dateselect">
    <td width="100" class="label">
      <bean:message key="stubuserview.title.formStartDate" />
    </td>
    <td width="300">
     <%=tawwpStubUserVO.getStartDate()%>
    </td>
  </tr>
  <tr id="dateselect">
    <td width="100" class="label">
      <bean:message key="stubuserview.title.formEndDate" />
    </td>
    <td width="300">
     <%=tawwpStubUserVO.getEndDate()%>
    </td>
  </tr>
  <tr class="label">
    <td width="100" class="label">
      <bean:message key="stubuserview.title.formStubuser" />
    </td>
    <td width="300">
      <%=tawwpStubUserVO.getStubuserName()%>
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="stubuserview.title.formContent" />
    </td>
    <td width="300">
      <%=tawwpStubUserVO.getContent()%>
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="stubuserview.title.formCheckUser" />
    </td>
    <td width="300">
      <%=tawwpStubUserVO.getCheckuserName()%>
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="stubuserview.title.formState" />
    </td>
    <td width="300">
      <%=tawwpStubUserVO.getStateName()%>
    </td>
  </tr>
  <tr>
    <td colSpan="2">
      <input type="button" value="<bean:message key="stubuserview.title.btnBack" />" name="B2" Onclick="GoBack();" class="button">
    </td>
  </tr>
</table>

<!--  body end  -->
</form>

<%@ include file="/common/footer_eoms.jsp"%>
