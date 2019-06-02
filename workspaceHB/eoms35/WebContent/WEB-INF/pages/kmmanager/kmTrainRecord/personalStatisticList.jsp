<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script language = 'javascript'>
	function period(){
	    var startDate = document.getElementById("startDate").value;
	    var endDate = document.getElementById("endDate").value;
	    var trainUser = document.getElementById("trainUser").value;
	    if(startDate == ""){
	        alert("请选择开始时间！");
	        return false;	    
	    }
	    if(startDate > endDate){
	        alert("开始时间要小于结束时间！");
	        return false;
	    }
	    location.href='${app}/kmmanager/trainRecords.do?method=searchPersonal&startDate='+startDate+'&endDate='+endDate+'&trainUser='+trainUser;
	}
</script>

<eoms:xbox id="tree1" dataUrl="${app}/xtree.do?method=userFromDept" 
	  	rootId="1" 
	  	rootText='人员树' 
	  	valueField="trainUser" handler="trainUserName"
		textField="trainUserName"
		checktype="user" single="true"		
	  ></eoms:xbox>	 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<content tag="heading">
	<b>个人培训记录统计</b>
</content>

	<br><br>

	<tr align = 'center'>	
        <td>
            统计时间：
            从&nbsp;&nbsp;<input type="text" name="startDate" id="startDate" value="${startDate}" size="10" onclick="popUpCalendar(this,this,null,null,null,false,-1);" readonly="true" class="text" alt="allowBlank:false,vtext:'请选择开始时间...'" />&nbsp;&nbsp;
            到&nbsp;&nbsp;<input type="text" name="endDate"   id="endDate"   value="${endDate}"   size="10" onclick="popUpCalendar(this,this,null,null,null,false,-1);" readonly="true" class="text" alt="allowBlank:false,vtext:'请选择结束时间...'" />&nbsp;&nbsp;
            姓名(可选)：<input type="text"   id="trainUserName" name="trainUserName" class="text" readonly="readonly" value='<eoms:id2nameDB id="${trainUser}" beanId="tawSystemUserDao" />' />
					<input type="hidden" id="trainUser"   name="trainUser" value="${trainUser}" />
          <a style="CURSOR:hand" onclick="period()">统计</a>&nbsp;&nbsp;
	    </td>		
	</tr>

	<display:table name="trainRecordList" cellspacing="0" cellpadding="0"
		id="trainRecordList" pagesize="${pageSize}" class="table trainRecordList"
		export="false"
		requestURI="${app}/kmmanager/trainRecords.do?method=searchPersonal"
		sort="list" partialList="true" size="resultSize">


	<display:column sortable="true" headerClass="sortable" titleKey="trainPersonalStatistic.trainUser">
		<eoms:id2nameDB id="${trainRecordList.trainUser}" beanId="tawSystemUserDao" />
	</display:column>
	


	<display:column property="trainCount" sortable="true"
			headerClass="sortable" titleKey="trainPersonalStatistic.trainCount"  paramId="id" paramProperty="id"/>

	<display:column property="timeCount" sortable="true"
			headerClass="sortable" titleKey="trainPersonalStatistic.timeCount"  paramId="id" paramProperty="id"/>

		<display:setProperty name="paging.banner.item_name" value="trainRecord" />
		<display:setProperty name="paging.banner.items_name" value="trainRecords" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>