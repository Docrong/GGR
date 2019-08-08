<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<%@ include file="/common/extlibs.jsp" %>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.common.util.StaticMethod" %>
<% GregorianCalendar cal_start = new GregorianCalendar();
    cal_start.add(cal_start.DATE, -1);
    String str_start = StaticMethod.Cal2String(cal_start);
    str_start = String.valueOf(StaticMethod.getVector(str_start, " ").elementAt(0));
%>
<style>
    #tabs {
        width: 90%;
    }

    #tabs .x-tabs-item-body {
        display: none;
        padding: 10px;
    }
</style>
<script type="text/javascript">
    var Tabs = {
        init: function () {
            var tabs = new Ext.TabPanel('tabs');
            tabs.addTab('form', '${eoms:a2u('日志查询')}');
            tabs.addTab('info', '${eoms:a2u('帮助')}');
            tabs.activate('form');
        }
    }
    Ext.onReady(Tabs.init, Tabs, true);
</script>
<div id="tabs">
    <div id="form" class="tab-content">
        <html:form action="/TawCommonLogCondition/querydo">
            <table border="0" width="95%" cellspacing="1">

                <tr class="tr_show">
                    <td class="clsfth">${eoms:a2u('用户ID')}</td>
                    <td colspan=3>
                        <html:text property="searchbyuser" style="width:100%" title="USERID"/>
                    </td>
                </tr>
                <!--  tr class="tr_show">
		<td class="clsfth">MODELID</td>
		<td colspan=3>
		<html:text property="searchbymodel" style="width:100%" title="MODELID"/>
	   </td>
 </tr>
  <tr class="tr_show">
		<td class="clsfth">OPERID</td>
		<td colspan=3>
		<html:text property="searchbyoper" style="width:100%" title="OPERID"/>
	   </td>
 </tr>-->
                <tr class="tr_show">
                    <td class="clsfth">${eoms:a2u('开始时间')}</td>
                    <td colspan=3>
                        <eoms:SelectDate name="searchbystarttime" formName="TawCommonLogConditionForm" flag="-1"
                                         value="<%=str_start%>"/>
                        <!-- html:text property="searchbystarttime" style="width:100%" title="STARTTIME"/>-->
                    </td>
                </tr>
                <tr class="tr_show">
                    <td class="clsfth">${eoms:a2u('结束时间')}</td>
                    <td colspan=3>
                        <eoms:SelectDate name="searchbyendtime" formName="TawCommonLogConditionForm" flag="0"
                                         value="<%=str_start%>"/>
                        <!--html:text property="searchbyendtime" style="width:100%" title="ENDTIME"/-->
                    </td>
                </tr>
                <tr class="tr_show">
                    <td class="clsfth">${eoms:a2u('日志类型')}</td>
                    <td colspan=3>
                        <eoms:dict key="dict-log" dictId="issucess" isQuery="true" selectId="issucess"
                                   beanId="selectXML"/>
                        <!--  select name="issucess">
                        <option value="all">ALL</option>
                        <option value="sucess">SUCESS</option>
                        <option value="error">ERROR</option>
                      </select>-->
                    </td>
                </tr>
            </table>
            <table border="0" width="70%" cellspacing="0">
                <tr>
                    <td width="100%" height="32" align="right">
                        <html:submit property="strutsButton" styleClass="clsbtn2">
                            ${eoms:a2u('查询')}
                        </html:submit>
                        &nbsp;&nbsp;
                    </td>
                </tr>
            </table>

        </html:form>
    </div>
    <div id="info">
        <dl>
            <dt>${eoms:a2u('日志管理')}</dt>
            <dd>${eoms:a2u('系统日志管理提供对系统日志的查询、统计和删除功能。')}</dd>
            <dt>${eoms:a2u('查询')}</dt>
            <dd>${eoms:a2u('输入日志查询条件，查询结果。')}</dd>
        </dl>
    </div>
</div>
</body>
</html>
