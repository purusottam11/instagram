package com.purusottam.instagram.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CopyUtils {

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static <T> T copySafe(Object src, T target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
        return target;
    }

    public static <T> List<T> copySafe(List<?> srcList, Class<T> targetType) {
        List<T> list = new ArrayList<>();
        for (Object src : srcList) {
            T target;
            try {
                target = targetType.newInstance();
                list.add(copySafe(src, target));
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}