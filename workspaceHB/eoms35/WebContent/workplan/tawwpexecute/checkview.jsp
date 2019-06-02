<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpExecuteContentVO"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpExecuteFileVO"%>
<%@ page import ="com.boco.eoms.workplan.util.TawwpUtil"%>
<%@ page import ="java.util.List"%>
<%
  TawwpExecuteContentVO tawwpExecuteContentVO = (TawwpExecuteContentVO)request.getAttribute("tawwpexecutecontentvo");
  List list = tawwpExecuteContentVO.getExecuteFileListVO();
  TawwpExecuteFileVO  tawwpExecuteFileVO = null;
%>
<script language="JavaScript">
function onAddons(url)
{
  var _sHeight = 600;
  var _sWidth = 820;
  var sTop=(window.screen.availHeight-_sHeight)/2;
  var sLeft=(window.screen.availWidth-_sWidth)/2;
  var sFeatures="dialogHeight: "+_sHeight+"px; dialogWidth: "+_sWidth+"px; dialogTop: "+sTop+"; dialogLeft: "+sLeft+"; center: Yes; scroll:Yes;help: No; resizable: No; status: No;";
  window.showModalDialog(url,window,sFeatures);
}
</script>

<!--  body begin  -->
<br>

<table class="formTable">
  <caption><bean:message key="viewmonthcontent.title.formTitle" /></caption>
  <tr>
    <td width="100" class="label"><bean:message key="viewmonthcontent.title.formName" /></td>
    <td width="310">
      <%=tawwpExecuteContentVO.getName()%>
    </td>
  </tr>
  <tr>
    <td width="100" class="label"><bean:message key="viewmonthcontent.title.formStartDate" /></td>
    <td width="310">
      <%=tawwpExecuteContentVO.getStartDate()%>
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="viewmonthcontent.title.formCrtime" />
    </td>
    <td width="310">
      <%=tawwpExecuteContentVO.getCrtime()%>
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="viewmonthcontent.title.formCruser" />
    </td>
    <td width="310">
      <%=tawwpExecuteContentVO.getCruser()%>
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="viewmonthcontent.title.formContent" />
    </td>
    <td width="310">
      <textarea name="content" class="textarea max" readonly="readonly"><%=tawwpExecuteContentVO.getContent()%></textarea>
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
     <bean:message key="viewmonthcontent.title.formRemark" />
    </td>
    <td width="310">
      <textarea name="remark" class="textarea max" readonly="readonly"><%=tawwpExecuteContentVO.getRemark()%></textarea>
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="viewmonthcontent.title.formAddons" />
    </td>
    <td width="310">
       <%
      if(!"".equals(tawwpExecuteContentVO.getFormDataId())){
    %>
    	<a href="javascript:onAddons('../tawwpaddons/addonsread.do?action=read&window=new&executeId=<%=tawwpExecuteContentVO.getId() %>&reaction=../tawwpexecute/redirection.jsp');"><%=tawwpExecuteContentVO.getFormName()%></a>
    <%
      }
    %>
    </td>
  </tr>
  <tr>
    <td width="100" class="label">
      <bean:message key="viewmonthcontent.title.formFile" />
    </td>
    <td width="310">
      <%
          if(!"".equals(tawwpExecuteContentVO.getFileName())){
            String[] tempStr = tawwpExecuteContentVO.getFileName().split(",");
            for(int j=0; j<tempStr.length; j++){
               String[] tempArray = tempStr[j].split("@");
        %>
        <%--
        <a href="do_download.jsp?id=<%=tempArray[1]%>"><%=tempArray[0]%></a><br>
        --%>
        <a href="<%=tempArray[1]%>"><%=tempArray[0]%></a><br>
        <%
            }
          }
        %>
    </td>
  </tr>
</table>
<br />
<input type="button" value="<bean:message key="viewmonthcontent.title.btnBack" />" onclick="javascript:window.history.back();" class="button">

<!--  body end  -->
<%@ include file="/common/footer_eoms.jsp"%>
