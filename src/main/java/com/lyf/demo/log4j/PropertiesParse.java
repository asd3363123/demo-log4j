package com.lyf.demo.log4j;

import org.apache.logging.log4j.core.config.properties.PropertiesConfiguration;
import org.apache.logging.log4j.core.config.properties.PropertiesConfigurationBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesParse {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(PropertiesParse.class.getResourceAsStream("/log4j2.properties"));
        PropertiesConfigurationBuilder builder = new PropertiesConfigurationBuilder();
        builder.setRootProperties(properties);

        PropertiesConfiguration configuration = builder.build();
        System.out.println(configuration);

    }
}
