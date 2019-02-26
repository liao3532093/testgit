package com.vsofo.automation.service;

import com.vsofo.automation.entity.PilExpResult;
import com.vsofo.automation.entity.PilingModel;

import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/6/5
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/6/5
 * @修改描述:
 */
public interface PilingModelService {
    long savePilingModelInfo(String value) throws Exception;

    List<PilingModel> selectPilingModelAll(String value) throws Exception;

    long selectPilingModelCount(String value) throws Exception;

    long updateOpenClose(String value) throws Exception;

    PilingModel selectPilingModelById(String value) throws Exception;

    List<PilingModel> findPilingModelAll(String value) throws Exception;

    long savePilModelExpResult(String value) throws Exception;

    List<PilExpResult> selectPilModelExpResultAll(String value) throws Exception;

    long selectPilModelExpResultCount(String value) throws Exception;

    long updateModelOpenClose(String value) throws Exception;

    PilExpResult selectPilModelExpResultById(String value) throws Exception;

    List<PilExpResult> selectExpModelIdByExpResult(String value) throws Exception;
}
