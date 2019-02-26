package com.vsofo.automation.controller;

import com.vsofo.automation.entity.*;
import com.vsofo.automation.service.PilExampleService;
import com.vsofo.automation.service.PilingImplService;
import com.vsofo.automation.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/6/8
 * @类描述: 渠道用例详情控制器
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/6/8
 * @修改描述:
 */
@Controller
@RequestMapping("/pilingImpl")
public class PilingImplController {
    @Autowired
    private PilingImplService mPilingImplService;
    @Autowired
    private PilExampleService mExampleService;

    @RequestMapping("/findPilImplementAll")
    @ResponseBody
    public ResponseItem<PilingImplement> findPilImplementAll(String page, String rows, PilingImplement model) throws Exception {
        ResponseItem<PilingImplement> result = new ResponseItem<>();
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt(rows) == 10 ? 50 : Integer.parseInt(rows));
        model.setExaTitle(StringUtils.isEmpty(model.getExaTitle()) ? "" : model.getExaTitle());
        model.setOldTime(StringUtils.isEmpty(model.getOldTime()) ? StringUtils.goToFrontDate(-7, "00", "00", "00") : model.getOldTime());
        model.setNewTime(StringUtils.isEmpty(model.getNewTime()) ? StringUtils.goToInDate("23", "59", "59") : model.getNewTime());
        String[] params = new String[]{
                String.valueOf(model.getExaId()), model.getExaTitle(), String.valueOf(model.getState()),
                model.getOldTime(), model.getNewTime(), String.valueOf(pageItem.getIndex()),
                String.valueOf(pageItem.getPageSize())
        };
        String str = StringUtils.appendUrl(params);
        List<PilingImplement> lists = mPilingImplService.findPilImplementAll(str);
        long total = mPilingImplService.findPilImplementCount(str);
        result.setRows(lists);
        result.setTotal(total);
        return result;
    }

    /**
     * 查询渠道用例执行详情
     *
     * @param exaId
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/findPilExecuteInfo")
    @ResponseBody
    public String findPilExecuteInfo(int id, int exaId, HttpServletRequest request) throws Exception {
        String str = "";
        str = StringUtils.appendUrl(new String[]{String.valueOf(exaId)});
        PilExample model = mExampleService.findPilExampleById(str);
        request.getSession().setAttribute("cName", model.getPilExaName());
        str = StringUtils.appendUrl(new String[]{String.valueOf(id)});
        List<FinalResult> params = mPilingImplService.findPilModelInfoById(str);
        for (FinalResult res : params) {
            //res.setRequestURL(StringUtils.wrapStr(res.getRequestURL(), 60));
            //res.setRequestParam(StringUtils.wrapStr(res.getRequestParam(), 60));
            res.setRequestParam(res.getRequestParam().replace("<", "&lt;"));
            res.setRequestParam(res.getRequestParam().replace(">", "&gt;"));
            //res.setReturnInfo(StringUtils.wrapStr(res.getReturnInfo(), 60));
        }
        request.getSession().setAttribute("params", params);
        List<FinalResult> infos = mPilingImplService.findPilModelInfoByIdResult(str);
        List<FinalResult> datas = mPilingImplService.findPilModelInfoByIdResultBody(str);
        if (infos.size() == datas.size()) {
            for (int i = 0; i < infos.size(); i++) {
                infos.get(i).settResult(datas.get(i).gettResult());
            }
        }
//        for (FinalResult res : infos) {
//            res.settResult(StringUtils.wrapStr(res.gettResult(), 20));
//        }
        request.getSession().setAttribute("infos", infos);
        return "1";
    }
}
