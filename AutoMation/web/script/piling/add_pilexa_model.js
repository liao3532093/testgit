/**
 * Created by vsofo on 2017/6/7.
 */
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
var url = '';
var modelObj;
var oldIndex = -1;
$(function () {
    isListPermission([], null, 'chanCaseList');
});
$(document).ready(function () {
    loadTable50Line('#add_pilexa_model_table');
    var json = $.session.get('pilexample');
    var obj = jsonToObj(json);
    $('#pilexa_name').text(obj.pilExaName);
    updateBtnStyle('#pilexa_model_add', '#pilexa_model_update');
    initPilModel();
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
            '<a href="###" onclick="updatePilExaModel(' + row.id + ')" style="color: blue;">修改</a>&nbsp;|&nbsp;' +
            '<a href="###" onclick="seePilModelResult(' + row.id + ')" style="color: blue;">预期结果</a>&nbsp;|&nbsp;' +
            '<a href="###" onclick="deletePilExaModel(' + row.id + ')" style="color: red;">删除</a>';
    } else {
        return '<a href="###" onclick="openClose(' + row.id + ')" style="color: blue;">启用</a>&nbsp;|&nbsp;' +
            '<a href="###" onclick="updatePilExaModel(' + row.id + ')" style="color: blue;">修改</a>&nbsp;|&nbsp;' +
            '<a href="###" onclick="seePilModelResult(' + row.id + ')" style="color: blue;">预期结果</a>&nbsp;|&nbsp;' +
            '<a href="###" onclick="deletePilExaModel(' + row.id + ')" style="color: red;">删除</a>';
    }
}
/**
 *  操作
 * @param value
 * @param row
 * @param index
 */
function operations(value, row, index) {
    return '<a id="model_result' + index + '" href="###" onclick="updatePilExaModelVal(' + row.id + ',' + row.resultId + ',' + index + ')" style="color: blue;">修改</a>' +
        '<input type="hidden" id="model_result_val' + index + '" value="0"/>';
}
/**
 *  预期值
 * @param value
 * @param row
 * @param index
 */
function operExaVal(value, row, index) {
    return '<font id="model_exasee' + index + '">' + row.expectValue + '</font>' +
        '<input id="model_exaVal' + index + '" type="text" value="' + row.expectValue + '" style="display: none"/>';
}
/**
 *  预期详情
 * @param value
 * @param row
 * @param index
 */
function operDetails(value, row, index) {
    return '<font id="model_datasee' + index + '">' + row.details + '</font>' +
        '<input id="model_dataVal' + index + '" type="text" value="' + row.details + '" style="display: none"/>';
}
/**
 *  返回/请求参数
 * @param value
 * @param row
 * @param index
 */
function opervalue(value, row, index) {
    return '<span style="word-wrap: break-word;" title="' + row.value + '" class="easyui-tooltip">' + row.value + '</span>';
}
/**
 *  加载渠道模块信息
 */
function initPilModel() {
    url = projectName + '/piling/findPilingModelAll.asp';
    $('#pilmodel_name').html('');
    $('#pilmodel_name').append($('<option value="-1">请选择</option>'));
    $.post(url, {}, function (result) {
        modelObj = result.rows;
        for (var i = 0; i < modelObj.length; i++) {
            $('#pilmodel_name').append($('<option value=' + modelObj[i].id + '>' + modelObj[i].modelName + '</option>'));
        }
    });
}
/**
 *  加载模块下的参数
 */
function loadModelVal() {
    var id = $('#pilmodel_name').val();
    if (-1 == id) {
        $('#pilmodel_val').val('');
        return;
    }
    for (var i = 0; i < modelObj.length; i++) {
        if (id == modelObj[i].id) {
            $('#pilmodel_val').val(modelObj[i].responseItem);
            break;
        }
    }
}
/**
 *  添加渠道模块信息
 */
function insertPilExaModel() {
    $('#pilexamodel_id').val(-1);
    savePilExaModelData(0);
}
/**
 *  修改渠道模块信息
 */
function updatePilExaModels() {
    savePilExaModelData(1);
}
/**
 *  保存信息
 * @param type
 */
function savePilExaModelData(type) {
    var modelId = $('#pilmodel_name').val();
    var modelVal = $('#pilmodel_val').val();
    var order = $('#pilmodel_num').val();
    if (-1 == modelId || stringUtils(modelVal) || 0 >= order || stringUtils(order)) {
        LOG_I('请填写完整信息...');
        return;
    }
    url = projectName + '/pilexample/savePilExampleModel.asp';
    $('#add_pilexa_model_form').ajaxSubmit({
        type: 'POST',
        dataType: 'json',
        url: url,
        success: function (result) {
            if (1 == result || 0 == result) {
                if (1 == type) {
                    updateBtnStyle('#pilexa_model_add', '#pilexa_model_update');
                }
                $('#pilmodel_name').val('-1');
                $('#pilmodel_val').val('');
                $('#pilmodel_num').val('');
                $('#add_pilexa_model_table').datagrid('reload');  //刷新列表
            } else if (-1 == result) {
                LOG_I('该顺序已重复...');
            }
        },
        error: function (error) {
            LOG_I('保存失败');
        },
        async: true
    });
}
/**
 *  禁用和启用
 * @param id
 */
function openClose(id) {
    url = projectName + '/pilexample/updatePilExaModelOpenClose.asp';
    $.post(url, {id: id}, function (result) {
        if (1 == result) {
            $('#add_pilexa_model_table').datagrid('reload');  //刷新列表
        }
    });
}
/**
 *  修改渠道模块信息
 */
function updatePilExaModel(id) {
    url = projectName + '/pilexample/findPilExampleModelById.asp';
    $.post(url, {id: id}, function (result) {
        updateBtnStyle('#pilexa_model_update', '#pilexa_model_add');
        var obj = result.obj;
        $('#pilexamodel_id').val(obj.id);
        $('#pilmodel_name').val(obj.modelId);
        $('#pilmodel_val').val(obj.value);
        $('#pilmodel_num').val(obj.orderId);
    });
}
/**
 *  查看渠道模块下的预期结果
 */
function seePilModelResult(id) {
    url = projectName + '/pilexample/initPilExaModelId.asp';
    $.post(url, {id: id}, function (result) {
        if (1 == result) {
            $('#pilexa_result_dialog').dialog({
                title: '用例预期详情【模拟渠道返回模块】',
                width: 600,
                height: 240,
                closed: false,
                href: projectName + '/main/piling/piling_example_result_dialog.jsp',
                modal: true
            }).dialog('open').window('center');
            oldIndex = -1;
            //loadTable50Line('#pilexa_model_result_table');
        }
    });
}
/**
 *  修改预期结果
 * @param id
 * @param index
 */
function updatePilExaModelVal(id, resultId, index) {
    var btnVal = $('#model_result_val' + index).val();
    if (0 == btnVal) {  //进入修改
        if (oldIndex != index && oldIndex > -1) {
            $('#model_exasee' + oldIndex).show();
            $('#model_exaVal' + oldIndex).hide();
            $('#model_datasee' + oldIndex).show();
            $('#model_dataVal' + oldIndex).hide();
            $('#model_result_val' + oldIndex).val('0');
            $('#model_result' + oldIndex).text('修改');
        }
        oldIndex = index;
        $('#model_exasee' + index).hide();
        $('#model_exaVal' + index).show();
        $('#model_datasee' + oldIndex).hide();
        $('#model_dataVal' + oldIndex).show();
        $('#model_dataVal' + index).focus();
        $('#model_result_val' + index).val('1');
        $('#model_result' + index).text('保存');
    } else {
        var info = $('#model_dataVal' + oldIndex).val();
        var value = $('#model_exaVal' + oldIndex).val();
        url = projectName + '/pilexample/updatePilModelResult.asp';
        $.post(url, {id: id, resultId: resultId, detail: info, resultVal: value}, function (result) {
            if (1 == result) {
                $('#pilexa_model_result_table').datagrid('reload');  //刷新列表
            }
        });
    }
}
/**
 *  删除用例模块信息
 * @param id
 */
function deletePilExaModel(id) {
    LOG_PROBLEM_DIALOG('警告', '是否要删除些模块信息?', function (r) {
        if (r) {
            url = projectName + '/pilexample/deletePilExampleModelById.asp';
            $.post(url, {id: id}, function (result) {
                if (1 == result) {
                    $('#add_pilexa_model_table').datagrid('reload');  //刷新列表
                }
            });
        }
    });
}