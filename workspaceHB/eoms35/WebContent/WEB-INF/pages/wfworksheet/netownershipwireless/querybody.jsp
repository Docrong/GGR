<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<script type="text/javascript">
    Ext.onReady(function () {
        Ext.Ajax.request({
            url: 'netownershipwireless.do?method=showNetType',
            method: 'POST',
            success: function (response) {
                var pro = Ext.get('netTypeChoiceExpression');
                var data = Ext.util.JSON.decode(response.responseText);
                Ext.each(data, function (d) {
                    var mySelect = document.getElementById("netTypeChoiceExpression");
                    var opp = new Option(d.netType, d.netType);
                    mySelect.add(opp);
                });
            },
            failure: function (response, options) {
                Ext.MessageBox.alert('失败', '请求超时或网络故障,错误编号：' + response.status);
            }
        });
    })

    function selectStep(statusChoice) {
        var stepId = document.getElementById("stepId");
        if (statusChoice.value == "0")
            stepId.disabled = false;
        else {
            stepId.selectedIndex = 0;
            stepId.disabled = true;

        }
    }
</script>
<html:form action="/netownershipwireless.do?method=performQuery" method="post" styleId="theform">
    <table class="formTable">

        <tr>
            <td class="label">地市/区县</td>
            <td class="content">
                <input type="button" id="areabtn" value="选择涉及地市和区县" class="btn"/>
                <br/><br/>
                地市:<textarea class="textarea max" readonly="true" name="eomsCity" style="height:50px"
                             id="eomsCity"></textarea><br/>
                区县:<textarea class="textarea max" readonly="true" name="eomsCounty" style="height:50px"
                             id="eomsCounty"></textarea>
                <input type="hidden" name="eomsCountyIdStringExpression" value="in"/>
                <input type="hidden" name="eomsCityIdStringExpression" value="in"/>
                <input type="hidden" name="main.eomsCountyId" id="eomsCountyId"/>
                <input type="hidden" name="main.eomsCityId" id="eomsCityId"/>
                <script type="text/javascript">
                    Ext.onReady(function () {
                        function callback(jsonData, str) {
                            var shengNameArr = [], shengIdArr = [], shiNameArr = [], shiIdArr = [];
                            eoms.log(jsonData);
                            Ext.each(jsonData, function (data) {
                                switch (data.id.length) {
                                    case 4 :
                                        shengNameArr.push(data.name);
                                        shengIdArr.push(data.id);
                                        break;
                                    case 6 :
                                        shiNameArr.push(data.name);
                                        shiIdArr.push(data.id);
                                        break;
                                }
                            });
                            $('eomsCity').value = shengNameArr.join(",");
                            $('eomsCounty').value = shiNameArr.join(",");
                            $('eomsCountyId').value = shiIdArr.join(",");
                            $('eomsCityId').value = shengIdArr.join(",");
                        }

                        var treeAction = '${app}/area/tawSystemAreas.do?method=getNodes';
                        var cfg = {
                            btnId: 'areabtn',
                            baseAttrs: {checked: false},
                            treeDataUrl: treeAction,
                            treeRootId: '-1',
                            treeRootText: '地域树图',
                            treeChkMode: '',
                            treeChkType: 'area',
                            callback: callback
                        }
                        var areaTree = new xbox(cfg);
                        areaTree.onBeforeCheck = function (node, checked) {
                            if (checked && node.parentNode) {
                                node.parentNode.getUI().toggleCheck(true);
                            }
                            return true;
                        }
                    });
                </script>
            </td>
        </tr>

        <!-- <tr>
            <td class="label">区县</td>
            <td class="content">
                <input type="text" name="main.county" class="text max" id="county" alt="allowBlank:false" />
                <input type="hidden" name="countyStringExpression" value="like"/>
            </td>
        </tr>

         -->

        <tr>
            <td class="label">网元类型</td>
            <td class="content">
                <input type="hidden" name="main.netType" id="netType"/>
                <select name="netTypeChoiceExpression" id="netTypeChoiceExpression">
                    <option value="" selected="selected">请选择</option>
                </select>
            </td>
        </tr>

        <tr>
            <td class="label">网元名称</td>
            <td class="content">
                <input type="text" name="main.netName" class="text max" id="netName" alt="allowBlank:false"/>
                <input type="hidden" name="netNameStringExpression" value="like"/>
            </td>
        </tr>

        <tr>
            <td class="label">网元ID</td>
            <td class="content">
                <input type="text" name="main.netId" class="text max" id="netId" alt="allowBlank:false"/>(支持用英文逗号隔开的多个查询)
                <input type="hidden" name="netIdStringExpression" value="like"/>
            </td>
        </tr>
        <!--
		<tr>
	          <td class="label">创建时间</td>
	          <td width="100%">
	           	 	<input type="hidden" name="main.createTime"/>
	                   开始时间
	                <input type="hidden" id="createTimeStartDateExpression" name="createTimeStartDateExpression" value=">="/>
	                <input type="text" name="createTimeStartDate" onclick="popUpCalendar(this, this, null, null, null, true, -1)" readonly="true" class="text" value="${startDate}"/> &nbsp;&nbsp;
	                <input type="hidden" name="createTimeLogicExpression" value="and"/>
	                   结束时间
	                <input type="hidden" id="createTimeEndDateExpression" name="createTimeEndDateExpression" value="<=" > 
	                <input type="text" name="createTimeEndDate" id="createTimeEndDate" onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt="" value="${endDate}"   readonly="true" class="text"/>
	          </td>
        </tr>
		 -->
        <tr>
            <td colspan="4">
                <input type="submit" value="提交" class="btn">
                <input type="reset" value="重置" class="btn">
            </td>
        </tr>
    </table>
</html:form>
<%@ include file="/common/footer_eoms.jsp" %>