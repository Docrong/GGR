<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpYearExecuteVO"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>

<%
  List yearExecuteVOList = (ArrayList)request.getAttribute("yearexecutevolist");
  String monthPlanId = (String)request.getAttribute("monthplanid");
  TawwpYearExecuteVO tawwpYearExecuteVO = null;
%>

<script language="JavaScript">
Ext.onReady(function(){
	colorRows('listTable');
})

function GoBack()
{
  window.history.back();
}

function onAddExecute(_yearExecuteId)
{
  if(confirm("<bean:message key="monthexecuteadd.title.warnAddConfirm" />"))
  {
    var monthPlanId = "<%=monthPlanId%>";
    location.href="../tawwpmonth/monthexecutesave.do?yearexecuteid=" + _yearExecuteId + "&monthplanid=" + monthPlanId;
  }
}

</script>

<!--  body begin  -->

<br>

<br>

<table class="listTable" id="listTable">
  <caption><bean:message key="monthexecuteadd.title.formTitle" /></caption>
  <thead>
  <tr>
    <td width="25%">
     作业项目
    </td>
    
    <td width="25%">
     业务类型
    </td>
    <td width="25%">
     执行单位级别
    </td>
    <td width="25%">
     适用说明
    </td>
    
    <td width="25%">
     执行周期
    </td>
    <td width="20%">
      <bean:message key="monthexecuteadd.title.formFormat" />
    </td>
    <!--
    <td width="15%">
      执行表格
    </td>
    -->
    <td width="20%">
      执行帮助
    </td>
    <td width="10%">
      <bean:message key="monthexecuteadd.title.formAdd" />
    </td>
  </tr>
  </thead>
  <tbody>
  <%
    for(int i=0; i<yearExecuteVOList.size(); i++){
       tawwpYearExecuteVO = (TawwpYearExecuteVO)(yearExecuteVOList.get(i));
  %>
  <tr>
    <td width="25%">
      <%=tawwpYearExecuteVO.getName()%>
    </td>
    <td width="25%">
      <%=tawwpYearExecuteVO.getBotype() %>
    </td>
    <td width="25%">
      <%=tawwpYearExecuteVO.getExecutedeptlevel() %>
    </td>
    <td width="25%">
      <%=tawwpYearExecuteVO.getAppdesc() %>
    </td>
    <td width="10%">
      <%=tawwpYearExecuteVO.getCycleName() %>
    </td>
    <td width="20%">
      <%=tawwpYearExecuteVO.getFormat()%>
    </td>
    <%--
    <td width="15%">
      <%=tawwpYearExecuteVO.getFormName()%>
    </td>
    --%>
    <td width="20%">
      <%=StaticMethod.null2String(tawwpYearExecuteVO.getRemark())%>
    </td>
    <td width="10%">
      <a href="javascript:onAddExecute('<%=tawwpYearExecuteVO.getId()%>');">
        <img src="${app }/images/icons/layout_add.png" width="20" height="27">
      </a>
    </td>
  </tr>
  <%
    }
  %>
  </tbody>
</table>

<br>
<input type="button" value="<bean:message key="monthexecuteadd.title.btnBack" />" name="B1" class="button" onclick="javascript:GoBack();">

<!--   body end   -->

<%@ include file="/common/footer_eoms.jsp"%>

