<%@ page contentType="text/html; charset=gb2312"%>
<%@ page import="java.util.*"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=GB2312">
<link href="../css/tab.css" rel="stylesheet" type="text/css">
<script src="../js/scripts.js"></script>
<script src="../js/navigate.js"></script>
<script src="../js/checkselect.js"></script>
<script src="../js/tab.js"></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
function AddDutyTask () {
// var ItemData = showModalDialog("/" + CurDB + "/EditDeptDutyTask?OpenForm", "", "status:no;resizable:no;help:no;dialogWidth:350px;dialogHeight:165px");
  var ItemData = showModalDialog("../manager/TawRmDefineTree/Folder.jsp", "", "status:no;resizable:no;help:no;dialogWidth:300px;dialogHeight:165px")
  if (ItemData) {
  
      document.forms[0].QueryString.value ="Type=Folder&Action=ADD&Parent=0&Name=" + ItemData["PlanName"] + "&cycle=" + ItemData["cycle"]+"&specility="+ItemData["specility"];
      _doClick();
     }
}

function deletetask(){
var form=window.document.forms[0]
var tempName=''
var DocCount=0;
for (var i=0;i<form.elements.length;i++)
{
	if(form.elements[i].type=='checkbox')
	   if(form.elements[i].checked & form.elements[i].value!="on")
	  {
             DocCount = DocCount+1
             if(tempName=='')  {tempName = form.elements[i].value}
             else
               	 {  tempName=tempName+','+form.elements[i].value}
       	  }
}
if (DocCount>5)
   {alert("��ѡ����"+DocCount+"����ҵ��ÿ�β���ѡȡ����5����ҵ����ɾ����")

   }
else if (DocCount==0)
   {alert("��ѡ��Ҫɾ������ҵ��")
   }
else
   //ɾ��ѡ�����ĵ�
  {
    document.forms[0].QueryString.value ="Node="+tempName+"&Action=DEL&Parent=0";
   //alert (newurl);
   if (confirm("ȷ��ɾ��"+DocCount+"����ҵ?")) {
      _doClick();
   }
  }
}


function DispDutyTask (nodeId,nodeName,specility,cycles,roomId) {
  resetCheck();
  document.form1.action="design.do?nodeId="+nodeId+"&Name="+nodeName+"&specility="+specility+"&cycle="+cycles+"&roomId="+roomId;
  document.form1.submit();
}

function resetCheck() {
  var form=window.document.forms[0]

  for (var i=0;i<form.elements.length;i++) {
     if(form.elements[i].type=='checkbox')
  	  form.elements[i].checked = false;
    }
 }
// -->
</SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
<!--
function _doClick() {
  var form = document.form1;
  if (form.onsubmit) {
     var retVal = form.onsubmit();
     if (typeof retVal == "boolean" && retVal == false)
       return false;
  }
  form.action+="?"+document.forms[0].QueryString.value;
  form.submit();
  return false;
}
// -->
</SCRIPT>
</HEAD>
<%
HashMap planMapCicyle=(HashMap)request.getAttribute("planMapCicyle");
%>

<BODY TEXT="000000" BGCOLOR="FFFFFF" topmargin=0 leftmargin=0 bgColor=#ffffff  onLoad="tabToggle(tab_tasklist);
displayToggle(tab3);
clearnodocumenttext();
setTableColor(table1,1);">

<FORM METHOD="post" ACTION="save.do" NAME="form1">
<input type="hidden" name="roomId" value="<%=request.getAttribute("roomId")%>">
<INPUT NAME="QueryString" VALUE="" STYLE="display:none">

<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center" height="102%">
<tr>
 <td  colspan="3" height="28">
   <table width="100%" border="0" cellspacing="0" cellpadding="0" background="../images/main-top_02.gif" class="p9">
     <tr>
      <td width="11"><img src="../images/main-top_01.gif" width="14" height="33"></td>
      <TD width=""></TD>
      <TD nowrap onmouseover=mOver_button(this) onmouseout=mOut_button(this) ><a href=javascript:self.location="selectroom.do"><IMG border=0 height=19        src="../images/backhome.gif" width=19 align=absmiddle>����&nbsp;</a></TD>
      <td nowrap onmouseover=mOver_button(this) onmouseout=mOut_button(this) ><a href="javascript:AddDutyTask()" title="�½�ֵ����ҵ"><img src="../images/new.gif" align="absmiddle" border="0">�½�ֵ����ҵ&nbsp;</a></td>
      <td nowrap onmouseover=mOver_button(this) onmouseout=mOut_button(this) ><a href="#" onclick="deletetask();return false" title=ɾ��ѡ���ĵ�><img src="../images/delete.gif" align="absmiddle" border="0">ɾ��&nbsp;</a></td>
      <td nowrap onmouseover=mOver_button(this) onmouseout=mOut_button(this) ><a href="#" onclick='self.location="listview.do?roomId=<%=request.getAttribute("roomId")%>"'><img src="../images/icon_refresh.gif" align="absmiddle" border="0" alt=ˢ��>ˢ��&nbsp;</a></td>
      <TD width="100%">&nbsp;</TD>
      <td align="right" width="158"><img src="../images/main-top_03.gif" width="158" height="33"></td>  </TR>
   </TABLE>
</td>
</tr>
<tr>
   <td bgcolor="#EFEFEF" width="1"><img width="1"></td>
   <td valign="top" width="2000">
        <!--
	<DIV class=tab id=tab_dept style="LEFT: 5px" onclick="RunAgent('opendept')">��������</DIV>
	<DIV class=tab id=tab_duty style="LEFT: 95px" onclick="RunAgent('openduty')">ֵ���</DIV>
	<DIV class=tab id=tab_info style="LEFT: 275px" onclick="RunAgent('openinfo')">��ʱ֪ͨ</DIV>
	<DIV class=tab id=tab_comment style="LEFT: 365px"  onclick="RunAgent('opencomment')">���ô������</DIV>
	-->
	<DIV class=tab id=tab_tasklist style="LEFT: 185px">ֵ����ҵ</DIV>
	<div id="Layer1" class=mainview  id=Layer1>
	<!-- ��һҳ  -->
	<DIV class=cpcontent id=tab3>
	<DIV id=oDescription style="PADDING-RIGHT: 5px; PADDING-LEFT: 5px; PADDING-BOTTOM: 5px; PADDING-TOP: 5px">
	<table id="table1" border=0 align=center width=98% cellpadding=0 cellspacing=1 style=font-size:9pt>
	<tr class=shitu_headcolor>
	   <td width=2% nowrap></td>
	   <td width=8% nowrap>
	      <center>����</center>
	   </td>
	   <td width=15% nowrap>
	       <center>רҵ</center>
	   </td>
	   <td width=40% nowrap >
	       <center>�ƻ�����</center>
	   </td>
	</tr>
        <%if(planMapCicyle!=null && planMapCicyle.size()>0) {
          String[] cycles={"workserial","day","week","month","quarter","halfyear","year"};
          String[] cyclesNames={"ÿ��","ÿ��","ÿ��","ÿ��","ÿ����","ÿ����","ÿ��"};
          for(int i=0;i<cycles.length;i++)
          {
          if(planMapCicyle.get(cycles[i])!=null)
          {
        %>
	<tr id="t1t<%=i+1%>">
	   <td>
	      <input type="checkbox" class="chkbox" name="L1L<%=i+1%>" onclick="show(this)">
	   </td>
	   <TD align="left" nowrap>
	      <img src="../images/mod.gif" id=a1a<%=i+1%> border=0
	      onClick=javascript:showhide('t1t<%=i+1%>');setTableColor(table1,1) style='CURSOR: hand'>
	      <%=cyclesNames[i]%></td><td></td><td></td><td></td><td></td>
	 </tr>
        <%
            Vector tmpVector=(Vector)planMapCicyle.get(cycles[i]);
            for(int j=0;j<tmpVector.size()/3;j++)
            {
         %>
   	   <tr ondblclick=DispDutyTask("<%=tmpVector.elementAt(j*3)%>","<%=tmpVector.elementAt(j*3+1)%>","<%=tmpVector.elementAt(j*3+2)%>","<%=cycles[i]%>","<%=request.getAttribute("roomId")%>")
	       onclick=javascript:previewdocument("")
	       id="t1t<%=i+1%>t<%=j+1%>" style="display:none" class=doczhedie align=left>
	     <td>
	        <input type="checkbox" class="chkbox" value="<%=tmpVector.elementAt(j*3)%>" name="L1L<%=i+1%>L<%=j+1%>"  onclick="show(this)">
	     </td>
	     <td>
	     </td>
	     <td><%=tmpVector.elementAt(j*3+2)%></td>
	     <td><%=tmpVector.elementAt(j*3+1)%></td>
	   </tr>
         <%
            //tmpVector.elementAt(j)
            }
          }
          }
          %>
        <%}%>
	</table>
	</DIV></DIV>
   </td>
   <td bgcolor="#EFEFEF" width="1"><img width="1"></td>
</tr>
<tr>
   <td height="18"  colspan="3"><img src="../images/main-buttom.gif" width="100%" height="18"></td>
</tr>
</table>
</BODY>
</HTML>
