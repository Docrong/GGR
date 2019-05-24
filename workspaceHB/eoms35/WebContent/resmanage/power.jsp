<%@page import="com.boco.eoms.common.controller.*"%>
<%@page import="com.boco.eoms.resmanage.operator.*"%>
<%@page import="com.boco.eoms.common.util.*"%>
<%
	/*Vector entityPower = new Vector();
	power epw = new power();
	int RootPower = 0;
	*/
	/******for affiche******/
	//boolean bflag = false;
	/************/
	/*operator opt = new operator();
	userGroup ug = new userGroup();
	if(session.getAttribute("UserInfo") != null)
	{
		opt = (operator)session.getAttribute("UserInfo");
		ug = groupOpt.getGroupInfoById(opt.getIGroup());
		entityPower = ug.getEntityOperate();
		/******for affiche*********/
	/*bflag = true;
	}*/
	//========modify for eoms by wuzongxian 2003.10.25
	userGroup ug = new userGroup();//temp need delte
	String webapp = request.getContextPath();
	String userId=null;
	int deptId =0;
	boolean bflag = false;
	String realPath = null;
	SaveSessionBeanForm saveSessionBeanForm=(SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
	if (saveSessionBeanForm!=null)
	{
		userId = saveSessionBeanForm.getWrf_UserID();
		deptId = saveSessionBeanForm.getWrf_DeptID();
		realPath = saveSessionBeanForm.getRealPath();
	}
	else
	{
		out.println(StaticMethod.dbNull2String("您的帐号闲置过久，请重新登录！"));
		return;
	}
%>
	<!-- <script language="javascript">
          alert("您的帐号闲置过久，请重新登录！");
          window.top.location.href = "<%=webapp%>/index.jsp";
   </script> -->