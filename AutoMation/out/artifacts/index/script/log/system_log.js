/**
 * Created by vsofo on 2017/5/10.
 */
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
$(function () {
    isPermission(null, null, null, 'systemLog');
    init();
});
$(document).ready(function () {
    //改变每页数据条数,例如改为50
    loadTable50Line('#system_log_table');
});
/**
 *  日志详情
 * @param value
 * @param row
 * @param index
 */
function operLogInfo(value, row, index) {
    return '<span style="word-wrap: break-word;" class="easyui-tooltip" title="' + row.info + '">' + row.info + '</span>';
}
/**
 *  初始化信息
 */
function init() {
    var myDate = new Date();
    //获取当前年
    var year = myDate.getFullYear();
    //获取当前月
    var month = myDate.getMonth() + 1;
    //获取当前日
    var date = myDate.getDate();
    var h = myDate.getHours();       //获取当前小时数(0-23)
    var m = myDate.getMinutes();     //获取当前分钟数(0-59)
    var s = myDate.getSeconds();
    var now = year + '-' + expDate(month) + "-" + expDate(date) + " " + expDate(h) + ':' + expDate(m) + ":" + expDate(s);
    $.post(projectName + '/systemLog/setLogDate.asp', {oldDate: next_dataTime(now, 7), newDate: now}, function () {
    });
    $('#systemlog_old_date').val(next_dataTime(now, 7));
    $('#systemlog_new_date').val(now);
    $('#log_type').html('');
    $('#log_type').append($('<option value="-1">请选择</option>'));
    //加载日志信息
    var url = projectName + '/logtype/selectLogTypeAll.asp';
    $.post(url, {page: 1, rows: 500}, function (result) {
        var obj = result.rows;
        for (var i = 0; i < obj.length; i++) {
            $('#log_type').append($('<option value="' + obj[i].id + '">' + obj[i].title + '</option>'));
        }
    });
}
/**
 *  往前推几天
 * @param oldDate
 * @param newDate
 */
function next_dataTime(oldDate, newDate) {
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
    var nd = addNDays(oldDate, newDate);
    var getNewDay = nd.format('yyyy-MM-dd hh:mm:ss');
    return getNewDay;
}
/**
 *  格式化日期
 * @param date
 */
function expDate(date) {
    return date < 10 ? '0' + date : date;
}
/**
 *  搜索
 */
function search_system_log() {
    var showName = $('#user_name').val();
    var info = $('#log_info').val();
    var logId = $('#log_type').val();
    var inDate = $('#systemlog_old_date').val();
    var outDate = $('#systemlog_new_date').val();
    $('#system_log_table').datagrid('load', {
        'showName': showName,
        'info': info,
        'logId': logId,
        'inDate': inDate,
        'outDate': outDate
    });
}
/**
 *  将对象转成json
 * @param obj
 * @returns {string|*}
 */
function getJson(obj) {
    return $.toJSON(obj);
}
/***
 *  判断是否为空
 * @param str
 * @returns {boolean}
 */
function stringUtils(str) {
    if (str == '' || str.length <= 0) {
        return true;
    } else {
        return false;
    }
}