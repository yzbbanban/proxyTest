package annotion;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by brander on 2018/7/29
 */
public class AnnotationDealUtil {
    private static Logger log = Logger.getAnonymousLogger();

    public static Map<String, Object> validate(Object bean) {
        Map<String, Object> result = new HashMap<>();
        result.put("message", "验证通过");
        result.put("result", true);
        Class<?> cls = bean.getClass();
        try {
            Field[] fields = cls.getDeclaredFields();
            for (Field f : fields) {
                //获取私有的属性
                f.setAccessible(true);
                //获取字段值
                Object value = f.get(bean);
                Annotation[] arrayAno = f.getAnnotations();
                for (Annotation annotation : arrayAno) {
                    //获取注解类型（注解类的Class）
                    Class<?> clz = annotation.annotationType();
                    Method[] methodArray = clz.getDeclaredMethods();
                    for (Method m : methodArray) {
                        String methodName = m.getName();
                        if ("message".equals(methodName)) {
                            continue;
                        }
                        Object obj = AnnotationDealUtil.class.newInstance();

                        try {
                            Method method = obj.getClass().getDeclaredMethod(methodName, Object.class, Field.class);
                            result = (Map<String, Object>) method.invoke(obj, value, f);
                            if (result.get("result").equals(false)) {
                                return result;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            log.info("找不到该方法:" + methodName);
                        }

                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("验证出错");

        }
        return result;
    }


    public Map<String, Object> isEmpty(Object value, Field field) {
        Map<String, Object> validateResult = new HashMap<>();
        IsEmptyAnnotation annotation = field.getAnnotation(IsEmptyAnnotation.class);
        if (value == null || "".equals(value)) {
            validateResult.put("message", field.getName() + annotation.message());
            validateResult.put("result", false);
        } else {
            validateResult.put("message", "通过验证");
            validateResult.put("result", true);
        }
        return validateResult;
    }
}
