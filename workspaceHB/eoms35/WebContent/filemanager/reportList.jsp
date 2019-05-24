<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
    String obj_name = "", obj_name_id = "", dept_region_id, input_type = "", sign = "", region = "", dept = "", obj_name_id_other = "";
    String canceldept = "";
    if (request.getParameter("obj_name") != null)
        obj_name = request.getParameter("obj_name").toString();
    obj_name_id = obj_name + "_id";
    obj_name_id_other = obj_name_id + "_other";
    dept_region_id = obj_name + "_region";

    if (request.getParameter("input_type") != null) input_type = request.getParameter("input_type").toString();
    if (request.getParameter("sign") != null) sign = request.getParameter("sign").toString();
    if (request.getParameter("region") != null) region = request.getParameter("region").toString();
    if (request.getParameter("dept") != null) dept = request.getParameter("dept").toString();
    if (request.getParameter("canceldept") != null)
        canceldept = request.getParameter("canceldept").toString();
    if (canceldept.equals(""))
        canceldept = "false";
    boolean chgType = true;
    if (obj_name.equalsIgnoreCase("assist_dept")) chgType = false;
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="MS.LOCALE" content="ZH-CN">
    <LINK rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/report/deeptree.css" />
    <script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/xmltree/xtree.js"></script>
</head>
<title>选择报表</title>
<body  bgcolor="f1f1f1" onload="initDept()">
<table width="100%">
    <tr><td colspan="3" width="80%"   bgcolor="f1f1f1">
        <Div id=deptTree name=deptTree
             style="position:relative; left:0px; top:10px; width:100%; height:expression(document.body.clientHeight-60); z-index:1; overflow: auto;margin-left:10px;border:2 inset;font-size:9pt;">

        </div></td></tr>
    <tr><td colspan="3"><br/></td></tr>
    <tr>
        <td>
            <button onclick="reload()">刷新</button>
        </td>
        <td colspan="2" align="right">
            <button onclick="submit();">确定</button>
            <button onclick="window.close()">取消</button>
            <%--	<button onclick="passVal('','','')">清除</button>--%>
        </td></tr>
</table>
</body>
</html>
<script language="JavaScript">
var xmlManage = "<%=request.getContextPath()%>/filemanager/reportListServer.jsp";
var urlXsl = "<%=request.getContextPath()%>/xmltree/transrsxml_dept.xsl";
var loadStr = "<infoTree input_type='<%=input_type%>' sign='<%=sign%>' reload='false' region='<%=region%>' dept='<%=dept%>' chgType='<%=chgType%>' canceldept='<%=canceldept%>' />";
ReceiveStr(loadStr);
function reXml(temp) {
    if (temp != null) {
        xmlDom = temp;
        document.all.deptTree.innerHTML = "<nobr>" + Transfrom("", urlXsl, "") + "</nobr>";
    }
}
function reload() {
    var loadStr = "<infoTree input_type='<%=input_type%>' sign='<%=sign%>' reload='true' region='<%=region%>' dept='<%=dept%>' chgType='<%=chgType%>' canceldept='<%=canceldept%>' />";
    ReceiveStr(loadStr);
}
function NodeInfo(node)
{
    var Ele = node.nextSibling;
    var sign = Ele.checked;
    if (sign) {
        Ele.checked = false;
    } else {
        Ele.checked = true;
    }
    ExpendNode(node.parentElement.id);
}
function SameTime()
{
    return true;
}
function ExpendNode(Id)
{
    var nodeState = document.all[Id].state;
    if (nodeState == "closed")
        SpreadNode(Id, imgminus);
    else if (nodeState == "open")
        CloseNode(Id, imgplus);
}
function submit() {
    var id_str = "";
    var name_str = "";
    var dept_ids_str = "";
    var region_ids = "";
    //var chk_eles = document.getElementsByTagName("input");
    var chk_eles = document.getElementsByName("input_colls");
    var len = chk_eles.length;
    if (len) {
        for (var i = 0; i < len; i++) {
            var chk_ele = chk_eles[i];
            if (chk_ele.checked) {
                id_str = id_str + "," + chk_ele.id;
                name_str = name_str + "," + chk_ele.app_name;
                if (chk_ele.region_id)
                    region_ids = region_ids + "," + chk_ele.region_id;
                if (chk_ele.dept_ids)
                    dept_ids_str += chk_ele.dept_ids;
            }
        }
    }
    if (id_str.length > 0) id_str = id_str.substring(1);
    if (name_str.length > 0) name_str = name_str.substring(1);
    if (region_ids.length > 0) region_ids = region_ids.substring(1);
    passVal(id_str, name_str, region_ids, dept_ids_str);
}

function passVal(selectDeptsId, selectDepts, region_ids, dept_ids_str) {
    var sData = dialogArguments;
    sData.document.forms[0].<%=obj_name%>.value = selectDepts;
    sData.document.forms[0].<%=obj_name_id%>.value = selectDeptsId;
    //alert(region_ids);
    if (sData.document.forms[0].<%=dept_region_id%>)
        sData.document.forms[0].<%=dept_region_id%>.value = region_ids;
    if (sData.document.forms[0].<%=obj_name_id_other%>)
        sData.document.forms[0].<%=obj_name_id_other%>.value = dept_ids_str;
    window.close();
}

function initDept(){
    var sData = dialogArguments;
    var selectDepts = sData.document.forms[0].<%=obj_name_id%>.value;
    if(selectDepts.length>0){
      var inputEles=document.getElementsByName("input_colls");
      var dept_arr=selectDepts.split(",");
        for(var j=0;j<inputEles.length;j++){
            //alert(selectDepts);
            //dept_arr = selectDepts.split(",");
            var len = dept_arr.length;
            if(len){
                for(var i =0;i<len;i++){
                    if(inputEles[j].id==dept_arr[i]){
                        inputEles[j].checked=true;
                        //selectDepts=selectDepts.substring(selectDepts.indexOf(",")+1);
                        break;
                     }
                }
            }else return;
         }
      }
  }
</script>