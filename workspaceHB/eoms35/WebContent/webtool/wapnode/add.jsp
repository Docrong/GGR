<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%String app = request.getContextPath(); %>

<html>
<head><title>����ԱWAP���ù���</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
<script language="javascript">
function checkInput(){
     var nameValue = document.getElementById('name').value;
     if(nameValue==""){       
       alert("'ϵͳ����'����Ϊ��");
       document.form1.name.focus();
       return false;
     }
     var descriptionValue = document.getElementById('description').value;
     if(descriptionValue.length==0){       
       alert("'ϵͳ����'����Ϊ��");
       document.form1.description.focus();
       return false;
     }
     var dbNameValue = document.getElementById('dbName').value;
     if(dbNameValue.length==0){       
       alert("'ʹ�����ݿ�'����Ϊ��");
       document.form1.dbName.focus();
       return false;
     }
     var cardNameValue = document.getElementById('cardName').value;
     if(cardNameValue.length==0){       
       alert("'��ҳ����'����Ϊ��");
       document.form1.cardName.focus();
       return false;
     }
     
}
</script>
</head>
<body>
  <form name="form1" action="<%=app %>/webtool/wapnode" onSubmit="return checkInput()">
  <input name="act" type="hidden" value="save">
    <table width="100%">
	 <tr>
	   <td class="title">����һ��ϵͳ</td>
	   <td align="right"></td>
	 </tr>
	</table>
	<table class="form_table">
	 <tr>
	   <th width="30%">ϵͳ����</th>
	   <td width="70%">
	   <input type="text" id="name" name="name" value=""/><font color="#FF0000">*</font></td>
	 </tr>
	 <tr>
	   <th>ϵͳ����</th>
	   <td>
	   <input type="text" id="description" name="description" value=""/><font color="#FF0000">*</font></td>
	 </tr>
	 <tr>
	   <th>ʹ�����ݿ�</th>
	   <td>
	     <input type="text" id="dbName" name="dbName" value=""/><font color="#FF0000">*</font>
	     <!-- 
	     <input type="button" name="" class="inputbutton" value="ѡ��" onclick="window.open('<%=app %>/webtool/common/getDBConnectionList.jsp','','height=400,width=310,status=no,toolbar=no,menubar=no,location=no')"/></td>
	      -->
	 </tr>
	 <tr>
	   <th>���ݿ�Id</th>
	   <td>
	   <input type="text" id="cardName" name="dbId" value=""/><font color="#FF0000">*</font></td>
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