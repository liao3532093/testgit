/**
 * Created by vsofo on 2017/6/5.
 */
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
function isExit(addId, updateId, tableId, pissUrl) {
    var url = projectName + '/user/isPermission.asp';
    $.post(url, {}, function (result) {
        if (1 == result) {
            $.messager.alert('提示', '你当前用户已失效,请重新登录!', 'warning', function (r) {
                location.href = projectName + '/index.jsp';
            });
        }
    });
    url = projectName + '/userPermission/' + pissUrl + '.asp';
    alert(url);
    $.post(url, {}, function (result) {
        if (1 == result) {
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
}
function isEmpty(obj) {
    if (obj == null) {
        return true;
    } else {
        return false;
    }
}