package com.dj.api.Repository

import com.dj.api.Model.PreorderEntity
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.client.RestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service

@Service
class PreorderRepository(val restTemplate: RestTemplate) {

    var logger = LoggerFactory.getLogger(PreorderRepository::class.java)

    @Cacheable("preorders")
    fun findPreorders() : List<PreorderEntity> {
        return restTemplate.exchange<List<PreorderEntity>>(
                "https://api.kinguin.net/v1/catalog/preorders?limit=100",
                HttpMethod.GET,
                null,
                object : ParameterizedTypeReference<List<PreorderEntity>>(){}
        ).body.orEmpty()
    }

}
