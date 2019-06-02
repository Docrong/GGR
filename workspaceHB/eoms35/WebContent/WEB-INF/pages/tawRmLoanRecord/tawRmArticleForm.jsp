
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<html:form action="tawRmArticle.do?method=xsave" method="post" styleId="tawRmArticleForm">
	<!--????????-->
	<html:hidden property="id" />
	<table class="formTable middle" align="center">
	<tr>
	<td align="center" colspan="2">
	<h2>
	<bean:message key="tawRmArticleForm.addTitle"/>
	
	</h2>
	</td>
	</tr>
		<tr>
			<td class="label" align="right">
				<eoms:label styleClass="desc" key="tawRmArticleForm.articleName" />
			</td>
			<td>
				<html:errors property="articleName" />
				<html:text property="articleName" styleId="articleName"
					styleClass="text medium"
					alt="allowBlank:false,vtext:'${eoms:a2u('???????')}'"></html:text>
			</td>
		</tr>
		<tr>
			<td class="label" align="right">
				<eoms:label styleClass="desc" key="tawRmArticleForm.articleType" />
			</td>
			<td>
				<html:errors property="articleType" />
				<html:text property="articleType" styleId="articleType"
					styleClass="text medium"
					alt="allowBlank:false,vtext:'${eoms:a2u('???????')}'"></html:text>
			</td>
		</tr>
		<tr>
			<td class="label" align="right">
				<eoms:label styleClass="desc" key="tawRmArticleForm.allNum" />
			</td>
			<td>
				<html:errors property="allNum" />
				<html:text property="allNum" styleId="allNum"
					styleClass="text medium"
					alt="allowBlank:false,vtype:'number',vtext:'${eoms:a2u('???????')}'"></html:text>
			</td>
		</tr>
		<tr>
			<td class="label" align="right">
				<eoms:label styleClass="desc" key="tawRmArticleForm.describe" />
			</td>
			<td>
				<html:errors property="describe" />
				<html:textarea property="describe" styleId="describe"
					styleClass="text medium" cols="30" rows="4"
					alt="allowBlank:false,vtext:'${eoms:a2u('???????')}',maxlenth:512"></html:textarea>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				<html:submit styleClass="button" property="method.save"
					onclick="bCancel=false">
					<bean:message key="button.save" />
				</html:submit> 
			&nbsp;&nbsp; 
				<html:reset styleClass="button" onclick="bCancel=true">
					<bean:message key="button.reset" />
				</html:reset>
				</td>
		</tr>
	</table>
</html:form>
<!--?????Javascript??-->
<script type="text/javascript">
  Ext.onReady(function() {
	v = new eoms.form.Validation({form:'TawRmArticleForm'});});
    function  backtoList()
    {
      document.forms[0].action="outerLinkmans.do?method=returnList";
       //document.forms.method = "POST";
       document.forms[0].submit(); 
    }
  </script>
<%@ include file="/common/footer_eoms.jsp"%>
