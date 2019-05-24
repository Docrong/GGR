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
        <td>�޸�ϵͳ��Ϣ</td>
        <td align="right"><a href="<%=app %>/webtool/card/add.jsp?wapNodeId=<%=wapNodeVO.getNodeId()%>">����ҳ��</a></td></tr>
    </table>    
	<table class="form_table">
	 <tr>
	   <th>ϵͳ����</th>
	   <td><input name='name' type='text' value='<%=wapNodeVO.getName()%>'/><font color="#FF0000">*</font></td>
	 </tr>
	 <tr>
	   <th>ϵͳ����</th>
	   <td><input name='description' type='text' value='<%=wapNodeVO.getDescription()%>'/><font color="#FF0000">*</font></td>
	 </tr>
	 <tr>
	   <th>ʹ�����ݿ�</th>
	   <td><input name='dbName' type='text' value='<%=wapNodeVO.getDbName()%>'/><font color="#FF0000">*</font>
	   <!-- 
	   <input type="button" name="" class="inputbutton" value="ѡ��" onclick="window.open('<%=app %>/webtool/common/getDBConnectionList.jsp','','height=400,width=310,status=no,toolbar=no,menubar=no,location=no')"/></td>
	    -->
	 </tr>
	 <tr>
	   <th>���ݿ�ID</th>
	   <td>
	   <input  id="dbId" name="dbId" type="text" value="<%=wapNodeVO.getDbId()%>"><font color="#FF0000">*</font>
	   <!-- <input type="button" class="inputbutton" value="ѡ����ҳ" onclick="window.open('<%=app %>/webtool/common/getCardList.jsp?wapNodeId=<%=wapNodeVO.getNodeId() %>','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	     -->
	   </td>
	 </tr>
	</table>
	<table width="100%">
	<tr>
	  <td align="right"><input type='submit' class="inputsubmit" value='�ύ'/></td>
	</tr>
	</table>
	<br/>
	<table class="list_table">
	<tr><td colspan="4" class="nobg">ҳ���б�</td></tr>
	 <tr>
	   <th width="25%">ҳ������</td>
	   <th width="25%">ҳ��ID</td>
	   <th width="25%">ҳ������</td>	 
	   <th width="25%">����</td>  
	 </tr>
	 <%for(Iterator it = cardList.iterator(); it.hasNext();){ 
	     WapCard cardVO =(WapCard)it.next();
	 %>
	 <tr>
	   <th class="spec"><%=cardVO.getName() %></td>
	   <td><%=cardVO.getCardId() %></td>
	   <td><%=cardVO.getDescription() %>&nbsp;</td>
	   <td>
	      <a href="<%=app %>/webtool/card?act=view&flag=view&wapCardId=<%=cardVO.getCardId()%>&wapNodeId=<%=wapNodeVO.getNodeId()%>">�鿴</a>&nbsp&nbsp
	      <a href="<%=app %>/webtool/card?act=view&flag=modify&wapCardId=<%=cardVO.getCardId()%>&wapNodeId=<%=wapNodeVO.getNodeId()%>">�޸�</a>&nbsp&nbsp
	      <a href="<%=app %>/webtool/card?act=remove&wapCardId=<%=cardVO.getCardId()%>&wapNodeId=<%=wapNodeVO.getNodeId()%>">ɾ��</a>
	   </td>
	 </tr>
	 <%} %>
	</table>
</form>
</body>
</html>