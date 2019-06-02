<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="com.boco.eoms.common.util.*,java.util.*,com.boco.eoms.common.controller.*"%>
<%@ page import="com.boco.eoms.jbzl.bo.TawValidatePrivBO"%>
<script language="javascript">
function onAdd()
{
  document.form1.action="add.do";
  document.form1.submit();
}
function onBoardAdmin()
{
  document.form1.action="../TawBoard/list.do";
  document.form1.submit();
}
function onCheckPass(listNum)
{
  var str=save_sel_id(listNum);
  if (str!="")
  {
    document.form1.id.value=str;
    document.form1.action="ChkPass.do";
    document.form1.submit();
  }
}
function onCheckModi(listNum)
{
  var str=save_sel_id(listNum);
  if (str!="")
  {
    document.form1.id.value=str;
    document.form1.action="ChkModi.do";
    document.form1.submit();
  }
}

function onCheckDeny(listNum)
{
  var str=save_sel_id(listNum);
  if (str!="")
  {
    document.form1.id.value=str;
    document.form1.action="ChkDeny.do";
    document.form1.submit();
  }
}
function onCheck()
{ document.form1.audit.value="0";
  document.form1.action="unlist.do";
  document.form1.submit();
}

function onTryAgain(listNum)
{ var str=save_sel_id(listNum);
  if (str!="")
  {
    document.form1.id.value=str;
    document.form1.action="TryAgain.do";
    document.form1.submit();
  }
}


function onModi()
{ document.form1.audit.value="2";
  document.form1.action="modilist.do";
  document.form1.submit();
}
function onDeny()
{ document.form1.audit.value="3";
  document.form1.action="denylist.do";
  document.form1.submit();
}
function onMyfile(listNo)
{
  var str=save_sel_id(listNo);
  if (str!="")
  {
    if (confirm("你确认要将选择的内容放入 \"我的关注\" 吗 ？")==true)
    {
      document.form1.infoid.value=str;
      document.form1.action="savefile.do";
      document.form1.submit();
    }
  }
  else
  {
    alert("请选择要将选择的内容放入 \"我的关注\" 的信息");
    return false;
  }
  return true;
}
function onRemove(listNum)
{
  var str=save_sel_id(listNum);
  if (str!="")
  {
    if (confirm("你确认要删除选定信息吗？")==true)
    {
      document.form1.id.value=str;
      document.form1.action="trash.do";
      document.form1.submit();
    }
  }
  else
  {
    alert("请选择要删除的信息");
    return false;
  }
  return true;
}
function onClassifySave(listNum,boardId)
{
  var str=save_sel_id(listNum);
  if (document.tawInformationForm.boardId.value=="0")
  {
    alert("你没有权限操作带*号的专题，请重新选择！");
    return false;
  }
  if (str!="")
  {
    document.tawInformationForm.boardId1.value=document.tawInformationForm.boardId.value;
    document.tawInformationForm.boardId.value=boardId;
    document.tawInformationForm.id.value=str;
    document.tawInformationForm.submit();
  }
}
function onQuery()
{

    document.tawInformationForm.action="query.do";
    document.tawInformationForm.submit();
}
function onClassify(listNum,boardId)
{

  var str=save_sel_id(listNum);
  if (str!="")
  {
    //alert(self.screenLeft + (screen.width-self.screenLeft)/2-200);
    //alert(self.screenTop + (screen.height-self.screenTop)/2-75);
    if (div1.innerHTML=="")
    {
      document.frames["hiddenframe"].location.replace("preclass.do?listNum=" + listNum + "&boardId=" + boardId);
    }
    document.all.div1.style.left=200;
    document.all.div1.style.top=200;
    document.all.div1.style.width=400;
    document.all.div1.style.height=150;
    document.all.div1.style.visibility="visible";
    if (pageInDiv())
    {
      document.form1.page.style.visibility="hidden";
    }
  }
  else
  {
    alert("请选择要归类的信息");
    return false;
  }
  return true;
}
function pageInDiv()
{
  var result=true;
  var pageLeft = document.form1.page.offsetLeft;
  var pageTop = document.all.tp1.offsetTop;
  var pageRight = pageLeft + form1.page.offsetWidth;
  var pageBottom = pageTop + form1.page.offsetHeight;
  var divLeft = document.all.div1.offsetLeft;
  var divTop = document.all.div1.offsetTop;
  var divRight = divLeft + document.all.div1.offsetWidth;
  var divBottom = divTop + document.all.div1.offsetHeight;

  if (pageLeft>divRight || pageRight<divLeft || pageTop>divBottom || pageBottom<divTop)
    result = false;
  else
    result=true;
  return result;
}

function onClassifyCancel()
{
  document.all.div1.style.visibility="hidden";
  document.form1.page.style.visibility="visible";
}
function save_sel_id(listNum){
  var parent_id="";
  var sel_idStr="";
  var i = 0;

  if (document.form1.chk_sel_id!=undefined) {
    if (document.form1.chk_sel_id.length==undefined){
      if (document.form1.chk_sel_id.checked){
        sel_idStr=document.form1.chk_sel_id.value;
      }
    }
    else {
      i=0;
      while (parent_id=="" && i<form1.chk_sel_id.length){
        if (document.form1.chk_sel_id[i].checked){
          parent_id=document.form1.chk_sel_pid[i].value;
        }
        i+=1;
      }
      for(i=0;i<form1.chk_sel_id.length;i++){
        if (document.form1.chk_sel_id[i].checked){
          if (document.form1.chk_sel_pid[i].value!=parent_id){
            alert("不能对不同级别的同时操作");
            return "";
          }
          else{
            if (sel_idStr==""){
              sel_idStr=document.form1.chk_sel_id[i].value;
            }
            else{
              sel_idStr=sel_idStr + "," + document.form1.chk_sel_id[i].value;
            }
            parent_id=document.form1.chk_sel_pid[i].value;
          }
        }
      }
    }
  }
  return sel_idStr;

}
function openChild(listNo,boardId){
  var targetImg =eval("document.all.img" + listNo);
  var targetTr =eval("document.all.tr" + listNo);
  var targetDiv = eval("document.all.divtr" + listNo);
  var url = "";
  if (targetTr.style.display!='block'){
    targetTr.style.display="block";
    targetImg.src="../images/nofollow.gif";
    if(targetDiv!=null){
      if (targetDiv.innerHTML==""){
        url="listReply.do?boardId=" + boardId;
        url = url + "&listNo=" + listNo;
        document.frames["hiddenframe"].location.replace(url);
      }
    }
  }
  else{
    targetTr.style.display="none";
    targetImg.src="../images/plus.gif";
  }
}
var mousedown=false;
var x=0,y=0;
function mouseDown()
{
  mousedown=true;
  x=window.event.clientX - div1.style.pixelLeft;
  y=window.event.clientY - div1.style.pixelTop;
}

function movetree(){

	if (mousedown==true)
	{
	  div1.style.left=window.event.clientX - x;
	  div1.style.top=window.event.clientY - y;
          if (pageInDiv())
          {
            document.form1.page.style.visibility="hidden";
          }
          else
          {
            document.form1.page.style.visibility="visible";
          }
	  window.status=div1.style.left + "," + div1.style.top;
	}
}
</script>
<%

String boardId=String.valueOf(StaticMethod.null2String(request.getParameter("boardId")));
if(boardId==null){
    boardId=(String)request.getAttribute("boardId");
}
String boardName=String.valueOf(request.getAttribute("boardName"));
String audit = StaticMethod.null2String(request.getParameter("audit"));
String flag1 =StaticMethod.nullObject2String(request.getAttribute("flag1"),"");
String flag2 =StaticMethod.nullObject2String(request.getAttribute("flag2"),"");
String flag3 =StaticMethod.nullObject2String(request.getAttribute("flag3"),"");
String flag4 =StaticMethod.nullObject2String(request.getAttribute("flag4"),"");
if(audit==null||audit.equals(""))
  audit="2";
if(flag1!=null&&flag1.equals("flag1"))
  audit="1";
if(flag2!=null&&flag2.equals("flag2"))
  audit="2";
if(flag3!=null&&flag3.equals("flag3"))
  audit="4";
if(flag4!=null&&flag4.equals("flag4"))
  audit="3";

%>
<form name="form1" method="post" >

<table cellpadding="0" cellspacing="0" border="0" width="100%" >
  <tr>
    <td class="table_title" width="100%" valign="middle">
       <%
         if (audit.equals("2"))
         { boardName="当前主题:"+boardName;
           out.println(boardName);
         }
         else
         {
           out.println(boardName);
         }
       %>

    </td>
     <tr>
    <td align="right" ><bean:write name="pagerHeader" scope="request" filter="false"/></td>
     </tr>
  </tr>
</table>
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
      <tr class="td_label">
        <td width="30%">资料名称</td>
        <td width="20%">发布人</td>
        <td width="30%">发布部门</td>
        <td width="20%">发布时间</td>
     </tr>
<%
int listNum=0;
int divFlag=0;
%>
<logic:iterate id="tawInformation" name="TAWINFORMATIONS" type="com.boco.eoms.kbs.model.TawInformation">
<tr class="tr_show">
  <td width="30%">
    <input type="checkbox" name="chk_sel_id" id="<%=tawInformation.getId()%>" value="<bean:write name="tawInformation" property="id" scope="page"/>">
    <%
    out.print("<input type=\"hidden\" name=\"chk_sel_pid\" id=\"" +tawInformation.getId()+ "\" value=\"" + 1 + "\">");
    %>
    <a href="view.do?id=<%=tawInformation.getId()%>&boardId=<%=tawInformation.getBoardId()%>" title="<%=tawInformation.getName()%>">
    <bean:write name="tawInformation" property="name" scope="page"/>
    </a>
  </td>
  <td width="20%">
    <bean:write name="tawInformation" property="publicerName" scope="page"/>
  </td>
  <td width="30%">
    <bean:write name="tawInformation" property="publicDeptName" scope="page"/>
  </td>
  <td width="20%">
    <bean:write name="tawInformation" property="publicTime" scope="page"/>
  </td>
  <input type="hidden" name="boardId" value=<bean:write name="tawInformation" property="boardId" scope="page"/>>
</tr>
<%
listNum = listNum + 1;
%>

</logic:iterate>
</table>

<table id="tp1" width="100%" cellspacing="1" border="0" cellpadding="1">
<tr>
<td align="right" >

<input type="hidden" name="audit" value=<%=audit%>>
<input type="hidden" name="id" value="">
<input type="hidden" name="infoid" value="">
<input type="hidden" name="boardId" value="<%=boardId%>">

<%
SaveSessionBeanForm saveSessionBeanForm =
          (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
String userId=saveSessionBeanForm.getWrf_UserID();
String domainType=(StaticVariable.INFORM_DOMAIN_TYPE)+"";
if (audit.equalsIgnoreCase("2"))
{
%>
<input type="button" Class="clsbtn2" value="<bean:message key="label.add"/>" onclick="onAdd();">
<input type="button" Class="clsbtn2" value="<bean:message key="label.classify"/>" onclick="return onClassify(<%=listNum%>,<%=boardId%>);">
<%}
else if (audit.equalsIgnoreCase("1")){

  %>
<input type="button" Class="clsbtn2" value="审核通过" onclick="onCheckPass(<%=listNum%>);">
<input type="button" Class="clsbtn2" value="不采纳" onclick="onCheckDeny(<%=listNum%>);">
<input type="button" Class="clsbtn2" value="退回修改" onclick="onCheckModi(<%=listNum%>);">
<input type="button" Class="clsbtn2" value="<bean:message key="label.cancel"/>" onclick="history.back()">

<%
  }
else if (audit.equalsIgnoreCase("3")){
  %>
<input type="button" Class="clsbtn2" value="提交" onclick="onTryAgain(<%=listNum%>);">
<input type="button" Class="clsbtn2" value="<bean:message key="label.cancel"/>" onclick="history.back()">

<%
  }
else if (audit.equalsIgnoreCase("4")){%>
<input type="button" Class="clsbtn2" value="<bean:message key="label.remove"/>" onclick="return onRemove(<%=listNum%>);">
<input type="button" Class="clsbtn2" value="<bean:message key="label.cancel"/>" onclick="history.back()">
<%
  }
%>
</td>
</tr>
</table>
</form>

<!--=======================================-->
<html:form  method="post" action="/TawInformation/classify">
<div id="div1" style="position:absolute;width:0;height:0;left:0;top:0;visibility:hidden">
</div>
<input type="hidden" name="boardId1" value=0>
<input type="hidden" name="audit" value=<%=audit%>>
<input type="hidden" name="id" value="">
</html:form>
<iframe width=0 height=0 src="" id="hiddenframe"></iframe>
