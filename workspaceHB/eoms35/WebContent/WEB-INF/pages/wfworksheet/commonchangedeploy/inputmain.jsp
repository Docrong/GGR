<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<!-- 新增页面公共的JS -->
<%@ include file="/WEB-INF/pages/wfworksheet/common/sheet/mainJs.jsp" %>
<!-- 个性化的JS -->
<jsp:include page="/WEB-INF/pages/wfworksheet/${module}/individuationJs.jsp" flush="true"/>

<div id="sheetform" style="padding:10px;">
    <form action="${module}.do?method=performAddNew" id="theform" method="post" preCommit="true">
        <!-- 新增工单的基本信息和包括隐藏域 -->
        <jsp:include page="/WEB-INF/pages/wfworksheet/common/sheet/baseinputmainhtmlnew.jsp" flush="true"/>

        <!-- 每个工单都有这两个隐藏域，但值都不一样 -->
        <input type="hidden" name="operateName" value="newWork"/>
        <input type="hidden" name="phaseId" id="phaseId" value="${toPhaseId}"/>
        <input type="hidden" name="operateType" id="operateType"/>


        <!-- 工单基本信息 -->
        <jsp:include page="/WEB-INF/pages/wfworksheet/${module}/inputmainDetail.jsp"/>
        <!-- 工单基本信息结束 -->


        <!-- 按钮 -->
        <div class="form-btns" id="btns">
            <!-- 包括提交，保存草稿，保存新模板，引用模板，保存当前模板 公共按钮-->
            <%@ include file="/WEB-INF/pages/wfworksheet/common/sheet/mainButtons.jsp" %>

            <!-- 个性化的按钮 -->
            <!--jsp:include page="/WEB-INF/pages/wfworksheet/${module}/individuationButton.jsp" flush="true"/-->
        </div>
        <!-- 按钮结束 -->
    </form>
</div>
</div>
<%@ include file="/common/footer_eoms.jsp" %>

 