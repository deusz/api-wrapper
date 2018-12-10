package com.dj.api.Repository

import org.springframework.cache.annotation.Cacheable
import org.springframework.web.client.RestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service

@Service
class PreorderRepository(val restTemplate: RestTemplate) {

    @Cacheable("preorders")
    fun findPreorders() : List<PreorderEntity> =
            restTemplate.exchange<List<PreorderEntity>>(
                    "https://api.kinguin.net/v1/catalog/preorders?limit=100",
                    HttpMethod.GET,
                    null,
                    object : ParameterizedTypeReference<List<PreorderEntity>>(){}
            ).body.orEmpty()

}
