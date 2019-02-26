/**
 * Created by vsofo on 2017/4/27.
 */
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
/***
 *  恢复默认值
 * @param value
 * @param row
 * @param index
 */
function operation(value, row, index) {
    return '<a href="###" onclick="recoveryModelVal(' + row.id + ',' + row.modelID + ',' + index + ')" style="color: blue;">恢复</a>';
}
/**
 *  参数值
 * @param val
 * @param row
 * @param index
 * @returns {string}
 */
function opervalue(val, row, index) {
    return '<input id="model_config_val' + index + '" type="text" value="' + row.value + '" style="width: 70%;"/>';
}
/**
 *  加载选中的内容
 */
function loadSelectData(mod, id) {
    var url = projectName + '/example/selectModelConfigByMid.asp';
    $.post(url, {mod: mod, mid: id}, function (result) {
        var rows = result.rows;
        if (rows.length > 0) {
            $('#case_model_config_table').datagrid({
                onLoadSuccess: function (data) {
                    var datas = data.rows;
                    // console.log(rows);
                    // console.log('*************************');
                    // console.log(datas);
                    for (var i in datas) {
                        for (var j in rows) {
                            if (datas[i].id == rows[j].caseModelId) {
                                //将这次的checkbox标记为选中
                                $('#case_model_config_table').datagrid("getPanel")
                                    .find(".datagrid-view2 .datagrid-body table input[type='checkbox']:eq(" + i + ") ")
                                    .attr("checked", true);
                                $('#model_config_val' + rows[j].id).val(rows[j].value);
                                break;
                            }
                        }
                    }
                }
            });
            // $('#case_model_config_table').datagrid({
            //     onLoadSuccess: function (data) {
            //         for (var i = 0; i < rows.length; i++) {
            //             $('#case_model_config_table').datagrid('selectRow', rows[i].id);
            //             //alert(rows[i].id+'   '+rows[i].value);
            //             $('#model_config_val' + rows[i].id).val(rows[i].value);
            //         }
            //     }
            // });
        }
    });
}
/**
 *  保存用例参数
 */
function saveModelConfig() {
    //取得用例配置信息
    var json = $.session.get('config');
    var mObj = jQuery.parseJSON(json);
    //取得选中的个数
    //var rows = $('#case_model_config_table').datagrid("getSelections");
    var rows = $('#case_model_config_table').datagrid("getChecked");
    if (rows.length > 0) {
        var list = new Array(rows.length);
        for (var i = 0; i < rows.length; i++) {
            var val = $('#model_config_val' + i).val();
            rows[i].value = stringUtils(val) ? '' : val;
            list[i] = rows[i];
        }
        var url = projectName + '/example/saveModelConfigInfo.asp';
        $.post(url, {id: mObj.id, message: getJson(list)}, function (result) {
            if (1 == result) {
                $.session.remove('config');
                setTimeout(function () {
                    art.dialog.open.origin.location.reload()
                }, 500);
            }
        });
    } else {
        alert('选择要保存的内容!')
    }
}
/**
 * 恢复参数的值
 */
function recoveryModelVal(id, mid, index) {
    var url = projectName + '/requestModular/selectModlarById.asp';
    $.post(url, {id: id, modelID: mid}, function (result) {
        var obj = result.obj;
        $('#model_config_val' + index).val(obj.value);
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