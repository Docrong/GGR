<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<script type="text/javascript">
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'partnerDeptForm'});
    });

    function setAreaName() {
        var areas = document.getElementsByName("area");
        var provinces = document.getElementsByName("province");
        var areaType = document.getElementById("areaType");
        var areaName = "";
        if (areas != null) {
            for (var i = 0; i < areas.length; i++) {
                if (areas[i].checked == true) areaName += areas[i].value + ",";
            }
        }
        if (provinces != null) {
            for (var j = 0; j < provinces.length; j++) {
                if (provinces[j].checked == true) areaName += provinces[j].value + ",";
            }
        }
        areaName = areaName.substring(0, areaName.length - 1);
        window.opener.document.forms[0].areaNames.value = areaName;
        window.opener.document.forms[0].areaType.value = areaType.value;
        window.close();
    }

    function hiddenArea() {
        var areas = document.getElementsByName("area");
        var provinces = document.getElementsByName("province");
        var areaType = document.getElementById("areaType");
        for (var i = 0; i < areas.length; i++) {
            areas[i].disabled = true;
        }
        for (var j = 0; j < provinces.length; j++) {
            provinces[j].disabled = false;
        }
        areaType.value = "province";
    }

    function hiddenProvince() {
        var areas = document.getElementsByName("area");
        var provinces = document.getElementsByName("province");
        var areaType = document.getElementById("areaType");
        for (var j = 0; j < provinces.length; j++) {
            provinces[j].disabled = true;
        }
        for (var i = 0; i < areas.length; i++) {
            areas[i].disabled = false;
        }
        areaType.value = "area";
    }

    function showArea() {
        var areas = document.getElementsByName("area");
        var areaType = document.getElementById("areaType");
        for (var i = 0; i < areas.length; i++) {
            areas[i].disabled = false;
        }
        areaType.value = "";
    }

    function showProvince() {
        var provinces = document.getElementsByName("province");
        var areaType = document.getElementById("areaType");
        for (var j = 0; j < provinces.length; j++) {
            provinces[j].disabled = false;
        }
        areaType.value = "";
    }
</script>

<html:form action="partnerDepts" styleId="partnerDeptForm" method="post">
    <input type="hidden" name="areaType" id="areaType">
    <fmt:bundle basename="config/applicationResources-partner-baseinfo">

        <table class="formTable">
            <caption>
                <div class="header center">选择地域范围</div>
            </caption>

            <c:if test="${provinceLists!=null}">
                <c:forEach items="${provinceLists}" var="province">
                    <tr>
                        <td class="content">
                            <input type="checkbox" name="province" value="${province.nodeName}"
                                   onclick="if(this.checked==true)hiddenArea();else showArea();">${province.nodeName}
                        </td>
                    </tr>
                </c:forEach>

            </c:if>

            <c:if test="${areaLists!=null}">
                <c:forEach items="${areaLists}" var="area">
                    <tr>
                        <td class="content">
                            <input type="checkbox" name="area" value="${area.nodeName}"
                                   onclick="if(this.checked==true)hiddenProvince();else showProvince();">${area.nodeName}
                        </td>
                    </tr>
                </c:forEach>
            </c:if>

        </table>
    </fmt:bundle>
    <table>
        <tr>
            <td>
                <input type="button" class="btn" value="确定" onclick="setAreaName();"/>
            </td>
        </tr>
    </table>

</html:form>


<%@ include file="/common/footer_eoms.jsp" %>