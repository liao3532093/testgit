/**
 * Created by vsofo on 2017/5/10.
 */
//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
var userObj;  //用户信息
$(document).ready(function () {
    var url = projectName + '/user/getUserItem.asp';
    $.post(url, {}, function (result) {
        userObj = result.obj;
        if (userObj != null) {
            $('#account').val(userObj.account);
            $('#showName').val(userObj.showName);
            $('#user_id').val(userObj.id);
        }
    });
});
/**
 *  修改信息
 */
function saveUser() {
    var showName = $('#showName').val();
    var password = $('#password').val();
    var oldPass = $('#oldPass').val();
    var newPass = $('#newPass').val();
    if (stringUtils(showName) || stringUtils(password) || stringUtils(oldPass) || stringUtils(newPass)) {
        alert('请完善信息!');
        return;
    }
    if (oldPass != newPass) {
        alert('两次输入密码不相同!');
        return;
    }
    var url = projectName + '/user/updateUserInfo.asp';
    $('#update_user_form').ajaxSubmit({
        type: 'POST',
        dataType: 'json',
        url: url,
        success: function (result) {
            if (1 == result) {
                setTimeout(function () {
                    art.dialog.open.origin.location.reload()
                }, 500);
            } else if (-1 == result) {
                alert('旧密码有误!');
            } else if (-2 == result) {
                alert('新密码和旧密码不能相同!');
            }
        },
        error: function (error) {
            alert('修改失败!');
        },
        async: true
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
    if (str == '' || str.length <= 0) {
        return true;
    } else {
        return false;
    }
}