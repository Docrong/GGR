<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<html:form action="/serviceprepare.do?method=addServiceConfiguration" styleId="theform">
    <table class="formTable" id="table">
        <caption>
            <center>服务详细信息</center>
        </caption>
        <tr>
            <td class="label">服务名称*</td>
            <td class="content">
                <select name="processId" id="processId" onChange="selectFlowId(this.value)" alt="allowBlank:false">
                    <option value="">请选择</option>
                    <logic:iterate id="toProcessTypeList" name="ProcessTypeList"
                                   type="com.boco.eoms.businessupport.serviceprepare.model.ProcessType">
                        <option value="<bean:write name="toProcessTypeList" property="flowId"/>">
                            <bean:write name="toProcessTypeList" property="name"/>
                        </option>
                    </logic:iterate>
                </select>
            </td>
            <td class="label">环节名称*</td>
            <input type="hidden" name="isServiceConfigurationIdArray" id="isServiceConfigurationIdArray" value=""/>
            <td class="content">
                <select name="taskId" id="taskId" onChange="selectTaskId(this.value)" alt="allowBlank:false">
                    <option value="">请选择</option>
                </select>
            </td>
        </tr>
        <tr>
            <td class="label">服务规格名称*</td>
            <td class="content" colspan="3">
                <select name="businessId" id="businessId" alt="allowBlank:false">
                    <option value="">请选择</option>
                    <logic:iterate id="toProductSpecificationList" name="productSpecificationList"
                                   type="com.boco.eoms.businessupport.serviceprepare.model.ProductSpecification">
                        <option value="<bean:write name="toProductSpecificationList" property="id"/>">
                            <bean:write name="toProductSpecificationList" property="name"/>
                        </option>
                    </logic:iterate>
                </select>
            </td>
        </tr>
        <tr id="addrow">
            <td class="label">专业服务名称*</td>
            <input type="hidden" name="specialtyIdArray" id="specialtyIdArray" value=""/>
            <td class="content" colspan="3" id="addcheckbox">

            </td>
        </tr>
        <tr>
            <td class="label">是否必做任务*</td>
            <td class="content" colspan="3">
                <select name="isNeed" id="isNeed" alt="allowBlank:false">
                    <option value="">请选择</option>
                    <option value="0">是</option>
                    <option value="1">否</option>
                </select>
            </td>
        </tr>
        <tr>
            <td class="label">备注</td>
            <td class="content" colspan="3">
                <textarea class="textarea max" name="remark" id="remark"
                          alt="allowBlank:true,maxLength:255,vtext:'请填写备注，最大长度为255字符'"></textarea>
            </td>
        </tr>
    </table>
    <html:submit styleClass="btn" property="method.send" styleId="method.send">提交
    </html:submit>
</html:form>
<script type="text/javascript">
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'theform'});
        v.custom = function () {
            var col = document.getElementById("addcheckbox");
            if (col.innerHTML == "") {
                alert("该流程无相关服务提供，不允许添加服务器");
                return false;
            } else if (col.innerHTML != "") {
                var checkArray = document.getElementsByName("specialtyId");
                var i = 0;
                var tmpspecialtyIdArray = "";
                for (var c0 = 0; c0 < checkArray.length; c0++) {
                    if (checkArray[c0].checked) {
                        i = 1;
                        if (tmpspecialtyIdArray == "") {
                            tmpspecialtyIdArray = checkArray[c0].value;
                        } else {
                            tmpspecialtyIdArray = tmpspecialtyIdArray + "," + checkArray[c0].value;
                        }
                    }
                }
                if (i == 0) {
                    alert("请选择相关服务！");
                    return false;
                } else {
                    $('${sheetPageName}specialtyIdArray').value = tmpspecialtyIdArray;
                    return true;
                }
            }
        };
    });

    function selectFlowId(id) {
        var taskId = document.getElementById("taskId");
        var col = document.getElementById("addcheckbox");
        taskId.length = 0;
        col.innerHTML = "该流程没有相关服务提供";
        taskId.options.add(new Option("请选择", ""));
        if (id == "") {
            return;
        } else {
            Ext.Ajax.request({
                method: "get",
                url: "serviceprepare.do?method=getTaskLinksByFlowId&flowId=" + id + "",
                success: function (x) {
                    var json = eoms.JSONDecode(x.responseText);
                    var taskLinksList = json[0];
                    var professionalServiceDirectoryList = json[1];
                    for (var i = 0; i < taskLinksList.length; i++) {
                        var taskLinks_id = taskLinksList[i]['id'];
                        var taskLinks_chName = taskLinksList[i]['chName'];
                        taskId.options.add(new Option(taskLinks_chName, taskLinks_id));
                    }
                    var innerStr = "";
                    for (var k = 0; k < professionalServiceDirectoryList.length; k++) {
                        var professionalServiceDirectory_id = professionalServiceDirectoryList[k]['id'];
                        var professionalServiceDirectory_name = professionalServiceDirectoryList[k]['name'];
                        // 该方法也可以实现循环生成动态checkbox。
                        //var input=document.createElement("input");//创建input元素对象
                        //var attr=document.createAttribute("type");//创建一个type属性对象
                        //var name=document.createAttribute("name");//创建name属性
                        //input.setAttribute("type","checkbox");
                        //input.setAttribute("name","specialtyId");
                        //input.setAttribute("value",professionalServiceDirectory_id);
                        //input.setAttribute("id","specialtyId");
                        //col.appendChild(input);
                        //var txtValue=document.createTextNode(" "+professionalServiceDirectory_name);
                        //col.appendChild(txtValue);
                        //var br=document.createElement("br");
                        //col.appendChild(br);
                        var str = "<INPUT id=specialtyId  type=checkbox  name=specialtyId   value =" + professionalServiceDirectory_id + "> " + professionalServiceDirectory_name + " ";
                        innerStr = innerStr + str;
                        col.innerHTML = innerStr;
                    }

                }
            });
        }
    }

    function selectTaskId(id) {
        var processId = document.getElementById("processId");
        var flowId = processId.options[processId.selectedIndex].value;
        var col = document.getElementById("addcheckbox");
        $('${sheetPageName}isServiceConfigurationIdArray').value = "";
        var checkArray = null;
        if (col.innerHTML != "") {
            checkArray = document.getElementsByName("specialtyId");
            for (var c0 = 0; c0 < checkArray.length; c0++) {
                if (checkArray[c0].checked) {
                    checkArray[c0].checked = false;
                }
            }
        }
        if (id == "") {
            return;
        } else {
            Ext.Ajax.request({
                method: "get",
                url: "serviceprepare.do?method=getServiceConfigurationListByCondition&flowId=" + flowId + "&taskId=" + id + "",
                success: function (x) {
                    var json = eoms.JSONDecode(x.responseText);
                    var ServiceConfigurationList = json[0];
                    var tmpisServiceConfigurationIdArray = "";
                    for (var i = 0; i < ServiceConfigurationList.length; i++) {
                        var professionalServiceDirectory_id = ServiceConfigurationList[i]['professionalServiceDirectory_id'];
                        var serviceConfiguration_id = ServiceConfigurationList[i]['serviceConfiguration_id'];
                        for (var c0 = 0; c0 < checkArray.length; c0++) {
                            if (checkArray[c0].value == professionalServiceDirectory_id) {
                                checkArray[c0].checked = true;
                                if (tmpisServiceConfigurationIdArray == "") {
                                    tmpisServiceConfigurationIdArray = serviceConfiguration_id;
                                } else {
                                    tmpisServiceConfigurationIdArray = tmpisServiceConfigurationIdArray + "," + serviceConfiguration_id;
                                }
                            }
                        }
                    }
                    $('${sheetPageName}isServiceConfigurationIdArray').value = tmpisServiceConfigurationIdArray;
//        	alert($('${sheetPageName}isServiceConfigurationIdArray').value);
                }
            });
        }
    }
</script>
<%@ include file="/common/footer_eoms.jsp" %>