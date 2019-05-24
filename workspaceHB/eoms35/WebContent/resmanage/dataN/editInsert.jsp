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
	String sId = null;
	if(request.getParameter("id") != null)
		sId = request.getParameter("id");
	else
		sId = "2";

	String ErrMsg = null;
    if(request.getParameter("ErrMsg") != null)
		ErrMsg = request.getParameter("ErrMsg");
	if (ErrMsg!=null)
	{
	  out.println("<script>alert('"+ErrMsg+"');history.go(-2)</script>");
	}
%>
<html>
<head>

<script language=javascript>
function formsubmit()
{
	editform.submit();
}
function clearinput()
{
	editform.reset();
}
</script>

<title>Ìí¼Ó×ÊÔ´</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
<link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body bgcolor="#eeeeee" text="#000000" class="content14">
<form name="editform" action="editSave.jsp" method=post>
<%
	entityoperate Entity = new entityoperate();
	syscolindex SysColIndex = new syscolindex();

	Vector SysVect = new Vector();

	SysVect = Entity.getcolinfor(sId);

	int colNum = SysVect.size();				//ÁÐÐÅÏ¢

	if(colNum != 0)
	{
		coldata colData = new coldata();
		/******************	¹¹ÔìÄÚÈÝ	*****************/
		out.println("<br><table bgcolor=#dddddd border=0 cellspacing=1 width='100%'>");
		int colorCounter = 0;
		for(int Col = 1; Col < SysVect.size(); Col ++)
		{
			SysColIndex = (syscolindex)SysVect.get(Col);

			int ref_flag = SysColIndex.getCi_ref_flag();
            int ass_flag = SysColIndex.getCi_ass_flag();
			if (ass_flag!=1 && ass_flag!=2)
			{
				colorCounter ++;
				out.println("<tr bgcolor="+((colorCounter%2 == 0)?"#eaeaea":"#eeeeee")+">");
				out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+SysColIndex.getCc_name()+"</font></td>");
			}
			//println  input text
			switch(ref_flag)
			{
				case 0:
					out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+"<input name="+SysColIndex.getCc_code()+"></input>"+"</font></td>");
					break;
				//drop down control
				case 1:
				   out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>");
				   out.println("<select name="+SysColIndex.getCc_code()+">");
				
				   refcoldata RefColData = new refcoldata();

				   Vector RefColVec = new Vector();

				   //µÃµ½²Î¿¼ÁÐµÄid,nameµÄvalue
				   RefColVec = Entity.getrefColValue(Integer.toString(SysColIndex.getPi_id()),"0");
				   for (int i=0;i<RefColVec.size();i++)
					{
					   int  idvalue   = ((refcoldata)RefColVec.elementAt(i)).getPi_idvalue();
					   String namevalue = ((refcoldata)RefColVec.elementAt(i)).getCc_namevalue();
					   out.println("<option  value="+idvalue+">"+namevalue+"</option>");
					}
				   out.println("</select>");
				   out.println("</font></td>");
				   break;
				case 2:
					out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+"<textarea name="+SysColIndex.getCc_code()+"></textarea>"+"</font></td>");
					break;
				//ÍØÆËÍ¼µÄÁ¬½Ó
				/*case 3:
					String topoName = Entity.getTopoName(Integer.toString(SysColIndex.getPi_id()));
				    if (topoName==null)
						topoName = "";
                    out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+"<input type=hidden name="+SysColIndex.getCc_code()+" value="+topoName+"></input>"+"</font></td>");
					break;
					
			    //ÎÄ¼þºÍÍ¼Æ¬
				case 4:
					break;
				//¼ÇÂ¼Çé¿ö
				case 5:
					out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+"<input type=hidden name="+SysColIndex.getCc_code()+" value='' "+"></input>"+"</font></td>");
					break;*/
				default:
					break;
			}
			if (ass_flag!=1 && ass_flag!=2) out.println("</tr>");
		}
	}
	else
		out.println("<br><br><br><br><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>No Info at This Id : "+ sId+"</font>");
%>
</table>
<br>
<table width='100%'>
	<tr>
		<td align=center width='100%'>
		<table>
			<tr>
				<td width=60 align=center><a href="javascript:formsubmit()"><img src="../images/button_submit.gif" border=0></a></td>
				<td width=20></td>
				<td width=60 align=center><a href="javascript:clearinput()"><img src="../images/button_cancel.gif" border=0></a></td>
			</tr>
		</table>
		</td>
	</tr>
<input type="hidden" name="id" value=<%=sId%>></input>
<input type="hidden" name="OptType" value="1"></input>
</table>
</form>
</body>
</html>