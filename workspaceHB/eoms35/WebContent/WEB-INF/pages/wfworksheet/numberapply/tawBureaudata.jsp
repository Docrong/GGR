<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="com.boco.eoms.commons.system.user.model.TawSystemUser" %>
<%@ page import="com.boco.eoms.sheet.numberapply.model.NumberApplyMain" %>
<%@ page import="com.boco.eoms.base.util.StaticMethod" %>
<%@ page import="com.boco.eoms.datum.model.TawBureaudataHlr" %>
<%@ page import="com.boco.eoms.partdata.model.TawPartFourteenSignal" %>
<%@ page import="com.boco.eoms.partdata.model.TawpartMscid" %>
<%@ page import="java.util.*" %>
<%
    String actionForword = (String) request.getAttribute("actionForword");
    NumberApplyMain numberApplyMain = (NumberApplyMain) request.getAttribute("numberApplyMain");
%>

<script type="text/javascript">
    var frmReg;
    Ext.onReady(function () {
        frmReg = new eoms.form.Validation({form: 'newFormPage'});
    });

    function onBack() {
        window.close();
    }

</script>

<form name="newFormPage" method="POST" id="newFormPage" action="numberapply.do?method=performManualSave">
    <table class="formTable">
        <input type="hidden" name="id" id="id" value="${id }"/><!-- 改条记录的主键id -->
        <input type="hidden" name="sheetKey" id="sheetKey" value="${sheetKey }"/><!-- 工单的主键id -->
        <input type="hidden" name="actionForword" id="actionForword" value="${actionForword }"/><!-- 跳转的标识 -->

        <logic:notEmpty name="TawBureaudataHlr">
            HLR24位信令点
            <display:table name="TawBureaudataHlr" cellspacing="0" cellpadding="0"
                           id="TawBureaudataHlr" pagesize="${pageSize}" class="listTable taskList"
                           export="false" sort="list" size="TawBureaudataHlrsize" partialList="true"
                           requestURI="numberapply.do">
                <display:column headerClass="sortable" title="">
                    <input type="radio" name="radiohlr24" id="radiohlr24">
                    <!-- 隐藏域 显示的记录的id -->
                    <input type="hidden" name="hlr24id" id="hlr24id" value="${TawBureaudataHlr.id }"/>
                </display:column>
                <display:column property="hlrsignalid" sortable="true" headerClass="sortable" title="24位信令点"/>
                <display:column property="hlrname" sortable="true" headerClass="sortable" title="HLR名称"/>
                <display:column property="belongcityid" sortable="true" headerClass="sortable" title="归属地市区号"/>
                <display:column property="hlrid" sortable="true" headerClass="sortable" title="HLRID"/>
            </display:table>
        </logic:notEmpty>
        <br>
        <br>
        <logic:notEmpty name="TawBureaudataHlr">
            HLRID
            <display:table name="TawBureaudataHlr" cellspacing="0" cellpadding="0"
                           id="TawBureaudataHlr" pagesize="${pageSize}" class="listTable taskList"
                           export="false" sort="list" size="TawBureaudataHlrsize" partialList="true"
                           requestURI="numberapply.do">
                <display:column headerClass="sortable" title="">
                    <input type="radio" name="radiohlrid" id="radiohlrid">
                    <!-- 隐藏域 显示的记录的id -->
                    <input type="hidden" name="commondhlrid" id="commondhlrid" value="${TawBureaudataHlr.id }"/>
                </display:column>
                <display:column property="hlrid" sortable="true" headerClass="sortable" title="HLRID"/>
                <display:column property="hlrname" sortable="true" headerClass="sortable" title="HLR名称"/>
                <display:column property="belongcityid" sortable="true" headerClass="sortable" title="归属地市区号"/>
                <display:column property="hlrsignalid" sortable="true" headerClass="sortable" title="24位信令点"/>
            </display:table>
        </logic:notEmpty>
        <br>
        <br>
        <logic:notEmpty name="TawPartFourteenSignal">
            14位信令点
            <display:table name="TawPartFourteenSignal" cellspacing="0" cellpadding="0"
                           id="TawPartFourteenSignal" pagesize="${pageSize}" class="listTable taskList"
                           export="false" sort="external" size="TawPartFourteenSignalsize" partialList="true"
                           requestURI="numberapply.do">
                <display:column headerClass="sortable" title="">
                    <input type="radio" name="radiohlr14" id="radiohlr14">
                    <!-- 隐藏域 显示的记录的id -->
                    <input type="hidden" name="commond14id" id="commond14id" value="${TawPartFourteenSignal.id}"/>
                </display:column>
                <display:column property="signalvalue" sortable="true" headerClass="sortable" title="14位信令点"/>
                <display:column property="signalnum" sortable="true" headerClass="sortable" title="信令编号"/>
                <display:column property="userid" sortable="true" headerClass="sortable" title="用户名id"/>
                <display:column property="updatedate" sortable="true" headerClass="sortable" title="更新时间"/>

            </display:table>
        </logic:notEmpty>
        <br>
        <br>
        <logic:notEmpty name="TawpartMscid">
            MSC24位信令点
            <display:table name="TawpartMscid" cellspacing="0" cellpadding="0"
                           id="TawpartMscid" pagesize="${pageSize}" class="listTable taskList"
                           export="false" sort="list" size="TawpartMscidsize" partialList="true">
                <display:column headerClass="sortable" title="">
                    <input type="radio" name="radiomsc24" id="radiomsc24">
                    <!-- 隐藏域 显示的记录的id -->
                    <input type="hidden" name="msc24id" id="msc24id" value="${TawpartMscid.id }"/>
                </display:column>
                <display:column property="signalname" sortable="true" headerClass="sortable" title="24位信令点"/>
                <display:column property="areaName" sortable="true" headerClass="sortable" title="分配地市名称"/>
                <display:column property="creator" sortable="true" headerClass="sortable" title="创建人"/>
                <display:column property="createTime" sortable="true" headerClass="sortable" title="创建时间"/>
                <display:column property="assigner" sortable="true" headerClass="sortable" title="分配人"/>
                <display:column property="assignTime" sortable="true" headerClass="sortable" title="分配时间"/>
            </display:table>
        </logic:notEmpty>
        <br>
        <br>
        <logic:notEmpty name="TawpartMscid">
            MSCID
            <display:table name="TawpartMscid" cellspacing="0" cellpadding="0"
                           id="TawpartMscid" pagesize="${pageSize}" class="listTable taskList"
                           export="false" sort="list" size="TawpartMscidsize" partialList="true">
                <display:column headerClass="sortable" title="">
                    <input type="radio" name="radiomscid" id="radiomscid">
                    <!-- 隐藏域 显示的记录的id -->
                    <input type="hidden" name="commondmscid" id="commondmscid" value="${TawpartMscid.id }"/>
                </display:column>
                <display:column property="numberFree" sortable="true" headerClass="sortable" title="MSCID"/>
                <display:column property="areaName" sortable="true" headerClass="sortable" title="分配地市名称"/>
                <display:column property="creator" sortable="true" headerClass="sortable" title="创建人"/>
                <display:column property="createTime" sortable="true" headerClass="sortable" title="创建时间"/>
                <display:column property="assigner" sortable="true" headerClass="sortable" title="分配人"/>
                <display:column property="assignTime" sortable="true" headerClass="sortable" title="分配时间"/>
            </display:table>
        </logic:notEmpty>


    </table>
    <input type="submit" value="保存" class="submit">
    <input type="button" value="关闭" Onclick="onBack();" class="button">
</form>

<%@ include file="/common/footer_eoms.jsp" %>
