<%@ include file="/common/taglibs.jsp"%>

<c:url var="urlDeal" value="/sheet/commonfault/commonfaultpack.do">
  <c:param name="method" value="${methodValue}"/>
  <c:param name="sheetKey" value="${sheetKey}"/>
  <c:param name="taskStatus" value="${taskStatus}"/> 
  <c:param name="piid" value="${piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="${operateType}"/>
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url> 

<script type="text/javascript">
var ifAccept;
var reviewResult;
var roleTree;
var v;
function initPage(){ 
	$('btns').show();	 
	}
 } 
Ext.onReady(function(){
	var dealTemplateId = "${dealTemplateId}";
	var strUrl = "${urlDeal}";
	var taskName = "${taskName}"; 
	var config = {
		url:strUrl,
		callback : initPage
	}
	eoms.util.appendPage("idSpecial",config);
});

   function changeType1(){
   	$('${sheetPageName}phaseId').value = "FirstExcuteTask";
   	$('${sheetPageName}operateType').value = "3";   	
   	//alert($('${sheetPageName}phaseId').value);
   }
   function changeType2(){
   	$('${sheetPageName}phaseId').value = "DraftTask";
   	$('${sheetPageName}operateType').value = "22";
   	//alert($('${sheetPageName}operateType').value);
   }
  

</script>

<div id="sheetform">
  <html:form action="../commonfault/commonfaultpack.do?method=performDeal" styleId="theform"> 
  	<!-- content -->
    <div id="idSpecial"></div>  
		<div class="form-btns" id="btns"> 
		ada<input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/commonfault/commonfaultpack.do?method=performDeal'" value="<bean:message bundle='sheet' key='button.save'/>" />
	     <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/commonfault/commonfaultpack.do?method=showListsendundo'" value="<bean:message bundle='sheet' key='button.back'/>" />
		</div>	 
    	    

</html:form>
</div>
