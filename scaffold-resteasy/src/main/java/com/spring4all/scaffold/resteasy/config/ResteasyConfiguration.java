package com.spring4all.scaffold.resteasy.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.spring4all.scaffold.common.BaseConstants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.core.Application;
import java.text.SimpleDateFormat;

/**
 * @author fangzhibin
 */
@Configuration
public class ResteasyConfiguration {

    @Bean
    public JacksonConfig jacksonConfig() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat(BaseConstants.DATE_FORMAT_UTC));
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return new JacksonConfig(objectMapper);
    }

    @Bean
    @ConditionalOnMissingBean(Application.class)
    public Application jaxRsApplication() {
        return new JaxRsApplication();
    }
}
