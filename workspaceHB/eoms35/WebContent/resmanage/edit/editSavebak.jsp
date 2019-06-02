<%@page contentType="text/html;charset=ISO8859_1"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="mcs.common.db.*"%>
<%@page import="com.boco.eoms.resmanage.operator.*"%>
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
    if(!bflag)
   out.println("<script>alert('您已经掉线，请重新登陆！');parent.location='../index.jsp';</script>");
   String type = null;
	if(request.getParameter("type") != null)
		type = request.getParameter("type");
	else
		type ="0";
	String sId = null;
	if(request.getParameter("id") != null)
		sId = request.getParameter("id");
	else
		sId = "2";
	String cityId = null;
	if(request.getParameter("cityid") != null)
		cityId = request.getParameter("cityid");
	else
		cityId = ug.getCity() + "";
	
	int  OptType = 0;//操作类型：1：insert,2:delete:3:update
    if(request.getParameter("OptType") != null)
		 OptType =Integer.parseInt(request.getParameter("OptType"));
	else
	     OptType=0;
	int tempmaxid=0;
	if (request.getParameter("tempmaxid")!=null)
		tempmaxid = Integer.parseInt(request.getParameter("tempmaxid"));
	String fi_city="";
	
%>
<%
	entityoperate Entity = new entityoperate();
	syscolindex SysColIndex = new syscolindex();
    systabindex SysTab = new systabindex();
	Vector TabVect = new Vector();
	TabVect = Entity.getTabinfor(sId);
	String tabname = null;
	for(int i=0;i<TabVect.size();i++)
	{
		SysTab = (systabindex)TabVect.get(i);
		tabname   = SysTab.getCc_name();
	}
	Vector SysVect = new Vector();
    Vector ColVec  = new Vector(); //各列的值的信息

	SysVect = Entity.getcolinfor(sId);

	int colNum = SysVect.size();				//列信息
	//out.println("colNum size is : "+colNum);

	if(colNum != 0)
	{
		switch(OptType)
		{
			//update
			case 3:
			    for(int Col = 0; Col < SysVect.size(); Col ++)
				{
					//得到列的信息
					SysColIndex = (syscolindex)SysVect.get(Col);
					String colName  = SysColIndex.getCc_name();       //列中文名
					String colCode  = SysColIndex.getCc_code();       //列英文名
					String colValue = request.getParameter(colCode);  //列的值
					if (colCode.equalsIgnoreCase("fi_city"))
						fi_city = colValue;
					int    ass_flag = SysColIndex.getCi_ass_flag();     //辅助标志
					int    nul_flag  = SysColIndex.getCi_nul_flag();     //是否可为空标志
					if (colValue==null || colValue.compareTo("")==0)
						colValue= null;
					String colType  = SysColIndex.getCc_type();       //列类型

					syscolindex colindex = new syscolindex();

					colindex.setCc_code(colCode);
					colindex.setCc_name(colName);
					colindex.setCc_type(colType);
					colindex.setCc_value(colValue);
					colindex.setCi_ass_flag(ass_flag);
					colindex.setCi_nul_flag(nul_flag);
					ColVec.addElement(colindex);
				}
				break;
			//insert 
			case 1:
				for(int Col = 1; Col < SysVect.size(); Col ++)
				{
					//得到列的信息
					SysColIndex = (syscolindex)SysVect.get(Col);
					String colName  = SysColIndex.getCc_name();       //列中文名
					String colCode  = SysColIndex.getCc_code();       //列英文名
					String colValue = request.getParameter(colCode);  //列的值
					if (colCode.equalsIgnoreCase("fi_city"))
						fi_city = colValue;
					int    ass_flag = SysColIndex.getCi_ass_flag();       //辅助标志
					int    nul_flag  = SysColIndex.getCi_nul_flag();     //是否可为空标志
					if (colValue==null || colValue.compareTo("")==0)
						colValue= null;

					String colType  = SysColIndex.getCc_type();       //列类型
					//out.println("colName is : 　"+colCode);
					//out.println("colValue is :  "+colValue);
					syscolindex colindex = new syscolindex();
					colindex.setCc_code(colCode);
					colindex.setCc_name(colName);
					colindex.setCc_type(colType);
					colindex.setCc_value(colValue);
					colindex.setCi_ass_flag(ass_flag);
					colindex.setCi_nul_flag(nul_flag);
					ColVec.addElement(colindex);
				}
	   			break;
			//delete
			case 2:    
            		SysColIndex = (syscolindex)SysVect.get(0);
					String colCode  = SysColIndex.getCc_code();       //列英文名
					String[] colValue = request.getParameterValues("pi_id");  //列的值
					for (int i=0;i<colValue.length;i++)
					{
						//out.println("colValue is :  "+colValue[i]);
						syscolindex colindex = new syscolindex();
						colindex.setCc_value(colValue[i]);
						ColVec.addElement(colindex);
					}
	   			break;
			default:
				break;
		}
		
   }
	else
	{
		out.println("<br><br><br><br><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>No Info at This Id : "+ sId+"</font>");
	}

	int ColVecSize = ColVec.size();
	//out.println("ColVecSize size is :" + ColVecSize);
	String ErrMsg = null;
	String sucMsg = "您编辑的资源已经成功的加入数据库中了！";
	if (ColVecSize!=0)
	{
		//delete时不需要校验
		if (OptType!=2)
		{
			ErrMsg = Entity.checkData(ColVec);
			//out.println(ErrMsg);
		}
		if (ErrMsg==null || ErrMsg.equals(""))
		{
			try
			{
			   int b_success = Entity.dataOperate(sId,ColVec,OptType);
		       //out.println(b_success);
			   if (b_success<1)
			     ErrMsg = "数据库操作错误！";
			   else
				{
				  DBSQLExecute db = new DBSQLExecute();
				  if (OptType ==1 || OptType == 3)
					{
				  int pi_id=0;
				  pi_id = Entity.getMaxId("sys_ass_log");
				  int ci_device=0;
				  String cc_devicename = "";
				  //String cc_entityname = Entity.getTabIdByName(sId);
				  String cc_name =tabname;
				  if (OptType ==1)
					{
					  cc_name+="的添加操作日志";
					  ci_device = tempmaxid;
					  cc_devicename = ((syscolindex)ColVec.elementAt(0)).getCc_value();
					}
				  if (OptType ==3)
					{
					  cc_name +="的修改操作日志";
					  ci_device = Integer.parseInt(((syscolindex)ColVec.elementAt(0)).getCc_value());
					  cc_devicename = ((syscolindex)ColVec.elementAt(1)).getCc_value();
					}
				  java.util.Date logtime = new java.util.Date();
				  String sql = "insert into sys_ass_log(pi_id,cc_name,fi_city,fi_entity,cc_entityname,ci_device";
				 sql+=",cc_devicename,ci_logtype,ci_operatorid,cc_operatorname,cd_logtime )";
				 sql+="  values("+pi_id+",'"+cc_name+"',"+fi_city+","+sId+",'"+tabname+"',";
				 sql+=ci_device+",'"+cc_devicename+"',";
		 	     sql+=OptType+","+opt.getIOperatorId()+",'"+opt.getStrOperatorName()+"',"+mtools.getStrValue(logtime)+")";
				 //out.println("insert log sql is:"+sql);
				 int log_row= db.executeInsert(sql);
				 if (log_row>0)
				 {
				   out.println("log insert suceess!!");
				 }
					}
				}
		     }
			catch(Exception e)
			{
				out.println(e.getMessage());
			}
		}
    }
	//out.println("ErrMsg:::"+ErrMsg);
%>
<%
String retpage = null;
if(ErrMsg != null)
{
	retpage = "editInsert.jsp";
}
else
{
	if (Integer.parseInt(sId)==202)
		retpage = "editDocList.jsp";
	else
		retpage = "editList.jsp";
}
%>
	<body onload="returnInput()">
	<form action="<%=retpage%>" method=POST name=editSaveForm>
	<input type=hidden name=ErrMsg value='<%=ErrMsg%>'>
	<input type=hidden name=SucMsg value='<%=sucMsg%>'>
	<input type=hidden name=id value='<%=sId%>'>
	<input type=hidden name=cityid value='<%=cityId%>'>
	<input type=hidden name=type value='<%=type%>'>
	</form>
	</body> 
<script>
function returnInput()
{
	editSaveForm.submit();
}
</script>