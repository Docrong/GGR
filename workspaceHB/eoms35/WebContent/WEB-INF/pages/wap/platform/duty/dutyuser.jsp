<%@page import="java.util.List,java.util.ArrayList,com.boco.eoms.wap.model.*" pageEncoding="UTF-8"%>
<%@ include file="/wap/common/taglibs.jsp"%>
<%@ page import="com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom" %>
<%@ page language="java"
	import="java.util.*,java.lang.*,org.apache.struts.util.*,com.boco.eoms.common.util.StaticMethod,com.boco.eoms.commons.system.user.model.TawSystemUser"%>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<link href="${app}/wap/css/style.css" rel="stylesheet" type="text/css" />
<title></title>
</head>
<body >
 <form action="${app}/wap/dutyroom.do?method=roomselect" method="post" >
 <jsp:include page="../../head.jsp" flush="true"/>
 <%
 	String roomName = (String)request.getAttribute("ROOMNAME");
 	String dutyDate = (String)request.getAttribute("DUTYDATE");
 	String roomId = (String)request.getAttribute("ROOMID");
 	String day = (String)request.getAttribute("DAY");
 	
 %>
 <%
    Vector vectorQueryResult = (Vector) request
   .getAttribute("QUERYRESULT");
   Vector getDutydate = (Vector) vectorQueryResult.elementAt(0);
   Vector getStarttime = (Vector) vectorQueryResult.elementAt(1);
	 Vector getEndtime = (Vector) vectorQueryResult.elementAt(2);
	 Vector getDutymaster = (Vector) vectorQueryResult.elementAt(3);
	 Vector getDutyman = (Vector) vectorQueryResult.elementAt(4);
			%>
			
<table border="0" cellspacing="0" cellpadding="0" class="sub_table_bg">
					<tr>
							<td class="btn_bg">
								<table>
								  <tr>
									<td><input name="Button" type="button" value="返回" class="btn_03" 
							onclick="javascript:{var url='${app}/wap/dutyroom.do?method=roomselect';location.href=url}"/></td>
									<td><input name="Button" type="button" value="前一天" class="btn_03" 
							onclick="javascript:{var url='${app}/wap/dutyuser.do?method=dutyUser&id=<%=roomId%>&day=-1&dutyDate=<%=dutyDate%>';location.href=url}"/></td>
									<td><input name="Button" type="button" value="当天" class="btn_03" 
							onclick="javascript:{var url='${app}/wap/dutyuser.do?method=dutyUser&id=<%=roomId%>';location.href=url}"/></td>
									<td><input name="Button" type="button" value="后一天" class="btn_03" 
							onclick="javascript:{var url='${app}/wap/dutyuser.do?method=dutyUser&id=<%=roomId%>&day=1&dutyDate=<%=dutyDate%>';location.href=url}"/></td>
								  </tr>
								</table>
							</td>
			  	</tr>
 	<!--<tr >
    <td colspan="2" class="title_bg_01"><a href="${app}/wap/dutyroom.do?method=roomselect" >返回</a> 
    
    <a href="${app}/wap/dutyuser.do?method=dutyUser&id=<%=roomId%>&day=-1&dutyDate=<%=dutyDate%>" >前一天</a>
    <a href="${app}/wap/dutyuser.do?method=dutyUser&id=<%=roomId%>" >当天</a>
    <a href="${app}/wap/dutyuser.do?method=dutyUser&id=<%=roomId%>&day=1&dutyDate=<%=dutyDate%>" >后一天</a>
    </td>
  </tr>-->
  
  <tr>
    <td class="title_bg_01"><img src="images/s_ico_05.gif" width="15" height="19" /><%=roomName%>(<%=dutyDate%>)
		</td>
  </tr>
  
  	<%
   if (getDutydate.size() == 0) {
   %>
   <tr>
    <td class="title_bg_01">&nbsp&nbsp本机房没有值班信息
		</td>
  </tr>
   
   
 <%
 }else{
   for(int i=0;i<getDutydate.size();i++){
  
   %>
   
			<table width="100%" border="1" cellspacing="0" cellpadding="0" class="sub_table_bg">
			    <tr>
			    	<td colSpan="2" class="tt_head_bg">&nbsp&nbsp&nbsp时间:&nbsp&nbsp&nbsp
			    			<%=String.valueOf(getStarttime.elementAt(i))
													.substring(11, 19)%>-<%=String.valueOf(getEndtime.elementAt(i))
													.substring(11, 19)%>
						</td>
			  	</tr>
   	
		
  <%
  List list = (List)getDutyman.elementAt(i);
  if(list!=null){
  for(int j=0;j<list.size();j++){
  TawSystemUser tawSystemUser = (TawSystemUser)list.get(j);
  %>
  
   		
					
						  <tr>
							<td colSpan="2" class="tt_head_bg">&nbsp&nbsp&nbsp姓名:
								 &nbsp&nbsp&nbsp <a href="wtai://wp/ap;<%= StaticMethod.null2String(tawSystemUser.getMobile())%>;<%=StaticMethod.null2String(tawSystemUser.getUsername())%>">
					    				 <%=StaticMethod.null2String(tawSystemUser.getUsername())%>
					      </a>
							</td>
						  </tr>
						  <tr>
							<td colSpan="2" class="tt_head_bg">&nbsp&nbsp&nbsp电话:
									 &nbsp&nbsp&nbsp <a href="wtai://wp/mc;<%= StaticMethod.null2String(tawSystemUser.getMobile())%>">
							     <%= StaticMethod.null2String(tawSystemUser.getMobile())%> 
							    </a>
					    </td>
						  </tr>
						  
						  
					
		
  

   <%
   }
   }
   %>
   </table>
   <%
   }
 }
 %>
 
 					
 						<tr>
							<td class="btn_bg">
								<table>
								  <tr>
									<td><input name="Button" type="button" value="返回" class="btn_03" 
							onclick="javascript:{var url='${app}/wap/dutyroom.do?method=roomselect';location.href=url}"/></td>
									<td><input name="Button" type="button" value="前一天" class="btn_03" 
							onclick="javascript:{var url='${app}/wap/dutyuser.do?method=dutyUser&id=<%=roomId%>&day=-1&dutyDate=<%=dutyDate%>';location.href=url}"/></td>
									<td><input name="Button" type="button" value="当天" class="btn_03" 
							onclick="javascript:{var url='${app}/wap/dutyuser.do?method=dutyUser&id=<%=roomId%>';location.href=url}"/></td>
									<td><input name="Button" type="button" value="后一天" class="btn_03" 
							onclick="javascript:{var url='${app}/wap/dutyuser.do?method=dutyUser&id=<%=roomId%>&day=1&dutyDate=<%=dutyDate%>';location.href=url}"/></td>
								  </tr>
								</table>
							</td>
			  	</tr>
  
</table>
	
 </form>
 
</body>
</html>