<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%> 
<%@ page import ="com.boco.eoms.base.util.StaticMethod"%>
<%
	  int yearFlag = StaticMethod.nullObject2int(request.getAttribute("yearflag"));
      int monthFlag = StaticMethod.nullObject2int(request.getAttribute("monthflag")); 
      int dayNow = StaticMethod.nullObject2int(request.getAttribute("dayNow"));
	  int yearNow = StaticMethod.nullObject2int(request.getAttribute("yearNow"));
      int monthNow = StaticMethod.nullObject2int(request.getAttribute("monthNow")); 
 	  int dayFlag = StaticMethod.nullObject2int(request.getAttribute("dayFlag"));
      String str =  StaticMethod.nullObject2String(request.getAttribute("str"));
%>
 <script type='text/javascript'>
 function onMonth(){
 	var dayNow=Number(<%=dayNow%>);
	var yearNow=Number(<%=yearNow%>);
 	var monthNow=Number(<%=monthNow%>);
 	var monthSelect=Number(document.forms[0].monthflag.value);
 	var yearSelect=Number(document.forms[0].yearflag.value);
 	var dayFlag=Number(<%=dayFlag%>);
  	if(yearNow==yearSelect && monthNow==monthSelect && dayNow<=dayFlag){
 		alert("您现在所统计的作业计划中尚有部分还未到达执行期限，如果您需要准确的统计数据，请您在本月“" + dayFlag + "号”以后进行！");
 		document.statyear.submit();
 	}else{
 		document.statyear.submit();
 	}
 }
 </script>

 <form name='statyear'>
<table width='700' align=center class="listTable" >
    <thead>
    <tr>
        <select size='1' id='yearflag' name='yearflag' class="select">
        <% for (int i = yearNow-5;i<=yearNow;i++) {
        	if(i == yearFlag){
        %>
          <option value='<%=i %>' selected><%=i %></option>
          <%}else{ %>
          <option value='<%=i %>'><%=i %></option>
          <% }}%>
        </select><bean:message key="statreportall.title.labYear" />
        <select size='1' id='monthflag' name='monthflag' class="select">
        <% for (int j = 1;j<=monthNow;j++) {
        	if(j == monthFlag){
        %>
           <option value='<%=j %>'selected><%=j %></option>
          <%}else{ %>
          <option value='<%=j %>'><%=j %></option>
          <% }}%>
        </select><bean:message key="statreportall.title.labMonth" />
        <input type="button" value="<bean:message key="statreportall.title.btnOnMonth" />" name="B1" onclick="onMonth()"  Class="button">
    </tr>
    </thead>
  </table>
 </form>
 <table class="listTable">
 <thead>
 <tr >
 <td rowspan='2'><bean:message key="statreportall.title.labDeptName" /></td><td colspan='3'><bean:message key="statreportall.title.labDay" /></td>
 <td colspan='3'><bean:message key="statreportall.title.labWeek" /></td><td colspan='3'><bean:message key="statreportall.title.labHalfMonth" /></td>
 <td colspan='3'><bean:message key="statreportall.title.labMonth" /></td><td colspan='3'><bean:message key="statreportall.title.labQuarterYear" /></td>
 <td colspan='3'><bean:message key="statreportall.title.labHalfYear" /></td><td colspan='3'><bean:message key="statreportall.title.labYear" /></td>
 <td colspan='3'><bean:message key="statreportall.title.labTemp" /></td><td colspan='3'><bean:message key="statreportall.title.labAllCount" /></td>
 <td rowspan='2'><bean:message key="statreportall.title.labInTimeRate" /></td>
 <td rowspan='2'><bean:message key="statreportall.title.labFinishRate" /></td>
 <td rowspan='2'><bean:message key="statreportall.title.labConstituteCount" /></td>
 <td rowspan='2'><bean:message key="statreportall.title.labScore" /></td>
 </tr>
 <tr  ><td ><bean:message key="statreportall.title.labAllCounts" /></td>
 <td ><bean:message key="statreportall.title.labInTimeCounts" /></td>
 <td ><bean:message key="statreportall.title.labFinishCounts" /></td>
 <td ><bean:message key="statreportall.title.labAllCounts" /></td>
 <td ><bean:message key="statreportall.title.labInTimeCounts" /></td>
 <td ><bean:message key="statreportall.title.labFinishCounts" /></td>
 <td ><bean:message key="statreportall.title.labAllCounts" /></td>
 <td ><bean:message key="statreportall.title.labInTimeCounts" /></td>
 <td ><bean:message key="statreportall.title.labFinishCounts" /></td>
 <td ><bean:message key="statreportall.title.labAllCounts" /></td>
 <td ><bean:message key="statreportall.title.labInTimeCounts" /></td>
 <td ><bean:message key="statreportall.title.labFinishCounts" /></td>
 <td ><bean:message key="statreportall.title.labAllCounts" /></td>
 <td ><bean:message key="statreportall.title.labInTimeCounts" /></td>
 <td ><bean:message key="statreportall.title.labFinishCounts" /></td>
 <td ><bean:message key="statreportall.title.labAllCounts" /></td>
 <td ><bean:message key="statreportall.title.labInTimeCounts" /></td>
 <td ><bean:message key="statreportall.title.labFinishCounts" /></td>
 <td ><bean:message key="statreportall.title.labAllCounts" /></td>
 <td ><bean:message key="statreportall.title.labInTimeCounts" /></td>
 <td ><bean:message key="statreportall.title.labFinishCounts" /></td>
 <td ><bean:message key="statreportall.title.labAllCounts" /></td>
 <td ><bean:message key="statreportall.title.labInTimeCounts" /></td>
 <td ><bean:message key="statreportall.title.labFinishCounts" /></td>
 <td ><bean:message key="statreportall.title.labAllCounts" /></td>
 <td ><bean:message key="statreportall.title.labInTimeCounts" /></td>
 <td ><bean:message key="statreportall.title.labFinishCounts" /></td>
 </tr>
 </thead>
	<%=str%>
 </table >
 <br>
 
 <%@ include file="/common/footer_eoms.jsp"%>