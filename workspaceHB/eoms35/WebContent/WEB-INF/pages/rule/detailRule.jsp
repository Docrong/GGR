<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<script type="text/javascript" src="${app}/scripts/base/prototype.js"></script>
<script src="<c:url value='/scripts/form/dynamictable.js'/>" type=text/javascript></script>
<link media="screen" href="${app}/styles/rule/style.css" type="text/css" rel="stylesheet"/>

<!-- ENDLIBS -->
<script type="text/javascript">
var iconshow = "${app}/images/icons/plus.png";
var iconhide = "${app}/images/icons/minus.png";

function togglet(id,img){
  var el = $(id);
  el.toggle();
  img.src = el.style.display=="none" ?  iconshow:iconhide;
}
function showResult(){
       $('ruleform').submit();
}

function page(currPage){
	var curr=document.getElementById("currPage2");
	curr.value=currPage;
	document.getElementById("pageForm").submit();
}

function toggleRows(el,str){
	var myRow = getParentForTag(el,"TR");
	var rows = getParentForTag(el,"TABLE").rows;
	for(var i=0;i<rows.length;i++){
		if(myRow!=rows[i]){
			if(str=="show"){
				rows[i].style.display = "";
			}
			else{
				var s = rows[i].style;
				s.display = s.display=="none" ? "":"none";
			}
		}
	}
}
var dialog;
var inputTpl,outputTpl,listenerTpl;
var Page = {
	init : function(){
	<c:forEach items="${rules.rules.rule}" var="ruleItem">
		var tabs = new Ext.TabPanel('rule-${ruleItem.id}');
        tabs.addTab('rule-${ruleItem.id}-input', "<bean:message key='rule.table.title.inputParameter'/>");
        tabs.addTab('rule-${ruleItem.id}-output', "<bean:message key='rule.table.title.outputParameter'/>");
        tabs.addTab('rule-${ruleItem.id}-listener', "<bean:message key='rule.table.title.listener'/>");
		tabs.activate('rule-${ruleItem.id}-input');
		$("rule-${ruleItem.id}").hide();
	</c:forEach>	
		showBtn = Ext.get('newrulebtn');
        showBtn.on('click', this.showDialog, this);
        
        dialog = new Ext.BasicDialog("hello-dlg", {
			autoTabs:true,
			width:400,
			height:200,
			shadow:true,
			minWidth:300,
			minHeight:250,
			proxyDrag: true
        });
		dialog.addKeyListener(27, dialog.hide, dialog);
        dialog.addButton('<bean:message key='rule.table.button.submit'/>', showResult, dialog);
        dialog.addButton('<bean:message key='rule.table.button.cancel'/>', dialog.hide, dialog);
		
		//创建三个模板
		inputTpl = Ext.Template.from("input-template");
		outputTpl = Ext.Template.from("output-template");
		listenerTpl = Ext.Template.from("listener-template");
		
	},
	showDialog : function(){
        dialog.show(showBtn.dom);
    }
	
}
Ext.onReady(Page.init, Page, true);
	
	function delRule(ruleId)
	{
		//window.location='<html:rewrite page='/RuleWebToolAction.do?method=delRule&id='/>'+id+'&ruleId='+ruleId+'&currPage=${currPage}&qdescription=${qdescription}&qid=${qid}&qclassName=${qclassName}';	
		var ruleId2=document.getElementById("ruleId2");
		ruleId2.value=ruleId;
		document.getElementById("delRuleForm").submit();
	}
	
</script>
</head>

<body>
<div id=page>
<div class=clearfix id=content>
<div id=main>

    <div id="hello-dlg" style="visibility:hidden;position:absolute;top:0px;">
		<div class="x-dlg-hd">Dialog</div>
		<div class="x-dlg-bd">
        <!-- Auto create tab 1 -->
			<div class="x-dlg-tab" title="<bean:message key='rule.table.title.newRule'/>">
            <!-- Nested "inner-tab" to safely add padding -->
            <div class="inner-tab">
			<form name="ruleform" id="ruleform" action="<html:rewrite page='/RuleWebToolAction.do?method=addRule'/>" method="post">
			<input type="hidden" name="currPage" value="${currPage }"/>
			<input type="hidden" name="id" value="${rule.id}"/>
			<input type="hidden" name="qdescription" value="${qdescription}"/>
			<input type="hidden" name="qid" value="${qid}"/>
			<input type="hidden" name="qclassName" value="${qclassName }"/>
			<table class="simple" cellspacing="0">
			<tbody>
            <tr>
              <td width="15%"><bean:message key="rule.table.title.id"/></td>
              <td class="msource"><input class="wide" value="" name="ruleId"/></td>
            </tr>
            <tr>
              <td><bean:message key="rule.table.title.description"/></td>
              <td class="msource"><input class="wide" value="" name="description"/></td>
            </tr>
            <tr>
              <td><bean:message key="rule.table.title.class"/></td>
              <td class="msource"><input class="wide" value="com.boco.eoms.commons.rule.service.ExpressionRuleService" name="className"/></td>
            </tr>
			</tbody>
			</table>
			</form>
            </div>
			</div>
        </div>

    </div>
<!-- 模板 -->
<div style="display:none">
    <table>
	<!-- 监听行模板开始 -->
	<tbody id="listener-template">
            <tr>
              <td class="micon">&nbsp;</td>
              <td class="mdesc"><bean:message key="rule.table.title.class"/></td>
              <td class="msource"><input class="wide" value="com.boco.eoms.commons.rule.listener.RuleCheckListener" name="listenerName" size="20"/></td>
              <td class="mdesc"><bean:message key="rule.table.title.description"/></td>
              <td class="msource"><input class="wide" value="" name="listenerDescription" size="20"/></td>
			  <td class="mrowbtns">
			  <input name="button2" type=button onClick="removeRow(this);" value="<bean:message key='rule.table.button.delete'/>"></td>
            </tr>
	</tbody>
	<!-- 监听行模板开始 -->
	</table>
	
	<!-- 输入模板开始 -->
	<div id="input-template">
		<table class="member-table" cellspacing="0">
          <tbody>
            <tr>
              <th class="micon">&nbsp;</th>
              <th class="mdesc"><bean:message key="rule.table.title.description"/></th>
              <th class="msource"><input type="text" value="" name="inputDescription"></th>
              <td rowspan="4" class="mbtns">
                <input type="button" onclick="removeTable(this);" value="<bean:message key='rule.table.button.delete'/>"><br/>
              </td>
            </tr>
            <tr class="alt">
              <td class="micon">&nbsp;</td>
              <td class="mdesc"><bean:message key="rule.table.title.parameterName"/></td>
              <td class="msource"><input type="text" value="" name="inputName" class="wide"></td>
            </tr>
            <tr>
              <td class="micon">&nbsp;</td>
              <td class="mdesc"><bean:message key="rule.table.title.parameterType"/></td>
              <td class="msource"><input type="text" value="" name="inputType" class="wide"></td>
            </tr>
          </tbody>
		</table>
     </div>
  	<!-- 输入模板结束 -->
	
	<!-- 输出模板开始 -->
	<div id="output-template">	
		  <table class="member-table" cellspacing="0">
          <tbody>
            <tr>
              <th class="micon">&nbsp;</th>
              <th class="mdesc"><bean:message key="rule.table.title.description"/></th>
              <th class="msource"><input value="" name="outputDescription" size="30"></th>
              <td rowspan="4" class="mbtns">
                <input type="button" onclick="removeTable(this);" value="<bean:message key='rule.table.button.delete'/>"><br/>
              </td>
            </tr>
            <tr class="alt">
              <td class="micon">&nbsp;</td>
              <td class="mdesc"><bean:message key="rule.table.title.parameterName"/></td>
              <td class="msource"><input value="" name="outputName" class="wide"></td>
            </tr>
            <tr>
              <td class="micon">&nbsp;</td>
              <td class="mdesc"><bean:message key="rule.table.title.parameterType"/></td>
              <td class="msource"><input value="" name="outputType" class="wide"></td>
            </tr>
            <tr class="alt">
              <td class="micon">&nbsp;</td>
              <td class="mdesc"><bean:message key="rule.table.title.expression"/></td>
              <td class="msource">
               <textarea rows="3" class="wide" name="outputExpression" id="expression-{ruleItemId}-{t}"></textarea>
			   <input value="<bean:message key='rule.table.button.editExpression'/>" type="button" 
			   onclick="javascript:window.open('<html:rewrite page='/RuleWebToolAction.do?method=initEditExpression&id={ruleId}&ruleId={ruleItemId}&outName={tempId}'/>','mywin', 'menubar=no,width=600,height=600,resizeable=yes');"/>
			  </td>
            </tr>
          </tbody>
      </table>
     </div>
	 <!-- 输出模板结束 -->
</div>

    <table width="80%">
		<tr><td><strong>${rule.name}</strong><br></td></tr>
        <tr><td><strong>${rule.xmlPath}</strong></td></tr>
        <tr><td><input name="newrulebtn" id="newrulebtn" type="button" onClick="" value="<bean:message key='rule.table.button.newRule'/>"></td></tr>
	</table>
<!-- 正文 -->


<c:forEach items="${rules.rules.rule}" var="ruleItem">
<form action="<html:rewrite page='/RuleWebToolAction.do?method=updateRule'/>" method="post"/>
<input type="hidden" name="id" value="${rule.id}"/>
<input type="hidden" name="ruleId" value="${ruleItem.id}"/>
<input type="hidden" name="currPage" value="${currPage }"/>
<input type="hidden" name="qdescription" value="${qdescription}"/>
<input type="hidden" name="qid" value="${qid}"/>
<input type="hidden" name="qclassName" value="${qclassName }"/>
<!-- 规则 -->
 <h2><bean:message key="rule.table.title.description"/> | <input type="text" value="${ruleItem.description }" name="ruleDescription" class="wide" size="80"/>
 <img src="/eoms/images/icons/plus.png" onclick="javascript:togglet('rule-${ruleItem.id}',this);" alt="<bean:message key='rule.table.title.openclose'/>"/>
 </h2>
<div class="detail-wrap" id="rule-${ruleItem.id}">
	<div class="mdetail">
		<strong><bean:message key="rule.table.title.ruleId"/>:</strong><input type="text" value="${ruleItem.id }" name="mruleId" class="wide" size="80"/><br>
        <strong>&nbsp;&nbsp;<bean:message key="rule.table.title.class"/>:&nbsp;&nbsp;</strong><input type="text" value="${ruleItem.className}" name="ruleClassName" size="80" class="wide"/> 
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" onClick="" value="<bean:message key='rule.table.button.submit'/>"><input type="button" value="<bean:message key='rule.table.button.delete'/>"  onclick="javascript:delRule('${ruleItem.id}');"/>
	</div>
	
	<div class="tab-content" id="rule-${ruleItem.id}-input">
		<c:forEach items="${ruleItem.input.parameters}" var="inputItem">
		  <table class="member-table" cellspacing="0" id="rule-${ruleItem.id}-input-table">
          <tbody>
            <tr>
              <th class="micon">&nbsp;</th>
              <th class="mdesc"><bean:message key="rule.table.title.description"/></th>
              <th class="msource"><input type="text" value="${inputItem.description }" name="inputDescription" size="80" class="wide"/></th>
              <td rowspan="4" class="mbtns">
                <input type="button" onclick="removeTable(this);" value="<bean:message key='rule.table.button.delete'/>">
              </td>
            </tr>
            <tr class="alt">
              <td class="micon">&nbsp;</td>
              <td class="mdesc"><bean:message key="rule.table.title.parameterName"/></td>
              <td class="msource"><input type="text" value="${inputItem.name }" name="inputName" size="80" class="wide"/></td>
            </tr>
            <tr>
              <td class="micon">&nbsp;</td>
              <td class="mdesc"><bean:message key="rule.table.title.parameterType"/></td>
              <td class="msource"><input  type="text" value="${inputItem.type }" name="inputType" size="80" class="wide"/></td>
            </tr>
          </tbody>
      	</table>
      	</c:forEach>
		<input type="button" onClick="inputTpl.append('rule-${ruleItem.id}-input')" value="<bean:message key='rule.table.button.addParameter'/>">
	</div>
	<div class="tab-content" id="rule-${ruleItem.id}-output">
		<c:forEach items="${ruleItem.output.parameters}" var="outputItem">
		  <table class="member-table" cellspacing="0">
          <tbody>
            <tr>
              <th class="micon">&nbsp;</th>
              <th class="mdesc"><bean:message key="rule.table.title.description"/></th>
              <th class="msource"><input type="text" name="outputDescription" value="${outputItem.description }" size="80" class="wide"/></th>
              <td rowspan="4" class="mbtns">
                <input type="button" onclick="removeTable(this);" value="<bean:message key='rule.table.button.delete'/>">
              </td>
            </tr>
            <tr class="alt">
              <td class="micon">&nbsp;</td>
              <td class="mdesc"><bean:message key="rule.table.title.parameterName"/></td>
              <td class="msource"><input type="text" name="outputName" value="${outputItem.name }" size="80" class="wide"/></td>
            </tr>
            <tr>
              <td class="micon">&nbsp;</td>
              <td class="mdesc"><bean:message key="rule.table.title.parameterType"/></td>
              <td class="msource"><input type="text" name="outputType" value="${outputItem.type }" size="80" class="wide"/></td>
            </tr>
            <tr class="alt">
              <td class="micon">&nbsp;</td>
              <td class="mdesc"><bean:message key="rule.table.title.expression"/></td>
              <td class="msource">
               <textarea rows="3" name="outputExpression" class="wide"  id="expression-${ruleItem.id}-${outputItem.name }" type="_moz">${outputItem.expression }</textarea>
               <input value="<bean:message key='rule.table.button.editExpression'/>" type="button" onclick="javascript:window.open('<html:rewrite page='/RuleWebToolAction.do?method=initEditExpression&id=${rule.id}&ruleId=${ruleItem.id }&outName=${outputItem.name}'/>','mywin', 'menubar=no,width=600,height=600,resizeable=yes');"/>
               </td>
            </tr>
          </tbody>
      </table>
		</c:forEach>
      
		<input type="button" onClick="outputTpl.append('rule-${ruleItem.id}-output',{ruleId:'${rule.id}',ruleItemId:'${ruleItem.id}',tempId:Ext.id()})" value="<bean:message key='rule.table.button.addParameter'/>">
	</div>
	<div class="tab-content" id="rule-${ruleItem.id}-listener">
		  <table class="member-table" cellspacing="0" id="rule-${ruleItem.id}-listener-table">
          <tbody>
            <tr>
              <th class="micon">&nbsp;</th>
              <th class="mdesc"><bean:message key='rule.table.title.listener'/></th>
              <th class="msource" colspan="4">&nbsp;</th>
            </tr>
            
	    <c:forEach items="${ruleItem.listeners.listeners}" var="listenerItem">
            <tr>
              <td class="micon">&nbsp;</td>
              <td class="mdesc"><bean:message key="rule.table.title.class"/></td>
              <td class="msource"><input type="text" name="listenerName" value="${listenerItem.name }" size="80"  class="wide"/></td>
              <td class="mdesc"><bean:message key="rule.table.title.description"/></td>
              <td class="msource"><input type="text" name="listenerDescription" value="${listenerItem.description }" size="80" class="wide"/></td>
			  <td class="mrowbtns">
			    <input name="button2" type=button onClick="removeRow(this);" value="<bean:message key='rule.table.button.delete'/>"></td>
            </tr>
            
	  	</c:forEach>
          </tbody>
      </table>
		<input type="button" onClick="listenerTpl.append('rule-${ruleItem.id}-listener-table')" value="<bean:message key='rule.table.button.addListener'/>">
	</div>
	
</div>
</form>
</c:forEach>

</div>
</div>
</div>
<table width="600">
	<tr><td width="20%">
		<bean:message key="rule.table.title.currPage" arg0="${currPage }"/>
	</td>
	<td width="15%">
		<bean:message key="rule.table.title.totalRows" arg0="${total }"/>
	</td>
	<td width="15%">
		<bean:message key="rule.table.title.totalPages" arg0="${pageTotal }"/>
	</td>
	<!-- 
	<td width="15%"><a href="<c:url value='RuleWebToolAction.do?method=detailRule&id=${rule.id}&currPage=${currPage-1 }&qid=${qid}&qdescription=${qdescription }&qclassName=${qclassName }'/>">Last Page</a></td>
	<td width="15%"><a href="<c:url value='RuleWebToolAction.do?method=detailRule&id=${rule.id}&currPage=${currPage+1 }&qid=${qid}&qdescription=${qdescription }&qclassName=${qclassName }'/>">Next Page</a></td>
	-->
	
	<td width="15%"><a href="#" onclick="javascript:page('${currPage-1}');"><bean:message key="rule.table.title.lastPage"/></a></td>
	<td width="15%"><a href="#" onclick="javascript:page('${currPage+1}');"><bean:message key="rule.table.title.nextPage"/></a></td>
	<td width="15%"><a href="<html:rewrite page='/RuleWebToolAction.do?method=listRule'/>"><bean:message key="rule.table.title.return"/></a></td>
	</tr>
	<form name="pageForm" id="pageForm" method="post" action="<html:rewrite page='/RuleWebToolAction.do?method=detailRule'/>">
	<input type="hidden" name="id" value="${rule.id }"/>
	<input type="hidden" name="currPage" value="1" id="currPage2"/>
	<input type="hidden" name="qid" value="${qid }"/>
	<input type="hidden" name="qdescription" value="${qdescription }"/>
	<input type="hidden" name="qclassName" value="${qclassName }"/>
	</form>
	
	<form name="delRuleForm" id="delRuleForm" method="post" action="<html:rewrite page='/RuleWebToolAction.do?method=delRule'/>">
		<input type="hidden" name="id" value="${rule.id }"/>
		<input type="hidden" name="ruleId" value="-1" id="ruleId2"/>		
		<input type="hidden" name="currPage" value="${currPage }"/>
		<input type="hidden" name="qid" value="${qid}"/>
		<input type="hidden" name="qclassName" value="${qclassName}"/>
		<input type="hidden" name="qdescription" value="${qdescription }"/>
	</form>
</table>
</body>
</html>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>