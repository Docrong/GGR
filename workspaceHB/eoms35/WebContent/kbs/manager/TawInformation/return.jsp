<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="java.util.*,java.text.SimpleDateFormat,com.boco.eoms.kbs.util.*"%>
<%@ page import="com.boco.eoms.common.util.*,com.boco.eoms.common.controller.*"%>
<link href="<%=request.getContextPath()%>/css/table_style.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="../inc/ubbcode.js"></script>
<script language="javascript">
function onSave(flag)
{
  if (trim(tawInformationForm.topic.value)=="")
  {
    alert("主题不能为空");
    return false;
  }
  else if (tawInformationForm.userName.value.length>1000)
  {
    alert("收件人不能为空")
    return false;
  }

  else if (tawInformationForm.body.value.length>1000)
  {
    alert("")
  }

  tawInformationForm.checkInfo.value=flag;
  tawInformationForm.attachName.value=IFrame1.tawFileUploadForm.fileValue.value;
  tawInformationForm.imgName.value=IFrame1.tawFileUploadForm.imgValue.value;
  //alert(tawInformationForm.attachName.value);
  tawInformationForm.submit();
  return true;
}
function trim(TempStr)
        {
         return TempStr = TempStr.replace(/(^\s*)|(\s*$)/g, "");
        }

function info_onclick() {
if (info.style.display=="")
	{
	info.style.display="none"

	document.tawInformationForm.important.disabled = false;
	document.tawInformationForm.userName.value="";
	document.tawInformationForm.userid.value="-1";
	}
else
	{
	info.style.display="";
	document.tawInformationForm.important.selectedIndex=0;
	document.tawInformationForm.important.disabled = true;

	}
}
function info_onclick1() {
	if(document.tawInformationForm.smsflag1.value=="1")
	{
	document.tawInformationForm.smsflag.value="1";
	}
	else
	{
	document.tawInformationForm.smsflag.value="-21";
	}
}
function onSelUser()
    {
       var _sHeight = 340;
       var _sWidth = 460;
       var sTop=(window.screen.availHeight-_sHeight)/2;
       var sLeft=(window.screen.availWidth-_sWidth)/2;
       var sFeatures="dialogHeight: "+_sHeight+"px; dialogWidth: "+_sWidth+"px; dialogTop: "+sTop+"; dialogLeft: "+sLeft+"; center: Yes; scroll:yes;help: No; resizable: yes; status: No;";
       window.showModalDialog('../TawInformation/seluser.do',window,sFeatures);
      //window.open('../TawInformation/seluser.do');
    }

</script>
<%
String parentId=String.valueOf(request.getParameter("parentId"));
String boardId=String.valueOf(request.getParameter("boardId"));
String infoType=String.valueOf(request.getParameter("infoType"));
SaveSessionBeanForm saveSessionBeanForm =
          (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
String userId=saveSessionBeanForm.getWrf_UserID();
String userName=saveSessionBeanForm.getWrf_UserName();
String deptName=saveSessionBeanForm.getWrf_DeptName();
Date currentDate = new Date();
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
String date = dateFormat.format(currentDate);
String attText="";
String attValue="";
String imgText="";
String imgValue="";

Object attachObj=request.getAttribute("attachName");
Object imgObj=request.getAttribute("imgName");
if (attachObj!=null)
{
  attValue=attachObj.toString();
  attText=StaticFunction.getShortName(attValue);
}
if (imgObj!=null)
{
  imgValue=imgObj.toString();
  imgText=StaticFunction.getShortName(imgValue);
}

%>
<html:form  action="/TawInformation/savemail" >
<html:hidden property="strutsAction"/>
<input type="hidden" name="userid" value="-1">

<!--<input type="hidden" name="boardId" value="<%=boardId%>">-->
<!--<input type="hidden" name="parentId" value="<%=parentId%>">-->
<c:choose>
      <c:when test="${requestScope['tawInformationForm'].strutsAction == 1}">
        <html:hidden property="parentId" value="<%=parentId%>" />
        <html:hidden property="boardId" value="<%=boardId%>" />
        <html:hidden property="infoType" value="<%=infoType%>" />
      </c:when>
      <c:otherwise>
        <html:hidden property="id"/>
        <html:hidden property="boardId"/>
        <html:hidden property="parentId"/>
        <html:hidden property="infoType"/>
</c:otherwise></c:choose>
<table bgcolor="#709fd5" align="center" width="90%" cellspacing="1" border="0" cellpadding="1" class="clsbkgd">
<tr>
  <td bgcolor="#d6e0ed" colspan="2" class="clsscd" width="100%" align="center">

回复邮件

  </td>
</tr>


<tr>
  <td width="15%" bgcolor="#e5edf8" class="clsthd"><bean:message key="TawInformation.topic"/></td>
  <td width="85%" bgcolor="#e5edf8" class="clsfth" >
    <html:text property="topic" size="50" maxlength="50" styleClass="clstext"/><font color="red">**</font>
  </td>
</tr>
<tr>
  <td width="15%" bgcolor="#e5edf8" class="clsthd"><bean:message key="TawInformation.comefrom"/></td>
  <td width="75%" bgcolor="#e5edf8" class="clsfth" ><%=deptName%></td>
</tr>
<tr>
  <td width="15%" bgcolor="#e5edf8" class="clsthd"><bean:message key="TawInformation.author"/></td>
  <td width="85%" bgcolor="#e5edf8" class="clsfth" ><%=userName%> &nbsp;&nbsp;<input type="checkbox" name="smsflag" value="1" > 发送短信</td>
</tr>
<tr id = info style="display:none">
  <td width="15%" bgcolor="#e5edf8" class="clsthd"><bean:message key="TawInformation.importance"/></td>
  <td width="85%" bgcolor="#e5edf8" class="clsfth" >
    <html:select property="important">
      <html:options collection="IMPORTANTS" property="value" labelProperty="label"/>
    </html:select>&nbsp;&nbsp;<input type="checkbox" name="flag" value="1" onclick='info_onclick()'> 个人
  </td>
</tr>

<tr>
  <td width="15%" bgcolor="#e5edf8" class="clsthd">接收人</td>
  <td width="85%" bgcolor="#e5edf8" class="clsfth" >
   <html:text name="tawInformationForm" property="userName"/>
  </td>
</tr>

<tr>
  <td width="15%" bgcolor="#e5edf8" align="right"><bean:message key="TawInformation.dateTime"/></td>
  <td width="85%" bgcolor="#e5edf8" align="left">
    <c:choose>
      <c:when test="${requestScope['tawInformationForm'].strutsAction == 1}">
        <%=date%>
      </c:when>
      <c:otherwise>
        <bean:write name="tawInformationForm" property="dateTime" />
      </c:otherwise>
    </c:choose>
  </td>
</tr>
<tr>
  <td width="15%" bgcolor="#e5edf8" align="right">内容</td>
  <td width="85%" bgcolor="#e5edf8" align="left"><html:textarea property="body" rows="15" cols="75" styleClass="clstext"/></td>
</tr>
<!--
<tr>
  <td colspan=2 bgcolor="#e5edf8" class="clsthd2"><%//pageContext.include("getubb.jsp");%></td>
</tr>
-->
<tr>
<html:hidden property="checkInfo"/>
<html:hidden property="attachName"/>
<html:hidden property="imgName"/>
<%
String operID=StaticVariable.INFORM_ADD_CHK[Integer.parseInt(infoType)-1]+"";
String operID1=StaticVariable.INFORM_ADD[Integer.parseInt(infoType)-1]+"";
String domainType=(StaticVariable.INFORM_DOMAIN_TYPE+Integer.parseInt(infoType))+"";
%>
<td align="right" colspan="2">
<INPUT  type=button value="发送"  name=button onclick="return onSave(0);">
<!--
<eoms:Validate userID="<%=userId%>" operID='<%=operID%>' domain="<%=boardId%>" domainType="<%=domainType%>" >
      <input type="button" Class="clsbtn2" value="<bean:message key="label.checkpublish"/>" onclick="return onSave(1);">
</eoms:Validate>
-->
<eoms:Validate userID="<%=userId%>" operID='<%=operID1%>' domain="<%=boardId%>" domainType="<%=domainType%>" >
     <!--
      <input type="button" Class="clsbtn2" value="<bean:message key="label.save"/>" onclick="return onSave(0);">
      -->

</eoms:Validate>
<!--
      <input type="button" Class="clsbtn2" value="<bean:message key="label.cancel"/>" onclick="history.back();">
  -->
</td>
</tr>
</table>
</html:form>
<!--<IFRAME ID=IFrame1 FRAMEBORDER=0 width="100%" SCROLLING=NO SRC="../kbs/manager/TawFileUpload/uploadfile.jsp?attValue=<%=attValue.trim()%>&attText=<%=attText.trim()%>"></IFRAME>-->
<IFRAME ID=IFrame1 FRAMEBORDER=0 width="100%" SCROLLING=NO SRC="file.do?attValue=<%=attValue.trim()%>&attText=<%=attText.trim()%>&imgValue=<%=imgValue.trim()%>&imgText=<%=imgText.trim()%>"></IFRAME>



