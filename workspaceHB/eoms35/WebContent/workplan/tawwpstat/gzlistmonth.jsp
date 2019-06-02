<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpMonthPlanVO"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>

<%
  List monthPlanVOList = (List)request.getAttribute("querymonthplan");
  TawwpMonthPlanVO tawwpMonthPlanVO = null;
   String startDategz="",endDategz="";
   startDategz=request.getAttribute("startDategz").toString();
   endDategz=request.getAttribute("endDategz").toString();
//   System.out.println("startDategz"+startDategz);
//   System.out.println("startDategz"+endDategz);

%>

<!--  body begin  -->

<form name="monthplan">

  <table class="listTable">
    <caption> <bean:message key="gzlistmonth.title.formTitle" /></caption>
    <thead>
    <tr>
      <td width="250"> <bean:message key="gzlistmonth.title.formPlanName" /></td>
      <td width="100"> <bean:message key="gzlistmonth.title.formNetName" /></td>
      <td width="100"> <bean:message key="gzlistmonth.title.formDeptName" /></td>
      <td width="50"> <bean:message key="gzlistmonth.title.formYear" /></td>
      <td width="50"> <bean:message key="gzlistmonth.title.formMonth" /></td>
      <td width="75"> <bean:message key="gzlistmonth.title.formConstituteState" /></td>
      <td width="75"> <bean:message key="gzlistmonth.title.formExecuteState" /></td>
      <td width="50"> <bean:message key="gzlistmonth.title.formDetail" /></td>
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
        if(tawwpMonthPlanVO.getConstituteState().equals("1")&&"0".equals(startDategz)){
        %>
        <a href="gzquerymonthplanuser.do?monthplanid=<%=tawwpMonthPlanVO.getId()%>">
           <bean:message key="gzlistmonth.title.formDetail" />
        </a>
        <%
        }else{
          %>
          <a href="gzquerymonthplanuser.do?monthplanid=<%=tawwpMonthPlanVO.getId()%>&startDategz=<%=startDategz%>&endDategz=<%=endDategz%>">
              <bean:message key="gzlistmonth.title.formDetail" />
        </a>
        <%}%>
      </td>
    </tr>
    <%
      }
    }
    else{
    %>
    <tr>
      <td colspan="8">
        <bean:message key="gzlistmonth.title.nolist" />
      </td>
    </tr>
    <%
    }
    %>
    </tbody>
  </table>
  <br>
<input type="button" value="<bean:message key="gzlistmonth.title.btnBack" />" onclick="javascript:window.history.back();" class="button">

</form>

<!-- body end -->

<%@ include file="/common/footer_eoms.jsp"%>

