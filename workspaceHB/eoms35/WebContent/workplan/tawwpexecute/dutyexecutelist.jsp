<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpMonthPlanVO"%>

<script language="javascript">
Ext.onReady(function(){
	colorRows('list-table');
})
</script>
<%
  List monthPlanVOList = (List)request.getAttribute("monthplanvolist");
  TawwpMonthPlanVO tawwpMonthPlanVO = null;
%>

<!--  body begin  -->

<form name="dutyexecuteplan">

  <table class="listTable" id="list" width="90%">
    <caption><bean:message key="dutyexecutelist.title.formTitle" /></caption>
    <%
      if(monthPlanVOList.size() != 0){
    %>
    <thead>
    <tr>
      <td width="20%">
        <bean:message key="dutyexecutelist.title.formPlanName"/>
      </td>
      <td width="15%">
        <bean:message key="dutyexecutelist.title.formConcernedNet"/>
      </td>
      <td width="15%">
        <bean:message key="dutyexecutelist.title.formDeptName"/>
      </td>
      <td width="12%">
        <bean:message key="dutyexecutelist.title.formExecuteMonth"/>
      </td>
      <td width="13%">
        <bean:message key="dutyexecutelist.title.formExecuteUser"/>
      </td>
      <td width="8%">
        <bean:message key="dutyexecutelist.title.formAdd"/>
      </td>
    </tr>
    </thead>
    
    <tbody>
    <%
        for(int i=0; i<monthPlanVOList.size(); i++){
          tawwpMonthPlanVO = (TawwpMonthPlanVO)monthPlanVOList.get(i);
    %>

    <tr>
      <td width="20%">
        <%=tawwpMonthPlanVO.getName()%>
      </td>
      <td width="15%">
        <%=tawwpMonthPlanVO.getNetName()%>
      </td>
      <td width="15%">
        <%=tawwpMonthPlanVO.getDeptName()%>
      </td>
      <td width="12%">
        <%=tawwpMonthPlanVO.getYearFlag()%><bean:message key="dutyexecutelist.title.formYear"/>
        <%=tawwpMonthPlanVO.getMonthFlag()%><bean:message key="dutyexecutelist.title.formMonth"/>
      </td>
      <td width="13%">
        <%=tawwpMonthPlanVO.getPrincipal()%>
      </td>
      <td width="8%">
        <a href="../tawwpexecute/executeadds.do?monthplanid=<%=tawwpMonthPlanVO.getId()%>&flag=duty">
          <bean:message key="dutyexecutelist.title.formAdd"/>
        </a>
      </td>
    </tr>

    <%
        }
      }else{
    %>
    <tr>
      <td height="25" colspan="6">
       <bean:message key="dutyexecutelist.title.nolist"/>
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
