/**
 * desc: 日历控件
 * author by mios
 * version 2.1.090505
 */

Date.daysInMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
Date.prototype.isLeapYear = function () {
    var year = this.getFullYear();
    return ((year & 3) == 0 && (year % 100 || (year % 400 == 0 && year)));
};
Date.prototype.getDaysInMonth = function () {
    Date.daysInMonth[1] = this.isLeapYear() ? 29 : 28;
    return Date.daysInMonth[this.getMonth()];
};

var intervalID1, intervalID2, timeoutID1, timeoutID2;

eoms.Calendar = function () {
    var curMonth, curYear, curDate, monthConstructed = false, yearConstructed = false;
    var isShowTime = false;
    var monthName = ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"];
    var sty, sty_m, sty_y;
    var todayText = "今天", preText = "上个月", nxtText = "下个月", selMonText = "选择月份";
    var selYerText = "选择年份";
    var closeText = "关闭日历";
    var startAt = 0; // 0 - sunday ; 1 - monday
    var dayName = ["日", "一", "二", "三", "四", "五", "六"];
    var arrTemp = dayName.slice(startAt, 7);
    dayName = arrTemp.concat(dayName.slice(0, startAt));
    var bPageLoaded = false;
    var ie = /*@cc_on!@*/false;
    var today = new Date();
    var dateNow = today.getDate(), yearNow = today.getYear(), monthNow = today.getMonth();
    var hourNow = today.getHours(), minNow = today.getMinutes(), secNow = today.getSeconds();
    if (!ie) {
        yearNow += 1900;
    }
    var min = new Date(yearNow, monthNow, dateNow);
    var max = new Date(3000, 0, 1);
    var bShow = false;
    var ctlToPlaceValue, dateFormat, timeFormat, nStartingYear;
    var padZero = function (num) {
        return (num < 10) ? '0' + num : num;
    }
    var selected = [];

    eoms.loadCSS(eoms.appPath + "/scripts/widgets/calendar/calendar.css");

    /**
     * 创建选择时、分、秒的三个下拉框
     */
    var createSelect = function (id, c, text) {
        var s = ['<select id="' + id + '">'];
        for (var j = 0; j < c; j++) {
            s[s.length] = '<option ';
            s[s.length] = (id == "selecthour" && hourNow == j) || (id == "selectminute" && minNow == j) || (id == "selectsecond" && secNow == j) ? "selected" : "";
            s[s.length] = ' value="' + padZero(j) + '">' + j + text + '</option>';
        }
        s[s.length] = "</select>";
        return s.join("");
    }

    var m = ["<div id='calendar' class='hide'>"];

    if (ie) m[m.length] = '<iframe id="cal_shim" src="javascript:false" style="border:0;position:absolute;visibility:inherit;'
        + 'top:0px;left:0px;width:185px; height:240px;z-index:-1;filter:alpha(opacity=0);"></iframe>';

    m = m.concat(['<table class="cal_table" cellspacing=0>',
        '<tr class="cal_row_top">' +
        '<td id="caption-left">' +
        '<a hidefocus="true" onfocus="this.blur();" unselectable="on" href="javascript:;" onclick="javascript:cal.decMonth()" >&nbsp;</a></td>',
        '<td id="captioncell">',
        '<a href="javascript:;" id="spanYear" hidefocus="true" onfocus="this.blur();" unselectable="on" onclick="cal.popUpYear()"></a>',
        '&nbsp;<a id="spanMonth" href="javascript:;" hidefocus="true" onfocus="this.blur();" unselectable="on" onclick="cal.popUpMonth()"></a>',
        '</td>',
        '<td id="caption-right">' +
        '<a hidefocus="true" onfocus="this.blur();" unselectable="on" href="javascript:;" onclick="javascript:cal.incMonth()" >&nbsp;</a></td></tr>',
        '<tr><td id="cal_content" colspan=3></td></tr>', '<tr><td align="center" colspan=3>',
        '<span id="cal_time">', createSelect("selecthour", 24, "时"), createSelect("selectminute", 60, "分"), createSelect("selectsecond", 60, "秒"),
        '</span>', '</td></tr>',
        '<tr><td class="cal_row_bottom" colspan=3>',
        '<div><input type="button" style="float:left;" value="', todayText, '" onclick="cal.now();" class="button">',
        '<input type="button" style="float:right;" value="确定" onclick="cal.closeCalendar();" class="button"></div></td></tr>',
        '</table></div><div id="selectYear"></div><div id="selectMonth"></div>']);
    document.write(m.join(""));

    eoms.Element.on.call(window, 'load', function () {
        cal.initCalendar();
    });
    eoms.Element.on.call(document, 'click', function () {
        cal.hide();
    });

    return {
        el: eoms.$('calendar'),
        timePicker: eoms.$('cal_time'),
        hide: function () {
            if (!bShow && !this.el.hasClass("hide")) {
                try {
                    this.el.addClass("hide");
                    this.popDownYear();
                    this.popDownMonth();
                } catch (e) {
                }
                ;
                min = new Date(yearNow, monthNow, dateNow);
                max = new Date(3000, 0, 1);
            }
            bShow = false;
        },
        setDate: function (d, curEl) {
            curDate = d;
            eoms.select("table.cal_days .cal_selected").each(function (el) {
                $(el).removeClass("cal_selected");
            });
            $(curEl).addClass("cal_selected");
            selected[0] = curYear;
            selected[1] = curMonth;
            selected[2] = curDate;
        },
        /**
         * 点击今天时，设置今日日期为选中日期
         */
        now: function () {
            curYear = yearNow;
            curMonth = monthNow;
            curDate = dateNow;
            selected[0] = curYear;
            selected[1] = curMonth;
            selected[2] = curDate;
            cal.update();
        },
        /**
         * 更新日历显示
         */
        update: function () {
            var d = new Date(curYear, curMonth, 1);
            var days = d.getDaysInMonth();

            var _min = min.getTime();
            var _max = max.getTime();

            var datePointer = 0;
            var dayPointer = this.firstdayofweek(d.getDay());

            var sHTML = "<table border=0 cellspacing='0' cellpadding='0' class='cal_days'><thead><tr>"

            for (i = 0; i < 7; i++) {
                sHTML += "<th>" + dayName[i] + "</th>"
            }
            sHTML += "</tr></thead><tbody><tr>"

            for (var i = 1; i <= dayPointer; i++) {
                sHTML += "<td>&nbsp;</td>"
            }
            for (datePointer = 1; datePointer <= days; datePointer++) {
                dayPointer++;
                sHTML += "<td>";
                var t = d.getTime();
                var cls = "cal_date";

                //是否被屏蔽日期
                if (t > _max || t < _min) {
                    cls = " cal_disabled";
                } else {
                    //选中的日期
                    if (datePointer == curDate && curYear == selected[0] && curMonth == selected[1]) {
                        cls = " cal_selected";
                    }
                    //周末
                    if (d.getDay() == 0 || d.getDay() == 6) {
                        cls += " cal_weekend"
                    }
                }

                sHTML += '<a href="javascript:;" hidefocus="true" onfocus="this.blur();" unselectable="on" class="' + cls + '"';
                if (!cls.hasSubString('cal_disabled')) {
                    sHTML += ' onclick="cal.setDate(' + datePointer + ',this);event.cancelBubble=true;return false;"';
                }
                sHTML += '>' + datePointer + '</a>';

                if ((dayPointer + startAt) % 7 == startAt) {
                    sHTML += "</tr><tr>";
                }
                d.setDate(d.getDate() + 1);
            }

            $("cal_content").innerHTML = sHTML;
            $("spanMonth").innerHTML = monthName[curMonth];
            $("spanYear").innerHTML = curYear + "年";
            this.setShim();
        },
        firstdayofweek: function (day) {
            day -= startAt;
            if (day < 0) {
                day = 7 + day;
            }
            return day;
        },
        show: function (handler, field, format, top, left, showTime, minDate, maxDate) {
            var leftpos = left || -1;
            var toppos = top || -1;
            var format = format || 'yyyy-mm-dd';
            var DateRe = /\d{4}\D\d{2}\D\d{2}/;

            if (minDate == -1) {
                min = new Date(1900, 0, 1);
            } else if (DateRe.test(minDate)) {
                var yMin = minDate.substring(0, 4);
                var mMin = minDate.substring(5, 7);
                var dMin = minDate.substring(8, 10);
                min = new Date(yMin, mMin - 1, dMin);
            }
            if (maxDate == -1) {
                max = new Date();
                max.setDate(today.getDate() - 1);
            } else if (DateRe.test(maxDate)) {
                var yMax = maxDate.substring(0, 4);
                var mMax = maxDate.substring(5, 7);
                var dMax = maxDate.substring(8, 10);
                max = new Date(yMax, mMax - 1, dMax - 1);
            }

            if (showTime == false) {
                isShowTime = false;
                this.timePicker.hide();
            } else {
                isShowTime = true;
                this.timePicker.show();
                format += " hh:ii:ss";
            }

            if (!bPageLoaded) return;
            if (!this.el.hasClass('hide')) {
                cal.hide();
                return;
            }

            ctlToPlaceValue = field;

            var splitFormat = format.split(" ");
            dateFormat = splitFormat[0];
            timeFormat = splitFormat[1];

            if (isShowTime && field.value != "") {
                var splitValue = field.value.split(" ");
                var tValue = splitValue[1];
                tValue = tValue.split(":");

                $("selecthour").value = tValue[0];
                $("selectminute").value = tValue[1];
                $("selectsecond").value = tValue[2];
            }

            formatChar = "-";

            if (formatChar != "" && field.value != "") {
                curYear = parseInt(field.value.split(formatChar)[0], 10);
                curMonth = parseInt(field.value.split(formatChar)[1], 10) - 1;
                curDate = parseInt(field.value.split(formatChar)[2], 10);
            }

            if (isNaN(curDate) || isNaN(curMonth) || isNaN(curYear)) {
                curDate = dateNow;
                curMonth = monthNow;
                curYear = yearNow;
            }

            selected[0] = curYear;
            selected[1] = curMonth;
            selected[2] = curDate;

            var aTag = handler;
            do {
                aTag = aTag.offsetParent;
                //TODO
                if ($(aTag).hasClass("x-tabs-item-body")) continue;
                leftpos += aTag.offsetLeft;
                toppos += aTag.offsetTop;
            } while (aTag.tagName != "BODY");

            sty.left = handler.offsetLeft + leftpos + "px";
            var top = handler.offsetTop + toppos + handler.offsetHeight + 2;
            sty.top = top + "px";

            cal.update(1, curMonth, curYear);
            this.el.removeClass("hide");
            bShow = true;
            var h = this.el.offsetHeight;
            if (top + h >= document.body.clientHeight && top - h > 22) {
                sty.top = top - h - 22 + "px";
            }
            this.setShim();
        },
        initCalendar: function () {
            sty = this.el.style;
            sty_m = $("selectMonth").style;
            sty_y = $("selectYear").style;
            cal.hide();

            this.el.on('click', function () {
                bShow = true;
            });

            bPageLoaded = true;
        },
        /**
         * 点确定时方法
         */
        closeCalendar: function () {
            var tempDate = new Date(curYear, curMonth, 1);
            if (curDate > tempDate.getDaysInMonth()) {
                curDate = tempDate.getDaysInMonth();
            }

            var d = new Date(curYear, curMonth, curDate);
            var t = d.getTime();
            var _min = min.getTime();
            var _max = max.getTime();

            var constructDate = function (d, m, y) {
                var s = dateFormat;
                s = s.replace("dd", "<e>");
                s = s.replace("d", "<d>");
                s = s.replace("<e>", padZero(d));
                s = s.replace("<d>", d);
                s = s.replace("mmm", "<o>");
                s = s.replace("mm", "<n>");
                s = s.replace("m", "<m>");
                s = s.replace("<m>", m + 1);
                s = s.replace("<n>", padZero(m + 1));
                s = s.replace("<o>", monthName[m]);
                if (isShowTime) {
                    return s.replace("yyyy", y) + " " + $V("selecthour") + ":" + $V("selectminute") + ":" + $V("selectsecond");
                } else {
                    return s.replace("yyyy", y)
                }
            };

            if (t > _max || t < _min) {
                cal.hide();
            } else {
                cal.hide();
                ctlToPlaceValue.value = constructDate(curDate, curMonth, curYear);
                ctlToPlaceValue.focus();
                ctlToPlaceValue.blur();
            }
        },
        popUpYear: function () {
            this.popDownMonth();
            if (sty_y.visibility == 'visible') {
                sty_y.visibility = 'hidden';
                return;
            }
            var sHTML = "";
            if (!yearConstructed) {

                sHTML = "<tr><td align='center' onmouseover='this.style.backgroundColor=\"#FFCC99\"' " + "onmouseout='clearInterval(intervalID1);this.style.backgroundColor=\"\"' style='cursor:pointer'    " + "onmousedown='clearInterval(intervalID1);intervalID1=setInterval(\"cal.decYear()\",30)' " + "onmouseup='clearInterval(intervalID1)'>-</td></tr>"

                var j = 0;
                nStartingYear = curYear - 3;
                var y;
                for (var i = (curYear - 3); i <= (curYear + 3); i++) {
                    y = i;
                    if (i == curYear) {
                        y = "<b>" + y + "</b>";
                    }

                    sHTML += "<tr><td id='y" + j + "' onmouseover='this.style.backgroundColor=\"#FFCC99\"' "
                        + "onmouseout='this.style.backgroundColor=\"\"' style='cursor:pointer' "
                        + "onclick='cal.selectYear(" + j + ");event.cancelBubble=true'>&nbsp;" + y + "&nbsp;</td></tr>"
                    j++;
                }

                sHTML += "<tr><td align='center' onmouseover='this.style.backgroundColor=\"#FFCC99\"' "
                    + "onmouseout='clearInterval(intervalID2);this.style.backgroundColor=\"\"' style='cursor:pointer' "
                    + "onmousedown='clearInterval(intervalID2);intervalID2=setInterval(\"cal.incYear()\",30)'   "
                    + "onmouseup='clearInterval(intervalID2)'>+</td></tr>";

                $("selectYear").innerHTML = "<table width='44' class='cal_menu' " + "onmouseover='clearTimeout(timeoutID2)' "
                    + "onmouseout='clearTimeout(timeoutID2);timeoutID2=setTimeout(\"cal.popDownYear()\",100);' "
                    + "cellspacing=0>" + sHTML + "</table>";

                yearConstructed = true
            }
            sty_y.visibility = "visible";
            sty_y.left = parseInt(sty.left) + 45 + "px";
            sty_y.top = parseInt(sty.top) + 26 + "px";
            if (ie) {
                this.timePicker.hide();
            }
        },
        popDownYear: function () {
            clearInterval(intervalID1);
            clearTimeout(timeoutID1);
            clearInterval(intervalID2);
            clearTimeout(timeoutID2);
            sty_y.visibility = "hidden";
            if (ie) {
                this.timePicker.show();
            }
        },
        popUpMonth: function () {
            this.popDownYear();
            if (sty_m.visibility == 'visible') {
                sty_m.visibility = 'hidden';
                return;
            }
            if (!monthConstructed) {
                var s = "";
                var m;
                for (var i = 0; i < 12; i++) {
                    m = monthName[i];
                    if (i == curMonth) {
                        m = "<b>" + m + "</b>";
                    }
                    s += "<tr><td id='m" + i + "' onmouseover='this.style.backgroundColor=\"#FFCC99\"' "
                        + "onmouseout='this.style.backgroundColor=\"\"' style='cursor:pointer' "
                        + "onclick='cal.selectMonth(" + i + ");"
                        + "event.cancelBubble=true;'>&nbsp;" + m + "&nbsp;</td></tr>";
                }

                $("selectMonth").innerHTML = "<table width='50' class='cal_menu' cellspacing=0 "
                    + "onmouseover='clearTimeout(timeoutID1)'  "
                    + "onmouseout='clearTimeout(timeoutID1);timeoutID1=setTimeout(\"cal.popDownMonth()\",100);"
                    + "event.cancelBubble=true'>" + s + "</table>";

                monthConstructed = true;
            }
            sty_m.visibility = "visible";
            sty_m.left = parseInt(sty.left) + 50 + "px";
            sty_m.top = parseInt(sty.top) + 26 + "px";
            if (ie) {
                this.timePicker.hide();
            }

        },
        popDownMonth: function () {
            sty_m.visibility = "hidden";
            if (ie) {
                this.timePicker.show();
            }
        },
        incYear: function () {
            var newYear;
            for (var i = 0; i < 7; i++) {
                newYear = (i + nStartingYear) + 1;
                $("y" + i).innerHTML = newYear == curYear ? "&nbsp;<b>" + newYear + "</b>&nbsp;" : "&nbsp;" + newYear + "&nbsp;";
            }
            nStartingYear++;
            bShow = true;
        },

        decYear: function () {
            var newYear;
            for (var i = 0; i < 7; i++) {
                newYear = (i + nStartingYear) - 1;
                $("y" + i).innerHTML = newYear == curYear ? "&nbsp;<b>" + newYear + "</b>&nbsp;" : "&nbsp;" + newYear + "&nbsp;";
            }
            nStartingYear--;
            bShow = true;
        },
        selectMonth: function (m) {
            monthConstructed = false;
            curMonth = m;
            this.update();
            this.popDownMonth();
        },
        selectYear: function (nYear) {
            curYear = parseInt(nYear + nStartingYear);
            yearConstructed = false;
            this.update();
            this.popDownYear();
        },
        incMonth: function () {
            curMonth++;
            if (curMonth > 11) {
                curMonth = 0;
                curYear++;
            }
            this.update();
        },
        decMonth: function () {
            curMonth--;
            if (curMonth < 0) {
                curMonth = 11;
                curYear--;
            }
            this.update();
        },
        /*
         * 设置shim
         */
        setShim: function () {
            if (ie) {
                eoms.$('cal_shim').style.width = this.el.offsetWidth + 'px';
                eoms.$('cal_shim').style.height = this.el.offsetHeight + 'px';
            }
        }

    }
    /* main return */
};

var cal = new eoms.Calendar();

/**
 * @param {HTMLElement} handler 定位元素
 * @param {HTMLElement) field 日期值的表单域
 * @param {String} format 格式字符串
 * @param {Number} top 顶部定位修正值
 * @parma {Number} left 左侧定位修正值
 * @param {Boolean} showTime 是否显示时间选择器
 * @param {String} minDate 最小可选日期,缺省为当前日期(当前日期之前的不可选)
 *  minDate=-1 无最小可选日期
 *  minDate="2008-08-01" 指定日期之前的日期不可选
 * @param {String} minDate 最大可选日期,缺省为无最大可选日期
 *  maxDate=-1 最大可选日期为昨天，今天之后的日期不可选
 *  maxDate="2008-08-01" 指定日期之后日期不可选（含当天）
 */
var popUpCalendar = function (handler, field, format, top, left, showTime, minDate, maxDate) {
    cal.show(handler, field, format, top, left, showTime, minDate, maxDate);
}