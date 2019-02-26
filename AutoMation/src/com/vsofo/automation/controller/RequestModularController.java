package com.vsofo.automation.controller;

import com.alibaba.fastjson.JSON;
import com.vsofo.automation.entity.CaseModel;
import com.vsofo.automation.entity.PageItem;
import com.vsofo.automation.entity.RequestModular;
import com.vsofo.automation.entity.ResponseItem;
import com.vsofo.automation.service.RequestModularService;
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
 * @类描述: 模块参数控制器Controller
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/4/25
 * @修改描述:
 */
@Controller
@RequestMapping("/requestModular")
public class RequestModularController {
    @Autowired
    private RequestModularService mRequestModularService;

    /**
     * 分页查询所有模块参数
     *
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectModlarAll")
    @ResponseBody
    public ResponseItem<RequestModular> selectModlarAll(String page, String rows, HttpServletRequest request) throws Exception {
        int modelID = (int) request.getSession().getAttribute("id");
        ResponseItem<RequestModular> response = new ResponseItem<>();
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt(rows));
        String str = StringUtils.appendUrl(new String[]{
                String.valueOf(modelID),
                String.valueOf(pageItem.getIndex()),
                String.valueOf(pageItem.getPageSize())
        });
        List<RequestModular> items = mRequestModularService.selectModlarAll(str);
        long count = mRequestModularService.selectModlarCount(str);
        response.setRows(items);
        response.setTotal(count);
        return response;
    }

    /**
     * 保存模块参数
     *
     * @param message
     * @return
     * @throws Exception
     */
    @RequestMapping("/saveModlarInfo")
    @ResponseBody
    public String saveModlarInfo(String message, HttpServletRequest request) throws Exception {
        RequestModular modular = JSON.parseObject(message, RequestModular.class);
        String[] datas = new String[]{
                StringUtils.isEmpty(modular.getTitle()) ? "" : modular.getTitle(),
                StringUtils.isEmpty(modular.getValue()) ? "" : modular.getValue(),
                String.valueOf(modular.getModelID()),
                String.valueOf(modular.getValueType()),
                String.valueOf(modular.getId())
        };
        String str = StringUtils.appendUrl(datas);
        long count = mRequestModularService.saveModlarInfo(str);
        return String.valueOf(count);
    }

    /**
     * 启用禁用模块参数
     *
     * @param id
     * @param modelID
     * @return
     * @throws Exception
     */
    @RequestMapping("/startCloseModlar")
    @ResponseBody
    public String startCloseModlar(int id, int modelID, HttpServletRequest request) throws Exception {
        String str = StringUtils.appendUrl(new String[]{String.valueOf(modelID), String.valueOf(id)});
        long count = mRequestModularService.startCloseModlar(str);
        return String.valueOf(count);
    }

    /**
     * 通过ID查询模块参数
     *
     * @param id
     * @param modelID
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectModlarById")
    @ResponseBody
    public ResponseItem<RequestModular> selectModlarById(int id, int modelID) throws Exception {
        ResponseItem<RequestModular> response = new ResponseItem<>();
        String str = StringUtils.appendUrl(new String[]{String.valueOf(modelID), String.valueOf(id)});
        RequestModular modular = mRequestModularService.selectModlarById(str);
        response.setObj(modular);
        return response;
    }

    /**
     * 在用例参数里加载模块参数
     *
     * @param page
     * @param rows
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectModelToModular")
    @ResponseBody
    public ResponseItem<RequestModular> selectModelToModular(String page, String rows, HttpServletRequest request) throws Exception {
        CaseModel item = (CaseModel) request.getSession().getAttribute("CaseModel");
        ResponseItem<RequestModular> response = new ResponseItem<>();
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt("10".equals(rows) ? "50" : rows));
        String str = StringUtils.appendUrl(new String[]{
                String.valueOf(item.getModelId()),
                String.valueOf(pageItem.getIndex()),
                String.valueOf(pageItem.getPageSize())
        });
        List<RequestModular> items = mRequestModularService.selectModlarAll(str);
        long count = mRequestModularService.selectModlarCount(str);
        response.setRows(items);
        response.setTotal(count);
        return response;
    }

    /**
     * 统一配置模块参数
     *
     * @param modId
     * @return
     * @throws Exception
     */
    @RequestMapping("/unifiedUpdateModlarInfo")
    @ResponseBody
    public String unifiedUpdateModlarInfo(int modId) throws Exception {
        long count = mRequestModularService.unifiedUpdateModlarInfo(String.valueOf(modId));
        return String.valueOf(count);
    }
}
