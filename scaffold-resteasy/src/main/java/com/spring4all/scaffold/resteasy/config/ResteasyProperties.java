package com.spring4all.scaffold.resteasy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author fangzhibin
 */

@ConfigurationProperties(prefix= "scaffold.rest")
public class ResteasyProperties {

    private boolean enableToken;

    public boolean isEnableToken() {
        return enableToken;
    }

    public void setEnableToken(boolean enableToken) {
        this.enableToken = enableToken;
    }
}
