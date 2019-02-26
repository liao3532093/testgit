/**
 * Created by vsofo on 2017/4/25.
 */
//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
var mId = 0;  //全局ID
/***
 *  操作
 * @param value
 * @param row
 * @param index
 */
function operation(value, row, index) {
    if (1 == row.isEnable) {
        return '<a href="###" style="color: blue" onclick="startClose(' + row.id + ')">禁用</a>&nbsp;|&nbsp;' +
            '<a href="###" style="color: blue" onclick="updateModular(' + row.id + ')">修改</a>&nbsp;|&nbsp;' +
            '<a href="###" style="color: blue" onclick="unifiedInfo(' + row.id + ')">统一调整</a>';
    } else {
        return '<a href="###" style="color: blue" onclick="startClose(' + row.id + ')">启用</a>&nbsp;|&nbsp;' +
            '<a href="###" style="color: blue" onclick="updateModular(' + row.id + ')">修改</a>&nbsp;|&nbsp;' +
            '<a href="###" style="color: blue" onclick="unifiedInfo(' + row.id + ')">统一调整</a>';
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
 *  参数值提示
 * @param value
 * @param row
 * @param index
 */
function opervalue(value, row, index) {
    return '<span style="word-wrap: break-word;" class="easyui-tooltip" title="' + row.value + '">' + row.value + '</span>';
}
/**
 * 方法
 * @param value
 * @param row
 * @param index
 */
function opermethod(value, row, index) {
    if (1 == row.valueType) {
        return '否';
    } else {
        return '是';
    }
}
/**
 *  是否为方法类型
 */
function isMethod() {
    if ($('#method_id').is(':checked')) {
        divHide('#method_div', '#parameter_div');
        var url = projectName + '/script/selectScriptMethodByName.asp';
        $.post(url, {page: 1, rows: 500}, function (result) {
            var obj = jQuery.parseJSON(result);
            $('#modular_script').html('');
            $('#modular_script').append($('<option value="-1">请选择</option>'));
            for (var i = 0; i < obj.length; i++) {
                $('#modular_script').append($('<option value=' + obj[i].id + '>' + obj[i].title + '</option>'));
            }
        });
    } else {
        divHide('#parameter_div', '#method_div');
    }
}
/***
 *  保存模块参数
 */
function saveModularInfo() {
    var modelID = $.session.get('id');
    var name = $('#modular_name').val();
    var isMethod = 1;
    var value = '';
    if ($('#method_id').is(':checked')) {  //方法选中
        isMethod = 2;
        value = $('#modular_script_value').val();
    } else {
        isMethod = 1;
        value = $('#modular_value').val();
    }
    if (stringUtils(name)) {
        alert('输入完整信息');
        return;
    }
    var obj = {
        id: mId,
        title: name,
        value: value,
        modelID: modelID,
        valueType: isMethod
    };
    var json = getJson(obj);
    var url = projectName + '/requestModular/saveModlarInfo.asp';
    $.post(url, {message: json}, function (result) {
        if (1 == result) {
            $('#modular_name').val('');
            $('#modular_script_value').val('');
            $('#modular_value').val('');
            $('#method_id').attr("checked", false);
            divHide('#parameter_div', '#method_div');
            updaBtnStyle('#request_modular_install', '#request_modular_update');
            $('#request_modular_table').datagrid('reload');  //刷新列表
        }
    });
}
/**
 *  修改模块参数
 * @param id
 */
function updateModular(id) {
    updaBtnStyle('#request_modular_update', '#request_modular_install');
    var url = projectName + '/requestModular/selectModlarById.asp';
    var modelID = $.session.get('id');
    $.post(url, {id: id, modelID: modelID}, function (result) {
        var obj = result.obj;
        mId = obj.id;  //设置ID
        $('#modular_name').val(obj.title);
        if (1 == obj.valueType) {  //参数类型（1=值类型；2=方法类型）
            divHide('#parameter_div', '#method_div');
            $('#method_id').attr("checked", false);
            $('#modular_value').val(obj.value);
        } else {
            divHide('#method_div', '#parameter_div');
            $('#method_id').attr("checked", true);
            $('#modular_script_value').val(obj.value);
            var url = projectName + '/script/selectScriptMethodByName.asp';
            $.post(url, {page: 1, rows: 500}, function (result) {
                var obj = jQuery.parseJSON(result);
                $('#modular_script').html('');
                $('#modular_script').append($('<option value="-1">请选择</option>'));
                for (var i = 0; i < obj.length; i++) {
                    $('#modular_script').append($('<option value=' + obj[i].id + '>' + obj[i].title + '</option>'));
                }
            });
        }
    });
}
/**
 *  统一调整
 */
function unifiedInfo(id) {
    var url = projectName + '/requestModular/unifiedUpdateModlarInfo.asp';
    $.post(url, {modId: id}, function (result) {
        if (1 == result) {
            alert('统一调整成功!');
        }
    });
}
/**
 *  下拉选择加载方法名
 */
function updateMethod() {
    if (-1 != $('#modular_script').val()) {
        var name = $('#modular_script').find('option:selected').text();
        $('#modular_script_value').val(name + '(...)');
    } else {
        $('#modular_script_value').val('');
    }
}
/***
 *  启用禁用
 */
function startClose(id) {
    var url = projectName + '/requestModular/startCloseModlar.asp';
    var modelID = $.session.get('id');
    $.post(url, {id: id, modelID: modelID}, function (result) {
        $('#request_modular_table').datagrid('reload');  //刷新列表
    });
}
/**
 *  更换按钮样式
 * @param name1
 * @param name2
 */
function updaBtnStyle(name1, name2) {
    $(name1).attr('disabled', false);
    $(name1).css('background', '#2288CC');
    $(name2).attr('disabled', true);
    $(name2).css('background', '#888888');
}
/**
 *  隐藏显示
 * @param name1
 * @param name2
 */
function divHide(name1, name2) {
    $(name1).show();
    $(name2).hide();
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