<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="com.boco.eoms.common.tree.*" %>
<%@ page import="com.boco.eoms.common.util.StaticMethod" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page language="java" import="java.util.*,java.lang.*,java.io.* " %>
<%
    String regionId = StaticMethod.nullObject2String(request.getAttribute("REGIONID"), "1");
    String path = request.getContextPath();
    WKTree wk_tree = new WKTree();
    String strTree = wk_tree.strWKTree(Integer.parseInt(regionId));
    String url = "";
    String dept1 = "";
    String wsClass = "-1";
    String sdomIds = StaticMethod.nullObject2String(request.getAttribute("SDOMIDS"));

    RelativeDrop rel = new RelativeDrop();
    String users = rel.strRelativeDrop(sdomIds);

    String userName = StaticMethod.nullObject2String(request.getAttribute("userName"), "");
%>


<script language="JavaScript">

    var User = new Array;
    <%=users%>


    function ifQuery(temp, temp3, tempId, tempName) {
        var i;
        var flag = -100;
        var id = window.projectForm.dept_id.value;

        if (temp.length == null) {
            if (temp.value == id) {
                temp.checked = false;
                window.projectForm.dept_id.value = 0;
                window.projectForm.dept_name.value = "";
            } else {
                window.projectForm.dept_id.value = temp.value;
                window.projectForm.dept_name.value = temp3;
            }
        } else {
            for (i = 0; i < temp.length; i++) {
                if (temp[i].checked == true) {
                    if (temp[i].value == id) {
                        temp[i].checked = false;
                    } else {
                        flag = i;
                    }
                }
            }

            if (flag == -100) {
                window.projectForm.dept_id.value = 0;
                window.projectForm.dept_name.value = "";
            } else {
                window.projectForm.dept_id.value = temp[flag].value;
                window.projectForm.dept_name.value = temp3;
            }
        }

        var dept_id = window.projectForm.dept_id.value;
        var sel_sprlen = document.projectForm.project_executor.options.length - 1;
        for (j = sel_sprlen; j >= 0; j--) {
            document.all.projectForm.project_executor.options[j] = null;
        }

        var m = 0;
        document.projectForm.project_executor.options[m] = new Option("", "");
        if ((dept_id != "0") && (User[dept_id])) {
            var i;
            var k = User[dept_id].length;
            for (i = 0; i * 2 < k; i++) {
                var tempoption = new Option(User[dept_id][i * 2], User[dept_id][i * 2 + 1]);
                document.projectForm.project_executor.options[++m] = tempoption;
            }
        }
    }
</script>
<head>
    <html>
    <head>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
        <script type="text/javascript" src="<%=path%>/css/finallytree.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/css/table_calendar.js"></script>
    </head>
    <script language="javascript">
        //�ɷ���
        function selectTree() {
            dWinOrnaments = "status:no;scroll:no;resizable:no;dialogHeight:450px;dialogWidth:480px;";
            dWin = showModalDialog('<%=request.getContextPath()%>/css/listbox/listbox_inf.jsp?sort=3&user=yes&checkall=no&selectself=yes', window, dWinOrnaments);
        }


        function onSave() {
            if ((projectForm.project_name.value) == "") {
                alert("��Ŀ���Ʋ���Ϊ��");
                return false;
            }
            if ((projectForm.project_code.value) == "") {
                alert("��ѡ����Ŀ���");
                return false;
            }
            if ((projectForm.project_level.value) == "") {
                alert("��ѡ�����׳̶�");
                return false;
            }
            if ((projectForm.project_state.value) == "") {
                alert("��ѡ������̶�");
                return false;
            }
            if ((projectForm.project_instancy.value) == "") {
                alert("��ѡ����Ҫ�̶�");
                return false;
            }
            if ((projectForm.project_comp_time.value) == "") {
                alert("ѡ�����ʱ��");
                return false;
            }
            if ((projectForm.dept_name.value) == "") {
                alert("ѡ��ִ�в���");
                return false;
            }
            if ((projectForm.project_executor.value) == "") {
                alert("ѡ��ִ����");
                return false;
            }

            projectForm.submit();
            return true;

        }
    </script>
    <title>��Ŀ����</title>

<body>
<style type="text/css">
    body {
        font-size: 9pt;
        color: #000000;
        LINE-HEIGHT: 18px
    }

    #tree {
        position: absolute;
        visibility: hidden;
        left: 72%;
        top: 10%;
        z-index: 2;
        background-color: #ECF2FE;
        padding: 12px;
        border-top: 1px solid #FeFeFe;
        border-left: 1px solid #FeFeFe;
        border-right: 3px solid #8E9295;
        border-bottom: 3px solid #8E9295;
    }
</style>


<html:form method="post" action="/project/updatedone">
    <SCRIPT language=javascript>
        <!--
        //������������������ʾ
        var outObject;
        var old_dd = null;
        writeCalender();
        bltSetDay(bltTheYear, bltTheMonth);
        //-->
    </SCRIPT>
    <input type="hidden" name="path" id="path" value="<%=path%>">
    <input type="hidden" name="sdomids" id="sdomids" value="<%=sdomIds%>">
    <div id="tree">
        <font face="����" style="font-size: 9pt" COLOR="#990000"><B><bean:message
                key="label.deptTree"/></B>&nbsp;[&nbsp;<A HREF="javascript:headerDisplay(0);"><bean:message
                key="label.hide"/></A>&nbsp;]</FONT>
        <BR>
        <script type="text/javascript">
            var path = document.all.path.value;
            var domids = document.all.sdomids.value;
            var Tree = new Array;
            <%=strTree%>
            if (domids == "")
                createTree9(Tree, <%=regionId%>, 0, path, "", "",
                    "window.projectForm.cid", "ifQuery",
                    "window.projectForm.dept_id",
                    "window.projectForm.dept_name", "tree");
            else
                createTree10(Tree, <%=regionId%>, 0, path, domids, "",
                    "window.tawRmUserForm.cid", "ifQuery",
                    "window.projectForm.dept_id",
                    "window.projectForm.dept_name", "tree");

        </script>
    </div>

    <center>
        <eoms:DictType typeName="project_code"/>
        <br>
        <table border="0" width="95%" cellspacing="0">
            <tr>
                <td width="106%" class="table_title" align="center">
                    <center>�޸���Ŀ��Ϣ</center>
                </td>
            </tr>
        </table>
        <table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
            <tr class="tr_show">
                <td width="25%" height="25" class="clsfth">
                    <center>��Ŀ����</center>
                </td>
                <td width="70%" height="25">
                    <html:text property="project_name" size="30" name="projectForm" styleClass="clstext"/></td>
            </tr>
            <tr class="tr_show">
                <td width="25%" height="25" class="clsfth">
                    <center>
                        ��Ŀ���
                    </center>
                </td>
                <td width="75%" height="25">

                    <html:select property="project_code" style="width: 3.6cm;">

                        <html:options collection="project_code" property="value" labelProperty="label"/>
                    </html:select><font color=crimson>*</font>
                </td>
            </tr>
            <%
                String pro_code = StaticMethod.getNodeName("SYSTEM.DICTTYPE.project_code");
            %>


            <html:hidden name="projectForm" property="project_id"/>


            <tr class="tr_show">
                <td width="25%" height="25" class="clsfth">
                    <center>
                        ���׳̶�
                    </center>
                </td>
                <td width="75%" height="25">
                    <html:select styleClass="clstext" property="project_level" style="width: 3.6cm;">
                        <bean:define id="level" name="projectForm" property="project_level" type="java.lang.String"/>

                        <option value=""<% if ("".equals(level)) out.print("selected");%> >��ѡ��</option>
                        <option value="��"<% if ("��".equals(level)) out.print("selected");%>  >��</option>
                        <option value="һ��"<% if ("һ��".equals(level)) out.print("selected");%>>һ��</option>
                        <option value="����"<% if ("����".equals(level)) out.print("selected");%>>����</option>
                    </html:select> <font color=crimson>*</font>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="25%" height="25" class="clsfth">
                    <center>
                        �����̶�
                    </center>
                </td>
                <td width="75%" height="25">
                    <html:select styleClass="clstext" property="project_state" style="width: 3.6cm;">
                        <bean:define id="state" name="projectForm" property="project_state" type="java.lang.String"/>
                        <option value=""<% if ("".equals(state)) out.print("selected");%>>��ѡ��</option>
                        <option value="�ǳ�����"<% if ("�ǳ�����".equals(state)) out.print("selected");%>>�ǳ�����</option>
                        <option value="����"<% if ("����".equals(state)) out.print("selected");%>>����</option>
                        <option value="������"<% if ("������".equals(state)) out.print("selected");%>>������</option>
                    </html:select> <font color=crimson>*</font>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="25%" height="25" class="clsfth">
                    <center>
                        ��Ҫ�̶�
                    </center>
                </td>
                <td width="75%" height="25">
                    <html:select styleClass="clstext" property="project_instancy" style="width: 3.6cm;">
                        <bean:define id="instancy" name="projectForm" property="project_instancy"
                                     type="java.lang.String"/>
                        <option value=""<% if ("".equals(instancy)) out.print("selected");%>>��ѡ��</option>
                        <option value="�ǳ���Ҫ"<% if ("�ǳ���Ҫ".equals(instancy)) out.print("selected");%>>�ǳ���Ҫ</option>
                        <option value="����Ҫ"<% if ("����Ҫ".equals(instancy)) out.print("selected");%>>����Ҫ</option>
                        <option value="����Ҫ"<% if ("����Ҫ".equals(instancy)) out.print("selected");%>>����Ҫ</option>
                    </html:select>
                    <font color=crimson>*</font>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="25%" height="25" class="clsfth">
                    <center>
                        ��ʼʱ��
                    </center>
                </td>
                <td width="75%" height="25">
                    <html:text styleClass="clstext" property="project_exec_time" readonly="true" size="20"
                               onfocus="setday(this)"/><font color=crimson>*</font>

                </td>
            </tr>
            <tr class="tr_show">
                <td width="25%" height="25" class="clsfth">
                    <center>
                        ���ʱ��
                    </center>
                </td>
                <td width="75%" height="25">
                    <html:text styleClass="clstext" property="project_comp_time" readonly="true" size="20"
                               onfocus="setday(this)"/><font color=crimson>*</font>

                </td>
            </tr>
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">
                    <center>ִ�в���</center>
                </td>
                <td width="70%" height="25">
                    <html:text property="dept_name" size="30" styleClass="clstext" readonly="true"/>
                    <A HREF="javascript:headerDisplay(1);"><font face="����" style="font-size: 9pt"><bean:message
                            key="label.deptTree"/></FONT></A>
                    <html:hidden property="dept_id"/><font color=crimson>*</font>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">
                    <center>ִ����</center>
                </td>
                <td width="70%" height="25">
                    <bean:define id="executor_name" name="projectForm" property="project_executor_name"
                                 type="java.lang.String"/>
                    <bean:define id="executor" name="projectForm" property="project_executor" type="java.lang.String"/>
                    <select size=1 name="project_executor" style="width:220;z-index:-1">
                        <option value="<%=executor%>"><%=executor_name%>
                        </option>
                    </select><font color=crimson>*</font>
                </td>
            </tr>

            <tr class="tr_show">
                <td width="30%" height="25" class="clsfth">
                    <center>�����</center>
                </td>
                <td colspan=3>

                    <bean:define id="check_project_group" name="projectForm" property="check_project_group"
                                 type="java.lang.String"/>

                    <bean:message key="Worksheet.audit"/>:<span id="sort1_text"><%=check_project_group%></span>
                    <INPUT TYPE="hidden" name="sort1_deptid" id="sort1_deptid" value="">
                    <INPUT TYPE="hidden" name="sort1_userid" id="sort1_userid" value="">
                    <INPUT TYPE="hidden" name="check_project_group" id="sort1_text1" value="">
                    <BR>
                    <INPUT type="button" class="clsbtn2" value=" ѡ�� " name="button" Onclick="selectTree();">
                </td>
            </tr>
            <tr class="tr_show">
                <td width="25%" height="25" class="clsfth">
                    <center>
                        ��Ŀ����
                    </center>
                </td>
                <td width="75%" height="25">
                    <html:textarea property="project_desc" rows="4" style="width:100%" name="projectForm" title="��������"/>
                </td>
            </tr>

        </table>
        <table border="0" width="100%" cellspacing="0" align="center">
            <tr>
                <td width="100%" colspan="2" height="32" align="right">
                    <input Class="clsbtn2" type="button" name="tosubmit" value="<bean:message key="label.save"/>"
                           onclick="return onSave()">
                    &nbsp;
                    <input Class="clsbtn2" type="reset" name="toreset" value="<bean:message key="label.reset"/>">
                </td>
            </tr>
        </table>
    </center>
</html:form>

</body>

</html>

