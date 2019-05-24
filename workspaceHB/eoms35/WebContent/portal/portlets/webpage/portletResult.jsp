<%@ page import="java.util.Random"%>
<%@ include file="/portal/common/taglibs-portal.jsp"%>
<%
Random random=new Random();
random.setSeed(random.nextLong());
long l=random.nextLong();
%>
<div >
 <iframe frameborder="0" name="portlet<%=l%>" scrolling="no" src="<c:out  value="${url}"/>" width="100%" height="<c:out  value="${height}"/>"></iframe>
</div>


