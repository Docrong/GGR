<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<script type="text/javascript">
		function doSubmit() {
			
			//单选题
			var arr = [<c:forEach items="${questionlist1}" var="obj" varStatus="status"><c:if test="${status.count > 1}">, </c:if>'${obj.questionID}'</c:forEach>];
			alert(arr.length);
			for(var i=0;i<arr.length;i++){
				var questionID = arr[i];
				var test2 = questionID+'answer';
				//标准答案
				var answerValue = document.getElementById(test2).value;
				alert(answerValue);
				//得到每道题的答案
				var checks = document.getElementsByName(questionID);
				//用户答案
				var answer = '';
				var len =checks.length; 
				for(var j=0;j<len;j++){
					if(checks[j].checked){
						answer+=checks[j].value;
					} 
				}
				if(answer==""){
					alert('请确保单选题答题完成'); 
					return false;
				}
				if(answer != answerValue){
					alert('单选题不完全正确');
					return false;
				}
			}	
			
			//多选题
			var arr = [<c:forEach items="${questionlist2}" var="obj" varStatus="status"><c:if test="${status.count > 1}">, </c:if>'${obj.questionID}'</c:forEach>];
			alert(arr.length);
			for(var i=0;i<arr.length;i++){
				var questionID = arr[i];
				var test2 = questionID+'answer';
				//标准答案
				var answerValue = document.getElementById(test2).value;
				alert(answerValue);
				//得到每道题的答案
				var checks = document.getElementsByName(questionID);
				//用户答案
				var answer = '';
				var len =checks.length; 
				for(var j=0;j<len;j++){
					if(checks[j].checked){
						answer+=checks[j].value;
					} 
				}
				if(answer==""){
					alert('请确保多选题答题完成');
					return false;
				}
				if(answer != answerValue){
					alert('多选题不完全正确');
					return false;
				}
			}	
			
			//判断题
			var arr = [<c:forEach items="${questionlist3}" var="obj" varStatus="status"><c:if test="${status.count > 1}">, </c:if>'${obj.questionID}'</c:forEach>];
			for(var i=0;i<arr.length;i++){
				var questionID = arr[i];
				var test2 = questionID+'answer';
				//标准答案
				var answerValue = document.getElementById(test2).value;
				alert(answerValue);
				//得到每道题的答案
				var checks = document.getElementsByName(questionID);
				//用户答案
				var answer = '';
				var len =checks.length; 
				for(var j=0;j<len;i++){
					if(checks[j].checked){
						answer+=checks[j].value;
					} 
				}
				if(answer==""){
					alert('请确保答题完成');
					return false;
				}
				if(answer != answerValue){
					alert('只有全部答对才能进入系统');
					return false;
				}
			}	
			
			//document.forms[0].submit();
			//return true;		
		}
 
	</script>
	</head>
<html:form action="/kmExamAttends.do?method=save" styleId="kmExamTestForm" method="post"> 
<table width="600" align="center">
	<caption>
		<div class="header center">${kmExamTest.testName}</div>
	</caption>
	<tr>
	    <td></td>
		<td colspan="2">
		<b>${kmExamTest.testDescription}</b>
		</td>
		<td></td>
	</tr>	
<c:if test="${kmExamTestTypeForm1.description!=null}">
<tr id="typeTable1">
		<td></td>
		<td colspan="2">
		  <table width="100%">
			<tr>
			    <td colspan="2">
					<b id="b1">单选题</b>	
				</td>					
			</tr>
			<tr>
			    <td colspan="2">
					<b>${kmExamTestTypeForm1.description}</b>
				</td>					
			</tr>	
		
			
			<c:forEach items="${questionlist1}" var="item" varStatus="status" begin="0" step="1">			   
			<tr>
			   <td colspan="2">
					<!-- 原内容
						${status.count},${item.question} 
					-->
					<!-- 修改后	-->
					<script type="text/javascript">
						var str = "${item.question}";
						var accessory = "${item.accessory}".split("#");
						for(var i=0;i<accessory.length-1;i++)
						{
							var temp = str.substring(str.indexOf("[attachimg]"),str.lastIndexOf("[/attachimg]")+12);
							str = str.replace("[attachimg]"+accessory[i]+"[/attachimg]","<br/><img src='${app}/kmpictures/kmExamAccessory/"+accessory[i]+"'><br/>");
						}
						document.write("${status.count},"+str);
					</script>
				</td>					
			 </tr>
			<!-- 问题答案 -->
			<input type="hidden" id="${item.questionID}answer" value="${item.answer}">	
			
			<c:forEach items="${choiceList1}" var="item1">
			<c:if test="${item1.questionsID==item.questionID}">
			  <tr>
			    <td colspan="2">
				<input type="radio" id="${item.questionID}" name="${item.questionID}" value="${item1.orderBy}" />${item1.orderBy},${item1.content}
				</td>					
			  </tr>
			  </c:if>
			</c:forEach>
			</c:forEach>							   
		  </table>			  
		</td>		
	</tr>
	</c:if>
	<c:if test="${kmExamTestTypeForm2.description!=null}">
	<tr  id="typeTable2">
		<td></td>
		<td colspan="2">
		  <table width="100%">
			<tr>
			    <td colspan="2">
					<b id="b2">多选题</b>	
				</td>					
			</tr>
			<tr>
			    <td colspan="2">
					<b>${kmExamTestTypeForm2.description}</b>
				</td>					
			</tr>	
		
			
			<c:forEach items="${questionlist2}" var="item"  varStatus="status" begin="0" step="1">			   
			
			<tr>
			   <td colspan="2">
					<!-- 原内容
						${status.count},${item.question} 
					-->
					<!-- 修改后	-->
					<script type="text/javascript">
						var str = "${item.question}";
						var accessory = "${item.accessory}".split("#");
						for(var i=0;i<accessory.length-1;i++)
						{
							var temp = str.substring(str.indexOf("[attachimg]"),str.lastIndexOf("[/attachimg]")+12);
							str = str.replace("[attachimg]"+accessory[i]+"[/attachimg]","<br/><img src='${app}/kmpictures/kmExamAccessory/"+accessory[i]+"'><br/>");
						}
						document.write("${status.count},"+str);
					</script>
				</td>					
			 </tr>
			<!-- 问题答案 -->
			<input type="hidden" id="${item.questionID}answer" value="${item.answer}">		
			<c:forEach items="${choiceList2}" var="item1">
			<c:if test="${item1.questionsID==item.questionID}">
			  <tr>
			    <td colspan="2">
				<input type="checkbox" id="${item.questionID}" name="${item.questionID}" value="${item1.orderBy}" />${item1.orderBy},${item1.content}
				
				</td>					
			  </tr>
			 </c:if>
			</c:forEach>	
		
			</c:forEach>							   
		  </table>			  
		</td>		
	</tr>
	 </c:if>
	 
    <c:if test="${kmExamTestTypeForm3.description!=null}">	 
	<tr id="typeTable3">
		<td></td>
		<td colspan="2">
		  <table width="100%">
			<tr>
			    <td colspan="2">
					<b id="b3">判断题</b>
				</td>					
			</tr>
			<tr>
			    <td colspan="2">
					<b>${kmExamTestTypeForm3.description}</b>
				</td>					
			</tr>	
		
			
			<c:forEach items="${questionlist3}" var="item"  varStatus="status" begin="0" step="1">			   
			<tr>
			   <td colspan="2">
					<!-- 原内容
						${status.count},${item.question} 
					-->
					<!-- 修改后	-->
					<script type="text/javascript">
						var str = "${item.question}";
						var accessory = "${item.accessory}".split("#");
						for(var i=0;i<accessory.length-1;i++)
						{
							var temp = str.substring(str.indexOf("[attachimg]"),str.lastIndexOf("[/attachimg]")+12);
							str = str.replace("[attachimg]"+accessory[i]+"[/attachimg]","<br/><img src='${app}/kmpictures/kmExamAccessory/"+accessory[i]+"'><br/>");
						}
						document.write("${status.count},"+str);
					</script>
				</td>					
			 </tr>
			<!-- 问题答案 -->
			<input type="hidden" id="${item.questionID}answer" value="${item.answer}">		
			<c:forEach items="${choiceList3}" var="item1">
			 <c:if test="${item1.questionsID==item.questionID}">
			  <tr>
			    <td colspan="2">
				<input type="radio" id="${item.questionID}" name="${item.questionID}" value="${item1.orderBy}" />${item1.orderBy},${item1.content}
				</td>					
			  </tr>	
			   </c:if>		 
			</c:forEach>			
			</c:forEach>							   
		  </table>			  
		</td>		
	</tr>
	</c:if>
	<c:if test="${kmExamTestTypeForm4.description!=null}">
	<tr  id="typeTable4">
		<td></td>
		<td colspan="2">
		  <table width="100%">
			<tr>
			    <td colspan="2">
					<b id="b4">填空题</b>
				</td>					
			</tr>
			<tr>
			    <td colspan="2">
					<b>${kmExamTestTypeForm4.description}</b>
				</td>					
			</tr>	
		
			<c:forEach items="${questionlist4}" var="item"  varStatus="status" begin="0" step="1">				   
			<tr>
			   <td colspan="2">
					<!-- 原内容
						${status.count},${item.question} 
					-->
					<!-- 修改后	-->
					<script type="text/javascript">
						var str = "${item.question}";
						var accessory = "${item.accessory}".split("#");
						for(var i=0;i<accessory.length-1;i++)
						{
							var temp = str.substring(str.indexOf("[attachimg]"),str.lastIndexOf("[/attachimg]")+12);
							str = str.replace("[attachimg]"+accessory[i]+"[/attachimg]","<br/><img src='${app}/kmpictures/kmExamAccessory/"+accessory[i]+"'><br/>");
						}
						document.write("${status.count},"+str);
					</script>
				</td>					
			 </tr>
			
			  <tr>
			    <td colspan="2">
				<input type="text" class="text max" id="${item.questionID}" name="${item.questionID}" value="" />
				</td>					
			  </tr>					
			</c:forEach>							   
		  </table>			  
		</td>		
	</tr>
	</c:if>
	<c:if test="${kmExamTestTypeForm5.description!=null}">
	<tr id="typeTable5">
		<td></td>
		<td colspan="2">
		  <table width="100%">
			<tr>
			    <td colspan="2">
					<b id="b5">简答题</b>
				</td>					
			</tr>
			<tr>
			    <td colspan="2">
					<b>${kmExamTestTypeForm5.description}</b>
				</td>					
			</tr>	
		
			
			<c:forEach items="${questionlist5}" var="item"  varStatus="status" begin="0" step="1">			   
			<tr>
			   <td colspan="2">
					<!-- 原内容
						${status.count},${item.question} 
					-->
					<!-- 修改后	-->
					<script type="text/javascript">
						var str = "${item.question}";
						var accessory = "${item.accessory}".split("#");
						for(var i=0;i<accessory.length-1;i++)
						{
							var temp = str.substring(str.indexOf("[attachimg]"),str.lastIndexOf("[/attachimg]")+12);
							str = str.replace("[attachimg]"+accessory[i]+"[/attachimg]","<br/><img src='${app}/kmpictures/kmExamAccessory/"+accessory[i]+"'><br/>");
						}
						document.write("${status.count},"+str);
					</script>
				</td>					
			 </tr>
			
			  <tr>
			    <td colspan="2">
			    <textarea name="${item.questionID}"  id="${item.questionID}" class="textarea max"></textarea>
				</td>					
			  </tr>			 			
			</c:forEach>							   
		  </table>			  
		</td>		
	</tr>	
	</c:if>
	</table>
	<input type="hidden" name="result" /> 
	<input type="hidden" name="testID" value="${kmExamTest.testID}">
	<input type="hidden" name="dateString" value="${dateString}">
	<table align="center">
	<tr>
		<td>
			<input type="button" class="btn" value="进入系统"  onclick="javascript:doSubmit();"/>	
		</td>
	</tr>
</table>	
</html:form>
</html> 
<script type="text/javascript">
   var order=['一','二','三','四','五'];
   var count=0;
   
   var des1='${kmExamTestTypeForm1.description}';  
   if(des1!=''){  
      $('b1').innerHTML=order[count]+" "+$('b1').innerHTML;
      count++;
   }
   var des2='${kmExamTestTypeForm2.description}';  
   if(des2!=''){  
      $('b2').innerHTML=order[count]+" "+$('b2').innerHTML;
      count++;
   }
   var des3='${kmExamTestTypeForm3.description}';  
   if(des3!=''){  
      $('b3').innerHTML=order[count]+" "+$('b3').innerHTML;
      count++;
   }
   var des4='${kmExamTestTypeForm4.description}';  
   if(des4!=''){  
      $('b4').innerHTML=order[count]+" "+$('b4').innerHTML;
      count++;
   }
   var des5='${kmExamTestTypeForm5.description}';  
   if(des5!=''){  
      $('b5').innerHTML=order[count]+" "+$('b5').innerHTML;
      count++;
   }

</script>
