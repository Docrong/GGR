<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<jsp:directive.page import="com.boco.eoms.km.exam.util.KmExamSpecialtyConstants"/>

<script type="text/javascript">
    var questionType = 0;
    var t_rownum1 = 0;
    var t_rownum2 = 0;
    var t_rownum3 = 0;
    var t_rownum4 = 0;
    var t_rownum5 = 0;

    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'kmExamTestForm'});
        var config = {
            btnId: 'specialtyName',
            treeDataUrl: '${app}/kmmanager/kmExamSpecialtys.do?method=getNodesRadioTree',
            treeRootId: '<%=KmExamSpecialtyConstants.TREE_ROOT_ID%>',
            treeRootText: '专业分类',
            treeChkMode: 'single',
            treeChkType: 'forums',
            showChkFldId: 'specialtyName',
            saveChkFldId: 'specialtyID'
        }
        tree = new xbox(config);

        initDomain('${questionIDStr1}', '${contentIDStr1}', '${scoreStr1}', 1, 0);
        initDomain('${questionIDStr2}', '${contentIDStr2}', '${scoreStr2}', 2, 0);
        initDomain('${questionIDStr3}', '${contentIDStr3}', '${scoreStr3}', 3, 0);
        if ('${type1}' == '') {
            disableType(1);
        }
        if ('${type2}' == '') {
            disableType(2);
        }
        if ('${type3}' == '') {
            disableType(3);
        }
    });

    //动态增加附件选择框
    function AddSpan(type) {
        var type1 = 'FileTable' + type;
        var myTable = document.getElementById(type1);
        //向表格中增加一行
        var myNewRow = myTable.insertRow(0);
        //取得表格的总行数
        var aRows = myTable.rows;
        //取得表格的总网格数
        var aCells = myNewRow.cells;
        //向新增行中增加1个网格

        var oCell1_1 = aRows[0].insertCell(0);
        //设置1个网格的html文本
        oCell1_1.innerHTML = "<span id='Special" + type + "'></span>";
        oCell1_1.colSpan = "4";

        return;
    }

    function createDIV(type) {
        var div = document.createElement("span");
        div.id = "Special" + type;
        var type1 = 'FileTable' + type;
//alert(type1);
        var myTable = document.getElementById(type1);
//myTable.appendChild(div); 
        myTable.insertAdjacentHTML("beforeBegin", "<span id='Specia2'></span>");

    }
</script>

<html:form action="/kmExamTests.do?method=save" styleId="kmExamTestForm" method="post">

    <fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

        <table class="formTable">
            <caption>
                <div class="header center"><fmt:message key="kmExamTest.form.heading"/></div>
            </caption>

            <tr>
                <td class="label">
                    <fmt:message key="kmExamTest.testName"/>
                </td>
                <td class="content" colspan="3">
                    <html:text property="testName" styleId="testName"
                               styleClass="text max"
                               alt="allowBlank:false,vtext:''" value="${kmExamTestForm.testName}"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="kmExamTest.test_description"/>
                </td>
                <td class="content" colspan="3">
                    <textarea name="testDescription" id="question" class="textarea max"
                              alt="allowBlank:false">${kmExamTestForm.testDescription}</textarea>
                </td>
            </tr>


            <!-- -->
            <tr>
                <td class="label">
                    <fmt:message key="kmExamTest.testBeginTime"/>
                </td>
                <td class="content">
                    <input type="text" size="20" readonly="true" class="text"
                           name="testBeginTime" id="testBeginTime"
                           onclick="popUpCalendar(this, this);"
                           alt="vtype:'lessThen',link:'testEndTime',allowBlank:false,vtext:'请选择开始时间...'"
                           value="${kmExamTestForm.testBeginTime}"/>
                </td>
                <td class="label">
                    <fmt:message key="kmExamTest.testEndTime"/>
                </td>
                <td class="content">
                    <input type="text" size="20" readonly="true" class="text"
                           name="testEndTime" id="testEndTime"
                           onclick="popUpCalendar(this, this);"
                           alt="vtype:'moreThen',link:'testBeginTime',allowBlank:false,vtext:'请选择结束时间...'"
                           value="${kmExamTestForm.testEndTime}"/>
                </td>
            </tr>


            <tr>
                <td class="label">
                    <fmt:message key="kmExamTest.testDuration"/>
                </td>
                <td class="content">
                    <html:text property="testDuration" styleId="testDuration"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${kmExamTestForm.testDuration}"/>
                </td>
                <td class="label">
                    <fmt:message key="kmExamTest.totalScore"/>
                </td>
                <td class="content">
                    <html:text property="totalScore" styleId="totalScore"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${kmExamTestForm.totalScore}"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="kmExamTest.specialtyID"/>
                </td>
                <td class="content">
                    <input type="text" id="specialtyName" name="specialtyName" class="text" readonly="readonly"
                           value='<eoms:id2nameDB id="${kmExamTestForm.specialtyID}" beanId="kmExamSpecialtyDao" />'
                           alt="allowBlank:false'"/>
                    <input type="hidden" id=specialtyID name="specialtyID" value="${kmExamTestForm.specialtyID}"/>
                </td>

                <td class="label">

                </td>
                <td class="content">

                </td>
            </tr>

            <!-- tr>
		<td class="label">
			<fmt:message key="kmExamTest.DeptID" />
		</td>
		<td class="content">
			<html:text property="deptID" styleId="deptID"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamTestForm.deptID}" />
		</td>
	</tr> -->
            <html:hidden property="DeptID" value="-1"/>
            <html:hidden property="isPublic" value="0"/>
            <html:hidden property="isDeleted" value="0"/>
            <!-- 是不是随机试卷 “1”不是随机试卷-->
            <html:hidden property="isPermittedOvertime" value="1"/>
            <tr>
                <td colspan="4">
                    <input type="button" class="btn" id="disableType1" style="" value="不要单选题" onclick="disableType(1);">
                    <input type="button" class="btn" id="disableType2" style="" value="不要多选题" onclick="disableType(2);">
                    <input type="button" class="btn" id="disableType3" style="" value="不要判断题" onclick="disableType(3);">
                </td>
                <html:hidden property="testTypeId1" value="${kmExamTestTypeForm1.testTypeId}"/>
                <html:hidden property="testTypeId2" value="${kmExamTestTypeForm2.testTypeId}"/>
                <html:hidden property="testTypeId3" value="${kmExamTestTypeForm3.testTypeId}"/>
                <html:hidden property="testTypeId4" value="${kmExamTestTypeForm4.testTypeId}"/>
                <html:hidden property="testTypeId5" value="${kmExamTestTypeForm5.testTypeId}"/>

            </tr>
            <tr>
                <td colspan="4">
                    <input type="button" class="btn" id="enableType1" style="display:none" value="要单选题"
                           onclick="enableType(1)">
                    <input type="button" class="btn" id="enableType2" style="display:none" value="要多选题"
                           onclick="enableType(2)">
                    <input type="button" class="btn" id="enableType3" style="display:none" value="要判断题"
                           onclick="enableType(3)">
                </td>

            </tr>
            <tr id="typeTable1">
                <td class="label">
                    单选题
                </td>
                <td colspan="3">
                    <table width="100%">
                        <tr>
                            <td class="label">
                                <fmt:message key="kmExamTestType.description"/>
                            </td>
                            <td class="content" colspan="3">
                                <textarea name="description1" id="description1" class="textarea max"
                                          alt="allowBlank:false">${kmExamTestTypeForm1.description}</textarea>
                            </td>
                        </tr>

                        <tr>
                            <td class="label">
                                <fmt:message key="kmExamTestType.quantity"/>
                            </td>
                            <td class="content">
                                <html:text property="quantity1" styleId="quantity1"
                                           styleClass="text medium"
                                           alt="allowBlank:false,vtext:''" value="${kmExamTestTypeForm1.quantity}"/>
                            </td>
                            <td class="label">
                                <fmt:message key="kmExamTestType.score"/>
                            </td>
                            <td class="content">
                                <html:text property="score1" styleId="score1"
                                           styleClass="text medium"
                                           alt="allowBlank:false,vtext:''" value="${kmExamTestTypeForm1.score}"/>
                            </td>
                        </tr>

                        <tr>
                            <td><input type="button" class="btn" value="选择试题" onclick="AddChoice(1);"></td>
                        </tr>

                    </table>
                    <table id="FileTable1" width="100%">
                        <tr>
                            <td class="content"></td>
                            <td class="content"></td>
                            <td class="content"></td>
                            <td class="content"></td>
                            <td class="content"></td>
                        </tr>
                        <span id="Special1"></span>
                    </table>
                </td>
            </tr>
            <tr id="typeTable2">
                <td class="label">
                    多选题
                </td>
                <td colspan="3">
                    <table width="100%">
                        <tr>
                            <td class="label">
                                <fmt:message key="kmExamTestType.description"/>
                            </td>
                            <td class="content" colspan="3">
                                <textarea name="description2" id="description2" class="textarea max"
                                          alt="allowBlank:false">${kmExamTestTypeForm2.description}</textarea>
                            </td>
                        </tr>

                        <tr>
                            <td class="label">
                                <fmt:message key="kmExamTestType.quantity"/>
                            </td>
                            <td class="content">
                                <html:text property="quantity2" styleId="quantity2"
                                           styleClass="text medium"
                                           alt="allowBlank:false,vtext:''" value="${kmExamTestTypeForm2.quantity}"/>
                            </td>
                            <td class="label">
                                <fmt:message key="kmExamTestType.score"/>
                            </td>
                            <td class="content">
                                <html:text property="score2" styleId="score2"
                                           styleClass="text medium"
                                           alt="allowBlank:false,vtext:''" value="${kmExamTestTypeForm2.score}"/>
                            </td>
                        </tr>

                        <tr>
                            <td><input type="button" class="btn" value="选择试题" onclick="AddChoice(2);"></td>
                        </tr>
                    </table>
                    <table id="FileTable2" width="100%">
                        <tr>
                            <td class="content"></td>
                            <td class="content"></td>
                            <td class="content"></td>
                            <td class="content"></td>
                            <td class="content"></td>
                        </tr>
                        <span id="Special2"></span>
                    </table>
                </td>
            </tr>
            <tr id="typeTable3">
                <td class="label">
                    判断题
                </td>
                <td colspan="3">
                    <table width="100%">
                        <tr>
                            <td class="label">
                                <fmt:message key="kmExamTestType.description"/>
                            </td>
                            <td class="content" colspan="3">
                                <textarea name="description3" id="description3" class="textarea max"
                                          alt="allowBlank:false">${kmExamTestTypeForm3.description}</textarea>
                            </td>
                        </tr>

                        <tr>
                            <td class="label">
                                <fmt:message key="kmExamTestType.quantity"/>
                            </td>
                            <td class="content">
                                <html:text property="quantity3" styleId="quantity3"
                                           styleClass="text medium"
                                           alt="allowBlank:false,vtext:''" value="${kmExamTestTypeForm3.quantity}"/>
                            </td>
                            <td class="label">
                                <fmt:message key="kmExamTestType.score"/>
                            </td>
                            <td class="content">
                                <html:text property="score3" styleId="score3"
                                           styleClass="text medium"
                                           alt="allowBlank:false,vtext:''" value="${kmExamTestTypeForm3.score}"/>
                            </td>
                        </tr>

                        <tr>
                            <td><input type="button" class="btn" value="选择试题" onclick="AddChoice(3);"></td>
                        </tr>

                    </table>
                    <table id="FileTable3" width="100%">
                        <tr>
                            <td class="content"></td>
                            <td class="content"></td>
                            <td class="content"></td>
                            <td class="content"></td>
                            <td class="content"></td>
                        </tr>
                        <span id="Special3"></span>
                    </table>
                </td>
            </tr>
        </table>

    </fmt:bundle>

    <table>
        <tr>
            <td>
                <!-- 已经发布的试卷不能被修改了 -->
                <c:if test="${kmExamTestForm.isPublic=='0'||empty kmExamTestForm.isPublic}">
                    <input type="button" class="btn" value="<fmt:message key="button.save"/>" onclick="doSubmit()"/>
                </c:if>
                <c:if test="${not empty kmExamTestForm.testID}">
                    <input type="button" class="btn" value="<fmt:message key="button.delete"/>"
                           onclick="javascript:if(confirm('<fmt:message key="message.delMessage"/>')){
                                   var url='${app}/kmmanager/kmExamTests.do?method=remove&id=${kmExamTestForm.testID}';
                                   location.href=url}"/>

                </c:if>
            </td>
        </tr>
    </table>
    <html:hidden property="testID" value="${kmExamTestForm.testID}"/>

    <html:hidden property="result1" value=""/>
    <html:hidden property="result2" value=""/>
    <html:hidden property="result3" value=""/>
    <html:hidden property="result4" value=""/>
    <html:hidden property="result5" value=""/>
</html:form>

<%@ include file="/common/footer_eoms.jsp" %>
<script type="text/javascript">
    <!--
    function AddChoice(type) {
        window.open('${app}/kmmanager/kmExamQuestionss.do?method=searchForChoice&type=' + type, null, 'left=600,top=150,height=800,width=1000,scrollbars=yes,resizable=yes');
    }

    function initDomain(questionIDStr, contentIDStr, scoreStr, questionType, flagValue) {
        AddSpan(questionType);
        loadDomain(questionIDStr, contentIDStr, scoreStr, questionType, flagValue);
    }

    function disableType(type) {
        eoms.form.disableArea('typeTable' + type, true);
        $('disableType' + type).style.display = "none";
        $('enableType' + type).style.display = "";
    }

    function enableType(type) {
        eoms.form.enableArea('typeTable' + type, true);
        $('disableType' + type).style.display = "";
        $('enableType' + type).style.display = "none";
    }

    function loadDomain(questionIDStr, contentIDStr, scoreStr, questionType, flagValue) {
        var url = "";
        var count = 0;

        if (questionIDStr == '')
            return;

        if (questionType == 1) {
            url = "${app}/kmmanager/kmExamQuestionss.do?method=searchForShow&questionIDStr=" + questionIDStr + "&questionType=" + questionType + "&count=" + t_rownum1 + "&flagValue=" + flagValue + "&contentIDStr=" + contentIDStr + "&scoreStr=" + scoreStr;
            eoms.util.appendPage("Special1", url, true, initjs);
        } else if (questionType == 2) {

            url = "${app}/kmmanager/kmExamQuestionss.do?method=searchForShow&questionIDStr=" + questionIDStr + "&questionType=" + questionType + "&count=" + t_rownum2 + "&flagValue=" + flagValue + "&contentIDStr=" + contentIDStr + "&scoreStr=" + scoreStr;
            eoms.util.appendPage("Special2", url, true, initjs);
        } else if (questionType == 3) {
            url = "${app}/kmmanager/kmExamQuestionss.do?method=searchForShow&questionIDStr=" + questionIDStr + "&questionType=" + questionType + "&count=" + t_rownum3 + "&flagValue=" + flagValue + "&contentIDStr=" + contentIDStr + "&scoreStr=" + scoreStr;
            eoms.util.appendPage("Special3", url, true, initjs);
        }
        //return;
    }

    function initjs() {
        //initfirsttable();
    }

    function doSubmit() {
        var questionID;
        var contentID;
        var score;

        var questionIDStr1 = '';
        var questionIDStr2 = '';
        var questionIDStr3 = '';
        var questionIDStr4 = '';
        var questionIDStr5 = '';

        var result1 = "[";
        var result2 = "[";
        var result3 = "[";
        var result4 = "[";
        var result5 = "[";

        var score1 = 0;
        var score2 = 0;
        var score3 = 0;
        var score4 = 0;
        var score5 = 0;

        //单选题
        if ($("typeTable1").style.display != 'none') {
            for (i = 0; i < t_rownum1; i++) {
                var doc = $("questionID1" + i);
                if (doc == null)
                    continue;
                questionID = $("questionID1" + i).value;
                if (questionIDStr1 != '' && questionIDStr1.indexOf(questionID) > -1) {
                    alert("单选题有重复选入试题");
                    $("del1" + i).focus();
                    $("del1" + i).style.background = '#CDE4FF';
                    return;
                }
                questionIDStr1 = questionIDStr1 + questionID;
                //alert(questionIDStr1);
                contentID = $("contentID1" + i).value;
                score = $("score1" + i).value;
                if (score == "" || score == "0") {
                    alert("请填写分值");
                    $("score1" + i).focus();
                    //$("score1"+i).style.background='#CDE4FF';
                    return;
                }
                score1 = parseInt(score1) + parseInt(score);
                if (isNaN(score)) {
                    alert("必须为数字");
                    $("score1" + i).focus();
                    //$("score1"+i).style.background='#CDE4FF';
                    return;
                }
                if (result1.length > 1) {
                    result1 += ',';
                }
                result1 += '{questionID:\'' + questionID + '\',contentID:\'' + contentID + '\',score:\'' + score + '\'}';
            }

            if (score1 != $("score1").value) {
                alert("单选题分数对不上");
                $("score1").focus();
                //$("score1").style.background='#CDE4FF';
                return;
            }
        }

        //多选题
        if ($("typeTable2").style.display != 'none') {
            for (i = 0; i < t_rownum2; i++) {
                var doc = $("questionID2" + i);
                if (doc == null)
                    continue;
                questionID = $("questionID2" + i).value;
                if (questionIDStr2 != '' && questionIDStr2.indexOf(questionID) > -1) {
                    alert("多选题有重复选入试题");
                    $("del2" + i).focus();
                    $("del2" + i).style.background = '#CDE4FF';
                    return;
                }
                // alert(questionIDStr2);
                questionIDStr2 = questionIDStr2 + "," + questionID;
                contentID = $("contentID2" + i).value;
                score = $("score2" + i).value;
                if (score == "" || score == "0") {
                    alert("请填写分值");
                    $("score2" + i).focus();
                    //$("score2"+i).style.background='#CDE4FF';
                    return;
                }
                score2 = parseInt(score2) + parseInt(score);
                if (isNaN(score)) {
                    alert("必须为数字");
                    $("score2" + i).focus();
                    // $("score2"+i).style.background='#CDE4FF';
                    return;
                }
                if (result2.length > 1) {
                    result2 += ',';
                }

                result2 += '{questionID:\'' + questionID + '\',contentID:\'' + contentID + '\',score:\'' + score + '\'}';
            }
            if (score2 != $("score2").value) {
                alert("多选题分数对不上");
                $("score2").focus();
                //$("score2").style.background='#CDE4FF';
                return;
            }
        }

        //判断题
        if ($("typeTable3").style.display != 'none') {
            for (i = 0; i < t_rownum3; i++) {
                var doc = $("questionID3" + i);
                if (doc == null)
                    continue;
                questionID = $("questionID3" + i).value;
                if (questionIDStr3 != '' && questionIDStr3.indexOf(questionID) > -1) {
                    alert("判断题有重复选入试题");
                    $("del3" + i).focus();
                    $("del3" + i).style.background = '#CDE4FF';
                    return;
                }
                questionIDStr3 = questionIDStr3 + questionID;
                contentID = $("contentID3" + i).value;
                score = $("score3" + i).value;
                if (score == "" || score == "0") {
                    alert("请填写分值");
                    $("score3" + i).focus();
                    // $("score3"+i).style.background='#CDE4FF';
                    return;
                }
                score3 = parseInt(score3) + parseInt(score);
                if (isNaN(score)) {
                    alert("必须为数字");
                    $("score3" + i).focus();
                    //$("score3"+i).style.background='#CDE4FF';
                    return;
                }
                if (result3.length > 1) {
                    result3 += ',';
                }
                result3 += '{questionID:\'' + questionID + '\',contentID:\'' + contentID + '\',score:\'' + score + '\'}';
            }
            if (score3 != $("score3").value) {
                alert("判断题分数对不上");
                $("score3").focus();
                //$("score3").style.background='#CDE4FF';
                return;
            }
        }

        var totalScore = parseInt(score1) + parseInt(score2) + parseInt(score3) + parseInt(score4) + parseInt(score5);
        if (totalScore != $("totalScore").value) {
            alert("总分对不上 总分数应该是" + totalScore + "分");
            $("totalScore").focus();
            //$("totalScore").style.background='#CDE4FF';
            return;
        }

        //判断有没有选择题目
        if (totalScore == "0") {
            alert("请至少选择一道题目");
            return;
        }

        result1 += "]";
        result2 += "]";
        result3 += "]";
        result4 += "]";
        result5 += "]";

        $('result1').value = result1;
        $('result2').value = result2;
        $('result3').value = result3;
        $('result4').value = result4;
        $('result5').value = result5;


        if (v.check()) {
            $("kmExamTestForm").submit();
        }

    }


    function hidden_submit_param(id) {
        var ua = navigator.userAgent;
        var opera = /opera [56789]|opera\/[56789]/i.test(ua);
        var ie = !opera && /msie [56789]/i.test(ua);       // preventing opera to be identified as ie
        var mozilla = !opera && /mozilla\/[56789]/i.test(ua);   // preventing opera to be identified as mz
        var submitURL = '${app}/kmmanager/kmExamTestTypeContents.do?method=remove';
        var oGet = null;
        var oReq = null;
        var param = "id=" + id;
        if (mozilla) {
            oReq = new XMLHttpRequest();
        } else {
            try {
                oReq = new ActiveXObject('MSXML2.XMLHTTP');
            } catch (e) {
                try {
                    oReq = new ActiveXObject('Microsoft.XMLHTTP');
                } catch (oc) {
                    oReq = null
                }
            }
        }
        try {
            oReq.open("POST", submitURL, false);
            oReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            oReq.send(param);
        } catch (e) {
            alert("隐含请求调用失败！");
            return oGet;
        }
        if (mozilla) {
            oGet = oReq.responseXML;
        } else {
            oGet = new ActiveXObject("MSXML2.DOMDocument");
            oGet.async = false;
            oGet.loadXML(oReq.responseText);
        }
        // 处理返回值
        //var retCodeNode = oGet.selectSingleNode("root/msg/content" );
        //var retCode = retCodeNode.text;
        //alert(retCode);

    }

    //-->
</script>