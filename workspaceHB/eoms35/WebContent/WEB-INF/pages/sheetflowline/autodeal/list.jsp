<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">

function addexecute(){
	location.href="../sheetflowline/autoDealsop.do?method=add";
}
function delexecute(){
	var obj=document.getElementsByName('id');
    var ids = '';
    for (var i=0;i<obj.length;i++){
      if(obj[i].checked){
        ids = obj[i].value+","+ids
       }
    }
	location.href="../sheetflowline/autoDealsop.do?method=delete&ids="+ids;
}


</script>


<jsp:include page="/WEB-INF/pages/sheetflowline/autodeal/quicklyquery.jsp"/>       
<bean:define id="url" value="preAllocated.do"/>
	<display:table name="taskList" cellspacing="0" cellpadding="0"
		id="taskList" pagesize="${pageSize}" class="listTable taskList"
		export="true" requestURI="preAllocated.do"
		sort="external" size="total" partialList="true"
		>
   
   		<display:caption media="html"> 
			<span class="map serious">自动处理列表</span>
   		</display:caption>
   		
		<display:column  sortable="true"  headerClass="sortable" class="icon" media="html" >
			<input type="checkbox" id="id" name="id"   value="${taskList.id}" />
		</display:column>
		<display:column property="alarmId" sortable="true"  
			headerClass="sortable" title="网管告警ID"
			 />	
		<display:column property="alarmTitle" sortable="true"  
			headerClass="sortable" title="告警主题"
			 />		
		<display:column  sortable="true"  property="autoDealTask"
			headerClass="sortable" title="自动处理环节"
			/>
		<display:column  sortable="true"  
			headerClass="sortable" title="处理方式"
			 >
			  <c:if test="${taskList.autoDealMode=='1' }">移交下一级</c:if>
			<c:if test="${taskList.autoDealMode=='2' }">回复并归档</c:if>
		</display:column>
	
		<display:column  sortable="true"  headerClass="sortable" title="详情编辑" >
		<a href="../sheetflowline/autoDealsop.do?method=edit&id=${taskList.id}">
            <img src="${app }/images/icons/edit.gif"
                  />
          </a>
		</display:column>	 	 
		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/>
	</display:table>
    <input type="button" id="b1" value="新增"  class="btn" onclick="addexecute();">
	<input type="button" name="b2"  id="b2" value="删除" class="btn" onclick="delexecute();"/>
<%@ include file="/common/footer_eoms.jsp"%>