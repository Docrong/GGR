<%@page language="java" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ page import="com.boco.eoms.db.util.*"%>
<%@ page import="com.boco.eoms.autosheet.util.*"%>
<%@ page import="com.boco.eoms.common.util.*"%>
<%@ page import="com.boco.eoms.common.controller.*"%>
<%@ page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm"%>


<%
  SheetName   shName=SheetName.getInstance();
  SheetUtil sheetUtil = new SheetUtil();
  RecordSet rs= new RecordSet();
%>
<head>
<title>????</title>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
</head>
<script src="<%=request.getContextPath()%>/css/common/js/comm.js"></script>
<script src="<%=request.getContextPath()%>/css/common/js/page.js"></script>
<script src="<%=request.getContextPath()%>/css/common/js/table.js"></script>
<script language="javascript">
//??????
//function window.onload(){
  //??????????id
  //setTableStyle(table1);
//}
</script>
<p>&nbsp;</p>
<p align="center"><b><font size="5"><fmt:message key="autosheet.bdbj"/></font></b></p>
<form name=del>
<table id=table1 cellSpacing=0 cellPadding=0  border=1 width="760" bordercolordark="#FFFFFF" bordercolorlight="#66CCFF" bgcolor=#F3F3F3 align="center">
  <thead>
  <tr class="SortTableTitle">
    <td height="18" nowrap width="25%">
      <div align="center"><fmt:message key="autosheet.formname"/></div>
    </td>
    <td height="18" nowrap width="15%">
      <div align="center"><fmt:message key="autosheet.ssmk"/></div>
    </td>
    <td height="18" nowrap width="15%">
      <div align="center"><fmt:message key="autosheet.bjjbsx"/></div>
    </td>
    <td height="18" nowrap width="20%">
      <div align="center"><fmt:message key="autosheet.bjsrkmc"/></div>
    </td>
    <td height="18" nowrap width="15%">
      <div align="center"><fmt:message key="autosheet.bjsrk"/></div>
    </td>
    <td height="18" nowrap width="15%">
      <div align="center"><fmt:message key="autosheet.delform"/></div>
    </td>
    <td height="18" nowrap width="10%">
      <div align="center"><fmt:message key="autosheet.rebuild"/></div>
    </td>
<!--<td height="18" nowrap width="10%">
      <div align="center">??</div>
	<td height="18" nowrap width="10%">
      <div align="center">????</div>
    </td>
-->
  </tr>
  </thead>
  <%
      //edit by wangheqi 2.7 to 3.5
      TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
        /*SaveSessionBeanForm saveSessionBeanForm =
            (SaveSessionBeanForm) request.getSession().getAttribute(
            "SaveSessionBeanForm");*/

  if (saveSessionBeanForm == null) {
        response.sendRedirect(request.getContextPath() + "/timeout.jsp");
        return;
      }
  int DeptId = Integer.parseInt(saveSessionBeanForm.getDeptid());
  String  UserId = StaticMethod.null2String(saveSessionBeanForm.getUserid());
  System.out.println("DeptId:"+DeptId);
  String sql ="";
   if (UserId.equals(StaticVariable.ADMIN)) {
     sql="select sheet_id,sh_cname,para3,module_id from taw_sheetname  order by module_id";
   }
  else
    sql="select sheet_id,sh_cname,para3,module_id from taw_sheetname where dept_id = "+DeptId+" or dept_id = 1 order by module_id";

   rs.execute(sql);
  while(rs.next()){
      int sheet_id=rs.getInt(1);
      String sh_cname=rs.getString(2);
      //System.out.println("sh_cname====="+sh_cname+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      int para3=rs.getInt(3);
     String module_id=rs.getString(4);

  %>
  <tbody>
  <tr>
    <td width="20%">
      <div align="left" nowrap>
        <a target="_blank" href="<%=request.getContextPath()%>/htmlservlet?action=insert&sheet_id=<%=sheet_id%>"><%=sh_cname%><fmt:message key="autosheet.preview"/></a>
      </div>
    </td>
    <td width="15%">
      <div align="center" nowrap>
      <a ><%out.println(sheetUtil.getAppName(module_id));%></a>
      </div>
    </td>
    <td width="15%">
      <div align="center"><a href="<%=request.getContextPath()%>/autosheet/editname.jsp?sheet_id=<%out.print(sheet_id);%>&type=name"><fmt:message key="autosheet.bjjbsx"/></a></div>
    </td>
    <td width="15%">
      <div align="center"><a href="<%=request.getContextPath()%>/autosheet/editattr.jsp?sheet_id=<%out.print(sheet_id);%>&type=attr"><fmt:message key="autosheet.bjsrkmc"/></a></div>
    </td>
    <td width="10%">
      <div align="center"><a href="<%=request.getContextPath()%>/autosheet/editvalue.jsp?sheet_id=<%out.print(sheet_id);%>&type=value"><fmt:message key="autosheet.bjsrk"/></a></div>
    </td>
    <td width="10%">
      <div align="center"><a href="<%=request.getContextPath()%>/laststepservlet?sheetID=<%=sheet_id%>&flag=delete" onClick = "javascript: return confirm('<fmt:message key="autosheet.confirmdel"/>');"><fmt:message key="autosheet.del"/></a></div>
    </td>
    <td width="10%">
      <div align="center"><a href="<%=request.getContextPath()%>/laststepservlet?sheetID=<%=sheet_id%>&flag=renew" onClick = "javascript: return confirm('<fmt:message key="autosheet.confirmrebuild"/>');"><fmt:message key="autosheet.rebuild"/></a></div>
    </td>


<!--<td width="10%">
      <div align="center">
<%if(para3==0){%>
??
<%}else{%>
<a href="<%=request.getContextPath()%>/laststepservlet?sheetID=<%=sheet_id%>&flag=publish" onClick = "javascript: return confirm('<fmt:message key="autosheet.confirmfabu"/>');"><fmt:message key="autosheet.fabu"/></a>
<%}%>
</div>
    </td>
	<td width="10%">
      <div align="center"><%if(para3==0){%><fmt:message key="autosheet.released"/><%}else{%><fmt:message key="autosheet.norelease"/><%}%></div>
    </td>
-->

  </tr>
  </tbody>
  <%}%>
</table>
</form>

 <p align=center><a href=" <%= request.getContextPath()%>/autosheet/sheetname.jsp"><fmt:message key="autosheet.back"/></a></p>
<%String url=request.getContextPath()+"/autosheet/index.jsp";
session.setAttribute("returnURL",url);
%>
<%@ include file="/common/footer_eoms.jsp"%>
