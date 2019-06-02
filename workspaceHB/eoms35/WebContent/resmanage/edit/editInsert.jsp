<%@page contentType="text/html;charset=gb2312"%> 
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="mcs.common.db.*"%>
<%@page import="com.boco.eoms.resmanage.operator.*"%>
<%@page  import="com.boco.eoms.common.util.*"%>
<%@page import ="com.boco.eoms.jbzl.bo.*"%>
<%@include file="../power.jsp"%>
<%
/**
*@ E-DIS (四川省)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ 显示资源列表信息
*@ version 1.0+
**/
%>
<%
    //if(!bflag)
    //out.println("<script>alert('您已经掉线，请重新登陆！');parent.location='../index.jsp';</script>");
	//**********鉴权处理*******
	request.setCharacterEncoding("GBK");
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
		out.println("<script>alert('没有权限,请与系统管理员联系！"+"');history.back(1);</script>");
		return;
	}
	/*for (int i=0;i<cityVec.size();i++)
	{
		out.println("domain "+i+" values is:::"+cityVec.get(i));
	}
    */
    int pageid;
   if(request.getParameter("pageid") != null)
	 pageid = Integer.parseInt(request.getParameter("pageid")); 
   else
	 pageid=1;
	String tabname = request.getParameter("distabname");
	String cc_location = "";
	cc_location =request.getParameter("cc_location");
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

<title>添加资源</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body>
<form name="editform" action="editSave.jsp" method=post>
<table width='100%' border=0 cellspacing=1>
<%
out.println("<tr>");
out.println("<td align=left width='50%' class='table_title'>"+cc_location+"->"+tabname+"的添加</td></tr>");
%>
</table>
<%
    Date date = new Date();
	String SysTime = mtools.CovDate(date);
	syscolindex SysColIndex = new syscolindex();
    int tempmaxid = Entity.getMaxId(Entity.getTableName(sId));
	Vector SysVect = new Vector();

	SysVect = Entity.getcolinfor(sId);

	int colNum = SysVect.size();				//列信息

	if(colNum != 0)
	{
		coldata colData = new coldata();
		/******************	构造内容	*****************/
		out.println("<br><table class='table_show' border=0 cellspacing=1 width='100%'>");
		int colorCounter = 0;
		for(int Col = 1; Col < SysVect.size(); Col ++)
		{
			SysColIndex = (syscolindex)SysVect.get(Col);

			int ref_flag = SysColIndex.getCi_ref_flag();
                        int ass_flag = SysColIndex.getCi_ass_flag();
			int nul_flag = SysColIndex.getCi_nul_flag();
			int read_flag = SysColIndex.getCi_read_flag();
			int len = 0;
			String cc_type = SysColIndex.getCc_type();

			int pos1 = cc_type.indexOf("(");
			int pos2 = cc_type.indexOf(")");
			int pos3 = cc_type.indexOf(",");
            if (pos3<=0)
			{
				if (pos1 > 0 && pos2>0)
				{
					len = Integer.parseInt(cc_type.substring(pos1+1,pos2));
				}
			}
			if (ass_flag!=1 && ass_flag!=2 && !SysColIndex.getCc_code().equals("date_badge") && read_flag!=1 && SysColIndex.getPi_id()!=80003)
			{
				colorCounter ++;
				out.println("<tr class='tr_show'>");
				out.println("<td align=center class='clsfth'>"+StaticMethod.dbNull2String(SysColIndex.getCc_name())+"</td>");
			}
			//println  input text
			switch(ref_flag)
			{
				case 0:
					String strTemp = "<input type=text  name="+SysColIndex.getCc_code()+"";
				    
					if (len >0)
					{
						strTemp = "<input type=text name="+SysColIndex.getCc_code()+" maxlength="+len+"";
					}
					if (SysColIndex.getCc_code().equals("date_badge") )
					{
						strTemp = "<input type=hidden  name="+SysColIndex.getCc_code()+" value="+SysTime+"";
					}
					if ( read_flag==1)
					{
						strTemp = "<input type=hidden  name="+SysColIndex.getCc_code()+" ";
					}
					if (nul_flag ==1)
					{
						strTemp+=" value='必填'";
					}
					out.println("<td align=center>"+strTemp+"></td>");
					//out.println("<td align=center>"+strTemp+"</td>");
					break;
				//drop down control
				case 1:
				   //out.println("<select name='"+SysColIndex.getCc_code()+"'>");
				   String refTableTemp = Entity.getrefTable(Integer.toString(SysColIndex.getPi_id()));
				   /*if(refTableTemp != null)
				{
				   if(refTableTemp.compareTo("sys_sub_code") == 0)
						out.print("<td align=center><select name='"+SysColIndex.getCc_code()+"'>");
					else
						out.print("<td align=center><select name='"+SysColIndex.getCc_code()+"'><option value=0>无</option>");
				}*/
					if (SysColIndex.getPi_id()==80003)
					{
						out.println("<input type=hidden name="+SysColIndex.getCc_code()+" value=140>");
						break;
					}
                   out.print("<td align=center><select name='"+SysColIndex.getCc_code()+"'>");
				   refcoldata RefColData = new refcoldata();
				   Vector RefColVec = new Vector();
				   //得到参考列的id,name的value
				   RefColVec = Entity.getrefColValueByCity(Integer.toString(SysColIndex.getPi_id()),"0",cityVec);
				  // out.println("RefColVec size is: "+RefColVec.size());
				   for (int i=0;i<RefColVec.size();i++)
					{
					   int  idvalue   = ((refcoldata)RefColVec.elementAt(i)).getPi_idvalue();
					   String namevalue = StaticMethod.dbNull2String(((refcoldata)RefColVec.elementAt(i)).getCc_namevalue());
					   /*if (namevalue!=null)
						   namevalue = Cvt.cha(namevalue);
						   */
					   out.println("<option value="+idvalue+">"+namevalue+"</option>");
					}
				   out.println("</select></td>");
				   //out.println("</td>");
				   break;
				case 2:
					out.println("<td align=center><textarea name="+SysColIndex.getCc_code()+"></textarea></td>");
					break;
				default:
					break;
			}
 		  if (ass_flag!=1 && ass_flag!=2) out.println("</tr>");
		}
	}
	else
		out.println("<br><br><br><br>No Info at This Id : "+ sId+"");
%>
</table>
<br>
<table width='100%'>
	<tr>
		<td align=right width='100%'>
		<table>
			<tr>
				<td width=60 align=center>
				<input type="button" name="b_001" value="保  存" alt="保存录入的数据" onclick="javascript:formsubmit()" class="clsbtn2"></td>
				<td width=20></td>
				<td width=60 align=center>
				<input type="button" name="b_001" value="取  消" alt="取消录入的数据" onclick="javascript:clearinput()" class="clsbtn2"></td>
			</tr>
		</table>
		</td>
	</tr>
<input type="hidden" name="id" value=<%=sId%>></input>
<input type="hidden" name="tempmaxid" value=<%=tempmaxid%>></input>
<input type="hidden" name="OptType" value="1"></input>
<input type="hidden" name="pageid" value=<%=pageid%>></input>
</table>
</form>
</body>
</html>