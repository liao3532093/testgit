package com.vsofo.automation.controller;

import com.vsofo.automation.entity.ExecuteItem;
import com.vsofo.automation.entity.PageItem;
import com.vsofo.automation.entity.ResponseItem;
import com.vsofo.automation.entity.UserItem;
import com.vsofo.automation.service.PlatCheckService;
import com.vsofo.automation.utils.SessionUtils;
import com.vsofo.automation.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/4/26
 * @类描述: 校验模块控制器Controller
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/4/26
 * @修改描述:
 */
@Controller
@RequestMapping("/check")
public class PlatCheckController {
    @Autowired
    private PlatCheckService mPlatCheckService;

    /**
     * 分页查询校验模块
     *
     * @param page
     * @param rows
     * @param item
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectPlatCheckAll")
    @ResponseBody
    public ResponseItem<ExecuteItem> selectPlatCheckAll(String page, String rows, ExecuteItem item) throws Exception {
        ResponseItem<ExecuteItem> response = new ResponseItem<>();
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt("10".equals(rows) ? "50" : rows));
        String[] datas = new String[]{
                String.valueOf(item.getPlatID()), String.valueOf(item.getExpectTypeID()), item.getTitle(),
                String.valueOf(pageItem.getIndex()), String.valueOf(pageItem.getPageSize())
        };
        String str = StringUtils.appendUrl(datas);
        System.out.println(str);
        List<ExecuteItem> items = mPlatCheckService.selectPlatCheckAll(str);
        long count = mPlatCheckService.selectPlatCheckCount(str);
        response.setRows(items);
        response.setTotal(count);
        return response;
    }

    /**
     * 保存校验模块
     *
     * @param item
     * @return
     * @throws Exception
     */
    @RequestMapping("/savePlatCheckInfo")
    @ResponseBody
    public String savePlatCheckInfo(ExecuteItem item, HttpServletRequest request) throws Exception {
        UserItem user = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
        String sqlTxt = item.getExecuteSql().toUpperCase();
        if (!StringUtils.isEmpty(sqlTxt) && !sqlTxt.startsWith("SELECT")) {
            return "-1";
        }
        String[] datas = new String[]{
                String.valueOf(item.getPlatID()), String.valueOf(item.getExpectTypeID()),
                StringUtils.isEmpty(item.getTitle()) ? "" : item.getTitle(),
                StringUtils.isEmpty(item.getExecuteSql()) ? "" : item.getExecuteSql(),
                StringUtils.isEmpty(item.getExpectValue()) ? "" : item.getExpectValue(),
                String.valueOf(user.getId()), String.valueOf(item.getId())
        };
        String str = StringUtils.appendUrl(datas);
        long count = mPlatCheckService.savePlatCheckInfo(str);
        return String.valueOf(count);
    }

    /**
     * 启用禁用
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/startClosePlatCheck")
    @ResponseBody
    public String startClosePlatCheck(int id) throws Exception {
        String str = StringUtils.appendUrl(new String[]{String.valueOf(id)});
        long count = mPlatCheckService.startClosePlatCheck(str);
        return String.valueOf(count);
    }

    /**
     * 通过ID查询校验模块
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectPlatCheckById")
    @ResponseBody
    public ResponseItem<ExecuteItem> selectPlatCheckById(int id) throws Exception {
        ResponseItem<ExecuteItem> response = new ResponseItem<>();
        String str = StringUtils.appendUrl(new String[]{String.valueOf(id)});
        ExecuteItem item = mPlatCheckService.selectPlatCheckById(str);
        response.setObj(item);
        return response;
    }

    /**
     * 通过预测类型ID查询校验信息
     *
     * @param predid
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectCheckScriptByPerd")
    @ResponseBody
    public ResponseItem<ExecuteItem> selectCheckScriptByPerd(int predid) throws Exception {
        ResponseItem<ExecuteItem> response = new ResponseItem<>();
        String str = StringUtils.appendUrl(new String[]{String.valueOf(predid)});
        List<ExecuteItem> items = mPlatCheckService.selectCheckScriptByPerd(str);
        response.setRows(items);
        return response;
    }
}
