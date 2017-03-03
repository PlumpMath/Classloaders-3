package com.epam.mp.reader;

import java.io.IOException;

public interface GenericConsoleReader {

    String GREETING_LINE = "Enter path to compiled class: ";

    String readConsoleTask() throws IOException;

}
