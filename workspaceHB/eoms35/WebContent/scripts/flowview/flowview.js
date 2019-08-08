/**
 * <p>Description: ��ʾ����ͼ</p>
 * @author mios
 * @version 1.2.1 ��ʽ����������ȵ������������ʾ
 * @include "/flowview/FlowViewWorkSpace.js"
 */

var xmlDoc, version, cells, lines, Page;
var missCells = new Array(), missLines = new Array();

var FlowView = function (el) {
    Page = el;
}

FlowView.NODESTATE_NORMAL = 0;
FlowView.NODESTATE_HISTORY = 1;
FlowView.NODESTATE_CURSTEP = 2;

FlowView.prototype.getFlow = function (url) {
    if (eoms.isIE) {
        xmlDoc = new ActiveXObject("Msxml2.DOMDocument.3.0");
    } else {
        Page.update("����ͼ������ʹ��IE������鿴��лл��");
        return;
    }
    if (steps.length <= 0 || actions.length <= 0) {
        return;
    }
    xmlDoc.async = false;
    xmlDoc.load(url);
    if (xmlDoc.parseError.errorCode != 0) {
        var error = xmlDoc.parseError;
        alert(error.reason)
        return;
    }

    var versionNode = xmlDoc.selectSingleNode("//eomsversion");
    if (versionNode) {
        version = versionNode.getAttribute("value");
    }

    //���̲���
    steps.each(function (step) {
        var sNode = getCellNode(step.id);
        if (sNode == null) {
            missCells.push("Step:" + step.name + " ID:" + step.id);
        } else {
            var nodeType = sNode.getAttribute("type");
            var node;
            switch (nodeType) {
                case "InitialActionCell" :
                    //�����ڵ�
                    node = new startNode(sNode, step);
                    break;
                default :
                    //��ͨ�ڵ�
                    node = new Node(sNode, step);

            }
            node.draw();
        }
    });

    //���̶���
    actions.each(function (action) {
        var sNode = getLineNode(action.id);
        if (sNode != null && getCellNode(action.from) != null
            && getCellNode(action.to) != null) {
            var curLine = new stepLine(sNode, action);
            curLine.draw();
        } else {
            if (sNode == null)
                missLines.push("Action:" + action.view + " ID:" + action.id);
            if (getCellNode(action.from) == null)
                missCells.push("Step:<action id=" + action.id + ">�Ŀ�ʼ�ڵ� ID:"
                    + action.from);
            if (getCellNode(action.to) == null)
                missCells.push("Step:<action id=" + action.id + ">�Ľ����ڵ� ID:"
                    + action.to);
        }
    });

    if (missCells.length > 0) {
        alert("�������̵�stepû�ж�����*.lyt.xml�ļ���:\n" + missCells.join("\n"))
    }
    if (missLines.length > 0) {
        alert("�������̵�actionû�ж�����*.lyt.xml�ļ���:\n" + missLines.join("\n"))
    }

    //������
    drawChannels();

    //����iframe�ĸ߶�
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
 * <p>��</p>
 * y1,y2�������±߽��Y����
 */
function drawChannels() {
    var channels = xmlDoc.selectNodes("//channel");
    $A(channels).each(function (c, index) {
        //alert(c.xml);
        //������������div
        var chl = Toolkit.newLayer();
        $(chl).addClass("fv-chl");
        chl.style.height = c.getAttribute("y2") - c.getAttribute("y1");
        chl.style.top = parseInt(c.getAttribute("y1")) + "px";
        //������ȣ���Ϊ�й�����ʱ��Ȼ��쳣
        $(chl).setStyle("width:"
            + (parent == window ? "100%" : Page.scrollWidth));

        //���������ֺ�ͼ��
        var chlText = Toolkit.newLayer();
        $(chlText).addClass("fv-chl-txt");

        var cIcon = Toolkit.newImage();
        cIcon.src = FlowViewWorkSpace.FLOW_PATH + "/img/role.gif";
        $(cIcon).setStyle("align:absBottom;margin-right:5px");
        chlText.appendChild(cIcon);
        chlText.innerHTML += c.getAttribute("text");

        //���л�ɫ
        if (index % 2 == 0) {
            $(chl).addClass("fv-chl-even");
            $(chlText).addClass("fv-chl-even");
        }

        chl.appendChild(chlText);
        Page.appendChild(chl);

    });
}

/**
 * <p>��ͨ�ڵ�</p>
 * @param {DOM node} cell
 * @param {String} step
 */
function Node(cell, config) {
    this.base = stepNode;
    this.id = cell.getAttribute("id");
    this.name = config.name;
    this.imageUrl = FlowViewWorkSpace.FLOW_PATH + "/img/node.gif";
    //�ڵ�״̬
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
 * <p>�����ڵ�</p>
 * @param {DOM node} cell
 * @param {object} step
 */
function startNode(cell, config) {
    this.base = stepNode;
    this.id = cell.getAttribute("id");
    this.name = config.name || "����";
    this.imageUrl = FlowViewWorkSpace.FLOW_PATH + "/img/node.gif";
    this.state = FlowView.NODESTATE_HISTORY;
    this.base(cell, config);
}

/**
 * <p>�ڵ㸸��</p>
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
    this.titleTxtCell.innerText = this.name;

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
 * <p>������</p>
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

    this.el = Toolkit.newElement("v:PolyLine");
    this.el.className = "fv-line";

    if (getCellNode(this.fromId) == null || getCellNode(this.toId) == null) {
        return;
    }

    var startPoint, endPoint;
    var routingNodes = connector.childNodes;
    if (routingNodes.length == 0) {
        var offset = getDirect(getCellNode(this.fromId), getCellNode(this.toId));
        startPoint = offset[0];
        endPoint = offset[1];
    } else {
        startPoint = getOffsetPoint(getCellNode(this.fromId), routingNodes[0]);
        endPoint = getOffsetPoint(getCellNode(this.toId),
            routingNodes[routingNodes.length - 1]);

    }

    //�����ʼ����
    var pointStr = startPoint.getX() + "," + startPoint.getY() + " ";
    //����ڵ�����
    for (var i = 0; i < routingNodes.length; i++) {
        pointStr += routingNodes[i].getAttribute("x") + ","
            + routingNodes[i].getAttribute("y") + " ";
    }
    //��ӽ�������
    pointStr += endPoint.getX() + "," + endPoint.getY() + " ";
    //alert(pointStr);

    this.el.setAttribute("Points", pointStr);
    this.el.setAttribute("filled", "false");

    var done = action.complete || false;
    if (done) {
        this.el.setAttribute("strokeColor", "#77BF90");
        this.el.setAttribute("StrokeWeight", "3");
    } else {
        this.el.setAttribute("strokeColor", "#53B1DF");
        this.el.setAttribute("StrokeWeight", "1");
    }

    //var inner = "<v:stroke EndArrow=\"Classic\" dashstyle=\""+(done ? "Solid" : "LongDash")+"\"/>\n";
    var inner = "<v:stroke EndArrow=\"Classic\" />\n";
    this.el.innerHTML = inner;

    this.label = new Label(connector, startPoint, endPoint, this.view);
    this.draw = function () {
        Page.appendChild(this.el);
        Page.appendChild(this.label.el);
    }
}

/**
 * <p>������������</p>
 * @param {DOM node}connector
 * @param {Point}startPt
 * @param {Point}endPt
 * @param {String}text
 */
function Label(connector, startPt, endPt, text) {
    var labelPt = getLabelPosition(startPt, endPt, connector);
    this.el = Toolkit.newElement("<div class=\"fv-lineLabel\"></div>");
    this.el.style.left = labelPt.getX() + "px";
    this.el.style.top = labelPt.getY() + "px";
    var labelText = Toolkit.newElement("span");
    labelText.innerText = text;
    this.el.appendChild(labelText);
}

/**
 * ȡ�������������ֵ�λ��
 * @return {Point}
 */
function getLabelPosition(startP, endP, connector) {
    //labelx��labely��ֵ�����Ϊ����label���ƶ�����ƫ����������line�ĳ���İٷֱ�
    var offsetX = cellGet(connector, "labelx");
    var offsetY = cellGet(connector, "labely");

    //����汾����Ϊ3.5����ֱ�ӷ�������ֵ
    if (version == "3.5") {
        return new Point(offsetX, offsetY);
    }
    var routings = connector.childNodes;
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
    //��Ϊֱ��ʱ����һ�������ĵ�����
    if (lineWidth == 0)
        lineWidth = 12;
    var lineHeight = yMax - yMin;
    if (lineHeight == 0)
        lineHeight = 8;

    //��������ֵ
    var offX = 1;
    var offY = 1;

    var initX, initY;//�ҳ�ԭ��
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
 * �������۵�ʱ,ֱ�Ӵӿ�ʼ�ͽ����ڵ�ȷ��������
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
 * ��һ��cellNode��һ��routingNode��ȡ������ĵ�����
 * @return {Point}
 */
function getOffsetPoint(node, routingNode) {
    //alert(routingNode)
    var x, y;
    var width = cellGet(node, "width");
    var height = cellGet(node, "height");
    //ȡ��from�ڵ�����
    var startx = cellGet(node, "x");
    var starty = cellGet(node, "y");

    if (routingNode == null) {
        return new Point(startx, starty);
    }
    //ȡ�õ�һ���۵�����
    var offsetx = cellGet(routingNode, "x");
    var offsety = cellGet(routingNode, "y");

    //�Ƚ����꣬ȷ����������

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
    return xmlDoc.selectSingleNode("//cell[@id='" + id + "']");
}

function getLineNode(id) {
    return xmlDoc.selectSingleNode("//connector[@id='" + id + "']");
}

/**
 * ��ȡ�ڵ���ĳ������ֵ����
 * @return {Float}
 */
function cellGet(node, att) {
    return parseFloat(node.getAttribute(att));
}

/**
 * �Ƿ�����ʷ�ڵ�
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
 * �Ƿ��ǵ�ǰ����ڵ�
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
 * ��ʾtips
 * @param {Object} step
 * @param {Point} point
 */
function showTips(step, point) {
    if (!step.tips)
        return;
    eoms.$("tip").update(step.tips).setStyle("left:" + point[0] + ";top:"
        + point[1]).show();
}

//����tips
function hideTips(step) {
    eoms.$("tip").update("").hide();
}


