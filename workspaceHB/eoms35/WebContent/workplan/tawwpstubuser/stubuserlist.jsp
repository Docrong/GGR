<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpStubUserVO"%>
<%@ page import ="java.util.Hashtable"%>
<%@ page import ="java.util.Enumeration"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<script language="javascript">
Ext.onReady(function(){
	colorRows('list-table');
})
</script>
<script language="JavaScript">
function onSubmit()
{ 
    document.forms[0].action="../tawwpstubuser/stubuserlist.do";
    return true;
}

</script>


<%
  List stubUserVOList = (List)request.getAttribute("stubuservolist");
  TawwpStubUserVO tawwpStubUserVO = null;
  Hashtable userHash = (Hashtable)request.getAttribute("userhash");
  Hashtable stateHash = (Hashtable)request.getAttribute("STATETYPE");
  Enumeration tempVar = userHash.keys();
  Enumeration tempstateVar = stateHash.keys();
  String userId = "";
  String userName = "";
%>

<!--  body begin  -->

<form name="stubuser" method="POST"> 
<table border="0" width="700" class="formTable">
  <tr>
  
   
    <td width="100" class="label">
      <bean:message key="stubuseradd.title.formCruser" />
    </td>
    <td width="300">
      <select name="checkuser" class="select">
      <option value="0">    </option>
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
    
     <td width="100" class="label">
     代理时间
    </td>
    <td width="300">
     <input type="text" name="stubtime" size="20"   readonly="true" onclick="popUpCalendar(this, this);" class="text">
    </td>
 
  
    <td width="100" class="label">
     <bean:message key="stubuserview.title.formState" />
    </td>
    <td width="300">
      <select name="state" class="select">
      <option value="100">   </option>
      <%
        tempstateVar = stateHash.keys();
        while(tempstateVar.hasMoreElements()){
          String id = (String)tempstateVar.nextElement();
          String name = (String)stateHash.get(id);
          if(!"".equals(userId)){
      %>
        <option value="<%=id%>"><%=name%></option>
      <%
          }
        }
      %>
      </select>
    </td>
  </tr>
  <tr>
    <td colSpan="6">
      <input type="submit" value="查询" name="B1" class="submit">
     
    </td>
  </tr>
</table>
	<br>

  <table width="700" class="listTable" id="list">
    <caption><bean:message key="stubuserlist.title.formTitle" /></caption>
    <thead>
    <tr>
      <td width="150">
        <bean:message key="stubuserlist.title.formCruser" />
      </td>
      <td width="150">
        <bean:message key="stubuserlist.title.formAgent" />
      </td>
      <td width="175">
        <bean:message key="stubuserlist.title.formStartTime" />
      </td>
      <td width="175">
        <bean:message key="stubuserlist.title.formEndTime" />
      </td>
      <td width="50">
        <bean:message key="stubuserlist.title.formView" />
      </td>
    </tr>
    </thead>
    <tbody>

    <%
      for(int i=0; i<stubUserVOList.size(); i++){
        tawwpStubUserVO = (TawwpStubUserVO)stubUserVOList.get(i);
    %>
    <tr>
      <td width="150">
        <%=tawwpStubUserVO.getCruserName()%>
      </td>
      <td width="150">
        <%=tawwpStubUserVO.getStubuserName()%>
      </td>
      <td width="175">
        <%=tawwpStubUserVO.getStartDate()%>
      </td>
      <td width="175">
        <%=tawwpStubUserVO.getEndDate()%>
      </td>
      <td width="50">
        <a href="../tawwpstubuser/stubuserview.do?stubuserid=<%=tawwpStubUserVO.getId()%>">
         <img src="../images/bottom/an_pz.gif" width="19" height="18" align="absmiddle">
        </a>
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
