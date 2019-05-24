<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript">

function combineSheet(){
	
	var mainCheckIds = document.getElementsByName("mainCheckId");
	var main = "";
	var m = 0;
	if (mainCheckIds.length > 0) {
		for(var i = 0; i < mainCheckIds.length; i++) {	
			if (mainCheckIds[i].checked == true) {
				main = main + mainCheckIds[i].value+",";			
				m++;
			} 
		}
	}
	if(""==main){
		Ext.Msg.alert("提示","请勾选你要批量操作的工单!");
		return;
	}
	
	var x = '${listSize}';		
	if(m>x){
		Ext.Msg.alert("提示","超出最大的批量接单数，请将批量接单的工单数量少于"+x);
		return;
	}
	
	if(confirm("即将批量确认受理"+m+"张工单,是否提交？")){			
		Ext.Ajax.request({
			url : "commonfault.do?method=performBatchClaimSheet",
			method : 'POST',
			params:{ids:main},
			success : function(x){
				var data = eoms.JSONDecode(x.responseText);	
				Ext.each(data,function(d){
					if(d.status=='0'){
						window.location="<html:rewrite page='/commonfault.do?method=showListAll'/>";	
					}else{
						Ext.each(d.data,function(o){
							Ext.MessageBox.alert('提示信息',o.text);
						});	
						
					}
				});	
					
				
			}
		});
	}		
}
</script>
<bean:define id="url" value="commonfault.do" />
<display:table name="taskList" cellspacing="0" cellpadding="0"
	id="taskList" pagesize="${pageSize}" class="listTable taskList"
	export="false" requestURI="commonfault.do" sort="list"
	size="total" partialList="true"
	decorator="com.boco.eoms.sheet.commonfault.webapp.action.BatchClaimSheetDisplaytagDecoratorHelper">

	<display:column sortable="false" property="id" media="html" headerClass="sortable" 
		title="" />
	<display:column property="sheetId" sortable="true" headerClass="sortable"  
		title="工单流水号" />
	<display:column property="title" sortable="true" headerClass="sortable"
		title="工单主题" />
	<display:column property="sendTime" sortable="true" headerClass="sortable" 
		title="派单时间" format="{0,date,yyyy-MM-dd HH:mm:ss}" />
	<display:column property="mainNetName" sortable="true" headerClass="sortable"
		title="网元名称" />
	<display:column  sortable="true" sortName="mainNetSortOne"
		headerClass="sortable" title="网络一级分类">
		<eoms:id2nameDB id="${taskList.mainNetSortOne}" beanId="ItawSystemDictTypeDao"/>
	</display:column>
	<display:column  sortable="true" sortName="mainNetSortTwo"
		headerClass="sortable" title="网络二级分类">
		<eoms:id2nameDB id="${taskList.mainNetSortTwo}" beanId="ItawSystemDictTypeDao"/>
	</display:column>
	<display:column  sortable="true" sortName="mainNetSortThree"
		headerClass="sortable" title="网络三级分类">
		<eoms:id2nameDB id="${taskList.mainNetSortThree}" beanId="ItawSystemDictTypeDao"/>
	</display:column>		
</display:table>
<c:if test="${total!=0 }">
<input type="button" class="btn" onclick="javascript:combineSheet();" value="受理" />
<input type="button" class="btn" onclick="javascript:window.location='<html:rewrite page='/commonfault.do?method=showBatchAcceptSheetList&psize=f'/>'" value="每页50张"/>
<input type="button" class="btn" onclick="javascript:window.location='<html:rewrite page='/commonfault.do?method=showBatchAcceptSheetList&psize=h'/>'" value="每页100张"/>
</c:if>

<%@ include file="/common/footer_eoms.jsp"%>
