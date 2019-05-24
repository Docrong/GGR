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
	
	int  OptType = 0;//�������ͣ�1��insert,2:delete:3:update
    if(request.getParameter("OptType") != null)
		 OptType =Integer.parseInt(request.getParameter("OptType"));
	else
	     OptType=0;

%>
<%
	entityoperate Entity = new entityoperate();
	syscolindex SysColIndex = new syscolindex();

	Vector SysVect = new Vector();
    Vector ColVec  = new Vector(); //���е�ֵ����Ϣ

	SysVect = Entity.getcolinfor(sId);

	int colNum = SysVect.size();				//����Ϣ
	//out.println("colNum size is : "+colNum);

	if(colNum != 0)
	{
		switch(OptType)
		{
			case 3:
			    for(int Col = 0; Col < SysVect.size(); Col ++)
				{
					//�õ��е���Ϣ
					SysColIndex = (syscolindex)SysVect.get(Col);
					String colName  = SysColIndex.getCc_name();       //��������
					String colCode  = SysColIndex.getCc_code();       //��Ӣ����
					String colValue = request.getParameter(colCode);  //�е�ֵ
					int    ass_flag = SysColIndex.getCi_ass_flag();       //������־
					if (colValue==null || colValue.compareTo("")==0)
						colValue= null;
					String colType  = SysColIndex.getCc_type();       //������

					syscolindex colindex = new syscolindex();

					colindex.setCc_code(colCode);
					colindex.setCc_name(colName);
					colindex.setCc_type(colType);
					colindex.setCc_value(colValue);
					colindex.setCi_ass_flag(ass_flag);
					ColVec.addElement(colindex);
				}
				break;
			case 1:
				for(int Col = 1; Col < SysVect.size(); Col ++)
				{
					//�õ��е���Ϣ
					SysColIndex = (syscolindex)SysVect.get(Col);
					String colName  = SysColIndex.getCc_name();       //��������
					String colCode  = SysColIndex.getCc_code();       //��Ӣ����
					String colValue = request.getParameter(colCode);  //�е�ֵ
					int    ass_flag = SysColIndex.getCi_ass_flag();       //������־
					
					if (colValue==null || colValue.compareTo("")==0)
						colValue= null;

					String colType  = SysColIndex.getCc_type();       //������
					//out.println("colName is : ��"+colCode);
					//out.println("colValue is :  "+colValue);

					syscolindex colindex = new syscolindex();

					colindex.setCc_code(colCode);
					colindex.setCc_name(colName);
					colindex.setCc_type(colType);
					colindex.setCc_value(colValue);
					colindex.setCi_ass_flag(ass_flag);
					ColVec.addElement(colindex);
				}
	   			break;
			case 2:    
            		SysColIndex = (syscolindex)SysVect.get(0);
					String colCode  = SysColIndex.getCc_code();       //��Ӣ����
					String[] colValue = request.getParameterValues("pi_id");  //�е�ֵ
				
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
	String sucMsg = "���༭����Դ�Ѿ��ɹ��ļ������ݿ����ˣ�";
	if (ColVecSize!=0)
	{
		//deleteʱ����ҪУ��
		if (OptType!=2)
		{
			ErrMsg = Entity.checkData(ColVec);
			//out.println(ErrMsg);
		}
		try
		{
           int b_success = Entity.dataOperate(sId,ColVec,OptType);
		   out.println(b_success);
		   if (b_success<1)
			   ErrMsg = "���ݿ��������";
		}
		catch(Exception e)
		{
			out.println(e.getMessage());
		}
    }
	//out.println("ErrMsg:::"+ErrMsg);
%>
<%
String retpage = null;
if(ErrMsg != null)
	retpage = "editInsert.jsp";
else
	retpage = "editList.jsp";
%>
	<body onload="returnInput()">
	<form action="<%=retpage%>" method=POST name=editSaveForm>
	<input type=hidden name=ErrMsg value='<%=ErrMsg%>'>
	<input type=hidden name=SucMsg value='<%=sucMsg%>'>
	<input type=hidden name=id value='<%=sId%>'>
	</form>
	</body>
<script>
function returnInput()
{
	editSaveForm.submit();
}
</script>