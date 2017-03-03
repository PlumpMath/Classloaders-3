package com.epam.mp.main;

import com.epam.mp.classloader.CustomClassReloader;
import com.epam.mp.reader.GenericConsoleReader;
import com.epam.mp.reader.impl.ConsoleTaskReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class ReloaderMain {

    private static final Logger log = LoggerFactory.getLogger(ReloaderMain.class);

    private static CustomClassReloader classReloader = new CustomClassReloader();

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext("com.epam.mp.*");

        GenericConsoleReader genericConsoleReader = ctx.getBean(ConsoleTaskReader.class);


        Class loadedClass;
        while (true) {
            log.info("Choose Hello World version: ");
            String response = genericConsoleReader.readConsoleTask();
            if (response.equals("1")) {
                loadedClass = classReloader.
                        loadClass("C:/Users/Luka_Chapukhin/IdeaProjects/Classloaders/src/main/resources/HelloWorldImpl");
            } else {
                loadedClass = classReloader.loadClass("C:/Users/Luka_Chapukhin/IdeaProjects/Classloaders/src/main/" +
                        "resources/HelloWonderfulWorld");
            }

            loadedClass.getMethods()[0].invoke(loadedClass.newInstance());
        }
    }

}
