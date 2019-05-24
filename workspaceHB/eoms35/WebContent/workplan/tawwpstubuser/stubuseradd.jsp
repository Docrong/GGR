<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="com.boco.eoms.workplan.util.TawwpStaticVariable"%>
<%@ page import ="java.util.Hashtable"%>
<%@ page import ="java.util.Enumeration"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>

<%
  String user = (String)request.getAttribute("username");
  String currUser = (String)request.getAttribute("curruser");
  Hashtable userHash = (Hashtable)request.getAttribute("userhash");
  Enumeration tempVar = userHash.keys();
  String userId = "";
  String userName = "";
  String time = StaticMethod.getLocalString();
%>
<script language="JavaScript">
function onSubmit()
{
  frmRep = document.forms[0];
  if(frmRep.startdate.value == ''){
    alert('开始时间不能为空！' );
   	return false;
  }
  else if(frmRep.enddate.value == ''){
    alert('结束时间不能为空！' );
    	return false;
  }
  else if(frmRep.stubuser.value == ''){
    alert('申请人不能为空!' );
    	return false;
  }
  else if(frmRep.content.value == ''){
    alert('描述不能为空!' );
    	return false; 
  }
  else if(frmRep.checkuser.value == ''){
    alert('审核人不能为空!' );
   	return false;
  }
  else if(frmRep.startdate.value > frmRep.enddate.value){
    alert('开始时间不能大于结束时间!' );
   	return false;
  } 
  else if(frmRep.checkuser.value == frmRep.stubuser.value){
    alert('申请人与审核人不能为同一人!' );
   	return false;
  }
  else{
    frmRep.action="../tawwpstubuser/stubusersave.do";
    return true;
  }
}
function GoBack()
{
  window.history.back()
}

</script>

<form name="reportform" method="POST">

<!--  body begin  -->

<table border="0" width="400" class="formTable">
  <input type="hidden" name="time" value="<%=time%>">
  <caption><bean:message key="stubuseradd.title.formTitle" /></caption>
  <tr>
    <td width="100" class="label">
      <bean:message key="stubuseradd.title.formCruser" />
    </td>
    <td width="300">
      <%=user%>
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="stubuseradd.title.formStartDate" />
    </td>
    <td width="300">
     <input type="text" name="startdate" size="20"   readonly="true" onclick="popUpCalendar(this, this);" class="text">
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="stubuseradd.title.formEndDate" />
    </td>
    <td width="300">
     <input type="text" name="enddate" size="20"   readonly="true" onclick="popUpCalendar(this, this);" class="text">
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="stubuseradd.title.formAgent" />
    </td>
    <td width="300">
      <select name="stubuser" class="select">
      <%
        while(tempVar.hasMoreElements()){
          userId = (String)tempVar.nextElement();
          userName = (String)userHash.get(userId);
          if(!"".equals(userId) && !currUser.equals(userId)){
      %>
        <option value="<%=userId%>"><%=userName%></option>
      <%
          }
        }
      %>
      </select>
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="stubuseradd.title.formContent" />
    </td>
    <td width="300">
      <textarea name="content" class="textarea max"></textarea>
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="stubuseradd.title.formCheckUser" />
    </td>
    <td width="300">
      <select name="checkuser" class="select">
      <%
        tempVar = userHash.keys();
        while(tempVar.hasMoreElements()){
          userId = (String)tempVar.nextElement();
          userName = (String)userHash.get(userId);
          if(!"".equals(userId)){
      %>
        <option value="<%=userId%>"><%=userName%></option>
      <%
          }
        }
      %>
      </select>
    </td>
  </tr>
  <tr>
    <td colSpan="2">
      <input type="submit" value="<bean:message key="stubuseradd.title.btnSubmit" />" name="B1" Onclick="return onSubmit();" class="submit">
      <input type="button" value="<bean:message key="stubuseradd.title.btnBack" />" name="B2" Onclick="GoBack();" class="button">
    </td>
  </tr>
</table>

<!--  body end  -->
</form>
<%@ include file="/common/footer_eoms.jsp"%>

