<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="java.util.Hashtable"%>
<%@ page import ="java.util.Enumeration"%>
<%@ page import ="com.boco.eoms.workplan.util.TawwpStaticVariable"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>

<script language="JavaScript">
<!--
function SubmitCheck(){

  frmReg = document.tawwpyearaddform;

  if(frmReg.systype.value == '0'){
    alert("<bean:message key="yearaddnomodel.title.warnSelSysType" />");
    return false;
  }

  if(frmReg.nettypeid.value == '0'){
    alert("<bean:message key="yearaddnomodel.title.warnSelNetType" />");
    return false;
  }

  if(frmReg.planname.value=='')
  {
    alert( "<bean:message key="yearaddnomodel.title.warnSelPlanName" />" );
    frmReg.planname.focus();
    return false;
  }

  return true;
}


    function onNet()
    {
      if( window.tawwpyearaddform.nettypeid.value == "")
      {
       alert("<bean:message key="yearaddnomodel.title.warnSelNetType" />")
       return;
      }
      else
      {
        var _sHeight = 300;
        var _sWidth = 400;
        var sTop=(window.screen.availHeight-_sHeight)/2;
        var sLeft=(window.screen.availWidth-_sWidth)/2;
        var sFeatures="dialogHeight: "+_sHeight+"px; dialogWidth: "+_sWidth+"px; dialogTop: "+sTop+"; dialogLeft: "+sLeft+"; center: Yes; scroll:No;help: No; resizable: No; status: No;";

        window.showModalDialog('../tawwpyear/netindexbynettype.do?deptid='+ window.tawwpyearaddform.deptid.value+'&nettypeid='+ window.tawwpyearaddform.nettypeid.value,window,sFeatures);
      }
    }
//-->
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
  System.out.println("systype = "+sysType[1]);
  netList = (List)hashtable.get(sysType);
  for(int i=0; i<netList.size(); i++){
    netType = (String[])netList.get(i);
    System.out.println("nettype = "+netType[0]);
    System.out.println("nettype1 = "+netType[1]);
%>
subcat[<%=count++%>] = new Array("<%=netType[0]%>","<%=sysType[1]%>","<%=netType[1]%>");
<%
  }
}
%>

onecount=<%=count%>;

function changelocation(locationid){

  document.tawwpyearaddform.nettypeid.length = 0;
  //var locationid=locationid;
  //alert("id = "+locationid);
  //alert("count = "+onecount);
  //document.tawwpyearaddform.nettypeid
  document.tawwpyearaddform.nettypeid.options[0] = new Option('<bean:message key="yearaddnomodel.title.selNetType" />','0');
  for (var i=0;i < onecount; i++){
  //alert("subcat = "+subcat[i][1]);
    if (subcat[i][1] == locationid){
     // alert (locationid);
      document.tawwpyearaddform.nettypeid.options[document.tawwpyearaddform.nettypeid.length] = new Option(subcat[i][0], subcat[i][2]);
    }
  }
  //alert(document.tawwpyearaddform.nettypeid.options.length);
}

//-->
</script>
<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawwpyearaddform'});
});
</script>
<input type="hidden" name="path" id="path" value="/EOMS_J2EE">
<input type="hidden" name="sdomids" id="sdomids" value="">
<%
		
      TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request.
          getSession().getAttribute(
          "sessionform");

          String deptId=saveSessionBeanForm.getDeptid();
          String deptName=saveSessionBeanForm.getDeptname();
          String userId=saveSessionBeanForm.getUserid();
          String userName=saveSessionBeanForm.getUsername();
%>
<form name="tawwpyearaddform" method="post" action="../tawwpyear/yearsavenomodel.do" onsubmit='return SubmitCheck()'>

  <table class="formTable">
    <caption><bean:message key="yearaddnomodel.title.formTitle" /></caption>
    <tr>
      <td class="label">
        <bean:message key="yearaddnomodel.title.formSysType" />
      </td>
      <td width="400">
        <select size=1 name="systype" class="select" onChange="changelocation(this.value)">
          <option value="0" selected><bean:message key="yearaddnomodel.title.selSysType" /></option>
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
        <bean:message key="yearaddnomodel.title.formNetType" />
      </td>
      <td width="400">
        <select size="1" name="nettypeid" class="select">
          <option value="" selected><bean:message key="yearaddnomodel.title.selNetType" /></option>
        </select>
      </td>
    </tr>
      <tr>
      <td class="label">
        <bean:message key="yearaddnomodel.title.formType" />
      </td>
      <td width="400">
        <select size="1" name="typeIndex" class="select">
          <option value="0" >无线网</option>
          <option value="1" selected>数据网</option>
          <option value="2" >汇接局</option>
          <option value="3" >端局</option>
          <option value="4" >关口局</option>
          <option value="5" >HLR</option>
          <option value="6" >智能网</option>
          <option value="7" >其他</option>
        </select>
      </td>
    </tr>
    <tr>
      <td width="100" class="label">
        <bean:message key="yearaddnomodel.title.formPlanName" />
      </td>
      <td width="500">
        <input type="text" name="planname" size="30" class="text">
        <input type="hidden" name="planid" size="30" styleClass="clstext" readonly="true" >
      </td>
    </tr>
    <tr>
      <td width="100" class="label">
        <bean:message key="yearaddnomodel.title.formDeptName" />
      </td>
      <td width="500">
        <input type="text" name="deptname" size="30" class="text" readonly="true" value="<%=deptName%>">
        <input type="hidden" name="deptid" size="30" styleClass="clstext" readonly="true" value="<%=deptId%>">

      </td>
    </tr>

    <tr>
      <td width="100" class="label">
        <bean:message key="yearaddnomodel.title.formCruser" />
      </td>
      <td width="500">
        <input type="text" name="cruser" size="30" class="text" readonly="true" value="<%=userName%>">
        <input type="hidden" name="userid" size="30" styleClass="clstext" readonly="true" value="<%=userId%>">
      </td>
    </tr>

    <tr>
      <td width="100" class="label">
        <bean:message key="yearaddnomodel.title.formYearFlag" />
      </td>
      <td width="500">
        <select name="yearflag" class="select">
<%
SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
Date curtime = new java.util.Date();
int crrtYear=Integer.parseInt(formatter.format(curtime));
for(int i=crrtYear;i<(crrtYear+2);i++){
  %>
  <option value"<%=i%>"><%=i%></option>
  <%
  }
%>
        </select>
      </td>
    </tr>
    <tr class="label"">
      <td width="100" class="label">
        <bean:message key="yearaddnomodel.title.formContent" />
      </td>
      <td width="500">
     	
        <textarea name="content" class="textarea max" alt="vtext:'作业计划内容字数不能超过50个汉字',maxLength:100" ></textarea>
      </td>
    </tr>
    <tr>
      <td width="100" class="label">
        <bean:message key="yearaddnomodel.title.formRemark" />
      </td>
      <td width="500">
        <textarea name="remark" class="textarea max" alt="vtext:'备注字数不能超过50个汉字',maxLength:100"></textarea>
      </td>
    </tr>
  </table>
  <br>
  <input type="submit" value="<bean:message key="yearaddnomodel.title.btnSubmit" />" name="B1"  class="submit">
  <input type="button" value="<bean:message key="yearaddnomodel.title.btnBack" />" onclick="javascript:window.history.back();" class="button">
</form>
<%@ include file="/common/footer_eoms.jsp"%>