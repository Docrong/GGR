<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<html:form action="tawRmArticle.do?method=xquery" method="post" styleId="tawRmArticleForm">
	<!--表示对所有的域有-->
	<html:hidden property="id" />
	<table class="formTable middle" align="center">
	<caption>
	<bean:message key="tawRmArticleForm.queryTitle"/>
	</caption>
		<tr>
			<td class="label" align="left" >
				<eoms:label styleClass="desc" key="tawRmArticleForm.articleName" />
			</td>
			<td >
				<html:errors property="articleName" />
				<html:text property="articleName" styleId="articleName"
					styleClass="text medium"></html:text>
			</td>
		
			<td class="label" align="left" >
				<eoms:label styleClass="desc" key="tawRmArticleForm.articleType" />
			</td>
			<td >
				<html:errors property="articleType" />
				<html:text property="articleType" styleId="articleType"
					styleClass="text medium"></html:text>
			</td>
		</tr>
		
		<tr>
			<td align="center" colspan="4">
				<html:submit styleClass="button" property="method.xquery" value="提交"
					onclick="bCancel=false">
					查询
				</html:submit> 
			 
				&nbsp;&nbsp;&nbsp;
				<html:reset styleClass="button" onclick="bCancel=true">
					重置
				</html:reset>
				</td>
		</tr>
	</table>
</html:form>
<!--自动生成的Javascript脚本-->

<%@ include file="/common/footer_eoms.jsp"%>
