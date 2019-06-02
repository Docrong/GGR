<%@page contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="com.boco.eoms.resmanage.operator.*"%>
<%@page import="mcs.common.db.*"%>
<%@page  import="com.boco.eoms.common.util.*"%>
<%@page import ="com.boco.eoms.jbzl.bo.*"%>
<%@include file="../power.jsp"%>
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
   // if(!bflag)
//out.println("<script>alert('您已经掉线，请重新登陆！');parent.location='../index.jsp';</script>");
//request.setCharacterEncoding("GBK");
//**********鉴权处理*******
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
	for (int i=0;i<oppId.size();i++)
	{
		oper_id = Integer.parseInt(oppId.get(0).toString());
	}
	Vector cityVec = new Vector();
	TawValidatePrivBO ValidatePrivBO = new TawValidatePrivBO();
	cityVec = ValidatePrivBO.validatePriv(userId,oper_id,1);
	//out.println("domain vec size is:::"+cityVec.size());
	if (cityVec.size()==0)
	{
		out.println("<script>alert('没有权限"+"');history.back(1);</script>");
		return;
	}
	/*for (int i=0;i<cityVec.size();i++)
	{
		out.println("domain "+i+" values is:::"+cityVec.get(i));
	}*/
   String id=request.getParameter("pi_id");
   int pageid;
   if(request.getParameter("pageid") != null)
	 pageid = Integer.parseInt(request.getParameter("pageid")); 
   else
	 pageid=1;
    String type = null;
	if(request.getParameter("type") != null)
		type = request.getParameter("type");
	else
		type ="0";
	int cityFlag=0;
	if(request.getParameter("cityflag") != null)
		cityFlag = Integer.parseInt(request.getParameter("cityflag"));
	String pi_id = null;
    if(request.getParameter("pi_id") != null)
		pi_id = request.getParameter("pi_id");
    String tabname = StaticMethod.dbNull2String(request.getParameter("distabname"));
	String cc_location = "";
	cc_location = StaticMethod.dbNull2String(request.getParameter("cc_location"));

%>
<html>
<head>
<title>修改资源</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body bgcolor="#eeeeee" text="#000000" class="content14">
<form name="editform" action="editSave.jsp">
<%
out.println("<tr bgcolor=#eeeeee>");
out.println("<td align=left width='50%'><font size=2>"+cc_location+"->"+tabname+"的修改</font></td>");
%>

<%
	Date date = new Date();
	String SysTime = mtools.CovDate(date);
	syscolindex SysColIndex = new syscolindex();

	Vector SysVect = new Vector();

	SysVect = Entity.getcolinfor(sId);

	int colNum = SysVect.size();				//列信息

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
			int nul_flag = SysColIndex.getCi_nul_flag();
			int read_flag = SysColIndex.getCi_read_flag();
            //========得到列的值-----
			String colValue = StaticMethod.dbNull2String(Entity.getColValue(sId,SysColIndex.getCc_code(),pi_id));
			//out.println(SysColIndex.getCc_code()+ "value is: " +colValue+"&nbsp");
			int len = 0;
			String cc_type = SysColIndex.getCc_type();

			int pos1 = cc_type.indexOf("(");
			int pos2 = cc_type.indexOf(")");
			
            if (pos1 > 0 && pos2>0)
			{
				len = Integer.parseInt(cc_type.substring(pos1+1,pos2));
				
			}
			String colValueTemp = colValue;
			if (colValue==null)
				colValue="";

			if (Col==0)
			{
				out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+"<input name="+SysColIndex.getCc_code()+"   type=hidden value="+colValue+"></input>"+"</font></td>");
			}
			else
			{
			if (ass_flag!=1 && ass_flag!=2 && !SysColIndex.getCc_code().equals("date_badge") && read_flag!=1 && SysColIndex.getPi_id()!=80003)
			{
				colorCounter ++;
				out.println("<tr bgcolor="+((colorCounter%2 == 0)?"#eaeaea":"#eeeeee")+">");
				out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+StaticMethod.dbNull2String(SysColIndex.getCc_name())+"</font></td>");
			}
			//println  input text
			switch(ref_flag)
			{
				case 0:
					String strTemp = "<input type=text  name="+SysColIndex.getCc_code()+" value="+colValue+">";
					if (len >0)
					{
						strTemp = "<input type=text name="+SysColIndex.getCc_code()+" maxlength="+len+" value="+colValue+">";
					}
					if (SysColIndex.getCc_code().equals("date_badge"))
					{
						strTemp = "<input type=hidden  name="+SysColIndex.getCc_code()+" value="+SysTime+">";
					}
					if ( read_flag==1)
					{
						strTemp = "<input type=hidden  name="+SysColIndex.getCc_code()+"  value="+colValue+">";
					}
					if (nul_flag ==1)
					{
						strTemp+="*必填";
					}
					out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+strTemp+"</font></td>");
					//out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+"<input name="+SysColIndex.getCc_code()+"   value="+colValue+"></input>"+"</font></td>");
					break;
				//drop down control
				case 1:
					if (SysColIndex.getPi_id()==80003)
					{
						out.println("<input type=hidden name="+SysColIndex.getCc_code()+" value=140>");
						break;
					}
				   out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>");
				   out.println("<select name="+SysColIndex.getCc_code()+">");
				   refcoldata RefColData = new refcoldata();
				   Vector RefColVec = new Vector();
                   Vector RefColVecTemp = new Vector();
				   //得到参考列的id,name的value
				  RefColVec = Entity.getrefColValueByCity(Integer.toString(SysColIndex.getPi_id()),"0",cityVec);	
                  String refTableTemp = Entity.getrefTable(Integer.toString(SysColIndex.getPi_id()));
				   if (colValue.equals(""))
					{
					   out.println("refcolvalue size is:"+RefColVec.size());
					   for(int col=0;col<RefColVec.size();col++)
						{
						    int idvalue = ((refcoldata)RefColVec.elementAt(col)).getPi_idvalue();
							String namevalue = StaticMethod.dbNull2String(((refcoldata)RefColVec.elementAt(col)).getCc_namevalue());
							out.println("<option value="+idvalue+">"+namevalue+"</option>");
						}			  		    
					}
					else
					{
						if (RefColVec.size()!=0)
						{
							for (int j=0;j<RefColVec.size();j++)
							{
								int idvalue = ((refcoldata)RefColVec.elementAt(j)).getPi_idvalue();
								String namevalue = StaticMethod.dbNull2String(((refcoldata)RefColVec.elementAt(j)).getCc_namevalue());
								if (Integer.parseInt(colValue) == idvalue)
								{
									out.println("<option  value="+idvalue+"  selected>"+namevalue+"</option>");
								}
								else
								{
									out.println("<option value="+idvalue+">"+namevalue+"</option>");
								}
							}
						}
					}
				   out.println("</select>");
				   out.println("</font></td>");
				   break;
				   /*for (int i=0;i<RefColVec.size();i++)
					{
					   if (RefColVecTemp.size()!=0)
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
							int  idvalue   = ((refcoldata)RefColVec.elementAt(i)).getPi_idvalue();
							String namevalue = ((refcoldata)RefColVecTemp.elementAt(i)).getCc_namevalue();
							if (Integer.parseInt(colValue) == idvalue)
							{
								out.println("<option  value="+idvalue+"  selected>"+namevalue+"</option>");
							}
							else
							{
								namevalue = ((refcoldata)RefColVec.elementAt(i)).getCc_namevalue();
								out.println("<option        value="+idvalue+">"+namevalue+"</option>");
							}*/
						//}
						/*else
						{
							int  idvalue   = ((refcoldata)RefColVec.elementAt(i)).getPi_idvalue();
							String namevalue = ((refcoldata)RefColVec.elementAt(i)).getCc_namevalue();
							out.println("<option value="+idvalue+">"+namevalue+"</option>");

						}
					}
				}*/
				   //=======
				case 2:
					out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+"<textarea name="+SysColIndex.getCc_code() +" value="+colValue+"></textarea>"+"</font></td>");
					break;
				
				default:
					break;
			}
			if (ass_flag!=1 && ass_flag!=2 ) out.println("</tr>");
			
		}
		}
	}
	else
		out.println("<br><br><br><br><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>No Info at This Id : "+ sId+"</font>");
%>

<br>
<table width='100%'>
	<tr>
		<td align=center width='100%'>
		<table>
			<tr>
				<td width=60 align=center><a href="javascript:formsubmit()"><img src="../images/button_submit.gif" alt="保存修改的数据" border=0></a></td>
				<td width=60 align=center><a href="javascript:clearinput()"><img src="../images/button_cancel.gif" alt="取消录入的数据" border=0></a></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<input type="hidden" name="id" value=<%=sId%>></input>
<input type="hidden" name="type" value=<%=type%>></input>
<input type="hidden" name="OptType" value='3'></input>
<input type="hidden" name="pageid" value=<%=pageid%>></input>
<!-- <input type="submit" value="提交"></input>
 --></form>
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
</table>
</body>
</html>