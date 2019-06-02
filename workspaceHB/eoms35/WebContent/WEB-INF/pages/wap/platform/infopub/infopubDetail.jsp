<%@page import="java.util.List,java.util.ArrayList,com.boco.eoms.wap.model.*" pageEncoding="UTF-8"%>
<%@ include file="/wap/common/taglibs.jsp"%>
<%@ page import="com.boco.eoms.base.util.StaticMethod,
                 com.boco.eoms.commons.accessories.service.*,
                 com.boco.eoms.commons.accessories.model.*,
                 com.boco.eoms.base.util.ApplicationContextHolder,
                 java.util.*,com.boco.eoms.commons.accessories.model.TawCommonsAccessoriesConfig" %>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<link href="${app}/wap/css/style.css" rel="stylesheet" type="text/css" />
<title></title>
</head>
<body >
<form action="${app}/wapback.do?method=performWapBack&wapLogin=wap" method="post" >
			<jsp:include page="../../head.jsp" flush="true"/>
			 <% List fileList = (List)request.getAttribute("FILE");
			 %>
	<table border="0" cellspacing="0" cellpadding="0" class="sub_table_bg">
					 	<tr>
								<td class="title_bg_01"><img src="${app}/wap/images/s_ico_03.gif" width="15" height="19" /> 信息发布</td>
						</tr>
						
								
					 	<tr>
							<td class="list_text_03">
								<div class="div_all">
										<table width="100%" border="0" cellpadding="1" cellspacing="1" class="add_table_bg">
					 							
					 							<tr class="fb_xx_head_tr">
													<td class="tt_head_bg"><nobr>标题</nobr></td>
													<td class="fb_add_content">
													      <bean:write name="threadForm" property="title" />
													</td>
													
												</tr>
												
					 								<tr class="fb_xx_head_tr">
					 									<td class="tt_head_bg"><nobr><bean:message key="threadForm.createrId" /></nobr></td>
						 								<td class="fb_add_content">
														   	  <eoms:id2nameDB id="${threadForm.createrId }" beanId="tawSystemUserDao" />
														</td>
					 								</tr>	
					 							
													 <tr class="fb_xx_head_tr">
														  <td class="tt_head_bg" width="20%">
														   <nobr><bean:message key="threadForm.threadTypeId" /></nobr>
														  </td>
													     <td class="fb_add_content" >
													   		<eoms:dict key="dict-workbench-infopub" dictId="threadType" itemId="${threadForm.threadTypeId }" beanId="id2nameXML" />
															</td>
													 </tr>
													  
													  <tr class="fb_xx_head_tr">
														  <td class="tt_head_bg" width="20%">
														   <nobr><bean:message key="threadForm.Validity" /></nobr>
														  </td>
														  <td class="fb_add_content" >
														   	  <bean:write name="threadForm" property="validity" />天
															</td>
													  </tr>
													  
													  <tr class="fb_xx_head_tr">
														  <td class="tt_head_bg" width="20%">
														   <nobr><bean:message key="threadForm.content" /></nobr> 
														  </td>
														  <td class="fb_add_content" colspan=2 > <%--
														   	  <bean:write name="threadForm" property="content" />--%>
														   	  ${threadForm.content}
															</td>
													  </tr>
													  
														<tr class="fb_xx_head_tr">
														  <td class="tt_head_bg" width="20%">
														   <nobr>附件</nobr>
														  </td>
														   <td class="fb_add_content"> 
														    <% 
														    
														    if(fileList!=null){
														    for(int i=0;i<fileList.size();i++){
														     TawCommonsAccessories file=(TawCommonsAccessories)fileList.get(i);
														      String fileName=file.getAccessoriesCnName();
														      String fileId = file.getAccessoriesName();
														     %>
														      <a href='${app}/accessories/tawCommonsAccessoriesConfigs.do?method=download&id=<%=file.getId()%>'>
														         <%=fileName%>
														     </a><br/>
														    <%
														  	  }
														    }
														    %>
														    
														  </td>
													  </tr>
					 
								 		</table>
								 </div>	
							 </td>
						</tr>
					  
					  
								<tr>
								    <td class="btn_bg">
											<table>	
												<tr>
												    <td><input name="Submit222" type="submit" class="btn_02" value="返回" /></td>
												</tr>
											</table>
										</td>
								</tr>
	</table>
</form>		 
</body>
</html>