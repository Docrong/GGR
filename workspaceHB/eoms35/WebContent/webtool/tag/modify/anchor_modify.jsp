<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@ page import="java.util.List,java.util.ArrayList" %>
<%@ page import="com.boco.eoms.mobilewap.base.tag.imp.WapAnchorTag" %>
<%@ page import="com.boco.eoms.mobilewap.base.tag.imp.WapPostField" %>
<%@ page autoFlush="true"%>
<%
WapAnchorTag tag = (WapAnchorTag)request.getAttribute("anchor");
String wapNodeId=(String)request.getAttribute("wapNodeId");
String wapCardId = (String)request.getAttribute("wapCardId");
String tagKey = (String)request.getAttribute("tagKey");

String app = request.getContextPath();
List arrayList = new ArrayList();
arrayList.add("�༭������Ϣ");
int n = 0;

%>
<script language="javascript">

var k = 3;
/**
 * �ύcheck
 */
function checkInput(){
   // �ύ��ַ����Ϊ��
   var addressValue = form1.hrefCardId.value;
   if(addressValue == null || addressValue.length==0) {
      alert("�ύ��ַ����Ϊ��!");
      document.form1.hrefCardId.focus();
      return false;
   }
   var textValue = form1.text.value;
   if(textValue == null || textValue.length==0) {
      alert("'��������'����Ϊ��!");
      document.form1.text.focus();
      return false;
   }
   // �ύ��ַ����Ϊ����
//   if(!addressValue.match(/^[0-9]*$/gi)) {
//      alert("�ύ��ַ����Ϊ����!");
//      document.form1.address.focus();
//     return false;
//   }

    // �������Ʋ���Ϊ��
    if(form1.postValue!=null){
        if(form1.postValue.value){
            if(form1.postName.value=="" || form1.postName.value==null) {
                alert("�������Ʋ���Ϊ��!");
                form1.postName.focus();
                return false;
            }
        }else{
            var postNames = form1.postName;
            for(i=0;i<postNames.length;i++){
                if(postNames[i].value=="" || postNames[i].value==null){
                    alert("�������Ʋ���Ϊ��!");
                    postNames[i].focus();
                    return false;
                }
            }
        }
    }
}
/**
 * ���Ӳ���
 */
function addPara(a,value) {
    if(a>k) {
        k=a;
    }
    var idVal = "str" + k;
    Para.innerHTML = Para.innerHTML+'<span id ="'+idVal+'"><table class="form_table"><tr>'
        +'<td>��������<br/><input name="postName" type="text" size="15" maxlength="20" value="'+value+'"  class="smallInput"><font color="#FF0000">*</font></td>'
        +'<td>����ֵ<br/><input id="text'+k+'" name="postValue" type="text"  size="15" value="'+"$("+value+")"+'">'
        +'</td>'
        +'<td class="nobg">'
        +'<input name="del" type="button" value="ɾ��" onClick="delOpt('+idVal+')">'
        +'</td>'
        +'</tr></table><span>';
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
<link type="text/css" rel="stylesheet"
	href="<%=app%>/webtool/styles/style.css" />
</head>
<body>
<form name="form1" action="<%=app%>/webtool/tag" onSubmit="return checkInput()">
  <input name="act" type="hidden"	value="modify"> 
  <input name="tagType" type="hidden"	value="anchor"> 
  
  <input name="tagKey" type="hidden" value="<%=tagKey %>">
  <input name="wapCardId" type="hidden" value="<%=wapCardId %>">
  <input name="classStr" type="hidden" value="com.boco.eoms.mobilewap.base.tag.imp.WapAnchorTag">
  <input name="wapNodeId" type="hidden" value="<%=wapNodeId %>">
  <input name="orderId" type="hidden" value="<%=tag.getOrderId() %>">

<table width="100%">
  <tr>
    <td class="title">�޸��ڲ�����</td>
    <td align="right"></td>
  </tr>
</table> 
<table class="form_table">
	<tr>
		<th>�ύ��ַ(ֻ��ҳ��ID)��</th>
		<td>
		<input id="hrefCardId"	name="hrefCardId" type="text" value="<%=tag.getHrefCardId()%>" size="20"><font color="#FF0000">*</font> 
		<!-- 
		<input type="button" value="ѡ���ύҳ��"
			onclick="window.open('<%=app%>/webtool/common/getCardList.jsp?wapNodeId=<%=wapNodeId%>','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">
		 -->	
		</td>
	</tr>
	<tr>
		<th>�����볬�������֣�</th>
		<td>
		<input id="text500" name="text"	type="text" value="<%=tag.getText().trim()%>" size="20" maxlength="20"><font color="#FF0000">*</font> 
		<!-- 
		<input type="button" value="ѡ��������"
			onclick="window.open('<%=app%>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId%>&k=500','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">
		 -->
		</td>
	</tr>
	<tr>
		<th>ѡ���ύ������</th>
		<td>
		<select	name="postFileValue" disabled="false">
			<%
			for (int i = 0; i < arrayList.size(); i++) {
			%>
			<option value=<%=arrayList.get(i)%>><%=arrayList.get(i)%></option>
			<%
			}
			%>
		</select> 
		<input type="button" name="btadd" value="����"
			onclick="javascript:addPara(form1.i_flag.value,form1.postFileValue.value)">
		<span id="Para"> 
<%
 		if (tag.getPostField() != null)
 		for (int j = 0; j < tag.getPostField().size(); j++) {
 			WapPostField pfVO = (WapPostField) tag.getPostField().get(j);
 			String idVal = "str" + j;
 %> 
<span id=<%=idVal%>>
<table class="form_table">
	<tr>
		<td>��������<br/>
		  <input name="postName" type="text" size="15" maxlength="20" value="<%=pfVO.getName()%>" class="smallInput"><font color="#FF0000">*</font>
		</td>
		<td>
		����ֵ<br/>
		 
		<input id="text<%=j%>" name="postValue" type="text" size="15" value="<%=pfVO.getValue()%>" class="smallInput">
		<!--
		<input type="button" value="ѡ�����"
			onclick="window.open('<%=app%>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId%>&k=<%=j%>','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">
		 -->
		</td>
		<td class="nobg"><input name="del" type="button"
			value="ɾ��" onClick="delOpt(<%=idVal%>)" class="submit"></td>
	</tr>
</table>
</span> 
<% } %> 
</span> 
 <span id="flags"><input type="hidden" name="i_flag" value="<%=n%>"></span>
		
		
		</td>
	</tr>
</table>

<table width="100%">
	<tr>
		<td align="right"><input type="submit" class="inputsubmit" value="�ύ"></td>
	</tr>
</table>
</form>
</body>
</html>
