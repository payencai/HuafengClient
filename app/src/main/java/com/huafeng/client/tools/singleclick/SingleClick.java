package com.huafeng.client.tools.singleclick;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作者：凌涛 on 2019/5/9 12:16
 * 邮箱：771548229@qq..com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SingleClick{
    long value() default 1000;
}
