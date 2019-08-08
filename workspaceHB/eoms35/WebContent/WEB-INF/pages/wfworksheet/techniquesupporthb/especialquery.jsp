<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<table class="formTable">
    <tr>
        <td class="label">
            <bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainTechSupportType"/>
        </td>
        <td>
            <input type="hidden" name="main.mainTechSupportType"/>
            <eoms:comboBox name="mainTechSupportTypeChoiceExpression" id="mainTechSupportType" initDicId="1014701"/>
        </td>
    </tr>
    <tr>
        <td class="label">
            厂商
        </td>
        <td width="100%">
            <input type="hidden" name="main.mainManufacturer"/>
            <eoms:comboBox name="mainManufacturerChoiceExpression" id="mainManufacturer" initDicId="1014713"/>
        </td>
    </tr>
    <tr>
        <td class="label">
            子专业
        </td>
        <td width="100%">
            <input type="hidden" name="main.mainProfessional"/>
            <eoms:comboBox name="mainProfessionalChoiceExpression" id="mainProfessional" initDicId="1014712"/>
        </td>
    </tr>
    <tr>
        <td class="label">
            <bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainSheetType"/>
        </td>
        <td width="100%">
            <input type="hidden" name="main.mainSheetType"/>
            <eoms:comboBox name="mainSheetTypeChoiceExpression" id="mainSheetType" initDicId="1014702"/>
        </td>
    </tr>
</table>