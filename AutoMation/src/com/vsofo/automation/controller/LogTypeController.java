package com.vsofo.automation.controller;

import com.vsofo.automation.entity.LogType;
import com.vsofo.automation.entity.PageItem;
import com.vsofo.automation.entity.ResponseItem;
import com.vsofo.automation.service.LogTypeService;
import com.vsofo.automation.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/5/9
 * @类描述: 日志类型控制器Controller
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/5/9
 * @修改描述:
 */
@Controller
@RequestMapping("/logtype")
public class LogTypeController {
    @Autowired
    private LogTypeService mLogTypeService;

    /**
     * 保存日志类型信息
     *
     * @param item
     * @return
     * @throws Exception
     */
    @RequestMapping("/saveLogTypeInfo")
    @ResponseBody
    public String saveLogTypeInfo(LogType item) throws Exception {
        String str = StringUtils.appendUrl(new String[]{
                item.getLogKey(), item.getTitle(), String.valueOf(item.getId())
        });
        long count = mLogTypeService.saveLogTypeInfo(str);
        return String.valueOf(count);
    }

    /**
     * 分页查询日志类型
     *
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectLogTypeAll")
    @ResponseBody
    public ResponseItem<LogType> selectLogTypeAll(String page, String rows) throws Exception {
        ResponseItem<LogType> response = new ResponseItem<>();
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt("10".equals(rows) ? "50" : rows));
        String str = StringUtils.appendUrl(new String[]{
                String.valueOf(pageItem.getIndex()), String.valueOf(pageItem.getPageSize())
        });
        List<LogType> items = mLogTypeService.selectLogTypeAll(str);
        long count = mLogTypeService.selectLogTypeCount(str);
        response.setRows(items);
        response.setTotal(count);
        return response;
    }

    /**
     * 按ID查询日志类型
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectLogTypeById")
    @ResponseBody
    public ResponseItem<LogType> selectLogTypeById(int id) throws Exception {
        ResponseItem<LogType> response = new ResponseItem<>();
        LogType logType = mLogTypeService.selectLogTypeById(String.valueOf(id));
        response.setObj(logType);
        return response;
    }

    /**
     * 删除日志类型
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteLogTypeById")
    @ResponseBody
    public String deleteLogTypeById(int id) throws Exception {
        long count = mLogTypeService.deleteLogTypeById(String.valueOf(id));
        return String.valueOf(count);
    }
}
