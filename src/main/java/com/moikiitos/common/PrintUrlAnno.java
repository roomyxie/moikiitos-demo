package com.moikiitos.common;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PrintUrlAnno {
    /**
     * layer
     **/
    public String layer() default "";

    /**
     * function description
     **/
    public String description() default "";
}
