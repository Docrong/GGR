<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.Hashtable"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.Enumeration"%>
<%@ page import ="com.boco.eoms.workplan.util.TawwpStaticVariable"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpAddonsTableVO"%>

<script language="javascript">
function onForm()
{
  var _sHeight = 118;
  var _sWidth = 320;
  var sTop=(window.screen.availHeight-_sHeight)/2;
  var sLeft=(window.screen.availWidth-_sWidth)/2;
  var sFeatures="dialogHeight: "+_sHeight+"px; dialogWidth: "+_sWidth+"px; dialogTop: "+sTop+"; dialogLeft: "+sLeft+"; center: Yes; scroll:No;help: No; resizable: No; status: No;";
  window.showModalDialog("../manager/tawgzplanitem/formselect.jsp",window,sFeatures);
}

function SubmitCheck()
{
  frmReg = document.tawwpitemadd;
  if( !JustifyNull1(frmReg.titleItemName))
  {
    alert( "<bean:message key="itemadd.tawwpyear.warnPlanName" />" );
    frmReg.titleItemName.focus();
    return false;
  }

  if(document.forms[0].haveplan.value > 0)
  {
    if(confirm("<bean:message key="itemadd.tawwpyear.warnNewToOld" />"))
    {
      document.forms[0].addflag.value = "true";
    }
  }
  document.forms[0].B1.style.display="none";
  return true;
}
<!--
var onecount;
onecount=0;

subcat = new Array();
<%
int cont=0;
for(int i=1;i<13;i++){
cont++;
%>
subcat[<%=cont%>] = new Array("<bean:message key="itemadd.tawwpyear.warnNumber" /><%=i%><bean:message key="itemadd.tawwpyear.warnMonth" />","7","<%=i%>");
<%}
for(int i=1;i<7;i++){
cont++;
%>
subcat[<%=cont%>] = new Array("<bean:message key="itemadd.tawwpyear.warnNumber" /><%=i%><bean:message key="itemadd.tawwpyear.warnMonth" />","6","<%=i%>");
<%}for(int i=1;i<5;i++){
cont++;
%>
subcat[<%=cont%>] = new Array("<bean:message key="itemadd.tawwpyear.warnNumber" /><%=i%><bean:message key="itemadd.tawwpyear.warnMonth" />","9","<%=i%>");
<%}for(int i=1;i<4;i++){
cont++;
%>
subcat[<%=cont%>] = new Array("<bean:message key="itemadd.tawwpyear.warnNumber" /><%=i%><bean:message key="itemadd.tawwpyear.warnMonth" />","5","<%=i%>");
<%}
for(int i=1;i<3;i++){
cont++;
%>
subcat[<%=cont%>] = new Array("<bean:message key="itemadd.tawwpyear.warnNumber" /><%=i%><bean:message key="itemadd.tawwpyear.warnMonth" />","8","<%=i%>");
<%}
%>

onecount=<%=cont%>;

function changecycle(locationid){
if(locationid<5){
document.tawwpitemadd.monthflag.style.display="none";
}else{
document.tawwpitemadd.monthflag.style.display="block";
}
  document.tawwpitemadd.monthflag.length = 0;
  var locationid=locationid;
  var i;

if(locationid==2){
    eoms.$('executeDay').show();
}else{
    eoms.$('executeDay').hide();
}

  for (i=1;i <= onecount; i++){
    if (subcat[i][1] == locationid){
      document.tawwpitemadd.monthflag.options[document.tawwpitemadd.monthflag.length] = new Option(subcat[i][0], subcat[i][2]);
    }
  }
}

//-->
</script>

<%
ArrayList addonsList=new ArrayList();
if(request.getAttribute("addonslist")!=null){
	addonsList=(ArrayList)request.getAttribute("addonslist");
}

Hashtable hashtable = TawwpStaticVariable.ADDONS_INF;
Enumeration enumeration = hashtable.keys();
TawwpAddonsTableVO tawwpAddonsTableVO = null;
String id = null;
%>

<form name="tawwpitemadd" method="post" action="../tawwpyear/itemsave.do?yearplanid=<%=request.getParameter("yearplanid")%>" >

  <table class="formTable">
    <caption><bean:message key="itemadd.tawwpyear.formTitle" /></caption>
    <tr>
      <td class="label">
        作业项目
      </td>
      <td width="400" colspan="2">
        <input type="text" name="name" class="text">
      </td>
    </tr>
        
    
    <tr>
      <td class="label">
        业务类型
      </td>
      <td width="400" colspan="2">
        <input type="text" name="botype" size="40" class="text">
      </td>
    </tr>
    <tr>
      <td class="label">
        执行单位级别
      </td>
      <td width="400" colspan="2">
        <input type="text" name="executedeptlevel" size="40" class="text">
      </td>
    </tr>
    <tr>
      <td class="label">
        适用说明
      </td>
      <td width="400" colspan="2">
        <input type="text" name="appdesc" size="40" class="text">
      </td>
    </tr>
    
    
    
    <tr>
      <td class="label">
        执行周期
      </td>
      <td width="50">
       <select class="select" size='1' name='cycle' value='1' onChange="changecycle(document.tawwpitemadd.cycle.options[document.tawwpitemadd.cycle.selectedIndex].value)">
         <option  value='1'>天</option>
         <option  value='2'>周</option>
         <option  value='3'>半月</option>
         <option  value='4'>月</option>
         <option  value='8'>两月</option>
         <option  value='5'>季度</option>
         <option  value='9'>四月</option>
         <option  value='6'>半年</option>
         <option  value='7'>年</option>
         <option  value='0'>其他</option>
        </select>
       	<select class="select" size='1' name='executeDay' id='executeDay' style="display:none">
         <option  value=''>请选择</option>
         <option  value='1'>周一</option>
         <option  value='2'>周二</option>
         <option  value='3'>周三</option>
         <option  value='4'>周四</option>
         <option  value='5'>周五</option>
         <option  value='6'>周六</option>
         <option  value='7'>周日</option>
        </select>
        </td>
        <td width="350">
        <select size="1" name="monthflag" class="text" style="display:none">
        </select>
      </td>
    </tr>
    <tr>
      <td class="label">
        <bean:message key="itemadd.tawwpyear.formFormat" />
      </td>
      <td width="400" colspan="2">
        <input type="text" name="format" class="text">
      </td>
    </tr>
    <tr>
      <td class="label">
         <bean:message key="itemadd.tawwpyear.formHoliday" />
      </td>
      <td width="400" colspan="2">
        <input type=radio name='isHoliday' value='0' checked >是&nbsp;&nbsp;<input type=radio name='isHoliday' value='1'>否
      </td>
    </tr>
    <tr>
      <td class="label">
         <bean:message key="itemadd.tawwpyear.formWeekend" />
      </td>
      <td width="400" colspan="2">
        <input type=radio name='isWeekend' value='0' checked >是&nbsp;&nbsp;<input type=radio name='isWeekend' value='1'>否
      </td>
    </tr>
    <tr>
      <td class="label">
        记录模版
      </td>
      <td width="400" colspan="2">
       <select name='formid' value='0' class="select">
         <option value='0'><bean:message key="itemadd.tawwpyear.selAddons" /></option>
          <%
         for(int j=0;j<addonsList.size();j++){
           tawwpAddonsTableVO=(TawwpAddonsTableVO)addonsList.get(j);
         %>
         <option value='<%=tawwpAddonsTableVO.getId()%>'><%=tawwpAddonsTableVO.getName()%></option>
         <%
         }
         %>
        </select>
      </td>
    </tr>
        
    <tr>
      <td class="label">
         是否必须上传附件
      </td>
      <td width="400" colspan="2">
        <input type=radio name='fileFlag' value='1' >是&nbsp;&nbsp;<input type=radio name='fileFlag' value='0' checked >否
      </td>
    </tr>
 
     <tr>
       <td class="label">
       	执行帮助
      </td>
      <td colspan=2 >
        <textarea name="remark" class="textarea max"></textarea>
      </td>
    </tr>
  </table>
  <br>
  <input type="submit" value="<bean:message key="itemadd.tawwpyear.btnSubmit" />" name="B1"  Class="submit">
  <input type="button" value="<bean:message key="itemadd.tawwpyear.btnBack" />" onclick="javascript:window.history.back();" class="button">
</form>
<%@ include file="/common/footer_eoms.jsp"%>


