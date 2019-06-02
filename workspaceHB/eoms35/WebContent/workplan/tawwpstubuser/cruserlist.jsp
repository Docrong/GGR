<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpStubUserVO"%>

<%
  List stubUserVOList = (List)request.getAttribute("stubuservolist");
  TawwpStubUserVO tawwpStubUserVO = null;
%>

<script language="JavaScript">
Ext.onReady(function(){
	colorRows('list-table');
})

function onAdd()
{
  location.href="../tawwpstubuser/stubuseradd.do";
}

</script>

<!-- body begin -->

<form name="stubuser">

  <table width="800" class="listTable">
    <caption><bean:message key="cruserlist.title.formTitle" /></caption>
    <thead>
    <tr>
      <td width="150">
        <bean:message key="cruserlist.title.formCruser" />
      </td>
      <td width="150">
        <bean:message key="cruserlist.title.formStubuser" />
      </td>
      <td width="175">
        <bean:message key="cruserlist.title.formStartDate" />
      </td>
      <td width="175">
        <bean:message key="cruserlist.title.formEndDate" />
      </td>
      <td width="50">
        <bean:message key="cruserlist.title.formState" />
      </td>
      <td width="50">
        <bean:message key="cruserlist.title.formEdit" />
      </td>
      <td width="50">
        <bean:message key="cruserlist.title.formToCheck" />
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
        <%=tawwpStubUserVO.getStateName()%>
      </td>
      <td width="50">
        <%
          if("0".equals(tawwpStubUserVO.getState()) || "3".equals(tawwpStubUserVO.getState())){
        %>
        <a href="../tawwpstubuser/stubuseredit.do?stubuserid=<%=tawwpStubUserVO.getId()%>">
         <img src="../images/bottom/an_bj.gif" width="19" height="18" align="absmiddle">
        </a>
        <%
          }
        %>
      </td>
      <td width="50">
        <%
          if("0".equals(tawwpStubUserVO.getState()) || "3".equals(tawwpStubUserVO.getState())){
        %>
        <a href="../tawwpstubuser/stubuserrefer.do?stubuserid=<%=tawwpStubUserVO.getId()%>">
         <img src="../images/bottom/an_pz.gif" width="19" height="18" align="absmiddle">
        </a>
        <%
          }
        %>
      </td>
    </tr>
    <%
      }
    %>
  </tbody>
  </table>

<input type="button" value="<bean:message key="cruserlist.title.btnAdd" />" name="B2" onclick="onAdd();" class="button">

</form>

<!--  body end  -->

<%@ include file="/common/footer_eoms.jsp"%>
