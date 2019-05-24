<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="java.util.Enumeration"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpMonthPlanVO"%>

<%
  List list = (List)request.getAttribute("statyearall");
  TawwpMonthPlanVO tawwpMonthPlanVO = null;
  String monthPlanId = "";
%>
<script type="text/javascript">
<!--
Ext.onReady(function(){
	colorRows('list-table');
})
//-->
</script>
<!--  body begin  -->

<form name="dailyexecuteplan">

<br>

  <table class="listTable" id="list-table">
    <caption><bean:message key="statreportdept.title.formTitle"/></caption>
    <%
      if(list.size() != 0){
    %>
    <thead>
    <tr>
      <td width="200">
        <bean:message key="statreportdept.title.formPlanName"/>
      </td>
      <td width="100">
        <bean:message key="statreportdept.title.formConcernedNet"/>
      </td>
      <td width="100">
        <bean:message key="statreportdept.title.formDeptName"/>
      </td>
      <td width="100">
        <bean:message key="statreportdept.title.formMonth"/>
      </td>
      <td width="100">
        <bean:message key="statreportdept.title.formPrincipal"/>
      </td>
      <td width="50">
        <bean:message key="statreportdept.title.formState"/>
      </td>
      <td width="50">
        <bean:message key="statreportdept.title.formView"/>
      </td>
    </tr>
    </thead>
    <tbody>

    <%
        for (int i=0; i<list.size(); i++) {
          tawwpMonthPlanVO = (TawwpMonthPlanVO)list.get(i);
    %>

    <tr class="tr_show">
      <td width="200" class="clsthd2">
        <%=tawwpMonthPlanVO.getName()%>
      </td>
      <td width="100" class="clsthd2">
        <%=tawwpMonthPlanVO.getNetName()%>
      </td>
      <td width="100" class="clsthd2">
        <%=tawwpMonthPlanVO.getDeptName()%>
      </td>
      <td width="100" class="clsthd2">
        <%=tawwpMonthPlanVO.getYearFlag()%><bean:message key="statreportdept.title.formYearFlag"/>
        <%=tawwpMonthPlanVO.getMonthFlag()%><bean:message key="statreportdept.title.formMonthFlag"/>
      </td>
      <td width="100" class="clsthd2">
        <%=tawwpMonthPlanVO.getPrincipal()%>
      </td>
      <td width="50" class="clsthd2">
        <%=tawwpMonthPlanVO.getExecuteStateName()%>
      </td>
      <td width="50" class="clsthd2">
        <a href="../tawwpexecute/viewall.do?monthplanid=<%=tawwpMonthPlanVO.getId()%>">
          <bean:message key="statreportdept.title.formView"/>
        </a>
      </td>
    </tr>
    <%
      }
    %>
    </tbody>
    <%
      }else{
    %>

    <tr>
      <td>
        <bean:message key="statreportdept.title.nolist"/>
      </td>
    </tr>
    <%
      }
    %>
    
  </table>

</form>

<!--  body end  -->
<%@ include file="/common/footer_eoms.jsp"%>

