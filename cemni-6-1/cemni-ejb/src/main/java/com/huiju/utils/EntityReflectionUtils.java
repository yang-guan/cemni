package com.huiju.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.huiju.module.util.ReflectionUtils;

/**
 * @author Administrator
 */
public class EntityReflectionUtils extends ReflectionUtils {
    
    /**
     * 获取类的所有属性名称，包含父类的属性，但不包含private static final修饰的属性
     * 
     * @param clazz
     * @return list
     */
    @SuppressWarnings("rawtypes")
    public static List<String> getAllFieldNames(Class clazz, String... excludes) {
        List<String> list = new ArrayList<String>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            //排除private static final修饰的属性 且非 protected static final修饰的属性，如serialVersionUID
            if (f.getModifiers() != 26 && f.getModifiers() != 28) {
                if (excludes != null && excludes.length > 0) {
                    for (String excludesName : excludes) {
                        if (f.getName().equals(excludesName)) {
                            continue;
                        }
                        list.add(f.getName());
                    }
                } else {
                    list.add(f.getName());
                }
            }
        }
        if (clazz.getSuperclass() != Object.class) {
            list.addAll(0, getAllFieldNames(clazz.getSuperclass(), excludes));
        }
        return list;
    }
    
}
