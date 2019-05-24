<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@ page import="com.boco.eoms.mobilewap.tag.imp.SqlTag" %>
<%@ page autoFlush="true" %>
<%
SqlTag tag = (SqlTag)request.getAttribute("sql");
String wapNodeId=tag.getCardId().substring(tag.getCardId().indexOf("_")+1,tag.getCardId().length());
String app = request.getContextPath(); 
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
    isPageination(document.getElementById("sqlType").value,dORv)
   }
}
function isPageination(isSelect,isDisplay){
    if(isSelect=="select"&&isDisplay=="display"){
    
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
			    '</td></tr>'+
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
  var statementV = form1.statement.value;
  if(statementV==""){
      alert("SQL语句不能为空!");
      document.form1.statement.focus();
      return false;
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
  }else if(verOrDisV=="display"&&form1.sqlType.value=="select"){
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
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
</head>
<body>
<form name="form1" action="<%=app %>/webtool/tag" onSubmit="return checkInput()">
  <input name="act" type="hidden" value="modify"	>
  <input name="tagType" type="hidden" value="sql">
  <input name="tagKey" type="hidden" value="<%=request.getParameter("tagKey") %>">
  <input name="cardId" type="hidden" value="<%=request.getParameter("cardId") %>">
  <input name="class_str" type="hidden" value="com.boco.eoms.mobilewap.tag.imp.SqlTag">

<table width="100%">
	 <tr>
	   <td class="title">修改数据库操作信息</td>
	   <td align="right"></td>
	 </tr>
</table>
<table class="form_table">
   <tr>
		<th>此操作返回变量名称：</th>
		<td>
			<input id="name" name="name" type="text" value="<%=tag.getName() %>" size="20" ><font color="#FF0000">*</font>
		</td>
	</tr>
    <tr>
		<th>SQL语句：</th>
		<td>
			<textarea rows="4" id="statement" name="statement" type="text" ><%=tag.getStatement() %></textarea><font color="#FF0000">*</font>
		</td>
	</tr>
	<tr>
	  <td class="note" colspan="2">说明：
	      <li>在参数框中各参数要以逗号(,)隔开</li>
	      <li>参数个数应与SQL语句中问号数相对应，如没有问号，则参数框为空</li>
      </td>
	</tr>
	<tr>
		<th>参数</th>
		<td>
			<input id="text7" name="sqlParam" type="text" value="<%=tag.getSqlParam() %>"  size="50" >
			<input type="button" value="选择参数变量" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=7','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
		</td>
	</tr>
	<tr>
		<th>SQL类型</th>
		<td>
			<select id="sqlType" name="sqlType" onchange="javascript:changeVerOrDis(form1.sqlType.value,form1.verOrDis.value)">
			  <option value="select" <%if(tag.getSqlType().equals("select"))out.print("selected"); %>>select</option>
			  <option value="update" <%if(tag.getSqlType().equals("update"))out.print("selected"); %>>update</option>
			  <option value="insert" <%if(tag.getSqlType().equals("insert"))out.print("selected"); %>>insert</option>
			  <option value="delete" <%if(tag.getSqlType().equals("delete"))out.print("selected"); %>>delete</option>
			</select>
		</td>
	</tr>
		<tr>
		<th>结果显示或判断</th>
		<td>
			<select id="verOrDis" name="verOrDis" onchange="javascript:changeVerOrDis(form1.sqlType.value,form1.verOrDis.value)">
			  <option value="display" <%if(tag.getVerOrDis().equals("display"))out.print("selected"); %>>显示</option>
			  <option value="verify" <%if(tag.getVerOrDis().equals("verify"))out.print("selected"); %>>判断</option>
			</select>
		</td>
	</tr>
</table>
<span id="span_verOrDis">
<%if(tag.getVerOrDis().equals("display")){
      if(tag.getSqlType().equals("select")){
 %>
        <table class="form_table">
		<tr>
		<th>是否分页</th>
		<td><select id="isPageination" name="isPageination" onchange="javascript:isPageinationPageSize(form1.isPageination.value)">
			<option value="false" <%if(!tag.getIsPageination())out.print("selected"); %>>否</option>
			<option value="true" <%if(tag.getIsPageination())out.print("selected"); %>>是</option>
		</select></td>
		</tr>
		<tr>
	       <th>显示内容：</th>
	       <td><textarea rows="4" id="text8" name="text" type="text"><%=tag.getText()%></textarea>
		      <input type="button" value="选择变量参数" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=8','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">    
		  </td>
	    </tr>
		</table>
		<span id="span_verValue">
		  <%
		  if(tag.getIsPageination()){ %>
		     <table>
				<tr>
				  <td>每页显示记录数：<input name="pageSize" type="text" value="<%=tag.getPageSize() %>"/>条</td>
				</tr>
			 </table>
		  <%} %>
        </span>

<%
      }else{
%>
        <table class="form_table">
		<tr>
	       <td>显示内容：<textarea rows="4" id="text8" name="text" type="text"><%=tag.getText()%></textarea>
		      <input type="button" value="选择变量参数" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=8','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">    
		  </td>
	    </tr>
		</table>
		<span id="span_verValue"></span>
</span>
<%      
      }
}else if(tag.getVerOrDis().equals("verify")){ %>
<table class="form_table">
		<tr>
		<td>选择校验方法</td>
		<td>
		  <select id="verifyMethod" name="verifyMethod" onchange="javascript:verValue(form1.verifyMethod.value)">'+
			<option value="listSize" <%if(!(tag.getVerifyMethod().indexOf("listSize")<0))out.print("selected"); %>>判断记录数</option>
			<option value="isEqual" <%if(!(tag.getVerifyMethod().indexOf("isEqual")<0))out.print("selected"); %>>两值比对</option>
		  </select>
		 </td>
		</tr>
		<tr>
		<td>校验失败是否继续执行下面标签</td>
		<td><select id="isDisplayNext" name="isDisplayNext">
					  <option value="false" <%if(!tag.getIsDisplayNext())out.print("selected"); %>>终止</option>
					  <option value="true" <%if(tag.getIsDisplayNext())out.print("selected"); %>>继续</option>
		</select></td>
		</tr>
		<tr>
		<td>校验正确显示内容</td>
		<%String[] content=tag.getText().split("@:"); %>
		<td><input id="text1" type="text" name="text" value=<%=content[0] %>>
		<input type="button" value="选择变量参数" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=1','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
		</td>
		</tr>
		<tr>
		<td>校验失败显示内容</td>
		<td><input id="text2" type="text" name="text" value=<%=content[1] %>>
		<input type="button" value="选择变量参数" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=2','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
		</td>
		</tr>
		</table>
		<span id="span_verValue">
		<%if(!(tag.getVerifyMethod().indexOf("listSize")<0)){
		    String temp_str=tag.getVerifyMethod();
		    temp_str=temp_str.substring(temp_str.indexOf("(")+1,temp_str.lastIndexOf(")"));
		%>
		    <table>
				<tr>
				   <td>比对记录数：<input name="method_value" type="text" value="<%=temp_str %>"/></td>
				</tr>
			</table>
		<%}else{
		    String temp_str=tag.getVerifyMethod();
		    String[] temp=temp_str.substring(temp_str.indexOf("(")+1,temp_str.lastIndexOf(")")).split(",");
		%>
		    <table>
			  <tr>
			    <td>
			        变量值：<input id="text5" name="method_value" type="text" value="<%=temp[0] %>"/>
			       <input type="button" value="选择变量参数" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=5','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
			    </td>
			    <td>
			        比对值：<input id="text6" name="method_value" type="text" value="<%=temp[1]%>"/>
			        <input type="button" value="选择比对参数" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=6','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
			    </td>
			  </tr>
			</table>
		<%} %>
		</span>
<%} %>
</span>

<table width="100%">
    <tr><td align="right"><input type="submit" class="inputsubmit" value="提交"></td></tr>
</table>  	  
</form>
</body>
</html>
