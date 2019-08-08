<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<script type="text/javascript">
    Ext.onReady(function () {
        var tabs = new Ext.TabPanel('main');
        tabs.addTab('sheetform', "工单质检筛选");
        tabs.activate('sheetform');

        //地域
        var showAreaId = document.getElementById("showArea");
        if (showAreaId != null) {
            var areatreeAction = '${app}/xtree.do?method=areaTree';
            deptetree = new xbox({
                btnId: 'showArea',
                treeDataUrl: areatreeAction, treeRootId: '-1', treeRootText: '地市', treeChkMode: '', treeChkType: 'area',
                showChkFldId: 'showArea', saveChkFldId: 'toAreaId'
            });
        }
    });

    function checkData(value) {
        var reg = new RegExp("^[1-9]d*|0$");
        if (!reg.test(value)) {
            alert("您输入的的数字不合法，请输入0-100之内的正整数!");
            $('ratio').value = "0";
        } else {
            if (parseInt(value) > 100) {
                alert("您输入的的数字已超出范围，请输入0-100之内的正整数!");
                $('ratio').value = "0";
            }
        }
    }
</script>

<div id="sheetform" class="tabContent">
    <html:form action="/${module}.do?method=queryQCSheetByWhere" styleId="theform">
        <table class="formTable">
            <tr>
                <td class="label">归档时间</td>
                <td colspan="3">
                    开始时间：
                    <input type="text" class="text" name="beginTime" readonly="readonly"
                           id="beginTime" value="" onclick="popUpCalendar(this, this,null,null,null,true,-1)"/>&nbsp;
                    结束时间：
                    <input type="text" class="text" name="endTime" readonly="readonly"
                           id="endTime" value="" onclick="popUpCalendar(this, this,null,null,null,true,-1)"/>
                </td>
            </tr>
            <tr>
                <td class="label">工单归档模式</td>
                <td class="content">
                    <select id="holdtype" name="holdtype">
                        <option value="0">手工归档</option>
                        <option value="1">自动归档</option>
                        <option value="2">全部</option>
                    </select>
                </td>
                <td class="label"><bean:message bundle="commonfault" key="commonfault.mainNetSortOne"/></td>
                <td class="content">
                    <eoms:comboBox name="mainNetSortOne" id="mainNetSortOne" sub="mainNetSortTwo" initDicId="1010104"/>
                </td>
            </tr>
            <tr>
                <td class="label"><bean:message bundle="commonfault" key="commonfault.mainNetSortTwo"/></td>
                <td class="content">
                    <eoms:comboBox name="mainNetSortTwo" id="mainNetSortTwo" sub="mainNetSortThree"/>
                </td>
                <td class="label"><bean:message bundle="commonfault" key="commonfault.mainNetSortThree"/></td>
                <td class="content">
                    <eoms:comboBox name="mainNetSortThree" id="mainNetSortThree"/>
                </td>
            </tr>
            <tr>
                <td class="label">抽取比例</td>
                <td class="content">
                    <input type="text" class="text" name="ratio" id="ratio" maxlength="3" onblur="checkData(this.value)"
                           value="0"><font size="2" color="red">（提示：请输入0-100的数字）</font>
                </td>
                <td class="label">区域</td>
                <td class="content">
                    <input type="text" class="text max" readonly="readonly" name="showArea" id="showArea"
                           beanId="tawSystemAreaDao"/>
                    <input type="hidden" name="toAreaId" id="toAreaId"/>
                </td>
            </tr>
        </table>
        <br>
        <input type="hidden" name="type" id="type" value="screen"/>
        <input type="submit" class="btn" value="确定"/>&nbsp;&nbsp;
        <input type="reset" class="btn" value="重置"/>
    </html:form>
</div>

<%@ include file="/common/footer_eoms.jsp" %>
