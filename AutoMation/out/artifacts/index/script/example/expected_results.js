/**
 * Created by vsofo on 2017/4/28.
 */
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);

document.write("<script type='text/javascript' src='" + projectName + "/script/utils/pako.js'></script>");

var scriptObj;  //全局脚本
var caseModelId = 'caseModelId';  //所属用例模块ID
/**
 * 状态
 * @param value
 * @param row
 * @param index
 */
function operstate(value, row, index) {
    if (1 == row.isEnable) {
        return '<font color="green">启用</font>';
    } else {
        return '<font color="red">禁用</font>';
    }
}
/**
 *  操作s
 * @param value
 * @param row
 * @param index
 */
function operation(value, row, index) {
    if (1 == row.isEnable) {
        return '<a href="###" style="color: blue;" onclick="openClose(' + row.id + ')">禁用</a>&nbsp;&nbsp;|&nbsp;&nbsp;' +
            '<a href="###" style="color: blue;" onclick="update_exp_result(' + row.id + ')">修改</a>';
    } else {
        return '<a href="###" style="color: blue;" onclick="openClose(' + row.id + ')">启用</a>&nbsp;&nbsp;|&nbsp;&nbsp;' +
            '<a href="###" style="color: blue;" onclick="update_exp_result(' + row.id + ')">修改</a>';
    }
}
/**
 *  执行脚本提示
 * @param value
 * @param row
 * @param index
 */
function operscript(value, row, index) {
    var val = stringUtils(row.sqlName) ? '' : row.sqlName;
    return '<span style="word-wrap: break-word;" class="easyui-tooltip" title="' + val + '">' + val + '</span>';
}
/**
 *  初始化信息
 */
function init() {
    var json = $.session.get('config');
    var obj = jQuery.parseJSON(json);
    $.session.set(caseModelId, obj.id);
    $('#config_detail_title').text(obj.caseName);
    $('#request_name').text(obj.modelName);
}
/**
 *  初始化添加
 */
function initAdd() {
    $('#caseModelId').val($.session.get(caseModelId));
    $.session.remove(caseModelId);  //删除所属用例模块ID
    var caseStr = $.session.get('json');
    caseStr = decodeURIComponent(unzip(caseStr));
    var caseObj = jQuery.parseJSON(caseStr);
    var json = $.session.get('config');
    var obj = jQuery.parseJSON(json);
    $('#case_detail_name').text(caseObj.detail);
    $('#expectType').html('');
    $('#expectType').append($('<option value="-1">请选择</option>'));
    $('#common_script').html('');
    $('#common_script').append($('<option value="-1">请选择</option>'));
    var url = projectName + '/prediction/selectPredIsEnableByName.asp';
    $.post(url, {type: 0}, function (result) {
        var perObj = result.rows;
        for (var i = 0; i < perObj.length; i++) {
            $('#expectType').append($('<option value=' + perObj[i].id + '>' + perObj[i].title + '</option>'));
        }
        var results = $.session.get('results');
        if (!stringUtils(results)) {
            var resObj = jQuery.parseJSON(results);
            $('#detail').val(resObj.detail);
            $('#expectType').val(resObj.expectTypeId);
            $('#orderNum').val(resObj.orderNum);
            $('#isEnable').val(resObj.isEnable);
            $('#exp_result_id').val(resObj.id);
            loadScriptInfo(resObj, resObj.expectTypeId);
        }
    });
}
/**
 *  加载脚本信息
 * @param sqlId
 */
function loadScript(sqlId) {
    $('#sqlId').val(sqlId);  //执行的脚本ID
    for (var i = 0; i < scriptObj.length; i++) {
        if (sqlId == scriptObj[i].id) {
            $('#script_info').val(scriptObj[i].executeSql);
            $('#exp_value').val(scriptObj[i].expectValue);
            break;
        }
    }
}
function loadScriptInfo(resObj, id) {
    scriptObj = null;
    $('#common_script').html('');
    $('#common_script').append($('<option value="-1">请选择</option>'));
    $('#expectTypeId').val(id);  //预测类型ID
    var url = projectName + '/check/selectCheckScriptByPerd.asp';
    $.post(url, {predid: id}, function (result) {
        var obj = result.rows;
        if (obj.length > 0) {
            $('#imple_script').show();
            $('#imple_value').show();
            scriptObj = obj;
            for (var i = 0; i < obj.length; i++) {
                $('#common_script').append($('<option value=' + obj[i].id + '>' + obj[i].title + '</option>'));
            }
        } else {
            $('#imple_script').hide();
            $('#imple_value').hide();
        }
        if (!stringUtils(resObj)) {
            loadScript(resObj.sqlId);
        }
    });
}
/**
 *  获取脚本信息
 */
function getScript() {
    var id = $('#expectType').val();
    loadScriptInfo(null, id);
}
/**
 *  获取脚本信息内容
 */
function getScriptUrl() {
    var id = $('#common_script').val();
    loadScript(id);
}
/**
 *  添加预期结果
 */
function add_exp_result() {
    $.session.remove('id');
    $.session.remove('results');
    var url = projectName + '/main/example/add_expected_results.jsp';
    art.dialog.open(url, {id: "tip", height: 420, width: 550, title: '新增预测结果', lock: true}, true); //打开子窗体
}
/**
 *  保存预期结果
 */
function save_exp_result() {
    var detail = $('#detail').val();
    var expectTypeId = $('#expectTypeId').val();
    var orderNum = $('#orderNum').val();
    var isEnable = $('#isEnable').val();
    if (stringUtils(detail) || -1 == expectTypeId || stringUtils(orderNum) || orderNum <= 0 || -1 == isEnable) {
        alert('请完善信息!');
        return;
    }
    var url = projectName + '/example/saveExpectedResults.asp';
    $('#exp_result_form').ajaxSubmit({
        type: 'POST',
        dataType: 'json',
        url: url,
        success: function (result) {
            if (1 == result) {
                $.session.remove('id');
                $.session.remove('results');
                setTimeout(function () {
                    art.dialog.open.origin.location.reload()
                }, 500);
            } else if (-1 == result) {
                alert('执行顺序重复!');
            }
        },
        error: function (error) {
            alert('保存失败');
        },
        async: true
    });
}
/**
 *  启用禁用
 * @param id
 */
function openClose(id) {
    var url = projectName + '/example/openCloseExpectedResults.asp';
    $.post(url, {id: id}, function (result) {
        if (1 == result) {
            $('#expected_results_table').datagrid('reload');  //刷新列表
        }
    });
}
/**
 *  修改预期结果
 * @param id
 */
function update_exp_result(id) {
    var url = projectName + '/example/selectExpectedResultsById.asp';
    $.post(url, {id: id}, function (result) {
        var json = getJson(result.obj);
        $.session.set('results', json);
        url = projectName + '/main/example/add_expected_results.jsp';
        art.dialog.open(url, {id: "tip", height: 400, width: 550, title: '修改预测结果', lock: true}, true); //打开子窗体
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
    if (str == null || str == '' || str.length <= 0) {
        return true;
    } else {
        return false;
    }
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
