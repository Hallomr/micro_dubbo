package com.zxk.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "gateway")
@Component
@RefreshScope
public class WhiteListProperties {
    private List<String> whitelist;
}
