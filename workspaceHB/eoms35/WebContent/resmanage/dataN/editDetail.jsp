<%@page contentType="text/html;charset=ISO8859_1"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="mcs.common.db.*"%>
<%
/**
*@ E-DIS (四川省)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ 显示资源列表信息
*@ version 1.0
**/
%>
<%
	String tabIndex = null;
	String pi_id    = null;
	if(request.getParameter("id") != null)
		pi_id = request.getParameter("id");
	if(request.getParameter("tabname") != null)
		tabIndex = request.getParameter("tabname");
%>
<SCRIPT language=JavaScript>

<!--
function call(htmlurl){
var newwin=window.open(htmlurl,"newsssWin_searchout","top=150,left=150,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,width=900,height=600");
newwin.focus();
return false;
}

function prodWin(file,window1){
	MsgWin=open(file,window1,'scrollbars=1,toolbar=0, resizable=no,width=560,height=400,ScreenX=30,screenY=30,top=30,left=30');
	MsgWin.window.focus();
}
//-->
</SCRIPT>
<html>
<head>
<title>添加资</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
<link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body bgcolor="#eeeeee" text="#000000" class="content14">
<form name="editform" action="editSave.jsp" method=post>
<%
	entityoperate Entity = new entityoperate();
	syscolindex SysColIndex = new syscolindex();

	Vector SysVect = new Vector();

	SysVect = Entity.getcolinfor(tabIndex);

	int colNum = SysVect.size();				//列信息

	if(colNum != 0)
	{
		/******************	构造显示的详细信息	*****************/
		out.println("<br><table  bgcolor=#dddddd width='100%' border=0 cellspacing=0><tr><td>");
		out.println("<table bgcolor=#dddddd width='100%' border=0 cellspacing=1><tr><td align=center width='40%'><font size=2><b>属性</b></font></td><td align=center><font size=2><b>值</b></font></td>");
		//<td align=center><font size=2><b>属性</b></font></td><td align=center><font size=2><b>值</b></font></td></tr>"

		for(int Col = 1; Col < SysVect.size(); Col ++)
		{
			SysColIndex = (syscolindex)SysVect.get(Col);
			String cc_name = SysColIndex.getCc_name();
			int ref_flag = SysColIndex.getCi_ref_flag();
			int ass_flag = SysColIndex.getCi_ass_flag();
            //========得到列的值-----
			String colValue = Entity.getColValue(tabIndex,SysColIndex.getCc_code(),pi_id);
			if (colValue==null)
			{
				colValue = "暂缺";
			}
			/*
			if((Col-1)%2 == 0)
			{*/
				out.println("<tr bgcolor="+((Col%2 == 0)?"eaeaea":"eeeeee")+">");
			//}
			out.println("<td align=center width='40%'><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+SysColIndex.getCc_name()+"</font></td>");
			//out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+SysColIndex.getCc_name()+"</font></td>");
			
			switch(ref_flag)
			{
				case 0:
					out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+colValue+"</font></td>");
					break;
				case 1:
				   out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>");
				
				   refcoldata RefColData = new refcoldata();

				   Vector RefColVec = new Vector();

				   //得到参考列的id,name的value
				   /*	Only One Value in Vector	*/
				   //out.println(SysColIndex.getPi_id());
				   //out.println(pi_id);
				   RefColVec = Entity.getrefColValue(Integer.toString(SysColIndex.getPi_id()),colValue);
				   //out.println("RefColVec size is ;"+RefColVec.size());
				   for (int i=0;i<RefColVec.size();i++)
					{
					   int  idvalue   = ((refcoldata)RefColVec.elementAt(i)).getPi_idvalue();

					   String namevalue = ((refcoldata)RefColVec.elementAt(i)).getCc_namevalue();
					   //是否输出连接的标志
					   int ci_link_flag = ((refcoldata)RefColVec.elementAt(i)).getCi_link_flag();
					   if (ci_link_flag ==1)
						{
						   String RefTableId  =Entity.getRefTableId(Integer.toString(SysColIndex.getPi_id()));
						   out.println("<a href=editDetail.jsp?id="+colValue+"&&tabname="+RefTableId+" target=_blank>"+namevalue+"</a>");
						}
						else
						{
							out.println(namevalue);
						}
					}
				   out.println("</font></td>");
				   break;
				default:
					break;
			}

			String linkJsp = Entity.getLinkJsp(Integer.toString(SysColIndex.getPi_id()));
			if (ass_flag == 1) //普通辅助信息
			{
				out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial,  Helvetica, sans-serif'><a href="+"../"+ linkJsp +pi_id+"  target=_blank>查看"+"</a></font></td>");
				//out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial,  Helvetica, sans-serif'><a href=javascript:prodWin('../"+linkJsp+pi_id+"','prodpop');>查看"+"</a></font></td>");
			}

			else if(ass_flag == 2) //是拓扑图
			{
				
				out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial,  Helvetica, sans-serif'><a href="+"../"+ linkJsp +pi_id+"&title="+cc_name+" onClick='return call(this.href);' target=_blank>查看"+"</a></font></td>");
				//out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial,  Helvetica, sans-serif'><a href=javascript:prodWin('../"+linkJsp+pi_id+"','prodpop');>查看"+"</a></font></td>");
			}

			//if(Col%2 == 0)
				out.println("</tr>");
		}
		out.println("</table></td></tr></table>");
	}
	else
		out.println("<br><br><br><br><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>No Info at This Id : "+ tabIndex+"</font>");
%>
</form>
</body>
</html>
