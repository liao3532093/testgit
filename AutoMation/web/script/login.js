/**
 * Created by vsofo on 2017/5/10.
 */
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
var verifyCode;
$(function () {
    // $.post(projectName + '/jump/sessionId.asp', {}, function (sessionid) {
    //     readMessage(sessionid, function (r) {
    //
    //     });
    // });
});
$(document).ready(function () {
    verifyCode = new GVerify("ver_code");
});
/**
 *  刷新验证码
 */
function readyCode() {
    verifyCode.refresh();  //刷新图形验证码
}
/**
 *  登录s
 */
function login() {
    var account = $('#account').val();
    var password = $('#password').val();
    var txtcode = $('#txtcode').val();
    if (stringUtils(account) || stringUtils(password)) {
        verifyCode.refresh();  //刷新图形验证码
        LOG_I('请输入账号和密码!');
        return;
    }
    if (!verifyCode.validate(txtcode)) {
        verifyCode.refresh();  //刷新图形验证码
        $('#txtcode').val('');
        LOG_I('验证码有误!');
        return;
    }
    var url = projectName + '/user/login.asp';
    $('#login_form').ajaxSubmit({
        type: 'POST',
        dataType: 'json',
        url: url,
        success: function (result) {
            if (1 == result) {
                location.href = projectName + '/jump/goToMain.asp';
            } else if (-1 == result) {
                verifyCode.refresh();  //刷新图形验证码
                LOG_I('用户名或密码有误或是否锁定!');
            } else if (-2 == result) {
                REMOVE_LOGIN(function (r) {
                    if (r) {  //进行强制登录
                        $.post(projectName + '/user/removeLoginUser.asp', {}, function (sessionId) {
                            sendMessage(sessionId, '你已经被人强制T下线了...', function (r) {
                                location.href = projectName + '/jump/goToMain.asp';
                            });
                        });
                    }
                });
            }
        },
        error: function (error) {
            verifyCode.refresh();  //刷新图形验证码
            LOG_I('登录失败!');
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