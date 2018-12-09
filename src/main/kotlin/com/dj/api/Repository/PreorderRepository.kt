package com.dj.api.Repository

import org.springframework.web.client.RestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.util.UriComponentsBuilder

@Service
class PreorderRepository(val restTemplate: RestTemplate) {

    fun findPreorders(page: Int?, pageSize: Int = 10, sortBy: String? = null, sortDir: String? = "ASC") : List<PreorderEntity>? {

        val builder = UriComponentsBuilder.fromHttpUrl("https://api.kinguin.net/v1/catalog/preorders")
                .queryParam("page", page)
                .queryParam("limit", pageSize)
                .queryParam("order", sortBy)
                .queryParam("dir", sortDir)

        val response = restTemplate.exchange<List<PreorderEntity>>(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                object : ParameterizedTypeReference<List<PreorderEntity>>(){},
                hashMapOf(page to "page")
        )
        return response.body
    }

    fun findPreorder(id: Int) : PreorderEntity? {
        val response = restTemplate.exchange<List<PreorderEntity>>(
                "https://api.kinguin.net/v1/catalog/preorders?filter\\[entity_id\\]=7570",
                HttpMethod.GET,
                null,
                object : ParameterizedTypeReference<List<PreorderEntity>>(){}
        )
        return response.body?.get(0)
    }

}
