package com.stayndine.edge.infrastructure.config;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.util.Timeout;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientsConfig {

    private HttpComponentsClientHttpRequestFactory httpClientFactory(int connectSeconds, int responseSeconds) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(Timeout.ofSeconds(connectSeconds))
                .setResponseTimeout(Timeout.ofSeconds(responseSeconds))
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Bean
    RestClient identityRestClient(DownstreamProperties props) {
        return RestClient.builder()
                .baseUrl(props.getIdentity().getBaseUrl())
                .defaultHeader("X-Internal-Api-Key", props.getIdentity().getApiKey())
                .requestFactory(httpClientFactory(3, 10))
                .build();
    }

    @Bean
    RestClient customersRestClient(DownstreamProperties props) {
        return RestClient.builder()
                .baseUrl(props.getCustomers().getBaseUrl())
                .defaultHeader("X-Internal-Api-Key", props.getCustomers().getApiKey())
                .requestFactory(httpClientFactory(3, 10))
                .build();
    }
}
