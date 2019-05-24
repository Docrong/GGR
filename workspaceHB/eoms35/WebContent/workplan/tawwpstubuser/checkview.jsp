<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpStubUserVO"%>

<%
  TawwpStubUserVO tawwpStubUserVO = (TawwpStubUserVO)request.getAttribute("tawwpstubuservo");
%>
<script language="JavaScript">

function GoBack()
{
  window.history.back()
}

function onPass()
{
  var stubUserId =  "<%=tawwpStubUserVO.getId()%>";
  location.href="../tawwpstubuser/pass.do?stubuserid=" + stubUserId;
}

function onReject()
{
  var stubUserId =  "<%=tawwpStubUserVO.getId()%>";
  location.href="../tawwpstubuser/reject.do?stubuserid=" + stubUserId;
}

</script>

<form name="reportform" method="POST" action="../tawwpstubuser/stubusersave.do" onsubmit="onSubmit();">
<!--  bogy begin  -->
<br>

<table border="0"  width="400" class="formTable">
  <caption><bean:message key="checkview.title.stubFmTitle" /></caption>
  <tr>
    <td width="100" class="label">
      <bean:message key="checkview.title.stubFmCruser" />
    </td>
    <td width="300">
      <%=tawwpStubUserVO.getCruserName()%>
    </td>
  </tr>
  <tr id="dateselect">
    <td width="100" class="label">
      <bean:message key="checkview.title.stubFmStartDate" />
    </td>
    <td width="300">
     <%=tawwpStubUserVO.getStartDate()%>
    </td>
  </tr>
  <tr id="dateselect">
    <td width="100" class="label">
      <bean:message key="checkview.title.stubFmEndDate" />
    </td>
    <td width="300">
     <%=tawwpStubUserVO.getEndDate()%>
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="checkview.title.stubFmAgent" />
    </td>
    <td width="300">
      <%=tawwpStubUserVO.getStubuserName()%>
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="checkview.title.stubFmContent" />
    </td>
    <td width="300">
      <%=tawwpStubUserVO.getContent()%>
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="checkview.title.checkFmCheckUser" />
    </td>
    <td width="300">
      <%=tawwpStubUserVO.getCheckuserName()%>
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="checkview.title.checkFmState" />
    </td>
    <td width="300">
      <%=tawwpStubUserVO.getStateName()%>
    </td>
  </tr>
</table>
  <input type="button" value="<bean:message key="checkview.title.btnPass" />" name="B2" Onclick="onPass();" class="button">
  <input type="button" value="<bean:message key="checkview.title.btnReject" />" name="B2" Onclick="onReject();" class="button">
  <input type="button" value="<bean:message key="checkview.title.btnBack" />" name="B2" Onclick="GoBack();" class="button">

<!--  body end  -->
</form>

<%@ include file="/common/footer_eoms.jsp"%>

