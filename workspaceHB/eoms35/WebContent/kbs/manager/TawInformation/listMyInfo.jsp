<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="com.boco.eoms.common.util.*,java.util.*,com.boco.eoms.common.controller.*"%>
<%@ page import="com.boco.eoms.jbzl.bo.TawValidatePrivBO"%>
<script language="javascript">
<!--
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
function onCheck()
{
  document.form1.checkInfo.value=1;
  document.form1.action="list.do";
  document.form1.submit();
}
function onRemove(listNum)
{
  var str=save_sel_id(listNum);
 //alert(str);
  if (str!="")
  {
    if (confirm("你确认要删除选定信息吗？")==true)
    {
      document.form1.id.value=str;
      //document.form1.action="trash.do";
      document.form1.action="delinfo.do";
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
function onClassify(listNum,infoType,boardId)
{

  var str=save_sel_id(listNum);
  if (str!="")
  {
    //alert(self.screenLeft + (screen.width-self.screenLeft)/2-200);
    //alert(self.screenTop + (screen.height-self.screenTop)/2-75);
    if (div1.innerHTML=="")
    {
      document.frames["hiddenframe"].location.replace("preclass.do?infoType=" + infoType + "&listNum=" + listNum + "&boardId=" + boardId);
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

function onClassifyCancel()
{
  document.all.div1.style.visibility="hidden";
  document.form1.page.style.visibility="visible";
}
function save_sel_id(listNum)
{

  var parent_id="";
  var sel_idStr="";
  if (listNum==1)
  {
    if (document.form1.chk_sel_id.checked)
      sel_idStr=document.form1.chk_sel_id.value;
  }
  else if (listNum>1)
  {
    i=0;
    while (parent_id=="" && i<listNum)
    {
      if (document.form1.chk_sel_id[i].checked)
        parent_id=document.form1.chk_sel_pid[i].value;
      i+=1;
    }
    for(i=0;i<listNum;i++)
    {
      if (document.form1.chk_sel_id[i].checked)
      {
        if (document.form1.chk_sel_pid[i].value!=parent_id)
        {
          alert("不能对不同级别的同时操作");
          return "";
        }
        else
        {
          if (sel_idStr=="")
            sel_idStr=document.form1.chk_sel_id[i].value;
          else
            sel_idStr=sel_idStr + "," + document.form1.chk_sel_id[i].value;
          parent_id=document.form1.chk_sel_pid[i].value;
        }
      }
    }
  }
  return sel_idStr;

}
function openChild(listNo)
{
  var targetImg =eval("document.all.img" + listNo);
  var targetDiv =eval("document.all.tr_" + listNo);
  if (targetDiv.style.display!='block')
  {
    targetDiv.style.display="block";
    targetImg.src="../images/nofollow.gif";
  }
  else
  {
    targetDiv.style.display="none";
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
-->
</script>
<%
String boardId="2020";
int infoType = 4;
//String boardId=String.valueOf(request.getParameter("boardId"));
//int infoType=Integer.parseInt(request.getParameter("infoType"));
int flag = Integer.parseInt(request.getParameter("flag"));
int pageNo=StaticMethod.nullObject2int(request.getAttribute("pageNo"));
int pageNum=StaticMethod.nullObject2int(request.getAttribute("pageNum"));
int listCount=StaticMethod.nullObject2int(request.getAttribute("listNum"));
String checkInfo = StaticMethod.null2String(request.getParameter("checkInfo"));
if (checkInfo=="")
  checkInfo="0";
ResourceBundle prop = ResourceBundle.getBundle("resources.application_zh_CN");
String nextPage = prop.getString("label.nextPage");
String lastPage = prop.getString("label.lastPage");
String prePage = prop.getString("label.prePage");
String firstPage = prop.getString("label.firstPage");
String boardName="";
if(flag ==0)
{
	 boardName="我的邮箱";
}
else
{
	 boardName="我的资料";
}
//String boardName=String.valueOf(request.getAttribute("boardName"));
//int childNum=StaticMethod.nullObject2int(request.getAttribute("childNum"));
%>
<form name="form1" method="post" >

<table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
    <td width="100%" valign="middle" class="clsscd">
       <%
         if (checkInfo=="1")
         {%>
           <bean:message key="TawInformation.checkList"/>
         <%
         }
         else
         {
          //<bean:message key="TawBoard.current"/>:
         %>


         <%
           out.println(boardName);
         }
       %>
    </td>
  </tr>
</table>
<table rules="rows" border="1" bordercolor="#709fd5" width="100%" cellspacing="0"  cellpadding="1">
      <tr >
        <td width="5%"   class="clsthd2" align=center></td>
        <td width="10%"  class="clsthd2" align=center>发件人</td>
        <td width="45%"  class="clsthd2"><bean:message key="TawInformation.topic"/></td>
        <td width="10%"  class="clsthd2"><bean:message key="TawInformation.deptId"/></td>
        <td width="15%"  class="clsthd2">日期</td>
        <td width="15%"  class="clsthd2" align="right">回复</td>
        <!--
        <td width="10%" bgcolor="#d7dae1" class="clsthd2"><bean:message key="TawInformation.hitNum"/></td>
        -->
     </tr>
<%
int listNum=0;
int divFlag=0;
%>
<logic:iterate id="tawInformation" name="TAWINFORMATIONS" type="com.boco.eoms.kbs.model.TawInformation">
<%
if (tawInformation.getLayer()==0 && divFlag==1)
{
  out.println("</table></td></tr>");
  divFlag=0;
}
%>
<tr>
  <td width="5%" nowrap bgcolor="#e5edf8" class="clsthd2">
    <input type="checkbox" name="chk_sel_id" id="<%=listNum%>" value="<bean:write name="tawInformation" property="id" scope="page"/>">
    <input type="hidden" name="chk_sel_pid" id="<%=listNum%>" value="<bean:write name="tawInformation" property="parentId" scope="page"/>">
<!--<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>-->

    <%
    if (tawInformation.getHitNum() == 0){
        out.println("<img src='../images/noread.gif'>");
    }else{
        out.println("&nbsp;");
    }
    %>
  </td>
  <td width="10%" nowrap bgcolor="#e5edf8" class="clsthd2" align=center>
    <bean:write name="tawInformation" property="userName" scope="page"/>
  </td>
  <td width="45%" nowrap bgcolor="#e5edf8">

    <%
    String att=StaticMethod.null2String(tawInformation.getAttachName());
    if (att.trim().equalsIgnoreCase("")){
         out.println("&nbsp;");
    }
    else{
         out.println("<img src='../images/attachsign.gif'>");
    }
    //for(int i=0;i<tawInformation.getLayer();i++) ;
    if (tawInformation.getChildNum()>0 && tawInformation.getLayer()==0)
      out.println("<img id=\"img" + listNum + "\" src='../images/plus.gif' onclick=\"openChild("+listNum+")\">");
    if (tawInformation.getChildNum()==0 && tawInformation.getLayer()==0)
      out.println("<img src='../images/nofollow.gif'>");

    %>
    <a href="viewinfo.do?id=<%=tawInformation.getId()%>&boardId=<%=tawInformation.getBoardId()%>&infoType=<%=tawInformation.getInfoType()%>" title="<%=tawInformation.getTopic()%>">

      <%
      String topic=tawInformation.getTopic();
      if (!"".equals(StaticMethod.null2String(tawInformation.getImgName()).trim()))
      {
        topic = topic + "(图)";
      }
      else if ("".equals(StaticMethod.null2String(tawInformation.getBody()).trim()))
      {
        topic = topic + "(无内容)";
      }
      byte[] temp=topic.getBytes();
      String aa = "";
      int limitLen = 0;
      if (temp.length>40-tawInformation.getLayer()*2)
      {
        aa = new String(temp,0,40);
        if ((aa+"1").equals("1"))
        {
          limitLen = 39;
        }
        else
        {
          limitLen=40;
        }
        out.println(new String(temp,0,limitLen-tawInformation.getLayer()*2) + "...");
      }
      else
      {
        out.println(topic);
      }
      %>
    </a>
  </td>
  <td width="10%" nowrap bgcolor="#e5edf8" class="clsthd2">
    <bean:write name="tawInformation" property="deptName" scope="page"/>
  </td>
  <td width="15%" nowrap bgcolor="#e5edf8" class="clsthd2">
    <bean:write name="tawInformation" property="dateTime" scope="page"/>
  </td>
  <td width="5%" nowrap bgcolor="#e5edf8" align="right">
   <a href="return.do?id=<%=tawInformation.getId()%>&boardId=<%=tawInformation.getBoardId()%>&infoType=<%=tawInformation.getInfoType()%>">回复</a>
  </td>

  <!--
  <td width="10%" nowrap bgcolor="#e5edf8" class="clsthd2">
    <bean:write name="tawInformation" property="hitNum" scope="page"/>
  </td>
  -->
</tr>

<%
if (tawInformation.getChildNum()>0 && tawInformation.getLayer()==0)
{
  out.println("<tr id=\"tr_" + listNum + "\" style=\"display:none\"><td colspan=6 width=100% ><table bgcolor=\"#f2f2f2\" width=\"100%\" cellspacing=\"0\" border=\"0\">");
  divFlag=1;
}
listNum+=1;
%>


              </logic:iterate>
<%
if (divFlag==1)
{
  out.println("</table></td></tr>");
  divFlag=0;
}
%>
</table>

<table id="tp1" bgcolor="#709fd5" width="100%" cellspacing="1" border="0" cellpadding="1" class="clsbkgd">
<tr>
<td align="right" bgcolor="#d7dae1">
<input type="hidden" name="boardId" value=<%//=boardId%>>
<input type="hidden" name="parentId" value=0>
<input type="hidden" name="checkInfo" value=<%//=checkInfo%>>
<input type="hidden" name="infoType" value=<%//=tawInformation.getInfoType()%>>
<input type="hidden" name="id" value="">
<input type="hidden" name="flag" value="<%=flag%>">
<bean:message key="taw.total"/> <%=listCount%> <bean:message key="taw.piece"/> <%=pageNum%> <bean:message key="taw.pageDescription"/>
<select name="page" onchange="goPage();">
<%
for (int i=1;i<=pageNum;i++)
{
  out.println("<option value='" + i + "'");
  if (pageNo==i)
    out.println(" selected");
  out.println( ">" + i + "</option>");
}
%>
</select><bean:message key="taw.page"/>
<%
if (pageNum>1)
{
      if (pageNo>1)
      {
  	out.println("<a href='myinfo.do?pageNo=1"+ "&flag=" + flag + "'>" + firstPage + "</a>&nbsp;");
        out.println("<a href='myinfo.do?pageNo=" + (pageNo-1) +  "&flag=" + flag + "'>" + prePage + "</a>&nbsp;");
      }
      if (pageNo<pageNum)
      {
       out.println("<a href='myinfo.do?pageNo=" + (pageNo+1) +  "&flag=" + flag + "'>" + nextPage + "</a>&nbsp;");
       out.println("<a href='myinfo.do?pageNo=" + pageNum + "&flag=" + flag + "'>" + lastPage + "</a>&nbsp;");
      }
}
SaveSessionBeanForm saveSessionBeanForm =
          (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
String userId=saveSessionBeanForm.getWrf_UserID();
%>
<input type="button" Class="clsbtn2" value="<bean:message key="label.remove" />" onclick="return onRemove(<%=listNum%>);">
<!--
<input type="button" Class="clsbtn2" value="<bean:message key="TawInformation.query"/>" onclick="onQuery();">
-->
</td>
</tr>
</table>
</form>

<!--=======================================-->
<html:form  method="post" action="/TawInformation/classify">
<div id="div1" style="position:absolute;width:0;height:0;left:0;top:0;visibility:hidden">
</div>
<input type="hidden" name="boardId1" value=0>
<input type="hidden" name="parentId" value=0>
<input type="hidden" name="checkInfo" value=<%//=checkInfo%>>
<input type="hidden" name="infoType" value=<%//=infoType%>>
<input type="hidden" name="id" value="">
<input type="hidden" name="flag" value="<%=flag%>">
</html:form>
<iframe width=0 height=0 src="" id="hiddenframe"></iframe>
<!--========================================-->

<script language="JavaScript">
function goPage()
{
  window.location="list.do?pageNo=" + document.all.page.value + "&boardId=<%=boardId%>&infoType=<%=infoType%>&checkInfo=<%=checkInfo%>";
}
</script>
