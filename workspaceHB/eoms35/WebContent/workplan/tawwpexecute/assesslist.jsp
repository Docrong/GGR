<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpMonthPlanVO"%>

<script type="text/javascript">
<!--
Ext.onReady(function(){
	colorRows('list-table');
})
//-->
</script>
<%
  List monthPlanVOList = (List)request.getAttribute("monthplanvolist");
  TawwpMonthPlanVO tawwpMonthPlanVO = null;
%>

<!--  body begin  -->

<form name="monthplan">

  <table width="700" class="listTable">
    <caption><bean:message key="assesslist.title.formTitle" /></caption>
    <thead>
    <tr>
      <td width="300">
        <bean:message key="assesslist.title.formPlanName" />
      </td>
      <td width="100">
        <bean:message key="assesslist.title.formDeptName" />
      </td>
      <td width="100">
        <bean:message key="assesslist.title.formMonth" />
      </td>
      <td width="150">
        <bean:message key="assesslist.title.formConcernedNet" />
      </td>
      <td width="50">
        <bean:message key="assesslist.title.formAssess" />
      </td>
    </tr>
    </thead>
    <tbody>
    <%
      for(int i=0; i<monthPlanVOList.size(); i++){
        tawwpMonthPlanVO = (TawwpMonthPlanVO)monthPlanVOList.get(i);
    %>
    <tr>
      <td width="300">
        <%=tawwpMonthPlanVO.getName()%>
      </td>
      <td width="100">
        <%=tawwpMonthPlanVO.getDeptName()%>
      </td>
      <td width="100">
        <%=tawwpMonthPlanVO.getYearFlag()%><bean:message key="assesslist.title.formYearFlag" />
        <%=tawwpMonthPlanVO.getMonthFlag()%><bean:message key="assesslist.title.formMonthFlag" />
      </td>
      <td width="150">
        <%=tawwpMonthPlanVO.getNetName()%>
      </td>
      <td width="50">
        <a href="../tawwpexecute/assessview.do?executeassessid=<%=tawwpMonthPlanVO.getMonthCheckId()%>&monthplanid=<%=tawwpMonthPlanVO.getId()%>">
         <img src="../images/bottom/an_pz.gif" width="19" height="18" align="absmiddle">
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

