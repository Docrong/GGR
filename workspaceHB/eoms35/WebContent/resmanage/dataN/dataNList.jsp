<%@page contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="mcs.common.db.*"%>
<%@page import ="com.boco.eoms.jbzl.bo.*"%>
<%@page import ="com.boco.eoms.common.util.*"%>
<%@include file="../power.jsp"%>
<%
/**
*@ E-DIS (�Ĵ�ʡ)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ ��ʾ��Դ�б���Ϣ
*@ version 1.0
**/
%>
<%
int  oper_id=0;
entityoperate Entity = new entityoperate();
String sId = null;
if(request.getParameter("id") != null)
	sId = request.getParameter("id");
else
	sId = "2";
String stroppId = Entity.getoperatorId(Integer.parseInt(sId));
List oppId = new ArrayList();
if (stroppId!=null)
	oppId = Entity.spitStr(stroppId,",");
for (int i=0;i<oppId.size();i++)
{
	oper_id = Integer.parseInt(oppId.get(0).toString());
}
//**********��Ȩ����*******
Vector cityVec = new Vector();
TawValidatePrivBO ValidatePrivBO = new TawValidatePrivBO();
cityVec = ValidatePrivBO.validatePriv(userId,oper_id,1);
//out.println("domain vec size is:::"+cityVec.size());
if (cityVec.size()==0)
{
	out.println("<script>alert('û��Ȩ��,����ϵͳ����Ա��ϵ"+"');</script>");
	return;
}
String cityId = null;
if(request.getParameter("cityid") != null)
	cityId = request.getParameter("cityid");
else
	cityId = "0";
String systemId = null;
if(request.getParameter("systemid") != null)
	systemId = request.getParameter("systemid");
else
	systemId = "0";
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
<title>��ʾ��Դ��Ϣ�б�</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body >
<!-- <br> <a href="editInsert.jsp?id=<%=sId%>">input </a>
 --><form action="editInsert" name="entity" method=POST >
<%
syscolindex SysColIndex = new syscolindex();

Vector EntVect = new Vector();
EntVect = Entity.getcolVec(sId,iFlag);	//�õ�ʵ��Map

//�õ�ʵ������
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
	tabname   = StaticMethod.dbNull2String(SysTab.getCc_name());
	cityFlag  = SysTab.getCi_cityflag();
	cc_location = StaticMethod.dbNull2String(SysTab.getCc_location());
}
out.println("<td class='table_title' align=left   width='70%'><font size=2>"+cc_location+"->"+tabname+"�б���ʾ</font></td>");
Vector SysVect = new Vector();
if(iFlag == 1)
	SysVect = Entity.getdiscol(sId);
else
	SysVect = Entity.getcolinfor(sId);

int colNum = EntVect.size();				//����Ϣ
if(colNum != 0)
{
	coldata colData = new coldata();
	/******************	��������	*****************/
	%>
	<br><table width='100%' border=0 cellspacing=1 class="table_show">
	<tr class="td_label">
	<td width=20></td>
	<%
	for(int Col = 1; Col < SysVect.size(); Col ++)
	{
		SysColIndex = (syscolindex)SysVect.get(Col);
		out.println("<td align=center><b><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><a href=javascript:goorder("+"'"+SysColIndex.getCc_code()+"',"+tmpflag+")>"+StaticMethod.dbNull2String(SysColIndex.getCc_name())+"</font></b>");
		if(SysColIndex.getCc_code().equals(orderColName))
		{
			if (orderflag == 1)
				out.println("<img src=../images/order2.gif border=0>");
			if (orderflag ==2)
				out.println("<img src=../images/order1.gif border=0>");
		}
		out.println("</td>");
	}
	%>
	</tr>
	<%
	String strSql = Entity.getDataNSql(EntVect,cityVec,systemId,orderColName,orderflag);	//�õ���ѯ��SQL���
	//out.println(strSql);
	/******************  �����ҳ��ʾ  *************/

	VectorRS rs = new VectorRS();
	rs.setPageCapacity(15);						//ÿҳ��ʾ��¼���ݸ���

	rs.setRS(strSql);
	rs.setCurrentPageIndex(pageid);
	if(rs.getRowCount() >= 1)
	{
		for(int i = 1; i <= rs.getCurrentPageRowNum(); i++)
		{
			out.println("<tr bgcolor="+((i%2 == 0)?"#eaeaea":"#eeeeee")+"><td><input type=checkbox name='pi_id' value="+rs.getString(1)+"></td>");
			int temp = 0;
			int tempFlag = 0;
			for(int a = 0; a < colNum; a ++)
			{
				colData = (coldata)EntVect.get(temp);
				temp ++;
				if(colData.getCol_flag() == 1)
				{
					out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><a href='editDetail.jsp?id="+rs.getString(a+1+tempFlag)+"&tabname="+colData.getTab_id()+"'>"+StaticMethod.dbNull2String(rs.getString(a+2+tempFlag))+"</a></font></td>");
					tempFlag = tempFlag + 1;
				}
				else
				{
					switch(a)
					{
						case 0:
							out.println("<td align=center><a href=editDetail.jsp?id="+rs.getString(a+1)+"&tabname="+sId+">");
							break;
						case 1:
							String tempvalue = StaticMethod.dbNull2String(rs.getString(a+1));
							//out.println("tempvalue is: "+tempvalue);
							out.println(tempvalue+"</a></td>");
							break;
						default:
							String tempValue=StaticMethod.dbNull2String(rs.getString(a+1+tempFlag));
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
<%if(rs.getRowCount() > 0)
	{
		%>
<!-- <td height=20 align=left width=60>
<input type=checkbox value="" name='chkall' onclick='CheckAll(entity)'><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>ȫѡ</font></input>
</td>
<%}%>
<td height=20 align=center width=60>
<a href="javascript:inputclick()"><img src="../images/button_add.gif" alt="����һ�������͵�ʵ��" border=0></a>
</td>
<td height=20 align=center width=60>
<a href="javascript:edit(entity)"><img src="../images/button_mot.gif" alt="�޸�ѡ�е�ʵ��" border=0></a>
</td>
 <td height=20 align=center width=60>
<a href="javascript:Del(entity)"><img src="../images/button_del.gif" alt="ɾ��ѡ�е�ʵ��" border=0></a></td>-->
<% if (rs.getPageCount()>0){%>
<td height=20 align=right><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>��<%=rs.getCurrentPageIndex()%>ҳ / ��<%=rs.getPageCount()%>ҳ</font></td>
<%}%>
<td height=20 align=center>
<%if(rs.getCurrentPageIndex() != rs.getFirstPageId())
				  out.println("<font face=webdings color=#06b020>4</font><a href=dataNList.jsp?id="+sId+"&pageid="+rs.getFirstPageId()+"&orderColName="+orderColName+"&orderflag="+orderflag+">��ҳ</a>");%>
				  <%if(rs.getCurrentPageIndex() != rs.getFirstPageId() && rs.getRowCount()>15)
				  out.println("<font face=webdings color=#06b020>4</font><a href=dataNList.jsp?id="+sId+"&pageid="+rs.getPreviewPageId()+"&orderColName="+orderColName+"&orderflag="+orderflag+">��һҳ</a>");%>
				  <%if(rs.getCurrentPageIndex() != rs.getLastPageId() && rs.getRowCount()>15)
				  out.println("<font face=webdings color=#06b020>4</font><a href=dataNList.jsp?id="+sId+"&pageid="+rs.getNextPageId()+"&orderColName="+orderColName+"&orderflag="+orderflag+">��һҳ</a>");%>
				  <%if(rs.getCurrentPageIndex() != rs.getLastPageId() && rs.getRowCount()>15)
				  out.println("<font face=webdings color=#06b020>4</font><a href=dataNList.jsp?id="+sId+"&pageid="+rs.getLastPageId()+"&orderColName="+orderColName+"&orderflag="+orderflag+">ĩҳ</a>");%>
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

</form>
<script language="javascript">
  function inputclick()
  {
	  entity.action="../edit/editInsert.jsp";
	  entity.submit();
  }
  function updateclick()
  {
	  entity.action="../edit/editUpdate.jsp";
	  entity.submit();
  }
  function deleteclick()
  {
	  entity.action="../edit/editSave.jsp";
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
      if ( confirm("��ȷ��Ҫɾ����Щ��Ŀ��"))
      {
	   form.action="editSave.jsp";
       form.submit();
      }
     }
    else {
    alert("�����ѡ��Ҫɾ������Ŀ");
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
      form.action="../edit/editUpdate.jsp";
	  form.submit();
   }
   if(icheck>1) alert("��һ��ֻ���޸�һ����Ŀ��");
   if(icheck==0) alert("�����ѡ��һ���޸ĵ���Ŀ");
   }
function  goorder(orderCol,orderflag)
{
	entity.action="dataNList.jsp?orderColName="+orderCol+"&orderflag="+orderflag;
	entity.submit();
}

</script>
</body>
</html>