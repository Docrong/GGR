<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>


<form method="post" id="theform" action="complaintauto.do?method=save">
    <table class="formTable">
        <caption>自动流转T2配置</caption>

        <tr>
            <td class="label"><bean:message bundle="complaint" key="complaint.complaintType1"/> *</td>
            <td>
                <eoms:comboBox name="complaintType1" id="complaintType1" sub="complaintType2" initDicId="1010601"
                               alt="allowBlank:true" defaultValue="${complaintAutoT2.complaintType1}"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="complaint" key="complaint.complaintType2"/></td>
            <td>
                <eoms:comboBox name="complaintType2" id="complaintType2" sub="complaintType3"
                               initDicId="${complaintAutoT2.complaintType1}"
                               defaultValue="${complaintAutoT2.complaintType2}"/>
            </td>
        </tr>


        <tr>
            <td class="label"><bean:message bundle="complaint" key="complaint.complaintType"/></td>
            <td>
                <eoms:comboBox name="complaintType3" id="complaintType3" sub="complaintType4"
                               initDicId="${complaintAutoT2.complaintType2}"
                               defaultValue="${complaintAutoT2.complaintType3}"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="complaint" key="complaint.complaintType4"/></td>
            <td>
                <eoms:comboBox name="complaintType4" id="complaintType4" sub="complaintType5"
                               initDicId="${complaintAutoT2.complaintType3}"
                               defaultValue="${complaintAutoT2.complaintType4}"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="complaint" key="complaint.complaintType5"/></td>
            <td>
                <eoms:comboBox name="complaintType5" id="complaintType5" sub="complaintType6"
                               initDicId="${complaintAutoT2.complaintType4}"
                               defaultValue="${complaintAutoT2.complaintType5}"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="complaint" key="complaint.complaintType6"/></td>
            <td>
                <eoms:comboBox name="complaintType6" id="complaintType6" sub="complaintType7"
                               initDicId="${complaintAutoT2.complaintType5}"
                               defaultValue="${complaintAutoT2.complaintType6}"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="complaint" key="complaint.complaintType7"/></td>
            <td>
                <eoms:comboBox name="complaintType7" id="complaintType7" initDicId="${complaintAutoT2.complaintType6}"
                               defaultValue="${complaintAutoT2.complaintType7}"/>
            </td>
        </tr>

        <!-- T1受理角色 -->
        <tr>
            <td class="label">
                T1受理角色 *
            </td>
            <td class="content">
                <eoms:id2nameDB id="${complaintAutoT2.autoAcceptRoleT1}" beanId="tawSystemSubRoleDao"/>
                <br/>
                <select id="sendsel" ${type == 'open' ? 'disabled="disabled"': ''}>
                    <option value=""><bean:message bundle="sheet" key="query.status.pleaseSelect"/></option>
                    <option value="role"><bean:message bundle="sheet" key="query.status.role"/></option>
                </select>
                <div id="viewer" class="viewer-list"></div>
                <input type="button" name="showSendRole"
                       value="<bean:message bundle="sheet" key="query.status.selectRole"/>" class="btn"
                       id="showSendRole">
                <input type="hidden" name="autoAcceptRoleT1" id="toSendRoleId"
                       value="${complaintAutoT2.autoAcceptRoleT1}"/>


            </td>
        </tr>
        <!-- T1受理对象 -->
        <tr>
            <td class="label">
                T1受理人员 *
            </td>
            <td class="content">
                <eoms:id2nameDB id="${complaintAutoT2.remark1}" beanId="tawSystemUserDao"/>
                <br/>
                <select id="usersel" ${type == 'open' ? 'disabled="disabled"': ''}>
                    <option value=""><bean:message bundle="sheet" key="query.status.pleaseSelect"/></option>
                    <option value="user"><bean:message bundle="sheet" key="query.status.user"/></option>
                </select>
                <div id="viewer-user" class="viewer-list"></div>
                <input type="button" name="showSendUser"
                       value="<bean:message bundle="sheet" key="query.status.selectUser"/>" class="btn"
                       id="showSendUser">
                <input type="hidden" name="remark1" id="toSendUserId" value="${complaintAutoT2.remark1}"/>
            </td>
        </tr>
        <!-- 工单处理对象 -->
        <tr>
            <td class="label">
                T2处理角色 *
            </td>
            <td class="content">
                <eoms:id2nameDB id="${complaintAutoT2.dealRoleT2}" beanId="tawSystemSubRoleDao"/>
                <br/>
                <select id="dealsel" ${type == 'open' ? 'disabled="disabled"': ''}>
                    <option value=""><bean:message bundle="sheet" key="query.status.pleaseSelect"/></option>
                    <option value="role"><bean:message bundle="sheet" key="query.status.role"/></option>
                </select>
                <div id="viewer-deal" class="viewer-list"></div>
                <input type="hidden" name="dealRoleT2" id="toDealRoleId" value="${complaintAutoT2.dealRoleT2}"/>
                <input type="button" name="showDealRole"
                       value="<bean:message bundle="sheet" key="query.status.selectRole"/>" class="btn"
                       id="showDealRole">

            </td>
        </tr>
        <tr>
            <td class="label">故障地区 *：</td>
            <td width="100%">
                <select id="faultSite" name="faultSite" ${type == 'open' ? 'disabled="disabled"': ''}>
                    <option value="${complaintAutoT2.faultSite}">${complaintAutoT2.faultSite}</option>
                    <option value="HB.WH">武汉</option>
                    <option value="HB.YC">宜昌</option>
                    <option value="HB.ES">恩施</option>
                    <option value="HB.JZ">荆州</option>
                    <option value="HB.JH">江汉</option>
                    <option value="HB.JM">荆门</option>
                    <option value="HB.HS">黄石</option>
                    <option value="HB.EZ">鄂州</option>
                    <option value="HB.XN">咸宁</option>
                    <option value="HB.HG">黄冈</option>
                    <option value="HB.XF">襄樊</option>
                    <option value="HB.SZ">随州</option>
                    <option value="HB.SY">十堰</option>
                    <option value="HB.XG">孝感</option>
                </select>
            </td>
        </tr>
        <tr>
            <td class="label">规则类型</td>
            <td>
                <select id="remark2" name="remark2" ${type == 'open' ? 'disabled="disabled"': ''}>
                    <option value="${complaintAutoT2.remark2}">${complaintAutoT2.remark2}</option>
                    <option value="autoT2">自动移交T2</option>
                    <option value="autoHold">自动质检归档</option>
                </select>
            </td>
        </tr>
        <tr>
            <td class="label">功能开/关：</td>
            <td width="100%">
                <select name="colseSwitch" ${type == 'open' ? 'disabled="disabled"': ''}>

                    <option value="yes" ${complaintAutoT2.colseSwitch == 'yes' ? 'selected' : ''}>开</option>
                    <option value="no" ${complaintAutoT2.colseSwitch == 'no' ? 'selected' : ''}>关</option>
                </select>
            </td>
        </tr>
        <logic:notEqual name="type" value="open">
            <tr>
                <td colspan="2">
                    <input type="hidden" name="id" value="${complaintAutoT2.id}">
                    <input type="submit" value="提交" class="btn">
                </td>
            </tr>
        </logic:notEqual>
        <logic:equal name="type" value="open">
            <tr>
                <td colspan="2">
                    <input type="button" value="编辑" class="btn" onclick="toedit();">
                    <input type="button" value="删除" class="btn" onclick="todelete();">
                </td>
            </tr>
        </logic:equal>

    </table>
    <%@ include file="gscommonqueryJs.jsp" %>
</form>
<script type="text/javascript">


    function tosubmit(e) {
        var complaintType1 = document.getElementById("complaintType1");
        var faultSite = document.getElementById("faultSite");
        var acceptRoleId = document.getElementById("toSendRoleId");
        var dealRoleId = document.getElementById("toDealRoleId");
        var userId = document.getElementById("toSendUserId");
        if (complaintType1.value == "") {
            alert("请选择网络分类");
            return false;
        }

        if (acceptRoleId.value == "") {
            alert("请选择T1受理角色");
            return false;
        }
        if (userId.value == "") {
            alert("请选择T1受理人员");
            return false;
        }
        if (dealRoleId.value == "") {
            alert("请选择T2处理角色");
            return false;
        }
        if (faultSite.value == "") {
            alert("请选择故障地区");
            return false;
        }
        var form = document.getElementById("theform");
        var ajaxForm = Ext.getDom(form);
        e = e || window.event;
        eoms.stopEvent(e);
        try {
            Ext.Ajax.request({
                form: ajaxForm,
                method: "post",
                url: "complaintauto.do?method=checkRepeateRecord",
                success: handleResponse,
                failure: function () {
                    alert("fail");
                }
            });
        } catch (e) {
            alert(e.message);
        }

        function handleResponse(x) {
            var o = eoms.JSONDecode(x.responseText);
            if (o.number == 0) {
                form.submit();
            } else {
                alert("已经有此流转配置规则，请重新配置！");
            }
        }
    }

    eoms.$("theform").onsubmit = tosubmit;

    function toedit() {
        location.href = "./complaintauto.do?method=edit&type=edit&id=${complaintAutoT2.id}";
    }

    function todelete() {
        location.href = "./complaintauto.do?method=remove&id=${complaintAutoT2.id}";
    }
</script>