<%@page contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="com.boco.eoms.common.util.*"%>
<%@page import="mcs.common.db.*"%>
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
String cityId=null;
if(request.getParameter("cityid") != null)
		cityId = request.getParameter("cityid");
	else
		cityId = ug.getCity() + "";
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
if(request.getParameter("tabname") != null)
	sId = request.getParameter("tabname");
else
	sId = "2";
String devId = null;
if(request.getParameter("id") != null)
	devId = request.getParameter("id");
else
	devId = "0";
String devtype = null;
if(request.getParameter("devtype") != null)
	devtype = request.getParameter("devtype");
else
	devtype = "0";
%>
<html>
<head>
<title>��ʾ��Դ��Ϣ�б�</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<body bgcolor="#eeeeee" text="#000000" class="listStyle">
<!-- <br> <a href="editInsert.jsp?id=<%=sId%>">input </a>
 --><form action="editInsert" name="entity" method=POST >
<%
entityoperate Entity = new entityoperate();
syscolindex SysColIndex = new syscolindex();

Vector EntVect = new Vector();
EntVect = Entity.getcolVec(sId,iFlag);	//�õ�ʵ��Map

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
	<br><table bgcolor=#dddddd width='100%'>
	<tr>
	<%
	for(int Col = 1; Col < SysVect.size(); Col ++)
	{
		SysColIndex = (syscolindex)SysVect.get(Col);
		out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+StaticMethod.dbNull2String(SysColIndex.getCc_name())+"</font></td>");
	}
	%>
	</tr>
	<%

	String strSql = Entity.getLogSql(EntVect,devtype,devId,cityId);	//�õ���ѯ��SQL���
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
			//out.println("<tr bgcolor=#eeeeee><td><input type=checkbox name='pi_id' value="+rs.getString(1)+"></td>");
			out.println("<tr bgcolor=#eeeeee>");
			int temp = 0;
			int tempFlag = 0;
			for(int a = 0; a < colNum; a ++)
			{
				colData = (coldata)EntVect.get(temp);
				temp ++;
				if(colData.getCol_flag() == 1)
				{
					out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><a href='editDetail.jsp?id="+rs.getString(a+1+tempFlag)+"&tabname="+colData.getTab_id()+"'>"+StaticMethod.dbNull2String(rs.getString(a+2+tempFlag))+"</a></font></td>");
					//out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><a href='displayImage.jsp?path="+rs.getString(a+1+tempFlag)+"'</a></font></td>");
					tempFlag = tempFlag + 1;
				}
				else
				{
					switch(a)
					{
						case 0:
							//out.println("<td align=center><a href=editDetail.jsp?id="+rs.getString(a+1)+"&tabname="+sId+">");
							//out.println("<td align=center><a href=displayImage.jsp?path=../"+rs.getString(a+2)+">");
							out.println("<td align=center><a href="+request.getContextPath()+"/"+rs.getString(a+2)+">");			
							//out.println("********"+rs.getString(a+2)+"********");
							break;
						case 1:
							out.println(StaticMethod.dbNull2String(rs.getString(a+1))+"</a></td>");
							break;
						default:
							out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+StaticMethod.dbNull2String(rs.getString(a+1+tempFlag))+"</font></td>");
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
<%if (rs.getRowCount()>0){%>
<tr>
<td height=20 align=right><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>��<%=rs.getCurrentPageIndex()%>ҳ / ��<%=rs.getPageCount()%>ҳ</font></td>
<%}%>
<td height=20 align=center>
<%if(rs.getCurrentPageIndex() != rs.getFirstPageId())
				  out.println("<font face=webdings color=#06b020>4</font><a href=viewImage.jsp?tabname="+sId+"&devtype="+devtype+"&id="+devId+"&pageid="+rs.getFirstPageId()+"&cityid="+cityId+">��ҳ</a>");%>
				  <%if(rs.getCurrentPageIndex() != rs.getFirstPageId())
				  out.println("<font face=webdings color=#06b020>4</font><a href=viewImage.jsp?tabname="+sId+"&devtype="+devtype+"&id="+devId+"&pageid="+rs.getPreviewPageId()+"&cityid="+cityId+">��һҳ</a>");%>
				  <%if(rs.getCurrentPageIndex() != rs.getLastPageId() && rs.getRowCount() > 15)
				  out.println("<font face=webdings color=#06b020>4</font><a href=viewImage.jsp?tabname="+sId+"&devtype="+devtype+"&id="+devId+"&pageid="+rs.getNextPageId()+"&cityid="+cityId+">��һҳ</a>");%>
				  <%if(rs.getCurrentPageIndex() != rs.getLastPageId() && rs.getRowCount() > 15)
				  out.println("<font face=webdings color=#06b020>4</font><a href=viewImage.jsp?tabname="+sId+"&devtype="+devtype+"&id="+devId+"&pageid="+rs.getLastPageId()+"&cityid="+cityId+">ĩҳ</a>");%>
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
<input type=hidden name='devid' value='<%=devId%>'>
<input type=hidden name='devtype' value='<%=devtype%>'>
<!-- <input type=button name=insert value='����' onclick="return inputclick()">
<input type=button name=update value='�޸�' onclick="return updateclick()"> -->
</form>
<script language="javascript">
  function inputclick()
  {
	  entity.action="editInsert.jsp";
	  entity.submit();
  }
  function updateclick()
  {
	  entity.action="editUpdate.jsp";
	  entity.submit();
  }
   function closeW()
{
	window.close();
}
</script>
</body>
<p align=center><font size=2><a href="javascript:closeW()">�ر�</a></font></p>
</html>
