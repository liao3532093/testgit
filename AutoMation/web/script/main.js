/**
 * Created by Administrator on 2017/4/18.
 */
//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
$(function () {
    // $.post(projectName + '/jump/sessionId.asp', {}, function (sessionid) {
    //     readMessage(sessionid, function (r) {
    //         alert(r);
    //         var time = window.setInterval(function () {
    //             if (time != null) {
    //                 window.clearInterval(time);
    //                 location.href = projectName + '/jump/goToLogin.asp';
    //             }
    //         }, 1000);
    //     });
    // });
});
/**
 *  退出系统
 */
function exit() {
    var url = projectName + '/user/exitSystem.asp';
    $.post(url, {}, function (result) {
        if (1 == result) {
            location.href = projectName + '/index.jsp';
        }
    });
}
/**
 *  修改个人资料
 */
function update_user_pwd() {
    var url = url = projectName + '/main/system/update_user_info.jsp';
    art.dialog.open(url, {id: "tip", height: 240, width: 400, title: '修改个人资料', lock: true}, true); //打开子窗体
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