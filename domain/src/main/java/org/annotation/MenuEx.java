package org.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ java.lang.annotation.ElementType.METHOD,
		java.lang.annotation.ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MenuEx {
	String code();

	String name() default "";

	String parentCd() default "";

	String link() default "";

	String target() default "";

	String smallIco() default "";

	String bigIco() default "";

	String appCd() default "";

	int listSort() default 0;
}
