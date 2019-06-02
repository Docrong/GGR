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

<%
  String text="month.xls";
  response.setHeader("Content-Disposition: ","attachment; filename="+text);
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
  float totalCount = Float.parseFloat(tawwpMonthPlanVO.getTotalCount()); //锟斤拷要执锟斤拷锟斤拷锟斤拷
  float executedCount = Float.parseFloat(tawwpMonthPlanVO.getExecutedCount()); //锟窖撅拷执锟斤拷锟斤拷锟斤拷
  float inTimeCount = Float.parseFloat(tawwpMonthPlanVO.getInTimeCount()); //锟斤拷时执锟斤拷锟斤拷锟斤拷
  float timeOutCount = Float.parseFloat(tawwpMonthPlanVO.getTimeOutCount()); //锟斤拷时执锟斤拷锟斤拷锟斤拷

  TawwpExecuteAssessVO tawwpExecuteAssessVO = null;
  TawwpExecuteReportVO tawwpExecuteReportVO = null;
%>
<script language="JavaScript">
Ext.onReady(function(){
	colorRows('detailList');
})

Ext.onReady(function(){
	colorRows('WMList');
})

Ext.onReady(function(){
	colorRows('fileList');
})

function onExecute(_executeContentId)
{
  location.href="viewmonthcontent.do?executecontentid=" +  _executeContentId;
}

function onAssessAdd()
{
  var monthPlanId = "<%=tawwpMonthPlanVO.getId()%>";
  location.href="addassess.do?monthplanid=" +  monthPlanId;
}
</script>

<!--  body begin  -->

<table class="listTable" id="detailList">
  <thead>
  <tr>
    <td width="20%" colspan="34" height="10">
      <%=tawwpMonthPlanVO.getYearFlag()%><bean:message key="querymonthplanresult.title.labYearFlag" />
      <%=tawwpMonthPlanVO.getMonthFlag()%><bean:message key="querymonthplanresult.title.labMonthFlag" />
      &lt;&nbsp; <%=tawwpMonthPlanVO.getName()%> &nbsp;&gt;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <font color="red"><bean:message key="querymonthplanresult.title.labCurrentRate" /><%=String.valueOf((executedCount/totalCount)*100)%>% &nbsp;&nbsp;<bean:message key="querymonthplanresult.title.labIntimeRate" /><%=String.valueOf((inTimeCount/executedCount)*100)%>%</font>
    </td>
  </tr>
  <tr>
    <td width="20%"  height="16" align="center"><bean:message key="querymonthplanresult.title.labContent" /></td>
    <td width="5%" height="16" align="center"><bean:message key="querymonthplanresult.title.labCycle" /></td>
    <td width="3%" height="16" align="center"><bean:message key="querymonthplanresult.title.labCount" /></td>
    <%
      for(int i=1; i<(dayCount+1); i++){
    %>
    <td width="3%" height="16">
      <%=i%>
    </td>
    <%
      }
    %>
  </tr>
  </thead>
  <tbody>
  <tr>
  <td width="20%" height="16"> </td>
  <td width="5%" height="16"> </td>
    <td width="3%" height="16"><bean:message key="querymonthplanresult.title.labWeek" /></td>
     
    <%
      for(int j=1; j<(dayCount+1); j++){
        if(week == 7){
    %>
    <td width="3%" height="16">
      <bean:message key="querymonthplanresult.title.labDay" />
    </td>
    <%
        week = 1;
        }
        else
        {
    %>
    <td width="3%" height="16">
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
    <td width="20%" rowspan="2" height="32">
      <%=tawwpMonthExecuteVO.getName()%>
    </td>
    <td width="5%" rowspan="2" height="32">
      <%=tawwpMonthExecuteVO.getCycleName()%>
    </td>
    <td width="3%" height="16"><bean:message key="querymonthplanresult.title.labCount" /></td>
    <%

      char[] temp = (tawwpMonthExecuteVO.getExecuteDate()).toCharArray();
      for(int x=1; x<(temp.length+1); x++){
        if(temp[x-1] == '1'){
          if(x<10){
            tempStr = tawwpMonthExecuteVO.getId()+"0"+ String.valueOf(x);
          }else{
            tempStr = tawwpMonthExecuteVO.getId()+String.valueOf(x);
          }
          tawwpExecuteContentVO = (TawwpExecuteContentVO)(executeContentVOHash.get(tempStr));

    %>
    <td width="3%" height="16">
<%	if(tawwpExecuteContentVO!=null){ %>
      <%--<a href="javascript:onExecute('<%=tawwpExecuteContentVO.getId()%>');">--%>
          <img src="${app }/images/icons/yes.gif">
      </a>
 <%} %>
    </td>
    <%
        }else{
    %>
    <td width="3%" height="16">
    </td>
    <%
        }
      }
    %>
  </tr>
  <tr class="complete">
    <td width="3%" height="16"><bean:message key="querymonthplanresult.title.labExecute" /></td>
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
    <td width="3%" height="16" >
    <img src="${app }/images/icons/yes.gif">
    </td>
    <%
            }
            else
            {
     %>
     <td width="3%" height="16">
     </td>
     <%
            }
          }
          else
          {
     %>
     <td width="3%" height="16">
     </td>
     <%
          }
        }
    %>
  </tr>
  <%
    }
  %>
  </tbody>
</table>

<br>
<br>

<table class="listTable" id="WMList">
  <caption><bean:message key="querymonthplanresult.title.WMFmTitle" /></caption>
  <thead>
  <tr>
    <td width="10%"><bean:message key="querymonthplanresult.title.WMFmType" /></td>
    <td width="19%"><bean:message key="querymonthplanresult.title.WMFmDate" /></td>
    <td width="30%"><bean:message key="querymonthplanresult.title.WMFmContent" /></td>
    <td width="8%"><bean:message key="querymonthplanresult.title.WMFmReportFlag" /></td>
    <td width="20%"><bean:message key="querymonthplanresult.title.WMFmAdvice" /></td>
    <td width="13%"><bean:message key="querymonthplanresult.title.WMFmRemark" /></td>
  </tr>
  </thead>
  <tbody>
  <%
    List executeReportVOList = tawwpMonthPlanVO.getExecuteReportVOList();
    for(int y=0; y<executeReportVOList.size(); y++){
      tawwpExecuteReportVO = (TawwpExecuteReportVO)executeReportVOList.get(y);
      String date = tawwpExecuteReportVO.getStartDate();
      
  %>
  <tr>
    <td width="10%">
      <%=tawwpExecuteReportVO.getReportTypeName()%>
    </td>
    <td width="19%">
      <%=tawwpExecuteReportVO.getStartDate().substring(5,10)%>
      <bean:message key="querymonthplanresult.title.WMFmDateTo" />
      <%=tawwpExecuteReportVO.getEndDate().substring(5,10)%>
    </td>
    <td width="30%">
      <%=tawwpExecuteReportVO.getContent()%>
    </td>
    <td width="8%">
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
    <td width="13%">
      <%=tawwpExecuteReportVO.getRemark()%>
    </td>
  </tr>
  <%
    }
  %>
  </tbody>
 </table>

 <br>
 <br>

<table class="listTable" id="fileList">
  <caption><bean:message key="querymonthplanresult.title.CkFmTitle" /></caption>
  <thead>
  <tr>
    <td width="15%"><bean:message key="querymonthplanresult.title.CkFmCheckUser" /></td>
    <td width="25%"><bean:message key="querymonthplanresult.title.CkFmCrtime" /></td>
    <td width="50%"><bean:message key="querymonthplanresult.title.CkFmContent" /></td>
    <td width="10%"><bean:message key="querymonthplanresult.title.CkFmState" /></td>
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
  <tr>
    <td colspan="4">
        <input type="button" name="b1" value="<bean:message key="querymonthplanresult.title.btnCheckContent" />" onclick="javascript:onAssessAdd();" Class="button">
      </td>
  </tr>
</table>

<!--  body end  -->
<%@ include file="/common/footer_eoms.jsp"%>

