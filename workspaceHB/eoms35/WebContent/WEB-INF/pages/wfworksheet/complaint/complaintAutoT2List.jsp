<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript">
Ext.onReady(function(){
	colorRows('list-table');
})
function add(){
	location.href = "./complaintauto.do?method=showComplaintAutoT2Page";
}
function openSheet(url){
	if(parent.frames['portal-north']){
		parent.frames['portal-north'].location.href = url;
	}
	else{
		location.href = url;
	}
}
</script>

<bean:define id="url" value="complaintauto.do"/>
<input type="button" value="新增" onclick="add()" class="btn">
	<display:table name="taskList" cellspacing="0" cellpadding="0"
		id="taskList" pagesize="${pageSize}" class="table businessdesignMain"
		export="false" requestURI="complaintauto.do"
		sort="list" size="total" partialList="true">
			
		<display:column  sortable="true" headerClass="sortable" title="一级分类" sortName="complaintType1">
			<a href="./complaintauto.do?method=edit&type=open&id=${taskList.id}">
				<eoms:id2nameDB id="${taskList.complaintType1}" beanId="ItawSystemDictTypeDao"/>
			</a>
		</display:column>
			
		<display:column  sortable="true" headerClass="sortable" title="二级分类" sortName="complaintType2">
			<eoms:id2nameDB id="${taskList.complaintType2}" beanId="ItawSystemDictTypeDao"/>
		</display:column>
			
		<display:column sortable="true" headerClass="sortable" title="三级分类" sortName="complaintType3">
			<eoms:id2nameDB id="${taskList.complaintType3}" beanId="ItawSystemDictTypeDao"/>
		</display:column>

		 <display:column  sortable="true" headerClass="sortable" title="四级分类" sortName="complaintType4">
			<eoms:id2nameDB id="${taskList.complaintType4}" beanId="ItawSystemDictTypeDao"/>
		</display:column>
				
		<display:column  sortable="true" headerClass="sortable" title="五级分类" sortName="complaintType5">
			<eoms:id2nameDB id="${taskList.complaintType5}" beanId="ItawSystemDictTypeDao"/>
		</display:column>
				
		<display:column sortable="true" headerClass="sortable" title="六级分类" sortName="complaintType6">
			<eoms:id2nameDB id="${taskList.complaintType6}" beanId="ItawSystemDictTypeDao"/>
		</display:column>
				
		<display:column  sortable="true" headerClass="sortable" title="七级分类" sortName="complaintType7">
			<eoms:id2nameDB id="${taskList.complaintType7}" beanId="ItawSystemDictTypeDao"/>
		</display:column>
		
		 <display:column property="faultSite" sortable="true"
			headerClass="sortable" title="故障地区" sortName="faultSite"/>

		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/>
		<display:setProperty name="export.rtf" value="false"/>
		<display:setProperty name="export.excel" value="false"/>
	</display:table>
<%@ include file="/common/footer_eoms.jsp"%>