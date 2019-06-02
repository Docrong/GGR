<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="java.util.Hashtable"%>
<%@ page import ="java.util.Enumeration"%>
<%@ page import ="com.boco.eoms.workplan.util.TawwpStaticVariable"%>
<script type="text/javascript" src="../css/functions.js"></script>
<script language="javascript">

function SubmitCheck(){

  frmReg = document.forms[0];

  if(frmReg.systype.value == '0'){
    alert("<bean:message key="netadd.title.warnSysType" />");
    return false;
  }
  if(!JustifyNull1(frmReg.serialno)){
    alert("请输入网元序列号");
    return false;
  }
  if(frmReg.nettype.value == '0'){
    alert("<bean:message key="netadd.title.warnNetType" />");
    return false;
  }

  if( !JustifyNull1(frmReg.name))
  {
    alert( "<bean:message key="netadd.title.warnNetName" />" );
    frmReg.name.focus();
    return false;
  }

  if(frmReg.roomid.value == '0'){
    alert("<bean:message key="netadd.title.warnRoom" />");
    return false;
  }

 if( !JustifyNull1(frmReg.deptid))
  {
    alert("请选择部门");
    frmReg.name.focus();
    return false;
  }
 
 return true;
}

//dap the dept window
function selectTree(){
  dWinOrnaments = "status:no;scroll:no;resizable:yes;dialogHeight:450px;dialogWidth:480px;";
  dWin = showModalDialog('<%=request.getContextPath()%>/css/listbox_zyjh/listbox_depttoinput.jsp?selectself=yes', window, dWinOrnaments);
}


<!--
var onecount;
onecount=0;

var myonecount;
myonecount=0;

subcat = new Array();
mysubcat = new Array();
<%
Hashtable hashtable = TawwpStaticVariable.SYS_NET_INF;

Hashtable myhashtable = TawwpStaticVariable.MY_NET_TYPE_INF;

Hashtable roomHashtable = TawwpStaticVariable.ROOM_INF;
List vendorList = TawwpStaticVariable.VENDOR_INF;

Enumeration sysEnumeration = null;

Enumeration mynetEnumeration = null;

Enumeration roomEnumeration = null;
List netList = null;

String[] sysType = null;
String[] netType = null;

String[] mynetType = null;

String vendor = null;
String room = null;

String netID = "";
String netName = "";
String nettypeid = "";

int count = 0;
sysEnumeration = hashtable.keys();
while(sysEnumeration.hasMoreElements()){
  sysType = (String[])sysEnumeration.nextElement();
  netList = (List)hashtable.get(sysType);
  for(int i=0; i<netList.size(); i++){
    netType = (String[])netList.get(i);
%>
subcat[<%=count++%>] = new Array("<%=netType[0]%>","<%=sysType[1]%>","<%=netType[1]%>");
<%
  } 
}

int mycount = 0;
mynetEnumeration = myhashtable.keys();
while(mynetEnumeration.hasMoreElements()){
  mynetType = (String[])mynetEnumeration.nextElement();
  nettypeid = (String)myhashtable.get(mynetType);
%>
mysubcat[<%=mycount++%>] = new Array("<%=mynetType[0] %>","<%=mynetType[1] %>","<%=nettypeid %>");
<%
  } 
%>

onecount=<%=count%>;
myonecount=<%=mycount%>;

function changelocation(locationid){
  document.forms[0].nettype.length = 0;
  var locationid=locationid;
  var i;

  document.forms[0].nettype.options[0] = new Option('<bean:message key="netadd.title.formSelectNet" />','0');

  for (i=0;i < onecount; i++){
    if (subcat[i][1] == locationid){
      document.forms[0].nettype.options[document.forms[0].nettype.length] = new Option(subcat[i][0], subcat[i][2]);
    }
  }
}

function mychangelocation(locationid){
  var locationid=locationid;
  document.forms[0].mynettype.length = 0;
  document.forms[0].mynettype.options[0] = new Option('选择专业代码','0');
  for (i=0;i < myonecount; i++){
    if (mysubcat[i][2] == locationid){
      document.forms[0].mynettype.options[document.forms[0].mynettype.length] = new Option(mysubcat[i][0],mysubcat[i][1]);
    }
  }
}
//-->

</script>
<script type="text/javascript">
Ext.onReady(function(){
	colorRows('listTable');
	var	userTreeAction='${app}/xtree.do?method=dept';
	userViewer = new Ext.JsonView("user-list",'<div id="user-{id}" class="viewlistitem-user">{name}</div>',
		{ 
			multiSelect: true,		 
			emptyText : '<div>没有选择部门</div>'								 
		}
	);
 	
	//var s = ' '; 
	//userViewer.jsonData = eoms.JSONDecode(s);
	 userViewer.refresh();

	
	userTree = new xbox({
		btnId:'userTreeBtn',dlgId:'dlg-user',	 
		treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'部门',treeChkMode:'',treeChkType:'dept',
		viewer:userViewer,saveChkFldId:'deptId'  
	});
	
	
}) 
</script>

<form name="tawgznetform" method="post" action="netsave.do" onsubmit='return SubmitCheck()'>

<table width="500" class="formTable">
  <caption><bean:message key="netadd.title.formTitle" /></caption>
  <tr>
    <td class="label" width="100">
      <bean:message key="netadd.title.formSysType" />
    </td>
    <td width="400">
      <select size=1 name="systype" class="select" onChange="changelocation(document.forms[0].systype.options[document.forms[0].systype.selectedIndex].value)">
        <option value="0" selected><bean:message key="netadd.title.formSelectSys" /> </option>
        <%
        sysEnumeration = hashtable.keys();
        while(sysEnumeration.hasMoreElements()){
          sysType = (String[])sysEnumeration.nextElement();
        %>
          <option value="<%=sysType[1]%>"><%=sysType[0]%></option>
        <%
        }
        %>
      </select>
    </td>
  </tr>
  <tr>
    <td class="label">
      <bean:message key="netadd.title.formNetType" />
    </td>
    <td width="400">
      <select size="1" name="nettype" class="select" onChange="mychangelocation(document.forms[0].nettype.options[document.forms[0].nettype.selectedIndex].value)">
        <option value="-1" selected><bean:message key="netadd.title.formSelectNet" /></option>
      </select>
    </td>
  </tr>

  
  <tr>
    <td class="label">
      专业代码
    </td>
    <td width="400">
      <select size="1" name="mynettype" class="select">
        <option value="-1" selected>选择专业代码</option>
      </select>
    </td>
  </tr>
   
  
  <tr>
    <td class="label">
      <bean:message key="netadd.title.formNetName" />
    </td>
    <td>
      <input type="text" name="name" size="40" class="text">
    </td>
  </tr>
  <tr>
    <td class="label">
      <bean:message key="netadd.title.formSerialNo" />
    </td>
    <td>
      <input type="text" name="serialno" size="40" class="text">
    </td>
  </tr>
  <tr>
    <td class="label">
      <bean:message key="netadd.title.formRoomName" />
    </td>
    <td>
      <select size='1' name='roomid' class="select">
        <option  value='0'><bean:message key="netadd.title.formSelectRoom" /></option> 
          <% 
          if(roomHashtable!=null&&roomHashtable.size()>0){
	          roomEnumeration = roomHashtable.keys();
	          while(roomEnumeration.hasMoreElements()){
	            room = (String)roomEnumeration.nextElement();
	            if(room != null){
	          %>
	          <option  value="<%=room%>">
	             <%=(String)roomHashtable.get(room)%> 
	          </option>
	          <%
	            }
	          } 
          }
          %>
         </select>
    </td>
  </tr>

  <tr>
    <td class="label">
      <bean:message key="netadd.title.formDeptName" />
    </td>
    <td>
    
      
		<div id="user-list" class="viewer-list"></div>
		 <INPUT TYPE="hidden" name="deptid" id="deptid" value="">
		<input type="button" value="选择部门" id="userTreeBtn" class="btn"/>
	 
      </td>
  </tr>
  <tr>
    <td class="label">
      <bean:message key="netadd.title.formVendor" />
    </td>
    <td>
        <select size='1' name='vendorsel' class="select">
          <option  value=''><bean:message key="netadd.title.formSelectVendor" /></option>
          <%

          for(int i=0; i<vendorList.size(); i++){
            vendor = (String)vendorList.get(i);
          %>
          <option  value='<%=vendor%>'><%=vendor%></option>
          <%
          }
          %>
        </select>
      <input type="text" name="vendor" size="20" class="text">
    </td>
  </tr>
</table>
<br>
<input type="submit" value="<bean:message key="netadd.title.formSubmit" />" name="B1"  Class="submit">
<input type="button" value="<bean:message key="netadd.title.formBack" />" onclick="javascript:window.history.back();" class="button">

</form>

<%@ include file="/common/footer_eoms.jsp"%>

