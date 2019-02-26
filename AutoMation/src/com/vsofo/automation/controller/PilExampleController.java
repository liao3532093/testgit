package com.vsofo.automation.controller;

import com.alibaba.fastjson.JSON;
import com.vsofo.automation.entity.*;
import com.vsofo.automation.service.PilExampleService;
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
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/6/6
 * @类描述: 渠道用例控制器
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/6/6
 * @修改描述:
 */
@Controller
@RequestMapping("/pilexample")
public class PilExampleController {
    @Autowired
    private PilExampleService mExampleService;
    @Autowired
    private PilingModelService mModelService;
    @Autowired
    private PlatformController mPlatform;
    @Autowired
    private SystemLogService mSystemLogService;

    /**
     * 保存渠道用例
     *
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/savePilExample")
    @ResponseBody
    public String savePilExample(PilExample model, HttpServletRequest request) throws Exception {
        PilExample old = findPilExampleById(model.getId(), request).getObj();  //查询渠道用例
        UserItem user = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
        String[] params = new String[]{
                String.valueOf(model.getPlatId()), model.getPilExaName(),
                String.valueOf(model.getState()), String.valueOf(user.getId()), String.valueOf(model.getId())
        };
        String str = StringUtils.appendUrl(params);
        long total = mExampleService.savePilExample(str);
        String strs = StringUtils.appendUrl(new String[]{
                String.valueOf(user.getId()),
                JSON.toJSONString(Utils.SaveLog("渠道用例列表", user.getShowName(), old, model)),
                JurisdictionUtils.PILINGLISTVIEWLOG, Utils.getIpConfig(request)
        });
        mSystemLogService.saveSystemLogInfo(strs);
        return String.valueOf(total);
    }

    /**
     * 分页查询渠道用例
     *
     * @param page
     * @param rows
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/findPilExampleAll")
    @ResponseBody
    public ResponseItem<PilExample> findPilExampleAll(String page, String rows, PilExample model) throws Exception {
        System.out.println(model);
        ResponseItem<PilExample> result = new ResponseItem<>();
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt(rows) == 10 ? 50 : Integer.parseInt(rows));
        model.setPilExaName(StringUtils.isEmpty(model.getPilExaName()) ? "" : model.getPilExaName());
        String[] params = new String[]{
                String.valueOf(model.getPlatId()), model.getPilExaName(), String.valueOf(model.getState()),
                String.valueOf(pageItem.getIndex()), String.valueOf(pageItem.getPageSize())
        };
        String str = StringUtils.appendUrl(params);
        List<PilExample> lists = mExampleService.findPilExampleAll(str);
        long total = mExampleService.findPilExampleCount(str);
        result.setRows(lists);
        result.setTotal(total);
        return result;
    }

    /**
     * 禁用和启用
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateExaOpenClose")
    @ResponseBody
    public String updateExaOpenClose(int id) throws Exception {
        String str = StringUtils.appendUrl(new String[]{String.valueOf(id)});
        long total = mExampleService.updateExaOpenClose(str);
        return String.valueOf(total);
    }

    /**
     * 按ID查询渠道用例
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/findPilExampleById")
    @ResponseBody
    public ResponseItem<PilExample> findPilExampleById(int id, HttpServletRequest request) throws Exception {
        request.getSession().setAttribute(SessionUtils.PILEXAID, id);
        ResponseItem<PilExample> result = new ResponseItem<>();
        String str = StringUtils.appendUrl(new String[]{String.valueOf(id)});
        PilExample model = mExampleService.findPilExampleById(str);
        result.setObj(model);
        return result;
    }

    /**
     * 保存渠道用例模块
     *
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/savePilExampleModel")
    @ResponseBody
    public String savePilExampleModel(PilExaModel model, HttpServletRequest request) throws Exception {
        PilExaModel old = findPilExampleModelById(model.getId(), request).getObj();
        int expId = (int) request.getSession().getAttribute(SessionUtils.PILEXAID);
        String[] params = new String[]{
                String.valueOf(model.getModelId()), String.valueOf(expId), model.getValue(),
                String.valueOf(model.getOrderId()), String.valueOf(model.getId())
        };
        String str = StringUtils.appendUrl(params);
        long total = mExampleService.savePilExampleModel(str);
        if (total > 0) {  //查询预期结果并添加
            str = StringUtils.appendUrl(String.valueOf(total));
            List<PilExpResult> datas = mModelService.selectExpModelIdByExpResult(str);
            if (datas != null && datas.size() > 0) {
                for (PilExpResult rs : datas) {
                    params = new String[]{
                            String.valueOf(total), String.valueOf(rs.getId()),
                            StringUtils.isEmpty(rs.getDetails()) ? "" : rs.getDetails(),
                            StringUtils.isEmpty(rs.geteValue()) ? "" : rs.geteValue()
                    };
                    str = StringUtils.appendUrl(params);
                    mExampleService.updatePilModelResult(str);
                }
                total = 1;
            } else {
                total = 1;
            }
        }
        UserItem user = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
        String strs = StringUtils.appendUrl(new String[]{
                String.valueOf(user.getId()),
                JSON.toJSONString(Utils.SaveLog("渠道用例列表-模块配置", user.getShowName(), old, model)),
                JurisdictionUtils.PILINGLISTVIEWLOG, Utils.getIpConfig(request)
        });
        mSystemLogService.saveSystemLogInfo(strs);
        return String.valueOf(total);
    }

    /**
     * 分页查询渠道用例模块
     *
     * @param page
     * @param rows
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/findPilExampleModelAll")
    @ResponseBody
    public ResponseItem<PilExaModel> findPilExampleModelAll(String page, String rows, HttpServletRequest request) throws Exception {
        int expId = (int) request.getSession().getAttribute(SessionUtils.PILEXAID);
        ResponseItem<PilExaModel> result = new ResponseItem<>();
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt(rows) == 10 ? 50 : Integer.parseInt(rows));
        String[] params = new String[]{
                String.valueOf(expId), String.valueOf(pageItem.getIndex()), String.valueOf(pageItem.getPageSize())
        };
        String str = StringUtils.appendUrl(params);
        List<PilExaModel> lists = mExampleService.findPilExampleModelAll(str);
        for (PilExaModel model : lists) {
            model.setValue(model.getValue().replace("<", "&lt;"));
            model.setValue(model.getValue().replace(">", "&gt;"));
        }
        long total = mExampleService.findPilExampleModelCount(str);
        result.setRows(lists);
        result.setTotal(total);
        return result;
    }

    /**
     * 渠道用例模块禁用和启用
     *
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/updatePilExaModelOpenClose")
    @ResponseBody
    public String updatePilExaModelOpenClose(int id, HttpServletRequest request) throws Exception {
        int expId = (int) request.getSession().getAttribute(SessionUtils.PILEXAID);
        String[] params = new String[]{
                String.valueOf(expId), String.valueOf(id)
        };
        String str = StringUtils.appendUrl(params);
        long total = mExampleService.updatePilExaModelOpenClose(str);
        return String.valueOf(total);
    }

    /**
     * 按ID查询渠道用例模块
     *
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/findPilExampleModelById")
    @ResponseBody
    public ResponseItem<PilExaModel> findPilExampleModelById(int id, HttpServletRequest request) throws Exception {
        int expId = (int) request.getSession().getAttribute(SessionUtils.PILEXAID);
        ResponseItem<PilExaModel> result = new ResponseItem<>();
        String[] params = new String[]{
                String.valueOf(expId), String.valueOf(id)
        };
        String str = StringUtils.appendUrl(params);
        PilExaModel model = mExampleService.findPilExampleModelById(str);
        result.setObj(model);
        return result;
    }

    /**
     * 通过ID查看渠道模块下的预期结果
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/findPilExampleModelByResultId")
    @ResponseBody
    public ResponseItem<PilExaModel> findPilExampleModelByResultId(String page, String rows, HttpServletRequest request) throws Exception {
        int id = (int) request.getSession().getAttribute(SessionUtils.PILEXAMODELID);
        ResponseItem<PilExaModel> result = new ResponseItem<>();
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt(rows) == 10 ? 50 : Integer.parseInt(rows));
        String[] params = new String[]{
                String.valueOf(id), String.valueOf(pageItem.getIndex()), String.valueOf(pageItem.getPageSize())
        };
        String str = StringUtils.appendUrl(params);
        List<PilExaModel> datas = mExampleService.findPilExampleModelByResultId(str);
        long total = mExampleService.findPilExampleModelByResultCount(str);
        result.setRows(datas);
        result.setTotal(total);
        return result;
    }

    /**
     * 加载渠道用例模块ID
     *
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/initPilExaModelId")
    @ResponseBody
    public String initPilExaModelId(int id, HttpServletRequest request) throws Exception {
        request.getSession().setAttribute(SessionUtils.PILEXAMODELID, id);
        return "1";
    }

    /**
     * 修改预期结果的值
     *
     * @param resultId
     * @param resultVal
     * @return
     * @throws Exception
     */
    @RequestMapping("/updatePilModelResult")
    @ResponseBody
    public String updatePilModelResult(int id, int resultId, String detail, String resultVal) throws Exception {
        String[] params = new String[]{
                String.valueOf(id), String.valueOf(resultId),
                StringUtils.isEmpty(detail) ? "" : detail,
                StringUtils.isEmpty(resultVal) ? "" : resultVal
        };
        String str = StringUtils.appendUrl(params);
        long total = mExampleService.updatePilModelResult(str);
        return String.valueOf(total);
    }

    /**
     * 删除用例下的模块信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/deletePilExampleModelById")
    @ResponseBody
    public String deletePilExampleModelById(int id) throws Exception {
        long total = mExampleService.deletePilExampleModelById(String.valueOf(id));
        return String.valueOf(total);
    }

    /**
     * 立即执行渠道用例
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/pilExpExecute")
    @ResponseBody
    public String pilExpExecute(String id, String platId, HttpServletRequest request) throws Exception {
        List<PilExeItem> datas = new ArrayList<>();
        PmanageItem item = mPlatform.selectPlatformManageByIdToName(Integer.parseInt(platId)).getObj();
        String path = "python " + item.getExeUrl() + " -v";
        String message = StringUtils.executePython(id, path).getObj();
        message = StringUtils.wrapStr(message, 20);
        datas.add(new PilExeItem(Integer.valueOf(id), message));
        request.getSession().setAttribute(SessionUtils.PILEXECUTE, datas);
        return "1";
    }

    /**
     * 批量执行渠道用例
     *
     * @param request
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping("/batchPilExpExecute")
    @ResponseBody
    public String batchPilExpExecute(HttpServletRequest request, String ids, String paths) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        try {
            System.out.println("**********************1");
            String[] arr = ids.split("\\|");
            String[] ps = paths.split("\\|");
            Callable call = new PilExpExecuteThread(request, arr, ps);
            Future future = pool.submit(call);
            System.out.println("**********************2");
            return (String) future.get();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        } finally {
            pool.shutdown();
        }
    }

    /**
     * 批量渠道用例测试线程
     */
    private class PilExpExecuteThread implements Callable<String> {
        private Lock lock = new ReentrantLock();  //线程锁
        private List<PilExeItem> datas = new ArrayList<>();
        //        private final String path = "python /root/Desktop/HttpService/Merchant_order/Main_function.py -v";
        private String[] ids;
        private String[] paths;
        private HttpServletRequest mRequest;

        public PilExpExecuteThread(HttpServletRequest request, String[] ids, String[] paths) {
            this.ids = ids;
            this.paths = paths;
            this.mRequest = request;
        }

        @Override
        public String call() throws Exception {
            for (int i = 0; i < ids.length; i++) {
                lock.lock();
                PmanageItem item = mPlatform.selectPlatformManageByIdToName(Integer.parseInt(paths[i])).getObj();
                String path = "python " + item.getExeUrl() + " -v";
                String message = StringUtils.executePython(ids[i], path).getObj();
                if (!StringUtils.isEmpty(message)) {
                    message = StringUtils.wrapStr(message, 20);
                    datas.add(new PilExeItem(Integer.valueOf(ids[i]), message));
                    lock.unlock();
                }
            }
            mRequest.getSession().setAttribute(SessionUtils.PILEXECUTE, datas);
            return "1";
        }
    }
}
