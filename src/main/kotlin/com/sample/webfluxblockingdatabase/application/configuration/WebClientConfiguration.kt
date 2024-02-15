package com.sample.webfluxblockingdatabase.application.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfiguration(
    @Value("\${user.service.url}") private val userClientBaseUrl: String,
    @Value("\${product.service.url}") private val productClientBaseUrl: String
) {

    @Primary
    @Bean
    fun defaultWebClient(): WebClient {
        return WebClient
            .builder()
            .build()
    }

    @Bean
    fun userWebClient(): WebClient {
        return WebClient
            .builder()
            .baseUrl(userClientBaseUrl)
            .build()
    }

    @Bean
    fun productWebClient(): WebClient {
        return WebClient
            .builder()
            .baseUrl(productClientBaseUrl)
            .build()
    }
}