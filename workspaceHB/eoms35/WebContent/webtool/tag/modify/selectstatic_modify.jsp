<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@ page import="java.util.List,
                 com.boco.eoms.mobilewap.base.tag.imp.WapSelectStaticTag,
                 com.boco.eoms.mobilewap.base.tag.imp.WapSelectOption" %>
<%@ page autoFlush="true" %>
<%
WapSelectStaticTag tag = (WapSelectStaticTag)request.getAttribute("selectStatic");
String wapNodeId=(String)request.getAttribute("wapNodeId");
String wapCardId = (String)request.getAttribute("wapCardId");
String tagKey = (String)request.getAttribute("tagKey");
String app = request.getContextPath(); 

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
function addPara(a,value) {
    if(a>k) {
        k=a;
    }
    var idVal = "str" + k;
    Para.innerHTML = Para.innerHTML  + 
    			'<span id ="'+idVal+'">'+
    			'<table class="form_table">'+
    			'<tr>'+
    			'<td>ֵ<br/><input name="value"  type="text" value="" size="15" maxlength="20" class="smallInput"><font color="#FF0000">*</font></td>'+
    			'<td>�ı�<br/><input name="text" type="text" value="" size="15" maxlength="20" class="smallInput"><font color="#FF0000">*</font></td>'+
    			'<td>�ύ��ַ<br/><input type="text" value="" size="10" maxlength="50" id="onpick'+k+'" name="onpick" class="smallInput">'+
    			'</div></td>'+
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
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
</head>
<body>
<form name="form1" action="<%=app %>/webtool/tag" onSubmit="return checkInput()">
  <input name="act" type="hidden" value="modify">
  <input name="tagType" type="hidden" value="selectstatic"	>
  
  <input name="classStr" type="hidden" value="com.boco.eoms.mobilewap.base.tag.imp.WapSelectStaticTag">
  <input name="tagKey" type="hidden" value="<%=tagKey %>">
  <input name="wapCardId" type="hidden" value="<%=wapCardId %>">  
  <input name="wapNodeId" type="hidden" value="<%=wapNodeId %>">
  <input name="orderId" type="hidden" value="<%=tag.getOrderId() %>">

<table width="100%">
	 <tr>
	   <td class="title">�޸ľ�̬��������Ϣ</td>
	   <td align="right"></td>
	 </tr>
</table>
<table class="form_table">
	<tr>
		<th>���������ƣ�</th>
		<td>
			<input id="name" name="name" type="text" value="<%=tag.getName() %>"  size="20" ><font color="#FF0000">*</font>
		</td>
	</tr>
	<tr >
		<th>�Ƿ�Ϊ��ѡ��</th>
        <td>
			<select name="multiple">
              <option value="false" <%if(tag.getMultiple().equals("false"))out.println("selected"); %>>��ѡ</option>
              <option value="true" <%if(tag.getMultiple().equals("true"))out.println("selected"); %>>��ѡ</option>
            </select>
        </td>
	</tr>
	<tr>
		<th>����option������</th>
		<td>
			<input type="button" name="btadd" value="����" onclick="javascript:addPara(form1.i_flag.value)">
			
			<span id ="Para">
			<%if(tag.getOptions()!=null){
			     String value1="";
			     String text1="";
			     String address1="";
			     for(int j=0; j<tag.getOptions().size(); j++){
			     WapSelectOption pfVO = (WapSelectOption)tag.getOptions().get(j);
			     value1=(pfVO.getVaule()==null)?"":pfVO.getVaule();
			     text1=(pfVO.getText()==null)?"":pfVO.getText();
			     address1=(pfVO.getOnpick()==null)?"":pfVO.getOnpick();
			     if(address1.equals("null"))
			         address1="";
			     String idVal = "str" + j;
			 %>
			    <span id =<%=idVal%>>
			        <table class="form_table">
			            <tr>
			                <td>ֵ<br/>
						      <input name="value" type="text" size="15" maxlength="20" value="<%=value1%>"><font color="#FF0000">*</font>
					        </td>
			                <td>�ı�<br/>
			                    <input name="text" type="text"  size="15" value="<%=text1%>"><font color="#FF0000">*</font>
					        </td>
    			            <td>�ύ��ַ<br/>
    			                <input type="onpick" value="<%=address1%>" size="10" maxlength="50" id="onpick<%=j %>" name="onpick" class="smallInput">
    			                <!-- 
    			                <input type="button" value="ѡ���ύҳ��" onclick="window.open('<%=app %>/webtool/common/getCardList2Select.jsp?wapNodeId=<%=wapNodeId %>&k=<%=j %>','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')"></div>
    			                 -->
    			            </td>
    			            <td class="nobg">
			                     <input name="del" type="button" value="ɾ��" onClick="delOpt(<%=idVal%>)" class="submit">
			                </td>
			            </tr>
			        </table>
			    </span>
	         <%}}%>			
			</span>
			<span id ="flags">
             <input type="hidden" name="i_flag" value="<%=n%>">
            </span>
			
		</td>
	</tr>
</table>
			
    <table width="100%">
  <tr><td align="right"><input type="submit" class="inputsubmit" value="�ύ"></td></tr>
  </table>
</form>
</body>
</html>
