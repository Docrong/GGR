
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<html:form action="tawRmArticle.do?method=xsave" method="post" styleId="tawRmArticleForm">
	<!--表示对所有的域有-->
	<html:hidden property="id" />
	<table class="formTable middle" align="center">
	<tr>
	<td align="center" colspan="2">
	<h2>
	<eoms:label styleClass="desc" key="tawRmArticleForm.addTitle" />
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
					alt="allowBlank:false,vtext:'${eoms:a2u('请输入物品名称')}'" readonly="true"></html:text>
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
					alt="allowBlank:false,vtext:'${eoms:a2u('请输入物品型号')}'" readonly="true"></html:text>
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
					alt="allowBlank:false,vtype:'number',vtext:'${eoms:a2u('请输入物品数量')}'" readonly="true"></html:text>
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
					alt="allowBlank:false,vtext:'${eoms:a2u('请输入物品描述')}',maxlenth:512" readonly="true"></html:textarea>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				 <html:button property="button.back" styleClass="button" onclick="javascript:window.self.close();">${eoms:a2u('关闭')}</html:button>
				</td>
		</tr>
	</table>
</html:form>
<!--自动生成的Javascript脚本-->
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
