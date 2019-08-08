<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="java.util.Hashtable" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="com.boco.eoms.workplan.util.TawwpStaticVariable" %>
<%@ page import="com.boco.eoms.workplan.vo.TawwpYearExecuteVO" %>
<%@ page import="com.boco.eoms.workplan.vo.TawwpAddonsTableVO" %>
<%@ page import="com.boco.eoms.common.util.StaticMethod" %>

<script language="javascript">
    <!--
    var onecount;
    onecount = 0;

    subcat = new Array();
    <%
    int cont=0;
    for(int i=1;i<13;i++){
    cont++;
    %>
    subcat[<%=cont%>] = new Array("<bean:message key="itemedit.tawwpyear.warnNumber" /><%=i%><bean:message key="itemedit.tawwpyear.warnMonth" />", "7", "<%=i%>");
    <%}
    for(int i=1;i<7;i++){
    cont++;
    %>
    subcat[<%=cont%>] = new Array("<bean:message key="itemedit.tawwpyear.warnNumber" /><%=i%><bean:message key="itemedit.tawwpyear.warnMonth" />", "6", "<%=i%>");
    <%}for(int i=1;i<5;i++){
    cont++;
    %>
    subcat[<%=cont%>] = new Array("<bean:message key="itemedit.tawwpyear.warnNumber" /><%=i%><bean:message key="itemedit.tawwpyear.warnMonth" />", "9", "<%=i%>");
    <%}for(int i=1;i<4;i++){
    cont++;
    %>
    subcat[<%=cont%>] = new Array("<bean:message key="itemedit.tawwpyear.warnNumber" /><%=i%><bean:message key="itemedit.tawwpyear.warnMonth" />", "5", "<%=i%>");
    <%}
    for(int i=1;i<3;i++){
    cont++;
    %>
    subcat[<%=cont%>] = new Array("<bean:message key="itemedit.tawwpyear.warnNumber" /><%=i%><bean:message key="itemedit.tawwpyear.warnMonth" />", "8", "<%=i%>");
    <%}
    %>

    onecount =<%=cont%>;

    function changecycle(locationid, monthflag) {
        if (locationid < 5) {
            document.tawwpitemedit.monthflag.style.display = "none";
        } else {
            document.tawwpitemedit.monthflag.style.display = "block";
        }
        document.tawwpitemedit.monthflag.length = 0;
        var locationid = locationid;
        var i;

        for (i = 1; i <= onecount; i++) {
            if (subcat[i][1] == locationid) {
                document.tawwpitemedit.monthflag.options[document.tawwpitemedit.monthflag.length] = new Option(subcat[i][0], subcat[i][2]);
            }
        }

        if (document.forms[0].cycle.value == "2") {
            eoms.$('executeDay').show();
        } else {
            eoms.$('executeDay').hide();
        }
    }

    //-->
    function onForm() {
        var _sHeight = 118;
        var _sWidth = 320;
        var sTop = (window.screen.availHeight - _sHeight) / 2;
        var sLeft = (window.screen.availWidth - _sWidth) / 2;
        var sFeatures = "dialogHeight: " + _sHeight + "px; dialogWidth: " + _sWidth + "px; dialogTop: " + sTop + "; dialogLeft: " + sLeft + "; center: Yes; scroll:No;help: No; resizable: No; status: No;";
        window.showModalDialog("../manager/tawgzplanitem/formselect.jsp", window, sFeatures);
    }

    function SubmitCheck() {
        frmReg = document.tawwpitemedit;

        if (frmReg.name.value == '') {
            alert("<bean:message key="itemedit.tawwpyear.warnPlanName" />");
            frmReg.name.focus();
            return false;
        }
        var data = eval('(' + frmReg.saved2.value + ')');
        var executer = "";
        var executeType = "";
        var i = 0;
        for (var j in data) {
            if (i > 2) {
                executer = executer + data[j].id + ",";
                executeType = data[j].nodeType;
            }
            i = i + 1;
        }
        frmReg.executer.value = executer;
        frmReg.executeType.value = executeType;

        return true;
    }

    var tree;

    Ext.onReady(function () {
        var treeAction = '${app}/xtree.do?method=dept';

        //创建xbox对象
        tree = new xbox({
            btnId: 'usert', dlgId: 'dlg',
            treeDataUrl: treeAction, treeRootId: '-1', treeRootText: '部门树', treeChkMode: '', treeChkType: 'dept',
            showChkFldId: 'showd2', saveChkFldId: 'saved2', returnJSON: true
        });

        //添加人员树图和角色树图
        tree.onLayout = function (cal, layout) {
            var config = {
                treeDataUrl: '${app}/xtree.do?method=userFromDept',
                treeRootId: '-1',
                treeRootText: '人员树',
                treeChkType: 'user'
            };
            layout.add('west', cal.newTreePanel(config));
            var config2 = {
                treeDataUrl: '${app}/xtree.do?method=getCptroomTree',
                treeRootId: '-1',
                treeRootText: '机房',
                treeChkType: 'cptroom'
            };
            layout.add('west', cal.newTreePanel(config2));
        };

        //添加只能同时选择一种节点类型的判断
        tree.onBeforeCheck = function (node, checked) {
            if (checked && this.gridData.getCount() > 0) {
                var r = this.gridData.getAt(0);
                if (r.json.nodeType != node.attributes.nodeType) {
                    alert('执行人只能选择一种类型');
                    return;
                }
            }
            return true;
        }

    });

</script>

<%
    TawwpYearExecuteVO tawwpYearExecuteVO = (TawwpYearExecuteVO) request.getAttribute("yearexecute");
    String flag = (String) request.getAttribute("flag");

    ArrayList addonsList = new ArrayList();
    if (request.getAttribute("addonslist") != null) {
        addonsList = (ArrayList) request.getAttribute("addonslist");
    }


    Hashtable hashtable = TawwpStaticVariable.ADDONS_INF;
    Enumeration enumeration = hashtable.keys();
    TawwpAddonsTableVO tawwpAddonsTableVO = null;
    String id = null;
%>


<form name="tawwpitemedit" method="post"
      action="../tawwpyear/itemmodify.do?yearplanid=<%=request.getParameter("yearplanid")%>&yearexecuteid=<%=tawwpYearExecuteVO.getId()%>"
      onsubmit='return SubmitCheck()'>


    <table class="formTable">
        <caption><bean:message key="itemedit.tawwpyear.formTitle"/></caption>
        <tr>
            <td class="label">
                作业项目
            </td>
            <td width="400" colspan="2">
                <input type="text" name="name" size="40" class="text" value="<%=tawwpYearExecuteVO.getName()%>">
            </td>
        </tr>

        <tr>
            <td class="label">
                业务类型
            </td>
            <td width="400" colspan="2">
                <input type="text" name="botype" size="40" class="text" value="<%=tawwpYearExecuteVO.getBotype() %>">
            </td>
        </tr>
        <tr>
            <td class="label">
                执行单位级别
            </td>
            <td width="400" colspan="2">
                <input type="text" name="executedeptlevel" size="40" class="text"
                       value="<%=tawwpYearExecuteVO.getExecutedeptlevel()%>">
            </td>
        </tr>
        <tr>
            <td class="label">
                适用说明
            </td>
            <td width="400" colspan="2">
                <input type="text" name="appdesc" size="40" class="text" value="<%=tawwpYearExecuteVO.getAppdesc() %>">
            </td>
        </tr>


        <tr>
            <td class="label">
                执行周期
            </td>
            <td width="50">

                <eoms:dict key="dict-workplan" dictId="cycle" isQuery="true"
                           defaultId="<%=tawwpYearExecuteVO.getCycle()%>"
                           onchange="changecycle(document.tawwpitemedit.cycle.options[document.tawwpitemedit.cycle.selectedIndex].value)"
                           selectId="cycle" beanId="selectXML"/>
                <br>
                <%if (tawwpYearExecuteVO.getCycle().equals("2")) {%>
                <select size='1' name='executeDay' id='executeDay' class='select'>
                    <option value=''>请选择</option>
                    <option value='1'>周一</option>
                    <option value='2'>周二</option>
                    <option value='3'>周三</option>
                    <option value='4'>周四</option>
                    <option value='5'>周五</option>
                    <option value='6'>周六</option>
                    <option value='7'>周日</option>
                </select>
                <%} else {%>
                <select size='1' name='executeDay' id='executeDay' class='select' style='display:none'>
                    <option value=''>请选择</option>
                    <option value='1'>周一</option>
                    <option value='2'>周二</option>
                    <option value='3'>周三</option>
                    <option value='4'>周四</option>
                    <option value='5'>周五</option>
                    <option value='6'>周六</option>
                    <option value='7'>周日</option>
                </select>
                <%}%>

            </td>
            <td width="350">
                <select size="1" name="monthflag" class="text" style="display:none">
                </select>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message key="itemedit.tawwpyear.formFormat"/>
            </td>
            <td width="400" colspan="2">
                <input type="text" name="format" size="40" class="text" value="<%=tawwpYearExecuteVO.getFormat()%>">
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message key="itemedit.tawwpyear.formHoliday"/>
            </td>
            <td width="400" colspan="2">
                <input type=radio name='isHoliday' value='0'
                    <%   if(tawwpYearExecuteVO.getIsHoliday()==null||tawwpYearExecuteVO.getIsHoliday().equals("0")) {%>
                       checked
                    <%   } %>
                >是&nbsp;&nbsp;<input type=radio name='isHoliday' value='1'
                <%   if(tawwpYearExecuteVO.getIsHoliday()!=null&&tawwpYearExecuteVO.getIsHoliday().equals("1")) {%>
                                     checked
                <%   } %>
            >否
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message key="itemedit.tawwpyear.formWeekend"/>
            </td>
            <td width="400" colspan="2">
                <input type=radio name='isWeekend' value='0'
                    <%   if(tawwpYearExecuteVO.getIsWeekend()==null||tawwpYearExecuteVO.getIsWeekend().equals("0")) {%>
                       checked
                    <%   } %>
                >是&nbsp;&nbsp;<input type=radio name='isWeekend' value='1'
                <%   if(tawwpYearExecuteVO.getIsWeekend()!=null&&tawwpYearExecuteVO.getIsWeekend().equals("1")) {%>
                                     checked
                <%   } %>

            >否
            </td>
        </tr>
        <tr>
            <td class="label">
                记录模版
            </td>
            <td width="400" colspan="2">
                <select name='formid' value='0' class="select">
                    <option value='0'><bean:message key="itemedit.tawwpyear.selAddons"/></option>
                    <%
                        for (int j = 0; j < addonsList.size(); j++) {
                            tawwpAddonsTableVO = (TawwpAddonsTableVO) addonsList.get(j);
                    %>
                    <option value='<%=tawwpAddonsTableVO.getId()%>'><%=tawwpAddonsTableVO.getName()%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </td>
        </tr>
        <tr>
            <td class="label">
                是否必须上传附件
            </td>
            <td>
                <input type=radio name='fileFlag' value='1'
                    <%   if(tawwpYearExecuteVO.getFileFlag().equals("1")) {%>
                       checked
                    <%   } %>>是
                <input type=radio name='fileFlag' value='0'
                    <%   if(tawwpYearExecuteVO.getFileFlag().equals("0")) {%>
                       checked
                    <%   } %>>否
            </td>
        </tr>
        <!--
        <tr>
           <td class="label">
                执行人
            </td>
            <td colspan="2" >
                 <input type="button" name="usert" id="usert" value="请选择人员" class="btn"/><br>
                  <textarea id="showd2" class="textarea" ></textarea>
                 <input type="hidden" name="saved2" id="saved2"/>
                 <input type="hidden" name="executer" id="executer"/>
                 <input type="hidden" name="executeType" id="executeType"/>
           </td>
        </tr>
         -->
        <tr>
            <td class="label">
                执行帮助
            </td>
            <td colspan="2">
                <textarea name="remark" class="textarea max"><%=tawwpYearExecuteVO.getRemark()%></textarea>
            </td>
        </tr>
    </table>
    <br>
    <input type="submit" value="<bean:message key="itemedit.tawwpyear.btnSubmit" />" name="B1" Class="submit">
    <input type="button" value="<bean:message key="itemedit.tawwpyear.btnBack" />"
           onclick="javascript:window.history.back();" class="button">

    <script language="javascript">
        document.forms[0].cycle.value = "<%=tawwpYearExecuteVO.getCycle()%>";
        document.forms[0].formid.value = "<%=tawwpYearExecuteVO.getFormId()%>";
        document.forms[0].executeDay.value = '<%=StaticMethod.null2String(tawwpYearExecuteVO.getExecuteDay())%>'
        changecycle(<%=tawwpYearExecuteVO.getCycle()%>,
            <%
              if(tawwpYearExecuteVO.getMonthFlag()==null||tawwpYearExecuteVO.getMonthFlag().equals("")){
                      out.print("0");
              }else{
                       out.print(tawwpYearExecuteVO.getMonthFlag());
              }
            %>);
    </script>

</form>
<%@ include file="/common/footer_eoms.jsp" %>


