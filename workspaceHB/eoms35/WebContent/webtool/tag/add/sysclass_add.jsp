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
    isPageination(document.getElementById("isList").value,dORv)
   }
}
function isPageination(isSelect,isDisplay){
    if(isSelect=="true"&&isDisplay=="display"){
    
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
			    '</th></tr>'+
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
  var classUrlV = form1.classUrl.value;
  if(classUrlV==""){
      alert("�ڲ���·������Ϊ��!");
      document.form1.classUrl.focus();
      return false;
  }
  var methodV = form1.method.value;
  if(methodV==""){
      alert("�ڲ��෽��������Ϊ��!");
      document.form1.method.focus();
      return false;
  }
  var inPutParamV = form1.inPutParam.value;
  if(!(inPutParamV=="")){
      if(form1.paramTypes.value==""){
	      alert("�������෽����������!");
	      document.form1.paramTypes.focus();
	      return false;
	  }
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
  }else if(verOrDisV=="display"&&form1.isList.value=="true"){
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
    <td class="title">����һ���ڲ���</td>
    <td align="right"></td>
  </tr>
</table>
<table class="form_table">
   <tr>
		<th>�˲������ر������ƣ�</th>
		<td>
			<input id="name" name="name" type="text" value="" size="20" ><font color="#FF0000">*</font>
		</td>
	</tr>
    <tr>
		<th>�ڲ���·����</th>
		<td>
			<input id="classUrl" name="classUrl" type="text" value=""><font color="#FF0000">*</font>
		</td>
	</tr>
	<tr>
		<th>�ڲ��෽������</th>
		<td>
			<input rows="4" id="method" name="method" type="text" value=""  ><font color="#FF0000">*</font>
		</td>
	</tr>
	<tr>
	  <td class="note" colspan="2">˵����
	      <li>�ڡ��ڲ��෽���������͡����и�ֵҪ�Զ���(,)����</li>
	      <li>����������ֵҪ��ʵ���෽�������ͼ���������һ��</li>
      </th>
	</tr>
	<tr>
		<th>�ڲ��෽���������ֵ</th>
		<td>
			<input id="text7" name="inPutParam" type="text" value=""  size="50" >
			<!-- 
			<input type="button" value="ѡ���������" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=7','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
			 -->
		</td>
	</tr>
	<tr>
		<th>�ڲ��෽����������</th>
		<td>
			<input id="paramTypes" name="paramTypes" type="text" value=""  size="50" >
			<!-- 
			<input type="button" value="ѡ������" onclick="window.open('<%=app %>/webtool/common/getParamTypes.jsp?wapNodeId=<%=wapNodeId %>&k=7','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
			 -->
		</td>
	</tr>
	<tr>
	  <td class="note" colspan="2">˵�����ڡ�����ֵ�Ƿ�ΪList���������緵��ֵ���Ͳ���List�������Ϊ�˸���������
      </td>
	</tr>
	<tr>
		<th>����ֵ�Ƿ�ΪList����</th>
		<td>
			<select id="isList" name="isList" onchange="javascript:changeVerOrDis(form1.isList.value,form1.verOrDis.value)">
			  <option value="true">��</option>
			  <option value="false">��</option>
			</select>
		</td>
	</tr>
		<tr>
		<th>�����ʾ���ж�</th>
		<td>
			<select id="verOrDis" name="verOrDis" onchange="javascript:changeVerOrDis(form1.isList.value,form1.verOrDis.value)">
			  <option value="display">��ʾ</option>
			  <option value="verify">�ж�</option>
			</select>
		</td>
	</tr>
</table>
<span id="span_verOrDis">
        <table class="form_table">
		<tr>
		<th>�Ƿ��ҳ</th>
		<td><select id="isPageination" name="isPageination" onchange="javascript:isPageinationPageSize(form1.isPageination.value)">
			<option value="false">��</option>
			<option value="true">��</option>
		</select></td>
		</tr>
		<tr>
	       <th>��ʾ���ݣ�</th>
	       <td><textarea rows="4" id="text8" name="text" type="text"></textarea>
	       <!-- 
		      <input type="button" value="ѡ���������" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=8','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">    
		    -->
		  </td>
	    </tr>
		</table>
		<span id="span_verValue">
        </span>
</span>


<table width="100%">
    <tr><td align="right"><input type="submit" class="inputsubmit" value="�ύ"></td></tr>
</table>  	  
</form>
</body>
</html>
