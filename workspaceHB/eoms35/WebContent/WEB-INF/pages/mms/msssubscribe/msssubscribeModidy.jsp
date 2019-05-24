<%@ page language="java" import="java.util.*,com.boco.eoms.commons.mms.base.config.*,com.boco.eoms.commons.mms.mmsreporttemplate.model.*,com.boco.eoms.base.util.StaticMethod" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">

function submitCheck(){
 if(document.forms[0].receivePerson.value==null||document.forms[0].receivePerson.value==''){
    alert( "请选择接收人");
    return false;  
  }
  document.forms[0].submit();
}  

var userByDeptTree='${app}/xtree.do?method=userFromDept';//部门用户树
var v;
Ext.onReady(function(){
	//v = new eoms.form.Validation({form:"theform"});
	//部门用户树
	userByDeptTree = new xbox({
		btnId:'userByDeptTreeBtn',dlgId:'dlg-user',
		treeDataUrl:userByDeptTree,treeRootId:'-1',treeRootText:'人员',treeChkMode:'',treeChkType:'user',
		showChkFldId:'userByDeptName',saveChkFldId:'receivePerson' 
	});
})

</script>
<%
	List mmsreportTemplateList = (List)request.getAttribute("mmsreportTemplateList");
%>
<html:form action="/msssubscribes.do?method=save" styleId="msssubscribeForm" method="post"> 

<fmt:bundle basename="config/applicationResources-mms">

<table class="formTable">
	<caption>
		<div class="header center">定阅彩信报</div>
	</caption>

 <tr class="tr_show">

				<td class="label" nowrap="nowrap" width="15%">
					彩信报名称
				</td>
				<td noWrap width="85%">
				${msssubscribeForm.mmreportName}
				
				</td>

			</tr>
	
		<tr class="tr_show">
         <td class="label" width="15%" nowrap="nowrap" >
             彩信接收人
        </td>
		
		<td width="500" colspan="5">
		<%/*
	  <input type="button" name="clkuser" id="clkuser" value="请选择人员" class="btn"/>
	  <input type="hidden" name="showuser" id="showuser" value="" class="text" alt="allowBlank:false"/>
	  <input type="hidden" name="receivePerson" id="receivePerson"/>
	  <eoms:xbox id="userTree" dataUrl="${app}/xtree.do?method=userFromDept" rootId="-1" rootText="人员树" valueField="receivePerson" handler="clkuser" checktype="user"
		textField="showuser" viewer="true">
	  </eoms:xbox>
	   */%>
	   
	   <input type="button" value="请选择人员" id="userByDeptTreeBtn" class="btn" /><!-- 部门人员选择 -->
		<input type="text"  name="userByDeptName"  value="" id ="userByDeptName" class="text" />
		<input type="hidden" id="receivePerson" value="" name="receivePerson" />
	   
        </td>
    </tr>	
	
 	<tr class="tr_show">
          <td class="label" nowrap="nowrap"  width="15%">
    	          接收时间
    	   </td>
    	       <td width="500" colspan="5">
        <input type="text" name="receiveTime" size="20" value ="${msssubscribeForm.receiveTime }" onclick="popUpCalendar(this, this,null,null,null,true,'<%=StaticMethod.getCurrentDateTime("yyyy-MM-dd")%>');" readonly="readonly" class="text"> 
	  </td>
    </tr>

</table>

</fmt:bundle>
  
<table>
	<tr>
		<td>
			<input type="button" class="btn" value="修改保存"  return onclick=submitCheck() />	
		</td>
	</tr>
</table>
<html:hidden property="id" value="${msssubscribeForm.id}" />
<html:hidden property="mmreportName" value="${msssubscribeForm.mmreportName}" />
<html:hidden property="mmsreport_templateId" value="${msssubscribeForm.mmsreport_templateId}" />
<html:hidden property="receiveMobile" value="${msssubscribeForm.receiveMobile}" />
<html:hidden property="createPerson" value="${msssubscribeForm.createPerson}" />
<html:hidden property="creatTime" value="${msssubscribeForm.creatTime}" />


</html:form>

<%@ include file="/common/footer_eoms.jsp"%>