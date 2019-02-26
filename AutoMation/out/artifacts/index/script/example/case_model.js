/**
 * Created by vsofo on 2017/6/9.
 */
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
var url = '';
$(function () {
    isExit(null, null);
    init(1, '#plat_name', projectName + '/PManage/selectPlatformManageByName.asp');
    init(1, '#inter_name', projectName + '/PManage/selectInterfaceByName.asp');
    loadData();
});
$(document).ready(function () {

});