/**
 * Created by vsofo on 2017/6/6.
 */
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
var url = '';
var scriptObj;
$(function () {
    isListPermission([], null, 'chanModelList');
});
$(document).ready(function () {
    var modelId = $.session.get('model_exp_id');
    $('#piling_model_id').val(modelId);
    $.session.remove('model_exp_id');
    initData();
});
/**
 *  初始化数据
 */
function initData() {
    $('#piling_model_exp_script').html('');
    $('#piling_model_exp_script').append($('<option value="-1">请选择</option>'));
    $('#piling_model_exp_type').html('');
    $('#piling_model_exp_type').append($('<option value="-1">请选择</option>'));
    url = projectName + '/prediction/selectPredIsEnableByName.asp';
    $.post(url, {type: 1}, function (result) {
        var obj = result.rows;
        for (var i = 0; i < obj.length; i++) {
            $('#piling_model_exp_type').append($('<option value=' + obj[i].id + '>' + obj[i].title + '</option>'));
        }
        loadData();
    });
}
function loadData() {
    initSelectList('#piling_model_exp_state', [0, 1], ['禁用', '启用']);
    var expJson = $.session.get('pilmodelexp');
    if (!stringUtils(expJson)) {
        var obj = jsonToObj(expJson);
        $.session.remove('pilmodelexp');
        $('#piling_model_exp').val(obj.details);
        $('#piling_model_exp_type').val(obj.expId);
        $('#piling_model_exp_id').val(obj.id);
        init_script(obj);
    }
}
/**
 *  加载脚本信息
 */
function load_script() {
    $('#piling_model_exp_script').html('');
    $('#piling_model_exp_script').append($('<option value="-1">请选择</option>'));
    var expId = $('#piling_model_exp_type').val();
    url = projectName + '/check/selectCheckScriptByPerd.asp';
    $.post(url, {predid: expId}, function (result) {
        scriptObj = result.rows;
        for (var i = 0; i < scriptObj.length; i++) {
            $('#piling_model_exp_script').append($('<option value=' + scriptObj[i].id + '>' + scriptObj[i].title + '</option>'));
        }
    });
}
function init_script(obj) {
    url = projectName + '/check/selectCheckScriptByPerd.asp';
    $.post(url, {predid: obj.expId}, function (result) {
        scriptObj = result.rows;
        for (var i = 0; i < scriptObj.length; i++) {
            $('#piling_model_exp_script').append($('<option value=' + scriptObj[i].id + '>' + scriptObj[i].title + '</option>'));
        }
        set_script_val(obj);
    });
}
function set_script_val(obj) {
    $('#piling_model_exp_script').val(obj.sqlId);
    $('#piling_model_exp_num').val(obj.num);
    $('#piling_model_exp_state').val(obj.state);
    init_script_val(obj.sqlId);
}
/**
 *  加载脚本信息
 */
function load_script_val() {
    var scriptId = $('#piling_model_exp_script').val();
    init_script_val(scriptId);
}
function init_script_val(scriptId) {
    for (var i = 0; i < scriptObj.length; i++) {
        if (scriptId == scriptObj[i].id) {
            $('#piling_model_exp_body').val(scriptObj[i].executeSql);
            $('#piling_model_exp_val').val(scriptObj[i].expectValue);
            break;
        }
    }
}
/**
 *  保存预期结果
 */
function savePilModelExp() {
    var details = $('#piling_model_exp').val();
    var expId = $('#piling_model_exp_type').val();
    var sqlId = $('#piling_model_exp_script').val();
    var num = $('#piling_model_exp_num').val();
    var state = $('#piling_model_exp_state').val();
    if (stringUtils(details) || -1 == expId || -1 == sqlId || stringUtils(num) || num <= 0 || -1 == state) {
        LOG_I('请填写完整信息...');
        return;
    }
    url = projectName + '/piling/savePilModelExpResult.asp';
    $('#piling_model_exp_form').ajaxSubmit({
        type: 'POST',
        dataType: 'json',
        url: url,
        success: function (result) {
            if (1 == result) {
                setTimeout(function () {
                    art.dialog.open.origin.location.reload()
                }, 500);
            } else if (-1 == result) {
                LOG_I('该顺序已重复...');
            }
        },
        error: function (error) {
            LOG_I('保存失败');
        },
        async: true
    });
}