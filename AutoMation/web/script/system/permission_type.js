/**
 * Created by vsofo on 2017/5/5.
 */
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
$(document).ready(function () {
    //改变每页数据条数,例如改为50
    $("#permission_table").datagrid('getPager').pagination('refresh', {pageSize: 50});
});
/**
 *  状态
 * @param value
 * @param row
 * @param index
 */
function operisLock(value, row, index) {
    if (1 == row.isLock) {
        return '<font color="red">锁定</font>';
    } else {
        return '<font color="green">正常</font>';
    }
}
/**
 *  操作
 * @param value
 * @param row
 * @param index
 */
function operation(value, row, index) {
    return '<a href="###" onclick="update_per_type(' + row.id + ')">修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;' +
        '<a href="###" onclick="loadPowerParent(' + row.id + ')">权限项</a>';
}
/**
 *  操作
 * @param value
 * @param row
 * @param index
 */
function operation_power_parent(value, row, index) {
    return '<a href="###" onclick="updatePowerParent(' + row.id + ')">修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;' +
        '<a href="###" onclick="loadPowerItem(' + row.id + ',' + row.powerClassId + ')">权限项</a>';
}
/**
 *  操作
 * @param value
 * @param row
 * @param index
 */
function operation_power_item(value, row, index) {
    return '<a href="###" onclick="updatePowerItem(' + row.id + ')">修改</a>';
}
/**
 *  添加
 */
function addPowerClass() {
    var name = $('#permission_name').val();
    var orderNo = $('#permission_code').val();
    if (stringUtils(name) || stringUtils(orderNo)) {
        alert('请输入完整内容!');
        return;
    }
    var url = projectName + '/permission/savePowerClassAll.asp';
    $('#permission_form').ajaxSubmit({
        type: 'POST',
        dataType: 'json',
        url: url,
        success: function (result) {
            $('#permission_install').val('添加')
            $('#permission_name').val('');
            $('#permission_code').val('');
            $('#checkbox').attr('checked', false);
            $('#permission_id').val('-1');
            if (1 == result) {
                $('#permission_table').datagrid('reload');  //刷新列表
            } else if (-1 == result) {
                alert('该顺序存在!');
            }
        },
        error: function (error) {
            alert('保存失败');
        },
        async: true
    });
}
/**
 *  设置大类别ID
 */
function initpClassId(id) {
    $('#pClassId').val(id);
    $('#power_parent_table').datagrid('load', {'powerClassId': id});
}
/**
 *  保存小类别
 */
function savePowerParent() {
    var name = $('#power_parent_name').val();
    var orderNo = $('#power_parent_code').val();
    if (stringUtils(name) || stringUtils(orderNo)) {
        alert('请输入完整内容!');
        return;
    }
    var url = projectName + '/permission/savePowerParentInfo.asp';
    $('#power_parent_form').ajaxSubmit({
        type: 'POST',
        dataType: 'json',
        url: url,
        success: function (result) {
            $('#power_parent_install').val('添加')
            $('#power_parent_name').val('');
            $('#power_parent_code').val('');
            $('#checkbox').attr('checked', false);
            $('#power_parent_id').val('-1');
            if (1 == result) {
                $('#power_parent_table').datagrid('reload');  //刷新列表
            } else if (-1 == result) {
                alert('该顺序存在!');
            }
        },
        error: function (error) {
            alert('保存失败');
        },
        async: true
    });
}
/**
 *  修改
 * @param id
 */
function update_per_type(id) {
    var url = projectName + '/permission/selectPowerClassById.asp';
    $.post(url, {id: id}, function (result) {
        var obj = result.obj;
        $('#permission_name').val(obj.title);
        $('#permission_code').val(obj.orderNo);
        if (0 == obj.isLock) {
            $('#checkbox').attr('checked', false);
        } else {
            $('#checkbox').attr('checked', true);
        }
        $('#permission_id').val(obj.id);
        $('#permission_install').val('修改')
    });
}
/**
 *  修改小类别权限
 * @param id
 */
function updatePowerParent(id) {
    var url = projectName + '/permission/selectPowerParentById.asp';
    $.post(url, {id: id}, function (result) {
        var obj = result.obj;
        $('#power_parent_name').val(obj.title);
        $('#power_parent_code').val(obj.orderNo);
        if (0 == obj.isLock) {
            $('#checkbox').attr('checked', false);
        } else {
            $('#checkbox').attr('checked', true);
        }
        $('#power_parent_id').val(obj.id);
        $('#power_parent_install').val('修改')
    });
}
/**
 *  设置小类别
 * @param id
 */
function initParentId(id) {
    $('#parentId').val(id);
    $('#power_item_table').datagrid('load', {'parentClassId': id});
}
/**
 *  保存关键字
 */
function savePowerItem() {
    var name = $('#power_item_name').val();
    var key = $('#power_item_key').val();
    if (stringUtils(name) || stringUtils(key)) {
        alert('请输入完整内容!');
        return;
    }
    var url = projectName + '/permission/savePowerItemInfo.asp';
    $('#power_item_form').ajaxSubmit({
        type: 'POST',
        dataType: 'json',
        url: url,
        success: function (result) {
            $('#power_item_install').val('添加')
            $('#power_item_key').val('');
            $('#power_item_name').val('');
            $('#checkbox').attr('checked', false);
            $('#power_item_id').val('-1');
            if (1 == result) {
                $('#power_item_table').datagrid('reload');  //刷新列表
            } else if (-1 == result) {
                alert('该关键字已存在!');
            }
        },
        error: function (error) {
            alert('保存失败');
        },
        async: true
    });
}
/**
 *  修改关键字
 * @param id
 */
function updatePowerItem(id) {
    var url = projectName + '/permission/selectPowerItemById.asp';
    $.post(url, {id: id}, function (result) {
        var obj = result.obj;
        $('#power_item_name').val(obj.title);
        $('#power_item_key').val(obj.keywords);
        $('#power_item_id').val(obj.id);
        $('#power_item_install').val('修改')
    });
}
/**
 *  加载小类别权限
 * @param id
 */
function loadPowerParent(id) {
    var url = projectName + '/permission/selectOpenPowerClass.asp';
    $.post(url, {id: id}, function (result) {
        location.href = projectName + '/main/system/power_parent_type.jsp';
    });
}
/**
 *  加载权限关键字
 * @param id
 */
function loadPowerItem(oid, id) {
    var url = projectName + '/permission/selectOpenPowerParent.asp';
    $.post(url, {id: id, oid: oid}, function (result) {
        location.href = projectName + '/main/system/power_item_type.jsp';
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

