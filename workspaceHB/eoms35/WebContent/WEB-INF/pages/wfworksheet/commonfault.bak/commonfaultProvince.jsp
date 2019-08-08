<%@ page language="java" errorPage="/error.jsp" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://acegisecurity.org/authz" prefix="authz" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean-el" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache" %>
<%@ taglib uri="/WEB-INF/eoms.tld" prefix="eoms" %>
<%@ taglib uri="/WEB-INF/tlds/priv.tld" prefix="priv" %>

<html>
<head>
    <title><fmt:message key="webapp.name"/></title>
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>


    <STYLE>
        /* A CSS Framework by Mike Stenhouse of Content with Style */

        /* clearing */
        /* 用于非IE浏览器清除浮动,在浮动内容后加<div class="clear"></div> */
        .stretch,
        .clear {
            clear: both;
            height: 1px;
            margin: 0;
            padding: 0;
            font-size: 15px;
            line-height: 1px;
        }

        /* 用于非IE浏览器清除浮动,不用加div */
        .clearfix:after {
            content: ".";
            display: block;
            height: 0;
            clear: both;
            visibility: hidden;
        }

        * html > body .clearfix {
            display: inline-block;
            width: 100%;
        }

        * html .clearfix {
            /* Hides from IE-mac \*/
            height: 1%;
            /* End hide from IE-mac */
        }

        /* end clearing */


        /* replace */
        .replace {
            display: block;

            background-repeat: no-repeat;
            background-position: left top;
            background-color: transparent;
        }

        /* tidy these up */
        .replace * {
            text-indent: -10000px;
            display: block;

            background-repeat: no-repeat;
            background-position: left top;
            background-color: transparent;
        }

        .replace a {
            text-indent: 0;
        }

        .replace a span {
            text-indent: -10000px;
        }

        /* end replace */


        /* accessibility */
        span.accesskey {
            text-decoration: none;
        }

        .accessibility {
            position: absolute;
            top: -999em;
            left: -999em;
        }

        /* end accessibility */

    </STYLE>

    <STYLE>

        /* NAV BAR AT THE TOP AND TWO COLUMNS OF CONTENT */
        div#content {
            width: 100%;
            margin: 0 auto 20px auto;
            padding: 0;
            text-align: left;
        }

        div#main {
            float: left;
            width: 100%;
            display: inline;
        }

        div#sub {
            float: right;
            width: 120px;
        }

        div#sub ul#local li a {
            margin-left: 40px;
        }

        div#sub ul#local > li a {
            margin-left: -5px !important;
        }

        div#nav {
            position: absolute;
            top: -25px;
            left: 0px;
            width: 100%;

            text-align: left;
        }

        /* END CONTENT */

    </STYLE>


    <STYLE>
        /* A CSS Framework by Mike Stenhouse of Content with Style

     Color pallete:
             #444        - dark gray (text)
             #3e9ade - medium blue (title)
             #b4c24b - lime (header 1)
             #ebf5fc - light cyan (hover text, footer)
             #d7e9f5    - medium cyan (tab)
             #1465b7 - dark blue (tab text, hyperlink)
             #ccc         - medium gray (line)
             #f7f7f7 - light gray (form background)
             #f90        - orange (required *)
*/

        /* TYPOGRAPHY */
        body {
            text-align: left;
            font-family: verdana, arial, helvetica, sans-serif;
            font-size: 76%;
            line-height: 1em;
            color: #444;
        }

        img {
            border: 0;
        }

        /* LINKS */
        a, a:link
        a:active {
            color: #1465b7;
            text-decoration: underline;
        }

        a:hover {
            text-decoration: none;
        }

        /* END LINKS */

        /* HEADINGS */
        h1, h2, h3, h4, h5, h6 {
            font-family: "Microsoft Yahei", "Microsoft JhengHei", Hei, SimHei, sans-serif;
            font-weight: normal;
        }

        h1 {
            font-size: 2.0em;
            letter-spacing: -1px;
            line-height: 1.3em;

            margin: 0 0 0.5em 0;
            padding: 0;
        }

        div#branding h1 {
            font-size: 2.6em;
            letter-spacing: -1px;

            margin: 0;
            line-height: 1.5em;
            color: #3e9ade;
        }

        div#branding p {
            margin: 0;
            line-height: 1em;
        }

        h2 {
            font-size: 1.4em;
            line-height: 1.5em;
            margin: 0 0 0.5em 0;
            padding: 0;
        }

        h3 {
            font-size: 1.3em;
            line-height: 1.3em;
            margin: 0 0 0.5em 0;
            padding: 0;
            color: black;
        }

        h4 {
            font-size: 1.2em;
            line-height: 1.3em;
            margin: 0 0 0.25em 0;
            padding: 0;
            color: black;
        }

        h5 {
            font-size: 1.1em;
            line-height: 1.3em;
            margin: 0 0 0.25em 0;
            padding: 0;
            color: black;
        }

        h6 {
            font-size: 1em;
            line-height: 1.3em;
            margin: 0 0 0.25em 0;
            padding: 0;
            color: black;
        }

        /* END HEADINGS */

        /* TEXT */
        p {
            font-size: 1em;
            margin: 0 0 1.5em 0;
            padding: 0;
            line-height: 1.4em;
        }

        blockquote {
            border-left: 10px solid #ddd;
            margin-left: 10px;
        }

        pre {
            font-family: monospace;
            font-size: 12px;
            white-space: normal;
        }

        strong, b {
            font-weight: bold;
        }

        em, i {
            font-style: italic;
        }

        code {
            font-family: "courier new", courier, monospace;
            font-size: 12px;
            white-space: pre;
        }

        /* END TEXT */

        /* LISTS */
        ul.glassList {
            list-style: url(../../images/aquadot.jpg) disc outside;
            vertical-align: top;
            line-height: 1.5em;
        }

        dl {
            margin: 0 0 1.5em 0;
            padding: 0;
            line-height: 1.4em;
        }

        dl dt {
            font-weight: bold;
            font-size: 12px;
            margin: 0.25em 0 0.25em 0;
            padding: 0;
        }

        dl dd {
            font-size: 12px;
            margin: 0 0 0 30px;
            padding: 0;
        }

        /* END LISTS */

        /* TABLE */
        table {
            font-size: 12px;
        }

        table.small {
            width: 400px;
        }

        table.middle {
            width: 600px;
        }

        table.large {
            width: 800px;
        }

        table caption {
            text-align: left;
            font-weight: bold;
            margin: 0 0 0 0;
            padding: 0 0 .5em;
        }

        table caption span {
            float: left;
        }

        table caption div.tip, table caption em { /* for table alert*/
            font-weight: normal;
            font-style: normal;
            float: right;
        }

        caption span em {
            padding-top: 10px;
            font-weight: bold;
            float: left;
        }

        caption span.map {
            float: right;
            font-weight: normal;
            background-color: #CAE8EA;
            background-repeat: no-repeat;
            background-position: 3px 50%;
            padding: 3px 3px 3px 23px;
            margin: 2px;
            border: 1px solid #94d3eb;
        }

        table caption div.help {
            width: 100%;
            font-weight: normal;
            background-color: #ffffe3;
            border: 1px solid #deddcf;
            padding: 5px;
        }

        table caption div.header {
            margin: 10px 0;
        }

        div#main div.help span {
            margin: 0 5px;
            padding: 0 5px;
        }

        div#main div.help dl {
            margin: 0;
        }

        div#main div.help dl dt {
            margin: 2px 0;
            padding: 0 0 0 20px;
            color: #444;
        }

        div#main div.help dl dd {
            margin: 0 0 0 20px;
        }

        div#main div.help dl dd a {
            color: #444;
        }

        div#main dt.warn {
            background: url(../../images/icons/warn.gif) no-repeat;
        }

        div#main dt.search {
            background: url(../../images/icons/search.gif) no-repeat;
        }

        div#main dt.add {
            background: url(../../images/icons/add.gif) no-repeat;
        }

        div#main li.disc {
            list-style-type: disc;
        }

        th {
            font-weight: bold;
            text-align: left;
        }

        td {
            font-size: 12px;
        }

        .listTable, .table {
            width: 100%;
            border-collapse: collapse;
            font-size: 12px;
        }

        .listTable caption, .table caption {
            color: #444;
        }

        .listTable td, .listTable th, .table td, .table th {
            border: 1px solid #c1dad7;
            padding: 6px 6px 6px 6px;
            background-color: #FFFFFF;
        }

        .listTable tr.header td, .listTable thead td, .listTable thead th, .table thead th {
            background: #cae8ea;
            color: #006699;
            font-weight: bold;
        }

        tr.colorrow td {
            background: #f5fafa;
        }

        .listTable td.icon {
            width: 20px;
            background-repeat: no-repeat;
            background-position: center;
        }

        td.imageColumn, th.imageColumn {
            width: 10px;
            text-align: center;
        }

        td.image, th.image {
            vertical-align: middle;
            text-align: center;
        }

        tr.serious .icon, caption span.serious {
            background-image: url(../../images/icons/sheet-icons/exclamation.png);
        }

        tr.alert .icon, caption span.alert {
            background-image: url(../../images/icons/sheet-icons/error.png);
        }

        tr.normal .icon, caption span.normal {
            background-image: url(../../images/icons/sheet-icons/information.png);
        }

        tr.marked .icon, caption span.marked {
            background-image: url(../../images/icons/sheet-icons/flag_orange.png);
        }

        tr.complete .icon, caption span.complete {
            background-image: url(../../images/icons/sheet-icons/accept.png);
        }

        tr.finish .icon, caption span.finish {
            background-image: url(../../images/icons/sheet-icons/page_save.png);
        }

        .listTable a:link, .table a:link {
            color: #006699;
            text-decoration: none;
        }

        .listTable a:visited, .table a:visited {
            color: #999;
            text-decoration: none;
        }

        .listTable a:hover, .listTable a:active, .table a:hover, .table a:active {
            color: #006699;
            text-decoration: underline;
        }

        .listTable thead a:visited, tr.header a:visited, .table thead a:visited {
            color: #006699;
        }

        /* END TABLE */

        hr {
            display: none;
        }

        div.hr {
            height: 1px;
            margin: 1.5em 10px;
            border-bottom: 1px dotted black;
        }

        /* END TYPOGRAPHY */


        /*****************
 * ?惧?(div)????峰?
 *****************/
        div {
            font-size: 12px;
        }

        .tabContent {
            padding: 15px;
            display: none;
        }

        .center {
            text-align: center;
        }

        .show {
            display: block;
        }

        .hide {
            display: none;
        }

        .tip {
            color: #77BC60;
            font-weight: bold;
        }

        .textHeader {
            font-weight: bold;
        }

        div.switchIcon {
            display: inline;
            width: 11px;
            height: 11px;
            background: url(../../images/icons/closed.gif) no-repeat scroll left top;
        }

        div.switchIcon .opened {
            background: url(../../images/icons/opened.gif) no-repeat scroll left top;
        }

        /*****************
 * View?т欢?峰?
 *****************/
        .viewer-box {
            width: 400px;
            margin: 10px 0;
            padding: 10px;
            background-color: #fdfdfd;
            border: 1px solid #eee;
            line-height: 1.8em;
        }

        .viewer-box div, .viewer-list div {
            display: inline;
            padding: 0 0 3px 22px;
            vertical-align: middle;
            cursor: default;
            background-repeat: no-repeat;
            background-position: 3px;
        }

        .viewer-list {
            padding: 5px 0px;
        }

        .viewer-list div {
            display: block;
            margin: 6px 0;
        }

        .viewlistitem-user {
            background-image: url(../../images/icons/nodetype/user.gif);
        }

        .viewlistitem-leader {
            background-image: url(../../images/icons/nodetype/leader.gif);
        }

        .viewlistitem-post {
            background-image: url(../../images/icons/nodetype/post.gif);
        }

        .viewlistitem-role {
            background-image: url(../../images/icons/nodetype/role.gif);
        }

        .viewlistitem-subrole {
            background-image: url(../../images/icons/nodetype/subrole.gif);
        }

        .viewlistitem-dept {
            background-image: url(../../images/icons/nodetype/dept.gif);
        }

        .viewlistitem-partner-dept {
            background-image: url(../../images/icons/nodetype/partner-dept.gif);
        }

    </STYLE>


    <STYLE>
        /* A CSS Framework by Mike Stenhouse of Content with Style */

        /* SITE SPECIFIC LAYOUT */
        body {
            margin: 0;
            padding: 0;
            background: white url(images/background.gif) top left repeat-x;
        }

        div#page {
            width: 95% !important;
            width: 98%;
            margin: 0 auto;
            padding: 0;
            text-align: center;
        }

        /* HEADER */
        div#header {
            margin: 0 0 5em 0;
            padding: 6px 0 2px 10px;
            text-align: left;
        }

        div#switchLocale {
            position: absolute;
            right: 10px;
        }

        div#branding {
            float: left;
            width: 50%;
            margin: 0;
            padding: 10px 0 10px 30px;
            text-align: left;
        }

        div#search {
            float: right;
            width: 49%;
            margin: 0;
            padding: 40px 40px 0 0;

            text-align: right;
        }

        /* END HEADER */


        /* CONTENT */
        div#content {
        }

        body#error #content {
            margin-top: 20px;
        }

        /* MAIN */
        div#main {
            padding-left: 0px !important;
            padding-left: 20px;
        }

        div#main form ul li {
            list-style-type: none;
            margin-left: 0;
            font-size: 12px;
        }

        /* END MAIN */

        /* SUB */
        div#sub h2 {
            margin-bottom: 5px;
        }

        div#sub ul {
            margin: 0;
        }

        div#sub ul li {
            list-style-type: none;
            margin-left: -30px;
            padding-bottom: 10px;
        }

        div#sub p.line {
            border-top: 1px dotted #ccc;
            margin: 15px 0;
        }

        /* END SUB */

        /* END CONTENT */

        /* FOOTER */
        div#footer {
            color: #444;
            padding: 0 39px;
            text-align: left;
            font-size: 0.9em;
        }

        div#footer div#divider {
            margin: 0 0 4px 0;
            border-top: 1px solid #ccc;
        }

        div#footer div#divider div {
            margin: 1px 0 0 0;
            border-top: 6px solid #ebf5fc;
        }

        div#footer .left {
            float: left;
        }

        div#footer .right {
            float: right;
        }

        /* END FOOTER */
        /* END LAYOUT */

    </STYLE>


    <STYLE>
        /*****************
 * 登录页面login.jsp
 *****************/
        #loginForm fieldset {
            width: 300px;
            padding: 10px 30px;
            margin: 0px auto;
        }

        #loginForm li {
            text-align: left;
            padding: 0px;
        }

        .login-box {
            color: #5F6775;
            width: 705px;
            margin: 4em auto 0pt;
            background: url(images/login-bg.jpg) no-repeat scroll left top;
            min-height: 436px;
        }

        .login-content {
            float: left;
            margin: 20px;
            padding-top: 75px;
            min-height: 150px !important;
            min-height: 80px;
        }

        .login-form {
            float: right;
            position: relative;
            margin: 20px;
            margin-right: 40px;
            height: 400px;
            width: 200px;
        }

        .login-form-content {
            position: absolute;
            bottom: 1px;
        }

        /*****************
 * 表单详细页面
 *****************/
        div#sheetinfo {
            padding: 15px;
        }

        .tabFrame {
            width: 98%;
            height: 500px;
            border: 0;
        }

        #tabtool {
            clear: none;
            float: right;
        }

        #filter {
            float: left;
            display: inline;
            margin: 5px;
            padding: 10px;
        }

        div.history-item {
            font-size: 12px;
            padding: 0 10px;
        }

        div.history-item-title {
            font: 14px bold;
            background-color: #EDF5FD;
        }

        div.history-item-content {
            width: 100%;
        }

        table.history-item-table {
            border-collapse: collapse;
            font-size: 12px;
        }

        table.history-item-table td {
            border: 1px solid #c1dad7;
            padding: 3px 6px;
        }

        td.column-title {
            background-color: #EDF5FD;
            width: 15%;
        }

        td.column-content {
            width: 35%;
        }

        ul.history {
            list-style: none;
        }

        li.history-item {
            font-size: 14px;
            padding: 0px;
            margin: 0px 2% 10px 5px;
        }

        li.history-item a {
            border: 1px solid #8cacbb;
            color: #006699;
            padding: 0.8ex 0px 0.8ex 8px;
            font-size: 14px;
            display: block;
            position: relative;
            text-decoration: none;
            height: auto;
            cursor: text;
        }

        li.history-item a:hover {
            background: #e5ecf9;
            border: 1px solid #8cacbb;
            color: #006699;
        }

        li.history-item a span {
            display: none;
        }

        li.history-item a span.show {
            display: block;
        }

        li.history-item a:hover span {
            display: block;
            position: relative;
        }

        .tabtool-history-detail {
        }

        h3.sheet-title {
            margin: 0 0 10px;
            font-size: 1.2em;
            font-weight: bold;
            color: #444;
        }

        .sheet-deal {
            border: 1px solid #dfeff6;
            margin: 20px 0;
        }

        .sheet-deal-header {
            background: transparent url(images/header-bg.gif) repeat-x;
            padding: 10px;
        }

        .sheet-deal-content {
            margin: 10px;
        }

        /*****************
 * 表单列表页面
 *****************/
        .list-title {
            height: 22px;
            margin: 0 10px 10px 10px;
            padding: 3px 10px 3px 20px;
            vertical-align: middle;
            width: 95%;
            clear: right;
            color: #8b8c8d;
            border-bottom: 1px solid #cfcfcf;
            background-image: url(../../images/icons/table.png);
            background-repeat: no-repeat;
            background-position: left center;
        }

        .list table {
            width: 95%;
            border-collapse: collapse;
            font-size: 12px;
        }


        .list table td {
            border: 1px solid #c1dad7;
            padding: 3px 6px;
        }


        .list .header {
            background: #cae8ea;
            color: #006699;
        }

        .list .header td a:link, .list .header td a:visited {
            color: #006699;
        }


        /*****************
 * 成功和失败页面

 *****************/
        .successPage h1, .errorPage h1, .failurePage h1 {
            height: 32px;
            padding: 10px 0 0 45px;
            margin: 20px;
            vertical-align: middle;
            background-repeat: no-repeat;
            background-position: 3px bottom;
        }

        .successPage .header {
            color: #3786d6;
            background-image: url(../../images/icons/success.gif);
        }

        .errorPage .header, .failurePage .header {
            color: #b4c24b;
            background-image: url(../../images/icons/failed.gif);
        }

        .errorPage .content, .successPage .content, .failurePage .content {
            line-height: 24px;
            color: #3786d6;
            border-top: 1px solid #acd0f4;
            padding: 10px;
            margin: 10px;
        }

        .content ul {
            margin-left: 30px;
        }

        .content #error-msg {
        }

        .hidden-el {
            display: none;
        }


        /***************************
 * 平台js框架页面
 **************************/
        #headerPanel {
            background: white url(images/background.gif);
            width: 100%;
            height: 100%;
        }

        #headerPanel h1 {
            font-size: 15px;
            padding: 10px;
            width: 100%;
            height: 100%;
        }

        .panel {
            padding: 10px;
        }

        .gridPanel-grid {
            border: 1px solid #6593cf;
            margin-bottom: 10px;
        }

        .app-header {
            background: url(images/header-bg.gif) repeat-x;
        }

        .app-panel {
            padding: 10px;
        }

        /*****************
 * 文件上传页面
 *****************/
        iframe.uploadframe {
            height: 50px;
        }

        .upload-box {
            /*border:1px solid #b5b8c8;
	padding:5px;*/
        }

        a.filelink {
            line-height: 1.2em;
        }

        /*****************
 * 欢迎页面
 *****************/
        .welcome {
            width: 758px;
        }

        .welcome .title {
            min-height: 190px;
            background: url(images/welcome-titlebg.gif) no-repeat;
        }

        .welcome .title .content {
            margin: 50px 10px 10px 20px;
            width: 700px;
            line-height: 2em;
        }

        .welcome .title .foot {
            height: 10px;
        }

        .module {
            margin-bottom: 20px;
        }

        .module .img {
            float: left;
        }

        .module .detail {
            line-height: 2em;
        }
    </STYLE>

    <STYLE>
        form {
            font-size: 1em;
        }

        /* ----- INFO ----- */
        form h2 {
            font-size: 1.8em;
            clear: left;
        }

        form .info {
            display: inline-block;
            margin: 0 0 4px 0;
            padding: 0 0 4px 0;
            border-bottom: 1px dotted #ccc !important;
            text-align: left;
        }

        form .info[class] {
            display: block;
        }

        form .info p {
            font-size: 1em;
            line-height: 1.3em;
            margin: 0 0 8px 0;
        }

        /* ----- FIELDS AND LABELS ----- */
        label.desc {
            margin: 4px 0 3px 0;
            border: 0;
            color: #444;
            font-size: 12px;
            line-height: 1.3em;
            display: block;
            font-weight: normal;
        }

        p label {
            font-weight: normal;
        }

        /* ----- 表单域样式 ----- */
        input {
            font-family: Verdana, Arial, Helvetica, sans-serif;
            font-size: 12px;
            font-size-adjust: none;
            font-stretch: normal;
            font-style: normal;
            font-variant: normal;
            font-weight: normal;
            line-height: 150%;
        }

        input.text, input.txt,
        textarea.textarea {
            background: #FFFFFF url(images/text-bg.gif) repeat-x;
            border: 1px solid #B5B8C8;
            padding: 3px 3px;
            height: 22px;
            line-height: 18px;
            vertical-align: middle;
            padding-bottom: 0pt;
            padding-top: 2px;
            width: 150px;
            color: #333;
        }

        textarea.textarea {
            height: 100px;
            font-size: 12px;
        }

        select {
            border: 1px solid #B5B8C8;
            vertical-align: middle;
            padding: 1px;
        }

        select.select {
            width: 150px;
        }

        input.invalid, textarea.invalid {
            background: #fff url(images/invalid_line.gif) repeat-x bottom;
            /*border:1px solid #dd7870;*/
        }

        select.invalid {
            border: 1px solid #dd7870;
        }

        option.invalid {
            background-color: #ffa07a;
            color: #fff;
        }

        input.file {
            border: 1px solid #B5B8C8;
            height: 22px;
            width: auto;
        }

        input.checkbox, input.radio {
            margin: 0 5px 0 0;
            vertical-align: middle;
        }

        input.focus, textarea.focus {
            background: #ffd;
            color: #000;
        }

        /* ----- SIZES ----- */
        input.max, textarea.max {
            width: 100%;
        }

        /* ----- 按钮样式  ----- */
        .form-btns {
            margin: 10px 0;
        }

        input.btn, input.button, input.submit, input.reset {
            font-size: 12px;
            width: auto;
            height: 22px;
            border: 1px solid #003c74;
            background: url("images/btn-bg.gif") repeat-x;
            padding: 0 5px 3px;
            vertical-align: middle;
        }

        input[type='button'], input[type='submit'], input[type='reset'] {
            font-size: 12px;
            width: auto;
            height: 22px;
            border: 1px solid #003c74;
            background: url("images/btn-bg.gif") repeat-x;
            padding: 0 5px 3px;
            vertical-align: middle;
        }

        /* ----- ERRORS ----- */
        form li.error {
            background-color: #FFDFDF;
            margin: 3px 0 !important;
        }

        input.error, textarea.error {
            border: 1px solid #EF5959;
            border-left: 1px solid #FF8F8F;
            border-top: 1px solid #FF8F8F;
            background: #fff;
        }

        p.error, li.error label.desc {
            color: red !important;
        }

        p.error {
            font-size: 9px !important;
            margin: 1px 0;
        }

        /* ----- REQUIRED ----- */
        .req {
            font-size: 1em !important;
            color: #f90;
            font-weight: bold;
        }

        /* ----- Fieldsets ----- */
        fieldset {
            margin: 0 0 5px 0;
            padding: 10px 10px 5px 10px;
            color: #444;
        }

        /* Firefox and others, don't apply to IE or it will put a border under each input element */
        li > fieldset, form > fieldset {
            border: 1px solid #C9DEFA
        }

        form fieldset {
            padding: 10px;
            border: 1px solid #C9DEFA
        }

        legend {
            margin: 0 0 5px 0;
            color: #444;
            font-size: 13px;
            line-height: 22px;
        }

        fieldset p {
            margin: 10px;
        }


        /* END FORM ELEMENTS */

        /* ----- 表格(table)通用样式 ----- */

        .formTable {
            width: 100%;
            border-collapse: collapse;
            font-size: 12px;

        }

        .formTable td {
            border: 1px solid #C9DEFA;
            padding: 6px 6px;
            background-color: #FFFFFF;
        }

        .formTable tr.header td {
            background: #cae8ea;
            color: #006699;
        }

        td.label {
            vertical-align: top;
            background-color: #EDF5FD;
            width: 15%;
        }

        td.content {
            vertical-align: top;
            width: 35%;
        }

        td.max {
            width: 90%;
        }

        td.checkColumn {
            width: 10px;
        }

        /* ----- 其他 ----- */


        .basicdlg {
            visibility: hidden;
            position: absolute;
            top: 0px;
        }
    </STYLE>


    <STYLE>
        /* This is the style for the informational messages presented to the user */

        div.error, div.message, li.error {
            background: #ffffcc;
            border: 1px solid #000;
            color: #000000;
            font-family: Arial, Helvetica, sans-serif;
            font-weight: normal;
            margin: 10px auto;
            padding: 3px;
            text-align: left;
            vertical-align: bottom;
        }

        /* use a different color for the errors */
        div.error, li.error {
            border: 1px solid red;
        }

        /* IE fix, followed by the rest of the world fix */
        li.error {
            padding: 3px !important;
        }

        ul > li.error {
            padding: 5px !important;
            line-height: 1.4em;
        }

        div.message p, div.message p {
            margin-bottom: 0;
        }

        img.validationWarning, div.error img.icon, div.message img.icon, li.error img.icon {
            border: 0 !important;
            width: 14px;
            height: 13px;
            vertical-align: middle;
            margin-left: 3px;
            background: transparent !important;
            /* important added because some themes define div#main img */
        }

        div.message a {
            background: transparent;
            color: #0000FF;
        }

        div.message a:visited {
            background: transparent;
            color: #0000FF;
        }

        div.message a:hover {
            background: transparent;
            color: #008000;
        }

        div.message a:active {
            text-decoration: underline overline;
        }

        div.message img.icon {
            vertical-align: middle;
        }

        span.fieldError, .errorMessage {
            color: red;
            font-size: .95em;
            font-weight: bold;
            display: block;
        }

    </STYLE>


    <STYLE>
        div.exportlinks {
            margin: 5px 0px 10px 10px;
            padding: 2px 4px 2px 0px;
            width: 100%;
        }

        div.exportlinks a {
            text-decoration: none;
        }

        div.exportlinks span {
            background-repeat: no-repeat;
        }

        span.csv {
            background-image: url(../../images/ico_file_csv.png);
        }

        span.excel {
            background-image: url(../../images/ico_file_excel.png);
        }

        span.pdf {
            background-image: url(../../images/ico_file_pdf.png);
        }

        span.xml {
            background-image: url(../../images/ico_file_xml.png);
        }

        span.rtf {
            background-image: url(../../images/ico_file_rtf.png);
        }

        span.export {
            cursor: pointer;
            display: inline;
            display: inline-block;
            padding: 0 4px 1px 20px;
        }

        span.pagebanner {
            display: block;
            margin: 10px 0px 10px 0px;
            padding: 2px 4px 2px 0px;
        }

        span.pagelinks {
            display: block;
            margin-bottom: 5px;
            margin-top: -18px;
            padding: 2px 0px 2px 0px;
            text-align: right;
            width: 100%;
        }

        th.sorted a, th.sortable a {
            background-position: right;
            display: block;
        }

        .table th.order1 a {
            background-image: url(../../images/arrow_down.png) !important;
        }

        .table th.order2 a {
            background-image: url(../../images/arrow_up.png) !important;
        }

        div#main .tableHeaderImage { /* Tapestry */
            position: absolute;
            margin: 2px 0px 0px -5px;
            background: transparent;
            border: 0px;
        }

        /*.table th.sortable a {
    background-image: url(../images/arrow_off.png);
}*/

        .table th.sorted {
            background-color: #ffd;
            color: #000000;
        }

        .table th.sorted a, .table th.sortable a {
            background-position: right;
            background-repeat: no-repeat;
            display: block;
        }

        .table tr.even {
            background: #eee;
            border-top: 1px solid #c0c0c0;
            color: #000000;
        }

        .table tr.odd {
            background: #fff;
            border-top: 1px solid #c0c0c0;
            color: #000000;
        }

        /* highlight .table row onmouseover */
        .table tr.over {
            border-bottom: 1px solid #C0C0C0;
            border-top: 1px solid #C0C0C0;
            color: #000000;
            cursor: pointer;
        }

        .detail th {
            text-align: right;
        }

    </STYLE>


    <STYLE>
        /*** COMMON **/
        #loading {
            position: absolute;
            left: 45%;
            top: 40%;
            border: 1px solid #6593cf;
            padding: 2px;
            background: #c3daf9;
            width: 180px;
            text-align: center;
            z-index: 20001;
        }

        .loading-indicator {
            border: 1px solid #a3bad9;
            background-position: 10px;
            color: #003366;
            font-size: 12px;
            padding: 10px 30px;
            margin: 0;
        }

        form .loading-indicator {
            border: 0;
            background-color: #F3FAFC;
        }

        /** Toolbar & Button **/
        .blist .x-btn-text { /** menu **/
            color: #fff;
            background-image: url(../../images/list-items.gif)
        }

        .x-layout-tools-add {
            padding-right: 20px;
            font-size: 12px;
            background-image: url(../../images/icons/icon-add.gif);
        }

        .x-layout-tools-add-em {
            vertical-align: middle;
            margin-top: 20px;
            margin-right: 10px;
        }

        .x-toolbar {
            /* modified by mios 070417
	border: 1px solid;
    border-color:#eaf0f7 #eaf0f7 #a9bfd3 #eaf0f7;
    background:#d0def0 url(../images/default/layout/panel-title-light-bg.gif) repeat-x;
    display: block;
	*/
            background: transparent;
            border: 0;
            padding: 2px;
        }

        .x-toolbar td, .x-toolbar span,
        .x-toolbar input, .x-toolbar div,
        .x-toolbar select, .x-toolbar label {
            font-size: 12px;
        }

        /* modified by mios 070417 */
        .x-toolbar .x-btn-over .x-btn-left {
            background: transparent;
        }

        .x-toolbar .x-btn-over .x-btn-right {
            background: transparent;
        }

        .x-toolbar .x-btn-over .x-btn-center {
            background: transparent;
        }

        .x-toolbar .x-btn-click .x-btn-left, .x-toolbar .x-btn-pressed .x-btn-left, .x-toolbar .x-btn-menu-active .x-btn-left {
            background: transparent;
        }

        .x-toolbar .x-btn-click .x-btn-right, .x-toolbar .x-btn-pressed .x-btn-right, .x-toolbar .x-btn-menu-active .x-btn-right {
            background: transparent;
        }

        .x-toolbar .x-btn-click .x-btn-center, .x-toolbar .x-btn-pressed .x-btn-center, .x-toolbar .x-btn-menu-active .x-btn-center {
            background: transparent;
        }

        .x-btn button {
            font-size: 12px;
        }

        .x-tabs-strip .x-tabs-text, .x-menu-list-item,
        .x-grid-hd-row td, .x-grid-row td, .x-dd-drag-ghost,
        .x-layout-panel-hd-text, .x-tip .x-tip-bd {
            font-size: 12px;
        }

        .add .x-btn-text, .add .x-btn-text-icon {
            background-image: url(../../images/icons/add.gif);
        }

        .remove .x-btn-text, .remove-mi .x-menu-item-icon, .remove .x-btn-text-icon {
            background-image: url(../../images/icons/delete.gif);
        }

        .new-mi .x-menu-item-icon {
            background-image: url(../../images/icons/add.gif);
        }

        .edit-mi .x-menu-item-icon {
            background-image: url(../../images/icons/edit.gif);
        }

        .list-mi .x-menu-item-icon {
            background-image: url(../../images/icons/list.png);
        }

        .save .x-btn-text {
            background-image: url(../../images/icons/save.gif);
        }

        .refresh .x-btn-text {
            background-image: url(../../images/icons/refresh.gif);
        }

        .expand .x-btn-text {
            background-image: url(../../images/icons/expand.gif);
        }

        .search .x-btn-text, .x-layout-search {
            background-image: url(../../images/icons/search.gif);
        }

        .x-layout-search {
            background-image: url(../../images/icons/search.gif);
            width: 16px;
            height: 16px;
        }

        .collapse .x-btn-text {
            background-image: url(../../images/icons/collapse.gif);
        }

        .msgbox {
            background-color: #e8f5fe;
            background-image: url(../../images/icons/yes.gif);
            background-repeat: no-repeat;
            background-position: 5px 5px;
            border: 1px solid #acc6e9;
            margin: 5px;
            padding: 5px;
            padding-left: 25px;
            height: 18px;
            text-align: left;
        }

        /* 用于chooser */
        .chooser-bg {
            background-color: #BDD5F6;
            border: 0px;
        }

        /******************
 * TREENODE ICONCLS
 *****************/
        .x-tree-node {
            font-size: 12px;
        }

        .x-tree-node-leaf img.dept, .x-tree-node-collapsed img.dept, .x-tree-node-expanded img.dept {
            background-image: url(../../images/icons/nodetype/dept.gif);
        }

        .x-tree-node-leaf img.partner-dept, .x-tree-node-collapsed img.partner-dept, .x-tree-node-expanded img.partner-dept {
            background-image: url(../../images/icons/nodetype/partner-dept.gif);
        }

        .x-tree-node-leaf img.role, .x-tree-node-collapsed img.role, .x-tree-node-expanded img.role {
            background-image: url(../../images/icons/nodetype/role.gif);
        }

        .x-tree-node-leaf img.post, .x-tree-node-collapsed img.post, .x-tree-node-expanded img.post {
            background-image: url(../../images/icons/nodetype/post.gif);
        }

        .x-tree-node-leaf img.user {
            background-image: url(../../images/icons/nodetype/user.gif);
        }

        .x-tree-node-leaf img.leader {
            background-image: url(../../images/icons/nodetype/leader.gif);
        }

        .x-tree-node-leaf img.subrole, .x-tree-node-collapsed img.subrole, .x-tree-node-expanded img.subrole {
            background-image: url(../../images/icons/nodetype/subrole.gif);
        }

        .x-tree-node-leaf img.success, .x-tree-node-collapsed img.success, .x-tree-node-expanded img.success {
            background-image: url(../../images/icons/nodetype/success.gif);
        }

        .x-tree-node-leaf img.failure, .x-tree-node-collapsed img.failure, .x-tree-node-expanded img.failure {
            background-image: url(../../images/icons/nodetype/failure.gif);
        }

        .x-tree-node-leaf img.empty {
            background-image: url(../../images/icons/nodetype/empty.gif);
        }

        .emptyText a span {
            color: #666;
        }

        input.x-tree-node-cb {
            vertical-align: middle;
        }

        /*****************
 * FORM STYLES
 *****************/
        .x-form fieldset legend {
            font-size: 13px;
        }

        .x-form-element label {
            clear: none;
            float: none;
            padding: 3px 3px 3px 0px;
        }

        .x-form-element {
            position: static;
        }

        .x-form-item .form-btns {
            clear: left;
            display: block;
            float: left;
            padding: 3px 3px 3px 0pt;
            position: relative;
        }

        .x-form-item .form-xbox {
            clear: left;
            display: block;
            float: left;
            padding: 3px 3px 3px 0pt;
            position: relative;
        }

        .x-layout-panel-south {
            background: #C3DAF9 none repeat scroll 0%;
            border-color: #DDECFE -moz-use-text-color -moz-use-text-color;
            border-style: solid none none;
            border-width: 1px 0px 0px;
        }

        .tb {
            background: #D0DEF0 url(../../scripts/ext/resources/images/default/layout/panel-title-light-bg.gif) repeat-x;
        }

        .ext-el-mask-msg div {
            cursor: auto;
        }

        .ext-el-mask {
            background-color: #EEEEEE;
        }

        .x-tip .x-tip-bd-inner {
            font-size: 12px;
        }

        .x-view-selected {
            background-color: #316AC5 !important;
            color: white;
        }

        /** ext表格中自动换行 **/
        .x-grid-row td {
            white-space: normal;
        }

        /** ext表格文字在ie中正常显示 **/
        em {
            line-height: 18px
        }

        /** 表单trigger图片在ie中正常显示
.x-form-field-wrap .x-form-trigger{
	top:0px !important;
	top:-1px;
}
**/

        /** 角色选择样式**/
        .x-tree-node div.cmp {
            background: #eee url(images/chooser-category.gif) repeat-x;
            margin-top: 1px;
            border-top: 1px solid #ddd;
            border-bottom: 1px solid #ccc;
            padding-top: 2px;
            padding-bottom: 1px;
        }

        .x-tree-node img.folder, .x-tree-node-collapsed img.folder {
            background-image: url(../../scripts/ext/resources/images/default/tree/folder.gif);
        }

        .x-tree-node-expanded img.folder {
            background-image: url(../../scripts/ext/resources/images/default/tree/folder-open.gif);
        }

    </STYLE>


</HTML>
</head>

<body>


<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%@page import="com.boco.eoms.sheet.commonfault.task.impl.CommonFaultTask" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.sheet.commonfault.service.*" %>
<%@page import="com.boco.eoms.commons.system.dict.service.ID2NameService" %>
<%@page import="com.boco.eoms.sheet.commonfault.model.*" %>
<%@page import="com.boco.eoms.commons.system.dict.service.IDictService" %>
<%@page import="java.util.List" %>
<%@page import="com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager" %>
<%@page import="com.boco.eoms.commons.util.xml.XmlManage" %>
<%@page import="com.boco.eoms.commons.accessories.model.TawCommonsAccessories" %>

    <%
CommonFaultMain basemain = (CommonFaultMain)request.getAttribute("sheetMain");
java.util.List list = (java.util.List)request.getAttribute("HISTORY");

ID2NameService id2NameService = (ID2NameService) ApplicationContextHolder
                .getInstance().getBean("ID2NameGetServiceCatch");
IDictService service = (IDictService) ApplicationContextHolder
                .getInstance().getBean("DictService");
ITawCommonsAccessoriesManager mgr = (ITawCommonsAccessoriesManager)ApplicationContextHolder.getInstance().getBean("ItawCommonsAccessoriesManager");

String sysPath = XmlManage.getFile("/com/boco/eoms/interfaces/province/config/province-util.xml").getProperty("Interface.Ftp.EomsUrl");
String filePath = sysPath + "accessories/tawCommonsAccessoriesConfigs.do?method=download&type=interface&userName=admin&id=";
%>
<br/>
<table class="formTable">
    <caption>工单基本信息</caption>
    <tr>
        <td class="label">工单流水号</td>
        <td class="content">${sheetMain.sheetId}</td>
        <td class="label">工单状态</td>
        <td class="content">
            <%=service.itemId2name("dict-sheet-common#sheetStatus", basemain.getStatus().intValue() + "")%>
        </td>
        <!--  <td class="content"><eoms:dict key="dict-sheet-common" dictId="sheetStatus" itemId="${sheetMain.status}" beanId="id2nameXML" /></td>-->
    </tr>
    <tr>
        <td class="label">工单标题</td>
        <td colspan='3'>${sheetMain.title}</td>
    </tr>


    <tr>
        <td class="label">操作人</td>
        <td class="content">
            <%=id2NameService.id2Name(basemain.getSendUserId(), "tawSystemUserDao")%>

        </td>
        <td class="label">操作人部门</td>
        <td class="content">
                <%=id2NameService.id2Name(basemain.getSendDeptId(),"tawSystemDeptDao")%>

    </tr>
    <tr>
        <td class="label">
            操作人当前角色
        </td>
        <td>
            <%=id2NameService.id2Name(basemain.getSendRoleId(), "tawSystemSubRoleDao")%>
        </td>
        <td class="label">
            操作人联系方式
        </td>
        <td>
            ${sheetMain.sendContact}
        </td>
    </tr>

    <tr>
        <td class="label">操作时间</td>
        <td colspan="3"><%=StaticMethod.date2String(basemain.getSendTime()) %>
        </td>

    </tr>

    <c:if test="${sheetMain.status==1}">
        <tr style="display:none">

        <tr>
            <td class="label">
                归档满意度
            </td>
            <td colspan='3'>
                <%=id2NameService.id2Name(basemain.getHoldStatisfied() + "", "ItawSystemDictTypeDao")%>
            </td>
        </tr>
        <tr>
            <td class="label">
                归档意见
            </td>
            <td colspan='3'>
                <pre>${sheetMain.endResult}</pre>
            </td>
        </tr>
    </c:if>
    <c:if test="${!empty sheetMain.parentSheetId}">
        <tr>
            <td class="label">
                父工单名称
            </td>
            <td>
                    ${sheetMain.parentSheetName}
            </td>
            <td class="label">
                父工单号
            </td>
            <td>
                    ${sheetMain.parentSheetId}
            </td>
        </tr>
    </c:if>
    <c:if test="${sheetMain.status==-13}">
        <tr>
            <td class="label">
                归档满意度
            </td>
            <td colspan='3'>
                <%=id2NameService.id2Name(basemain.getHoldStatisfied() + "", "ItawSystemDictTypeDao")%>
            </td>
        </tr>
        <tr>
            <td class="label">
                操作描述
            </td>
            <td colspan='3'>
                    ${sheetMain.cancelReason}
            </td>
        </tr>
    </c:if>
    <c:if test="${sheetMain.status==-14||sheetMain.status==-12}">
        <tr>
            <td class="label">
                操作描述
            </td>
            <td colspan='3'>
                    ${sheetMain.cancelReason}
            </td>
        </tr>
    </c:if>
    <c:if test="${sheetMain.mainIfCheck=='2'}">
        <tr>
            <td class="label">
                质检结果
            </td>
            <td colspan='3'>
                <%=id2NameService.id2Name(basemain.getMainCheckResult(), "ItawSystemDictTypeDao")%>
            </td>
        </tr>
        <tr>
            <td class="label">
                质检概述
            </td>
            <td colspan='3'>
                <pre>${sheetMain.mainCheckIdea}</pre>
            </td>
        </tr>
    </c:if>

</table>
<br/>
<table class="formTable">
    <caption></caption>
    <tr>
        <td class="label">网络告警ID</td>
        <td class="content">${sheetMain.mainAlarmId}</td>
        <td class="label">网管告警流水号</td>
        <td class="content">${sheetMain.mainAlarmNum}</td>
    </tr>
    <tr>
        <td class="label">告警清除时间</td>
        <td class="content" colspan='3'>
            <%=StaticMethod.date2String(basemain.getMainAlarmSolveDate()) %>
        </td>
    </tr>
    <tr>
        <td class="label">告警级别</td>
        <td class="content">
            ${sheetMain.mainAlarmLevel}
        </td>
        <td class="label">告警来源</td>
        <td class="content">
            ${sheetMain.mainAlarmSource}
        </td>

    </tr>
    <tr>
        <td class="label">告警逻辑分类</td>
        <td class="content">
            ${sheetMain.mainAlarmLogicSort}
        </td>
        <td class="label">告警逻辑子类</td>
        <td class="content">
            ${sheetMain.mainAlarmLogicSortSub}
        </td>
    </tr>
    <tr>
        <td class="label">告警描述</td>
        <td class="content" colspan='3'>
            ${sheetMain.mainAlarmDesc}
        </td>
    </tr>
</table>
<br/>

<table class="formTable">
    <caption></caption>
    <tr>
        <td class="label">网络分类(一级)</td>
        <td class="content"><%=id2NameService.id2Name(basemain.getMainNetSortOne(), "ItawSystemDictTypeDao")%>
        </td>
        <td class="label">网络分类(二级)</td>
        <td class="content"><%=id2NameService.id2Name(basemain.getMainNetSortTwo(), "ItawSystemDictTypeDao")%>
        </td>
    </tr>
    <tr>
        <td class="label">网络分类(三级)</td>
        <td class="content"><%=id2NameService.id2Name(basemain.getMainNetSortThree(), "ItawSystemDictTypeDao")%>
        </td>

        <td class="label">故障处理响应级别</td>
        <td class="content"><%=id2NameService.id2Name(basemain.getMainFaultResponseLevel(), "ItawSystemDictTypeDao")%>
        </td>

    </tr>
    <tr>
        <td class="label">工单受理时限</td>
        <td class="content">
            <%=StaticMethod.date2String(basemain.getSheetAcceptLimit()) %>
        </td>
        <td class="label">工单处理时限</td>
        <td class="content">
            <%=StaticMethod.date2String(basemain.getSheetCompleteLimit()) %>
        </td>
    </tr>
    <tr>
        <td class="label">T1处理时限</td>
        <td class="content">
            <%=StaticMethod.date2String(basemain.getMainCompleteLimitT1()) %>
        </td>
        <td class="label">T2处理时限</td>
        <td class="content">
            <%=StaticMethod.date2String(basemain.getMainCompleteLimitT2()) %>
        </td>
    </tr>
    <tr>
        <td class="label">T3处理时限</td>
        <td class="content" colspan='3'>
            <%=StaticMethod.date2String(basemain.getMainCompleteLimitT3()) %>
        </td>
    </tr>

    <tr>
        <td class="label">派单方式</td>
        <td class="content"><%=id2NameService.id2Name(basemain.getMainSendMode(), "ItawSystemDictTypeDao")%>
        </td>
        <td class="label">网元名称</td>
        <td class="content">${sheetMain.mainNetName}</td>
    </tr>

    <tr>
        <td class="label">故障设备厂商</td>
        <td class="content"><%=id2NameService.id2Name(basemain.getMainEquipmentFactory(), "ItawSystemDictTypeDao")%>
        </td>
        <td class="label">故障设备型号</td>
        <td class="content">${sheetMain.mainEquipmentModel}</td>
    </tr>

    <tr>
        <td class="label">故障省份</td>
        <td class="content">${sheetMain.mainFaultGenerantPriv}</td>

        <td class="label">故障地市</td>
        <td class="content"><%=id2NameService.id2Name(basemain.getToDeptId(), "tawSystemAreaDao")%>
        </td>
    </tr>

    <tr>
        <td class="label">故障发生时间</td>
        <td class="content"><%=StaticMethod.date2String(basemain.getMainFaultGenerantTime())%>
        </td>
        <td class="label">故障发现方式</td>
        <td class="content"><%=id2NameService.id2Name(basemain.getMainFaultDiscoverableMode(), "ItawSystemDictTypeDao")%>
        </td>

    </tr>
    <tr>
        <td class="label">是否影响业务</td>
        <td class="content"><%=id2NameService.id2Name(basemain.getMainIfAffectOperation(), "ItawSystemDictTypeDao")%>
        </td>
        <td class="label">是否预处理</td>
        <td class="content"><%=id2NameService.id2Name(basemain.getMainPretreatment(), "ItawSystemDictTypeDao")%>
        </td>

    </tr>
    <tr>
        <td class="label">相关投诉处理工单号</td>
        <td colspan='3' class="content">
            ${sheetMain.mainApplySheetId}
        </td>
    </tr>


    <tr>
        <td class="label">附件</td>
        <td colspan='5'>
            <%
                if (basemain.getSheetAccessories() != null) {
                    String fileName = basemain.getSheetAccessories().replaceAll("'", "");
                    String[] fileList = fileName.split(",");
                    for (int i = 0; i < fileList.length; i++) {
                        String file = fileList[i];
                        TawCommonsAccessories accessories = mgr.getTawCommonsAccessoriesByName(file);
                        String href = "<a href=\"" + filePath + accessories.getId() + "\">" + accessories.getAccessoriesCnName() + "</a>";
                        out.println(href);
                    }
                }
            %>
        </td>
    </tr>

</table>
<br/>
<br/>


<caption>工单历史信息</caption>

<logic:present name="HISTORY" scope="request">
        <%int jNum=0;%>
<logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.commonfault.model.CommonFaultLink">
        <%
		        jNum += 1;
		        String divName ="buzhou"+jNum;
       		%>
<div class="history-item">&nbsp;
    <div class="history-item-title">
        <%=jNum%>.
        <%=StaticMethod.date2String(baselink.getOperateTime())%>&nbsp;
        <%=id2NameService.id2Name(baselink.getOperateUserId(), "tawSystemUserDao")%>
        操作类型:
        <%=service.itemId2name("dict-sheet-commonfault#mainOperateType", baselink.getOperateType().intValue() + "")%>
        <div class="history-item-content">
            <table class="history-item-table" width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td class="column-title">
                        操作人
                    </td>
                    <td class="column-content">
                        <%=id2NameService.id2Name(baselink.getOperateUserId(), "tawSystemUserDao")%>
                    </td>
                    <td class="column-title">
                        操作人部门
                    </td>
                    <td class="column-content">
                        <%=id2NameService.id2Name(baselink.getOperateDeptId(), "tawSystemDeptDao")%>
                    </td>
                </tr>
                <tr>
                    <td class="column-title">
                        操作人角色
                    </td>
                    <td class="column-content">
                        <%=id2NameService.id2Name(baselink.getOperateRoleId(), "tawSystemSubRoleDao")%>
                        　<%=id2NameService.id2Name(baselink.getOperateRoleId(), "tawSystemUserDao")%>
                    </td>
                    <td class="column-title">
                        操作人联系方式
                    </td>
                    <td class="column-content">
                            ${baselink.operaterContact}
                    </td>
                </tr>
                <tr>
                    <td class="column-title">
                        操作时间
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.operateTime}
                    </td>
                </tr>

                <tr>
                    <td class="column-title">
                        操作类型
                    </td>
                    <td class="column-content">
                        <%=service.itemId2name("dict-sheet-commonfault#mainOperateType", baselink.getOperateType().intValue() + "")%>
                    </td>
                    <td class="column-title">
                        派往对象
                    </td>
                    <td class="column-content">
                        <%=id2NameService.id2Name(baselink.getToOrgRoleId(), "tawSystemSubRoleDao")%>
                        　<%=id2NameService.id2Name(baselink.getToOrgRoleId(), "tawSystemUserDao")%>
                    </td>
                </tr>
                <tr>
                    <td class="column-title">
                        受理时限
                    </td>
                    <td class="column-content">
                            ${baselink.nodeAcceptLimit}
                    </td>
                    <td class="column-title">
                        处理时限
                    </td>
                    <td class="column-content">
                            ${baselink.nodeCompleteLimit}
                    </td>
                </tr>

                <%
                    //T1环节
                    if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("FirstExcuteHumTask")) {
                %>
                <%
                    //移交T2
                    if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 1) {
                %>

                <tr>
                    <td class="column-title">
                        是否涉及互联互通
                    </td>
                    <td class="column-content">
                        <%=id2NameService.id2Name(baselink.getLinkIfMutualCommunication(), "ItawSystemDictTypeDao")%>
                    </td>
                    <td class="column-title">
                        是否涉及安全
                    </td>
                    <td class="column-content">
                        <%=id2NameService.id2Name(baselink.getLinkIfSafe(), "ItawSystemDictTypeDao")%>
                    </td>
                </tr>
                <tr>
                    <td class="column-title">
                        是否重大故障
                    </td>
                    <td class="column-content" colspan=3>
                        <%=id2NameService.id2Name(baselink.getLinkIfGreatFault(), "ItawSystemDictTypeDao")%>
                    </td>
                </tr>
                <tr>
                    <td class="column-title">
                        故障初步处理情况
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.linkFaultFirstDealDesc}

                    </td>
                </tr>

                <tr>
                    <td class="column-title">
                        故障描述
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.linkFaultDesc}

                    </td>
                </tr>

                <%} %>

                <%
                    //处理完成
                    if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 46) {
                %>
                <tr>
                    <td class="column-title">
                        故障处理结果
                    </td>
                    <td class="column-content">
                        <%=id2NameService.id2Name(baselink.getLinkFaultDealResult(), "ItawSystemDictTypeDao")%>

                    </td>
                    <td class="column-title">
                        是否重大故障
                    </td>
                    <td class="column-content">
                        <%=id2NameService.id2Name(baselink.getLinkIfGreatFault(), "ItawSystemDictTypeDao")%>

                    </td>
                </tr>
                <tr>
                    <td class="column-title">
                        故障原因类别
                    </td>
                    <td class="column-content">
                        <%=id2NameService.id2Name(baselink.getLinkFaultReasonSort(), "ItawSystemDictTypeDao")%>

                    </td>
                    <td class="column-title">
                        故障原因细分
                    </td>
                    <td class="column-content">
                        <%=id2NameService.id2Name(baselink.getLinkFaultReasonSubsection(), "ItawSystemDictTypeDao")%>

                    </td>
                </tr>
                <tr>
                    <td class="column-title">
                        处理措施
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.linkDealStep}

                    </td>
                </tr>
                <tr>
                    <td class="column-title">
                        是否实施网络变更
                    </td>
                    <td class="column-content" colspan=3>
                        <%=id2NameService.id2Name(baselink.getLinkIfExcuteNetChange(), "ItawSystemDictTypeDao")%>

                    </td>
                </tr>

                <tr>
                    <td class="column-title">
                        是否为最终解决方案
                    </td>
                    <td class="column-content" colspan=3>
                        <%=id2NameService.id2Name(baselink.getLinkIfFinallySolveProject(), "ItawSystemDictTypeDao")%>

                    </td>
                </tr>

                <tr>
                    <td class="column-title">
                        是否申请入案例库
                    </td>
                    <td class="column-content" colspan=3>
                        <%=id2NameService.id2Name(baselink.getLinkIfAddCaseDataBase(), "ItawSystemDictTypeDao")%>

                    </td>
                </tr>
                <tr>
                    <td class="column-title">
                        故障消除时间>
                    </td>
                    <td class="column-content">
                            ${baselink.linkFaultAvoidTime}

                    </td>
                    <td class="column-title">
                        业务恢复时间
                    </td>
                    <td class="column-content">
                            ${baselink.linkOperRenewTime}

                    </td>
                </tr>

                <tr>
                    <td class="column-title">
                        影响业务时长
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.linkAffectTimeLength}

                    </td>
                </tr>
                <%} %>
                <%
                    //分派回复
                    if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 11) {
                %>

                <tr>
                    <td class="column-title">
                        是否重大故障
                    </td>
                    <td class="column-content" colspan=3>
                        <%=id2NameService.id2Name(baselink.getLinkIfGreatFault(), "ItawSystemDictTypeDao")%>

                    </td>
                </tr>

                <tr>
                    <td class="column-title">
                        故障原因类别
                    </td>
                    <td class="column-content">
                        <%=id2NameService.id2Name(baselink.getLinkFaultReasonSort(), "ItawSystemDictTypeDao")%>

                    </td>
                    <td class="column-title">
                        故障原因细分
                    </td>
                    <td class="column-content">
                        <%=id2NameService.id2Name(baselink.getLinkFaultReasonSubsection(), "ItawSystemDictTypeDao")%>

                    </td>
                </tr>

                <tr>
                    <td class="column-title">
                        处理措施
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.linkDealStep}

                    </td>
                </tr>
                <tr>
                    <td class="column-title">
                        故障消除时间
                    </td>
                    <td class="column-content">
                            ${baselink.linkFaultAvoidTime}

                    </td>
                    <td class="column-title">
                        业务恢复时间
                    </td>
                    <td class="column-content">
                            ${baselink.linkOperRenewTime}

                    </td>
                </tr>

                <tr>
                    <td class="column-title">
                        影响业务时长
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.linkAffectTimeLength}

                    </td>
                </tr>
                <%} %>

                <%
                    //延期申请
                    if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 5) {
                %>

                <tr>
                    <td class="column-title">
                        申请内容
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.linkTransmitContent}

                    </td>
                </tr>

                <%} %>

                <%} %>

                <%if (baselink.getActiveTemplateId() != null && (baselink.getActiveTemplateId().equals("SecondExcuteHumTask") || baselink.getActiveTemplateId().equals("secondSubTask"))) {%>

                <%
                    //移交T3
                    if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 2) {
                %>
                <tr>
                    <td class="column-title">
                        是否重大故障
                    </td>
                    <td class="column-content" colspan=3>
                        <%=id2NameService.id2Name(baselink.getLinkIfGreatFault(), "ItawSystemDictTypeDao")%>

                    </td>
                </tr>
                <tr>
                    <td class="label">故障处理情况</td>
                    <td colspan="3">
                            ${baselink.linkFaultDealInfo}

                    </td>
                </tr>

                <tr>
                    <td class="column-title">
                        移交理由
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.linkTransmitReason}

                    </td>
                </tr>

                <%} %>
                <%
                    //分派回复
                    if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 11) {
                %>

                <tr>
                    <td class="column-title">
                        是否重大故障
                    </td>
                    <td class="column-content" colspan=3>
                        <%=id2NameService.id2Name(baselink.getLinkIfGreatFault(), "ItawSystemDictTypeDao")%>

                    </td>
                </tr>

                <tr>
                    <td class="column-title">
                        故障原因类别
                    </td>
                    <td class="column-content">
                        <%=id2NameService.id2Name(baselink.getLinkFaultReasonSort(), "ItawSystemDictTypeDao")%>

                    </td>
                    <td class="column-title">
                        故障原因细分
                    </td>
                    <td class="column-content">
                        <%=id2NameService.id2Name(baselink.getLinkFaultReasonSubsection(), "ItawSystemDictTypeDao")%>

                    </td>
                </tr>

                <tr>
                    <td class="column-title">
                        处理措施
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.linkDealStep}

                    </td>
                </tr>
                <tr>
                    <td class="column-title">
                        故障消除时间
                    </td>
                    <td class="column-content">
                            ${baselink.linkFaultAvoidTime}

                    </td>
                    <td class="column-title">
                        业务恢复时间
                    </td>
                    <td class="column-content">
                            ${baselink.linkOperRenewTime}

                    </td>
                </tr>

                <tr>
                    <td class="column-title">
                        影响业务时长
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.linkAffectTimeLength}

                    </td>
                </tr>
                <%} %>
                <%
                    //分派回复
                    if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 11) {
                %>

                <tr>
                    <td class="column-title">
                        是否重大故障
                    </td>
                    <td class="column-content" colspan=3>
                        <%=id2NameService.id2Name(baselink.getLinkIfGreatFault(), "ItawSystemDictTypeDao")%>

                    </td>
                </tr>

                <tr>
                    <td class="column-title">
                        故障原因类别
                    </td>
                    <td class="column-content">
                        <%=id2NameService.id2Name(baselink.getLinkFaultReasonSort(), "ItawSystemDictTypeDao")%>

                    </td>
                    <td class="column-title">
                        故障原因细分
                    </td>
                    <td class="column-content">
                        <%=id2NameService.id2Name(baselink.getLinkFaultReasonSubsection(), "ItawSystemDictTypeDao")%>

                    </td>
                </tr>

                <tr>
                    <td class="column-title">
                        处理措施
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.linkDealStep}

                    </td>
                </tr>
                <tr>
                    <td class="column-title">
                        故障消除时间
                    </td>
                    <td class="column-content">
                            ${baselink.linkFaultAvoidTime}

                    </td>
                    <td class="column-title">
                        业务恢复时间
                    </td>
                    <td class="column-content">
                            ${baselink.linkOperRenewTime}

                    </td>
                </tr>

                <tr>
                    <td class="column-title">
                        影响业务时长
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.linkAffectTimeLength}

                    </td>
                </tr>
                <%} %>
                <%
                    //延期申请
                    if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 5) {
                %>

                <tr>
                    <td class="column-title">
                        申请内容
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.linkTransmitContent}

                    </td>
                </tr>

                <%} %>

                <%} %>

                <%
                    //T3处理环节
                    if (baselink.getActiveTemplateId() != null && (baselink.getActiveTemplateId().equals("ThirdExcuteHumTask")
                            || baselink.getActiveTemplateId().equals("firstSubTask"))) {
                %>
                <%
                    //处理完成
                    if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 46) {
                %>
                <tr>
                    <td class="column-title">
                        故障处理结果
                    </td>
                    <td class="column-content">
                        <%=id2NameService.id2Name(baselink.getLinkFaultDealResult(), "ItawSystemDictTypeDao")%>

                    </td>
                    <td class="column-title">
                        是否重大故障
                    </td>
                    <td class="column-content">
                        <%=id2NameService.id2Name(baselink.getLinkIfGreatFault(), "ItawSystemDictTypeDao")%>

                    </td>
                </tr>
                <tr>
                    <td class="column-title">
                        故障原因类别
                    </td>
                    <td class="column-content">
                        <%=id2NameService.id2Name(baselink.getLinkFaultReasonSort(), "ItawSystemDictTypeDao")%>

                    </td>
                    <td class="column-title">
                        故障原因细分
                    </td>
                    <td class="column-content">
                        <%=id2NameService.id2Name(baselink.getLinkFaultReasonSubsection(), "ItawSystemDictTypeDao")%>

                    </td>
                </tr>
                <tr>
                    <td class="column-title">
                        处理措施
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.linkDealStep}

                    </td>
                </tr>
                <tr>
                    <td class="column-title">
                        是否实施网络变更
                    </td>
                    <td class="column-content" colspan=3>
                        <%=id2NameService.id2Name(baselink.getLinkIfExcuteNetChange(), "ItawSystemDictTypeDao")%>

                    </td>
                </tr>

                <tr>
                    <td class="column-title">
                        是否为最终解决方案
                    </td>
                    <td class="column-content" colspan=3>
                        <%=id2NameService.id2Name(baselink.getLinkIfFinallySolveProject(), "ItawSystemDictTypeDao")%>

                    </td>
                </tr>

                <tr>
                    <td class="column-title">
                        是否申请入案例库
                    </td>
                    <td class="column-content" colspan=3>
                        <%=id2NameService.id2Name(baselink.getLinkIfAddCaseDataBase(), "ItawSystemDictTypeDao")%>

                    </td>
                </tr>
                <tr>
                    <td class="column-title">
                        故障消除时间>
                    </td>
                    <td class="column-content">
                            ${baselink.linkFaultAvoidTime}

                    </td>
                    <td class="column-title">
                        业务恢复时间
                    </td>
                    <td class="column-content">
                            ${baselink.linkOperRenewTime}

                    </td>
                </tr>

                <tr>
                    <td class="column-title">
                        影响业务时长
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.linkAffectTimeLength}

                    </td>
                </tr>
                <%} %>
                <%
                    //分派回复
                    if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 11) {
                %>

                <tr>
                    <td class="column-title">
                        是否重大故障
                    </td>
                    <td class="column-content" colspan=3>
                        <%=id2NameService.id2Name(baselink.getLinkIfGreatFault(), "ItawSystemDictTypeDao")%>
                    </td>
                </tr>

                <tr>
                    <td class="column-title">
                        故障原因类别
                    </td>
                    <td class="column-content">
                        <%=id2NameService.id2Name(baselink.getLinkFaultReasonSort(), "ItawSystemDictTypeDao")%>
                    </td>
                    <td class="column-title">
                        故障原因细分
                    </td>
                    <td class="column-content">
                        <%=id2NameService.id2Name(baselink.getLinkFaultReasonSubsection(), "ItawSystemDictTypeDao")%>
                    </td>
                </tr>

                <tr>
                    <td class="column-title">
                        处理措施
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.linkDealStep}
                    </td>
                </tr>
                <tr>
                    <td class="column-title">
                        故障消除时间
                    </td>
                    <td class="column-content">
                            ${baselink.linkFaultAvoidTime}
                    </td>
                    <td class="column-title">
                        业务恢复时间
                    </td>
                    <td class="column-content">
                            ${baselink.linkOperRenewTime}
                    </td>
                </tr>

                <tr>
                    <td class="column-title">
                        影响业务时长
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.linkAffectTimeLength}
                    </td>
                </tr>
                <%} %>

                <%
                    //延期申请
                    if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 5) {
                %>

                <tr>
                    <td class="column-title">
                        申请内容
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.linkTransmitContent}
                    </td>
                </tr>

                <%} %>

                <%} %>

                <%
                    //延期申请审批
                    if (baselink.getActiveTemplateId() != null && (baselink.getActiveTemplateId().equals("ExamineHumTask")
                            || baselink.getActiveTemplateId().equals("fourSubTask"))) {
                %>


                <tr>
                    <td class="column-title">
                        是否延期
                    </td>
                    <td class="column-content" colspan=3>
                        <%=id2NameService.id2Name(baselink.getLinkIfDeferResolve(), "ItawSystemDictTypeDao")%>
                    </td>
                </tr>
                <tr>
                    <td class="column-title">
                        审批内容
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.linkExamineContent}
                    </td>
                </tr>

                <%} %>
                <%
                    //归档环节
                    if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("HoldHumTask")) {
                %>

                <%if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 17) {%>
                <tr>
                    <td class="column-title">
                        退回原因
                    </td>
                    <td class="column-content" colspan=3>
                        <%=id2NameService.id2Name(baselink.getLinkUntreadReason(), "ItawSystemDictTypeDao")%>
                    </td>
                </tr>
                <tr>
                    <td class="column-title">
                        退回意见
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.linkUntreadIdea}
                    </td>
                </tr>

                <%} %>


                <%} %>
                <%
                    //非流程动作（抄送、阶段回复、阶段通知、驳回）
                    if (baselink.getActiveTemplateId() != null && (baselink.getActiveTemplateId().equals("cc")
                            || baselink.getOperateType().intValue() == 9 || baselink.getActiveTemplateId().equals("advice") ||
                            baselink.getOperateType().intValue() == 4)) {
                %>

                <tr>
                    <td class="column-title">
                        意见
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.remark}
                    </td>
                </tr>

                <%} %>
                <%
                    //确认受理
                    if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 61) {
                %>

                <tr>
                    <td class="column-title">
                        操作描述
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.remark}
                    </td>
                </tr>
                <%} %>
                <%
                    //移交
                    if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 8) {
                %>

                <tr>
                    <td class="column-title">
                        移交理由
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.transferReason}
                    </td>
                </tr>
                <%} %>
                <%
                    //处理回复
                    if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 6 || baselink.getOperateType().intValue() == 7)) {
                %>
                <tr>
                    <td class="column-title">
                        备注
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.remark}
                    </td>
                </tr>
                <%} %>
                <%
                    //分派
                    if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 10) {
                %>
                <tr>
                    <td class="column-title">
                        是否重大故障
                    </td>
                    <td class="column-content" colspan=3>
                        <%=id2NameService.id2Name(baselink.getLinkIfGreatFault(), "ItawSystemDictTypeDao")%>
                    </td>

                </tr>
                <tr>
                    <td class="column-title">
                        故障处理情况
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.linkFaultDealInfo}
                    </td>
                </tr>
                <tr>
                    <td class="column-title">
                        分派理由
                    </td>
                    <td class="column-content" colspan=3>
                            ${baselink.remark}
                    </td>

                </tr>

                <%} %>

                <%
                    //附件
                    if (baselink.getNodeAccessories() != null && !baselink.getNodeAccessories().equals("")) {
                %>
                <tr>
                    <td>
                        附件
                    </td>
                    <td class="column-content" colspan=3>
                        <%
                            String fileName = baselink.getNodeAccessories().replaceAll("'", "");
                            String[] fileList = fileName.split(",");
                            for (int i = 0; i < fileList.length; i++) {
                                String file = fileList[i];
                                TawCommonsAccessories accessories = mgr.getTawCommonsAccessoriesByName(file);
                                String href = "<a href=\"" + filePath + accessories.getId() + "\">" + accessories.getAccessoriesCnName() + "</a>";
                                out.println(href);
                            }
                        %>
                    </td>
                </tr>


                <%}%>

            </table>

        </div>
    </div>
</div>
</logic:iterate>
</logic:present>

<br>







