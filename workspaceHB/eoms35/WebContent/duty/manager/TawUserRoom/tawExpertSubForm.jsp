<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java"
	import="java.util.List,com.boco.eoms.duty.webapp.form.TawExpertSubForm;"%>
<!--根据给定的实例名生成标题 -->
<style type="text/css">
.formTab2{
	width: 600px;
	border-collapse: collapse;
	font-size: 12px;
	table-layout:fixed;
}

.formTab2 td{
	border: 1px solid #C9DEFA;
	padding: 6px 6px;
	background-color:#FFFFFF;
}

.formTab2 tr.header td{
	background: #cae8ea;
	color:#006699;	
} 
td.label2{
	vertical-align:top;
	background-color:#EDF5FD;
	width:90px;
}
td.label3{
	vertical-align:top;
	
	width:90px;
}
td.content{
	vertical-align:top;
	width:35%;
}
td.max{
	width:90%;
}
td.checkColumn{
	width:10px;
}
</style>
<title>专家评价</title>
<html:form action="TawExpertInfo.do" method="post"
	styleId="tawExpertInfoForm">
	<html:hidden property="id" />
	<table class="formTable middle" align="center">

		<tr>
			<td colspan="6" align="center">
				<h2>
					专家评价
				</h2>
			</td>
		</tr>
		
		<tr>
			<td class="label" nowrap="nowrap" align="right">
					专家姓名
			</td>
			<td colspan="2">
			
					${tawExpertInfoForm.expertName}
			</td>
			<td class="label" nowrap="nowrap" align="right">
					办公室电话
			</td>
			<td colspan="2">
				${tawExpertInfoForm.telephone}
			</td>
		</tr>
		<tr>
			<td class="label" nowrap="nowrap" align="right">
				手机
			</td>
			<td colspan="2">
			${tawExpertInfoForm.mobile}
						
			</td>
			<td class="label" nowrap="nowrap" align="right">
				邮箱
			</td>
			<td colspan="2">
				${tawExpertInfoForm.email}
			</td>
		</tr>
		<tr>
			<td class="label" nowrap="nowrap" align="right">
				  工作地址
			</td>
			<td colspan="5">
				${tawExpertInfoForm.adress}
			</td>
			
		</tr>
		<tr>
			<td class="label" nowrap="nowrap" align="center" >
				特长
			</td>
				
			<td colspan="5" >
			<html:textarea property="beGoodAt" styleId="beGoodAt"
						styleClass="text medium" rows="3" cols="40" />
				
			</td>
			
		</tr>
		<tr>
			<td class="label" nowrap="nowrap"   align="center" >
				简历
				</td>
				
				<td colspan="5">

					<html:textarea property="resume" styleId="resume"
						styleClass="text medium" rows="3" cols="40" />
				</td>
		</tr>
	</table>
</html:form>
<html:form action="TawExpertSub.do?method=xsaveSub" method="post"
	styleId="TawExpertSubForm">

	<%
	  
		List list = (List) request.getAttribute("subList");
		for (int i = 0; i < list.size(); i++) {
			TawExpertSubForm sub = (TawExpertSubForm) list.get(i);
	%>
	<br>
	<table class="formTab2" align="center">
	<tr>
	<td align="center" class="label3" >
	故障处理评价
	</td>
	</tr>
	<tr>
		<td class="label2" nowrap="nowrap" align="right">
			故障描述
		</td>
		<td colspan="3">
		<html:textarea rows="3" cols="40" value="<%=sub.getMalfunctionName() %>"
						styleClass="text medium" property="malfunctionName"/>
		</td>
		</tr>
		<tr>
		<td class="label2" nowrap="nowrap" align="right">
			故障解决结果
		</td>
		<td colspan="3">
		<html:textarea rows="3" cols="40" value="<%=sub.getMalfunctionDeal()%>"
						styleClass="text medium" property="malfunctionDeal"/>
		
		</td>
		
	</tr>
	<tr>
		<td class="label2" nowrap="nowrap" align="right">
			评价
		</td>
		<td colspan="3">
		<eoms:dict key="dict-duty" dictId="expertState" itemId="<%=sub.getScore()%>" beanId="id2nameXML" />
		
		</td>
	</tr>
	<tr>
		<td class="label2" nowrap="nowrap" align="right">
			添加人
		</td>
		<td colspan="3">
		<eoms:id2nameDB id="<%=sub.getAddMan() %>"
						beanId="tawSystemUserDao" />
		
		</td>
		</tr>
		<tr>
		<td class="label2" nowrap="nowrap" align="right">
			添加时间
		</td>
		<td colspan="3">
		<%=sub.getAddTime() %>
		</td>
	</tr>
	</table>
	
	<%
	}
	%>
<br>

	<table class="formTab2" align="center">
	<% String mainId = (String) request.getAttribute("mainId"); %>
	<html:hidden property="mainId" value="<%=mainId%>"/>
	<tr>
	<td align="center" class="label3" >
	故障处理评价
	</td>
	</tr>
		<tr>
			<td class="label2" nowrap="nowrap" align="right">
				故障描述
			</td>
			<td colspan="3">
			<html:textarea property="malfunctionName" styleId="malfunctionName" alt="allowBlank:false,vtext:'请输入故障描述'"
						styleClass="text medium"  rows="3" cols="40"/>
			<br></td></tr>
			<tr>
			<td class="label2" nowrap="nowrap" align="right">
				故障解决结果
			</td>
			<td colspan="3">
				<html:textarea property="malfunctionDeal" styleId="malfunctionDeal" rows="3" cols="40"
						styleClass="text medium"  alt="allowBlank:false,vtext:'请输入故障解决结果'"/>
			<br></td>
		</tr>
		<tr>
		<td class="label2" nowrap="nowrap" align="right">
				评价
			 </td>
			<td colspan="2">
			<eoms:dict key="dict-duty" dictId="expertState" beanId="selectXML" alt="allowBlank:false,vtext:'请选择评价'"
                 defaultId="" isQuery="false"  selectId="score"/>
			</td>
			<td align="center" >
			<html:submit styleClass="button" property="method.xsave"> 
						提交
					</html:submit>
			</td>
		</tr>
	</table>
<html:hidden property="mainId" value="${id}"/>

	<!--自动生成的Javascript脚本-->
<script type="text/javascript">    
   Ext.onReady(function() {
	v = new eoms.form.Validation({form:'TawExpertSubForm'});
}); 
  </script>
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>