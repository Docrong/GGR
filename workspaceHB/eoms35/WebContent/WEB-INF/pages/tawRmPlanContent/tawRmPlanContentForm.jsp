<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import ="com.boco.eoms.workplan.util.TawwpUtil"%>

<!--根据给定的实例名生成标题 -->
<title><fmt:message key="tawRmPlanContentDetail.title"/></title>
<content tag="heading">
<fmt:message key="tawRmPlanContentDetail.heading"/>
</content>
<%
	String memoId = (String) request.getParameter("id");
%>

<script language="javascript">

function confirmDelete(){
if ( confirm('${eoms:a2u("是否要删除此工作计划")}') ){
	return true;
	}else{
	return false;
	}
}

Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawRmPlanContentForm'});
});
</script>
<!--对表单的自动生成的处�?-->
<html:form action="tawRmPlanContent" method="post" styleId="tawRmPlanContentForm"> 
<ul>

    <!--表示对所有的域有�? -->
		<html:hidden property="id" />
		<html:hidden property="deptId" />
		<html:hidden property="userId" />
		<html:hidden property="roomId" />
		<html:hidden property="workSerial" />
		<br>


		<table class="formTable">

		    <tr>
		      <td width="100" class="label">
		        <bean:message key="label.year"/>
		      </td>
		      <td width="200">
		      	<eoms:dict key="dict-plancontent" dictId="yearflag" beanId="selectXML"
						isQuery="false" defaultId="${tawRmPlanContentForm.yearflag}"
						selectId="yearflag" alt="allowBlank:false" />
		      </td>
		
		      <td width="100" class="label">
		        <bean:message key="label.month"/>
		      </td>
		      <td width="200">
		      	<eoms:dict key="dict-plancontent" dictId="monthflag" beanId="selectXML"
						isQuery="false" defaultId="${tawRmPlanContentForm.monthflag}"
						selectId="monthflag" alt="allowBlank:false" />
		      </td>
		      
		    </tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmPlanContentForm.contentName" />
				</td>
				<td width="500" colspan="3">
					<html:textarea property="contentName" styleId="contentName"
						styleClass="text medium" rows="5" cols="50"
						alt="allowBlank:false,vtext:'${eoms:a2u('请输入作业内容')}'" />
				</td>
			</tr>

			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmPlanContentForm.monthplanName" />
				</td> 
				<td width="500" colspan="3">
					<html:text property="monthplanName" styleId="monthplanName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:'${eoms:a2u('请输入月度计划名称')}'" />
				</td>
			</tr>
		</table>
		
		<br>
		<table>
			<tr>
				<td>
					<html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            			<fmt:message key="button.save"/>
        			</html:submit>
				</td>
				
				<%
				if (memoId != null) {
				%>
				<td>
					<html:submit styleClass="button" property="method.delete"
						onclick="bCancel=true; return confirmDelete()">
						<fmt:message key="button.delete" />
					</html:submit>
				</td>

				<%
				}
				%>
			</tr>
		</table>
   <!--  <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>
        <!--用自动生成的参数调用Javascript --> 
       <!--  <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawRmPlanContent')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>-->
</ul>
  <!--自动生成的Javascript脚本-->

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>