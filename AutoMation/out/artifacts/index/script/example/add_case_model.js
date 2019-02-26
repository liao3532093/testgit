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
    initAddModel();
});
/**
 *
 */
function initAddModel() {
    var json = $.session.get('json');
    var obj = jQuery.parseJSON(json);
    $('#add_case_plat').text(obj.platName);
    $('#add_case_inter').text(obj.interName);
    $('#add_case_id').val(obj.id);
    $('#add_case_request_name').html('');
    $('#add_case_request_name').append($('<option value="-1">请选择</option>'));
    var url = projectName + '/PManage/selectPlatRequestByNames.asp';
    var model = $.session.get('model');
    if (undefined != model) {
        var id = $.session.get('id');
        if (undefined != id) {
            $('#add_case_model_id').val(id);
        }
        $.post(url, {type: 0}, function (result) {
            var obj = result.rows;
            for (var i = 0; i < obj.length; i++) {
                $('#add_case_request_name').append($('<option value=' + obj[i].id + '>' + obj[i].title + '</option>'));
            }
            var mObj = jQuery.parseJSON(model);
            $('#add_case_request_name').val(mObj.modelId);
            $('#add_case_model_num').val(mObj.orderNum);
            $('#add_case_model_type').val(mObj.isEnable);
            $.session.remove('model');
        });
    } else {
        $.post(url, {type: 1}, function (result) {
            var obj = result.rows;
            for (var i = 0; i < obj.length; i++) {
                $('#add_case_request_name').append($('<option value=' + obj[i].id + '>' + obj[i].title + '</option>'));
            }
        });
    }
}
/**
 * 保存用例模块
 */
function saveModelInfo() {
    var modelId = $('#add_case_request_name').val();
    var orderNum = $('#add_case_model_num').val();
    var isEnable = $('#add_case_model_type').val();
    if (-1 == modelId || stringUtils(orderNum) || -1 == isEnable) {
        alert('请填写完整信息!');
        return;
    }
    var url = projectName + '/example/saveCaseModelInfo.asp';
    $('#case_model_form').ajaxSubmit({
        type: 'POST',
        dataType: 'json',
        url: url,
        success: function (result) {
            if (1 == result) {
                setTimeout(function () {
                    art.dialog.open.origin.location.reload()
                }, 500);
            } else if (-1 == result) {
                alert('该顺序已存在!');
            }
        },
        error: function (error) {
            alert('保存失败');
        },
        async: true
    });
}