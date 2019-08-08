<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<script type="text/javascript">
    function queding() {
        var linkSendRes = document.getElementById("linkSendRes").value;
        var linkSendOtherName = document.getElementById("linkSendOtherName").value;
        var linkSendOtherTel = document.getElementById("linkSendOtherTel").value;


        if (linkSendRes == '') {
            alert("转派理由 不能为空");
            return false;
        }

        if (linkSendOtherName == '') {
            alert("已联系待转派单位班组维护人员姓名 不能为空");
            return false;
        }

        if (linkSendOtherTel == '') {
            alert("已联系待转派单位班组维护人员电话 不能为空");
            return false;
        }

        var dealPerformer = document.getElementById("dealPerformer").value;

        if (dealPerformer == '') {
            alert("T2处理 不能为空");
            return false;
        }

        var tag1 = document.getElementsByTagName("*");
        var dealPerformerName = '';
        for (var i = 0; i < tag1.length; i++) {
            if (tag1[i].className == 'viewlistitem-subrole') {
                dealPerformerName = tag1[i].innerHTML;
                break;
            }
        }

        window.opener.document.getElementById("linkSendRes").value = linkSendRes;
        window.opener.document.getElementById("linkSendOtherName").value = linkSendOtherName;
        window.opener.document.getElementById("linkSendOtherTel").value = linkSendOtherTel;

        //这里只是将班组名称传到父页面，次工单驳回给T1
        //window.opener.document.getElementById("dealPerformerLeader1").value = dealPerformer;
        //window.opener.document.getElementById("dealPerformer1").value = dealPerformer;
        //window.opener.document.getElementById("dealPerformerType1").value = "subrole";
        window.opener.document.getElementById("subRoleName").innerHTML = dealPerformerName;
        window.opener.document.getElementById("mainIsOtherFlag").value = "1";

        window.opener.document.getElementById("mainOtherSubrole").value = dealPerformer;
        window.opener.document.getElementById("mainThisSubrole").value = '${operateRoleId}';
        window.opener.document.getElementById("mainIsOther").value = "1";//表示选择了其他班组，次数工单派往T1，并对次工单打标

        //清空掉原来说选择的班组
        window.opener.document.getElementById("dealPerformer1").value = "";

        window.close();

    }


</script>


<table class="formTable">

    <tr>
        <td class="label">
            转派理由*
        </td>
        <td colspan="3">
            <textarea name="linkSendRes" class="textarea max" id="linkSendRes">${linkSendRes}</textarea>
        </td>
    </tr>

    <tr>
        <td class="label">
            已联系待转派单位班组维护人员姓名*
        </td>
        <td>
            <input type="text" class="text" name="linkSendOtherName" id="linkSendOtherName"
                   value="${linkSendOtherName}"/>
        </td>
        <td class="label">
            已联系待转派单位班组维护人员电话*
        </td>
        <td>
            <input type="text" class="text" name="linkSendOtherTel" id="linkSendOtherTel" value="${linkSendOtherTel}"/>
        </td>
    </tr>


</table>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>：
        <span id="roleName">
		 	 T2处理
		 </span>
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="sendObject" type="role" roleId="8005106" flowId="51"
                      config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'}]"
                      data="${sendObject}"/>
    </div>
</fieldset>

<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="queding()">
    确定
</html:button>


<%@ include file="/common/footer_eoms.jsp" %>