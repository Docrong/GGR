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
      //-------------------------У�鲿�ֿ�ʼ-------------------------------
        '<table class="form_table">'+
		'<tr>'+
		'<th>ѡ��У�鷽��</th>'+
		'<td><select id="verifyMethod" name="verifyMethod" onchange="javascript:verValue(form1.verifyMethod.value)">'+
			'<option value="listSize">�жϼ�¼��</option>'+
			'<option value="isEqual">��ֵ�ȶ�</option>'+
		'</select></td>'+
		'</tr>'+
		'<tr>'+
		'<th>У��ʧ���Ƿ����ִ�������ǩ</th>'+
		'<td><select id="isDisplayNext" name="isDisplayNext">'+
					  '<option value="false">��ֹ</option>'+
					  '<option value="true">����</option>'+
		'</select></td>'+
		'</tr>'+
		'<tr>'+
		'<th>У����ȷ��ʾ����</th>'+
		'<td><input id="text1" type="text" name="text">'+
		'<input type="button" value="ѡ���������" onclick="window.open(\'<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=1\',\'\',\'height=400,width=340,status=no,toolbar=no,menubar=no,location=no\')">'+	    
		'</td>'+
		'</tr>'+
		'<tr>'+
		'<th>У��ʧ����ʾ����</th>'+
		'<td><input id="text2" type="text" name="text">'+
		'<input type="button" value="ѡ���������" onclick="window.open(\'<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=2\',\'\',\'height=400,width=340,status=no,toolbar=no,menubar=no,location=no\')">'+	    
		'</td>'+
		'</tr>'+
		'</table>'+
		'<span id="span_verValue"></span>';
������//-------------------------У�鲿�ֽ���-------------------------------
	span_verValue.innerHTML=
              '<table class="form_table">'+
				  '<tr>'+
				   ' <th>�ȶԼ�¼����</th><td><input name="method_value" type="text" value=""/></td>'+
				  '</tr>'+
				'</table>';
   }else{
    isPageination(document.getElementById("sqlType").value,dORv)
   }
}
function isPageination(isSelect,isDisplay){
    if(isSelect=="select"&&isDisplay=="display"){
    
    //-------------------------��ʾ���ֿ�ʼ-------------------------------
        span_verOrDis.innerHTML=
        '<table class="form_table">'+
		'<tr>'+
		'<th>�Ƿ��ҳ</th>'+
		'<td><select id="isPageination" name="isPageination" onchange="javascript:isPageinationPageSize(form1.isPageination.value)">'+
			'<option value="false">��</option>'+
			'<option value="true">��</option>'+
		'</select></td>'+
		'</tr>'+
		'<tr>'+
	      ' <th>��ʾ���ݣ�</th><td><textarea rows="4" id="text3" name="text" type="text"></textarea>'+
		      '<input type="button" value="ѡ���������" onclick="window.open(\'<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=3\',\'\',\'height=400,width=340,status=no,toolbar=no,menubar=no,location=no\')">'+	    
		  '</td>'+
	    '</tr>'+		
		'</table>'+
		'<span id="span_verValue"></span>';
	//-------------------------��ʾ���ֽ���-------------------------------
	
    }else{
        span_verOrDis.innerHTML=
        '<table class="form_table">'+
		'<tr>'+
	      ' <th>��ʾ���ݣ�</th><td><textarea rows="4" id="text4" name="text" type="text"></textarea>'+
		      '<input type="button" value="ѡ���������" onclick="window.open(\'<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=4\',\'\',\'height=400,width=340,status=no,toolbar=no,menubar=no,location=no\')">'+	    
		  '</td>'+
	    '</tr>'+
		
		'</table>'+
		'<span id="span_verValue"></span>';
    }
}
function isPageinationPageSize(v){
   if(v=="true"){
    //-------------------------���ڡ���ʾ��������ѡ���ҳ���ÿҳ��ʾ��¼����ʼ-------------------------------
      span_verValue.innerHTML=
                '<table class="form_table">'+
				  '<tr>'+
				   ' <th>ÿҳ��ʾ��¼����</th><td><input name="pageSize" type="text" value=""/>��</td>'+
				  '</tr>'+
				'</table>';
    //-------------------------���ڡ���ʾ��������ѡ���ҳ���ÿҳ��ʾ��¼������-------------------------------				
   }else{
      span_verValue.innerHTML='';
   }
}
function verValue(v){
    if(v=="isEqual"){
   //-------------------------���ڡ�У�顰������ѡ��isEqual����ʾ���ݿ�ʼ-------------------------------
      span_verValue.innerHTML=
			'<table class="form_table">'+
			  '<tr>'+
			    '<th>'+
			        '����ֵ��</th><td><input id="text5" name="method_value" type="text" value=""/>'+
			        '<input type="button" value="ѡ���������" onclick="window.open(\'<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=5\',\'\',\'height=400,width=340,status=no,toolbar=no,menubar=no,location=no\')">'+	    
			    '</td></tr>'+
			    '<tr><th>'+
			       ' �ȶ�ֵ��</th><td><input id="text6" name="method_value" type="text" value=""/>'+
			        '<input type="button" value="ѡ��ȶԲ���" onclick="window.open(\'<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=6\',\'\',\'height=400,width=340,status=no,toolbar=no,menubar=no,location=no\')">'+	    
			    '</td>'+
			  '</tr>'+
			'</table>';
 //-------------------------���ڡ�У�顰������ѡ��isEqual����ʾ���ݽ���-------------------------------			
    }
    else if(v=="listSize"){
 //-------------------------���ڡ�У�顰������ѡ��listSize����ʾ���ݿ�ʼ-------------------------------    
      span_verValue.innerHTML=
              '<table class="form_table">'+
				  '<tr>'+
				   ' <th>�ȶԼ�¼����</th><td><input name="method_value" type="text" value=""/></td>'+
				  '</tr>'+
				'</table>';
//-------------------------���ڡ�У�顰������ѡ��listSize����ʾ���ݽ���ʼ-------------------------------    				
    }
}
function checkInput(){
  var nameV = form1.name.value;
  if(nameV==""){
      alert("���ر������Ʋ���Ϊ��!");
      document.form1.name.focus();
      return false;
  }
  var statementV = form1.statement.value;
  if(statementV==""){
      alert("SQL��䲻��Ϊ��!");
      document.form1.statement.focus();
      return false;
  }
  var verOrDisV = form1.verOrDis.value;
  //���Ϊ�ж�ʱ
  if(verOrDisV=="verify"){

    //У����ȷ��ʧ�ܵ���ʾ���ݶ�����Ϊ��
     var texts=form1.text;
     if(texts[0].value==null||texts[0].value==""){
        alert("У����ȷ��ʾ���ݲ���Ϊ��!");
        texts[0].focus();
        return false;
     }
     if(texts[1].value==null||texts[1].value==""){
        alert("У��ʧ����ʾ���ݲ���Ϊ��!");
        texts[1].focus();
        return false;
     }
    //Ϊ��¼���ȶԷ���ʱ�ȶԼ�¼������Ϊ����
    if(form1.verifyMethod.value=="listSize"){
       if(form1.method_value.value==""||!form1.method_value.value.match(/^[0-9]*$/gi)){
          alert("�ȶԼ�¼������Ϊ����!");
          document.form1.method_value.focus();
          return false;
       }
     //Ϊ��ֵ�ȶԷ���ʱ����ֵ�ͱȶ�ֵ������Ϊ��
     }else if(form1.verifyMethod.value=="isEqual"){
       var method_values = form1.method_value;       
       if(method_values[0].value==null||method_values[0].value==""){
          alert("����ֵ����Ϊ��!");
          method_values[0].focus();
          return false;
       }
       if(method_values[1].value==null||method_values[1].value==""){
          alert("�ȶ�ֵ����Ϊ��!");
          method_values[1].focus();
          return false;
       }       
     }
     
  //���Ϊ��ʾ����SQL����Ϊselectʱ
  }else if(verOrDisV=="display"&&form1.sqlType.value=="select"){
    //���Ҫ���з�ҳ����ÿҳ��ʾ��¼������Ϊ����
    if(form1.isPageination.value=="true"){
      if(form1.pageSize.value==""||!form1.pageSize.value.match(/^[0-9]*$/gi)){
        alert("ÿҳ��ʾ��¼������Ϊ����!");
        document.form1.pageSize.focus();
        return false;
      }
    } 
   }
}
</script>
<html>
<head>
<title>����ԱWAP���ù���</title>
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
	   <td class="title">�޸����ݿ������Ϣ</td>
	   <td align="right"></td>
	 </tr>
</table>
<table class="form_table">
   <tr>
		<th>�˲������ر������ƣ�</th>
		<td>
			<input id="name" name="name" type="text" value="<%=tag.getName() %>" size="20" ><font color="#FF0000">*</font>
		</td>
	</tr>
    <tr>
		<th>SQL��䣺</th>
		<td>
			<textarea rows="4" id="statement" name="statement" type="text" ><%=tag.getStatement() %></textarea><font color="#FF0000">*</font>
		</td>
	</tr>
	<tr>
	  <td class="note" colspan="2">˵����
	      <li>�ڲ������и�����Ҫ�Զ���(,)����</li>
	      <li>��������Ӧ��SQL������ʺ������Ӧ����û���ʺţ��������Ϊ��</li>
      </td>
	</tr>
	<tr>
		<th>����</th>
		<td>
			<input id="text7" name="sqlParam" type="text" value="<%=tag.getSqlParam() %>"  size="50" >
			<input type="button" value="ѡ���������" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=7','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
		</td>
	</tr>
	<tr>
		<th>SQL����</th>
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
		<th>�����ʾ���ж�</th>
		<td>
			<select id="verOrDis" name="verOrDis" onchange="javascript:changeVerOrDis(form1.sqlType.value,form1.verOrDis.value)">
			  <option value="display" <%if(tag.getVerOrDis().equals("display"))out.print("selected"); %>>��ʾ</option>
			  <option value="verify" <%if(tag.getVerOrDis().equals("verify"))out.print("selected"); %>>�ж�</option>
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
		<th>�Ƿ��ҳ</th>
		<td><select id="isPageination" name="isPageination" onchange="javascript:isPageinationPageSize(form1.isPageination.value)">
			<option value="false" <%if(!tag.getIsPageination())out.print("selected"); %>>��</option>
			<option value="true" <%if(tag.getIsPageination())out.print("selected"); %>>��</option>
		</select></td>
		</tr>
		<tr>
	       <th>��ʾ���ݣ�</th>
	       <td><textarea rows="4" id="text8" name="text" type="text"><%=tag.getText()%></textarea>
		      <input type="button" value="ѡ���������" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=8','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">    
		  </td>
	    </tr>
		</table>
		<span id="span_verValue">
		  <%
		  if(tag.getIsPageination()){ %>
		     <table>
				<tr>
				  <td>ÿҳ��ʾ��¼����<input name="pageSize" type="text" value="<%=tag.getPageSize() %>"/>��</td>
				</tr>
			 </table>
		  <%} %>
        </span>

<%
      }else{
%>
        <table class="form_table">
		<tr>
	       <td>��ʾ���ݣ�<textarea rows="4" id="text8" name="text" type="text"><%=tag.getText()%></textarea>
		      <input type="button" value="ѡ���������" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=8','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">    
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
		<td>ѡ��У�鷽��</td>
		<td>
		  <select id="verifyMethod" name="verifyMethod" onchange="javascript:verValue(form1.verifyMethod.value)">'+
			<option value="listSize" <%if(!(tag.getVerifyMethod().indexOf("listSize")<0))out.print("selected"); %>>�жϼ�¼��</option>
			<option value="isEqual" <%if(!(tag.getVerifyMethod().indexOf("isEqual")<0))out.print("selected"); %>>��ֵ�ȶ�</option>
		  </select>
		 </td>
		</tr>
		<tr>
		<td>У��ʧ���Ƿ����ִ�������ǩ</td>
		<td><select id="isDisplayNext" name="isDisplayNext">
					  <option value="false" <%if(!tag.getIsDisplayNext())out.print("selected"); %>>��ֹ</option>
					  <option value="true" <%if(tag.getIsDisplayNext())out.print("selected"); %>>����</option>
		</select></td>
		</tr>
		<tr>
		<td>У����ȷ��ʾ����</td>
		<%String[] content=tag.getText().split("@:"); %>
		<td><input id="text1" type="text" name="text" value=<%=content[0] %>>
		<input type="button" value="ѡ���������" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=1','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
		</td>
		</tr>
		<tr>
		<td>У��ʧ����ʾ����</td>
		<td><input id="text2" type="text" name="text" value=<%=content[1] %>>
		<input type="button" value="ѡ���������" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=2','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
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
				   <td>�ȶԼ�¼����<input name="method_value" type="text" value="<%=temp_str %>"/></td>
				</tr>
			</table>
		<%}else{
		    String temp_str=tag.getVerifyMethod();
		    String[] temp=temp_str.substring(temp_str.indexOf("(")+1,temp_str.lastIndexOf(")")).split(",");
		%>
		    <table>
			  <tr>
			    <td>
			        ����ֵ��<input id="text5" name="method_value" type="text" value="<%=temp[0] %>"/>
			       <input type="button" value="ѡ���������" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=5','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
			    </td>
			    <td>
			        �ȶ�ֵ��<input id="text6" name="method_value" type="text" value="<%=temp[1]%>"/>
			        <input type="button" value="ѡ��ȶԲ���" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=6','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
			    </td>
			  </tr>
			</table>
		<%} %>
		</span>
<%} %>
</span>

<table width="100%">
    <tr><td align="right"><input type="submit" class="inputsubmit" value="�ύ"></td></tr>
</table>  	  
</form>
</body>
</html>
