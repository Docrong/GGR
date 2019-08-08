<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="java.util.List" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <script type="text/javascript">
        function showPicture(obj) {
            var ig = new Image();
            ig.src = obj.src;
            var pDiv = document.getElementById("pictureContainer");
            pDiv.innerHTML = '<div id="close" style="width:100%;text-align:right;cursor:pointer;" onclick="hiddenP(this)">关闭</div>';
            pDiv.style.display = "";
            pDiv.style.position = "absolute";
            pDiv.style.top = (document.body.clientHeight - ig.height) / 2;
            pDiv.style.left = (document.body.clientWidth - ig.width) / 2;
            pDiv.style.width = ig.width;
            pDiv.style.heigth = ig.height;
            pDiv.appendChild(ig);
        }

        function hiddenP(obj) {
            var pDiv = document.getElementById("pictureContainer");
            pDiv.removeChild(obj.nextSibling);
            pDiv.style.display = "none";
        }

        function doSubmit() {
            //填空题
            var arr = [<c:forEach items="${questionlist4}" var="obj" varStatus="status"><c:if test="${status.count > 1}">, </c:if>'${obj.questionID}'</c:forEach>];
            for (var i = 0; i < arr.length; i++) {
                var questionID = arr[i];
                var value = document.getElementById(questionID).value;
                var test2 = questionID + '1';
                var scoreValue = document.getElementById(test2).value;
                if (value == "") {
                    alert('还有题目没有评分');
                    return false;
                }
                if (parseInt(value) > parseInt(scoreValue)) {
                    alert('不能超过该题的总分');
                    return false;
                }
            }

            //简答题
            var arr = [<c:forEach items="${questionlist5}" var="obj" varStatus="status"><c:if test="${status.count > 1}">, </c:if>'${obj.questionID}'</c:forEach>];
            for (var i = 0; i < arr.length; i++) {
                var questionID = arr[i];
                var value = document.getElementById(questionID).value;
                var test2 = questionID + '1';
                var scoreValue = document.getElementById(test2).value;
                if (value == "") {
                    alert('还有题目没有评分');
                    return false;
                }
                if (parseInt(value) > parseInt(scoreValue)) {
                    alert('不能超过该题的总分');
                    return false;
                }
            }
            document.forms[0].submit();
            return true;
        }

    </script>

</head>
<html:form action="/kmExamAttends.do?method=saveRead" styleId="kmExamTestForm" method="post">
    <div id="content" style="text-align:center;position:relative;z-index:0;">
        <div id="pictureContainer" style="display:none;border:10px #98c0f4 solid;z-index:100;"></div>
        <div style="color:#818181;">
            <b>答题人姓名：</b><eoms:id2nameDB id="${attendUser}" beanId="tawSystemUserDao"/>&nbsp;&nbsp;&nbsp; |&nbsp;&nbsp;&nbsp;<b>考试时间：</b><font
                color="red">${kmExamTest.testDuration }</font> 分钟&nbsp;&nbsp;&nbsp;<b>考试总分：</b><font
                color="red">${kmExamTest.totalScore }</font> 分&nbsp;&nbsp;&nbsp;
        </div>
        <table width="100%" align="center">
            <caption>
                <div class="header center" style="font-size:23px;">${kmExamTest.testName}</div>
            </caption>
            <tr>
                <td align="center">
                    <div style="border:1px #98c0f4 solid;width:90%;height:60px;">
                        <div style="text-align:left;margin:5px auto auto 5px;"><b>阅卷须知</b></div>
                        <div style="float:left;margin:5px auto auto 18px;"><font color='red'>*</font>号为必填内容</div>
                    </div>
                </td>
            </tr>
        </table>

        <div style="text-align:left;width:90%;margin-top:10px;">
            <div style="font-size:14px;"><b>试题列表</b></div>
            <div style="color:#818181;margin-top:5px;">本次试卷共<font color="red">
                <%
                    int size1 = (List) request.getAttribute("questionlist1") == null ? 0 : ((List) request.getAttribute("questionlist1")).size();
                    int size2 = (List) request.getAttribute("questionlist2") == null ? 0 : ((List) request.getAttribute("questionlist2")).size();
                    int size3 = (List) request.getAttribute("questionlist3") == null ? 0 : ((List) request.getAttribute("questionlist3")).size();
                    int size4 = (List) request.getAttribute("questionlist4") == null ? 0 : ((List) request.getAttribute("questionlist4")).size();
                    int size5 = (List) request.getAttribute("questionlist5") == null ? 0 : ((List) request.getAttribute("questionlist5")).size();
                %>
                <%=size1 + size2 + size3 + size4 + size5 %>
            </font>道题目
            </div>
        </div>
        <div style="width:90%;text-align:right;margin-top:5px;">
            <input type="button" class="btn" style="font-size:15px;margin-right:20px;" value="提交"
                   onclick="javascript:doSubmit();"/>
        </div>
        <div id="testContent" style="width:90%;text-align:left;">
            <c:if test="${kmExamTestTypeForm1.description!=null}">
                <div id="part1" style="padding-bottom:20px;">
                    <table width="100%">
                        <tr>
                            <td colspan="2" align="center">
                                <b style="margin-top:15px;width:95%;height:30px;text-align:left;">${kmExamTestTypeForm1.description}（共<%=((List) request.getAttribute("questionlist1")).size() %>
                                    道题，共${kmExamTestTypeForm1.score }分）</b>
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
                                        <div style="background-image:url(${app }/images/head/num_bg.PNG);width:27px;height:20;float:left;padding-top:2px;color:#fff;float:left;">
                                            <b>${status.count}</b></div>
                                        <div style="background-image:url(${app }/images/head/top.PNG);width:48px;height:20px;float:right;cursor:pointer;padding-top:6px;padding-left:17px;"
                                             onclick="document.body.scrollTop=0;">顶部
                                        </div>
                                    </div>
                                    <div style="position:relative;width:95%;text-align:left;margin-top:10px;"
                                         id="${item.questionID}question">
                                        <b style="width:90%">
                                            <script type="text/javascript">
                                                var str = "${item.question}";
                                                var accessory = "${item.accessory}".split("#");
                                                for (var i = 0; i < accessory.length - 1; i++) {
                                                    var temp = str.substring(str.indexOf("[attachimg]"), str.lastIndexOf("[/attachimg]") + 12);
                                                    str = str.replace("[attachimg]" + accessory[i] + "[/attachimg]", "<br/><img src='${app}/kmpictures/kmExamAccessory/" + accessory[i] + "' width='100' height='80' onclick='showPicture(this);' ><br/>");
                                                }
                                                document.write(str);

                                            </script>
                                        </b>
                                        <div style="width:100%;height:16px;padding-left:18px;padding-top:10px;padding-bottom:10px;margin-bottom:10px;margin-top:10px;border-top:1px #cacaca dotted;border-bottom:1px dotted #cacaca;">
                                            <c:forEach items="${answerList1}" var="item0">
                                                <c:if test="${item0.questionId==item.questionID}">
                                                    您的答案：<b>${item0.answer}</b><br/><br/>得分:<font color="red"><b>${item0.score}</b></font><br/><br/>标准答案:<b>${item0.referenceAnswer}</b>
                                                </c:if>
                                            </c:forEach>

                                        </div>
                                    </div>
                                </td>
                            </tr>

                            <c:forEach items="${choiceList1}" var="item1">
                                <c:if test="${item1.questionsID==item.questionID}">
                                    <tr>
                                        <td colspan="2" align="center">
                                            <div style="width:95%;text-align:left;margin-top:2px;">${item1.orderBy}.&nbsp;<input
                                                    type="radio" id="${item.questionID}" name="${item.questionID}"
                                                    value="${item1.orderBy}" disabled="true"/>&nbsp;${item1.content}
                                            </div>
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
                                <b style="margin-top:15px;width:95%;height:30px;text-align:left;">${kmExamTestTypeForm2.description}（共<%=((List) request.getAttribute("questionlist2")).size() %>
                                    道题，共${kmExamTestTypeForm2.score }分）</b>
                            </td>
                        </tr>


                        <c:forEach items="${questionlist2}" var="item" varStatus="status" begin="0" step="1">

                            <tr>
                                <td colspan="2" align="center">
                                    <!-- 原内容
						${status.count},${item.question} 
					-->
                                    <!-- 修改后	-->
                                    <div style="border-bottom:1px #dbdbdb solid;width:95%;margin-top:10px;">
                                        <div style="background-image:url(${app }/images/head/num_bg.PNG);width:27px;height:20;float:left;padding-top:2px;color:#fff;float:left;">
                                            <b>${status.count}</b></div>
                                        <div style="background-image:url(${app }/images/head/top.PNG);width:48px;height:20px;float:right;cursor:pointer;padding-top:6px;padding-left:17px;"
                                             onclick="document.body.scrollTop=0;">顶部
                                        </div>
                                    </div>
                                    <div style="position:relative;width:95%;text-align:left;margin-top:10px;"
                                         id="${item.questionID}question">
                                        <b>
                                            <script type="text/javascript">
                                                var str = "${item.question}";
                                                var accessory = "${item.accessory}".split("#");
                                                for (var i = 0; i < accessory.length - 1; i++) {
                                                    var temp = str.substring(str.indexOf("[attachimg]"), str.lastIndexOf("[/attachimg]") + 12);
                                                    str = str.replace("[attachimg]" + accessory[i] + "[/attachimg]", "<br/><img src='${app}/kmpictures/kmExamAccessory/" + accessory[i] + "' width='100' height='80' onclick='showPicture(this);'><br/>");
                                                }
                                                document.write(str);

                                            </script>
                                        </b>
                                        <div style="width:100%;height:16px;padding-left:18px;padding-top:10px;padding-bottom:10px;margin-bottom:10px;margin-top:10px;border-top:1px #cacaca dotted;border-bottom:1px dotted #cacaca;">
                                            <c:forEach items="${answerList2}" var="item0">
                                                <c:if test="${item0.questionId==item.questionID}">
                                                    您的答案：<b>${item0.answer}</b><br/><br/>得分:<font color="red"><b>${item0.score}</b></font><br/><br/>标准答案:<b>${item0.referenceAnswer}</b>
                                                </c:if>
                                            </c:forEach>

                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <c:forEach items="${choiceList2}" var="item1">
                                <c:if test="${item1.questionsID==item.questionID}">
                                    <tr>
                                        <td colspan="2" align="center">
                                            <div style="width:95%;text-align:left;margin-top:2px;">${item1.orderBy}.&nbsp;<input
                                                    type="checkbox" disabled="true" id="${item.questionID}"
                                                    name="${item.questionID}"
                                                    value="${item1.orderBy}"/>&nbsp;${item1.content}</div>
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
                                <b style="margin-top:15px;width:95%;height:30px;text-align:left;">${kmExamTestTypeForm3.description}（共<%=((List) request.getAttribute("questionlist3")).size() %>
                                    道题，共${kmExamTestTypeForm3.score }分）</b>
                            </td>
                        </tr>


                        <c:forEach items="${questionlist3}" var="item" varStatus="status" begin="0" step="1">
                            <tr>
                                <td colspan="2" align="center">
                                    <!-- 原内容
						${status.count},${item.question} 
					-->
                                    <!-- 修改后	-->
                                    <div style="border-bottom:1px #dbdbdb solid;width:95%;margin-top:10px;">
                                        <div style="background-image:url(${app }/images/head/num_bg.PNG);width:27px;height:20;float:left;padding-top:2px;color:#fff;float:left;">
                                            <b>${status.count}</b></div>
                                        <div style="background-image:url(${app }/images/head/top.PNG);width:48px;height:20px;float:right;cursor:pointer;padding-top:6px;padding-left:17px;"
                                             onclick="document.body.scrollTop=0;">顶部
                                        </div>
                                    </div>
                                    <div style="position:relative;width:95%;text-align:left;margin-top:10px;"
                                         id="${item.questionID}question">
                                        <b>
                                            <script type="text/javascript">
                                                var str = "${item.question}";
                                                var accessory = "${item.accessory}".split("#");
                                                for (var i = 0; i < accessory.length - 1; i++) {
                                                    var temp = str.substring(str.indexOf("[attachimg]"), str.lastIndexOf("[/attachimg]") + 12);
                                                    str = str.replace("[attachimg]" + accessory[i] + "[/attachimg]", "<br/><img src='${app}/kmpictures/kmExamAccessory/" + accessory[i] + "' width='100' height='80' onclick='showPicture(this);'><br/>");
                                                }
                                                document.write(str);

                                            </script>
                                        </b>
                                        <div style="width:100%;height:16px;padding-left:18px;padding-top:10px;padding-bottom:10px;margin-bottom:10px;margin-top:10px;border-top:1px #cacaca dotted;border-bottom:1px dotted #cacaca;">
                                            <c:forEach items="${answerList3}" var="item0">
                                                <c:if test="${item0.questionId==item.questionID}">
                                                    您的答案：<b>${item0.answer}</b><br/><br/>得分:<font color="red"><b>${item0.score}</b></font><br/><br/>标准答案:<b>${item0.referenceAnswer}</b>
                                                </c:if>
                                            </c:forEach>

                                        </div>
                                    </div>
                                </td>
                            </tr>

                            <c:forEach items="${choiceList3}" var="item1">
                                <c:if test="${item1.questionsID==item.questionID}">
                                    <tr>
                                        <td colspan="2" align="center">
                                            <div style="width:95%;text-align:left;margin-top:2px;">${item1.orderBy}.&nbsp;<input
                                                    type="radio" disabled="true" id="${item.questionID}"
                                                    name="${item.questionID}"
                                                    value="${item1.orderBy}"/>&nbsp;${item1.content}</div>
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
                                <b style="margin-top:15px;width:95%;height:30px;text-align:left;">${kmExamTestTypeForm4.description}（共<%=((List) request.getAttribute("questionlist4")).size() %>
                                    道题，共${kmExamTestTypeForm4.score }分）</b>
                            </td>
                        </tr>

                        <c:forEach items="${questionlist4}" var="item" varStatus="status" begin="0" step="1">
                            <tr>
                                <td colspan="2" align="center">
                                    <!-- 原内容
						${status.count},${item.question} 
					-->
                                    <!-- 修改后	-->
                                    <div style="border-bottom:1px #dbdbdb solid;width:95%;margin-top:10px;">
                                        <div style="background-image:url(${app }/images/head/num_bg.PNG);width:27px;height:20;float:left;padding-top:2px;color:#fff;float:left;">
                                            <b>${status.count}</b></div>
                                        <div style="background-image:url(${app }/images/head/top.PNG);width:48px;height:20px;float:right;cursor:pointer;padding-top:6px;padding-left:17px;"
                                             onclick="document.body.scrollTop=0;">顶部
                                        </div>
                                    </div>
                                    <div style="position:relative;width:95%;text-align:left;margin-top:10px;"
                                         id="${item.questionID}question">
                                        <b>
                                            <script type="text/javascript">
                                                var str = "${item.question}";
                                                var accessory = "${item.accessory}".split("#");
                                                for (var i = 0; i < accessory.length - 1; i++) {
                                                    var temp = str.substring(str.indexOf("[attachimg]"), str.lastIndexOf("[/attachimg]") + 12);
                                                    str = str.replace("[attachimg]" + accessory[i] + "[/attachimg]", "<br/><img src='${app}/kmpictures/kmExamAccessory/" + accessory[i] + "' width='100' height='80' onclick='showPicture(this);'><br/>");
                                                }
                                                document.write(str);

                                            </script>
                                        </b>
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <td colspan="2" align="center">
                                    <div style="width:95%;text-align:left;padding-top:10px;padding-bottom:10px;margin-bottom:10px;margin-top:10px;border-top:1px #cacaca dotted;border-bottom:1px dotted #cacaca;">
                                        <c:forEach items="${answerList4}" var="item0">
                                            <c:if test="${item0.questionId==item.questionID}">
                                                答案 ：<b>${item0.answer} </b><br/><br/>
                                                参考答案 ：${item0.referenceAnswer} <br/><br/>
                                                评分&nbsp;<font color='red'>*</font> ：<input type="text" id="${item.questionID}" name="${item.questionID}" onchange="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}"><br>
                                                <input type="hidden" id="${item.questionID}1"
                                                       value="${item0.referenceScore}">
                                            </c:if>
                                        </c:forEach>

                                    </div>
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
                                <b style="margin-top:15px;width:95%;height:30px;text-align:left;">${kmExamTestTypeForm5.description}（共<%=((List) request.getAttribute("questionlist5")).size() %>
                                    道题，共${kmExamTestTypeForm5.score }分）</b>
                            </td>
                        </tr>


                        <c:forEach items="${questionlist5}" var="item" varStatus="status" begin="0" step="1">
                            <tr>
                                <td colspan="2" align="center">
                                    <!-- 原内容
						${status.count},${item.question} 
					-->
                                    <!-- 修改后	-->
                                    <div style="border-bottom:1px #dbdbdb solid;width:95%;margin-top:10px;">
                                        <div style="background-image:url(${app }/images/head/num_bg.PNG);width:27px;height:20;float:left;padding-top:2px;color:#fff;float:left;">
                                            <b>${status.count}</b></div>
                                        <div style="background-image:url(${app }/images/head/top.PNG);width:48px;height:20px;float:right;cursor:pointer;padding-top:6px;padding-left:17px;"
                                             onclick="document.body.scrollTop=0;">顶部
                                        </div>
                                    </div>
                                    <div style="position:relative;width:95%;text-align:left;margin-top:10px;"
                                         id="${item.questionID}question">
                                        <b>
                                            <script type="text/javascript">
                                                var str = "${item.question}";
                                                var accessory = "${item.accessory}".split("#");
                                                for (var i = 0; i < accessory.length - 1; i++) {
                                                    var temp = str.substring(str.indexOf("[attachimg]"), str.lastIndexOf("[/attachimg]") + 12);
                                                    str = str.replace("[attachimg]" + accessory[i] + "[/attachimg]", "<br/><img src='${app}/kmpictures/kmExamAccessory/" + accessory[i] + "' width='100' height='80' onclick='showPicture(this);'><br/>");
                                                }
                                                document.write(str);

                                            </script>
                                        </b>
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <td colspan="2" align="center">
                                    <div style="width:95%;text-align:left;padding-top:10px;padding-bottom:10px;margin-bottom:10px;margin-top:10px;border-top:1px #cacaca dotted;border-bottom:1px dotted #cacaca;">
                                        <c:forEach items="${answerList5}" var="item0">
                                            <c:if test="${item0.questionId==item.questionID}">
                                                答案 ：<b>${item0.answer} </b><br/><br/>
                                                参考答案 ：${item0.referenceAnswer} <br/><br/>
                                                评分&nbsp;<font color='red'>*</font> ：<input type="text" id="${item.questionID}" name="${item.questionID}" onchange="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}"><br>
                                                <input type="hidden" id="${item.questionID}1"
                                                       value="${item0.referenceScore}">
                                            </c:if>
                                        </c:forEach>

                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:if>
        </div>
    </div>
    <input type="hidden" name="testID" value="${kmExamTest.testID}">
    <input type="hidden" name="attendUser" value="${attendUser}">
</html:form>
</html>
<script type="text/javascript">
    Ext.onReady(function () {
        var tabs = new Ext.TabPanel('testContent');
        if (${kmExamTestTypeForm1.description!=null})
            tabs.addTab('part1', '单选题');
        if (${kmExamTestTypeForm2.description!=null})
            tabs.addTab('part2', '多选题');
        if (${kmExamTestTypeForm3.description!=null})
            tabs.addTab('part3', '判断题');
        if (${kmExamTestTypeForm4.description!=null})
            tabs.addTab('part4', '填空题');
        if (${kmExamTestTypeForm5.description!=null})
            tabs.addTab('part5', '简答题');
        tabs.activate(0);
    });

</script>
