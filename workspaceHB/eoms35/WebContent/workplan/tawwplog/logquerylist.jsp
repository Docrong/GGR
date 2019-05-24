<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpLogVO"%>

<script language="javascript">

Ext.onReady(function(){
	colorRows('list-table');
})

  function onNetAdd(titleId)
  {
    location.href="netadd.do";
  }

</script>

<%
TawwpLogVO tawwpLogVO = null;
List list = (List)request.getAttribute("loglist");
%>

<table border="0"  width="700" class="listTable" id="list-table">
  <caption><bean:message key="logquerylist.title.formTitle" /></caption>
  <thead>
  <tr>
    <td width="100">
      <bean:message key="logquerylist.title.formCruser" />
    </td>
    <td width="200">
      <bean:message key="logquerylist.title.formCrtime" />
    </td>
    <td width="300">
      <bean:message key="logquerylist.title.formContent" />
    </td>
    <!-- 
    <td width="100">
      <bean:message key="logquerylist.title.formLogTypeName" />
    </td>
     -->
  </tr>
  </thead>
  <tbody>
  <%
  if(list.size() > 0){
    for(int i=0; i<list.size(); i++){
      tawwpLogVO = (TawwpLogVO)list.get(i);
  %>
  <tr class="tr_show">
    <td width="100">
      <%=tawwpLogVO.getCruserName()%>
    </td>
    <td width="200">
      <%=tawwpLogVO.getCrtime()%>
    </td>
    <td width="300">
      <%=tawwpLogVO.getContent()%>
    </td>
    <!-- 
    <td width="100">
      <%=tawwpLogVO.getLogTyepName()%>
    </td>
    -->
  </tr>
  <%
    }
  }
  else{
  %>
  <tr>
    <td width="700" colspan="8">
      <bean:message key="logquerylist.title.nolist" />
    </td>
  </tr>
  <%
  }
  %>
  </tbody>
</table>

<%@ include file="/common/footer_eoms.jsp"%>
