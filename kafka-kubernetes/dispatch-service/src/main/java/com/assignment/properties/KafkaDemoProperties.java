package com.assignment.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Configuration
@ConfigurationProperties("kafka")
@Getter
@Setter
@Validated
public class KafkaDemoProperties {
    @NotNull
    private String outboundTopic;
}
