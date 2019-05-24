<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<!--根据给定的实例名生成标题 -->
<title>专家信息
</title>
	<html:form action="TawExpertInfo.do?method=xsave" method="post"
		styleId="TawExpertInfoForm">
		<html:hidden property="id" />
		<table class="formTable large" align="center">
			<tr>
				<td colspan="6" align="center">
					<h2>
						专家信息
					</h2>
				</td>
			</tr>
			<tr>
			<td class="label" nowrap="nowrap" align="right">
					专家姓名
					<html:errors property="expertName" />
				</td>
				<td colspan="2">
               <html:text property="expertName" styleId="expertName" alt="allowBlank:false,vtext:'请输入专家姓名',maxLength:64" 
						styleClass="text medium"  readonly="true"/>					
				</td>
				<td class="label" nowrap="nowrap" align="right">
					专家id
					<html:errors property="expertId" />
				</td>
				<td colspan="2" >
               <html:text property="expertId" styleId="expertId" alt="allowBlank:false,vtext:'请输入专家id',maxLength:32" 
						styleClass="text medium"  readonly="true"/>					
				</td>
			</tr>
			<tr>
					<td class="label" nowrap="nowrap" align="right">
                       办公地点
					<html:errors property="adress" />
				</td>
				<td colspan="2">
					<html:text property="adress" styleId="adress"
						styleClass="text medium" readonly="true"
						alt="allowBlank:false,vtext:'请输入工作地址',maxLength:64" />
				</td>
				<td class="label" nowrap="nowrap" align="right">
					办公室电话
					<html:errors property="telephone" />
				</td>
				<td colspan="2">
					<html:text property="telephone" styleId="telephone"
						styleClass="text medium" readonly="true"
						alt="allowBlank:false,vtype:'number',vtext:'办公室电话',maxLength:64" />
				</td>
			</tr>

			<tr>
				<td class="label" nowrap="nowrap" align="right">
					手机
					<html:errors property="mobile" />
				</td>
				<td colspan="2">
					<html:text property="mobile" styleId="mobile"
						styleClass="text medium" readonly="true"
						alt="allowBlank:false,vtype:'number',vtext:'请输入手机',maxLength:32" />
				</td>
				<td class="label" nowrap="nowrap" align="right">
                       邮箱
					<html:errors property="email" />
				</td>
				<td colspan="2">
					<html:text property="email" styleId="email"
						styleClass="text medium" readonly="true"
						alt="allowBlank:false,vtype:'email',vtext:'请输入专家邮箱',maxLength:64" />
				</td>
			</tr>
			<tr>
				
				<td class="label" nowrap="nowrap" align="right">
					特长
					<html:errors property="beGoodAt" />
				</td>
				<td colspan="5">

					<html:textarea property="beGoodAt" styleId="beGoodAt"
						styleClass="text medium" rows="3" cols="40" readonly="true"
						alt="allowBlank:false,vtext:'请输入特长',maxLength:128" />
				</td>
			</tr>
			<tr>
				<td class="label" nowrap="nowrap" align="right">
					简历
					<html:errors property="resume" />
				</td>
				<td colspan="5">

					<html:textarea property="resume" styleId="resume"
						styleClass="text medium" rows="3" cols="40" readonly="true"
						alt="allowBlank:false,vtext:'请输入简历',maxLength:128" />
				</td>
			</tr>
			<%--<tr>
				<td colspan="6" align="center">
				
					<html:submit styleClass="button" property="method.xsave">
						保存
					</html:submit>
					&nbsp;&nbsp;
					<html:reset styleClass="button" onclick="bCancel=true">
						<fmt:message key="button.reset" />
					</html:reset>
				</td>
			</tr>
		--%></table>

	</html:form>
	<!--自动生成的Javascript脚本-->
<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'TawExpertInfoForm'});
	});
</script>	
<%@ include file="/common/footer_eoms.jsp"%>
