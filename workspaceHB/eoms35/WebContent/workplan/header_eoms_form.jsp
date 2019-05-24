<%@ page language="java" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
 
<html>
<head>
  <title> </title>
  <style  >
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
        a,a:link
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
            font-family: tahoma, arial, helvetica, sans-serif;
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
        }

        strong,b {
            font-weight: bold;
        }

        em,i {
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

        table caption {
        	text-align:left;
            font-weight: bold;
            margin: 0 0 0 0;
            padding: 0 0 1em 0;
        }

        th {
            font-weight: bold;
            text-align: left;
        }

        td {
            font-size: 12px;
        }

		.listTable, .table{
			width: 100%;
			/* hack for ie */
			*width: 98%;
			border-collapse: collapse;
			font-size: 12px;
		}
		.listTable caption, .table caption{
			color:#444;
		}
		.listTable td, .table td, .table th{
			border: 1px solid #c1dad7;
			padding: 6px 6px 6px 6px;
			background-color:#FFFFFF;
		}
		.listTable tr.header td, .listTable thead td, .table thead th{
			background: #cae8ea;
			color:#006699;
			font-weight:bold;	
			word-break:keep-all;
			word-wrap:normal;
		}
		tr.colorrow td{
			background: #f5fafa;
		}
		.listTable td.icon {
			width:20px;
			background-repeat:no-repeat;
			background-position:center;
		}
		td.imageColumn,th.imageColumn{
			width:10px;
			text-align:center;
		}
		td.image, th.image{
			vertical-align:middle;
			text-align:center;
		}
		tr.serious .icon {
			background-image:url(../../images/icons/sheet-icons/exclamation.png);
		}
		tr.alert .icon {
			background-image:url(../../images/icons/sheet-icons/error.png);
		}
		tr.normal .icon {
			background-image:url(../../images/icons/sheet-icons/information.png);
		}
		
		tr.marked .icon {
			background-image:url(../../images/icons/sheet-icons/flag_orange.png);
		}
		tr.complete .icon {
			background-image:url(../../images/icons/sheet-icons/accept.png);
		}
		tr.finish .icon {
			background-image:url(../../images/icons/sheet-icons/page_save.png);
		}
		
		.listTable a:link, .table a:link {
			color: #006699;
			text-decoration:none;
		}
		
		.listTable a:visited, .table a:visited {
			color: #999;
			text-decoration:none;
		}
		.listTable a:hover, .listTable a:active , .table a:hover, .table a:active{
			color: #006699;
			text-decoration:underline;
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


 
div{
	font-size:12px;
}
.tabContent{
	padding:15px;
	display:none;
}
.show{
	display : block;
}
.hide{
	display : none;
}
.tip{
	color:#77BC60;
	font-weight:bold;
}
.textHeader{
	font-weight:bold;
}
div.switchIcon{
	display:inline;
	width:11px;
	height:11px;
	background:url(../../images/icons/closed.gif) no-repeat scroll left top;
}
div.switchIcon .opened{
	background:url(../../images/icons/opened.gif) no-repeat scroll left top;
}
 
.viewer-box{
	margin:10px 0;
	padding:10px;
	background-color:#f1f1f1;
	border:1px solid #dadada;
}
.viewer-list{
	padding:5px 0px;
}
.viewer-list div{
	margin:6px 0;
}
.viewlistitem-user{
	display:inline;
    color: #336699;
	padding:3px 0 0 22px;
	vertical-align:middle;
	background-image:url(../../images/icons/user.gif);
	background-repeat:no-repeat;
	background-position:3px;
	cursor:default;
}

.viewlistitem-role, .viewlistitem-subrole{
	display:block;
    color: #336699;
	padding:3px 0 0 22px;
	vertical-align:middle;
	background-image:url(../../images/icons/role.gif);
	background-repeat:no-repeat;
	background-position:3px;
	cursor:default;
}
.viewlistitem-dept{
	display:block;
    color: #336699;
	padding:3px 0 0 22px;
	vertical-align:middle;
	background-image:url(../../images/icons/dept.gif);
	background-repeat:no-repeat;
	background-position:3px;
	cursor:default;	
}
  </style>
    <!-- HTTP 1.1 -->
  <meta http-equiv="Cache-Control" content="no-store"/>
  <!-- HTTP 1.0 -->
  <meta http-equiv="Pragma" content="no-cache"/>
  <!-- Prevents caching at the Proxy Server --> 
  <meta http-equiv="Expires" content="0"/>
  <meta http-equiv="Content-Type" content="text/html; charset=GB2312"/>
  <script type="text/javascript" charset="utf-8" src="<%=basePath%>/scripts/local/zh_CN.js"></script>  
  <script type="text/javascript" charset="utf-8" src="<%=basePath%>/scripts/base/eoms.js"></script>
  
  <link rel="stylesheet" type="text/css" media="all" href="<%=basePath%>/styles/default/tools.css" />
 <link rel="stylesheet" type="text/css" media="all" href="<%=basePath%>/styles/default/layout-navtop-subright.css" />
 <link rel="stylesheet" type="text/css" media="all" href="<%=basePath%>/styles/default/layout.css" />
 <link rel="stylesheet" type="text/css" media="all" href="<%=basePath%>/styles/default/forms.css" />
 <link rel="stylesheet" type="text/css" media="all" href="<%=basePath%>/styles/default/pages.css" />
 <link rel="stylesheet" type="text/css" media="all" href="<%=basePath%>/styles/default/messages.css" />
 <link rel="stylesheet" type="text/css" media="all" href="<%=basePath%>/styles/displaytag.css" /> 
 
 
 </head>
 <body>
<div id="page">
<%-- Put constants into request scope --%> 
  <div id="content" class="clearfix">
    <div id="main"><br/><br/>
