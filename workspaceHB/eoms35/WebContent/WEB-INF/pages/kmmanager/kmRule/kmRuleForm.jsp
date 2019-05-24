<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript" src="/common/jquery.js"></script>
<%
   String val="4";
%>
<script type="text/javascript">
var jsonContent = eoms.JSONDecode('${data}');//获取返回结果 
var tableName='${tableName}';
var sqlheader="select * from "+tableName+" where ";
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmRuleForm'});
	init();	
	v.custom = function(){	
	  setFinalSql(); 
	  return true;
	}	
});
function init(){
        var exprarea = $('ruleEditArea').value;	
        var position=exprarea.indexOf("where");        
        exprarea=exprarea.substring(position+6);
	    for(var i=0;i<jsonContent.length;i++){                 
            var value=jsonContent[i].id; 
            var replacestr="【"+jsonContent[i].name+"】";
            var regS = new RegExp(value,"gi");
	        exprarea=exprarea.replace(regS,replacestr);        
        }	
        $('ruleEditArea').value=exprarea;   
}

function append(doc){
    $('ruleEditArea').focus();      
    var currSelectIndex = $(doc).selectedIndex;    
	if( currSelectIndex != null )
		{		
			var tip = jsonContent[currSelectIndex].name;			
			$('ruleEditArea').innerHTML=$('ruleEditArea').innerHTML+"【"+convertBr(tip)+"】";						
		}
		
}

function getIdByName(name){
   for(var i=0;i<jsonContent.length;i++){
       if(jsonContent[i].name==name){
        alert("name="+jsonContent[i].name);
         return jsonContent[currSelectIndex].id;
       }
   }
   return name;
  
}
function getTypeByName(name){
var type='';
   for(var i=0;i<jsonContent.length;i++){
       if(jsonContent[i].name==name){
         type=jsonContent[i].colType+"("+jsonContent[i].typeValue+")";
       }
   }
   return type;
}

function appendExpr(str,type)
{		
    
	if(str=='')	return;	
	var exprarea = $('ruleEditArea');	
	if(type==null) type='0';	    
	insertAtCursor(exprarea,formatExpr(str,type));	
}

function setSelByBiz(sel,biz,param,vn,nn){
	   var sel_vars = new Array();
	   var sub = new HiddenSubmit(biz);
	   for(var i=0;i<param.length;i++){
          sub.add(param[i][0],param[i][1]);
       }
       if(sub.submit()){
           var vfs = sub.getValues(vn);
           var nfs = sub.getValues(nn);
           for(var i=0;i<vfs.length;i++){
              sel_vars.push([vfs[i],nfs[i]]);
           }	               	             
       }
       addSelectOptions(sel,sel_vars)
	}
	function getSelText(obj){
	  var os = $("option",$(obj));
	  for(var i=0;i<os.length;i++){
	     if(os.eq(i).is(":selected"))
	       return os.eq(i).text();
	  }
	  return;
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
	      default://其他直接输出
	        break;        
	    }
	 return str;   
	}		
	
  function addSelectOptions(selobj,values) {           
	    var sel = $(selobj).get(0);
	    sel.options.length=0;
	    if(values!=null&&values.length>0){
			sel.options.length = values.length;
			for (var i=0;i<values.length;i++) {
				sel.options[i].value = values[i][0];
				sel.options[i].text = values[i][1];
			}
		}
	}  
	function setCheckedValue(obj,value,type){
	
	  var selobj =  $(obj).val([value]);
	  /*
	  if(type==null) type="SELECT";
	  switch(type){
	  case "SELECT":	
	      selobj = $(obj);      
		  for(var i=0;i<selobj.length;i++){
		       if(selobj.options[i].value==value){
		         selobj.selectedIndex=i;
		         break;
		       }
		  }
	   break;
	  case "RADIO":
	      for(var i=0;i<selobj.length;i++){
		       if(selobj[i].value==value){
		         selobj[i].checked=true;
		         break;
		       }
		  }
	      break;
	  case "CHECKBOX":
  	    for(var i=0;i<selobj.length;i++){
		       if(selobj[i].value==value){
		          selobj[i].checked=true;		      
		       }else{
		         selobj[i].checked=false;		      
		       }
		  }
		  break;
	  }
	  */
	}	
	function insertAtCursor(myField,myValue){ 	
	 if (document.selection){	  
	        myField.focus();   
	        sel   =   document.selection.createRange();   
	        sel.text   =   myValue;   
	  }   
	  else if (myField.selectionStart|| myField.selectionStart == "0")   
	  { 	  
	      var   startPos = myField.selectionStart;   
	      var   endPos   = myField.selectionEnd;   
	      myField.value  = myField.value.substring(0,startPos) + myValue + 
	                       myField.value.substring(endPos,myField.value.length);   
	  }else{	 
	  	  myField.value +=   myValue;   
	  }
	  }

     function  resetEdit(){ 
	   var exprarea = $('ruleEditArea');
	   exprarea.value='';
	   $('ruleEditArea').focus();
	  }
	
	 function getPara(){ 
	   $('ruleParameter').value="";
	   var exprarea = $('ruleEditArea').value;
	   var position=exprarea.indexOf("?");
	   var i=0;
	   while(position>-1){
	      i++;
	      var position1=exprarea.indexOf("【");
	      var position2=exprarea.indexOf("】");
	      var str=exprarea.substring(position1,position2+1);
	      var name=exprarea.substring(position1+1,position2); 
	          
	      $('ruleParameter').innerHTML=$('ruleParameter').innerHTML+i+","+str+" 类型："+getTypeByName(name)+" ;  ";
	      exprarea=exprarea.substring(position+1);	     
	      position=exprarea.indexOf("?");
	   }	
	  }
	  
	   function  setFinalSql(){ 
	     var exprarea = $('ruleEditArea').value;	
	     for(var i=0;i<jsonContent.length;i++){                 
            var value=jsonContent[i].id; 
            var replacestr="【"+jsonContent[i].name+"】";
            var regS = new RegExp(replacestr,"gi");
	         exprarea=exprarea.replace(regS,value);        
         }	
         $('ruleScript').value=sqlheader+exprarea;
	  }
	  
      function  getFinalSql(){ 
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
         return sqlheader+exprarea;
	  }
     
     function hidden_submit_param()
{	
	var ua      = navigator.userAgent;
    var opera   = /opera [56789]|opera\/[56789]/i.test(ua);
    var ie      = !opera && /msie [56789]/i.test(ua);       // preventing opera to be identified as ie
    var mozilla = !opera && /mozilla\/[56789]/i.test(ua);   // preventing opera to be identified as mz
	var submitURL='${app}/kmmanager/kmRules.do?method=checkSql';
	var oGet = null;
	var oReq = null;
	var regS = new RegExp("\\?","gi");
	var sqlstr=getFinalSql();	
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
</script>

<html:form action="/kmRules.do?method=save" styleId="kmRuleForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmRule.form.heading"/></div>
	</caption>
	<tr>
		<td class="label">			
		</td>
		<td class="content">		
		</td>
		<td class="label">			
		</td>
		<td class="content">		
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmRule.ruleName" />
		</td>
		<td class="content" colspan="3">
			<html:text property="ruleName" styleId="ruleName"
						styleClass="text max"
						alt="allowBlank:false,vtext:''" value="${kmRuleForm.ruleName}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmRule.ruleScript" />
		</td>
		
		<td class="content" colspan="3">
		<table>  
  <tr>
    <td>
    字段列表：
    </td>
    <td rowspan="2">
      <table width="100%">
        <tr>
          <td>         
		 <input type="button" class="btn" styleId="btnb2" value="并且" onclick="appendExpr('and','1');" />
		 <input type="button" class="btn" styleId="btnb3" value="或者" onclick="appendExpr('or','1');" />
		 <input type="button" class="btn" styleId="btnb3" value="(" onclick="appendExpr('(','1');" />
		 <input type="button" class="btn" styleId="btnb3" value=")" onclick="appendExpr(')','1');" />
		 <input type="button" class="btn" styleId="btnb1" value="等于" onclick="appendExpr('=','1');" />
		 <input type="button" class="btn" styleId="btnb3" value="大于" onclick="appendExpr('>','1');" />
		 <input type="button" class="btn" styleId="btnb3" value="小于" onclick="appendExpr('<','1');" />
		 <input type="button" class="btn" styleId="btnb4" value="不等于" onclick="appendExpr('!=','1');" />
		 <input type="button" class="btn" styleId="btnb5" value="不小于"	onclick="appendExpr('>=','1');" />
		 <input type="button" class="btn" styleId="btnb6" value="不大于"	onclick="appendExpr('<=','1');"/> 
		 <input type="button" class="btn" styleId="btnb6" value="参数"	onclick="appendExpr('?','1');"/>
		 <input type="button" class="btn" styleId="btnb6" value="like"	onclick="appendExpr('like','1');"/>        
          </td>
        </tr>
         <tr>          
          <td>           
           <textarea name="ruleEditArea" id="ruleEditArea" class="textarea max" alt="allowBlank:false'"  cols="70" rows="24" attributesText="id='ruleEditArea'" >${kmRuleForm.ruleScript}</textarea>
           <html:hidden property="ruleScript" value="${kmRuleForm.ruleScript}" />         
           </td>          
        </tr>
         <tr>          
          <td>           
             <!--input type="button" id="btnsave" value="重置" class="btn" onclick='reset()'>  -->
             <input type="button" id="btnsave" value="清除" class="btn" onclick='resetEdit()'>
             <input type="button" id="btnsave" value="获取参数" class="btn" onclick='getPara()'> 
             <input type="button" id="btnsave" value="测试脚本" class="btn" onclick='hidden_submit_param()'>         
           </td>          
        </tr>
      </table>
    </td>  
  </tr>
  
  <tr>
    <td width="15%">  
      <html:select property="ruleScriptX" styleId="ruleScript" size="11" onclick="append(this)">
        <html:optionsCollection label="colChname" name="list" value="colName" />
      </html:select>
    </td> 
  </tr> 
   
</table>

</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmRule.ruleParameter" />
		</td>
		<td class="content" colspan="3">		
			<textarea name="ruleParameter" id="ruleParameter" class="textarea max" alt="allowBlank:false'" >${kmRuleForm.ruleParameter}</textarea>
		</td>
	</tr>

	<html:hidden property="isDeleted" value="0" />

	<tr>
		<td class="label">
			<fmt:message key="kmRule.remark" />
		</td>
		<td class="content" colspan="3">
			
		<textarea name="remark" id="remark" class="textarea max" alt="allowBlank:false'" >${kmRuleForm.remark}</textarea>
		</td>
	</tr>


</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty kmRuleForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('<fmt:message key="message.delMessage"/>')){
						var url='${app}/kmmanager/kmRules.do?method=remove&id=${kmRuleForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>


<html:hidden property="id" value="${kmRuleForm.id}" />
<html:hidden property="contentId" value="${kmRuleForm.contentId}" />
<html:hidden property="nodeId" value="${nodeId}" />
<html:hidden property="itemId" value="" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>