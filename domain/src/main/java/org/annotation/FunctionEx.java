package org.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import java.lang.annotation.Inherited;

@Target(value=ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FunctionEx {

	  String code();
	  
	  String name() default "";
	  
	  String parentCd();
	  
	  String link() default "";
	  
	  String target() default "";
	  
	  String appCd() default ""; 
}
