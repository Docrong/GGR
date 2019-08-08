<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%

    request.setAttribute("roleId", "1940");


    long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
%>
<%@ include file="/WEB-INF/pages/wfworksheet/circuitcontrol/baseinputmainhtmlnew.jsp" %>
<script type="text/javascript">
    //selectLimit();
    function selectLimit() {
        Ext.Ajax.request({
            method: "get",
            url: "circuitcontrol.do?method=newShowLimit&flowName=CircuitControl",
            success: function (x) {
                var o = eoms.JSONDecode(x.responseText);
                if ((o.acceptLimit == null || o.acceptLimit == "") && (o.replyLimit == null || o.replyLimit == "")) {
                    // $("sheetAcceptLimit").value = "";
                    // $('sheetCompleteLimit').value = "";
                } else {
                    var times =<%=localTimes%>;
                    var dt1 = new Date().add(Date.MINUTE, parseInt(o.acceptLimit, 10));
                    var dt2 = dt1.add(Date.MINUTE, parseInt(o.replyLimit, 10));
                    $("sheetAcceptLimit").value = dt1.format('Y-m-d H:i:s');
                    $('sheetCompleteLimit').value = dt2.format('Y-m-d H:i:s');
                }
            }
        });
    }
</script>
<input type="hidden" name="processTemplateName" value="CircuitControl"/>
<input type="hidden" name="operateName" value="newWorkSheet"/>
<c:if test="${status!=1}">


    <input type="hidden" name="phaseId" id="phaseId" value="FirstApproval"/>

    <input type="hidden" id="operateType" name="operateType" value="0"/>
    <input type="hidden" name="gatherPhaseId" id="gatherPhaseId" value="HoldTask"/>
</c:if>
<c:if test="${status==1}">
    <input type="hidden" name="phaseId" id="phaseId" value="OverTask"/>
</c:if>
<input type="hidden" name="beanId" value="iCircuitControlMainManager"/>
<input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.circuitcontrol.model.CircuitControlMain"/>
<input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.circuitcontrol.model.CircuitControlLink"/>
<input type="hidden" value="" id="circuitName" name="circuitName"/>
<input type="hidden" value="" id="startName" name="startName"/>
<input type="hidden" value="" id="endName" name="endName"/>
<input type="hidden" value="" id="remarkRate" name="remarkRate"/>
<br>

<!-- 工单基本信息 -->
<table id="sheet" class="formTable">

    <tr>
        <td class="label">
            <!-- 申请单号 -->
            <bean:message bundle="circuitcontrol" key="circuitControlMain.applyNum"/>*
        </td>
        <td>
            <input type="text" class="text" name="applyNum" id="applyNum"
                   alt="allowBlank:false,maxLength:1000,vtext:'请填入 申请单号 信息，最多输入 1000 字符'"
                   value="${sheetMain.applyNum}"/>
        </td>
        <td class="label">
            <!-- 业务类型 -->
            <bean:message bundle="circuitcontrol" key="circuitControlMain.businessType"/>*
        </td>
        <td colspan="3">
            <eoms:comboBox name="businessType" id="businessType"
                           initDicId="1012102"
                           defaultValue="${sheetMain.businessType}" alt="allowBlank:false"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <bean:message bundle="circuitcontrol" key="circuitControlMain.circuitName"/>*
        </td>
        <td class="label">
            <bean:message bundle="circuitcontrol" key="circuitControlMain.startName"/>*
        </td>
        <td class="label">
            <bean:message bundle="circuitcontrol" key="circuitControlMain.endName"/>*
        </td>
        <td class="label">
            <bean:message bundle="circuitcontrol" key="circuitControlMain.remarkRate"/>*
        </td>
        <td class="label">
            <input name="Submit1" type="button" onclick="javascript:add1()" value="增加">
        </td>
    </tr>

    <tr>
        <td>
            <input type="text" class="text" name="proportionCircuitName"
                   alt="allowBlank:false,maxLength:4000,vtext:'请填入 电路名称 信息，最多输入 4000 字符'"/>
        </td>
        <td>
            <input type="text" class="text" name="proportionStartName"
                   alt="allowBlank:false,maxLength:4000,vtext:'请填入 起点站名 信息，最多输入 4000 字符'"/>
        </td>
        <td>
            <input type="text" class="text" name="proportionEndName"
                   alt="allowBlank:false,maxLength:4000,vtext:'请填入 终点站名 信息，最多输入 4000 字符'"/>
        </td>
        <td>
            <input type="text" class="text" name="proportionRemarkRate"
                   alt="allowBlank:false,maxLength:4000,vtext:'请填入 备注(速率) 信息，最多输入 4000 字符'"/>
        </td>
    </tr>

    <tr>
        <td>
            <div id="upid1"></div>
        </td>
        <td>
            <div id="upid2"></div>
        </td>
        <td>
            <div id="upid3"></div>
        </td>
        <td>
            <div id="upid4"></div>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 备注 -->
            <bean:message bundle="circuitcontrol" key="circuitControlMain.remark"/>*
        </td>
        <td colspan="3">
            <textarea name="remark" id="remark" class="textarea max"
                      alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入4000汉字'"
                      alt="width:'500px'">${sheetMain.remark}</textarea>
        </td>
    </tr>

</table>


<!-- 附件 -->
<table id="sheet" class="formTable">
    <!--附件模板-->
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
        </td>
        <td colspan="3">
            <eoms:attachment name="tawSheetAccess" property="accesss"
                             scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>
        </td>
    </tr>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="mainForm.accessories"/>
        </td>
        <td colspan="3">
            <eoms:attachment name="sheetMain" property="sheetAccessories"
                             scope="request" idField="sheetAccessories" appCode="circuitcontrol" alt="allowBlank:true"/>
        </td>
    </tr>
</table>


<!--派单树-->
<br/>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>：
        <span id="roleName">
		 	 有线科
		 </span>
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="sendObject" type="role" roleId="1941" flowId="619" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sheetMain.sendObject}"/>
    </div>
</fieldset>
<script type="text/javascript">
    function add1() {
        str = '<input   type="text" class="text"  name=proportionCircuitName />';
        //document.write(str);
        window.upid1.innerHTML += str + '</br>';
        str = '<input   type="text" class="text"  name=proportionStartName />';
        //document.write(str);
        window.upid2.innerHTML += str + '</br>';
        str = '<input   type="text" class="text"  name=proportionEndName />';
        //document.write(str);
        window.upid3.innerHTML += str + '</br>';
        str = '<input   type="text" class="text"  name=proportionRemarkRate />';
        //document.write(str);
        window.upid4.innerHTML += str + '</br>';
    }

    function integrated() {
        var proportionCircuitNameValue = '';
        var proportionStartNameValue = '';
        var proportionEndNameValue = '';
        var proportionRemarkRateValue = '';
        var proportionCircuitNameArray = document.getElementsByName("proportionCircuitName");
        var proportionStartNameArray = document.getElementsByName("proportionStartName");
        var proportionEndNameArray = document.getElementsByName("proportionEndName");
        var proportionRemarkRateArray = document.getElementsByName("proportionRemarkRate");

        for (var i = 0; i < proportionCircuitNameArray.length; i++) {
            //alert(proportionArray[i].value);
            if (proportionCircuitNameArray[i].value == "") {
                alert("电路名称不能为空");
                return false;
            } else {
                if (proportionCircuitNameValue == "") {
                    proportionCircuitNameValue = proportionCircuitNameArray[i].value;
                } else {
                    proportionCircuitNameValue = proportionCircuitNameValue + ',' + proportionCircuitNameArray[i].value;
                }
            }
        }
        var circuitName = document.getElementById("circuitName");
        circuitName.value = proportionCircuitNameValue;

        for (var i = 0; i < proportionStartNameArray.length; i++) {
            //alert(proportionArray[i].value);
            if (proportionStartNameArray[i].value == "") {
                alert("起点站名不能为空");
                return false;
            } else {
                if (proportionStartNameValue == "") {
                    proportionStartNameValue = proportionStartNameArray[i].value;
                } else {
                    proportionStartNameValue = proportionStartNameValue + ',' + proportionStartNameArray[i].value;
                }
            }
        }
        var startName = document.getElementById("startName");
        startName.value = proportionStartNameValue;

        for (var i = 0; i < proportionEndNameArray.length; i++) {
            //alert(proportionArray[i].value);
            if (proportionEndNameArray[i].value == "") {
                alert("终点站名不能为空");
                return false;
            } else {
                if (proportionEndNameValue == "") {
                    proportionEndNameValue = proportionEndNameArray[i].value;
                } else {
                    proportionEndNameValue = proportionEndNameValue + ',' + proportionEndNameArray[i].value;
                }
            }
        }
        var endName = document.getElementById("endName");
        endName.value = proportionEndNameValue;

        for (var i = 0; i < proportionRemarkRateArray.length; i++) {
            //alert(proportionArray[i].value);
            if (proportionRemarkRateArray[i].value == "") {
                alert("备注(速率)不能为空");
                return false;
            } else {
                if (proportionRemarkRateValue == "") {
                    proportionRemarkRateValue = proportionRemarkRateArray[i].value;
                } else {
                    proportionRemarkRateValue = proportionRemarkRateValue + ',' + proportionRemarkRateArray[i].value;
                }
            }
        }
        var remarkRate = document.getElementById("remarkRate");
        remarkRate.value = proportionRemarkRateValue;
    }
</script>