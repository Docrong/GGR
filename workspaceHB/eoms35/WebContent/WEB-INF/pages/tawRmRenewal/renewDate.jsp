<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="com.boco.eoms.base.util.StaticMethod"%>

<!--根据给定的实例名生成标题 -->
<title><fmt:message key="tawRmTestcardDetail.title"/></title>
<!-- <content tag="heading"><fmt:message key="tawRmTestcardDetail.heading"/></content> -->

<script language="javascript">

function confirmDate(){
	var intendDate = document.getElementById("intendingReturnDate");
	var renewDate = document.getElementById("renewDate");
	if(renewDate.value<=intendDate.value)
    { 
      alert("${eoms:a2u('续借时间必须大于预计归还时间！')}");
      return false;
	}
}

Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawRmRenewalForm'});
});
</script>
<!--对表单的自动生成的处�?-->
<html:form action="tawRmRenewals" method="post" styleId="tawRmRenewalForm"> 

<ul>
		   <!-- <html:hidden property="id"/> -->
		   <html:hidden property="testcardId"/>
		   <html:hidden property="borrowerId"/>
    <!--表示对所有的域有�? -->
	         <br>
		<table class="formTable">		
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmInoutRecordForm.borrowDate" />
				</td> 
				<td width="500" colspan="2">
					<html:text property="borrowDate" styleId="borrowDate"
						styleClass="text medium" readonly="true"/>
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmInoutRecordForm.intendingReturnDate" />
				</td> 
				<td width="500" colspan="2">
					<html:text property="intendingReturnDate" styleId="intendingReturnDate"
						styleClass="text medium" readonly="true"/>
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmInoutRecordForm.borrowerId" />
				</td> 
				<td width="500" colspan="2">
					<html:text property="borrowerName" styleId="borrowerName"
						styleClass="text medium" readonly="true"/>
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmInoutRecordForm.renewDate" />
				</td> 
				<td width="500" colspan="2">
					<html:text property="renewDate" styleId="renewDate"
						styleClass="text medium"
						alt="allowBlank:false,vtext:'${eoms:a2u('请输入续借时间')}'" 
						onclick="popUpCalendar(this, this,'yyyy-mm-dd',-1,-1,false);" readonly="true"/>
				</td>
			</tr>

			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmInoutRecordForm.remark" />
				</td> 
				<td width="500" colspan="2">
					<html:textarea property="remark" styleId="remark"
						styleClass="text medium" rows="5" cols="30"/>
				</td>
			</tr>
		</table>
		
		<br>
		<table>
			<tr>
				<td>
					<html:submit styleClass="button" property="method.renewDate" onclick="return confirmDate()">
            			<fmt:message key="button.save"/>
        			</html:submit>
				</td>
			</tr>
		</table>
</ul>
  <!--自动生成的Javascript脚本-->

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>