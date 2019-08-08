<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
           onclick="location.href='<html:rewrite page='/commonfaulthjs.do?method=add'/>'"
           value="<fmt:message key="button.add"/>"/>
</c:set>

<fmt:bundle basename="config/applicationResource-commonfaulthj">

    <content tag="heading">
        工单统计自动核减列表
    </content>

    <form name="queryList" method="post" action="${app }/commonfaulthj/excelimport.do?method=messageimport">
        <table>
            <tr>
                <td>
                    <input type="submit" class="btn" value="导入"/>
                </td>
            </tr>
        </table>
    </form>


    <display:table name="commonfaulthjList" cellspacing="0" cellpadding="0"
                   id="commonfaulthjList" pagesize="${pageSize}" class="table commonfaulthjList"
                   export="false"
                   requestURI="${app}/commonfaulthj/commonfaulthjs.do?method=search"
                   sort="list" partialList="true" size="resultSize">

        <display:column property="sheetid" sortable="true"
                        headerClass="sortable" title="工单号" href="${app}/commonfaulthj/commonfaulthjs.do?method=edit"
                        paramId="id" paramProperty="id"/>

        <display:column property="creater" sortable="true"
                        headerClass="sortable" title="创建人" href="${app}/commonfaulthj/commonfaulthjs.do?method=edit"
                        paramId="id" paramProperty="id"/>

        <display:column property="savetime" sortable="true"
                        headerClass="sortable" title="保存时间"
                        format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

        <display:column property="updatetime" sortable="true"
                        headerClass="sortable" title="修改时间" format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

        <display:column property="sendyear" sortable="true"
                        headerClass="sortable" title="派单年份" href="${app}/commonfaulthj/commonfaulthjs.do?method=edit"
                        paramId="id" paramProperty="id"/>

        <display:column property="sendmonth" sortable="true"
                        headerClass="sortable" title="派单月份" href="${app}/commonfaulthj/commonfaulthjs.do?method=edit"
                        paramId="id" paramProperty="id"/>

        <display:column property="sendday" sortable="true"
                        headerClass="sortable" title="派单日" href="${app}/commonfaulthj/commonfaulthjs.do?method=edit"
                        paramId="id" paramProperty="id"/>

        <display:column sortable="true"
                        headerClass="sortable" title="核减类型">
            <eoms:id2nameDB id="${commonfaulthjList.subtractType}" beanId="ItawSystemDictTypeDao"/>
        </display:column>

        <display:column property="remark" sortable="true"
                        headerClass="sortable" title="核减理由" href="${app}/commonfaulthj/commonfaulthjs.do?method=edit"
                        paramId="id" paramProperty="id"/>


        <display:setProperty name="paging.banner.item_name" value="commonfaulthj"/>
        <display:setProperty name="paging.banner.items_name" value="commonfaulthjs"/>
    </display:table>


</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp" %>