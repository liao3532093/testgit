/**
 * Created by vsofo on 2017/6/9.
 *  查看权限信息
 */
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
var url;
function isPermission(addId, updateId, tableId, name) {
    url = projectName + '/syspermission/isPermission.asp';
    $.post(url, {name: name}, function (result) {
        if (500 == result) {
            location.href = projectName + '/jump/goToLogin.asp';
        } else if (1 == result) {
            location.href = projectName + '/jump/goTo404.asp';
        } else if (2 == result) {
            if (!isEmpty(addId))
                $(addId).hide();
            if (!isEmpty(updateId))
                $(updateId).hide();
            if (!isEmpty(tableId))
                $(tableId).datagrid('hideColumn', 'operation');
        }
    });
    return;
}
function isListPermission(ids, tableId, name) {
    url = projectName + '/syspermission/isPermission.asp';
    $.post(url, {name: name}, function (result) {
        if (500 == result) {
            location.href = projectName + '/jump/goToLogin.asp';
        } else if (1 == result) {
            location.href = projectName + '/jump/goTo404.asp';
        } else if (2 == result) {
            for (var i = 0; i < ids.length; i++) {
                if (!isEmpty(ids[i]))
                    $(ids[i]).hide();
            }
            if (!isEmpty(tableId))
                $(tableId).datagrid('hideColumn', 'operation');
        }
    });
    return;
}
function isEmpty(obj) {
    if (obj == null) {
        return true;
    } else {
        return false;
    }
}