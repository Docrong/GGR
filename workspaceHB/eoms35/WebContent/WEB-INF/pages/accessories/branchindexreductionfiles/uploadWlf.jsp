<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="com.boco.eoms.base.util.StaticMethod,
                 com.boco.eoms.commons.accessories.service.*,
                 com.boco.eoms.commons.accessories.model.*,
                 com.boco.eoms.base.util.ApplicationContextHolder,
                 java.util.*,
                 com.boco.eoms.commons.accessories.model.TawCommonsAccessoriesConfig" %>
<fmt:bundle basename="config/ApplicationResources-accessories">
    <%
        String appId = StaticMethod.nullObject2String(request.getParameter("appId"));
        String filelist = StaticMethod.nullObject2String(request.getParameter("filelist"));
        String idField = StaticMethod.nullObject2String(request.getParameter("idField"));
        String sheetFlag = StaticMethod.nullObject2String(request.getParameter("sheetFlag"));
        ITawCommonsAccessoriesConfigManager mgrr =
                (ITawCommonsAccessoriesConfigManager) ApplicationContextHolder.getInstance().getBean("ItawCommonsAccessoriesConfigManager");
        System.out.println("=======appId：" + appId + "==========filelist:" + filelist + "=====idField:" + idField);

        TawCommonsAccessoriesConfig tawCommonsAccessoriesConfig = mgrr
                .getAccessoriesConfigByAppcode(appId);
        String type = tawCommonsAccessoriesConfig.getAllowFileType();
        System.out.println("========type:" + type);

    %>
    <html>
    <head>
        <title>
            <fmt:message key="tawCommonsAccessoriesDetail.title"/>
        </title>
        <%@ include file="/common/meta.jsp" %>
        <link rel="stylesheet" type="text/css" media="all" href="${app}/styles/default/theme.css"/>
        <script src="${app}/scripts/jquery/static/jquery-1.7.2.js" type="text/javascript"></script>
        <script src="${app}/scripts/jquery/static/jquery-form.js" type="text/javascript"></script>
        <script type="text/javascript">

            function confirmForm() {
                //判断是否已经上传了文件
                var input = document.getElementById("uploadexcel");

                //alert(input);
                if (input != undefined && input != null) {
                    alert("只能导入一个excle!");
                    return
                }
                var frm = document.tawCommonsAccessoriesForm;
                var appId = frm.appId.value;
                var filelist = frm.filelist.value;
                var idField = frm.idField.value;
                var filetype = 'xls';
                if (frm.files.value == "") {
                    alert("请选择您要上传的文件!");
                    return;
                }
                var fileName = document.getElementById("files").value;
                // alert(fileName);
                var fileLast = fileName.substring(fileName.lastIndexOf(".") + 1)
                var filetype = 'xlsx'; // 增加声明定义
                if (filetype != null || !filetype.eques("")) {
                    if (filetype != fileLast.toLowerCase()) {

                        alert("请正确选择上传文件的类型。" + "\n" + "文件类型为xlsx");
                        return;
                    }

                    var ope2 = '${operateType}';  // 操作人
                    //alert(ope2);
                    // 判断核减专业是否为空
                    // var ReserveE = parent.document.getElementById("ReserveE").value;

                    var profName = parent.document.getElementById("mainSubtractProfessional").value;
                    var sheetFlag = document.getElementById("sheetFlag").value;
                    //alert(sheetFlag);
                    if (profName == '' || profName == null) {
                        alert("请选择核减专业！")
                        return false;
                    }

                    //判断核减指标类型是否为空 add by wmm 20180201
                    var indexName = parent.document.getElementById("mainSubtractIndexName").value;
                    //alert(indexName);
                    if (indexName == '' || indexName == null) {
                        alert("请选择核减指标类型！")
                        return false;
                    }


                    if (ope2 == '54') {
                        var obj = document.getElementById("iframepage").contentWindow.document;
                        var infoId = obj.getElementsByName('infoId');
                        var listSize = parent.document.getElementById("listsize").value;
                        // 判断条数
                        if (listSize != 0 && listSize > 0 && listSize == total) {
                            //return true;
                        } else {
                            alert("导入的条数与导出的条数不一致，请核查！");
                            return false；
                        }

                    }

                    if (ope2 == '101') {
                        var obj = document.getElementById("iframepage").contentWindow.document;
                        var infoId = obj.getElementsByName('infoId');
                        var listSize = parent.document.getElementById("listsize").value;
                        // 判断条数
                        if (listSize != 0 && listSize > 0 && listSize == total) {
                            //return true;
                        } else {
                            alert("导入的条数与导出的条数不一致，请核查！");
                            return false；
                        }
                    }
                }

                //var alarmSheetId = "";

                // 上傳文件的路徑 branchindexreduction/subtracttablesupload
                var noPassObj = parent.document.getElementById("noPass");
                var noPass = '';
                if (noPassObj != null) {
                    noPass = noPassObj.value;
                }
                var mainReserveAObj = parent.document.getElementById("mainReserveA");
                var mainReserveA = '';
                if (mainReserveAObj != null) {
                    mainReserveA = mainReserveAObj.value;
                }
                var url = "${app}/sheet/branchindexreduction/subtracttablesupload.do?method=upload&profName=" + profName + "&appId=" + appId + "&sheetFlag=" + sheetFlag + "&indexName=" + indexName + "&noPass=" + noPass + "&refSheetId=" + mainReserveA;
                // alert(url);
                var form = jQuery("form[name=tawCommonsAccessoriesForm]");
                var options = {

                    url: url,
                    type: 'post',
                    dataType: "json",
                    success: function (data) {
                        // alert(data);
                        Ext.MessageBox.hide();
                        if (document.getElementById('uploadDiv') != null) {
                            if (data.excel != null) {
                                document.getElementById('uploadDiv').innerHTML = "<input id='uploadexcel' type='checkbox' name='files' value='" + data.excel.accessoriesName + "' class='checkbox'>" +
                                    "<a href='${app}/accessories/tawCommonsAccessoriesConfigs.do?method=download&id=" + data.excel.id + "' class='filelink'>" + data.excel.accessoriesCnName + "</a>";
                            } else {
                                document.getElementById('uploadDiv').innerHTML = "";
                            }
                        }
                        //使用jQuery清空file
                        var file = jQuery("#files");
                        // alert("file==="+file)
                        file.after(file.clone().val(""));
                        file.remove();

                        if (data.result != null && data.result != "") {

                            alert(data.result);
                            //调用父页面的方法实现页面刷新

                            //add by wyf begin
                        } else {
                            var refSheetId = data.refSheetId;
                            // alert(refSheetId);
                            // window.parent.window.callBack(data);
                            var reserveA = document.getElementById("uploadexcel").value;
                            //alert("reserveA");
                            var url = "${app}/sheet/branchindexreduction/branchindexreduction.do?method=search&ifShowOther=no&refSheetId=" + refSheetId;
                            var obj = window.parent.document.getElementById('iframepage');
                            window.parent.document.getElementById('mainReserveA').value = refSheetId;
                            obj.setAttribute("src", url);
                            // alert("导入成功!");
                            window.parent.document.getElementById('importInforList').style.display = '';
                        }
                    },
                    error: function () {
                        alert("导入的文件有误，请检查！");
                    }
                };

                //  frm.action="${app}/accessories/branchindexreductionfiles/uploadWlf.jsp?appId="+appId
                //           +"&filelist="+filelist+"&idField="+idField;

                //frm.submit();
                form.ajaxSubmit(options);
                Ext.MessageBox.wait("正在导入，请稍后！", "正在处理");
            }

            function delay() {
                var i = i + 1;
            }

            function deleteFile() {
                var frm = document.tawCommonsAccessoriesForm;
                var temp = "";
                // alert("測試"); ok!!
                //获取选中的checkbox
                if (jQuery("input[type='checkbox']").attr == true) {
                    alert("请选择您要删除的文件。");
                    return;
                }
                if (document.uploadfile.files.length != null) {
                    for (var i = 0; i < document.uploadfile.files.length; i++) {
                        if (document.uploadfile.files[i].checked) {
                            temp = temp + "'" + document.uploadfile.files[i].value + "',";
                        }
                    }
                } else {
                    if (document.uploadfile.files.checked) {
                        temp = temp + "'" + document.uploadfile.files.value + "',";
                    }
                }
                if (temp.length == 0) {
                    alert("请选择您要删除的文件。");
                    return;
                }
                if (confirm("您确定要删除上传的文件吗?")) {
                    document.getElementById('uploadDiv').innerHTML = "";

                }

            }

        </script>
        <script type="text/javascript" src="${app}/scripts/util/iframe.js"></script>
    </head>

    <body style="background-image:none">
    <div class="upload-box" style="height: 60px;">
        <%
            if (!appId.equals("")) {
                ITawCommonsAccessoriesManager mgr =
                        (ITawCommonsAccessoriesManager) ApplicationContextHolder.getInstance().getBean("ItawCommonsAccessoriesManager");
                List fileList = mgr.saveFile(request, appId, filelist);
                if (fileList != null) {
        %>
        <form name="uploadfile" id="uploadDiv">
            <%
                filelist = "";
                for (int i = 0; i < fileList.size(); i++) {
                    TawCommonsAccessories file = (TawCommonsAccessories) fileList.get(i);
                    String fileName = file.getAccessoriesCnName();
                    filelist = filelist + ",'" + file.getAccessoriesName() + "'";
            %>
            <input type='checkbox' name='files' value='<%=file.getAccessoriesName()%>' class="checkbox">
            <a href='${app}/accessories/tawCommonsAccessoriesConfigs.do?method=download&id=<%=file.getId()%>'
               class="filelink">
                <%=fileName%>
            </a>
            <br>

            <%
                }
                if (filelist.indexOf(",") == 0) {
                    filelist = filelist.substring(1);
                }
            %>
        </form>
        <%
                } else {
                    filelist = "";
                }
            }
        %>

        <form name="tawCommonsAccessoriesForm" enctype="multipart/form-data"
              method="post" id="tawCommonsAccessoriesForm">
            <table>
                <tr>
                    <td colspan="2">

                        </br><input type="file" id="files" name="files" size="30" class="upload" class="file">
                        <input type="text" id="ss" name="ss" value="分公司指标导入" readonly="readonly"
                               style="color:#FF0000;border:none;">
                        <input type="hidden" name="appId" value="<%=appId%>">
                        <input type="hidden" name="filelist" value="<%=filelist%>">
                        <input type="hidden" name="idField" value="<%=idField%>">
                        <input type="hidden" name="sheetFlag" id="sheetFlag" value="<%=sheetFlag%>">
                    </td>
                <tr>
                    <td>
                        <input type="button" class="btn" value="<fmt:message key="tawCommonsAccessories.upload"/>"
                               name="button" onclick="confirmForm();">
                    </td>
                    <td>
                        <input type="button" class="btn" value="<fmt:message key="tawCommonsAccessories.delete"/>"
                               name="button" onclick="deleteFile();">

                    </td>
                </tr>
            </table>
        </form>
    </div>

    <script type="text/javascript" src="${app}/scripts/util/iframe.js"></script>
    <script type="text/javascript">
        try {
            parent.document.getElementById('<%=idField%>').value = "<%=filelist%>";
            if ("<%=filelist%>" != "") parent.v.clear();
        } catch (e) {
        }
        ;
    </script>
    </body>
    </html>
</fmt:bundle>
<%@ include file="/common/footer_eoms.jsp" %>