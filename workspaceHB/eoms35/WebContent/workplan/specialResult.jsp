<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="com.boco.eoms.workplan.bo.TawwpExecuteBO"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>

<%
String time=request.getParameter("time");
out.println("时间"+time);
int type=StaticMethod.null2int(request.getParameter("type"));
out.println("类型"+type);
TawwpExecuteBO executeBO=new TawwpExecuteBO();
if(type==1)
     executeBO.reportDayExecute(time);
if(type==2)
     executeBO.reportExcel(time);
if(type==3){
     executeBO.reportDayExecute(time);
     executeBO.reportExcel(time);
}
if(type==4)
executeBO.reportDir();

if(type==5)
executeBO.specialRename(time,"temp");

out.println("操作成功");



%>

