<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="com.boco.eoms.base.util.StaticMethod"%>

<!--根据给定的实例名生成标题 -->
<title><fmt:message key="tawRmTestcardDetail.title"/></title>
<!-- <content tag="heading"><fmt:message key="tawRmTestcardDetail.heading"/></content> -->
<%
	String[] ids = (String[]) request.getAttribute("ids");
	String idStr="";
	for (int i = 0; i < ids.length-1; i++) {
		String id=ids[i];
		idStr=idStr+id+",";
	}
	idStr=idStr+ids[ids.length-1];
	String borrowDate = StaticMethod.getLocalStringNoHours();
%>

<script language="javascript">

var date='<%=borrowDate%>';
function confirmDate(){
	var obj = document.getElementById("intendingReturnDate");
	if(obj.value<=date)
    { 
      alert("${eoms:a2u('预计归还时间必须大于当前时间！')}");
      return false;
	}
}

Ext.onReady(function() {
	var	borrowerTreeAction='${app}/xtree.do?method=userFromDept';
	borrowerTree = new xbox({
		btnId:'borrowerTreeBtn',dlgId:'dlg-huser',	
		treeDataUrl:borrowerTreeAction,treeRootId:'-1',treeRootText:'${eoms:a2u("人员列表")}',treeChkMode:'single',treeChkType:'user',
		showChkFldId:'borrowerName',saveChkFldId:'borrowerId' 
	});
	v = new eoms.form.Validation({form:'tawRmInoutRecordForm'});
});
</script>
<!--对表单的自动生成的处�?-->
<html:form action="tawRmInoutRecords" method="post" styleId="tawRmInoutRecordForm"> 

<ul>
		   <html:hidden property="borrowDate" value="<%=borrowDate%>"/>
		   <html:hidden property="ids" value="<%=idStr%>"/>
    <!--表示对所有的域有�? -->
	         <br>
		<table class="formTable">		
			<tr>
				<html:hidden property="borrowerId" styleId="borrowerId" styleClass="text medium" />
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmInoutRecordForm.borrowerId" />
				</td> 
				<td width="500" colspan="2">
					<html:text property="borrowerName" styleId="borrowerName"
						styleClass="text medium" readonly="true" 
						alt="allowBlank:false,vtext:'${eoms:a2u('请输入接收人')}'"/>
					<input type="button" value="${eoms:a2u('人员列表')}" id="borrowerTreeBtn" class="btn" />
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmInoutRecordForm.intendingReturnDate" />
				</td> 
				<td width="500" colspan="2">
					<html:text property="intendingReturnDate" styleId="intendingReturnDate"
						styleClass="text medium"
						alt="allowBlank:false,vtext:'${eoms:a2u('请输入预计归还时间')}'" 
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
					<html:submit styleClass="button" property="method.outStorage" onclick="return confirmDate()">
            			<fmt:message key="button.save"/>
        			</html:submit>
				</td>
			</tr>
		</table>
</ul>
  <!--自动生成的Javascript脚本-->

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>