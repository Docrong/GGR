<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<table class="formTable">
    <!-- ����һ������ -->
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="query.status.mainNetTypeOne"/>
        </td>
        <td>
            <input type="hidden" name="main.mainNetTypeOne"/>
            <eoms:comboBox name="mainNetTypeOneChoiceExpression" id="mainNetTypeOne" sub="mainNetTypeTwo"
                           initDicId="1010104"/>
        </td>
    </tr>
    <!-- ����������� -->
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="query.status.mainNetTypeTwo"/>
        </td>
        <td>
            <input type="hidden" name="main.mainNetTypeTwo"/>
            <eoms:comboBox name="mainNetTypeTwoChoiceExpression" id="mainNetTypeTwo" sub="mainNetTypeThree"/>
        </td>
    </tr>
    <!-- ������������ -->
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="query.status.mainNetTypeThree"/>
        </td>
        <td>
            <input type="hidden" name="main.mainNetTypeThree"/>
            <eoms:comboBox name="mainNetTypeThreeChoiceExpression" id="mainNetTypeThree"/>
        </td>
    </tr>

    <tr>
        <td class="label"><!-- �������� --> <bean:message
                bundle="mofficedata" key="mofficeDataMain.mainDescRemark"/></td>
        <td width="100%">
            <input type="hidden" name="mainDescRemarkStringExpression" value="like"/>
            <input type="text" class="text"
                   name="main.mainDescRemark" id="mainDescRemark"
                   size="30"/>

        </td>

    </tr>

    <tr>
        <td class="label"><!-- ��Ԫ���� --> <bean:message
                bundle="mofficedata" key="mofficeDataMain.mainNetType"/></td>
        <td width="100%">
            <input type="hidden" name="mainNetTypeStringExpression" value="like"/>
            <input type="text" class="text" name="main.mainNetType" id="mainNetType" size="30"/>

        </td>
    </tr>


</table>  