<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/trainRecords.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>


<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'trainRecordForm'});
});
</script>

<eoms:xbox id="tree1" dataUrl="${app}/xtree.do?method=userFromDept" 
	  	rootId="1" 
	  	rootText='人员树' 
	  	valueField="trainUser" handler="trainUserName"
		textField="trainUserName"
		checktype="user" single="true"		
	  ></eoms:xbox>	  
	
	
<html:form action="/trainRecords.do?method=searchX" styleId="trainRecordForm" method="post"> 
<table align="center">
  <tr >
  	
  	<td>
		培训查询时间段：&nbsp;<font color='red'>*</font>
	</td>
	<td>
		<input type="text" size="20" readonly="true" class="text" 
			          name="trainBeginTime" id=""trainBeginTime"" 
			          onclick="popUpCalendar(this,this,null,null,null,true,-1);"
			          alt="vtype:'lessThen',link:'trianEndTime',allowBlank:false,vtext:'请选择开始时间...'" 
			          value="${trainBeginTime }"/>
	</td>
	<td>
		至
	</td>
	<td >
		<input type="text" size="20" readonly="true" class="text" 
			          name="trianEndTime"  id="trianEndTime" 
			          onclick="popUpCalendar(this,this,null,null,null,true,-1);"
			          alt="vtype:'moreThen',link:'trainBeginTime',allowBlank:false,vtext:'请选择结束时间...'" 
			          value="${trianEndTime }"/>
	</td>
    <td>
		&nbsp;&nbsp;&nbsp;&nbsp;培训人
	</td>
	<td>
		<input type="text"   id="trainUserName" name="trainUserName" class="text" readonly="readonly" value='<eoms:id2nameDB id="${trainRecordForm.trainUser}" beanId="tawSystemUserDao" />' />
		<input type="hidden" id="trainUser"   name="trainUser" value="${trainRecordForm.trainUser}" />
	</td>
	
	 <td>
		 &nbsp;&nbsp;培训名称
	</td>
	 <td>
		<input type="text" class="text"  id="trainName" name="trainName" value="${trainRecordForm.trainName}" />	
	</td>
	
	 <td>
		 <input type="submit" class="btn" value="查询"/>		
	</td>
  </tr>
  
</table>
</html:form>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
	<display:table name="trainRecordList" cellspacing="0" cellpadding="0"
		id="trainRecordList" pagesize="${pageSize}" class="table trainRecordList"
		export="false"
		requestURI="${app}/kmmanager/trainRecords.do?method=search"
		sort="list" partialList="true" size="resultSize">
	
	<display:column property="trainName" sortable="true"
			headerClass="sortable" titleKey="trainRecord.trainName"  paramId="id" paramProperty="id"/>
	
	<display:column sortable="true" headerClass="sortable" titleKey="trainRequire.trainUser">
		<eoms:id2nameDB id="${trainRecordList.trainUser}" beanId="tawSystemUserDao" />
	</display:column>
	
	<display:column  sortable="true" headerClass="sortable" titleKey="trainRecord.trainDept" >
			<eoms:id2nameDB id="${trainRecordList.trainDept}" beanId="tawSystemDeptDao" />
	</display:column>
	
	<display:column property="trainAddress" sortable="true"
			headerClass="sortable" titleKey="trainRecord.trainAddress"  paramId="id" paramProperty="id"/>

	<display:column property="trainDate" sortable="true"  format="{0,date,yyyy-MM-dd HH:mm:ss}"
			headerClass="sortable" titleKey="trainRecord.trainDate"  paramId="id" paramProperty="id"/>

	<display:column property="trainTime" sortable="true" 
			headerClass="sortable" titleKey="trainRecord.trainTime"  paramId="id" paramProperty="id"/>

	<display:column property="trainVender" sortable="true"
			headerClass="sortable" titleKey="trainRecord.trainVender"  paramId="id" paramProperty="id"/>

	<display:column property="trainType" sortable="true"
			headerClass="sortable"  titleKey="trainRecord.trainType"  paramId="id" paramProperty="id"/>

	
	<display:column property="trainCenter" sortable="true"
			headerClass="sortable" titleKey="trainRecord.trainCenter"  paramId="id" paramProperty="id"/>
	
	<display:column property="trainUnit" sortable="true"
			headerClass="sortable" titleKey="trainRecord.trainUnit"  paramId="id" paramProperty="id"/>
			
	

	<display:column title="查看" headerClass="imageColumn">
		    <a href="javascript:var id = '${trainRecordList.id}';
		                        var url='${app}/kmmanager/trainRecords.do?method=detail&id=' + id ;
		                        location.href=url">
		       <img src="${app}/images/icons/search.gif"/></a>
	</display:column> 

		<display:setProperty name="paging.banner.item_name" value="trainRecord" />
		<display:setProperty name="paging.banner.items_name" value="trainRecords" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>