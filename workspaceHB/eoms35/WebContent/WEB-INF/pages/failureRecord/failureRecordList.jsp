<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<style>
    #tabs {
        width: 100%;
    }

    #tabs .x-tabs-item-body {
        display: none;
        padding: 10px;
    }
</style>
<script type="text/javascript">
    Ext.onReady(function () {
        var tabs = new Ext.TabPanel('tabs');
        var formTab = tabs.addTab('form', "<bean:message key='Failure.List'/>");
        var infoTab = tabs.addTab('info', "<bean:message key='Failure.Help'/>");
        formTab.on('activate', function () {

        });
        infoTab.on('activate', function () {

        });
        tabs.activate('form');
    });
</script>


<!-- <c:out value="${buttons}" escapeXml="false"/> -->
<div id="tabs">

    <div id="form" class="tab-content">
        <html:form
                action="/failureRecord.do?method=failureRecordList">
            <table border="0" width="100%">

                <tr class="tr_show">
                    <td class="clsfth">
                        <bean:message key='Failure.title'/>
                    </td>
                    <td colspan="3">
                        <html:text property="title" size="75"/>
                    </td>

                </tr>

                <tr class="tr_show">
                    <td class="label">
                        <bean:message key='Failure.faulttype1'/>
                    </td>
                    <td>
                        <eoms:comboBox name="faulttype1" id="a1" sub="a2" initDicId="120"/>
                    </td>
                    <td class="label">
                        <bean:message key='Failure.faulttype2'/>
                    </td>
                    <td>
                        <eoms:comboBox name="faulttype2" id="a2" sub="a3" styleClass="border"/><br/>
                    </td>


                </tr>
                <tr class="tr_show">

                    <td class="label">
                        <bean:message key='Failure.faulttype3'/>
                    </td>
                    <td>
                        <eoms:comboBox name="faulttype3" id="a3" sub="a4" styleClass="border"/><br/>

                    </td>
                    <td class="label">

                        <bean:message key='Failure.faulttype4'/>
                    </td>
                    <td>
                        <eoms:comboBox name="faulttype4" id="a4" styleClass="border"/><br/>

                    </td>

                </tr>
                <tr class="tr_show">

                    <td class="label">

                        <bean:message key='Failure.faultregion'/>
                    </td>
                    <td>
                        <eoms:comboBox name="faultregion" id="a5" initDicId="122"/>

                    </td>
                    <td class="label">

                        <bean:message key='Failure.faultjudge'/>
                    </td>
                    <td>
                        <eoms:comboBox name="faultjudge" id="a5" initDicId="10302"/>


                    </td>

                </tr>
                <tr class="tr_show">
                    <td class="label">
                        <bean:message key='Failure.Faultstarttime'/>
                    </td>
                    <td>

                        <input type="text" name="faultstarttime" size="30" readonly="true"
                               class="text" onclick="popUpCalendar(this, this);">

                    </td>
                    <td class="label">

                        <bean:message key='Failure.Faultendtime'/>
                    </td>
                    <td>

                        <input type="text" name="faultendtime" size="30" readonly="true"
                               class="text" onclick="popUpCalendar(this, this);">


                    </td>

                </tr>
                <tr class="tr_show">


                    <td class="label">

                        <bean:message key='Failure.faultstatus'/>
                    </td>
                    <td colspan="3">
                        <eoms:comboBox name="faultstatus" id="a7" initDicId="124"/>

                    </td>

                </tr>

            </table>
            <table border="0" width="100%" cellspacing="0">
                <tr>
                    <td width="100%" height="32" align="right">
                        <html:submit property="strutsButton" styleClass="clsbtn2">
                            <bean:message key='Failure.Enquiries'/>
                        </html:submit>
                        &nbsp;&nbsp;
                    </td>
                </tr>
            </table>
        </html:form>
        <bean:message key="Failure.List"/>
        <fmt:bundle
                basename="config/ApplicationResources-commons-FailureRecord">
            <display:table name="FailureRecordList" cellspacing="0"
                           cellpadding="0" id="FailureRecordList" pagesize="${pageSize}"
                           class="table FailureRecordList" export="true" requestURI=""
                           sort="list">

                <display:column property="title" sortable="true"
                                headerClass="sortable" titleKey="Failure.title"/>

                <display:column property="faulttype1Name" sortable="true"
                                headerClass="sortable" titleKey="Failure.faulttype1"/>
                <display:column property="faulttype2Name" sortable="true"
                                headerClass="sortable" titleKey="Failure.faulttype2"/>
                <display:column property="faulttype3Name" sortable="true"
                                headerClass="sortable" titleKey="Failure.faulttype3"/>

                <display:column property="view" sortable="true"
                                headerClass="sortable" titleKey="Failure.view" paramId="id"
                                paramProperty="id"
                                href="${app}/failureRecord/failureRecord.do?method=detail"/>
                <display:column property="del" sortable="true"
                                headerClass="sortable" titleKey="Failure.del" paramId="id"
                                paramProperty="id"
                                href="${app}/failureRecord/failureRecord.do?method=xdelete"/>
                <display:column property="edit" sortable="true"
                                headerClass="sortable" titleKey="Failure.Edit" paramId="id"
                                paramProperty="id"
                                href="${app}/failureRecord/failureRecord.do?method=edit"/>
                <display:column property="news" sortable="true"
                                headerClass="sortable" titleKey="Failure.news" paramId="id"
                                paramProperty="id"
                                href="${app}/failureRecord/failureRecord.do?method=failureRecordInterface"/>

                <display:setProperty name="paging.banner.item_name" value="forums"/>
                <display:setProperty name="paging.banner.items_name" value="forumss"/>

            </display:table>
            <c:out value="${buttons}" escapeXml="false"/>
        </fmt:bundle>
    </div>
    <div id="info">
        <dl>
            <dt>${eoms:a2u('功能说明')}</dt>
            <dd>${eoms:a2u('故障记录列表中根据信息能够查询到所需故障记录，其中可以对故障记录进行查看、删除、修改操作，如果需要进行派发的工单流转操作，则需生成工单。')}</dd>
            <dd>${eoms:a2u('根据查询出的记录列表，可以导出到本地进行操作。')}</dd>
            <dt>${eoms:a2u('记录查询')}</dt>
            <dd>${eoms:a2u('输入记录查询条件，查询结果。')}</dd>
        </dl>
    </div>
</div>


<%@ include file="/common/footer_eoms.jsp" %>