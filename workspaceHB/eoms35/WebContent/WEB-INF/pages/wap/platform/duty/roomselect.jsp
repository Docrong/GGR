<%@page import="java.util.List,java.util.ArrayList,com.boco.eoms.wap.model.*" pageEncoding="UTF-8"%>
<%@ include file="/wap/common/taglibs.jsp"%>
<%@ page import="com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom" %>
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
 <% List roomList = (List)request.getAttribute("ROOMLIST");
 %>
<table border="0" cellspacing="0" cellpadding="0" class="sub_table_bg">
  <tr>
    <td class="title_bg_01"><img src="${app}/wap/images/s_ico_05.gif" width="15" height="19" />所属机房</td> 
  </tr>
  
 <%
 for(int i=0;i<roomList.size();i++){
   TawSystemCptroom room = (TawSystemCptroom)roomList.get(i);
   %>
   <div style="padding-top:5px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="sub_table_bg">
			   <tr>
			   <!-- <td class="list_text_03"><div style="padding-top:5px;">
			    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
			    		<tr>-->
				        <td class="tt_head_bg"><img src="${app}/wap/images/tu01_1.gif"/>
				    			<a href="${app}/wap/dutyuser.do?method=dutyUser&id=<%=room.getId()%>" > <%=room.getRoomname()%> </a>
				    		</td><!--
			    		</tr>
			    	</table>
			    </td> -->
			   </tr>
			</table>   
		</div>
   <%
 	}
 %>
 
  <tr colspan="2" class="table_td">
    <td><input name="Submit222" type="submit" class="btn_02" value="返回" /></td>
  </tr>
</table>
 </form>
</body>
</html>