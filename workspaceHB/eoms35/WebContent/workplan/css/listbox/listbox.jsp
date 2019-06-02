<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>

<%@ page import = "java.util.*,java.lang.*"%>
<%@ page import ="com.boco.eoms.common.controller.SaveSessionBeanForm"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import ="com.boco.eoms.common.util.StaticVariable"%>

<%@ page import ="com.boco.eoms.jbzl.bo.*"%>
<%@ page import ="com.boco.eoms.jbzl.model.TawDept"%>

<%@ page import ="com.boco.eoms.jbzl.flow.bo.*"%>
<%@ page import ="com.boco.eoms.jbzl.flow.model.*"%>
<%
// ----------------------------------------------------------------------
// EOMS Listbox 页面组件
// Copyright (c) 2004-2006, 亿阳信通网络事业部 EOMS
// All rights reserved.
// Abstract ：即时取值的树型选择及分配
// Version　：1.1
// Author   ：miao peng (MSN:mios426@hotmail.com)
// Finished Date ：2004-09-09
// Last Modified ：2004-12-13
//
//  参数:例如 listbox.jsp?sort=2-4&user=no&checkall=no&selectself=yes
//   sort		结果框个数及名称 1：派发 2：抄送 3：审核 4：受理
//   user		值为"no"时,不显示人员
//   checkall	值为"no"时,可以重复选择
//   selectself 值为"yes"时,可以选择本部门和本人员
//
//	功能:
//   支持即时取值的树型选择框
//	 可添加到三类选择结果框中,和从结果框中删除
//   父节点和子节点不能被同时选择
//	 选择后再次点出窗口时,构造已选项
//   点确定按钮,选择结果框中的选项显示到父页面中,本界面关闭
//   点取消按钮,本界面关闭
//	 点清空按钮,清空选择结果框中的选项
//	 不可选择项目灰度显示
//   按下CTRL/SHIFT键可进行多选
//	 结果框中双点删除
//
// ------------------------------------------------------------------------

	response.addHeader("Cache-Control", "no-cache");
	response.addHeader("Expires", "Thu, 01 Jan 1970 00:00:01 GMT");

	String webapp = request.getContextPath();

	//登陆用户的deptId，以及userId
	int myDeptId = 0;
 	String myUserId = "";

    //session超时处理
	SaveSessionBeanForm saveSessionBeanForm =
		(SaveSessionBeanForm) request.getSession().getAttribute("SaveSessionBeanForm");
	if (saveSessionBeanForm != null) {
		myUserId = StaticMethod.null2String(saveSessionBeanForm.getWrf_UserID());
		myDeptId = saveSessionBeanForm.getWrf_DeptID();
	}
	else{
%>
		<script language="javascript">
			alert("您的帐号闲置过久，请重新登录！");
			dialogArguments.top.location.href = "<%=webapp%>/index.jsp";
			window.close();
		</script>
<%
		return;
	}

	//获取和登陆用户相关联的组
	TawWsGroupBO tawWsBO = new TawWsGroupBO();
	List GROUPS = tawWsBO.getGroupList(myUserId,StaticVariable.OBJECT_USER);

	request.setAttribute("GROUPS",GROUPS);

	String[] arrSort = {"派发","抄送","审核"};
	String strUseroff = StaticMethod.nullObject2String(request.getParameter("user")) ;
	String strCheckall = StaticMethod.nullObject2String(request.getParameter("checkall"));
	String strSelectSelf = StaticMethod.nullObject2String(request.getParameter("selectself"));


	if (request.getParameter("sort") != null)
	{
		String strSort = request.getParameter("sort");
		arrSort = strSort.split("-");
		for (int i=0;i<arrSort.length;i++){
			switch (Integer.parseInt(arrSort[i])){
				case 1:
					arrSort[i] = "派发";
					break;
				case 2:
					arrSort[i] = "抄送";
					break;
				case 3:
					arrSort[i] = "审核";
					break;
				case 4:
					arrSort[i] = "受理";
					break;
			}
		}
	}

%>
<HTML xmlns:BOCO="BOCO Inter-Telecom">
<HEAD>
<TITLE>选择派发信息</TITLE>
<SCRIPT language="jscript">

 	var father = dialogArguments.document;
	var pageSelected = null;
	var objSelectedItems = null ;
	var trueIcon = new Image();
		trueIcon.src = "images/true.gif";
	var falseIcon = new Image();
		falseIcon.src = "images/false.gif";
	var userIcon = new Image();
		userIcon.src = "images/user.gif";
	var deptIcon = new Image();
		deptIcon.src = "images/dept.gif";

	var sortNum = <%=arrSort.length%>;
	var arrSortID = new Array();
	for(i=0;i<sortNum;i++){
		arrSortID[i] = "listboxSort" + (i + 1);
	}

	var checkall = "<%=strCheckall%>";
	var selectself = "<%=strSelectSelf%>";

	function fnEOMSListboxOnChange(elName) {
		with (window.document.all(elName)) {
			var item = objSelectedItems.lastItem();
			var selID = item.ID ;
			var selText = item.text ;
			var selValue = item.value ;
			var selTag = item.tagInfo ;
//			var selIndex = selectedIndex;
			var selParent = item.parentID;
		}
		var msg = "OnChange event!!!" +
				"\nid=" + selID +
				"\ntext=" + selText +
				"\nvalue=" + selValue +
				"\ntaginfo=" + selTag +
				"\nparentID=" + selParent;
//				"\nsindex=" + selIndex;
//		alert(msg) ;
	}

	function fnEOMSListboxMoveRow(elName){
		if (objSelectedItems.count() == 0 && objSelectedItems.getSelectedGroup() == null)
			alert("请选择一个项目。");
		else
		{
			switch (objSelectedItems.action)
			{
				case "add" : fnEOMSListboxAddRow(elName);break;
				case "del" : fnEOMSListboxDelRow(elName);break;
			}
		}
	}

	function getRealID(str){
		if(str.indexOf("tr_parentid_")!=-1){
			return str.substring(str.indexOf("tr_parentid_")+12);
		}
		else if(str.indexOf("tr_userid_")!=-1){
			return str.substring(str.indexOf("tr_userid_")+10);
		}
		else{
			return str;
		}
	}

	function fnEOMSListboxAddRow(elName) {
		var mydept = "<%=myDeptId%>";
		var myuser = "<%=myUserId%>";
		with (window.document.all(elName)) {

			var alertAllInfo = "";

			for(var i=0; i<objSelectedItems.count();i++){
				var alertItemInfo = "";
				var item = objSelectedItems.getSelectedItem(i);

//				alert("item.ID=" + item.ID +
//					"\nitem.parentID=" + item.parentID +
//					"\nitem.parentValue=" + item.parentValue +
//					"\nitem.text=" + item.text +
//					"\nitem.value=" + item.value +
//					"\nitem.tagInfo=" + item.tagInfo);

				//判断此项目能否被选择
				if(item.property == "dept" && item.value==mydept && selectself!="yes"){
					alertItemInfo += "不能对本部门进行操作，请选择其他部门。";
				}
				if(item.property == "user" && item.value==myuser && selectself!="yes"){
					alertItemInfo += "不能对自己进行操作，请选择其他人员。";
				}

				if (checkall != "no"){
					for(j=0;j<arrSortID.length;j++){
						if (inBox(arrSortID[j], item.value, "value", item.property))
							{alertItemInfo += "\n该项目\""+item.text+"\"已经被选择了.不能重复选择。";break;}
						if (item.property == "user" && inBox(arrSortID[j], item.parentID, "parent", "dept"))
							{alertItemInfo += "\n人员\""+item.text+"\"所属的部门已经被选择了, 该人员不能被选择。";break;}
						if (item.property == "dept" && inBox(arrSortID[j],item.parentID, "parent", "user"))
							{alertItemInfo += "\n部门\""+item.text+"\"下的人员已经被选择了，该部门不能被选择。";break;}

//						特别为四川移动增加的限制
						if (item.property == "dept" && getParentLevel(item.parentID, 1) == "2" && inBoxByParent(arrSortID[j],"2","dept",1))
							{alertItemInfo += "\n已经选择了集团公司或其下属部门，该部门\""+item.text+"\"不能被选择。";break;}
						if (item.property == "dept" && item.parentID == "2" && inBoxByParent(arrSortID[j],"2","dept",1))
							{alertItemInfo += "\n已经选择了集团公司或其下属部门，该部门\""+item.text+"\"不能被选择。";break;}
						if (item.property == "dept" && getParentLevel(item.parentID, 1) == "2" && inBox(arrSortID[j],"2","parent","dept"))
							{alertItemInfo += "\n已经选择了集团公司或其下属部门，该部门\""+item.text+"\"不能被选择。";break;}

					}
				}
				else{
					if (inBox(elName, item.value,  "value", item.property))
						{alertItemInfo += "\n该项目\""+item.text+"\"已经被选择了.不能重复选择。";}
					if (item.property == "user" && inBox(elName,item.parentID, "parent", "dept"))
						{alertItemInfo += "\n人员\""+item.text+"\"所属的部门已经被选择了, 该人员不能被选择。";}
					if (item.property == "dept" && inBoxByParent(elName,item.parentID, "parent", "user"))
						{alertItemInfo += "\n部门\""+item.text+"\"下的人员已经被选择了，该部门不能被选择。";}

//						特别为四川移动增加的限制
						if (item.property == "dept" && getParentLevel(item.parentID, 1) == "2" && inBoxByParent(elName,"2","dept",1))
							{alertItemInfo += "\n已经选择了集团公司或其下属部门，该部门\""+item.text+"\"不能被选择。";}
						if (item.property == "dept" && item.parentID == "2" && inBoxByParent(elName,"2","dept",1))
							{alertItemInfo += "\n已经选择了集团公司或其下属部门，该部门\""+item.text+"\"不能被选择。";}
						if (item.property == "dept" && getParentLevel(item.parentID, 1) == "2" && inBox(elName,"2","parent","dept"))
							{alertItemInfo += "\n已经选择了集团公司或其下属部门，该部门\""+item.text+"\"不能被选择。";}
				}

				//处理选择动作
				if (alertItemInfo == ""){
					if(item.property == "dept")
						addItem("I", item.text, item.value, -1, deptIcon.src, item.ID, item.parentID);
					if(item.property == "user")
						addItem("I", item.text, item.value, -1, userIcon.src,item.ID, item.parentID);
				}

				//处理每项选择后的操作
				alertAllInfo += alertItemInfo;

				if(checkall != "no")
				{
					item.className = (alertItemInfo == "") ? "EOMSListboxItemUnEnabled" : "EOMSListboxItem";
				}
				if(checkall == "no")
				{
					var allSelected = true;
					for(k=0;k<arrSortID.length;k++){
						if (!inBox(arrSortID[k], item.value, item.property))
							{allSelected = false;break;}
					}
					item.className = (allSelected == true) ? "EOMSListboxItemUnEnabled" : "EOMSListboxItem";
				}
//				alert(item.text+"ok");
			}//END OF FOR

			//处理全部选择后的操作
			if (alertAllInfo != "")
				alert(alertAllInfo);
			objSelectedItems.clear();

		}// END OF WITH

	}

	function fnEOMSListboxDelRow(elName) {
		with (window.document.all(elName)) {
			if (objSelectedItems.count() > 0) {
				for(i=0; i<objSelectedItems.count();i++){
					var rootId = objSelectedItems.getSelectedItem(i).tagInfo;
					var delIndex = getElementIndex(objSelectedItems.getSelectedItem(i));
					removeItem(delIndex);
					//从部门树和组中去除项目的不可选状态
					if(document.getElementById(rootId)){
						var rootEl = document.getElementById(rootId);
						if (rootEl.className = "EOMSListboxItemUnEnabled"){
							rootEl.className = "EOMSListboxItem";
						}
					}
					if(document.getElementById("group"+rootId)){
						var rootEl = document.getElementById("group"+rootId);
						if (rootEl.className = "EOMSListboxItemUnEnabled"){
							rootEl.className = "EOMSListboxItem";
						}
					}
				}
				objSelectedItems.clear();
			}
		}
	}

	function resetResult(){
		var optionsElement;
		for(var i=0;i<sortNum;i++){
			optionsElement = window.document.all(arrSortID[i]).children[0];
			while (optionsElement.rows.length>0) {
					var rootId = optionsElement.rows[0].children[3].innerHTML;
					optionsElement.parentElement.removeItem(0);
					if(document.getElementById(rootId)){
						var rootEl = document.getElementById(rootId);
						if (rootEl.className = "EOMSListboxItemUnEnabled"){
							rootEl.className = "EOMSListboxItem";
						}
					}
					if(document.getElementById("group"+rootId)){
						var rootEl = document.getElementById("group"+rootId);
						if (rootEl.className = "EOMSListboxItemUnEnabled"){
							rootEl.className = "EOMSListboxItem";
						}
					}
			}
		}
	}

	//
	function load(divName,divID){
		var targetTR = eval(divName+"tr_treeid_" + divID);
		var targetTD = eval(divName+'td_treeid_' + divID);

		if (targetTR.style.display!="block"){
			targetTR.style.display="block";
			eval("document."+divName+"img_parentid_"+divID+".src=trueIcon.src");

			bufferFrame.document.location="loadtree.jsp?divName="+divName+"&divID="+divID+"&user=<%=strUseroff%>&selectself=<%=strSelectSelf%>&mydeptid=<%=myDeptId%>&myuserid=<%=myUserId%>";

		}
		else{
			targetTR.style.display="none";
			eval("document."+divName+"img_parentid_"+divID+".src=falseIcon.src");
		}
	}

	//打开组下的部门信息
	function openGroup(divID){
		var targetTR = eval("grouptr_treeid_" + divID);
		var targetTD = eval("grouptd_treeid_" + divID);

		if (targetTR.style.display!="block"){
			targetTR.style.display="block";
			eval("document.groupimg_parentid_"+divID+".src=trueIcon.src");

		}
		else{
			targetTR.style.display="none";
			eval("document.groupimg_parentid_"+divID+".src=falseIcon.src");
		}
	}

	function inBox(boxName, targetValue, option, targetProperty){
		with (window.document.all(boxName)) {
			for (var i=0;i<itemCount();i++){
				if (option == "value" && getItemProperty(i) == targetProperty && targetValue == getItemValue(i))	return true;
				if (option == "parent" && getItemProperty(i) == targetProperty && targetValue == getItemParentID(i)) return true;
			}
			return false;
		}
	}

	function inBoxByParent(boxName, targetValue, targetProperty, parentLevel){
		with (window.document.all(boxName)) {
			for (var i=0;i<itemCount();i++){
				if (getItemProperty(i) == targetProperty && targetValue == getParentLevel(getItemParentID(i), parentLevel))	return true;
			}
			return false;
		}
	}

	function getParentLevel(str, level, direction){
		var levelStr, locat;
		if(str!=""){
			locat = str.length-(level * 2);
			levelStr = locat > 1 ? str.substring(0,locat) : str.substring(0,1);
			return levelStr;
		}
	}
	function getResult(elName,targetLayer){
		var arrReceiversDeptID = new Array();
		var arrReceiversUserID = new Array();
		var arrReceiversName = new Array();

		with (window.document.all(elName)) {
			for (var i=0;i<itemCount();i++){
				if((getItemTag(i).indexOf("tr_parentid_")) != -1){
					arrReceiversDeptID = arrReceiversDeptID.concat(getItemValue(i));
					arrReceiversName[i] = "<img src='"+deptIcon.src+"' align=absmiddle><span id=\""+getItemTag(i)+"\" parentid=\""+getItemParentID(i)+"\">"+getItemText(i)+"</span>";
				}
				if((getItemTag(i).indexOf("tr_userid_")) != -1){
					arrReceiversUserID = arrReceiversUserID.concat(getItemValue(i));
					arrReceiversName[i] = "<img src='"+userIcon.src+"' align=absmiddle><span id=\""+getItemTag(i)+"\" parentid=\""+getItemParentID(i)+"\">"+getItemText(i)+"</span>";
				}
			}

			father.getElementById(targetLayer+"_deptid").value = arrReceiversDeptID.join(",");
			father.getElementById(targetLayer+"_userid").value = arrReceiversUserID.join(",");
			father.getElementById(targetLayer+"_text").innerHTML = arrReceiversName.join(",");
		}
	}

	function window.onload(){
		var arrSeletedItem = new Array();
		for (i=1;i<=sortNum;i++){
			var strTemp = dialogArguments.document.getElementById("sort"+i+"_text").getElementsByTagName("span");
			for(j=0;j<strTemp.length;j++){

				if(strTemp.item(j).id.indexOf("tr_parentid_")!=-1){
					window.document.all(arrSortID[i-1]).addItem("I", strTemp.item(j).innerHTML, getRealID(strTemp.item(j).id), -1, deptIcon.src, strTemp.item(j).id, strTemp.item(j).parentid);
				}
				if(strTemp.item(j).id.indexOf("tr_userid_")!=-1){
					window.document.all(arrSortID[i-1]).addItem("I", strTemp.item(j).innerHTML, getRealID(strTemp.item(j).id), -1, userIcon.src, strTemp.item(j).id, strTemp.item(j).parentid);
				}
			}


		}
	}
</SCRIPT>


<style>
BOCO\:EOMSTab {
	POSITION: relative; leftImage: url(../tab/images/tabLeft.gif); centerImage: url(../tab/images/tabCenter.gif); rightImage: url(../tab/images/tabRight.gif); leftImageOver: url(../tab/images/tabLeftOver.gif); centerImageOver: url(../tab/images/tabCenterOver.gif); rightImageOver: url(../tab/images/tabRightOver.gif); leftImageDown: url(../tab/images/tabLeftDown.gif); centerImageDown: url(../tab/images/tabCenterDown.gif); rightImageDown: url(../tab/images/tabRightDown.gif)
}
</style>
<link rel="StyleSheet" href="EOMSListbox.css" type="text/css">

<?import namespace = BOCO implementation = "../tab/EOMSTab.htc" />
</HEAD>

<base target="_self">
<BODY style="margin:0" onkeydown="if (event.keyCode==116){reload.href=window.location.href;reload.click()}">

<table width="100%" height="100%-32" border="0" cellspacing="1" cellpadding="0" bgcolor="#BEDEF8">
  <form name="form1">
    <input type="hidden" name="saveID" id="saveID" value="">
    <tr>
      <td rowspan="3" bgcolor="#E2F1FC" valign="top" width="22"></td>
      <td nowrap class="small" bgcolor="#E2F1FC" valign="absmiddle">&nbsp;&nbsp;选择工单的接受方：
	  </td>
    </tr>
    <tr>
      <td bgcolor="#FFFFFF" height="100%" valign="top">

<TABLE cellSpacing=0 cellPadding=0 width=100% border=0 align="center">
  <TBODY>
  <TR>
    <TD valign="top" width="50%">

	<BOCO:EOMSTab id=myTab selectedIndex="0">
	<BOCO:EOMSTabButton tabPage="groupRoot" tabText="自定义组"/>
	<BOCO:EOMSTabButton tabPage="listboxRoot" tabText="部门树"/>
	</BOCO:EOMSTab>

	<!-- 组 开始------------------------>
	<SPAN class="EOMSListbox" id=groupRoot onchange="jscript: fnEOMSListboxOnChange('groupRoot');" style="margin:5px;">
      <TABLE cellSpacing=0 cellPadding=0>
        <TBODY id=rootTbody>

<c:choose>
	<c:when test="${requestScope['GROUPS'] != null}">
	<logic:iterate id="tawWsGroup" name="GROUPS" type="com.boco.eoms.jbzl.flow.model.TawWsGroup">

	<bean:define id="groupIdI" name="tawWsGroup" property="groupId" type="java.lang.Integer"/>
	<%
		int groupId = groupIdI.intValue();
	%>
	<TR class="EOMSListboxGroup" id="tr_groupid_<%=groupId%>">
		<TD class="EOMSListboxImage"><IMG id="groupimg_parentid_<%=groupId%>" name="groupimg_parentid_<%=groupId%>" src="images/false.gif" onclick="openGroup(<%=groupId%>)" style="cursor:hand"><!--<IMG src="<%=webapp%>/sysadmin/flow/listbox/images/group.gif">--></TD>
		<TD class="EOMSListboxCaption"><bean:write name="tawWsGroup" property="groupName" scope="page"/></TD>
		<TD class="EOMSListboxValue"><%=groupId%></TD>
		<TD class="EOMSListboxTag"></TD>
		<TD class="EOMSListboxParent"></TD>

	</TR>

			<tr style="display:none" id="grouptr_treeid_<%=groupId%>">
			<td width=5><IMG height=5  src="" width=5></td>
			<td id="grouptd_treeid_<%=groupId%>" colspan="4">

				<table cellSpacing=0 cellPadding=0 id='table_groupid_<%=groupId%>'><TBODY id=rootTbody>
			<%
				//列出组下的子部门
				TawWsGroupBO tawGroupBO = new TawWsGroupBO();
				ArrayList list = tawGroupBO.getGroupMemberInfor(groupId);

				TawDeptBO tawDeptBO = new TawDeptBO();
				TawRmUserBO tawUserBO = new TawRmUserBO();
				for (int i=0;i<list.size();i++){
					TawFlowGroup tawFlowGroup = (TawFlowGroup)list.get(i);
					String sID = StaticMethod.nullObject2String(tawFlowGroup.getMemberId());

					int dID = StaticMethod.nullObject2int(tawFlowGroup.getMemberId());
					String dName = tawFlowGroup.getMemberName();
					int memberType = tawFlowGroup.getMemberType();

					out.println("<tr class=EOMSListboxItem id='group"+groupId+"tr_parentid_"+dID+"'>");
					out.println("<td class=EOMSListboxImage><img src='"+webapp+"/css/listbox/images/dept.gif' align='TextTop'></td>");
					out.println("<td class=EOMSListboxCaption>"+dName+"</td>");
					out.println("<td class=EOMSListboxValue>"+dID+"</td>");
					out.println("<td class=EOMSListboxTag></TD>");
					out.println("<td class=EOMSListboxParent></TD>");

					String id = "1";
					if(memberType == 1){
					  id = tawDeptBO.getDeptId(dID);
					}
					else if(memberType == 2){
					   id =	tawDeptBO.getDeptId(tawUserBO.getDeptIdByUID(sID));
					}

					out.println("<td class=EOMSListboxParent>"+id+"</td></tr>");
				}
			%>
				</tbody></table>

			</td></tr>

	</logic:iterate>
	</c:when>
</c:choose>

		</TBODY></TABLE>

	</SPAN>
	<!-- 组 结束------------------------>

	<!-- 部门树 开始------------------------>
	<SPAN class="EOMSListbox" id=listboxRoot onchange="jscript: fnEOMSListboxOnChange('listboxRoot');" style="display:none;margin:5px;">
      <TABLE cellSpacing=0 cellPadding=0>
        <TBODY id=rootTbody>
<%
	TawDeptBO deptbo = new TawDeptBO();
	List list_dept = deptbo.getSecondDepts(-1);
	for (int i=0;i<list_dept.size(); i++ ){
		TawDept dept = (TawDept)list_dept.get(i);
		int deptid = dept.getDeptId();
		String ID = dept.getId();
        String deptname = dept.getDeptName();
%>
        <TR class=EOMSListboxItem id="tr_parentid_<%=deptid%>">
          <TD class=EOMSListboxImage>
		  <IMG id="img_parentid_<%=deptid%>" name="img_parentid_<%=deptid%>" src="images/false.gif" onclick="load('',<%=deptid%>)" style="cursor:hand"></TD>
          <TD class=EOMSListboxCaption><%=deptname%></TD>
          <TD class=EOMSListboxValue><%=deptid%></TD>
          <TD class=EOMSListboxTag></TD>
		  <TD class=EOMSListboxParent><%=ID%></TD></TR>

			<tr style="display:none" id="tr_treeid_<%=deptid%>">
			<td width=5><IMG height=5  src="" width=5></td>
			<td id="td_treeid_<%=deptid%>" colspan="4">
			<div>loading...</div></td></tr>
<%}%>

		</TBODY></TABLE>
		</SPAN>

	<!-- 部门树 结束------------------------>
	</TD>

<!--操作按钮列 开始-->
    <TD align="center" width="50">
	<span style="magin:5px;HEIGHT:expression(document.body.offsetHeight - 95);width:50px">
	<?import namespace=BOCO implementation="<%=request.getContextPath()%>/css/button/genericButton.htc"/>
	<table height="100%">

<%	for(int b=0;b<arrSort.length;b++){ %>
	<tr><td valign="middle" width="50" align="center">
	<BOCO:genericButton css="classic" onclick="fnEOMSListboxMoveRow('listboxSort<%=b+1%>');" image="<%=request.getContextPath()%>/css/listbox/images/btnarrow.gif">
	 <%=arrSort[b]%>
	</BOCO:genericButton>
	</td></tr>
<%	} %>

	</table>
	</span>
	</TD>
<!--操作按钮列 结束-->

<!--选择结果列 开始-->
	<TD valign="top" width="50%">
	<SPAN style="HEIGHT:expression(document.body.offsetHeight - 90);WIDTH: 100%">
	<table width=100% cellSpacing=5 cellPadding=0>
<%	for(int b=0;b<arrSort.length;b++){ %>
	<tr><td>
	<SPAN class="EOMSListbox" id="listboxSort<%=b+1%>" style=" WIDTH: 100%; HEIGHT: expression( (document.body.offsetHeight - 90 - <%=arrSort.length%> * 3) / <%=arrSort.length%>)" onchange="fnEOMSListboxOnChange('listboxSort<%=b+1%>');" ondblclick="fnEOMSListboxDelRow('listboxSort<%=b+1%>')">
      <TABLE cellSpacing=0 cellPadding=0>
        <TBODY id=sortTbody></TBODY></TABLE></SPAN>
    </td></tr>
<%	} %>
	</table>
	<SPAN>
		</TD>
<!--选择结果列 结束-->

	</TR>
</TBODY></TABLE>

		</td>
    </tr>
    <tr>
      <td bgcolor="#E2F1FC">
	  &nbsp;显示人员:<%if(strUseroff.equals("no")) out.print("否");else{out.print("是");}%>&nbsp;|
	  &nbsp;允许选择多个操作:<%if(strCheckall.equals("no")) out.print("是");else{out.print("否");}%>&nbsp;|
	  &nbsp;可以选择本部门:<%if(strSelectSelf.equals("yes")) out.print("是");else{out.print("否");}%>&nbsp;|
	  </td>
    </tr>
	<tr>
	  <td colspan="2" class="buttonBanner" height="30">
	  <table WIDTH="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr><td HEIGHT="1" BGCOLOR="buttonshadow"></td></tr>
	  <tr><td HEIGHT="1" BGCOLOR="buttonhighlight"></td></tr>
	  </table>
<%
	String strOnclick = "";
	String strReset = "";
	for (int i=1;i<=arrSort.length;i++){
		strOnclick += "getResult('listboxSort"+i+"','sort"+i+"');";
		strReset += "resetResult('sort"+i+"');";
	}
%>

<TABLE cellpadding="5" width="100%">
<TR>
	<TD id="infofield"  width="70%" nowrap>&nbsp;</TD>
	<TD width="50">
	<BOCO:genericButton onclick="<%=strOnclick%>window.returnValue=true;window.close();"> 确定 </BOCO:genericButton></TD>
	<TD width="50">
	<BOCO:genericButton onclick="window.close();"> 取消</BOCO:genericButton></TD>
	<TD width="50">
	<BOCO:genericButton onclick="resetResult();"> 清空</BOCO:genericButton></TD>
</TR>
</TABLE>
	  </td>
	</tr>
  </form>
</table>

<a id="reload" href="listbox.jsp" style="display:none">reload...</a>
<iframe id="bufferFrame" name="bufferFrame" width="0" height="0" src="" frameborder=no></iframe>

</BODY>
</HTML>