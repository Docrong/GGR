<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import ="java.util.List"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpMonthPlanVO"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
<!--
Ext.onReady(function(){
	colorRows('list-table');
})
//-->
function goBack()
{
  location.href="queryyearplan.do";
}
</script>
<%
  List monthPlanVOList = (List)request.getAttribute("querymonthplan");
  TawwpMonthPlanVO tawwpMonthPlanVO = null;
%>

<!--  body body  -->

<form name="monthplan">

  <table width="750" class="listTable" id="list-table">
    <caption><bean:message key="listmonthplan.title.formTitle" /></caption>
    <thead>
    <tr>
      <td width="250"><bean:message key="listmonthplan.title.formPlanName" /></td>
      <td width="100"><bean:message key="listmonthplan.title.formNetName" /></td>
      <td width="100"><bean:message key="listmonthplan.title.formDeptName" /></td>
      <td width="50"><bean:message key="listmonthplan.title.formYearFlag" /></td>
      <td width="50"><bean:message key="listmonthplan.title.formMonthFlag" /></td>
      <td width="75"><bean:message key="listmonthplan.title.formConstituteState" /></td>
      <td width="75"><bean:message key="listmonthplan.title.formExecuteState" /></td>
      <td width="50"><bean:message key="listmonthplan.title.formDetail" /></td>
    </tr>
    </thead>
    <tbody>
    <%
    if(monthPlanVOList.size()>0){
      for(int i=0; i<monthPlanVOList.size(); i++){
        tawwpMonthPlanVO = (TawwpMonthPlanVO)monthPlanVOList.get(i);
    %>
    <tr>
      <td width="250"><%=tawwpMonthPlanVO.getName()%></td>
      <td width="100"><%=tawwpMonthPlanVO.getNetName()%></td>
      <td width="100"><%=tawwpMonthPlanVO.getDeptName()%></td>
      <td width="50"><%=tawwpMonthPlanVO.getYearFlag()%></td>
      <td width="50"><%=tawwpMonthPlanVO.getMonthFlag()%></td>
      <td width="75"><%=tawwpMonthPlanVO.getConstituteStateName()%></td>
      <td width="75"><%=tawwpMonthPlanVO.getExecuteStateName()%></td>
      <td width="50">
        <%
        if(tawwpMonthPlanVO.getConstituteState().equals("1")){
        %>
        <a href="querymonthplanresult.do?monthplanid=<%=tawwpMonthPlanVO.getId()%>">
          <bean:message key="listmonthplan.title.formDetail" />
        </a>
        <%
        }
        %>
      </td>
    </tr>
    <%
      }
    }
    else{
    %>
    <tr>
      <td width="700" colspan="8">
        <bean:message key="listmonthplan.title.nolist" />
      </td>
    </tr>
    <%
    }
    %>
    </tbody>
  </table>
  <br/>
  <input type="button" value="<bean:message key="listmonthplan.title.btnBack" />" onclick="javascript:goBack();" class="button">
</form>

<!--  body end  -->
<%@ include file="/common/footer_eoms.jsp"%>
