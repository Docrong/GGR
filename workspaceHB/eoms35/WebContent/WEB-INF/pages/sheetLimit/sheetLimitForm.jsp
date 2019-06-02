<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List"%>
<script type="text/javascript">
   var v;
   Ext.onReady(function(){
     v = new eoms.form.Validation({form:'sheetLimitForm'});
  });
 
  function getDealLimit(obj){
        Ext.Ajax.request({
		method:"get",
		url: "sheetLimit.do?method=getDealLimitBySpecial&faultResponseLevel="+obj,
		success: function(x){
        	var o = eoms.JSONDecode(x.responseText);
        	$("replyLimit").value = "";
        	if((o.replyLimit == null||o.replyLimit =="" )){
        	    alert("${eoms:a2u('您选择的响应级别没有配置处理时长，请先配置工单处理时长！')}");
        	    return false;
        	}else{
        	   $("replyLimit").value = o.replyLimit;
        	}
   		}
    });
   }
    function limitCheck(){
     var tempValue = parseInt(parseInt($("t1Limit").value,10)+ parseInt($("t2Limit").value,10)+ parseInt($("t3Limit").value,10),10);
    
 
        if($("replyLimit").value == ""||$("replyLimit").value == null) {
          alert("${eoms:a2u('您选择的响应级别没有配置处理时长，请先配置工单处理时长！')}");
          return false;
        }
        if(parseInt(tempValue)> parseInt($("replyLimit").value,10)){
         alert("${eoms:a2u('T1、T2、T3总时长不能超过响应级别配置的处理时长！')}");
         return false;
        }
   }
    
 </script> 
   
<fmt:setBundle basename="config/ApplicationResources-sheet-sheetLimit" var="sheetLimit"/>

<title><fmt:message bundle="${sheetLimit}" key="sheetLimit.title"/></title>
<content tag="heading"><fmt:message bundle="${sheetLimit}" key="sheetLimit.title"/></content>
<html:form action="saveSheetLimit.do" method="post" styleId="sheetLimitForm"> 
<ul>
<html:hidden property="id"/>
    <li>
        <eoms:label styleClass="desc" bundle="sheetLimit" key="sheetLimit.specialty2"/>
        <html:errors property="specialty2"/>
        <eoms:comboBox name="specialty2" id="${sheetLimitForm.specialty2}" defaultValue="${sheetLimitForm.specialty2}" initDicId="1010304" 
	  	      size="1" alt="allowBlank:false" styleClass="select-class" onchange="getDealLimit(this.value);"/>
    </li>
    <li>
        <eoms:label styleClass="desc" bundle="sheetLimit" key="sheetLimit.replyLimit"/>
        <html:errors property="replyLimit"/>
        <html:text property="replyLimit" styleId="replyLimit" readonly="true" styleClass="text medium"/>
        <input type="button" title="${eoms:a2u('如果级别没有配置处理时长，可以点击此处获取！')}"  value="${eoms:a2u('获取处理时长')}" onclick="location.href='<c:url value="/sheet/sheetLimit/sheetLimit.do?method=editDealLimit"/>'">
        
   </li>
     <li>
        <eoms:label styleClass="desc" bundle="sheetLimit" key="sheetLimit.specialty1"/>
        <html:errors property="specialty1"/>
         <eoms:comboBox name="specialty1" id="specialty1" defaultValue="${sheetLimitForm.specialty1}" initDicId="1010104" sub="specialty3"
	  	       size="1" alt="allowBlank:false" styleClass="select-class"/>
   </li>

    <li>
        <eoms:label styleClass="desc" bundle="sheetLimit" key="sheetLimit.specialty3"/>
        <html:errors property="specialty3"/>
        <eoms:comboBox name="specialty3" id="specialty3" defaultValue="${sheetLimitForm.specialty3}" initDicId="${sheetLimitForm.specialty1}" sub="specialty4"
	  	      size="1" styleClass="select-class"/>
    </li>

    <li>
        <eoms:label styleClass="desc" bundle="sheetLimit" key="sheetLimit.specialty4"/>
        <html:errors property="specialty4"/>
        <eoms:comboBox name="specialty4" id="specialty4" defaultValue="${sheetLimitForm.specialty4}" initDicId="${sheetLimitForm.specialty3}" sub="specialty5"
	  	      size="1" styleClass="select-class"/>
    </li>
     <li>
        <eoms:label styleClass="desc" bundle="sheetLimit" key="sheetLimit.specialty4"/>
        <html:errors property="specialty5"/>
        <eoms:comboBox name="specialty5" id="specialty5" defaultValue="${sheetLimitForm.specialty5}" initDicId="${sheetLimitForm.specialty4}"
	  	      size="1" styleClass="select-class"/>
    </li>
 
   <li>
        <eoms:label styleClass="desc" bundle="sheetLimit" key="sheetLimit.t1Limit"/>
        <html:errors property="t1Limit"/>
        <html:text property="t1Limit" styleId="t1Limit" styleClass="text medium" alt="allowBlank:false"/>
    </li>
    
    <li>
        <eoms:label styleClass="desc" bundle="sheetLimit" key="sheetLimit.t2Limit"/>
        <html:errors property="t2Limit"/>
        <html:text property="t2Limit" styleId="t2Limit" styleClass="text medium" alt="allowBlank:false"/>
    </li>
    
    <li>
        <eoms:label styleClass="desc" bundle="sheetLimit" key="sheetLimit.t3Limit"/>
        <html:errors property="t3Limit"/>
        <html:text property="t3Limit" styleId="t3Limit" styleClass="text medium" alt="allowBlank:false"/>
    </li>

  
    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="return limitCheck();">
            <fmt:message key="button.save"/>12212
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="v.passing=true;bCancel=true">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>

  </ul>

 </html:form>


<%@ include file="/common/footer_eoms.jsp"%>