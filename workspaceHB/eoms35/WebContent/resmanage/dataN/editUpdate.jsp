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
	String sId = null;
	if(request.getParameter("id") != null)
		sId = request.getParameter("id");
	else
		sId = "2";
	String pi_id = null;
    if(request.getParameter("pi_id") != null)
		pi_id = request.getParameter("pi_id");
%>
<html>
<head>
<title>�޸���Դ</title>
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

	int colNum = SysVect.size();				//����Ϣ

	if(colNum != 0)
	{
		coldata colData = new coldata();
		
		out.println("<br><table bgcolor=#dddddd width='100%' border=0 cellspacing=1>");
		int colorCounter = 0;
		for(int Col = 0; Col < SysVect.size(); Col ++)
		{
			SysColIndex = (syscolindex)SysVect.get(Col);

			int ref_flag = SysColIndex.getCi_ref_flag();
            int ass_flag = SysColIndex.getCi_ass_flag();
            //========�õ��е�ֵ-----
			String colValue = Entity.getColValue(sId,SysColIndex.getCc_code(),pi_id);
			if (colValue==null)
				colValue="";

			if (Col==0)
			{
				out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+"<input name="+SysColIndex.getCc_code()+"   type=hidden value="+colValue+"></input>"+"</font></td>");
			}
			else
			{
			if (ass_flag!=1)
			{
				colorCounter ++;
				out.println("<tr bgcolor="+((colorCounter%2 == 0)?"#eaeaea":"#eeeeee")+">");
				out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+SysColIndex.getCc_name()+"</font></td>");
			}
			//println  input text
			switch(ref_flag)
			{
				case 0:
					out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+"<input name="+SysColIndex.getCc_code()+"   value="+colValue+"></input>"+"</font></td>");
					break;
				//drop down control
				case 1:
				   out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>");
				   out.println("<select name="+SysColIndex.getCc_code()+">");
				
				   refcoldata RefColData = new refcoldata();

				   Vector RefColVec = new Vector();
                   Vector RefColVecTemp = new Vector();


				   //�õ��ο��е�id,name��value
				  RefColVec = Entity.getrefColValue(Integer.toString(SysColIndex.getPi_id()),"0");
                  
				   RefColVecTemp = Entity.getrefColValue(Integer.toString(SysColIndex.getPi_id()),colValue);
				  
			      out.println("pi id is : " +SysColIndex.getPi_id());
				  out.println("col values is :" +colValue);				

				   for (int i=0;i<RefColVec.size();i++)
					{
					   for (int j=0;j<RefColVecTemp.size();j++)
						{
					        int  idvalue   = ((refcoldata)RefColVec.elementAt(i)).getPi_idvalue();
							
							int  idvalueTemp = 
							((refcoldata)RefColVecTemp.elementAt(j)).getPi_idvalue();


							String namevalue = ((refcoldata)RefColVecTemp.elementAt(j)).getCc_namevalue();

							
                           if (idvalue == idvalueTemp)
							{
								out.println("<option  value="+idvalue+"  selected>"+namevalue+"</option>");
							}
							else
							{
								namevalue = ((refcoldata)RefColVec.elementAt(i)).getCc_namevalue();
								out.println("<option        value="+idvalue+">"+namevalue+"</option>");
							}
						}
					}
				   out.println("</select>");
				   /*RefColVec = Entity.getrefColValue(Integer.toString(SysColIndex.getPi_id()),"0");
				   for (int i=0;i<RefColVec.size();i++)
					{
					   int  idvalue   = ((refcoldata)RefColVec.elementAt(i)).getPi_idvalue();
					   String namevalue = ((refcoldata)RefColVec.elementAt(i)).getCc_namevalue();
					   out.println("<option  value="+idvalue+">"+namevalue+"</option>");
					}
				   out.println("</select>");*/
				   out.println("</font></td>");
				   break;
				case 2:
					out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+"<textarea name="+SysColIndex.getCc_code() +" value="+colValue+"></textarea>"+"</font></td>");
					break;
				
				default:
					break;
			}
			if (ass_flag!=1) out.println("</tr>");
			
		}
		}
	}
	else
		out.println("<br><br><br><br><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>No Info at This Id : "+ sId+"</font>");
%>

<br>
<input type="hidden" name="id" value=<%=sId%>></input>
<input type="hidden" name="OptType" value='3'></input>
<input type="submit" value="�ύ"></input>
</form>
</table>
</body>
</html>