<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page
        import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>


<script language="javascript">
    Ext.onReady(function () {
        var saveflag = document.getElementById("saveflag").value;
        if (saveflag == 'ok') {
            window.close();

        }
    });


    function SubmitCheck() {
        frmReg = document.forms[0];
        var errorDesc = document.getElementById("errorDesc").value;
        if (errorDesc == '') {
            alert("问题描述不能为空");
            return false;
        }
        var errorSource = document.getElementById("errorSource").value;
        if (errorSource == '') {
            alert("问题来源不能为空");
            return false;
        }
        var toDeptId = document.getElementById("toDeptId").value;
        if (toDeptId == '') {
            alert("请选择地市");
            //return false;
        }

        frmReg.submit();//提交
    }


</script>

<form name="addform" method="post"
      action="../supervisetask/supervisetask.do?method=supervisetaskBoardMainAddSave">

    <table width="500" class="formTable">
        <caption>主体责任单新增</caption>


        <%--
        --%>
        <input type="hidden" id="saveflag" name="saveflag" value="${saveflag}">
        <!--<td>
	<tr>
			专业*
	    </td>
		<td>
	    	<eoms:comboBox name="major" id="major" initDicId="1010107" defaultValue="" onchange="showNoticeUserId();">
	    		</eoms:comboBox>
	    </td>
	</tr>
		
	-->

        <!--<tr>
		<td class="label">网络分类一级*</td>
		<td><eoms:comboBox name="mainNetSortOne" id="mainNetSortOne"
			sub="mainNetSortTwo" initDicId="1010104" /></td>
		<td class="label">网络分类二级</td>
		<td><eoms:comboBox name="mainNetSortTwo" id="mainNetSortTwo"
			sub="mainNetSortThree" /></td>
	</tr>
	<tr>
		<td class="label">网络分类三级</td>
		<td><eoms:comboBox name="mainNetSortThree" id="mainNetSortThree" />
		</td>
	</tr>
		

	-->
        <tr>
            <td class="label">月份</td>
            <td class="content">
                <input type="text" style="border:none" readonly="readonly" id="month" name="month" value="${month }">
            </td>
            <td class="label">生产任务类型</td>
            <td class="content">
                <input type="hidden" style="border:none" readonly="readonly" id="workflowType" name="workflowType"
                       value="${workflowType }">
                <c:choose>
                    <c:when test="${workflowType=='listedregulation' }">摘挂牌工单</c:when>
                    <c:when test="${workflowType=='commonfault'}">故障工单</c:when>
                    <c:otherwise>1</c:otherwise>
                </c:choose>
            </td>

        </tr>
        <tr>
            <td class="label">工单号</td>
            <td class="content">
                <input type="text" style="border:none" readonly="readonly" id="sheetId" name="sheetId"
                       value="${object.sheetId }">
            </td>
            <td class="label">创建时间</td>
            <td class="content"><input type="text" style="border:none" readonly="readonly" id="createtime"
                                       name="createtime" value="${createtime }"></td>
        </tr>
        <tr>
            <td class="label">地市*</td>
            <td id="tdToDeptId"><select id="toDeptId" name="toDeptId"
                                        onchange="">

            </select></td>
            <%--
            <td class="label">区县</td>
            <td id="tdMainFaultGenerantCitySubCode"><select
                id="mainFaultGenerantCitySubCode" name="mainFaultGenerantCitySubCode" onchange="showNoticeUserId();">

            </select></td>
            --%>
        </tr>
        <tr>
            <td class="label">工单状态</td>
            <td class="content">
                <input type="hidden" style="border:none" readonly="readonly" id="workflowStatus" name="workflowStatus"
                       value="${object.status }">
                <eoms:dict key="dict-sheet-common" dictId="sheetStatus" itemId="${object.status}" beanId="id2nameXML"/>

            </td>
            <td class="label">问题来源</td>
            <td class="content">
                <input type="text" style="width:100%" id="errorSource" name="errorSource" value="">
            </td>
        </tr>

        <tr>
            <td class="label">派发部门</td>
            <td class="content">
                <input type="hidden" style="border:none" readonly="readonly" id="deptid" name="deptid"
                       value="${deptid }"><eoms:id2nameDB id="${deptid }" beanId="tawSystemDeptDao"/>
            </td>
            <td class="label">问题派发人</td>
            <td class="content">
                <input type="hidden" style="border:none" readonly="readonly" id="sendUserid" name="sendUserid"
                       value="${userid }">
                <eoms:id2nameDB id="${userid }" beanId="tawSystemUserDao"/>

            </td>
        </tr>
        <tr>
            <td class="label">问题描述</td>
            <td class="content">
                <textarea style="width: 100%" id="errorDesc" name="errorDesc"></textarea>
            </td>
        </tr>
        <tr>
            <td class="label">成效评估目标</td>
            <td class="content">
                <textarea style="width: 100%" id="expectGoal" name="expectGoal"></textarea>
            </td>
        </tr>


    </table>


    </table>
    <input type="button" value="保存" Class="button" onclick="SubmitCheck();">


</form>

<%@ include file="/common/footer_eoms.jsp" %>
