<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%
String wapCardId = request.getParameter("wapCardId");
String wapNodeId = request.getParameter("wapNodeId");
String tagType = request.getParameter("tagType");
String addPageURL = request.getContextPath()+"/webtool/tag/add/";

if(tagType.equals("anchor"))
	addPageURL += "anchor_add.jsp?";
else if(tagType.equals("href"))
	addPageURL += "href_add.jsp?";
else if(tagType.equals("image"))
	addPageURL += "image_add.jsp?";
else if(tagType.equals("input"))
	addPageURL += "input_add.jsp?";
else if(tagType.equals("onevent"))
	addPageURL += "onevent_add.jsp?";
else if(tagType.equals("selectDynamic"))
	addPageURL += "selectdynamic_add.jsp?";
else if(tagType.equals("selectstatic"))
	addPageURL += "selectstatic_add.jsp?";
else if(tagType.equals("sqlClass"))
	addPageURL += "sql_add.jsp?";
else if(tagType.equals("submit"))
	addPageURL += "submit_add.jsp?";
else if(tagType.equals("sysClass"))
	addPageURL += "sysclass_add.jsp?";
else if(tagType.equals("text"))
	addPageURL += "text_add.jsp?";
else if(tagType.equals("br"))
	addPageURL += "br_add.jsp?";
addPageURL += "wapCardId="+wapCardId+"&wapNodeId="+wapNodeId+"&tagType="+tagType;

response.sendRedirect(addPageURL);
%>