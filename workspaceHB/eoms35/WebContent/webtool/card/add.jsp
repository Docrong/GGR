<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%String app = request.getContextPath(); %>

<html>
<head><title>����ԱWAP���ù���</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
<script language="javascript">
function checkInput(){
     var nameValue = document.getElementById("name").value;
     if(nameValue==""){       
       alert("'ϵͳ����'����Ϊ��");
       document.form1.name.focus();
       return false;
     }
     var descriptionValue = document.getElementById("description").value;
     if(descriptionValue.length==0){       
       alert("'ϵͳ����'����Ϊ��");
       document.form1.description.focus();
       return false;
     }
}
</script>
</head>
<body>
  <form name="form1" action="<%=app %>/webtool/card" onSubmit="return checkInput()">
    <input name="act" type="hidden" value="save">
    <input name="wapNodeId" type="hidden" value="<%=request.getParameter("wapNodeId") %>">
	<table width="100%">
	 <tr>
	   <td class="title">����һ��ҳ��</td>
	   <td align="right"></td>
	 </tr>
	</table>
	<table class="form_table">
	 <tr>
	   <th width="30%" >ҳ������</th>
	   <td width="70%">
	     <input type="text" id="name" name="name" value=""/><font color="#FF0000">*</font>
	   </td>
	 </tr>
	 <tr>
	   <th>ҳ������</th>
	   <td>
	     <input type="text" id="description" name="description" value=""/><font color="#FF0000">*</font>
	   </td>
	 </tr>
	 <tr>
	   <th>ҳ��title</th>
	   <td>
	    <input type="text" id="title" name="title" value=""/>
       </td>
	 </tr>
	</table>
	
	<table width="100%">
	 <tr>
	   <td align="right"><input type="submit" class="inputsubmit" value="�ύ"/></td>
	 </tr>
	</table>
  </form>
</body>
</html>