<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
 
<script>


function onsubSave(struts){
 	    var begin = document.forms[0].begintime.value;
 	  	var end = document.forms[0].endtime.value;
 	   
 	    var happentime = document.forms[0].happentime.value;
 	   
    	if(happentime==""){
    		alert("${eoms:a2u('请填写发生时间!')}");
    		return false; 
    	}
       if(comptime(begin,happentime)<0){
    		alert("${eoms:a2u('发生时间不能个小于主事件的开始时间')}");
    		return false; 
    	}if(comptime(end,happentime)>0){
    		alert("${eoms:a2u('发生时间不能大于主事件的结束时间')}");
    		return false; 
    	}if(document.forms[0].content.value.length >300){
    		alert("${eoms:a2u('内容长度不能大于300字节')}")
    		return false;
    	}
    	var worksheetid = document.forms[0].worksheetid.value;
    	//if(worksheetid==""){
    		//alert("${eoms:a2u('请选择紧急程度!')}");
    		//return false; 
    	//}
    	var content = document.forms[0].content.value;
    	if(content==""){
    		alert("${eoms:a2u('请填写内容!')}");
    		return false; 
    	} 
    	var id = document.forms[0].id.value;
     	document.forms[0].id.value = happentime;
    	document.forms[0].happentime.value = happentime;
    	document.forms[0].worksheetid.value = worksheetid;
    	document.forms[0].content.value = content;
    	document.forms[0].action ="${app}/duty/dutyevent.do?method=saveSub&struts="+struts;
    	document.forms[0].submit();
        return true;
  	  
  	}
  	
function comptime(beginTime,endTime){

		var beginTimes=beginTime.substring(0,10).split('-');
		var endTimes=endTime.substring(0,10).split('-');

		beginTime=beginTimes[1]+'-'+beginTimes[2]+'-'+beginTimes[0]+' '+beginTime.substring(10,19);
		endTime=endTimes[1]+'-'+endTimes[2]+'-'+endTimes[0]+' '+endTime.substring(10,19);
		
		var a =(Date.parse(endTime)-Date.parse(beginTime))/3600/1000;

		if(a<0){
		return -1;
		}else if (a>0){
		return 1;
		}else if (a==0){
		return 0;
		}else{
		return 'exception'
		}
 
}
  	

</script>
<script language="javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawRmDutyEventForm'});
});
</script>
<table>
	<caption><%--
		  <bean:message key="tawRmDutyEvent.addEvent" />
	--%></caption>
</table>
 
<html:form action="/dutyevent.do?method=edit"
	method="post" styleId="tawRmDutyEventForm">
	 <input type="hidden" id="id" name="id" value='${tawRmDutyEventForm.id}'/>
	 <input type="hidden" name="begintime" value='${tawRmDutyEventForm.beginTime}' />
	 <input type="hidden" name="endtime" value='${tawRmDutyEventForm.endTime}' />
	 <html:hidden property="id" />
	<table border=0 cellspacing="1" cellpadding="1" class="listTable">
		<!--附加表以及XML文件基本属性表格：开始-->
		<tr>
			<td COLSPAN="4" class="label">
				 <bean:message key="tawRmDutyEvent.faultType" />
			</td>
			<td COLSPAN="10">
			<bean:write name="tawRmDutyEventForm" property="faultTypeName"/>
		 </td>
			<td COLSPAN="4" class="label">
				 <bean:message key="tawRmDutyEvent.flag" />
			</td>
			<td COLSPAN="10"> 
			<bean:write name="tawRmDutyEventForm" property="flagName"/>
			  			 
						<%--
				<input name="flag" value=""
					class="text">
			--%></td>
		</tr> 
		<tr>
			<td COLSPAN="4" class="label">
					 <bean:message key="tawRmDutyEvent.beginTime" />
			</td>
			<td COLSPAN="10">
			 
			<bean:write name="tawRmDutyEventForm" property="beginTime"/>
			</td>
			<td COLSPAN="4" class="label">
					 <bean:message key="tawRmDutyEvent.endtime" />
			</td>
			<td COLSPAN="10">
			<bean:write name="tawRmDutyEventForm" property="endtime"/>
			 			 
			</td>
		</tr>

		<tr class="tr_show">
			<td COLSPAN="4" class="label">
				 <bean:message key="tawRmDutyEvent.eventtitle" />
			</td>
			<td COLSPAN="24">
			<bean:write name="tawRmDutyEventForm" property="eventtitle"/>
				 
	 	</tr>
		<TR>
		 	<td COLSPAN="4" class="label">
				 <bean:message key="tawRmDutyEvent.complateFlag" />
			</td>
			<td COLSPAN="10"> 
						<bean:write name="tawRmDutyEventForm" property="complateFlagName"/>
		</td>
		 <td COLSPAN="4" class="label">
				<bean:message key="tawRmDutyEvent.pubstatus" />
			</td>
			<td COLSPAN="10">
				<bean:write name="tawRmDutyEventForm" property="pubStatusName"/>
			</td>
		</tr>
	 	<TR>
		 	<td COLSPAN="4" class="label">
				 <bean:message key="tawRmDutyEvent.accessories" />
			</td>
			<td COLSPAN="24"> 
					 <eoms:attachment name="tawRmDutyEventForm" property="accessories" 
            scope="request" idField="accessories" appCode="9" viewFlag="Y"/> 
		</td>
		</tr>
	</table>
	<% 
	String comFlag = (String)request.getAttribute("COMPLATEFLAG");
	System.out.println(comFlag);
	if(comFlag!=null && comFlag.equals("2")){
	 %>
<table>
	<caption>
		 <bean:message key="tawRmDutyEvent.addEventSub" />
	</caption>
</table>
	<table border=0 cellspacing="1" cellpadding="1" class="listTable">
		<!--附加表以及XML文件基本属性表格：开始-->
		<tr>
			<td COLSPAN="4" class="label">
				 <bean:message key="tawRmDutyEventSub.happentime" />
			</td>
			<td COLSPAN="10">
			
				<input type="text" name="happentime" size="20" onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="true"  class="text">
			</td>
			<td COLSPAN="4" class="label">
				 <bean:message key="tawRmDutyEventSub.worksheetid" />
			</td>
			<td COLSPAN="10">
			<html:text property="worksheetid" styleId="worksheetid"
						styleClass="text medium" />
				 
			</td>
		</tr> 
	 

		<tr class="tr_show">
			<td COLSPAN="4" class="label">
				 <bean:message key="tawRmDutyEventSub.content" />
			</td>
			<td COLSPAN="24">
					 
					 <textarea name="content"   rows="3" cols="20"   value="" ></textarea>
				 
		</tr>
		 
		<tr>
			<td COLSPAN="28" > 
			<input type="button" value=<bean:message key="tawRmDutyEventSub.addEventSub" /> onClick="javascript:onsubSave(0);" class="button">
			<input type="button" value=<bean:message key="tawRmDutyEventSub.addEventSubandClose" />  onClick="javascript:onsubSave(1);" class="button">
			</td>
		</tr>
	</table>
	<%} %>
	<table border=0 cellspacing="1" cellpadding="1" class="listTable">
	<display:table name="TawRmDutyEventSubList" cellspacing="0" cellpadding="0"
    id="TawRmDutyEventSubList" pagesize="15" class="table tawRmDutyEventSubList"
      sort="list" partialList="true" size="resultSize"
    requestURI="${app}/duty/dutyevent.do?method.do?method=search"  >

	<display:column property="recordman" sortable="true" headerClass="sortable"
         titleKey="tawRmDutyEventSub.recordman" />
         
    <display:column property="happentime" sortable="true" headerClass="sortable"
         titleKey="tawRmDutyEventSub.happentime"/>

    <display:column property="worksheetid" sortable="true" headerClass="sortable"
         titleKey="tawRmDutyEventSub.worksheetid"/>

    <display:column property="content" sortable="true" headerClass="sortable"
         titleKey="tawRmDutyEventSub.content"/>

    <display:setProperty name="paging.banner.item_name" value="thread"/>
    <display:setProperty name="paging.banner.items_name" value="threads"/>
</display:table>

</table>
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>
