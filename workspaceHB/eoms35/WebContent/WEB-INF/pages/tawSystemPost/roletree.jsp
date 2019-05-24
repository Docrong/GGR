<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import ="com.boco.eoms.commons.system.role.service.RoleTree"%>
<%@ page import ="com.boco.eoms.base.util.StaticMethod"%>



<%@ taglib uri="http://acegisecurity.org/authz" prefix="authz" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean-el" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://struts-menu.sf.net/tag-el" prefix="menu" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="/WEB-INF/eoms.tld" prefix="eoms" %>
<%
String regionId = StaticMethod.nullObject2String(request.getAttribute("REGIONID"),"1");
String flag=StaticMethod.nullObject2String(request.getAttribute("workflowFlag"));
System.out.println("sssssssssssssssssss="+flag);
String path = request.getContextPath();

int topPostId=3;
RoleTree roleTree = new RoleTree();
String strTree = roleTree.strRoleTree(topPostId); //各专业流程顶层角色ID
String shareTree= roleTree.strRoleTree(1290); //公共使能流程顶层角色ID
String url = "";
String postId1 = "";
String wsClass = "-1";
int postId = 1;
%>
<html>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
<link rel="StyleSheet" href="<%=path%>/css/tree.css" type="text/css">
	<script type="text/javascript" src="<%=path%>/css/onlytree.js"></script>

<title><bean:message key="tawSystemDeptForm.deptName"/></title>
<body background="<%=request.getContextPath()%>/images/img/bg001.gif">

<table width="700">
<tr>
<td width="250" align="center" valign="top">
<br>
<div id="tree" align="left">
<input type="hidden" name="url" id="url" value="<%=url%>">
<input type="hidden" name="wsClass" id="wsClass" value="<%=wsClass%>">
<input type="hidden" name="path" id="path" value="<%=path%>">
<input type="hidden" name="fromDept" id="fromDept" value="<%=postId1%>">

<bean:define id="test" name="tawSystemPostForm" property="postId" type="java.lang.Integer"/>
<bean:define id="parent" name="tawSystemPostForm" property="parentId" type="java.lang.Integer"/>
<bean:define id="posttypeId" name="tawSystemPostForm" property="postTypeId" type="java.lang.Integer"/>
<bean:define id="posttypeName" name="tawSystemPostForm" property="posttypeName" type="java.lang.String"/>
<bean:define id="postName" name="tawSystemPostForm" property="postName" type="java.lang.String"/>


<script type="text/javascript">

var postId1=document.all.fromDept.value;
var url=document.all.url.value;
var wsclass=document.all.wsClass.value;
var path=document.all.path.value;

var Tree = new Array;
<%=strTree%>
createTree3(Tree,<%=topPostId%>,-1,path,url,"<%=postId%>",wsclass);
<%=shareTree%>
createTree3(Tree,1290,-1,path,url,"<%=postId%>",wsclass);

function selectMembers(){
   dWinOrnaments = "status:no;scroll:no;resizable:yes;dialogHeight:600px;dialogWidth:650px;";
   var obj = document.orgPostForm;
   var ret = showModalDialog('<%=path%>/assess/configbox/roleListBox/listbox_onlyuser.jsp?checkall=yes&post=no', window, dWinOrnaments);
}
function userCallBack() {

	document.orgPostForm.submit();
	//document.orgPostForm.userid.value
	//window.document.
}
function remove(){
var removeflag=confirm("是否确定删除该部门及其子部门！");
if(removeflag){
  window.location.href ='removeFromTree.do?postId=<%=test%>&posttypeName=<%=posttypeName%>';
 }
}
</script>
</div>

</td>


<td width="500" valign="top" align="center">
    <c:choose>
      <c:when test="${requestScope['orgPostForm'].strutsAction == 3}">
<div align="center">
  <center>
<br>
<html:form method="post" action="/OrgPost/saveRoleMembers">
<table align="center" border="0" width="95%" cellspacing="0">
  <tr>
    <td width="100%" class="table_title" align="center">
        <b>&nbsp;&nbsp;<bean:message key="label.view"/>&nbsp;</b>　</td>
  </tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
  <tr class="tr_show">
    <td width="30%" class="clsfth" height="25">&nbsp;<bean:message key="OrgPost.postName"/></td>
    <td width="70%" height="25"><bean:write name="orgPostForm" property="postName" scope="request"/></td>
  </tr>
  <tr class="tr_show">
    <td width="30%" class="clsfth" height="25">&nbsp;<bean:message key="OrgPost.posttypeName"/></td>
    <td width="70%" height="25"><bean:write name="orgPostForm" property="posttypeName" scope="request"/></td>
  </tr>
  <tr class="tr_show">
    <td width="30%" height="25" class="clsfth">&nbsp;是否派单角色
  </td>
  <td width="70%" height="25">
  	<html:checkbox property="workflowFlag"></html:checkbox>

  </td>
  <tr class="tr_show">
    <td width="30%" height="25" class="clsfth">&nbsp;父角色
   </td>
    <td width="70%" height="25">
    	<bean:write name="orgPostForm" property="parentName" scope="request"/>
     </td>
  </tr>
  <%if(flag!=null&&flag.equals("1")){%>
  <tr class="tr_show">
    <td width="30%" height="25" class="clsfth">&nbsp;受理工单门限值
   </td>
    <td width="70%" height="25">
      <bean:write name="orgPostForm" property="limitCount" scope="request"/>
     </td>
  </tr>
  <%}%>
  <tr class="tr_show">
    <td width="30%" height="25" class="clsfth">&nbsp;<bean:message key="OrgPost.notes"/>
   </td>
    <td width="70%" height="25">
    	<bean:write name="orgPostForm" property="notes" scope="request"/>
     </td>
  </tr>
  <tr class="tr_show">
    <td width="30%" height="25" class="clsfth">&nbsp;<bean:message key="OrgPost.roleMembers"/>
   </td>

    <td width="70%" height="25">
    	<html:hidden property="postId"></html:hidden>
    	<input type="hidden" id="sort1_userid" name="userIdArray"/>
    	<div id="sort1_text">
    	<%
    	  String postNameArray = request.getAttribute("userNameArray").toString();
    	  String userIdArray = request.getAttribute("userIdArray").toString();
    	  String[] postNames = postNameArray.split(",");
    	  String[] userIds = userIdArray.split(",");
    	  if (!postNameArray.equals("")) {
    	  	System.out.println(postNames.length);
    	  	System.out.println("44"+postNames[0]);
	    	  for (int i=0;i<postNames.length;i++) {
    	%>
    	<img src='<%=path%>/assess/configbox/roleListBox/images/user.gif' align=absmiddle><span id='tr_userid_<%=userIds[i]%>' style="width: 40%"><%=postNames[i]%></span>
    	<%} }%>
    	</div>
     </td>
  </tr>

</table>
<table align="center" border="0" width="95%" cellspacing="0">
  <tr>
    <td width="100%" align="right" height="32">
     &nbsp;&nbsp;
     <INPUT id=button type=button value=<bean:message key="label.roleMembers"/> name=button Onclick="selectMembers()" class="clsbtn2">
     &nbsp;&nbsp;
     <INPUT id=button type=button value=<bean:message key="label.add"/> name=button Onclick="window.location.href ='addFromTree.do?postId=<%=test%>&posttypeId=<%=posttypeId%>&posttypeName=<%=posttypeName%>'" class="clsbtn2">
     &nbsp;&nbsp;
     <INPUT id=button type=button value=<bean:message key="label.edit"/> name=button Onclick="window.location.href ='editFromTree.do?postId=<%=test%>&parentId=<%=parent%>&posttypeId=<%=posttypeId%>&posttypeName=<%=posttypeName%>'" class="clsbtn2">
     &nbsp;&nbsp;
     <INPUT id=button type=button value=<bean:message key="label.remove"/> name=button Onclick="remove()" class="clsbtn2">
     &nbsp;&nbsp;
    </td>
  </tr>
</table>



</html:form>
  </center>
</div>
      </c:when>
      <c:otherwise>
      </c:otherwise>
    </c:choose>
</td>


</tr>
</table>
</body>

</html>
