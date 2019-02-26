/**
 * Created by vsofo on 2017/4/21.
 */
//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
/***
 *  接口信息操作
 * @param value
 * @param row
 * @param index
 */
function operation(value, row, index) {
    if (1 == row.isEnable) {
        return '<a href="###" style="color: blue" onclick="openClose(' + row.id + ')">禁用</a>&nbsp;&nbsp;|&nbsp;&nbsp;' +
            '<a href="###" style="color: blue" onclick="updateInterfaceById(' + row.id + ')">修改</a>';
    } else {
        return '<a href="###" style="color: blue" onclick="openClose(' + row.id + ')">启用</a>&nbsp;&nbsp;|&nbsp;&nbsp;' +
            '<a href="###" style="color: blue" onclick="updateInterfaceById(' + row.id + ')">修改</a>';
    }
}
/***
 *  状态
 * @param value
 * @param row
 * @param inde
 * @returns {*}
 */
function operstate(value, row, inde) {
    if (1 == row.isEnable) {
        return '<font color="green">启用</font>';
    } else {
        return '<font color="red">禁用</font>';
    }
}
/***
 *  初始化数据
 */
function initData(type, id, pid) {
    var url = projectName + '/PManage/selectPlatformManageByName.asp';
    $.post(url, {type: type}, function (result) {
        $(id).html('');
        $(id).append($('<option value="-1">请选择</option>'));
        for (var i = 0; i < result.rows.length; i++) {
            $(id).append('<option value="' + result.rows[i].id + '">' + result.rows[i].title + '</option>');
        }
        if (0 < pid) {
            url = projectName + '/PManage/selectPlatInterfaceById.asp';
            $.post(url, {id: pid}, function (result) {
                var obj = result.obj;
                $('#plat_name').val(obj.platId);
                $('#interface_name').val(obj.title);
                $('#interface_type').val(obj.isEnable);
            });
        }
    });
}
/**
 *  搜索接口
 */
function searchInterface() {
    var platid = $('#search_platid').val();
    var name = $('#search_name').val();
    if (platid == -1) {
        $('#interface_table').datagrid('load', {'title': name});
    } else {
        $('#interface_table').datagrid('load', {'platId': platid, 'title': name});
    }
}
/***
 *  添加平台接口
 */
function addPlatInterface() {
    $.session.remove('id');
    var url = url = projectName + '/main/platform/add_interface.jsp';
    art.dialog.open(url, {id: "tip", height: 180, width: 400, title: '新增接口', lock: true}, true); //打开子窗体
}
/***
 *  保存平台接口
 */
function savePlatInterface() {
    var url = projectName + '/PManage/savePlatformInterface.asp';
    var platName = $('#plat_name').val();
    var type = $('#interface_type').val();
    var interfaceName = $('#interface_name').val();
    if (-1 != platName && -1 != type && !stringUtils(interfaceName)) {
        $('#interface_form').ajaxSubmit({
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
}
/**
 *  启用禁用
 * @param id
 */
function openClose(id) {
    var url = projectName + '/PManage/openCloseInterfaceById.asp';
    $.post(url, {id: id}, function (result) {
        if (1 == result) {
            $('#interface_table').datagrid('reload');  //刷新列表
        }
    });
}
/***
 *  修改接口
 * @param platId
 * @param typeId
 * @param interName
 */
function updateInterfaceById(platId) {
    $.session.set('id', platId);
    var url = projectName + '/main/platform/add_interface.jsp?id=' + platId;
    art.dialog.open(url, {id: "tip", height: 180, width: 400, title: '修改接口', lock: true}, true); //打开子窗体
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