/**
 * Created by vsofo on 2017/6/9.
 */
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
var url = '';
$(function () {
    isExit(null, null);
});
$(document).ready(function () {
    initAdd();
});
/**
 *  初始化添加
 */
function initAdd() {
    $('#caseModelId').val($.session.get(caseModelId));
    $.session.remove(caseModelId);  //删除所属用例模块ID
    var caseStr = $.session.get('json');
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
 *  获取脚本信息
 */
function getScript() {
    var id = $('#expectType').val();
    loadScriptInfo(null, id);
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
/**
 *  获取脚本信息内容
 */
function getScriptUrl() {
    var id = $('#common_script').val();
    loadScript(id);
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