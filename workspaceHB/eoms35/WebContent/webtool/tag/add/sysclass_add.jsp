<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@ page autoFlush="true" %>
<%
String app = request.getContextPath(); 

String wapCardId = request.getParameter("wapCardId");
String wapNodeId = request.getParameter("wapNodeId");
String tagType = request.getParameter("tagType");

 %>
<script language="javascript">
function changeVerOrDis(isSelect,dORv){
   if(dORv=="verify"){
     span_verOrDis.innerHTML=
      //-------------------------校验部分开始-------------------------------
        '<table class="form_table">'+
		'<tr>'+
		'<th>选择校验方法</th>'+
		'<td><select id="verifyMethod" name="verifyMethod" onchange="javascript:verValue(form1.verifyMethod.value)">'+
			'<option value="listSize">判断记录数</option>'+
			'<option value="isEqual">两值比对</option>'+
		'</select></td>'+
		'</tr>'+
		'<tr>'+
		'<th>校验失败是否继续执行下面标签</th>'+
		'<td><select id="isDisplayNext" name="isDisplayNext">'+
					  '<option value="false">终止</option>'+
					  '<option value="true">继续</option>'+
		'</select></td>'+
		'</tr>'+
		'<tr>'+
		'<th>校验正确显示内容</th>'+
		'<td><input id="text1" type="text" name="text">'+
		'<input type="button" value="选择变量参数" onclick="window.open(\'<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=1\',\'\',\'height=400,width=340,status=no,toolbar=no,menubar=no,location=no\')">'+	    
		'</td>'+
		'</tr>'+
		'<tr>'+
		'<th>校验失败显示内容</th>'+
		'<td><input id="text2" type="text" name="text">'+
		'<input type="button" value="选择变量参数" onclick="window.open(\'<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=2\',\'\',\'height=400,width=340,status=no,toolbar=no,menubar=no,location=no\')">'+	    
		'</td>'+
		'</tr>'+
		'</table>'+
		'<span id="span_verValue"></span>';
　　　//-------------------------校验部分结束-------------------------------
	span_verValue.innerHTML=
              '<table class="form_table">'+
				  '<tr>'+
				   ' <th>比对记录数：</th><td><input name="method_value" type="text" value=""/></td>'+
				  '</tr>'+
				'</table>';
   }else{
    isPageination(document.getElementById("isList").value,dORv)
   }
}
function isPageination(isSelect,isDisplay){
    if(isSelect=="true"&&isDisplay=="display"){
    
    //-------------------------显示部分开始-------------------------------
        span_verOrDis.innerHTML=
        '<table class="form_table">'+
		'<tr>'+
		'<th>是否分页</th>'+
		'<td><select id="isPageination" name="isPageination" onchange="javascript:isPageinationPageSize(form1.isPageination.value)">'+
			'<option value="false">否</option>'+
			'<option value="true">是</option>'+
		'</select></td>'+
		'</tr>'+
		'<tr>'+
	      ' <th>显示内容：</th><td><textarea rows="4" id="text3" name="text" type="text"></textarea>'+
		      '<input type="button" value="选择变量参数" onclick="window.open(\'<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=3\',\'\',\'height=400,width=340,status=no,toolbar=no,menubar=no,location=no\')">'+	    
		  '</td>'+
	    '</tr>'+		
		'</table>'+
		'<span id="span_verValue"></span>';
	//-------------------------显示部分结束-------------------------------
	
    }else{
        span_verOrDis.innerHTML=
        '<table class="form_table">'+
		'<tr>'+
	      ' <th>显示内容：</th><td><textarea rows="4" id="text4" name="text" type="text"></textarea>'+
			      '<input type="button" value="选择变量参数" onclick="window.open(\'<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=4\',\'\',\'height=400,width=340,status=no,toolbar=no,menubar=no,location=no\')">'+	    
		  '</td>'+
	    '</tr>'+
		
		'</table>'+
		'<span id="span_verValue"></span>';
    }
}
function isPageinationPageSize(v){
   if(v=="true"){
    //-------------------------用于”显示“部分中选择分页后的每页显示记录数开始-------------------------------
      span_verValue.innerHTML=
                '<table class="form_table">'+
				  '<tr>'+
				   ' <th>每页显示记录数：</th><td><input name="pageSize" type="text" value=""/>条</td>'+
				  '</tr>'+
				'</table>';
    //-------------------------用于”显示“部分中选择分页后的每页显示记录数结束-------------------------------				
   }else{
      span_verValue.innerHTML='';
   }
}
function verValue(v){
    if(v=="isEqual"){
   //-------------------------用于”校验“部分中选择isEqual后显示内容开始-------------------------------
      span_verValue.innerHTML=
			'<table class="form_table">'+
			  '<tr>'+
			    '<th>'+
			        '变量值：</th><td><input id="text5" name="method_value" type="text" value=""/>'+
			        '<input type="button" value="选择变量参数" onclick="window.open(\'<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=5\',\'\',\'height=400,width=340,status=no,toolbar=no,menubar=no,location=no\')">'+	    
			    '</th></tr>'+
			    '<tr><th>'+
			       ' 比对值：</th><td><input id="text6" name="method_value" type="text" value=""/>'+
			        '<input type="button" value="选择比对参数" onclick="window.open(\'<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=6\',\'\',\'height=400,width=340,status=no,toolbar=no,menubar=no,location=no\')">'+	    
			    '</td>'+
			  '</tr>'+
			'</table>';
 //-------------------------用于”校验“部分中选择isEqual后显示内容结束-------------------------------			
    }
    else if(v=="listSize"){
 //-------------------------用于”校验“部分中选择listSize后显示内容开始-------------------------------    
      span_verValue.innerHTML=
              '<table class="form_table">'+
				  '<tr>'+
				   ' <th>比对记录数：</th><td><input name="method_value" type="text" value=""/></td>'+
				  '</tr>'+
				'</table>';
//-------------------------用于”校验“部分中选择listSize后显示内容结束始-------------------------------    				
    }
}
function checkInput(){
  var nameV = form1.name.value;
  if(nameV==""){
      alert("返回变量名称不能为空!");
      document.form1.name.focus();
      return false;
  }
  var classUrlV = form1.classUrl.value;
  if(classUrlV==""){
      alert("内部类路径不能为空!");
      document.form1.classUrl.focus();
      return false;
  }
  var methodV = form1.method.value;
  if(methodV==""){
      alert("内部类方法名不能为空!");
      document.form1.method.focus();
      return false;
  }
  var inPutParamV = form1.inPutParam.value;
  if(!(inPutParamV=="")){
      if(form1.paramTypes.value==""){
	      alert("请输入类方法参数类型!");
	      document.form1.paramTypes.focus();
	      return false;
	  }
  }
  var verOrDisV = form1.verOrDis.value;
  //如果为判断时
  if(verOrDisV=="verify"){

    //校验正确和失败的显示内容都不能为空
     var texts=form1.text;
     if(texts[0].value==null||texts[0].value==""){
        alert("校验正确显示内容不能为空!");
        texts[0].focus();
        return false;
     }
     if(texts[1].value==null||texts[1].value==""){
        alert("校验失败显示内容不能为空!");
        texts[1].focus();
        return false;
     }
    //为记录数比对方法时比对记录数必须为数字
    if(form1.verifyMethod.value=="listSize"){
       if(form1.method_value.value==""||!form1.method_value.value.match(/^[0-9]*$/gi)){
          alert("比对记录数必须为数字!");
          document.form1.method_value.focus();
          return false;
       }
     //为两值比对方法时变量值和比对值都不能为空
     }else if(form1.verifyMethod.value=="isEqual"){
       var method_values = form1.method_value;       
       if(method_values[0].value==null||method_values[0].value==""){
          alert("变量值不能为空!");
          method_values[0].focus();
          return false;
       }
       if(method_values[1].value==null||method_values[1].value==""){
          alert("比对值不能为空!");
          method_values[1].focus();
          return false;
       }       
     }
     
  //如果为显示并且SQL类型为select时
  }else if(verOrDisV=="display"&&form1.isList.value=="true"){
    //如果要进行分页，则每页显示记录数必须为数字
    if(form1.isPageination.value=="true"){
      if(form1.pageSize.value==""||!form1.pageSize.value.match(/^[0-9]*$/gi)){
        alert("每页显示记录数必须为数字!");
        document.form1.pageSize.focus();
        return false;
      }
    } 
   }
}
</script>
<html>
<head>
<title>管理员WAP配置工具</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css" />
</head>
<body>
<form name="form1" action="<%=app %>/webtool/tag" onSubmit="return checkInput()">
  <input name="act" type="hidden" value="save"	>
   <input name="tagType" type="hidden" value="<%=tagType %>">
  
  <input name="wapCardId" type="hidden" value="<%=wapCardId %>">
  <input name="classStr" type="hidden" value="com.boco.eoms.mobilewap.base.tag.imp.WapSysClassTag">
  <input name="wapNodeId" type="hidden" value="<%=wapNodeId %>">   
<table width="100%">
  <tr>
    <td class="title">增加一个内部类</td>
    <td align="right"></td>
  </tr>
</table>
<table class="form_table">
   <tr>
		<th>此操作返回变量名称：</th>
		<td>
			<input id="name" name="name" type="text" value="" size="20" ><font color="#FF0000">*</font>
		</td>
	</tr>
    <tr>
		<th>内部类路径：</th>
		<td>
			<input id="classUrl" name="classUrl" type="text" value=""><font color="#FF0000">*</font>
		</td>
	</tr>
	<tr>
		<th>内部类方法名：</th>
		<td>
			<input rows="4" id="method" name="method" type="text" value=""  ><font color="#FF0000">*</font>
		</td>
	</tr>
	<tr>
	  <td class="note" colspan="2">说明：
	      <li>在“内部类方法参数类型”框中各值要以逗号(,)隔开</li>
	      <li>各参数类型值要与实际类方法中类型及个数保持一致</li>
      </th>
	</tr>
	<tr>
		<th>内部类方法输入参数值</th>
		<td>
			<input id="text7" name="inPutParam" type="text" value=""  size="50" >
			<!-- 
			<input type="button" value="选择参数变量" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=7','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
			 -->
		</td>
	</tr>
	<tr>
		<th>内部类方法参数类型</th>
		<td>
			<input id="paramTypes" name="paramTypes" type="text" value=""  size="50" >
			<!-- 
			<input type="button" value="选择类型" onclick="window.open('<%=app %>/webtool/common/getParamTypes.jsp?wapNodeId=<%=wapNodeId %>&k=7','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
			 -->
		</td>
	</tr>
	<tr>
	  <td class="note" colspan="2">说明：在“返回值是否为List集”框中如返回值类型不是List，则必须为八个基本类型
      </td>
	</tr>
	<tr>
		<th>返回值是否为List集合</th>
		<td>
			<select id="isList" name="isList" onchange="javascript:changeVerOrDis(form1.isList.value,form1.verOrDis.value)">
			  <option value="true">是</option>
			  <option value="false">否</option>
			</select>
		</td>
	</tr>
		<tr>
		<th>结果显示或判断</th>
		<td>
			<select id="verOrDis" name="verOrDis" onchange="javascript:changeVerOrDis(form1.isList.value,form1.verOrDis.value)">
			  <option value="display">显示</option>
			  <option value="verify">判断</option>
			</select>
		</td>
	</tr>
</table>
<span id="span_verOrDis">
        <table class="form_table">
		<tr>
		<th>是否分页</th>
		<td><select id="isPageination" name="isPageination" onchange="javascript:isPageinationPageSize(form1.isPageination.value)">
			<option value="false">否</option>
			<option value="true">是</option>
		</select></td>
		</tr>
		<tr>
	       <th>显示内容：</th>
	       <td><textarea rows="4" id="text8" name="text" type="text"></textarea>
	       <!-- 
		      <input type="button" value="选择变量参数" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=8','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">    
		    -->
		  </td>
	    </tr>
		</table>
		<span id="span_verValue">
        </span>
</span>


<table width="100%">
    <tr><td align="right"><input type="submit" class="inputsubmit" value="提交"></td></tr>
</table>  	  
</form>
</body>
</html>
