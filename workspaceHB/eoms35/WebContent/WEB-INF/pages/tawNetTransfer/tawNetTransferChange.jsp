<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<!--根据给定的实例名生成标题 -->
<title>网调信息
</title>
	<html:form action="TawNetTransfer.do?method=xsave" method="post"
		styleId="TawNetTransferForm">
        <html:hidden property="state" />
		<html:hidden property="id" />
		<table class="formTable middle" align="center">
			<tr>
				<td colspan="6" align="center">
					<h2>
						网调信息
					</h2>
				</td>
			</tr>
			<tr>
				<td class="label" nowrap="nowrap" align="right">
					 发起部门
				</td>
				<td colspan="2">
					<html:errors property="originateDept" />
					<html:text property="originateDept" styleId="originateDept"
						styleClass="text medium"
						alt="allowBlank:false,vtext:'请输入发起部门',maxLength:64" />
				</td>
				<td class="label" nowrap="nowrap" align="right">
					发文编号
					<html:errors property="dispatchNum" />
				</td>
				<td colspan="2">
					<html:text property="dispatchNum" styleId="dispatchNum"
						styleClass="text medium" 
						alt="allowBlank:false,vtext:'请输入发文编号',maxLength:128" />
				</td>
			</tr>

			<tr>
				<td class="label" nowrap="nowrap" align="right">
					责任人
					<html:errors property="dutyMan" />
				</td>
				<td colspan="2">
					<html:text property="dutyMan" styleId="dutyMan"
						styleClass="text medium"
						alt="allowBlank:false,vtext:'请输入责任人',maxLength:64" />
				</td>
				<td class="label" nowrap="nowrap" align="right">
                       责任人联系方式
					<html:errors property="contact" />
				</td>
				<td colspan="2">
					<html:text property="contact" styleId="contact"
						styleClass="text medium"
						alt="allowBlank:false,vtype='number',vtext:'请输入责任人联系方式',maxLength:64" />
				</td>
			</tr>
			<tr>
				<td class="label" nowrap="nowrap" align="right">
					专业名称
					<html:errors property="speciality" />
				</td>
				<td colspan="2">
					<html:text property="speciality" styleId="speciality"
						styleClass="text medium"
						alt="allowBlank:false,vtext:'请输入专业名称',maxLength:64" />
				</td>
				<td class="label" nowrap="nowrap" align="right">
					状态
					<html:errors property="state" />
				</td>
				<td colspan="2">
					<html:text property="state" styleId="state"
						styleClass="text medium" readonly="true" />
				</td>
				</tr>
			<tr>
				<td class="label" nowrap="nowrap" align="right">
					设备所属部门
				</td>
				<td colspan="2">
					<html:errors property="equipmentDept" />
					<html:text property="equipmentDept" styleId="equipmentDept"
						styleClass="text medium"
						alt="allowBlank:false,vtext:'请输入设备所属部门',maxLength:512" />
				</td>
				<td class="label" nowrap="nowrap" align="right">
					涉及网元
					<html:errors property="referNet" />
				</td>
				<td colspan="2">
					<html:text property="referNet" styleId="referNet"
						styleClass="text medium"
						alt="allowBlank:false,vtext:'请输入涉及网元',maxLength:128" />
				</td>
			</tr>
			<tr>
				<td class="label" nowrap="nowrap" align="right">
					主题
					<html:errors property="title" />
				</td>
				<td colspan="5">

					<html:text property="title" styleId="title"
						styleClass="text medium"
						alt="allowBlank:false,vtext:'请输入主题',maxLength:128" />
				</td>
			</tr>
			<tr>
				<td class="label" nowrap="nowrap" align="right">
					网调内容
					<html:errors property="content" />
				</td>
				<td colspan="5">

					<html:textarea property="content" styleId="content"
						styleClass="text medium"
						alt="allowBlank:false,vtext:'请输入网调内容',maxLength:512" cols="40" rows="3" />
				</td>
			</tr>
<tr>
				<td class="label" nowrap="nowrap" align="right">
					备注
					<html:errors property="remark" />
				</td>
				<td colspan="5">
					<html:textarea property="remark" styleId="remark"
						alt="allowBlank:true,vtext:'请输入备注',maxLength:512"
						styleClass="text medium" cols="40" rows="3" />
				</td>
			</tr>
			<tr>
				<td class="label" nowrap="nowrap" align="right">
					附件
					<html:errors property="accessory" />
				</td>
				<td colspan="5">
					<eoms:attachment name="tawNetTransferForm" property="accessory"
						scope="request" idField="accessories" appCode="netTransfer"
						viewFlag="N" />
				</td>
			</tr>
			<tr>
				<td colspan="6" align="center">
				
					<html:submit styleClass="button" property="method.xsave">
						保存修改
					</html:submit>
					&nbsp;&nbsp;
					<logic:notEqual value="1" name="tawNetTransferForm" property="state">
					<html:submit styleClass="button" property="method.xsave"
						onclick="draft('1');">
						发布
					</html:submit>
					&nbsp;&nbsp;
					</logic:notEqual>
					<html:reset styleClass="button" onclick="bCancel=true">
						<fmt:message key="button.reset" />
					</html:reset>
					&nbsp;&nbsp;
					<html:submit styleClass="button" property="method.xdelete"
						onclick="javascript:return deleteF();">
						删除
					</html:submit>
				</td>
			</tr>
		</table>

	</html:form>
	<!--自动生成的Javascript脚本-->

	<script type="text/javascript">
    
   Ext.onReady(function() {
	v = new eoms.form.Validation({form:'TawNetTransferForm'});
});
   
    function draft(w){
    if(w=='0'){
    document.getElementById('state').value="0";
    }
    else{
     document.getElementById('state').value="1";
    }
    };
    
    function deleteF(){
    var msg ="确认删除此记录？";
		if (confirm(msg)==true){
			 document.forms[0].action="TawNetTransfer.do?method=xdelete";
			return true;
		}else{
			return false;
		}
   
    };
  </script>
<%@ include file="/common/footer_eoms.jsp"%>
