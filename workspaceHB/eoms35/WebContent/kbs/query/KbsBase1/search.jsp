<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="java.util.*,java.text.SimpleDateFormat,com.boco.eoms.kbs.util.*"%>
<%@ page import="com.boco.eoms.common.util.*,com.boco.eoms.common.controller.*"%>
<%String path = request.getContextPath();
%>
<script language="javascript">

function transmitRadio(flag){

	var frm = document.forms[0];;
	if (flag == 1) {
          //申告
            document.all.flag.value=1;
            document.all.compliant.style.display="block";
            document.all.fault.style.display="none";
            document.all.compliantdescription.style.display="block";
            document.all.compliantcause.style.display="block";
            document.all.faultdescription.style.display="none";
            document.all.faultcause.style.display="none";
            document.all.faultType.value="";
            document.all.specialtyType.value="";
			document.all.onefault.style.display="block";
			document.all.towfault.style.display="block";
	}
	else if(flag==0){
          //故障
            document.all.flag.value=0;
            document.all.fault.style.display="block";
            document.all.compliant.style.display="none";
            document.all.compliantdescription.style.display="none";
            document.all.compliantcause.style.display="none";
            document.all.faultdescription.style.display="block";
            document.all.faultcause.style.display="block";
            document.all.applyType.value="";
            document.all.custType.value="";
			document.all.onefault.style.display="block";
			document.all.towfault.style.display="block";
	}else if(flag==2){
	document.all.flag.value=2;
            document.all.fault.style.display="none";
            document.all.compliant.style.display="none";
            document.all.compliantdescription.style.display="none";
            document.all.compliantcause.style.display="none";
            document.all.faultdescription.style.display="none";
            document.all.faultcause.style.display="none";
			document.all.onefault.style.display="none";
			document.all.towfault.style.display="none";
            document.all.applyType.value="";
            document.all.custType.value="";
	
	}

	return true;
}
function dealTimeLimit(){
}
function onQuery()
{
  kbsBaseForm.submit();
}
</script>
<link title="style" href="<%=path%>/css/wsstyle.css" type="text/css" rel="stylesheet">
<link rel="StyleSheet" href="<%=path%>/css/tree.css" type="text/css">
<link rel="stylesheet" href="<%=path%>/css/table_style.css" type="text/css">
<?import namespace=BOCOimplementation="<%=path%>/css/button/genericButton.htc"/>
<script type="text/javascript" src="<%=path%>/css/onlytree.js"></script>
<script language="javascript" src="<%=path%>/css/table_calendar.js"></script>
<script type="text/javascript" src="<%=path%>/css/finallytree.js"></script>
<eoms:DictType typeName="ApplyType"/>
<eoms:DictType typeName="CompliantType"/>
<body >
<html:form  method="post" action="/KbsBase/searchdo.do">

<br>
<table border="0" width="70%" cellspacing="1" cellpadding="1" align=center>
  <tr>
    <td class="table_title" width="100%">
      查询
    </td>
  </tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
    <tr class="tr_show">
    <td width="30%" height="25" class="clsfth">&nbsp;案例编码
  </td>
  <td width="70%" height="25" colspan="3">
        <html:text  styleClass="clstext" property="code" size="30"/>
  </td>
  </tr>

  <tr class="tr_show">
    <td width="30%" height="25" class="clsfth">&nbsp;案例主题
  </td>
  <td width="70%" height="25" colspan="3">
        <html:text  styleClass="clstext" property="name" size="30"/>
  </td>
  </tr>
    <tr class="tr_show">
    <td width="30%" height="25" class="clsfth">&nbsp;关键字
  </td>
  <td width="70%" height="25" colspan="3">
        <html:text  styleClass="clstext" property="keyword" size="30"/>
  </td>
  </tr>
  <!--<tr class="tr_show">
    <td width="30%" height="25" class="clsfth">&nbsp;工单状态
  </td>
  <td width="70%" height="25" colspan="3">
        <html:select property="status" style="width: 3.6cm;"  >
		<OPTION VALUE="-1">全部</OPTION>
		<OPTION VALUE="0">草稿</OPTION>
		<OPTION VALUE="1">待审核</OPTION>
		<OPTION VALUE="2">通过并启用</OPTION>
		<OPTION VALUE="4">不采用</OPTION>
<OPTION VALUE="5">停用</OPTION>
 </html:select>
  </td>
  </tr>-->
  <tr class="tr_show">
  <td width="25%" class="clsfth">&nbsp;工单类型</td>
   <td colspan = 3>
      <html:radio  property="flag" onclick="transmitRadio(0);" value="0" styleClass="clstext"/>故障&nbsp;&nbsp;&nbsp;&nbsp;
      <html:radio  property="flag" onclick="transmitRadio(1);"value="1" styleClass="clstext"/>申告
	  &nbsp;&nbsp;&nbsp;&nbsp;
	  <html:radio  property="flag" onclick="transmitRadio(2);"value="2" styleClass="clstext"/>全部
   </td>
</tr>
<tr class="tr_show" id="fault" style="display: block;">
    <td class= "clsfth">&nbsp;<bean:message key="Faultsheet.mainSpecialty"/><!--专业类型--></td>
    <td >
      <eoms:SelectDictRel jsDealTimeLimit="true" name="specialtyType" typeName="Specialty" formName="kbsBaseForm" relProperty="faultType" relTypeName="FaultType" value="15" />
   </td>
   <td class= "clsfth">&nbsp;<bean:message key="Faultsheet.mainFaultTypeName"/><!--故障类型--></td>
   <td >
	<html:select property="faultType" style="width: 3.6cm;">
         <html:options collection="faultType" property="value" labelProperty="label"/>
       </html:select>
  </td>
</tr>
<tr class="tr_show" id="compliant" style="display: none;">
		<td class= "clsfth">&nbsp;<bean:message key="Applysheet.mainApplyType"/></td>
		<td>
		<html:select property="applyType" style="width: 3.6cm;"  >
<OPTION VALUE="*15">其他网络投诉</OPTION>
<OPTION VALUE="*14">他数据业务类投诉</OPTION>

<OPTION VALUE="*13">CMNET业务类投诉</OPTION>
<OPTION VALUE="*12">GPRS业务投诉类</OPTION>
<OPTION VALUE="*11">话音增值业务类投诉</OPTION>
<OPTION VALUE="*10">交叉覆盖类投诉</OPTION>
<OPTION VALUE="*9">HLR故障类投诉</OPTION>
<OPTION VALUE="*8">智能网平台类投诉</OPTION>
<OPTION VALUE="*7">彩信业务类投诉</OPTION>
<OPTION VALUE="*6">短信业务类投诉</OPTION>
<OPTION VALUE="*5">IP电话类投诉</OPTION>

<OPTION VALUE="*4">互联互通类投诉</OPTION>
<OPTION VALUE="*3">漫游类</OPTION>
<OPTION VALUE="*2">通信质量</OPTION>
<OPTION VALUE="*1">网络覆盖</OPTION>
 </html:select>
		</td>
                <td class= "clsfth">&nbsp;<bean:message key="Applysheet.mainCustType"/></td>
		<td>
		<html:select property="custType" style="width: 3.6cm;">
                <html:options collection="CompliantType" property="value" labelProperty="label"/>
                </html:select>
		</td>
</tr>

  <tr class="tr_show" id="onefault">
     <td width="25%" id="faultdescription" style="display: block;" height="25" class="clsfth">
       &nbsp;故障描述
  </td>
    <td width="25%" id="compliantdescription" style="display: none;" height="25" class="clsfth">&nbsp;投诉描述
  </td>
  <td width="75%" height="25" colspan="3">
        <html:textarea   rows="6" style="width:5.8cm;" property="description"  styleClass="clstext"  value="" />
  </td>
  </tr>
  <tr class="tr_show" id="towfault" >
    <td width="25%" id="faultcause" style="display: block;" height="25" class="clsfth">
      &nbsp;故障原因分析
   </td>
    <td width="25%" id="compliantcause" style="display: none;" height="25" class="clsfth">&nbsp;投诉原因分析
  </td>
  <td width="75%" height="25" colspan="3">
        <html:textarea  rows="6" style="width:5.8cm;"  styleClass="clstext" property="cause" value=""/>
  </td>
  </tr>

  <tr class="tr_show">
    <td width="30%" height="25" class="clsfth">&nbsp;处理过程
   </td>
    <td width="70%" height="25" colspan="3">
      <html:textarea property="deal" rows="6" style="width:5.8cm;" styleClass="clstext"/>
     </td>
  </tr>
</table>
<table border="0" width="70%" cellspacing="1" cellpadding="1" align=center>
  <tr>
    <td width="100%" align="right" height="32">
      <input type="button" Class="clsbtn2" value="<bean:message key="label.ok"/>" onclick="return onQuery();">
      <input type="button" Class="clsbtn2" value="<bean:message key="label.cancel"/>" onclick="history.back();">    </td>
  </tr>
</table>
</html:form>
</body>
