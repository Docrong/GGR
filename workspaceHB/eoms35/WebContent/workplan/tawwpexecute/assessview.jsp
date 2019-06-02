<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="java.util.Hashtable"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpMonthPlanVO"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpMonthExecuteVO"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpExecuteContentVO"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpExecuteAssessVO"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpExecuteReportVO"%>
<%@ page import ="com.boco.eoms.workplan.util.TawwpUtil"%>
<%@ page import ="com.boco.eoms.workplan.flow.model.Step"%>

<%
  TawwpMonthPlanVO tawwpMonthPlanVO = (TawwpMonthPlanVO)request.getAttribute("tawwpmonthplanvo");
  String firstDay = tawwpMonthPlanVO.getYearFlag() + "-" + tawwpMonthPlanVO.getMonthFlag() + "-"
                  + "01 00:00:00";
  int week = TawwpUtil.getWeek(firstDay)-1;
  int dayCount = TawwpUtil.getDayCountOfMonth(tawwpMonthPlanVO.getYearFlag(),tawwpMonthPlanVO.getMonthFlag());
  TawwpMonthExecuteVO tawwpMonthExecuteVO = null;
  Hashtable monthExecuteVOHash = tawwpMonthPlanVO.getMonthExecuteVOHash();
  Hashtable executeContentVOHash = null;
  String tempStr = "";

  TawwpExecuteContentVO tawwpExecuteContentVO = null;
  float totalCount = Float.parseFloat(tawwpMonthPlanVO.getTotalCount()); //需要执行总数
  float executedCount = Float.parseFloat(tawwpMonthPlanVO.getExecutedCount()); //已经执行总数
  float inTimeCount = Float.parseFloat(tawwpMonthPlanVO.getInTimeCount()); //及时执行总数
  float timeOutCount = Float.parseFloat(tawwpMonthPlanVO.getTimeOutCount()); //超时执行总数

  TawwpExecuteAssessVO tawwpExecuteAssessVO = null;
  TawwpExecuteReportVO tawwpExecuteReportVO = null;

%>
<script language="JavaScript">
Ext.onReady(function(){
	colorRows('list-table');
})

function onPass()
{
  if( !confirm("<bean:message key="assessview.title.warnPass" />") ) return;
  document.yearcheck.action= "assesspass.do?executeassessid=<%=request.getParameter("executeassessid")%>&monthplanid=<%=request.getParameter("monthplanid")%>";
  document.yearcheck.submit();
}

function onReject()
{
  if( !confirm("<bean:message key="assessview.title.warnReject" />") ) return;
  document.yearcheck.action= "assessreject.do?executeassessid=<%=request.getParameter("executeassessid")%>&monthplanid=<%=request.getParameter("monthplanid")%>";
  document.yearcheck.submit();
}
</script>

<!--  body begin  -->

<table width="90%" class="formTable">
  <tr>
    <td width="20%" colspan="34" height="10" align="center">
      <%=tawwpMonthPlanVO.getYearFlag()%><bean:message key="assessview.title.labYear" />
      <%=tawwpMonthPlanVO.getMonthFlag()%><bean:message key="assessview.title.labMonth" />
      &lt; <%=tawwpMonthPlanVO.getName()%> &gt;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <font color="red"><bean:message key="assessview.title.labExecuteRate" /><%=(executedCount/totalCount)*100%>% &nbsp;&nbsp;<bean:message key="assessview.title.labinTimeRate" /><%=(inTimeCount/executedCount)*100%>%</font>
    </td>
  </tr>
  <tr>
    <td width="20%" rowspan="2" height="36" align="center"><bean:message key="assessview.title.labContent" /></td>
    <td width="5%" rowspan="2" height="36" align="center"><bean:message key="assessview.title.labCycle" /></td>
    <td width="3%" height="16" align="center"><bean:message key="assessview.title.labCount" /></td>
    <%
      for(int i=1; i<(dayCount+1); i++){
    %>
    <td width="3%" height="16" align="center" align="center">
      <%=i%>
    </td>
    <%
      }
    %>
  </tr>
  <tr>
    <td width="3%" height="14" align="center" align="center"><bean:message key="assessview.title.labWeek" /></td>
     
    <%
      for(int j=1; j<(dayCount+1); j++){
        if(week == 7){
    %>
    <td width="3%" height="16" align="center" align="center">
      <bean:message key="assessview.title.labDay" />
    </td>
    <%
        week = 1;
        }
        else
        {
    %>
    <td width="3%" height="16" align="center" align="center">
      <%=week%>
    </td>
    <%
        week++;
        }
      }
    %>
  </tr>
  <%
    List monthExecuteVOList = tawwpMonthPlanVO.getMonthExecuteVOList();
    for(int k=0; k<monthExecuteVOList.size(); k++){
      tawwpMonthExecuteVO = (TawwpMonthExecuteVO)monthExecuteVOList.get(k);
      executeContentVOHash = (Hashtable)monthExecuteVOHash.get(tawwpMonthExecuteVO);
  %>
  <tr>
    <td width="20%" rowspan="2" height="38" align="center">
      <%=tawwpMonthExecuteVO.getName()%>
    </td>
    <td width="5%" rowspan="2" height="38" align="center">
      <%=tawwpMonthExecuteVO.getCycleName()%>
    </td>
    <td width="3%" height="16" align="center"><bean:message key="assessview.title.labCount" /></td>
    <%
      char[] temp = (tawwpMonthExecuteVO.getExecuteDate()).toCharArray();
      for(int x=1; x<(temp.length+1); x++){
        if(temp[x-1] == '1'){
          if(x<10){
            tempStr = tawwpMonthExecuteVO.getId()+"0"+ String.valueOf(x);
          }else{
            tempStr = tawwpMonthExecuteVO.getId()+String.valueOf(x);
          }
          //System.out.println("调试信息：tempStr="+tempStr);
          tawwpExecuteContentVO = (TawwpExecuteContentVO)(executeContentVOHash.get(tempStr));
    %>
    <td width="3%" height="16" align="center">
      <%--
      <a href="javascript:onExecute('<%=tawwpExecuteContentVO.getId()%>');">
        √
      </a>
      --%>

        <img src="${app }/images/icons/yes.gif">

    </td>
    <%
        }else{
    %>
    <td width="3%" height="16" align="center">
    </td>
    <%
        }
      }
    %>
  </tr>
  <tr>
    <td width="3%" height="14" align="center"><bean:message key="assessview.title.labExecute" /></td>
    <%
      for(int x=1; x<(dayCount+1); x++){
          if(x<10){
            tempStr = tawwpMonthExecuteVO.getId()+"0"+ String.valueOf(x);
          }else{
            tempStr = tawwpMonthExecuteVO.getId()+String.valueOf(x);
          }

          tawwpExecuteContentVO = (TawwpExecuteContentVO)(executeContentVOHash.get(tempStr));
          if(tawwpExecuteContentVO!=null){
            if (!"0".equals(tawwpExecuteContentVO.getExecuteFlag())) {
    %>
    <td width="3%" height="16" align="center">
      <img src="${app }/images/icons/yes.gif">
    </td>
    <%
            }
            else
            {
     %>
     <td width="3%" height="16" align="center">
     </td>
     <%
            }
          }
          else
          {
     %>
     <td width="3%" height="16" align="center">
     </td>
     <%
          }
        }
    %>
  </tr>
  <%
    }
  %>
</table>

<br>

<table width="90%" class="listTable">
  <caption><bean:message key="assessview.title.fmWMTitle" /></caption>
  <thead>
  <tr>
     <td width="10%"><%--<bean:message key="assessview.title.fmWMType" />--%></td>
     <td width="20%"><bean:message key="assessview.title.fmWMDate" /></td>
    <td width="25%"><bean:message key="assessview.title.fmWMContent" /></td>
    <td width="5%"><%--<bean:message key="assessview.title.fmWMFlag" />--%></td>
    <td width="20%"><bean:message key="assessview.title.fmWMAdvice" /></td>
    <td width="15%"><bean:message key="assessview.title.fmWMRemark" /></td>
    <!--<td width="5%">编辑</td>-->
  </tr>
  </thead>
  <tbody>
  <%
    List executeReportVOList = tawwpMonthPlanVO.getExecuteReportVOList();
    for(int y=0; y<executeReportVOList.size(); y++){
      tawwpExecuteReportVO = (TawwpExecuteReportVO)executeReportVOList.get(y);
  %>
  <tr>
    <td width="10%">
      <%=tawwpExecuteReportVO.getReportTypeName()%>
    </td>
    <td width="20%">
      <%=tawwpExecuteReportVO.getStartDate()%> &nbsp;-&nbsp; <%=tawwpExecuteReportVO.getEndDate()%>
    </td>
    <td width="25%">
      <%=tawwpExecuteReportVO.getContent()%>
    </td>
    <td width="10%">
      <%
        if("1".equals(tawwpExecuteReportVO.getReportFlag())){
      %>
      <font color="red"><%=tawwpExecuteReportVO.getReportFlagName()%></font>
      <%
        }else{
      %>
      <%=tawwpExecuteReportVO.getReportFlagName()%>
      <%
        }
      %>
    </td>
    <td width="20%">
      <%=tawwpExecuteReportVO.getAdvice()%>
    </td>
    <td width="15%">
      <%=tawwpExecuteReportVO.getRemark()%>
    </td>
    <!--
    <td width="5%">
      <a href="javascript:onReportEdit('<%=tawwpExecuteReportVO.getId()%>');">
        <img src="../images/top/bianji.gif">
      </a>
    </td>
    -->
  </tr>
  <%
    }
  %>
  </tbody>
 </table>

 <br>

<table width="90%"class="listTable">
  <caption><bean:message key="assessview.title.fmCFTitle" /></caption>
  <thead>
  <tr>
    <td width="15%"><bean:message key="assessview.title.fmCFCheckUser" /></td>
    <td width="25%"><bean:message key="assessview.title.fmCFCheckTime" /></td>
    <td width="50%"><bean:message key="assessview.title.fmCFContent" /></td>
    <td width="10%"><bean:message key="assessview.title.fmCFState" /></td>
  </tr>
  </thead>
  <tbody>
  <%
    List executeAssessVOList = tawwpMonthPlanVO.getExecuteAssessVOList();
    for(int y=0; y<executeAssessVOList.size(); y++){
      tawwpExecuteAssessVO = (TawwpExecuteAssessVO)executeAssessVOList.get(y);
  %>
  <tr>
    <td width="15%"><%=tawwpExecuteAssessVO.getCheckUserName()%></td>
    <td width="25%"><%=tawwpExecuteAssessVO.getCrtime()%></td>
    <td width="50%"><%=tawwpExecuteAssessVO.getContent()%></td>
    <td width="10%"><%=tawwpExecuteAssessVO.getStateName()%></td>
  </tr>
  <%
    }
  %>
  </tbody>
</table>

  <form name="yearcheck" method="post" action="" >
  <%
  Step step=tawwpMonthPlanVO.getStep();

  if(step!=null){
    if(step.getCheckUserIdStr() != null &&!step.getCheckUserIdStr().equals("")){
  %>
    <table align="right">
      <tr align="right">
        <td width="100%" align="right" >
          <input type="hidden" name="checkuser" value="<%=step.getCheckUserIdStr()%>">
          <input type="hidden" name="flowserial" value="<%=step.getFlowSerial()%>">
          <input type="hidden" name="deptserial" value="<%=step.getSerial()%>">
        </td>
      </tr>
    </table>
    <br>
    <table class="listTable" width="100%">
      <caption><bean:message key="assessview.title.labYearCheck" /></caption>
      <tr>
        <td width="100%">
          <textarea name="content" class="textarea max"></textarea>
        </td>
      </tr>
      <tr align="right">
        <td width="100%" align="right" >
         <input type="button" value="<bean:message key="assessview.title.btnPass" />" onclick="javascript:onPass();" class="button">
         <input type="button" value="<bean:message key="assessview.title.btnReject" />" onclick="javascript:onReject();" class="button">
         <input type="button" value="<bean:message key="assessview.title.btnBack" />" onclick="javascript:window.history.back();" class="button">
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
          &lt;<%=step.getName()%>&gt;<bean:message key="assessview.title.noCheckUser" />
        </td>
      </tr>
    </table>
  <%
    }
  }
  else{
  %>
   <form name="yearcheck" method="post" action="" >
    <table class="listTable" width="100%">
      <tr>
        <td width="100%">
          <textarea name="content" class="textarea max"></textarea>
        </td>
      </tr>
      <tr align="right">
        <td width="100%" align="right" >
         <input type="button" value="<bean:message key="assessview.title.btnPass" />" onclick="javascript:onPass();" class="button">
         <input type="button" value="<bean:message key="assessview.title.btnReject" />" onclick="javascript:onReject();" class="button">
         <input type="button" value="<bean:message key="assessview.title.btnBack" />" onclick="javascript:window.history.back();" class="button">
        </td>
      </tr>
    </table>
  </form>
  <%
  }
  %>


<!--  正文结束  -->

<%@ include file="/common/footer_eoms.jsp"%>

