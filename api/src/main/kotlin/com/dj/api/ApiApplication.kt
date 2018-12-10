package com.dj.api

import org.apache.http.conn.ssl.NoopHostnameVerifier
import org.apache.http.impl.client.HttpClients
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

@SpringBootApplication
@EnableCaching
class ApiApplication {
    @Bean
    fun restTemplate(): RestTemplate {
        val httpClient = HttpClients.custom()
                .setSSLHostnameVerifier(NoopHostnameVerifier())
                .build()
        val reqFactory = HttpComponentsClientHttpRequestFactory()
        reqFactory.httpClient = httpClient

        return RestTemplate(reqFactory)
    }
}

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}