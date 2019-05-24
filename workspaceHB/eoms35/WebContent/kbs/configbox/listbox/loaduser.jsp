<%@ page language="java"%>
<%@ page contentType="text/html; charset=gb2312"%>
<%@ page import="java.util.*"%>
<%@ page import="com.boco.eoms.jbzl.bo.TawDeptBO"%>
<%@ page import="com.boco.eoms.db.util.ConnectionPool"%>
<%@ page import="com.boco.eoms.jbzl.model.TawDept"%>
<%@ page import="java.util.*,com.boco.eoms.common.util.*"%>
<%@ page import="com.boco.eoms.jbzl.model.TawRmUser"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import="com.boco.eoms.jbzl.flow.bo.*"%>
<%@ page import="com.boco.eoms.jbzl.flow.model.*"%>
<%@ page import="com.boco.eoms.assess.dao.*"%>
<%@ page import ="com.boco.eoms.assess.model.OrgPost"%>

<%
String webapp = request.getContextPath();
String DASID = StaticMethod.nullObject2String(request.getParameter("divID"));
String selectself = StaticMethod.nullObject2String(request.getParameter("selectself"));
String myDeptId = StaticMethod.nullObject2String(request.getParameter("mydeptid"));
String myUserId = StaticMethod.nullObject2String(request.getParameter("myuserid"));
com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.
ConnectionPool.getInstance();
int postId = Integer.parseInt(DASID);//岗位id

String catContent = "<table cellSpacing=0 cellPadding=0 id='tr_parentid_"+ postId +"'><TBODY id=rootTbody>";

OrgPostDAO orgPostDAO=new OrgPostDAO(ds);
List listu = orgPostDAO.getUsers(postId);

//人员列表
if (listu!=null){
for (int j=0;j<listu.size();j++){
	String uID = ((TawRmUser)listu.get(j)).getUserId();
	String uName = ((TawRmUser)listu.get(j)).getUserName();
	catContent += "<tr class=EOMSListboxItem id='tr_userid_"+uID+"'>";
	catContent += "<td class=EOMSListboxImage><img src='"+webapp+"/css/listbox/images/user.gif' id='img_treeid_"+uID+"' align='TextTop'></td>";
	catContent += "<td class=EOMSListboxCaption>"+uName+"</td><td class=EOMSListboxValue>"+uID+"</td>";
	catContent += "<td class=EOMSListboxTag></td><td class=EOMSListboxParent>"+uID+"</td></tr>";
	}
}

catContent += "</tbody></table>";




%>

<html>
<head>
<META HTTP-EQUIV="content-type" CONTENT="text/html; charset=GB2312">
<script language="JavaScript">
function window.onload(){
	var father = parent.document;
	var selectself = "<%=selectself%>";
	var myDeptId = "tr_parentid_<%=myDeptId%>";
	var myUserId = "tr_userid_<%=myUserId%>";
	var tElement = father.getElementById("td_treeid_p<%=DASID%>");
	tElement.innerHTML="<%=catContent%>";

        if(selectself != "yes"){
		if (father.getElementById(myDeptId))
			father.getElementById(myDeptId).className = "EOMSListboxItemUnEnabled";
		if (father.getElementById(myUserId))
			father.getElementById(myUserId).className = "EOMSListboxItemUnEnabled";
	}

}


</script>
</head>
</html>
