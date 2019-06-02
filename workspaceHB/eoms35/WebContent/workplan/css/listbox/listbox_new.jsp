<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import = "java.util.*,java.lang.*"%>
<%@ page import ="com.boco.eoms.common.controller.SaveSessionBeanForm"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import ="com.boco.eoms.common.util.StaticVariable"%>
<%@ page import ="com.boco.eoms.worksheet.flow.bo.*"%>
<%@ page import ="com.boco.eoms.worksheet.flow.controller.*"%>
<%@ page import ="com.boco.eoms.worksheet.flow.model.*"%>

<%
	response.addHeader("Cache-Control", "no-cache");
	response.addHeader("Expires", "Thu, 01 Jan 1970 00:00:01 GMT");
%>
<%
// ----------------------------------------------------------------------
// EOMS Listbox 页面组件
// Copyright (c) 2004-2006, 亿阳信通网络事业部 EOMS
// All rights reserved.
// Abstract ：即时取值的树型选择及分配
// Version　：1.0
// Author   ：苗鹏 (mios426@hotmail.com)
// Finished Date ：2004-09-09
// Last Modified ：2004-09-09
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
//
//	待完成功能:
//   不可选择项目灰度显示
//   多选、全选及反选功能
//
//
// ------------------------------------------------------------------------

String[] arrSort = {"派发","抄送","审核"};
String strUseroff = "";
String strCheckall = "";
String strSelectSelf = "";


SaveSessionBeanForm saveSessionBeanForm =
          (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");

int myDeptId = saveSessionBeanForm.getWrf_DeptID();
String myUserId = saveSessionBeanForm.getWrf_UserID();

if (request.getParameter("user") != null){
	strUseroff = request.getParameter("user");
}

if (request.getParameter("checkall") != null){
	strCheckall = request.getParameter("checkall");
}

if (request.getParameter("selectself") != null){
	strSelectSelf = request.getParameter("selectself");
}

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
	var trueIcon = new Image();
		trueIcon.src = "images/true.gif";
	var falseIcon = new Image();
		falseIcon.src = "images/false.gif";
	var userIcon = new Image();
		userIcon.src = "images/user.gif";
	var deptIcon = new Image();
		deptIcon.src = "images/dept.gif";
	var groupIcon = new Image();
		groupIcon.src = "images/group.gif";

	var sortNum = <%=arrSort.length%>;
	var arrSortID = new Array();
	for(i=0;i<sortNum;i++){
		arrSortID[i] = "listboxSort" + (i + 1);
	}
	var checkall = "<%=strCheckall%>";
	var selectself = "<%=strSelectSelf%>";

	function fnEOMSListboxOnChange(elName) {
		with (window.document.all(elName)) {
			var selID = ID ;
			var selText = text ;
			var selValue = value ;
		}
		var msg = "OnChange event!!!" +
				"\nid=" + selID +
				"\ntext=" + selText +
				"\nvalue=" + selValue ;
//		alert(msg) ;
	}

	function fnEOMSListboxMoveRow(elName,operType){
		if (pageSelected == null)
			alert("请选择一个项目。");
		else
		{
			switch (pageSelected.action)
			{
				case "add" : fnEOMSListboxAddRow(elName,operType);break;
				case "del" : fnEOMSListboxDelRow(elName);break;
			}
		}
	}

	function getRealID(str){
		if(str.indexOf("tr_parentid_")!=-1){
			str=str.substring(12);
		}
		if(str.indexOf("tr_userid_")!=-1){
			str=str.substring(10);
		}
		return str;
	}

	function fnEOMSListboxAddRow(elName, operType) {
		var listBox = window.document.all("listboxRoot");
		if (pageSelected.property == "group"){
			listBox = window.document.all("listboxGroup");
		}
		var alertinfo = "";
		var mydept = "<%=myDeptId%>";
		var myuser = "<%=myUserId%>";
		var canOper = pageSelected.operType;
		var strTemp = "";
//	alert(pageSelected.operType);		
		if (pageSelected.operType==""){
			pageSelected.operType = document.getElementById(pageSelected.parentID).operType;
		}

//	alert(pageSelected.operType);
		if ((pageSelected.operType.indexOf(operType)) == -1){
//			var arrTemp = new Array();
//				arrTemp = pageSelected.operType.split();
//			for(int i=0;i<arrTemp.length;i++){
//				strTemp += arrSort[arrTemp[i]-1]+",";
//			}
			alertinfo = "此对象不能执行此操作";
		}

		if(pageSelected.property == "dept" && getRealID(pageSelected.ID)==mydept && selectself!="yes"){
			alertinfo = "不能对本部门进行操作，请选择其他部门。";
		}
		if(pageSelected.property == "user" && getRealID(pageSelected.ID)==myuser && selectself!="yes"){
			alertinfo = "不能对自己进行操作，请选择其他人员。";
		}

		if (checkall=="no"){
			if (inBox(elName,pageSelected.ID))
				{alertinfo = "该项目已经被选择了。";}
			if (pageSelected.property == "user" && inBox(elName,pageSelected.parentID))
				{alertinfo = "该人员\""+pageSelected.text+"\"所属的部门已经被选择了。";}
			if (pageSelected.property == "dept" && parentinBox(elName,pageSelected.ID))
				{alertinfo = "已经选择了\""+pageSelected.text+"\"下的人员。";}
		}
		else
		{
			for(i=0;i<arrSortID.length;i++){
				if (inBox(arrSortID[i],pageSelected.ID))
					{alertinfo = "该项目已经被选择了。";break;}
				if (pageSelected.property == "user" && inBox(arrSortID[i],pageSelected.parentID))
					{alertinfo = "该人员\""+pageSelected.text+"\"所属的部门已经被选择了。";break;}
				if (pageSelected.property == "dept" && parentinBox(arrSortID[i],pageSelected.ID))
					{alertinfo = "已经选择了\""+pageSelected.text+"\"下的人员。";break;}
			}
		}

		if (alertinfo == "")
		{
			with (window.document.all(elName)) {
				if (pageSelected.property == "dept")
					addItem("I", listBox.text, listBox.value, -1, deptIcon.src, pageSelected.ID);
				if (pageSelected.property == "user")
					addItem("I", listBox.text, listBox.value, -1, userIcon.src, pageSelected.ID, pageSelected.parentID);
				if (pageSelected.property == "group")
					eval("addGroup_"+listBox.value+"(elName);");
//					addItem("I", listBox.text, listBox.value, -1, groupIcon.src, pageSelected.ID);
					
			}
		}
		else {
			alert(alertinfo);
		}
	}

	function fnEOMSListboxDelRow(elName) {
		with (window.document.all(elName)) {
			if (selectedIndex != -1) {
				var rootID = getItemTag(selectedIndex);
				removeItem(selectedIndex);
			}
		}
	}

	function load(divName,divID){
		var targetTR = eval("tr_treeid_" + divID);
		var targetTD = eval('td_treeid_' + divID);

		if (targetTR.style.display!="block"){
			targetTR.style.display="block";
			eval("document.img_parentid_"+divID+".src=trueIcon.src");

			bufferFrame.document.location="loadtree_new.jsp?divID="+divID+"&user=<%=strUseroff%>";

		}
		else{
			targetTR.style.display="none";
			eval("document.img_parentid_"+divID+".src=falseIcon.src");
		}


	}

	function inBox(boxName,targetID){
		with (window.document.all(boxName)) {
			for (var i=0;i<itemCount();i++){
				if (targetID == getItemTag(i))	return true;
			}
			return false;
		}
	}

	function parentinBox(boxName,targetID){
		with (window.document.all(boxName)) {
			for (var i=0;i<itemCount();i++){
				if (targetID == getItemParentID(i))	return true;
			}
			return false;
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
					arrReceiversName[i] = "<img src='"+deptIcon.src+"' align=absmiddle><span id=\""+getItemTag(i)+"\">"+getItemText(i)+"</span>";
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

	function resetResult(){
		var optionsElement;
//		father.getElementById(targetLayer+"_deptid").value = "";
//		father.getElementById(targetLayer+"_userid").value = "";
//		father.getElementById(targetLayer+"_text").innerHTML = "";
		for(var i=0;i<sortNum;i++){
			optionsElement = window.document.all(arrSortID[i]).children[0];
//			alert(optionsElement.rows.length);
			while (optionsElement.rows.length>0) {
					optionsElement.parentElement.removeItem(0);
			}
//			alert(optionsElement.outerHTML);
		}
	}

	function window.onload(){
		var arrSeletedItem = new Array();
		for (i=1;i<=sortNum;i++){
			var strTemp = dialogArguments.document.getElementById("sort"+i+"_text").getElementsByTagName("span");
			for(j=0;j<strTemp.length;j++){

				if(strTemp.item(j).id.indexOf("tr_parentid_")!=-1){
					window.document.all(arrSortID[i-1]).addItem("I", strTemp.item(j).innerHTML, getRealID(strTemp.item(j).id), -1, deptIcon.src, strTemp.item(j).id);
				}
				if(strTemp.item(j).id.indexOf("tr_userid_")!=-1){
					window.document.all(arrSortID[i-1]).addItem("I", strTemp.item(j).innerHTML, getRealID(strTemp.item(j).id), -1, userIcon.src, strTemp.item(j).id, strTemp.item(j).parentid);
				}
			}


		}
	}
</SCRIPT>

<link rel="StyleSheet" href="EOMSListbox.css" type="text/css">

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
<!---------------------------------------------------------------------------------------------------------->
<!--部门人员列表 开始-->
<!---------------------------------------------------------------------------------------------------------->
	<SPAN class="EOMSListbox" id=listboxRoot onchange="jscript: fnEOMSListboxOnChange('listboxRoot');" style="margin:5px;HEIGHT: expression(document.body.offsetHeight - 95);">
      <TABLE cellSpacing=0 cellPadding=0>
        <TBODY id=rootTbody>
<%
	int sheetType =
		StaticMethod.nullObject2int(request.getParameter("sheetType"),1);

	HashMap map = new HashMap();

	TawWsFlowBO tawWsFlowBO = new TawWsFlowBO();
	TawWsGroupBO tawWsGroupBO = new TawWsGroupBO();
	HashMap list = tawWsFlowBO.getSendObjectsToJsp(myDeptId,sheetType,map);

	Iterator itr2 = list.keySet().iterator();
	
	String[] memberIcon = {"images/dept.gif","images/user.gif"};
	String[] trId = {"tr_parentid_","tr_userid_","tr_groupid_"};
	String[] operIcon = {"<img src='images/oper1.gif'>","<img src='images/oper2.gif'>","<img src='images/oper3.gif'>"};

	while(itr2.hasNext()){
		Object obj = itr2.next();
		TawWsFlow tawWsFlow = (TawWsFlow)list.get(obj);
		//操作类型
		//int operType =  tawWsFlow.getOperType();
		ArrayList operTypes = (ArrayList)map.get(obj);
		for(int j=0;j<operTypes.size();j++){
			String strTemp = operTypes.get(j).toString();
			System.out.println(strTemp);
		}
		
		String toObject = tawWsFlow.getToObject();
		int toType = tawWsFlow.getToType();
		String toName = tawWsFlow.getToName();

		switch(toType){
		    case StaticVariable.OBJECT_DEPT:
				String operTypeString = "";
				for(int j=0;j<operTypes.size();j++){
					operTypeString += StaticMethod.nullObject2String(operTypes.get(j));
				}

%>
				<TR class=EOMSListboxItem id="tr_parentid_<%=toObject%>">
					<TD class=EOMSListboxImage>
					<IMG id="img_parentid_<%=toObject%>" name="img_parentid_<%=toObject%>" src="images/false.gif" onclick="load('',<%=toObject%>)" style="cursor:hand"></TD>
					<TD class=EOMSListboxCaption><%=toName%>
					<%
						for(int j=0;j<operTypes.size();j++){
							int intTemp = StaticMethod.nullObject2int(operTypes.get(j));
							out.print(operIcon[intTemp-1]);
						}
					%>					
					</TD>
					<TD class=EOMSListboxValue><%=toObject%></TD>
					<TD class=EOMSListboxTag><%=operTypeString%></TD>
 				</TR>
						<tr style="display:none" id="tr_treeid_<%=toObject%>">
							<td width=5><IMG height=5  src="" width=5></td>
							<td id="td_treeid_<%=toObject%>" colspan="3">
								<div>loading...</div></td>
						</tr>
<%
				break;
	    	case StaticVariable.OBJECT_USER:
%>
				<TR class=EOMSListboxItem id="tr_userid_<%=toObject%>">
					<TD class=EOMSListboxImage>
					<IMG src="images/user.gif" ></TD>
					<TD class=EOMSListboxCaption><%=toName%>
					<%
						for(int j=0;j<operTypes.size();j++){
							int intTemp = StaticMethod.nullObject2int(operTypes.get(j));
							out.print(operIcon[intTemp-1]);
						}
					%>		
					</TD>
					<TD class=EOMSListboxValue><%=toObject%></TD>
					<TD class=EOMSListboxTag>
					<%
						for(int j=0;j<operTypes.size();j++){
							int intTemp = StaticMethod.nullObject2int(operTypes.get(j));
							out.print(intTemp);
						}
					%>	</TD>
 				</TR>
<%
				break;
			case StaticVariable.OBJECT_GROUP:
			default:
			break;
		}
	}
%>

		</TBODY></TABLE></SPAN>

<!---------------------------------------------------------------------------------------------------------->
<!--部门人员列表 结束-->
<!---------------------------------------------------------------------------------------------------------->
	</TD>

<!--操作按钮列 开始-->
    <TD align="center" width="50">
	<span style="magin:5px;HEIGHT:expression(document.body.offsetHeight - 95);width:50px">
	<?import namespace=BOCO implementation="<%=request.getContextPath()%>/css/button/genericButton.htc"/>
	<table height="100%">

<%	for(int b=0;b<arrSort.length;b++){ %>
	<tr><td valign="middle" width="50" align="center">
	<BOCO:genericButton css="classic" onclick="fnEOMSListboxMoveRow('listboxSort<%=b+1%>',<%=b+1%>);" image="<%=request.getContextPath()%>/css/listbox/images/btnarrow.gif">
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
	<SPAN style="HEIGHT:expression(document.body.offsetHeight - 95);WIDTH: 100%">
	<table width=100% cellSpacing=5 cellPadding=0>
<%	for(int b=0;b<arrSort.length;b++){ 
	String[] spanColor={"#448CCB","#50BE6D","#CB8344"};	
%>
	<tr><td>
	<SPAN class="EOMSListbox" id="listboxSort<%=b+1%>" style="BORDER: <%=spanColor[b]%> 1px solid; WIDTH: 100%; HEIGHT: expression( (document.body.offsetHeight - 95 - <%=arrSort.length%> * 3) / <%=arrSort.length%>)" onchange="fnEOMSListboxOnChange('listboxSort<%=b+1%>');">
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
	  <td colspan="2" align="right" class="buttonBanner" height="30">
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
<TABLE cellpadding="5" width="200px">
<TR>
	<TD>
	<BOCO:genericButton css="classic" onclick="<%=strOnclick%>window.returnValue=true;window.close();"> 确定 </BOCO:genericButton></TD>
	<TD>
	<BOCO:genericButton css="classic" onclick="window.close();"> 取消</BOCO:genericButton></TD>
	<TD>
	<BOCO:genericButton css="classic" onclick="resetResult();"> 清空</BOCO:genericButton></TD>
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