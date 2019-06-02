<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="java.util.Hashtable"%>
<%@ page import ="java.util.Enumeration"%>
<%@ page import ="com.boco.eoms.workplan.model.TawwpMonthPlan"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpMonthPlanVO"%>

<script language="javascript">
Ext.onReady(function(){
	colorRows('list-table');
})
</script>

<%
  List listKey = (List)request.getAttribute("listKey");
  Hashtable monthPlanVOHash = (Hashtable)request.getAttribute("monthplanvohash");
  List stubMonthPlanList = (List)request.getAttribute("stubmonthplanlist");
  Enumeration hashKeys = null;
   TawwpMonthPlan tawwpMonthPlan = null;
  TawwpMonthPlanVO tawwpMonthPlanVO = null;
  String monthPlanId = "";
  Hashtable tempHash = null;
%>

<!--  body begin -->

<form name="dailyexecuteplan">

  <table class="listTable" id="list">
    <caption><bean:message key="dailyexecutelist.title.formTitle" /></caption>
    <%
      if(monthPlanVOHash.size() != 0 || stubMonthPlanList.size() != 0){
    %>
    <thead>
    <tr>
      <td width="15%">
        <bean:message key="dailyexecutelist.title.formPlanName"/>
      </td>
     
      <td width="8%">
        <bean:message key="dailyexecutelist.title.formAdd" />
      </td>
      <td width="8%">
        <bean:message key="dailyexecutelist.title.formView"/>
      </td>
      <td width="8%">
        <bean:message key="dailyexecutelist.title.formConfirm"/>
      </td>
    </tr>
    </thead>
    
    <tbody>
    <%
        hashKeys = monthPlanVOHash.keys();
        for(int z=0;z<listKey.size();z++){
           tawwpMonthPlan = (TawwpMonthPlan)listKey.get(z);
         // monthPlanId = (String)(hashKeys.nextElement());
         // tawwpMonthPlanVO = (TawwpMonthPlanVO)monthPlanVOHash.get(monthPlanId);
         tawwpMonthPlanVO = (TawwpMonthPlanVO)monthPlanVOHash.get(tawwpMonthPlan);
    %>

    <tr>
      <td width="15%">
        <%=tawwpMonthPlanVO.getName()%>
      </td>
   
      <td width="8%">
        <%
        if(!"0".equals(tawwpMonthPlanVO.getMonthExecuteUserState())){
        %>
        <a href="../tawwpexecute/executeadds.do?monthplanid=<%=tawwpMonthPlanVO.getId()%>&flag=daily" target="_parent">
          <bean:message key="dailyexecutelist.title.formAdd" />
        </a>
        <%
        }
        %>
      </td>
      <td width="8%">
        <%
        if(!"0".equals(tawwpMonthPlanVO.getMonthExecuteUserState())){
        %>
        <a href="../tawwpexecute/executeview.do?monthplanid=<%=tawwpMonthPlanVO.getId()%>" target="_parent">
          <bean:message key="dailyexecutelist.title.formView"/>
        </a>
        <%
        }
        %>
      </td>
       <td width="8%">
        <%
        if("0".equals(tawwpMonthPlanVO.getMonthExecuteUserState())){
        %>
        <a href="../tawwpexecute/confirm.do?monthexecuteuserid=<%=tawwpMonthPlanVO.getMonthExecuteUserId()%>">
          <bean:message key="dailyexecutelist.title.formConfirm"/>
        </a>
        <%
        }
        %>
      </td>
    </tr>

    <%
        }
        for(int i=0; i<stubMonthPlanList.size(); i++){
          tempHash = (Hashtable)stubMonthPlanList.get(i);
          hashKeys = tempHash.keys();
          while (hashKeys.hasMoreElements()) {
            monthPlanId = (String)(hashKeys.nextElement());
            tawwpMonthPlanVO = (TawwpMonthPlanVO)tempHash.get(monthPlanId);
    %>

    <tr>
      <td width="20%">
        <%=tawwpMonthPlanVO.getName()%>
        [<font color="red"><bean:message key="dailyexecutelist.title.formAgent"/></font>]
      </td>
      <td width="15%">
        <%
        if(tawwpMonthPlanVO.getNetName().equals("无网元")){
      %>
        <%=tawwpMonthPlanVO.getNetTypeName()%>
      <% }else{%>
        <%=tawwpMonthPlanVO.getNetName()%>
      <% }%>
      </td>
      <td width="15%">
        <%=tawwpMonthPlanVO.getDeptName()%>
      </td>
      <td width="12%">
        <%=tawwpMonthPlanVO.getYearFlag()%><bean:message key="dailyexecutelist.title.formYear"/>
        <%=tawwpMonthPlanVO.getMonthFlag()%><bean:message key="dailyexecutelist.title.formMonth"/>
      </td>
      <td width="13%">
        <%=tawwpMonthPlanVO.getPrincipal()%>
      </td>
      <td width="8%">
        <%
        if(!"0".equals(tawwpMonthPlanVO.getMonthExecuteUserState())){
        %>
        <a href="../tawwpexecute/executeadds.do?monthplanid=<%=tawwpMonthPlanVO.getId()%>&flag=daily&userbystub=<%=tawwpMonthPlanVO.getUserByStub()%>">
          <bean:message key="dailyexecutelist.title.formAdd" />
        </a>
        <%
        }
        %>   
      </td>
      <td width="8%">
        <%
        if(!"0".equals(tawwpMonthPlanVO.getMonthExecuteUserState())){
        %>
        <a href="../tawwpexecute/executeview.do?monthplanid=<%=tawwpMonthPlanVO.getId()%>&userbystub=<%=tawwpMonthPlanVO.getUserByStub()%>">
          <bean:message key="dailyexecutelist.title.formView"/>
        </a>
        <%
        }
        %>
      </td>
      <td width="8%">
        <%
        if("0".equals(tawwpMonthPlanVO.getMonthExecuteUserState())){
        %>
        <a href="../tawwpexecute/confirm.do?monthexecuteuserid=<%=tawwpMonthPlanVO.getMonthExecuteUserId()%>">
          <bean:message key="dailyexecutelist.title.formConfirm"/>
        </a>
        <%
        }
        %>
      </td>
       <td width="8%">
        <%
        if("0".equals(tawwpMonthPlanVO.getMonthExecuteUserState())){
        %>
        <a href="../tawwpexecute/confirm.do?monthexecuteuserid=<%=tawwpMonthPlanVO.getMonthExecuteUserId()%>">
          <bean:message key="dailyexecutelist.title.formConfirm"/>
        </a>
        <%
        }
        %>
      </td>
    </tr>

    <%    }
        }
      }
      else
      {
    %>
    <tr>
      <td height="25" colspan="8">
        <bean:message key="dailyexecutelist.title.nolist"/>
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
