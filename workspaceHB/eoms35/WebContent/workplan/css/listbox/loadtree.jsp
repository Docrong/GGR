<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import="com.boco.eoms.jbzl.bo.TawDeptBO"%>
<%@ page import="com.boco.eoms.db.util.ConnectionPool"%>
<%@ page import="com.boco.eoms.jbzl.model.TawDept"%>
<%@ page import="java.util.*,com.boco.eoms.common.util.*"%>
<%@ page import="com.boco.eoms.jbzl.model.TawRmUser"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import="com.boco.eoms.jbzl.flow.bo.*"%>
<%@ page import="com.boco.eoms.jbzl.flow.model.*"%>

<%
String webapp = request.getContextPath();
String themePath = webapp + "/skins/themes/blue";
String parentValue = StaticMethod.nullObject2String(request.getParameter("divID"));
String divName = StaticMethod.nullObject2String(request.getParameter("divName"));
String user = StaticMethod.nullObject2String(request.getParameter("user"));
String selectself = StaticMethod.nullObject2String(request.getParameter("selectself"));
String myDeptId = StaticMethod.nullObject2String(request.getParameter("mydeptid"));
String myUserId = StaticMethod.nullObject2String(request.getParameter("myuserid"));

String catContent = "<table cellSpacing=0 cellPadding=0 id='tr_parentid_"+parentValue+"'><TBODY id=rootTbody>";

int deptId = Integer.parseInt(parentValue);//部门deptid
TawDeptBO tawDeptBO = new TawDeptBO();
String ID = tawDeptBO.getDeptId(deptId);//部门id
List list = tawDeptBO.getSecondDepts(deptId);
List listu = tawDeptBO.getRmUsers(deptId);

//子部门列表
for (int i=0;i<list.size();i++){
	int subDeptID = ((TawDept)list.get(i)).getDeptId();
//	String ID = tawDeptBO.getDeptId(dID);
	String subID = ((TawDept)list.get(i)).getId();
	String subDeptName = ((TawDept)list.get(i)).getDeptName();
	catContent += "<tr class=EOMSListboxItem id='tr_parentid_"+subDeptID+"'>";
	catContent += "<td class=EOMSListboxImage><img src='images/false.gif' id='img_parentid_"+subDeptID+"' name='img_parentid_"+subDeptID+"' onclick=load('"+divName+"',"+subDeptID+") align='TextTop' style='cursor:hand'></td>";
	catContent += "<td class=EOMSListboxCaption>"+subDeptName+"</td><td class=EOMSListboxValue>"+subDeptID+"</td>";
	catContent += "<td class=EOMSListboxTag></td><td class=EOMSListboxParent>"+subID+"</td></tr>";
	catContent += "<tr style='display:none' id='tr_treeid_"+subDeptID+"'><td width=5></td><td id='td_treeid_"+subDeptID+"' colspan=4><div>loading...</div></td></tr>";
}

//人员列表
if (!user.equals("no")){
for (int j=0;j<listu.size();j++){
	String uID = ((TawRmUser)listu.get(j)).getUserId();
	String uName = ((TawRmUser)listu.get(j)).getUserName();
	catContent += "<tr class=EOMSListboxItem id='tr_userid_"+uID+"'>";
	catContent += "<td class=EOMSListboxImage><img src='"+webapp+"/css/listbox/images/user.gif' id='img_treeid_"+uID+"' align='TextTop'></td>";
	catContent += "<td class=EOMSListboxCaption>"+uName+"</td><td class=EOMSListboxValue>"+uID+"</td>";
	catContent += "<td class=EOMSListboxTag></td><td class=EOMSListboxParent>"+ID+"</td></tr>";
	}
}

catContent += "</tbody></table>";
%>

<html>
<head>
<META HTTP-EQUIV="content-type" CONTENT="text/html; charset=GB2312">
<script language="JavaScript">
<!--
function window.onload(){
	var father = parent.document;
	var user = "<%=user%>";
	var selectself = "<%=selectself%>";
	var myDeptId = "tr_parentid_<%=myDeptId%>";
	var myUserId = "tr_userid_<%=myUserId%>";
	var tElement = father.getElementById("<%=divName%>td_treeid_<%=parentValue%>");
	tElement.innerHTML="<%=catContent%>";
	if(selectself != "yes"){
		if (father.getElementById(myDeptId))
			father.getElementById(myDeptId).className = "EOMSListboxItemUnEnabled";
		if (father.getElementById(myUserId))
			father.getElementById(myUserId).className = "EOMSListboxItemUnEnabled";
	}

}
//-->
</script>
</head>
</html>