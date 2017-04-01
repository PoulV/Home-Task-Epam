package com.tests.rtlabs.exceptions;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;

/**
 * Created by 1 on 01.04.2017.
 */
public class ExceptionUtils {

    private static final Logger logger = Logger.getLogger(ExceptionUtils.class);

    public static void throwCustomIOException(Exception ioException, String filePath) {
        Class trueClassOfIOException = ioException.getClass();
        StringBuilder messageBuilder = new StringBuilder();
        if (trueClassOfIOException.equals(FileNotFoundException.class)) {
            messageBuilder.append("File \"")
                    .append(filePath)
                    .append("\" is not found!");

            logger.error(messageBuilder.toString(), ioException);
            throw new CustomIOException(messageBuilder.toString(), ioException);
        } else {
            messageBuilder.append("Raise ")
                    .append(trueClassOfIOException.toString())
                    .append(" when try work with \"")
                    .append(filePath)
                    .append("\"!");
            logger.error(messageBuilder.toString(), ioException);
            throw new CustomIOException(messageBuilder.toString(), ioException);
        }
    }

}
