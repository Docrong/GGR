<%@page contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="com.boco.eoms.resmanage.query.*"%>
<%@page import="com.boco.eoms.resmanage.operator.*"%>
<%@page import="mcs.common.db.*"%>
<%@page import ="com.boco.eoms.jbzl.bo.*"%>
<%@include file="../power.jsp"%>
<%
/**
*@ EOMS_RES (EOMS资源管理模块--数据维护)
*@ Copyright : (c) 2003.10.25
*@ Company : BOCO.
*@ 显示资源列表信息
*@ author:wuzongxian
*@ version 1.0
**/
%>

<%
String sId = null;
if(request.getParameter("id") != null)
	sId = request.getParameter("id");
else
	sId = "0";
int  oper_id=0;
entityoperate Entity = new entityoperate();
String stroppId = Entity.getoperatorId(Integer.parseInt(sId));
List oppId = new ArrayList();
if (stroppId!=null)
	oppId = Entity.spitStr(stroppId,",");
oper_id = Integer.parseInt(oppId.get(0).toString());
//**********鉴权处理*******
Vector cityVec = new Vector();
TawValidatePrivBO ValidatePrivBO = new TawValidatePrivBO();
cityVec = ValidatePrivBO.validatePriv(userId,oper_id,1);
//out.println("domain vec size is:::"+cityVec.size());
if (cityVec.size()==0)
{
	out.println("<script>alert('没有权限,请与系统管理员联系"+"');</script>");
	return;
}
/*for (int i=0;i<cityVec.size();i++)
{ 
	out.println("domain "+i+" values is:::"+cityVec.get(i));
}*/
/*TawValidatePrivBO ValidateBo = new TawValidatePrivBO();
if (!ValidateBo.validatePriv(userId,oper_id,deptId,1))
{
	out.println("<script>alert('没有权限"+"');</script>");
	return;
}
else*/
	//out.println("<script>alert('有权限!!!!"+"');</script>");

//************************
request.getParameter("gb2312");
if(request.getParameter("SucMsg") != null)
{
	%>
	<script>
		alert('<%=request.getParameter("SucMsg")%>');
	</script>
	<%
}
String cityId = null;
if(request.getParameter("cityid") != null)
	cityId = request.getParameter("cityid");
else
	cityId = "0";
	//cityId = ug.getCity() + "";
int pageid;
if(request.getParameter("pageid") != null)
	pageid = Integer.parseInt(request.getParameter("pageid"));
else
	pageid=1;

int iFlag;
if(request.getParameter("flag") != null)
	iFlag = Integer.parseInt(request.getParameter("flag"));
else
	iFlag = 1;
String orderColName = null;
if(request.getParameter("orderColName") != null)
	orderColName = request.getParameter("orderColName");
else
	orderColName = "";
int orderflag =1;
int tmpflag = 1;

if(request.getParameter("orderflag") != null)
{
	orderflag = Integer.parseInt(request.getParameter("orderflag"));
	if (orderflag == 1)
		tmpflag =2;
	if (orderflag == 2)
		tmpflag =1;
}
else
{
	orderflag =1;
}
%>
<html>
<head>
<title>显示资源信息列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<body bgcolor="#eeeeee" text="#000000" class="listStyle">
<!-- <br> <a href="editInsert.jsp?id=<%=sId%>">input </a>
 --><form action="editInsert" name="entity" method=POST >
<table bgcolor=#dddddd width='100%' border=0 cellspacing=1>
<%
//加入当前位置
syscolindex SysColIndex = new syscolindex();

//得到实体类型
systabindex SysTab = new systabindex();
Vector TabVect = new Vector();
TabVect = Entity.getTabinfor(sId);
int EntityType=0;
int cityFlag=0;
String tabname = null;
String cc_location="";
for(int i=0;i<TabVect.size();i++)
{
	SysTab = (systabindex)TabVect.get(i);
	EntityType = SysTab.getFi_type();
	tabname   = SysTab.getCc_name();
	cityFlag  = SysTab.getCi_cityflag();
	cc_location = SysTab.getCc_location();
}
out.println("<tr bgcolor=#eeeeee>");
out.println("<td align=left width='70%'><font size=2>"+cc_location+"->"+tabname+"列表显示</font></td>");
/*if (validatePriv && cityFlag==0)
{
  queryRes qre = new queryRes();
  Vector ctV = new Vector();
  cityClass ct = new cityClass();
  ctV = qre.getCity(0);
 out.println("<td align=right width='25%'><font size=2>请您选择一个城市:</font></td><td width='25%' align=left><select name=cityid onchange='gofilter()'><option value=0>--全部--</option>");
for(int a = 0; a < ctV.size(); a ++)
{
	ct = (cityClass)ctV.get(a);
	if(ct.getId() == Integer.parseInt(cityId))
		out.println("<option value="+ct.getId()+" selected>"+ct.getName()+"</option>");
	else
		out.println("<option value="+ct.getId()+">"+ct.getName()+"</option>");
}
out.println("</select></td>");	
}
out.println("</tr>");*/
%>
</table>
<%
Vector EntVect = new Vector();
EntVect = Entity.getcolVec(sId,iFlag);	//得到实体Map

//out.println("Entity name :"+Entity.getCc_name());
Vector SysVect = new Vector();
if(iFlag == 1)
	SysVect = Entity.getdiscol(sId);
else
	SysVect = Entity.getcolinfor(sId);
int colNum = EntVect.size();				//列信息
String state=null;
if(colNum != 0)
{
	coldata colData = new coldata();
	/******************	构造内容	*****************/
	%>
	<br><table bgcolor=#dddddd width='100%' border=0 cellspacing=1>
	<tr>
	<%
	/*if (powerflag == 1)
	{
		out.println("<td width=20></td>");
	}*/
	out.println("<td width=20></td>");
	for(int Col = 1; Col < SysVect.size(); Col ++)
	{
		SysColIndex = (syscolindex)SysVect.get(Col);
		out.println("<td align=center><b><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><a href=javascript:goorder("+"'"+SysColIndex.getCc_code()+"',"+tmpflag+")>"+SysColIndex.getCc_name()+"</a></font></b>");
		if(SysColIndex.getCc_code().equals(orderColName))
		{
			if (orderflag == 1)
				out.println("<image src=../images/order2.gif>");
			if (orderflag ==2)
				out.println("<image src=../images/order1.gif>");
		}
		out.println("</td>");
	}
	%>
	</tr>
	<%
    if (request.getParameter("state")!=null)
	{
		state=request.getParameter("state");
	}
	else
	{
		state="0";
	}
	String strSql = Entity.getStrSql(EntVect,cityVec,orderColName,orderflag,state);	
    
	//得到查询的SQL语句
	//out.println(strSql);
	/******************  构造分页显示  *************/

	VectorRS rs = new VectorRS();
	int pagecapacity = 15;
	rs.setPageCapacity(pagecapacity);						//每页显示记录数据个数

	rs.setRS(strSql);
	rs.setCurrentPageIndex(pageid);
	//out.println("row count is: "+rs.getRowCount());
	if(rs.getRowCount() >= 1)
	{
		for(int i = 1; i <= rs.getCurrentPageRowNum(); i++)
		{
			out.println("<tr bgcolor="+((i%2 == 0)?"#eaeaea":"#eeeeee")+">");
			//=====modify
			/*if (powerflag ==1)
			{
				out.println("<td><input type=checkbox name='pi_id' value="+rs.getString(1)+"></td>");
			}*/
			out.println("<td><input type=checkbox name='pi_id' value="+rs.getString(1)+"></td>");
			//========
			int temp = 0;
			int tempFlag = 0;
			for(int a = 0; a < colNum; a ++)
			{
				colData = (coldata)EntVect.get(temp);
				temp ++;
				if(colData.getCol_flag() == 1)
				{
					if (EntityType ==0)
					{
						out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+rs.getString(a+2+tempFlag)+"</font></td>");
					}
					else
					{
						String templink="";
						if (colData.getTab_id()!=null)
						{
							templink ="<a href='editDetail.jsp?id="+rs.getString(a+1+tempFlag)+"&tabname="+colData.getTab_id()+"'>"+rs.getString(a+2+tempFlag)+"</a>";
						}
						//out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><a href='editDetail.jsp?id="+rs.getString(a+1+tempFlag)+"&tabname="+colData.getTab_id()+"'>"+rs.getString(a+2+tempFlag)+"</a></font></td>");
						out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+templink+"</font></td>");
					}
					tempFlag = tempFlag + 1;
				}
				else
				{
					switch(a)
					{
						case 0:
							if (EntityType == 0)
							{
								out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>");
							}
							else
							{
								out.println("<td align=center><a href=editDetail.jsp?id="+rs.getString(a+1)+"&tabname="+sId+">");
							}
							break;
						case 1:
							//modify 2003-07-14 by wuzongxian
							String tempvalue = rs.getString(a+1);
							//out.println("tempvalue is: "+tempvalue);
							if (tempvalue==null)
							{
							  tempvalue="";
							}
							out.println(tempvalue+"</font></a></td>");
							break;
						default:
							String tempValue=rs.getString(a+1+tempFlag);
						    if (tempValue==null)
								tempValue = "";
							out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+tempValue+"</font></td>");
							break;
					}
				}
			}
			rs.next();
			out.println("</tr>");
		}
	}
	out.println("</table>");
	%>
<table width=100%>
<tr>
<td height=20 align=center width=40></td>
<td height=20 align=center width=60>
<%
	if (Integer.parseInt(state)==140)
		out.println("<input type=button value='审核通过' onclick='edit(entity)'>");
    if (Integer.parseInt(state)==141)
		out.println("<input type=button value='分配IP' onclick='edit(entity)'>");
%>
</td>
<% if (rs.getPageCount()>0){%>
<td height=20 align=right><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>第<%=rs.getCurrentPageIndex()%>页 / 共<%=rs.getPageCount()%>页</font></td>
<%}%>
<td height=20 align=center>
<%if(rs.getCurrentPageIndex() != rs.getFirstPageId())
out.println("<font face=webdings color=#06b020>4</font><a href=editIpList.jsp?id="+sId+"&pageid="+rs.getFirstPageId()+"&cityid="+cityId+"&orderColName="+orderColName+"&orderflag="+orderflag+">首页</a>");%>
 <%if(rs.getCurrentPageIndex() != rs.getFirstPageId())
 out.println("<font face=webdings color=#06b020>4</font><a href=editIpList.jsp?id="+sId+"&pageid="+rs.getPreviewPageId()+"&cityid="+cityId+"&orderColName="+orderColName+"&orderflag="+orderflag+">上一页</a>");%>
 <%if(rs.getCurrentPageIndex() != rs.getLastPageId() && rs.getRowCount() > pagecapacity)
 out.println("<font face=webdings color=#06b020>4</font><a href=editIpList.jsp?id="+sId+"&pageid="+rs.getNextPageId()+"&cityid="+cityId+"&orderColName="+orderColName+"&orderflag="+orderflag+">下一页</a>");%>
 <%if(rs.getCurrentPageIndex() != rs.getLastPageId() && rs.getRowCount() > pagecapacity)
  out.println("<font face=webdings color=#06b020>4</font><a href=editIpList.jsp?id="+sId+"&pageid="+rs.getLastPageId()+"&cityid="+cityId+"&orderColName="+orderColName+"&orderflag="+orderflag+">末页</a>");%>
</td>
<td></td>
<td></td>
</table>

	<%
}
else
	out.println("<br><br><br><br><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>No Info at This Id : "+ sId+"</font>");
%>
<input type=hidden name='id' value='<%=sId%>'>
<input type=hidden name='tabid' value='<%=sId%>'>
<input type=hidden name='cityid' value='<%=cityId%>'>
<input type=hidden name="distabname" value='<%=tabname%>'>
<input type=hidden name="cc_location" value='<%=cc_location%>'>
<input type=hidden name="orderColName" value='<%=orderColName%>'>
<input type=hidden name="orderflag" value='<%=tmpflag%>'>
<input type=hidden name='OptType' value='2'>
<input type=hidden name='pageid' value=<%=pageid%>>
<input type=hidden name='state' value='141'>
</form>
<script language="javascript">
  function inputclick()
  {
	  var type = <%=EntityType%>;
	  var sId = <%=sId%>;
	  if (type ==0)
	  {
		entity.action="editEntSelect.jsp?opttype=1";
	  }
	  else
	  {
		entity.action="editInsert.jsp";
	  }
	  if (sId==10)
	  {
		  entity.action="selectRoom.jsp?id=4";
		  //entity.action="typeSelect.jsp";
	  }
	  entity.submit();
  }
  function updateclick()
  {
	  
	  entity.action="editUpdate.jsp";
	  entity.submit();
  }
  function deleteclick()
  {
	  entity.action="editSave.jsp";
	  entity.submit();
  }
  function Del(form)
{
   var icheck=0;
   for (var i=0;i<form.elements.length;i++)
    {
      var e=form.elements[i];
      if (e.checked==true) icheck+=1;
    }
    if (icheck>0)
     {
      if ( confirm("你确定要删除这些项目吗？"))
      {
	   form.action="editSave.jsp";
       form.submit();
      }
     }
    else {
     alert("你必须选中要删除的项目");
    }
}
function CheckAll(form)
  {
  for (var i=0;i<form.elements.length;i++)
    {
    var e = form.elements[i];
    if (e.name != 'chkall')
       e.checked = form.chkall.checked;
    }
  }
function edit(form){
   var icheck=0;
   var id=0;
   var tempstate='<%=state%>';
   for (var i=0;i<form.elements.length;i++)
      {
       var e = form.elements[i];
       if(e.checked==true)
		{
			 icheck+=1;
        }
      }
   if(icheck==1) 
   {
	  if (tempstate==141)
	   {
		  form.action="editList.jsp?id=801";
	   }
	   else
	   {
		form.action="editIpSave.jsp";
	   }
	  form.submit();
   }
   if(icheck>1) alert("你一次只能修改一个项目！");
   if(icheck==0) alert("你必须选择一个IP地址申请单");
   }
    function gofilter()
{
	entity.action = "editIpList.jsp";
	entity.submit();
}
function  goorder(orderCol,orderflag)
{
	entity.action="editIpList.jsp?orderColName="+orderCol+"&orderflag="+orderflag;
	entity.submit();
}
</script>
</body>
</html>