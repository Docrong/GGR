<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript">
function makeProductCode(){
	var value1 = document.getElementById("mainProductCode1").value;
	var value2 = document.getElementById("mainProductCode2").value;
	var value3 = document.getElementById("mainProductCode3").value;
	var valueCode = "";
	if(value1!=null&&value1!=''){
		valueCode = value1+'%-P-%';
	}
	if(value2!=null&&value2!=''){
		valueCode += value2+'%-%';
	}
	if(value3!=null&&value3!=''){
		valueCode += value3;
	}
	document.getElementById("mainProductCode").value = valueCode;
	document.getElementById("mainProductCode1").value='';
	document.getElementById("mainProductCode2").value='';
	document.getElementById("mainProductCode3").value='';
}
</script>
<div id="sheetform">
	<html:form action="/${module}.do?method=performQuery" styleId="theform">
		<%@ include file="/WEB-INF/pages/wfworksheet/common/commonqueryhead.jsp"%>
		<jsp:include page="/WEB-INF/pages/wfworksheet/${module}/especialquery.jsp"/> 
		<%@ include file="/WEB-INF/pages/wfworksheet/common/commonquerybottom.jsp"%>	
		<!-- buttons -->
		<div class="form-btns">
		  	<html:submit styleClass="btn" property="method.save" styleId="method.save" onclick="makeProductCode()">
				<fmt:message key="button.done" />
			</html:submit>
		 </div>	 
	</html:form>
</div>
<%@ include file="/WEB-INF/pages/wfworksheet/common/commonqueryJs.jsp"%>
<%@ include file="/common/footer_eoms.jsp"%>
