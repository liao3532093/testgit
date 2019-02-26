/**
 * Created by vsofo on 2017/5/4.
 */
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
$(document).ready(function () {
    //改变每页数据条数,例如改为50
    $("#inter_statistics_table").datagrid('getPager').pagination('refresh', {pageSize: 50});
});
/**
 * 用例总数
 */
function operCaseInfo(value, row, index) {
    return '<a href="###" style="color: green" onclick="initCaseImplInfo(' + row.id + ',-1)">' + row.caseCount + '</a>';
}
/**
 * 用例成功数
 */
function operCaseSuccess(value, row, index) {
    return '<a href="###" style="color: green" onclick="initCaseImplInfo(' + row.id + ',2)">' + row.successNum + '</a>';
}
/**
 * 脚本异常失败数
 */
function operCaseError(value, row, index) {
    return '<a href="###" style="color: green" onclick="initCaseImplInfo(' + row.id + ',3)">' + row.errorNum + '</a>';
}
/**
 * 用例失败数
 */
function operCaseFail(value, row, index) {
    return '<a href="###" style="color: green" onclick="initCaseImplInfo(' + row.id + ',1)">' + row.failNum + '</a>';
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
    $('#inter_statistics_old_date').val(next_dataTime(now, 7));
    $('#inter_statistics_new_date').val(now);
    $('#plat_name').html('');
    $('#plat_name').append($('<option value="-1">请选择</option>'));
    $('#inter_name').html('');
    $('#inter_name').append($('<option value="-1">请选择</option>'));
    //设置日期
    var url = projectName + '/monitor/initCaseStatisticsDate.asp';
    $.post(url, {inDate: next_dataTime(now, 7), outDate: now}, function () {
    });
    //加载平台信息
    url = projectName + '/PManage/selectPlatformManageByName.asp';
    $.post(url, {type: 1}, function (result) {
        var obj = result.rows;
        for (var i = 0; i < obj.length; i++) {
            $('#plat_name').append($('<option value="' + obj[i].id + '">' + obj[i].title + '</option>'));
        }
    });
    //加载接口信息
    url = projectName + '/PManage/selectInterfaceByName.asp';
    $.post(url, {type: 1}, function (result) {
        var obj = result.rows;
        for (var i = 0; i < obj.length; i++) {
            $('#inter_name').append($('<option value="' + obj[i].id + '">' + obj[i].title + '</option>'));
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
function search_inter_statis() {
    var platId = $('#plat_name').val();
    var interId = $('#inter_name').val();
    var inDate = $('#inter_statistics_old_date').val();
    var outDate = $('#inter_statistics_new_date').val();
    $('#inter_statistics_table').datagrid('load', {
        'platID': platId,
        'interfaceID': interId,
        'inDate': inDate,
        'outDate': outDate
    });
}
/**
 *  加载当日执行详情
 * @param id
 */
function initCaseImplInfo(id, type) {
    var url = projectName + '/monitor/initCaseStatisticsById.asp';
    $.post(url, {id: id, type: type}, function (result) {
        location.href = projectName + '/main/monitor/implement_info_item.jsp';
    });
}