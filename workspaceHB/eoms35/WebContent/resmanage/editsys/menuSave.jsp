<%@page contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*"%>
<%@page import="com.boco.eoms.resmanage.menu.*"%>
<%@page import="com.boco.eoms.resmanage.editsys.*"%>
<%@include file="../power.jsp"%>
<%
/**
*@ E-DIS (�Ĵ�ʡ)
*@ Copyright : (c) 2003
*@ Company : BOCO.
*@ ���ʵ�� first
*@ version 1.0
**/
%>
<%
//if(!bflag)
//	out.println("<script>alert('���Ѿ����ߣ������µ�½��');parent.location='../index.jsp';</script>");
String tmp = request.getParameter("menuid");
int pos = tmp.indexOf("^");
int menuid = Integer.parseInt(tmp.substring(0,pos));
int level = Integer.parseInt(tmp.substring(pos+1)) + 1;

int type = Integer.parseInt(request.getParameter("type"));
int tabid = Integer.parseInt(request.getParameter("tabid"));
String menuname = request.getParameter("menuname");
secMenu sm = new secMenu();
int id = menuVector.getThisMenuIdforTabId(tabid);

sm.setMenuId(1);
sm.setSecMName(menuname);
sm.setTarget("mainFrame");
sm.setSecType(0);
sm.setSecLink("/edit/editList.jsp?id="+tabid);
sm.setSecLevel(level);
sm.setAttachSecId(menuid);
sm.setFSecId(0);
//sm.setRemark();
if(type != 0 && id != 0)
{
	sm.setSecMId(id);
	if(menuVector.UpdateMenu(sm))
		out.println("<script>alert('�����ɹ�����ˢ����״�˵�');window.close();</script>");
	else
		out.println("<script>alert('��������ϵ������');</script>");
}
else
{
	if(menuVector.AddMenu(sm,tabid))
		out.println("<script>alert('�����ɹ�����ˢ����״�˵�');window.close();</script>");
	else
		out.println("<script>alert('��������ϵ������');</script>");
}
%>