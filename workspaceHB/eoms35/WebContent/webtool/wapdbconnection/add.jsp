<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%String app = request.getContextPath(); %>
<script language="javascript">
function checkInput(){
     var dbNameValue = document.getElementById("dbName").value;
     if(dbNameValue==""){       
       alert("'���ӳ�����'����Ϊ��");
       document.form1.dbName.focus();
       return false;
     }
     var descriptionValue = document.getElementById("description").value;
     if(descriptionValue.length==0){       
       alert("'���ӳ�����'����Ϊ��");
       document.form1.description.focus();
       return false;
     }
     var DbTypeValue = document.getElementById("DbType").value;
     if(DbTypeValue.length==0){       
       alert("'���ݿ�����'����Ϊ��");
       document.form1.DbType.focus();
       return false;
     }
     var DB_CharsetValue = document.getElementById("DB_Charset").value;
     if(DB_CharsetValue.length==0){       
       alert("'�ַ�������'����Ϊ��");
       document.form1.DB_Charset.focus();
       return false;
     }
     var dbConnectionUrlValue = document.getElementById("dbConnectionUrl").value;
     if(dbConnectionUrlValue.length==0){       
       alert("'���ӳ�URL'����Ϊ��");
       document.form1.dbConnectionUrl.focus();
       return false;
     }
}
</script>
<html>
<head><title>����ԱWAP���ù���</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
</head>
<body>
  <form name="form1" action="<%=app %>/webtool/wapDBConnection" onSubmit="return checkInput()">
    <input name="act" type="hidden" value="save">
	    <table width="100%">
	 <tr>
	   <td class="title">����һ�����ӳ�</td>
	   <td align="right"></td>
	 </tr>
	</table>
	<table class="form_table">
	 <tr>
	   <th>���ӳ�����</td>
	   <td>
	     <input type="text" id="dbName" name="dbName" value=""/><font color="#FF0000">*</font>
	   </td>
	 </tr>
	 <tr>
	   <th>���ӳ�����</td>
	   <td>
	     <input type="text" id="description" name="description" value=""/><font color="#FF0000">*</font>
	   </td>
	 </tr>
	 <tr>
	   <th>���ݿ�����</td>
	   <td>
	    <input type="text" id="DbType" name="DbType" value=""/><font color="#FF0000">*</font>
       </td>
	 </tr>
	 <tr>
	   <th>�ַ�������</td>
	   <td>
          <input type="text" id="DB_Charset" name="DB_Charset" value=""/><font color="#FF0000">*</font>
	   </td>
	 </tr>
	 <tr>
	   <th>���ӳ�URL</td>
	   <td>
          <input type="text" id="dbConnectionUrl" name="dbConnectionUrl" value=""/><font color="#FF0000">*</font>
	   </td>
	 </tr>
	 <tr>
	   <th>�û���</td>
	   <td>
           <input type="text" name="userName" value=""/>
	   </td>
	 </tr>
	 <tr>
	   <th>����</td>
	   <td>
           <input type="password" name="password" value=""/>
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