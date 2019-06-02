<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@page import="java.util.List,
                java.util.Iterator,
                com.boco.eoms.mobilewap.base.tag.WapTagInterface,
                com.boco.eoms.mobilewap.base.card.imp.WapCard" %>
<%
WapCard vo = (WapCard)request.getAttribute("cardVO");
List list = (List)request.getAttribute("wmlTagList");
String app = request.getContextPath(); 
String wapCardId=request.getParameter("wapCardId");
String wapNodeId=request.getParameter("wapNodeId");
%>

<html>
<head><title>����ԱWAP���ù���</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
<script language="javascript">
function checkInput(){
     var nameValue = document.form2.getElementById("name").value;
     if(nameValue==""){       
       alert("'ϵͳ����'����Ϊ��");
       document.form1.name.focus();
       return false;
     }
     var descriptionValue = document.form2.getElementById("description").value;
     if(descriptionValue.length==0){       
       alert("'ϵͳ����'����Ϊ��");
       document.form1.description.focus();
       return false;
     }
}
</script>
</head>
<body onload="javascript:top.leftFrame.reloadtree()">
<form name="form1" action="<%=app %>/webtool/tag/addControl.jsp" onSubmit="return checkInput()">	
    <input name="wapCardId" type="hidden" value="<%=wapCardId %>">
    <input name="wapNodeId" type="hidden" value="<%=wapNodeId %>">
	<table width="100%">
	<tr>
	  <td  align="right">��ѡ���ǩ���ͽ������ӱ�ǩ�Ĳ���
	    <select id="tagType" name="tagType">
	      ��<option value="anchor">�ύ���ӱ�ǩAnchor</option>
	       <option value="href">ҳ�����ӱ�ǩHref</option>
	       <option value="image">ͼƬ��ǩ��ǩImage</option>
	       <option value="input">������ǩInput</option>
	       <option value="onevent">��ʱ��ת��ǩOnevent</option>
	       <option value="selectstatic">�����б��ǩSelectStatic</option>
	       <option value="sqlClass">���ݿ��ǩSqlclass</option>
	       <option value="sysClass">�ڲ����ǩSysclass</option>
	       <option value="text">�ı���ǩText</option>
	       <option value="br">���б�ǩBr</option>
	    </select>
        <input type="submit"  class="inputsubmit" value="����ҳ���ǩ"></td>
	</tr>
	</table>
  </form>
  <form name="form2" action="<%=app %>/webtool/card">
    <input name="act" type="hidden" value="modify">
    <input name="wapCardId" type="hidden" value="<%=wapCardId %>">
    <input name="wapNodeId" type="hidden" value="<%=wapNodeId %>">
	<table class="form_table">
	<tr>
	   <th width="30%">ҳ��ID</th>
	   <td width="70%">
	     <%=vo.getCardId() %>
	   </td>
	 </tr>
	 <tr>
	   <th>ҳ������</th>
	   <td width="70%">
	     <input name="name" type="text" value="<%=vo.getName() %>"><font color="#FF0000">*</font>
	   </td>
	 </tr>
	 <tr>
	   <th>ҳ������</th>
	   <td>
	     <input name="description" type="text" value="<%=vo.getDescription() %>"><font color="#FF0000">*</font>
	   </td>
	 </tr>
	 <tr>
	   <th>ҳ��title</th>
	   <td>
	    <input name="title" type="text" value="<%=vo.getTitle() %>">
       </td>
	 </tr>
	</table>
	<br/>
	<table class="list_table">
	<tr><td colspan="3" class="nobg">��ǩ�б�</td></tr>
	<tr>
	   <th width="25%">��ǩ����</th>	   	
	   <th width="25%">����ҳ����λ������</th>
	   <th width="50%">����</th>
    </tr>
	 <%for(Iterator it = list.iterator(); it.hasNext();){ 
	     WapTagInterface tagVo =(WapTagInterface)it.next();
	 %>
	 <tr>
	   <th class="spec"><%=tagVo.getType() %></th>
	   <td>
	   ��<select name="orderIds">
	       <%for(int i=1; i<list.size()+1; i++){%>
	       <option value=<%=i %> <%if((i+"").equals(tagVo.getOrderId()))out.print("selected");%>><%=i %></option>
	       <%} %>	     
	    </select>
	    <input name="tagKeys" type="hidden" value="<%=tagVo.getTagKey() %>">
	   </td> 
	   <td>
	     <%if(tagVo.getClassStr()==null||tagVo.getClassStr().equals("")) {%>
	      �鿴&nbsp&nbsp
	      �޸�&nbsp&nbsp	     
	     <%}else{ %>
	      <a href="<%=app %>/webtool/tag?act=view&flag=view&wapNodeId=<%=wapNodeId %>&wapCardId=<%=wapCardId %>&tagKey=<%=tagVo.getTagKey()%>&tagType=<%=tagVo.getType()%>&class_str=<%=tagVo.getClassStr()%>">�鿴</a>&nbsp&nbsp
	      <%if(!tagVo.getType().equals("Br")){ %>
	      <a href="<%=app %>/webtool/tag?act=view&flag=modify&wapNodeId=<%=wapNodeId %>&wapCardId=<%=wapCardId %>&tagKey=<%=tagVo.getTagKey()%>&tagType=<%=tagVo.getType()%>&class_str=<%=tagVo.getClassStr()%>">�޸�</a>&nbsp&nbsp
	      <%}else{ %>
	      &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
	     <%}} %>
	     <a href="<%=app %>/webtool/tag?act=remove&wapCardId=<%=request.getParameter("wapCardId") %>&tagKey=<%=tagVo.getTagKey()%>&wapNodeId=<%=wapNodeId%>">ɾ��</a>
	   </td>  	 	   
	 </tr>
	 <%} %>
	</table>
	
	<table width="100%">
	<tr>
	  <td align="right"><input type='submit' class="inputsubmit" value='�ύ'/></td>
	</tr>
	</table>
  </form>
</body>
</html>