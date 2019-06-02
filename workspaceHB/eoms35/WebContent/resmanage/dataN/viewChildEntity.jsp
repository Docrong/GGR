<%@page contentType="text/html;charset=ISO8859_1"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="mcs.common.db.*"%>
<%
/**
*@ E-DIS (ËÄ´¨Ê¡)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ ÏÔÊ¾×ÊÔ´ÁÐ±íÐÅÏ¢
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
String pi_id = null;
if(request.getParameter("id") != null)
	pi_id = request.getParameter("id");

String sId = null;
if(request.getParameter("tabname") != null)
	sId = request.getParameter("tabname");
else
	sId = "2";
%>
<html>
<head>
<title>ÏÔÊ¾×ÊÔ´ÐÅÏ¢ÁÐ±í</title>
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
EntVect = Entity.getcolVec(sId,iFlag);	//µÃµ½ÊµÌåMap

//out.println("Entity name :"+Entity.getCc_name());
Vector SysVect = new Vector();
if(iFlag == 1)
	SysVect = Entity.getdiscol(sId);
else
	SysVect = Entity.getcolinfor(sId);

int colNum = EntVect.size();				//ÁÐÐÅÏ¢
if(colNum != 0)
{
	coldata colData = new coldata();
	/******************	¹¹ÔìÄÚÈÝ	*****************/
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

	String strSql = Entity.getChildStrSql(EntVect,pi_id);	//µÃµ½²éÑ¯µÄSQLÓï¾ä
	//out.println(strSql);
	/******************  ¹¹Ôì·ÖÒ³ÏÔÊ¾  *************/

	VectorRS rs = new VectorRS();
	rs.setPageCapacity(15);						//Ã¿Ò³ÏÔÊ¾¼ÇÂ¼Êý¾Ý¸öÊý

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
					out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'><a href='editDetail.jsp?id="+rs.getString(a+1+tempFlag)+"&tabname="+colData.getTab_id()+"'>"+rs.getString(a+2+tempFlag)+"</a></font></td>");
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
							out.println(rs.getString(a+1)+"</a></td>");
							break;
						default:
							out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+rs.getString(a+1+tempFlag)+"</font></td>");
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
<td height=20 align=right><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>µÚ<%=rs.getCurrentPageIndex()%>Ò³ / ¹²<%=rs.getPageCount()%>Ò³</font></td>
<td height=20 align=center>
<%if(rs.getCurrentPageIndex() != rs.getFirstPageId())
				  out.println("<font face=webdings color=#06b020>4</font><a href=viewChildEntity.jsp?tabname="+sId+"&id="+pi_id+"&pageid="+rs.getFirstPageId()+">Ê×Ò³</a>");%>
				  <%if(rs.getCurrentPageIndex() != rs.getFirstPageId())
				  out.println("<font face=webdings color=#06b020>4</font><a href=viewChildEntity.jsp?tabname="+sId+"&id="+pi_id+"&pageid="+rs.getPreviewPageId()+">ÉÏÒ»Ò³</a>");%>
				  <%if(rs.getCurrentPageIndex() != rs.getLastPageId())
				  out.println("<font face=webdings color=#06b020>4</font><a href=viewChildEntity.jsp?tabname="+sId+"&id="+pi_id+"&pageid="+rs.getNextPageId()+">ÏÂÒ»Ò³</a>");%>
				  <%if(rs.getCurrentPageIndex() != rs.getLastPageId())
				  out.println("<font face=webdings color=#06b020>4</font><a href=viewChildEntity.jsp?tabname="+sId+"&id="+pi_id+"&pageid="+rs.getLastPageId()+">Ä©Ò³</a>");%>
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
<!-- <input type=button name=insert value='Ôö¼Ó' onclick="return inputclick()">
<input type=button name=update value='ÐÞ¸Ä' onclick="return edit(entity)">
<input type=button name=delete value='É¾³ý' onclick="return Del(entity)"> -->
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
      if ( confirm("ÄãÈ·¶¨ÒªÉ¾³ýÕâÐ©ÏîÄ¿Âð£¿"))
      {
	   form.action="editSave.jsp";
       form.submit();
      }
     }
    else {
    alert("Äã±ØÐëÑ¡ÖÐÒªÉ¾³ýµÄÏîÄ¿");
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
      form.action="editUpdate.jsp";
	  form.submit();
   }
   if(icheck>1) alert("ÄãÒ»´ÎÖ»ÄÜÐÞ¸ÄÒ»¸öÏîÄ¿£¡");
   if(icheck==0) alert("Äã±ØÐëÑ¡ÔñÒ»¸öÐÞ¸ÄµÄÏîÄ¿");
   }
</script>
</body>
</html>