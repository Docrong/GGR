<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="com.boco.eoms.base.util.StaticMethod"%>

<!--根据给定的实例名生成标题 -->
<title><fmt:message key="tawRmTestcardDetail.title"/></title>
<!-- <content tag="heading"><fmt:message key="tawRmTestcardDetail.heading"/></content> -->
<%
	String ids = (String) request.getAttribute("ids");
	String realReturnDate = StaticMethod.getLocalString();
%>

<script language="javascript">

Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawRmInoutRecordForm'});
});
</script>
<!--对表单的自动生成的处�?-->
<html:form action="tawRmInoutRecords" method="post" styleId="tawRmInoutRecordForm"> 

<ul>
		   <html:hidden property="realReturnDate" value="<%=realReturnDate%>"/>
		   <html:hidden property="ids" value="<%=ids%>"/>
    <!--表示对所有的域有�? -->
	         <br>
		<table class="formTable">		
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmInoutRecordForm.remark" />
				</td> 
				<td width="500" colspan="2">
					<html:textarea property="inStorageRemark" styleId="inStorageRemark"
						styleClass="text medium" rows="5" cols="30"/>
				</td>
			</tr>
		</table>
		
		<br>
		<table>
			<tr>
				<td>
					<html:submit styleClass="button" property="method.inStorage">
            			<fmt:message key="button.save"/>
        			</html:submit>
				</td>
			</tr>
		</table>
</ul>
  <!--自动生成的Javascript脚本-->

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>