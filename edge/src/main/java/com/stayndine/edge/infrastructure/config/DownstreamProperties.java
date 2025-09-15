package com.stayndine.edge.infrastructure.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "downstream")
public class DownstreamProperties {

    private final Service identity = new Service();
    private final Service customers = new Service();

    @Getter
    @Setter
    public static class Service {
        @NotBlank
        private String baseUrl;

        @NotBlank
        private String apiKey;
    }
}
