package com.vsofo.automation.controller;

import com.vsofo.automation.entity.PageItem;
import com.vsofo.automation.entity.PredictionItem;
import com.vsofo.automation.entity.ResponseItem;
import com.vsofo.automation.entity.UserItem;
import com.vsofo.automation.service.PredictionService;
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
 * @创建时间: 2017/4/25
 * @类描述: 预测类型控制器Controller
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/4/25
 * @修改描述:
 */
@Controller
@RequestMapping("/prediction")
public class PredictionController {
    @Autowired
    private PredictionService mPredictionService;

    /**
     * 保存预测类型
     *
     * @param item
     * @return
     * @throws Exception
     */
    @RequestMapping("/savePredictionInfo")
    @ResponseBody
    public String savePredictionInfo(PredictionItem item, HttpServletRequest request) throws Exception {
        UserItem user = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
        String str = StringUtils.appendUrl(new String[]{item.getTitle(), String.valueOf(user.getId()), String.valueOf(item.getId())});
        long count = mPredictionService.savePredictionInfo(str);
        return String.valueOf(count);
    }

    /**
     * 分页查询预测类型
     *
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectPredictionAll")
    @ResponseBody
    public ResponseItem<PredictionItem> selectPredictionAll(String page, String rows) throws Exception {
        ResponseItem<PredictionItem> response = new ResponseItem<>();
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt("10".equals(rows) ? "50" : rows));
        String str = StringUtils.appendUrl(new String[]{String.valueOf(pageItem.getIndex()), String.valueOf(pageItem.getPageSize())});
        List<PredictionItem> items = mPredictionService.selectPredictionAll(str);
        System.out.println(items);
        long count = mPredictionService.selectPredictionCount(str);
        response.setRows(items);
        response.setTotal(count);
        return response;
    }

    /**
     * 按ID查询预测类型
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectPredictionById")
    @ResponseBody
    public ResponseItem<PredictionItem> selectPredictionById(int id) throws Exception {
        ResponseItem<PredictionItem> response = new ResponseItem<>();
        String str = StringUtils.appendUrl(String.valueOf(id));
        PredictionItem item = mPredictionService.selectPredictionById(str);
        response.setObj(item);
        return response;
    }

    /**
     * 启用或禁用
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/openPredictionById")
    @ResponseBody
    public String openPredictionById(int id) throws Exception {
        String str = StringUtils.appendUrl(String.valueOf(id));
        long count = mPredictionService.openPredictionById(str);
        return String.valueOf(count);
    }

    /**
     * 查询预测类型的ID和名称
     *
     * @param type
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectPredIsEnableByName")
    @ResponseBody
    public ResponseItem<PredictionItem> selectPredIsEnableByName(int type) throws Exception {
        ResponseItem<PredictionItem> response = new ResponseItem<>();
        String str = StringUtils.appendUrl(new String[]{String.valueOf(type)});
        List<PredictionItem> items = mPredictionService.selectPredIsEnableByName(str);
        response.setRows(items);
        return response;
    }
}
