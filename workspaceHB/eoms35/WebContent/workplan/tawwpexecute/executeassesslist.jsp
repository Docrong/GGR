<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.Hashtable"%>
<%@ page import ="java.util.Enumeration"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpMonthPlanVO"%>

<%
  Hashtable monthPlanVOHash = (Hashtable)request.getAttribute("monthplanvohash");
  Enumeration hashKeys = null;
  TawwpMonthPlanVO tawwpMonthPlanVO = null;
  String monthCheckId = "";
%>
<<script type="text/javascript">
<!--
Ext.onReady(function(){
	colorRows('list-table');
})
//-->
</script>
<!--  body begin  -->

<form name="monthplan">

  <table class="listTable">
    <caption><bean:message key="executeassesslist.title.formTitle" /></caption>
    <thead>
    <tr>
      <td width="15%">
        <bean:message key="executeassesslist.title.formName" />
      </td>
      <td width="15%">
        <bean:message key="executeassesslist.title.formType" />
      </td>
      <td width="15%">
        <bean:message key="executeassesslist.title.formDeptName" />
      </td>
      <td width="15%">
        <bean:message key="executeassesslist.title.formMonth" />
      </td>
      <td width="15%">
        <bean:message key="executeassesslist.title.formPrincipal" />
      </td>
      <td width="15%">
        <bean:message key="executeassesslist.title.formNetList" />
      </td>
      <td width="10%">
        <bean:message key="executeassesslist.title.formCheck" />
      </td>
    </tr>
    </thead>
    <tbody>
    <%hashKeys = monthPlanVOHash.keys();
      while (hashKeys.hasMoreElements()) {
        monthCheckId = ((String)hashKeys.nextElement());
        tawwpMonthPlanVO = (TawwpMonthPlanVO)monthPlanVOHash.get("tawwpYearPlan");
    %>
    <tr>
      <td width="15%">
        <%=tawwpMonthPlanVO.getName()%>
      </td>
      <td width="15%">
        <%=tawwpMonthPlanVO.getExecuteTypeName()%>
      </td>
      <td width="15%">
        <%=tawwpMonthPlanVO.getDeptName()%>
      </td>
      <td width="15%">
        <%=tawwpMonthPlanVO.getYearFlag()%><bean:message key="executeassesslist.title.labYear" />
        <%=tawwpMonthPlanVO.getMonthFlag()%><bean:message key="executeassesslist.title.labMonth" />
      </td>
      <td width="15%">
        <%=tawwpMonthPlanVO.getPrincipalName()%>
      </td>
      <td width="15%">
        <%=tawwpMonthPlanVO.getNetName()%>
      </td>
      <td width="5%">
        <a href="../tawwpmonth/monthcheckview.do?monthcheckid=<%=monthCheckId%>&monthplanid=<%=tawwpMonthPlanVO.getId()%>">
         <img src="../images/bottom/an_pz.gif" width="19" height="18">
        </a>
      </td>
    </tr>
    <%
      }
    %>
    </tbody>
  </table>

</form>

<!--  body end  -->
<%@ include file="/common/footer_eoms.jsp"%>
