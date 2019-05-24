<%@ include file="../../../common/taglibs.jsp"%>
<%@ include file="../../../common/header_eoms_form.jsp"%>
<%@ include file="../../../common/xtreelibs.jsp"%>
 
<%@ page language="java" import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.common.tree.*,com.boco.eoms.common.util.StaticMethod"%>
<%
String  deptStr =(String)request.getAttribute("deptStr");
String path = request.getContextPath();
WKTree wk_tree = new WKTree();
String strTree = wk_tree.strWKTree(deptStr);
String url = "";
String dept1 = "";
String wsClass = "-1";
String sdomIds = StaticMethod.nullObject2String(request.getAttribute("SDOMIDS"));
//String sdomIds = request.getAttribute("SDOMIDS").toString();

RelativeDrop rel = new RelativeDrop();
String users = rel.strRelativeDrop(sdomIds);
%>

<head>  
<script type="text/javascript" src="${app}/scripts/widgets/calendar/calendar.js"></script>
<script type="text/javascript" src="${app}/scripts/form/validation.js"></script>
<title> </title>
 
</head>

<body>
<SCRIPT LANGUAGE=javascript>
<!--
var User = new Array;
<%=users%>
        function ifQuery(json,ids){
	   
        var deptId  = ids
        var sel_sprlen=document.form1.sDeptUser.options.length-1;
        var j=0;
        for(j=sel_sprlen;j>=0;j--)
        {
             document.all.form1.sDeptUser.options[j]=null;
        }

         var m=0;
         document.form1.sDeptUser.options[m]=new Option("","");
         if (deptId != "0")
         {
            var i;
            if (User[deptId]){
            k=User[deptId].length;

            for (i=0;i*2<k;i++)
            {
             var tempoption=new Option(User[deptId][i*2],User[deptId][i*2+1]);
             document.form1.sDeptUser.options[m]=tempoption;
             m++;
            }
            }
         }

       }




function add_RoomTrue()
{
  if (document.all.form1.sDeptUser.selectedIndex>-1)
  {
      selected_spr_text=document.all.form1.sDeptUser.options[document.all.form1.sDeptUser.selectedIndex].text;

      selected_spr_value=document.form1.sDeptUser.options[document.form1.sDeptUser.selectedIndex].value;
      var sel_sprlen1=document.form1.sRoomUserFalse.options.length-1;
      var sel_sprlen=document.form1.sRoomUserTrue.options.length-1;

      var exist_flag=1;
      var j=0;
      for(j=0;j<=sel_sprlen1;j++)
      {
        if(document.form1.sRoomUserFalse.options[j].value==selected_spr_value)
        {
          exist_flag=0;
          break;
        }
      }
      for(j=0;j<=sel_sprlen;j++)
      {
        if(document.form1.sRoomUserTrue.options[j].value==selected_spr_value)
        {
          exist_flag=0;
          break;
        }
      }

      if(exist_flag)
      {
        var test1=new Option(selected_spr_text);
        test1.value=selected_spr_value;
        document.all.sRoomUserTrue.options[++sel_sprlen]=test1;

      }
      else alert("<bean:message key="entry.TawRmSysteminfo.warnLive"/>");
  }
}
function add_all_RoomTrue()
{
  var sel_sprlen0=document.form1.sDeptUser.options.length-1;
  for(i=0;i<=sel_sprlen0;i++)
  {
      selected_spr_text=document.all.form1.sDeptUser.options[i].text;

      selected_spr_value=document.form1.sDeptUser.options[i].value;
      var sel_sprlen1=document.form1.sRoomUserFalse.options.length-1;
      var sel_sprlen=document.form1.sRoomUserTrue.options.length-1;

      var exist_flag=1;
      var j=0;
      for(j=0;j<=sel_sprlen1;j++)
      {
        if(document.form1.sRoomUserFalse.options[j].value==selected_spr_value)
        {
          exist_flag=0;
          break;
        }
      }
      for(j=0;j<=sel_sprlen;j++)
      {
        if(document.form1.sRoomUserTrue.options[j].value==selected_spr_value)
        {
          exist_flag=0;
          break;
        }
      }

      if(exist_flag)
      {
        var test1=new Option(selected_spr_text);
        test1.value=selected_spr_value;
        document.all.sRoomUserTrue.options[++sel_sprlen]=test1;

      }
      else alert("<bean:message key="entry.TawRmSysteminfo.warnLive"/>");
  }
}

function del_RoomTrue()
{
  var sel_sprindex=document.all.form1.sRoomUserTrue.selectedIndex;
  if(sel_sprindex!=-1)
    document.all.form1.sRoomUserTrue.options[sel_sprindex]=null;
}
function del_all_RoomTrue()
{
  var sel_sprlen=document.form1.sRoomUserTrue.options.length-1;
  var j=0;
  for(j=sel_sprlen;j>=0;j--)
  {
  document.all.form1.sRoomUserTrue.options[j]=null;
  }
  var sel_sprlen1=document.form1.sRoomUserFalse.options.length-1;
  var j=0;
  for(j=sel_sprlen1;j>=0;j--)
  {
  document.all.form1.sRoomUserFalse.options[j]=null;
  }
}
function add_RoomFalse()
{
  if (document.all.form1.sRoomUserTrue.selectedIndex>-1)
  {
      selected_spr_text=document.all.form1.sRoomUserTrue.options[document.all.form1.sRoomUserTrue.selectedIndex].text;
      selected_spr_value=document.form1.sRoomUserTrue.options[document.form1.sRoomUserTrue.selectedIndex].value;
      var sel_sprlen=document.form1.sRoomUserFalse.options.length -1;

      var exist_flag=1;
      var j=0;
      for(j=0;j<=sel_sprlen;j++)
      {
        if(document.form1.sRoomUserFalse.options[j].value==selected_spr_value)
        {
          exist_flag=0;
          break;
        }
      }

      if(exist_flag)
      {
        var test1=new Option(selected_spr_text);
        test1.value=selected_spr_value;
        document.all.sRoomUserFalse.options[++sel_sprlen]=test1;
        var sel_sprindex=document.all.form1.sRoomUserTrue.selectedIndex;
        document.all.form1.sRoomUserTrue.options[sel_sprindex]=null;

      }
      else alert(" <bean:message key="entry.TawRmSysteminfo.warnLive"/>");
  }
}
function del_RoomFalse()

{
 
  if (document.all.form1.sRoomUserFalse.selectedIndex>-1)
  {
      selected_spr_text=document.all.form1.sRoomUserFalse.options[document.all.form1.sRoomUserFalse.selectedIndex].text;
      selected_spr_value=document.form1.sRoomUserFalse.options[document.form1.sRoomUserFalse.selectedIndex].value;
      var sel_sprlen=document.form1.sRoomUserTrue.options.length-1;

      var exist_flag=1;
      var j=0;
      for(j=0;j<=sel_sprlen;j++)
      {
        if(document.form1.sRoomUserTrue.options[j].value==selected_spr_value)
        {
          exist_flag=0;
          break;
        }
      }

      if(exist_flag)
      {
        var test1=new Option(selected_spr_text);
        test1.value=selected_spr_value;
        document.all.sRoomUserTrue.options[++sel_sprlen]=test1;
        var sel_sprindex=document.all.form1.sRoomUserFalse.selectedIndex;
        document.all.form1.sRoomUserFalse.options[sel_sprindex]=null;

      }
      else alert("<bean:message key="entry.TawRmSysteminfo.warnLiveOn"/>");
  }
}
function sent_room_user()
{
    document.all.form1.user_true.value = ""
    
    var sel_sprlen=document.form1.sRoomUserTrue.options.length-1;
    if(<%=request.getAttribute("UserNum")!=null?"true":"false"%> && (sel_sprlen+1)%<%=request.getAttribute("UserNum")%>!=0)
   {
    alert("<bean:message key="entry.TawRmSysteminfo.warnError"/>");
    return;
   }
    if (sel_sprlen>= 0){
      document.all.form1.user_true.value =  document.all.form1.sRoomUserTrue.options[0].value;
      for(j=1;j<=sel_sprlen;j++)
        {
              document.all.form1.user_true.value = document.all.form1.user_true.value + "," + document.form1.sRoomUserTrue.options[j].value;
        }
    }
    document.all.form1.user_false.value = ""
    var sel_sprlen=document.form1.sRoomUserFalse.options.length-1;
    if (sel_sprlen>= 0){
        document.all.form1.user_false.value =  document.all.form1.sRoomUserFalse.options[0].value;
        for(j=1;j<=sel_sprlen;j++)
          {
                document.all.form1.user_false.value = document.all.form1.user_false.value + "," + document.form1.sRoomUserFalse.options[j].value;
          }
    }

    document.form1.action="saveNew.do";
    document.form1.submit();
}

function fnSwap(flag){
   var selectIndex=document.form1.sRoomUserTrue.selectedIndex;
   if(selectIndex==-1)
   {
   alert("<bean:message key="entry.TawRmSysteminfo.SelectOne"/>");
   return;
   }
   if(flag==0)
   {
     if(selectIndex==0)
	    return;
	 document.form1.sRoomUserTrue.options(selectIndex).swapNode(document.form1.sRoomUserTrue.options(selectIndex-1));
	}
   else
   {
     if(selectIndex==document.form1.sRoomUserTrue.length-1)
        return;
     document.form1.sRoomUserTrue.options(selectIndex).swapNode(document.form1.sRoomUserTrue.options(selectIndex+1));
   }
}

//-->
</SCRIPT>

<script type="text/javascript">
var	userTreeAction='${app}/xtree.do?method=deptForDuty';
Ext.onReady(function(){
	userTree = new xbox({
		btnId:'userTreeBtn',dlgId:'dlg-dept',
		treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'${eoms:a2u("所属部门域")}',treeChkMode:'single',treeChkType:'user',
		showChkFldId:'deptName',saveChkFldId:'deptId',callback:ifQuery
	});
	 
})
 
</script>
<input type="hidden" name="path" id="path" value="<%=request.getContextPath()%>">
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/duty/css/table_style.css" type="text/css">
        <script type="text/javascript"  charset="UTF-8" src="<%=path%>/duty/css/finallytree.js"></script>
</head>
<body>
<form  name="form1" id="form1" method="post" >
<br>
<table cellpadding="0" cellspacing="0" border="0" width="100%" align="center">
<tr>
<td class="label">
<table cellpadding="0" cellspacing="0" border="0" width="100%" class="listTable">
  <tr>
<td width="100%" align="center" class="label">
<%=request.getAttribute("ROOMNAME") %> <bean:message key="entry.TawRmSysteminfo.apparatusroom"/><bean:message key="entry.TawUserRoom.btn_new"/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%
String saveflag = String.valueOf(request.getParameter("save"));
if (saveflag.trim().equals("true"))
{%>
 <font color="red"><bean:message key="entry.TawRmSysteminfo.savesuccess"/></font>
<%}%>

  </td>
</tr>

  <tr>
<td>
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="listTable" align=center> 
<tr class="tr_show">
<input type="hidden" name="sdomids" id="sdomids" value="<%=sdomIds%>">
 
<td width="50%" align="center" >

<%
Vector DeptUser = new Vector();
DeptUser = (Vector)request.getAttribute("DeptUser");
%>
<input type="hidden" name="deptId" id="deptId" value="">
<input type="button" value="${eoms:a2u('部门域列表')}" id="userTreeBtn" class="btn" />
<bean:message key="entry.TawRmSysteminfo.deptId"/>
<p>

<input type="text" name="deptName" size="20" value="" id ="deptName"  readonly="readonly">
<input type="hidden" name="deptId" value="0">
<p>
<bean:message key="TawUserRoom.room_user_all"/>
<select size=14 name="sDeptUser" style="width:150">
<%
for (int i=0;i*2<DeptUser.size();i++)
{
%>
<option value="<%out.print(DeptUser.elementAt(i*2));%>"><%out.print(DeptUser.elementAt(i*2+1));%></option>
<%
}
%>

</select>
</td>
<td width="20%" align="center" valign = "top">
<br>
<br>
<br>
<br>
<br>
<input type=button class="button" name=btnAddItem value=<bean:message key="TawUserRoom.btn_add"/> onclick="javascript:add_RoomTrue();")>
<p><p>
<input type=button class="button" name=btnDelItem value=<bean:message key="TawUserRoom.btn_del"/> onclick="javascript:del_RoomTrue();">
<p>
<input type=button class="button" name=btnAddAll value=<bean:message key="TawUserRoom.btn_add_all"/> onclick="javascript:add_all_RoomTrue();">
<p>
<input type=button class="button" name=btnDelAll value=<bean:message key="TawUserRoom.btn_del_all"/> onclick="javascript:del_all_RoomTrue();">
</td>
<td width="30%" align="center">
<table class="formTable">
<tr>
<td align="center" class="label">
<%
Vector RoomUserTrue = new Vector();
Vector RoomUserFalse = new Vector();
RoomUserTrue = (Vector)request.getAttribute("RoomUserTrue");
RoomUserFalse = (Vector)request.getAttribute("RoomUserFalse");
%>
<bean:message key="TawUserRoom.room_user_true"/>
</td>
</tr>
<tr>
<td align="center" class="label">
<select size=8 name="sRoomUserTrue" style="width:150">
<%
for (int i=0;i*2<RoomUserTrue.size();i++)
{
%>
<option value="<%out.print(RoomUserTrue.elementAt(i*2));%>"><%out.print(RoomUserTrue.elementAt(i*2+1));%></option>
<%
}
%>
</select>
</td>
<%if(request.getAttribute("UserNum")!=null){%>
<td>
<table>
<tr><td><img src="<%=request.getContextPath()%>/duty/images/bottom/up.gif" onclick="fnSwap(0);"></td></tr>
<tr><td><img src="<%=request.getContextPath()%>/duty/images/bottom/down.gif" onclick="fnSwap(1);"></td></tr>
</table>
</td>
<%}%>
</tr>
<tr>
<td align="center">
<p>
<input type="button" class="button" name="btnIn" value="${eoms:a2u('向上')}" onclick=javascript:del_RoomFalse();>
<input type="button" class="button" name="btnOut" value="${eoms:a2u('向下')}" onclick=javascript:add_RoomFalse();>
</td>
</tr>
<tr>
<td align="center" class="label">
<p>
<bean:message key="TawUserRoom.room_user_false"/>
<select size=4 name="sRoomUserFalse" style="width:150">
<%
for (int i=0;i*2<RoomUserFalse.size();i++)
{
%>
<option value="<%out.print(RoomUserFalse.elementAt(i*2));%>"><%out.print(RoomUserFalse.elementAt(i*2+1));%></option>
<%
}
%>
</select>
</td>
</tr>
</table>
</td>
</tr>
</table>
</td>
</tr>
  <tr>
<td>
<table height="30" cellpadding="0" cellspacing="0" border="0" width="100%" >
<tr>
<td align="right">


<input type=button class="button" name=save value=<bean:message key="label.save"/> onclick="sent_room_user();">
<input type=button class="button" name=save value=<bean:message key="entry.TawRmSysteminfo.btn_new"/> Onclick="window.location.href ='../TawRmSysteminfo/add.do?roomId=<%=request.getParameter("roomId")%>'">&nbsp;&nbsp;
<input type="hidden" size = 400 name = "user_true" value = "">
<input type="hidden" size = 400 name = "user_false" value = "">
<input type="hidden"  name = "roomId" value = "<%=request.getParameter("roomId")%>">

  </td>
</tr>
</table>
</td>
</tr>
  </table>
</td>
</tr>
</table>

</form>
</body>









