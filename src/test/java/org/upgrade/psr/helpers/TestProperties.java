package org.upgrade.psr.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestProperties {
    private static Properties testProperties;
    private static String PROPERTY_FILE_NAME = "performancetest.properties";

    public TestProperties() {
    }

    public static String getAsString(String key) {
        if (testProperties == null) {
            init();
        }

        String value = testProperties.getProperty(key);
        if (value == null) {
            throw new RuntimeException(String.format("Could not find property %s in %s", key, PROPERTY_FILE_NAME));
        } else {
            return value;
        }
    }

    public static int getAsInt(String key) {
        return Integer.parseInt(getAsString(key));
    }

    public static Boolean getAsBool(String key) {
        return Boolean.parseBoolean(getAsString(key));
    }

    public static void setProperty(String key, String value) {
        if (testProperties == null) {
            init();
        }

        testProperties.setProperty(key, value);
    }

    private static void init() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        testProperties = new Properties();

        try {
            InputStream resourceStream = loader.getResourceAsStream(PROPERTY_FILE_NAME);
            Throwable var2 = null;

            try {
                testProperties.load(resourceStream);
                testProperties.putAll(System.getProperties());
            } catch (Throwable var12) {
                var2 = var12;
                throw var12;
            } finally {
                if (resourceStream != null) {
                    if (var2 != null) {
                        try {
                            resourceStream.close();
                        } catch (Throwable var11) {
                            var2.addSuppressed(var11);
                        }
                    } else {
                        resourceStream.close();
                    }
                }

            }
        } catch (IOException var14) {
            var14.printStackTrace();
        }

    }
}
