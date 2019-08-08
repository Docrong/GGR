<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page
        import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>


<script language="javascript">
    Ext.onReady(function () {
        initCity();
        initCountry('${object.city}');
        var mainNetSortOne = '${object.mainNetSortOne}';
        var mainNetSortTwo = '${object.mainNetSortTwo}';
        var mainNetSortThree = '${object.mainNetSortThree}';
        var city = '${object.city}';
        var country = '${object.country}';
        console.log(city);
        console.log(country);
        var n1 = document.getElementById("n1");
        var n2 = document.getElementById("n2");
        var n3 = document.getElementById("n3");
        var n4 = document.getElementById("n4");

        var noticeUserId = '${object.noticeUser1}';
        var noticeUserId2 = '${object.noticeUser2}';
        var noticeUserId3 = '${object.noticeUser3}';
        var noticeUserId4 = '${object.noticeUser4}';

        var xmlhttp;
        if (window.XMLHttpRequest) {
            //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
            xmlhttp = new XMLHttpRequest();
        } else {
            // IE6, IE5 浏览器执行代码
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var o = eoms.JSONDecode(xmlhttp.responseText);

                var oCheckbox;
                var oText;
                var br;
                for (var i = 0; i < o.length; i++) {
                    oCheckbox = document.createElement("input");
                    oText = document.createTextNode(o[i].name);
                    br = document.createElement("br");
                    oCheckbox.setAttribute('type', 'checkbox');
                    oCheckbox.setAttribute('name', 'noticeUserId');
                    oCheckbox.setAttribute('value', o[i].id);

                    n1.appendChild(oCheckbox);
                    n1.appendChild(oText);
                    n1.appendChild(br);
                    if (noticeUserId.indexOf(o[i].id) != -1) {
                        oCheckbox.setAttribute('checked', true);
                    }

                    oCheckbox = document.createElement("input");
                    oText = document.createTextNode(o[i].name);
                    br = document.createElement("br");
                    oCheckbox.setAttribute('type', 'checkbox');
                    oCheckbox.setAttribute('name', 'noticeUserId2');
                    oCheckbox.setAttribute('value', o[i].id);

                    n2.appendChild(oCheckbox);
                    n2.appendChild(oText);
                    n2.appendChild(br);
                    if (noticeUserId2.indexOf(o[i].id) != -1) {
                        oCheckbox.setAttribute('checked', true);
                    }

                    oCheckbox = document.createElement("input");
                    oText = document.createTextNode(o[i].name);
                    br = document.createElement("br");
                    oCheckbox.setAttribute('type', 'checkbox');
                    oCheckbox.setAttribute('name', 'noticeUserId3');
                    oCheckbox.setAttribute('value', o[i].id);

                    n3.appendChild(oCheckbox);
                    n3.appendChild(oText);
                    n3.appendChild(br);
                    if (noticeUserId3.indexOf(o[i].id) != -1) {
                        oCheckbox.setAttribute('checked', true);
                    }

                    oCheckbox = document.createElement("input");
                    oText = document.createTextNode(o[i].name);
                    br = document.createElement("br");
                    oCheckbox.setAttribute('type', 'checkbox');
                    oCheckbox.setAttribute('name', 'noticeUserId4');
                    oCheckbox.setAttribute('value', o[i].id);

                    n4.appendChild(oCheckbox);
                    n4.appendChild(oText);
                    n4.appendChild(br);
                    if (noticeUserId4.indexOf(o[i].id) != -1) {
                        oCheckbox.setAttribute('checked', true);
                    }
                }
            }
        }
        //xmlhttp.open("GET","commonfaultSmsRule.do?method=getPostInfo&mainNetSortOne="+mainNetSortOne+"&mainNetSortTwo="+mainNetSortTwo+"&mainNetSortThree="+mainNetSortThree+"&city="+city+"&country="+encodeURI(country),true);
        xmlhttp.open("GET", "supervisetask.do?method=getPostInfo&mainNetSortOne=" + mainNetSortOne + "&mainNetSortTwo=" + mainNetSortTwo + "&mainNetSortThree=" + mainNetSortThree + "&city=" + city + "&country=" + encodeURI(country), true);
        xmlhttp.send();
    });

    //初始化地市
    function initCity() {
        Ext.Ajax.request({
            url: "${app}/sheet/commonfault/commonfaultSmsRule.do?method=initCity&parentareaid=15",
            method: 'post',
            success: function (data) {
                var value = eoms.JSONDecode(data.responseText);
                document.getElementById("tdToDeptId").innerHTML = value[0].cityOpt;
            }
        });
    }

    //初始化区县
    function initCountry(city) {
        Ext.Ajax.request({
            url: "${app}/sheet/commonfault/commonfaultSmsRule.do?method=initCountry&city=" + city,
            method: 'post',
            success: function (data) {
                var value = eoms.JSONDecode(data.responseText);
                document.getElementById("tdMainFaultGenerantCitySubCode").innerHTML = value[0].countryOpt;
            }
        });
    }


    function SubmitCheck() {
        frmReg = document.forms[0];
        frmReg.submit();//提交
    }

    function showNoticeUserId() {
        var mainNetSortOne = document.getElementById('mainNetSortOne').value;
        var mainNetSortTwo = document.getElementById('mainNetSortTwo').value;
        var mainNetSortThree = document.getElementById('mainNetSortThree').value;
        var city = document.getElementById('toDeptId').value;
        var country = document.getElementById('mainFaultGenerantCitySubCode').value;
        var n1 = document.getElementById("n1");
        var n2 = document.getElementById("n2");
        var n3 = document.getElementById("n3");
        var n4 = document.getElementById("n4");
        n1.innerHTML = '';
        n2.innerHTML = '';
        n3.innerHTML = '';
        n4.innerHTML = '';

        var xmlhttp;
        if (window.XMLHttpRequest) {
            //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
            xmlhttp = new XMLHttpRequest();
        } else {
            // IE6, IE5 浏览器执行代码
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var o = eoms.JSONDecode(xmlhttp.responseText);

                var oCheckbox;
                var oText;
                var br;
                for (var i = 0; i < o.length; i++) {
                    oCheckbox = document.createElement("input");
                    oText = document.createTextNode(o[i].name);
                    br = document.createElement("br");
                    oCheckbox.setAttribute('type', 'checkbox');
                    oCheckbox.setAttribute('name', 'noticeUserId');
                    oCheckbox.setAttribute('value', o[i].id);
                    n1.appendChild(oCheckbox);
                    n1.appendChild(oText);
                    n1.appendChild(br);

                    oCheckbox = document.createElement("input");
                    oText = document.createTextNode(o[i].name);
                    br = document.createElement("br");
                    oCheckbox.setAttribute('type', 'checkbox');
                    oCheckbox.setAttribute('name', 'noticeUserId2');
                    oCheckbox.setAttribute('value', o[i].id);
                    n2.appendChild(oCheckbox);
                    n2.appendChild(oText);
                    n2.appendChild(br);

                    oCheckbox = document.createElement("input");
                    oText = document.createTextNode(o[i].name);
                    br = document.createElement("br");
                    oCheckbox.setAttribute('type', 'checkbox');
                    oCheckbox.setAttribute('name', 'noticeUserId3');
                    oCheckbox.setAttribute('value', o[i].id);
                    n3.appendChild(oCheckbox);
                    n3.appendChild(oText);
                    n3.appendChild(br);

                    oCheckbox = document.createElement("input");
                    oText = document.createTextNode(o[i].name);
                    br = document.createElement("br");
                    oCheckbox.setAttribute('type', 'checkbox');
                    oCheckbox.setAttribute('name', 'noticeUserId4');
                    oCheckbox.setAttribute('value', o[i].id);
                    n4.appendChild(oCheckbox);
                    n4.appendChild(oText);
                    n4.appendChild(br);
                }
            }
        }
        //xmlhttp.open("GET","commonfaultSmsRule.do?method=getPostInfo&mainNetSortOne="+mainNetSortOne+"&mainNetSortTwo="+mainNetSortTwo+"&mainNetSortThree="+mainNetSortThree+"&city="+city+"&country="+encodeURI(country),true);
        xmlhttp.open("GET", "supervisetask.do?method=getPostInfo&mainNetSortOne=" + mainNetSortOne + "&mainNetSortTwo=" + mainNetSortTwo + "&mainNetSortThree=" + mainNetSortThree + "&city=" + city + "&country=" + encodeURI(country), true);
        xmlhttp.send();


    }

    function publicClose() {
        window.close();
    }

</script>

<form name="addform" method="post"
      action="../supervisetask/supervisetask.do?method=supervisetaskRuleAddSave">

    <table width="500" class="formTable">
        <caption>督办规则编辑</caption>
        <input type="hidden" id="id" name="id" value="${object.id }"/>
        <tr>
            <td>
                专业*
            </td>
            <td>
                <eoms:comboBox name="major" id="major" initDicId="1010107" defaultValue="${object.major }">
                </eoms:comboBox>
            </td>

        </tr>

        <tr>
            <td class="label">网络分类一级*</td>
            <td><eoms:comboBox name="mainNetSortOne" id="mainNetSortOne"
                               sub="mainNetSortTwo" initDicId="1010104" defaultValue="${object.mainNetSortOne }"/></td>
            <td class="label">网络分类二级</td>
            <td><eoms:comboBox name="mainNetSortTwo" id="mainNetSortTwo" initDicId="${object.mainNetSortOne }"
                               sub="mainNetSortThree" defaultValue="${object.mainNetSortTwo }"/></td>
        </tr>
        <tr>
            <td class="label">网络分类三级</td>
            <td><eoms:comboBox name="mainNetSortThree" id="mainNetSortThree" initDicId="${object.mainNetSortTwo }"
                               defaultValue="${object.mainNetSortThree }"/>
            </td>


        </tr>
        <tr>

        </tr>
        <tr>
            <td class="label">地市*</td>
            <td id="tdToDeptId"><select id="toDeptId" name="toDeptId"
                                        onchange="initCountry(this.value);showNoticeUserId();">

            </select></td>
            <td class="label">区县</td>
            <td id="tdMainFaultGenerantCitySubCode"><select
                    id="mainFaultGenerantCitySubCode" name="mainFaultGenerantCitySubCode"
                    onchange="showNoticeUserId();">

            </select></td>

        </tr>
        <tr>
            <td class="label">挂牌类型</td>
            <td>
                <input type="text" name=listedRegulationType id="listedRegulationType"
                       class="text" value="${object.listedRegulationType }"/>
            </td>
            <td class="label">挂牌周期</td>
            <td>
                <input type="text" id="listedRegulationCycle" name="listedRegulationCycle"
                       value="${object.listedRegulationCycle }">
            </td>

        </tr>
        <tr>
            <td class="label">督办方式</td>
            <td class="content">
                <input type="checkbox" name="superviseType" value="IVR"
                       <c:if test="${fn:contains(object.superviseType,'IVR') }">checked="checked"</c:if> >IVR
                <input type="checkbox" name="superviseType" value="SMS"
                       <c:if test="${fn:contains(object.superviseType,'SMS') }">checked="checked"</c:if>>短信
            </td>
        </tr>

        <table class="table" width="100%" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <th class="label">督办级别</th>
                <th class="label">受理超时(天)</th>
                <th class="label">处理超时(天)</th>
                <th class="label">督办对象</th>
            </tr>
            </thead>
            <tr>
                <td class="label">1</td>
                <td>
                    <input type="text" name="acceptOverTime1" id="acceptOverTime1" class="text"
                           value="${object.acceptOverTime1 }"/>(0~9)
                </td>
                <td>
                    <input type="text" name="dealOverTime1" id="dealOverTime1" class="text"
                           value="${object.dealOverTime1 }">(0~9)
                </td>
                <td id="n1"></td>
            </tr>
            <tr>
                <td class="label">2</td>
                <td>
                    <input type="text" name="acceptOverTime2" id="acceptOverTime2" class="text"
                           value="${object.acceptOverTime2 }"/>(0~9)
                </td>
                <td>
                    <input type="text" name="dealOverTime2" id="dealOverTime2" class="text"
                           value="${object.dealOverTime2 }">(0~9)
                </td>
                <td id="n2"></td>
            </tr>
            <tr>
                <td class="label">3</td>
                <td>
                    <input type="text" name="acceptOverTime3" id="acceptOverTime3" class="text"
                           value="${object.acceptOverTime3 }"/>(0~9)
                </td>
                <td>
                    <input type="text" name="dealOverTime3" id="dealOverTime3" class="text"
                           value="${object.dealOverTime3 }">(0~9)
                </td>
                <td id="n3"></td>
            </tr>
            <tr>
                <td class="label">4</td>
                <td>
                    <input type="text" name="acceptOverTime4" id="acceptOverTime4" class="text"
                           value="${object.acceptOverTime4 }"/>(0~9)
                </td>
                <td>
                    <input type="text" name="dealOverTime4" id="dealOverTime4" class="text"
                           value="${object.dealOverTime4 }">(0~9)
                </td>
                <td id="n4"></td>
            </tr>

        </table>


    </table>
    <center>
        <br><br>
        <input type="button" value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;保存&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" Class="button"
               onclick="SubmitCheck();">
        &emsp;
        <input type="button" value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;关闭&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" Class="button"
               onclick="publicClose();">
    </center>


</form>

<%@ include file="/common/footer_eoms.jsp" %>
