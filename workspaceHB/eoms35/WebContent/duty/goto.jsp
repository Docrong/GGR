<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page language="java" import ="java.util.*,java.io.*,com.boco.eoms.common.util.StaticMethod"%>
<%
String duty_id = StaticMethod.nullObject2String(request.getAttribute("id"));
String setStrutsAction = StaticMethod.nullObject2String(request.getAttribute("setStrutsAction"));
System.out.println("setStrutsAction:"+setStrutsAction);
String url ="/TawRmRecord/viewmain.do?id="+duty_id+"&setStrutsAction="+setStrutsAction;
System.out.println("url:"+url);
%>

<logic:redirect page="<%=url%>"/>


