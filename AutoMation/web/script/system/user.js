/**
 * Created by vsofo on 2017/5/5.
 */
//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
/***
 *  用户操作
 * @param value
 * @param row
 * @param index
 */
function operation(value, row, index) {
    if (0 == row.isLock) {
        return '<a href="###" style="color: blue" onclick="openClose(' + row.id + ')">禁用</a>&nbsp;&nbsp;|' +
            '&nbsp;&nbsp;<a href="###" style="color: blue" onclick="update_user_pass(' + row.id + ')">修改密码</a>&nbsp;&nbsp;' +
            '|&nbsp;&nbsp;<a href="###" style="color: blue" onclick="openUserPermission(' + row.id + ')">权限配置</a>';
    } else {
        return '<a href="###" style="color: blue" onclick="openClose(' + row.id + ')">启用</a>&nbsp;&nbsp;|' +
            '&nbsp;&nbsp;<a href="###" style="color: blue" onclick="update_user_pass(' + row.id + ')">修改密码</a>&nbsp;&nbsp;' +
            '|&nbsp;&nbsp;<a href="###" style="color: blue" onclick="openUserPermission(\' + row.id + \')">权限配置</a>';
    }
}
/**
 *  用户状态
 * @param value
 * @param row
 * @param index
 */
function operstate(value, row, index) {
    if (0 == row.isLock) {
        return '<font color="green">正常</font>';
    } else {
        return '<font color="red">锁定</font>';
    }
}
/**
 *  初始化信息
 */
function initAddUser() {
    if (stringUtils($.session.get('uid'))) {
        $('#old_tr').hide();
        $('#new_tr').hide();
    } else {
        $('#account_tr').hide();
        $('#pass_tr').hide();
        var json = $.session.get('uData');
        var obj = jQuery.parseJSON(json);
        $('#user_id').val(obj.id);
        $('#user_name').val(obj.showName);
        $('#user_name').attr('readonly', true);
    }
}
/**
 *  添加用户信息
 */
function add_user() {
    $.session.remove('uid');
    $.session.remove('uData');
    var url = projectName + '/main/system/add_user.jsp';
    art.dialog.open(url, {id: "tip", height: 260, width: 400, title: '添加用户', lock: true}, true); //打开子窗体
}
/**
 *  启用禁用
 * @param id
 */
function openClose(id) {
    var url = projectName + '/user/openCloseUserInfoById.asp';
    $.post(url, {id: id}, function (result) {
        if (1 == result) {
            $('#user_table').datagrid('reload');  //刷新列表
        }
    });
}
/***
 *  保存用户信息
 */
function saveUser() {
    var uName = $('#user_name').val();
    var uAmin = $('#user_anim').val();
    var uPass = $('#user_pass').val();
    var oldPass = $('#old_user_pass').val();
    var newPass = $('#new_user_pass').val();
    if (stringUtils($.session.get('uid'))) {
        if (stringUtils(uName) || stringUtils(uAmin) || stringUtils(uPass)) {
            alert('请填写完整信息!');
            return;
        }
    } else {
        if (stringUtils(uName) || stringUtils(oldPass) || stringUtils(newPass)) {
            alert('请填写完整信息!');
            return;
        }
    }
    var url = projectName + '/user/saveUserInfo.asp';
    $('#user_form').ajaxSubmit({
        type: 'POST',
        dataType: 'json',
        url: url,
        success: function (result) {
            if (1 == result) {
                $.session.remove('uid');
                $.session.remove('uData');
                setTimeout(function () {
                    art.dialog.open.origin.location.reload()
                }, 500);
            } else if (-1 == result) {
                alert('用户账号已存在!');
            } else if (-2 == result) {
                alert('旧密码有误!');
            } else if (-3 == result) {
                alert('新密码和旧密码不能相同!');
            }
        },
        error: function (error) {
            alert('保存失败');
        },
        async: true
    });
}
/**
 *  搜索用户
 */
function searchUser() {
    var showName = $('#showName').val();
    $('#user_table').datagrid('load', {'showName': showName});
}
/**
 *  修改
 * @param id
 */
function update_user_pass(id) {
    $.session.set('uid', id);
    var url = projectName + '/user/selectUserInfoById.asp';
    $.post(url, {id: id}, function (result) {
        var json = getJson(result.obj);
        $.session.set('uData', json);
        url = projectName + '/main/system/add_user.jsp';
        art.dialog.open(url, {id: "tip", height: 260, width: 400, title: '修改密码', lock: true}, true); //打开子窗体
    });
}
/**
 *  设置权限
 */
function setPermission() {
    var id = $.session.get('uid');
    var parentId = $.session.get('parentClassId');
    var rows = $('#ermission_user_table').datagrid("getSelections");
    // if (rows.length > 0) {
    //     var url = projectName + '/user/addUserPermission.asp';
    //     $.post(url, {id: id, permission: getJson(rows)}, function (result) {
    //         if (1 == result) {
    //             alert('权限设置成功!');
    //         }
    //     });
    // } else {
    //     alert('请选择权限!')
    // }
    var url = projectName + '/user/addUserPermission.asp';
    $.post(url, {id: id, pId: parentId, permission: getJson(rows)}, function (result) {
        if (1 == result) {
            alert('权限设置成功!');
        }
    });
}
/**
 *  打开用户权限
 * @param id
 */
function openUserPermission(id) {
    $.session.set('uid', id);
    var url = projectName + '/user/loadPermissionInfo.asp';
    $.post(url, {}, function (result) {
        location.href = projectName + '/main/system/permission_user.jsp';
    });
}
/**
 * 加载权限项
 * @param id
 */
function openPermission(id) {
    var uid = $.session.get('uid');
    $.session.set('parentClassId', id);
    $('#ermission_user_table').datagrid('load', {'parentClassId': id});
    $('#ermission_user_table').datagrid({
        onLoadSuccess: function (data) {
            var url = projectName + '/user/loadUserPermission.asp';
            $.post(url, {id: uid, cid: id}, function (result) {
                if (result.obj != null) {
                    var item = result.obj;
                    for (var i = 0; i < item.length; i++) {
                        $('#ermission_user_table').datagrid('selectRow', item[i]);
                    }
                }
            });
        }
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
    if (str == null || str == '' || str.length <= 0 || str == undefined) {
        return true;
    } else {
        return false;
    }
}