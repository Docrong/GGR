/**
 * html元素工具箱
 * <p>Description: </p>
 */
function Toolkit() {
}

//static function
Toolkit.newLayer = function () {
	return document.createElement("div");
};
Toolkit.newImage = function () {
	return document.createElement("img");
};
Toolkit.newTable = function () {
	return document.createElement("table");
};
Toolkit.newElement = function (tagName) {
	return document.createElement(tagName);
};

Toolkit.safeParseInt = function (val) {
	return Math.round(parseFloat(val));
};

//获得元素坐标
Toolkit.getXY = function(el,container){
	var p, pe, b, scroll, bd = (document.body || document.documentElement);
	if(el == bd){
		return [0, 0];
	}

	var x = 0, y = 0;

	p = el;

    while (p) {
        x += p.offsetLeft;
        y += p.offsetTop;
        p = p.offsetParent;
    }

    p = el.parentNode;
    while (p && p != bd) {
        x -= p.scrollLeft;
        y -= p.scrollTop;
        p = p.parentNode;
    }

	return [x, y];
};

//获得鼠标坐标
Toolkit.getContainerCoord = function (e, container) {
	container = container.getUI ? container.getUI() : container;
	if (typeof e.offsetX != "undefined") {
		var srcObj = e.target || e.srcElement;
		var x = 0, y = 0;
		while (srcObj && srcObj != container) {
			x += srcObj.offsetLeft;
			y += srcObj.offsetTop;
			srcObj = srcObj.offsetParent;
		}
		return new Point(e.offsetX + x, e.offsetY + y);
	} else {
		if (typeof e.pageX != "undefined") {
			var x = 0, y = 0;
			while (container) {
				x += container.offsetLeft;
				y += container.offsetTop;
				container = container.offsetParent;
			}
			return new Point(e.pageX - x, e.pageY - y);
		} else {
			return null;
		}
	}
};
Toolkit.clearElement = function (element) {
	if (!element) {
		return;
	}
	var node = element.getUI ? element.getUI() : element;
	if (!node) {
		return;
	}
	while (node.firstChild) {
		node.removeChild(node.firstChild);
	}
};

