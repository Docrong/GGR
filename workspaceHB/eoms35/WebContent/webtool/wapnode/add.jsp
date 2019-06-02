<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%String app = request.getContextPath(); %>

<html>
<head><title>管理员WAP配置工具</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
<script language="javascript">
function checkInput(){
     var nameValue = document.getElementById('name').value;
     if(nameValue==""){       
       alert("'系统名称'不能为空");
       document.form1.name.focus();
       return false;
     }
     var descriptionValue = document.getElementById('description').value;
     if(descriptionValue.length==0){       
       alert("'系统描述'不能为空");
       document.form1.description.focus();
       return false;
     }
     var dbNameValue = document.getElementById('dbName').value;
     if(dbNameValue.length==0){       
       alert("'使用数据库'不能为空");
       document.form1.dbName.focus();
       return false;
     }
     var cardNameValue = document.getElementById('cardName').value;
     if(cardNameValue.length==0){       
       alert("'首页名称'不能为空");
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
	   <td class="title">增加一个系统</td>
	   <td align="right"></td>
	 </tr>
	</table>
	<table class="form_table">
	 <tr>
	   <th width="30%">系统名称</th>
	   <td width="70%">
	   <input type="text" id="name" name="name" value=""/><font color="#FF0000">*</font></td>
	 </tr>
	 <tr>
	   <th>系统描述</th>
	   <td>
	   <input type="text" id="description" name="description" value=""/><font color="#FF0000">*</font></td>
	 </tr>
	 <tr>
	   <th>使用数据库</th>
	   <td>
	     <input type="text" id="dbName" name="dbName" value=""/><font color="#FF0000">*</font>
	     <!-- 
	     <input type="button" name="" class="inputbutton" value="选择" onclick="window.open('<%=app %>/webtool/common/getDBConnectionList.jsp','','height=400,width=310,status=no,toolbar=no,menubar=no,location=no')"/></td>
	      -->
	 </tr>
	 <tr>
	   <th>数据库Id</th>
	   <td>
	   <input type="text" id="cardName" name="dbId" value=""/><font color="#FF0000">*</font></td>
	 </tr>	 
	</table>
	
	<table width="100%">
	 <tr>
	   <td align="right"><input type="submit" class="inputsubmit" value="提交"/></td>
	 </tr>
	</table>
  </form>
</body>
</html>