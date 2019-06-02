<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<html:html>
    <head>
tp-equiv="Content-Type" content="text/html; charset=utf-8"/>

        <title>报表统计条件</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/wsstyle.css" type="text/css">
        
        
        <script type="text/javascript" src="<%=request.getContextPath()%>/css/finallytree.js"></script>
        <script language="JavaScript" type="text/javascript"
                src="<%=request.getContextPath()%>/xmltree/xtree.js"></script>
        <script language="javascript">
            //弹出派发树窗口
            function selectTree() {
                dWinOrnaments = "status:no;scroll:no;resizable:no;dialogHeight:450px;dialogWidth:480px;";
                dWin = showModalDialog('<%=request.getContextPath()%>/css/listbox/listbox_stat.jsp?sort=1-4&checkall=no&user=no&selectself=yes', window, dWinOrnaments);
            }
            function showReportTree(objName, inputType, ctrlType, regionSign, deptSign, cancel_dept) {
                window.showModalDialog("reportList.jsp?obj_name=" + objName + "&input_type=" + inputType + "&sign=" + ctrlType + "&region=" + regionSign + "&dept=" + deptSign + "&canceldept=" + cancel_dept, window, "dialogWidth:450px;dialogHeight:550px;status:no;scrollBars:no;Resizeable:yes");
            }
        </script>

<script type="text/javascript" src="<%=request.getContextPath()%>/css/finallytree.js"></script>
  <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/scripts/local/zh_CN.js"></script>  
  <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/scripts/base/eoms.js"></script>
  <script type="text/javascript">
	eoms.appPath = "<%=request.getContextPath()%>";
  </script>
  <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/styles/default/theme.css" />
  <!-- EXT LIBS verson 1.1 -->
  <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/ext/adapter/ext/ext-base.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/ext/ext-all.js"></script>
  <script type="text/javascript" charset="utf-8"  src="<%=request.getContextPath()%>/scripts/adapter/ext-ext.js"></script>
  <script type="text/javascript" charset="utf-8"  src="<%=request.getContextPath()%>/scripts/ext/source/locale/ext-lang-zh_CN.js"></script>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/ext/resources/css/ext-all.css" />
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/default/ext-adpter.css" />
  <!-- EXT LIBS END -->

  <script type="text/javascript">

	//depttree
	var deptTree;
	
	function FCKeditor_OnComplete(editorInstance) {
		window.status = editorInstance.Description;
	}
		
	
	Ext.onReady(function(){
		deptViewer = new Ext.JsonView("view",
			'<div class="viewlistitem-{nodeType}">{name}</div>',
			{ 
				emptyText : '<div>没有选择项目</div>'
			}
		);
		var s='[]';
		deptViewer.jsonData = eoms.JSONDecode(s);
		deptViewer.refresh();
		var	treeAction='/eoms/xtree.do?method=getDeptSubRoleUserTree';
		deptTree = new xbox({
			btnId:'clkOrg',dlgId:'hello-dlg',
			treeDataUrl:treeAction,treeRootId:'-1',treeRootText:'组织结构选择',treeChkMode:'',treeChkType:'dept',
			showChkFldId:'sort1_text',saveChkFldId:'sort1_deptid',viewer:deptViewer
		});
	
	});
	
</script>



<script type="text/javascript">

	//depttree
	var deptTree1;
	
	function FCKeditor_OnComplete(editorInstance) {
		window.status = editorInstance.Description;
	}
		
	
	Ext.onReady(function(){
		deptViewer1 = new Ext.JsonView("view2",
			'<div class="viewlistitem-{nodeType}">{name}</div>',
			{ 
				emptyText : '<div>没有选择项目</div>'
			}
		);
		var s2='[]';
		deptViewer1.jsonData = eoms.JSONDecode(s2);
		deptViewer1.refresh();
		var	treeAction='/eoms/xtree.do?method=getDeptSubRoleUserTree';
		deptTree1 = new xbox({
			btnId:'clkOrg2',dlgId:'hello-dlg',
			treeDataUrl:treeAction,treeRootId:'-1',treeRootText:'组织结构选择',treeChkMode:'',treeChkType:'dept',
			showChkFldId:'sort2_text',saveChkFldId:'sort2_deptid',viewer:deptViewer1
		});
	
	});
	
</script>



  <STYLE>
    .InputComponent{
        behavior: url("../behavior/InputComponent.htc");
    }
    </STYLE>
    </head>


    <body>
    <br/>
    <br/>
    <table width="100%" id="listTitle">
        <tr>
            <td>&nbsp;&nbsp;<font size="2"><b>报表统计</b></font></TD>
        </tr>
    </table>
    <br/>
        <%--enctype="multipart/form-data"--%>
    <html:form action="/StatReportMgrAction.do" method="post">
        <html:hidden property="act"/>
        <table width="100%" class="formTable">
            <TR>
                <td class="label">
                    <b>统计类型</b>
                </td>
                <td>
                    <html:radio property="statType" value="1"/>按部门统计 &nbsp; &nbsp; &nbsp; &nbsp;
                    <html:radio property="statType" value="2"/>报表名称统计
                </td>
            </TR>
            <TR>
                <td class="label">
                    <b>开始时间</b>
                </td>
                <td>
                    <eoms:SelectTime name="startTime" formName="StatForm" day="-30"/>
                </td>
            </TR>
            <TR>
                <td class="label">
                    <b>截止时间</b>
                </td>
                <td>
                    <eoms:SelectTime name="endTime" formName="StatForm" day="1"/>
                </td>
            </TR>
            <TR>
                <td class="label">
                    <b>汇总部门</b>
                </td>
                <td>
		<input type="text"  readonly id ="sort1_text" name="sort1_text" class="text"/>
		<input type="hidden" id="sort1_deptid" name="sort1_deptid"/>
			<input type="button" id="clkOrg" name="clkOrg" value="选择部门" class="button"/>
			<div id="view"></div>
                </td>
            </TR>
            <TR>
                <td noWrap width="10%" class="label"><b>上报部门</b> </td>

                <td>
   		<input type="text"  readonly id ="sort2_text" name="sort2_text" class="text"/>
		<input type="hidden" id="sort2_deptid" name="sort2_deptid"/>
			<input type="button" id="clkOrg2" name="clkOrg2" value="选择部门" class="button"/>
			<div id="view2"></div>
                </td>
            </TR>
            <TR>
                <td noWrap width="10%" class="label"><b>报表选择</b></td>
                <td>
                    <textarea rows="3" cols="68" class="textarea max" name='report' id='report' style="cursor:hand"
                           onclick="showReportTree(this.name,'checkbox','accept',false,true,false);" type="_moz">不选择</textArea>
                    <input  type="hidden" id="report_id" name="report_id" value=""/><br>
                </td>
            </TR>
        </table>
        <br/>
        <TABLE>
            <TR>
                <TD>
                    <input type="button" class="button" name="cancel" value="取消"/>
                    &nbsp;
                    <input type="submit" class="button" name="confirm"  value="开始统计"/>
                </TD>
            </TR>
        </TABLE>

    </html:form>
    </body>

</html:html>
