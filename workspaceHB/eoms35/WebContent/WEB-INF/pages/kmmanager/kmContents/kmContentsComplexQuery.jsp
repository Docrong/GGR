<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<jsp:directive.page import="com.boco.eoms.base.util.ApplicationContextHolder"/>

<script type="text/javascript" src="/common/jquery.js"></script>

<bean:define id="nodeId"    name="KmTableTheme" property="nodeId" />
<bean:define id="themeName" name="KmTableTheme" property="themeName" />

<html:form action="/kmContentss.do?method=complexQueryDo" styleId="kmContentsForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<eoms:xbox id="tree" dataUrl="${app}/xtree.do?method=dept" 
    rootId="-1"
    rootText='部门树'
    valueField="deptId" handler="deptName"
    textField="deptName"
    checktype="dept" single="true"></eoms:xbox>

<eoms:xbox id="tree1" dataUrl="${app}/xtree.do?method=userFromDept" 
    rootId="-1" 
    rootText='用户树' 
    valueField="userId" handler="userName"
    textField="userName"
    checktype="user" single="true"></eoms:xbox>

<script type="text/javascript">
var tree;
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmContentsForm'});	
	v.custom = function(){	
	  setFinalSql(); 
	  return true;
	}
});

function onTableChg(table){
    var selValue = table.options[table.options.selectedIndex].value;
	var url = '${app}/kmmanager/kmContentss.do?method=complexQuery&TABLE_ID='+ selValue;
	location.href = url;
}

var jsonContent = eoms.JSONDecode('${data}');//获取返回结果 
var tableName = '${tableName}';
var sqlheader = "select ID,CONTENT_TITLE,CREATE_USER,CREATE_DEPT,CONTENT_KEYS,TABLE_ID,THEME_ID ,CREATE_TIME from " + tableName + " where ";
var userNameArray=new Array(20);
var userNameArray1=new Array(20);
var userIdArray=new Array(20);
var userCount=0;

function append(doc){
    $('ruleEditArea').focus();      
    var currSelectIndex = $(doc).selectedIndex;    
	if( currSelectIndex != null ){
	    var tip = jsonContent[currSelectIndex].name;			
		$('ruleEditArea').innerHTML=$('ruleEditArea').innerHTML+"【"+convertBr(tip)+"】";						
	}
}

function appendExpr(str, type){
	if(str=='')	return;	
	var exprarea = $('ruleEditArea');	
	if(type==null) type='0';	    
	insertAtCursor(exprarea, formatExpr(str,type));	
}

function appendUser(){		
    $('userName').click();
}

function appendUserBack(){ 
    var userName=$('userName').value;
    var userId  =$('userId').value;
    if(userName=='')return;
    if(userId=='')return;
	var exprarea = $('ruleEditArea');    
	insertAtCursor(exprarea,"'"+userId+"'");	
}

function appendDept(){		
    $('deptName').click();
}

function appendDeptBack(){ 
    var deptName=$('deptName').value;
    var deptId  =$('deptId').value;
    if(deptName=='')return;
    if(deptId=='')return;
	var exprarea = $('ruleEditArea');    
	insertAtCursor(exprarea,"'"+deptId+"'");	
}

function appendTime(){
    popUpCalendar($('time'), $('time'), null, null, null, true, -1);
}

function appendTimeBack(){
    var time=$('time').value; 
    if(time=='')return;  
    var exprarea = $('ruleEditArea');    
    <% if ("org.hibernate.dialect.OracleDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) { %>
	insertAtCursor(exprarea,"to_date('"+time+"','yyyy-mm-dd hh24:mi:ss')");
	<% } else {%>
	insertAtCursor(exprarea, time);
	<% } %>
}

function appendDict(doc){
    var exprarea = $('ruleEditArea');    
	insertAtCursor(exprarea, doc.value);
}
	
function convertBr(str){
    var ns = "";
	for(var i=0;i<str.length;i++){
	    if(str.charAt(i)=='\n'){
	        ns+="<br>";
	    }else{
	        ns+=str.charAt(i);
	    }
	}	   
	return ns;	 
}
	
function formatExpr(str,type){
    switch(type){
        case '0'://+【】 如：变量
            str = " 【"+str+"】";
	        break;
	    case '1'://+空格 如：操作符号
	        str = " "+str+" ";
	        break;
	    case '2'://+( ) 如：函数
	        str = " @"+str+"( ) ";
	        break;
	    case '3'://%
	        str = " "+str+" '%%'";
	        break;
	    default://其他直接输出
	        break;        
    }
    return str;   
}		

function insertAtCursor(myField,myValue){
    if (document.selection){	  
        myField.focus();   
        sel   =   document.selection.createRange();   
        sel.text   =   myValue;   
    }   
    else if (myField.selectionStart|| myField.selectionStart == "0"){ 	  
        var startPos = myField.selectionStart;   
        var endPos   = myField.selectionEnd;   
        myField.value = myField.value.substring(0,startPos) + myValue + 
                        myField.value.substring(endPos,myField.value.length);   
    }else{	 
        myField.value += myValue;   
    }
}

function resetEdit(){
    var exprarea = $('ruleEditArea');
	exprarea.value='';
	$('ruleEditArea').focus();
}

function setFinalSql(){
    var exprarea = $('ruleEditArea').value;	
    for(var i=0;i<jsonContent.length;i++){                 
        var value=jsonContent[i].id; 
        var replacestr="【"+jsonContent[i].name+"】";
        var regS = new RegExp(replacestr,"gi");
        exprarea=exprarea.replace(regS,value);        
    }	
    $('ruleScript').value = sqlheader + exprarea;
}
	  
function getFinalSql(){
    var exprarea = $('ruleEditArea').value;
	if(exprarea==""){
	    alert("请填写规则脚本");
	    return null;
	}	
	for(var i=0;i<jsonContent.length;i++){                 
        var value=jsonContent[i].id; 
        var replacestr="【"+jsonContent[i].name+"】";
        var regS = new RegExp(replacestr,"gi");
	    exprarea=exprarea.replace(regS,value);        
    }
    var regS = new RegExp('\\%',"gi");
	exprarea=exprarea.replace(regS,'#');    
    return sqlheader + exprarea;
}
     
function hidden_submit_param(){	
	var ua      = navigator.userAgent;
    var opera   = /opera [56789]|opera\/[56789]/i.test(ua);
    var ie      = !opera && /msie [56789]/i.test(ua);       // preventing opera to be identified as ie
    var mozilla = !opera && /mozilla\/[56789]/i.test(ua);   // preventing opera to be identified as mz
	var submitURL='${app}/kmmanager/kmRules.do?method=checkSql';
	var oGet = null;
	var oReq = null;
	var regS = new RegExp("\\?","gi");
	var sqlstr = getFinalSql();
	if(sqlstr!=null){
		var sql=getFinalSql().replace(regS,"''");	
		var param="para="+sql;
		if (mozilla) {
			oReq = new XMLHttpRequest(); 
		} else {
			try { oReq=new ActiveXObject('MSXML2.XMLHTTP'); } catch(e) {
				try{ oReq=new ActiveXObject('Microsoft.XMLHTTP'); } catch(oc) { oReq=null }
			}
		}
		try {
			oReq.open("POST", submitURL, false);
			oReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			oReq.send(param);		
		} catch (e) {
			alert("隐含请求调用失败！");
			return oGet;
		}
		if (mozilla) {
			oGet = oReq.responseXML;
		} else {
			oGet = new ActiveXObject("MSXML2.DOMDocument");
			oGet.async=false;
			oGet.loadXML(oReq.responseText);
		}
		// 处理返回值
		var retCodeNode = oGet.selectSingleNode("root/msg/content" );
		var retCode = retCodeNode.text;
		alert(retCode);
	}
}

function onSubmit(){
	var ua      = navigator.userAgent;
    var opera   = /opera [56789]|opera\/[56789]/i.test(ua);
    var ie      = !opera && /msie [56789]/i.test(ua);       // preventing opera to be identified as ie
    var mozilla = !opera && /mozilla\/[56789]/i.test(ua);   // preventing opera to be identified as mz
	var submitURL='${app}/kmmanager/kmRules.do?method=checkSql';
	var oGet = null;
	var oReq = null;
	var regS = new RegExp("\\?","gi");
	var sqlstr = getFinalSql();
	if(sqlstr==null){
		return null;
	}
	if(sqlstr!=null){
		var sql=getFinalSql().replace(regS,"''");	
		var param="para="+sql;
		if (mozilla) {
			oReq = new XMLHttpRequest(); 
		} else {
			try { oReq=new ActiveXObject('MSXML2.XMLHTTP'); } catch(e) {
				try{ oReq=new ActiveXObject('Microsoft.XMLHTTP'); } catch(oc) { oReq=null }
			}
		}
		try {
			oReq.open("POST", submitURL, false);
			oReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			oReq.send(param);		
		} catch (e) {
			alert("隐含请求调用失败！");
			return oGet;
		}
		if (mozilla) {
			oGet = oReq.responseXML;
		} else {
			oGet = new ActiveXObject("MSXML2.DOMDocument");
			oGet.async=false;
			oGet.loadXML(oReq.responseText);
		}
		// 处理返回值
		var retCodeNode = oGet.selectSingleNode("root/msg/content" );
		var retCode = retCodeNode.text;
	}
	if(retCode!='测试成功!!!'){
		alert(retCode);
		return null;
	}
		setFinalSql();
		document.forms[0].submit();
}
</script>

<!-- 知识状态：1-草稿，2-有效，3-失效，4-删除 -->
<input type="hidden" id="CONTENT_STATUS" name="TableInfo/CONTENT_STATUS" value="2" />

<input type="hidden" id="ruleScript"  name="ruleScript" value="" />

<table class="formTable">
    <caption>
		<div class="header center"><fmt:message key="kmContents.form.heading"/>&nbsp;查询</div>
	</caption>
	
	<tr>
	    <td style="background:#EDF5FD;width:5%;" >
	        <fmt:message key="kmContents.tableId" />
	    </td>
		<td class="content" colspan="2">
	        <html:select property="QueryCond/TABLE_ID/criteria/value" styleId="TABLE_ID"
						styleClass="text medium"
						alt="allowBlank:false,vtext:'请选择知识库...'" value="${kmContentsForm.id}" onchange="onTableChg(this)">
			    <html:optionsCollection name="KmTableGeneralList" value="id" label="tableChname"></html:optionsCollection>
	        </html:select>
		</td>	
	</tr>

	<tr>
        <td style="background:#EDF5FD;width:5%;" rowspan="5">组合查询条件</td>
	    <td style="background:#EDF5FD;width:10%;" >字段列表：</td>	
	    <td class="content">
	        <input type="button" class="btn" styleId="btnb2" value="并且" onclick="appendExpr('and','1');" />
	        <input type="button" class="btn" styleId="btnb3" value="或者" onclick="appendExpr('or','1');" />
	        <input type="button" class="btn" styleId="btnb3" value="(" onclick="appendExpr('(','1');" />
	        <input type="button" class="btn" styleId="btnb3" value=")" onclick="appendExpr(')','1');" />
	        <input type="button" class="btn" styleId="btnb1" value="等于" onclick="appendExpr('=','1');" />
	        <input type="button" class="btn" styleId="btnb3" value="大于" onclick="appendExpr('>','1');" />
	        <input type="button" class="btn" styleId="btnb3" value="小于" onclick="appendExpr('<','1');" />
	        <input type="button" class="btn" styleId="btnb4" value="不等于" onclick="appendExpr('!=','1');" />
	        <input type="button" class="btn" styleId="btnb5" value="不小于" onclick="appendExpr('>=','1');" />
	        <input type="button" class="btn" styleId="btnb6" value="不大于" onclick="appendExpr('<=','1');"/>
	        <input type="button" class="btn" styleId="btnb6" value="like" onclick="appendExpr('like','3');"/> 	    
	    </td>
	</tr>

	<tr>
        <td style="width:10%;" rowspan="4">
            <html:select property="themeId" styleId="themeId" size="15" onclick="append(this)">
	        <html:optionsCollection label="colChname" name="KmTableColumnList" value="colName" />
	        </html:select>
        </td>
 
	    <td class="content">
	        <input type="button" class="btn" styleId="btnb6" value="用户树"	onclick="appendUser();"/>
	        <input type="hidden" id="deptName" name="deptName" value="" />
	        <input type="hidden" id="deptId"   name="deptId"   value="" onpropertychange="appendDeptBack();"/>
	                            
	        <input type="button" class="btn" styleId="btnb6" value="部门树"	onclick="appendDept();"/>
	        <input type="hidden" id="userName" name="userName" value="" />
	        <input type="hidden" id="userId"   name="userId"   value="" onpropertychange="appendUserBack();"/>	
	                                                        
	        <input type="button" class="btn" styleId="btnb6" value="时间控件"	onclick="appendTime();"/>
	        <input type="text" style="width:0px;height:0px;" id="time" name="time" value="" onpropertychange="appendTimeBack();"/>
	    
	    </td>
	</tr>

	<tr>	    
	    <td class="content">
	         知识难易程度：<eoms:dict key="dict-kmmanager" dictId="levelFlag" isQuery="false" defaultId="" selectId="levelFlag" 
	                     beanId="selectXML" alt="allowBlank:true" onchange="appendDict(this)"/>&nbsp;&nbsp;
	         知识等级：<eoms:dict key="dict-kmmanager" dictId="rolestrFlag" isQuery="false" defaultId="" selectId="rolestrFlag" 
	                      beanId="selectXML" alt="allowBlank:true" onchange="appendDict(this)"/>&nbsp;&nbsp;
	         知识状态：<eoms:dict key="dict-kmmanager" dictId="contentStatus" isQuery="false" defaultId="" selectId="contentStatus" 
	                   beanId="selectXML" alt="allowBlank:true" onchange="appendDict(this)"/>&nbsp;&nbsp;
	         是否：<eoms:dict key="dict-kmmanager" dictId="isOrNot" isQuery="false" defaultId="" selectId="isOrNot" 
	                beanId="selectXML" alt="allowBlank:true" onchange="appendDict(this)"/>&nbsp;&nbsp;
	    </td>
	</tr>

	<tr>	    
	    <td class="content">
	        <textarea name="ruleEditArea" id="ruleEditArea" class="textarea max" alt="allowBlank:false'"  cols="70" rows="24" attributesText="id='ruleEditArea'" ></textarea>
	    </td>
	</tr>

	<tr>
	    <td class="content">
	        <input type="button" id="btnsave" value="清除" class="btn" onclick='resetEdit()'>             
	        <input type="button" id="btnsave" value="测试脚本" class="btn" onclick='hidden_submit_param()'>   	    
	    </td>
	</tr>
</table>

</fmt:bundle>

<br>

<table>
	<tr>
		<td>
			<input type="button" class="btn" value="<fmt:message key="button.search"/>"  onclick="onSubmit()"/>
		</td>
	</tr>
</table>

<br>
<font color="red">注意：查询前，请先测试脚本！</font>

</html:form>

<%@ include file="/common/footer_eoms.jsp"%>