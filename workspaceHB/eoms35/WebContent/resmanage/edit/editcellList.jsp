<%@page contentType="text/html;charset=ISO8859_1"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="mcs.common.db.*"%>
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
request.setCharacterEncoding("GBK");
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

String sId = null;
if(request.getParameter("id") != null)
	sId = request.getParameter("id");
else
	sId = "2";
%>
<html>
<head>
<title>��ʾ��Դ��Ϣ�б�</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body bgcolor="#eeeeee" text="#000000" class="listStyle">
<!-- <br> <a href="editInsert.jsp?id=<%=sId%>">input </a>
 --><form action="editInsert" name="entity" method=POST >
<%
entityoperate Entity = new entityoperate();
syscolindex SysColIndex = new syscolindex();

//�õ�ʵ������
systabindex SysTab = new systabindex();
Vector TabVect = new Vector();
TabVect = Entity.getTabinfor(sId);
int EntityType=0;
for(int i=0;i<TabVect.size();i++)
{
	SysTab = (systabindex)TabVect.get(i);
	EntityType = SysTab.getFi_type();
}

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
	<br><table bgcolor=#dddddd width='100%' border=0 cellspacing=1>
	<tr><td width=20></td>
	<%
	for(int Col = 1; Col < SysVect.size(); Col ++)
	{
		SysColIndex = (syscolindex)SysVect.get(Col);
		out.println("<td align=center><b><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+SysColIndex.getCc_name()+"</font></b></td>");
	}
	%>
	</tr>
	<%

	String strSql = Entity.getStrSql(EntVect,cityId);	//�õ���ѯ��SQL���
	//out.println(strSql);
	/******************  �����ҳ��ʾ  *************/

	VectorRS rs = new VectorRS();
	rs.setPageCapacity(15);						//ÿҳ��ʾ��¼���ݸ���

	rs.setRS(strSql);
	rs.setCurrentPageIndex(pageid);
	//out.println("row count is: "+rs.getRowCount());
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
					if (EntityType ==0)
					{
						out.println("<td align=center width=100><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+rs.getString(a+2+tempFlag)+"</font></td>");
					}
					else
					{
						out.println("<td align=center width=100><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><a href='editDetail.jsp?id="+rs.getString(a+1+tempFlag)+"&tabname="+colData.getTab_id()+"'>"+rs.getString(a+2+tempFlag)+"</a></font></td>");
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
							out.println(rs.getString(a+1)+"</font></a></td>");
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
<a href="javascript:inputclick()"><img src="../images/button_add.gif" alt="����һ�������͵�ʵ��" border=0></a>
</td>
<td height=20 align=center width=60>
<a href="javascript:edit(entity)"><img src="../images/button_mot.gif" alt="�޸�ѡ�е�ʵ��" border=0></a>
</td>
<td height=20 align=center width=60>
<a href="javascript:Del(entity)"><img src="../images/button_del.gif" alt="ɾ��ѡ�е�ʵ��" border=0></a></td>
<td height=20 align=center width=60>
<%
	if(rs.getRowCount() != 0)
	{
		%>
<input type=checkbox value="" name='chkall' onclick='CheckAll(entity)'><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>ȫѡ</font></input>
<%
	}
%>
</td>
<td height=20 align=right><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>��<%=rs.getCurrentPageIndex()%>ҳ / ��<%=rs.getPageCount()%>ҳ</font></td>
<td height=20 align=center>
<%if(rs.getCurrentPageIndex() != rs.getFirstPageId())
				  out.println("<font face=webdings color=#06b020>4</font><a href=editList.jsp?id="+sId+"&pageid="+rs.getFirstPageId()+">��ҳ</a>");%>
				  <%if(rs.getCurrentPageIndex() != rs.getFirstPageId())
				  out.println("<font face=webdings color=#06b020>4</font><a href=editList.jsp?id="+sId+"&pageid="+rs.getPreviewPageId()+">��һҳ</a>");%>
				  <%if(rs.getCurrentPageIndex() != rs.getLastPageId() && rs.getRowCount() > 15)
				  out.println("<font face=webdings color=#06b020>4</font><a href=editList.jsp?id="+sId+"&pageid="+rs.getNextPageId()+">��һҳ</a>");%>
				  <%if(rs.getCurrentPageIndex() != rs.getLastPageId() && rs.getRowCount() > 15)
				  out.println("<font face=webdings color=#06b020>4</font><a href=editList.jsp?id="+sId+"&pageid="+rs.getLastPageId()+">ĩҳ</a>");%>
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
<input type=hidden name='OptType' value='2'>

</form>
<script language="javascript">
  function inputclick()
  {
	  var type = <%=EntityType%>;
	  if (type ==0)
	  {
		entity.action="editEntSelect.jsp?opttype=1";
	  }
	  else
	  {
		entity.action="editInsert.jsp";
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
	  form.action="editLogUpdate.jsp";
	  form.submit();
   }
   if(icheck>1) alert("��һ��ֻ���޸�һ����Ŀ��");
   if(icheck==0) alert("�����ѡ��һ���޸ĵ���Ŀ");
   }
</script>
</body>
</html>