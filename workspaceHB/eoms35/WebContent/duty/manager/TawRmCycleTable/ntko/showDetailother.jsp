<%@ include file="/common/taglibs.jsp"%>
<%@ page import="com.boco.eoms.workplan.util.TawwpStaticVariable"%>
<%

	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
 %>
<HTML>
 <HEAD>
  <TITLE>  </TITLE>
  <SCRIPT LANGUAGE="JavaScript" src="ntkoocx.js"></SCRIPT>
 </HEAD>
 
<BODY onLoad="javascript:TANGER_OCX_OpenDoc2('<%=basePath+"/"+request.getParameter("path")%>')">
<%
   
%>
<FORM id="myForm" METHOD="POST" ENCTYPE="multipart/form-data" ACTION="uploadDetail.jsp?filePath=<%=TawwpStaticVariable.wwwDir+request.getParameter("path")%>">
<input type="hidden" name="id" value="" >
<input type="hidden" name="filePath" value="" >
<TABLE BORDER=0 width = 600>
	<tr> 
		 <td align="center"> <input TYPE=hidden id="filename" name="filename" MAXLENGTH=50 size=50 value="<%=request.getParameter("path")%>">
		 <INPUT type=BUTTON VALUE=${eoms:a2u("保存")}  onclick="TANGER_OCX_SaveEditToServer('<%=request.getParameter("path")%>');"></td>		
	   		
	 
	</tr>
	</TABLE>
</form>
 
	 
  <object id="TANGER_OCX" classid="clsid:C9BC4DFF-4248-4a3c-8A49-63A7D317F404" codebase="OfficeControl.ocx#version=3,0,0,7" width="100%" height="100%">
        <param name="BorderStyle" value="1">
        <param name="Titlebar" value="false">
        <param name="BorderColor" value="14402205">        
	 	<param name="TitlebarColor" value="14402205">
        <param name="TitlebarTextColor" value="0">	 
        <param name="IsShowToolMenu" value="-1">
        <param name="IsNoCopy" value="-1">
		<SPAN STYLE="color:red">不能装载文档控件。请在检查浏览器的选项中检查浏览器的安全设置。</SPAN>
	</object>
	<script language="JScript" for=TANGER_OCX event="OnDocumentClosed()">
		TANGER_OCX_OnDocumentClosed();
	</script>
	<script language="JScript" for=TANGER_OCX event="OnDocumentOpened(TANGER_OCX_str,TANGER_OCX_obj)">
		TANGER_OCX_OnDocumentOpened(TANGER_OCX_str,TANGER_OCX_obj);
	</script>
	<!-- 以下函数相应控件的两个事件:OnDocumentClosed,和OnDocumentOpened -->
	<script language="JScript" for=TANGER_OCX event="OnDocumentClosed()">
		TANGER_OCX_OnDocumentClosed();
	</script>
	<script language="JScript" for=TANGER_OCX event="OnDocumentOpened(TANGER_OCX_str,TANGER_OCX_obj)">
		TANGER_OCX_OnDocumentOpened(TANGER_OCX_str,TANGER_OCX_obj);
	</script>
 </BODY>
</HTML>
