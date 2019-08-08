<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page language="java" import="java.util.*,com.boco.eoms.partner.baseinfo.webapp.form.*;" %>
<script language="JavaScript" type="text/JavaScript" src="${app}/scripts/module/partner/ajax.js"></script>

<script type="text/javascript">
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'tawApparatusForm'});
    });

</script>

<html:form action="/tawApparatuss.do?method=save"
           styleId="tawApparatusForm" method="post">

    <fmt:bundle basename="config/applicationResources-partner-baseinfo">

        <table class="formTable">
            <caption>
                <div class="header center">
                    <fmt:message key="tawApparatus.form.heading"/>
                </div>
            </caption>
            <tr>
                <td class="label">
                    仪器仪表编号&nbsp;*
                </td>
                <td class="content" colspan="3">
                    <html:text property="apparatusId" styleId="apparatusId"
                               styleClass="text medium" alt="allowBlank:false,vtext:''"
                               value="${tawApparatusForm.apparatusId}"/>${fallure }
                </td>
            </tr>
            <tr>
                <td class="label">
                    <fmt:message key="tawApparatus.apparatusName"/>&nbsp;*
                </td>
                <td class="content">
                    <html:text property="apparatusName" styleId="apparatusName"
                               styleClass="text medium" alt="allowBlank:false,vtext:''"
                               value="${tawApparatusForm.apparatusName}"/>
                </td>

                <td class="label">
                    <fmt:message key="tawApparatus.factory"/>&nbsp;*
                </td>
                <td class="content">
                        <%--<eoms:dict key="dict-partner" dictId="factory_apparatus" beanId="selectXML" alt="allowBlank:false,vtext:''"
                     defaultId="" isQuery="false"  selectId="factory" onchange="factorychange(this.value)"/>
                    --%>
                    <eoms:comboBox name="factory" id="factory" initDicId="11203"
                                   alt="allowBlank:false,vtext:''"
                                   onchange="factorychange(this.value)"
                                   defaultValue='${tawApparatusForm.factory}'/>
                    <div style="display:none;" id="control">
                        <html:text property="factory2" styleId="factory2"
                                   styleClass="text medium"/>
                        请输入生产厂家
                    </div>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="tawApparatus.area_id"/>&nbsp;*
                </td>
                <td class="content">

                    <html:select disabled="true" property="area_id" styleId="area_id" size="1" onchange="changeDep();">
                        <%
                            List listId = (List) request.getAttribute("listId");
                            List listName = (List) request.getAttribute("listName");
                            TawApparatusForm fm = (TawApparatusForm) request.getAttribute("tawApparatusForm");
                            String nodeId = fm.getArea_id();
                            for (int i = 0; i < listId.size(); i++) {
                                if (nodeId.equals(listId.get(i))) {
                        %>
                        <option value="<%=listId.get(i) %>" selected>
                            <%=listName.get(i) %>
                        </option>
                        <%} else { %>
                        <option value="<%=listId.get(i) %>">
                            <%=listName.get(i) %>
                        </option>
                        <%
                                }
                            }
                        %>
                    </html:select>
                        <%--<html:text property="area_id" styleId="area_id"
                            styleClass="text medium" alt="allowBlank:false,vtext:''"
                            value="${tawApparatusForm.area_id}" />
                    --%>
                    <html:hidden property="area_id"/>
                </td>
                <td class="label">
                    <fmt:message key="tawApparatus.dept_id"/>&nbsp;*
                </td>
                <td class="content">
                    <html:select property="dept_id" disabled="true" styleId="dept_id" size="1">
                        <html:hidden property="dept_id"/>
                    </html:select>
                        <%--<html:text property="dept_id" styleId="dept_id"
                            styleClass="text medium" alt="allowBlank:false,vtext:''"
                            value="${tawApparatusForm.dept_id}" />
                    --%></td>


            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="tawApparatus.type"/>&nbsp;*
                </td>
                <td class="content">
                    <eoms:comboBox name="type" id="type" initDicId="11204"
                                   defaultValue='${tawApparatusForm.type}'
                                   alt="allowBlank:false,vtext:''"/>
                        <%--<html:text property="type" styleId="type"
                            styleClass="text medium"
                            alt="allowBlank:false,vtext:''" value="${tawApparatusForm.type}" />
            --%>
                </td>

                <td class="label">
                    <fmt:message key="tawApparatus.model"/>&nbsp;*
                </td>
                <td class="content">
                    <html:text property="model" styleId="model"
                               styleClass="text medium" alt="allowBlank:false,vtext:''"
                               value="${tawApparatusForm.model}"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="tawApparatus.state"/>&nbsp;*
                </td>
                <td class="content">
                    <eoms:comboBox name="state" id="state" initDicId="11205"
                                   defaultValue='${tawApparatusForm.state}'
                                   alt="allowBlank:false,vtext:''"/>
                        <%--<html:text property="state" styleId="state"
                            styleClass="text medium"
                            alt="allowBlank:false,vtext:''" value="${tawApparatusForm.state}" />
            --%>
                </td>

                <td class="label">
                    <fmt:message key="tawApparatus.storage"/>&nbsp;*
                </td>
                <td class="content">
                    <html:text property="storage" styleId="storage"
                               styleClass="text medium" alt="allowBlank:false,vtext:''"
                               value="${tawApparatusForm.storage}"/>
                </td>
            </tr>
            <logic:notEmpty name="tawApparatusForm" property="addMan">
                <tr>
                    <td class="label">
                        添加人姓名
                    </td>
                    <td class="content">
                        <eoms:id2nameDB id="${tawApparatusForm.addMan}"
                                        beanId="tawSystemUserDao"/>&nbsp;&nbsp;
                        (<bean:write name="tawApparatusForm" property="connectType"/>)
                    </td>

                    <td class="label">
                        添加时间
                    </td>
                    <td class="content">
                        <bean:write name="tawApparatusForm" property="addTime"/>

                    </td>
                </tr>
            </logic:notEmpty>
            <tr>
                <td class="label">
                    <fmt:message key="tawApparatus.remark"/>
                </td>
                <td class="content" colspan="3">
                    <html:textarea property="remark" styleId="remark"
                                   styleClass="text medium" rows="3" cols="40"
                                   value="${tawApparatusForm.remark}"/>
                </td>
            </tr>
            <input type="hidden" name="nodeId" value="<%=request.getAttribute("nodeId") %>">
        </table>

    </fmt:bundle>

    <table>
        <tr>
            <td>
                <input type="submit" class="btn"
                       onclick="javascript: return check()"
                       value="<fmt:message key="button.save"/>"/>
                <c:if test="${not empty tawApparatusForm.id}">
                    <input type="button" class="btn"
                           value="<fmt:message key="button.delete"/>"
                           onclick="javascript:if(confirm('确认删除?')){
                                   var url='${app}/partner/baseinfo/tawApparatuss.do?method=remove&id=${tawApparatusForm.id}';
                                   location.href=url}"/>
                    <%--<input type="button" class="btn" value="返回"
                        onclick="javascript:{
                        var url='${app}/partner/baseinfo/tawApparatuss.do?method=backToSearch';
                        location.href=url}" />
                --%></c:if>
                <input type="button" class="btn" value="批量导入"
                       onclick="javascript:{
                               var url='${app}/partner/baseinfo/tawApparatuss.do?method=toXls';
                               location.href=url}"/>
            </td>
        </tr>
    </table>
    <html:hidden property="id" value="${tawApparatusForm.id}"/>
</html:form>
<script type="text/javascript">
    var id = document.getElementById("area_id").value;
    var url = "<%=request.getContextPath()%>/partner/baseinfo/tawApparatuss.do?method=changeDep&id=" + id;
    var fieldName = "dept_id";
    var deptId = "<%=((TawApparatusForm)request.getAttribute("tawApparatusForm")).getDept_id()%>";
    changeList(url, fieldName, deptId);

    function changeDep() {
        var id = document.getElementById("area_id").value;
        var url = "<%=request.getContextPath()%>/partner/baseinfo/tawApparatuss.do?method=changeDep&id=" + id;
        var fieldName = "dept_id";
        changeList(url, fieldName, "");
    }

    function factorychange(ss) {
        if (ss == "1120301") {
            document.getElementById("control").style.display = "inline";
        } else {
            document.getElementById("control").style.display = "none";
        }
    }

    function check() {
        var fac = document.getElementById("factory").value;
        if (fac == "1120301") {
            var fac2 = document.getElementById("factory2").value;
            if (fac2 == "" || fac2 == " ") {
                alert("请输入厂商！");
                return false;
            }
        }
    }
</script>
<%@ include file="/common/footer_eoms.jsp" %>
