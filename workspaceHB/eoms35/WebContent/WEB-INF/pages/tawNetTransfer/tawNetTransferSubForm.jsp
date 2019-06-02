<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java"
	import="java.util.List,com.boco.eoms.duty.webapp.form.TawNetTransferSubForm;"%>
<!--根据给定的实例名生成标题 -->
<title>网调信息</title>
<html:form action="TawNetTransfer.do" method="post"
	styleId="tawNetTransferForm">
	<html:hidden property="state" />
	<html:hidden property="id" />
	<table class="formTable middle" align="center">

		<tr>
			<td colspan="6" align="center" >
				<h2>
					网调信息
				</h2>
			</td>
		</tr>
		<tr>
			<td class="label" align="right">
				发起部门
			</td>
			<td colspan="2">
			<eoms:id2nameDB id="${tawNetTransferForm.originateDept }" beanId="tawSystemDeptDao"/>
				
			</td>
			<td class="label" align="right">
				发文编号
			</td>
			<td colspan="2">
				${tawNetTransferForm.dispatchNum}
			</td>
		</tr>
		<tr>
			<td class="label"  align="right">
				责任人
			</td>
			<td colspan="2">
			${tawNetTransferForm.dutyMan}
						
			</td>
			<td class="label"  align="right">
				责任人联系方式
			</td>
			<td colspan="2">
				${tawNetTransferForm.contact}
			</td>
		</tr>
		<tr>
			<td class="label"  align="right">
				专业名称
			</td>
			<td colspan="2">
			<eoms:dict key="dict-duty" dictId="speciality" itemId="${tawNetTransferForm.speciality}" beanId="id2nameXML" />
				
			</td>
			<td class="label" align="right">
				发起人
			</td>
			<td colspan="2">
			<eoms:id2nameDB id="${tawNetTransferForm.originater}"
						beanId="tawSystemUserDao" />
			</td>
		</tr>
		<tr>
			<td class="label"  align="right">
				设备所属部门
			</td>
			<td colspan="2">
			<eoms:id2nameDB id="${tawNetTransferForm.equipmentDept}" beanId="tawSystemDeptDao"/>
			</td>
			<td class="label"  align="right">
				涉及网元
			</td>
			<td colspan="2">
				${tawNetTransferForm.referNet}
			</td>
		</tr>
		<tr>
			<td class="label"  align="right">
				主题
			</td>
			<td colspan="5">
				${tawNetTransferForm.title}
			</td>
		</tr>
		<tr>
			<td class="label"  align="right">
				网调内容
			</td>
			<td colspan="5">
				<html:textarea property="content" styleId="content"
					styleClass="text medium" readonly="true" cols="40" rows="3" />
			</td>
		</tr>
		<logic:notEmpty name="tawNetTransferForm" property="remark">
			<tr>
				<td class="label"  align="right">
					备注
				</td>
				<td colspan="5">
					<html:textarea property="remark" styleId="remark" readonly="true"
						styleClass="text medium" cols="40" rows="3" />
				</td>
			</tr>
		</logic:notEmpty>
		<logic:notEmpty name="tawNetTransferForm" property="accessory">
			<tr>
				<td class="label"  align="right">
					附件
					<html:errors property="accessory" />
				</td>
				<td colspan="5">
					<eoms:attachment name="tawNetTransferForm" property="accessory"
						scope="request" idField="accessories" appCode="netTransfer"
						viewFlag="Y" />
				</td>
			</tr>
		</logic:notEmpty>
	</table>
</html:form>
<html:form action="TawNetTransferSub.do?method=xsaveSub" method="post"
	styleId="TawNetTransferSubForm">

	<%
	  
		List list = (List) request.getAttribute("subList");
		for (int i = 0; i < list.size(); i++) {
			TawNetTransferSubForm sub = (TawNetTransferSubForm) list.get(i);
	%>
	<br>
	<table class="formTable middle" align="center">
	<tr>
		<td class="label" nowrap="nowrap" align="right"> 
			预计开始时间
		</td>
		<td>
			<%=sub.getPreDate() %>
		</td>
		<td class="label" nowrap="nowrap" align="right"> 
			完成时间
		</td>
		<td>
		<%=sub.getFinishDate() %>
		</td>
		
	</tr>
	<tr>
		<td class="label" nowrap="nowrap" align="right">
			持续时间
		</td>
		<td>
		<%=sub.getLast() %>
		</td>
		<td class="label" nowrap="nowrap" align="right">
			状态
		</td>
		<td>
		<eoms:dict key="dict-duty" dictId="netSubState" itemId="<%=sub.getState() %>" beanId="id2nameXML" />
		
		</td>
	</tr>
	<tr>
		<td class="label" nowrap="nowrap" align="right">
			添加人
		</td>
		<td>
		<eoms:id2nameDB id="<%=sub.getAddMan() %>"
						beanId="tawSystemUserDao" />
		
		</td>
		<td class="label" nowrap="nowrap" align="right">
			添加时间
		</td>
		<td>
		<%=sub.getAddTime() %>
		</td>
	</tr>
	</table>
	
	<%
	}
	%>
<br>
<% String hasSub = (String)request.getAttribute("state"); 
   if(!hasSub.equals("2")){
%>
	<table class="formTable middle" align="center">
	
		<tr>
			<td class="label" nowrap="nowrap" align="right">
				预计开始时间
			</td>
			<td>
			<html:text property="preDate" styleId="preDate" alt="allowBlank:false,vtext:'请选择开始时间'"
						styleClass="text medium" readonly="true"
						onclick="popUpCalendar(this, this,null,null,null,true,-1); "/>
			<br></td>
			
			<td class="label" nowrap="nowrap" align="right">
				完成时间
			</td>
			<td>
				<html:text property="finishDate" styleId="finishDate"
						styleClass="text medium" readonly="true" alt="allowBlank:false,vtext:'请选择完成时间'"
						onclick="popUpCalendar(this, this,null,null,null,true,-1); "/>
			<br></td>
		</tr>
		<tr>
		<td class="label" nowrap="nowrap" align="right">
				持续时间
			</td>
			<td>
				<html:text property="last" styleId="last"
						styleClass="text medium" alt="allowBlank:false,vtype:'number',vtext:'请输入数据格式的持续时间',maxLength:64"/>
			<br></td>
			<td class="label" nowrap="nowrap" align="right">
			 状态
			 </td>
			<td>
			<eoms:dict key="dict-duty" dictId="netSubState" beanId="selectXML" alt="allowBlank:false,vtext:'请选择状态'"
                 defaultId="" isQuery="false"  selectId="state"/>
			<br></td>
		</tr>
		<tr>
		<td colspan="4" align="center">
		<html:submit styleClass="button" property="method.xsave" onclick="draft();"> 
						提交&nbsp; 
					</html:submit>
					</td>
	</table>
<html:hidden property="mainId" value="${id}"/>
<html:hidden property="mainTitle" value="${mainTitle}"/>
<%} %>

	<!--自动生成的Javascript脚本-->
<script type="text/javascript">    
   Ext.onReady(function() {
	v = new eoms.form.Validation({form:'TawNetTransferSubForm'});
}); 
  </script>
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>