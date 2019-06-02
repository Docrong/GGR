<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpExecuteContentUserVO"%>

<%
  List executeContentUserVOList = (List)request.getAttribute("executecontentuservolist");
  TawwpExecuteContentUserVO tawwpExecuteContentUserVO = null;
%>

<script language="JavaScript">
Ext.onReady(function(){
	colorRows('listTable');
})

function onAddons(url)
{
  var _sHeight = 600;
  var _sWidth = 800;
  var sTop=(window.screen.availHeight-_sHeight)/2;
  var sLeft=(window.screen.availWidth-_sWidth)/2;
  var sFeatures="dialogHeight: "+_sHeight+"px; dialogWidth: "+_sWidth+"px; dialogTop: "+sTop+"; dialogLeft: "+sLeft+"; center: Yes; scroll:Yes;help: No; resizable: No; status: No;";
  window.showModalDialog(url,window,sFeatures);
}

</script>

<!--  body begin  -->

<form name="sameexecute">

<br>

  <table class="listTable" id="listTable">
    <caption><bean:message key="sameview.title.formTitle"/></caption>
    <thead>
    <tr>
      <td width="20%"><bean:message key="sameview.title.formExecuter"/></td>
      <td width="40%"><bean:message key="sameview.title.formContent"/></td>
      <td width="20%"><bean:message key="sameview.title.formFileName"/></td>
      <td width="20%"><bean:message key="sameview.title.formForm"/></td>
    </tr>
    </thead>
    <tbody>
	<%System.err.println(executeContentUserVOList.size());%>
    <%
      for(int i=0; i<executeContentUserVOList.size(); i++){
        tawwpExecuteContentUserVO = (TawwpExecuteContentUserVO)executeContentUserVOList.get(i);
    %>

    <tr>
      <td width="20%">
        <%=tawwpExecuteContentUserVO.getUserName()%>
      </td>
      <td width="20%">
        <%=tawwpExecuteContentUserVO.getContent()%>
      </td>
      <td width="20%">
        <%
        if(tawwpExecuteContentUserVO.getFileName().length()>0)
        {
          String[] tempStr = tawwpExecuteContentUserVO.getFileName().split(",");
          for(int j=0; j<tempStr.length; j++){
             String[] tempArray = tempStr[j].split("@");
        %>
        <a href="do_download.jsp?id=<%=tempArray[2]%>"><%=tempArray[0]%></a><br>
        <%--
        <a href="do_download.jsp?path=..<%=(request.getContextPath()+"/workplan")%><%=(tempArray[1].substring(2,tempArray[1].length()))%>&name=<%=tempArray[0]%>"><%=tempArray[0]%></a><br>
        --%>
        <%
          }
        }
        %>
      </td>
      <td width="20%">
        <%
      if("".equals(tawwpExecuteContentUserVO.getFormDataId())){
    %>
      <bean:message key="sameview.title.labNot"/>
    <%
      }else{
    %>
      <a href="javascript:onAddons('../tawwpaddons/addonsread.do?action=read&executeId=<%=tawwpExecuteContentUserVO.getId() %>&reaction=/tawwpexecute/redirection.jsp');"><%=tawwpExecuteContentUserVO.getFormName()%></a>
      <input type="hidden" name="formdataid" value="<%=tawwpExecuteContentUserVO.getFormDataId()%>">
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

</form>

<!--  body end  -->
<%@ include file="/common/footer_eoms.jsp"%>

