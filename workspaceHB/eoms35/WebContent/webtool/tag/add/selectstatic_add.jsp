<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@ page autoFlush="true" %>
<%
String app = request.getContextPath(); 
String wapCardId = request.getParameter("wapCardId");
String wapNodeId = request.getParameter("wapNodeId");
String tagType = request.getParameter("tagType");
int n=0;
 %>
<script language="javascript">

var k = 3;

/**
 * �ر�
 */
function bt(){
    window.close();
}

/**
 * �ύcheck
 */
function checkInput(){
   //���������Ʋ���Ϊ��
   var nameValue = form1.name.value;
   if(nameValue == null || nameValue.length==0) {
      alert("���������Ʋ���Ϊ��!");
      document.form1.name.focus();
      return false;
   }       
    // �������Ʋ���Ϊ��
    if(form1.value!=null){
        if(form1.value.value==''){
            if(form1.text.value=="" || form1.text.value==null) {
                alert("'�ı�'����Ϊ��!");
                form1.text.focus();
                return false;
            }
            if(form1.value.value=="" || form1.value.value==null) {
                alert("'ֵ'����Ϊ��!");
                form1.value.focus();
                return false;
            }
        }else{
            var paraNames = form1.text;
            var values= form1.value;
            for(i=0;i<paraNames.length;i++){
                if(paraNames[i].value=="" || paraNames[i].value==null){
                    alert("'�ı�'����Ϊ��!");
                    paraNames[i].focus();
                    return false;
                }
                if(values[i].value=="" || values[i].value==null){
                    alert("'ֵ'����Ϊ��!");
                    values[i].focus();
                    return false;
                }
            }
        }
    }
}

/**
 * ���Ӳ���
 */
function addPara(a) {
    if(a>k) {
        k=a;
    }
    var idVal = "str" + k;

    Para.innerHTML = Para.innerHTML  + 
    			'<span id ="'+idVal+'">'+
    			'<table class="form_table">'+
    			'<tr><td nowrap>ֵ��<br/><input name="value"  type="text" value="" size="10" maxlength="10" class="smallInput"></td>'+
    			'<td nowrap>�ı���<br/><input name="text" type="text" value="" size="10" maxlength="10" class="smallInput"></td>'+
    			'<td nowrap>�ύ��ַ��</br><input type="text" value="" size="10" maxlength="50" id="onpick'+k+'" name="onpick" class="smallInput">'+
    			
    			'</td>'+
    			'<td class="nobg"><input name="del" type="button" value="ɾ��" onClick="delOpt('+idVal+')" class="submit"></td></tr>'+
    			'</table></span>';
    flags.innerHTML ='<input type="hidden" name="i_flag" value="'+k+'">';
    k++;
}

/**
 * ɾ��ָ������
 */
function delOpt(str) {
    if(confirm("��ȷʵҪɾ��������?")){
        str.innerHTML ='';
    }
}

/**
 * ѡ��ť
 */
function btt1(con){
    var retValue = window.showModalDialog("WapParamAllPop.jsp",
        "","help:0;resizable:0;status=0;scrollbars=0;dialogWidth=25;dialogHeight=30;center=true");

    if(retValue == null || retValue == ''){
        return;
    }

    con.value = con.value + retValue;
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
  <input name="tagType" type="hidden" value="<%=tagType %>"	>
  
  <input name="classStr" type="hidden" value="com.boco.eoms.mobilewap.base.tag.imp.WapSelectStaticTag">
  <input name="wapCardId" type="hidden" value="<%=wapCardId %>">
  <input name="wapNodeId" type="hidden" value="<%=wapNodeId %>">
<table width="100%">
  <tr>
    <td class="title">����һ����̬������</td>
    <td align="right"></td>
  </tr>
</table>
<table class="form_table">
	<tr>
		<th>���������ƣ�</th>
		<td>
			<input id="name" name="name" type="text" value=""  size="20" ><font color="#FF0000">*</font>
		</td>
	</tr>
	<tr >
		<th>�Ƿ�Ϊ��ѡ��</th>
        <td>
			<select name="multiple">
              <option value="false" >��ѡ</option>
              <option value="true">��ѡ</option>
            </select>
        </td>
	</tr>
	<tr>
		<th>����option������</td>
		<td>
			<input type="button" name="btadd" value="����" onclick="javascript:addPara(form1.i_flag.value)">
			<span id ="Para"></span>
            <span id ="flags"><input type="hidden" name="i_flag" value="<%=n%>"></span>
		</td>
	</tr>
</table>

<table width="100%">
  <tr><td align="right"><input type="submit" class="inputsubmit"  value="�ύ"></td></tr>
</table>
</form>
</body>
</html>
