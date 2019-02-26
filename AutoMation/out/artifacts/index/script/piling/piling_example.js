/**
 * Created by vsofo on 2017/6/5.
 */
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
var url;
$(function () {
    isListPermission(['#execList', '#case_chan_install'], '#piling_exa_table', 'chanCaseList');
});
$(document).ready(function () {
    initData();
});
/**
 *  状态
 * @param value
 * @param row
 * @param index
 */
function operstate(value, row, index) {
    if (1 == row.state) {
        return '<font color="green">启用</font>';
    } else {
        return '<font color="red">禁用</font>';
    }
}
/**
 *  操作
 * @param value
 * @param row
 * @param index
 */
function operation(value, row, index) {
    if (1 == row.state) {
        return '<a href="###" onclick="openClose(' + row.id + ')" style="color: blue;">禁用</a>&nbsp;|&nbsp;' +
            '<a href="###" onclick="updatePilingExa(' + row.id + ')" style="color: blue;">修改</a>&nbsp;|&nbsp;' +
            '<a href="###" onclick="pilModelConfig(' + row.id + ')" style="color: blue;">模块配置</a>&nbsp;|&nbsp;' +
            '<a href="###" onclick="pilexpResult(' + row.id + ',' + row.platId + ',' + row.state + ')" style="color: blue;">立即执行</a>';
    } else {
        return '<a href="###" onclick="openClose(' + row.id + ')" style="color: blue;">启用</a>&nbsp;|&nbsp;' +
            '<a href="###" onclick="updatePilingExa(' + row.id + ')" style="color: blue;">修改</a>&nbsp;|&nbsp;' +
            '<a href="###" onclick="pilModelConfig(' + row.id + ')" style="color: blue;">模块配置</a>&nbsp;|&nbsp;' +
            '<a href="###" onclick="pilexpResult(' + row.id + ',' + row.platId + ',' + row.state + ')" style="color: blue;">立即执行</a>';
    }
}
/*初始化数据*/
function initData() {
    initSelectListNoTtile('#exp_state', [1, 0], ['启用', '禁用']);
    loadTable50Line('#piling_exa_table');
    initSelectData(projectName + '/PManage/selectPlatformManageByName.asp', '#plat_name', 1);
}
/**
 * 初始化下拉列表信息
 * @param url 服务器地址
 * @param selectid 下拉列表id
 * @param type 是否已启用
 */
function initSelectData(url, selectid, type) {
    $(selectid).html('');
    $(selectid).append($('<option value="-1">请选择</option>'));
    $.post(url, {type: type}, function (result) {
        var obj = result.rows;
        for (var i = 0; i < obj.length; i++) {
            $(selectid).append($('<option value=' + obj[i].id + '>' + obj[i].title + '</option>'));
        }
    });
}
/**
 *
 * @param title
 */
function addPilingExa(title) {
    $.session.remove('pilexample');
    goToPilExa(title);
}
/**
 *  添加用例
 * @param title
 */
function goToPilExa(title) {
    url = projectName + '/main/piling/add_piling_example.jsp';
    art.dialog.open(url, {id: "tip", height: 240, width: 450, title: title, lock: true}, true); //打开子窗体
}
/**
 *  搜索
 */
function searchPilExa() {
    var platName = $('#plat_name').val();
    var detailName = $('#detail_name').val();
    var state = $('#exp_state').val();
    $('#piling_exa_table').datagrid('load', {'platId': platName, 'pilExaName': detailName, 'state': state});
}
/**
 *  禁用和启用
 * @param id
 */
function openClose(id) {
    url = projectName + '/pilexample/updateExaOpenClose.asp';
    $.post(url, {id: id}, function (result) {
        if (1 == result) {
            $('#piling_exa_table').datagrid('reload');  //刷新列表
        }
    });
}
/**
 *  修改
 */
function updatePilingExa(id) {
    url = projectName + '/pilexample/findPilExampleById.asp';
    $.post(url, {id: id}, function (result) {
        $.session.set('pilexample', getJson(result.obj));
        goToPilExa('修改渠道用例');
    });
}
/**
 *  跳转到渠道模块配置
 */
function goToPilExaModel(title) {
    url = projectName + '/main/piling/add_pilexa_model.jsp';
    art.dialog.open(url, {id: "tip", height: 600, width: 1000, title: title, lock: true}, true); //打开子窗体
}
/**
 *  渠道模块配置
 * @param id
 */
function pilModelConfig(id) {
    url = projectName + '/pilexample/findPilExampleById.asp';
    $.post(url, {id: id}, function (result) {
        $.session.set('pilexample', getJson(result.obj));
        goToPilExaModel('渠道模块配置');
    });
}
/**
 *  立即执行渠道用例
 * @param id
 * @param state
 */
function pilexpResult(id, platId, state) {
    if (0 == state) {
        LOG_I('该用例已被禁用...');
        return;
    }
    onloading();
    url = projectName + '/pilexample/pilExpExecute.asp';
    $.post(url, {'id': id, 'platId': platId}, function (result) {
        if (1 == result) {
            removeload();
            goToPilExecute('立即执行结果信息');
        }
    });
}
/**
 *  批量执行渠道用例
 */
function batchExetute() {
    var arr = new Array();
    var ps = new Array();
    var rows = $('#piling_exa_table').datagrid('getChecked');
    for (var i = 0; i < rows.length; i++) {
        if (rows[i].state == 1) {
            arr.push(rows[i].id);
            ps.push(rows[i].platId);
        }
    }
    if (arr.length > 0) {
        onloading();
        var ids = arr.join("|");
        var paths = ps.join("|");
        url = projectName + '/pilexample/batchPilExpExecute.asp';
        $.post(url, {'ids': ids, 'paths': paths}, function (result) {
            if (1 == result) {
                removeload();
                goToPilExecute('批量执行结果信息');
            } else {
                LOG_I('服务器出错了!!:' + result);
            }
        });
    } else {
        LOG_I('请选择要执行的用例...');
    }
}
/***
 *  跳转到立即执行结果界面
 * @param title
 */
function goToPilExecute(title) {
    LOG_I('用例执行完成...');
    //url = projectName + '/main/piling/piling_execute_info.jsp';
    //art.dialog.open(url, {id: "tip", height: 600, width: 800, title: title, lock: true}, true); //打开子窗体
}