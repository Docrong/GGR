<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<link media="screen" href="${app}/styles/rule/style.css" type="text/css" rel="stylesheet"/>


<script>

function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } 
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
}
    
    
 //实现当前位置插入
     var start=0;
    var end=0;
 function savePos(textBox){
        //如果是Firefox(1.5)的话，方法很简单
        if(typeof(textBox.selectionStart) == "number"){
            start = textBox.selectionStart;
            end = textBox.selectionEnd;
        }
        //下面是IE(6.0)的方法，麻烦得很，还要计算上'\n'
        else if(document.selection){
            var range = document.selection.createRange();
            if(range.parentElement().id == textBox.id){
                // create a selection of the whole textarea
                var range_all = document.body.createTextRange();
                range_all.moveToElementText(textBox);
                //两个range，一个是已经选择的text(range)，一个是整个textarea(range_all)
                //range_all.compareEndPoints()比较两个端点，如果range_all比range更往左(further to the left)，则                //返回小于0的值，则range_all往右移一点，直到两个range的start相同。
                // calculate selection start point by moving beginning of range_all to beginning of range
                for (start=0; range_all.compareEndPoints("StartToStart", range) < 0; start++)
                    range_all.moveStart('character', 1);
                // get number of line breaks from textarea start to selection start and add them to start
                // 计算一下\n
                for (var i = 0; i <= start; i ++){
                    if (textBox.value.charAt(i) == '\n')
                        start++;
                }
                // create a selection of the whole textarea
                 var range_all = document.body.createTextRange();
                 range_all.moveToElementText(textBox);
                 // calculate selection end point by moving beginning of range_all to end of range
                 for (end = 0; range_all.compareEndPoints('StartToEnd', range) < 0; end ++)
                     range_all.moveStart('character', 1);
                     // get number of line breaks from textarea start to selection end and add them to end
                     for (var i = 0; i <= end; i ++){
                         if (textBox.value.charAt(i) == '\n')
                             end ++;
                     }
                }
            }
        //document.getElementById("start").value = start;
        //document.getElementById("end").value = end;
    }
    
        
    
function startDict() {
    createXMLHttpRequest();
    xmlHttp.onreadystatechange = handleDictStateChange;
    var dictId=document.getElementById('dict').value;
    xmlHttp.open("POST", "<html:rewrite page='/RuleWebToolAction.do?method=relateDict&id=${ruleConfig.id}&dictId='/>"+dictId, true);
    xmlHttp.send(null);
}




function checkExpression(){
  var xhr = new Ajax.Request(
  				"<html:rewrite page='/RuleWebToolAction.do?method=checkExpression'/>",
  				{
  					method:"POST",
  					onComplete: handleExpression,
					postBody:"expression="+$E($('expression').value)
  				}
  	);
}

function handleExpression(req) {
  if(req.responseText=="true"){
  	$("expsucc").show();
  	$("experror").hide();
  }else if(req.responseText=="false"){
  	$("experror").show();
  	$("expsucc").hide();
  }  
}


function startInput(){
	 createXMLHttpRequest();
	 var input=document.getElementById('inputParameter').value.split('#');
		var className=input[1];
    xmlHttp.onreadystatechange = handleInputStateChange;
    xmlHttp.open("POST", "<html:rewrite page='/RuleWebToolAction.do?method=relateParameter&className='/>"+className, true);
    xmlHttp.send(null);
}


function startOutput(){
	 createXMLHttpRequest();
	 var input=document.getElementById('outputParameter').value.split('#');
		var className=input[1];
    xmlHttp.onreadystatechange = handleOutputStateChange;
    xmlHttp.open("POST", "<html:rewrite page='/RuleWebToolAction.do?method=relateParameter&className='/>"+className, true);
    xmlHttp.send(null);
}
    
function handleInputStateChange() {
    if(xmlHttp.readyState == 4) {
    	clearInputList();
	    var input = document.getElementById("input");
         var xmlDoc = xmlHttp.responseXML;
         var dicts = xmlDoc.getElementsByTagName("method");
         var i;
        var option = null;
        var noneOption=document.createElement("option");
         noneOption.setAttribute('value',"");
         noneOption.appendChild(document.createTextNode('<bean:message key='rule.table.title.select'/>'));
         input.appendChild(noneOption);
        
   		for(var i = 0; i < dicts.length; i++) {
        option = document.createElement("option");
       	option.setAttribute('value',dicts[i].getAttributeNode('value').value);
        option.appendChild(document.createTextNode(dicts[i].getAttributeNode('name').value));
        input.appendChild(option);
    	}
        
    }
}

function handleOutputStateChange() {
    if(xmlHttp.readyState == 4) {
	    clearOutputList();
		var output = document.getElementById("output");
         var xmlDoc = xmlHttp.responseXML;
         var dicts = xmlDoc.getElementsByTagName("method");
         var i;
         var noneOption=document.createElement("option");
         noneOption.setAttribute('value',"");
         noneOption.appendChild(document.createTextNode('<bean:message key='rule.table.title.select'/>'));
         output.appendChild(noneOption);
   		for(var i = 0; i < dicts.length; i++) {
        option = document.createElement("option");
       	option.setAttribute('value',dicts[i].getAttributeNode('value').value);
        option.appendChild(document.createTextNode(dicts[i].getAttributeNode('name').value));
        output.appendChild(option);
    	}
        
    }
}

function handleDictStateChange() {
    if(xmlHttp.readyState == 4) {
	    clearDictList();
	     var dictName = document.getElementById("dictName");
         var xmlDoc = xmlHttp.responseXML;
         var dicts = xmlDoc.getElementsByTagName("dict");
         var i;
         var noneOption=document.createElement("option");
         noneOption.setAttribute('value',"");
         noneOption.appendChild(document.createTextNode('<bean:message key='rule.table.title.select'/>'));
         dictName.appendChild(noneOption);
         
   		for(var i = 0; i < dicts.length; i++) {
        option = document.createElement("option");
       	option.setAttribute('value',dicts[i].getAttributeNode('value').value);
        option.appendChild(document.createTextNode(dicts[i].getAttributeNode('name').value));
        dictName.appendChild(option);
    	}
        
    }
}


function clearDictList() {
    var dictName = document.getElementById("dictName");
    while(dictName.childNodes.length > 0) {
        dictName.removeChild(dictName.childNodes[0]);
    }
}

function clearOutputList() {
    var dictName = document.getElementById("output");
    while(dictName.childNodes.length > 0) {
        dictName.removeChild(dictName.childNodes[0]);
    }
}

function clearInputList() {
    var dictName = document.getElementById("input");
    while(dictName.childNodes.length > 0) {
        dictName.removeChild(dictName.childNodes[0]);
    }
}

function ret(){
var value=document.getElementById('expression').value;
window.opener.document.getElementById('expression-${rule.id}-${outName}').value=value;
self.close();
}

</script>


<table  class="member-table" cellspacing="0">
	<tr>
		<td width="100%"><h2><bean:message key='rule.table.title.editExpression'/></h2></td>
	</tr>
	<tr>
		<td><textarea name="expression" style="width:500;height: 120" id="expression" 
							onKeydown="savePos(this)" 
                            onKeyup="savePos(this)" 
                            onmousedown="savePos(this)" 
                            onmouseup="savePos(this)" 
                            onfocus="savePos(this)" >${expression }</textarea></td>
        
          
	</tr>
		

	<tr>
		<td ><input type="button" value="<bean:message key='rule.table.button.checkExpression'/>" onclick="checkExpression();"/>
		<span id="experror" style="display:none">
		  <img src="${app}/images/icons/no.gif" alt="" align="absmiddle">
		</span>
		<span id="expsucc" style="display:none">
		  <img src="${app}/images/icons/yes.gif" alt="" align="absmiddle">
		</span>
		</td>
	</tr>
	<th class="mdesc" colspan="2"><bean:message key='rule.table.title.inputParameter'/></th>
	<tr>
		<td>
			<select name="inputParameter" id="inputParameter" onchange="javascript:startInput();">
				<option value=""><bean:message key='rule.table.title.select'/></option>
				<c:forEach items="${rule.input.parameters }" var="inputItem">
					<option value="${inputItem.name }# ${inputItem.type }">${inputItem.description},${inputItem.name }</option>
				</c:forEach>
			</select>		
		</td>
	</tr>
	<tr><td><select name="input"/><option value=""><bean:message key='rule.table.title.select'/></option>
		<input type="button" value="<bean:message key='rule.table.button.insertIputParameter'/>" onclick="insertInput();"/>
		</td>
	
	</tr>
	<th class="mdesc" colspan="2"><bean:message key='rule.table.title.outputParameter'/></th>
	<tr>
	<td>
			<select name="outputParameter" id="outputParameter"  onchange="javascript:startOutput();">
				<option value=""><bean:message key='rule.table.title.select'/></option>
				<c:forEach items="${rule.output.parameters }" var="outputItem">
					<option value="${outputItem.name }# ${outputItem.type }">${outputItem.description},${outputItem.name }</option>
				</c:forEach>
			</select>			
		</td>
	</tr>
	<tr><td><select name="output"/><option value=""><bean:message key='rule.table.title.select'/></option></select>
	<input type="button" value="<bean:message key='rule.table.button.insertOutputParameter'/>" onclick="insertOutput();"/>
	</td></tr>
	<th class="mdesc" colspan="2"><bean:message key='rule.table.title.dictionary'/></th>
	<tr>
		<td>
		<select name="dict" id="dict"  onchange="startDict();">
		<option value=""><bean:message key='rule.table.title.select'/></option>
		<c:forEach items="${dictList }" var="dictsItem">
			<option value="${dictsItem.value }">${dictsItem.name }</option>
		</c:forEach>
		</select>
		<select name="dictName" id="dictName">
			<option value=""><bean:message key='rule.table.title.select'/></option>
		</select>
		<bean:message key='rule.table.title.isString'/><input type="checkbox" checked="true" id="isStr"><input type="button" value="<bean:message key='rule.table.button.insertDictionary'/>" onclick="insertDict();"/>		
		</td>
	</tr>

	
	<tr>
		<td align="right"><input type="button" value="&nbsp;<bean:message key='rule.table.button.return'/>&nbsp;" onclick="javascript:ret();"/>	
		</td>
	</tr>
	
</table>
<script>



	function insertInput(){
	
		var textBox = document.getElementById("expression");
        var pre = textBox.value.substr(0, start);
        var post = textBox.value.substr(end);
		var input=document.getElementById('inputParameter').value.split("#")[0];
		if(document.getElementById('input').value==null || document.getElementById('input').value=='')
		{
		//document.getElementById('expression').innerText=document.getElementById('expression').value+"{"+ input+"}";
		textBox.value = pre + "#"+input + post;
		}else
		{
		textBox.value = pre +"#" + input+"."+document.getElementById('input').value + post;
		//document.getElementById('expression').innerText=document.getElementById('expression').value+"{"+ input +"."+ document.getElementById('input').value +"}";
		}
	}
	
	function insertOutput(){
		var textBox = document.getElementById("expression");
        var pre = textBox.value.substr(0, start);
        var post = textBox.value.substr(end);
        
		var output=document.getElementById('outputParameter').value.split("#")[0];
		if(document.getElementById('output').value==null || document.getElementById('output').value=='')
		{
		//document.getElementById('expression').innerText=document.getElementById('expression').value+"{"+ output+"}";
		textBox.value = pre + "#"+output+ post;
		}else
		{
		textBox.value = pre +"#"+ output+"."+document.getElementById('output').value + post;
		//document.getElementById('expression').innerText=document.getElementById('expression').value+"{"+ output +"."+ document.getElementById('output').value +"}";
		}
	}
	function insertDict(){
		var textBox = document.getElementById("expression");
        var pre = textBox.value.substr(0, start);
        var post = textBox.value.substr(end);
		if(document.getElementById('isStr').checked)
		{
			textBox.value = pre + "'" +document.getElementById('dictName').value+"'"+ post;
			//document.getElementById('expression').innerText=document.getElementById('expression').value+"'"+document.getElementById('dictName').value+"'";
		}else
		{
			textBox.value = pre + document.getElementById('dictName').value + post;
			//document.getElementById('expression').innerText=document.getElementById('expression').value+document.getElementById('dictName').value;
		}
	}
	
</script>
<%@ include file="/common/footer_eoms.jsp"%>