/**
 * Created by vsofo on 2017/4/21.
 */
//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
/**
 * 初始化DB配置模块
 */
function init_platform_request() {
    var url = projectName + '/PManage/selectPlatformManageByName.asp';
    init(url, 1, '#plat_name', -1);  //平台信息
    url = projectName + '/PManage/selectInterfaceByName.asp';
    init(url, 1, '#inter_name', -1);  //接口信息
    //改变每页数据条数,例如改为50
    $("#request_table").datagrid('getPager').pagination('refresh', {pageSize: 50});
}
/**
 * 初始化添加DB模块
 */
function init_add_request() {
    $('.request_type_tr').hide();
    var id = $.session.get('id');
    if (id == undefined) {
        var url = projectName + '/PManage/selectPlatformManageByName.asp';
        init(url, 1, '#plat_name', -1);  //平台信息
        url = projectName + '/PManage/selectInterfaceByName.asp';
        init(url, 1, '#inter_name', -1);  //接口信息
    } else {
        var url = projectName + '/PManage/selectPlatformManageByName.asp';
        init(url, 1, '#plat_name', id);  //平台信息
        url = projectName + '/PManage/selectInterfaceByName.asp';
        init(url, 1, '#inter_name', -1);  //接口信息
    }
    $.session.remove('id');
}
/***
 *  平台信息操作
 * @param value
 * @param row
 * @param index
 */
function operation(value, row, index) {
    if (1 == row.isEnable) {
        return '<a href="###" style="color: blue" onclick="openClose(' + row.id + ')">禁用</a>&nbsp;&nbsp;|&nbsp;&nbsp;' +
            '<a href="###" style="color: blue" onclick="update_request(' + row.id + ')">修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;' +
            '<a href="###" style="color: blue" onclick="addRequestModular(' + row.id + ')">参数配置</a>';
    } else {
        return '<a href="###" style="color: blue" onclick="openClose(' + row.id + ')">启用</a>&nbsp;&nbsp;|&nbsp;&nbsp;' +
            '<a href="###" style="color: blue" onclick="update_request(' + row.id + ')">修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;' +
            '<a href="###" style="color: blue" onclick="addRequestModular(' + row.id + ')">参数配置</a>';
    }
}
/***
 *  DB模块操作
 * @param value
 * @param row
 * @param index
 */
function operationDB(value, row, index) {
    if (1 == row.isEnable) {
        return '<a href="###" style="color: blue" onclick="openClose(' + row.id + ')">禁用</a>&nbsp;&nbsp;|&nbsp;&nbsp;' +
            '<a href="###" style="color: blue" onclick="update_request_db(' + row.id + ')">修改</a>';
    } else {
        return '<a href="###" style="color: blue" onclick="openClose(' + row.id + ')">启用</a>&nbsp;&nbsp;|&nbsp;&nbsp;' +
            '<a href="###" style="color: blue" onclick="update_request_db(' + row.id + ')">修改</a>';
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
/**
 *  请求方式
 * @param value
 * @param row
 * @param index
 * @returns {*}
 */
function request_mode(value, row, index) {
    if (1 == row.requestType) {
        return 'POST';
    } else if (2 == row.requestType) {
        return 'GET';
    }
}
/***
 *  参数格式
 * @param value
 * @param row
 * @param index
 */
function parameter_format(value, row, index) {
    if (-1 == row.paramType) {
        return '';
    } else if (1 == row.paramType) {
        return 'XML';
    } else if (2 == row.paramType) {
        return 'JSON';
    }
}
/**
 *  地址/脚本
 * @param value
 * @param row
 * @param index
 */
function operUrl(value, row, index) {
    return '<span style="word-wrap: break-word;" class="easyui-tooltip" title="' + row.requestUrl + '">' + row.requestUrl + '</span>';
}
/***
 *  加载初始化信息
 * @param url
 * @param type
 * @param id
 */
function init(url, type, id, rid) {
    $.post(url, {type: type}, function (result) {
        $(id).html('');
        $(id).append($('<option value="-1">请选择</option>'));
        for (var i = 0; i < result.rows.length; i++) {
            $(id).append('<option value="' + result.rows[i].id + '">' + result.rows[i].title + '</option>');
        }
        if (0 < rid) {
            var urls = projectName + '/PManage/selectPlatRequestById.asp';
            $.post(urls, {id: rid}, function (result) {
                var obj = result.obj;
                $('#plat_name').val(obj.platId);
                $('#inter_name').val(obj.interfaceId);
                $('#request_name').val(obj.title);
                $('#request_id').val(obj.id);
                $('#request_url').val(obj.requestUrl);
                $('#request_type').val(obj.requestType);
                $('#interface_type').val(obj.isEnable);
            });
        }
    });
}
/***
 *  添加请求
 */
function addRequest() {
    $.session.remove('id');
    var url = projectName + '/main/platform/add_request.jsp';
    art.dialog.open(url, {id: "tip", height: 360, width: 500, title: '新增用例模块', lock: true}, true); //打开子窗体
}
/**
 * 添加DB配置模块F
 */
function addRequestDB() {
    $.session.remove('id');
    var url = projectName + '/main/platform/add_request_db.jsp';
    art.dialog.open(url, {id: "tip", height: 500, width: 650, title: '新增DB配置模块', lock: true}, true); //打开子窗体
}
/**
 *  显示和隐藏参数格式
 */
function loadRequestType() {
    var id = $('#request_type').children('option:selected').val();
    if (1 == id) {
        $('.request_type_tr').show();
    } else {
        $('.request_type_tr').hide();
    }
}
/**
 *  保存用例模块
 */
function saveRequestInfo() {
    var url = projectName + '/PManage/savePlatRequestInfo.asp';
    var platid = $('#plat_name').val();
    var interid = $('#inter_name').val();
    var requestName = $('#request_name').val();
    var requestUrl = $('#request_url').val();
    var requestId = $('#request_type').val();
    var interfaceId = $('#interface_type').val();
    var mType = $('#request_mType').val();
    if (-1 == platid || -1 == interid || stringUtils(requestName) || stringUtils(requestUrl)
        || -1 == requestId || -1 == interfaceId || -1 == mType) {
        //alert('不能添加');
        LOG_I('不能添加');
        return;
    }
    if (requestUrl.indexOf('http') == -1) {
        //alert('只能添加http或https开头的地址');
        LOG_I('只能添加http或https开头的地址');
        return;
    }
    $('#request_form').ajaxSubmit({
        type: 'POST',
        dataType: 'json',
        url: url,
        success: function (result) {
            if (1 == result) {
                LOG_I('保存成功');
                setTimeout(function () {
                    art.dialog.open.origin.location.reload()
                }, 500);
            }
        },
        error: function (error) {
            LOG_I('保存失败');
        },
        async: true
    });
}
/**
 *  保存DB模块
 */
function saveRequestInfoDB() {
    var url = projectName + '/PManage/savePlatRequestInfo.asp';
    var platid = $('#plat_name').val();
    var interid = $('#inter_name').val();
    var requestName = $('#request_name').val();
    var requestUrl = $('#request_url').val();
    var interfaceId = $('#interface_type').val();
    if (-1 == platid || -1 == interid || stringUtils(requestName) || stringUtils(requestUrl)
        || -1 == interfaceId) {
        LOG_I('不能添加');
        return;
    }
    if (requestUrl.indexOf('http') == 0) {
        LOG_I('不能添加http或https开头的地址');
        return;
    }
    $('#request_form').ajaxSubmit({
        type: 'POST',
        dataType: 'json',
        url: url,
        success: function (result) {
            if (1 == result) {
                LOG_I('保存成功');
                setTimeout(function () {
                    art.dialog.open.origin.location.reload()
                }, 500);
            }
        },
        error: function (error) {
            LOG_I('保存失败');
        },
        async: true
    });
}
/**
 *  搜索用例
 */
function searchRequest() {
    var platId = $('#plat_name').val();
    var interfaceId = $('#inter_name').val();
    var name = $('#request_title').val();
    $('#request_table').datagrid('load', {'platId': platId, 'interfaceId': interfaceId, 'title': name});
}
/**
 *  启用禁用
 * @param id
 */
function openClose(id) {
    var url = projectName + '/PManage/openClosePlatRequestById.asp';
    $.post(url, {id: id}, function (result) {
        if (1 == result) {
            $('#request_table').datagrid('reload');  //刷新列表
        }
    });
}
/***
 *  修改请求信息
 * @param id
 */
function update_request(id) {
    $.session.set('id', id);
    var url = projectName + '/main/platform/add_request.jsp';
    art.dialog.open(url, {id: "tip", height: 360, width: 500, title: '修改用例模块', lock: true}, true); //打开子窗体
}
/***
 *  修改请求信息
 * @param id
 */
function update_request_db(id) {
    $.session.set('id', id);
    var url = projectName + '/main/platform/add_request_db.jsp';
    art.dialog.open(url, {id: "tip", height: 500, width: 650, title: '修改DB模块', lock: true}, true); //打开子窗体
}
/**
 *  参数配置
 * @param id
 */
function addRequestModular(id) {
    $.session.set('id', id);
    var url = projectName + '/PManage/setModelID.asp';
    $.post(url, {id: id}, function (result) {
        url = projectName + '/main/platform/add_request_modular.jsp';
        art.dialog.open(url, {id: "tip", height: 450, width: 600, title: '模块参数名配置', lock: true}, true); //打开子窗体
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

