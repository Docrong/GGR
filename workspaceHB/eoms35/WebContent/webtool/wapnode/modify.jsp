<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@page import="java.util.List,
                java.util.Iterator,
                com.boco.eoms.mobilewap.base.card.imp.WapCard,
                com.boco.eoms.mobilewap.base.node.WapNode" %>
<%
WapNode wapNodeVO = (WapNode)request.getAttribute("wapNodeVO");

List cardList = (List)request.getAttribute("cardList");
String app = request.getContextPath();
String wapCardId = request.getParameter("wapCardId");
//System.out.println("refreshId = "+request.getParameter("refreshId"));
%>
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

<script type="text/javascript" src="<%=app%>/webtool/scripts/ajax/prototype.js">
    Try.these(
    function(){parent.webFXTreeHandler.all["${refreshId}"].parentNode.reload()}

    );
</script>
</head>
<body onload="javascript:top.leftFrame.reloadtree()">
<form name="form1" action="<%=app %>/webtool/wapnode" onSubmit="return checkInput()">
    <input name="act" type="hidden" value="modify">
    <input name="wapNodeId" type="hidden" value="<%=wapNodeVO.getNodeId()%>">
    <table width="100%">
      <tr>
        <td>修改系统信息</td>
        <td align="right"><a href="<%=app %>/webtool/card/add.jsp?wapNodeId=<%=wapNodeVO.getNodeId()%>">增加页面</a></td></tr>
    </table>    
	<table class="form_table">
	 <tr>
	   <th>系统名称</th>
	   <td><input name='name' type='text' value='<%=wapNodeVO.getName()%>'/><font color="#FF0000">*</font></td>
	 </tr>
	 <tr>
	   <th>系统描述</th>
	   <td><input name='description' type='text' value='<%=wapNodeVO.getDescription()%>'/><font color="#FF0000">*</font></td>
	 </tr>
	 <tr>
	   <th>使用数据库</th>
	   <td><input name='dbName' type='text' value='<%=wapNodeVO.getDbName()%>'/><font color="#FF0000">*</font>
	   <!-- 
	   <input type="button" name="" class="inputbutton" value="选择" onclick="window.open('<%=app %>/webtool/common/getDBConnectionList.jsp','','height=400,width=310,status=no,toolbar=no,menubar=no,location=no')"/></td>
	    -->
	 </tr>
	 <tr>
	   <th>数据库ID</th>
	   <td>
	   <input  id="dbId" name="dbId" type="text" value="<%=wapNodeVO.getDbId()%>"><font color="#FF0000">*</font>
	   <!-- <input type="button" class="inputbutton" value="选择首页" onclick="window.open('<%=app %>/webtool/common/getCardList.jsp?wapNodeId=<%=wapNodeVO.getNodeId() %>','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	     -->
	   </td>
	 </tr>
	</table>
	<table width="100%">
	<tr>
	  <td align="right"><input type='submit' class="inputsubmit" value='提交'/></td>
	</tr>
	</table>
	<br/>
	<table class="list_table">
	<tr><td colspan="4" class="nobg">页面列表</td></tr>
	 <tr>
	   <th width="25%">页面名称</td>
	   <th width="25%">页面ID</td>
	   <th width="25%">页面描述</td>	 
	   <th width="25%">操作</td>  
	 </tr>
	 <%for(Iterator it = cardList.iterator(); it.hasNext();){ 
	     WapCard cardVO =(WapCard)it.next();
	 %>
	 <tr>
	   <th class="spec"><%=cardVO.getName() %></td>
	   <td><%=cardVO.getCardId() %></td>
	   <td><%=cardVO.getDescription() %>&nbsp;</td>
	   <td>
	      <a href="<%=app %>/webtool/card?act=view&flag=view&wapCardId=<%=cardVO.getCardId()%>&wapNodeId=<%=wapNodeVO.getNodeId()%>">查看</a>&nbsp&nbsp
	      <a href="<%=app %>/webtool/card?act=view&flag=modify&wapCardId=<%=cardVO.getCardId()%>&wapNodeId=<%=wapNodeVO.getNodeId()%>">修改</a>&nbsp&nbsp
	      <a href="<%=app %>/webtool/card?act=remove&wapCardId=<%=cardVO.getCardId()%>&wapNodeId=<%=wapNodeVO.getNodeId()%>">删除</a>
	   </td>
	 </tr>
	 <%} %>
	</table>
</form>
</body>
</html>