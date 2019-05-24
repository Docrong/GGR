<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<script type="text/javascript">
		var checkQuestions = new Array();
		function marker(obj)
		{
			if(obj.style.color == "#f17f4d")
				obj.style.color = "#000";
			else
				obj.style.color = "#f17f4d";
		}
		function addCheck(name)
		{
			checkQuestions.push(name);
		}
		function checkTest()
		{
			var count1 = 0;
			var count2 = 0;
			for(var i=0;i<checkQuestions.length;i++)
			{	
				var obj = document.getElementsByName(checkQuestions[i]);
				var isDo = false;
				for(var j=0;j<obj.length;j++)
				{	
					if(obj[j].type=="radio"&&obj[j].checked==true){isDo=true;}
					if(obj[j].type=="checkbox"&&obj[j].checked==true){isDo=true;}
					if(obj[j].type=="text"&&obj[j].value.length>0){isDo=true;}
					if(obj[j].type=="textarea"&&obj[j].value.length>0){isDo=true;}
				}
				if(!isDo){
					document.getElementById(checkQuestions[i]+"question").style.color = "red";
					count1++;
				}else{
					if(document.getElementById(checkQuestions[i]+"question").style.color!="#f17f4d")
						document.getElementById(checkQuestions[i]+"question").style.color = "#000";
					else 
						count2++;
				}	
			}
			if(count1>0&&count2>0)
				alert("您有"+count1+"道题末做已用红色标出,"+count2+"道题不满意已用橙色标出！");
			else if(count1>0)
				alert("您有"+count1+"道题末做已用红色标出.");
			else if(count2>0)
				alert("您有"+count2+"道题不满意已用橙色标出！");
			else
				alert("您已完成试卷！");
		}
		function showPicture(obj)
		{
			var ig = new Image();
	        ig.src = obj.src;
	        var pDiv = document.getElementById("pictureContainer");
	        pDiv.innerHTML = '<div id="close" style="width:100%;text-align:right;cursor:pointer;" onclick="hiddenP(this)">关闭</div>';
	        pDiv.style.display = "";
		    pDiv.style.position="absolute";
			pDiv.style.top = (document.body.clientHeight-ig.height)/2;
			pDiv.style.left = (document.body.clientWidth-ig.width)/2;
			pDiv.style.width = ig.width;
			pDiv.style.heigth = ig.height;
			pDiv.appendChild(ig);			
		}
		function hiddenP(obj)
		{
			var pDiv = document.getElementById("pictureContainer");
			pDiv.removeChild(obj.nextSibling);
			pDiv.style.display = "none";
		}
		function doSubmit() {
			var count1 = 0;
			var count2 = 0;
			for(var i=0;i<checkQuestions.length;i++)
			{	
				var obj = document.getElementsByName(checkQuestions[i]);
				var isDo = false;
				for(var j=0;j<obj.length;j++)
				{	
					if(obj[j].type=="radio"&&obj[j].checked==true){isDo=true;}
					if(obj[j].type=="checkbox"&&obj[j].checked==true){isDo=true;}
					if(obj[j].type=="text"&&obj[j].value.length>0){isDo=true;}
					if(obj[j].type=="textarea"&&obj[j].value.length>0){isDo=true;}
				}
				if(!isDo){
					document.getElementById(checkQuestions[i]+"question").style.color = "red";
					count1++;
				}else{
					if(document.getElementById(checkQuestions[i]+"question").style.color!="#f17f4d")
						document.getElementById(checkQuestions[i]+"question").style.color = "#000";
					else 
						count2++;
				}	
			}
			var isCommit = false;
			if(count1>0&&count2>0){
				isCommit = confirm("您有"+count1+"道题末做已用红色标出,"+count2+"道题不满意已用橙色标出！\r\r确定要提交吗？");
				if(isCommit)
					{document.forms[0].submit();return true;}
			}
			else if(count1>0){
				isCommit = confirm("您有"+count1+"道题末做已用红色标出！\r\r确定要提交吗？");
				if(isCommit)
					{document.forms[0].submit();return true;}
			}
			else if(count2>0){
				isCommit = confirm("您有"+count2+"道题不满意已用橙色标出！\r\r确定要提交吗？");
				if(isCommit)
					{document.forms[0].submit();return true;}
			}
			else
				{document.forms[0].submit();return true;}
			//return true;		
		}
		
	</script>
	
	</head>
<html:form action="/kmExamAttends.do?method=save" styleId="kmExamTestForm" method="post"> 
<div id="content" style="text-align:center;position:relative;z-index:0;">
<div id="pictureContainer" style="display:none;border:10px #98c0f4 solid;z-index:100;"></div>
<div style="color:#818181;">
	<b>考生姓名：</b>${sessionform.username }&nbsp;&nbsp;&nbsp; |&nbsp;&nbsp;&nbsp;<b>考试时间：</b><font color="red">${kmExamTest.testDuration }</font> 分钟&nbsp;&nbsp;&nbsp;<b>考试总分：</b><font color="red">${kmExamTest.totalScore }</font> 分&nbsp;&nbsp;&nbsp;<b>离交卷还有：<font color="red" id="hour">0</font></b>小时<b><font color="red" id="minute">0</font></b>分钟<b><font color="red" id="second">0</font></b>秒 
</div>
<table width="100%" align="center">
	<caption>
		<div class="header center" style="font-size:23px;">${kmExamTest.testName}</div>
	</caption>
	<tr>
		<td align="center">
			<div style="border:1px #98c0f4 solid;width:90%;height:60px;">
				<div style="text-align:left;margin:5px auto auto 5px;"><b><font color="red">考试须知</font></b></div>
				<div style="float:left;margin:5px auto auto 18px;">${kmExamTest.testDescription}</div>
			</div>
		</td>
	</tr>	
</table>

<div style="text-align:left;width:90%;margin-top:10px;">
	<div style="font-size:14px;"><b>试题列表</b></div>
	<div style="color:#818181;margin-top:5px;">本次考试共<font color="red">
	<%
	int size1 = (List)request.getAttribute("questionlist1")==null?0:((List)request.getAttribute("questionlist1")).size();
	int size2 = (List)request.getAttribute("questionlist2")==null?0:((List)request.getAttribute("questionlist2")).size();
	int size3 = (List)request.getAttribute("questionlist3")==null?0:((List)request.getAttribute("questionlist3")).size();
	int size4 = (List)request.getAttribute("questionlist4")==null?0:((List)request.getAttribute("questionlist4")).size();
	int size5 = (List)request.getAttribute("questionlist5")==null?0:((List)request.getAttribute("questionlist5")).size();
	%>
	<%=size1+size2+size3+size4+size5 %> 
	</font>道题目</div>
</div>
<div style="width:90%;text-align:right;margin-top:5px;">
	<input type="button" class="btn" style="font-size:15px;margin-right:20px;" value="检查试卷"  onclick="checkTest()"/><input type="button" class="btn" style="font-size:15px;margin-right:20px;" value="提交试卷"  onclick="javascript:doSubmit();"/>	
</div>
<div id="testContent" style="width:90%;text-align:left;">
<c:if test="${kmExamTestTypeForm1.description!=null}">
		<div id="part1" style="padding-bottom:20px;">
		  <table width="100%">
			<tr>
			    <td colspan="2" align="center">
					<b style="margin-top:15px;width:95%;height:30px;text-align:left;">${kmExamTestTypeForm1.description}（共<%=((List)request.getAttribute("questionlist1")).size() %>道题，共${kmExamTestTypeForm1.score }分）</b>
				</td>					
			</tr>	
		
			
			<c:forEach items="${questionlist1}" var="item" varStatus="status" begin="0" step="1">			   
			<tr>
			   <td colspan="2" align="center">
					<!-- 原内容
						${status.count},${item.question} 
					-->
					<!-- 修改后	-->
					<div style="border-bottom:1px #dbdbdb solid;width:95%;margin-top:10px;">
						<div style="background-image:url(${app }/images/head/num_bg.PNG);width:27px;height:20;float:left;padding-top:2px;color:#fff;float:left;"><b>${status.count}</b></div><div style="background-image:url(${app }/images/head/top.PNG);width:48px;height:20px;float:right;cursor:pointer;padding-top:6px;padding-left:17px;" onclick="document.body.scrollTop=0;">顶部</div>				
					</div>
					<div style="position:relative;width:95%;text-align:left;margin-top:10px;" id="${item.questionID}question">
						<b style="width:90%">
						<script type="text/javascript">
							var str = "${item.question}";
							var accessory = "${item.accessory}".split("#");
							for(var i=0;i<accessory.length-1;i++)
							{
								var temp = str.substring(str.indexOf("[attachimg]"),str.lastIndexOf("[/attachimg]")+12);
								str = str.replace("[attachimg]"+accessory[i]+"[/attachimg]","<br/><img src='${app}/kmpictures/kmExamAccessory/"+accessory[i]+"' width='100' height='80' onclick='showPicture(this);' ><br/>");
							}
							document.write(str);
							addCheck("${item.questionID}");
						</script>
						</b>
						<div style="background-image:url(${app }/images/head/edit.PNG);width:45px;height:16px;cursor:pointer;position:absolute;right:0px;top:0px;background-repeat:no-repeat;padding-left:18px;padding-top:2px;" title="答的不满意，标记一下，方便查看" onclick="marker(this.parentNode)">标记</div>
					</div>
				</td>					
			 </tr>
				
			<c:forEach items="${choiceList1}" var="item1">
			<c:if test="${item1.questionsID==item.questionID}">
			  <tr>
			    <td colspan="2" align="center">
					<div style="width:95%;text-align:left;margin-top:2px;">${item1.orderBy}.&nbsp;<input type="radio" id="${item.questionID}" name="${item.questionID}" value="${item1.orderBy}" />&nbsp;${item1.content}</div>
				</td>					
			  </tr>
			  </c:if>
			</c:forEach>
			</c:forEach>							   
		  </table>			  
		</div>
	</c:if>
	<c:if test="${kmExamTestTypeForm2.description!=null}">
	<div id="part2" style="padding-bottom:20px;">
		  <table width="100%">
			
			<tr>
			    <td colspan="2" align="center">
					<b style="margin-top:15px;width:95%;height:30px;text-align:left;">${kmExamTestTypeForm2.description}（共<%=((List)request.getAttribute("questionlist2")).size() %>道题，共${kmExamTestTypeForm2.score }分）</b>
				</td>					
			</tr>	
		
			
			<c:forEach items="${questionlist2}" var="item"  varStatus="status" begin="0" step="1">			   
			
			<tr>
			   <td colspan="2" align="center">
					<!-- 原内容
						${status.count},${item.question} 
					-->
					<!-- 修改后	-->
					<div style="border-bottom:1px #dbdbdb solid;width:95%;margin-top:10px;">
						<div style="background-image:url(${app }/images/head/num_bg.PNG);width:27px;height:20;float:left;padding-top:2px;color:#fff;float:left;"><b>${status.count}</b></div><div style="background-image:url(${app }/images/head/top.PNG);width:48px;height:20px;float:right;cursor:pointer;padding-top:6px;padding-left:17px;" onclick="document.body.scrollTop=0;">顶部</div>				
					</div>
					<div style="position:relative;width:95%;text-align:left;margin-top:10px;" id="${item.questionID}question">
						<b>
							<script type="text/javascript">
								var str = "${item.question}";
								var accessory = "${item.accessory}".split("#");
								for(var i=0;i<accessory.length-1;i++)
								{
									var temp = str.substring(str.indexOf("[attachimg]"),str.lastIndexOf("[/attachimg]")+12);
									str = str.replace("[attachimg]"+accessory[i]+"[/attachimg]","<br/><img src='${app}/kmpictures/kmExamAccessory/"+accessory[i]+"' width='100' height='80' onclick='showPicture(this);'><br/>");
								}
								document.write(str);
								addCheck("${item.questionID}");
							</script>
						</b>
						<div style="background-image:url(${app }/images/head/edit.PNG);width:45px;height:16px;cursor:pointer;position:absolute;right:0px;top:0px;background-repeat:no-repeat;padding-left:18px;padding-top:2px;" title="答的不满意，标记一下，方便查看" onclick="marker(this.parentNode)">标记</div>
					</div>
				</td>					
			 </tr>
			<c:forEach items="${choiceList2}" var="item1">
			<c:if test="${item1.questionsID==item.questionID}">
			  <tr>
			    <td colspan="2" align="center">
				<div style="width:95%;text-align:left;margin-top:2px;">${item1.orderBy}.&nbsp;<input type="checkbox" id="${item.questionID}" name="${item.questionID}" value="${item1.orderBy}" />&nbsp;${item1.content}</div>
				</td>					
			  </tr>
			 </c:if>
			</c:forEach>	
		
			</c:forEach>							   
		  </table>			  
		</div>
	 </c:if>
	 
    <c:if test="${kmExamTestTypeForm3.description!=null}">	 
		<div id="part3" style="padding-bottom:20px;">
		  <table width="100%">
			
			<tr>
			    <td colspan="2" align="center">
					<b style="margin-top:15px;width:95%;height:30px;text-align:left;">${kmExamTestTypeForm3.description}（共<%=((List)request.getAttribute("questionlist3")).size() %>道题，共${kmExamTestTypeForm3.score }分）</b>
				</td>					
			</tr>	
		
			
			<c:forEach items="${questionlist3}" var="item"  varStatus="status" begin="0" step="1">			   
			<tr>
			   <td colspan="2" align="center">
					<!-- 原内容
						${status.count},${item.question} 
					-->
					<!-- 修改后	-->
					<div style="border-bottom:1px #dbdbdb solid;width:95%;margin-top:10px;">
						<div style="background-image:url(${app }/images/head/num_bg.PNG);width:27px;height:20;float:left;padding-top:2px;color:#fff;float:left;"><b>${status.count}</b></div><div style="background-image:url(${app }/images/head/top.PNG);width:48px;height:20px;float:right;cursor:pointer;padding-top:6px;padding-left:17px;" onclick="document.body.scrollTop=0;">顶部</div>					
					</div>
					<div style="position:relative;width:95%;text-align:left;margin-top:10px;" id="${item.questionID}question">
						<b>
					<script type="text/javascript">
						var str = "${item.question}";
						var accessory = "${item.accessory}".split("#");
						for(var i=0;i<accessory.length-1;i++)
						{
							var temp = str.substring(str.indexOf("[attachimg]"),str.lastIndexOf("[/attachimg]")+12);
							str = str.replace("[attachimg]"+accessory[i]+"[/attachimg]","<br/><img src='${app}/kmpictures/kmExamAccessory/"+accessory[i]+"' width='100' height='80' onclick='showPicture(this);'><br/>");
						}
						document.write(str);
						addCheck("${item.questionID}");
					</script>
					</b>
					<div style="background-image:url(${app }/images/head/edit.PNG);width:45px;height:16px;cursor:pointer;position:absolute;right:0px;top:0px;background-repeat:no-repeat;padding-left:18px;padding-top:2px;" title="答的不满意，标记一下，方便查看" onclick="marker(this.parentNode)">标记</div>
					</div>
				</td>					
			 </tr>
				
			<c:forEach items="${choiceList3}" var="item1">
			 <c:if test="${item1.questionsID==item.questionID}">
			  <tr>
			    <td colspan="2" align="center">
				<div style="width:95%;text-align:left;margin-top:2px;">${item1.orderBy}.&nbsp;<input type="radio" id="${item.questionID}" name="${item.questionID}" value="${item1.orderBy}" />&nbsp;${item1.content}</div>
				</td>					
			  </tr>	
			   </c:if>		 
			</c:forEach>			
			</c:forEach>							   
		  </table>			  
		</div>
	</c:if>
	<c:if test="${kmExamTestTypeForm4.description!=null}">
		<div id="part4" style="padding-bottom:20px;">
		  <table width="100%">
			
			<tr>
			    <td colspan="2" align="center">
					<b style="margin-top:15px;width:95%;height:30px;text-align:left;">${kmExamTestTypeForm4.description}（共<%=((List)request.getAttribute("questionlist4")).size() %>道题，共${kmExamTestTypeForm4.score }分）</b>
				</td>					
			</tr>	
		
			<c:forEach items="${questionlist4}" var="item"  varStatus="status" begin="0" step="1">				   
			<tr>
			   <td colspan="2" align="center">
					<!-- 原内容
						${status.count},${item.question} 
					-->
					<!-- 修改后	-->
					<div style="border-bottom:1px #dbdbdb solid;width:95%;margin-top:10px;">
						<div style="background-image:url(${app }/images/head/num_bg.PNG);width:27px;height:20;float:left;padding-top:2px;color:#fff;float:left;"><b>${status.count}</b></div><div style="background-image:url(${app }/images/head/top.PNG);width:48px;height:20px;float:right;cursor:pointer;padding-top:6px;padding-left:17px;" onclick="document.body.scrollTop=0;">顶部</div>					
					</div>
					<div style="position:relative;width:95%;text-align:left;margin-top:10px;" id="${item.questionID}question">
						<b>
						<script type="text/javascript">
							var str = "${item.question}";
							var accessory = "${item.accessory}".split("#");
							for(var i=0;i<accessory.length-1;i++)
							{
								var temp = str.substring(str.indexOf("[attachimg]"),str.lastIndexOf("[/attachimg]")+12);
								str = str.replace("[attachimg]"+accessory[i]+"[/attachimg]","<br/><img src='${app}/kmpictures/kmExamAccessory/"+accessory[i]+"' width='100' height='80' onclick='showPicture(this);'><br/>");
							}
							document.write(str);
							addCheck("${item.questionID}");
						</script>
						</b>
						<div style="background-image:url(${app }/images/head/edit.PNG);width:45px;height:16px;cursor:pointer;position:absolute;right:0px;top:0px;background-repeat:no-repeat;padding-left:18px;padding-top:2px;" title="答的不满意，标记一下，方便查看" onclick="marker(this.parentNode)">标记</div>
					</div>
				</td>					
			 </tr>
			
			  <tr>
			    <td colspan="2" align="center">
				<div style="width:95%;text-align:left;margin-top:2px;"><input type="text" class="text max" id="${item.questionID}" name="${item.questionID}" value="" /></div>
				</td>					
			  </tr>					
			</c:forEach>							   
		  </table>			  
		</div>
	</c:if>
	<c:if test="${kmExamTestTypeForm5.description!=null}">
		<div id="part5" style="padding-bottom:20px;">
		  <table width="100%">
			
			<tr>
			    <td colspan="2" align="center">
					<b style="margin-top:15px;width:95%;height:30px;text-align:left;">${kmExamTestTypeForm5.description}（共<%=((List)request.getAttribute("questionlist5")).size() %>道题，共${kmExamTestTypeForm5.score }分）</b>
				</td>					
			</tr>	
		
			
			<c:forEach items="${questionlist5}" var="item"  varStatus="status" begin="0" step="1">			   
			<tr>
			   <td colspan="2" align="center">
					<!-- 原内容
						${status.count},${item.question} 
					-->
					<!-- 修改后	-->
					<div style="border-bottom:1px #dbdbdb solid;width:95%;margin-top:10px;">
						<div style="background-image:url(${app }/images/head/num_bg.PNG);width:27px;height:20;float:left;padding-top:2px;color:#fff;float:left;"><b>${status.count}</b></div><div style="background-image:url(${app }/images/head/top.PNG);width:48px;height:20px;float:right;cursor:pointer;padding-top:6px;padding-left:17px;" onclick="document.body.scrollTop=0;">顶部</div>					
					</div>
					<div style="position:relative;width:95%;text-align:left;margin-top:10px;" id="${item.questionID}question">
						<b>
						<script type="text/javascript">
							var str = "${item.question}";
							var accessory = "${item.accessory}".split("#");
							for(var i=0;i<accessory.length-1;i++)
							{
								var temp = str.substring(str.indexOf("[attachimg]"),str.lastIndexOf("[/attachimg]")+12);
								str = str.replace("[attachimg]"+accessory[i]+"[/attachimg]","<br/><img src='${app}/kmpictures/kmExamAccessory/"+accessory[i]+"' width='100' height='80' onclick='showPicture(this);'><br/>");
							}
							document.write(str);
							addCheck("${item.questionID}");
						</script>
						</b>
						<div style="background-image:url(${app }/images/head/edit.PNG);width:45px;height:16px;cursor:pointer;position:absolute;right:0px;top:0px;background-repeat:no-repeat;padding-left:18px;padding-top:2px;" title="答的不满意，标记一下，方便查看" onclick="marker(this.parentNode)">标记</div>
					</div>
				</td>					
			 </tr>
			
			  <tr>
			    <td colspan="2" align="center">
			    <div style="width:95%;text-align:left;margin-top:2px;"><textarea name="${item.questionID}"  id="${item.questionID}" class="textarea max"></textarea></div>
				</td>					
			  </tr>			 			
			</c:forEach>							   
		  </table>			  
		</div>	
	</c:if>
	</div>
	</div>
	<input type="hidden" name="result" /> 
	<input type="hidden" name="testID" value="${kmExamTest.testID}">
	<input type="hidden" name="dateString" value="${dateString}">

</html:form>
</html> 
<script type="text/javascript">
	var endTime = "${overTime }";
	var date = new Date();
	date.setTime(endTime);
	function showTime()
	{	
		var leftTime = date-new Date();
		var leftHour = parseInt(leftTime/(1000*60*60));
		var leftMinute = parseInt(leftTime%(1000*60*60)/(1000*60));
		var leftSecond = parseInt(leftTime%(1000*60)/1000);
		document.getElementById("hour").innerHTML = leftHour;
		document.getElementById("minute").innerHTML = leftMinute;
		document.getElementById("second").innerHTML = leftSecond;
		if(leftHour==0&&leftMinute==4&&leftSecond==59)
			alert("离考试还有五分钟，请尽快交卷！");
		if(leftHour<=0&&leftMinute<=0&&leftSecond<=0)
		{
			document.forms[0].submit();
			alert("时间到，试卷已自动提交！");
			return true;
		}
		window.setTimeout(showTime,1000);
	}
	Ext.onReady(function(){
			var tabs = new Ext.TabPanel('testContent');
			if(${kmExamTestTypeForm1.description!=null})
				tabs.addTab('part1', '单选题');
			if(${kmExamTestTypeForm2.description!=null})
        		tabs.addTab('part2', '多选题');
        	if(${kmExamTestTypeForm3.description!=null})
        		tabs.addTab('part3', '判断题');
        	if(${kmExamTestTypeForm4.description!=null})
        		tabs.addTab('part4', '填空题');
        	if(${kmExamTestTypeForm5.description!=null})
        		tabs.addTab('part5', '简答题');
    		tabs.activate(0);
    		showTime();
	});
	
</script>
