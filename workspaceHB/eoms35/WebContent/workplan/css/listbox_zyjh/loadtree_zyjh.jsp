<%@ page language="java" pageEncoding="UTF-8" %>
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
<%@ page import ="com.boco.eoms.assess.model.OrgPost"%>

<%
String webapp = request.getContextPath();
String themePath = webapp + "/skins/themes/blue";
String parentValue = StaticMethod.nullObject2String(request.getParameter("divID"));
String divName = StaticMethod.nullObject2String(request.getParameter("divName"));

String user = StaticMethod.nullObject2String(request.getParameter("user"));
String post = StaticMethod.nullObject2String(request.getParameter("post"));
String room = StaticMethod.nullObject2String(request.getParameter("room"));

String selectself = StaticMethod.nullObject2String(request.getParameter("selectself"));
String myDeptId = StaticMethod.nullObject2String(request.getParameter("mydeptid"));
String myUserId = StaticMethod.nullObject2String(request.getParameter("myuserid"));

//deptClass:部门节点的classname，决定部门是否可选，可选为EOMSListboxItem,不可选为EOMSListboxItemUnSelected
String deptClass;
if (divName.equals("dept") || divName.equals(""))
{
	deptClass = "EOMSListboxItem";
}
else {
	deptClass = "EOMSListboxItemUnSelected";
}

String catContent = "<table cellSpacing=0 cellPadding=0 id='tr_parentid_"+parentValue+"'><TBODY id=rootTbody>";

int deptId = Integer.parseInt(parentValue);//部门deptid
TawDeptBO tawDeptBO = new TawDeptBO();
String ID = tawDeptBO.getDeptId(deptId);//部门id
List list = tawDeptBO.getSecondDepts(deptId);
List listu = tawDeptBO.getRmUsers(deptId);
List listp = tawDeptBO.getPosts(deptId);
System.out.println("listp:"+listp.toString());

//子部门列表
for (int i=0;i<list.size();i++){
	int subDeptID = ((TawDept)list.get(i)).getDeptId();
//	String ID = tawDeptBO.getDeptId(dID);
	String subID = ((TawDept)list.get(i)).getId();
	String subDeptName = ((TawDept)list.get(i)).getDeptName();
	catContent += "<tr class="+deptClass+" id='"+divName+"tr_parentid_"+subDeptID+"'>";
	catContent += "<td class=EOMSListboxImage><img src='images/false.gif' id='"+divName+"img_parentid_"+subDeptID+"' name='"+divName+"img_parentid_"+subDeptID+"' onclick=load('"+divName+"',"+subDeptID+") align='TextTop' style='cursor:hand'></td>";
	catContent += "<td class=EOMSListboxCaption>"+subDeptName+"</td><td class=EOMSListboxValue>"+subDeptID+"</td>";
	catContent += "<td class=EOMSListboxTag></td><td class=EOMSListboxParent>"+subID+"</td></tr>";
	catContent += "<tr style='display:none' id='"+divName+"tr_treeid_"+subDeptID+"'><td width=5></td><td id='"+divName+"td_treeid_"+subDeptID+"' colspan=4><div>loading...</div></td></tr>";
}

if (!post.equals("no")){
  if(listp!=null){
    for (int j=0;j<listp.size();j++){

      int pID = ((OrgPost)listp.get(j)).getPostId();
      String pName = ((OrgPost)listp.get(j)).getPostName();
      catContent += "<tr class=EOMSListboxItem  id='"+divName+"tr_postid_"+pID+"'>";
      catContent += "<td class=EOMSListboxImage><img src='images/post.gif' id='"+divName+"img_treeid_"+pID+"' align='TextTop'></td>";
      catContent += "<td class=EOMSListboxCaption>"+pName+"</td><TD class=EOMSListboxValue>"+pID+"</TD>";
      catContent += "<td class=EOMSListboxTag></td><td class=EOMSListboxParent>"+ID+"</td></tr>";
    }
  }
}

//人员列表
if (!user.equals("no")){
for (int j=0;j<listu.size();j++){
	String uID = ((TawRmUser)listu.get(j)).getUserId();
	String uName = ((TawRmUser)listu.get(j)).getUserName();
	catContent += "<tr class=EOMSListboxItem id='"+divName+"tr_userid_"+uID+"'>";
	catContent += "<td class=EOMSListboxImage><img src='images/user.gif' id='"+divName+"img_treeid_"+uID+"' align='TextTop'></td>";
	catContent += "<td class=EOMSListboxCaption>"+uName+"</td><td class=EOMSListboxValue>"+uID+"</td>";
	catContent += "<td class=EOMSListboxTag></td><td class=EOMSListboxParent>"+ID+"</td></tr>";
	}
}

//机房列表
if (!user.equals("no")){
//在此加入机房代码，参考以上人员或岗位的写法	
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

        var post = "<%=post%>";

	var selectself = "<%=selectself%>";
	var myDeptId = "<%=divName%>tr_parentid_<%=myDeptId%>";
	var myUserId = "<%=divName%>tr_userid_<%=myUserId%>";
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
