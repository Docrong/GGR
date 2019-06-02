<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="com.boco.eoms.workplan.vo.TawwpModelPlanVO"%>
<%
			 
%>

<script language="javascript">
function SubmitCheck(){

  frmReg = document.tawwpmodeledit;

  //if( !JustifyNull1(frmReg.name))
  if(frmReg.name.value=='')
  {
    alert( "<bean:message key="modeledit.title.warnInputName" />" );
    frmReg.name.focus();
    return false;
  }
  //else if(frmReg.typeIndex.value==''){
  //	alert( "请选择所属类型" );
  //  frmReg.typeIndex.focus();
  //  return false;
  //}
  return true;
}

</script>

<%
			TawwpModelPlanVO tawwpModelPlanVO = (TawwpModelPlanVO) request
			.getAttribute("modelplan");
%>
<form name="tawwpmodeledit" method="post"
	action="../tawwpmodel/modelmodify.do?modelplanid=<%=tawwpModelPlanVO.getId()%>"
	onsubmit='return SubmitCheck()'>
	<table class="formTable">
		<caption>
			<bean:message key="modeledit.title.formTitle" />
		</caption>
		<tr>
			<td class="label" width="300" >
				<bean:message key="modeledit.title.labSysType" />
			</td>
			<td  >
			   <%=tawwpModelPlanVO.getSysTypeIdName()%>
			</td>
		</tr>
		<tr>
			<td class="label"  >
				<bean:message key="modeledit.title.labNetType" />
			</td>
			<td  >
			   <%=tawwpModelPlanVO.getNetTypeIdName()%>
			 </td>
		</tr>
		<tr>
			<td class="label" >
				<bean:message key="modeledit.title.formName" />
			</td>
			<td>
				<input type="text" name="name" class="text"
					value="<%=tawwpModelPlanVO.getName()%>">
			</td>
		</tr>
		<%-- 
		<tr>
			<td class="label">
				<bean:message key="modeledit.title.formTypeName" />
			</td>
			<td>
			    <eoms:dict key="dict-workplan" dictId="typeIndex" isQuery="false" defaultId="<%=tawwpModelPlanVO.getTypeIndex()%>" selectId="typeIndex" beanId="selectXML"/>
				 
			</td>
		</tr>
		--%>
	</table>

	<br>
	<input type="submit"
		value="<bean:message key="modeledit.title.btnSubmit" />" name="B1"
		class="submit">
	<input type="button"
		value="<bean:message key="modeledit.title.btnBack" />"
		onclick="javascript:window.history.back();" class="button">

</form>
<%@ include file="/common/footer_eoms.jsp"%>



