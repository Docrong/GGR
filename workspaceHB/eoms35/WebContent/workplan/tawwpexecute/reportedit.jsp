<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpExecuteReportVO"%>

<%
  TawwpExecuteReportVO tawwpExecuteReportVO = (TawwpExecuteReportVO)request.getAttribute("tawwpexecutereportvo");
  String monthPlanId = (String)request.getAttribute("monthplanid");
%>
<script language="JavaScript">

function GoBack()
{
  window.history.back()
}

</script>

<form name="reportform" method="POST" action="../tawwpexecute/reporteditsave.do?monthplanid=<%=monthPlanId%>" >
<!--  body begin  -->
<br>

<table class="formTalbe">
  <caption><bean:message key="reportedit.title.formTitle" /></caption>
  <tr>
    <td width="100" class="label">
      <bean:message key="reportedit.title.formType" />
    </td>
    <td width="300">
      <%=tawwpExecuteReportVO.getReportTypeName()%>
    </td>
  </tr>
  <%if("0".equals(tawwpExecuteReportVO.getReportType())){%>
  <tr>
    <td width="100" class="label">
      <bean:message key="reportedit.title.formCycle" />
    </td>
    <td width="300">
     <%=tawwpExecuteReportVO.getStartDate()%>-----><%=tawwpExecuteReportVO.getEndDate()%>
    </td>
  </tr>
  <%
    }
  %>
  <tr>
    <td width="100" class="label">
      <bean:message key="reportedit.title.formContent" />
    </td>
    <td width="300">
      <textarea name="content" rows="5" cols="45" class="textarea"><%=tawwpExecuteReportVO.getContent()%></textarea>
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="reportedit.title.formReportFlag" />
    </td>
    <td width="300">
      <select name="reportflag" class="select">
        <%
          if("0".equals(tawwpExecuteReportVO.getReportFlag())){
        %>
        <option value="0" selected="selected">否</option>
        <%
          }
          else
          {
        %>
        <option value="0">否</option>
        <%
          }
        %>
        <%
          if("1".equals(tawwpExecuteReportVO.getReportFlag())){
        %>
        <option value="1" selected="selected">是</option>
        <%
          }
          else
          {
        %>
        <option value="1">是</option>
        <%
          }
        %>
      </select>
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="reportedit.title.formAdvice" />
    </td>
    <td width="300">
      <textarea name="advice" rows="5" cols="45" class="textarea"><%=tawwpExecuteReportVO.getAdvice()%></textarea>
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="reportedit.title.formRemark" />
    </td>
    <td width="300">
      <textarea name="remark" rows="5" cols="45" class="textarea"><%=tawwpExecuteReportVO.getRemark()%></textarea>
    </td>
  </tr>
</table>
<br>
<input type="hidden" value="<%=tawwpExecuteReportVO.getId()%>" name="executereportid">
<input type="submit" value="<bean:message key="reportedit.title.btnSubmit" />" name="B1" class="submit">
<input type="button" value="<bean:message key="reportedit.title.btnBack" />" name="B2" Onclick="GoBack();" class="button">

<!--  body end  -->
</form>
<%@ include file="/common/footer_eoms.jsp"%>

