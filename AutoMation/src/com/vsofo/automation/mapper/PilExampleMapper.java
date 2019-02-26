package com.vsofo.automation.mapper;

import com.vsofo.automation.entity.PilExaModel;
import com.vsofo.automation.entity.PilExample;

import java.util.List;
import java.util.Set;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/6/6
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/6/6
 * @修改描述:
 */
public interface PilExampleMapper {
    long savePilExample(String value) throws Exception;

    List<PilExample> findPilExampleAll(String value) throws Exception;

    long findPilExampleCount(String value) throws Exception;

    long updateExaOpenClose(String value) throws Exception;

    PilExample findPilExampleById(String value) throws Exception;

    long savePilExampleModel(String value) throws Exception;

    List<PilExaModel> findPilExampleModelAll(String value) throws Exception;

    long findPilExampleModelCount(String value) throws Exception;

    long updatePilExaModelOpenClose(String value) throws Exception;

    PilExaModel findPilExampleModelById(String value) throws Exception;

    List<PilExaModel> findPilExampleModelByResultId(String value) throws Exception;

    long findPilExampleModelByResultCount(String value) throws Exception;

    long updatePilModelResult(String value) throws Exception;

    long deletePilExampleModelById(String value) throws Exception;
}
