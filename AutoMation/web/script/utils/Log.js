/**
 * Created by vsofo on 2017/8/1.
 */
/***
 *  信息提示
 * @param message
 * @constructor
 */
function LOG_I(message) {
    $.messager.show({
        title: '提示',
        msg: message,
        timeout: 2000,
        showType: 'fade',
        style: {
            right: '',
            bottom: ''
        }
    });
}
/**
 *  询问对话框
 * @param message
 * @constructor
 */
function LOG_PROBLEM_DIALOG(title, message, callback) {
    $.messager.confirm(title, message, function (r) {
        callback.call(this, r);
    });
}
/**
 *  系统信息
 * @param message
 * @constructor
 */
function LOG_I_R_B(message) {
    $.messager.show({
        title: '系统信息',
        msg: message,
        timeout: 1000,
        showType: 'show'
    });
}
/**
 *  注销登录
 * @constructor
 */
function REMOVE_LOGIN(callback) {
    $.messager.confirm('提示', '该用户已经登录,是否强制登录?', function (r) {
        callback.call(this, r);
    })
}