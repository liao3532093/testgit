/**
 * Created by vsofo on 2017/6/5.
 */
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
/**
 *  初始化下拉列表信息
 * @param selectid
 * @param ids
 * @param objs
 */
function initSelectListNoTtile(selectid, ids, objs) {
    $(selectid).html('');
    if (objs.length > 0) {
        for (var i = 0; i < objs.length; i++) {
            $(selectid).append($('<option value=' + ids[i] + '>' + objs[i] + '</option>'));
        }
    }
}
/**
 *  初始化下拉列表信息
 * @param selectid
 * @param ids
 * @param objs
 */
function initSelectList(selectid, ids, objs) {
    $(selectid).html('');
    $(selectid).append($('<option value="-1">请选择</option>'));
    if (objs.length > 0) {
        for (var i = 0; i < objs.length; i++) {
            $(selectid).append($('<option value=' + ids[i] + '>' + objs[i] + '</option>'));
        }
    }
}
/**
 * 修改列表显示数量
 * @param tableId
 */
function loadTable50Line(tableId) {
    //改变每页数据条数,例如改为50
    $(tableId).datagrid('getPager').pagination('refresh', {pageSize: 50});
}
/**
 *  将对象转成json
 * @param obj
 * @returns {string|*}
 */
function getJson(obj) {
    return $.toJSON(obj);
}
/**
 *  json转对象
 * @param json
 */
function jsonToObj(json) {
    return jQuery.parseJSON(json);
}
/***
 *  判断是否为空
 * @param str
 * @returns {boolean}
 */
function stringUtils(str) {
    if (str == '' || str.length <= 0 || undefined == str) {
        return true;
    } else {
        return false;
    }
}
/**
 *  更改样式
 * @param showName
 * @param hideName
 */
function updateBtnStyle(showName, hideName) {
    $(showName).attr('disabled', false);
    $(showName).css('background', '#2288CC');
    $(hideName).attr('disabled', true);
    $(hideName).css('background', '#888888');
}
/**
 *  获取当前日期
 * @returns {string}
 */
function initInDate(h, m, s) {
    var myDate = new Date();
    //获取当前年
    var year = myDate.getFullYear();
    //获取当前月
    var month = myDate.getMonth() + 1;
    //获取当前日
    var date = myDate.getDate();
    //var h = myDate.getHours();       //获取当前小时数(0-23)
    //var m = myDate.getMinutes();     //获取当前分钟数(0-59)
    //var s = myDate.getSeconds();
    var now = year + '-' + expDate(month) + "-" + expDate(date) + " " + expDate(h) + ':' + expDate(m) + ":" + expDate(s);
    return now;
}
/**
 *  格式化日期
 * @param date
 */
function expDate(date) {
    return date < 10 ? '0' + date : date;
}
/**
 *  往前推几天
 * @param newDate
 */
function next_dataTime(newDate) {
    Date.prototype.format = function (format) {
        var o = {
            "M+": this.getMonth() + 1, //month
            "d+": this.getDate(), //day
            "h+": this.getHours(), //hour
            "m+": this.getMinutes(), //minute
            "s+": this.getSeconds(), //second
            "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
            "S": this.getMilliseconds() //millisecond
        }
        if (/(y+)/.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
            }
        }
        return format;
    }
    var addNDays = function (date, n) {
        var d = new Date(Date.parse(date.replace(/-/g, "/")));
        var time = d.getTime();
        var newTime = time - n * 24 * 60 * 60 * 1000;
        return new Date(newTime);
    };
    var nd = addNDays(initInDate(0, 0, 0), newDate);
    var getNewDay = nd.format('yyyy-MM-dd hh:mm:ss');
    return getNewDay;
}
/**
 *  加载进度条
 */
function onloading() {
    $("<div class=\"datagrid-mask\"></div>").css({
        display: "block",
        width: "100%",
        height: $(window).height()
    }).appendTo("body");
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在执行用例，请稍候。。。").appendTo("body").css({
        display: "block",
        left: ($(document.body).outerWidth(true) - 190) / 2,
        top: ($(window).height() - 45) / 2
    });
}
/**
 *  关闭进度条
 */
function removeload() {
    $(".datagrid-mask").remove();
    $(".datagrid-mask-msg").remove();
}
/**
 *  压缩
 * @param str
 * @returns {string}
 */
function zip(str) {
    var binaryString = pako.gzip(str, {to: 'string'});
    return btoa(binaryString);
}
function unzip(zip) {
    var strData = atob(zip);
    // Convert binary string to character-number array
    var charData = strData.split('').map(function (x) {
        return x.charCodeAt(0);
    });
    // Turn number array into byte-array
    var binData = new Uint8Array(charData);
    // // unzip
    var data = pako.inflate(binData);
    // Convert gunzipped byteArray back to ascii string:
    strData = String.fromCharCode.apply(null, new Uint16Array(data));
    return strData;
}