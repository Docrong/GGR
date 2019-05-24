<%@page import="java.util.List,java.util.ArrayList,com.boco.eoms.wap.model.*" pageEncoding="UTF-8"%>
<%@ include file="/wap/common/taglibs.jsp"%>
<%@ page import="com.boco.eoms.base.util.StaticMethod,
                 com.boco.eoms.commons.accessories.service.*,
                 com.boco.eoms.commons.accessories.model.*,
                 com.boco.eoms.base.util.ApplicationContextHolder,
                 java.util.*,com.boco.eoms.commons.accessories.model.TawCommonsAccessoriesConfig" %>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpExecuteContentVO"%>
<%@ page import ="com.boco.eoms.workplan.util.TawwpUtil"%>
<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
		<link href="${app}/wap/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%
  List executeContentVOList = (List)request.getAttribute("executecontentvolist");
  String flag = (String)request.getAttribute("flag");
  String monthPlanId = (String)request.getAttribute("monthplanid");
  String userByStub = (String)request.getAttribute("userbystub");
  String date = (String)request.getAttribute("date");
  TawwpExecuteContentVO tawwpExecuteContentVO = null;
  String contentIdStr = "";
  String contentFlag="";
  for(int i=0; i<executeContentVOList.size(); i++){
    contentIdStr += (((TawwpExecuteContentVO)executeContentVOList.get(i)).getId() + ",");
    if((((TawwpExecuteContentVO)executeContentVOList.get(i)).getExecuteType().equals("1")))
    {
      contentFlag="1";
    }
  }
  
  String executeType = "";
  contentIdStr = contentIdStr.substring(0,contentIdStr.length()-1);
  String currUser = (String)request.getAttribute("curruser");
%>

<form name="executeadds" method="post" action="../wap/workplan.do?method=executeSaves&executetype=<%=executeType%>">
<jsp:include page="../../head.jsp" flush="true"/>
  <table border="0" cellspacing="0" cellpadding="0" class="sub_table_bg">
  						<tr>
								    <td class="title_bg_01"><img src="${app}/wap/images/s_ico_02.gif" /> 作业计划</td>
							</tr>
  	<%--
    <tr>
      <td bgcolor='<bean:message key = "wap.common.style.title.backgrond" />' width="30%">
        作业项目
      </td>
      <td bgcolor='<bean:message key = "wap.common.style.title.backgrond" />' width="70%">
        内容附件
        
      </td>
    </tr>
								
    --%>
    <tr>
      <td class="list_text_03">
      	<div class="div_all">
					<table  width="100%" border="0" cellpadding="1" cellspacing="1" class="add_table_bg">
    <%
      for(int i=0; i<executeContentVOList.size(); i++){
        tawwpExecuteContentVO = (TawwpExecuteContentVO)executeContentVOList.get(i);
        String writeDate = "";
        if(tawwpExecuteContentVO.getWriteDate()!=null&&!tawwpExecuteContentVO.getWriteDate().equals("")){
            writeDate = tawwpExecuteContentVO.getWriteDate();
        }
    %>
				
    
						  <tr class="fb_xx_head_tr">
						  	<td class="tt_head_bg">
						        <nobr><%=tawwpExecuteContentVO.getName()%></nobr>
						        <input type="hidden" name="executecontentuserid<%=i%>" value="<%=tawwpExecuteContentVO.getExecuteContentUserId()%>">
						        <input type="hidden" name="executecontentid<%=i%>" value="<%=tawwpExecuteContentVO.getId()%>">
						        <%
						          if("1".equals(tawwpExecuteContentVO.getStubFlag())){
						        %>
						        <bean:message key="stubuseradd.title.formAgent" />
						        <input type="hidden" name="userid<%=i%>" value="<%=tawwpExecuteContentVO.getUserByStub()%>">
						        <input type="hidden" name="stubuser<%=i%>" value="<%=currUser%>">
						        <%
						          }
						          else
						          {
						        %>
						        <input type="hidden" name="userid<%=i%>" value="<%=currUser%>">
						        <input type="hidden" name="stubuser<%=i%>" value="">
						        <%
						          }
						        %>
						     	</td>
						        <input type="hidden" name="writeDate<%=i%>" size="20" value="<%=StaticMethod.getCurrentDateTime()%>">
						      <td class="fb_add_content">
						        <textarea name="content<%=i%>"  rows=2  value=""><%=tawwpExecuteContentVO.getContent()%></textarea>
						      </td>
						     </tr>
						     
							     <tr class="fb_xx_head_tr">						
							      <td class="tt_head_bg">
							      <nobr>附件</nobr> 
							      </td>
							        <%
							          if(!"".equals(tawwpExecuteContentVO.getFileName())){
							            String[] tempStr = tawwpExecuteContentVO.getFileName().split(",");
							            for(int j=0; j<tempStr.length; j++){
							               String[] tempArray = tempStr[j].split("@");
							               String tempArray1 = tempArray[1].substring(2);
							        %>
							        	<td><a href='${app}/workplan<%=tempArray1%>'><%=tempArray[0]%></a>
							        	</td>
							        <%
							            }
							          }else{
							        %>
							        <td></td>
							     			<%}%>
							    </tr>
						    
						    <%
						      }
						    %>
						    
						    
										</table>
										</div>
									 </td>
								</tr>
						 <input type="hidden" value="<%=contentIdStr%>" name="contentidstr">
						 <input type="hidden" value="<%=monthPlanId%>" name="monthplanid">
						 <input type="hidden" value="<%=flag%>" name="flag">
						 <input type="hidden" value="<%=userByStub%>" name="userbystub">
						 <input type="hidden" value="<%=date%>" name="date">
						
						<tr>
									<td class="btn_bg">
										<table>
										  <tr>
												<td><input type="submit" value="提交" class="btn_03" name='s'>
												<input name="Button" type="button" value="返回" class="btn_03" 
																		onclick="javascript:{var url='${app}/wapback.do?method=performWapBack&wapLogin=wap';location.href=url}"/>	
												</td>
											</tr>
										</table>
									</td>
								 </tr>
						
<%@ include file="/common/footer_eoms.jsp"%>
</table>
<body>
</html>