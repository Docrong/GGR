<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="com.boco.eoms.base.util.StaticMethod" %>

<%

    String before = StaticMethod.getCurrentTimeString(-5);
    String current = StaticMethod.getCurrentTimeString(2);
    String findListForward = "";
    String before1 = StaticMethod.getLocalString(-1);
    String beforedate = before1.substring(0, 10);
%>
<script type="text/javascript">
    var userTreeAction = '${app}/xtree.do?method=dept';//部门树
    var userByDeptTree = '${app}/xtree.do?method=userFromDept';//部门用户树
    var areaTree = '${app}/xtree.do?method=areaTree'; //地域树

    var v;
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: "StSubscriptionForm"});


        //选择地域对话框
        areaTree = new xbox({
            btnId: 'areaTreeBtn',
            dlgId: 'dlg-area',
            treeDataUrl: areaTree,
            treeRootId: '-1',
            treeRootText: '${eoms:a2u("区域")}',
            treeChkMode: '',
            treeChkType: 'area',
            showChkFldId: 'areaName',
            saveChkFldId: 'areaid'
        });

        //用户树
        userByDeptTree = new xbox({
            btnId: 'userByDeptTreeBtn',
            dlgId: 'dlg-user',
            treeDataUrl: userByDeptTree,
            treeRootId: '-1',
            treeRootText: '${eoms:a2u("人员树")}',
            treeChkMode: '',
            treeChkType: 'user',
            showChkFldId: 'userByDeptName',
            saveChkFldId: 'userByDeptid'
        });

        //部门小角色树
        var flowRoleSubroleTree = "${app}/sheet/workflow/workflow.do?method=getAllWorkflow";
        var cfg = {
            btnId: 'subroleFromDeptBtn',
            treeDataUrl: flowRoleSubroleTree,
            treeRootId: '-1',
            treeRootText: '${eoms:a2u("流程角色树")}',
            treeChkMode: '',
            treeChkType: 'subrole',
            showChkFldId: 'subroleFromDeptName',
            saveChkFldId: 'subroleFromDeptid',
            baseAttrs: {
                loader: new Ext.tree.TreeLoader({
                    dataUrl: eoms.appPath + '/xtree.do?method=flowRoleSubrole',
                    listeners: {
                        beforeload: function (loader, node, callback) {
                            loader.baseParams['nodeType'] = node.attributes.nodeType || '';
                        }
                    }
                })
            }
        }

        var frTree = new xbox(cfg);
        frTree.defaultTree.loader.applyLoader = false;

    })


    function displayTR(sel) {

        // begin
        if (sel.value == "commonfault-onetimepass-KPI2_oracle") {  <% findListForward="onetimepass-statisticsheetlist-kpi2";  %>
            eoms.form.disableArea("td4", true);

            eoms.form.enableArea("td1");
            eoms.form.enableArea("td2");
            eoms.form.enableArea("td3");

        } else if (sel.value == "commonfault_delay_KPI3_oracle") {  <% findListForward="commonfault_delay_KPI3list";  %>
            eoms.form.disableArea("td4", true);

            eoms.form.enableArea("td1");
            eoms.form.enableArea("td2");
            eoms.form.enableArea("td3");
        } else if (sel.value == "commonfault_intime_KPI1_oracle") { <% findListForward="intime_statisticsheetlist";  %>
            eoms.form.disableArea("td4", true);

            eoms.form.enableArea("td1");
            eoms.form.enableArea("td2");
            eoms.form.enableArea("td3");
        }



        // end


        else if (sel.value == 0) {
            eoms.form.disableArea("operateroletd", true);
            eoms.form.enableArea("operateusertd");
        } else if (sel.value == 1) {
            eoms.form.disableArea("operateusertd", true);
            eoms.form.enableArea("operateroletd");
        }
        return;
    }


    function setDeptType(sel) {

        if (sel.value == "revdept") {//部门
            eoms.$("reportIndex").value = 0;

            eoms.form.disableArea("selrevrole", true);
            eoms.form.disableArea("selrevperson", true);
            eoms.form.enableArea("selrevdept");

        } else if (sel.value == "revrole") {//角色
            eoms.$("reportIndex").value = 1;

            eoms.form.disableArea("selrevdept", true);
            eoms.form.disableArea("selrevperson", true);
            eoms.form.enableArea("selrevrole");
        } else {//人
            eoms.$("reportIndex").value = 2;

            eoms.form.disableArea("selrevdept", true);
            eoms.form.disableArea("selrevrole", true);
            eoms.form.enableArea("selrevperson");
        }
    }


</script>

<title>custom
</title>


<html:form action="stSubscriptions.do?method=xsave" method="post"
           styleId="StSubscriptionForm">

    <input type="hidden" id="reportFromType" name="reportFromType" value="StatFrom"/>

    <table class="formTable middle" align="center">
        <tr>
            <td colspan="3" align="center">
                <bean:message key="stSubscriptionList.heading"/>
            </td>
        </tr>

        <tr class="tr_show">
            <td class="label" width="2%" nowrap="nowrap" align="center">
                <input type="radio" name="statMode" value="5" checked>
            </td>
            <td width="13%">
                    ${eoms:a2u('年报统计')}
            </td>
            <td width="85%">
                    ${eoms:a2u('统计时间段：从上年1号0点到上年末23点59分，报表呈现的时间是本年1号')}
            </td>
        </tr>
        <tr class="tr_show">
            <td class="label" width="2%" nowrap="nowrap" align="center">
                <input type="radio" name="statMode" value="6">
            </td>
            <td width="13%">
                    ${eoms:a2u('季报统计')}
            </td>
            <td width="85%">
                    ${eoms:a2u('统计选择起始时间:')}
                <select name="seasonSelectFrom" style="width: 2.0cm;">
                    <option value="1">${eoms:a2u("1号")}</option>
                    <option value="2">${eoms:a2u("2号")}</option>
                    <option value="3">${eoms:a2u("3号")}</option>
                    <option value="4">${eoms:a2u("4号")}</option>
                    <option value="5">${eoms:a2u("5号")}</option>
                    <option value="6">${eoms:a2u("6号")}</option>
                    <option value="7">${eoms:a2u("7号")}</option>
                    <option value="8">${eoms:a2u("8号")}</option>
                    <option value="9">${eoms:a2u("9号")}</option>
                    <option value="10">${eoms:a2u("10号")}</option>
                    <option value="11">${eoms:a2u("11号")}</option>
                    <option value="12">${eoms:a2u("12号")}</option>
                    <option value="13">${eoms:a2u("13号")}</option>
                    <option value="14">${eoms:a2u("14号")}</option>
                    <option value="15">${eoms:a2u("15号")}</option>
                    <option value="16">${eoms:a2u("16号")}</option>
                    <option value="17">${eoms:a2u("17号")}</option>
                    <option value="18">${eoms:a2u("18号")}</option>
                    <option value="19">${eoms:a2u("19号")}</option>
                    <option value="20">${eoms:a2u("20号")}</option>
                    <option value="21">${eoms:a2u("21号")}</option>
                    <option value="22">${eoms:a2u("22号")}</option>
                    <option value="23">${eoms:a2u("23号")}</option>
                    <option value="24">${eoms:a2u("24号")}</option>
                    <option value="25">${eoms:a2u("25号")}</option>
                    <option value="26">${eoms:a2u("26号")}</option>
                    <option value="27">${eoms:a2u("27号")}</option>
                    <option value="28">${eoms:a2u("28号")}</option>
                </select>
                    ${eoms:a2u('以此为周期')}
            </td>
        </tr>
        <tr class="tr_show">
            <td class="label" width="2%" nowrap="nowrap" align="center">
                <input type="radio" name="statMode" value="2">
            </td>
            <td width="13%">
                    ${eoms:a2u('月报统计')}
            </td>
            <td width="85%">
                    ${eoms:a2u('统计选择起始时间:')}
                <select name="monthSelectFrom" style="width: 2.0cm;">
                    <option value="1">${eoms:a2u("1号")}</option>
                    <option value="2">${eoms:a2u("2号")}</option>
                    <option value="3">${eoms:a2u("3号")}</option>
                    <option value="4">${eoms:a2u("4号")}</option>
                    <option value="5">${eoms:a2u("5号")}</option>
                    <option value="6">${eoms:a2u("6号")}</option>
                    <option value="7">${eoms:a2u("7号")}</option>
                    <option value="8">${eoms:a2u("8号")}</option>
                    <option value="9">${eoms:a2u("9号")}</option>
                    <option value="10">${eoms:a2u("10号")}</option>
                    <option value="11">${eoms:a2u("11号")}</option>
                    <option value="12">${eoms:a2u("12号")}</option>
                    <option value="13">${eoms:a2u("13号")}</option>
                    <option value="14">${eoms:a2u("14号")}</option>
                    <option value="15">${eoms:a2u("15号")}</option>
                    <option value="16">${eoms:a2u("16号")}</option>
                    <option value="17">${eoms:a2u("17号")}</option>
                    <option value="18">${eoms:a2u("18号")}</option>
                    <option value="19">${eoms:a2u("19号")}</option>
                    <option value="20">${eoms:a2u("20号")}</option>
                    <option value="21">${eoms:a2u("21号")}</option>
                    <option value="22">${eoms:a2u("22号")}</option>
                    <option value="23">${eoms:a2u("23号")}</option>
                    <option value="24">${eoms:a2u("24号")}</option>
                    <option value="25">${eoms:a2u("25号")}</option>
                    <option value="26">${eoms:a2u("26号")}</option>
                    <option value="27">${eoms:a2u("27号")}</option>
                    <option value="28">${eoms:a2u("28号")}</option>
                </select>
                    ${eoms:a2u('以此为周期')}
            </td>
        </tr>
        <tr class="tr_show">
            <td class="label" width="2%" nowrap="nowrap" align="center">
                <input type="radio" name="statMode" value="1">
            </td>
            <td noWrap width="13%">
                    ${eoms:a2u('周报统计')}
            </td>
            <td width="85%">
                    ${eoms:a2u('统计选择起始时间:')}
                <select name="weekSelectFrom" style="width: 2.0cm;">
                    <option value="1">${eoms:a2u("周一")}</option>
                    <option value="2">${eoms:a2u("周二")}</option>
                    <option value="3">${eoms:a2u("周三")}</option>
                    <option value="4">${eoms:a2u("周四")}</option>
                    <option value="5">${eoms:a2u("周五")}</option>
                    <option value="6">${eoms:a2u("周六")}</option>
                    <option value="7">${eoms:a2u("周日")}</option>
                </select>
                    ${eoms:a2u('以此为周期')}
            </td>
        </tr>
        <tr class="tr_show">
            <td class="label" width="2%" nowrap="nowrap" align="center">
                <html:radio property="statMode" value="4"/>
            </td>
            <td noWrap width="13%">
                    ${eoms:a2u('日报统计')}
            </td>
            <td noWrap width="85%">
                    ${eoms:a2u('统计时间段：从昨天0点到昨天23点59分，报表呈现的时间是今日')}
            </td>
        </tr>
        <tr class="tr_show">
            <td class="label" width="2%" nowrap="nowrap" align="center">
                <html:radio property="statMode" value="3"/>
            </td>
            <td noWrap width="13%">
                    ${eoms:a2u('时间统计')}
            </td>
            <td noWrap width="85%">
                    ${eoms:a2u('从')}
                <input type="text" name="statfromdate" id="statfromdate" value="" readonly="readonly"
                       onclick="popUpCalendar(this, this,null,null,null,true,-1);" class="text"/>
                    ${eoms:a2u('到')}
                <input type="text" name="stattodate" id="stattodate" value="" readonly="readonly"
                       onclick="popUpCalendar(this, this,null,null,null,true,-1);" class="text"/>
            </td>
        </tr>


        <!-- 下面可以添加需要的统计   -->
        <tr class="tr_show">
            <td class="label" width="2%" nowrap="nowrap" align="center">
            <td noWrap width="13%">
                    ${eoms:a2u('统计类别')}
            </td>

            <td noWrap width="85%">&nbsp;&nbsp;&nbsp;
                <select name="item" onchange="displayTR(this)" style="width: 6.2cm;">
                    <option value="commonfault_T_resolve_KPI4_oracle" selected="true">${eoms:a2u("故障工单_解决率")}</option>
                    <option value="commonfault-onetimepass-KPI2_oracle">${eoms:a2u("故障工单_一次处理完成率统计")}</option>
                    <option value="commonfault_delay_KPI3_oracle">${eoms:a2u("故障工单_延期解决率统计")}</option>
                    <option value="commonfault_intime_KPI1_oracle">${eoms:a2u("故障工单_故障处理及时率统计")}</option>


                </select>
            </td>
        </tr>
        <!-- 下面是id=td1...要什么数据    -->
        <tr id="td1" class="tr_show">
            <td class="label" width="2%" nowrap="nowrap" align="center">
            <td noWrap width="13%">
                <select name="reportIndex" onchange="displayTR(this)">
                    <option value="0" selected="true">${eoms:a2u("按处理人")}</option>
                    <option value="1">${eoms:a2u("按处理角色")}</option>
                </select>
            </td>
            <td id=operateusertd>&nbsp;&nbsp;&nbsp;
                <input type="button" value="${eoms:a2u('部门人员选择')}" id="userByDeptTreeBtn" class="btn"/>
                <input type="text" name="userByDeptName" value="" id="userByDeptName" class="text"/>
                <input type="hidden" id="userByDeptid" value="" name="userByDeptid"/>
            </td>
            <td id=operateroletd style="display:none;">&nbsp;&nbsp;&nbsp;
                <input type="button" value="${eoms:a2u('部门角色选择')}" id="subroleFromDeptBtn" class="btn"/>
                <input type="text" name="subroleFromDeptName" value="" id="subroleFromDeptName" class="text"/>
                <input type="hidden" id="subroleFromDeptid" value="" name="subroleFromDeptid"/>
            </td>
        </tr>


        <tr id="td2" class="tr_show">
            <td class="label" width="2%" nowrap="nowrap" align="center">
            <td noWrap width="13%">${eoms:a2u('选择地域')}</td>
            <td noWrap width="85%">&nbsp;&nbsp;&nbsp;
                <input type="button" value="${eoms:a2u('地域选择')}" id="areaTreeBtn" class="btn"/>
                <input type="text" name="areaName" value="" id="areaName" class="text" readonly="readonly"/>
                <input type="hidden" id="areaid" value="" name="areaid"/>
            </td>
        </tr>
        <tr id="td3" class="tr_show">
            <td class="label" width="2%" nowrap="nowrap" align="center">
            <td noWrap width="13%"> ${eoms:a2u('投诉分类')}</td>
            <td noWrap width="85%">&nbsp;&nbsp;&nbsp;
                <eoms:comboBox name="${sheetPageName}mainNetSortOne" id="${sheetPageName}mainNetSortOne"
                               sub="" initDicId="1010104" defaultValue="${sheetMain.mainNetSortOne}"/>
            </td>
        </tr>


        <input type="hidden" id="findListForward" name="findListForward" value="<%=findListForward %>">

        <tr>
            <td colspan="3" align="center">
                <html:submit styleClass="button" property="method.save">
                    ${eoms:a2u('定制')}
                </html:submit>
                &nbsp;&nbsp;
            </td>
        </tr>
    </table>
    <br>
    <br>
    <br>
    <br>
    <br>

    <table class="formTable middle" align="center">
        <tr>
            <td colspan="3" align="center">
                    ${eoms:a2u('说明：定制统计和及时统计条件选择和结果呈现都一致；建议对性能要求比较高的KPI使用定制统计。')}
            </td>
        </tr>

    </table>

</html:form>
<%@ include file="/common/footer_eoms.jsp" %>
