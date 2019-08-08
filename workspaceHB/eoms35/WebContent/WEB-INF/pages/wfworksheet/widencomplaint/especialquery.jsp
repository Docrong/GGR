<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<table class="formTable">
    <!-- 所属地域 -->
    <!-- 工单流水号 -->
    <tr>
        <td class="label">
            投诉内容
        </td>
        <td width="100%">
            <input type="hidden" name="complaintDescStringExpression" value="like"/>
            <input type="text" name="main.complaintDesc" id="complaintDesc" size="30" class="text"/>
        </td>

    </tr>
    <tr>
        <td class="label">
            客服流水号
        </td>
        <td width="100%">
            <input type="hidden" name="parentCorrelationStringExpression" value="like"/>
            <input type="text" name="main.parentCorrelation" id="parentCorrelation" size="30" class="text"/>
        </td>

    </tr>
    <tr>
        <td class="label">
            <bean:message bundle="widencomplaint" key="widenComplaintMain.startDealCity"/>
        </td>
        <td width="100%">
            <input type="hidden" name="toDeptIdStringExpression" value="in"/>
            <input type="text" class="text" readonly="readonly" name="showArea" id="showArea"
                   beanId="tawSystemAreaDao"/>
            <input type="hidden" name="main.toDeptId" id="toAreaId"/>
        </td>
    </tr>
    <!-- 投诉一级分类 -->
    <tr>
        <td class="label">
            <bean:message bundle="widencomplaint" key="widenComplaintMain.complaintType1"/>
        </td>
        <td>
            <input type="hidden" name="main.complaintType1"/>
            <eoms:comboBox name="complaintType1ChoiceExpression" id="complaintType1" sub="complaintType2"
                           initDicId="1010625"/>
        </td>
    </tr>
    <!-- 投诉二级分类 -->
    <tr>
        <td class="label">
            <bean:message bundle="widencomplaint" key="widenComplaintMain.complaintType2"/>
        </td>
        <td>
            <input type="hidden" name="main.complaintType2"/>
            <eoms:comboBox name="complaintType2ChoiceExpression" id="complaintType2" sub="complaintType"/>
        </td>
    </tr>
    <!-- 投诉三级分类 -->
    <tr>
        <td class="label">
            <bean:message bundle="widencomplaint" key="widenComplaintMain.complaintType"/>
        </td>
        <td>
            <input type="hidden" name="main.complaintType"/>
            <eoms:comboBox name="complaintTypeChoiceExpression" id="complaintType" sub="complaintType4"/>
        </td>
    </tr>
    <!-- 投诉四级分类 -->
    <tr>
        <td class="label">
            <bean:message bundle="widencomplaint" key="widenComplaintMain.complaintType4"/>
        </td>
        <td>
            <input type="hidden" name="main.complaintType4"/>
            <eoms:comboBox name="complaintType4ChoiceExpression" id="complaintType4" sub="complaintType5"/>
        </td>
    </tr>
    <!-- 投诉五级分类 -->
    <tr>
        <td class="label">
            <bean:message bundle="widencomplaint" key="widenComplaintMain.complaintType5"/>
        </td>
        <td>
            <input type="hidden" name="main.complaintType5"/>
            <eoms:comboBox name="complaintType5ChoiceExpression" id="complaintType5" sub="complaintType6"/>
        </td>
    </tr>
    <!-- 投诉六级分类 -->
    <tr>
        <td class="label">
            <bean:message bundle="widencomplaint" key="widenComplaintMain.complaintType6"/>
        </td>
        <td>
            <input type="hidden" name="main.complaintType6"/>
            <eoms:comboBox name="complaintType6ChoiceExpression" id="complaintType6" sub="complaintType7"/>
        </td>
    </tr>
    <!-- 投诉七级分类 -->
    <tr>
        <td class="label">
            <bean:message bundle="widencomplaint" key="widenComplaintMain.complaintType7"/>
        </td>
        <td>
            <input type="hidden" name="main.complaintType7"/>
            <eoms:comboBox name="complaintType7ChoiceExpression" id="complaintType7"/>
        </td>
    </tr>
    <!-- 是否大面积投诉 -->
    <tr>
        <td class="label">
            是否大面积投诉
        </td>
        <td>
            <input type="hidden" name="main.bdeptContactPhone"/>
            <select name="bdeptContactPhoneChoiceExpression">
                <option value=""></option>
                <option value="1030801">普通投诉</option>
                <option value="1030802">大面积投诉</option>
                <option value="1030803">升级投诉</option>
            </select>
        </td>
    </tr>
</table>  