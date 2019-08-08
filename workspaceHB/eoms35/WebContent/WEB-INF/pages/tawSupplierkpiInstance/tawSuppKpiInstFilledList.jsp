<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<style type="text/css">
    .alert td {
        background-color: red;
    }
</style>
<jsp:directive.page
        import="com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInstance"/>
<fmt:bundle basename="config/ApplicationResources-supplierkpi">
    <content tag="heading">
        <fmt:message key="tawSupplierkpiInstanceList.fillFinish"/>
    </content>
</fmt:bundle>
<%
    String pageNum = (String) request.getAttribute("pageNum");
    int num = Integer.parseInt(pageNum);
    num = num * 10;
%>
<table class="list-title">
    <tr>
        <%
            String serviceTy = (String) request.getAttribute("serviceTy");
            String specialTy = (String) request.getAttribute("specialTy");
            String statictsCy = (String) request.getAttribute("statictsCy");
            String timeLati = (String) request.getAttribute("timeLati");
            String years = (String) request.getAttribute("_year");
            String latitu = "";
            if (years != null && !years.equals("")) {
                if (timeLati != null && !timeLati.equals("")
                        && timeLati.equals("year")) {
                    latitu = years + "\u5E74\u5EA6";
                } else if (timeLati != null && !timeLati.equals("")
                        && timeLati.equals("one")) {
                    latitu = years + "\u5E74\u7B2C\u4E00\u5B63\u5EA6";
                } else if (timeLati != null && !timeLati.equals("")
                        && timeLati.equals("two")) {
                    latitu = years + "\u5E74\u7B2C\u4E8C\u5B63\u5EA6";
                } else if (timeLati != null && !timeLati.equals("")
                        && timeLati.equals("three")) {
                    latitu = years + "\u5E74\u7B2C\u4E09\u5B63\u5EA6";
                } else if (timeLati != null && !timeLati.equals("")
                        && timeLati.equals("four")) {
                    latitu = years + "\u5E74\u7B2C\u56DB\u5B63\u5EA6";
                } else if (timeLati != null && !timeLati.equals("")) {
                    latitu = years + "-" + timeLati;
                }
            }
            if (serviceTy != null && !serviceTy.equals("")) {
        %>
        <td>
        </td>
        <td width="30%">&nbsp;&nbsp;
            <bean:message key="tawSupplierkpiInstanceList.serviceType"/>:
            <eoms:id2nameDB id="<%=serviceTy%>" beanId="tawSupplierkpiDictDao"/>
        </td>
        <td width="30%">
            <bean:message key="tawSupplierkpiInstanceList.specialType"/>:
            <eoms:id2nameDB id="<%=specialTy%>" beanId="tawSupplierkpiDictDao"/>
        </td>
        <td width="30%">
            <bean:message key="tawSupplierkpiInstanceList.timeLatitude"/>:
            <%=latitu%>
        </td>
        <%
            }
        %>
    </tr>
    <br>
    <tr>
        <td width="2%">
        </td>
        <td>&nbsp;&nbsp;
            <label>${eoms:a2u('查询历史数据')}</label>
        </td>
        <td>
            <bean:message key="tawSupplierkpiInstanceList.year"/>:
            <select name="year" id="slt1" onchange="monthShow();">
                <option value="-1"><label>${eoms:a2u('请选择年份')}</label></option>
                </option>
                <option value="2005">2005</option>
                <option value="2006">2006</option>
                <option value="2007">2007</option>
                <option value="2008">2008</option>
                <option value="2009">2009</option>
                <option value="2010">2010</option>
                <option value="2011">2011</option>
                <option value="2012">2012</option>
                <option value="2013">2013</option>
                <option value="2014">2014</option>
                <option value="2015">2015</option>
            </select>
        </td>
        <td>
            <bean:message key="tawSupplierkpiInstanceList.month"/> :
            <select name="month" id="slt2" onchange="changeLatitude();">
                <option value="-1">
                    <label>${eoms:a2u('请选择月份')}</label></option>
                <option value="01">01</option>
                <option value="02">02</option>
                <option value="03">03</option>
                <option value="04">04</option>
                <option value="05">05</option>
                <option value="06">06</option>
                <option value="07">07</option>
                <option value="08">08</option>
                <option value="09">09</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
            </select>
        </td>
    </tr>
</table>
<%
    request.setAttribute("dyndecorator", new org.displaytag.decorator.TableDecorator() {
        public String addRowClass() {
            return ((TawSupplierkpiInstance) getCurrentRowObject()).getFillFlag() == 2 ? "alert" : "";
        }
    });
%>
<fmt:bundle basename="config/ApplicationResources-supplierkpi">
    <!-- <c:out value="${buttons}" escapeXml="false"/> -->
    <display:table name="viewList" cellspacing="0"
                   cellpadding="0" pagesize="100"
                   requestURI="/supplierkpi/tawSupplierkpiInstances.do"
                   id="tawSupplierkpiInstance"
                   class="table viewList" partialList="true"
                   size="viewSize" decorator="dyndecorator">

        <display:column titleKey="tawSupplierkpiInstanceList.numb" sortable="true"
                        headerClass="sortable">
            <%=++num %>
        </display:column>

        <display:column property="manufacturerName" sortable="true"
                        headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceList.supplier"/>

        <display:column property="statictsCycleName" sortable="true"
                        headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceList.statictsCycle"/>

        <display:column property="kpiName" sortable="true"
                        headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceList.itemType"/>

        <display:column property="assessContent" sortable="true"
                        headerClass="sortable" url="/supplierkpi/editTawSupplierkpiInstance.do" paramId="id"
                        paramProperty="id"
                        titleKey="tawSupplierkpiInstanceList.assessContent"/>

        <display:column property="examineContent" sortable="true"
                        headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceList.examineContent"/>

        <display:column property="unitName" sortable="true"
                        headerClass="sortable" titleKey="tawSupplierkpiInstanceList.unit"/>

        <display:column property="memo" sortable="true" headerClass="sortable"
                        titleKey="tawSupplierkpiInstanceForm.memo"/>

        <display:setProperty name="paging.banner.item_name" value="tawSupplierkpiInstance"/>
        <display:setProperty name="paging.banner.items_name" value="tawSupplierkpiInstances"/>

    </display:table>
</fmt:bundle>
<script>
    document.getElementById("slt2").disabled = true;

    function monthShow() {
        if (document.getElementById("slt1").value != '-1') document.getElementById("slt2").disabled = false;
        if (document.getElementById("slt1").value == '-1') document.getElementById("slt2").disabled = true;
    }

    function changeLatitude() {
        var objResult1 = document.getElementById("slt1");
        var year = objResult1.value;
        var objResult2 = document.getElementById("slt2");
        var month = objResult2.value;
        var url = "<c:url value="/supplierkpi/tawSupplierkpiInstances.do?method=searchView&id="/>";
        url += "<%=specialTy%>";
        url += "&year=" + year;
        url += "&month=" + month;
        location.href = url;
    }
</script>
<%@ include file="/common/footer_eoms.jsp" %>
