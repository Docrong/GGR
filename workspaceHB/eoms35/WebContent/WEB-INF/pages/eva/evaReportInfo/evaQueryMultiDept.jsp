<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:directive.page import="com.boco.eoms.eva.util.EvaConstants"/>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<script type="text/javascript">
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'evaKpiInstanceForm'});
    })

    function changeTemplateType() {
        var templateTypeId = document.getElementById("templateTypeId").value;
        var url = "<%=request.getContextPath()%>/eva/evaTasks.do?method=changeTemplateType&templateTypeId=" + templateTypeId;
        var fieldName = "taskId";

        var result = getResponseText(url);

        var arrOptions = result.split(",");
        var obj = $(fieldName);
        var i = 0;
        var j = 0;
        for (i = 0; i < arrOptions.length; i++) {
            var opt = new Option(arrOptions[i + 1], arrOptions[i]);
            obj.options[j] = opt;
            j++;
            i++;
        }
    }

    function getResponseText(url) {
        var xmlhttp;
        if (eoms.isIE) {
            try {
                xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
            } catch (e) {
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
        } else {
            xmlhttp = new XMLHttpRequest();
        }

        var method = "post";
        url = url + "&" + new Date();
        xmlhttp.open(method, url, false);
        xmlhttp.setRequestHeader("content-type", "text/html; charset=GBK");
        xmlhttp.send(null);
        return xmlhttp.responseText;
    }
</script>

<html:form action="/evaReportMultiDepts.do?method=reportMultiDept" styleId="evaKpiInstanceForm" method="post">
    <table class="formTable" id="form">
        <caption>
            <div class="header center">考核报表查询(同一月份-不同厂商)</div>
        </caption>
        <input type="hidden" id="queryType" name="queryType" value="run"/>
        <tr>
            <td class="label">
                选择考核模板
            </td>
            <td class="content">
                <select name="templateTypeId" id="templateTypeId"
                        alt="allowBlank:false,vtext:'请选择考核模板分类'"
                        onchange="changeTemplateType();">
                    <option value="">
                        --请选择模板分类--
                    </option>
                    <logic:iterate id="templateType" name="templateType">
                        <option value="${templateType.nodeId}">
                            <eoms:id2nameDB id="${templateType.nodeId}" beanId="evaTreeDao"/>
                        </option>
                    </logic:iterate>
                </select>
                <select name="taskId" id="taskId"
                        alt="allowBlank:false,vtext:'请选择考核模板'">
                    <option value="">
                        --请选择模板--
                    </option>
                </select>
            </td>
            <td class="label">
                选择合作伙伴
            </td>
            <td class="content">
                所有厂商
            </td>
        </tr>
        <tr>
            <td class="label">
                选择时间
            </td>
            <td class="content">
                <select name="year" id="year" alt="allowBlank:false,vtext:'请选择年份'">
                    <option value="">请选择</option>
                    <option value="2005">2005</option>
                    <option value="2006">2006</option>
                    <option value="2007">2007</option>
                    <option value="2008">2008</option>
                    <option value="2009">2009</option>
                    <option value="2010">2010</option>
                    <option value="2011">2011</option>
                    <option value="2012">2012</option>
                    <option value="2013">2013</option>
                    <option value="2014">2014</option>
                    <option value="2015">2015</option>
                </select>年
                <select name="month" id="month" alt="allowBlank:false,vtext:'请选择月份'">
                    <option value="">请选择</option>
                    <option value="01">1</option>
                    <option value="02">2</option>
                    <option value="03">3</option>
                    <option value="04">4</option>
                    <option value="05">5</option>
                    <option value="06">6</option>
                    <option value="07">7</option>
                    <option value="08">8</option>
                    <option value="09">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                </select>月
            </td>
            <td class="label">
                时间周期类型
            </td>
            <td class="content">
                月度
            </td>
        </tr>
    </table>
    <table id="submit-btn" align="left">
        <tr>
            <td>
                <input type="submit" class="btn" value="查询"/>
            </td>
        </tr>
    </table>
</html:form>
<%@ include file="/common/footer_eoms.jsp" %>