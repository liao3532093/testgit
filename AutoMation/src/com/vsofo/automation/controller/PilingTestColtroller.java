package com.vsofo.automation.controller;

import com.alibaba.fastjson.JSON;
import com.vsofo.automation.entity.*;
import com.vsofo.automation.service.PilingModelService;
import com.vsofo.automation.service.SystemLogService;
import com.vsofo.automation.utils.JurisdictionUtils;
import com.vsofo.automation.utils.SessionUtils;
import com.vsofo.automation.utils.StringUtils;
import com.vsofo.automation.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/6/5
 * @类描述: 渠道模块控制
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/6/5
 * @修改描述:
 */
@Controller
@RequestMapping("/piling")
public class PilingTestColtroller {
    @Autowired
    private PilingModelService modelService;
    @Autowired
    private SystemLogService mSystemLogService;

    /**
     * 保存渠道模块
     *
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/savePilingModelInfo")
    @ResponseBody
    public String savePilingModelInfo(PilingModel model, HttpServletRequest request) throws Exception {
        PilingModel old = selectPilingModelById(model.getId(), 1, request).getObj();
        UserItem user = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
        model.setRequestUrl(StringUtils.isEmpty(model.getRequestUrl()) ? "" : model.getRequestUrl());
        model.setResponseItem(StringUtils.isEmpty(model.getResponseItem()) ? model.getRequestItem() : model.getResponseItem());
        switch (model.getqType()) {
            case 0:
                model.setRequestType("GET");
                break;
            case 1:
                model.setRequestType("POST");
                break;
            default:
                model.setRequestType("");
                break;
        }
        String[] params = new String[]{
                String.valueOf(model.getPlatId()), model.getModelName(), String.valueOf(model.getModelType()),
                model.getRequestUrl(), model.getRequestType(), model.getResponseItem(),
                String.valueOf(model.getState()), String.valueOf(user.getId()), String.valueOf(model.getId())
        };
        String str = StringUtils.appendUrl(params);
        long count = modelService.savePilingModelInfo(str);
        String strs = StringUtils.appendUrl(new String[]{
                String.valueOf(user.getId()),
                JSON.toJSONString(Utils.SaveLog("渠道模块管理", user.getShowName(), old, model)),
                JurisdictionUtils.PILINGLISTVIEWLOG, Utils.getIpConfig(request)
        });
        mSystemLogService.saveSystemLogInfo(strs);
        return String.valueOf(count);
    }

    /**
     * 查询渠道模块
     *
     * @param page
     * @param rows
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectPilingModelAll")
    @ResponseBody
    public ResponseItem<PilingModel> selectPilingModelAll(String page, String rows, PilingModel model) throws Exception {
        ResponseItem<PilingModel> result = new ResponseItem<>();
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt(rows) == 10 ? 50 : Integer.parseInt(rows));
        model.setModelName(StringUtils.isEmpty(model.getModelName()) ? "" : model.getModelName());
        String[] params = new String[]{
                String.valueOf(model.getPlatId()), model.getModelName(), String.valueOf(model.getModelType()),
                String.valueOf(pageItem.getIndex()), String.valueOf(pageItem.getPageSize())
        };
        String str = StringUtils.appendUrl(params);
        List<PilingModel> lists = modelService.selectPilingModelAll(str);
        long count = modelService.selectPilingModelCount(str);
        result.setRows(lists);
        result.setTotal(count);
        return result;
    }

    /**
     * 查询所有启用渠道模块
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/findPilingModelAll")
    @ResponseBody
    public ResponseItem<PilingModel> findPilingModelAll() throws Exception {
        ResponseItem<PilingModel> result = new ResponseItem<>();
        List<PilingModel> lists = modelService.findPilingModelAll("");
        result.setRows(lists);
        return result;
    }

    /**
     * 禁用和启用
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateOpenCloseModel")
    @ResponseBody
    public String updateOpenCloseModel(int id) throws Exception {
        String str = StringUtils.appendUrl(new String[]{String.valueOf(id)});
        long total = modelService.updateOpenClose(str);
        return String.valueOf(total);
    }

    /**
     * 按ID查询
     *
     * @param id
     * @param state
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectPilingModelById")
    @ResponseBody
    public ResponseItem<PilingModel> selectPilingModelById(int id, int state, HttpServletRequest request) throws Exception {
        request.getSession().setAttribute(SessionUtils.PILMODELEXPID, id);  //存放模块ID
        ResponseItem<PilingModel> result = new ResponseItem<>();
        String str = StringUtils.appendUrl(new String[]{String.valueOf(id), String.valueOf(state)});
        PilingModel model = modelService.selectPilingModelById(str);
        result.setObj(model);
        return result;
    }

    /**
     * 保存渠道预期结果
     *
     * @param item
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/savePilModelExpResult")
    @ResponseBody
    public String savePilModelExpResult(PilExpResult item, HttpServletRequest request) throws Exception {
        PilExpResult old = selectPilModelExpResultById(item.getId(), request).getObj();
        int modelId = (int) request.getSession().getAttribute(SessionUtils.PILMODELEXPID);
        UserItem user = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
        String[] params = new String[]{
                item.getDetails(), String.valueOf(item.getNum()), String.valueOf(item.getState()),
                String.valueOf(item.getExpId()), String.valueOf(modelId), String.valueOf(item.getSqlId()),
                String.valueOf(user.getId()), String.valueOf(item.getId())
        };
        String str = StringUtils.appendUrl(params);
        long total = modelService.savePilModelExpResult(str);
        String strs = StringUtils.appendUrl(new String[]{
                String.valueOf(user.getId()),
                JSON.toJSONString(Utils.SaveLog("渠道模块管理-预期结果", user.getShowName(), old, item)),
                JurisdictionUtils.PILINGLISTVIEWLOG, Utils.getIpConfig(request)
        });
        mSystemLogService.saveSystemLogInfo(strs);
        return String.valueOf(total);
    }

    /**
     * 分页查询渠道预期结果
     *
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/findPilModelExpResult")
    @ResponseBody
    public ResponseItem<PilExpResult> findPilModelExpResult(String page, String rows, HttpServletRequest request) throws Exception {
        int modelId = (int) request.getSession().getAttribute(SessionUtils.PILMODELEXPID);
        ResponseItem<PilExpResult> result = new ResponseItem<>();
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt(rows) == 10 ? 50 : Integer.parseInt(rows));
        String[] params = new String[]{
                String.valueOf(modelId), String.valueOf(pageItem.getIndex()), String.valueOf(pageItem.getPageSize())
        };
        String str = StringUtils.appendUrl(params);
        List<PilExpResult> lists = modelService.selectPilModelExpResultAll(str);
        long total = modelService.selectPilModelExpResultCount(str);
        result.setRows(lists);
        result.setTotal(total);
        return result;
    }

    /**
     * 启用和禁用
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateModelOpenClose")
    @ResponseBody
    public String updateModelOpenClose(int id) throws Exception {
        String str = StringUtils.appendUrl(new String[]{String.valueOf(id)});
        long total = modelService.updateModelOpenClose(str);
        return String.valueOf(total);
    }

    /**
     * 按ID查询渠道预期结果
     *
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectPilModelExpResultById")
    @ResponseBody
    public ResponseItem<PilExpResult> selectPilModelExpResultById(int id, HttpServletRequest request) throws Exception {
        int modelId = (int) request.getSession().getAttribute(SessionUtils.PILMODELEXPID);
        ResponseItem<PilExpResult> result = new ResponseItem<>();
        String str = StringUtils.appendUrl(new String[]{String.valueOf(modelId), String.valueOf(id)});
        PilExpResult model = modelService.selectPilModelExpResultById(str);
        result.setObj(model);
        return result;
    }
}
