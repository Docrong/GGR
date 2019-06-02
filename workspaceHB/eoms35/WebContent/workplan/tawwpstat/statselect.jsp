<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="java.util.Hashtable"%>
<%@ page import ="java.util.Enumeration"%>
<%@ page import ="com.boco.eoms.workplan.util.TawwpStaticVariable"%>
<%@ page import ="com.boco.eoms.workplan.util.TawwpUtil,com.boco.eoms.commons.system.session.form.TawSystemSessionForm"%>

<%
//  String yearFlag = TawwpUtil.getCurrentDateTime("yyyy");
//  String monthFlag = "08";
// dayCount = TawwpUtil.getDayCountOfMonth(yearFlag, monthFlag); 
  TawSystemSessionForm tawSystemSessionForm = (TawSystemSessionForm) request
			.getSession().getAttribute("sessionform");
  String deptId = tawSystemSessionForm.getDeptid();
  
  int dayCount = 31;
  String tempStr = "";
  String year =(String)request.getAttribute("year");
  String _mm="";
  String month = (String)request.getAttribute("month");
%>
<script language="javascript">
<!--begin by denden(贵州本地) 增加对日期的选择-->
function showuser(){
    document.getElementById("userselect").style.display = "";
    document.getElementById("deptselect").style.display = "none";
    window.form1.deptName.value =""
    window.form1.deptid.value =""
    window.form1.roomid.value =""
    document.getElementById("roomselect").style.display = "none";
    window.form1.roomName.value =""
    document.getElementById("sysselect").style.display = "none";
    document.getElementById("netselect").style.display = "none";
    document.getElementById("netbuttonselect").style.display = "none";
    document.getElementById("netnameselect").style.display = "none";
    
}

function showdept(){
    document.getElementById("deptselect").style.display = "";
    document.getElementById("roomselect").style.display = "none";
    window.form1.roomName.value =""
    window.form1.roomid.value =""
    document.getElementById("userselect").style.display = "none";
    window.form1.userid.value =""
    window.form1.userName.value =""
    
    document.getElementById("sysselect").style.display = "none";
    document.getElementById("netselect").style.display = "none";
    document.getElementById("netbuttonselect").style.display = "none";
    document.getElementById("netnameselect").style.display = "none";
   }
function showroom(){
    document.getElementById("roomselect").style.display = "";
    document.getElementById("deptselect").style.display = "none";
    window.form1.deptName.value =""
    window.form1.deptid.value =""
    document.getElementById("userselect").style.display = "none";
    window.form1.userName.value =""
    window.form1.userid.value =""
    
    document.getElementById("sysselect").style.display = "none";
    document.getElementById("netselect").style.display = "none";
    document.getElementById("netbuttonselect").style.display = "none";
    document.getElementById("netnameselect").style.display = "none";
   }
function shownet(){
	document.getElementById("sysselect").style.display = "";
    document.getElementById("netselect").style.display = "";
    document.getElementById("netbuttonselect").style.display = "";
    document.getElementById("netnameselect").style.display = "";
    
    document.getElementById("deptselect").style.display = "none";
    window.form1.deptName.value =""
    window.form1.deptid.value =""
    document.getElementById("roomselect").style.display = "none";
    window.form1.roomName.value =""
    window.form1.roomid.value =""
    document.getElementById("userselect").style.display = "none";
    window.form1.userName.value =""
    window.form1.userid.value =""
   }

function SubmitCheck(){
  if(window.form1.yearflag.value == "0"){
    alert("请选择查询年份"); 
    return false;
  }else{
    frmReg = document.tawwpquerymonthplan;
    return true;
  }
}

function onNet()
{
  if( window.form1.nettype.value == "0")
  {
    alert("请选择网元类型")
    return;
  }
  else
  {
    var _sHeight = 350;
    var _sWidth = 400;
    var sTop=(window.screen.availHeight-_sHeight)/2;
    var sLeft=(window.screen.availWidth-_sWidth)/2;
    var sFeatures="dialogHeight: "+_sHeight+"px; dialogWidth: "+_sWidth+"px; dialogTop: "+sTop+"; dialogLeft: "+sLeft+"; center: Yes; scroll:No;help: No; resizable: No; status: No;";
    window.showModalDialog('../tawwpstat/netselect.do?refresh=<%=TawwpUtil.getCurrentDateTime("yyyyMMddhhssmm")%>&deptid='+ window.form1.deptid.value+'&nettypeid='+ window.form1.nettype.value,window,sFeatures);
  }
}

//弹出选择部门窗口
function selectTree(){
  dWinOrnaments = "status:no;scroll:no;resizable:yes;dialogHeight:450px;dialogWidth:480px;";
  dWin = showModalDialog('<%=request.getContextPath()%>/css/listbox_zyjh/listbox_depttoinput.jsp', window, dWinOrnaments);
}


<!--
var onecount;
onecount=0;

subcat = new Array();
<%
Hashtable hashtable = TawwpStaticVariable.SYS_NET_INF;
Enumeration sysEnumeration = null;
List netList = null;

String[] sysType = null;
String[] netType = null;
String netID = "";
String netName = "";

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
%>

onecount=<%=count%>;

function changelocation(locationid){
  
  document.form1.nettype.length = 0;
  var locationid=locationid;
  var i;

  document.form1.nettype.options[0] = new Option('<bean:message key="gzquerymonth.title.selNetType" />','0');

  for (i=0;i < onecount; i++){
    if (subcat[i][1] == locationid){
      document.form1.nettype.options[document.form1.nettype.length] = new Option(subcat[i][0], subcat[i][2]);
    }
  }
}
Ext.onReady(function(){
	var	userTreeAction='${app}/xtree.do?method=userFromDept';
			new xbox({
				btnId:'userName',dlgId:'dlg-audit',dlgTitle:'请选择人员',
				treeDataUrl:userTreeAction,treeRootId:'<%=deptId%>',treeRootText:'所有人员',treeChkMode:'',treeChkType:'user',
				showChkFldId:'userName',saveChkFldId:'userid'
			}); 
    var	deptTreeAction='${app}/xtree.do?method=dept';
			new xbox({
				btnId:'deptName',dlgId:'dlg-audit',dlgTitle:'请选择部门',
				treeDataUrl:deptTreeAction,treeRootId:'<%=deptId%>',treeRootText:'部门',treeChkMode:'single',treeChkType:'dept',
				showChkFldId:'deptName',saveChkFldId:'deptid'
			}); 
	var	roomTreeAction='${app}/xtree.do?method=getCptroomTree';
			new xbox({
				btnId:'roomName',dlgId:'dlg-audit',dlgTitle:'请选择机房',
				treeDataUrl:roomTreeAction,treeRootId:'-1',treeRootText:'机房',treeChkMode:'',treeChkType:'cptroom',
				showChkFldId:'roomName',saveChkFldId:'roomid'
			});
})

//-->

</script>

<form name="form1" method="post" action="statquery.do" onsubmit='return SubmitCheck()'>


  <table class="formTable" width="600">
    <caption><bean:message key="gzquerymonth.title.formTitle" /></caption>
    <!--月度统计,屏蔽月度查询的一些按钮如：系统类型，网元类型，相关网元等三个选择项        -->
 	 

    <tr>
      <td align="center" style="{background-color:#EDF5FD;}"  rowSpan="2" >
         选择统计类型
      </td>
         
      
   	 <td  colspan="6">
            <input type="radio" name="type" value="userType" onclick="javascript:showuser();">人员
       		<input type="text" name="userName" value="" id="userselect" style="display:none">
       		<input type="hidden" name="userid" value=""/>
            <input type="radio" name="type" value="deptType" onclick="javascript:showdept();"  >部门
    		<input type="text" name="deptName" value="" id="deptselect" style="display:none">
       		<input type="hidden" name="deptid" value=""/>
    		<input type="radio" name="type" value="roomType" onclick="javascript:showroom();"  >机房
    		<input type="text" name="roomName" value="" id="roomselect" style="display:none">
       		<input type="hidden" name="roomid" value=""/>
        </td>
      
    
        
    </tr>
    <tr>
       <td  colspan="6">
        <input type="radio" name="type" value="nettyperadio" onclick="javascript:shownet();"  >网元 &nbsp;&nbsp;&nbsp;
        <select id="sysselect" style="display:none" size=1 name="systype" class="select" onChange="changelocation(document.form1.systype.options[document.form1.systype.selectedIndex].value)">
          <option value="0" selected><bean:message key="querymonthplan.title.formSelectSys"/></option>
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
        <select id="netselect" style="display:none" size="1" name="nettype" class="select">
          <option value="0" selected><bean:message key="querymonthplan.title.formSelectNetType"/></option>
        </select>
        <input type="text" name="netnamelist" size="30" readonly="true" value="" class="text" id="netnameselect" style="display:none">
        <input type="hidden" name="netlist" size="30" styleClass="clstext" readonly="true" value="">
        <input type="button" value="<bean:message key="querymonthplan.title.formSelectNet"/>" name="B1" class="button" Onclick="javascript:onNet();" id="netbuttonselect" style="display:none">
     
      </td>
    </tr>

   

    <tr>
      <td  style="{background-color:#EDF5FD;}" align="center" >
       <bean:message key="gzquerymonth.title.formYear" />
      </td>
      <td   >
        <select name="yearflag" class="select">
          <option value="0"><bean:message key="gzquerymonth.title.formSelect" /> </option>
          <%for(int i=2006;i<=2012;i++){
           if(year.equals(i+"")){%>
          <option value="<%=i%>" selected ><%=i%></option>
          <%}else{%>
          <option value="<%=i%>"><%=i%></option>
          <%}}%>
        </select>
      </td>

      <td   style="{background-color:#EDF5FD;}">
        <bean:message key="gzquerymonth.title.formMonth" /> 
      </td>
      <td  >
        <select name="monthflag" class="select">
          <option value="0"><bean:message key="gzquerymonth.title.formSelect" /> </option>
           <%for(int i=1;i<=12;i++){
            if(i<10){
            _mm="0"+i;
            }
           	if(month.equals(i+"")){%>
          <option value="<%=_mm%>" selected > <%=_mm%></option>
          <%}else{%>
          <option value=" <%=_mm%>"> <%=_mm%></option>
          <%}}%>
        </select>
      </td>
    
    </tr>
     

    <tr class="label">
      <td colspan="5">
        <input type="submit" value="<bean:message key='queryyearplan.title.formSubmit' />" name="B1" class="submit">
      </td>
    </tr>
  </table>
</form>

<%@ include file="/common/footer_eoms.jsp"%>