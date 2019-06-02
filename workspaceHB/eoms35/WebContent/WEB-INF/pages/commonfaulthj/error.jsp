<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">
 
body{font-size:24px}
</style>

  <div>
    <b><font size=5> 导入数据有误，请检查模板数据是否正确填入！</font></b>
   	
  </div>


<script type="text/javascript">
	function intoOwner(){
	 var undo=document.location.href;
	 undo = undo.substring(0,undo.indexOf("?")+1)+"method=messageimport";
	 location.href = undo;
	}
	window.setTimeout(intoOwner, 3000);
</script>
<%@ include file="/common/footer_eoms.jsp"%>