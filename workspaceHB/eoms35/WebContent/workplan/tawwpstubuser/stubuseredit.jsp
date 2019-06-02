<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="com.boco.eoms.workplan.util.TawwpStaticVariable"%>
<%@ page import ="java.util.Hashtable"%>
<%@ page import ="java.util.Enumeration"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpStubUserVO"%>

<%
  String currUser = (String)request.getAttribute("curruser");
  TawwpStubUserVO tawwpStubUserVO = (TawwpStubUserVO)request.getAttribute("tawwpstubuservo");
  Hashtable userHash = (Hashtable)request.getAttribute("userhash");
  Enumeration tempVar = userHash.keys();
  String userId = "";
  String userName = "";
%>
<script language="JavaScript">

function GoBack()
{
  window.history.back()
}

function onDelete()
{
  if(confirm("<bean:message key="stubuseredit.title.ifDelete" />"))
  var stubUserId =  "<%=tawwpStubUserVO.getId()%>";
  location.href="../tawwpstubuser/stubuserdel.do?stubuserid=" + stubUserId;
}

</script>

<form name="reportform" method="POST" action="../tawwpstubuser/stubusermodify.do">
<!--  body begin  -->
<br>

<table width="400" class="formTable">
  <caption><bean:message key="stubuseredit.title.formTitle" /></caption>
  <tr>
    <td width="100" class="label">
      <bean:message key="stubuseredit.title.formCruser" />
    </td>
    <td width="300">
      <%=tawwpStubUserVO.getCruserName()%>
    </td>
  </tr>
  <tr id="dateselect">
    <td width="100" class="label">
      <bean:message key="stubuseredit.title.formStartDate" />
    </td>
    <td width="300">
     <input type="text" name="startdate" size="20" onfocus="setday(this)" readonly="true" onclick="popUpCalendar(this, this);" value="<%=tawwpStubUserVO.getStartDate()%>">
    </td>
  </tr>
  <tr id="dateselect">
    <td width="100" class="label">
      <bean:message key="stubuseredit.title.formEndDate" />
    </td>
    <td width="300">
     <input type="text" name="enddate" size="20" onfocus="setday(this)" readonly="true" onclick="popUpCalendar(this, this);" value="<%=tawwpStubUserVO.getEndDate()%>">
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="stubuseredit.title.formAgent" />
    </td>
    <td width="300">
      <select name="stubuser" class="select">
      <%
        while(tempVar.hasMoreElements()){
          userId = (String)tempVar.nextElement();
          userName = (String)userHash.get(userId);
          if(!"".equals(userId) && userId.equals(tawwpStubUserVO.getStubuser())){
      %>
      <option value="<%=userId%>" selected="selected"><%=userName%></option>
      <%
          }else if(!"".equals(userId) && !currUser.equals(userId)){
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
      <bean:message key="stubuseredit.title.formContent" />
    </td>
    <td width="300">
      <textarea name="content" class="textarea max"><%=tawwpStubUserVO.getContent()%></textarea>
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="stubuseredit.title.formCheckUser" />
    </td>
    <td width="300">
      <select name="checkuser" class="select">
      <%
        tempVar = userHash.keys();
        while(tempVar.hasMoreElements()){
          userId = (String)tempVar.nextElement();
          userName = (String)userHash.get(userId);
          if(!"".equals(userId) && userId.equals(tawwpStubUserVO.getCheckuser())){
      %>
      <option value="<%=userId%>" selected="selected"><%=userName%></option>
      <%
          }else if(!"".equals(userId)){
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
      <bean:message key="stubuseredit.title.formState" />
    </td>
    <td width="300">
      <%=tawwpStubUserVO.getStateName()%>
    </td>
  </tr>
  <tr>
    <td colSpan="2">
      <input type="hidden" value="<%=tawwpStubUserVO.getId()%>" name="stubuserid">
      <input type="submit" value="<bean:message key="stubuseredit.title.btnSubmit" />" name="B1" class="submit">
      <input type="button" value="<bean:message key="stubuseredit.title.btnRemove" />" name="B3" class="button" onclick="onDelete();">
      <input type="button" value="<bean:message key="stubuseredit.title.btnBack" />" name="B2" Onclick="GoBack();" class="button">
    </td>
  </tr>
</table>

<!--  body end  -->
</form>
<%@ include file="/common/footer_eoms.jsp"%>

