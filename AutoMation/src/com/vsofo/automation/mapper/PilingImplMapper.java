package com.vsofo.automation.mapper;

import com.vsofo.automation.entity.FinalResult;
import com.vsofo.automation.entity.PilingImplement;

import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/6/8
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/6/8
 * @修改描述:
 */
public interface PilingImplMapper {
    List<PilingImplement> findPilImplementAll(String value) throws Exception;

    long findPilImplementCount(String value) throws Exception;

    List<FinalResult> findPilModelInfoById(String value) throws Exception;

    List<FinalResult> findPilModelInfoByIdResult(String value) throws Exception;

    List<FinalResult> findPilModelInfoByIdResultBody(String value) throws Exception;
}
