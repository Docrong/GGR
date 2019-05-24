<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<% 
String flagValue=(String)request.getAttribute("flagValue");
System.out.println("@@@@@@@="+request.getAttribute("contentIDStr"));
String contentIDStr=(String)request.getAttribute("contentIDStr");
String scoreStr=(String)request.getAttribute("scoreStr");
String[] contentIDArr=contentIDStr.split(",");
String[] scoreArr=scoreStr.split(",");
int i=0;
int count=new Integer(((String)request.getAttribute("count"))).intValue();
int flag=count;
if(flagValue.equals("0")){
%>
<table id="table${questionType}<%=count%>">
			<c:forEach items="${kmExamQuestionsList}" var="item">			   
				<tr id="rowId<%=count%>" width="100%">				
				<td class="content" width="30%">${item.question}</td>
				<td class="content" width="20%"><eoms:id2nameDB id="${item.createUser}" beanId="tawSystemUserDao" /></td>
				<td class="content" width="20%">${item.createTime}</td>	
				<!--  td class="content" width="10%">${item.createUser}</td>-->					
				<input type="hidden" name="questionID${questionType}<%=count%>" id="questionID${questionType}<%=count%>" value="${item.questionID}">
				<input type="hidden" name="contentID${questionType}<%=count%>" id="contentID${questionType}<%=count%>" value="<%=contentIDArr[i]%>">
				<td class="content"><input type="text" name="score${questionType}<%=count%>" value="<%=scoreArr[i]%>" size="2" alt="allowBlank:false,vtext:'请设置分数'">分</td>	
				<c:if test="${isPublic=='0'}">			
				<td class="content" width="10%"><input type="button" name="del${questionType}<%=count%>" class="btn" value="删除" onclick="DeleteChoiceDB${questionType}<%=flag%>('rowId<%=count%>','<%=contentIDArr[i]%>')"></td>
				</c:if>
				<% count++;i++; %>
				</tr>
				</c:forEach>
</table>
<%}else if(flagValue.equals("1")){ %>
<table id="table${questionType}<%=count%>">
			<c:forEach items="${kmExamQuestionsList}" var="item">			   
				<tr id="rowId<%=count%>" width="100%">				
				<td class="content">${item.question}</td>
				<td class="content"><eoms:id2nameDB id="${item.createUser}" beanId="tawSystemUserDao" /></td>
				<td class="content">${item.createTime}</td>	
				<!--<td class="content">${item.createUser}</td>	-->				
				<input type="hidden" name="questionID${questionType}<%=count%>" id="questionID${questionType}<%=count%>" value="${item.questionID}">
				<input type="hidden" name="contentID${questionType}<%=count%>" id="contentID${questionType}<%=count%>" value="">				
				<td class="content"><input type="text" name="score${questionType}<%=count%>" size="2" alt="allowBlank:false,vtext:'请设置分数'">分</td>	
				<td class="content"><input type="button" name="del${questionType}<%=count%>" class="btn" value="删除" onclick="DeleteChoice${questionType}<%=flag%>('rowId<%=count%>')"></td>
				<% count++; %>
				</tr>
				</c:forEach>
</table>
<%} %>				
	<script>
		t_rownum${questionType}=<%=count%>;
		
		function DeleteChoice${questionType}<%=flag%>(rowId){
			var myTable = document.getElementById('table${questionType}<%=flag%>');	
			//t_rownum${questionType}=t_rownum${questionType}-1;	
			myTable.deleteRow(eval(rowId).rowIndex);	
			return;
		}
		
		function DeleteChoiceDB${questionType}<%=flag%>(rowId,contentID){
		    if(confirm('您确认删除吗？')){	   
			   hidden_submit_param(contentID);      
		    }else
		      return;
		    DeleteChoice${questionType}<%=flag%>(rowId);
		}
</script>			


