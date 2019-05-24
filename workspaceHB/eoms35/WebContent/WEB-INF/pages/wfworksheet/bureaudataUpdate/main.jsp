<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
String parentCorrelation=com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("parentCorrelation"));
String parentSheetId=com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("parentSheetId"));
String parentSheetName=com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("parentSheetName"));
%>

<script type="text/javascript">
	var roleTree;
	var v;

	function initPage(){
		 v = new eoms.form.Validation({form:'theform'});
		 $('btns').show();   
	//	 v.preCommitUrl = "${app}/sheet/bureaudataUpdate/bureaudataUpdate.do?method=performPreCommit";		
	}
    
     
   Ext.onReady(function(){
    //showPage();
    
    var tabs = new Ext.TabPanel('main');
	tabs.addTab('sheetform', "<bean:message bundle="bureaudataUpdate" key="bureaudataUpdate.header"/>");
	tabs.activate('sheetform');     
    
    var el = Ext.get("idSpecial");
	var mgr = el.getUpdateManager();
	mgr.loadScripts = true;
	if ('${templateId}'!= null && '${templateId}' != "") {
		mgr.update("${app}/sheet/bureaudataUpdate/bureaudataUpdate.do?method=showTemplateInputSheetPage&templateId=${templateId}");
	} else {
		mgr.update("${app}/sheet/bureaudataUpdate/bureaudataUpdate.do?method=showNewInputSheetPage&processname=BureaudataUpdateProcess&parentCorrelation=${parentCorrelation}&parentSheetId=${parentSheetId}&parentSheetName=${parentSheetName}&parentPhaseName=${parentPhaseName}");
	}
    
	mgr.on("update", initPage);
   });
   
   function changeType1(){
   
   	$('${sheetPageName}phaseId').value = 'Assessor';
   	$('${sheetPageName}operateType').value = "0"; 
   	if(!checkSegment()){
       return false;
    }
   	return true;
   }
   function changeType2(){
   // if(!v.check()){return;}
   	$('${sheetPageName}phaseId').value = 'DraftTask';
   	$('${sheetPageName}operateType').value = "22"; 	
	if(!checkSegment()){
       return false;
    }
   	return true;
   
   }
   
  function saveTemplate(type) {
   	var form = document.getElementById("theform");
    var ajaxForm = Ext.getDom(form);
   	var templateManage = "${type}";
  	if(v.check()){
  	v.passing = false;
   	if (confirm("确认保存模板吗？")) {
	   	if (templateManage == "templateManage") {
	   		form.action = "${app}/sheet/bureaudataUpdate/bureaudataUpdate.do?method=saveTemplate&templateId=${templateId}&type=${type}";
	   		form.submit();
	   	} else {
		   	if (type == "new"){
			   	Ext.Ajax.request({
			   		form: ajaxForm,
					method:"post",
					url: "bureaudataUpdate.do?method=saveTemplate&type=${type}",
					success: function(){
			        	alert("保存模板成功！");
			 		}
			    });
		   	}else {
		   		Ext.Ajax.request({
			   		form: ajaxForm,
					method:"post",
					url: "bureaudataUpdate.do?method=saveTemplate&templateId=${templateId}&type=${type}",
					success: function(){
			        	alert("保存模板成功！");
			 		}
			    });
		   	}
	   	}
   	}
  }
  }
   
  function removeTemplate() {
   if (confirm('<bean:message bundle="sheet" key="worksheet.delete.confirm" />')) {
		var thisform = document.forms[0];
		thisform.action = "${app}/sheet/bureaudataUpdate/bureaudataUpdate.do?method=removeTemplate&templateId=${templateId}&type=${type}";
		thisform.submit();
	}
 }
 function checkSegment(){
        var beginsegArr = document.getElementsByName('beginsegment');
        var endsegArr = document.getElementsByName('endsegment');
        var hlrIdArr = document.getElementsByName('hlrSignalid');
        var wanhaoArr = new Array();
        var indexArr = new Array();
        var tip = "";//提示信息
        if(beginsegArr.length == 0){
          alert("无未归属的号段，无法申请工单！");
          return false;
        }
        for(var i = 0; i < beginsegArr.length; i++){
          $('segTab').rows[i].className = 'tr_show';
        }
        for(var i = 0; i < beginsegArr.length; i++){
          if(beginsegArr[i].value == ''){
            alert('启始号段不能为空，请输入！');
            beginsegArr[i].focus();
            return false;
          }
          if(endsegArr[i].value == ''){
            alert('终止号段不能为空，请输入！');
            endsegArr[i].focus();
            return false;
          }
          if(hlrIdArr[i].value == ''){
            alert('请选择HLR信令点！');
            hlrIdArr[i].focus();
            return false;
          }
          if((beginsegArr[i].value-0) > (endsegArr[i].value-0)){
            alert('启始号段不能大于终止号段，请重新输入！');
            if(beginsegArr[i].readOnly){
            	endsegArr[i].select();
            	endsegArr[i].focus();
            }else{
            	beginsegArr[i].select();
              	beginsegArr[i].focus();
            }
            return false;
          }
          var endSegTmp = endsegArr[i].value-0;
          for(var j = (beginsegArr[i].value-0); j <= endSegTmp; j++){
            if(g_segMap[j] == null){
            	alert('输入的号段不在指定的号段范围内，请重新输入！');
            	if(j==beginsegArr[i].value){
            		beginsegArr[i].select();
            		beginsegArr[i].focus();
            	}else{
            		endsegArr[i].select();
            		endsegArr[i].focus();
            	}
            	return false;
            }
            if(wanhaoArr[j] != null){
            	alert('号段有重叠，请检查后重新输入!');
            	if(j==beginsegArr[i].value){
            		beginsegArr[i].select();
            		beginsegArr[i].focus();
            	}else{
            		endsegArr[i].select();
            		endsegArr[i].focus();
            	}
            	return false;
            }
            wanhaoArr[j] = j;
            if((j==(g_segMap[j]-0)) && ((g_hlrIdMap[hlrIdArr[i].value]-0) != j)){
            	tip = tip + '号段：' + j + '，HLR名称：' + g_hlrnameMap[j] + '\n';
                indexArr[indexArr.length] = i;
            }
          }
        }
        if(tip != ''){
          for(var i=0; i < indexArr.length; i++){
          	$('segTab').rows[indexArr[i]].className = 'tr_show_orange';
          }
          tip = '此次操作号段中有HLR编号所在号段，是否确定强行调整？\n' + tip;
          return confirm(tip);
        }
        return true;
}
 
 
 
</script>

<div id="sheetform" class="tabContent">
<html:form action="/bureaudataUpdate.do?method=newPerformAdd" styleId="theform">
	<div ID="idSpecial"></div>
    <p/>	
	<!-- buttons -->
	<div class="form-btns" id="btns" style="display:none">

	<logic:notPresent name="templateManage">
		<html:submit styleClass="btn" property="method.save" onclick="return changeType1();" styleId="method.save">
		 <bean:message bundle="sheet" key="button.send"/>
		</html:submit>
		
		<html:submit styleClass="btn" property="method.saveDraft" onclick="v.passing=true;return changeType2()" styleId="method.saveDraft">
		<bean:message bundle="sheet" key="button.saveAsDraft" />
		</html:submit>
		
		<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="window.open('./bureaudataUpdate.do?method=getTemplatesByUserId',null,'left=300,top=150,height=400,width=500,scrollbars=yes,resizable=yes')">
	        <bean:message bundle="sheet" key="button.refereTemplate" />
	    </html:button>
	    <html:button styleClass="btn" property="method.save" styleId="method.save" onclick="v.passing=true;saveTemplate('new')">
	         <bean:message bundle="sheet" key="button.saveTemplate" />
	     </html:button>
		 <c:if test="${templateId != null && templateId != ''}">
			<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="v.passing=true;saveTemplate('current')">
	           <bean:message bundle="sheet" key="button.saveCurTemplate" />
	        </html:button>
		 </c:if>	
		</div>
	</logic:notPresent>
	<logic:present name="templateManage">
		<c:if test="${templateId != null && templateId != ''}">
		 <logic:present name="type">
		 	<div>
		 		<table id="sheet" class="formTable">
		 			<tr>
		 				<td class="label"><bean:message bundle="sheet" key="input.templateName" /></td>
		 				<td><input type="text" name="newSheetTemplateName" value="${sheetMain.sheetTemplateName}" class="text max"></td>
		 			</tr>
		 		</table>
		 	</div>
		 	<br>
			<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="v.passing=true;saveTemplate('current')">
	           	<bean:message bundle="sheet" key="button.saveCurTemplate" />
	        </html:button>
	        <html:button styleClass="btn" property="method.save" styleId="method.save" onclick="removeTemplate()">
         		<bean:message bundle="sheet" key="button.delete" />
			</html:button>
		</logic:present>
			<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="history.back(-1)">
			    <bean:message bundle="sheet" key="button.back" />
			</html:button>
		 </c:if>	
		</div>
	</logic:present>
	</html:form>
</div>

<%@ include file="/common/footer_eoms.jsp"%>
