/**
 * <p>Description: 显示流程图</p>
 * @author mios
 * @version 1.2.1 样式调整、甬道宽度调整、浏览器显示
 * @include "/flowview/FlowViewWorkSpace.js"
 */
var xmlDoc, version, cells, lines, Page, ctx;
var missCells = new Array(), missLines = new Array();

var FlowView = function (el) {
    Page = el;
    ctx = $('canvas').getContext("2d");

}

FlowView.NODESTATE_NORMAL = 0;
FlowView.NODESTATE_HISTORY = 1;
FlowView.NODESTATE_CURSTEP = 2;

FlowView.prototype.getFlow = function (url) {
    if (eoms.isIE) {
        xmlDoc = new ActiveXObject("Msxml2.DOMDocument.3.0");
    } else if (document.implementation && document.implementation.createDocument) {
        xmlDoc = document.implementation.createDocument('', '', null);
    }
    if (steps.length <= 0 || actions.length <= 0) {
        return;
    }
    xmlDoc.async = false;
    xmlDoc.load(url);
    if (eoms.isIE) {
        if (xmlDoc.parseError.errorCode != 0) {
            var error = xmlDoc.parseError;
            alert(error.reason)
            return;
        }

        var versionNode = xmlDoc.selectSingleNode("//eomsversion");
        if (versionNode) {
            version = versionNode.getAttribute("value");
        }
    } else {
        var versionNode = xmlDoc.getElementsByTagName("eomsversion");
        if (versionNode.length != 0) {
            version = versionNode[0].getAttribute("value");
        }
    }

    //流程甬道
    drawChannels();

    //流程步骤
    steps.each(function (step) {
        var sNode = getCellNode(step.id);
        if (sNode == null) {
            missCells.push("Step:" + step.name + " ID:" + step.id);
        } else {
            var nodeType = sNode.getAttribute("type");
            var node;
            switch (nodeType) {
                //启动节点
                case "InitialActionCell" :
                    node = new startNode(sNode, step);
                    break;
                //普通节点
                default :
                    node = new Node(sNode, step);

            }
            node.draw();
        }
    });
    //流程动作
    actions.each(function (action) {
        var sNode = getLineNode(action.id);
        if (sNode != null && getCellNode(action.from) != null
            && getCellNode(action.to) != null) {
            var curLine = new stepLine(sNode, action);
        } else {
            if (sNode == null)
                missLines.push("Action:" + action.view + " ID:" + action.id);
            if (getCellNode(action.from) == null)
                missCells.push("Step:<action id=" + action.id + ">??????? ID:"
                    + action.from);
            if (getCellNode(action.to) == null)
                missCells.push("Step:<action id=" + action.id + ">??????? ID:"
                    + action.to);
        }
    });

    if (missCells.length > 0) {
        alert("下列流程的step没有定义在*.lyt.xml文件中:\n" + missCells.join("\n"))
    }
    if (missLines.length > 0) {
        alert("下列流程的action没有定义在*.lyt.xml文件中:\n" + missLines.join("\n"))
    }

    //????iframe????
    if (parent == window)
        return
    try {
        var i = parent.document.getElementById(window.name);
        var iHeight = i["innerHeight"] || document.body.scrollHeight;
        i["innerHeight"] = iHeight;
        i.style.height = (iHeight + 60) + "px";
    } catch (e) {
    }
}

/**
 * <p>甬道</p>
 * y1,y2是甬道的上下边界的Y坐标
 */
function drawChannels() {
    if (eoms.isIE) {
        var channels = xmlDoc.selectNodes("//channel");
    } else {
        var channels = xmlDoc.getElementsByTagName("channel");
    }
    $A(channels).each(function (c, index) {
        //alert(c.xml);
        //创建构成甬道div
        var chl = Toolkit.newLayer();
        $(chl).addClass("fv-chl");
        chl.style.height = c.getAttribute("y2") - c.getAttribute("y1");
        chl.style.top = parseInt(c.getAttribute("y1")) + "px";
        //修正宽度，因为有滚动条时宽度会异常
        $(chl).setStyle("width:"
            + (parent == window ? "100%" : Page.scrollWidth));
        var chlText = Toolkit.newLayer();
        $(chlText).addClass("fv-chl-txt");

        //创建甬道文字和图标
        var cIcon = Toolkit.newImage();
        cIcon.src = FlowViewWorkSpace.FLOW_PATH + "/img/role.gif";
        $(cIcon).setStyle("align:absBottom;margin-right:5px;filter:alpha(opacity=50);");
        chlText.appendChild(cIcon);
        if (eoms.isIE) {
            chlText.innerText += c.getAttribute("text");
        } else {
            chlText.innerHTML += c.getAttribute("text");
        }
        //隔行换色
        if (index % 2 == 0) {
            $(chl).addClass("fv-chl-even");
            $(chlText).addClass("fv-chl-even");
        }

        chl.appendChild(chlText);
        Page.appendChild(chl);

    });
}

/**
 * <p>普通节点</p>
 * @param {DOM node} cell
 * @param {String} step
 */
function Node(cell, config) {
    this.base = stepNode;
    this.id = cell.getAttribute("id");
    this.name = config.name;
    this.imageUrl = FlowViewWorkSpace.FLOW_PATH + "/img/node.gif";
    //节点状态
    if (isCurStep(this.id)) {
        this.state = FlowView.NODESTATE_CURSTEP;
        this.imageUrl = FlowViewWorkSpace.FLOW_PATH + "/img/start.gif";
    } else if (isHistoryStep(this.id)) {
        this.state = FlowView.NODESTATE_HISTORY;
    } else {
        this.state = FlowView.NODESTATE_NORMAL;
    }
    this.base(cell, config);

}

/**
 * <p>启动节点</p>
 * @param {DOM node} cell
 * @param {object} step
 */
function startNode(cell, config) {
    this.base = stepNode;
    this.id = cell.getAttribute("id");
    this.name = config.name || "启动";
    this.imageUrl = FlowViewWorkSpace.FLOW_PATH + "/img/node.gif";
    this.state = FlowView.NODESTATE_HISTORY;
    this.base(cell, config);
}

/**
 * <p>节点父类</p>
 * @param {DOM node} cell
 * @param {object} step
 */
function stepNode(cell, config) {
    this.x = cellGet(cell, "x");
    this.y = cellGet(cell, "y");
    this.width = cellGet(cell, "width");
    this.height = cellGet(cell, "height");

    this.el = Toolkit.newLayer();
    this.el.id = "fv-cell-" + cellGet(cell, "id");
    this.id = this.el.id;
    this.el.style.left = this.x;
    this.el.style.top = this.y;
    this.el.style.width = this.width;
    this.el.style.height = this.height;

    switch (this.state) {
        case FlowView.NODESTATE_NORMAL :
            this.el.className = "flowview_node";
            break;
        case FlowView.NODESTATE_HISTORY :
            this.el.className = "history_node";
            break;
        case FlowView.NODESTATE_CURSTEP :
            this.el.className = "curstep_node";
            break;
        default :
            this.el.className = "flowview_node";
    }

    this.table = Toolkit.newTable();
    this.table.width = "100%";
    this.table.height = "100%";
    this.table.cellPadding = 0;
    this.table.cellSpacing = 0;
    this.el.appendChild(this.table);

    //
    var titleRow = this.table.insertRow(-1);
    titleRow.className = "TITLE";

    var titleImgCell = titleRow.insertCell(-1);
    titleImgCell.className = "IMG";

    var titleImg = Toolkit.newImage();
    titleImg.src = this.imageUrl;
    titleImgCell.appendChild(titleImg);

    //
    this.titleTxtCell = titleRow.insertCell(-1);
    this.titleTxtCell.className = "TXT";
    if (eoms.isIE) {
        this.titleTxtCell.innerText = this.name;
    } else {
        this.titleTxtCell.innerHTML = this.name;
    }


    if (this.state != FlowView.NODESTATE_NORMAL && config.url) {
        this.el.style.cursor = "hand";
        this.el.onclick = function () {
            eoms.openWindow(config.url);
        };
    }

    if (config.tips) {
        var el = eoms.$(this.el);
        el.on("mouseover", function (div, e) {
            var p = Toolkit.getXY($(this.id), $("workflowCanvas"));
            if ((p[0] + this.width + 230) > Page.clientWidth) {
                p[0] -= 235;
            } else {
                p[0] += this.width;
            }
            p[1] -= 2;
            showTips(config, p);
        }, this);
        el.on("mouseout", function () {
            hideTips(config);
        }, this);
    }

    this.draw = function () {
        Page.appendChild(this.el);
    }
}

/**
 * <p>流程线</p>
 * @param {DOM node}connector
 * @param {object}action
 */
function stepLine(connector, action) {
    this.id = connector.getAttribute("id");
    this.fromId = action.from;
    this.toId = action.to;
    this.view = action.text || action.view;
    this.labelx = connector.getAttribute("labelx");
    this.labely = connector.getAttribute("labely");

    if (getCellNode(this.fromId) == null || getCellNode(this.toId) == null) {
        return;
    }

    var startPoint, endPoint;
    var routingNodes = new Array();
    for (var i = 0; i < connector.childNodes.length; i++) {
        if (connector.childNodes[i].nodeType == 1) {
            routingNodes.push(connector.childNodes[i]);
        }
    }

    if (routingNodes.length == 0) {
        var offset = getDirect(getCellNode(this.fromId), getCellNode(this.toId));
        startPoint = offset[0];
        endPoint = offset[1];

    } else {
        startPoint = getOffsetPoint(getCellNode(this.fromId), routingNodes[0]);
        endPoint = getOffsetPoint(getCellNode(this.toId),
            routingNodes[routingNodes.length - 1]);
    }
    //在canvas面板中画线
    ctx.strokeStyle = "rgb(127, 127, 127)";
    ctx.beginPath();
    ctx.moveTo(startPoint.getX(), startPoint.getY());
    var lastRoutPx = startPoint.getX();
    var lastRoutPy = startPoint.getY();
    for (var i = 0; i < routingNodes.length; i++) {
        if (routingNodes[i].attributes) {
            ctx.lineTo(routingNodes[i].getAttribute("x"), routingNodes[i].getAttribute("y"));
            lastRoutPx = routingNodes[i].getAttribute("x");
            lastRoutPy = routingNodes[i].getAttribute("y");
        }
    }
    ctx.lineTo(endPoint.getX(), endPoint.getY());
    //画线的箭头
    //纵向的时候画箭头
    if (lastRoutPx == endPoint.getX()) {
        if (lastRoutPy < endPoint.getY()) {
            ctx.moveTo(endPoint.getX(), endPoint.getY());
            ctx.lineTo(endPoint.getX() + 5, endPoint.getY() - 5);
            ctx.moveTo(endPoint.getX(), endPoint.getY());
            ctx.lineTo(endPoint.getX() - 5, endPoint.getY() - 5);
        } else {
            ctx.moveTo(endPoint.getX(), endPoint.getY());
            ctx.lineTo(endPoint.getX() + 5, endPoint.getY() + 5);
            ctx.moveTo(endPoint.getX(), endPoint.getY());
            ctx.lineTo(endPoint.getX() - 5, endPoint.getY() + 5);
        }
    }
    //横向的时候画箭头
    else if (lastRoutPy == endPoint.getY()) {
        if (lastRoutPx < endPoint.getX()) {
            ctx.moveTo(endPoint.getX(), endPoint.getY());
            ctx.lineTo(endPoint.getX() - 5, endPoint.getY() + 5);
            ctx.moveTo(endPoint.getX(), endPoint.getY());
            ctx.lineTo(endPoint.getX() - 5, endPoint.getY() - 5);
        } else {
            ctx.moveTo(endPoint.getX(), endPoint.getY());
            ctx.lineTo(endPoint.getX() + 5, endPoint.getY() + 5);
            ctx.moveTo(endPoint.getX(), endPoint.getY());
            ctx.lineTo(endPoint.getX() + 5, endPoint.getY() - 5);
        }
    }
    //斜向的时候画箭头
    else if ((lastRoutPx != endPoint.getX()) && (lastRoutPy != endPoint.getY())) {
        var aLen = 8;
        if (lastRoutPx < endPoint.getX() && lastRoutPy < endPoint.getY()) {
            ctx.moveTo(endPoint.getX(), endPoint.getY());
            ctx.lineTo(endPoint.getX() + aLen * Math.cos(Math.PI - Math.atan((endPoint.getY() - lastRoutPy) / (endPoint.getX() - lastRoutPx)) - 1 / 6 * Math.PI), endPoint.getY() - aLen * Math.sin(Math.PI - Math.atan((endPoint.getY() - lastRoutPy) / (endPoint.getX() - lastRoutPx)) - 1 / 6 * Math.PI));
            ctx.moveTo(endPoint.getX(), endPoint.getY());
            ctx.lineTo(endPoint.getX() + aLen * Math.cos(Math.PI - Math.atan((endPoint.getY() - lastRoutPy) / (endPoint.getX() - lastRoutPx)) + 1 / 6 * Math.PI), endPoint.getY() - aLen * Math.sin(Math.PI - Math.atan((endPoint.getY() - lastRoutPy) / (endPoint.getX() - lastRoutPx)) + 1 / 6 * Math.PI));
        } else if (lastRoutPx < endPoint.getX() && lastRoutPy > endPoint.getY()) {
            ctx.moveTo(endPoint.getX(), endPoint.getY());
            ctx.lineTo(endPoint.getX() + aLen * Math.cos(Math.atan((lastRoutPy - endPoint.getY()) / (endPoint.getX() - lastRoutPx)) - Math.PI - 1 / 6 * Math.PI), endPoint.getY() - aLen * Math.sin(Math.atan((lastRoutPy - endPoint.getY()) / (endPoint.getX() - lastRoutPx)) - Math.PI - 1 / 6 * Math.PI));
            ctx.moveTo(endPoint.getX(), endPoint.getY());
            ctx.lineTo(endPoint.getX() + aLen * Math.cos(Math.atan((lastRoutPy - endPoint.getY()) / (endPoint.getX() - lastRoutPx)) + Math.PI - 1 / 6 * Math.PI), endPoint.getY() - aLen * Math.sin(Math.atan((lastRoutPy - endPoint.getY()) / (endPoint.getX() - lastRoutPx)) - Math.PI + 1 / 6 * Math.PI));
        } else if (lastRoutPx > endPoint.getX() && lastRoutPy < endPoint.getY()) {
            ctx.moveTo(endPoint.getX(), endPoint.getY());
            ctx.lineTo(endPoint.getX() + aLen * Math.cos(Math.atan((lastRoutPy - endPoint.getY()) / (endPoint.getX() - lastRoutPx)) - 1 / 6 * Math.PI), endPoint.getY() - aLen * Math.sin(Math.atan((lastRoutPy - endPoint.getY()) / (endPoint.getX() - lastRoutPx)) - 1 / 6 * Math.PI));
            ctx.moveTo(endPoint.getX(), endPoint.getY());
            ctx.lineTo(endPoint.getX() + aLen * Math.cos(Math.atan((lastRoutPy - endPoint.getY()) / (endPoint.getX() - lastRoutPx)) + 1 / 6 * Math.PI), endPoint.getY() - aLen * Math.sin(Math.atan((lastRoutPy - endPoint.getY()) / (endPoint.getX() - lastRoutPx)) + 1 / 6 * Math.PI));
        } else if (lastRoutPx > endPoint.getX() && lastRoutPy > endPoint.getY()) {
            ctx.moveTo(endPoint.getX(), endPoint.getY());
            ctx.lineTo(endPoint.getX() + aLen * Math.cos(0 - Math.atan((endPoint.getY() - lastRoutPy) / (endPoint.getX() - lastRoutPx)) - 1 / 6 * Math.PI), endPoint.getY() - aLen * Math.sin(0 - Math.atan((endPoint.getY() - lastRoutPy) / (endPoint.getX() - lastRoutPx)) - 1 / 6 * Math.PI));
            ctx.moveTo(endPoint.getX(), endPoint.getY());
            ctx.lineTo(endPoint.getX() + aLen * Math.cos(0 - Math.atan((endPoint.getY() - lastRoutPy) / (endPoint.getX() - lastRoutPx)) + 1 / 6 * Math.PI), endPoint.getY() - aLen * Math.sin(0 - Math.atan((endPoint.getX() - lastRoutPx) / (endPoint.getY() - lastRoutPy)) - 1 / 6 * Math.PI));
        }
    }
    ctx.stroke();
    //画线上的label
    this.label = new Label(connector, startPoint, endPoint, this.view);
    Page.appendChild(this.label.el);
}

/**
 * <p>流程上的文字</p>
 * @param {DOM node}connector
 * @param {Point}startPt
 * @param {Point}endPt
 * @param {String}text
 */
function Label(connector, startPt, endPt, text) {
    var labelPt = getLabelPosition(startPt, endPt, connector);
    //alert(labelPt.getX()+":"+labelPt.getY());
    this.el = Toolkit.newElement("div");
    this.el.className = "fv-lineLabel";
    this.el.style.left = labelPt.getX() + "px";
    this.el.style.top = labelPt.getY() + "px";
    var labelText = Toolkit.newElement("span");
    if (eoms.isIE) {
        labelText.innerText = text;
    } else {
        labelText.innerHTML = text + labelPt.getX() + ":" + labelPt.getY();
    }
    this.el.appendChild(labelText);
}

/**
 * 取得流程线上文字的位置坐标
 * @return {Point}
 */
function getLabelPosition(startP, endP, connector) {
    var offsetX = cellGet(connector, "labelx");
    var offsetY = cellGet(connector, "labely");

    if (version == "3.5") {
        return new Point(offsetX, offsetY);
    }
    var routings = new Array();
    for (var i = 0; i < connector.childNodes.length; i++) {
        if (connector.childNodes[i].nodeType == 1) {
            routings.push(connector.childNodes[i]);
        }
    }
    var xMax = Math.max(startP.getX(), endP.getX());
    var yMax = Math.max(startP.getY(), endP.getY());
    var xMin = Math.min(startP.getX(), endP.getX());
    var yMin = Math.min(startP.getY(), endP.getY());
    for (var i = 0; i < routings.length; i++) {
        xMax = Math.max(xMax, cellGet(routings[i], "x"));
        yMax = Math.max(yMax, cellGet(routings[i], "y"));
        xMin = Math.min(xMin, cellGet(routings[i], "x"));
        yMin = Math.min(yMin, cellGet(routings[i], "y"));
    }

    var px = (xMax + xMin) / 2;
    var py = (yMax + yMin) / 2;

    var lineWidth = xMax - xMin;
    //当为直线时，给一个基本的调整量
    if (lineWidth == 0)
        lineWidth = 12;
    var lineHeight = yMax - yMin;
    if (lineHeight == 0)
        lineHeight = 8;

    //正负调整值
    var offX = 1;
    var offY = 1;

    var initX, initY;//找出原点
    if (endP.getX() >= startP.getX()) {
        initX = xMin;
    } else {
        initX = xMax;
        offX = -1;
    }
    if (endP.getY() >= startP.getY()) {
        initY = yMin;
    } else {
        initY = yMax;
        offY = -1;
    }

    if (offsetX != 500.0 && offsetY != 500.0) {
        px = initX + (lineWidth * (offsetX / 1000) * offX);
        py = initY + (lineHeight * (offsetY / 1000) * offY);
    }
    return new Point(px, py);
}

/**
 * 线上无折点的时候，直接由开始节点和结束节点画出流程线
 * @return {Array} [startPoint,endPoint]
 */
function getDirect(fromNode, toNode) {
    if ((!fromNode) || (!toNode)) {
        return null;
    }
    //
    var fromX = cellGet(fromNode, "x");
    var fromY = cellGet(fromNode, "y");
    var fromWidth = cellGet(fromNode, "width");
    var fromHeight = cellGet(fromNode, "height");
    var fromMinX = fromX;
    var fromMinY = fromY;
    var fromMaxX = fromX + fromWidth;
    var fromMaxY = fromY + fromHeight;
    var fromCenterX = fromMinX + fromWidth / 2;
    var fromCenterY = fromMinY + fromHeight / 2;

    //
    var toX = cellGet(toNode, "x");
    var toY = cellGet(toNode, "y");
    var toWidth = cellGet(toNode, "width");
    var toHeight = cellGet(toNode, "height");
    var toMinX = toX;
    var toMinY = toY;
    var toMaxX = toX + toWidth;
    var toMaxY = toY + toHeight;
    var toCenterX = toMinX + toWidth / 2;
    var toCenterY = toMinY + toHeight / 2;

    //
    if (fromMaxY <= toMinY) {
        if ((fromMaxX >= toMinX) && (fromMinX <= toMaxX)) {
            return [new Point(fromCenterX, fromMaxY),
                new Point(toCenterX, toMinY)];
        } else {
            if (fromMinX > toMaxX) {
                return [new Point(fromMinX, fromMaxY),
                    new Point(toCenterX, toMinY)];
            } else {
                if (fromMaxX < toMinX) {
                    return [new Point(fromMaxX, fromMaxY),
                        new Point(toCenterX, toMinY)];
                }
            }
        }
    }

    //
    if (fromMinY >= toMaxY) {
        if ((fromMaxX >= toMinX) && (fromMinX <= toMaxX)) {
            var min = Math.max(fromMinX, toMinX);
            var max = Math.min(fromMaxX, toMaxX);
            var x = Math.round((min + max) / 2);
            return [new Point(fromCenterX, fromMinY),
                new Point(toCenterX, toMaxY)];
        } else {
            if (fromMinX > toMaxX) {
                return [new Point(fromMinX, fromMinY),
                    new Point(toCenterX, toMaxY)];
            } else {
                if (fromMaxX < toMinX) {
                    return [new Point(fromMaxX, fromMinY),
                        new Point(toCenterX, toMaxY)];
                }
            }
        }
    }

    //
    if ((fromMaxY > toMinY) && (fromMinY < toMaxY)) {
        if ((fromMaxX >= toMinX) && (fromMinX <= toMaxX)) {
            return [new Point(fromCenterX, fromMinY),
                new Point(toCenterX, toMaxY)];
        } else {
            if (fromMinX > toMaxX) {
                return [new Point(fromMinX, fromCenterY),
                    new Point(toMaxX, toCenterY)];
            } else {
                if (fromMaxX < toMinX) {
                    return [new Point(fromMaxX, fromCenterY),
                        new Point(toMinX, toCenterY)];
                }
            }
        }
    }

    //
    return [new Point(fromX, fromY), new Point(toX, toY)];
}

/**
 * 从一个cellNode和一个routingNode获得修正后的点坐标
 * @return {Point}
 */
function getOffsetPoint(node, routingNode) {
    var x, y;
    var width = cellGet(node, "width");
    var height = cellGet(node, "height");
    //取得from节点坐标
    var startx = cellGet(node, "x");
    var starty = cellGet(node, "y");

    if (routingNode == null) {
        return new Point(startx, starty);
    }
    //取得第一个折点的坐标
    var offsetx = cellGet(routingNode, "x");
    var offsety = cellGet(routingNode, "y");
    //比较坐标，确定调整坐标

    if (offsetx > startx + width) {
        x = startx + width;
    } else if (offsetx < startx) {
        x = startx;
    } else {
        x = offsetx;
    }

    if (offsety > starty + height) {
        y = starty + height;
    } else if (offsety < starty) {
        y = starty;
    } else {
        y = offsety;
    }
    return new Point(x, y);
}

function getCellNode(id) {
    if (eoms.isIE) {
        return xmlDoc.selectSingleNode("//cell[@id='" + id + "']");
    } else {
        var tmp = xmlDoc.getElementsByTagName("cell");
        for (var i = 0; i < tmp.length; i++) {
            if (tmp[i].getAttribute("id") == id) {
                return tmp[i];
            }
        }
    }
}

function getLineNode(id) {
    if (eoms.isIE) {
        return xmlDoc.selectSingleNode("//connector[@id='" + id + "']");
    } else {
        var tmp = xmlDoc.getElementsByTagName("connector");
        for (var i = 0; i < tmp.length; i++) {
            if (tmp[i].getAttribute("id") == id) {
                return tmp[i];
            }
        }
    }
}

/**
 * 获得节点上某个浮点型属性
 * @return {Float}
 */
function cellGet(node, att) {
    if (node.attributes)
        return parseFloat(node.getAttribute(att));
}

/**
 * 是否是历史节点
 * @return {Boolean}
 */
function isHistoryStep(id) {
    if (historyStepIds == null || historyStepIds == "undefined")
        return false;
    for (var i = 0; i < historyStepIds.length; i++) {
        if (id == historyStepIds[i])
            return true;
    }
    return false;
}

/**
 * 是否为当前步骤节点
 * @return {Boolean}
 */
function isCurStep(id) {
    if (currentStepIds == null || currentStepIds == "undefined")
        return false;
    for (var i = 0; i < currentStepIds.length; i++) {
        if (id == currentStepIds[i])
            return true;
    }
    return false;
}

/**
 * 显示tips
 * @param {Object} step
 * @param {Point} point
 */
function showTips(step, point) {
    if (!step.tips)
        return;
    eoms.$("tip").update(step.tips).setStyle("left:" + point[0] + ";top:"
        + point[1]).show();
}

//隐藏tips
function hideTips(step) {
    eoms.$("tip").update("").hide();
}


