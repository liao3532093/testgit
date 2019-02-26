package com.vsofo.automation.controller;

import com.vsofo.automation.entity.*;
import com.vsofo.automation.service.CaseStatisticsService;
import com.vsofo.automation.service.FinalResultService;
import com.vsofo.automation.service.ImplementService;
import com.vsofo.automation.service.SystemLogService;
import com.vsofo.automation.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/5/3
 * @类描述: 监控管理控制器Controller
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/5/3
 * @修改描述:
 */
@Controller
@RequestMapping("/monitor")
public class MonitorController {
    @Autowired
    private ImplementService mImplementService;  //执行详情
    @Autowired
    private CaseStatisticsService mCaseStatisticsService;  //用例平台统计
    @Autowired
    private FinalResultService mFinalResultService;
    //@Autowired
    //private SystemLogService mSystemLogService;

    /**
     * 分页查询执行详情
     *
     * @param page
     * @param rows
     * @param item
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectImplementInfoAll")
    @ResponseBody
    public ResponseItem<ImplementInfo> selectImplementInfoAll(String page, String rows, ImplementInfo item, HttpServletRequest request) throws Exception {
        String inDate = (String) request.getSession().getAttribute("inDate");
        String outDate = (String) request.getSession().getAttribute("outDate");
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt("10".equals(rows) ? "50" : rows));
        String[] datas = new String[]{
                String.valueOf(item.getPlatId()), String.valueOf(item.getInterId()), StringUtils.isEmpty(item.getdName()) ? "" : item.getdName(),
                String.valueOf(item.getState()), StringUtils.isEmpty(item.getInDate()) ? inDate : item.getInDate(),
                StringUtils.isEmpty(item.getOutDate()) ? outDate : item.getOutDate(), String.valueOf(pageItem.getIndex()),
                String.valueOf(pageItem.getPageSize())
        };
        String str = StringUtils.appendUrl(datas);
        ResponseItem<ImplementInfo> response = new ResponseItem<>();
        List<ImplementInfo> items = mImplementService.selectImplementInfoAll(str);
        long count = mImplementService.selectImplementInfoCount(str);
//        UserItem user = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
//        String strs = StringUtils.appendUrl(new String[]{
//                String.valueOf(user.getId()),
//                user.getShowName() + "查询执行详情",
//                JurisdictionUtils.CASECONFIGMANAGELOG
//        });
//        mSystemLogService.saveSystemLogInfo(strs);
        response.setRows(items);
        response.setTotal(count);
        return response;
    }

    /**
     * 分页查询执行详情
     *
     * @param page
     * @param rows
     * @param item
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectImplementInfoItemAll")
    @ResponseBody
    public ResponseItem<ImplementInfo> selectImplementInfoItemAll(String page, String rows, ImplementInfo item, HttpServletRequest request) throws Exception {
        ResponseItem<ImplementInfo> response = new ResponseItem<>();
        System.out.println(DataUtils.statistics);
        System.out.println(DataUtils.caseType);
        item.setPlatId(item.getPlatId() <= 0 ? DataUtils.statistics.getPlatID() : item.getPlatId());
        item.setInterId(item.getInterId() <= 0 ? DataUtils.statistics.getInterfaceID() : item.getInterId());
        item.setInDate(StringUtils.isEmpty(item.getInDate()) ? DataUtils.statistics.getExecTime() + " 00:00:00" : item.getInDate());
        item.setOutDate(StringUtils.isEmpty(item.getOutDate()) ? DataUtils.statistics.getExecTime() + " 23:59:59" : item.getOutDate());
        item.setState(DataUtils.caseType);
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt("10".equals(rows) ? "50" : rows));
        String[] datas = new String[]{
                String.valueOf(item.getPlatId()), String.valueOf(item.getInterId()), StringUtils.isEmpty(item.getdName()) ? "" : item.getdName(),
                String.valueOf(item.getState()), item.getInDate(), item.getOutDate(), String.valueOf(pageItem.getIndex()),
                String.valueOf(pageItem.getPageSize())
        };
        String str = StringUtils.appendUrl(datas);
        List<ImplementInfo> items = mImplementService.selectImplementInfoAll(str);
        long count = mImplementService.selectImplementInfoCount(str);
        response.setRows(items);
        response.setTotal(count);
        return response;
    }

    /**
     * 取得状态设置初始日期
     *
     * @param inDate
     * @param outDate
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/getImplementState")
    @ResponseBody
    public ResponseItem<List<Map<String, Object>>> getImplementState(String inDate, String outDate, HttpServletRequest request) throws Exception {
        ResponseItem<List<Map<String, Object>>> response = new ResponseItem<>();
        request.getSession().setAttribute("inDate", inDate);
        request.getSession().setAttribute("outDate", outDate);
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < Constant.implStateCode.length; i++) {
            Map<String, Object> data = new HashMap<>();
            data.put("code", Constant.implStateCode[i]);
            data.put("msg", Constant.implStateMsg[i]);
            list.add(data);
        }
        response.setObj(list);
        return response;
    }

    /**
     * 设置日期
     *
     * @param inDate
     * @param outDate
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/initCaseStatisticsDate")
    @ResponseBody
    public String initCaseStatisticsDate(String inDate, String outDate, HttpServletRequest request) throws Exception {
        request.getSession().setAttribute("inDate", inDate);
        request.getSession().setAttribute("outDate", outDate);
        return "";
    }

    /**
     * 通过平台ID取得统计信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/initCaseStatisticsById")
    @ResponseBody
    public String initCaseStatisticsById(String id, String type) throws Exception {
        DataUtils.caseType = Integer.parseInt(type);
        DataUtils.statistics = mCaseStatisticsService.selectCaseStatisticsById(String.valueOf(id));
        return "-1";
    }

    /**
     * 分页查询用例平台统计
     *
     * @param page
     * @param rows
     * @param item
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectCaseStatisticsAll")
    @ResponseBody
    public ResponseItem<CaseStatistics> selectCaseStatisticsAll(String page, String rows, CaseStatistics item, HttpServletRequest request) throws Exception {
        ResponseItem<CaseStatistics> response = new ResponseItem<>();
        String inDate = (String) request.getSession().getAttribute("inDate");
        String outDate = (String) request.getSession().getAttribute("outDate");
        item.setInDate(StringUtils.isEmpty(item.getInDate()) ? inDate : item.getInDate());
        item.setOutDate(StringUtils.isEmpty(item.getOutDate()) ? outDate : item.getOutDate());
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt("10".equals(rows) ? "50" : rows));
        String[] datas = new String[]{
                String.valueOf(item.getPlatID()), item.getInDate(), item.getOutDate(),
                String.valueOf(pageItem.getIndex()), String.valueOf(pageItem.getPageSize())
        };
        String str = StringUtils.appendUrl(datas);
        List<CaseStatistics> lists = mCaseStatisticsService.selectCaseStatisticsSize(StringUtils.appendUrl(new String[]{
                item.getInDate(), item.getOutDate()
        }));
        List<CaseStatistics> items = mCaseStatisticsService.selectCaseStatisticsAll(str);
        long count = mCaseStatisticsService.selectCaseStatisticsCount(str);
        response.setRows(items);
        response.setTotal(count);
//        UserItem user = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
//        String strs = StringUtils.appendUrl(new String[]{
//                String.valueOf(user.getId()),
//                user.getShowName() + "查询用例平台统计",
//                JurisdictionUtils.CASECONFIGMANAGELOG
//        });
//        mSystemLogService.saveSystemLogInfo(strs);
        return response;
    }

    /**
     * 分页查询用例接口统计
     *
     * @param page
     * @param rows
     * @param item
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectCaseInterStatisticsAll")
    @ResponseBody
    public ResponseItem<CaseStatistics> selectCaseInterStatisticsAll(String page, String rows, CaseStatistics item, HttpServletRequest request) throws Exception {
        ResponseItem<CaseStatistics> response = new ResponseItem<>();
        String inDate = (String) request.getSession().getAttribute("inDate");
        String outDate = (String) request.getSession().getAttribute("outDate");
        item.setInDate(StringUtils.isEmpty(item.getInDate()) ? inDate : item.getInDate());
        item.setOutDate(StringUtils.isEmpty(item.getOutDate()) ? outDate : item.getOutDate());
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt("10".equals(rows) ? "50" : rows));
        String[] datas = new String[]{
                String.valueOf(item.getPlatID()), String.valueOf(item.getInterfaceID()),
                item.getInDate(), item.getOutDate(),
                String.valueOf(pageItem.getIndex()), String.valueOf(pageItem.getPageSize())
        };
        String str = StringUtils.appendUrl(datas);
        List<CaseStatistics> lists = mCaseStatisticsService.selectCaseStatisticsSize(StringUtils.appendUrl(new String[]{
                item.getInDate(), item.getOutDate()
        }));
        List<CaseStatistics> items = mCaseStatisticsService.selectCaseInterStatisticsAll(str);
        long count = mCaseStatisticsService.selectCaseInterStatisticsCount(str);
        response.setRows(items);
        response.setTotal(count);
//        UserItem user = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
//        String strs = StringUtils.appendUrl(new String[]{
//                String.valueOf(user.getId()),
//                user.getShowName() + "查询用例接口统计",
//                JurisdictionUtils.CASECONFIGMANAGELOG
//        });
//        mSystemLogService.saveSystemLogInfo(strs);
        return response;
    }

    /**
     * 分页查询用例接口统计
     *
     * @param page
     * @param rows
     * @param item
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectCaseInterStatisticsItemAll")
    @ResponseBody
    public ResponseItem<CaseStatistics> selectCaseInterStatisticsItemAll(String page, String rows, CaseStatistics item, HttpServletRequest request) throws Exception {
        ResponseItem<CaseStatistics> response = new ResponseItem<>();
        item.setPlatID(item.getPlatID() <= 0 ? DataUtils.statistics.getPlatID() : item.getPlatID());
        item.setInterfaceID(item.getInterfaceID() <= 0 ? DataUtils.statistics.getInterfaceID() : item.getInterfaceID());
        item.setInDate(StringUtils.isEmpty(item.getInDate()) ? DataUtils.statistics.getExecTime() + " 00:00:00" : item.getInDate());
        item.setOutDate(StringUtils.isEmpty(item.getOutDate()) ? DataUtils.statistics.getExecTime() + " 23:59:59" : item.getOutDate());
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt("10".equals(rows) ? "50" : rows));
        String[] datas = new String[]{
                String.valueOf(item.getPlatID()), String.valueOf(item.getInterfaceID()),
                item.getInDate(), item.getOutDate(),
                String.valueOf(pageItem.getIndex()), String.valueOf(pageItem.getPageSize())
        };
        String str = StringUtils.appendUrl(datas);
        List<CaseStatistics> lists = mCaseStatisticsService.selectCaseStatisticsSize(StringUtils.appendUrl(new String[]{
                item.getInDate(), item.getOutDate()
        }));
        List<CaseStatistics> items = mCaseStatisticsService.selectCaseInterStatisticsAll(str);
        for (CaseStatistics it : items) {
            for (CaseStatistics is : lists) {
                if (it.getId() == is.getId()) {
                    it.setCaseCount(is.getCaseCount());
                }
            }
        }
        long count = mCaseStatisticsService.selectCaseInterStatisticsCount(str);
        response.setRows(items);
        response.setTotal(count);
        return response;
    }

    /**
     * 加载执行结果信息
     *
     * @param id
     * @param cid
     * @return
     * @throws Exception
     */
    @RequestMapping("/getFinalResultInfo")
    @ResponseBody
    public String getFinalResultInfo(int id, int cid, HttpServletRequest request) throws Exception {
        String cName = mFinalResultService.selectCaseDicName(String.valueOf(cid));
        request.getSession().setAttribute("cName", cName);
        List<FinalResult> params = mFinalResultService.selectCaseDicParams(String.valueOf(id));
        for (FinalResult res : params) {
            //res.setRequestURL(StringUtils.wrapStr(res.getRequestURL(), 60));
            res.setRequestParam(res.getRequestParam().replace("<", "&lt;"));
            res.setRequestParam(res.getRequestParam().replace(">", "&gt;"));
            res.setReturnInfo(res.getReturnInfo().replace("<", "&lt;"));
            res.setReturnInfo(res.getReturnInfo().replace(">", "&gt;"));
            //res.setReturnInfo(StringUtils.wrapStr(res.getReturnInfo(), 60));
        }
        request.getSession().setAttribute("params", params);
        List<FinalResult> infos = mFinalResultService.selectExpTypeInfo(String.valueOf(id));
        //        for (FinalResult res : infos) {
        //            res.settResult(StringUtils.wrapStr(res.gettResult(), 20));
        //            System.out.println(res.gettResult());
        //        }
        request.getSession().setAttribute("infos", infos);
        return "1";
    }
}
