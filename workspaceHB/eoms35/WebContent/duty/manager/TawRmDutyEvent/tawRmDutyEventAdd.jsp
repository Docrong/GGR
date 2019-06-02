<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
 

<table>
	<caption>
		<bean:message key="tawRmDutyEvent.addEvent" />
	</caption>
</table>
 
<html:form action="/dutyevent.do?method=save&isSubmit=1"
	method="post" styleId="tawRmDutyEventForm"  >
	 <input type="hidden" id="isSubmit" name="isSubmit" />
	<table border=0 cellspacing="1" cellpadding="1" class="listTable">
		<!--附加表以及XML文件基本属性表格：开始-->
		<tr>
			<td COLSPAN="4" class="label">
				 <bean:message key="tawRmDutyEvent.faultType" />
			</td>
			<td COLSPAN="10">
			 
					<eoms:dict key="dict-duty" dictId="faultType" beanId="selectXML"  isQuery="false"  selectId="faultType"  alt="allowBlank:false" />
			</td>
			<td COLSPAN="4" class="label">
				 <bean:message key="tawRmDutyEvent.flag" />
			</td>
			<td COLSPAN="10">
			<eoms:dict key="dict-duty" dictId="faultflag" beanId="selectXML"  isQuery="false"  selectId="flag" alt="allowBlank:false" />
			 
		 	</td>
		</tr> 
		<tr>
			<td COLSPAN="4" class="label">
					 <bean:message key="tawRmDutyEvent.beginTime" />
			</td>
			<td COLSPAN="10">
				<input type="text" name="beginTime" size="20" onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="true"  class="text" alt="allowBlank:false,vtext:'请填写开始时间'">
			</td>
			<td COLSPAN="4" class="label">
					<bean:message key="tawRmDutyEvent.endtime" />
			</td>
			<td COLSPAN="10">
					<input type="text" name="endtime" size="20" onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="true"  class="text" alt="allowBlank:false,vtext:'请填写结束时间">
			</td>
		</tr>

		<tr class="tr_show">
			<td COLSPAN="4" class="label">
				 <bean:message key="tawRmDutyEvent.eventtitle" />
			</td>
			<td COLSPAN="24">
					<input name="eventtitle"   type="text"  class="clstext"  size="77" value=""  alt="allowBlank:false,vtext:'请填写标题时间'"/>
		</tr>
		<tr>
		 	<td COLSPAN="4" class="label">
				<bean:message key="tawRmDutyEvent.complateFlag" />
			</td>
			<td COLSPAN="24">
				<eoms:dict key="dict-duty" dictId="complateFlag" beanId="selectXML"  isQuery="false"  selectId="complateFlag" alt="allowBlank:false" />
			</td>
		</tr>
			<tr>
		 	<td COLSPAN="4" class="label">
				<bean:message key="tawRmDutyEvent.accessories" />
			</td>
			<td COLSPAN="24">
				 <eoms:attachment idList="" idField="accessories" appCode="9" />
			</td>
		</tr>
		</tr>
			<tr id="pubstatus" style="display:none">
		 	<td COLSPAN="4" class="label">
			</td>
			<td COLSPAN="10">
			<html:select property="pubStatus" style="width:100px">
					<html:option value="" ><bean:message key="tawRmDutyEvent.chose" /></html:option>
        			<html:option value="0" ><bean:message key="tawRmDutyEvent.nopublish" /></html:option>
        			<html:option value="1" ><bean:message key="tawRmDutyEvent.apublish" /></html:option>
        		</html:select>
				 
			</td>
			<td COLSPAN="4" class="label">
			</td>
			<td COLSPAN="10">
				 <html:select property="pflag" style="width:100px">
				 	<html:option value="" ><bean:message key="tawRmDutyEvent.chose" /></html:option>
        			<html:option value="0" ><bean:message key="tawRmDutyEvent.common" /></html:option>
        			<html:option value="1" ><bean:message key="tawRmDutyEvent.top" /></html:option>
        			<html:option value="2" ><bean:message key="tawRmDutyEvent.cycle" /></html:option>
        		</html:select>
			</td>
		</tr>
		<tr id="time" style="display:none">
		 	<td COLSPAN="4" class="label">
				<bean:message key="tawRmDutyEvent.starttime" />
			</td>
			<td COLSPAN="10">
				<input type="text" name="startTime" size="20" onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="true"  class="text" >
			</td>
			<td COLSPAN="4" class="label">
				<bean:message key="tawRmDutyEvent.pendtime" />
			</td>
			<td COLSPAN="10">
				 <input type="text" name="endTime" size="20" onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="true"  class="text" >
			</td>
		</tr>
		<tr id="title" style="display:none">
		 	<td COLSPAN="4" class="label">
				<bean:message key="tawRmDutyEvent.oid" />
			</td>
			<td COLSPAN="10">
				  <html:text property="oid" size="20"/>
			</td>
			<td COLSPAN="4" class="label">
				<bean:message key="tawRmDutyEvent.title" />
			</td>
			<td COLSPAN="10">
				  <html:text property="title" size="20"/>
			</td>
		</tr>
		<!--onClick="javascript:onSave(1);"-->
			<td> 
			<input type="submit" value="<bean:message key="designer.title.btnSubmit" />"  class="button" >
			</td>

	</table>
</html:form>
<script language="javascript">
Ext.onReady(function() {
 v = new eoms.form.Validation({form:'tawRmDutyEventForm'});
 v.custom = function(){ 
    if(document.forms[0].beginTime.value>=document.forms[0].endtime.value){
      alert("起始时间不能大于等于结束时间");
        return false; 
    } 
    if(document.forms[0].eventtitle.value.length >100){
    		alert("事件标题不能大于100");
    		return false;
    	}
    return true;
   };

  var el = eoms.$('flag');
  var maxFlag = 3;
  el.on('change',function(el){
	  if(el.selectedIndex>=maxFlag){
	    eoms.$('time').show();
	    eoms.$('title').show();
	    eoms.$('pubstatus').show();
	  }
	  else{
	    eoms.$('time').hide();
	    eoms.$('title').hide();
	    eoms.$('pubstatus').hide();
	  	
	  }
  });

});


</script>
<%@ include file="/common/footer_eoms.jsp"%>
