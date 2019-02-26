/**
 * Created by vsofo on 2017/4/21.
 */
//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
// $(document).ready(function () {
//     //改变每页数据条数,例如改为50
//     loadTable50Line('#manage_table');
//     //$('#manage_update').attr('disabled', true);
//     //$('#manage_update').css('background', '#888888');
// });
function init_manage() {
    //改变每页数据条数,例如改为50
    loadTable50Line('#manage_table');
}
function init_add_manage() {
    var id = $.session.get('manage_id');
    $('#manage_id').val(id);
    if (id > 0) {
        var url = projectName + '/PManage/selectPlatformManageByIdToName.asp'
        $.post(url, {id: id}, function (result) {
            var obj = result.obj;
            $('#manage_info').val(obj.title);
            $('#manage_url').val(obj.exeUrl);
            $('#manage_id').val(obj.id);
        });
    }
    $.session.remove('manage_id');
}
/**
 *  渠道用例执行地址
 * @param value
 * @param row
 * @param index
 */
function operexecuteurl(value, row, index) {
    return '<span style="word-wrap: break-word;" class="easyui-tooltip" title="' + row.exeUrl + '">' + row.exeUrl + '</span>';
}
/***
 *  平台信息操作
 * @param value
 * @param row
 * @param index
 */
function operation(value, row, index) {
    if (1 == row.isEnable) {
        return '<a href="###" style="color: blue" onclick="disableManage(' + row.id + ')">禁用</a>&nbsp;|&nbsp;' +
            '<a href="###" style="color: blue" onclick="updateManageById(' + row.id + ')">修改</a>';
    } else {
        return '<a href="###" style="color: blue" onclick="disableManage(' + row.id + ')">启用</a>&nbsp;|&nbsp;' +
            '<a href="###" style="color: blue" onclick="updateManageById(' + row.id + ')">修改</a>';
    }
}
/***
 *  平台信息状态
 * @param value
 * @param row
 * @param index
 * @returns {*}
 */
function operstate(value, row, index) {
    if (1 == row.isEnable) {
        return '<font color="green">启用</font>';
    } else {
        return '<font color="red">禁用</font>';
    }
}
/***
 *  修改平台名称
 * @param id
 * @param name
 */
function updateManageById(id) {
    $.session.set('manage_id', id);
    var url = projectName + '/main/platform/add_manage.jsp';
    art.dialog.open(url, {id: "tip", height: 180, width: 400, title: '平台信息', lock: true}, true); //打开子窗体
}
/***
 *  禁用平台名称
 * @param id
 */
function disableManage(id) {
    var url = projectName + '/PManage/disablePlatformManageById.asp';
    $.post(url, {
            id: id
        }, function (result) {
            if (1 == result) {
                $('#manage_table').datagrid('reload');  //刷新列表
            }
        }
    );
}
/**
 *  保存平台信息
 */
function saveManage() {
    var url = '';
    var manage_info = $('#manage_info').val();
    var manage_url = $('#manage_url').val();
    var manage_id = $('#manage_id').val();
    if (stringUtils(manage_info) || stringUtils(manage_url)) {
        alert('请填写完整信息');
        return;
    }
    if (manage_id > 0) {
        url = projectName + '/PManage/updatePlatformManageById.asp';
        $.post(url, {
            'id': manage_id,
            'title': manage_info,
            'exeUrl': manage_url
        }, function (res) {
            if (1 == res) {
                art.dialog.open.origin.location.reload();
                //$('#manage_table').datagrid('reload');  //刷新列表
            }
        })
    } else {
        url = projectName + '/PManage/addPlatformManage.asp';
        $.post(url, {
            'title': manage_info,
            'exeUrl': manage_url
        }, function (res) {
            if (1 == res) {
                //$.success('添加完成');
                alert('添加完成');
                art.dialog.open.origin.location.reload();
                //$('#manage_table').datagrid('reload');  //刷新列表
            } else if (-1 == res) {
                //$.success('平台名称已存在!');
                alert('平台名称已存在!');
            }
        })
    }
}
/**
 *  搜索平台信息
 */
function searchManage() {
    var manage_info = $('#manage_info').val();
    $('#manage_table').datagrid('load', {'title': manage_info});
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
