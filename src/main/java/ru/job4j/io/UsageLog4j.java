package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte byteValue = 127;
        short shortValue = 32767;
        int intValue = 65566655;
        long longValue = 345660678878L;
        float floatValue = 3.14f;
        double doubleValue = 12447657123.44d;
        char charValue = 'a';
        boolean boolValue = true;
        LOG.debug("byte : {}, short : {}, int : {}, long : {}",
                byteValue, shortValue, intValue, longValue);
        LOG.debug("float : {}, double : {}", floatValue, doubleValue);
        LOG.debug("char : {}", charValue);
        LOG.debug("boolean : {}", boolValue);
    }
}