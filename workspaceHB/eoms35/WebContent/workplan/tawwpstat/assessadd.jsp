<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<%
  String monthPlanId = request.getParameter("monthplanid");
%>
<script language="JavaScript">

function GoBack()
{
  window.history.back()
}

</script>

<form name="assessform" method="POST" action="../tawwpstat/saveassess.do">
<!--  body begin  -->
<br>

<table class="formTable">
  <caption><bean:message key="assessadd.title.formTitle" /></caption>
  <tr>
    <td width="100" class="label">
      <bean:message key="assessadd.title.formContent" />
    </td>
    <td width="300">
      <textarea name="content" class="textarea max"></textarea>
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="assessadd.title.formState" />
    </td>
    <td width="300">
      <select name="state" class="select">
        <option value="4" selected><bean:message key="assessadd.title.pass" /></option>
        <option value="2" ><bean:message key="assessadd.title.unpass" /></option>
      </select>
    </td>
  </tr>
  </table>
  <br />
  <input type="hidden" value="<%=monthPlanId%>" name="monthplanid">
  <input type="submit" value="<bean:message key="assessadd.title.btnSubmit" />" name="B1" class="submit">
  <input type="button" value="<bean:message key="assessadd.title.btnBack" />" name="B2" Onclick="GoBack();" class="button">

<!--  body end  -->
</form>
<%@ include file="/common/footer_eoms.jsp"%>
