<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="java.util.Hashtable"%>
<%@ page import ="java.util.Enumeration"%>
<%@ page import ="com.boco.eoms.workplan.util.TawwpStaticVariable"%>
<script type="text/javascript">
function SubmitCheck(){
var time1 = document.tawwplogform.startdate.value;
var time2 = document.tawwplogform.enddate.value;

var setdate,settime,tmptime1,tmptime2;

setdate = time1.split(" ")[0];
settime = time1.split(" ")[1];
tmptime1 = new Date(setdate.split("-")[0],setdate.split("-")[1] - 1,setdate.split("-")[2],settime.split(":")[0],settime.split(":")[1]);

setdate = time2.split(" ")[0];
settime = time2.split(" ")[1];
tmptime2 = new Date(setdate.split("-")[0],setdate.split("-")[1] - 1,setdate.split("-")[2],settime.split(":")[0],settime.split(":")[1]);

var temp = tmptime2.getTime() - tmptime1.getTime();
if(temp<0||temp==0){
  alert("<bean:message key="logquery.title.warnDate" />");
  document.tawwplogform.enddate.focus();
  return false;
}else{
  return true;
  }
}
</script>

<form name="tawwplogform" method="post" action="logquerylist.do" onsubmit='return SubmitCheck()'>

<table class="formTable" id="list-table">
  <caption><bean:message key="logquery.title.formTitle" /></caption>
  <tr>
    <td class="label" width="100">
      <bean:message key="logquery.title.formCruser" />
    </td>
    <td width="400">
      <input type="text" name="cruser" size="40" class="text">
      <font color="red"><bean:message key="logquery.title.example" /></font>
    </td>
  </tr>
  <tr>
    <td class="label">
      <bean:message key="logquery.title.formStartDate" />
    </td>
    <td>
      <input type="text" name="startdate" size="20" onclick="popUpCalendar(this, this,null,null,null,false,-1);" readonly="true"  class="text">
    </td>
  </tr>
  <tr>
    <td class="label">
      <bean:message key="logquery.title.formEndDate" />
    </td>
    <td>
      <input type="text" name="enddate" size="20" onclick="popUpCalendar(this, this,null,null,null,false,-1);" readonly="true" class="text">
    </td>
  </tr>
  <!-- 
  <tr>
    <td class="label">
      <bean:message key="logquery.title.formLogType" />
    </td>
    <td>
      <select size='1' name='logtype' class="select">
        <option  value='0'><bean:message key="logquery.title.formSelLogType" /></option>
      </select>
    </td>
  </tr>
  -->
</table>
<br/>
<input type="submit" value="<bean:message key="logquery.title.formSubmit" />" name="B1" Class="submit">
</form>
<%@ include file="/common/footer_eoms.jsp"%>


