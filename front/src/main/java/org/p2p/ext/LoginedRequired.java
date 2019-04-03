package org.p2p.ext;

import java.lang.annotation.*;

/*
 * 用户是否登录判断的注解(annotation)<br/>
 * 此注解表示在调用此Controller方法前需要先
 * 系统登录.默认登录方式为表单提交(有刷新)
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginedRequired {
    RequestTypeEnum value() default RequestTypeEnum.PAGE;
}
