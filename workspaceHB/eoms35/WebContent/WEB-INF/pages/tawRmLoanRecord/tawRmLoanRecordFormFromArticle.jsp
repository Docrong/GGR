<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<!--根据给定的实例名生成标题 -->
<%
	String articleId = (String) request.getParameter("articleId");
	String articleName = (String) request.getAttribute("articleName");
	String articleType=request.getParameter("articleType");
%>
<script language="javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawRmLoanRecordForm'});
});
</script>

<!--对表单的自动生成的处�?-->
<html:form action="tawRmLoanRecord" method="post" styleId="tawRmLoanRecordForm"> 
<ul>

    <!--表示对所有的域有�? -->
	       <html:hidden property="id"/>
	       <html:hidden property="articleId" value="${articleId}"/>
	       <html:hidden property="articleType" value="${articleType}"/>
		   <html:hidden property="userId" />
		   <html:hidden property="workSerial" />
		   <html:hidden property="tmpArticleName" />
		   <html:hidden property="tmpPiece" />
		   <html:hidden property="tmpBorrowerName" />
		   <html:hidden property="tmpLoanTime" />
		   <html:hidden property="tmpReturnTime" />
		   <html:hidden property="roomId" />
		   <html:hidden property="startTime" />
		   <html:hidden property="endTime" />
		   <br>
		<table class="formTable middle" align="center">
			<tr>
			<td colspan="3" align="center">
			<h3><fmt:message key="tawRmLoanRecordDetail.title"/></h3>
			</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmLoanRecordForm.articleName" />
				</td> 
				<td width="500" colspan="2">
				
					<html:text property="articleName" styleId="articleName"
						styleClass="text medium" readonly="true" value="${articleName}"/>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmArticleForm.articleType" />
				</td> 
				<td width="500" colspan="2">
				
					<html:text property="articleType" styleId="articleType"
						styleClass="text medium" readonly="true" value="${articleType}"/>
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmLoanRecordForm.piece" />
				</td> 
				<td width="500" colspan="2">
					<html:text property="piece" styleId="piece"
						styleClass="text medium"
						alt="allowBlank:false,vtype:'number',vtext:'${eoms:a2u('请输入数量')}'" />
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmLoanRecordForm.borrowerName" />
				</td> 
				<td width="500" colspan="2">
				
					<html:text property="borrowerName" styleId="borrowerName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:'${eoms:a2u('请输入借物人姓名')}'" />
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmLoanRecordForm.loanTime" />
				</td> 
				<td width="500" colspan="2">
					<html:text property="loanTime" styleId="loanTime"
						styleClass="text medium"
						alt="allowBlank:false,vtext:'${eoms:a2u('请输入物品借出时间')}'" 
						onclick="popUpCalendar(this, this);" readonly="true"/>
				</td>
			</tr>
            <tr>
            <td colspan="3" align="center">		
					<html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            			<fmt:message key="button.save"/>
        			</html:submit>
        				&nbsp;&nbsp; 
        			<html:reset styleClass="button" onclick="bCancel=true">
					<fmt:message key="button.reset" />
				</html:reset>
				</td>
			</tr>
		</table>
</ul>
  <!--自动生成的Javascript脚本-->

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>