<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>
<script type="text/javascript">
    <!--
    function showContent() {
        window.open("smsSend.do?method=forward2Content", 'win1', 'directories=no,fullscreen=no,height=420,width=600,top=0,left=0,location=no,menubar=no,resizable=yes,scrollbars=yes,status=yes,toolbar=no');
    }

    function showMobiles() {
        window.open("smsSend.do?method=forward2Mobiles", 'win1', 'directories=no,fullscreen=no,height=420,width=600,top=0,left=0,location=no,menubar=no,resizable=yes,scrollbars=yes,status=yes,toolbar=no');
    }

    Ext.QuickTips.init();
    var reback = false;
    xbox.prototype.onAfterCheck = function (node, checked) {
        reback = false;
        if (checked && (!node.attributes.mobile || node.attributes.mobile == '')) {
            alert('没有该人员的电话号码！');
            node.getUI().checkbox.checked = false;
            var record = this.gridData.getById(node.id);
            if (typeof record == "object")
                this.gridData.remove(record);
        }
    };
    Ext.onReady(function () {
        var treeCfg = {
            btnId: 'treeBtn',
            treeDataUrl: "smsSend.do?method=forward2Mobiles", treeRootId: '-1', treeRootText: '号码组', treeChkMode: '',
            showChkFldId: 'users', saveChkFldId: 'mobiles'
        };
        treeCfg.onLayout = function (cal, layout) {
            var config = {
                treeDataUrl: '${app}/xtree.do?method=userFromDept',
                treeRootId: '-1',
                treeRootText: '人员树',
                treeChkType: 'user'
            };
            layout.add('west', cal.newTreePanel(config));
        };
        treeCfg.onOutput = function (textList, dataList, jsonData) {
            var mobileArr = [], userArr = [];
            Ext.each(jsonData, function (item) {
                userArr.push(item.name);
                var temp = item.mobile.split(",");
                mobileArr = mobileArr.concat(temp);

            });
            if (this.showChkFldId) {

                $(this.showChkFldId).update(userArr.toString());
                //激活文本域的验证
                try {
                    $(this.showChkFldId).focus();
                    $(this.showChkFldId).blur();
                } catch (e) {
                }
            }
            if (this.saveChkFldId) {
                $(this.saveChkFldId).update(mobileArr.toString());
            }
        };
        var tree = new xbox(treeCfg);

    });
    //-->
</script>
<!--根据给定的实例名生成标题 -->
<title><fmt:message key="smsContentTemplateDetail.title"/></title>
<!-- <content tag="heading"><fmt:message key="smsContentTemplateDetail.heading"/></content> -->


<html:form action="/smsSend.do?method=xsave" method="post" styleId="smsSendForm">
    <table class="formTable">
        <caption><bean:message key='smsTitle.newContent'/></caption>
        <tr>
            <td class="label">
                <bean:message key='smsSend.code'/>
            </td>
            <td class="content max">
                <html:textarea property="mobiles" styleId="mobiles" cols="90" rows="5"></html:textarea>
                <input type="button" id="treeBtn" value="选择群组"/>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message key='smsSend.user'/>
            </td>
            <td class="content max">
                <html:textarea property="users" styleId="users" cols="90" rows="5"></html:textarea>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message key='smsSend.remark'/>
            </td>
            <td class="content max">
                <html:textarea property="remark" cols="90" rows="5"></html:textarea>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message key='smsSend.content'/>
            </td>
            <td class="content max">
                <html:textarea property="content" cols="90" rows="5"></html:textarea>
                <a href="#" onclick="showContent()"><bean:message key='smsSend.content'/></a>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message key='smsSend.contentRemark'/>
            </td>
            <td class="content max">
                <html:textarea property="contentRemark" cols="90" rows="5"></html:textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <html:submit property="save"><bean:message key="smsButton.send"/></html:submit>
            </td>
        </tr>
    </table>
</html:form>
<%@ include file="/common/footer_eoms.jsp" %>