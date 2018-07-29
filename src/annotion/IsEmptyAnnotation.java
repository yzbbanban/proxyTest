package annotion;

import java.lang.annotation.*;

/**
 * Created by brander on 2018/7/29
 */
@Documented
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsEmptyAnnotation {
    boolean isEmpty() default true;
    String message() default "字段不能为空";
}
