<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%> 
<%@ page import ="java.util.List"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpModelPlanVO"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpModelExecuteVO"%>
 
<%
TawwpModelPlanVO tawwpModelPlanVO = (TawwpModelPlanVO)request.getAttribute("modelplan");
TawwpModelExecuteVO tawwpModelExecuteVO = null;
List list = null;
%>
 
<script language="JavaScript">
Ext.onReady(function(){
	colorRows('list-table');
})
function onModelPlanAdd()
{
  location.href="itemadd.do?modelplanid=<%=request.getParameter("modelplanid")%>";
}
function onModelGroupList()
{
  location.href="grouplist.do?modelplanid=<%=request.getParameter("modelplanid")%>";
}
function onModelExport()
{
  location.href="modelexport.do?modelid=<%=request.getParameter("modelplanid")%>&reaction=addons/filedownload.jsp";
}
function onBack()
{
 
 
//  document.modelexecutelist.action= "modellists.do?Mtype='<%=tawwpModelPlanVO.getNetTypeId() %>'";
//  document.modelexecutelist.submit();
    location.href="modellists.do?Mtype=<%=tawwpModelPlanVO.getNetTypeId() %>";
 
}
</script>
 

 
<form name="modelexecutelist">

  <table width="100%" class="listTable" id="list-table">
    <caption>
      	&lt;&nbsp; <%=tawwpModelPlanVO.getName()%> &nbsp;&gt;<bean:message key="itemlist.tawwpmodel.formTitle" />
    </caption>

  <%
  list = tawwpModelPlanVO.getModelExecuteList();
  %>
  <thead>
   <tr>
      <td width="200">作业项目</td>
      <td width="50"><bean:message key="itemlist.tawwpmodel.formCycle" /></td>
      <td width="150"><bean:message key="itemlist.tawwpmodel.formDefault" /></td>
      <td width="150">记录模版</td>
      <td width="150">是否必须上传附件</td>
      <td width="50"><bean:message key="itemlist.tawwpmodel.formEdit" /></td>
      <td width="50"><bean:message key="itemlist.tawwpmodel.formRemove" /></td>
    </tr>
    </thead>
    <tbody>
    <%
    for(int i=0; i<list.size(); i++){
      tawwpModelExecuteVO = (TawwpModelExecuteVO)list.get(i);
    %>
	    <tr>
	      <td><%=tawwpModelExecuteVO.getName()%></td>
	      <td><%=tawwpModelExecuteVO.getCycleName()%></td>
	      <td><%=tawwpModelExecuteVO.getFormat()%></td>
	      <td>
	        <a target="_blank" href="../tawwpaddons/addonsread.do?action=read&window=new&myid=&model=50&addonsid=<%=tawwpModelExecuteVO.getFormId()%>&reaction=/tawwpaddons/addonslist.do">
				<%=tawwpModelExecuteVO.getFormName()%>
	        </a>
	      </td>
	      
	      <td>
	      <%=tawwpModelExecuteVO.getFileFlagName()%>
	      </td>
	      
	      <td>
	        <a href="itemedit.do?modelplanid=<%=tawwpModelPlanVO.getId()%>&modelexecuteid=<%=tawwpModelExecuteVO.getId()%>">
	          <img src="${app }/images/icons/edit.gif">
	        </a>
	      </td>
	      <td>
	        <a href="itemremove.do?modelplanid=<%=tawwpModelPlanVO.getId()%>&modelexecuteid=<%=tawwpModelExecuteVO.getId()%>">
	          <img src="${app }/images/icons/icon.gif" width="20" height="25">
	        </a>
	      </td>
	    </tr>
    <%
    }
    %>
    </tbody>
  </table>

  <br>
  <%--<input type="button" Class="button"  value="<bean:message key="itemlist.tawwpmodel.btnExport" />" onclick="javascript:onModelExport();"  >--%>
  <input type="button" Class="button"  value="<bean:message key="itemlist.tawwpmodel.btnAdd" />" onclick="javascript:onModelPlanAdd();" >
  <input type="button" Class="button"  value="<bean:message key="itemlist.tawwpmodel.btnBack" />" onclick="javascript:onBack();"  >

</form>  
<%@ include file="/common/footer_eoms.jsp"%>
