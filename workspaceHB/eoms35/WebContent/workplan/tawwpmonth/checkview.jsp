<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpMonthPlanVO"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpMonthExecuteVO"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpMonthCheckVO"%>
<%@ page import ="com.boco.eoms.workplan.flow.model.Step"%>

<%
  TawwpMonthPlanVO tawwpMonthPlanVO = (TawwpMonthPlanVO)request.getAttribute("mnonthplanvo");
  List monthExecuteVOList = tawwpMonthPlanVO.getMonthExecuteVOList();
  List monthCheckVOList = tawwpMonthPlanVO.getMonthCheckVOList();
  TawwpMonthExecuteVO tawwpMonthExecuteVO = null;
  TawwpMonthCheckVO tawwpMonthCheckVO = null;
  String monthCheckId = request.getParameter("monthcheckid");
%>

<script language="JavaScript">
Ext.onReady(function(){
	colorRows('list-table');
})

function onPass()
{
  if( !confirm("<bean:message key="checkview.title.warnMonthCheckPass" />") ) return;
  document.yearcheck.action= "checkpass.do?monthcheckid=<%=request.getParameter("monthcheckid")%>&monthplanid=<%=request.getParameter("monthplanid")%>";
  document.yearcheck.submit();
}

function onReject()
{
  if( !confirm("<bean:message key="checkview.title.warnMonthCheckReject" />") ) return;
  document.yearcheck.action= "checkreject.do?monthcheckid=<%=request.getParameter("monthcheckid")%>&monthplanid=<%=request.getParameter("monthplanid")%>";
  document.yearcheck.submit();
}

</script>

<!--  body begin  -->

<table width="100%" class="listTable">
  <caption>
		<%=tawwpMonthPlanVO.getYearFlag()%> &nbsp;<bean:message key="checkview.title.labYear" />&nbsp; 
		<%=tawwpMonthPlanVO.getMonthFlag()%> &nbsp;<bean:message key="checkview.title.labMonth" />&nbsp;
		&lt; &nbsp;<%=tawwpMonthPlanVO.getName()%>&nbsp;&gt;<bean:message key="checkview.title.labCheck" />
  </caption>
  <tr>
    <td width="100" class="label">
      <bean:message key="checkview.title.yearFmDeptName" />
    </td>
    <td width="250">
      <%=tawwpMonthPlanVO.getDeptName()%>
    </td>
    <td width="100" class="label">
      <bean:message key="checkview.title.yearFmConcernedNet" />
    </td>
    <td width="250">
      <%
        if(tawwpMonthPlanVO.getNetName().equals("无网元Ԫ")){
      %>
        <%=tawwpMonthPlanVO.getNetTypeName()%>
      <% }else{%>
        <%=tawwpMonthPlanVO.getNetName()%>
      <% }%>
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="checkview.title.executeFmType" />
    </td>
    <td width="250" >
      <%=tawwpMonthPlanVO.getExecuteTypeName()%>
    </td>
    <td width="100" class="label">
      <bean:message key="checkview.title.executeFmPrincipal" />
    </td>
    <td width="250"  >
      <%=tawwpMonthPlanVO.getPrincipalName()%>
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="checkview.title.executeFmConstituteState" />
    </td>
    <td width="250" >
      <%=tawwpMonthPlanVO.getConstituteStateName()%>
    </td>
    <td width="100" class="label">
      <bean:message key="checkview.title.executeFmExecuteState" />
    </td>
    <td width="250"  >
      <%=tawwpMonthPlanVO.getExecuteStateName()%>
    </td>
  </tr>
</table>

<br>

<table width="100%" class="listTable">
  <caption><bean:message key="checkview.title.monthFmTitle" /></caption>
  <thead>
  <tr>
				<td width="120">
					作业
					<p>
					项目
				</td>
				
				<td width="120">
					业务
					<p>
					类型
				</td>
				<td width="120">
					执行单
					<p>
					位级别
				</td>
				<td width="120">
					适用
					<p>
					说明
				</td>
    <td colspan="31">
      计划执行时间
    </td>
  </tr>
  </thead>
  <tbody>
  <%
    for(int i=0; i<monthExecuteVOList.size(); i++){
      tawwpMonthExecuteVO = (TawwpMonthExecuteVO)(monthExecuteVOList.get(i));
  %>
  <tr>
  <td rowspan="3" nowrap>
      <%=tawwpMonthExecuteVO.getName()%>
    </td>
    <td rowspan="3" nowrap>
      <%=tawwpMonthExecuteVO.getBotype() %>
    </td>
    <td rowspan="3" nowrap>
      <%=tawwpMonthExecuteVO.getExecutedeptlevel() %>
    </td>
    <td rowspan="3" nowrap>
      <%=tawwpMonthExecuteVO.getAppdesc() %>
    </td>
    
    <td colspan="<%=tawwpMonthExecuteVO.getDayCount()%>">
      执行单位或人员：
      <%=tawwpMonthExecuteVO.getExecuterName()%>
      &nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="checkview.title.executeFmCycle" /><%=tawwpMonthExecuteVO.getCycleName()%>
      &nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="checkview.title.executeFmForm" /><a target="_blank" href="../tawwpaddons/addonsread.do?action=read&window=self&myid=&model=50&addonsid=<%=tawwpMonthExecuteVO.getFormId()%>&reaction=./"><%=tawwpMonthExecuteVO.getFormName()%></a>
    </td>
  </tr>
  <tr>
    <%
      for(int j=0; j<Integer.parseInt(tawwpMonthExecuteVO.getDayCount()); j++){
    %>
    <td width="5"><%=j+1%></td>
    <%
      }
    %>
  </tr>
  <tr>
    <%
      char[] temp = (tawwpMonthExecuteVO.getExecuteDate()).toCharArray();
      for(int k=0; k<temp.length; k++){
        if(temp[k] == '1'){
    %>
    <td><img src='${app}/images/icons/yes.gif' /></td>
    <%
        }else{
    %>
    <td></td>
    <%
        }
      }
    }
    %>
  </tr>
</tbody>
</table>

<br>

<table width="100%" class="listTable">
  <caption><bean:message key="checkview.title.monthFmCheck" /></caption>
  <thead>
  <tr>
    <td width="100"><bean:message key="checkview.title.checkFmCheckUser" /></td>
    <td width="150"><bean:message key="checkview.title.checkFmCheckTime" /></td>
    <td width="400"><bean:message key="checkview.title.checkFmCheckContent" /></td>
    <td width="50"><bean:message key="checkview.title.checkFmState" /></td>
  </tr>
  </thead>
  <tbody>
  <%
    for(int l=0; l<monthCheckVOList.size(); l++){
      tawwpMonthCheckVO = (TawwpMonthCheckVO)(monthCheckVOList.get(l));
  %>
  <tr>
    <td width="100"><%=tawwpMonthCheckVO.getCheckUserName()%></td>
    <td width="150"><%=tawwpMonthCheckVO.getChecktime()%></td>
    <td width="400"><%=tawwpMonthCheckVO.getContent()%></td>
    <td width="50"><%=tawwpMonthCheckVO.getState()%></td>
  </tr>
  <%
    }
  %>
  </tbody>
</table>
  <%
  Step step=tawwpMonthPlanVO.getStep();
  System.out.println("1");
  if(step!=null){
    System.out.println("2");
    if(step.getCheckUserIdStr() != null &&!step.getCheckUserIdStr().equals("")){
      System.out.println("3");
  %>
  <form name="yearcheck" method="post" action="" >
    <table align="center">
      <tr   align="center">
        <td  width="100%" align="center" >
          <bean:message key="checkview.title.checkFmCheckUsers" /><%=tawwpMonthPlanVO.getCheckUsers()%>
        </td>
      </tr>
      <tr align="right">
        <td width="100%" align="right" >
          <input type="hidden" name="checkuser" value="<%=step.getCheckUserIdStr()%>">
          <input type="hidden" name="flowserial" value="<%=step.getFlowSerial()%>">
          <input type="hidden" name="deptserial" value="<%=step.getSerial()%>">
        </td>
      </tr>
    </table>
    <br>
    
    <table width="100%">
      <caption><bean:message key="checkview.title.checkFmContent" /></caption>
      <tr>
        <td width="100%">
          <textarea name="content" class="textarea max"></textarea>
        </td>
      </tr>
      <tr align="right">
        <td width="100%" align="right" >
         <input type="button" value="<bean:message key="checkview.title.btnPass" />" onclick="javascript:onPass();" class="button">
         <input type="button" value="<bean:message key="checkview.title.btnReject" />" onclick="javascript:onReject();" class="button">
         <input type="button" value="<bean:message key="checkview.title.btnBack" />" onclick="javascript:window.history.back();" class="button">
        </td>
      </tr>
    </table>
  </form>
  <%
  }
  else{
  %>
    <table width="100%" class="listTable">
      <tr>
        <td width="60%" align="center">
          &lt;&nbsp;<%=step.getName()%>&nbsp;&gt;<bean:message key="checkview.title.noCheckUser" />
        </td>
      </tr>
    </table>
  <%
    }
  }
  else{
  %>
   <form name="yearcheck" method="post" action="" >
    <table width="100%">
      <tr>
        <td width="100%">
          <textarea name="content" class="textarea max"></textarea>
        </td>
      </tr>
      <tr align="right">
        <td width="100%" align="right" >
         <input type="button" value="<bean:message key="checkview.title.btnPass" />" onclick="javascript:onPass();" class="button">
         <input type="button" value="<bean:message key="checkview.title.btnReject" />" onclick="javascript:onReject();" class="button">
         <input type="button" value="<bean:message key="checkview.title.btnBack" />" onclick="javascript:window.history.back();" class="button">
        </td>
      </tr>
    </table>
  </form>
  <%
  }
  %>

<!--  body begin  -->


<%@ include file="/common/footer_eoms.jsp"%>


