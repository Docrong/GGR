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
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<body bgcolor="#eeeeee" text="#000000" class="listStyle">
<%
String Name = request.getParameter("cityname");
out.println("The City Name is : "+Name+"<br>");

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
	out.println("<br><table bgcolor=#dddddd width='100%'><tr>");
	for(int Col = 0; Col < SysVect.size(); Col ++)
	{
		SysColIndex = (syscolindex)SysVect.get(Col);
		out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+SysColIndex.getCc_name()+"</font></td>");
	}
	out.println("</tr>");

	String strSql = Entity.getStrSql(EntVect);	//�õ���ѯ��SQL���
	
	/******************  �����ҳ��ʾ  *************/

	VectorRS rs = new VectorRS();
	rs.setPageCapacity(30);						//ÿҳ��ʾ��¼���ݸ���

	rs.setRS(strSql);
	rs.setCurrentPageIndex(pageid);
	if(rs.getRowCount() >= 1)
	{
		for(int i = 1; i <= rs.getCurrentPageRowNum(); i++)
		{
			out.println("<tr bgcolor=#eeeeee>");
			int temp = 0;
			for(int a = 0; a < colNum + 1; a ++)
			{
				colData = (coldata)EntVect.get(temp);
				temp ++;
				if(colData.getCol_flag() == 1)
				{
					out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><a href='it.jsp?id="+rs.getString(a+1)+"'>"+rs.getString(a+2)+"</a></font></td>");
					a ++;
				}
				else
					out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+rs.getString(a+1)+"</font></td>");
			}
			rs.next();
			out.println("</tr>");
		}
	}
	out.println("</table>");
}
else
	out.println("<br><br><br><br><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>No Info at This Id : "+ sId+"</font>");
%>
</body>
</html>