<%@ page language="java"%>
<%@ page contentType="text/html; charset=gb2312"%>
<%@ page import="java.util.*"%>
<%@ page import ="com.boco.eoms.jbzl.bo.TawDeptBO"%>
<%@ page import ="com.boco.eoms.db.util.ConnectionPool"%>
<%@ page import ="com.boco.eoms.jbzl.model.TawDept"%>
<%@ page import ="com.boco.eoms.common.util.*"%>
<%@ page import ="com.boco.eoms.jbzl.model.TawRmUser"%>
<%@ page import ="com.boco.eoms.assess.model.OrgPost"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>

<%

String webapp = request.getContextPath();
String DASID = StaticMethod.nullObject2String(request.getParameter("divID"));
String post = StaticMethod.nullObject2String(request.getParameter("post"));
String selectself = StaticMethod.nullObject2String(request.getParameter("selectself"));
String myDeptId = StaticMethod.nullObject2String(request.getParameter("mydeptid"));
String myUserId = StaticMethod.nullObject2String(request.getParameter("myuserid"));

int deptId = Integer.parseInt(DASID);//部门id
TawDeptBO tawDeptBO = new TawDeptBO();
List list = tawDeptBO.getSecondDepts(deptId);
List listp = tawDeptBO.getPosts(deptId);

String catContent = "<table cellSpacing=0 cellPadding=0 id='tr_parentid_"+deptId+"'><TBODY id=rootTbody>";

//子部门列表
for (int i=0;i<list.size();i++){
	int dID = ((TawDept)list.get(i)).getDeptId();
	String dName = ((TawDept)list.get(i)).getDeptName();
	catContent += "<tr class=EOMSListboxItem  id='tr_parentid_"+dID+"'><td class=EOMSListboxImage>";
	catContent += "<img src='"+webapp+"/assess/configbox/listbox/images/false.gif' id='img_parentid_"+dID+"' name='img_parentid_"+dID+"' onclick=loadDeptPost('"+dName+"','"+dID+"') align='TextTop' style='cursor:hand'></td>";
	catContent += "<td class=EOMSListboxCaption>"+dName+"</td><TD class=EOMSListboxValue>"+dID+"</TD><TD class=EOMSListboxTag></TD></tr>";
	catContent += "<tr style='display:none' id='tr_treeid_d"+dID+"'><td width=5></td><td id='td_treeid_d"+dID+"'><div>loading...</div></td></tr>";
     //  System.out.println(" dept catContent:"+catContent);
}

if (!post.equals("no")){
  if(listp!=null){
    for (int j=0;j<listp.size();j++){

      int pID = ((OrgPost)listp.get(j)).getPostId();
      String pName = ((OrgPost)listp.get(j)).getPostName();
      catContent += "<tr class=EOMSListboxItem  id='tr_postid_"+pID+"'><td class=EOMSListboxImage>";
      catContent += "<img src='"+webapp+"/assess/configbox/listbox/images/post.gif' id='img_parentid_"+pID+"' name='img_parentid_"+pID+"' onclick=loadUser('"+pName+"','"+pID+"') align='TextTop' style='cursor:hand'></td>";
      catContent += "<td class=EOMSListboxCaption>"+pName+"</td><TD class=EOMSListboxValue>"+pID+"</TD><TD class=EOMSListboxTag></TD></tr>";
      catContent += "<tr style='display:none' id='tr_treeid_p"+pID+"'><td width=5></td><td id='td_treeid_p"+pID+"'><div>loading...</div></td></tr>";
   //   System.out.println("post  catContent:"+catContent);
    }
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
	var post = "<%=post%>";
	var selectself = "<%=selectself%>";
	var myDeptId = "tr_parentid_<%=myDeptId%>";
	var myUserId = "tr_userid_<%=myUserId%>";
	var tElement = father.getElementById("td_treeid_d<%=DASID%>");

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