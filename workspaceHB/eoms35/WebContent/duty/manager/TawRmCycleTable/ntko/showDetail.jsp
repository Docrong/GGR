<%@ include file="/common/taglibs.jsp"%>
<%@ include file="../../../../common/header_eoms_form_nobody.jsp"%>
<%@ page import="com.boco.eoms.workplan.util.TawwpStaticVariable"%>
<%@ page import="com.boco.eoms.base.util.StaticMethod,com.boco.eoms.duty.util.DutyMgrLocator" %>
<%

	 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
 	 String id = request.getParameter("id");
 	 String url = request.getParameter("url");
 	 String sell = request.getParameter("sell");
 	 String iterator = request.getParameter("iterator");
 	 String path = "";
 	 String uploadPath="";
 	 if(url.equals("")){  
 	  String timeTag = StaticMethod.getCurrentDateTime("yyyyMMddHHmmss");
	  uploadPath = DutyMgrLocator.getAttributes().getDutyOldPath()+ "/"+timeTag+".xls"; 
	  path = request.getRealPath("/")+uploadPath;
 	 }else{
 	 path=url;
 	 }
 

 %>
<HTML>
 <HEAD>
  <TITLE>  </TITLE>
  <SCRIPT LANGUAGE="JavaScript" src="ntkoocx.js"></SCRIPT>
 </HEAD>
 
<BODY onLoad="javascript:TANGER_OCX_OpenDoc2('<%=basePath+"/"+request.getParameter("path")%>')">
<%
   
%>
<FORM id="myForm" METHOD="POST" ENCTYPE="multipart/form-data" ACTION="uploadDetail.jsp?filePath=<%=path%>">
<input type="hidden" name="id" value="<%=id %>" >
<input type="hidden" name="filePath" value="<%=uploadPath%>" >
<input type="hidden" name="url" value="<%=url%>" >
<input type="hidden" name="iterator" value="<%=iterator%>" >
<TABLE BORDER=0 width = 600>
	<tr> 
		 <td align="left"> <input TYPE=hidden id="filename" name="filename" MAXLENGTH=50 size=50 value="<%=request.getParameter("path")%>">
		<%if(sell.equals(iterator)) {%>
		 <INPUT type=BUTTON VALUE=${eoms:a2u("保存")} class="button" onclick="TANGER_OCX_SaveEditToServer('<%=request.getParameter("path")%>');">	
	   		<font color="red">${eoms:a2u("如果需要保存您填写的内容，请点击左边的保存按钮")} </font>	
	   		</td>	
	 <%} else{ %>
	 	
	 <%} %>
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
         
		<SPAN STYLE="color:red">${eoms:a2u("不能装载文档控件。请在检查浏览器的选项中检查浏览器的安全设置。")}</SPAN>
	</object>
	<script language="JScript" for=TANGER_OCX event="OnDocumentClosed()">
		TANGER_OCX_OnDocumentClosed();
	</script>
	<script language="JScript" for=TANGER_OCX event="OnDocumentOpened(TANGER_OCX_str,TANGER_OCX_obj)">
		TANGER_OCX_OnDocumentOpened(TANGER_OCX_str,TANGER_OCX_obj);
	</script>
	<!-- ä»¥ä¸å½æ°ç¸åºæ§ä»¶çä¸¤ä¸ªäºä»¶:OnDocumentClosed,åOnDocumentOpened -->
	<script language="JScript" for=TANGER_OCX event="OnDocumentClosed()">
		TANGER_OCX_OnDocumentClosed();
	</script>
	<script language="JScript" for=TANGER_OCX event="OnDocumentOpened(TANGER_OCX_str,TANGER_OCX_obj)">
		TANGER_OCX_OnDocumentOpened(TANGER_OCX_str,TANGER_OCX_obj);
	</script>
 </BODY>
</HTML>
