package com.vsofo.automation.utils;

import java.lang.annotation.*;

/**
 * @创建者: liaowenjun
 * @创建时间: 2018/5/2
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2018/5/2
 * @修改描述:
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface LogInfo {
    String[] key() default {};

    String[] val() default {};
}
