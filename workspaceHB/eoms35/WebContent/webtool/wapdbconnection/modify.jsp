<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@page import="com.boco.eoms.mobilewap.webtool.vo.WapDBConnectionVO" %>
<%
WapDBConnectionVO vo = (WapDBConnectionVO)request.getAttribute("wapDBConnectionVO");
String app = request.getContextPath();
%>

<html>
<head><title>管理员WAP配置工具</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
<script language="javascript">
function checkInput(){
     var dbNameValue = document.getElementById("dbName").value;
     if(dbNameValue==""){       
       alert("连接池名称不能为空");
       document.form1.dbName.focus();
       return false;
     }
     var descriptionValue = document.getElementById("description").value;
     if(descriptionValue.length==0){       
       alert("连接池描述不能为空");
       document.form1.description.focus();
       return false;
     }
     var DbTypeValue = document.getElementById("DbType").value;
     if(DbTypeValue.length==0){       
       alert("数据库类型不能为空");
       document.form1.DbType.focus();
       return false;
     }
     var DB_CharsetValue = document.getElementById("DB_Charset").value;
     if(DB_CharsetValue.length==0){       
       alert("字符集设置不能为空");
       document.form1.DB_Charset.focus();
       return false;
     }
     var dbConnectionUrlValue = document.getElementById("dbConnectionUrl").value;
     if(dbConnectionUrlValue.length==0){       
       alert("连接池URL不能为空");
       document.form1.dbConnectionUrl.focus();
       return false;
     }
}
</script>
</head>
<body>
  <form name="form1" action="<%=app %>/webtool/wapDBConnection" onSubmit="return checkInput()">
    <input name="act" type="hidden" value="modify">
    <input name="dbId" type="hidden" value="<%=vo.getDbId() %>">
	<table width="100%">
      <tr>
        <td>修改系统信息</td>
        <td align="right"></td></tr>
    </table>    
	<table class="form_table">
	 <tr>
	   <th>连接池名称</th>
	   <td>
	     <input type="text" name="dbName" value="<%=vo.getDbName() %>"/><font color="#FF0000">*</font>
	   </td>
	 </tr>
	 <tr>
	   <th>连接池描述</th>
	   <td>
	     <input type="text" name="description" value="<%=vo.getDescription() %>"/><font color="#FF0000">*</font>
	   </td>
	 </tr>
	 <tr>
	   <th>数据库类型</th>
	   <td>
	    <input type="text" name="DbType" value="<%=vo.getDbType()%>"/><font color="#FF0000">*</font>
       </td>
	 </tr>
	 <tr>
	   <th>字符集设置</th>
	   <td>
           <input type="text" name="DB_Charset" value="<%=vo.getDB_Charset() %>"/><font color="#FF0000">*</font>
	   </td>
	 </tr>
	 <tr>
	   <th>连接池URL</th>
	   <td>
          <input type="text" name="dbConnectionUrl" value="<%=vo.getDbConnectionUrl() %>"/><font color="#FF0000">*</font>
	   </td>
	 </tr>
	 <tr>
	   <th>用户名</th>
	   <td>
           <input type="text" name="userName" value="<%=vo.getUserName() %>"/>
	   </td>
	 </tr>
	 <tr>
	   <th>密码</th>
	   <td>
           <input type="password" name="password" value="<%=vo.getPassword() %>"/>
	   </td>
	 </tr>
	</table>
	<table width="100%">
	<tr>
	  <td align="right"><input type='submit' class="inputsubmit" value='提交'/></td>
	</tr>
	</table>
  </form>
</body>
</html>