<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import ="com.boco.eoms.jbzl.bo.TawDeptBO"%>
<%@ page import ="com.boco.eoms.db.util.ConnectionPool"%>
<%@ page import ="com.boco.eoms.jbzl.model.TawDept"%>
<%@ page import="java.util.*,com.boco.eoms.common.util.*"%>
<%@ page import="java.util.*,com.boco.eoms.jbzl.model.TawRmUser"%>

<%
String webapp = request.getContextPath();
String themePath = webapp + "/skins/themes/blue";
String DASID = "";
String user = "";

if (request.getParameter("divID") != null){
	DASID = request.getParameter("divID");
}

if (request.getParameter("user") != null){
	user = request.getParameter("user");
}


//获取部门和人员的数组 ，cheng修改
com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.
ConnectionPool.getInstance();

int deptId = Integer.parseInt(DASID);//部门id
TawDeptBO tawDeptBO = new TawDeptBO(ds);
List list = tawDeptBO.getSecondDepts(deptId);
//for (int j=0;j<list.size();j++){
//out.println("部门id："+((TawDept)list.get(j)).getDeptId());
//out.println("部门名称："+((TawDept)list.get(j)).getDeptName());
//}

List listu = tawDeptBO.getRmUsers(deptId);
//for (int k=0;k<listu.size();k++) {
//out.println("用户id："+((TawRmUser)listu.get(k)).getUserId());
//out.println("用户名称："+((TawRmUser)listu.get(k)).getUserName());
//}
//修改结束

String catContent = "<table cellSpacing=0 cellPadding=0 id='tr_parentid_"+deptId+"'><TBODY id=rootTbody>";

//子部门列表
for (int i=0;i<list.size();i++){
	int dID = ((TawDept)list.get(i)).getDeptId();
	String dName = ((TawDept)list.get(i)).getDeptName();
	catContent += "<tr class=EOMSListboxItem  id='tr_parentid_"+dID+"'><td class=EOMSListboxImage>";
	catContent += "<img src='"+webapp+"/css/listbox/images/false.gif' id='img_parentid_"+dID+"' name='img_parentid_"+dID+"' onclick=load('"+dName+"','"+dID+"') align='TextTop' style='cursor:hand'></td>";
	catContent += "<td class=EOMSListboxCaption>"+dName+"</td><TD class=EOMSListboxValue>"+dID+"</TD><TD class=EOMSListboxTag></TD></tr>";
	catContent += "<tr style='display:none' id='tr_treeid_"+dID+"'><td width=5></td><td id='td_treeid_"+dID+"'><div>loading...</div></td></tr>";
}

//人员列表
if (!user.equals("no")){
for (int j=0;j<listu.size();j++){
	String uID = ((TawRmUser)listu.get(j)).getUserId();
	String uName = ((TawRmUser)listu.get(j)).getUserName();
	catContent += "<tr class=EOMSListboxItem id='tr_userid_"+uID+"'>";
	catContent += "<td class=EOMSListboxImage><img src='"+webapp+"/css/listbox/images/user.gif' id='img_treeid_"+uID+"' align='TextTop' style='cursor:hand'></td>";
	catContent += "<td class=EOMSListboxCaption>"+uName+"</td><TD class=EOMSListboxValue>"+uID+"</TD><TD class=EOMSListboxTag></TD></tr>";
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
	parent.td_treeid_<%=DASID%>.innerHTML="<%=catContent%>";
}
//-->
</script>
</head>
</html>