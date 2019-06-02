<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="com.boco.eoms.workplan.util.TawwpStaticVariable"%>

<%
  String monthPlanId = (String)request.getAttribute("monthplanid");
  String yearFlag = (String)request.getAttribute("yearflag");
  String monthFlag = TawwpStaticVariable.MONTH[Integer.parseInt((String)request.getAttribute("monthflag"))];
  int dayCount = Integer.parseInt((String)request.getAttribute("daycount"));
  String tempStr = "";
%>
<script language="JavaScript">

function GoBack()
{
  window.history.back()
}

function show(){
    document.getElementById("dateselect").style.display = "";
    document.forms[0].reporttype.value = "week";
}

function hidden(){
    document.getElementById("dateselect").style.display = "none";
    document.forms[0].reporttype.value = "month";
}

function onSubmit(){

  if(document.forms[0].reporttype.value == "week"){
    var yearFlag = "<%=yearFlag%>";
    var monthFlag = "<%=monthFlag%>";

    var sDate = document.forms[0].sDate.value;
    var eDate = document.forms[0].eDate.value;

    document.forms[0].startdate.value=yearFlag+"-"+monthFlag+"-"+document.forms[0].sDate.value;
    document.forms[0].enddate.value=yearFlag+"-"+monthFlag+"-"+document.forms[0].eDate.value;
  }else if(document.forms[0].reporttype.value == "month"){
    document.forms[0].startdate.value = "";
    document.forms[0].enddate.value = "";
  }
}

</script>

<form name="reportform" method="POST" action="../tawwpexecute/reportsave.do" onsubmit="onSubmit();">
<!--  body begin  -->
<br>

<table class="formTable">
  <caption><bean:message key="reportadd.title.formTitle" /></caption>
  <tr>
    <td width="100" class="label">
      <bean:message key="reportadd.title.formType" />
    </td>
    <td width="300">
      <input type="radio" name="type" value="week" onclick="javascript:show();"><bean:message key="reportadd.title.radWeekRept" />
      <input type="radio" name="type" value="month" onclick="javascript:hidden();" checked="checked"><bean:message key="reportadd.title.radMonthRept" />
    </td>
  </tr>
  <tr id="dateselect" style="DISPLAY: none">
    <td width="100" class="label">
      
    <%--
      <bean:message key="reportadd.title.formCycle" />
    --%></td>
    <td nowarp>
     <%=yearFlag%><bean:message key="reportadd.title.labYear" /><%=monthFlag%><bean:message key="reportadd.title.labMonth" />
     <select name="sDate" class="select">
       <%
         for(int i=1; i<(dayCount+1); i++){
           tempStr = String.valueOf(i);
           if(tempStr.length()==1){
             tempStr = "0"+ tempStr;
           }
       %>
        <option value="<%=tempStr%>"><%=tempStr%></option>
       <%
         }
       %>
      </select>
      <bean:message key="reportadd.title.labDay" />--->
      <select name="eDate" class="select">
       <%
         for(int j=1; j<(dayCount+1); j++){
           tempStr = String.valueOf(j);
           if(tempStr.length()==1){
             tempStr = "0"+ tempStr;
           }
       %>
        <option value="<%=tempStr%>"><%=tempStr%></option>
       <%
         }
       %>
      </select>
      <bean:message key="reportadd.title.labDay" />
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="reportadd.title.formContent" />
    </td>
    <td>
      <textarea name="content" rows="5" cols="45" class="textarea max"></textarea>
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="reportadd.title.formReportFlag" />
    </td>
    <td width="300">
      <select name="reportflag" class="select">
        <option value="0">否</option>
        <option value="1">是</option>
      </select>
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="reportadd.title.formAdvice" />
    </td>
    <td width="300">
      <textarea name="advice" rows="5" cols="45" class="textarea"></textarea>
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="reportadd.title.formRemark" />
    </td>
    <td width="300">
      <textarea name="remark" rows="5" cols="45" class="textarea"></textarea>
    </td>
  </tr>
  <tr>
    <td colSpan="2">
      <input type="hidden" value="<%=monthPlanId%>" name="monthplanid">
      <input type="hidden" value="" name="startdate">
      <input type="hidden" value="" name="enddate">
      <input type="hidden" value="month" name="reporttype">
    </td>
  </tr>
</table>
<br>
<input type="submit" value="<bean:message key="reportadd.title.btnSubmit" />" name="B1" class="submit">
<input type="button" value="<bean:message key="reportadd.title.btnBack" />" name="B2" Onclick="GoBack();" class="button">

<!-- body end -->
</form>
<%@ include file="/common/footer_eoms.jsp"%>

