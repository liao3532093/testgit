/**
 * Created by vsofo on 2017/6/9.
 */
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
var url = '';
$(function () {
    isExit(null, null);
    init(1, '#plat_name', projectName + '/PManage/selectPlatformManageByName.asp');
    init(1, '#inter_name', projectName + '/PManage/selectInterfaceByName.asp');
    loadData();
});
$(document).ready(function () {

});
/**
 *  初始化并加载数据
 */
function loadData() {
    var id = $.session.get('id');
    if (undefined != id) {
        var url = projectName + '/example/selectCaseDetailById.asp';
        $.post(url, {id: id}, function (result) {
            var obj = result.obj;
            $('#plat_name').val(obj.platID);
            $('#inter_name').val(obj.interfaceID);
            $('#case_detail_name').val(obj.title);
            $('#case_detail_detail').val(obj.detail);
            $('#case_detail_type').val(obj.isEnable);
            $('#case_detail_id').val(obj.id);
        });
    }
}
/**
 *  保存用例详情
 */
function saveCaseDetail() {
    var platId = $('#plat_name').val();
    var interId = $('#inter_name').val();
    var name = $('#case_detail_name').val();
    var detail = $('#case_detail_detail').val();
    var type = $('#case_detail_type').val();
    if (-1 == platId || -1 == interId || stringUtils(name) || stringUtils(detail) || -1 == type) {
        alert('请填写完整信息!');
        return;
    }
    var url = projectName + '/example/saveCaseDetailInfo.asp';
    $('#case_detail_form').ajaxSubmit({
        type: 'POST',
        dataType: 'json',
        url: url,
        success: function (result) {
            if (1 == result) {
                setTimeout(function () {
                    art.dialog.open.origin.location.reload()
                }, 500);
            }
        },
        error: function (error) {
            alert('保存失败');
        },
        async: true
    });
}
/**
 *  初始化数据
 * @param type
 * @param selecdid
 * @param url
 */
function init(type, selectid, url) {
    $(selectid).html('');
    $(selectid).append($('<option value="-1">请选择</option>'));
    $.post(url, {type: type}, function (result) {
        var obj = result.rows;
        for (var i = 0; i < obj.length; i++) {
            $(selectid).append($('<option value=' + obj[i].id + '>' + obj[i].title + '</option>'));
        }
    });
}