/**
 * Created by vsofo on 2018/4/26.
 */

var pathName = window.document.location.pathname;
//获取带"/"的项目group by 多个字段名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);

var LayMsg = {
    Notice: 0,  //警告
    Success: 1,  //正确
    Error: 2,  //错误
    Inquiry: 3,  //询问
    Locking: 4,  //锁定
    Sad: 5,  //伤心
    Happy: 6  //开心
}
var LayDate = {
    YEAR: 'year',
    MONTH: 'month',
    DATE: 'date',
    TIME: 'time',
    DATETIME: 'datetime'
}
LayUtils = {
    dialog: {
        openDialog: function (url, title, area, callback) {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.open({
                    title: title,
                    type: 1,
                    area: area,
                    anim: 2,
                    shade: 0.5,
                    closeBtn: 2,
                    resize: false,
                    content: url,
                    success: callback
                })
            })
        },
        closeAll: function () {  //关闭所有层
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.closeAll('dialog'); //关闭信息框
            })
        },
        loadDialog: function (type) {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.load(type, {
                    shade: [0.3, '#000'] //0.1透明度的白色背景
                });
            })
        },
        closeLoad: function () {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.closeAll('loading');
            })
        },
        winDialog: function (url, area) {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.open({
                    type: 2,
                    area: area,
                    content: url
                })
            })
        }
    },
    table: {
        createTable: function (tabid, filter, url, id, rows, toolClick, complete) {  //创建表格
            layui.use('table', function () {
                var table = layui.table;
                table.render({
                    elem: tabid,
                    url: url,
                    page: true,
                    limit: 50,
                    limits: [50, 100, 200, 500],
                    id: id,
                    cols: [rows],
                    done: complete
                });
                //监听工具条
                table.on('tool(' + filter + ')', toolClick);
            })
        },
        reloadTable: function (id, params) {  //刷新数据
            layui.use('table', function () {
                var table = layui.table;
                table.reload(id, {where: params});
            })
        },
        reloadUrlTable: function (id, url, params) {  //刷新数据
            layui.use('table', function () {
                var table = layui.table;
                table.reload(id, {url: url, where: params});
            })
        },
        createHeightTable: function (tabid, filter, url, id, height, rows, toolClick, complete) {  //创建规定高度的表格
            layui.use('table', function () {
                var table = layui.table;
                table.render({
                    elem: tabid,
                    url: url,
                    page: true,
                    limit: 50,
                    limits: [50, 100, 200, 500],
                    id: id,
                    height: 'full-' + height,
                    cols: [rows],
                    done: complete,
                    request: {
                        limitName: 'rows'
                    },
                    response: {
                        dataName: 'rows'
                    }
                });
                //监听工具条
                table.on('tool(' + filter + ')', toolClick);
            })
        },
        /**
         * 创建表格
         * @param tabid 表格ID
         * @param filter 表格filter
         * @param url
         * @param id 表格自定义ID
         * @param height
         * @param rows 表格列
         * @param request 返回参数
         * @param response
         * @param toolClick 工具监听
         * @param complete 表格加载完成监听
         */
        createHeightTable: function (tabid, filter, url, id, height, rows, request, response, toolClick, complete) {  //创建规定高度的表格
            layui.use('table', function () {
                var table = layui.table;
                table.render({
                    elem: tabid,
                    url: url,
                    page: true,
                    limit: 50,
                    limits: [50, 100, 200, 500],
                    id: id,
                    height: 'full-' + height,
                    cols: [rows],
                    done: complete,
                    request: request,
                    response: response
                });
                //监听工具条
                table.on('tool(' + filter + ')', toolClick);
            })
        },
        /**
         * 创建表格
         * @param tabid 表格ID
         * @param filter 表格filter
         * @param url
         * @param id 表格自定义ID
         * @param toolbar 头部信息
         * @param height
         * @param rows 表格列
         * @param request 返回参数
         * @param response
         * @param toolClick 工具监听
         * @param complete 表格加载完成监听
         */
        createToolbarTable: function (tabid, filter, url, id, toolbar, height, rows, request, response, toolClick, complete) {  //创建规定高度的表格
            layui.use('table', function () {
                var table = layui.table;
                table.render({
                    elem: tabid,
                    url: url,
                    toolbar: toolbar,
                    page: true,
                    limit: 50,
                    limits: [50, 100, 200, 500],
                    id: id,
                    height: 'full-' + height,
                    cols: [rows],
                    done: complete,
                    request: request,
                    response: response
                });
                //监听工具条
                table.on('tool(' + filter + ')', toolClick);
            })
        },
        createRowTable: function (tabid, filter, url, id, height, rows, request, response, toolClick, complete) {  //创建规定高度的表格
            layui.use('table', function () {
                var table = layui.table;
                table.render({
                    elem: tabid,
                    url: url,
                    totalRow: true,
                    page: true,
                    limit: 50,
                    limits: [50, 100, 200, 500],
                    id: id,
                    height: 'full-' + height,
                    cols: [rows],
                    done: complete,
                    request: request,
                    response: response
                });
                //监听工具条
                table.on('tool(' + filter + ')', toolClick);
            })
        },
        switchTag: function (filter, success) {
            layui.use('table', function () {
                var form = layui.form;
                form.on('switch(' + filter + ')', success);
            })
        },
        editTag: function (filter, success) {
            layui.use('table', function () {
                var table = layui.table;
                table.on('edit(' + filter + ')', success);
            })
        }
    },
    msg: function (body, index) {  //弹出提示框
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.msg(body, {icon: index});
        })
    },
    noticefirm: function (title, body, btns, call1) {  //警告提示框
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.confirm(body, {icon: 3, btn: btns, anim: 6, title: title}, call1);
        })
    },
    closeNotice: function () {
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.closeAll('dialog'); //关闭信息框
        })
    },
    date: {
        defaultDate: function (id, type) {
            layui.use('laydate', function () {
                var laydate = layui.laydate;
                //执行一个laydate实例
                laydate.render({
                    elem: id, //指定元素
                    type: type,
                    btns: ['now', 'confirm']
                });
            })
        },
        isInitDate: function (id, type, defDate) {
            layui.use('laydate', function () {
                var laydate = layui.laydate;
                //执行一个laydate实例
                laydate.render({
                    elem: id, //指定元素
                    type: type,
                    btns: ['now', 'confirm'],
                    value: defDate
                });
            })
        }
    },
    data: {
        save: function (key, val) {
            layui.data('liao', {
                key: key,
                value: val
            })
        },
        del: function (key) {
            layui.data('liao', {
                key: key,
                remove: true
            })
        },
        find: function () {
            return layui.data('liao');
        },
        close: function () {
            layui.data('liao', null)
        }
    },
    TextArea: {
        /**
         * TODO:绑定编辑器
         * @param id
         * @param success
         */
        initTextArea: function (id, success) {
            layui.use('layedit', function () {
                var layedit = layui.layedit;
                var index = layedit.build(id); //建立编辑器
                success(index);
            });
        },
        /**
         * TODO:获取编辑器内容
         * @param index
         * @param success
         */
        getHtml: function (index, success) {
            layui.use('layedit', function () {
                var layedit = layui.layedit;
                var content = layedit.getContent(index);
                success(content);
            });
        }
    }
}