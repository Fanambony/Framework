/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2023.framework.utils;

import etu2023.framework.Mapping;
import etu2023.framework.annotation.Annotation;
import etu2023.framework.annotation.Scope;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Benji
 */
public class Utils {
    public static List<Class> getClassFrom(String packages) throws Exception{
        String path = packages.replaceAll("[.]", "\\\\");
        URL packagesUrl = Thread.currentThread().getContextClassLoader().getResource(path);
        File packDir = new File(packagesUrl.toURI());
        File[] inside = packDir.listFiles();
        List<Class> liste = new ArrayList<>();
        for(File f:inside) {
            String c = packages+ "" +f.getName().substring(0, f.getName().lastIndexOf("."));
            liste.add(Class.forName(c));
        }
        return liste;
    }
    
    public static HashMap<String, Mapping> getMappingUrls(Class<?> c) {
        HashMap<String, Mapping> mappingUrls = new HashMap<>();
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] a = method.getAnnotationsByType(Annotation.class);
            if (a.length > 0) {
                mappingUrls.put(a[0].value(), new Mapping(c.getSimpleName(), method.getName()));
            }
        }
        return mappingUrls;
    }
    
    public static boolean isSingleton(Class<?> c) {
        Scope sc = c.getAnnotation(Scope.class);
        return sc != null && "singleton".equals(sc.value());
    }
}
