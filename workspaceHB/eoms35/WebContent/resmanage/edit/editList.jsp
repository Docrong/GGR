<%@page contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="com.boco.eoms.resmanage.query.*"%>
<%@page import="com.boco.eoms.resmanage.operator.*"%>
<%@page import="mcs.common.db.*,com.boco.eoms.common.util.*"%>
<%@page import ="com.boco.eoms.jbzl.bo.*"%>
<%@include file="../power.jsp"%>
<%
/**
*@ EOMS_RES (EOMS��Դ����ģ��--����ά��)
*@ Copyright : (c) 2003.10.25
*@ Company : BOCO.
*@ ��ʾ��Դ�б���Ϣ
*@ author:wuzongxian
*@ version 1.0
**/
%>

<%
request.setCharacterEncoding("GBK");
String sId = null;
if(request.getParameter("id") != null)
	sId = request.getParameter("id");
else
	sId = "0";
int  oper_id=0;
entityoperate Entity = new entityoperate();
String stroppId = Entity.getoperatorId(Integer.parseInt(sId));
//modify by wuzongxian 2004-02-17
/*if (stroppId!=null)
   oper_id = Integer.parseInt(stroppId);
   */

List oppId = new ArrayList();
if (stroppId!=null)
	oppId = Entity.spitStr(stroppId,",");
oper_id = Integer.parseInt(oppId.get(0).toString());

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

//************************
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
<title>��ʾ��Դ��Ϣ�б�</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body>
<!-- <br> <a href="editInsert.jsp?id=<%=sId%>">input </a>
 --><form action="editInsert" name="entity" method=POST >
<table width='100%' border=0 cellspacing=1>
<%
//���뵱ǰλ��
syscolindex SysColIndex = new syscolindex();

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
out.println("<tr>");
out.println("<td class='table_title' align='left' width='70%'>"+cc_location+"->"+tabname+"�б���ʾ</td>");
/*if (validatePriv && cityFlag==0)
{
  queryRes qre = new queryRes();
  Vector ctV = new Vector();
  cityClass ct = new cityClass();
  ctV = qre.getCity(0);
 out.println("<td align=right width='25%'>����ѡ��һ������:</td><td width='25%' align=left><select name=cityid onchange='gofilter()'><option value=0>--ȫ��--</option>");
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
EntVect = Entity.getcolVec(sId,iFlag);	//�õ�ʵ��Map

//out.println("Entity name :"+Entity.getCc_name());
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
	<br>
	<table border="0" width='100%' cellspacing="1" cellpadding="1" class="table_show" align=center>
	<tr class="td_label">
	<%
	/*if (powerflag == 1)
	{
		out.println("<td width=20></td>");
	}*/
	out.println("<td width=20></td>");
	for(int Col = 1; Col < SysVect.size(); Col ++)
	{
		SysColIndex = (syscolindex)SysVect.get(Col);
		out.println("<td align=center><b><a href=javascript:goorder("+"'"+SysColIndex.getCc_code()+"',"+tmpflag+")>"+StaticMethod.dbNull2String(SysColIndex.getCc_name())+"</a></b>");
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
	String state=null;
    if (request.getParameter("state")!=null)
	{
		state=request.getParameter("state");
	}
	else
	{
		state="0";
	}
	String strSql = Entity.getStrSql(EntVect,cityVec,orderColName,orderflag,state);

	//�õ���ѯ��SQL���
	//out.println(strSql);
	/******************  �����ҳ��ʾ  *************/

	VectorRS rs = new VectorRS();
	int pagecapacity = 15;
	rs.setPageCapacity(pagecapacity);						//ÿҳ��ʾ��¼���ݸ���

	rs.setRS(strSql);
	rs.setCurrentPageIndex(pageid);
	//out.println("row count is: "+rs.getRowCount());
	//out.println(StaticMethod.dbNull2String(rs.getString(5)));
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
						out.println("<td align=center>"+StaticMethod.dbNull2String(rs.getString(a+2+tempFlag))+"</td>");
					}
					else
					{
						String templink="";
						if (colData.getTab_id()!=null)
						{
							templink ="<a href='editDetail.jsp?id="+rs.getString(a+1+tempFlag)+"&tabname="+colData.getTab_id()+"'>"+StaticMethod.dbNull2String(rs.getString(a+2+tempFlag))+"</a>";
							out.println("<td align=center>"+templink+"</td>");
						}else{
							out.println("<td align=center>"+StaticMethod.dbNull2String(rs.getString(a+2+tempFlag))+"</td>");
							//out.println("<td align=center>"+templink+"</td>");
							//out.println(StaticMethod.dbNull2String(rs.getString(a+2+tempFlag)));
							//out.println("*******");						
						}

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
								out.println("<td align=center>");
							}
							else
							{
								out.println("<td align=center><a href=editDetail.jsp?id="+rs.getString(a+1)+"&tabname="+sId+">");
							}
							break;
						case 1:
							//modify 2003-07-14 by wuzongxian
							String tempvalue = StaticMethod.dbNull2String(rs.getString(a+1));
							//out.println("tempvalue is: "+tempvalue);
							out.println(tempvalue+"</a></td>");
							break;
						default:
							String tempValue=StaticMethod.dbNull2String(rs.getString(a+1+tempFlag));
							out.println("<td align=center>"+tempValue+"</td>");
							//out.println("<td align=center>"+StaticMethod.dbNull2String(rs.getString(5))+"</td>");				
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
<td height=10 align=center width=60>
<%
	if(rs.getRowCount() != 0)
	{
		%>
<input type=checkbox value="" name='chkall' onclick='CheckAll(entity)'>ȫѡ</input>
<%
	}
%>
</td>
<td height=20 align=center width=40></td>
<td height=20 align=center width=60>
<input type="button" name="bt_001" value="����" alt="����һ�������͵�ʵ��" onclick="javascript:inputclick()" class="clsbtn2">
</td>
<td height=20 align=center width=60>
<input type="button" name="bt_001" value="�޸�" alt="�޸�ѡ�е�ʵ��" onclick="javascript:edit(entity)" class="clsbtn2">
</td>
<td height=20 align=center width=60>
<input type="button" name="bt_001" value="ɾ��" alt="ɾ��ѡ�е�ʵ��" onclick="javascript:Del(entity)" class="clsbtn2"></td>

<td height=20 align=center width=60>
<input type="button" name="bt_001" value="��������" alt="��������" onclick="javascript:download()" class="clsbtn2"></td>
<td height=20 align=center width=60>
<input type="button" name="bt_001" value="��������" alt="��������" onclick="javascript:upload('1')" class="clsbtn2"></td>
<td height=20 align=center width=60>
<input type="button" name="bt_001" value="���µ���" alt="���µ���" onclick="javascript:upload('2')" class="clsbtn2"></td>
<td height=20 align=center width=60>

<!-- <a href="javascript:download()"><img src="../images/button_down.gif" alt="��������" border=0></a>
</td>
<td height=20 align=center width=60>
<a href="javascript:upload('1')"><img src="../images/button_update.gif" alt="��������" border=0></a>
</td>
<td height=20 align=center width=60>
<a href="javascript:upload('2')"><img src="../images/button_update1.gif" alt="���µ���" border=0></a>
</td> -->
<% if (rs.getPageCount()>0){%>
<td height=20 align=right>��<%=rs.getCurrentPageIndex()%>ҳ / ��<%=rs.getPageCount()%>ҳ</td>
<%}%>
<td height=20 align=center>
<%if(rs.getCurrentPageIndex() != rs.getFirstPageId())
out.println("<font face=webdings color=#06b020>4</font><a href=editList.jsp?id="+sId+"&pageid="+rs.getFirstPageId()+"&cityid="+cityId+"&orderColName="+orderColName+"&orderflag="+orderflag+">��ҳ</a>");%>
 <%if(rs.getCurrentPageIndex() != rs.getFirstPageId())
 out.println("<font face=webdings color=#06b020>4</font><a href=editList.jsp?id="+sId+"&pageid="+rs.getPreviewPageId()+"&cityid="+cityId+"&orderColName="+orderColName+"&orderflag="+orderflag+">��һҳ</a>");%>
 <%if(rs.getCurrentPageIndex() != rs.getLastPageId() && rs.getRowCount() > pagecapacity)
 out.println("<font face=webdings color=#06b020>4</font><a href=editList.jsp?id="+sId+"&pageid="+rs.getNextPageId()+"&cityid="+cityId+"&orderColName="+orderColName+"&orderflag="+orderflag+">��һҳ</a>");%>
 <%if(rs.getCurrentPageIndex() != rs.getLastPageId() && rs.getRowCount() > pagecapacity)
  out.println("<font face=webdings color=#06b020>4</font><a href=editList.jsp?id="+sId+"&pageid="+rs.getLastPageId()+"&cityid="+cityId+"&orderColName="+orderColName+"&orderflag="+orderflag+">ĩҳ</a>");%>
</td>
<td></td>
<td></td>
</table>

	<%
}
else
	out.println("<br><br><br><br>�޴����ݱ� : "+ sId+"");
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
		  //entity.action="selectRoom.jsp?id=4";
		  entity.action="typeSelect.jsp";
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
	  var type = <%=EntityType%>;
	  if (type==0)
	  {
		form.action="editLogUpdate.jsp";
	  }
	   else
	   {
		form.action="editUpdate.jsp";
	   }
	  form.submit();
   }
   if(icheck>1) alert("��һ��ֻ���޸�һ����Ŀ��");
   if(icheck==0) alert("�����ѡ��һ���޸ĵ���Ŀ");
   }
    function gofilter()
{
	entity.action = "editList.jsp";
	entity.submit();
}
function  goorder(orderCol,orderflag)
{
	entity.action="editList.jsp?orderColName="+orderCol+"&orderflag="+orderflag;
	entity.submit();
}
function download()
  {
        entity.action="editDown.jsp?sId="+<%=sId%>+"&cityId=" +<%=cityId%>;
        entity.submit();
  }
  function upload(importtype)
  {      
         var linkUrl="editUp.jsp?sId="+<%=sId%>+"&cityId=" +<%=cityId%>+"&importtype="+importtype;
         entity.action=window.open(linkUrl,"�ϴ��ļ�ѡ��","height=150,width=400,top=450,status=yes");
   }         
</script>
</body>
</html>