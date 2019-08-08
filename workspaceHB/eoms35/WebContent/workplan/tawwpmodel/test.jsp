<%@ page language="java" pageEncoding="UTF-8" %>


<html>
<head>

    <title>在此处插入标题</title>

    <link rel="stylesheet" href="ext-all.css" type="text/css">
    <link rel="stylesheet" href="LovCombo.css" type="text/css">
    <link rel="stylesheet" href="multiselect.css" type="text/css">
    <script type="text/javascript" src="ext-all-debug.js"></script>
    <script type="text/javascript" src="LovCombo.js"></script>
    <script type="text/javascript">
        Ext.namespace("Ext.ux.form");
        var ds = new Ext.data.JsonStore({
            autoLoad: true,
            url: "checkCombo.json",
            fields: [
                {name: 'VALUE'},
                {name: 'TEXT'}
            ],
            root: "datasource"
        });
        Ext.ux.form.LovCombo = Ext.form.LovCombo || Ext.ux.form.LovCombo;
        var combox = new Ext.ux.form.LovCombo({
            renderTo: Ext.getBody(),
            store: ds,
            mode: "local",
            fieldLabel: "测试",
            displayField: "TEXT",
            valueField: "VALUE",
            hiddenName: "ces",
            name: "ces",
            triggerAction: "all",
            id: "cc",
            //width         : 220,
            //autoSelect        : true,
            value: "8960,8970,8964,8965,8967,8980",
            //lazyInit      : true,
            showSelectAll: true,
            resizable: true
        });

    </script>

</head>
<body>
<div class="x-combo-list-item"></div>
</body>
</html>