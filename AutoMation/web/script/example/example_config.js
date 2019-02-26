/**
 * Created by vsofo on 2017/4/26.
 */
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);

document.write("<script type='text/javascript' src='" + projectName + "/script/utils/pako.js'></script>");

$(function () {
    // init(1, '#plat_name', projectName + '/PManage/selectPlatformManageByName.asp');
    // init(1, '#inter_name', projectName + '/PManage/selectInterfaceByName.asp');
    // initSelectListNoTtile('#exa_state', [-1, 0, 1], ['全部', '禁用', '启用']);
});
function init_exa_config() {
    init(1, '#plat_name', projectName + '/PManage/selectPlatformManageByName.asp');
    init(1, '#inter_name', projectName + '/PManage/selectInterfaceByName.asp');
    initSelectListNoTtile('#exa_state', [-1, 0, 1], ['全部', '禁用', '启用']);
}
/***
 *  操作
 * @param value
 * @param row
 * @param index
 */
function operation(value, row, index) {
    if (1 == row.isEnable) {
        return '<a href="###" onclick="openClose(' + row.id + ')" style="color: blue;">禁用</a>&nbsp;|&nbsp;' +
            '<a href="###" onclick="updateCaseDetail(' + row.id + ')" style="color: blue;">修改</a>&nbsp;|&nbsp;' +
            '<a href="###" onclick="jump_model(' + row.id + ')" style="color: blue;">用例模块配置</a>&nbsp;|&nbsp;' +
            '<a href="###" onclick="exaExecute(' + row.id + ',' + row.isEnable + ')" style="color: blue;">立即执行用例</a>';
    } else {
        return '<a href="###" onclick="openClose(' + row.id + ')" style="color: blue;">启用</a>&nbsp;|&nbsp;' +
            '<a href="###" onclick="updateCaseDetail(' + row.id + ')" style="color: blue;">修改</a>&nbsp;|&nbsp;' +
            '<a href="###" onclick="jump_model(' + row.id + ')" style="color: blue;">用例模块配置</a>&nbsp;|&nbsp;' +
            '<a href="###" onclick="exaExecute(' + row.id + ',' + row.isEnable + ')" style="color: blue;">立即执行用例</a>';
    }
}
/***
 *  状态
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
 *  用例模块操作
 * @param value
 * @param row
 * @param index
 */
function opermodelation(value, row, index) {
    if (row.modelUrl.indexOf('http://') == 0 || row.modelUrl.indexOf('https://') == 0) {
        return '<a href="###" onclick="updateCaseModel(' + row.id + ')" style="color: blue;">修改</a>&nbsp;|&nbsp;' +
            '<a href="###" onclick="loadModelConfig(' + row.id + ')" style="color: blue;">参数配置</a>&nbsp;|&nbsp;' +
            '<a href="###" onclick="expectedResults(' + row.id + ')" style="color: blue;">预期结果配置</a>';
    } else {
        return '<a href="###" onclick="updateCaseModel(' + row.id + ')" style="color: blue;">修改</a>';
    }
}
/**
 *  请求地址|SQL地址
 * @param value
 * @param row
 * @param index
 */
function operUrl(value, row, index) {
    return '<span style="word-wrap: break-word;" class="easyui-tooltip" title="' + row.modelUrl + '">' + row.modelUrl + '</span>';
}
/**
 *  用例标题
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function oper_exa_title(value, row, index) {
    return '<span style="word-wrap: break-word;">' + row.title + '</span>';
}
/**
 *  用例描述
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function oper_exa_sex(value, row, index) {
    return '<span style="word-wrap: break-word;">' + row.detail + '</span>';
}
/**
 *  初始化数据
 * @param type
 * @param selecdid
 * @param url
 */
function init(type, selectid, url) {
    var obj = undefined;
    $(selectid).html('');
    $(selectid).append($('<option value="-1">请选择</option>'));
    $.post(url, {type: type}, function (result) {
        $(selectid).html('');
        $(selectid).append($('<option value="-1">请选择</option>'));
        obj = result.rows;
        for (var i = 0; i < obj.length; i++) {
            $(selectid).append($('<option value=' + obj[i].id + '>' + obj[i].title + '</option>'));
        }
        if ('#inter_name' == selectid) {
            var exa = LayUtils.data.find().exaConfig;
            if (exa != undefined) {
                $('#plat_name').val(exa.platID);
                $('#inter_name').val(exa.interfaceID);
                $('#detail_name').val(exa.title);
                $('#detail_dec').val(exa.detail);
                $('#exa_state').val(exa.isEnable);
                LayUtils.data.del('exaConfig');
                searchCaseDetail(-1);
            }
        }
    });
}
/**
 *
 */
function initAddModel() {
    var json = $.session.get('json');
    json = decodeURIComponent(unzip(json));
    var obj = jQuery.parseJSON(json);
    $('#add_case_plat').text(obj.platName);
    $('#add_case_inter').text(obj.interName);
    $('#add_case_id').val(obj.id);
    var platId = obj.platID;
    $('#add_case_request_name').html('');
    $('#add_case_request_name').append($('<option value="-1">请选择</option>'));
    var url = projectName + '/PManage/selectPlatRequestByNames.asp';
    var model = $.session.get('model');
    if (undefined != model) {
        model = decodeURIComponent(unzip(model));
        var id = $.session.get('id');
        if (undefined != id) {
            $('#add_case_model_id').val(id);
        }
        $.post(url, {platId: platId, type: 0}, function (result) {
            var obj = result.rows;
            for (var i = 0; i < obj.length; i++) {
                $('#add_case_request_name').append($('<option value=' + obj[i].id + '>' + obj[i].title + '</option>'));
            }
            var mObj = jQuery.parseJSON(model);
            $('#add_case_request_name').val(mObj.modelId);
            $('#add_case_model_num').val(mObj.orderNum);
            $('#add_case_model_type').val(mObj.isEnable);
            $.session.remove('model');
        });
    } else {
        $.post(url, {platId: platId, type: 1}, function (result) {
            var obj = result.rows;
            for (var i = 0; i < obj.length; i++) {
                $('#add_case_request_name').append($('<option value=' + obj[i].id + '>' + obj[i].title + '</option>'));
            }
        });
    }
}
/**
 * 保存用例模块
 */
function saveModelInfo() {
    var modelId = $('#add_case_request_name').val();
    var orderNum = $('#add_case_model_num').val();
    var isEnable = $('#add_case_model_type').val();
    if (-1 == modelId || stringUtils(orderNum) || -1 == isEnable) {
        alert('请填写完整信息!');
        return;
    }
    var url = projectName + '/example/saveCaseModelInfo.asp';
    $('#case_model_form').ajaxSubmit({
        type: 'POST',
        dataType: 'json',
        url: url,
        success: function (result) {
            if (1 == result) {
                setTimeout(function () {
                    art.dialog.open.origin.location.reload()
                }, 500);
            } else if (-1 == result) {
                alert('该顺序已存在!');
            }
        },
        error: function (error) {
            alert('保存失败');
        },
        async: true
    });
}
/**
 *  初始化并加载数据
 */
function loadData() {
    var id = $.session.get('id');
    if (undefined != id) {
        var url = projectName + '/example/selectCaseDetailById.asp';
        $.post(url, {id: id}, function (result) {
            var obj = result.obj;
            $('#plat_name').val(obj.platID);
            $('#inter_name').val(obj.interfaceID);
            $('#case_detail_name').val(obj.title);
            $('#case_detail_detail').val(obj.detail);
            $('#case_detail_type').val(obj.isEnable);
            $('#case_detail_id').val(obj.id);
        });
    }
}
/**
 *  添加
 */
function addCaseDetail() {
    $.session.remove('id');
    var url = projectName + '/main/example/add_case_detail.jsp';
    art.dialog.open(url, {id: "tip", height: 300, width: 500, title: '添加用例', lock: true}, true); //打开子窗体
}
/**
 *  修改
 */
function updateCaseDetail(id) {
    $.session.set('id', id);
    var url = projectName + '/main/example/add_case_detail.jsp';
    art.dialog.open(url, {id: "tip", height: 300, width: 500, title: '修改用例', lock: true}, true); //打开子窗体
}
/**
 *  启用禁用配置详情
 * @param id
 */
function openClose(id) {
    var url = projectName + '/example/openCloseCaseDetailById.asp';
    $.post(url, {id: id}, function (result) {
        if (1 == result) {
            $('#case_detail_table').datagrid('reload');  //刷新列表
        }
    });
}
/**
 *  保存
 */
function saveCaseDetail() {
    var platId = $('#plat_name').val();
    var interId = $('#inter_name').val();
    var name = $('#case_detail_name').val();
    var detail = $('#case_detail_detail').val();
    var type = $('#case_detail_type').val();
    if (-1 == platId || -1 == interId || stringUtils(name) || stringUtils(detail) || -1 == type) {
        alert('请填写完整信息!');
        return;
    }
    var url = projectName + '/example/saveCaseDetailInfo.asp';
    $('#case_detail_form').ajaxSubmit({
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
/**
 *  搜索
 */
function searchCaseDetail(type) {
    var platId = $('#plat_name').val();
    var interId = $('#inter_name').val();
    var detailName = $('#detail_name').val();
    var details = $('#detail_dec').val();
    var exa_state = $('#exa_state').val();
    if (type > 0) {
        var obj = {
            'platID': platId,
            'interfaceID': interId,
            'title': detailName,
            'detail': details,
            'isEnable': exa_state
        }
        LayUtils.data.save('exaConfig', obj);
    } else {
        $('#case_detail_table').datagrid('load', {
            'platID': platId,
            'interfaceID': interId,
            'title': detailName,
            'detail': details,
            'isEnable': exa_state
        });
    }
}
/**
 *  跳转到用例模块配置
 */
function jump_model(id) {
    searchCaseDetail(1);
    var url = projectName + '/example/setCaseIdAction.asp';
    $.post(url, {id: id}, function (result) {
        var json = zip(encodeURIComponent(getJson(result.obj)));
        $.session.set('json', json);
        //location.href = projectName + '/main/example/case_model.jsp';
        location.href = projectName + '/jump/goToPage.asp?page=main/example/case_model';
    });
}
/**
 *  添加用例模块
 */
function addCaseModel() {
    $.session.remove('id');
    var url = projectName + '/main/example/add_case_model.jsp';
    art.dialog.open(url, {id: "tip", height: 220, width: 400, title: '添加用例模块', lock: true}, true); //打开子窗体
}
/***
 *  修改用例模块
 * @param id
 */
function updateCaseModel(id) {
    $.session.set('id', id);
    var url = projectName + '/example/findModelById.asp';
    $.post(url, {id: id}, function (result) {
        if (-1 != result) {
            var json = zip(encodeURIComponent(getJson(result.obj)));
            $.session.set('model', json);
        }
        url = projectName + '/main/example/add_case_model.jsp';
        art.dialog.open(url, {id: "tip", height: 220, width: 400, title: '修改用例模块', lock: true}, true); //打开子窗体
    });
}
/**
 * 跳转到用例参数设置
 */
function loadModelConfig(id) {
    var url = projectName + '/example/loadModelConfigInfo.asp';
    $.post(url, {id: id}, function (result) {
        var json = getJson(result.obj);
        $.session.set("config", json);
        url = projectName + '/main/example/case_model_config.jsp';
        art.dialog.open(url, {id: "tip", height: 350, width: 550, title: '用例参数设置', lock: true}, true); //打开子窗体
    });
}
/**
 *  跳转到预期结果配置
 */
function expectedResults(id) {
    var url = projectName + '/example/loadModelConfigInfo.asp';
    $.post(url, {id: id}, function (result) {
        var json = getJson(result.obj);
        $.session.set("config", json);
        location.href = projectName + '/main/example/expected_results.jsp';
    });
}
/**
 *  立即执行
 * @param id
 * @param enable
 */
function exaExecute(id, enable) {
    if (0 == enable) {
        alert('该用例已禁用！');
        return;
    }
    onloading();
    var url = projectName + '/example/exaExecute.asp';
    $.post(url, {id: id}, function (result) {
        removeload();
        alert(result.obj);
    });
}
/**
 *  加载进度条
 */
function onloading() {
    $("<div class=\"datagrid-mask\"></div>").css({
        display: "block",
        width: "100%",
        height: $(window).height()
    }).appendTo("body");
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在执行用例，请稍候。。。").appendTo("body").css({
        display: "block",
        left: ($(document.body).outerWidth(true) - 190) / 2,
        top: ($(window).height() - 45) / 2
    });
}
/**
 *  关闭进度条
 */
function removeload() {
    $(".datagrid-mask").remove();
    $(".datagrid-mask-msg").remove();
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
/**
 *  初始化下拉列表信息
 * @param selectid
 * @param ids
 * @param objs
 */
function initSelectListNoTtile(selectid, ids, objs) {
    $(selectid).html('');
    if (objs.length > 0) {
        $(selectid).html('');
        for (var i = 0; i < objs.length; i++) {
            $(selectid).append($('<option value=' + ids[i] + '>' + objs[i] + '</option>'));
        }
    }
}
/**
 *  压缩
 * @param str
 * @returns {string}
 */
function zip(str) {
    var binaryString = pako.gzip(str, {to: 'string'});
    return btoa(binaryString);
}
function unzip(zip) {
    var strData = atob(zip);
    // Convert binary string to character-number array
    var charData = strData.split('').map(function (x) {
        return x.charCodeAt(0);
    });
    // Turn number array into byte-array
    var binData = new Uint8Array(charData);
    // // unzip
    var data = pako.inflate(binData);
    // Convert gunzipped byteArray back to ascii string:
    strData = String.fromCharCode.apply(null, new Uint16Array(data));
    return strData;
}

