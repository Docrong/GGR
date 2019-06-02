<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<html>
  <head>
    <title>My JSP 'MyJsp.jsp' starting page</title>
  </head>
  <body>
    This is my JSP page. <br>
    <form>
    	<input type="button" class="btn" value="${eoms:a2u('关闭')}" onClick="window.close()" />
	</form> 
  </body>
</html>
<%@ include file="/common/footer_eoms.jsp"%>