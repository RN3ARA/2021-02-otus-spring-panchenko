package org.hell.homework04.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@Data
@ConfigurationProperties(prefix = "application")
public class AppProps {
    private String questionCsvPath;
    private Locale locale;
}
