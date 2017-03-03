package com.epam.mp.classloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CustomClassReloader extends ClassLoader {

    private Map<String, Class> loadedClassesMap = new HashMap<>();

    private static final Logger log = LoggerFactory.getLogger(CustomClassloader.class);

    @Override
    public Class<?> loadClass(String path) throws ClassNotFoundException {
        log.info("Custom loader " + new Date());
        String className = extractClassName(path);
        Class loadedClass = loadClass(path, className);
        log.info("Class {} has been loaded!", className);

        return loadedClass;
    }

    private Class loadClass(String path, String className) throws ClassNotFoundException {
        Class loadedClass = loadClassAtFirst(path, className);
        loadedClassesMap.put(className, loadedClass);
        return loadedClass;
    }

    private Class loadClassAtFirst(String path, String className) throws ClassNotFoundException {
        String urlPath = path.replace(className, "");
        URLClassLoader urlClassLoader = configureUrlClassLoader(urlPath);
        Class loadedClass = urlClassLoader.loadClass(className);
        loadedClassesMap.put(className, loadedClass);
        return loadedClass;
    }

    private URLClassLoader configureUrlClassLoader(String urlPath) {
        URLClassLoader urlClassLoader = null;
        try {
            urlClassLoader = URLClassLoader.newInstance(new URL[]{new URL("file:///" + urlPath)});
        } catch (MalformedURLException e) {
            log.error(e.getMessage());
        }
        return urlClassLoader;
    }

    private String extractClassName(String path) {
        String[] pathArray = path.split("/");
        return pathArray[pathArray.length - 1];
    }
}
