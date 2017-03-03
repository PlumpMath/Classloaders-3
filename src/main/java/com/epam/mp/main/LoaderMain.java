package com.epam.mp.main;

import com.epam.mp.classloader.CustomClassloader;
import com.epam.mp.reader.GenericConsoleReader;
import com.epam.mp.reader.impl.ConsoleTaskReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LoaderMain {

    private static final Logger log = LoggerFactory.getLogger(LoaderMain.class);

    private static CustomClassloader classloader = new CustomClassloader();


    public static void main(String[] args) throws Exception {
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext("com.epam.mp.*");

        GenericConsoleReader genericConsoleReader = ctx.getBean(ConsoleTaskReader.class);
        log.info("Enter path to class: ");
        Class loadedClass = classloader.loadClass(genericConsoleReader.readConsoleTask());
        loadedClass.getMethods()[0].invoke(loadedClass.newInstance());


    }


}
