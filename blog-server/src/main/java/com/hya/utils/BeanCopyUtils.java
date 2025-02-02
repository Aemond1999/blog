package com.hya.utils;

import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class BeanCopyUtils {
    public static <T,O> T copyBean(O source, Class<T> clazz) {
        T result = null;
        try {
            result = clazz.getConstructor().newInstance();
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static <T,O> List<T> copyBeanList(List<O> source, Class<T> clazz) {
        return source
                .stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }
}
