package com.epam.mp.reader.impl;

import com.epam.mp.reader.GenericConsoleReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class ConsoleTaskReader implements GenericConsoleReader {

    private static final Logger log = LoggerFactory.getLogger(ConsoleTaskReader.class);

    private BufferedReader consoleBufferedReader = createConsoleReader();

    public String readConsoleTask() throws IOException {

        log.info(GREETING_LINE);
        return consoleBufferedReader.readLine();
    }

    private static BufferedReader createConsoleReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
