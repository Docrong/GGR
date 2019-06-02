<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpStubUserVO"%>
<script type="text/javascript">
<!--
Ext.onReady(function(){
	colorRows('list-table');
})
//-->
</script>

<%
  List stubUserVOList = (List)request.getAttribute("stubuservolist");
  TawwpStubUserVO tawwpStubUserVO = null;
%>

<!-- body begin  -->

<form name="stubuser">

  <table width="700" class="listTable">
    <caption><bean:message key="checklist.title.formStubTitle" /></caption>
    <thead>
    <tr>
      <td width="150">
        <bean:message key="checklist.title.formStubCruser" />
      </td>
      <td width="150">
        <bean:message key="checklist.title.formStubUser" />
      </td>
      <td width="175">
        <bean:message key="checklist.title.formStubStartT" />
      </td>
      <td width="175">
        <bean:message key="checklist.title.formStubEndT" />
      </td>
      <td width="50">
        <bean:message key="checklist.title.formCheck" />
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
        <a href="../tawwpstubuser/checkview.do?stubuserid=<%=tawwpStubUserVO.getId()%>">
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

