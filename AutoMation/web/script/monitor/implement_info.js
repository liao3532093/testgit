/**
 * Created by vsofo on 2017/5/3.
 */
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
$(document).ready(function () {
    //改变每页数据条数,例如改为50
    $("#implement_table").datagrid('getPager').pagination('refresh', {pageSize: 50});
});
/**
 * 状态
 * @param value
 * @param row
 * @param index
 */
function operstate(value, row, index) {
    if (0 == row.result) {
        return '<font color="#bc8f8f">等待</font>';
    } else if (1 == row.result) {
        return '<font color="red">失败</font>';
    } else if (2 == row.result) {
        return '<font color="green">成功</font>';
    } else if (3 == row.result) {
        return '<font color="red">脚本异常</font>';
    }
}
/**
 *  用例标题
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function opertitle(value, row, index) {
    return '<a href="###" style="color: green;word-wrap: break-word;" onclick="open_result_info(' + row.id + ',' + row.caseId + ')" class="easyui-tooltip" title="' + row.dName + '">' + row.dName + '</a>';
}
/**
 *  用例描述
 * @param value
 * @param row
 * @param index
 */
function operdetail(value, row, index) {
    return '<span style="word-wrap: break-word;" title="' + row.detail + '" class="easyui-tooltip">' + row.detail + '</span>';
}
/**
 * 打开
 */
function open_result_info(id, cid) {
    var url = projectName + '/monitor/getFinalResultInfo.asp';
    $.post(url, {id: id, cid: cid}, function (result) {
        if (1 == result) {
            url = projectName + '/main/monitor/implement_result_info.jsp';
            art.dialog.open(url, {id: "tip", height: 600, width: 800, title: '用例执行详情', lock: true}, true); //打开子窗体
        }
    });
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
    $('#implement_old_date').val(next_dataTime(now, 7));
    $('#implement_new_date').val(now);
    $('#plat_name').html('');
    $('#plat_name').append($('<option value="-1">请选择</option>'));
    $('#inter_name').html('');
    $('#inter_name').append($('<option value="-1">请选择</option>'));
    $('#state').html('');
    $('#state').append($('<option value="-1">请选择</option>'));
    //加载平台信息
    var url = projectName + '/PManage/selectPlatformManageByName.asp';
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
    //加载状态信息
    url = projectName + '/monitor/getImplementState.asp';
    $.post(url, {inDate: next_dataTime(now, 7), outDate: now}, function (result) {
        var obj = result.obj;
        for (var i = 0; i < obj.length; i++) {
            $('#state').append($('<option value="' + obj[i].code + '">' + obj[i].msg + '</option>'));
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
function search_implement() {
    var platId = $('#plat_name').val();
    var interId = $('#inter_name').val();
    var inDate = $('#implement_old_date').val();
    var outDate = $('#implement_new_date').val();
    var dName = $('#detail_name').val();
    var state = $('#state').val();
    $('#implement_table').datagrid('load', {
        'platId': platId,
        'interId': interId,
        'inDate': inDate,
        'outDate': outDate,
        'dName': dName,
        'state': state
    });
}