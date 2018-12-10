package com.dj.api

import com.dj.api.Repository.PreorderRepository
import org.hamcrest.Matchers.`is`
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.junit.Assert.*
import org.springframework.cloud.contract.wiremock.WireMockRestServiceServer
import org.springframework.web.client.RestTemplate

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class PreorderControllerIntTests {

    @Autowired
    lateinit var restTemplate: RestTemplate

    @Autowired
    lateinit var mvc: MockMvc

    @Test
    fun testKinguinApi() {

        WireMockRestServiceServer.with(restTemplate)
                .baseUrl("https://api.kinguin.net")
                .stubs("classpath:/stubs/get-preorders.stub.json")
                .build()

        val client = PreorderRepository(restTemplate)
        assertEquals(client.findPreorders()[0].id, 117570)
    }

    @Test
    fun whenGetPreorders_thenResponse200AndJson() {
        mvc.perform(
            get("/api/v2/preorders?page=2&size=3&sort=regularPrice"))
            .andExpect(status().isOk)
            .andExpect(content().string("{\n" +
                    "  \"_embedded\" : {\n" +
                    "    \"preorderResources\" : [ {\n" +
                    "      \"entityId\" : 51503,\n" +
                    "      \"name\" : \"Doom Eternal EU PRE-ORDER Steam CD Key\",\n" +
                    "      \"imageUrl\" : \"https://cdns.kinguin.net/media/catalog/category/cache/1/image/173x120/17f82f742ffe127f42dca9de82fb58b1/doom_male.jpg\",\n" +
                    "      \"regularPrice\" : null,\n" +
                    "      \"minPrice\" : 44.99,\n" +
                    "      \"_links\" : {\n" +
                    "        \"self\" : {\n" +
                    "          \"href\" : \"http://localhost/api/v2/preorders/51503\"\n" +
                    "        }\n" +
                    "      }\n" +
                    "    }, {\n" +
                    "      \"entityId\" : 51515,\n" +
                    "      \"name\" : \"Devil May Cry 5 PRE-ORDER EU Steam CD Key\",\n" +
                    "      \"imageUrl\" : \"https://cdns.kinguin.net/media/catalog/category/cache/1/image/173x120/17f82f742ffe127f42dca9de82fb58b1/devil_male.jpg\",\n" +
                    "      \"regularPrice\" : null,\n" +
                    "      \"minPrice\" : 43.99,\n" +
                    "      \"_links\" : {\n" +
                    "        \"self\" : {\n" +
                    "          \"href\" : \"http://localhost/api/v2/preorders/51515\"\n" +
                    "        }\n" +
                    "      }\n" +
                    "    }, {\n" +
                    "      \"entityId\" : 51518,\n" +
                    "      \"name\" : \"Star Wars: Jedi Fallen Order PRE-ORDER Origin CD Key\",\n" +
                    "      \"imageUrl\" : \"https://cdns.kinguin.net/media/catalog/category/cache/1/image/173x120/17f82f742ffe127f42dca9de82fb58b1/star_wars_male.jpg\",\n" +
                    "      \"regularPrice\" : null,\n" +
                    "      \"minPrice\" : 55.34,\n" +
                    "      \"_links\" : {\n" +
                    "        \"self\" : {\n" +
                    "          \"href\" : \"http://localhost/api/v2/preorders/51518\"\n" +
                    "        }\n" +
                    "      }\n" +
                    "    } ]\n" +
                    "  },\n" +
                    "  \"_links\" : {\n" +
                    "    \"first\" : {\n" +
                    "      \"href\" : \"http://localhost/api/v2/preorders?page=0&size=3&sort=regularPrice,asc\"\n" +
                    "    },\n" +
                    "    \"prev\" : {\n" +
                    "      \"href\" : \"http://localhost/api/v2/preorders?page=1&size=3&sort=regularPrice,asc\"\n" +
                    "    },\n" +
                    "    \"self\" : {\n" +
                    "      \"href\" : \"http://localhost/api/v2/preorders?page=2&size=3&sort=regularPrice,asc\"\n" +
                    "    },\n" +
                    "    \"next\" : {\n" +
                    "      \"href\" : \"http://localhost/api/v2/preorders?page=3&size=3&sort=regularPrice,asc\"\n" +
                    "    },\n" +
                    "    \"last\" : {\n" +
                    "      \"href\" : \"http://localhost/api/v2/preorders?page=10&size=3&sort=regularPrice,asc\"\n" +
                    "    }\n" +
                    "  },\n" +
                    "  \"page\" : {\n" +
                    "    \"size\" : 3,\n" +
                    "    \"totalElements\" : 32,\n" +
                    "    \"totalPages\" : 11,\n" +
                    "    \"number\" : 2\n" +
                    "  }\n" +
                    "}"))
    }

    @Test
    fun whenGetPreorder_thenResponse200AndJson() {
        mvc.perform(
                get("/api/v2/preorders/51503"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("name", `is`("Doom Eternal EU PRE-ORDER Steam CD Key")))
    }
}
