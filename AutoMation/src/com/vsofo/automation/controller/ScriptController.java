package com.vsofo.automation.controller;

import com.alibaba.fastjson.JSON;
import com.vsofo.automation.entity.PageItem;
import com.vsofo.automation.entity.ResponseItem;
import com.vsofo.automation.entity.ScriptItem;
import com.vsofo.automation.entity.UserItem;
import com.vsofo.automation.service.ScriptService;
import com.vsofo.automation.utils.SessionUtils;
import com.vsofo.automation.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/4/25
 * @类描述: 脚本方法控制器Controller
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/4/25
 * @修改描述:
 */
@Controller
@RequestMapping("/script")
public class ScriptController {
    @Autowired
    private ScriptService mScriptService;

    /***
     * 分页查询脚本方法
     *
     * @param page
     * @param rows
     * @param item
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectScriptMethodAll")
    @ResponseBody
    public ResponseItem<ScriptItem> selectScriptMethodAll(String page, String rows, ScriptItem item) throws Exception {
        ResponseItem<ScriptItem> response = new ResponseItem<>();
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt("10".equals(rows) ? "50" : rows));
        String str = StringUtils.appendUrl(new String[]{
                StringUtils.isEmpty(item.getTitle()) ? "" : item.getTitle(),
                StringUtils.isEmpty(item.getDetail()) ? "" : item.getDetail(),
                String.valueOf(pageItem.getIndex()),
                String.valueOf(pageItem.getPageSize())});
        List<ScriptItem> items = mScriptService.selectScriptMethodAll(str);
        long count = mScriptService.selectScriptMethodCount(str);
        response.setRows(items);
        response.setTotal(count);
        return response;
    }

    /**
     * 保存脚本方法
     *
     * @param item
     * @return
     * @throws Exception
     */
    @RequestMapping("/saveScriptMethod")
    @ResponseBody
    public String saveScriptMethod(ScriptItem item, HttpServletRequest request) throws Exception {
        UserItem user = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
        String[] datas = new String[]{
                item.getTitle(), item.getDetail(), item.getIsEnable(), String.valueOf(user.getId()), String.valueOf(item.getId())
        };
        String str = StringUtils.appendUrl(datas);
        long count = mScriptService.saveScriptMethod(str);
        return String.valueOf(count);
    }

    /**
     * 按ID查询脚本方法
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectScriptMethodById")
    @ResponseBody
    public ResponseItem<ScriptItem> selectScriptMethodById(int id) throws Exception {
        ResponseItem<ScriptItem> response = new ResponseItem<>();
        String str = StringUtils.appendUrl(String.valueOf(id));
        ScriptItem item = mScriptService.selectScriptMethodById(str);
        response.setObj(item);
        return response;
    }

    /**
     * 查询所有脚本方法和ID
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectScriptMethodByName")
    @ResponseBody
    public String selectScriptMethodByName() throws Exception {
        List<ScriptItem> items = mScriptService.selectScriptMethodByName("");
        return JSON.toJSONString(items);
    }

    /**
     * 启用禁用
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/openCloseScriptMethodById")
    @ResponseBody
    public String openCloseScriptMethodById(int id) throws Exception {
        long count = mScriptService.openCloseScriptMethodById(String.valueOf(id));
        return String.valueOf(count);
    }
}
