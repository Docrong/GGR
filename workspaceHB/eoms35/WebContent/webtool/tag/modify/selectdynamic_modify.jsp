<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@ page import="com.boco.eoms.mobilewap.tag.imp.SelectDynamicTag,
                 com.boco.eoms.mobilewap.tag.vo.SelectOptionVO" %>
<%@ page autoFlush="true" %>
<%
SelectDynamicTag tag = (SelectDynamicTag)request.getAttribute("selectDynamic");
SelectOptionVO soVO = tag.getOptionList();
String wapNodeId=tag.getCardId().substring(tag.getCardId().indexOf("_")+1,tag.getCardId().length());
String app = request.getContextPath(); 
%>
 <script language="javascript">
/**
 * �ύcheck
 */
function checkInput(){
   
   var nameValue = form1.name.value;
   var valueV = form1.value.value;
   var textV = form1.text.value;
// ���������Ʋ���Ϊ��
   if(nameValue == null || nameValue.length==0) {
      alert("���������Ʋ���Ϊ��!");
      document.form1.name.focus();
      return false;
   }
   // Optionֵ����Ϊ��
   if(valueV == null || valueV.length==0) {
      alert("Optionֵ����Ϊ��!");
      document.form1.value.focus();
      return false;
   }
   // ��ʾ���ݲ���Ϊ��
   if(textV == null || textV.length==0) {
      alert("��ʾ���ݲ���Ϊ��!");
      document.form1.text.focus();
      return false;
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
  <input name="tagType" type="hidden" value="selectdynamic"	>
  <input name="tagKey" type="hidden" value="<%=request.getParameter("tagKey") %>">
  <input name="cardId" type="hidden" value="<%=request.getParameter("cardId") %>">
  <input name="class_str" type="hidden" value="com.boco.eoms.mobilewap.tag.imp.SelectDynamicTag">

<table width="100%">
	 <tr>
	   <td class="title">�޸Ķ�̬��������Ϣ</td>
	   <td align="right"></td>
	 </tr>
</table>
<table class="form_table">
    <tr>
		<td class="note" colspan="2">
		ע��
		<li>����תҳ��ID�����Ҫ���ݲ�������ID������ʺţ�?�����ֹ����</li>
		<li>����ӡ�optionֵ���롰��ʾ���ݡ�ʱѡ����������Ʊ��뺬��List���ұ���ͬΪһList������ϵͳ�����</li>
		</td>
	</tr>
    <tr>
		<th>����������</th>
		<td>
			<input id="name" name="name" type="text" value="<%=tag.getName() %>"  size="20" ><font color="#FF0000">*</font>
		</td>
	</tr>
    <tr>
		<th>�Ƿ��ѡ</th>
		<td>
			<select name="multiple">
			  <option value="false" <%if(tag.getMultiple()==false)out.println("selected"); %>>��ѡ</option>
			  <option value="true"<%if(tag.getMultiple()==true)out.println("selected"); %>>��ѡ</option>
			</select>
		</td>
	</tr>
	<tr>
		<th>optionֵ</th>
		<td>
           <input id="value" name="value" type="text" value="<%=soVO.getVaule() %>"  size="15" ><font color="#FF0000">*</font>
		   <input type="button" value="ѡ��ѭ������" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList2Value.jsp?wapNodeId=<%=wapNodeId %>','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
		</td>
	</tr>
	<tr>
		<th>��ʾ����</th>
		<td>
		   <input id="text" name="text" type="text" value="<%=soVO.getText() %>"  size="15" ><font color="#FF0000">*</font>
		   <input type="button" value="ѡ��ѭ������" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList2Text.jsp?wapNodeId=<%=wapNodeId %>','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
		</td>
	</tr>
	<tr>
		<th>��תҳ��ID</th>
		<td>
			<input id="hrefCardId" name="hrefCardId" type="text" value="<%=soVO.getOnpick() %>"  size="15" >
			<input type="button" value="ѡ���ύҳ��" onclick="window.open('<%=app %>/webtool/common/getCardList.jsp?wapNodeId=<%=wapNodeId %>','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
		</td>		
	</tr>
</table>
<table width="100%">
  <tr><td align="right"><input type="submit" class="inputsubmit" value="�ύ"></td></tr>
</table>
</form>
</body>
</html>
