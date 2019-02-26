/**
 * Created by vsofo on 2017/5/3.
 */
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
$(document).ready(function () {
    //改变每页数据条数,例如改为50
    $("#case_statistics_table").datagrid('getPager').pagination('refresh', {pageSize: 50});
});
/**
 *  平台名称
 * @param value
 * @param row
 * @param index
 */
function operPlatName(value, row, index) {
    return '<a href="###" style="color: green;word-wrap: break-word;" onclick="load_inter_statistics(' + row.id + ')">' + row.platName + '</a>';
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
    var url = projectName + '/monitor/initCaseStatisticsDate.asp';
    $.post(url, {inDate: next_dataTime(now, 7), outDate: now}, function () {
    });
    $('#case_statistics_old_date').val(next_dataTime(now, 7));
    $('#case_statistics_new_date').val(now);
    $('#plat_name').html('');
    $('#plat_name').append($('<option value="-1">请选择</option>'));
    //加载平台信息
    url = projectName + '/PManage/selectPlatformManageByName.asp';
    $.post(url, {type: 1}, function (result) {
        var obj = result.rows;
        for (var i = 0; i < obj.length; i++) {
            $('#plat_name').append($('<option value="' + obj[i].id + '">' + obj[i].title + '</option>'));
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
function search_statistics() {
    var platId = $('#plat_name').val();
    var inDate = $('#case_statistics_old_date').val();
    var outDate = $('#case_statistics_new_date').val();
    $('#case_statistics_table').datagrid('load', {
        'platID': platId,
        'inDate': inDate,
        'outDate': outDate
    });
}
/**
 *  加载接口统计
 */
function load_inter_statistics(id) {
    var url = projectName + '/monitor/initCaseStatisticsById.asp';
    $.post(url, {id: id, type: -100}, function (result) {
        location.href = projectName + '/main/monitor/inter_statistics_item.jsp';
    });
}