<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@ page autoFlush="true" %>
<%
String app = request.getContextPath(); 
String wapCardId = request.getParameter("wapCardId");
String wapNodeId = request.getParameter("wapNodeId");
String tagType = request.getParameter("tagType");
 %>
<html>
<head>
<title>����ԱWAP���ù���</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
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
	Para.innerHTML ='<table class="form_table"> '+
                	'<tr><th>��ѡ��ͼƬ��</th>'+
	                '<td><input name="src" type="file" align="left"  onkeydown="javascript:fileClick();">'+
	                '<font color="#FF0000">*</font></td></tr></table>';
}	               
else{
   Para.innerHTML = '<input name="src" type="hidden" value="" >';
}              
}
</script>


</head>
<body>
<form action="<%=app %>/webtool/tag" name="form1" onSubmit="return checkInput();">
  <input name="act" type="hidden" value="save">
  <input name="tagType" type="hidden" value="<%=tagType %>"	>
  
  <input name="wapCardId" type="hidden" value="<%=wapCardId %>" >
  <input name="classStr" type="hidden" value="com.boco.eoms.mobilewap.base.tag.imp.WapImageTag">
  <input name="wapNodeId" type="hidden" value="<%=wapNodeId %>" >
<table width="100%">
	 <tr>
	   <td class="title">����һ��ͼƬ</td>
	   <td align="right"></td>
	 </tr>
</table>
<table class="form_table">
    <tr>
      <td colspan="2" class="note">
      ˵���� <br>
        <li>ͼƬ�Ŀ�Ⱥ͸߶Ȳ�����ȡĬ��ֵ</li>
	    <li>ͼƬ�ĸ߶��벻Ҫ����80px��ͼƬ�Ŀ���벻Ҫ����90px</li>
        <li>ͼƬ����ֻ����Ϊ���֡���ĸ���»���</li>
    </tr>
    <tr>
      <th>ͼƬ���ƣ�</td>
      <td>
          <input id="text0" name="alt" type="text"  size="30" maxlength="50" value=""><font color="#FF0000">*</font>
          <!-- 
        <input type="button" value="ѡ���������" onclick="window.open('<%=app %>/webtool/common/getNodeVariableList.jsp?wapNodeId=<%=wapNodeId %>&k=0','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
         -->
        </div></td>
    </tr>
    <tr >
      <th>ͼƬλ�ã�</td>
      <td>
	    <select name="align">
	     <option value="middle">�м� </option>
		   <option value="top">����</option>
		   <option value="bottom"> �ײ�</option>
	    </select>
	  </td>
    </tr>
	 <tr >
      <th>ͼƬ��ȣ�</td>
      <td>
	    <input name="width" type="text"  size="15" maxlength="3" value="">
	    &nbsp; px	
	  </td>
    </tr>
	<tr >
      <th>ͼƬ�߶ȣ�</td>
      <td>
	     <input name="height" size="15" type="text" maxlength="3" value="" >
		&nbsp; px	
	  </td>
    </tr>
    <tr>
      <th>ͼƬ���ͣ�</td>
      <td>
	    <select name="type" onchange="javascript:addUpload(form1.type.value)">
	       <option value="auto">���������ϴ� </option>
		   <option value="cake">�������ݱ�ͼ</option>
		   <option value="line">����������ͼ</option>
		   <option value="pole">����������ͼ</option>
	    </select>
	  </td>
    </tr>
    </table>
    <span id="Para">
    <table class="form_table" >
    <tr>   
      <th>��ѡ��ͼƬ��</td>
      <td>
	    <input name="src" type="file" align="left"  onkeydown="javascript:fileClick();">
	  <font color="#FF0000">*</font>
	  </td>
    </tr>
</table>
</span>

<table width="100%">
 <tr>
   <td align="right"><input type="submit" class="inputsubmit" value="�ύ"/></td>
 </tr>
</table>
</form>
</body>
</html>
