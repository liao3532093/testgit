package com.vsofo.automation.controller;

import com.vsofo.automation.entity.PageItem;
import com.vsofo.automation.entity.ResponseItem;
import com.vsofo.automation.entity.SystemLog;
import com.vsofo.automation.service.SystemLogService;
import com.vsofo.automation.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/5/10
 * @类描述: 系统日志控制器Controller
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/5/10
 * @修改描述:
 */
@Controller
@RequestMapping("/systemLog")
public class SystemLogController {
    @Autowired
    private SystemLogService mSystemLogService;

    /**
     * 查询日志
     *
     * @param page
     * @param rows
     * @param item
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectSystemLogAll")
    @ResponseBody
    public ResponseItem<SystemLog> selectSystemLogAll(String page, String rows, SystemLog item, HttpServletRequest request) throws Exception {
        String oldDate = (String) request.getSession().getAttribute("oldDate");
        String newDate = (String) request.getSession().getAttribute("newDate");
        if (StringUtils.isEmpty(item.getInDate()) && StringUtils.isEmpty(item.getOutDate())) {
            item.setInDate(oldDate);
            item.setOutDate(newDate);
        }
        ResponseItem<SystemLog> response = new ResponseItem<>();
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt("10".equals(rows) ? "50" : rows));
        String[] datas = new String[]{
                StringUtils.isEmpty(item.getShowName()) ? "" : item.getShowName(),
                StringUtils.isEmpty(item.getInfo()) ? "" : item.getInfo(),
                String.valueOf(item.getLogId()), item.getInDate(), item.getOutDate(),
                String.valueOf(pageItem.getIndex()), String.valueOf(pageItem.getPageSize())
        };
        String str = StringUtils.appendUrl(datas);
        List<SystemLog> items = mSystemLogService.selectSystemLogAll(str);
        long count = mSystemLogService.selectSystemLogCount(str);
        response.setRows(items);
        response.setTotal(count);
        return response;
    }

    /**
     * 设置日期
     *
     * @param oldDate
     * @param newDate
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/setLogDate")
    @ResponseBody
    public String setLogDate(String oldDate, String newDate, HttpServletRequest request) throws Exception {
        request.getSession().setAttribute("oldDate", oldDate);
        request.getSession().setAttribute("newDate", newDate);
        return "1";
    }
}
