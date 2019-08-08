<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_innerpage.jsp" %>
<%@ include file="/common/extlibs.jsp" %>
<script type="text/javascript" src="js/winbox.js"></script>
<link rel="stylesheet" type="text/css" href="css/winbox.css"/>

<div id="box" class="box">
    <div id="tree" style="overflow:auto; height:300px;border:1px solid #c3daf9;"></div>
    <div id="btn" style="margin:5px 0 5px 120px"></div>
    <script type="text/javascript">
        Ext.onReady(function () {
            //var el = Ext.get("box");
            //el.setStyle({width:"100%",height:"100%"});
            Ext.lib.Ajax.defaultPostHeader += '; charset=utf-8';
            winbox.init({
                el: "tree",
                rootId: -1,
                rootText: "上海移动",
                url: "${app}/xtree.do?method=userFromDept",
                checktype: "dept,user"
            });
        });

    </script>
</div>
<%@ include file="/common/footer_eoms_innerpage.jsp" %>