package com.vsofo.automation.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vsofo.automation.entity.*;
import com.vsofo.automation.service.*;
import com.vsofo.automation.utils.JurisdictionUtils;
import com.vsofo.automation.utils.SessionUtils;
import com.vsofo.automation.utils.StringUtils;
import com.vsofo.automation.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/4/26
 * @类描述: 用例信息控制器
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/4/26
 * @修改描述:
 */
@Controller
@RequestMapping("/example")
public class ExampleController {
    @Autowired
    private ExampleService mExampleService;  //用例详情
    @Autowired
    private CaseModelService mCaseModelService;  //用例模块
    @Autowired
    private ModelConfigService mModelConfigService;  //用例模块参数
    @Autowired
    private ExpectedResultsService mExpectedResultsService; //预测结果
    @Autowired
    private RequestModularService mRequestModularService;  //模块参数
    @Autowired
    private SystemLogService mSystemLogService;

    /**
     * 分页查询用例详情
     *
     * @param page
     * @param rows
     * @param item
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectCaseDetailAll", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseItem<ExampleItem> selectCaseDetailAll(String page, String rows, ExampleItem item) throws Exception {
        ResponseItem<ExampleItem> response = new ResponseItem<>();
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt("10".equals(rows) ? "50" : rows));
        String[] datas = new String[]{
                String.valueOf(item.getPlatID()), String.valueOf(item.getInterfaceID()),
                StringUtils.isEmpty(item.getTitle()) ? "" : item.getTitle(),
                StringUtils.isEmpty(item.getDetail()) ? "" : item.getDetail(),
                item.getIsEnable(),
                String.valueOf(pageItem.getIndex()), String.valueOf(pageItem.getPageSize())
        };
        String str = StringUtils.appendUrl(datas);
        List<ExampleItem> items = mExampleService.selectCaseDetailAll(str);
        long count = mExampleService.selectCaseDetailCount(str);
        response.setRows(items);
        response.setTotal(count);
        return response;
    }

    /**
     * 保存用例详情
     *
     * @param item
     * @return
     * @throws Exception
     */
    @RequestMapping("/saveCaseDetailInfo")
    @ResponseBody
    public String saveCaseDetailInfo(ExampleItem item, HttpServletRequest request) throws Exception {
        ExampleItem old = selectCaseDetailById(item.getId()).getObj();  //获取旧用例信息
        UserItem user = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
        String[] datas = new String[]{
                String.valueOf(item.getPlatID()), String.valueOf(item.getInterfaceID()),
                item.getTitle(), item.getDetail(), item.getIsEnable(), String.valueOf(user.getId()), String.valueOf(item.getId())
        };
        String str = StringUtils.appendUrl(datas);
        long count = mExampleService.saveCaseDetailInfo(str);
        String strs = StringUtils.appendUrl(new String[]{
                String.valueOf(user.getId()),
                JSON.toJSONString(Utils.SaveLog("用例配置管理", user.getShowName(), old, item)),
                JurisdictionUtils.CASECONFIGLISTVIEWLOG, Utils.getIpConfig(request)
        });
        mSystemLogService.saveSystemLogInfo(strs);
        return String.valueOf(count);
    }

    /**
     * 通过ID查询用例详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectCaseDetailById")
    @ResponseBody
    public ResponseItem<ExampleItem> selectCaseDetailById(int id) throws Exception {
        ResponseItem<ExampleItem> response = new ResponseItem<>();
        String str = StringUtils.appendUrl(String.valueOf(id));
        ExampleItem item = mExampleService.selectCaseDetailById(str);
        response.setObj(item);
        return response;
    }

    /**
     * 设置用例详情ID
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/setCaseIdAction")
    @ResponseBody
    public ResponseItem<ExampleItem> setCaseIdAction(int id, HttpServletRequest request) throws Exception {
        ResponseItem<ExampleItem> response = new ResponseItem<>();
        request.getSession().removeAttribute("id");
        request.getSession().setAttribute("id", id);
        String str = StringUtils.appendUrl(String.valueOf(id));
        ExampleItem item = mExampleService.selectCaseDetailById(str);
        response.setObj(item);
        return response;
    }

    /**
     * 启用禁用用例详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/openCloseCaseDetailById")
    @ResponseBody
    public String openCloseCaseDetailById(int id) throws Exception {
        long count = mExampleService.openCloseCaseDetailById(String.valueOf(id));
        return String.valueOf(count);
    }

    /**
     * 分页查询用例模块
     *
     * @param page
     * @param rows
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectCaseModelAll")
    @ResponseBody
    public ResponseItem<CaseModel> selectCaseModelAll(String page, String rows, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        int modelId = (int) session.getAttribute("id");  //加载模块ID
        ResponseItem<CaseModel> response = new ResponseItem<>();
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt("10".equals(rows) ? "50" : rows));
        String str = StringUtils.appendUrl(new String[]{
                String.valueOf(modelId), String.valueOf(pageItem.getIndex()), String.valueOf(pageItem.getPageSize())
        });
        List<CaseModel> items = mCaseModelService.selectCaseModelAll(str);
        if (items.size() > 0) {
            session.setAttribute(SessionUtils.CASEMODELINFO, items);  //保存用例模块信息
        } else {
            session.removeAttribute(SessionUtils.CASEMODELINFO);
        }
        long count = mCaseModelService.selectCaseModelCount(str);
        response.setRows(items);
        response.setTotal(count);
        return response;
    }

    /**
     * 保存用例模块
     *
     * @param item
     * @return
     * @throws Exception
     */
    @RequestMapping("/saveCaseModelInfo")
    @ResponseBody
    public String saveCaseModelInfo(CaseModel item, HttpServletRequest request) throws Exception {
        CaseModel old = findModelById(item.getId(), request).getObj();  //取得旧的用例模块
        UserItem user = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
        String[] datas = new String[]{
                String.valueOf(item.getCaseId()), String.valueOf(item.getModelId()),
                String.valueOf(item.getOrderNum()), item.getIsEnable(), "1", String.valueOf(item.getId())
        };
        String str = StringUtils.appendUrl(datas);
        long count = mCaseModelService.saveCaseModelInfo(str);
        if (1 == count && item.getId() <= 0) {
            long mid = mCaseModelService.selectCaseModelByDescId("");  //取得刚插入的用例模块ID
            List<RequestModular> requestModulars = mRequestModularService.selectModlarByMid(String.valueOf(item.getModelId()));
            for (RequestModular modular : requestModulars) {
                String[] dataList = new String[]{
                        String.valueOf(mid), String.valueOf(modular.getId()), modular.getValue(),
                        String.valueOf(user.getId()), String.valueOf(modular.getValueType())
                };
                str = StringUtils.appendUrl(dataList);
                mModelConfigService.saveModelConfigInfo(str);
            }
        }
        String strs = StringUtils.appendUrl(new String[]{
                String.valueOf(user.getId()),
                JSON.toJSONString(Utils.SaveLog("用例配置管理-用例模块配置", user.getShowName(), old, item)),
                JurisdictionUtils.CASECONFIGLISTVIEWLOG, Utils.getIpConfig(request)
        });
        mSystemLogService.saveSystemLogInfo(strs);
        return String.valueOf(count);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/findModelById")
    @ResponseBody
    public ResponseItem<CaseModel> findModelById(int id, HttpServletRequest request) throws Exception {
        ResponseItem<CaseModel> response = new ResponseItem<>();
        HttpSession session = request.getSession();
        List<CaseModel> items = (List<CaseModel>) session.getAttribute(SessionUtils.CASEMODELINFO);
        if (items != null && items.size() > 0) {
            CaseModel cases = null;
            for (CaseModel it : items) {
                if (it.getId() == id) {
                    cases = it;
                    break;
                }
            }
            response.setObj(cases);
        }
        return response;
    }

    /**
     * 通过ID加载模块信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/loadModelConfigInfo")
    @ResponseBody
    public ResponseItem<CaseModel> loadModelConfigInfo(int id, HttpServletRequest request) throws Exception {
        ResponseItem<CaseModel> response = new ResponseItem<>();
        String str = StringUtils.appendUrl(new String[]{String.valueOf(id)});
        CaseModel item = mCaseModelService.selectCaseModelByExaInfo(str);
        request.getSession().setAttribute("CaseModel", item);
        response.setObj(item);
        return response;
    }

    /**
     * 按用例模块ID查询
     *
     * @param mid
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectModelConfigByMid")
    @ResponseBody
    public ResponseItem<ModelConfig> selectModelConfigByMid(int mod, int mid) throws Exception {
        List<ModelConfig> datas = new ArrayList<>();
        ResponseItem<ModelConfig> response = new ResponseItem<>();
        List<RequestModular> items = mRequestModularService.selectModlarByIds(String.valueOf(mod));
        List<ModelConfig> objs = mModelConfigService.selectModelConfigByMid(String.valueOf(mid));
        for (int i = 0; i < objs.size(); i++) {
            for (int j = 0; j < items.size(); j++) {
                if (objs.get(i).getParamId() == items.get(j).getId()) {
                    //ids.add(j);
                    ModelConfig config = new ModelConfig();
                    config.setId(i);
                    config.setCaseModelId(items.get(j).getId());
                    config.setValue(objs.get(i).getValue());
                    datas.add(config);
                    break;
                }
            }
        }
        response.setRows(datas);
        return response;
    }

    /**
     * 保存用例模块参数
     *
     * @param id
     * @param message
     * @return
     * @throws Exception
     */
    @RequestMapping("/saveModelConfigInfo")
    @ResponseBody
    public String saveModelConfigInfo(int id, String message, HttpServletRequest request) throws Exception {
        UserItem user = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
        mModelConfigService.removeModelConfigById(String.valueOf(id));
        JSONArray array = JSON.parseArray(message);
        for (int i = 0; i < array.size(); i++) {
            JSONObject object = array.getJSONObject(i);
            RequestModular modular = JSON.parseObject(object.toString(), RequestModular.class);
            String[] datas = new String[]{
                    String.valueOf(id), String.valueOf(modular.getId()), modular.getValue(),
                    String.valueOf(user.getId()), String.valueOf(modular.getValueType())
            };
            String str = StringUtils.appendUrl(datas);
            mModelConfigService.saveModelConfigInfo(str);
        }
        return "1";
    }

    /**
     * 分页查询预测结果
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectExpectedResultsAll")
    @ResponseBody
    public ResponseItem<ExpectedResults> selectExpectedResultsAll(String page, String rows, HttpServletRequest request) throws Exception {
        ResponseItem<ExpectedResults> response = new ResponseItem<>();
        HttpSession session = request.getSession();
        CaseModel item = (CaseModel) session.getAttribute("CaseModel");
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt("10".equals(rows) ? "50" : rows));
        List<ExpectedResults> array = new ArrayList<>();
        /*先查询没有脚本的预测结果*/
        String str = StringUtils.appendUrl(new String[]{
                String.valueOf(item.getId()), "-1",
                String.valueOf(pageItem.getIndex()), String.valueOf(pageItem.getPageSize())
        });
        List<ExpectedResults> items = mExpectedResultsService.selectExpectedResultsAll(str);
        array.addAll(items);
        str = StringUtils.appendUrl(new String[]{
                String.valueOf(item.getId()), "1",
                String.valueOf(pageItem.getIndex()), String.valueOf(pageItem.getPageSize())
        });
        List<ExpectedResults> items2 = mExpectedResultsService.selectExpectedResultsAll(str);
        array.addAll(items2);
        Collections.sort(array, new Comparator<ExpectedResults>() {
            @Override
            public int compare(ExpectedResults o1, ExpectedResults o2) {
                return new Integer(o1.getOrderNum()).compareTo(new Integer(o2.getOrderNum()));
            }
        });
        long count = mExpectedResultsService.selectExpectedResultsCount(str);
        response.setRows(array);
        response.setTotal(count);
        return response;
    }

    /**
     * 保存预测结果
     *
     * @param item
     * @return
     * @throws Exception
     */
    @RequestMapping("/saveExpectedResults")
    @ResponseBody
    public String saveExpectedResults(ExpectedResults item, HttpServletRequest request) throws Exception {
        ExpectedResults old = selectExpectedResultsById(item.getId()).getObj();
        UserItem user = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
        String[] datas = new String[]{
                String.valueOf(item.getCaseModelId()), String.valueOf(item.getExpectTypeId()),
                item.getDetail(), String.valueOf(item.getSqlId()), String.valueOf(item.getOrderNum()),
                item.getIsEnable(), String.valueOf(user.getId()), String.valueOf(item.getId())
        };
        String str = StringUtils.appendUrl(datas);
        long count = mExpectedResultsService.saveExpectedResults(str);
        String strs = StringUtils.appendUrl(new String[]{
                String.valueOf(user.getId()),
                JSON.toJSONString(Utils.SaveLog("用例配置管理-用例模块配置-预测结果配置", user.getShowName(), old, item)),
                JurisdictionUtils.CASECONFIGLISTVIEWLOG, Utils.getIpConfig(request)
        });
        mSystemLogService.saveSystemLogInfo(strs);
        return String.valueOf(count);
    }

    /**
     * 启用和禁用预测结果
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/openCloseExpectedResults")
    @ResponseBody
    public String openCloseExpectedResults(int id) throws Exception {
        long count = mExpectedResultsService.openCloseExpectedResults(String.valueOf(id));
        return String.valueOf(count);
    }

    /**
     * 按ID查询预测结果
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectExpectedResultsById")
    @ResponseBody
    public ResponseItem<ExpectedResults> selectExpectedResultsById(int id) throws Exception {
        ResponseItem<ExpectedResults> response = new ResponseItem<>();
        ExpectedResults results = mExpectedResultsService.selectExpectedResultsById(String.valueOf(id));
        response.setObj(results);
        return response;
    }

    /**
     * 立即执行
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/exaExecute")
    @ResponseBody
    public ResponseItem<String> exaExecute(String id) throws Exception {
        return StringUtils.executePython(id, "python /root/Desktop/Automation/MainThread.py -v");
        //        ResponseItem<String> res = new ResponseItem<>();
        //        StringBuilder sb = new StringBuilder();
        //        sb.append("cd /root; ").append("python /root/Desktop/Automation/MainThread.py -v ").append(id);
        //        String[] cmd = {"/bin/sh", "-c", sb.toString()};
        //        Process process = Runtime.getRuntime().exec(cmd);
        //        InputStream is = process.getInputStream();
        //        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        //        sb.setLength(0);
        //        String len = "";
        //        while ((len = br.readLine()) != null) {
        //            sb.append(len).append("\n");
        //        }
        //        process.waitFor();
        //        res.setObj(sb.toString());
        //        return res;
    }
}
