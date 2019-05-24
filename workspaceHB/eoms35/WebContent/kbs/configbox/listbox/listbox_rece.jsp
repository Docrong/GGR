<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ page import = "java.util.*,java.lang.*"%>
<%@ page import ="com.boco.eoms.common.controller.SaveSessionBeanForm"%>
<%@ page import = "com.boco.eoms.common.util.StaticMethod"%>
<%@ page import = "com.boco.eoms.common.util.StaticVariable"%>
<%@ page import = "com.boco.eoms.kbs.model.*"%>

<%
response.addHeader("Cache-Control", "no-cache");
response.addHeader("Expires", "Thu, 01 Jan 1970 00:00:01 GMT");

String webapp = request.getContextPath();
String themePath = webapp + "/skins/themes/blue";

SaveSessionBeanForm saveSessionBeanForm =
          (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");

int myDeptId = saveSessionBeanForm.getWrf_DeptID();
String myUserId = saveSessionBeanForm.getWrf_UserID();

int groupId = StaticMethod.nullObject2int(request.getParameter("groupId"));

String[] arrSort = {"ѡ��"};
String strUseroff = "yes";
String strPostoff = "yes";
String strCheckall = "";
String strSelectSelf = "no";

String infoId = "tr_parentid_" + StaticMethod.nullObject2String(request.getAttribute("infoId"));
%>

<HTML xmlns:BOCO="BOCO Inter-Telecom">
<HEAD>
    <c:choose>
      <c:when test="${requestScope['tawWsGroupForm'].strutsAction == 1}">
           <TITLE><bean:message key="label.add"/>������</TITLE>
      </c:when>
      <c:otherwise>
        <TITLE><bean:message key="label.edit"/>������</TITLE>
      </c:otherwise>
    </c:choose>

<script language="JavaScript" src="<%=webapp%>/css/checkform.js"></script>
<SCRIPT language="jscript">

 	var father = dialogArguments.document;
	var pageSelected = null;
	var objSelectedItems = null ;
	var trueIcon = new Image();
		trueIcon.src = "<%=webapp%>/kbs/configbox/listbox/images/true.gif";
	var falseIcon = new Image();
		falseIcon.src = "<%=webapp%>/kbs/configbox/listbox/images/false.gif";
	var userIcon = new Image();
		userIcon.src = "<%=webapp%>/kbs/configbox/listbox/images/user.gif";
        var postIcon = new Image();
		postIcon.src = "<%=webapp%>/kbs/configbox/listbox/images/post.gif";
	var deptIcon = new Image();
		deptIcon.src = "<%=webapp%>/kbs/configbox/listbox/images/dept.gif";

	var sortNum = <%=arrSort.length%>;
	var arrSortID = new Array();
	for(i=0;i<sortNum;i++){
		arrSortID[i] = "listboxSort" + (i + 1);
	}

	var checkall = "<%=strCheckall%>";
	var selectself = "<%=strSelectSelf%>";


	var infoid = "<%=infoId%>";

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

	}

	function fnEOMSListboxMoveRow(elName){
		if (objSelectedItems.count() == 0)
			alert("��ѡ��һ����Ŀ��");
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
			str=str.substring(12);
		}
                if(str.indexOf("tr_postid_")!=-1){
			str=str.substring(10);
		}
		if(str.indexOf("tr_userid_")!=-1){
			str=str.substring(10);
		}

		return str;
	}

	function fnEOMSListboxAddRow(elName) {
		var listBox = window.document.all("listboxRoot");
		var mydept = "<%=myDeptId%>";
		var myuser = "<%=myUserId%>";


			with (window.document.all(elName)) {

				var alertAllInfo = "";

				for(var i=0; i<objSelectedItems.count();i++){

					var alertItemInfo = "";
					var item = objSelectedItems.getSelectedItem(i);

		//				alert("item.ID=" + item.ID +
		//					"\nitem.parentID=" + item.parentID +
		//					"\nitem.text=" + item.text +
		//					"\nitem.value=" + item.value +
		//					"\nitem.tagInfo=" + item.tagInfo);

					//�жϴ���Ŀ�ܷ�ѡ��
					if (checkall != "no"){

						for(j=0;j<arrSortID.length;j++){

                                                   if (inBox(elName,item.ID))
								{alertItemInfo += "\n����Ŀ\""+item.text+"\"�Ѿ���ѡ����.�����ظ�ѡ��";break;}
							if (item.property == "user" && inBox(arrSortID[j],item.parentID))
								{alertItemInfo += "\n��Ա\""+item.text+"\"�����Ĳ����Ѿ���ѡ����, ����Ա���ܱ�ѡ��";break;}
							if (item.property == "post" && inBox(arrSortID[j],item.parentID))
								{alertItemInfo += "\n��λ\""+item.text+"\"�����ĸ�λ�Ѿ���ѡ����, �ø�λ���ܱ�ѡ��";break;}
                                                        if (item.property == "dept" && parentinBox(arrSortID[j],item.ID))
								{alertItemInfo += "\n����\""+item.text+"\"�µ���Ա�Ѿ���ѡ���ˣ��ò��Ų��ܱ�ѡ��";break;}

						}
					}
					else{
						if (inBox(elName,item.ID))
							{alertItemInfo += "\n����Ŀ\""+item.text+"\"�Ѿ���ѡ����.�����ظ�ѡ��";}
						if (item.property == "user" && inBox(elName,item.parentID))
							{alertItemInfo += "\n��Ա\""+item.text+"\"�����Ĳ����Ѿ���ѡ����, ����Ա���ܱ�ѡ��";}
                                                if (item.property == "post" && inBox(elName,item.parentID))
							{alertItemInfo += "\n��λ\""+item.text+"\"�����ĸ�λ�Ѿ���ѡ����, �ø�λ���ܱ�ѡ��";}
						if (item.property == "dept" && parentinBox(elName,item.ID))
							{alertItemInfo += "\n����\""+item.text+"\"�µ���Ա�Ѿ���ѡ���ˣ��ò��Ų��ܱ�ѡ��";}
					}

					//����ѡ����
					if (alertItemInfo == ""){
						if(item.property == "dept") {

                                                	exData = "<input type='checkbox' name='DEPTMEMBERS'  hidefocus checked style='display:none' value='"+ item.value+"'><img src='"+deptIcon.src+"'>"
							         +"<input type='hidden' name='deptNameArray'  value='"+item.text+"'>";

                                                                 addItem("C", item.text, item.value, -1, exData, item.ID);
						}
                     //                           if(item.property == "post") {
                      //                                 exData = "<input type='checkbox' name='POSTMEMBERS'  hidefocus checked style='display:none' value='"+ item.value+"'><img src='"+postIcon.src+"'>"
                     //                                           +"<input type='hidden' name='postNameArray'  value='"+item.text+"'>";
                     //                                          addItem("C", item.text, item.value, -1, exData,item.ID, item.parentID);
                     //                           }
			//			if(item.property == "user"){
                      //                                 exData = "<input type='checkbox' name='USERMEMBERS'  hidefocus checked style='display:none' value='"+ item.value+"'><img src='"+userIcon.src+"'>"
                     //                                          +"<input type='hidden' name='userNameArray'  value='"+item.text+"'>";
                     //                                  addItem("C", item.text, item.value, -1, exData,item.ID, item.parentID);
			//			}
					}

					//����ÿ��ѡ���Ĳ���
					alertAllInfo += alertItemInfo;

					if(checkall != "no")
					{
						item.className = (alertItemInfo == "") ? "EOMSListboxItemUnEnabled" : "EOMSListboxItem";
					}
					if(checkall == "no")
					{
						var allSelected = true;
						for(k=0;k<arrSortID.length;k++){
							if (!inBox(arrSortID[k],item.ID))
								{allSelected = false;break;}
						}

						item.className = (allSelected == true) ? "EOMSListboxItemUnEnabled" : "EOMSListboxItem";
					}

				}//END OF FOR

				//����ȫ��ѡ���Ĳ���
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
					if(document.getElementById(rootId)){
						var rootEl = document.getElementById(rootId);
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
					var rootId = optionsElement.rows[0].children[3].innerText;
					optionsElement.parentElement.removeItem(0);
					if(document.getElementById(rootId)){
						var rootEl = document.getElementById(rootId);
						if (rootEl.className = "EOMSListboxItemUnEnabled"){
							rootEl.className = "EOMSListboxItem";
						}
					}
			}
		}
	}

	function loadDeptPost(divName,divID){

		var targetTR = eval("tr_treeid_d" + divID);
		var targetTD = eval('td_treeid_d' + divID);

		if (targetTR.style.display!="block"){
			targetTR.style.display="block";
			eval("document.img_parentid_"+divID+".src=trueIcon.src");
			bufferFrame.document.location="<%=webapp%>/kbs/configbox/listbox/loaddeptpost.jsp?divID="+divID+"&post=<%=strPostoff%>&selectself=<%=strSelectSelf%>&mydeptid=<%=myDeptId%>&myuserid=<%=myUserId%>";
		}
		else{
			targetTR.style.display="none";
			eval("document.img_parentid_"+divID+".src=falseIcon.src");
		}
	}

        function loadUser(divName,divID){
		var targetTR = eval("tr_treeid_p" + divID);
		var targetTD = eval('td_treeid_p' + divID);

		if (targetTR.style.display!="block"){
			targetTR.style.display="block";
			eval("document.img_parentid_"+divID+".src=postIcon.src");
			bufferFrame.document.location="<%=webapp%>/kbs/configbox/listbox/loaduser.jsp?divID="+divID+"&selectself=<%=strSelectSelf%>&mydeptid=<%=myDeptId%>&myuserid=<%=myUserId%>";

		}
		else{
			targetTR.style.display="none";
			eval("document.img_parentid_"+divID+".src=postIcon.src");
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
                               alert("itemCount():"+itemCount());
                              alert("i:"+i);

                              alert(getItemParentID(i));
                              if (targetID == getItemParentID(i))	return true;
			}
			return false;
		}
	}

	function getResult(elName,targetLayer){
		var arrReceiversDeptID = new Array();
                var arrReceiversPostID = new Array();
		var arrReceiversUserID = new Array();
		var arrReceiversName = new Array();

		with (window.document.all(elName)) {
			for (var i=0;i<itemCount();i++){
				if((getItemTag(i).indexOf("tr_parentid_")) != -1){
					arrReceiversDeptID = arrReceiversDeptID.concat(getItemValue(i));
					arrReceiversName[i] = "<img src='"+deptIcon.src+"' align=absmiddle><span id=\""+getItemTag(i)+"\">"+getItemText(i)+"</span>";
				}
                                if((getItemTag(i).indexOf("tr_postid_")) != -1){
					arrReceiversPostID = arrReceiversPostID.concat(getItemValue(i));
					arrReceiversName[i] = "<img src='"+postIcon.src+"' align=absmiddle><span id=\""+getItemTag(i)+"\" parentid=\""+getItemParentID(i)+"\">"+getItemText(i)+"</span>";
				}
				if((getItemTag(i).indexOf("tr_userid_")) != -1){
					arrReceiversUserID = arrReceiversUserID.concat(getItemValue(i));
					arrReceiversName[i] = "<img src='"+userIcon.src+"' align=absmiddle><span id=\""+getItemTag(i)+"\" parentid=\""+getItemParentID(i)+"\">"+getItemText(i)+"</span>";
				}
			}

			father.getElementById(targetLayer+"_infoid").value = arrReceiversDeptID.join(",");
			father.getElementById(targetLayer+"_userid").value = arrReceiversUserID.join(",");
			father.getElementById(targetLayer+"_text").innerHTML = arrReceiversName.join(",");
		}
	}


	function window.onload(){
		//load('',1);
	}

	function saveResult(){
		if (document.tawInformationForm.DEPTMEMBERS){
                var length =  document.tawInformationForm.DEPTMEMBERS.length;
                var obj = window.dialogArguments;

                obj.receiverName.value="";
                if(length!=null){
                for(i=0;i<length;i++){
                obj.deptids.value +=document.tawInformationForm.DEPTMEMBERS[i].value+",";
                obj.receiverName.value +=document.tawInformationForm.deptNameArray[i].value+" ";
                }
                }
                else
                 {obj.deptids.value =document.tawInformationForm.DEPTMEMBERS.value;
                 obj.receiverName.value =document.tawInformationForm.deptNameArray.value;
                 }
		}

               if (document.tawInformationForm.POSTMEMBERS){
                var length =  document.tawInformationForm.POSTMEMBERS.length;
                var obj = window.dialogArguments;

                obj.receiverName.value="";
                if(length!=null){
                for(i=0;i<length;i++){
                obj.postids.value +=document.tawInformationForm.POSTMEMBERS[i].value+",";
                obj.receiverName.value +=document.tawInformationForm.postNameArray[i].value+" ";
                }
                }
                else
                 {obj.postids.value =document.tawInformationForm.POSTMEMBERS.value;
                 obj.receiverName.value =document.tawInformationForm.postNameArray.value;
                 }
		}

                if (document.tawInformationForm.USERMEMBERS){
                var length =  document.tawInformationForm.USERMEMBERS.length;
                var obj = window.dialogArguments;

                obj.receiverName.value="";
                if(length!=null){
                for(i=0;i<length;i++){
                obj.userids.value +=document.tawInformationForm.USERMEMBERS[i].value+",";
                obj.receiverName.value +=document.tawInformationForm.userNameArray[i].value+" ";
                }
                }
                else
                 {obj.userids.value =document.tawInformationForm.USERMEMBERS.value;
                 obj.receiverName.value =document.tawInformationForm.userNameArray.value;
                 }
		}
		 window.close();
	}

</SCRIPT>

<link rel="StyleSheet" href="<%=webapp%>/kbs/configbox/listbox/EOMSListbox.css" type="text/css">
<link rel="stylesheet" href="<%=webapp%>/css/table_style.css" type="text/css">
</HEAD>

<base target="_self">
<BODY style="margin:0" onkeydown="if (event.keyCode==116){reload.href=window.location.href;reload.click()}">

<table width="100%" height="100%-32" border="0" cellspacing="1" cellpadding="0" bgcolor="#BEDEF8">

<html:form method="post" action="/TawInformation/receedit">

    <input type="hidden" name="saveID" id="saveID" value="">
    <tr>
      <td rowspan="3" bgcolor="#E2F1FC" valign="top" width="22"></td>
      <td nowrap class="small" bgcolor="#E2F1FC" valign="absmiddle">&nbsp;&nbsp;ѡ�����������֯��Ԫ��
	  </td>
    </tr>
    <tr>
      <td bgcolor="#FFFFFF" height="100%" valign="top">

<TABLE cellSpacing=0 cellPadding=0 width=100% border=0 align="center">
  <TBODY>
  <TR>
    <TD valign="top" width="45%">
	<SPAN class="EOMSListbox" id=listboxRoot onchange="jscript: fnEOMSListboxOnChange('listboxRoot');" style="margin:5px;">
      <TABLE cellSpacing=0 cellPadding=0>
        <TBODY id=rootTbody>
        <TR class=EOMSListboxItem id="tr_parentid_1">
          <TD class=EOMSListboxImage>
		  <IMG id="img_parentid_1" name="img_parentid_1" src="<%=webapp%>/kbs/configbox/listbox/images/false.gif" onclick="loadDeptPost('',1)" style="cursor:hand"></TD>
          <TD class=EOMSListboxCaption>ʡ��˾</TD>
          <TD class=EOMSListboxValue>1</TD>
          <TD class=EOMSListboxTag></TD></TR>

			<tr style="display:none" id="tr_treeid_d1">
			<td width=5><IMG height=5  src="" width=5></td>
			<td id="td_treeid_d1" colspan="3">
			<div>loading...</div></td></tr>

		</TBODY></TABLE></SPAN>
	</TD>

<!--������ť�� ��ʼ-->
    <TD align="center" width="100">
	<span style="magin:5px;HEIGHT:expression(document.body.offsetHeight - 95);width:80px">
	<?import namespace=BOCO implementation="<%=webapp%>/css/button/genericButton.htc"/>
	<table height="100%">

<%	for(int b=0;b<arrSort.length;b++){ %>
	<tr><td valign="middle" width="50" align="center">
	<BOCO:genericButton css="classic" onclick="fnEOMSListboxMoveRow('listboxSort<%=b+1%>');" image="<%=webapp%>/css/listbox/images/btnarrow.gif">
	 <%=arrSort[b]%>
	</BOCO:genericButton>
	</td></tr>
<%	} %>

	</table>
	</span>
	</TD>
<!--������ť�� ����-->

<!--ѡ������ ��ʼ-->
	<TD valign="top" width="45%">
	<SPAN style="HEIGHT:expression(document.body.offsetHeight - 95);WIDTH: 100%">
	<table width=100% cellSpacing=5 cellPadding=0>
<%	for(int b=0;b<arrSort.length;b++){ %>
	<tr><td>
	<SPAN class="EOMSListbox" id="listboxSort<%=b+1%>" style=" WIDTH: 100%; HEIGHT: expression( (document.body.offsetHeight - 95 - <%=arrSort.length%> * 3) / <%=arrSort.length%>)" onchange="fnEOMSListboxOnChange('listboxSort<%=b+1%>');" ondblclick="fnEOMSListboxDelRow('listboxSort<%=b+1%>')">
      <TABLE cellSpacing=0 cellPadding=0>
        <TBODY id=sortTbody>

    <c:choose>
      <c:when test="${requestScope['KBSRECEIVERS'] != null}">

<logic:iterate id="kbsReceiver" name="KBSRECEIVERS" type="com.boco.eoms.kbs.model.KbsReceiver">

<bean:define id="receiverType" name="kbsReceiver" property="receiverType" type="java.lang.Integer"/>
<bean:define id="receiver" name="kbsReceiver" property="receiver" type="java.lang.String"/>

<%
int receiver_type = receiverType.intValue();

String strMemberType = "";
String strImg = "";
String strTrId = "";
switch (receiver_type){
	case 1:
                strMemberType = "DEPTMEMBERS";
		strImg = "dept.gif";
		strTrId = "tr_parentid_"+receiver;
		break;
       case 2:
		strMemberType = "POSTMEMBERS";
		strImg = "post.gif";
		strTrId = "tr_postid_"+receiver;
		break;
	case 3:
		strMemberType = "USERMEMBERS";
		strImg = "user.gif";
		strTrId = "tr_userid_"+receiver;
		break;
	default:
		strMemberType = "DEPTMEMBERS";
		strImg = "dept.gif";
		strTrId = "tr_parentid_"+receiver;
}

//System.out.println(strMemberType);
%>
    <tr class="EOMSListboxItem" id="<%=strTrId%>">
	<td class="EOMSListboxImage">
	<input type="checkbox" name="<%=strMemberType%>" hidefocus checked style="display:none" value="<%=receiver%>">
	<IMG src="<%=webapp%>/kbs/configbox/listbox/images/<%=strImg%>"></td>
	<td class="EOMSListboxCaption"><bean:write name="kbsReceiver" property="receiverName" scope="page"/></td>
	<td class="EOMSListboxValue"><%=receiver%></td>
	<td class="EOMSListboxTag"><%=strTrId%></td>
	</tr>
</logic:iterate>

      </c:when>
    </c:choose>


		</TBODY>
</TABLE></SPAN>
    </td></tr>
<%	} %>
	</table>
	</SPAN>
		</TD>
<!--ѡ������ ����-->

	</TR>
</TBODY></TABLE>

		</td>
    </tr>
    <tr>
      <td bgcolor="#E2F1FC">
          &nbsp;��ʾ��λ:<%if(strPostoff.equals("no")) out.print("��");else{out.print("��");}%>&nbsp;|
	  &nbsp;��ʾ��Ա:<%if(strUseroff.equals("no")) out.print("��");else{out.print("��");}%>&nbsp;|
	  &nbsp;����ѡ��������:<%if(strCheckall.equals("no")) out.print("��");else{out.print("��");}%>&nbsp;|
	  &nbsp;����ѡ�񱾲���:<%if(strSelectSelf.equals("yes")) out.print("��");else{out.print("��");}%>&nbsp;|
	  </td>
    </tr>
	<tr>
	  <td colspan="2" align="right" class="buttonBanner" height="30">
	  <table WIDTH="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr><td HEIGHT="1" BGCOLOR="buttonshadow"></td></tr>
	  <tr><td HEIGHT="1" BGCOLOR="buttonhighlight"></td></tr>
	  </table>
<%

%>
<TABLE cellpadding="5" width="30%">
<TR>
	<TD width="50">
	<BOCO:genericButton css="classic" onclick="saveResult();"> ���� </BOCO:genericButton></TD>
	<TD width="50">
	<BOCO:genericButton css="classic" onclick="window.close();"> ȡ��</BOCO:genericButton></TD>
	<TD width="50">
	<BOCO:genericButton css="classic" onclick="resetResult();"> ���</BOCO:genericButton></TD>
</TR>
</TABLE>
	  </td>
	</tr>
  </html:form>
</table>

<a id="reload" href="listbox.jsp" style="display:none">reload...</a>
<iframe id="bufferFrame" name="bufferFrame" width="100" height="100" src="" frameborder=no></iframe>

</BODY>
</HTML>