<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@ page import="com.boco.eoms.mobilewap.base.tag.imp.WapImageTag" %>
<%@ page autoFlush="true" %>
<%
WapImageTag tag = (WapImageTag)request.getAttribute("image");

String wapNodeId=(String)request.getAttribute("wapNodeId");
String wapCardId = (String)request.getAttribute("wapCardId");
String tagKey = (String)request.getAttribute("tagKey");

String app = request.getContextPath(); 
%>
<script language="javascript">
/**
 * �ύcheck
 */
function checkInput() {
    var imgAlt = document.form1.alt.value;
    var typeV = document.form1.type.value;

    if(imgAlt.length==0) {
        alert("ͼƬ���Ʋ���Ϊ��,������!");
        document.all.alt.focus();
        return false;
    }

    if(typeV=="auto") {
      if( document.form1.src.value==""){
        alert("�����������ѡ��ͼƬ!");
        document.all.src.focus();
        return false;
      }
    }
}

/**
 * ѡ��ͼƬ����ֱ������
 */
function fileClick(){
    alert('���' + '"' + '���' + '"' + '��ť��ѡ��ͼƬ!');
    document.all.Submit.focus();
}
function addUpload(value){
Para.innerHTML ="";
if(value=="auto"){
	Para.innerHTML ='<table width="100%" border="1" cellpadding="1" cellspacing="1"> '+
                	'<tr ><td width="40%" ><div align="right">��ͼƬ<img alt="û���ϴ�ͼƬ" src="<%=request.getRealPath("/")+tag.getSrc()%>" width="<%=tag.getWidth()%>" height="<%=tag.getHeight() %>"/>��ѡ��ͼƬ��</div></td>'+
	                '<td width="" align="left" > <div align="left">'+
	                '<input name="src" type="file" align="left"  onkeydown="javascript:fileClick();">'+
	                '<font color="#FF0000">*</font></div></td></tr></table>';
}	               
else{
   Para.innerHTML = '<input name="src" type="hidden" value="" >';
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
  <input name="tagType" type="hidden" value="image"	>
  
  <input name="tagKey" type="hidden" value="<%=tagKey %>">
  <input name="wapCardId" type="hidden" value="<%=wapCardId %>">
  <input name="classStr" type="hidden" value="com.boco.eoms.mobilewap.base.tag.imp.WapImageTag">
  <input name="wapNodeId" type="hidden" value="<%=wapNodeId %>">
  <input name="orderId" type="hidden" value="<%=tag.getOrderId() %>">
  
<table width="100%">
	 <tr>
	   <td class="title">�޸�ͼƬ��Ϣ</td>
	   <td align="right"></td>
	 </tr>
</table>
<table class="form_table">
    <tr>
      <td class="note" colspan="2">
      ˵��<br>
        <li>ͼƬ�Ŀ�Ⱥ͸߶Ȳ�����ȡĬ��ֵ</li>
	    <li>ͼƬ�ĸ߶��벻Ҫ����80pix��ͼƬ�Ŀ���벻Ҫ����90pix</li>
        <li>ͼƬ����ֻ����Ϊ���֡���ĸ���»���</li>
    </th>
    <tr>
      <th>ͼƬ���ƣ�</th>
      <td>
        <input id="text0" name="alt" type="text"  size="30" maxlength="50" value="<%=tag.getAlt()%>"><font color="#FF0000">*</font>
        <!-- 
        <input type="button" value="ѡ���������" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=0','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
         -->
      </td>
    </tr>
    <tr >
      <th>ͼƬλ�ã�</th>
      <td>
	    <select name="align">
	     <option value="middle" <%if(tag.getAlign().equals("middle"))out.println("select"); %>>�м� </option>
		   <option value="top" <%if(tag.getAlign().equals("top"))out.println("select"); %>>����</option>
		   <option value="bottom" <%if(tag.getAlign().equals("bottom"))out.println("select"); %>> �ײ�</option>
	    </select>
	  </th>
    </tr>
	 <tr>
      <th>ͼƬ��ȣ�</th>
      <td>
	    <input name="width" type="text"  size="15" maxlength="3" value="<%=tag.getWidth()%>">
	    &nbsp; px	
	  </td>
    </tr>
	<tr>
      <th>ͼƬ�߶ȣ�</th>
      <td>
	     <input name="height" size="15" type="text" maxlength="3" value="<%=tag.getHeight() %>" >
		&nbsp; px	
	  </td>
    </tr>
    <tr >
      <th>ͼƬ���ͣ�</th>
      <td>
	    <select name="type" onchange="javascript:addUpload(form1.type.value)">
	       <option value="auto" <%if(tag.getType().equals("auto"))out.println("selected"); %>>���������ϴ� </option>
		   <option value="cake" <%if(tag.getType().equals("cake"))out.println("selected"); %>>�������ݱ�ͼ</option>
		   <option value="line" <%if(tag.getType().equals("line"))out.println("selected"); %>> ����������ͼ</option>
		   <option value="pole" <%if(tag.getType().equals("pole"))out.println("selected"); %>> ����������ͼ</option>
	    </select>
	  </td>
    </tr>
    <tr>
    	<th>ͼƬ·����</th>
    	<td>
    		<input name="src" size="50" type="text" maxlength="50" value="<%=tag.getSrc() %>" >
    	</td>
    </tr>
</table>
<span id ="Para">
<%if(tag.getImageType().equals("auto")){ %>
    <table class="form_table"> 
      <tr>
        <th>��ͼƬ<img alt="û���ϴ�ͼƬ" src="<%=request.getRealPath("/")+tag.getSrc()%>" width="<%=tag.getWidth()%>" height="<%=tag.getHeight() %>"/>
            <br/>��ѡ��ͼƬ��
        </th>
	    <td>
	  <!-- <input name="src" type="file" align="left"  onkeydown="javascript:fileClick();"> 
	  <font color="#FF0000">*</font></td></tr>-->
	</table>
<%} %>	
</span>  
<table width="100%">  
    <tr >
	  <td  align="right"><input type="submit" class="inputsubmit" name="Submit" value="ȷ��"></td>
	</tr>
</table>
</form>
</body>
</html>
